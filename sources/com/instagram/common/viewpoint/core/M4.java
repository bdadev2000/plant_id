package com.instagram.common.viewpoint.core;

import android.app.KeyguardManager;
import android.content.Context;
import java.util.Arrays;
import java.util.Map;

/* loaded from: assets/audience_network.dex */
public final class M4 {
    public static byte[] A00;
    public static final String A01;

    public static String A00(int i2, int i3, int i4) {
        byte[] copyOfRange = Arrays.copyOfRange(A00, i2, i2 + i3);
        for (int i5 = 0; i5 < copyOfRange.length; i5++) {
            copyOfRange[i5] = (byte) ((copyOfRange[i5] ^ i4) ^ 102);
        }
        return new String(copyOfRange);
    }

    public static void A01() {
        A00 = new byte[]{18, 40, 38, 58, 36, 54, 34, 49, 39, 17, 29, 8, 73, 79, 72, 88, 91, 74, 72, 71, 75, 69, 84, 65, 69, 94};
    }

    static {
        A01();
        A01 = M4.class.getSimpleName();
    }

    public static boolean A02(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(A00(1, 8, 37));
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean A03(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        String str = map.get(A00(16, 5, 74));
        String str2 = map.get(A00(21, 5, 84));
        String keyguardRestrictedInputMode = map.get(A00(9, 3, 28));
        if (str != null) {
            String flagDismissKeyguard = A00(0, 1, 69);
            if (str.equals(flagDismissKeyguard) && str2 != null && str2.equals(flagDismissKeyguard) && keyguardRestrictedInputMode != null && keyguardRestrictedInputMode.equals(A00(12, 4, 91))) {
                return true;
            }
        }
        return false;
    }

    public static boolean A04(Map<String, String> map) {
        String keyguardRestrictedInputMode;
        if (map == null || map.isEmpty()) {
            return false;
        }
        String str = map.get(A00(16, 5, 74));
        String str2 = map.get(A00(21, 5, 84));
        String flagDismissKeyguard = A00(0, 1, 69);
        return (str == null || !str.equals(flagDismissKeyguard)) && (str2 == null || !str2.equals(flagDismissKeyguard)) && (keyguardRestrictedInputMode = map.get(A00(9, 3, 28))) != null && keyguardRestrictedInputMode.equals(A00(12, 4, 91));
    }
}
