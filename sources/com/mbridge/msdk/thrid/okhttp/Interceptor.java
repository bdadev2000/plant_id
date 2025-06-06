package com.mbridge.msdk.thrid.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public interface Interceptor {

    /* loaded from: classes3.dex */
    public interface Chain {
        Call call();

        int connectTimeoutMillis();

        Connection connection();

        Response proceed(Request request) throws IOException;

        int readTimeoutMillis();

        Request request();

        Chain withConnectTimeout(int i9, TimeUnit timeUnit);

        Chain withReadTimeout(int i9, TimeUnit timeUnit);

        Chain withWriteTimeout(int i9, TimeUnit timeUnit);

        int writeTimeoutMillis();
    }

    Response intercept(Chain chain) throws IOException;
}
