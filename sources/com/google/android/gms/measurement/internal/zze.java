package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zze extends zzf {
    private boolean zza;

    public zze(zzhj zzhjVar) {
        super(zzhjVar);
        this.zzu.zzaa();
    }

    public final void zzu() {
        if (zzy()) {
        } else {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzv() {
        if (!this.zza) {
            if (!zzz()) {
                this.zzu.zzz();
                this.zza = true;
                return;
            }
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    public final void zzw() {
        if (!this.zza) {
            zzx();
            this.zzu.zzz();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    public void zzx() {
    }

    public final boolean zzy() {
        if (this.zza) {
            return true;
        }
        return false;
    }

    public abstract boolean zzz();
}
