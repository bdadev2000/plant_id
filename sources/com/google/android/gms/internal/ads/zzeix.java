package com.google.android.gms.internal.ads;

import android.content.Context;

/* loaded from: classes2.dex */
public final class zzeix implements zzhkp {
    private final zzhlg zza;
    private final zzhlg zzb;

    public zzeix(zzhlg zzhlgVar, zzhlg zzhlgVar2) {
        this.zza = zzhlgVar;
        this.zzb = zzhlgVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzhlg
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzeiw zzb() {
        return new zzeiw((Context) this.zza.zzb(), (zzctg) this.zzb.zzb());
    }
}
