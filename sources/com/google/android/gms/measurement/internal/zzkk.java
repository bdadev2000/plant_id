package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class zzkk implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzjq zzc;

    public zzkk(zzjq zzjqVar, AtomicReference atomicReference, boolean z2) {
        this.zza = atomicReference;
        this.zzb = z2;
        this.zzc = zzjqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zzo().zza(this.zza, this.zzb);
    }
}
