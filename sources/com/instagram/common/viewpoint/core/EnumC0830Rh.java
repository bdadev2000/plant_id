package com.instagram.common.viewpoint.core;

import java.util.Arrays;

/* renamed from: com.facebook.ads.redexgen.X.Rh, reason: case insensitive filesystem */
/* loaded from: assets/audience_network.dex */
public enum EnumC0830Rh {
    A04,
    A08,
    A07,
    A0A,
    A05,
    A02,
    A06,
    A03,
    A09;

    public static byte[] A00;

    public static String A00(int i2, int i3, int i4) {
        byte[] copyOfRange = Arrays.copyOfRange(A00, i2, i2 + i3);
        for (int i5 = 0; i5 < copyOfRange.length; i5++) {
            copyOfRange[i5] = (byte) ((copyOfRange[i5] - i4) - 120);
        }
        return new String(copyOfRange);
    }

    public static void A01() {
        A00 = new byte[]{-11, 8, -7, -7, -8, 5, -4, 1, -6, -27, -14, -14, -17, -14, -8, -13, -5, -12, -21, -36, -16, -18, -32, -33, 12, 8, -3, 21, -2, -3, -1, 7, 27, -1, 11, 9, 12, 8, 1, 16, 1, 0, -23, -21, -34, -23, -38, -21, -34, -35, 47, 49, 36, 47, 32, 49, 40, 45, 38, 19, 11, 9, 16, 16, 5, 4, -14, -13, -32, -15, -13, -28, -29};
    }

    static {
        A01();
    }
}
