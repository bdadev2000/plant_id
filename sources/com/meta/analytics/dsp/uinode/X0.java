package com.meta.analytics.dsp.uinode;

import android.util.Log;
import com.applovin.exoplayer2.common.base.Ascii;
import com.facebook.ads.internal.exoplayer2.thirdparty.Format;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: assets/audience_network.dex */
public final class X0 implements InterfaceC1100Cy {
    public static byte[] A0E;
    public static String[] A0F = {"NvpEV7R7ZS5uNQ5TFb57EvhjwIZCTQku", "DdUDSiCrGQUoEviiVingxcQFfwNbDa", "hetlnsRY2wqGSQpiRITV", "h6jp2Z", "H3YUjFwBN5I", "HDGGogJPAHFPfOUxVzLcmHcfpTDLo", "fvYnB4Gz7ZvwaQ8Y4EtF6ZMgsdzNJKEV", "DcduA0DmY9P8nwkm6XpLf73k0ckt"};
    public long A00;
    public long A01;
    public C4 A02;
    public D3 A03;
    public String A04;
    public boolean A05;
    public final D7 A0B;
    public final boolean[] A0D = new boolean[3];
    public final D4 A0A = new D4(32, 128);
    public final D4 A08 = new D4(33, 128);
    public final D4 A06 = new D4(34, 128);
    public final D4 A07 = new D4(39, 128);
    public final D4 A09 = new D4(40, 128);
    public final C1217Hz A0C = new C1217Hz();

    public static String A01(int i9, int i10, int i11) {
        byte[] copyOfRange = Arrays.copyOfRange(A0E, i9, i9 + i10);
        for (int i12 = 0; i12 < copyOfRange.length; i12++) {
            copyOfRange[i12] = (byte) ((copyOfRange[i12] ^ i11) ^ 41);
        }
        return new String(copyOfRange);
    }

    public static void A02() {
        String[] strArr = A0F;
        if (strArr[4].length() == strArr[2].length()) {
            throw new RuntimeException();
        }
        A0F[3] = "YNSDJ1";
        A0E = new byte[]{121, 3, 7, 4, 99, 84, 80, 85, 84, 67, 126, 69, 78, 83, 91, 78, 72, 95, 78, 79, Ascii.VT, 74, 88, 91, 78, 72, 95, 116, 89, 74, 95, 66, 68, 116, 66, 79, 72, Ascii.VT, 93, 74, 71, 94, 78, 17, Ascii.VT, 78, 81, 92, 93, 87, Ascii.ETB, 80, 93, 78, 91};
    }

    static {
        A02();
    }

    public X0(D7 d72) {
        this.A0B = d72;
    }

    public static Format A00(String str, D4 d42, D4 d43, D4 d44) {
        byte[] bArr = new byte[d42.A00 + d43.A00 + d44.A00];
        System.arraycopy(d42.A01, 0, bArr, 0, d42.A00);
        System.arraycopy(d43.A01, 0, bArr, d42.A00, d43.A00);
        System.arraycopy(d44.A01, 0, bArr, d42.A00 + d43.A00, d44.A00);
        I0 i02 = new I0(d43.A01, 0, d43.A00);
        i02.A07(44);
        int maxSubLayersMinus1 = i02.A05(3);
        i02.A06();
        i02.A07(88);
        i02.A07(8);
        int i9 = 0;
        for (int i10 = 0; i10 < maxSubLayersMinus1; i10++) {
            if (i02.A0A()) {
                i9 += 89;
            }
            if (i02.A0A()) {
                i9 += 8;
            }
        }
        i02.A07(i9);
        if (maxSubLayersMinus1 > 0) {
            i02.A07((8 - maxSubLayersMinus1) * 2);
        }
        i02.A04();
        int A04 = i02.A04();
        if (A04 == 3) {
            i02.A06();
        }
        int picHeightInLumaSamples = i02.A04();
        int confWinLeftOffset = i02.A04();
        if (A0F[1].length() == 12) {
            throw new RuntimeException();
        }
        String[] strArr = A0F;
        strArr[4] = "a8PzOvYW3xW";
        strArr[2] = "i4yWj6LgSPQERWLdQfta";
        if (i02.A0A()) {
            int chromaFormatIdc = i02.A04();
            int A042 = i02.A04();
            int toSkip = i02.A04();
            int A043 = i02.A04();
            picHeightInLumaSamples -= (chromaFormatIdc + A042) * ((A04 == 1 || A04 == 2) ? 2 : 1);
            confWinLeftOffset -= (toSkip + A043) * (A04 == 1 ? 2 : 1);
        }
        i02.A04();
        i02.A04();
        int A044 = i02.A04();
        for (int i11 = i02.A0A() ? 0 : maxSubLayersMinus1; i11 <= maxSubLayersMinus1; i11++) {
            i02.A04();
            i02.A04();
            i02.A04();
        }
        i02.A04();
        i02.A04();
        i02.A04();
        i02.A04();
        i02.A04();
        i02.A04();
        if (i02.A0A() && i02.A0A()) {
            A05(i02);
        }
        i02.A07(2);
        if (i02.A0A()) {
            i02.A07(8);
            i02.A04();
            i02.A04();
            i02.A06();
        }
        A06(i02);
        if (i02.A0A()) {
            for (int i12 = 0; i12 < i02.A04(); i12++) {
                i02.A07(A044 + 4 + 1);
            }
        }
        i02.A07(2);
        float f9 = 1.0f;
        if (i02.A0A() && i02.A0A()) {
            int picWidthInLumaSamples = i02.A05(8);
            if (picWidthInLumaSamples == 255) {
                int A05 = i02.A05(16);
                int A052 = i02.A05(16);
                if (A05 != 0 && A052 != 0) {
                    f9 = A05 / A052;
                }
            } else {
                float[] fArr = AbstractC1213Hv.A04;
                String[] strArr2 = A0F;
                if (strArr2[4].length() == strArr2[2].length()) {
                    throw new RuntimeException();
                }
                String[] strArr3 = A0F;
                strArr3[4] = "Mq8kmMTSiz0";
                strArr3[2] = "TBAE07oWZ5Khy7farxDr";
                if (picWidthInLumaSamples < fArr.length) {
                    f9 = AbstractC1213Hv.A04[picWidthInLumaSamples];
                } else {
                    Log.w(A01(0, 10, 24), A01(10, 35, 2) + picWidthInLumaSamples);
                }
            }
        }
        String A01 = A01(45, 10, 17);
        List singletonList = Collections.singletonList(bArr);
        if (A0F[3].length() != 6) {
            String[] strArr4 = A0F;
            strArr4[7] = "REKIidOCsv4ptzsgivtfnGOww6JG";
            strArr4[5] = "T94vPaWVm7jdMSWQElc8luWGTOlSo";
            return Format.A03(str, A01, null, -1, -1, picHeightInLumaSamples, confWinLeftOffset, -1.0f, singletonList, -1, f9, null);
        }
        String[] strArr5 = A0F;
        strArr5[7] = "W2GdBlThEFkhmpYhDIzNH9YY1BaI";
        strArr5[5] = "0yDCfDeoWht2juYgXJKTHBbNY9ana";
        return Format.A03(str, A01, null, -1, -1, picHeightInLumaSamples, confWinLeftOffset, -1.0f, singletonList, -1, f9, null);
    }

    private void A03(long j7, int i9, int i10, long j9) {
        if (this.A05) {
            this.A03.A02(j7, i9);
        } else {
            this.A0A.A04(i10);
            this.A08.A04(i10);
            this.A06.A04(i10);
            if (this.A0A.A03() && this.A08.A03() && this.A06.A03()) {
                this.A02.A5n(A00(this.A04, this.A0A, this.A08, this.A06));
                this.A05 = true;
            }
        }
        if (this.A07.A04(i10)) {
            D4 d42 = this.A07;
            if (A0F[1].length() == 12) {
                throw new RuntimeException();
            }
            A0F[3] = "Yba7mj";
            this.A0C.A0b(this.A07.A01, AbstractC1213Hv.A02(d42.A01, this.A07.A00));
            this.A0C.A0Z(5);
            this.A0B.A02(j9, this.A0C);
        }
        if (this.A09.A04(i10)) {
            this.A0C.A0b(this.A09.A01, AbstractC1213Hv.A02(this.A09.A01, this.A09.A00));
            this.A0C.A0Z(5);
            this.A0B.A02(j9, this.A0C);
        }
    }

    private void A04(long j7, int i9, int i10, long j9) {
        if (this.A05) {
            this.A03.A03(j7, i9, i10, j9);
        } else {
            this.A0A.A01(i10);
            this.A08.A01(i10);
            this.A06.A01(i10);
        }
        this.A07.A01(i10);
        this.A09.A01(i10);
    }

    public static void A05(I0 i02) {
        for (int i9 = 0; i9 < 4; i9++) {
            int i10 = 0;
            while (i10 < 6) {
                int matrixId = 1;
                if (!i02.A0A()) {
                    i02.A04();
                } else {
                    int sizeId = i9 << 1;
                    int min = Math.min(64, 1 << (sizeId + 4));
                    if (i9 > 1) {
                        i02.A03();
                    }
                    for (int sizeId2 = 0; sizeId2 < min; sizeId2++) {
                        i02.A03();
                    }
                }
                if (i9 == 3) {
                    matrixId = 3;
                }
                i10 += matrixId;
            }
        }
    }

    public static void A06(I0 i02) {
        int A04 = i02.A04();
        boolean z8 = false;
        int numNegativePics = 0;
        for (int stRpsIdx = 0; stRpsIdx < A04; stRpsIdx++) {
            if (stRpsIdx != 0) {
                z8 = i02.A0A();
            }
            if (z8) {
                i02.A06();
                i02.A04();
                for (int i9 = 0; i9 <= numNegativePics; i9++) {
                    if (i02.A0A()) {
                        i02.A06();
                    }
                }
            } else {
                int previousNumDeltaPocs = i02.A04();
                int A042 = i02.A04();
                numNegativePics = previousNumDeltaPocs + A042;
                for (int numShortTermRefPicSets = 0; numShortTermRefPicSets < previousNumDeltaPocs; numShortTermRefPicSets++) {
                    i02.A04();
                    i02.A06();
                }
                for (int numShortTermRefPicSets2 = 0; numShortTermRefPicSets2 < A042; numShortTermRefPicSets2++) {
                    i02.A04();
                    i02.A06();
                }
            }
        }
    }

    private void A07(byte[] bArr, int i9, int i10) {
        if (this.A05) {
            this.A03.A04(bArr, i9, i10);
        } else {
            this.A0A.A02(bArr, i9, i10);
            this.A08.A02(bArr, i9, i10);
            this.A06.A02(bArr, i9, i10);
        }
        this.A07.A02(bArr, i9, i10);
        this.A09.A02(bArr, i9, i10);
    }

    @Override // com.meta.analytics.dsp.uinode.InterfaceC1100Cy
    public final void A4R(C1217Hz c1217Hz) {
        while (true) {
            int A04 = c1217Hz.A04();
            if (A0F[1].length() == 12) {
                throw new RuntimeException();
            }
            String[] strArr = A0F;
            strArr[0] = "AvzYB124iDQiX3hdxqfkLksaQ25q0Ga1";
            strArr[6] = "Nv2bR4D3VQ28suo5ivBlL2099SuCEJEb";
            if (A04 > 0) {
                int limit = c1217Hz.A06();
                int A07 = c1217Hz.A07();
                byte[] bArr = c1217Hz.A00;
                long j7 = this.A01;
                int offset = c1217Hz.A04();
                this.A01 = j7 + offset;
                C4 c42 = this.A02;
                int offset2 = c1217Hz.A04();
                c42.AFR(c1217Hz, offset2);
                while (limit < A07) {
                    int A042 = AbstractC1213Hv.A04(bArr, limit, A07, this.A0D);
                    if (A042 == A07) {
                        A07(bArr, limit, A07);
                        return;
                    }
                    int bytesWrittenPastPosition = AbstractC1213Hv.A00(bArr, A042);
                    int i9 = A042 - limit;
                    if (i9 > 0) {
                        A07(bArr, limit, A042);
                    }
                    int i10 = A07 - A042;
                    long j9 = this.A01 - i10;
                    int offset3 = i9 < 0 ? -i9 : 0;
                    A03(j9, i10, offset3, this.A00);
                    long absolutePosition = this.A00;
                    A04(j9, i10, bytesWrittenPastPosition, absolutePosition);
                    limit = A042 + 3;
                }
            } else {
                return;
            }
        }
    }

    @Override // com.meta.analytics.dsp.uinode.InterfaceC1100Cy
    public final void A4p(InterfaceC1076Bu interfaceC1076Bu, DC dc) {
        dc.A05();
        this.A04 = dc.A04();
        C4 AGi = interfaceC1076Bu.AGi(dc.A03(), 2);
        this.A02 = AGi;
        this.A03 = new D3(AGi);
        this.A0B.A03(interfaceC1076Bu, dc);
    }

    @Override // com.meta.analytics.dsp.uinode.InterfaceC1100Cy
    public final void ADs() {
    }

    @Override // com.meta.analytics.dsp.uinode.InterfaceC1100Cy
    public final void ADt(long j7, boolean z8) {
        this.A00 = j7;
    }

    @Override // com.meta.analytics.dsp.uinode.InterfaceC1100Cy
    public final void AFg() {
        AbstractC1213Hv.A0B(this.A0D);
        this.A0A.A00();
        this.A08.A00();
        this.A06.A00();
        this.A07.A00();
        this.A09.A00();
        this.A03.A01();
        this.A01 = 0L;
    }
}
