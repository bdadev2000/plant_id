package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdy;

/* loaded from: classes3.dex */
final class zzfh extends zzdy.zza {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzdy.zzd zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfh(zzdy.zzd zzdVar, Activity activity) {
        super(zzdy.this);
        this.zzc = activity;
        this.zzd = zzdVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdy.zza
    public final void zza() throws RemoteException {
        zzdj zzdjVar;
        zzdjVar = zzdy.this.zzj;
        ((zzdj) Preconditions.checkNotNull(zzdjVar)).onActivityResumed(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}
