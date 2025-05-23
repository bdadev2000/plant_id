package com.applovin.impl;

import android.os.Bundle;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.utils.StringUtils;

/* loaded from: classes.dex */
public class lc {

    /* renamed from: a, reason: collision with root package name */
    private final StringBuilder f8827a = new StringBuilder();

    public lc a(com.applovin.impl.sdk.ad.b bVar) {
        boolean z8 = bVar instanceof bq;
        a("Format", bVar.getAdZone().d() != null ? bVar.getAdZone().d().getLabel() : null).a("Ad ID", Long.valueOf(bVar.getAdIdNumber())).a("Zone ID", bVar.getAdZone().e()).a("Ad Class", z8 ? "VastAd" : "AdServerAd");
        String dspName = bVar.getDspName();
        if (StringUtils.isValidString(dspName)) {
            a("DSP Name", dspName);
        }
        if (z8) {
            a("VAST DSP", ((bq) bVar).m1());
        }
        return this;
    }

    public lc b(com.applovin.impl.sdk.ad.b bVar) {
        a("Target", bVar.d0()).a("close_style", bVar.n()).a("close_delay_graphic", Long.valueOf(bVar.p()), "s");
        if (bVar instanceof com.applovin.impl.sdk.ad.a) {
            com.applovin.impl.sdk.ad.a aVar = (com.applovin.impl.sdk.ad.a) bVar;
            a("HTML", aVar.e1().substring(0, Math.min(aVar.e1().length(), 64)));
        }
        if (bVar.hasVideoUrl()) {
            a("close_delay", Long.valueOf(bVar.k0()), "s").a("skip_style", bVar.b0()).a("Streaming", Boolean.valueOf(bVar.G0())).a("Video Location", bVar.P()).a("video_button_properties", bVar.i0());
        }
        return this;
    }

    public String toString() {
        return this.f8827a.toString();
    }

    public lc a(AppLovinAdView appLovinAdView) {
        return a("Size", appLovinAdView.getSize().getWidth() + "x" + appLovinAdView.getSize().getHeight()).a("Alpha", Float.valueOf(appLovinAdView.getAlpha())).a("Visibility", ar.a(appLovinAdView.getVisibility()));
    }

    public lc a(Bundle bundle) {
        if (bundle == null) {
            return this;
        }
        for (String str : bundle.keySet()) {
            a(str, bundle.get(str));
        }
        return this;
    }

    public lc a() {
        this.f8827a.append("\n========================================");
        return this;
    }

    public lc b(String str) {
        this.f8827a.append(str);
        return this;
    }

    public lc a(be beVar) {
        return a("Network", beVar.c()).a("Adapter Version", beVar.y()).a("Format", beVar.getFormat().getLabel()).a("Ad Unit ID", beVar.getAdUnitId()).a("Placement", beVar.getPlacement()).a("Network Placement", beVar.S()).a("Serve ID", beVar.Q()).a("Creative ID", StringUtils.isValidString(beVar.getCreativeId()) ? beVar.getCreativeId() : "None").a("Ad Review Creative ID", StringUtils.isValidString(beVar.getAdReviewCreativeId()) ? beVar.getAdReviewCreativeId() : "None").a("Ad Domain", StringUtils.isValidString(beVar.u()) ? beVar.u() : "None").a("DSP Name", StringUtils.isValidString(beVar.getDspName()) ? beVar.getDspName() : "None").a("DSP ID", StringUtils.isValidString(beVar.getDspId()) ? beVar.getDspId() : "None").a("Server Parameters", beVar.l());
    }

    public lc a(com.applovin.impl.sdk.k kVar) {
        return a("Muted", Boolean.valueOf(kVar.g0().isMuted()));
    }

    public lc a(String str) {
        StringBuilder sb = this.f8827a;
        sb.append("\n");
        sb.append(str);
        return this;
    }

    public lc a(String str, Object obj) {
        return a(str, obj, "");
    }

    public lc a(String str, Object obj, String str2) {
        StringBuilder sb = this.f8827a;
        sb.append("\n");
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        sb.append(str2);
        return this;
    }
}
