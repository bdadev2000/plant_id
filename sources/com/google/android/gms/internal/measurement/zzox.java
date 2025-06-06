package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzox implements zzoy {
    private static final zzgz<Boolean> zza;

    static {
        zzhh zza2 = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.sdk.collection.enable_extend_user_property_size", true);
        zza2.zza("measurement.sdk.collection.last_deep_link_referrer2", true);
        zza = zza2.zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zza2.zza("measurement.id.sdk.collection.last_deep_link_referrer2", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzoy
    public final boolean zza() {
        return zza.zza().booleanValue();
    }
}
