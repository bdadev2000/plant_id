package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingDeque;
import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
/* loaded from: classes4.dex */
public abstract class ForwardingBlockingDeque<E> extends ForwardingDeque<E> implements BlockingDeque<E> {
    @Override // com.google.common.collect.ForwardingDeque, com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract BlockingDeque<E> delegate();

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return delegate().drainTo(collection);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public boolean offer(E e, long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().offer(e, j2, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque
    public boolean offerFirst(E e, long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().offerFirst(e, j2, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque
    public boolean offerLast(E e, long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().offerLast(e, j2, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public E poll(long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().poll(j2, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque
    public E pollFirst(long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().pollFirst(j2, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque
    public E pollLast(long j2, TimeUnit timeUnit) throws InterruptedException {
        return delegate().pollLast(j2, timeUnit);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public void put(E e) throws InterruptedException {
        delegate().put(e);
    }

    @Override // java.util.concurrent.BlockingDeque
    public void putFirst(E e) throws InterruptedException {
        delegate().putFirst(e);
    }

    @Override // java.util.concurrent.BlockingDeque
    public void putLast(E e) throws InterruptedException {
        delegate().putLast(e);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return delegate().remainingCapacity();
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        return delegate().take();
    }

    @Override // java.util.concurrent.BlockingDeque
    public E takeFirst() throws InterruptedException {
        return delegate().takeFirst();
    }

    @Override // java.util.concurrent.BlockingDeque
    public E takeLast() throws InterruptedException {
        return delegate().takeLast();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i2) {
        return delegate().drainTo(collection, i2);
    }
}
