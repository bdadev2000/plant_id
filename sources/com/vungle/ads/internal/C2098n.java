package com.vungle.ads.internal;

import android.content.Context;
import com.vungle.ads.f1;

/* renamed from: com.vungle.ads.internal.n, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C2098n extends G7.k implements F7.a {
    final /* synthetic */ Context $context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2098n(Context context) {
        super(0);
        this.$context = context;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, com.vungle.ads.internal.util.x] */
    @Override // F7.a
    public final com.vungle.ads.internal.util.x invoke() {
        return f1.Companion.getInstance(this.$context).getService(com.vungle.ads.internal.util.x.class);
    }
}
