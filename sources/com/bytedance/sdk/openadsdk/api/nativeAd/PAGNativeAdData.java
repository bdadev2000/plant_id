package com.bytedance.sdk.openadsdk.api.nativeAd;

import android.view.View;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public interface PAGNativeAdData {

    /* loaded from: classes.dex */
    public enum PAGNativeMediaType {
        PAGNativeMediaTypeImage,
        PAGNativeMediaTypeVideo
    }

    @Nullable
    View getAdChoicesView();

    View getAdLogoView();

    String getButtonText();

    String getDescription();

    PAGImageItem getIcon();

    PAGNativeMediaType getMediaType();

    PAGMediaView getMediaView();

    String getTitle();
}
