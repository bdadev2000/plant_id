package com.meta.analytics.dsp.uinode;

/* loaded from: assets/audience_network.dex */
public final class I0 {
    public static String[] A04 = {"BtHKr", "GAfuq", "1TcIbYyYGpE6Ekv4glvvfZz9bC", "q9fmd3d0ImW5Daxca", "ooOXUvpeMSptobOLnq6qd7gNdq", "VwsWXVZM3brBJlmy9", "pSziVAe", "zUHU2CyO73eexnRwL2LOOWJW4Jr0bcWv"};
    public int A00;
    public int A01;
    public int A02;
    public byte[] A03;

    public I0(byte[] bArr, int i9, int i10) {
        A08(bArr, i9, i10);
    }

    private int A00() {
        int i9 = 0;
        while (!A0A()) {
            i9++;
        }
        int i10 = (1 << i9) - 1;
        int leadingZeros = i9 > 0 ? A05(i9) : 0;
        return i10 + leadingZeros;
    }

    private void A01() {
        boolean z8;
        int i9 = this.A02;
        if (i9 >= 0) {
            int i10 = this.A01;
            if (A04[1].length() == 29) {
                throw new RuntimeException();
            }
            String[] strArr = A04;
            strArr[6] = "f06qvbC";
            strArr[0] = "HuIz9";
            if (i9 < i10 || (i9 == i10 && this.A00 == 0)) {
                z8 = true;
                AbstractC1192Ha.A04(z8);
            }
        }
        z8 = false;
        AbstractC1192Ha.A04(z8);
    }

    private boolean A02(int i9) {
        if (2 <= i9 && i9 < this.A01) {
            byte[] bArr = this.A03;
            byte b = bArr[i9];
            String[] strArr = A04;
            if (strArr[6].length() != strArr[0].length()) {
                String[] strArr2 = A04;
                strArr2[5] = "Jod3dkhqFQ8qWj2s5";
                strArr2[3] = "0zxF2BZpdiUX5xaGz";
                if (b == 3 && bArr[i9 - 2] == 0) {
                    byte b8 = bArr[i9 - 1];
                    String[] strArr3 = A04;
                    if (strArr3[6].length() != strArr3[0].length()) {
                        String[] strArr4 = A04;
                        strArr4[6] = "lQKXp2Z";
                        strArr4[0] = "Kosug";
                        if (b8 == 0) {
                            return true;
                        }
                    }
                }
            }
            throw new RuntimeException();
        }
        return false;
    }

    public final int A03() {
        int A00 = A00();
        int codeNum = A00 % 2;
        int i9 = codeNum == 0 ? -1 : 1;
        int codeNum2 = A00 + 1;
        return i9 * (codeNum2 / 2);
    }

    public final int A04() {
        return A00();
    }

    public final int A05(int i9) {
        int i10;
        int i11 = 0;
        int returnValue = this.A00;
        this.A00 = returnValue + i9;
        while (true) {
            i10 = this.A00;
            if (i10 <= 8) {
                break;
            }
            int i12 = i10 - 8;
            this.A00 = i12;
            byte[] bArr = this.A03;
            int i13 = this.A02;
            int returnValue2 = bArr[i13];
            i11 |= (returnValue2 & 255) << i12;
            int returnValue3 = i13 + 1;
            if (!A02(returnValue3)) {
                r7 = 1;
            }
            this.A02 = i13 + r7;
        }
        byte[] bArr2 = this.A03;
        int i14 = this.A02;
        int returnValue4 = bArr2[i14];
        int i15 = returnValue4 & 255;
        String[] strArr = A04;
        String str = strArr[5];
        String str2 = strArr[3];
        int length = str.length();
        int returnValue5 = str2.length();
        if (length != returnValue5) {
            throw new RuntimeException();
        }
        A04[7] = "CI85ryy9UuGcWp4q0bXmx78DrLL4QG6i";
        int returnValue6 = 8 - i10;
        int i16 = i11 | (i15 >> returnValue6);
        int returnValue7 = (-1) >>> (32 - i9);
        int i17 = i16 & returnValue7;
        if (i10 == 8) {
            this.A00 = 0;
            int returnValue8 = i14 + 1;
            this.A02 = i14 + (A02(returnValue8) ? 2 : 1);
        }
        A01();
        return i17;
    }

    public final void A06() {
        int i9 = this.A00 + 1;
        this.A00 = i9;
        if (i9 == 8) {
            this.A00 = 0;
            int i10 = this.A02;
            this.A02 = i10 + (A02(i10 + 1) ? 2 : 1);
        }
        A01();
    }

    public final void A07(int i9) {
        int i10 = this.A02;
        int oldByteOffset = i9 / 8;
        int numBytes = this.A02 + oldByteOffset;
        this.A02 = numBytes;
        int i11 = this.A00 + (i9 - (oldByteOffset * 8));
        this.A00 = i11;
        if (i11 > 7) {
            int i12 = numBytes + 1;
            String[] strArr = A04;
            if (strArr[2].length() != strArr[4].length()) {
                throw new RuntimeException();
            }
            String[] strArr2 = A04;
            strArr2[2] = "WKnUhbHg5PXpVOXLjQbslPOixC";
            strArr2[4] = "5elOCNAU3UTje1WUqtoqjArHkZ";
            this.A02 = i12;
            this.A00 = i11 - 8;
        }
        int numBytes2 = i10 + 1;
        while (numBytes2 <= this.A02) {
            if (A02(numBytes2)) {
                this.A02++;
                numBytes2 += 2;
            }
            numBytes2++;
        }
        A01();
    }

    public final void A08(byte[] bArr, int i9, int i10) {
        this.A03 = bArr;
        this.A02 = i9;
        this.A01 = i10;
        this.A00 = 0;
        A01();
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0009 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean A09() {
        /*
            r7 = this;
            int r6 = r7.A02
            int r5 = r7.A00
            r4 = 0
        L5:
            int r1 = r7.A02
            int r0 = r7.A01
            if (r1 >= r0) goto L14
            boolean r0 = r7.A0A()
            if (r0 != 0) goto L14
            int r4 = r4 + 1
            goto L5
        L14:
            int r3 = r7.A02
            int r0 = r7.A01
            r2 = 0
            r1 = 1
            if (r3 != r0) goto L2e
            r0 = 1
        L1d:
            r7.A02 = r6
            r7.A00 = r5
            if (r0 != 0) goto L2d
            int r0 = r4 * 2
            int r0 = r0 + r1
            boolean r0 = r7.A0B(r0)
            if (r0 == 0) goto L2d
            r2 = 1
        L2d:
            return r2
        L2e:
            r0 = 0
            goto L1d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meta.analytics.dsp.uinode.I0.A09():boolean");
    }

    public final boolean A0A() {
        boolean returnValue = (this.A03[this.A02] & (128 >> this.A00)) != 0;
        A06();
        return returnValue;
    }

    public final boolean A0B(int i9) {
        int numBytes = this.A02;
        int oldByteOffset = i9 / 8;
        int i10 = this.A02 + oldByteOffset;
        int newBitOffset = (this.A00 + i9) - (oldByteOffset * 8);
        if (newBitOffset > 7) {
            i10++;
            newBitOffset -= 8;
        }
        int newByteOffset = numBytes + 1;
        while (newByteOffset <= i10 && i10 < this.A01) {
            if (A02(newByteOffset)) {
                i10++;
                newByteOffset += 2;
            }
            newByteOffset++;
        }
        int oldByteOffset2 = this.A01;
        if (i10 >= oldByteOffset2) {
            return i10 == oldByteOffset2 && newBitOffset == 0;
        }
        return true;
    }
}
