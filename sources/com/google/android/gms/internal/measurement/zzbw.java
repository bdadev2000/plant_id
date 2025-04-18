package com.google.android.gms.internal.measurement;

import android.os.BadParcelableException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.d;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class zzbw {
    private static final ClassLoader zza = zzbw.class.getClassLoader();

    private zzbw() {
    }

    public static <T extends Parcelable> T zza(Parcel parcel, Parcelable.Creator<T> creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return creator.createFromParcel(parcel);
    }

    public static void zzb(Parcel parcel) {
        int dataAvail = parcel.dataAvail();
        if (dataAvail > 0) {
            throw new BadParcelableException(d.i("Parcel data not fully consumed, unread size: ", dataAvail));
        }
    }

    public static boolean zzc(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static HashMap zza(Parcel parcel) {
        return parcel.readHashMap(zza);
    }

    public static void zza(Parcel parcel, boolean z2) {
        parcel.writeInt(z2 ? 1 : 0);
    }

    public static void zza(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void zza(Parcel parcel, IInterface iInterface) {
        if (iInterface == null) {
            parcel.writeStrongBinder(null);
        } else {
            parcel.writeStrongBinder(iInterface.asBinder());
        }
    }

    public static void zzb(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 1);
        }
    }
}
