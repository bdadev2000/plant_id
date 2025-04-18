package com.facebook.ads.internal.exoplayer2.thirdparty.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;
import com.instagram.common.viewpoint.core.IK;
import com.instagram.common.viewpoint.core.IQ;
import java.util.Arrays;
import org.objectweb.asm.Opcodes;

/* loaded from: assets/audience_network.dex */
public final class DummySurface extends Surface {
    public static int A03;
    public static boolean A04;
    public static byte[] A05;
    public static String[] A06 = {"BIp7vzupCQc69wolM1qrlBnEL5MetbDW", "24tJ79UOmEXBcyykx", "xCvv5gABaBgUMcrcs4LVlgCS7", "iwWXLugCxXUhH2", "11hzhE0Xkbsnly22MyoIB7rR66v", "gCs3LWFTmjjaP32jtswNknURctpSvZ", "4q1ECNbz", "paI3IYAtvVDbkAKPOxbipTd"};
    public boolean A00;
    public final boolean A01;
    public final IQ A02;

    public static String A02(int i2, int i3, int i4) {
        byte[] copyOfRange = Arrays.copyOfRange(A05, i2, i2 + i3);
        for (int i5 = 0; i5 < copyOfRange.length; i5++) {
            copyOfRange[i5] = (byte) ((copyOfRange[i5] ^ i4) ^ Opcodes.FREM);
        }
        return new String(copyOfRange);
    }

    public static void A04() {
        A05 = new byte[]{83, 81, 90, 73, 83, 78, 66, 73, 102, 100, 121, 98, 115, 117, 98, 115, 114, 73, 117, 121, 120, 98, 115, 120, 98, 101, 103, 108, Byte.MAX_VALUE, 107, 104, 114, Byte.MAX_VALUE, 83, 85, 82, 70, 65, 67, 69, 76, 69, 83, 83, Byte.MAX_VALUE, 67, 79, 78, 84, 69, 88, 84, 69, 126, 99, 101, 96, 96, Byte.MAX_VALUE, 98, 100, 117, 116, 48, 96, 98, 121, Byte.MAX_VALUE, 98, 48, 100, Byte.MAX_VALUE, 48, 81, 64, 89, 48, 124, 117, 102, 117, 124, 48, 33, 39, 66, 78, 43, 44, 47, 42, 86, 89, 83, 69, 88, 94, 83, 25, 95, 86, 69, 83, 64, 86, 69, 82, 25, 65, 69, 25, 95, 94, 80, 95, 104, 71, 82, 69, 81, 88, 69, 90, 86, 89, 84, 82, 124, 110, 98, 124, 122, 97, 104};
    }

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:147)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:118)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:626)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:39)
     */
    public static synchronized boolean A05(Context context) {
        boolean z2;
        synchronized (DummySurface.class) {
            z2 = true;
            if (!A04) {
                A03 = IK.A02 < 24 ? 0 : A00(context);
                A04 = true;
            }
            if (A03 == 0) {
                z2 = false;
            }
        }
        return z2;
    }

    static {
        A04();
    }

    public DummySurface(IQ iq, SurfaceTexture surfaceTexture, boolean z2) {
        super(surfaceTexture);
        this.A02 = iq;
        this.A01 = z2;
    }

    public static int A00(Context context) {
        String eglQueryString;
        if (IK.A02 < 26) {
            String A02 = A02(127, 7, Opcodes.LUSHR);
            String[] strArr = A06;
            if (strArr[6].length() == strArr[2].length()) {
                throw new RuntimeException();
            }
            String[] strArr2 = A06;
            strArr2[6] = "9NBzrv4O";
            strArr2[2] = "dOh1vNCRJLmwoUK8kN24J42WE";
            if (A02.equals(IK.A05) || A02(85, 6, 104).equals(IK.A06)) {
                return 0;
            }
        }
        if ((IK.A02 < 26 && !context.getPackageManager().hasSystemFeature(A02(91, 36, 69))) || (eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) == null || !eglQueryString.contains(A02(0, 25, 100))) {
            return 0;
        }
        String A022 = A02(25, 27, 82);
        String[] strArr3 = A06;
        if (strArr3[6].length() == strArr3[2].length()) {
            throw new RuntimeException();
        }
        String[] strArr4 = A06;
        strArr4[4] = "MMzWoZaG5rKncs6KGkf7KtQ7FMr";
        strArr4[5] = "AmTtu2xzwZpmljoFnnZCY6SmnReBn7";
        if (eglQueryString.contains(A022)) {
            return 1;
        }
        return 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.facebook.ads.internal.exoplayer2.thirdparty.video.DummySurface A01(android.content.Context r4, boolean r5) {
        /*
            A03()
            r3 = 0
            if (r5 == 0) goto L2a
            boolean r4 = A05(r4)
            java.lang.String[] r2 = com.facebook.ads.internal.exoplayer2.thirdparty.video.DummySurface.A06
            r0 = 6
            r1 = r2[r0]
            r0 = 2
            r0 = r2[r0]
            int r1 = r1.length()
            int r0 = r0.length()
            if (r1 == r0) goto L3e
            java.lang.String[] r2 = com.facebook.ads.internal.exoplayer2.thirdparty.video.DummySurface.A06
            java.lang.String r1 = "duZpseMwc0gGjqrDW9WsXWuTAvu"
            r0 = 4
            r2[r0] = r1
            java.lang.String r1 = "L8PQQ0Rvj3M4DBbwizvKN4JEZ06Jax"
            r0 = 5
            r2[r0] = r1
            if (r4 == 0) goto L3c
        L2a:
            r0 = 1
        L2b:
            com.instagram.common.viewpoint.core.AbstractC0576Hf.A04(r0)
            com.facebook.ads.redexgen.X.IQ r0 = new com.facebook.ads.redexgen.X.IQ
            r0.<init>()
            if (r5 == 0) goto L37
            int r3 = com.facebook.ads.internal.exoplayer2.thirdparty.video.DummySurface.A03
        L37:
            com.facebook.ads.internal.exoplayer2.thirdparty.video.DummySurface r0 = r0.A04(r3)
            return r0
        L3c:
            r0 = 0
            goto L2b
        L3e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.exoplayer2.thirdparty.video.DummySurface.A01(android.content.Context, boolean):com.facebook.ads.internal.exoplayer2.thirdparty.video.DummySurface");
    }

    public static void A03() {
        if (IK.A02 >= 17) {
        } else {
            throw new UnsupportedOperationException(A02(52, 33, 98));
        }
    }

    @Override // android.view.Surface
    public final void release() {
        super.release();
        synchronized (this.A02) {
            if (!this.A00) {
                this.A02.A05();
                this.A00 = true;
            }
        }
    }
}
