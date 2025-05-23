package com.google.android.gms.common.api;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class PendingResults {
    private PendingResults() {
    }

    @NonNull
    public static PendingResult<Status> canceledPendingResult() {
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.cancel();
        return statusPendingResult;
    }

    @NonNull
    @KeepForSdk
    public static <R extends Result> PendingResult<R> immediateFailedResult(@NonNull R r9, @NonNull GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(r9, "Result must not be null");
        Preconditions.checkArgument(!r9.getStatus().isSuccess(), "Status code must not be SUCCESS");
        zag zagVar = new zag(googleApiClient, r9);
        zagVar.setResult(r9);
        return zagVar;
    }

    @NonNull
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(@NonNull R r9) {
        Preconditions.checkNotNull(r9, "Result must not be null");
        zah zahVar = new zah(null);
        zahVar.setResult(r9);
        return new OptionalPendingResultImpl(zahVar);
    }

    @NonNull
    public static <R extends Result> PendingResult<R> canceledPendingResult(@NonNull R r9) {
        Preconditions.checkNotNull(r9, "Result must not be null");
        Preconditions.checkArgument(r9.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
        zaf zafVar = new zaf(r9);
        zafVar.cancel();
        return zafVar;
    }

    @NonNull
    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(@NonNull R r9, @NonNull GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(r9, "Result must not be null");
        zah zahVar = new zah(googleApiClient);
        zahVar.setResult(r9);
        return new OptionalPendingResultImpl(zahVar);
    }

    @NonNull
    public static PendingResult<Status> immediatePendingResult(@NonNull Status status) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }

    @NonNull
    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(@NonNull Status status, @NonNull GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(googleApiClient);
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }
}
