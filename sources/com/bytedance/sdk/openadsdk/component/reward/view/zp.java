package com.bytedance.sdk.openadsdk.component.reward.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.bytedance.sdk.component.utils.cz;
import com.bytedance.sdk.component.widget.SSWebView;
import com.bytedance.sdk.openadsdk.common.LandingPageLoadingLayout;
import com.bytedance.sdk.openadsdk.core.customview.PAGLinearLayout;
import com.bytedance.sdk.openadsdk.core.customview.PAGRelativeLayout;
import com.bytedance.sdk.openadsdk.core.customview.PAGTextView;
import com.bytedance.sdk.openadsdk.core.widget.PAGLogoView;
import com.bytedance.sdk.openadsdk.core.widget.TTRoundRectImageView;
import com.bytedance.sdk.openadsdk.utils.WNy;
import com.bytedance.sdk.openadsdk.utils.ku;
import com.bytedance.sdk.openadsdk.utils.vDp;

/* loaded from: classes.dex */
public class zp {
    public static void lMd(FrameLayout frameLayout) {
        Context context = frameLayout.getContext();
        View rFDownloadBarLayout = new RFDownloadBarLayout(context);
        rFDownloadBarLayout.setId(vDp.vDp);
        rFDownloadBarLayout.setVisibility(8);
        rFDownloadBarLayout.setPadding(WNy.lMd(context, 15.0f), 0, 0, 0);
        rFDownloadBarLayout.setBackgroundColor(cz.ku(context, "tt_download_bar_background_new"));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, WNy.lMd(context, 90.0f));
        layoutParams.gravity = 80;
        frameLayout.addView(rFDownloadBarLayout, layoutParams);
        View pAGLinearLayout = new PAGLinearLayout(context);
        pAGLinearLayout.setId(vDp.tG);
        pAGLinearLayout.setVisibility(8);
        frameLayout.addView(pAGLinearLayout, new FrameLayout.LayoutParams(-1, -1));
        View sSWebView = new SSWebView(context, true);
        sSWebView.setLayerType(2, null);
        sSWebView.setVisibility(4);
        sSWebView.setId(vDp.rV);
        frameLayout.addView(sSWebView, new FrameLayout.LayoutParams(-1, -1));
        View frameLayout2 = new FrameLayout(context);
        frameLayout2.setId(vDp.dQp);
        frameLayout2.setVisibility(4);
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        View sSWebView2 = new SSWebView(context, true);
        sSWebView2.setVisibility(8);
        sSWebView2.setId(vDp.KVG);
        frameLayout.addView(sSWebView2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(new RFEndCardBackUpLayout(context), new FrameLayout.LayoutParams(-1, -1));
    }

    public static void zp(FrameLayout frameLayout) {
        Context context = frameLayout.getContext();
        PAGRelativeLayout pAGRelativeLayout = new PAGRelativeLayout(context);
        pAGRelativeLayout.setId(vDp.ox);
        pAGRelativeLayout.setBackgroundColor(Color.parseColor("#F8F8F8"));
        frameLayout.addView(pAGRelativeLayout, new FrameLayout.LayoutParams(-1, -1));
        View landingPageLoadingLayout = new LandingPageLoadingLayout(context);
        landingPageLoadingLayout.setId(vDp.RCv);
        pAGRelativeLayout.addView(landingPageLoadingLayout, new RelativeLayout.LayoutParams(-1, -1));
        PAGLinearLayout pAGLinearLayout = new PAGLinearLayout(context);
        pAGLinearLayout.setId(vDp.Lij);
        pAGLinearLayout.setOrientation(1);
        pAGLinearLayout.setVisibility(8);
        pAGLinearLayout.setGravity(1);
        pAGLinearLayout.setId(vDp.ffE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        pAGRelativeLayout.addView(pAGLinearLayout, layoutParams);
        TTRoundRectImageView tTRoundRectImageView = new TTRoundRectImageView(context);
        tTRoundRectImageView.setId(vDp.irS);
        pAGLinearLayout.addView(tTRoundRectImageView, new LinearLayout.LayoutParams(WNy.lMd(context, 80.0f), WNy.lMd(context, 80.0f)));
        PAGTextView pAGTextView = new PAGTextView(context);
        pAGTextView.setId(vDp.QUv);
        pAGTextView.setTextColor(Color.parseColor("#161823"));
        pAGTextView.setTextSize(2, 24.0f);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.topMargin = WNy.lMd(context, 12.0f);
        pAGLinearLayout.addView(pAGTextView, layoutParams2);
        PAGTextView pAGTextView2 = new PAGTextView(context);
        pAGTextView2.setId(vDp.cW);
        pAGTextView2.setGravity(17);
        pAGTextView2.setTextColor(Color.parseColor("#80161823"));
        pAGTextView2.setTextSize(2, 16.0f);
        pAGTextView2.setTextAlignment(4);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        int lMd = WNy.lMd(context, 60.0f);
        layoutParams3.leftMargin = lMd;
        layoutParams3.rightMargin = lMd;
        layoutParams3.setMarginStart(lMd);
        layoutParams3.setMarginEnd(layoutParams3.rightMargin);
        layoutParams3.topMargin = WNy.lMd(context, 8.0f);
        pAGLinearLayout.addView(pAGTextView2, layoutParams3);
        PAGTextView pAGTextView3 = new PAGTextView(context);
        pAGTextView3.setId(vDp.YhE);
        pAGTextView3.setGravity(17);
        pAGTextView3.setTextColor(-1);
        pAGTextView3.setText(cz.lMd(context, "tt_video_mobile_go_detail"));
        pAGTextView3.setBackground(ku.zp(context, "tt_reward_video_download_btn_bg"));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(WNy.lMd(context, 255.0f), WNy.lMd(context, 44.0f));
        layoutParams4.topMargin = WNy.lMd(context, 32.0f);
        pAGLinearLayout.addView(pAGTextView3, layoutParams4);
        PAGTextView pAGTextView4 = new PAGTextView(context);
        pAGTextView4.setId(vDp.Gzh);
        pAGTextView4.setGravity(17);
        pAGTextView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        pAGTextView4.setText("Sorry,this AD fails to load...");
        pAGTextView4.setVisibility(8);
        pAGTextView4.setGravity(1);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams5.addRule(13);
        pAGRelativeLayout.addView(pAGTextView4, layoutParams5);
        View pAGLogoView = new PAGLogoView(context);
        pAGLogoView.setId(vDp.WNy);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, WNy.lMd(context, 14.0f));
        layoutParams6.addRule(12);
        int lMd2 = WNy.lMd(context, 16.0f);
        layoutParams6.leftMargin = lMd2;
        layoutParams6.setMarginStart(lMd2);
        layoutParams6.bottomMargin = WNy.lMd(context, 20.0f);
        pAGRelativeLayout.addView(pAGLogoView, layoutParams6);
    }
}
