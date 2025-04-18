package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes4.dex */
public final class PointProviderLab implements PointProvider {
    @Override // com.google.android.material.color.utilities.PointProvider
    public double distance(double[] dArr, double[] dArr2) {
        double d = dArr[0] - dArr2[0];
        double d2 = dArr[1] - dArr2[1];
        double d3 = dArr[2] - dArr2[2];
        return (d3 * d3) + (d2 * d2) + (d * d);
    }

    @Override // com.google.android.material.color.utilities.PointProvider
    public double[] fromInt(int i2) {
        double[] labFromArgb = ColorUtils.labFromArgb(i2);
        return new double[]{labFromArgb[0], labFromArgb[1], labFromArgb[2]};
    }

    @Override // com.google.android.material.color.utilities.PointProvider
    public int toInt(double[] dArr) {
        return ColorUtils.argbFromLab(dArr[0], dArr[1], dArr[2]);
    }
}
