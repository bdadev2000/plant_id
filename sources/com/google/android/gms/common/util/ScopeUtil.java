package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

@KeepForSdk
/* loaded from: classes2.dex */
public final class ScopeUtil {
    private ScopeUtil() {
    }

    @NonNull
    @KeepForSdk
    public static String[] toScopeString(@NonNull Set<Scope> set) {
        Preconditions.checkNotNull(set, "scopes can't be null.");
        Scope[] scopeArr = (Scope[]) set.toArray(new Scope[set.size()]);
        Preconditions.checkNotNull(scopeArr, "scopes can't be null.");
        String[] strArr = new String[scopeArr.length];
        for (int i9 = 0; i9 < scopeArr.length; i9++) {
            strArr[i9] = scopeArr[i9].getScopeUri();
        }
        return strArr;
    }
}
