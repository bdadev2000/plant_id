package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.lang.ref.PhantomReference;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
public abstract class FinalizablePhantomReference<T> extends PhantomReference<T> implements FinalizableReference {
    public FinalizablePhantomReference(T t2, FinalizableReferenceQueue finalizableReferenceQueue) {
        super(t2, finalizableReferenceQueue.queue);
        finalizableReferenceQueue.cleanUp();
    }
}
