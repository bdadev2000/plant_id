package com.instagram.common.viewpoint.core;

import android.content.Context;

/* renamed from: com.facebook.ads.redexgen.X.aJ, reason: case insensitive filesystem */
/* loaded from: assets/audience_network.dex */
public class C1072aJ extends C02483h {
    public int A00;

    public C1072aJ(Context context) {
        super(context);
        this.A00 = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A00(int i2) {
        this.A00 = i2;
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0007 */
    @Override // com.instagram.common.viewpoint.core.C02483h, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r5, int r6) {
        /*
            r4 = this;
            int r3 = r4.A00
            r2 = 0
        L3:
            int r0 = r4.getChildCount()
            if (r2 >= r0) goto L1f
            android.view.View r1 = r4.getChildAt(r2)
            r0 = 0
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0)
            r1.measure(r5, r0)
            int r0 = r1.getMeasuredHeight()
            if (r0 <= r3) goto L1c
            r3 = r0
        L1c:
            int r2 = r2 + 1
            goto L3
        L1f:
            r0 = 1073741824(0x40000000, float:2.0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r0)
            super.onMeasure(r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.instagram.common.viewpoint.core.C1072aJ.onMeasure(int, int):void");
    }
}
