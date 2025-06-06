package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zacp;

/* loaded from: classes2.dex */
public abstract class ResultTransform<R extends Result, S extends Result> {
    @NonNull
    public final PendingResult<S> createFailedResult(@NonNull Status status) {
        return new zacp(status);
    }

    @NonNull
    public Status onFailure(@NonNull Status status) {
        return status;
    }

    @Nullable
    public abstract PendingResult<S> onSuccess(@NonNull R r9);
}
