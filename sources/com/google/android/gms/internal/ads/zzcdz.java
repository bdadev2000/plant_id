package com.google.android.gms.internal.ads;

import android.os.Looper;

/* loaded from: classes2.dex */
final class zzcdz implements Runnable {
    public zzcdz(zzceb zzcebVar) {
    }

    @Override // java.lang.Runnable
    public final void run() {
        Looper.myLooper().quit();
    }
}
