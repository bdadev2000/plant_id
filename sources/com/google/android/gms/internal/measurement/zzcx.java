package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/* loaded from: classes3.dex */
final class zzcx extends zzcy {
    @Override // com.google.android.gms.internal.measurement.zzcy
    public final URLConnection zza(URL url, String str) throws IOException {
        return url.openConnection();
    }

    private zzcx() {
    }
}
