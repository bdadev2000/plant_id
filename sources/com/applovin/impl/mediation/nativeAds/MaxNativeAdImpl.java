package com.applovin.impl.mediation.nativeAds;

import android.view.View;
import androidx.annotation.Nullable;
import com.applovin.impl.mediation.ads.b;
import java.util.List;

/* loaded from: classes.dex */
public class MaxNativeAdImpl {
    private b adViewTracker;
    private List<View> clickableViews;

    @Nullable
    public b getAdViewTracker() {
        return this.adViewTracker;
    }

    @Nullable
    public List<View> getClickableViews() {
        return this.clickableViews;
    }

    public void setAdViewTracker(b bVar) {
        this.adViewTracker = bVar;
    }

    public void setClickableViews(List<View> list) {
        this.clickableViews = list;
    }
}
