package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.MainThread;

/* loaded from: classes3.dex */
public final class zzhi implements ServiceConnection {
    final /* synthetic */ zzhf zza;
    private final String zzb;

    public zzhi(zzhf zzhfVar, String str) {
        this.zza = zzhfVar;
        this.zzb = str;
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zza.zza.zzj().zzu().zza("Install Referrer connection returned with null binder");
            return;
        }
        try {
            com.google.android.gms.internal.measurement.zzbz zza = com.google.android.gms.internal.measurement.zzby.zza(iBinder);
            if (zza == null) {
                this.zza.zza.zzj().zzu().zza("Install Referrer Service implementation was not found");
            } else {
                this.zza.zza.zzj().zzp().zza("Install Referrer Service connected");
                this.zza.zza.zzl().zzb(new zzhh(this, zza, this));
            }
        } catch (RuntimeException e) {
            this.zza.zza.zzj().zzu().zza("Exception occurred while calling Install Referrer API", e);
        }
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzj().zzp().zza("Install Referrer Service disconnected");
    }
}
