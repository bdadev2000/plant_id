package com.mbridge.msdk.out;

/* loaded from: classes3.dex */
public interface OnMBMediaViewListener {
    void onEnterFullscreen();

    void onExitFullscreen();

    void onFinishRedirection(Campaign campaign, String str);

    void onRedirectionFailed(Campaign campaign, String str);

    void onStartRedirection(Campaign campaign, String str);

    void onVideoAdClicked(Campaign campaign);

    void onVideoStart();
}
