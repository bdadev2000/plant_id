package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes4.dex */
public final class zzbdl extends zzbdm {
    private final com.google.android.gms.ads.internal.zzg zza;

    @Nullable
    private final String zzb;
    private final String zzc;

    public zzbdl(com.google.android.gms.ads.internal.zzg zzgVar, @Nullable String str, String str2) {
        this.zza = zzgVar;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // com.google.android.gms.internal.ads.zzbdn
    public final String zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbdn
    public final String zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzbdn
    public final void zzd(@Nullable IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            return;
        }
        this.zza.zza((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzbdn
    public final void zze() {
        this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzbdn
    public final void zzf() {
        this.zza.zzc();
    }
}
