package com.applovin.exoplayer2.common.base;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes.dex */
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    /* loaded from: classes.dex */
    public class a implements Iterable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Iterable f6138a;

        /* renamed from: com.applovin.exoplayer2.common.base.Optional$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0006a extends b {

            /* renamed from: c, reason: collision with root package name */
            private final Iterator f6139c;

            public C0006a() {
                this.f6139c = (Iterator) Preconditions.checkNotNull(a.this.f6138a.iterator());
            }

            @Override // com.applovin.exoplayer2.common.base.b
            public Object a() {
                while (this.f6139c.hasNext()) {
                    Optional optional = (Optional) this.f6139c.next();
                    if (optional.isPresent()) {
                        return optional.get();
                    }
                }
                return b();
            }
        }

        public a(Iterable iterable) {
            this.f6138a = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator iterator() {
            return new C0006a();
        }
    }

    public static <T> Optional<T> absent() {
        return com.applovin.exoplayer2.common.base.a.a();
    }

    public static <T> Optional<T> fromNullable(@NullableDecl T t9) {
        if (t9 == null) {
            return absent();
        }
        return new d(t9);
    }

    public static <T> Optional<T> of(T t9) {
        return new d(Preconditions.checkNotNull(t9));
    }

    public static <T> Iterable<T> presentInstances(Iterable<? extends Optional<? extends T>> iterable) {
        Preconditions.checkNotNull(iterable);
        return new a(iterable);
    }

    public abstract Set<T> asSet();

    public abstract boolean equals(@NullableDecl Object obj);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Optional<T> or(Optional<? extends T> optional);

    public abstract T or(Supplier<? extends T> supplier);

    public abstract T or(T t9);

    @NullableDecl
    public abstract T orNull();

    public abstract String toString();

    public abstract <V> Optional<V> transform(Function<? super T, V> function);
}
