package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
final /* synthetic */ class zzad implements RemoteCall {
    static final RemoteCall zza = new zzad();

    private zzad() {
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        ((TaskCompletionSource) obj2).setResult(((com.google.android.gms.internal.location.zzaz) obj).zzA());
    }
}
