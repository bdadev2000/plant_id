package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;

/* loaded from: classes2.dex */
public final class zzcyq implements zzhkp {
    private final zzhlg zza;
    private final zzhlg zzb;
    private final zzhlg zzc;
    private final zzhlg zzd;
    private final zzhlg zze;
    private final zzhlg zzf;
    private final zzhlg zzg;
    private final zzhlg zzh;
    private final zzhlg zzi;
    private final zzhlg zzj;
    private final zzhlg zzk;
    private final zzhlg zzl;

    public zzcyq(zzhlg zzhlgVar, zzhlg zzhlgVar2, zzhlg zzhlgVar3, zzhlg zzhlgVar4, zzhlg zzhlgVar5, zzhlg zzhlgVar6, zzhlg zzhlgVar7, zzhlg zzhlgVar8, zzhlg zzhlgVar9, zzhlg zzhlgVar10, zzhlg zzhlgVar11, zzhlg zzhlgVar12) {
        this.zza = zzhlgVar;
        this.zzb = zzhlgVar2;
        this.zzc = zzhlgVar3;
        this.zzd = zzhlgVar4;
        this.zze = zzhlgVar5;
        this.zzf = zzhlgVar6;
        this.zzg = zzhlgVar7;
        this.zzh = zzhlgVar8;
        this.zzi = zzhlgVar9;
        this.zzj = zzhlgVar10;
        this.zzk = zzhlgVar11;
        this.zzl = zzhlgVar12;
    }

    @Override // com.google.android.gms.internal.ads.zzhlg
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzcyp zzb() {
        zzflt zzfltVar = (zzflt) this.zza.zzb();
        VersionInfoParcel zza = ((zzcjv) this.zzb).zza();
        ApplicationInfo zzb = ((zzdzq) this.zzc).zzb();
        String zzb2 = ((zzdzv) this.zzd).zzb();
        zzbeg zzbegVar = zzbep.zza;
        return new zzcyp(zzfltVar, zza, zzb, zzb2, com.google.android.gms.ads.internal.client.zzba.zza().zza(), (PackageInfo) this.zzf.zzb(), zzhko.zza(zzhla.zza(this.zzg)), ((zzcjh) this.zzh).zzb(), (String) this.zzi.zzb(), ((zzeya) this.zzj).zzb(), ((zzczc) this.zzk).zza(), (zzdeu) this.zzl.zzb());
    }
}
