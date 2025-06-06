package com.mbridge.msdk.dycreator.binding;

import android.text.TextUtils;
import android.view.View;
import com.adjust.sdk.Constants;
import com.mbridge.msdk.dycreator.baseview.MBCountDownView;
import com.mbridge.msdk.dycreator.baseview.MBLinearLayout;
import com.mbridge.msdk.dycreator.baseview.MBRelativeLayout;
import com.mbridge.msdk.dycreator.baseview.inter.InterBase;
import com.mbridge.msdk.dycreator.binding.a.e;
import com.mbridge.msdk.dycreator.binding.a.f;
import com.mbridge.msdk.dycreator.binding.a.g;
import com.mbridge.msdk.dycreator.binding.base.BaseStrategy;
import com.mbridge.msdk.dycreator.bridge.MBSplashData;
import com.mbridge.msdk.dycreator.e.d;
import com.vungle.ads.internal.presenter.y;

/* loaded from: classes3.dex */
public final class a implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private com.mbridge.msdk.dycreator.f.a.a f15202a;

    public a(com.mbridge.msdk.dycreator.f.a.a aVar) {
        this.f15202a = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        com.mbridge.msdk.dycreator.f.a.a aVar;
        if (view != 0 && (view instanceof InterBase)) {
            if ((view instanceof MBCountDownView) && (aVar = this.f15202a) != null && aVar.getEffectData() != null && !this.f15202a.getEffectData().isCanSkip()) {
                return;
            }
            com.mbridge.msdk.dycreator.f.a.a aVar2 = this.f15202a;
            if (aVar2 != null && (view instanceof MBRelativeLayout) && d.a(view, aVar2)) {
                com.mbridge.msdk.dycreator.f.a.a aVar3 = this.f15202a;
                if (aVar3 instanceof MBSplashData) {
                    MBRelativeLayout mBRelativeLayout = (MBRelativeLayout) view;
                    ((MBSplashData) aVar3).setxInScreen(mBRelativeLayout.getxInScreen());
                    ((MBSplashData) this.f15202a).setyInScreen(mBRelativeLayout.getyInScreen());
                    ((MBSplashData) this.f15202a).setClickType(1);
                }
            }
            com.mbridge.msdk.dycreator.f.a.a aVar4 = this.f15202a;
            if (aVar4 != null && (view instanceof MBLinearLayout) && d.a(view, aVar4)) {
                com.mbridge.msdk.dycreator.f.a.a aVar5 = this.f15202a;
                if (aVar5 instanceof MBSplashData) {
                    MBLinearLayout mBLinearLayout = (MBLinearLayout) view;
                    ((MBSplashData) aVar5).setxInScreen(mBLinearLayout.getxInScreen());
                    ((MBSplashData) this.f15202a).setyInScreen(mBLinearLayout.getyInScreen());
                    ((MBSplashData) this.f15202a).setClickType(0);
                }
            }
            String strategyDes = ((InterBase) view).getStrategyDes();
            if (!TextUtils.isEmpty(strategyDes)) {
                c.a();
                BaseStrategy baseStrategy = null;
                if (!TextUtils.isEmpty(strategyDes)) {
                    if (strategyDes.equals("close")) {
                        baseStrategy = new com.mbridge.msdk.dycreator.binding.a.c();
                    } else if (strategyDes.equals(y.DOWNLOAD)) {
                        baseStrategy = new com.mbridge.msdk.dycreator.binding.a.b();
                    } else if (!strategyDes.equals(Constants.DEEPLINK) && strategyDes.equals("activity")) {
                        baseStrategy = new com.mbridge.msdk.dycreator.binding.a.a();
                    }
                    if (strategyDes.equals("feedback")) {
                        baseStrategy = new com.mbridge.msdk.dycreator.binding.a.d();
                    }
                    if (strategyDes.equals("notice")) {
                        baseStrategy = new e();
                    }
                    if (strategyDes.equals("permissionInfo")) {
                        baseStrategy = new f();
                    }
                    if (strategyDes.equals("privateAddress")) {
                        baseStrategy = new g();
                    }
                }
                if (baseStrategy != null) {
                    baseStrategy.bindClickData(view, this.f15202a);
                }
            }
        }
    }
}
