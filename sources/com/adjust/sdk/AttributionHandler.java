package com.adjust.sdk;

import android.net.Uri;
import com.adjust.sdk.network.IActivityPackageSender;
import com.adjust.sdk.scheduler.SingleThreadCachedScheduler;
import com.adjust.sdk.scheduler.ThreadScheduler;
import com.adjust.sdk.scheduler.TimerOnce;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AttributionHandler implements IAttributionHandler, IActivityPackageSender.ResponseDataCallbackSubscriber {
    private static final String ATTRIBUTION_TIMER_NAME = "Attribution timer";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private IActivityPackageSender activityPackageSender;
    private String lastInitiatedBy;
    private boolean paused;
    private ILogger logger = AdjustFactory.getLogger();
    private ThreadScheduler scheduler = new SingleThreadCachedScheduler("AttributionHandler");
    private TimerOnce timer = new TimerOnce(new a(), ATTRIBUTION_TIMER_NAME);

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            AttributionHandler.this.sendAttributionRequest();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            AttributionHandler.this.lastInitiatedBy = "sdk";
            AttributionHandler.this.getAttributionI(0L);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SessionResponseData f5521a;

        public c(SessionResponseData sessionResponseData) {
            this.f5521a = sessionResponseData;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IActivityHandler iActivityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
            if (iActivityHandler == null) {
                return;
            }
            AttributionHandler.this.checkSessionResponseI(iActivityHandler, this.f5521a);
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SdkClickResponseData f5522a;

        public d(SdkClickResponseData sdkClickResponseData) {
            this.f5522a = sdkClickResponseData;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IActivityHandler iActivityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
            if (iActivityHandler == null) {
                return;
            }
            AttributionHandler.this.checkSdkClickResponseI(iActivityHandler, this.f5522a);
        }
    }

    /* loaded from: classes.dex */
    public class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AttributionResponseData f5523a;

        public e(AttributionResponseData attributionResponseData) {
            this.f5523a = attributionResponseData;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IActivityHandler iActivityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
            if (iActivityHandler == null) {
                return;
            }
            AttributionHandler.this.checkAttributionResponseI(iActivityHandler, this.f5523a);
        }
    }

    /* loaded from: classes.dex */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            AttributionHandler.this.sendAttributionRequestI();
        }
    }

    /* loaded from: classes.dex */
    public class g implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ResponseData f5525a;

        public g(ResponseData responseData) {
            this.f5525a = responseData;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IActivityHandler iActivityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
            if (iActivityHandler == null) {
                return;
            }
            ResponseData responseData = this.f5525a;
            if (responseData.trackingState == TrackingState.OPTED_OUT) {
                iActivityHandler.gotOptOutResponse();
            } else if (responseData instanceof AttributionResponseData) {
                AttributionHandler.this.checkAttributionResponseI(iActivityHandler, (AttributionResponseData) responseData);
            }
        }
    }

    public AttributionHandler(IActivityHandler iActivityHandler, boolean z8, IActivityPackageSender iActivityPackageSender) {
        init(iActivityHandler, z8, iActivityPackageSender);
    }

    private ActivityPackage buildAndGetAttributionPackage() {
        long currentTimeMillis = System.currentTimeMillis();
        IActivityHandler iActivityHandler = this.activityHandlerWeakRef.get();
        ActivityPackage buildAttributionPackage = new PackageBuilder(iActivityHandler.getAdjustConfig(), iActivityHandler.getDeviceInfo(), iActivityHandler.getActivityState(), iActivityHandler.getSessionParameters(), currentTimeMillis).buildAttributionPackage(this.lastInitiatedBy);
        this.lastInitiatedBy = null;
        return buildAttributionPackage;
    }

    private void checkAttributionI(IActivityHandler iActivityHandler, ResponseData responseData) {
        if (responseData.jsonResponse == null) {
            return;
        }
        Long l = responseData.askIn;
        if (l == null || l.longValue() < 0) {
            iActivityHandler.setAskingAttribution(false);
            return;
        }
        iActivityHandler.setAskingAttribution(true);
        this.lastInitiatedBy = "backend";
        getAttributionI(l.longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAttributionResponseI(IActivityHandler iActivityHandler, AttributionResponseData attributionResponseData) {
        checkAttributionI(iActivityHandler, attributionResponseData);
        checkDeeplinkI(attributionResponseData);
        iActivityHandler.launchAttributionResponseTasks(attributionResponseData);
    }

    private void checkDeeplinkI(AttributionResponseData attributionResponseData) {
        JSONObject optJSONObject;
        String optString;
        JSONObject jSONObject = attributionResponseData.jsonResponse;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("attribution")) == null || (optString = optJSONObject.optString(Constants.DEEPLINK, null)) == null) {
            return;
        }
        attributionResponseData.deeplink = Uri.parse(optString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSdkClickResponseI(IActivityHandler iActivityHandler, SdkClickResponseData sdkClickResponseData) {
        checkAttributionI(iActivityHandler, sdkClickResponseData);
        iActivityHandler.launchSdkClickResponseTasks(sdkClickResponseData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSessionResponseI(IActivityHandler iActivityHandler, SessionResponseData sessionResponseData) {
        checkAttributionI(iActivityHandler, sessionResponseData);
        iActivityHandler.launchSessionResponseTasks(sessionResponseData);
    }

    private Map<String, String> generateSendingParametersI() {
        HashMap hashMap = new HashMap();
        PackageBuilder.addString(hashMap, "sent_at", Util.dateFormatter.format(Long.valueOf(System.currentTimeMillis())));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAttributionI(long j7) {
        if (this.timer.getFireIn() > j7) {
            return;
        }
        if (j7 != 0) {
            this.logger.debug("Waiting to query attribution in %s seconds", Util.SecondsDisplayFormat.format(j7 / 1000.0d));
        }
        this.timer.startIn(j7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAttributionRequest() {
        this.scheduler.submit(new f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAttributionRequestI() {
        if (this.activityHandlerWeakRef.get().getActivityState().isGdprForgotten) {
            return;
        }
        if (this.paused) {
            this.logger.debug("Attribution handler is paused", new Object[0]);
            return;
        }
        ActivityPackage buildAndGetAttributionPackage = buildAndGetAttributionPackage();
        this.logger.verbose("%s", buildAndGetAttributionPackage.getExtendedString());
        this.activityPackageSender.sendActivityPackage(buildAndGetAttributionPackage, generateSendingParametersI(), this);
    }

    public void checkAttributionResponse(AttributionResponseData attributionResponseData) {
        this.scheduler.submit(new e(attributionResponseData));
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void checkSdkClickResponse(SdkClickResponseData sdkClickResponseData) {
        this.scheduler.submit(new d(sdkClickResponseData));
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void checkSessionResponse(SessionResponseData sessionResponseData) {
        this.scheduler.submit(new c(sessionResponseData));
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void getAttribution() {
        this.scheduler.submit(new b());
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void init(IActivityHandler iActivityHandler, boolean z8, IActivityPackageSender iActivityPackageSender) {
        this.activityHandlerWeakRef = new WeakReference<>(iActivityHandler);
        this.paused = !z8;
        this.activityPackageSender = iActivityPackageSender;
    }

    @Override // com.adjust.sdk.network.IActivityPackageSender.ResponseDataCallbackSubscriber
    public void onResponseDataCallback(ResponseData responseData) {
        this.scheduler.submit(new g(responseData));
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void pauseSending() {
        this.paused = true;
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void resumeSending() {
        this.paused = false;
    }

    @Override // com.adjust.sdk.IAttributionHandler
    public void teardown() {
        this.logger.verbose("AttributionHandler teardown", new Object[0]);
        TimerOnce timerOnce = this.timer;
        if (timerOnce != null) {
            timerOnce.teardown();
        }
        ThreadScheduler threadScheduler = this.scheduler;
        if (threadScheduler != null) {
            threadScheduler.teardown();
        }
        WeakReference<IActivityHandler> weakReference = this.activityHandlerWeakRef;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.timer = null;
        this.logger = null;
        this.scheduler = null;
        this.activityHandlerWeakRef = null;
    }
}
