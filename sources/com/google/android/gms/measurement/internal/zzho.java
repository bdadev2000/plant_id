package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class zzho extends LruCache<String, com.google.android.gms.internal.measurement.zzb> {
    private final /* synthetic */ zzhl zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzho(zzhl zzhlVar, int i2) {
        super(20);
        this.zza = zzhlVar;
    }

    @Override // androidx.collection.LruCache
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzb create(String str) {
        String str2 = str;
        Preconditions.checkNotEmpty(str2);
        return zzhl.zza(this.zza, str2);
    }
}
