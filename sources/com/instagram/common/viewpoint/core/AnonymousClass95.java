package com.instagram.common.viewpoint.core;

import java.util.Arrays;

/* renamed from: com.facebook.ads.redexgen.X.95, reason: invalid class name */
/* loaded from: assets/audience_network.dex */
public enum AnonymousClass95 {
    A03,
    A05,
    A04;

    public static byte[] A00;
    public static String[] A01 = {"seqAlwXEOvzJRT", "2kwhh1C9lzELPF4tFLY2", "Jj", "sVBc1MgsVLAc0bDFJylP1xNQuOv", "HeA0CnCob9O6sWYI", "hyCzZMBJpVm0WSm5X0p8b9", "uKys1875npMZUjGe7nShhq", "p4I3Upmes50ZeKIDqz8mdw8ttLtPhh"};

    public static String A00(int i2, int i3, int i4) {
        byte[] copyOfRange = Arrays.copyOfRange(A00, i2, i2 + i3);
        int i5 = 0;
        while (true) {
            int length = copyOfRange.length;
            String[] strArr = A01;
            if (strArr[1].length() == strArr[2].length()) {
                throw new RuntimeException();
            }
            A01[3] = "YOiRDsLdwk415VnA";
            if (i5 >= length) {
                return new String(copyOfRange);
            }
            copyOfRange[i5] = (byte) ((copyOfRange[i5] ^ i4) ^ 80);
            i5++;
        }
    }

    public static void A01() {
        A00 = new byte[]{93, 94, 79, 88, 83, 94, 95, 68, 73, 94, 88, 84, 73, 95, 72, 72, 75, 90, 77, 70, 81, 76, 91, 72, 72, 75, 92, 81, 90, 65, 65, 81, 93, 67, 79, 66, 66, 88, 89, 73, 68, 83, 85, 89, 68, 82, 69, 73, 66, 89, 73, 80, 83, 66, 85, 94};
    }

    static {
        A01();
    }
}
