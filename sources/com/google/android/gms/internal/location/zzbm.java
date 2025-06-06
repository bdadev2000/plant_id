package com.google.android.gms.internal.location;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class zzbm {
    public static int zza(int i9, int i10, @NullableDecl String str) {
        String zza;
        if (i9 >= 0 && i9 < i10) {
            return i9;
        }
        if (i9 >= 0) {
            if (i10 < 0) {
                StringBuilder sb = new StringBuilder(26);
                sb.append("negative size: ");
                sb.append(i10);
                throw new IllegalArgumentException(sb.toString());
            }
            zza = zzbn.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i9), Integer.valueOf(i10));
        } else {
            zza = zzbn.zza("%s (%s) must not be negative", "index", Integer.valueOf(i9));
        }
        throw new IndexOutOfBoundsException(zza);
    }

    public static int zzb(int i9, int i10, @NullableDecl String str) {
        if (i9 >= 0 && i9 <= i10) {
            return i9;
        }
        throw new IndexOutOfBoundsException(zzd(i9, i10, "index"));
    }

    public static void zzc(int i9, int i10, int i11) {
        String zzd;
        if (i9 >= 0 && i10 >= i9 && i10 <= i11) {
            return;
        }
        if (i9 >= 0 && i9 <= i11) {
            if (i10 >= 0 && i10 <= i11) {
                zzd = zzbn.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i10), Integer.valueOf(i9));
            } else {
                zzd = zzd(i10, i11, "end index");
            }
        } else {
            zzd = zzd(i9, i11, "start index");
        }
        throw new IndexOutOfBoundsException(zzd);
    }

    private static String zzd(int i9, int i10, @NullableDecl String str) {
        if (i9 < 0) {
            return zzbn.zza("%s (%s) must not be negative", str, Integer.valueOf(i9));
        }
        if (i10 >= 0) {
            return zzbn.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i9), Integer.valueOf(i10));
        }
        StringBuilder sb = new StringBuilder(26);
        sb.append("negative size: ");
        sb.append(i10);
        throw new IllegalArgumentException(sb.toString());
    }
}
