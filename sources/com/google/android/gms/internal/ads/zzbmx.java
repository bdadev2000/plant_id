package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.gms.ads.h5.H5AdsRequestHandler;
import com.google.android.gms.ads.h5.OnH5AdsEventListener;

/* loaded from: classes2.dex */
public final class zzbmx extends zzbmk {
    public static final /* synthetic */ int zza = 0;

    @Nullable
    private WebViewClient zzb;
    private final H5AdsRequestHandler zzc;
    private final WebView zzd;

    public zzbmx(Context context, final WebView webView) {
        context.getClass();
        webView.getClass();
        zzfyg.zzf(webView.getSettings().getJavaScriptEnabled(), "JavaScript must be enabled on the WebView.");
        this.zzd = webView;
        this.zzc = new H5AdsRequestHandler(context, new OnH5AdsEventListener() { // from class: com.google.android.gms.internal.ads.zzbmw
            @Override // com.google.android.gms.ads.h5.OnH5AdsEventListener
            public final void onH5AdsEvent(String str) {
                int i9 = zzbmx.zza;
                webView.evaluateJavascript(str, null);
            }
        });
    }

    private final boolean zzc(WebView webView) {
        if (this.zzd.equals(webView)) {
            return true;
        }
        com.google.android.gms.ads.internal.util.client.zzm.zzg("H5AdsWebViewClient must be attached to the same WebVew which was passed in the constructor.");
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbmk
    @Nullable
    public final WebViewClient getDelegate() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbmk, android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        if (zzc(webView) && !this.zzc.handleH5AdsRequest(str)) {
            super.onLoadResource(webView, str);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmk, android.webkit.WebViewClient
    @RequiresApi(api = 24)
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        if (!zzc(this.zzd)) {
            return false;
        }
        if (this.zzc.handleH5AdsRequest(webResourceRequest.getUrl().toString())) {
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    public final void zza() {
        this.zzc.clearAdObjects();
    }

    public final void zzb(@Nullable WebViewClient webViewClient) {
        boolean z8;
        if (webViewClient != this) {
            z8 = true;
        } else {
            z8 = false;
        }
        zzfyg.zzf(z8, "Delegate cannot be itself.");
        this.zzb = webViewClient;
    }

    @Override // com.google.android.gms.internal.ads.zzbmk, android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!zzc(webView)) {
            return false;
        }
        if (this.zzc.handleH5AdsRequest(str)) {
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }
}
