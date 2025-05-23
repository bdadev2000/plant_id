package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public class MediationAdConfiguration {
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;
    private final String zza;
    private final Bundle zzb;
    private final Bundle zzc;
    private final Context zzd;
    private final boolean zze;
    private final int zzf;
    private final int zzg;

    @Nullable
    private final String zzh;
    private final String zzi;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TagForChildDirectedTreatment {
    }

    public MediationAdConfiguration(@NonNull Context context, @NonNull String str, @NonNull Bundle bundle, @NonNull Bundle bundle2, boolean z8, @Nullable Location location, int i9, int i10, @Nullable String str2, @NonNull String str3) {
        this.zza = str;
        this.zzb = bundle;
        this.zzc = bundle2;
        this.zzd = context;
        this.zze = z8;
        this.zzf = i9;
        this.zzg = i10;
        this.zzh = str2;
        this.zzi = str3;
    }

    @NonNull
    public String getBidResponse() {
        return this.zza;
    }

    @NonNull
    public Context getContext() {
        return this.zzd;
    }

    @Nullable
    public String getMaxAdContentRating() {
        return this.zzh;
    }

    @NonNull
    public Bundle getMediationExtras() {
        return this.zzc;
    }

    @NonNull
    public Bundle getServerParameters() {
        return this.zzb;
    }

    @NonNull
    public String getWatermark() {
        return this.zzi;
    }

    public boolean isTestRequest() {
        return this.zze;
    }

    public int taggedForChildDirectedTreatment() {
        return this.zzf;
    }

    public int taggedForUnderAgeTreatment() {
        return this.zzg;
    }
}
