package com.google.zxing.aztec.detector;

import android.support.v4.media.d;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

/* loaded from: classes2.dex */
public final class Detector {
    private static final int[] EXPECTED_CORNER_BITS = {3808, 476, 2107, 1799};
    private boolean compact;
    private final BitMatrix image;
    private int nbCenterLayers;
    private int nbDataBlocks;
    private int nbLayers;
    private int shift;

    /* loaded from: classes2.dex */
    public static final class Point {
        private final int x;

        /* renamed from: y, reason: collision with root package name */
        private final int f28928y;

        public Point(int i2, int i3) {
            this.x = i2;
            this.f28928y = i3;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.f28928y;
        }

        public ResultPoint toResultPoint() {
            return new ResultPoint(getX(), getY());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(this.x);
            sb.append(' ');
            return d.o(sb, this.f28928y, '>');
        }
    }

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private static float distance(Point point, Point point2) {
        return MathUtils.distance(point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    private static ResultPoint[] expandSquare(ResultPoint[] resultPointArr, int i2, int i3) {
        float f2 = i3 / (i2 * 2.0f);
        float x = resultPointArr[0].getX() - resultPointArr[2].getX();
        float y2 = resultPointArr[0].getY() - resultPointArr[2].getY();
        float x2 = (resultPointArr[2].getX() + resultPointArr[0].getX()) / 2.0f;
        float y3 = (resultPointArr[2].getY() + resultPointArr[0].getY()) / 2.0f;
        float f3 = x * f2;
        float f4 = y2 * f2;
        ResultPoint resultPoint = new ResultPoint(x2 + f3, y3 + f4);
        ResultPoint resultPoint2 = new ResultPoint(x2 - f3, y3 - f4);
        float x3 = resultPointArr[1].getX() - resultPointArr[3].getX();
        float y4 = resultPointArr[1].getY() - resultPointArr[3].getY();
        float x4 = (resultPointArr[3].getX() + resultPointArr[1].getX()) / 2.0f;
        float y5 = (resultPointArr[3].getY() + resultPointArr[1].getY()) / 2.0f;
        float f5 = x3 * f2;
        float f6 = f2 * y4;
        return new ResultPoint[]{resultPoint, new ResultPoint(x4 + f5, y5 + f6), resultPoint2, new ResultPoint(x4 - f5, y5 - f6)};
    }

    private void extractParameters(ResultPoint[] resultPointArr) throws NotFoundException {
        long j2;
        long j3;
        if (!isValid(resultPointArr[0]) || !isValid(resultPointArr[1]) || !isValid(resultPointArr[2]) || !isValid(resultPointArr[3])) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i2 = this.nbCenterLayers * 2;
        int[] iArr = {sampleLine(resultPointArr[0], resultPointArr[1], i2), sampleLine(resultPointArr[1], resultPointArr[2], i2), sampleLine(resultPointArr[2], resultPointArr[3], i2), sampleLine(resultPointArr[3], resultPointArr[0], i2)};
        this.shift = getRotation(iArr, i2);
        long j4 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = iArr[(this.shift + i3) % 4];
            if (this.compact) {
                j2 = j4 << 7;
                j3 = (i4 >> 1) & 127;
            } else {
                j2 = j4 << 10;
                j3 = ((i4 >> 2) & 992) + ((i4 >> 1) & 31);
            }
            j4 = j2 + j3;
        }
        int correctedParameterData = getCorrectedParameterData(j4, this.compact);
        if (this.compact) {
            this.nbLayers = (correctedParameterData >> 6) + 1;
            this.nbDataBlocks = (correctedParameterData & 63) + 1;
        } else {
            this.nbLayers = (correctedParameterData >> 11) + 1;
            this.nbDataBlocks = (correctedParameterData & 2047) + 1;
        }
    }

    private ResultPoint[] getBullsEyeCorners(Point point) throws NotFoundException {
        this.nbCenterLayers = 1;
        Point point2 = point;
        Point point3 = point2;
        Point point4 = point3;
        boolean z2 = true;
        while (this.nbCenterLayers < 9) {
            Point firstDifferent = getFirstDifferent(point, z2, 1, -1);
            Point firstDifferent2 = getFirstDifferent(point2, z2, 1, 1);
            Point firstDifferent3 = getFirstDifferent(point3, z2, -1, 1);
            Point firstDifferent4 = getFirstDifferent(point4, z2, -1, -1);
            if (this.nbCenterLayers > 2) {
                double distance = (distance(firstDifferent4, firstDifferent) * this.nbCenterLayers) / (distance(point4, point) * (this.nbCenterLayers + 2));
                if (distance < 0.75d || distance > 1.25d || !isWhiteOrBlackRectangle(firstDifferent, firstDifferent2, firstDifferent3, firstDifferent4)) {
                    break;
                }
            }
            z2 = !z2;
            this.nbCenterLayers++;
            point4 = firstDifferent4;
            point = firstDifferent;
            point2 = firstDifferent2;
            point3 = firstDifferent3;
        }
        int i2 = this.nbCenterLayers;
        if (i2 != 5 && i2 != 7) {
            throw NotFoundException.getNotFoundInstance();
        }
        this.compact = i2 == 5;
        ResultPoint[] resultPointArr = {new ResultPoint(point.getX() + 0.5f, point.getY() - 0.5f), new ResultPoint(point2.getX() + 0.5f, point2.getY() + 0.5f), new ResultPoint(point3.getX() - 0.5f, point3.getY() + 0.5f), new ResultPoint(point4.getX() - 0.5f, point4.getY() - 0.5f)};
        int i3 = this.nbCenterLayers;
        return expandSquare(resultPointArr, (i3 * 2) - 3, i3 * 2);
    }

    private int getColor(Point point, Point point2) {
        float distance = distance(point, point2);
        float x = (point2.getX() - point.getX()) / distance;
        float y2 = (point2.getY() - point.getY()) / distance;
        float x2 = point.getX();
        float y3 = point.getY();
        boolean z2 = this.image.get(point.getX(), point.getY());
        int ceil = (int) Math.ceil(distance);
        int i2 = 0;
        for (int i3 = 0; i3 < ceil; i3++) {
            x2 += x;
            y3 += y2;
            if (this.image.get(MathUtils.round(x2), MathUtils.round(y3)) != z2) {
                i2++;
            }
        }
        float f2 = i2 / distance;
        if (f2 <= 0.1f || f2 >= 0.9f) {
            return (f2 <= 0.1f) == z2 ? 1 : -1;
        }
        return 0;
    }

    private static int getCorrectedParameterData(long j2, boolean z2) throws NotFoundException {
        int i2;
        int i3;
        if (z2) {
            i2 = 7;
            i3 = 2;
        } else {
            i2 = 10;
            i3 = 4;
        }
        int i4 = i2 - i3;
        int[] iArr = new int[i2];
        for (int i5 = i2 - 1; i5 >= 0; i5--) {
            iArr[i5] = ((int) j2) & 15;
            j2 >>= 4;
        }
        try {
            new ReedSolomonDecoder(GenericGF.AZTEC_PARAM).decode(iArr, i4);
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                i6 = (i6 << 4) + iArr[i7];
            }
            return i6;
        } catch (ReedSolomonException unused) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private int getDimension() {
        if (this.compact) {
            return (this.nbLayers * 4) + 11;
        }
        int i2 = this.nbLayers;
        if (i2 <= 4) {
            return (i2 * 4) + 15;
        }
        return ((((i2 - 4) / 8) + 1) * 2) + (i2 * 4) + 15;
    }

    private Point getFirstDifferent(Point point, boolean z2, int i2, int i3) {
        int x = point.getX() + i2;
        int y2 = point.getY();
        while (true) {
            y2 += i3;
            if (!isValid(x, y2) || this.image.get(x, y2) != z2) {
                break;
            }
            x += i2;
        }
        int i4 = x - i2;
        int i5 = y2 - i3;
        while (isValid(i4, i5) && this.image.get(i4, i5) == z2) {
            i4 += i2;
        }
        int i6 = i4 - i2;
        while (isValid(i6, i5) && this.image.get(i6, i5) == z2) {
            i5 += i3;
        }
        return new Point(i6, i5 - i3);
    }

    private Point getMatrixCenter() {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        ResultPoint resultPoint4;
        ResultPoint resultPoint5;
        ResultPoint resultPoint6;
        ResultPoint resultPoint7;
        ResultPoint resultPoint8;
        try {
            ResultPoint[] detect = new WhiteRectangleDetector(this.image).detect();
            resultPoint3 = detect[0];
            resultPoint4 = detect[1];
            resultPoint2 = detect[2];
            resultPoint = detect[3];
        } catch (NotFoundException unused) {
            int width = this.image.getWidth() / 2;
            int height = this.image.getHeight() / 2;
            int i2 = width + 7;
            int i3 = height - 7;
            ResultPoint resultPoint9 = getFirstDifferent(new Point(i2, i3), false, 1, -1).toResultPoint();
            int i4 = height + 7;
            ResultPoint resultPoint10 = getFirstDifferent(new Point(i2, i4), false, 1, 1).toResultPoint();
            int i5 = width - 7;
            ResultPoint resultPoint11 = getFirstDifferent(new Point(i5, i4), false, -1, 1).toResultPoint();
            resultPoint = getFirstDifferent(new Point(i5, i3), false, -1, -1).toResultPoint();
            resultPoint2 = resultPoint11;
            resultPoint3 = resultPoint9;
            resultPoint4 = resultPoint10;
        }
        int round = MathUtils.round((resultPoint2.getX() + (resultPoint4.getX() + (resultPoint.getX() + resultPoint3.getX()))) / 4.0f);
        int round2 = MathUtils.round((resultPoint2.getY() + (resultPoint4.getY() + (resultPoint.getY() + resultPoint3.getY()))) / 4.0f);
        try {
            ResultPoint[] detect2 = new WhiteRectangleDetector(this.image, 15, round, round2).detect();
            resultPoint5 = detect2[0];
            resultPoint6 = detect2[1];
            resultPoint7 = detect2[2];
            resultPoint8 = detect2[3];
        } catch (NotFoundException unused2) {
            int i6 = round + 7;
            int i7 = round2 - 7;
            resultPoint5 = getFirstDifferent(new Point(i6, i7), false, 1, -1).toResultPoint();
            int i8 = round2 + 7;
            resultPoint6 = getFirstDifferent(new Point(i6, i8), false, 1, 1).toResultPoint();
            int i9 = round - 7;
            resultPoint7 = getFirstDifferent(new Point(i9, i8), false, -1, 1).toResultPoint();
            resultPoint8 = getFirstDifferent(new Point(i9, i7), false, -1, -1).toResultPoint();
        }
        return new Point(MathUtils.round((resultPoint7.getX() + (resultPoint6.getX() + (resultPoint8.getX() + resultPoint5.getX()))) / 4.0f), MathUtils.round((resultPoint7.getY() + (resultPoint6.getY() + (resultPoint8.getY() + resultPoint5.getY()))) / 4.0f));
    }

    private ResultPoint[] getMatrixCornerPoints(ResultPoint[] resultPointArr) {
        return expandSquare(resultPointArr, this.nbCenterLayers * 2, getDimension());
    }

    private static int getRotation(int[] iArr, int i2) throws NotFoundException {
        int i3 = 0;
        for (int i4 : iArr) {
            i3 = (i3 << 3) + ((i4 >> (i2 - 2)) << 1) + (i4 & 1);
        }
        int i5 = ((i3 & 1) << 11) + (i3 >> 1);
        for (int i6 = 0; i6 < 4; i6++) {
            if (Integer.bitCount(EXPECTED_CORNER_BITS[i6] ^ i5) <= 2) {
                return i6;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private boolean isValid(int i2, int i3) {
        return i2 >= 0 && i2 < this.image.getWidth() && i3 > 0 && i3 < this.image.getHeight();
    }

    private boolean isWhiteOrBlackRectangle(Point point, Point point2, Point point3, Point point4) {
        Point point5 = new Point(point.getX() - 3, point.getY() + 3);
        Point point6 = new Point(point2.getX() - 3, point2.getY() - 3);
        Point point7 = new Point(point3.getX() + 3, point3.getY() - 3);
        Point point8 = new Point(point4.getX() + 3, point4.getY() + 3);
        int color = getColor(point8, point5);
        return color != 0 && getColor(point5, point6) == color && getColor(point6, point7) == color && getColor(point7, point8) == color;
    }

    private BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        GridSampler gridSampler = GridSampler.getInstance();
        int dimension = getDimension();
        float f2 = dimension / 2.0f;
        int i2 = this.nbCenterLayers;
        float f3 = f2 - i2;
        float f4 = f2 + i2;
        return gridSampler.sampleGrid(bitMatrix, dimension, dimension, f3, f3, f4, f3, f4, f4, f3, f4, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY());
    }

    private int sampleLine(ResultPoint resultPoint, ResultPoint resultPoint2, int i2) {
        float distance = distance(resultPoint, resultPoint2);
        float f2 = distance / i2;
        float x = resultPoint.getX();
        float y2 = resultPoint.getY();
        float x2 = ((resultPoint2.getX() - resultPoint.getX()) * f2) / distance;
        float y3 = ((resultPoint2.getY() - resultPoint.getY()) * f2) / distance;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            float f3 = i4;
            if (this.image.get(MathUtils.round((f3 * x2) + x), MathUtils.round((f3 * y3) + y2))) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    public AztecDetectorResult detect() throws NotFoundException {
        return detect(false);
    }

    private static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private boolean isValid(ResultPoint resultPoint) {
        return isValid(MathUtils.round(resultPoint.getX()), MathUtils.round(resultPoint.getY()));
    }

    public AztecDetectorResult detect(boolean z2) throws NotFoundException {
        ResultPoint[] bullsEyeCorners = getBullsEyeCorners(getMatrixCenter());
        if (z2) {
            ResultPoint resultPoint = bullsEyeCorners[0];
            bullsEyeCorners[0] = bullsEyeCorners[2];
            bullsEyeCorners[2] = resultPoint;
        }
        extractParameters(bullsEyeCorners);
        BitMatrix bitMatrix = this.image;
        int i2 = this.shift;
        return new AztecDetectorResult(sampleGrid(bitMatrix, bullsEyeCorners[i2 % 4], bullsEyeCorners[(i2 + 1) % 4], bullsEyeCorners[(i2 + 2) % 4], bullsEyeCorners[(i2 + 3) % 4]), getMatrixCornerPoints(bullsEyeCorners), this.compact, this.nbDataBlocks, this.nbLayers);
    }
}
