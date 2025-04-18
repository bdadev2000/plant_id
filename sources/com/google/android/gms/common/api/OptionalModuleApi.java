package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;

/* loaded from: classes4.dex */
public interface OptionalModuleApi {
    @NonNull
    @KeepForSdk
    Feature[] getOptionalFeatures();
}
