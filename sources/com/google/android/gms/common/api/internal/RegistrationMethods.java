package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@KeepForSdk
/* loaded from: classes4.dex */
public class RegistrationMethods<A extends Api.AnyClient, L> {

    @NonNull
    @KeepForSdk
    public final RegisterListenerMethod<A, L> register;

    @NonNull
    public final UnregisterListenerMethod zaa;

    @NonNull
    public final Runnable zab;

    @KeepForSdk
    /* loaded from: classes4.dex */
    public static class Builder<A extends Api.AnyClient, L> {
        private RemoteCall zaa;
        private RemoteCall zab;
        private ListenerHolder zad;
        private Feature[] zae;
        private int zag;
        private Runnable zac = zacj.zaa;
        private boolean zaf = true;

        private Builder() {
        }

        @NonNull
        @KeepForSdk
        public RegistrationMethods<A, L> build() {
            Preconditions.checkArgument(this.zaa != null, "Must set register function");
            Preconditions.checkArgument(this.zab != null, "Must set unregister function");
            Preconditions.checkArgument(this.zad != null, "Must set holder");
            return new RegistrationMethods<>(new zack(this, this.zad, this.zae, this.zaf, this.zag), new zacl(this, (ListenerHolder.ListenerKey) Preconditions.checkNotNull(this.zad.getListenerKey(), "Key must not be null")), this.zac, null);
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> onConnectionSuspended(@NonNull Runnable runnable) {
            this.zac = runnable;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> register(@NonNull RemoteCall<A, TaskCompletionSource<Void>> remoteCall) {
            this.zaa = remoteCall;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> setAutoResolveMissingFeatures(boolean z2) {
            this.zaf = z2;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> setFeatures(@NonNull Feature... featureArr) {
            this.zae = featureArr;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> setMethodKey(int i2) {
            this.zag = i2;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> unregister(@NonNull RemoteCall<A, TaskCompletionSource<Boolean>> remoteCall) {
            this.zab = remoteCall;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder<A, L> withHolder(@NonNull ListenerHolder<L> listenerHolder) {
            this.zad = listenerHolder;
            return this;
        }

        public /* synthetic */ Builder(zacm zacmVar) {
        }
    }

    public /* synthetic */ RegistrationMethods(RegisterListenerMethod registerListenerMethod, UnregisterListenerMethod unregisterListenerMethod, Runnable runnable, zacn zacnVar) {
        this.register = registerListenerMethod;
        this.zaa = unregisterListenerMethod;
        this.zab = runnable;
    }

    @NonNull
    @KeepForSdk
    public static <A extends Api.AnyClient, L> Builder<A, L> builder() {
        return new Builder<>(null);
    }
}
