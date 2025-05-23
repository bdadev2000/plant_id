package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import androidx.annotation.Nullable;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzfay {
    private final zzbxu zza;
    private final int zzb;

    public zzfay(zzbxu zzbxuVar, int i9) {
        this.zza = zzbxuVar;
        this.zzb = i9;
    }

    public final int zza() {
        return this.zzb;
    }

    @Nullable
    public final PackageInfo zzb() {
        return this.zza.zzf;
    }

    public final String zzc() {
        return this.zza.zzd;
    }

    public final String zzd() {
        return zzfyv.zzc(this.zza.zza.getString("ms"));
    }

    public final String zze() {
        return this.zza.zzh;
    }

    public final List zzf() {
        return this.zza.zze;
    }

    public final boolean zzg() {
        return this.zza.zzl;
    }

    public final boolean zzh() {
        return this.zza.zza.getBoolean("is_gbid");
    }

    public final boolean zzi() {
        return this.zza.zzk;
    }
}
