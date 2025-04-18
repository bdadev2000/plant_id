package com.google.common.hash;

import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: Access modifiers changed from: package-private */
@Immutable
@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    private final int seed;
    static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
    static final HashFunction GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);

    /* loaded from: classes2.dex */
    public static final class Murmur3_128Hasher extends AbstractStreamingHasher {
        private static final long C1 = -8663945395140668459L;
        private static final long C2 = 5545529020109919103L;
        private static final int CHUNK_SIZE = 16;

        /* renamed from: h1, reason: collision with root package name */
        private long f28781h1;
        private long h2;
        private int length;

        public Murmur3_128Hasher(int i2) {
            super(16);
            long j2 = i2;
            this.f28781h1 = j2;
            this.h2 = j2;
            this.length = 0;
        }

        private void bmix64(long j2, long j3) {
            long mixK1 = mixK1(j2) ^ this.f28781h1;
            this.f28781h1 = mixK1;
            long rotateLeft = Long.rotateLeft(mixK1, 27);
            long j4 = this.h2;
            this.f28781h1 = ((rotateLeft + j4) * 5) + 1390208809;
            long mixK2 = mixK2(j3) ^ j4;
            this.h2 = mixK2;
            this.h2 = ((Long.rotateLeft(mixK2, 31) + this.f28781h1) * 5) + 944331445;
        }

        private static long fmix64(long j2) {
            long j3 = (j2 ^ (j2 >>> 33)) * (-49064778989728563L);
            long j4 = (j3 ^ (j3 >>> 33)) * (-4265267296055464877L);
            return j4 ^ (j4 >>> 33);
        }

        private static long mixK1(long j2) {
            return Long.rotateLeft(j2 * C1, 31) * C2;
        }

        private static long mixK2(long j2) {
            return Long.rotateLeft(j2 * C2, 33) * C1;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long j2 = this.f28781h1;
            int i2 = this.length;
            long j3 = j2 ^ i2;
            long j4 = this.h2 ^ i2;
            long j5 = j3 + j4;
            this.f28781h1 = j5;
            this.h2 = j4 + j5;
            this.f28781h1 = fmix64(j5);
            long fmix64 = fmix64(this.h2);
            long j6 = this.f28781h1 + fmix64;
            this.f28781h1 = j6;
            this.h2 = fmix64 + j6;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.f28781h1).putLong(this.h2).array());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void process(ByteBuffer byteBuffer) {
            bmix64(byteBuffer.getLong(), byteBuffer.getLong());
            this.length += 16;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001b. Please report as an issue. */
        @Override // com.google.common.hash.AbstractStreamingHasher
        public void processRemaining(ByteBuffer byteBuffer) {
            long j2;
            long j3;
            long j4;
            long j5;
            long j6;
            long j7;
            long j8;
            this.length = byteBuffer.remaining() + this.length;
            long j9 = 0;
            switch (byteBuffer.remaining()) {
                case 1:
                    j2 = 0;
                    j8 = j2 ^ UnsignedBytes.toInt(byteBuffer.get(0));
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 2:
                    j3 = 0;
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = j2 ^ UnsignedBytes.toInt(byteBuffer.get(0));
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 3:
                    j4 = 0;
                    j3 = (UnsignedBytes.toInt(byteBuffer.get(2)) << 16) ^ j4;
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = j2 ^ UnsignedBytes.toInt(byteBuffer.get(0));
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 4:
                    j5 = 0;
                    j4 = j5 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j3 = (UnsignedBytes.toInt(byteBuffer.get(2)) << 16) ^ j4;
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = j2 ^ UnsignedBytes.toInt(byteBuffer.get(0));
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 5:
                    j6 = 0;
                    j5 = j6 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j4 = j5 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j3 = (UnsignedBytes.toInt(byteBuffer.get(2)) << 16) ^ j4;
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = j2 ^ UnsignedBytes.toInt(byteBuffer.get(0));
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 6:
                    j7 = 0;
                    j6 = (UnsignedBytes.toInt(byteBuffer.get(5)) << 40) ^ j7;
                    j5 = j6 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j4 = j5 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j3 = (UnsignedBytes.toInt(byteBuffer.get(2)) << 16) ^ j4;
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = j2 ^ UnsignedBytes.toInt(byteBuffer.get(0));
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 7:
                    j7 = UnsignedBytes.toInt(byteBuffer.get(6)) << 48;
                    j6 = (UnsignedBytes.toInt(byteBuffer.get(5)) << 40) ^ j7;
                    j5 = j6 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j4 = j5 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j3 = (UnsignedBytes.toInt(byteBuffer.get(2)) << 16) ^ j4;
                    j2 = j3 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j8 = j2 ^ UnsignedBytes.toInt(byteBuffer.get(0));
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 8:
                    j8 = byteBuffer.getLong();
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 9:
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong();
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 10:
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(9)) << 8;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong();
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 11:
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(10)) << 16;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(9)) << 8;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong();
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 12:
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(11)) << 24;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(10)) << 16;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(9)) << 8;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong();
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 13:
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(12)) << 32;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(11)) << 24;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(10)) << 16;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(9)) << 8;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong();
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 14:
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(13)) << 40;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(12)) << 32;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(11)) << 24;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(10)) << 16;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(9)) << 8;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong();
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                case 15:
                    j9 = UnsignedBytes.toInt(byteBuffer.get(14)) << 48;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(13)) << 40;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(12)) << 32;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(11)) << 24;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(10)) << 16;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(9)) << 8;
                    j9 ^= UnsignedBytes.toInt(byteBuffer.get(8));
                    j8 = byteBuffer.getLong();
                    this.f28781h1 = mixK1(j8) ^ this.f28781h1;
                    this.h2 ^= mixK2(j9);
                    return;
                default:
                    throw new AssertionError("Should never get here.");
            }
        }
    }

    public Murmur3_128HashFunction(int i2) {
        this.seed = i2;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 128;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Murmur3_128HashFunction) && this.seed == ((Murmur3_128HashFunction) obj).seed;
    }

    public int hashCode() {
        return Murmur3_128HashFunction.class.hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        int i2 = this.seed;
        StringBuilder sb = new StringBuilder(32);
        sb.append("Hashing.murmur3_128(");
        sb.append(i2);
        sb.append(")");
        return sb.toString();
    }
}
