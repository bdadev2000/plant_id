package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
public final class zaad {
    private final Map zaa = Collections.synchronizedMap(new WeakHashMap());
    private final Map zab = Collections.synchronizedMap(new WeakHashMap());

    private final void zah(boolean z8, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.zaa) {
            hashMap = new HashMap(this.zaa);
        }
        synchronized (this.zab) {
            hashMap2 = new HashMap(this.zab);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z8 || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).forceFailureUnlessReady(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z8 || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }

    public final void zac(BasePendingResult basePendingResult, boolean z8) {
        this.zaa.put(basePendingResult, Boolean.valueOf(z8));
        basePendingResult.addStatusListener(new zaab(this, basePendingResult));
    }

    public final void zad(TaskCompletionSource taskCompletionSource, boolean z8) {
        this.zab.put(taskCompletionSource, Boolean.valueOf(z8));
        taskCompletionSource.getTask().addOnCompleteListener(new zaac(this, taskCompletionSource));
    }

    public final void zae(int i9, @Nullable String str) {
        StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
        if (i9 == 1) {
            sb.append(" due to service disconnection.");
        } else if (i9 == 3) {
            sb.append(" due to dead object exception.");
        }
        if (str != null) {
            sb.append(" Last reason for disconnect: ");
            sb.append(str);
        }
        zah(true, new Status(20, sb.toString()));
    }

    public final void zaf() {
        zah(false, GoogleApiManager.zaa);
    }

    public final boolean zag() {
        if (this.zaa.isEmpty() && this.zab.isEmpty()) {
            return false;
        }
        return true;
    }
}
