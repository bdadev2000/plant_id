package com.adjust.sdk.network;

import com.adjust.sdk.AdjustFactory;
import com.adjust.sdk.ILogger;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import com.mbridge.msdk.foundation.download.Command;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UtilNetworking {
    private static String userAgent;

    /* loaded from: classes.dex */
    public interface IConnectionOptions {
        void applyConnectionOptions(HttpsURLConnection httpsURLConnection, String str);
    }

    /* loaded from: classes.dex */
    public interface IHttpsURLConnectionProvider {
        HttpsURLConnection generateHttpsURLConnection(URL url);
    }

    /* loaded from: classes.dex */
    public class a implements IConnectionOptions {
        @Override // com.adjust.sdk.network.UtilNetworking.IConnectionOptions
        public final void applyConnectionOptions(HttpsURLConnection httpsURLConnection, String str) {
            httpsURLConnection.setRequestProperty("Client-SDK", str);
            httpsURLConnection.setConnectTimeout(60000);
            httpsURLConnection.setReadTimeout(60000);
            if (UtilNetworking.userAgent != null) {
                httpsURLConnection.setRequestProperty(Command.HTTP_HEADER_USER_AGENT, UtilNetworking.userAgent);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements IHttpsURLConnectionProvider {
        @Override // com.adjust.sdk.network.UtilNetworking.IHttpsURLConnectionProvider
        public final HttpsURLConnection generateHttpsURLConnection(URL url) {
            return (HttpsURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
        }
    }

    public static IConnectionOptions createDefaultConnectionOptions() {
        return new a();
    }

    public static IHttpsURLConnectionProvider createDefaultHttpsURLConnectionProvider() {
        return new b();
    }

    public static Long extractJsonLong(JSONObject jSONObject, String str) {
        Object opt = jSONObject.opt(str);
        if (opt instanceof Long) {
            return (Long) opt;
        }
        if (opt instanceof Number) {
            return Long.valueOf(((Number) opt).longValue());
        }
        if (!(opt instanceof String)) {
            return null;
        }
        try {
            return Long.valueOf((long) Double.parseDouble((String) opt));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static String extractJsonString(JSONObject jSONObject, String str) {
        Object opt = jSONObject.opt(str);
        if (opt instanceof String) {
            return (String) opt;
        }
        if (opt != null) {
            return opt.toString();
        }
        return null;
    }

    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }

    public static void setUserAgent(String str) {
        userAgent = str;
    }
}
