package com.google.zxing.common.reedsolomon;

import androidx.compose.foundation.text.input.a;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators;
    private final GenericGF field;

    public ReedSolomonEncoder(GenericGF genericGF) {
        this.field = genericGF;
        ArrayList arrayList = new ArrayList();
        this.cachedGenerators = arrayList;
        arrayList.add(new GenericGFPoly(genericGF, new int[]{1}));
    }

    private GenericGFPoly buildGenerator(int i2) {
        if (i2 >= this.cachedGenerators.size()) {
            GenericGFPoly genericGFPoly = (GenericGFPoly) a.g(this.cachedGenerators, 1);
            for (int size = this.cachedGenerators.size(); size <= i2; size++) {
                GenericGF genericGF = this.field;
                genericGFPoly = genericGFPoly.multiply(new GenericGFPoly(genericGF, new int[]{1, genericGF.exp(genericGF.getGeneratorBase() + (size - 1))}));
                this.cachedGenerators.add(genericGFPoly);
            }
        }
        return this.cachedGenerators.get(i2);
    }

    public void encode(int[] iArr, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i2;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        GenericGFPoly buildGenerator = buildGenerator(i2);
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        int[] coefficients = new GenericGFPoly(this.field, iArr2).multiplyByMonomial(i2, 1).divide(buildGenerator)[1].getCoefficients();
        int length2 = i2 - coefficients.length;
        for (int i3 = 0; i3 < length2; i3++) {
            iArr[length + i3] = 0;
        }
        System.arraycopy(coefficients, 0, iArr, length + length2, coefficients.length);
    }
}
