package com.instagram.common.viewpoint.core;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.common.primitives.Ints;

/* renamed from: com.facebook.ads.redexgen.X.2e, reason: invalid class name and case insensitive filesystem */
/* loaded from: assets/audience_network.dex */
public abstract class AbstractC02192e extends FrameLayout {
    public static final InterfaceC02212g A0A;
    public int A00;
    public int A01;
    public boolean A02;
    public boolean A03;
    public final Rect A04;
    public final Rect A05;
    public final InterfaceC02202f A06;
    public static String[] A07 = {"ShNBvS5", "b2z1l7evnHkZXpFh4jUAU", "JfQekQfzdw1mJ2RkSzlEvaL1jVLq96XT", "0M9zDLqBQAVDpkbZHtwaURqwF1mV2f1S", "jQ6MUrNiHIuV2Q80zD0eWZ3NfiJ07L6A", "0up6ixfDqbrZOotDwCZ4gMtZBiUGrTem", "xF0da7GYt9A5Qra0MND7i27I5BG4MHvn", "fGPiNZIH9FIGNZUMCtCVq24hO8AJIHug"};
    public static final int[] A0B = {R.attr.colorBackground};
    public static final float A08 = Resources.getSystem().getDisplayMetrics().density;
    public static final int A09 = (int) (A08 * 1.0f);

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 13 out of bounds for length 13
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:125)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:118)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:626)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:39)
     */
    public AbstractC02192e(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.A04 = new Rect();
        this.A05 = new Rect();
        this.A06 = new C1128bG(this);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(A0B);
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        Color.colorToHSV(color, new float[3]);
        ColorStateList valueOf = ColorStateList.valueOf(-328966);
        this.A02 = false;
        this.A03 = true;
        this.A04.left = 0;
        this.A04.top = 0;
        this.A04.right = 0;
        this.A04.bottom = 0;
        float f2 = 10.0f > 50.0f ? 10.0f : 50.0f;
        this.A01 = 0;
        this.A00 = 0;
        A0A.A9H(this.A06, context, valueOf, 10.0f, 10.0f, f2);
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            A0A = new C1126bE();
        } else if (Build.VERSION.SDK_INT >= 17) {
            A0A = new C0524Fb();
        } else {
            A0A = new C1124bC();
        }
        A0A.A9G();
    }

    public AbstractC02192e(Context context) {
        this(context, null);
    }

    public AbstractC02192e(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorStateList getCardBackgroundColor() {
        return A0A.A6U(this.A06);
    }

    public float getCardElevation() {
        return A0A.A7H(this.A06);
    }

    public int getContentPaddingBottom() {
        return this.A04.bottom;
    }

    public int getContentPaddingLeft() {
        return this.A04.left;
    }

    public int getContentPaddingRight() {
        return this.A04.right;
    }

    public int getContentPaddingTop() {
        return this.A04.top;
    }

    public float getMaxCardElevation() {
        return A0A.A7k(this.A06);
    }

    public boolean getPreventCornerOverlap() {
        return this.A03;
    }

    public float getRadius() {
        return A0A.A8A(this.A06);
    }

    public boolean getUseCompatPadding() {
        return this.A02;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        if (!(A0A instanceof C1126bE)) {
            int mode = View.MeasureSpec.getMode(i2);
            switch (mode) {
                case Integer.MIN_VALUE:
                case Ints.MAX_POWER_OF_TWO /* 1073741824 */:
                    int minWidth = (int) Math.ceil(A0A.A7q(this.A06));
                    i2 = View.MeasureSpec.makeMeasureSpec(Math.max(minWidth, View.MeasureSpec.getSize(i2)), mode);
                    break;
            }
            int mode2 = View.MeasureSpec.getMode(i3);
            if (A07[1].length() == 3) {
                throw new RuntimeException();
            }
            String[] strArr = A07;
            strArr[4] = "ezVHALe6egolV0dGNwTkgEwTmiIckZEU";
            strArr[5] = "9kVtZpYuS1B6eNaF5MtLQpOmcikxmxeD";
            switch (mode2) {
                case Integer.MIN_VALUE:
                case Ints.MAX_POWER_OF_TWO /* 1073741824 */:
                    i3 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(A0A.A7p(this.A06)), View.MeasureSpec.getSize(i3)), mode2);
                    break;
            }
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, i3);
    }

    public void setCardBackgroundColor(int i2) {
        A0A.AGJ(this.A06, ColorStateList.valueOf(i2));
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        A0A.AGJ(this.A06, colorStateList);
    }

    public void setCardElevation(float f2) {
        A0A.AGP(this.A06, f2);
    }

    public void setMaxCardElevation(float f2) {
        A0A.AGW(this.A06, f2);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i2) {
        this.A00 = i2;
        super.setMinimumHeight(i2);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i2) {
        this.A01 = i2;
        super.setMinimumWidth(i2);
    }

    @Override // android.view.View
    public final void setPadding(int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View
    public final void setPaddingRelative(int i2, int i3, int i4, int i5) {
    }

    public void setPreventCornerOverlap(boolean z2) {
        if (z2 != this.A03) {
            this.A03 = z2;
            A0A.ADD(this.A06);
        }
    }

    public void setRadius(float f2) {
        A0A.AGc(this.A06, f2);
    }

    public void setUseCompatPadding(boolean z2) {
        if (this.A02 != z2) {
            this.A02 = z2;
            A0A.ABY(this.A06);
        }
    }
}
