package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
final class zam {
    private final int zaa;
    private final ConnectionResult zab;

    public zam(ConnectionResult connectionResult, int i9) {
        Preconditions.checkNotNull(connectionResult);
        this.zab = connectionResult;
        this.zaa = i9;
    }

    public final int zaa() {
        return this.zaa;
    }

    public final ConnectionResult zab() {
        return this.zab;
    }
}
