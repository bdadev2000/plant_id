package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.VideoController;

/* loaded from: classes2.dex */
public final class zzfj extends zzds {
    private final VideoController.VideoLifecycleCallbacks zza;

    public zzfj(VideoController.VideoLifecycleCallbacks videoLifecycleCallbacks) {
        this.zza = videoLifecycleCallbacks;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zze() {
        this.zza.onVideoEnd();
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zzf(boolean z8) {
        this.zza.onVideoMute(z8);
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zzg() {
        this.zza.onVideoPause();
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zzh() {
        this.zza.onVideoPlay();
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zzi() {
        this.zza.onVideoStart();
    }
}
