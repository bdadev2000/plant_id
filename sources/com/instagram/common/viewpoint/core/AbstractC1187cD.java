package com.instagram.common.viewpoint.core;

import com.facebook.ads.internal.adapters.datamodels.AdInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.Utf8;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.objectweb.asm.Opcodes;

/* renamed from: com.facebook.ads.redexgen.X.cD, reason: case insensitive filesystem */
/* loaded from: assets/audience_network.dex */
public abstract class AbstractC1187cD extends C1E implements Serializable {
    public static JSONObject A0F = null;
    public static byte[] A0G = null;
    public static String[] A0H = {"MTFiGS2hyLPfirnVlCyKu8FL5ydCU71a", "6CtUdYtPnHS0iwt", "3FBcQYGRSJhXKbj7ODsddhFyR1abGnGF", "UQ7LtGE24TX4QkAlsGDlXXS4Vcev", "JcPJu", "s3C", "M3uL5ar3Pz1QrNsTamNzt8iOeyqnSnZC", "bpoJK"};
    public static final LinkedHashMap<String, String> A0I;
    public static final long serialVersionUID = -5352540727250859603L;
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public C1B A05;
    public C1T A06;
    public C1W A07;
    public C1Z A08;
    public C01941f A09;
    public String A0A;
    public final List<C1F> A0D;
    public boolean A0B = false;
    public boolean A0C = false;
    public final Map<String, String> A0E = new HashMap();

    public static String A05(int i2, int i3, int i4) {
        byte[] copyOfRange = Arrays.copyOfRange(A0G, i2, i2 + i3);
        for (int i5 = 0; i5 < copyOfRange.length; i5++) {
            copyOfRange[i5] = (byte) ((copyOfRange[i5] ^ i4) ^ 112);
        }
        return new String(copyOfRange);
    }

    public static void A09() {
        A0G = new byte[]{81, 84, 111, 83, 88, 95, 89, 83, 85, 67, 111, 92, 89, 94, 91, 111, 69, 66, 92, 122, 117, 68, 119, 116, 124, 116, 68, 111, 98, 107, 126, 29, 9, 8, 19, 31, 16, 21, 31, 23, 35, 31, 19, 9, 18, 8, 24, 19, 11, 18, 35, 8, 21, 17, 25, 53, 33, 32, 59, 55, 56, 61, 55, Utf8.REPLACEMENT_BYTE, 11, 55, 32, 53, 11, 32, 61, 57, 49, 76, 88, 89, 66, 78, 65, 68, 78, 70, 114, 75, 65, 76, 91, 66, 95, 85, 87, 85, 94, 83, 105, 87, 69, 69, 83, 66, 69, 45, 47, 60, 33, 59, 61, 43, 34, 59, 48, 57, 49, 54, 7, 57, 60, 43, 7, 62, 42, 61, 41, 45, 61, 54, 59, 33, 15, 3, 1, 66, 10, 13, 15, 9, 14, 3, 3, 7, 66, 13, 8, 31, 66, 5, 2, 24, 9, 30, 31, 24, 5, 24, 5, 13, 0, 66, 10, 5, 2, 5, 31, 4, 51, 13, 15, 24, 5, 26, 5, 24, 21, 6, 17, 91, 76, 83, 96, 94, 79, 79, 96, 93, 80, 74, 81, 91, 96, 74, 77, 83, 76, 36, 32, 118, 35, 29, 37, 50, 29, 45, 52, 39, 48, 46, 35, 59, 29, 49, 39, 33, 55, 48, 39, 29, 54, 45, 41, 39, 44, 101, 111, 99, 98, 84, 83, 78, 73, 92, 81, 81, 98, 79, 88, 91, 88, 79, 79, 88, 79, 120, Byte.MAX_VALUE, 101, 116, 99, 98, 101, 120, 101, 120, 112, 125, 67, 78, 65, 75, 92, 76, 78, 95, 74, 86, 91, 67, 85, 79, 78, 82, 77, 80, 86, 80, 67, 75, 86, 31, 8, 26, 12, 31, 9, 8, 9, 50, 27, 4, 9, 8, 2, 68, 95, 88, 66, 91, 83, 104, 91, 88, 80, 104, 86, 89, 78, 104, 94, 86, 85, 104, 84, 91, 94, 84, 92, 104, 88, 89, 84, 82, 126, 99, 126, 102, 111, 13, 10, 20};
    }

    static {
        A09();
        A0I = new LinkedHashMap<>(10, 0.75f, false);
    }

    public AbstractC1187cD(List<C1F> list) {
        this.A0D = list;
    }

    public static String A06(String str) {
        return A0I.get(str);
    }

    private HashMap<String, String> A07(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(A05(Opcodes.DRETURN, 18, 79));
        HashMap<String, String> hashMap = new HashMap<>();
        if (optJSONObject == null) {
            return hashMap;
        }
        Iterator<String> nameItr = optJSONObject.keys();
        while (nameItr.hasNext()) {
            try {
                String next = nameItr.next();
                hashMap.put(next, optJSONObject.getString(next));
            } catch (JSONException unused) {
            }
        }
        return hashMap;
    }

    public static List<C1F> A08(JSONObject jSONObject, C1045Zs c1045Zs, C1R c1r) {
        JSONArray optJSONArray = jSONObject.optJSONArray(A05(101, 8, 62));
        if (optJSONArray != null && optJSONArray.length() > 0) {
            return C1N.A01(optJSONArray, jSONObject, c1045Zs, c1r);
        }
        List<AdInfo> adInfoList = new ArrayList<>();
        C1F A00 = C1F.A00(jSONObject);
        c1r.A3j(A00, jSONObject);
        adInfoList.add(A00);
        return adInfoList;
    }

    private void A0A(int i2) {
        this.A00 = i2;
    }

    private final void A0B(int i2) {
        this.A04 = i2;
    }

    private void A0C(C1B c1b) {
        this.A05 = c1b;
    }

    private final void A0D(C1T c1t) {
        this.A06 = c1t;
    }

    private void A0E(C1W c1w) {
        this.A07 = c1w;
    }

    private final void A0F(C1Z c1z) {
        this.A08 = c1z;
    }

    private void A0G(C01941f c01941f) {
        this.A09 = c01941f;
    }

    private void A0H(String str) {
        this.A0A = str;
    }

    public final int A1J() {
        return this.A00;
    }

    public final int A1K() {
        return this.A01;
    }

    public final int A1L() {
        return this.A02;
    }

    public final int A1M() {
        return this.A03;
    }

    public final int A1N() {
        return this.A04;
    }

    public final C1B A1O() {
        return this.A05;
    }

    public final C1F A1P() {
        return this.A0D.get(0);
    }

    public final C1T A1Q() {
        return this.A06;
    }

    public final C1W A1R() {
        return this.A07;
    }

    public final C1Z A1S() {
        return this.A08;
    }

    public final C01941f A1T() {
        return this.A09;
    }

    public final String A1U() {
        return this.A0A;
    }

    public final String A1V(String str) {
        return this.A0E.get(str);
    }

    public final List<C1F> A1W() {
        return Collections.unmodifiableList(this.A0D);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void A1X(MR mr) {
        char c2;
        String A0c = A0c();
        switch (A0c.hashCode()) {
            case -1364000502:
                if (A0c.equals(A05(276, 14, 29))) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 604727084:
                if (A0c.equals(A05(241, 12, 97))) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                mr.A4P(A05(128, 45, 28));
                break;
            case 1:
                break;
            default:
                return;
        }
        mr.A4P(EnumC0818Qv.A08.A03());
    }

    public final void A1Y(JSONObject jSONObject) {
        String A05;
        A0D(C1T.A00(jSONObject.optJSONObject(A05(225, 16, 77))));
        A0F = jSONObject.optJSONObject(A05(89, 12, 70));
        C1Y A06 = new C1Y().A06(jSONObject.optString(A05(319, 5, 122)));
        String A052 = A05(221, 4, 124);
        if (jSONObject.optJSONObject(A052) != null) {
            A05 = jSONObject.optJSONObject(A052).optString(A05(324, 3, 8));
        } else {
            A05 = A05(0, 0, 15);
        }
        A0F(A06.A05(A05).A04(jSONObject.optString(A05(0, 19, 64))).A07(AbstractC01891a.A03(jSONObject)).A08());
        JSONObject optJSONObject = jSONObject.optJSONObject(A05(262, 6, 74));
        JSONObject layoutObject = optJSONObject != null ? optJSONObject.optJSONObject(A05(268, 8, 82)) : null;
        C1O A01 = C1O.A01(layoutObject);
        String[] strArr = A0H;
        if (strArr[1].length() == strArr[7].length()) {
            throw new RuntimeException();
        }
        String[] strArr2 = A0H;
        strArr2[2] = "MaJS3iOjDVpkOvzQIu4XnTvkYtI43ENf";
        strArr2[6] = "dcadpZfpRdAwhtf1LK3imqQWfZ9Fu8JJ";
        A0C(new C1B(A01, C1O.A01(optJSONObject != null ? optJSONObject.optJSONObject(A05(253, 9, 95)) : null)));
        A0G(AbstractC01891a.A01(jSONObject));
        A0E(AbstractC01891a.A00(jSONObject));
        A0A(jSONObject.optInt(A05(19, 12, 107), 0));
        A0B(jSONObject.optInt(A05(109, 19, 40), -1));
        this.A0E.putAll(A07(jSONObject));
        this.A03 = jSONObject.optInt(A05(55, 18, 36), 0);
        this.A01 = jSONObject.optInt(A05(73, 16, 93), 1);
        this.A0C = jSONObject.optBoolean(A05(290, 29, 71), false);
        this.A02 = jSONObject.optInt(A05(31, 24, 12), this.A03);
        String optString = jSONObject.optString(A05(Opcodes.LRETURN, 2, 21));
        A0H(optString);
        A0I.put(optString, jSONObject.optString(A05(Opcodes.INSTANCEOF, 28, 50)));
        A0w(jSONObject);
    }

    public final void A1Z(boolean z2) {
        this.A0B = z2;
    }

    public final boolean A1a() {
        return this.A0B;
    }

    public final boolean A1b() {
        return this.A0C;
    }
}
