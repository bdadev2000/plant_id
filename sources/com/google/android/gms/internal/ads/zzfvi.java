package com.google.android.gms.internal.ads;

import android.os.IBinder;

/* loaded from: classes3.dex */
final class zzfvi extends zzfwm {
    private IBinder zza;
    private String zzb;
    private int zzc;
    private float zzd;
    private int zze;
    private String zzf;
    private byte zzg;

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwm zza(String str) {
        this.zzf = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwm zzb(String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwm zzc(int i2) {
        this.zzg = (byte) (this.zzg | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwm zzd(int i2) {
        this.zzc = i2;
        this.zzg = (byte) (this.zzg | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwm zze(float f2) {
        this.zzd = f2;
        this.zzg = (byte) (this.zzg | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwm zzf(int i2) {
        this.zzg = (byte) (this.zzg | 8);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwm zzg(IBinder iBinder) {
        if (iBinder == null) {
            throw new NullPointerException("Null windowToken");
        }
        this.zza = iBinder;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwm zzh(int i2) {
        this.zze = i2;
        this.zzg = (byte) (this.zzg | 16);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfwm
    public final zzfwn zzi() {
        IBinder iBinder;
        if (this.zzg == 31 && (iBinder = this.zza) != null) {
            return new zzfvk(iBinder, this.zzb, this.zzc, this.zzd, 0, 0, null, this.zze, null, this.zzf, null, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" windowToken");
        }
        if ((this.zzg & 1) == 0) {
            sb.append(" layoutGravity");
        }
        if ((this.zzg & 2) == 0) {
            sb.append(" layoutVerticalMargin");
        }
        if ((this.zzg & 4) == 0) {
            sb.append(" displayMode");
        }
        if ((this.zzg & 8) == 0) {
            sb.append(" triggerMode");
        }
        if ((this.zzg & 16) == 0) {
            sb.append(" windowWidthPx");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
