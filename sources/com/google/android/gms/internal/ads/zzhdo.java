package com.google.android.gms.internal.ads;

import com.safedk.android.analytics.brandsafety.ImpressionLog;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzhdo implements Map.Entry, Comparable {
    final /* synthetic */ zzhdu zza;
    private final Comparable zzb;
    private Object zzc;

    public zzhdo(zzhdu zzhduVar, Comparable comparable, Object obj) {
        this.zza = zzhduVar;
        this.zzb = comparable;
        this.zzc = obj;
    }

    private static final boolean zzb(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.zzb.compareTo(((zzhdo) obj).zzb);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return zzb(this.zzb, entry.getKey()) && zzb(this.zzc, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzb;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.zzc;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.zzb;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.zzc;
        return hashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        zzhdu.zzi(this.zza);
        Object obj2 = this.zzc;
        this.zzc = obj;
        return obj2;
    }

    public final String toString() {
        return androidx.compose.foundation.text.input.a.k(String.valueOf(this.zzb), ImpressionLog.R, String.valueOf(this.zzc));
    }

    public final Comparable zza() {
        return this.zzb;
    }
}
