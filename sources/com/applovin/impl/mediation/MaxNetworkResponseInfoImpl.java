package com.applovin.impl.mediation;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxMediatedNetworkInfo;
import com.applovin.mediation.MaxNetworkResponseInfo;

/* loaded from: classes.dex */
public class MaxNetworkResponseInfoImpl implements MaxNetworkResponseInfo {

    /* renamed from: a, reason: collision with root package name */
    private final MaxNetworkResponseInfo.AdLoadState f8940a;
    private final MaxMediatedNetworkInfo b;

    /* renamed from: c, reason: collision with root package name */
    private final Bundle f8941c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f8942d;

    /* renamed from: e, reason: collision with root package name */
    private final long f8943e;

    /* renamed from: f, reason: collision with root package name */
    private final String f8944f;

    /* renamed from: g, reason: collision with root package name */
    private final MaxError f8945g;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8946a;

        static {
            int[] iArr = new int[MaxNetworkResponseInfo.AdLoadState.values().length];
            f8946a = iArr;
            try {
                iArr[MaxNetworkResponseInfo.AdLoadState.FAILED_TO_LOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8946a[MaxNetworkResponseInfo.AdLoadState.AD_LOADED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public MaxNetworkResponseInfoImpl(MaxNetworkResponseInfo.AdLoadState adLoadState, MaxMediatedNetworkInfo maxMediatedNetworkInfo, Bundle bundle, boolean z8, long j7, String str, @Nullable MaxError maxError) {
        this.f8940a = adLoadState;
        this.b = maxMediatedNetworkInfo;
        this.f8941c = bundle;
        this.f8942d = z8;
        this.f8943e = j7;
        this.f8944f = str;
        this.f8945g = maxError;
    }

    public boolean canEqual(Object obj) {
        return obj instanceof MaxNetworkResponseInfoImpl;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MaxNetworkResponseInfoImpl)) {
            return false;
        }
        MaxNetworkResponseInfoImpl maxNetworkResponseInfoImpl = (MaxNetworkResponseInfoImpl) obj;
        if (!maxNetworkResponseInfoImpl.canEqual(this) || isBidding() != maxNetworkResponseInfoImpl.isBidding() || getLatencyMillis() != maxNetworkResponseInfoImpl.getLatencyMillis()) {
            return false;
        }
        MaxNetworkResponseInfo.AdLoadState adLoadState = getAdLoadState();
        MaxNetworkResponseInfo.AdLoadState adLoadState2 = maxNetworkResponseInfoImpl.getAdLoadState();
        if (adLoadState != null ? !adLoadState.equals(adLoadState2) : adLoadState2 != null) {
            return false;
        }
        MaxMediatedNetworkInfo mediatedNetwork = getMediatedNetwork();
        MaxMediatedNetworkInfo mediatedNetwork2 = maxNetworkResponseInfoImpl.getMediatedNetwork();
        if (mediatedNetwork != null ? !mediatedNetwork.equals(mediatedNetwork2) : mediatedNetwork2 != null) {
            return false;
        }
        Bundle credentials = getCredentials();
        Bundle credentials2 = maxNetworkResponseInfoImpl.getCredentials();
        if (credentials != null ? !credentials.equals(credentials2) : credentials2 != null) {
            return false;
        }
        String bCode = getBCode();
        String bCode2 = maxNetworkResponseInfoImpl.getBCode();
        if (bCode != null ? !bCode.equals(bCode2) : bCode2 != null) {
            return false;
        }
        MaxError error = getError();
        MaxError error2 = maxNetworkResponseInfoImpl.getError();
        if (error != null ? error.equals(error2) : error2 == null) {
            return true;
        }
        return false;
    }

    @Override // com.applovin.mediation.MaxNetworkResponseInfo
    public MaxNetworkResponseInfo.AdLoadState getAdLoadState() {
        return this.f8940a;
    }

    public String getBCode() {
        return this.f8944f;
    }

    @Override // com.applovin.mediation.MaxNetworkResponseInfo
    public Bundle getCredentials() {
        return this.f8941c;
    }

    @Override // com.applovin.mediation.MaxNetworkResponseInfo
    @Nullable
    public MaxError getError() {
        return this.f8945g;
    }

    @Override // com.applovin.mediation.MaxNetworkResponseInfo
    public long getLatencyMillis() {
        return this.f8943e;
    }

    @Override // com.applovin.mediation.MaxNetworkResponseInfo
    public MaxMediatedNetworkInfo getMediatedNetwork() {
        return this.b;
    }

    public int hashCode() {
        int i9;
        int hashCode;
        int hashCode2;
        int hashCode3;
        int hashCode4;
        if (isBidding()) {
            i9 = 79;
        } else {
            i9 = 97;
        }
        long latencyMillis = getLatencyMillis();
        int i10 = ((i9 + 59) * 59) + ((int) (latencyMillis ^ (latencyMillis >>> 32)));
        MaxNetworkResponseInfo.AdLoadState adLoadState = getAdLoadState();
        int i11 = i10 * 59;
        int i12 = 43;
        if (adLoadState == null) {
            hashCode = 43;
        } else {
            hashCode = adLoadState.hashCode();
        }
        int i13 = i11 + hashCode;
        MaxMediatedNetworkInfo mediatedNetwork = getMediatedNetwork();
        int i14 = i13 * 59;
        if (mediatedNetwork == null) {
            hashCode2 = 43;
        } else {
            hashCode2 = mediatedNetwork.hashCode();
        }
        int i15 = i14 + hashCode2;
        Bundle credentials = getCredentials();
        int i16 = i15 * 59;
        if (credentials == null) {
            hashCode3 = 43;
        } else {
            hashCode3 = credentials.hashCode();
        }
        int i17 = i16 + hashCode3;
        String bCode = getBCode();
        int i18 = i17 * 59;
        if (bCode == null) {
            hashCode4 = 43;
        } else {
            hashCode4 = bCode.hashCode();
        }
        int i19 = i18 + hashCode4;
        MaxError error = getError();
        int i20 = i19 * 59;
        if (error != null) {
            i12 = error.hashCode();
        }
        return i20 + i12;
    }

    @Override // com.applovin.mediation.MaxNetworkResponseInfo
    public boolean isBidding() {
        return this.f8942d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0041, code lost:
    
        if (r1 != 2) goto L9;
     */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "MaxResponseInfo{adLoadState="
            r1.<init>(r2)
            com.applovin.mediation.MaxNetworkResponseInfo$AdLoadState r2 = r3.f8940a
            r1.append(r2)
            java.lang.String r2 = ", mediatedNetwork="
            r1.append(r2)
            com.applovin.mediation.MaxMediatedNetworkInfo r2 = r3.b
            r1.append(r2)
            java.lang.String r2 = ", credentials="
            r1.append(r2)
            android.os.Bundle r2 = r3.f8941c
            r1.append(r2)
            java.lang.String r2 = ", isBidding="
            r1.append(r2)
            boolean r2 = r3.f8942d
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            int[] r1 = com.applovin.impl.mediation.MaxNetworkResponseInfoImpl.a.f8946a
            com.applovin.mediation.MaxNetworkResponseInfo$AdLoadState r2 = r3.f8940a
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L44
            r2 = 2
            if (r1 == r2) goto L4e
            goto L58
        L44:
            java.lang.String r1 = ", error="
            r0.append(r1)
            com.applovin.mediation.MaxError r1 = r3.f8945g
            r0.append(r1)
        L4e:
            java.lang.String r1 = ", latencyMillis="
            r0.append(r1)
            long r1 = r3.f8943e
            r0.append(r1)
        L58:
            java.lang.String r1 = "}"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.mediation.MaxNetworkResponseInfoImpl.toString():java.lang.String");
    }
}
