package com.google.android.gms.internal.ads;

/* loaded from: classes2.dex */
public final class zzeqh implements zzhkp {
    private final zzhlg zza;
    private final zzhlg zzb;
    private final zzhlg zzc;
    private final zzhlg zzd;
    private final zzhlg zze;

    public zzeqh(zzhlg zzhlgVar, zzhlg zzhlgVar2, zzhlg zzhlgVar3, zzhlg zzhlgVar4, zzhlg zzhlgVar5) {
        this.zza = zzhlgVar;
        this.zzb = zzhlgVar2;
        this.zzc = zzhlgVar3;
        this.zzd = zzhlgVar4;
        this.zze = zzhlgVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzhlg
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzeqf zzb() {
        zzgge zzggeVar = (zzgge) this.zza.zzb();
        zzgge zzggeVar2 = zzcci.zza;
        zzhkx.zzb(zzggeVar2);
        return new zzeqf(zzggeVar, zzggeVar2, ((zzcjj) this.zzc).zza(), ((zzczc) this.zzd).zza(), ((zzcsd) this.zze).zza());
    }
}
