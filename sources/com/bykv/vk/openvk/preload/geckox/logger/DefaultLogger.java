package com.bykv.vk.openvk.preload.geckox.logger;

import android.util.Log;
import com.bykv.vk.openvk.preload.geckox.c.b;

/* loaded from: classes.dex */
class DefaultLogger implements Logger {
    @Override // com.bykv.vk.openvk.preload.geckox.logger.Logger
    public void d(String str, Object... objArr) {
        if (objArr == null) {
            Log.d(str, "null");
        } else {
            try {
                Log.d(str, b.a().f13232a.a(objArr));
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.bykv.vk.openvk.preload.geckox.logger.Logger
    public void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // com.bykv.vk.openvk.preload.geckox.logger.Logger
    public void w(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // com.bykv.vk.openvk.preload.geckox.logger.Logger
    public void w(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        Log.w(str, str2);
    }
}
