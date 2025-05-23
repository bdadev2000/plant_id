package com.google.android.gms.internal.ads;

/* loaded from: classes2.dex */
public enum zzhiw implements zzhbs {
    SAFE_BROWSING_URL_API_TYPE_UNSPECIFIED(0),
    PVER3_NATIVE(1),
    PVER4_NATIVE(2),
    ANDROID_SAFETYNET(3),
    FLYWHEEL(4),
    REAL_TIME(5),
    PVER5_NATIVE_REAL_TIME(6),
    ANDROID_SAFEBROWSING_REAL_TIME(7),
    ANDROID_SAFEBROWSING(8);

    private static final zzhbt zzj = new zzhbt() { // from class: com.google.android.gms.internal.ads.zzhiu
        @Override // com.google.android.gms.internal.ads.zzhbt
        public final /* synthetic */ zzhbs zza(int i9) {
            return zzhiw.zzb(i9);
        }
    };
    private final int zzl;

    zzhiw(int i9) {
        this.zzl = i9;
    }

    public static zzhiw zzb(int i9) {
        switch (i9) {
            case 0:
                return SAFE_BROWSING_URL_API_TYPE_UNSPECIFIED;
            case 1:
                return PVER3_NATIVE;
            case 2:
                return PVER4_NATIVE;
            case 3:
                return ANDROID_SAFETYNET;
            case 4:
                return FLYWHEEL;
            case 5:
                return REAL_TIME;
            case 6:
                return PVER5_NATIVE_REAL_TIME;
            case 7:
                return ANDROID_SAFEBROWSING_REAL_TIME;
            case 8:
                return ANDROID_SAFEBROWSING;
            default:
                return null;
        }
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzl);
    }

    @Override // com.google.android.gms.internal.ads.zzhbs
    public final int zza() {
        return this.zzl;
    }
}
