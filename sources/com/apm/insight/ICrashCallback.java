package com.apm.insight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public interface ICrashCallback {
    void onCrash(@NonNull CrashType crashType, @Nullable String str, @Nullable Thread thread);
}
