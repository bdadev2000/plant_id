package com.bytedance.adsdk.lottie.zp.lMd;

import java.util.List;

/* loaded from: classes.dex */
public class jU extends QR<Float> {
    public jU(List<com.bytedance.adsdk.lottie.QR.zp<Float>> list) {
        super(list);
    }

    public float KS(com.bytedance.adsdk.lottie.QR.zp<Float> zpVar, float f9) {
        if (zpVar.zp != null && zpVar.lMd != null) {
            if (this.KS == null) {
                return com.bytedance.adsdk.lottie.HWF.COT.zp(zpVar.HWF(), zpVar.QR(), f9);
            }
            zpVar.QR.getClass();
            jU();
            ku();
            throw null;
        }
        throw new IllegalStateException("Missing values for keyframe.");
    }

    public float YW() {
        return KS(KS(), COT());
    }

    @Override // com.bytedance.adsdk.lottie.zp.lMd.zp
    /* renamed from: lMd, reason: merged with bridge method [inline-methods] */
    public Float zp(com.bytedance.adsdk.lottie.QR.zp<Float> zpVar, float f9) {
        return Float.valueOf(KS(zpVar, f9));
    }
}
