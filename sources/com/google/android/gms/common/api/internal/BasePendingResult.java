package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import com.mbridge.msdk.foundation.entity.o;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk
@KeepName
/* loaded from: classes2.dex */
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    static final ThreadLocal zaa = new zaq();
    public static final /* synthetic */ int zad = 0;

    @KeepName
    private zas resultGuardian;

    @NonNull
    protected final CallbackHandler zab;

    @NonNull
    protected final WeakReference zac;
    private final Object zae;
    private final CountDownLatch zaf;
    private final ArrayList zag;

    @Nullable
    private ResultCallback zah;
    private final AtomicReference zai;

    @Nullable
    private Result zaj;
    private Status zak;
    private volatile boolean zal;
    private boolean zam;
    private boolean zan;

    @Nullable
    private ICancelToken zao;
    private volatile zada zap;
    private boolean zaq;

    @Deprecated
    public BasePendingResult() {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(Looper.getMainLooper());
        this.zac = new WeakReference(null);
    }

    private final Result zaa() {
        Result result;
        synchronized (this.zae) {
            Preconditions.checkState(!this.zal, "Result has already been consumed.");
            Preconditions.checkState(isReady(), "Result is not ready.");
            result = this.zaj;
            this.zaj = null;
            this.zah = null;
            this.zal = true;
        }
        zadb zadbVar = (zadb) this.zai.getAndSet(null);
        if (zadbVar != null) {
            zadbVar.zaa.zab.remove(this);
        }
        return (Result) Preconditions.checkNotNull(result);
    }

    private final void zab(Result result) {
        this.zaj = result;
        this.zak = result.getStatus();
        this.zao = null;
        this.zaf.countDown();
        if (this.zam) {
            this.zah = null;
        } else {
            ResultCallback resultCallback = this.zah;
            if (resultCallback == null) {
                if (this.zaj instanceof Releasable) {
                    this.resultGuardian = new zas(this, null);
                }
            } else {
                this.zab.removeMessages(2);
                this.zab.zaa(resultCallback, zaa());
            }
        }
        ArrayList arrayList = this.zag;
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            ((PendingResult.StatusListener) arrayList.get(i9)).onComplete(this.zak);
        }
        this.zag.clear();
    }

    public static void zal(@Nullable Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e4) {
                Log.w("BasePendingResult", "Unable to release ".concat(String.valueOf(result)), e4);
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(@NonNull PendingResult.StatusListener statusListener) {
        boolean z8;
        if (statusListener != null) {
            z8 = true;
        } else {
            z8 = false;
        }
        Preconditions.checkArgument(z8, "Callback cannot be null.");
        synchronized (this.zae) {
            try {
                if (isReady()) {
                    statusListener.onComplete(this.zak);
                } else {
                    this.zag.add(statusListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    @ResultIgnorabilityUnspecified
    public final R await() {
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        Preconditions.checkState(!this.zal, "Result has already been consumed");
        Preconditions.checkState(this.zap == null, "Cannot await if then() has been called.");
        try {
            this.zaf.await();
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return (R) zaa();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public void cancel() {
        synchronized (this.zae) {
            if (!this.zam && !this.zal) {
                ICancelToken iCancelToken = this.zao;
                if (iCancelToken != null) {
                    try {
                        iCancelToken.cancel();
                    } catch (RemoteException unused) {
                    }
                }
                zal(this.zaj);
                this.zam = true;
                zab(createFailedResult(Status.RESULT_CANCELED));
            }
        }
    }

    @NonNull
    @KeepForSdk
    public abstract R createFailedResult(@NonNull Status status);

    @KeepForSdk
    @Deprecated
    public final void forceFailureUnlessReady(@NonNull Status status) {
        synchronized (this.zae) {
            try {
                if (!isReady()) {
                    setResult(createFailedResult(status));
                    this.zan = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final boolean isCanceled() {
        boolean z8;
        synchronized (this.zae) {
            z8 = this.zam;
        }
        return z8;
    }

    @KeepForSdk
    public final boolean isReady() {
        if (this.zaf.getCount() == 0) {
            return true;
        }
        return false;
    }

    @KeepForSdk
    public final void setCancelToken(@NonNull ICancelToken iCancelToken) {
        synchronized (this.zae) {
            this.zao = iCancelToken;
        }
    }

    @KeepForSdk
    public final void setResult(@NonNull R r9) {
        synchronized (this.zae) {
            try {
                if (!this.zan && !this.zam) {
                    isReady();
                    Preconditions.checkState(!isReady(), "Results have already been set");
                    Preconditions.checkState(!this.zal, "Result has already been consumed");
                    zab(r9);
                    return;
                }
                zal(r9);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public final void setResultCallback(@Nullable ResultCallback<? super R> resultCallback) {
        synchronized (this.zae) {
            try {
                if (resultCallback == null) {
                    this.zah = null;
                    return;
                }
                boolean z8 = true;
                Preconditions.checkState(!this.zal, "Result has already been consumed.");
                if (this.zap != null) {
                    z8 = false;
                }
                Preconditions.checkState(z8, "Cannot set callbacks if then() has been called.");
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.zab.zaa(resultCallback, zaa());
                } else {
                    this.zah = resultCallback;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        boolean z8;
        TransformedResult<S> then;
        Preconditions.checkState(!this.zal, "Result has already been consumed.");
        synchronized (this.zae) {
            try {
                boolean z9 = false;
                if (this.zap == null) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                Preconditions.checkState(z8, "Cannot call then() twice.");
                if (this.zah == null) {
                    z9 = true;
                }
                Preconditions.checkState(z9, "Cannot call then() if callbacks are set.");
                Preconditions.checkState(!this.zam, "Cannot call then() if result was canceled.");
                this.zaq = true;
                this.zap = new zada(this.zac);
                then = this.zap.then(resultTransform);
                if (isReady()) {
                    this.zab.zaa(this.zap, zaa());
                } else {
                    this.zah = this.zap;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return then;
    }

    public final void zak() {
        boolean z8 = true;
        if (!this.zaq && !((Boolean) zaa.get()).booleanValue()) {
            z8 = false;
        }
        this.zaq = z8;
    }

    public final boolean zam() {
        boolean isCanceled;
        synchronized (this.zae) {
            try {
                if (((GoogleApiClient) this.zac.get()) != null) {
                    if (!this.zaq) {
                    }
                    isCanceled = isCanceled();
                }
                cancel();
                isCanceled = isCanceled();
            } catch (Throwable th) {
                throw th;
            }
        }
        return isCanceled;
    }

    public final void zan(@Nullable zadb zadbVar) {
        this.zai.set(zadbVar);
    }

    /* loaded from: classes2.dex */
    public static class CallbackHandler<R extends Result> extends com.google.android.gms.internal.base.zau {
        public CallbackHandler() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public final void handleMessage(@NonNull Message message) {
            int i9 = message.what;
            if (i9 != 1) {
                if (i9 != 2) {
                    Log.wtf("BasePendingResult", o.h(i9, "Don't know how to handle message: "), new Exception());
                    return;
                } else {
                    ((BasePendingResult) message.obj).forceFailureUnlessReady(Status.RESULT_TIMEOUT);
                    return;
                }
            }
            Pair pair = (Pair) message.obj;
            ResultCallback resultCallback = (ResultCallback) pair.first;
            Result result = (Result) pair.second;
            try {
                resultCallback.onResult(result);
            } catch (RuntimeException e4) {
                BasePendingResult.zal(result);
                throw e4;
            }
        }

        public final void zaa(@NonNull ResultCallback resultCallback, @NonNull Result result) {
            int i9 = BasePendingResult.zad;
            sendMessage(obtainMessage(1, new Pair((ResultCallback) Preconditions.checkNotNull(resultCallback), result)));
        }

        public CallbackHandler(@NonNull Looper looper) {
            super(looper);
        }
    }

    @KeepForSdk
    @Deprecated
    public BasePendingResult(@NonNull Looper looper) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(looper);
        this.zac = new WeakReference(null);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    @ResultIgnorabilityUnspecified
    public final R await(long j7, @NonNull TimeUnit timeUnit) {
        if (j7 > 0) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }
        Preconditions.checkState(!this.zal, "Result has already been consumed.");
        Preconditions.checkState(this.zap == null, "Cannot await if then() has been called.");
        try {
            if (!this.zaf.await(j7, timeUnit)) {
                forceFailureUnlessReady(Status.RESULT_TIMEOUT);
            }
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return (R) zaa();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public final void setResultCallback(@NonNull ResultCallback<? super R> resultCallback, long j7, @NonNull TimeUnit timeUnit) {
        synchronized (this.zae) {
            try {
                if (resultCallback == null) {
                    this.zah = null;
                    return;
                }
                boolean z8 = true;
                Preconditions.checkState(!this.zal, "Result has already been consumed.");
                if (this.zap != null) {
                    z8 = false;
                }
                Preconditions.checkState(z8, "Cannot set callbacks if then() has been called.");
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.zab.zaa(resultCallback, zaa());
                } else {
                    this.zah = resultCallback;
                    CallbackHandler callbackHandler = this.zab;
                    callbackHandler.sendMessageDelayed(callbackHandler.obtainMessage(2, this), timeUnit.toMillis(j7));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public BasePendingResult(@Nullable GoogleApiClient googleApiClient) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zac = new WeakReference(googleApiClient);
    }

    @KeepForSdk
    public BasePendingResult(@NonNull CallbackHandler<R> callbackHandler) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = (CallbackHandler) Preconditions.checkNotNull(callbackHandler, "CallbackHandler must not be null");
        this.zac = new WeakReference(null);
    }
}
