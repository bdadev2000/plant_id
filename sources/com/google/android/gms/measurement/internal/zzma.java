package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class zzma implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzls zzb;

    public zzma(zzls zzlsVar, zzo zzoVar) {
        this.zza = zzoVar;
        this.zzb = zzlsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgb zzgbVar;
        zzgbVar = this.zzb.zzb;
        if (zzgbVar == null) {
            this.zzb.zzj().zzg().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzgbVar.zze(this.zza);
        } catch (RemoteException e) {
            this.zzb.zzj().zzg().zza("Failed to reset data on the service: remote exception", e);
        }
        this.zzb.zzar();
    }
}
