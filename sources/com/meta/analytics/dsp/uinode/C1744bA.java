package com.meta.analytics.dsp.uinode;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Base64;
import android.view.View;
import com.applovin.exoplayer2.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.redexgen.X.bA, reason: case insensitive filesystem */
/* loaded from: assets/audience_network.dex */
public final class C1744bA extends AbstractC08020r {
    public static byte[] A0E;
    public static String[] A0F = {"AssHqLhhTyxVt8qn53NKh99glOkg76Zu", "x1ztYNG5B8Yh1fFD9IH28HNLftAJV3DA", "znnjTlsCkULP275jnLAB9UeCHu0mwbQ7", "ZnygQwUKP9vcdRc0ZykdXWpZHZyK4mhg", "TpC7jOiqrQiocH3FafzZC3NFqv5kOY4I", "3gx5dg94KuTf2YQxiOsayTUTecU9lJlq", "0JsvRjae8Zudzo37ubyWjxcMew8uJTBn", "UifaSDkpcUGfPYHJ0LY2wgaMbKOuGMMh"};
    public View A00;
    public View A01;
    public AnonymousClass12 A02;
    public AnonymousClass13 A03;
    public JS A04;
    public String A05;
    public List<View> A06;
    public boolean A07;
    public boolean A08;
    public boolean A09;
    public boolean A0A;
    public boolean A0B;
    public boolean A0C;
    public final C1754bK A0D;

    public static String A01(int i9, int i10, int i11) {
        byte[] copyOfRange = Arrays.copyOfRange(A0E, i9, i9 + i10);
        for (int i12 = 0; i12 < copyOfRange.length; i12++) {
            copyOfRange[i12] = (byte) ((copyOfRange[i12] - i11) - 15);
        }
        return new String(copyOfRange);
    }

    public static void A05() {
        A0E = new byte[]{-57, -16, -20, -21, -99, -30, -11, -32, -30, -19, -15, -26, -20, -21, -30, -11, -12, -12, -17, -18, -27, -18, -29, -11, -11, -53, -44, -47, -53, -45, -55, -54, -44, -51, -42, -23, -27, -29, -46, -48, -39, -38, -33, -27, -28, -53, -58, -80, -76, -88, -82, -84, -35, -38, -28, -27, -21, -29, -30, -25, -33, -12, -25, -29, -11, -97, -110, -98, -103, -86, -99, -87, -77, -44, -57, -40, -57, -40, -23, -35, -14, -28, -15, -36, -42, -31, -26, -31, -18, -19, -20, -11, -86, -87, -78, -99, -84, -10, -4, -15, -18, -15, -24, -26, -24, -19, -96, -94, -107, -109, -111, -109, -104, -107, -113, -99, -107, -108, -103, -111, -120, 126, -113, 122, -38, -43, -56, -41, -38, -49, -42, -37, -2, -17, 2, -2, -114, -109, -118, Ascii.DEL, -44, -62, -43, -47, -36, -43, -46, -43, -42, -34, -43, -103, -116, -120, -102, -114, -127, 125, -113, Ascii.DEL, -118, -121, -115, -120, -77, -96, 114, 93, -100, 100, 88, -81, 114, 93, -100, -75, -2, -5, -67, -88, -25, -81, -93, -4, -67, -88, -25, 0};
    }

    static {
        A05();
    }

    public C1744bA(C1636Yn c1636Yn, AbstractC08030s abstractC08030s, RE re, C1754bK c1754bK) {
        super(c1636Yn, abstractC08030s, re);
        this.A02 = AnonymousClass12.A03;
        this.A03 = null;
        this.A0D = c1754bK;
    }

    private String A02(View view) {
        try {
            JSONObject json = A04(view);
            return json.toString();
        } catch (JSONException unused) {
            return A01(0, 14, 110);
        }
    }

    private String A03(View view) {
        int width = view.getWidth();
        String A01 = A01(0, 0, 124);
        if (width <= 0 || view.getHeight() <= 0) {
            return A01;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            createBitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            view.draw(new Canvas(createBitmap));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(Bitmap.CompressFormat.JPEG, this.A0D.A0A(), byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception unused) {
            return A01;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x014e, code lost:
    
        if (r5 != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0150, code lost:
    
        r4 = A01(47, 5, 56);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0160, code lost:
    
        if ((r10 instanceof com.facebook.ads.MediaView) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0162, code lost:
    
        r4 = A01(56, 9, 111);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0170, code lost:
    
        if ((r10 instanceof android.view.ViewGroup) == false) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0172, code lost:
    
        r4 = A01(155, 9, 9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x015b, code lost:
    
        if (r5 != false) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.json.JSONObject A04(android.view.View r10) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 401
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meta.analytics.dsp.uinode.C1744bA.A04(android.view.View):org.json.JSONObject");
    }

    @Override // com.meta.analytics.dsp.uinode.AbstractC08020r
    public final void A06(Map<String, String> map) {
        if (this.A0D == null) {
            return;
        }
        JS js = this.A04;
        if (js != null) {
            map.put(A01(97, 3, 121), String.valueOf(js.A04()));
        }
        if (this.A07) {
            map.put(A01(83, 3, 95), Boolean.TRUE.toString());
        }
        if (this.A0B) {
            map.put(A01(89, 3, 112), Boolean.TRUE.toString());
        }
        if (this.A08) {
            map.put(A01(92, 5, 45), Boolean.TRUE.toString());
        }
        if (this.A00 != null && this.A0D.A0T()) {
            map.put(A01(151, 4, 20), A02(this.A00));
        }
        if (this.A00 != null && this.A0D.A0U()) {
            map.put(A01(124, 8, 88), A03(this.A00));
        }
        if (this.A0A) {
            map.put(A01(86, 3, 105), Boolean.TRUE.toString());
        }
        AnonymousClass12 anonymousClass12 = this.A02;
        if (anonymousClass12 != null) {
            map.put(A01(106, 14, 33), anonymousClass12.toString());
        }
        if (this.A09) {
            map.put(A01(140, 4, 80), Boolean.TRUE.toString());
        }
        View view = this.A01;
        if (A0F[0].charAt(27) == 'G') {
            throw new RuntimeException();
        }
        A0F[0] = "P6MGDEoKncCrYirOOQ1jmq4l0Ss52l96";
        if (view != null) {
            map.put(A01(69, 4, 45), String.valueOf((int) (view.getWidth() / LD.A02)));
            map.put(A01(65, 4, 34), String.valueOf((int) (this.A01.getHeight() / LD.A02)));
        }
        AnonymousClass13 anonymousClass13 = this.A03;
        if (anonymousClass13 != null) {
            map.put(A01(73, 5, 87), anonymousClass13.toString());
        }
        String str = this.A05;
        if (str != null) {
            map.put(A01(34, 11, 98), str);
        }
        if (this.A0C) {
            map.put(A01(78, 5, 108), Boolean.TRUE.toString());
        }
        this.A0D.A0O(map);
    }

    public final void A07(View view) {
        this.A01 = view;
    }

    public final void A08(View view) {
        this.A00 = view;
    }

    public final void A09(AnonymousClass12 anonymousClass12) {
        this.A02 = anonymousClass12;
    }

    public final void A0A(AnonymousClass13 anonymousClass13) {
        this.A03 = anonymousClass13;
    }

    public final void A0B(JS js) {
        this.A04 = js;
    }

    public final void A0C(String str) {
        this.A05 = str;
    }

    public final void A0D(List<View> clickableViews) {
        this.A06 = clickableViews;
    }

    public final void A0E(boolean z8) {
        this.A07 = z8;
    }

    public final void A0F(boolean z8) {
        this.A08 = z8;
    }

    public final void A0G(boolean z8) {
        this.A09 = z8;
    }

    public final void A0H(boolean z8) {
        this.A0A = z8;
    }

    public final void A0I(boolean z8) {
        this.A0B = z8;
    }

    public final void A0J(boolean z8) {
        this.A0C = z8;
    }
}
