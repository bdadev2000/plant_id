package Y2;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.google.ads.mediation.mintegral.MintegralMediationAdapter;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MediationUtils;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.mbridge.msdk.out.BannerAdWithCodeListener;
import com.mbridge.msdk.out.BannerSize;
import com.mbridge.msdk.out.MBBannerView;
import com.mbridge.msdk.out.MBridgeIds;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class b extends BannerAdWithCodeListener implements MediationBannerAd {
    public final MediationBannerAdConfiguration b;

    /* renamed from: c, reason: collision with root package name */
    public final MediationAdLoadCallback f3759c;

    /* renamed from: d, reason: collision with root package name */
    public MBBannerView f3760d;

    /* renamed from: f, reason: collision with root package name */
    public MediationBannerAdCallback f3761f;

    public b(MediationBannerAdConfiguration mediationBannerAdConfiguration, MediationAdLoadCallback mediationAdLoadCallback) {
        this.b = mediationBannerAdConfiguration;
        this.f3759c = mediationAdLoadCallback;
    }

    public static BannerSize a(Context context, AdSize adSize) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AdSize(320, 50));
        arrayList.add(new AdSize(300, 250));
        arrayList.add(new AdSize(728, 90));
        AdSize findClosestSize = MediationUtils.findClosestSize(context, adSize, arrayList);
        BannerSize bannerSize = null;
        if (findClosestSize == null) {
            return null;
        }
        if (findClosestSize.equals(AdSize.BANNER)) {
            bannerSize = new BannerSize(4, 0, 0);
        }
        if (findClosestSize.equals(AdSize.MEDIUM_RECTANGLE)) {
            bannerSize = new BannerSize(2, 0, 0);
        }
        if (findClosestSize.equals(AdSize.LEADERBOARD)) {
            bannerSize = new BannerSize(3, findClosestSize.getWidth(), 0);
        }
        if (bannerSize == null) {
            return new BannerSize(5, findClosestSize.getWidth(), findClosestSize.getHeight());
        }
        return bannerSize;
    }

    @Override // com.mbridge.msdk.out.BannerAdListener
    public final void closeFullScreen(MBridgeIds mBridgeIds) {
        MediationBannerAdCallback mediationBannerAdCallback = this.f3761f;
        if (mediationBannerAdCallback != null) {
            mediationBannerAdCallback.onAdClosed();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAd
    public final View getView() {
        return this.f3760d;
    }

    @Override // com.mbridge.msdk.out.BannerAdListener
    public final void onClick(MBridgeIds mBridgeIds) {
        MediationBannerAdCallback mediationBannerAdCallback = this.f3761f;
        if (mediationBannerAdCallback != null) {
            mediationBannerAdCallback.reportAdClicked();
        }
    }

    @Override // com.mbridge.msdk.out.BannerAdListener
    public final void onCloseBanner(MBridgeIds mBridgeIds) {
        MediationBannerAdCallback mediationBannerAdCallback = this.f3761f;
        if (mediationBannerAdCallback != null) {
            mediationBannerAdCallback.onAdClosed();
        }
    }

    @Override // com.mbridge.msdk.out.BannerAdListener
    public final void onLeaveApp(MBridgeIds mBridgeIds) {
        MediationBannerAdCallback mediationBannerAdCallback = this.f3761f;
        if (mediationBannerAdCallback != null) {
            mediationBannerAdCallback.onAdLeftApplication();
        }
    }

    @Override // com.mbridge.msdk.out.BannerAdWithCodeListener
    public final void onLoadFailedWithCode(MBridgeIds mBridgeIds, int i9, String str) {
        AdError l = android.support.v4.media.session.a.l(i9, str);
        Log.w(MintegralMediationAdapter.TAG, l.toString());
        this.f3759c.onFailure(l);
    }

    @Override // com.mbridge.msdk.out.BannerAdListener
    public final void onLoadSuccessed(MBridgeIds mBridgeIds) {
        MediationAdLoadCallback mediationAdLoadCallback = this.f3759c;
        if (mediationAdLoadCallback != null) {
            this.f3761f = (MediationBannerAdCallback) mediationAdLoadCallback.onSuccess(this);
        }
    }

    @Override // com.mbridge.msdk.out.BannerAdListener
    public final void onLogImpression(MBridgeIds mBridgeIds) {
        MediationBannerAdCallback mediationBannerAdCallback = this.f3761f;
        if (mediationBannerAdCallback != null) {
            mediationBannerAdCallback.reportAdImpression();
        }
    }

    @Override // com.mbridge.msdk.out.BannerAdListener
    public final void showFullScreen(MBridgeIds mBridgeIds) {
        MediationBannerAdCallback mediationBannerAdCallback = this.f3761f;
        if (mediationBannerAdCallback != null) {
            mediationBannerAdCallback.onAdOpened();
        }
    }
}
