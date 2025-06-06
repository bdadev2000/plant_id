package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public final class zzcbb extends zzbad implements zzcbd {
    public zzcbb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.signals.ISignalCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzcbd
    public final void zzb(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzdc(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcbd
    public final void zzc(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbaf.zzd(zza, bundle);
        zzdc(3, zza);
    }
}
