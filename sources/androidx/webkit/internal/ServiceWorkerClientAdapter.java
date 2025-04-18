package androidx.webkit.internal;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import org.chromium.support_lib_boundary.ServiceWorkerClientBoundaryInterface;
import org.chromium.support_lib_boundary.util.Features;

/* loaded from: classes.dex */
public class ServiceWorkerClientAdapter implements ServiceWorkerClientBoundaryInterface {
    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public final String[] getSupportedFeatures() {
        return new String[]{Features.SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST};
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerClientBoundaryInterface
    public final WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
        throw null;
    }
}
