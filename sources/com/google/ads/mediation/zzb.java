package com.google.ads.mediation;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AppEventListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;

@VisibleForTesting
/* loaded from: classes4.dex */
final class zzb extends AdListener implements AppEventListener, com.google.android.gms.ads.internal.client.zza {

    @VisibleForTesting
    final AbstractAdViewAdapter zza;

    @VisibleForTesting
    final MediationBannerListener zzb;

    public zzb(AbstractAdViewAdapter abstractAdViewAdapter, MediationBannerListener mediationBannerListener) {
        this.zza = abstractAdViewAdapter;
        this.zzb = mediationBannerListener;
    }

    @Override // com.google.android.gms.ads.AdListener
    public final void onAdClicked() {
        this.zzb.onAdClicked(this.zza);
    }

    @Override // com.google.android.gms.ads.AdListener
    public final void onAdClosed() {
        this.zzb.onAdClosed(this.zza);
    }

    @Override // com.google.android.gms.ads.AdListener
    public final void onAdFailedToLoad(LoadAdError loadAdError) {
        this.zzb.onAdFailedToLoad(this.zza, loadAdError);
    }

    @Override // com.google.android.gms.ads.AdListener
    public final void onAdLoaded() {
        this.zzb.onAdLoaded(this.zza);
    }

    @Override // com.google.android.gms.ads.AdListener
    public final void onAdOpened() {
        this.zzb.onAdOpened(this.zza);
    }

    @Override // com.google.android.gms.ads.admanager.AppEventListener
    public final void onAppEvent(String str, String str2) {
        this.zzb.zzb(this.zza, str, str2);
    }
}
