package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
public enum zzgo implements zzcu {
    BROADCAST_ACTION_UNSPECIFIED(0),
    PURCHASES_UPDATED_ACTION(1),
    LOCAL_PURCHASES_UPDATED_ACTION(2),
    ALTERNATIVE_BILLING_ACTION(3);

    private static final zzcv zze = new zzcv() { // from class: com.google.android.gms.internal.play_billing.zzgm
    };
    private final int zzg;

    zzgo(int i9) {
        this.zzg = i9;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzg);
    }
}
