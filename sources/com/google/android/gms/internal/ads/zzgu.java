package com.google.android.gms.internal.ads;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.facebook.ads.AdError;
import com.facebook.gamingservices.internal.TournamentShareDialogURIBuilder;
import com.safedk.android.analytics.brandsafety.creatives.e;
import com.safedk.android.internal.partials.AdMobNetworkBridge;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/* loaded from: classes3.dex */
public final class zzgu extends zzfz implements zzhc {
    private final boolean zza;
    private final int zzb;
    private final int zzc;

    @Nullable
    private final String zzd;

    @Nullable
    private final zzhb zze;
    private final zzhb zzf;

    @Nullable
    private zzgm zzg;

    @Nullable
    private HttpURLConnection zzh;

    @Nullable
    private InputStream zzi;
    private boolean zzj;
    private int zzk;
    private long zzl;
    private long zzm;

    public /* synthetic */ zzgu(String str, int i2, int i3, boolean z2, boolean z3, zzhb zzhbVar, zzfya zzfyaVar, boolean z4, zzgt zzgtVar) {
        super(true);
        this.zzd = str;
        this.zzb = i2;
        this.zzc = i3;
        this.zza = z2;
        this.zze = zzhbVar;
        this.zzf = new zzhb();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.net.HttpURLConnection zzk(java.net.URL r3, int r4, @androidx.annotation.Nullable byte[] r5, long r6, long r8, boolean r10, boolean r11, java.util.Map r12) throws java.io.IOException {
        /*
            r2 = this;
            java.net.URLConnection r3 = r3.openConnection()
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3
            int r4 = r2.zzb
            r3.setConnectTimeout(r4)
            int r4 = r2.zzc
            r3.setReadTimeout(r4)
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            com.google.android.gms.internal.ads.zzhb r5 = r2.zze
            java.util.Map r5 = r5.zza()
            r4.putAll(r5)
            com.google.android.gms.internal.ads.zzhb r5 = r2.zzf
            java.util.Map r5 = r5.zza()
            r4.putAll(r5)
            r4.putAll(r12)
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L32:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L4e
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r12 = r5.getKey()
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            r3.setRequestProperty(r12, r5)
            goto L32
        L4e:
            r4 = 0
            int r12 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            r0 = -1
            if (r12 != 0) goto L5d
            int r6 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r6 != 0) goto L5c
            r4 = 0
            goto L72
        L5c:
            r6 = r4
        L5d:
            java.lang.String r4 = "bytes="
            java.lang.String r5 = "-"
            java.lang.StringBuilder r4 = androidx.compose.foundation.text.input.a.r(r4, r6, r5)
            int r5 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r5 == 0) goto L6e
            long r6 = r6 + r8
            long r6 = r6 + r0
            r4.append(r6)
        L6e:
            java.lang.String r4 = r4.toString()
        L72:
            if (r4 == 0) goto L79
            java.lang.String r5 = "Range"
            r3.setRequestProperty(r5, r4)
        L79:
            java.lang.String r4 = r2.zzd
            if (r4 == 0) goto L82
            java.lang.String r5 = "User-Agent"
            r3.setRequestProperty(r5, r4)
        L82:
            r4 = 1
            if (r4 == r10) goto L88
            java.lang.String r4 = "identity"
            goto L8a
        L88:
            java.lang.String r4 = "gzip"
        L8a:
            java.lang.String r5 = "Accept-Encoding"
            r3.setRequestProperty(r5, r4)
            r3.setInstanceFollowRedirects(r11)
            r4 = 0
            r3.setDoOutput(r4)
            int r4 = com.google.android.gms.internal.ads.zzgm.zzh
            java.lang.String r4 = "GET"
            r3.setRequestMethod(r4)
            r3.connect()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgu.zzk(java.net.URL, int, byte[], long, long, boolean, boolean, java.util.Map):java.net.HttpURLConnection");
    }

    private final URL zzl(URL url, @Nullable String str, zzgm zzgmVar) throws zzgy {
        if (str == null) {
            throw new zzgy("Null location redirect", zzgmVar, AdError.INTERNAL_ERROR_CODE, 1);
        }
        try {
            URL url2 = new URL(url, str);
            String protocol = url2.getProtocol();
            if (!TournamentShareDialogURIBuilder.scheme.equals(protocol) && !e.e.equals(protocol)) {
                throw new zzgy("Unsupported protocol redirect: ".concat(String.valueOf(protocol)), zzgmVar, AdError.INTERNAL_ERROR_CODE, 1);
            }
            if (this.zza || protocol.equals(url.getProtocol())) {
                return url2;
            }
            throw new zzgy(androidx.compose.foundation.text.input.a.m("Disallowed cross-protocol redirect (", url.getProtocol(), " to ", protocol, ")"), zzgmVar, AdError.INTERNAL_ERROR_CODE, 1);
        } catch (MalformedURLException e) {
            throw new zzgy(e, zzgmVar, AdError.INTERNAL_ERROR_CODE, 1);
        }
    }

    private final void zzm() {
        HttpURLConnection httpURLConnection = this.zzh;
        if (httpURLConnection != null) {
            try {
                AdMobNetworkBridge.httpUrlConnectionDisconnect(httpURLConnection);
            } catch (Exception e) {
                zzea.zzd("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
            this.zzh = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzp
    public final int zza(byte[] bArr, int i2, int i3) throws zzgy {
        if (i3 == 0) {
            return 0;
        }
        try {
            long j2 = this.zzl;
            if (j2 != -1) {
                long j3 = j2 - this.zzm;
                if (j3 != 0) {
                    i3 = (int) Math.min(i3, j3);
                }
                return -1;
            }
            InputStream inputStream = this.zzi;
            int i4 = zzeu.zza;
            int read = inputStream.read(bArr, i2, i3);
            if (read == -1) {
                return -1;
            }
            this.zzm += read;
            zzg(read);
            return read;
        } catch (IOException e) {
            zzgm zzgmVar = this.zzg;
            int i5 = zzeu.zza;
            throw zzgy.zza(e, zzgmVar, 2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:121:0x01d2, code lost:
    
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01f4, code lost:
    
        throw new com.google.android.gms.internal.ads.zzgy(new java.net.NoRouteToHostException("Too many redirects: " + r6), r25, com.facebook.ads.AdError.INTERNAL_ERROR_CODE, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00b7, code lost:
    
        if (r8 == 0) goto L37;
     */
    @Override // com.google.android.gms.internal.ads.zzgg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long zzb(com.google.android.gms.internal.ads.zzgm r25) throws com.google.android.gms.internal.ads.zzgy {
        /*
            Method dump skipped, instructions count: 511
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgu.zzb(com.google.android.gms.internal.ads.zzgm):long");
    }

    @Override // com.google.android.gms.internal.ads.zzgg
    @Nullable
    public final Uri zzc() {
        HttpURLConnection httpURLConnection = this.zzh;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    @Override // com.google.android.gms.internal.ads.zzgg
    public final void zzd() throws zzgy {
        try {
            InputStream inputStream = this.zzi;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    zzgm zzgmVar = this.zzg;
                    int i2 = zzeu.zza;
                    throw new zzgy(e, zzgmVar, AdError.SERVER_ERROR_CODE, 3);
                }
            }
        } finally {
            this.zzi = null;
            zzm();
            if (this.zzj) {
                this.zzj = false;
                zzh();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfz, com.google.android.gms.internal.ads.zzgg
    public final Map zze() {
        HttpURLConnection httpURLConnection = this.zzh;
        return httpURLConnection == null ? zzgba.zzd() : new zzgs(httpURLConnection.getHeaderFields());
    }
}
