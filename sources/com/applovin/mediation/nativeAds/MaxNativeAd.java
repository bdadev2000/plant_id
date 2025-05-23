package com.applovin.mediation.nativeAds;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.applovin.impl.mediation.nativeAds.MaxNativeAdImpl;
import com.applovin.mediation.MaxAdFormat;
import java.util.List;

/* loaded from: classes.dex */
public class MaxNativeAd extends MaxNativeAdImpl {
    private static final float MINIMUM_STARS_TO_RENDER = 3.0f;
    private final String advertiser;
    private final String body;
    private final String callToAction;
    private final MaxAdFormat format;
    private final MaxNativeAdImage icon;
    private final View iconView;
    private boolean isExpired;
    private final MaxNativeAdImage mainImage;
    private final float mediaContentAspectRatio;
    private final View mediaView;
    private MaxNativeAdView nativeAdView;
    private final View optionsView;
    private final Double starRating;
    private final String title;

    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private MaxAdFormat f12708a;
        private String b;

        /* renamed from: c, reason: collision with root package name */
        private String f12709c;

        /* renamed from: d, reason: collision with root package name */
        private String f12710d;

        /* renamed from: e, reason: collision with root package name */
        private String f12711e;

        /* renamed from: f, reason: collision with root package name */
        private MaxNativeAdImage f12712f;

        /* renamed from: g, reason: collision with root package name */
        private View f12713g;

        /* renamed from: h, reason: collision with root package name */
        private View f12714h;

        /* renamed from: i, reason: collision with root package name */
        private View f12715i;

        /* renamed from: j, reason: collision with root package name */
        private MaxNativeAdImage f12716j;

        /* renamed from: k, reason: collision with root package name */
        private float f12717k;
        private Double l;

        public MaxNativeAd build() {
            return new MaxNativeAd(this);
        }

        public Builder setAdFormat(MaxAdFormat maxAdFormat) {
            this.f12708a = maxAdFormat;
            return this;
        }

        public Builder setAdvertiser(String str) {
            this.f12709c = str;
            return this;
        }

        public Builder setBody(String str) {
            this.f12710d = str;
            return this;
        }

        public Builder setCallToAction(String str) {
            this.f12711e = str;
            return this;
        }

        public Builder setIcon(MaxNativeAdImage maxNativeAdImage) {
            this.f12712f = maxNativeAdImage;
            return this;
        }

        public Builder setIconView(View view) {
            this.f12713g = view;
            return this;
        }

        public Builder setMainImage(MaxNativeAdImage maxNativeAdImage) {
            this.f12716j = maxNativeAdImage;
            return this;
        }

        public Builder setMediaContentAspectRatio(float f9) {
            this.f12717k = f9;
            return this;
        }

        public Builder setMediaView(View view) {
            this.f12715i = view;
            return this;
        }

        public Builder setOptionsView(View view) {
            this.f12714h = view;
            return this;
        }

        public Builder setStarRating(Double d2) {
            this.l = d2;
            return this;
        }

        public Builder setTitle(String str) {
            this.b = str;
            return this;
        }
    }

    public MaxNativeAd(Builder builder) {
        this.format = builder.f12708a;
        this.title = builder.b;
        this.advertiser = builder.f12709c;
        this.body = builder.f12710d;
        this.callToAction = builder.f12711e;
        this.icon = builder.f12712f;
        this.iconView = builder.f12713g;
        this.optionsView = builder.f12714h;
        this.mediaView = builder.f12715i;
        this.mainImage = builder.f12716j;
        this.mediaContentAspectRatio = builder.f12717k;
        Double d2 = builder.l;
        this.starRating = (d2 == null || d2.doubleValue() < 3.0d) ? null : d2;
    }

    @Nullable
    public final String getAdvertiser() {
        return this.advertiser;
    }

    @Nullable
    public final String getBody() {
        return this.body;
    }

    @Nullable
    public final String getCallToAction() {
        return this.callToAction;
    }

    @NonNull
    public final MaxAdFormat getFormat() {
        return this.format;
    }

    @Nullable
    public final MaxNativeAdImage getIcon() {
        return this.icon;
    }

    @Nullable
    public final View getIconView() {
        return this.iconView;
    }

    @Nullable
    public final MaxNativeAdImage getMainImage() {
        return this.mainImage;
    }

    public final float getMediaContentAspectRatio() {
        return this.mediaContentAspectRatio;
    }

    @Nullable
    public final View getMediaView() {
        return this.mediaView;
    }

    @Nullable
    public final View getOptionsView() {
        return this.optionsView;
    }

    @Nullable
    public final Double getStarRating() {
        return this.starRating;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public boolean isContainerClickable() {
        return false;
    }

    public final boolean isExpired() {
        return this.isExpired;
    }

    public void performClick() {
        Button callToActionButton;
        MaxNativeAdView maxNativeAdView = this.nativeAdView;
        if (maxNativeAdView != null && (callToActionButton = maxNativeAdView.getCallToActionButton()) != null) {
            callToActionButton.performClick();
        }
    }

    public boolean prepareForInteraction(List<View> list, ViewGroup viewGroup) {
        return false;
    }

    @Deprecated
    public void prepareViewForInteraction(MaxNativeAdView maxNativeAdView) {
    }

    public void setExpired() {
        this.isExpired = true;
    }

    public void setNativeAdView(MaxNativeAdView maxNativeAdView) {
        this.nativeAdView = maxNativeAdView;
    }

    public boolean shouldPrepareViewForInteractionOnMainThread() {
        return true;
    }

    /* loaded from: classes.dex */
    public static class MaxNativeAdImage {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f12718a;
        private Uri b;

        public MaxNativeAdImage(Drawable drawable) {
            this.f12718a = drawable;
        }

        @Nullable
        public Drawable getDrawable() {
            return this.f12718a;
        }

        @Nullable
        public Uri getUri() {
            return this.b;
        }

        public MaxNativeAdImage(Uri uri) {
            this.b = uri;
        }
    }
}
