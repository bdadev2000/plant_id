package com.google.android.gms.ads.internal.util.client;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* loaded from: classes2.dex */
public final class zzq {
    public static Context zza(Context context) throws zzp {
        return zzc(context).getModuleContext();
    }

    public static Object zzb(Context context, String str, zzo zzoVar) throws zzp {
        try {
            return zzoVar.zza(zzc(context).instantiate(str));
        } catch (Exception e4) {
            throw new zzp(e4);
        }
    }

    private static DynamiteModule zzc(Context context) throws zzp {
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
        } catch (Exception e4) {
            throw new zzp(e4);
        }
    }
}
