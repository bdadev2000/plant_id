package com.meta.analytics.dsp.uinode;

import android.util.Log;
import com.applovin.exoplayer2.common.base.Ascii;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: assets/audience_network.dex */
public final class YC implements J0 {
    public static byte[] A02;
    public static String[] A03 = {"T62Ry0cHQcXSDxKL7NhTV4RGJa1EK", "DJGQMadZkB17LVbr", "iOkG4K13Hjl0NUqXuJlVUejicjlk1nzW", "33srKXABt3KwFUuhEJwkq3ZAZBzDwUgt", "PLu8UfSRSTd2rcgSm6FUEhoaf9WcktnC", "1tfjOobuNxYUsDgE6aIuCnswgchDbiIE", "3cN0JgpVgSdwOhRQcWnO1AlK5DQJr2L6", "ilEunU5aQdATmqkKFrVV7R1hAU4VbdID"};
    public static final String A04;
    public C1635Ym A00;
    public YD A01;

    public static String A00(int i9, int i10, int i11) {
        byte[] copyOfRange = Arrays.copyOfRange(A02, i9, i9 + i10);
        for (int i12 = 0; i12 < copyOfRange.length; i12++) {
            copyOfRange[i12] = (byte) ((copyOfRange[i12] ^ i11) ^ 72);
        }
        return new String(copyOfRange);
    }

    public static void A01() {
        A02 = new byte[]{86, Ascii.DLE, Ascii.EM, 4, 86, 19, 0, 19, Ascii.CAN, 2, 63, Ascii.DC2, 86, 115, 32, 38, 48, 48, 54, 32, 32, 53, 38, 63, 63, 42, 125, 32, 34, 3, 10, 3, Ascii.DC2, 3, 2, 70, 3, Ascii.DLE, 3, 8, Ascii.DC2, Ascii.NAK, 70, 3, Ascii.RS, 5, 3, 3, 2, 3, 2, 70, Ascii.DC4, 3, Ascii.DC2, Ascii.DC4, Ascii.US, 70, 10, Ascii.SI, Ascii.VT, Ascii.SI, Ascii.DC2, 72, 70, 37, 9, 19, 8, Ascii.DC2, 92, 70, 65, 102, 110, 107, 98, 99, 39, 115, 104, 39, 119, 102, 117, 116, 98, 39, 102, 105, 39, 98, 113, 98, 105, 115, 39, 110, 105, 39, 98, 113, 98, 105, 115, 116, 39, 102, 117, 117, 102, 126, 39, 97, 104, 117, 39, 99, 110, 116, 119, 102, 115, 100, 111, 39, 97, 102, 110, 107, 114, 117, 98, 41, 112, 87, 95, 90, 83, 82, Ascii.SYN, 66, 89, Ascii.SYN, 70, 87, 68, 69, 83, Ascii.SYN, 87, 88, Ascii.SYN, 83, 64, 83, 88, 66, Ascii.SYN, 95, 88, Ascii.SYN, 83, 64, 83, 88, 66, 69, Ascii.SYN, 90, 95, 69, 66, Ascii.SYN, 65, 94, 83, 88, Ascii.SYN, 70, 68, 83, 70, 87, 68, 95, 88, 81, Ascii.SYN, 82, 95, 69, 70, 87, 66, 85, 94, Ascii.SYN, 70, 87, 79, 90, 89, 87, 82, Ascii.CAN, 7, 49, 38, 34, 49, 38, 116, 36, 38, 59, 55, 49, 39, 39, 49, 48, 116, 49, 34, 49, 58, 32, Ascii.GS, 48, 116, 17, 39, 48, 52, 39, 48, 98, 48, 39, 54, 55, 48, 44, 39, 38, 98, 44, 45, 44, 111, 48, 39, 54, 48, 59, 35, 32, 46, 39, 98, 39, 48, 48, 45, 48, 98, 33, 45, 38, 39, 98, 88, 110, 121, 125, 110, 121, 43, 121, 110, Ascii.DEL, 126, 121, 101, 110, 111, 43, 121, 110, Ascii.DEL, 121, 114, 106, 105, 103, 110, 43, 110, 121, 121, 100, 121, 43, 104, 100, 111, 110, 43, 68, Ascii.DEL, 112, 115, 125, 116, 49, 101, 126, 49, 97, 112, 99, 98, 116, 49, 98, 116, 99, 103, 116, 99, 49, 99, 116, 98, 97, 126, Ascii.DEL, 98, 116, 49, 112, 101, 49, 97, 126, 98, 120, 101, 120, 126, Ascii.DEL, 49, 117, 121, 114, 115, Ascii.CR, Ascii.RS, Ascii.CR, 6, Ascii.FS, Ascii.ESC, 56, 59, 63, 42, 43, 44, 59, 115, 61, 49, 48, 56, 55, 57, 115, 59, 40, 59, 48, 42, 115, 51, 63, 57, 55, 61, 95, 92, 88, 77, 76, 75, 92, 102, 90, 86, 87, 95, 80, 94, 116, 121, 117, 98, 100, 104, 117, 99, 88, 99, 102, 115, 102, 101, 102, 116, 98, 60, 39, 35, 45, 38, 58, 33, 37, 43, 32, 17, 39, 42, 32, 59, 63, 49, 58, 39};
    }

    static {
        A01();
        A04 = YC.class.getSimpleName();
    }

    public YC(C1635Ym c1635Ym, YD yd) {
        this.A00 = c1635Ym;
        this.A01 = yd;
    }

    private void A02(Set<String> eventsToRetry, Set<String> eventsToDelete) {
        int A0B = this.A01.A0B(Ij.A0G(this.A00), eventsToRetry, eventsToDelete);
        if (A0B > 0) {
            this.A00.A07().A9a(A00(405, 15, 79), C8A.A10, new C8B(A00(28, 44, 46) + A0B));
        }
    }

    @Override // com.meta.analytics.dsp.uinode.J0
    public final JSONObject A4a() {
        int A0F = Ij.A0F(this.A00);
        List<JSONObject> A0C = this.A01.A0C(A0F);
        JSONObject jSONObject = new JSONObject();
        Iterator<JSONObject> it = A0C.iterator();
        while (true) {
            if (it.hasNext()) {
                JSONObject next = it.next();
                try {
                    String optString = next.optString(A00(420, 5, 0));
                    String uuid = UUID.randomUUID().toString();
                    jSONObject.put(uuid, optString);
                    next.put(A00(425, 8, 6), uuid);
                } catch (JSONException e4) {
                    C7k A042 = this.A00.A04();
                    if (A03[2].charAt(22) != 'j') {
                        break;
                    }
                    A03[7] = "eMFgGe3BwzjIEwfr0eq56CqWHHfmSWyD";
                    if (A042.A8z()) {
                        Log.e(A04, A00(134, 72, 126), e4);
                    }
                }
            } else {
                JSONArray jSONArray = new JSONArray((Collection) A0C);
                if (Ij.A0P(this.A00)) {
                    int A08 = Ij.A08(this.A00);
                    JSONArray A043 = C8C.A04(this.A00, A08);
                    if (A03[7].charAt(31) == 'D') {
                        String[] strArr = A03;
                        strArr[3] = "31pn7rS79XEbH8v4aDva0zyRbLn9ex8z";
                        strArr[6] = "3BqVDGT4msLu1Rtl2iVh8DraLvTqVCKW";
                        if (A043 != null) {
                            int eventLimit = A043.length();
                            if (eventLimit > 0) {
                                jSONArray = VP.A02(this.A00, A043, jSONArray, A0F + A08);
                            }
                        }
                    }
                }
                JSONObject jSONObject2 = null;
                try {
                    int eventLimit2 = jSONArray.length();
                    if (eventLimit2 > 0) {
                        jSONObject2 = new JSONObject();
                        int eventLimit3 = jSONObject.length();
                        if (eventLimit3 > 0) {
                            jSONObject2.put(A00(433, 6, 28), jSONObject);
                        }
                        jSONObject2.put(A00(357, 6, 32), jSONArray);
                    }
                    return jSONObject2;
                } catch (JSONException unused) {
                    return null;
                }
            }
        }
        throw new RuntimeException();
    }

    @Override // com.meta.analytics.dsp.uinode.J0
    public final boolean A9A() {
        return this.A01.A0A() > 0;
    }

    @Override // com.meta.analytics.dsp.uinode.J0
    public final void AAi() {
        C8C.A0F(this.A00);
    }

    @Override // com.meta.analytics.dsp.uinode.J0
    public final void ABI(JSONArray jSONArray) {
        int length = jSONArray.length();
        HashSet hashSet = new HashSet(length);
        for (int i9 = 0; i9 < length; i9++) {
            try {
                JSONObject eventJson = jSONArray.getJSONObject(i9);
                String string = eventJson.getString(A00(403, 2, 85));
                if (C8C.A0I(string)) {
                    C8C.A0B(this.A00, string);
                } else {
                    hashSet.add(string);
                }
            } catch (JSONException e4) {
                if (this.A00.A04().A8z()) {
                    Log.e(A04, A00(72, 62, 79), e4);
                }
            }
        }
        A02(hashSet, new HashSet());
    }

    @Override // com.meta.analytics.dsp.uinode.J0
    public final boolean ABJ(JSONArray jSONArray) {
        String A00 = A00(27, 1, 70);
        boolean z8 = true;
        boolean A0P = Ij.A0P(this.A00);
        Set<String> eventsToDelete = new HashSet<>();
        HashSet hashSet = new HashSet();
        for (int i9 = 0; i9 < jSONArray.length(); i9++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i9);
                String string = jSONObject.getString(A00(403, 2, 85));
                if (A00(363, 26, 22).equals(string)) {
                    C1225Ih.A0R(this.A00).A2X(jSONObject.getString(A00(389, 14, 113)));
                    this.A00.A03().AAL();
                } else {
                    int i10 = jSONObject.getInt(A00(353, 4, 94));
                    if (i10 == 1) {
                        if (this.A00.A04().A8z()) {
                            String str = A00(206, 25, 28) + string + A00(13, 14, 27);
                        }
                        if (A0P) {
                            C8C.A0D(string);
                        }
                        eventsToDelete.add(string);
                    } else {
                        String A002 = A00(0, 13, 62);
                        if (i10 >= 1000 && i10 < 2000) {
                            if (this.A00.A04().A8z()) {
                                Log.e(A04, A00(272, 37, 67) + i10 + A002 + string + A00);
                            }
                            if (C8C.A0I(string)) {
                                C8C.A0B(this.A00, string);
                            } else {
                                hashSet.add(string);
                            }
                            z8 = false;
                        } else if (i10 >= 2000 && i10 < 3000) {
                            if (this.A00.A04().A8z()) {
                                Log.e(A04, A00(231, 41, 10) + i10 + A002 + string + A00);
                            }
                            eventsToDelete.add(string);
                            if (A0P) {
                                C8C.A0D(string);
                            }
                        }
                    }
                }
            } catch (JSONException e4) {
                if (this.A00.A04().A8z()) {
                    String eventId = A04;
                    Log.e(eventId, A00(309, 44, 89) + i9 + A00, e4);
                }
                z8 = false;
            }
        }
        A02(hashSet, eventsToDelete);
        return z8;
    }

    @Override // com.meta.analytics.dsp.uinode.J0
    public final void ACo() {
        this.A01.A4J();
        C8C.A07(this.A00);
    }
}
