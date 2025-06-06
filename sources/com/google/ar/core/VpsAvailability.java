package com.google.ar.core;

import androidx.annotation.NonNull;
import com.google.ar.core.exceptions.FatalException;

/* loaded from: classes2.dex */
public enum VpsAvailability {
    UNKNOWN(0),
    AVAILABLE(1),
    UNAVAILABLE(2),
    ERROR_INTERNAL(-1),
    ERROR_NETWORK_CONNECTION(-2),
    ERROR_NOT_AUTHORIZED(-3),
    ERROR_RESOURCE_EXHAUSTED(-4);

    final int nativeCode;

    VpsAvailability(int i9) {
        this.nativeCode = i9;
    }

    @NonNull
    public static VpsAvailability forNumber(int i9) {
        for (VpsAvailability vpsAvailability : values()) {
            if (vpsAvailability.nativeCode == i9) {
                return vpsAvailability;
            }
        }
        throw new FatalException(A.a("Unexpected value for native VpsAvailability, value=", (byte) 51, i9));
    }
}
