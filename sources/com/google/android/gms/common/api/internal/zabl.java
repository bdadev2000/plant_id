package com.google.android.gms.common.api.internal;

import android.os.Handler;
import com.google.android.gms.common.api.internal.BackgroundDetector;

/* loaded from: classes2.dex */
final class zabl implements BackgroundDetector.BackgroundStateChangeListener {
    final /* synthetic */ GoogleApiManager zaa;

    public zabl(GoogleApiManager googleApiManager) {
        this.zaa = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z8) {
        Handler handler;
        Handler handler2;
        GoogleApiManager googleApiManager = this.zaa;
        handler = googleApiManager.zar;
        handler2 = googleApiManager.zar;
        handler.sendMessage(handler2.obtainMessage(1, Boolean.valueOf(z8)));
    }
}
