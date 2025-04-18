package com.google.android.gms.internal.measurement;

import android.support.v4.media.d;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzjt.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public abstract class zzjt<MessageType extends zzjt<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzib<MessageType, BuilderType> {
    private static Map<Class<?>, zzjt<?, ?>> zzc = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzmj zzb = zzmj.zzc();

    /* loaded from: classes3.dex */
    public static class zza<T extends zzjt<T, ?>> extends zzif<T> {
        public zza(T t2) {
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class zzb<MessageType extends zzjt<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzid<MessageType, BuilderType> {
        protected MessageType zza;
        private final MessageType zzb;

        public zzb(MessageType messagetype) {
            this.zzb = messagetype;
            if (messagetype.zzco()) {
                throw new IllegalArgumentException("Default instance must be immutable.");
            }
            this.zza = (MessageType) messagetype.zzce();
        }

        private final BuilderType zzb(byte[] bArr, int i2, int i3, zzjg zzjgVar) throws zzkb {
            if (!this.zza.zzco()) {
                zzal();
            }
            try {
                zzlq.zza().zza((zzlq) this.zza).zza(this.zza, bArr, 0, i3, new zzij(zzjgVar));
                return this;
            } catch (zzkb e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            } catch (IndexOutOfBoundsException unused) {
                throw zzkb.zzi();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.internal.measurement.zzid
        /* renamed from: zzc, reason: merged with bridge method [inline-methods] */
        public final BuilderType zzb(zziw zziwVar, zzjg zzjgVar) throws IOException {
            if (!this.zza.zzco()) {
                zzal();
            }
            try {
                zzlq.zza().zza((zzlq) this.zza).zza(this.zza, zzjb.zza(zziwVar), zzjgVar);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzid
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzbVar = (zzb) this.zzb.zza(zze.zze, null, null);
            zzbVar.zza = (MessageType) zzaj();
            return zzbVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzid
        /* renamed from: zza */
        public final /* synthetic */ zzid zzb(zziw zziwVar, zzjg zzjgVar) throws IOException {
            return (zzb) zzb(zziwVar, zzjgVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzid
        /* renamed from: zzaf */
        public final /* synthetic */ zzid clone() {
            return (zzb) clone();
        }

        @Override // com.google.android.gms.internal.measurement.zzlb
        /* renamed from: zzag, reason: merged with bridge method [inline-methods] */
        public final MessageType zzai() {
            MessageType messagetype = (MessageType) zzaj();
            if (zzjt.zza(messagetype, true)) {
                return messagetype;
            }
            throw new zzmh(messagetype);
        }

        @Override // com.google.android.gms.internal.measurement.zzlb
        /* renamed from: zzah, reason: merged with bridge method [inline-methods] */
        public MessageType zzaj() {
            if (!this.zza.zzco()) {
                return this.zza;
            }
            this.zza.zzcl();
            return this.zza;
        }

        public final void zzak() {
            if (this.zza.zzco()) {
                return;
            }
            zzal();
        }

        public void zzal() {
            MessageType messagetype = (MessageType) this.zzb.zzce();
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        @Override // com.google.android.gms.internal.measurement.zzle
        public final /* synthetic */ zzlc zzck() {
            return this.zzb;
        }

        @Override // com.google.android.gms.internal.measurement.zzle
        public final boolean zzcn() {
            return zzjt.zza(this.zza, false);
        }

        @Override // com.google.android.gms.internal.measurement.zzid
        public final /* synthetic */ zzid zza(byte[] bArr, int i2, int i3) throws zzkb {
            return zzb(bArr, 0, i3, zzjg.zza);
        }

        @Override // com.google.android.gms.internal.measurement.zzid
        public final /* synthetic */ zzid zza(byte[] bArr, int i2, int i3, zzjg zzjgVar) throws zzkb {
            return zzb(bArr, 0, i3, zzjgVar);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb.equals(messagetype)) {
                return this;
            }
            if (!this.zza.zzco()) {
                zzal();
            }
            zza(this.zza, messagetype);
            return this;
        }

        private static <MessageType> void zza(MessageType messagetype, MessageType messagetype2) {
            zzlq.zza().zza((zzlq) messagetype).zza(messagetype, messagetype2);
        }
    }

    /* loaded from: classes3.dex */
    public static final class zzc implements zzjo<zzc> {
        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjo
        public final int zza() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjo
        public final zzms zzb() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjo
        public final zzmz zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjo
        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjo
        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjo
        public final zzlb zza(zzlb zzlbVar, zzlc zzlcVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzjo
        public final zzlh zza(zzlh zzlhVar, zzlh zzlhVar2) {
            throw new NoSuchMethodError();
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzjt<MessageType, BuilderType> implements zzle {
        protected zzjm<zzc> zzc = zzjm.zzb();

        public final zzjm<zzc> zza() {
            if (this.zzc.zzf()) {
                this.zzc = (zzjm) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    /* loaded from: classes3.dex */
    public enum zze {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzh = {1, 2, 3, 4, 5, 6, 7};

        public static int[] zza() {
            return (int[]) zzh.clone();
        }
    }

    /* loaded from: classes3.dex */
    public static class zzf<ContainingType extends zzlc, Type> extends zzjh<ContainingType, Type> {
    }

    private final int zza() {
        return zzlq.zza().zza((zzlq) this).zzb(this);
    }

    private final int zzb(zzlu<?> zzluVar) {
        return zzluVar == null ? zzlq.zza().zza((zzlq) this).zza(this) : zzluVar.zza(this);
    }

    public static zzka zzcf() {
        return zzjw.zzd();
    }

    public static zzjz zzcg() {
        return zzkn.zzd();
    }

    public static <E> zzkc<E> zzch() {
        return zzlp.zzd();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzlq.zza().zza((zzlq) this).zzb(this, (zzjt) obj);
        }
        return false;
    }

    public int hashCode() {
        if (zzco()) {
            return zza();
        }
        if (this.zza == 0) {
            this.zza = zza();
        }
        return this.zza;
    }

    public String toString() {
        return zzld.zza(this, super.toString());
    }

    public abstract Object zza(int i2, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.measurement.zzib
    public final int zzby() {
        return this.zzd & Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    @Override // com.google.android.gms.internal.measurement.zzib
    public final void zzc(int i2) {
        if (i2 < 0) {
            throw new IllegalStateException(d.i("serialized size must be non-negative, was ", i2));
        }
        this.zzd = (i2 & Api.BaseClientBuilder.API_PRIORITY_OTHER) | (this.zzd & Integer.MIN_VALUE);
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final int zzcb() {
        return zza((zzlu) null);
    }

    public final <MessageType extends zzjt<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzcc() {
        return (BuilderType) zza(zze.zze, (Object) null, (Object) null);
    }

    public final BuilderType zzcd() {
        return (BuilderType) ((zzb) zza(zze.zze, (Object) null, (Object) null)).zza((zzb) this);
    }

    public final MessageType zzce() {
        return (MessageType) zza(zze.zzd, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final /* synthetic */ zzlb zzci() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final /* synthetic */ zzlb zzcj() {
        return ((zzb) zza(zze.zze, (Object) null, (Object) null)).zza((zzb) this);
    }

    @Override // com.google.android.gms.internal.measurement.zzle
    public final /* synthetic */ zzlc zzck() {
        return (zzjt) zza(zze.zzf, (Object) null, (Object) null);
    }

    public final void zzcl() {
        zzlq.zza().zza((zzlq) this).zzd(this);
        zzcm();
    }

    public final void zzcm() {
        this.zzd &= Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    @Override // com.google.android.gms.internal.measurement.zzle
    public final boolean zzcn() {
        return zza(this, true);
    }

    public final boolean zzco() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzib
    public final int zza(zzlu zzluVar) {
        if (zzco()) {
            int zzb2 = zzb(zzluVar);
            if (zzb2 >= 0) {
                return zzb2;
            }
            throw new IllegalStateException(d.i("serialized size must be non-negative, was ", zzb2));
        }
        if (zzby() != Integer.MAX_VALUE) {
            return zzby();
        }
        int zzb3 = zzb(zzluVar);
        zzc(zzb3);
        return zzb3;
    }

    public final <MessageType extends zzjt<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zza(MessageType messagetype) {
        return (BuilderType) zzcc().zza(messagetype);
    }

    public static <T extends zzjt<?, ?>> T zza(Class<T> cls) {
        zzjt<?, ?> zzjtVar = zzc.get(cls);
        if (zzjtVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzjtVar = zzc.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzjtVar == null) {
            zzjtVar = (T) ((zzjt) zzml.zza(cls)).zza(zze.zzf, (Object) null, (Object) null);
            if (zzjtVar != null) {
                zzc.put(cls, zzjtVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return (T) zzjtVar;
    }

    public static zzjz zza(zzjz zzjzVar) {
        return zzjzVar.zza(zzjzVar.size() << 1);
    }

    public static <E> zzkc<E> zza(zzkc<E> zzkcVar) {
        return zzkcVar.zza(zzkcVar.size() << 1);
    }

    public static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (!(cause instanceof RuntimeException)) {
                if (cause instanceof Error) {
                    throw ((Error) cause);
                }
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
            throw ((RuntimeException) cause);
        }
    }

    public static Object zza(zzlc zzlcVar, String str, Object[] objArr) {
        return new zzls(zzlcVar, str, objArr);
    }

    public static <T extends zzjt<?, ?>> void zza(Class<T> cls, T t2) {
        t2.zzcm();
        zzc.put(cls, t2);
    }

    @Override // com.google.android.gms.internal.measurement.zzlc
    public final void zza(zzjc zzjcVar) throws IOException {
        zzlq.zza().zza((zzlq) this).zza((zzlu) this, (zznb) zzjf.zza(zzjcVar));
    }

    public static final <T extends zzjt<T, ?>> boolean zza(T t2, boolean z2) {
        byte byteValue = ((Byte) t2.zza(zze.zza, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zze2 = zzlq.zza().zza((zzlq) t2).zze(t2);
        if (z2) {
            t2.zza(zze.zzb, zze2 ? t2 : null, null);
        }
        return zze2;
    }
}
