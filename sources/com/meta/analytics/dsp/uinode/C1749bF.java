package com.meta.analytics.dsp.uinode;

import com.facebook.ads.AdError;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.redexgen.X.bF, reason: case insensitive filesystem */
/* loaded from: assets/audience_network.dex */
public class C1749bF implements AnonymousClass61 {
    public final /* synthetic */ FY A00;

    public C1749bF(FY fy) {
        this.A00 = fy;
    }

    @Override // com.meta.analytics.dsp.uinode.AnonymousClass61
    public final void AAl() {
        AtomicBoolean atomicBoolean;
        AnonymousClass18 anonymousClass18;
        atomicBoolean = this.A00.A0C;
        atomicBoolean.set(true);
        anonymousClass18 = this.A00.A01;
        anonymousClass18.ACy(this.A00);
    }

    @Override // com.meta.analytics.dsp.uinode.AnonymousClass61
    public final void AAm() {
        AnonymousClass18 anonymousClass18;
        anonymousClass18 = this.A00.A01;
        anonymousClass18.AD1(this.A00, AdError.CACHE_ERROR);
    }
}
