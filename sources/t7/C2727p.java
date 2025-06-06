package t7;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: t7.p, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C2727p implements Collection, H7.a {
    public final byte[] b;

    @Override // java.util.Collection
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof C2726o)) {
            return false;
        }
        byte b = ((C2726o) obj).b;
        byte[] bArr = this.b;
        int length = bArr.length;
        int i9 = 0;
        while (true) {
            if (i9 < length) {
                if (b == bArr[i9]) {
                    break;
                }
                i9++;
            } else {
                i9 = -1;
                break;
            }
        }
        if (i9 < 0) {
            return false;
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        boolean z8;
        G7.j.e(collection, "elements");
        Collection collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        for (Object obj : collection2) {
            if (obj instanceof C2726o) {
                byte b = ((C2726o) obj).b;
                byte[] bArr = this.b;
                int length = bArr.length;
                int i9 = 0;
                while (true) {
                    if (i9 < length) {
                        if (b == bArr[i9]) {
                            break;
                        }
                        i9++;
                    } else {
                        i9 = -1;
                        break;
                    }
                }
                if (i9 >= 0) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                if (z8) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (!(obj instanceof C2727p)) {
            return false;
        }
        if (!G7.j.a(this.b, ((C2727p) obj).b)) {
            return false;
        }
        return true;
    }

    @Override // java.util.Collection
    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        if (this.b.length == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new G7.a(this.b, 2);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final int size() {
        return this.b.length;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return G7.i.a(this);
    }

    public final String toString() {
        return "UByteArray(storage=" + Arrays.toString(this.b) + ')';
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        G7.j.e(objArr, "array");
        return G7.i.b(this, objArr);
    }
}
