package com.mbridge.msdk.videocommon.c;

import android.content.Context;
import android.os.Build;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.net.f.e;
import com.mbridge.msdk.foundation.same.net.f.f;
import com.mbridge.msdk.foundation.tools.j;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.out.MBConfiguration;

/* loaded from: classes3.dex */
public final class b extends com.mbridge.msdk.foundation.same.net.f.c {
    public b(Context context) {
        super(context);
    }

    @Override // com.mbridge.msdk.foundation.same.net.f.c
    public final void addExtraParams(String str, e eVar) {
        try {
            super.addExtraParams(str, eVar);
            eVar.a("platform", "1");
            eVar.a("os_version", Build.VERSION.RELEASE);
            eVar.a(CampaignEx.JSON_KEY_PACKAGE_NAME, z.e(this.mContext));
            eVar.a("app_version_name", z.p(this.mContext));
            eVar.a("app_version_code", z.q(this.mContext) + "");
            eVar.a("orientation", z.r(this.mContext) + "");
            eVar.a("model", z.h());
            eVar.a("brand", z.x());
            int l = z.l(this.mContext);
            eVar.a("network_type", l + "");
            eVar.a("network_str", z.a(this.mContext, l) + "");
            eVar.a("language", z.s(this.mContext));
            eVar.a("timezone", z.y());
            eVar.a("useragent", z.g());
            eVar.a("sdk_version", MBConfiguration.SDK_VERSION);
            eVar.a("screen_size", z.i(this.mContext) + "x" + z.g(this.mContext));
            if (com.mbridge.msdk.e.b.a()) {
                eVar.a("gaid", "");
                eVar.a("gp_version", j.c(this.mContext));
                eVar.a("is_clever", com.mbridge.msdk.foundation.same.a.f15656C);
            } else {
                eVar.a("gaid", com.mbridge.msdk.foundation.tools.e.c());
            }
            f.d(eVar);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }
}
