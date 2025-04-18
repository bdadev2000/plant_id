package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.lang.ref.WeakReference;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
public abstract class FinalizableWeakReference<T> extends WeakReference<T> implements FinalizableReference {
    public FinalizableWeakReference(T t2, FinalizableReferenceQueue finalizableReferenceQueue) {
        super(t2, finalizableReferenceQueue.queue);
        finalizableReferenceQueue.cleanUp();
    }
}
