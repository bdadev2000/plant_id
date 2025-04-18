package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class OneDReader implements Reader {
    /* JADX WARN: Removed duplicated region for block: B:34:0x0077 A[Catch: ReaderException -> 0x00c8, TRY_LEAVE, TryCatch #3 {ReaderException -> 0x00c8, blocks: (B:32:0x0071, B:34:0x0077), top: B:31:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ce A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r22, java.util.Map<com.google.zxing.DecodeHintType, ?> r23) throws com.google.zxing.NotFoundException {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    public static float patternMatchVariance(int[] iArr, int[] iArr2, float f2) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f3 = i2;
        float f4 = f3 / i3;
        float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            float f7 = iArr2[i5] * f4;
            float f8 = iArr[i5];
            float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }

    public static void recordPattern(BitArray bitArray, int i2, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i3 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int size = bitArray.getSize();
        if (i2 >= size) {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean z2 = !bitArray.get(i2);
        while (i2 < size) {
            if (bitArray.get(i2) == z2) {
                i3++;
                if (i3 == length) {
                    break;
                }
                iArr[i3] = 1;
                z2 = !z2;
            } else {
                iArr[i3] = iArr[i3] + 1;
            }
            i2++;
        }
        if (i3 != length) {
            if (i3 != length - 1 || i2 != size) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    public static void recordPatternInReverse(BitArray bitArray, int i2, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z2 = bitArray.get(i2);
        while (i2 > 0 && length >= 0) {
            i2--;
            if (bitArray.get(i2) != z2) {
                length--;
                z2 = !z2;
            }
        }
        if (length >= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        recordPattern(bitArray, i2 + 1, iArr);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    public abstract Result decodeRow(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e) {
            if (map != null && map.containsKey(DecodeHintType.TRY_HARDER) && binaryBitmap.isRotateSupported()) {
                BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
                Result doDecode = doDecode(rotateCounterClockwise, map);
                Map<ResultMetadataType, Object> resultMetadata = doDecode.getResultMetadata();
                int i2 = 270;
                if (resultMetadata != null) {
                    ResultMetadataType resultMetadataType = ResultMetadataType.ORIENTATION;
                    if (resultMetadata.containsKey(resultMetadataType)) {
                        i2 = (((Integer) resultMetadata.get(resultMetadataType)).intValue() + 270) % 360;
                    }
                }
                doDecode.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i2));
                ResultPoint[] resultPoints = doDecode.getResultPoints();
                if (resultPoints != null) {
                    int height = rotateCounterClockwise.getHeight();
                    for (int i3 = 0; i3 < resultPoints.length; i3++) {
                        resultPoints[i3] = new ResultPoint((height - resultPoints[i3].getY()) - 1.0f, resultPoints[i3].getX());
                    }
                }
                return doDecode;
            }
            throw e;
        }
    }
}
