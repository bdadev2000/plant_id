package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public abstract class zzaz extends com.google.android.gms.internal.location.zzb implements zzba {
    public zzaz() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static zzba zzb(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        if (queryLocalInterface instanceof zzba) {
            return (zzba) queryLocalInterface;
        }
        return new zzay(iBinder);
    }

    @Override // com.google.android.gms.internal.location.zzb
    public final boolean zza(int i9, Parcel parcel, Parcel parcel2, int i10) throws RemoteException {
        if (i9 != 1) {
            if (i9 != 2) {
                return false;
            }
            zze((LocationAvailability) com.google.android.gms.internal.location.zzc.zzb(parcel, LocationAvailability.CREATOR));
        } else {
            zzd((LocationResult) com.google.android.gms.internal.location.zzc.zzb(parcel, LocationResult.CREATOR));
        }
        return true;
    }
}
