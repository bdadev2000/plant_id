package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.primitives.ImmutableLongArray;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;
import okhttp3.HttpUrl;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
/* loaded from: classes3.dex */
public class AtomicDoubleArray implements Serializable {
    private static final long serialVersionUID = 0;
    private transient AtomicLongArray longs;

    public AtomicDoubleArray(int i2) {
        this.longs = new AtomicLongArray(i2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
        for (int i2 = 0; i2 < readInt; i2++) {
            builder.add(Double.doubleToRawLongBits(objectInputStream.readDouble()));
        }
        this.longs = new AtomicLongArray(builder.build().toArray());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int length = length();
        objectOutputStream.writeInt(length);
        for (int i2 = 0; i2 < length; i2++) {
            objectOutputStream.writeDouble(get(i2));
        }
    }

    @CanIgnoreReturnValue
    public double addAndGet(int i2, double d) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.longs.get(i2);
            longBitsToDouble = Double.longBitsToDouble(j2) + d;
        } while (!this.longs.compareAndSet(i2, j2, Double.doubleToRawLongBits(longBitsToDouble)));
        return longBitsToDouble;
    }

    public final boolean compareAndSet(int i2, double d, double d2) {
        return this.longs.compareAndSet(i2, Double.doubleToRawLongBits(d), Double.doubleToRawLongBits(d2));
    }

    public final double get(int i2) {
        return Double.longBitsToDouble(this.longs.get(i2));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(int i2, double d) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.longs.get(i2);
            longBitsToDouble = Double.longBitsToDouble(j2);
        } while (!this.longs.compareAndSet(i2, j2, Double.doubleToRawLongBits(longBitsToDouble + d)));
        return longBitsToDouble;
    }

    public final double getAndSet(int i2, double d) {
        return Double.longBitsToDouble(this.longs.getAndSet(i2, Double.doubleToRawLongBits(d)));
    }

    public final void lazySet(int i2, double d) {
        this.longs.lazySet(i2, Double.doubleToRawLongBits(d));
    }

    public final int length() {
        return this.longs.length();
    }

    public final void set(int i2, double d) {
        this.longs.set(i2, Double.doubleToRawLongBits(d));
    }

    public String toString() {
        int length = length();
        int i2 = length - 1;
        if (i2 == -1) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder(length * 19);
        sb.append('[');
        int i3 = 0;
        while (true) {
            sb.append(Double.longBitsToDouble(this.longs.get(i3)));
            if (i3 == i2) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i3++;
        }
    }

    public final boolean weakCompareAndSet(int i2, double d, double d2) {
        return this.longs.weakCompareAndSet(i2, Double.doubleToRawLongBits(d), Double.doubleToRawLongBits(d2));
    }

    public AtomicDoubleArray(double[] dArr) {
        int length = dArr.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = Double.doubleToRawLongBits(dArr[i2]);
        }
        this.longs = new AtomicLongArray(jArr);
    }
}
