package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

@Immutable
@ElementTypesAreNonnullByDefault
/* loaded from: classes.dex */
abstract class AbstractNonStreamingHashFunction extends AbstractHashFunction {

    /* loaded from: classes.dex */
    public final class BufferingHasher extends AbstractHasher {
        final ExposedByteArrayOutputStream stream;

        public BufferingHasher(int i2) {
            this.stream = new ExposedByteArrayOutputStream(i2);
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            return AbstractNonStreamingHashFunction.this.hashBytes(this.stream.byteArray(), 0, this.stream.length());
        }

        @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b2) {
            this.stream.write(b2);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr, int i2, int i3) {
            this.stream.write(bArr, i2, i3);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer byteBuffer) {
            this.stream.write(byteBuffer);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream(int i2) {
            super(i2);
        }

        public byte[] byteArray() {
            return ((ByteArrayOutputStream) this).buf;
        }

        public int length() {
            return ((ByteArrayOutputStream) this).count;
        }

        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            int i2 = ((ByteArrayOutputStream) this).count;
            int i3 = i2 + remaining;
            byte[] bArr = ((ByteArrayOutputStream) this).buf;
            if (i3 > bArr.length) {
                ((ByteArrayOutputStream) this).buf = Arrays.copyOf(bArr, i2 + remaining);
            }
            byteBuffer.get(((ByteArrayOutputStream) this).buf, ((ByteArrayOutputStream) this).count, remaining);
            ((ByteArrayOutputStream) this).count += remaining;
        }
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(ByteBuffer byteBuffer) {
        return newHasher(byteBuffer.remaining()).putBytes(byteBuffer).hash();
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public abstract HashCode hashBytes(byte[] bArr, int i2, int i3);

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashInt(int i2) {
        return hashBytes(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i2).array());
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashLong(long j2) {
        return hashBytes(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(j2).array());
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return hashBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int length = charSequence.length();
        ByteBuffer order = ByteBuffer.allocate(length * 2).order(ByteOrder.LITTLE_ENDIAN);
        for (int i2 = 0; i2 < length; i2++) {
            order.putChar(charSequence.charAt(i2));
        }
        return hashBytes(order.array());
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return newHasher(32);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public Hasher newHasher(int i2) {
        Preconditions.checkArgument(i2 >= 0);
        return new BufferingHasher(i2);
    }
}
