package com.google.android.gms.ads.internal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzfvg;
import com.google.android.gms.internal.ads.zzfvh;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class zzad {
    public static Bundle zza(Context context, String str, SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (TextUtils.isEmpty(str)) {
            return Bundle.EMPTY;
        }
        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        return zzb(context, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001b  */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.os.Bundle zzb(android.content.Context r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.zzad.zzb(android.content.Context, java.lang.String):android.os.Bundle");
    }

    public static void zzc(Context context) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzgs)).booleanValue() && context != null) {
            context.deleteDatabase("OfflineUpload.db");
        }
        try {
            zzfvg zzj = zzfvg.zzj(context);
            zzfvh zzi = zzfvh.zzi(context);
            zzj.zzk();
            zzj.zzl();
            zzi.zzj();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzcX)).booleanValue()) {
                zzi.zzk();
            }
            if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzcY)).booleanValue()) {
                zzi.zzl();
            }
        } catch (IOException e4) {
            com.google.android.gms.ads.internal.zzu.zzo().zzw(e4, "clearStorageOnIdlessMode");
        }
    }
}
