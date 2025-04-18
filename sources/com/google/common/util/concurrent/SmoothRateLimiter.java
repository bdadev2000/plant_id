package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
@GwtIncompatible
/* loaded from: classes3.dex */
public abstract class SmoothRateLimiter extends RateLimiter {
    double maxPermits;
    private long nextFreeTicketMicros;
    double stableIntervalMicros;
    double storedPermits;

    /* loaded from: classes3.dex */
    public static final class SmoothBursty extends SmoothRateLimiter {
        final double maxBurstSeconds;

        public SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d) {
            super(sleepingStopwatch);
            this.maxBurstSeconds = d;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public double coolDownIntervalMicros() {
            return this.stableIntervalMicros;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double d, double d2) {
            double d3 = this.maxPermits;
            double d4 = this.maxBurstSeconds * d;
            this.maxPermits = d4;
            if (d3 == Double.POSITIVE_INFINITY) {
                this.storedPermits = d4;
                return;
            }
            double d5 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            if (d3 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d5 = (this.storedPermits * d4) / d3;
            }
            this.storedPermits = d5;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public long storedPermitsToWaitTime(double d, double d2) {
            return 0L;
        }
    }

    /* loaded from: classes3.dex */
    public static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double coldFactor;
        private double slope;
        private double thresholdPermits;
        private final long warmupPeriodMicros;

        public SmoothWarmingUp(RateLimiter.SleepingStopwatch sleepingStopwatch, long j2, TimeUnit timeUnit, double d) {
            super(sleepingStopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(j2);
            this.coldFactor = d;
        }

        private double permitsToTime(double d) {
            return (d * this.slope) + this.stableIntervalMicros;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public double coolDownIntervalMicros() {
            return this.warmupPeriodMicros / this.maxPermits;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double d, double d2) {
            double d3 = this.maxPermits;
            double d4 = this.coldFactor * d2;
            long j2 = this.warmupPeriodMicros;
            double d5 = (j2 * 0.5d) / d2;
            this.thresholdPermits = d5;
            double d6 = ((j2 * 2.0d) / (d2 + d4)) + d5;
            this.maxPermits = d6;
            this.slope = (d4 - d2) / (d6 - d5);
            if (d3 == Double.POSITIVE_INFINITY) {
                this.storedPermits = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                return;
            }
            if (d3 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d6 = (this.storedPermits * d6) / d3;
            }
            this.storedPermits = d6;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public long storedPermitsToWaitTime(double d, double d2) {
            long j2;
            double d3 = d - this.thresholdPermits;
            if (d3 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                double min = Math.min(d3, d2);
                j2 = (long) (((permitsToTime(d3) + permitsToTime(d3 - min)) * min) / 2.0d);
                d2 -= min;
            } else {
                j2 = 0;
            }
            return j2 + ((long) (this.stableIntervalMicros * d2));
        }
    }

    public abstract double coolDownIntervalMicros();

    @Override // com.google.common.util.concurrent.RateLimiter
    public final double doGetRate() {
        return TimeUnit.SECONDS.toMicros(1L) / this.stableIntervalMicros;
    }

    public abstract void doSetRate(double d, double d2);

    @Override // com.google.common.util.concurrent.RateLimiter
    public final void doSetRate(double d, long j2) {
        resync(j2);
        double micros = TimeUnit.SECONDS.toMicros(1L) / d;
        this.stableIntervalMicros = micros;
        doSetRate(d, micros);
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final long queryEarliestAvailable(long j2) {
        return this.nextFreeTicketMicros;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final long reserveEarliestAvailable(int i2, long j2) {
        resync(j2);
        long j3 = this.nextFreeTicketMicros;
        double d = i2;
        double min = Math.min(d, this.storedPermits);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, storedPermitsToWaitTime(this.storedPermits, min) + ((long) ((d - min) * this.stableIntervalMicros)));
        this.storedPermits -= min;
        return j3;
    }

    public void resync(long j2) {
        if (j2 > this.nextFreeTicketMicros) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + ((j2 - r0) / coolDownIntervalMicros()));
            this.nextFreeTicketMicros = j2;
        }
    }

    public abstract long storedPermitsToWaitTime(double d, double d2);

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0L;
    }
}
