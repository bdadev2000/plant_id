package com.google.android.gms.measurement.internal;

import com.facebook.gamingservices.cloudgaming.internal.SDKAnalyticsEvents;

/* loaded from: classes4.dex */
public final class zzjj {
    public static final String[] zza = {"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id", "last_advertising_id_reset", "first_open_after_install", "lifetime_user_engagement", "session_user_engagement", "non_personalized_ads", "ga_session_number", "ga_session_id", "last_gclid", "session_number", SDKAnalyticsEvents.PARAMETER_SESSION_ID};
    public static final String[] zzb = {"_ln", "_fot", "_fvt", "_ldl", "_id", "_lair", "_fi", "_lte", "_se", "_npa", "_sno", "_sid", "_lgclid", "_sno", "_sid"};

    public static String zza(String str) {
        return zzlh.zza(str, zza, zzb);
    }
}
