package com.facebook.share.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.compose.foundation.text.input.a;
import androidx.fragment.app.Fragment;
import com.facebook.FacebookCallback;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.DialogFeature;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.internal.FragmentWrapper;
import com.facebook.share.Sharer;
import com.facebook.share.internal.LegacyNativeDialogParameters;
import com.facebook.share.internal.MessageDialogFeature;
import com.facebook.share.internal.NativeDialogParameters;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class MessageDialog extends ShareDialog implements Sharer {
    private static final int DEFAULT_REQUEST_CODE = CallbackManagerImpl.RequestCodeOffset.Message.toRequestCode();
    private boolean shouldFailOnDataError;

    /* loaded from: classes.dex */
    public class NativeHandler extends FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler {
        private NativeHandler() {
            super(MessageDialog.this);
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public /* bridge */ /* synthetic */ boolean canShow(ShareContent<?, ?> shareContent, boolean z2) {
            return canShow2((ShareContent) shareContent, z2);
        }

        @Override // com.facebook.internal.FacebookDialogBase.ModeHandler
        public /* bridge */ /* synthetic */ AppCall createAppCall(ShareContent<?, ?> shareContent) {
            return createAppCall2((ShareContent) shareContent);
        }

        /* renamed from: canShow, reason: avoid collision after fix types in other method */
        public boolean canShow2(ShareContent shareContent, boolean z2) {
            return shareContent != null && MessageDialog.canShow((Class<? extends ShareContent<?, ?>>) shareContent.getClass());
        }

        /* renamed from: createAppCall, reason: avoid collision after fix types in other method */
        public AppCall createAppCall2(final ShareContent shareContent) {
            ShareContentValidation.validateForMessage(shareContent);
            final AppCall createBaseAppCall = MessageDialog.this.createBaseAppCall();
            final boolean shouldFailOnDataError = MessageDialog.this.getShouldFailOnDataError();
            MessageDialog.logDialogShare(MessageDialog.this.getActivityContext(), shareContent, createBaseAppCall);
            DialogPresenter.setupAppCallForNativeDialog(createBaseAppCall, new DialogPresenter.ParameterProvider() { // from class: com.facebook.share.widget.MessageDialog.NativeHandler.1
                @Override // com.facebook.internal.DialogPresenter.ParameterProvider
                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(createBaseAppCall.getCallId(), (ShareContent<?, ?>) shareContent, shouldFailOnDataError);
                }

                @Override // com.facebook.internal.DialogPresenter.ParameterProvider
                public Bundle getParameters() {
                    return NativeDialogParameters.create(createBaseAppCall.getCallId(), (ShareContent<?, ?>) shareContent, shouldFailOnDataError);
                }
            }, MessageDialog.getFeature(shareContent.getClass()));
            return createBaseAppCall;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MessageDialog(android.app.Activity r2) {
        /*
            r1 = this;
            int r0 = com.facebook.share.widget.MessageDialog.DEFAULT_REQUEST_CODE
            r1.<init>(r2, r0)
            r2 = 0
            r1.shouldFailOnDataError = r2
            com.facebook.share.internal.ShareInternalUtility.registerStaticShareCallback(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.widget.MessageDialog.<init>(android.app.Activity):void");
    }

    public static boolean canShow(Class<? extends ShareContent<?, ?>> cls) {
        DialogFeature feature = getFeature(cls);
        return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DialogFeature getFeature(Class<? extends ShareContent> cls) {
        if (ShareLinkContent.class.isAssignableFrom(cls)) {
            return MessageDialogFeature.MESSAGE_DIALOG;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logDialogShare(Context context, ShareContent shareContent, AppCall appCall) {
        DialogFeature feature = getFeature(shareContent.getClass());
        String str = feature == MessageDialogFeature.MESSAGE_DIALOG ? "status" : feature == MessageDialogFeature.MESSENGER_GENERIC_TEMPLATE ? AnalyticsEvents.PARAMETER_SHARE_MESSENGER_GENERIC_TEMPLATE : feature == MessageDialogFeature.MESSENGER_MEDIA_TEMPLATE ? AnalyticsEvents.PARAMETER_SHARE_MESSENGER_MEDIA_TEMPLATE : "unknown";
        InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(context);
        Bundle c2 = a.c(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_TYPE, str);
        c2.putString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_UUID, appCall.getCallId().toString());
        c2.putString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PAGE_ID, shareContent.getPageId());
        internalAppEventsLogger.logEventImplicitly(AnalyticsEvents.EVENT_SHARE_MESSENGER_DIALOG_SHOW, c2);
    }

    public static void show(Activity activity, ShareContent shareContent) {
        new MessageDialog(activity).show(shareContent);
    }

    @Override // com.facebook.share.widget.ShareDialog, com.facebook.internal.FacebookDialogBase
    public AppCall createBaseAppCall() {
        return new AppCall(getRequestCode());
    }

    @Override // com.facebook.share.widget.ShareDialog, com.facebook.internal.FacebookDialogBase
    public List<FacebookDialogBase<ShareContent<?, ?>, Sharer.Result>.ModeHandler> getOrderedModeHandlers() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new NativeHandler());
        return arrayList;
    }

    @Override // com.facebook.share.widget.ShareDialog, com.facebook.share.Sharer
    public boolean getShouldFailOnDataError() {
        return this.shouldFailOnDataError;
    }

    @Override // com.facebook.share.widget.ShareDialog, com.facebook.internal.FacebookDialogBase
    public void registerCallbackImpl(CallbackManagerImpl callbackManagerImpl, FacebookCallback<Sharer.Result> facebookCallback) {
        ShareInternalUtility.registerSharerCallback(getRequestCode(), callbackManagerImpl, facebookCallback);
    }

    @Override // com.facebook.share.widget.ShareDialog, com.facebook.share.Sharer
    public void setShouldFailOnDataError(boolean z2) {
        this.shouldFailOnDataError = z2;
    }

    public static void show(Fragment fragment, ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }

    public MessageDialog(Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    public static void show(android.app.Fragment fragment, ShareContent shareContent) {
        show(new FragmentWrapper(fragment), shareContent);
    }

    public MessageDialog(android.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }

    private static void show(FragmentWrapper fragmentWrapper, ShareContent shareContent) {
        new MessageDialog(fragmentWrapper).show(shareContent);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private MessageDialog(com.facebook.internal.FragmentWrapper r2) {
        /*
            r1 = this;
            int r0 = com.facebook.share.widget.MessageDialog.DEFAULT_REQUEST_CODE
            r1.<init>(r2, r0)
            r2 = 0
            r1.shouldFailOnDataError = r2
            com.facebook.share.internal.ShareInternalUtility.registerStaticShareCallback(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.widget.MessageDialog.<init>(com.facebook.internal.FragmentWrapper):void");
    }

    public MessageDialog(Activity activity, int i2) {
        super(activity, i2);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(i2);
    }

    public MessageDialog(Fragment fragment, int i2) {
        this(new FragmentWrapper(fragment), i2);
    }

    public MessageDialog(android.app.Fragment fragment, int i2) {
        this(new FragmentWrapper(fragment), i2);
    }

    private MessageDialog(FragmentWrapper fragmentWrapper, int i2) {
        super(fragmentWrapper, i2);
        this.shouldFailOnDataError = false;
        ShareInternalUtility.registerStaticShareCallback(i2);
    }
}
