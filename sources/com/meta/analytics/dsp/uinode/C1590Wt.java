package com.meta.analytics.dsp.uinode;

/* renamed from: com.facebook.ads.redexgen.X.Wt, reason: case insensitive filesystem */
/* loaded from: assets/audience_network.dex */
public final class C1590Wt implements DD {
    public static String[] A06 = {"f3ASEvE0MACuH7PXtcW6SQzpVLuDWBs6", "UAkl7YLcJ73MhJIfOQ7gtBxP58V39hXf", "sKTt9gGYn0qCOxzPnchEZBW", "TIj2tKxLfGdHgu1Q1GnzrxOOh9OPKkUY", "NlS114awdnJt6ePSWIiXVyR", "Ttbg6FQm3oQML", "hnRFaN1D1NI4WPLkXSYbmAvKq8H9o2zk", "6DjXFLFEMVHWeegs6rtlgiLmiwpKRWRw"};
    public int A00;
    public int A01;
    public boolean A02;
    public boolean A03;
    public final D6 A04;
    public final C1217Hz A05 = new C1217Hz(32);

    public C1590Wt(D6 d62) {
        this.A04 = d62;
    }

    @Override // com.meta.analytics.dsp.uinode.DD
    public final void A4S(C1217Hz c1217Hz, boolean z8) {
        int i9 = -1;
        if (z8) {
            int A0E = c1217Hz.A0E();
            int A062 = c1217Hz.A06();
            String[] strArr = A06;
            String str = strArr[4];
            String str2 = strArr[2];
            int payloadStartOffset = str.length();
            if (payloadStartOffset != str2.length()) {
                throw new RuntimeException();
            }
            A06[5] = "fX8Qw0M3arh1i";
            i9 = A062 + A0E;
        }
        if (this.A03) {
            if (!z8) {
                return;
            }
            this.A03 = false;
            c1217Hz.A0Y(i9);
            this.A00 = 0;
        }
        while (c1217Hz.A04() > 0) {
            int payloadStartPosition = this.A00;
            if (payloadStartPosition < 3) {
                if (payloadStartPosition == 0) {
                    int tableId = c1217Hz.A0E();
                    c1217Hz.A0Y(c1217Hz.A06() - 1);
                    if (tableId == 255) {
                        this.A03 = true;
                        return;
                    }
                }
                int tableId2 = c1217Hz.A04();
                int min = Math.min(tableId2, 3 - this.A00);
                c1217Hz.A0c(this.A05.A00, this.A00, min);
                int payloadStartPosition2 = this.A00 + min;
                this.A00 = payloadStartPosition2;
                if (payloadStartPosition2 == 3) {
                    this.A05.A0W(3);
                    this.A05.A0Z(1);
                    int A0E2 = this.A05.A0E();
                    int headerBytesToRead = this.A05.A0E();
                    this.A02 = (A0E2 & 128) != 0;
                    this.A01 = (((A0E2 & 15) << 8) | headerBytesToRead) + 3;
                    int headerBytesToRead2 = this.A05.A05();
                    if (headerBytesToRead2 < this.A01) {
                        byte[] bArr = this.A05.A00;
                        C1217Hz c1217Hz2 = this.A05;
                        int headerBytesToRead3 = this.A01;
                        c1217Hz2.A0W(Math.min(4098, Math.max(headerBytesToRead3, bArr.length * 2)));
                        System.arraycopy(bArr, 0, this.A05.A00, 0, 3);
                    }
                }
            } else {
                int A04 = c1217Hz.A04();
                int headerBytesToRead4 = this.A01;
                int min2 = Math.min(A04, headerBytesToRead4 - this.A00);
                c1217Hz.A0c(this.A05.A00, this.A00, min2);
                int payloadStartPosition3 = this.A00 + min2;
                this.A00 = payloadStartPosition3;
                int bodyBytesToRead = this.A01;
                if (payloadStartPosition3 != bodyBytesToRead) {
                    continue;
                } else {
                    if (this.A02) {
                        if (IF.A09(this.A05.A00, 0, this.A01, -1) != 0) {
                            this.A03 = true;
                            return;
                        }
                        this.A05.A0W(this.A01 - 4);
                    } else {
                        this.A05.A0W(bodyBytesToRead);
                    }
                    this.A04.A4R(this.A05);
                    this.A00 = 0;
                }
            }
        }
    }

    @Override // com.meta.analytics.dsp.uinode.DD
    public final void A8q(IB ib, InterfaceC1076Bu interfaceC1076Bu, DC dc) {
        this.A04.A8q(ib, interfaceC1076Bu, dc);
        this.A03 = true;
    }

    @Override // com.meta.analytics.dsp.uinode.DD
    public final void AFg() {
        this.A03 = true;
    }
}
