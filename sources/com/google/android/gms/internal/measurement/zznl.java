package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public final class zznl implements Supplier<zznk> {
    private static zznl zza = new zznl();
    private final Supplier<zznk> zzb = Suppliers.ofInstance(new zznn());

    @SideEffectFree
    public static long zza() {
        return ((zznk) zza.get()).zza();
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ zznk get() {
        return this.zzb.get();
    }
}
