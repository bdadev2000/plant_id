package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinMediationProvider;

/* loaded from: classes2.dex */
public final class zzbdz {
    public static int zza(Context context) {
        return zzf(context, "crash_without_write");
    }

    public static int zzb(Context context) {
        return zzf(context, "init_without_write");
    }

    public static void zzc(Context context) {
        zzg(context, "crash_without_write");
    }

    public static void zzd(Context context) {
        zzg(context, "init_without_write");
    }

    public static void zze(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppLovinMediationProvider.ADMOB, 0);
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putInt("init_without_write", 0).putInt("crash_without_write", 0).commit();
    }

    private static int zzf(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppLovinMediationProvider.ADMOB, 0);
        if (sharedPreferences == null) {
            return 0;
        }
        try {
            return sharedPreferences.getInt(str, 0);
        } catch (ClassCastException unused) {
            return 0;
        }
    }

    private static void zzg(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppLovinMediationProvider.ADMOB, 0);
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putInt(str, zzf(context, str) + 1).commit();
    }
}
