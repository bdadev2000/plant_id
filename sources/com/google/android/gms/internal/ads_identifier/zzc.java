package com.google.android.gms.internal.ads_identifier;

import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzc {
    private static final ClassLoader zza = zzc.class.getClassLoader();

    private zzc() {
    }

    public static void zza(Parcel parcel, boolean z8) {
        parcel.writeInt(1);
    }

    public static boolean zzb(Parcel parcel) {
        if (parcel.readInt() != 0) {
            return true;
        }
        return false;
    }
}
