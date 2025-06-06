package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.measurement.zzdq;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdp extends zzdq.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Context zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ zzdq zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdp(zzdq zzdqVar, String str, String str2, Context context, Bundle bundle) {
        super(zzdqVar);
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = bundle;
        this.zzg = zzdqVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdq.zza
    public final void zza() {
        boolean zzc;
        String str;
        String str2;
        String str3;
        zzdb zzdbVar;
        boolean z8;
        zzdb zzdbVar2;
        String str4;
        String str5;
        try {
            zzc = this.zzg.zzc(this.zzc, this.zzd);
            if (zzc) {
                String str6 = this.zzd;
                String str7 = this.zzc;
                str5 = this.zzg.zzc;
                str3 = str6;
                str2 = str7;
                str = str5;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            Preconditions.checkNotNull(this.zze);
            zzdq zzdqVar = this.zzg;
            zzdqVar.zzj = zzdqVar.zza(this.zze, true);
            zzdbVar = this.zzg.zzj;
            if (zzdbVar == null) {
                str4 = this.zzg.zzc;
                Log.w(str4, "Failed to connect to measurement client.");
                return;
            }
            int localVersion = DynamiteModule.getLocalVersion(this.zze, ModuleDescriptor.MODULE_ID);
            int remoteVersion = DynamiteModule.getRemoteVersion(this.zze, ModuleDescriptor.MODULE_ID);
            int max = Math.max(localVersion, remoteVersion);
            if (remoteVersion < localVersion) {
                z8 = true;
            } else {
                z8 = false;
            }
            zzdo zzdoVar = new zzdo(97001L, max, z8, str, str2, str3, this.zzf, com.google.android.gms.measurement.internal.zzhd.zza(this.zze));
            zzdbVar2 = this.zzg.zzj;
            ((zzdb) Preconditions.checkNotNull(zzdbVar2)).initialize(ObjectWrapper.wrap(this.zze), zzdoVar, this.zza);
        } catch (Exception e4) {
            this.zzg.zza(e4, true, false);
        }
    }
}
