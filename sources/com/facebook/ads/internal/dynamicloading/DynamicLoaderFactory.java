package com.facebook.ads.internal.dynamicloading;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.BuildConfig;
import com.facebook.ads.internal.settings.MultithreadedBundleWrapper;
import com.facebook.ads.internal.util.common.Preconditions;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import x0.AbstractC2914a;

@Keep
/* loaded from: classes.dex */
public class DynamicLoaderFactory {
    private static final String AUDIENCE_NETWORK_CODE_PATH = "audience_network";
    public static final String AUDIENCE_NETWORK_DEX = "audience_network.dex";
    private static final String CODE_CACHE_DIR = "code_cache";
    static final String DEX_LOADING_ERROR_MESSAGE = "Can't load Audience Network Dex. Please, check that audience_network.dex is inside of assets folder.";
    private static final int DEX_LOAD_RETRY_COUNT = 3;
    private static final int DEX_LOAD_RETRY_DELAY_MS = 200;
    private static final String DYNAMIC_LOADING_BUILD_TYPE = "releaseDL";
    private static final String OPTIMIZED_DEX_PATH = "optimized";
    private static boolean sFallbackMode;
    public static final boolean LOAD_FROM_ASSETS = "releaseDL".equals(BuildConfig.BUILD_TYPE);
    private static final AtomicReference<DynamicLoader> sDynamicLoader = new AtomicReference<>();
    private static final AtomicBoolean sInitializing = new AtomicBoolean();
    private static boolean sUseLegacyClassLoader = true;

    /* loaded from: classes.dex */
    public interface RemoteClassLoaderFactory {
        @Nullable
        ClassLoader create(Context context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AudienceNetworkAds.InitResult createErrorInitResult(Throwable th) {
        return new d(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String createErrorMessage(Throwable th) {
        return "Can't load Audience Network Dex. Please, check that audience_network.dex is inside of assets folder.\n" + stackTraceToString(th);
    }

    @SuppressLint({"CatchGeneralException"})
    @TargetApi(26)
    private static ClassLoader createInMemoryClassLoader(Context context) throws IOException {
        try {
            ClassLoader create = ((RemoteClassLoaderFactory) context.getClassLoader().loadClass("com.facebook.ads.internal.dynamicloading.RemoteClassLoaderFactoryImpl").getDeclaredConstructor(null).newInstance(null)).create(context);
            if (create != null) {
                return create;
            }
        } catch (Exception unused) {
        }
        InputStream open = context.getAssets().open(AUDIENCE_NETWORK_DEX);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                open.close();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                ByteBuffer wrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
                com.bykv.vk.openvk.component.video.zp.jU.a.n();
                return com.bykv.vk.openvk.component.video.zp.jU.a.j(wrap, DynamicLoaderFactory.class.getClassLoader());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doCallInitialize(Context context, @Nullable DynamicLoader dynamicLoader, @Nullable Throwable th, boolean z8, @Nullable MultithreadedBundleWrapper multithreadedBundleWrapper, @Nullable AudienceNetworkAds.InitListener initListener) {
        if (th != null) {
            if (initListener != null) {
                new Handler(Looper.getMainLooper()).postDelayed(new c(initListener, th), 100L);
                return;
            } else {
                Log.e(AudienceNetworkAds.TAG, DEX_LOADING_ERROR_MESSAGE, th);
                return;
            }
        }
        if (dynamicLoader != null) {
            if (z8) {
                dynamicLoader.createAudienceNetworkAdsApi().onContentProviderCreated(context);
            } else {
                dynamicLoader.createAudienceNetworkAdsApi().initialize(context, multithreadedBundleWrapper, initListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DynamicLoader doMakeLoader(Context context, boolean z8) throws Exception {
        DynamicLoader dynamicLoader;
        AtomicReference<DynamicLoader> atomicReference = sDynamicLoader;
        DynamicLoader dynamicLoader2 = atomicReference.get();
        if (dynamicLoader2 == null) {
            if (!LOAD_FROM_ASSETS) {
                dynamicLoader = (DynamicLoader) Class.forName("com.facebook.ads.internal.dynamicloading.DynamicLoaderImpl").newInstance();
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                dynamicLoader = (DynamicLoader) makeAdsSdkClassLoader(context.getApplicationContext()).loadClass("com.facebook.ads.internal.dynamicloading.DynamicLoaderImpl").newInstance();
                Log.d(AudienceNetworkAds.TAG, "SDK dex loading time: " + (System.currentTimeMillis() - currentTimeMillis));
            }
            if (z8) {
                dynamicLoader.maybeInitInternally(context);
            }
            atomicReference.set(dynamicLoader);
            return dynamicLoader;
        }
        return dynamicLoader2;
    }

    @Nullable
    @SuppressLint({"PrivateApi", "CatchGeneralException"})
    private static Context getApplicationContextViaReflection() {
        try {
            return (Context) Class.forName("android.app.ActivityThread").getMethod("currentApplication", null).invoke(null, null);
        } catch (Throwable th) {
            Log.e(AudienceNetworkAds.TAG, "Failed to fetch Context from  ActivityThread. Audience Network SDK won't work unless you call AudienceNetworkAds.buildInitSettings().withListener(InitListener).initialize().", th);
            return null;
        }
    }

    private static File getCacheCodeDirLegacy(Context context, File file) throws IOException {
        File file2 = new File(file, CODE_CACHE_DIR);
        try {
            mkdirChecked(file2);
            return file2;
        } catch (IOException unused) {
            File dir = context.getDir(CODE_CACHE_DIR, 0);
            mkdirChecked(dir);
            return dir;
        }
    }

    private static File getCodeCacheDir(Context context, File file) throws IOException {
        return context.getCodeCacheDir();
    }

    @Nullable
    public static DynamicLoader getDynamicLoader() {
        return sDynamicLoader.get();
    }

    private static File getSecondaryDir(File file) throws IOException {
        File file2 = new File(file, AUDIENCE_NETWORK_CODE_PATH);
        mkdirChecked(file2);
        return file2;
    }

    public static void initialize(Context context, @Nullable MultithreadedBundleWrapper multithreadedBundleWrapper, @Nullable AudienceNetworkAds.InitListener initListener, boolean z8) {
        if (z8 || !sInitializing.getAndSet(true)) {
            new Thread(new b(context, multithreadedBundleWrapper, initListener, z8)).start();
        }
    }

    public static synchronized boolean isFallbackMode() {
        boolean z8;
        synchronized (DynamicLoaderFactory.class) {
            z8 = sFallbackMode;
        }
        return z8;
    }

    private static ClassLoader makeAdsSdkClassLoader(Context context) throws Exception {
        if (Build.VERSION.SDK_INT >= 30) {
            return createInMemoryClassLoader(context);
        }
        if (sUseLegacyClassLoader) {
            return makeLegacyAdsSdkClassLoader(context);
        }
        File secondaryDir = getSecondaryDir(getCodeCacheDir(context, new File(context.getApplicationInfo().dataDir)));
        StringBuilder sb = new StringBuilder();
        sb.append(secondaryDir.getPath());
        String h6 = AbstractC2914a.h(sb, File.separator, AUDIENCE_NETWORK_DEX);
        InputStream open = context.getAssets().open(AUDIENCE_NETWORK_DEX);
        FileOutputStream fileOutputStream = new FileOutputStream(h6);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                open.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(secondaryDir.getPath());
                File file = new File(AbstractC2914a.h(sb2, File.separator, OPTIMIZED_DEX_PATH));
                mkdirChecked(file);
                return new DexClassLoader(h6, file.getPath(), null, context.getClassLoader());
            }
        }
    }

    private static DexClassLoader makeLegacyAdsSdkClassLoader(Context context) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getPath());
        String h6 = AbstractC2914a.h(sb, File.separator, AUDIENCE_NETWORK_DEX);
        InputStream open = context.getAssets().open(AUDIENCE_NETWORK_DEX);
        FileOutputStream fileOutputStream = new FileOutputStream(h6);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                open.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                return new DexClassLoader(h6, context.getDir(OPTIMIZED_DEX_PATH, 0).getPath(), null, DynamicLoaderFactory.class.getClassLoader());
            }
        }
    }

    public static synchronized DynamicLoader makeLoader(Context context) {
        DynamicLoader makeLoader;
        synchronized (DynamicLoaderFactory.class) {
            makeLoader = makeLoader(context, true);
        }
        return makeLoader;
    }

    @SuppressLint({"CatchGeneralException"})
    public static synchronized DynamicLoader makeLoaderUnsafe() {
        synchronized (DynamicLoaderFactory.class) {
            AtomicReference<DynamicLoader> atomicReference = sDynamicLoader;
            if (atomicReference.get() == null) {
                Context applicationContextViaReflection = getApplicationContextViaReflection();
                if (applicationContextViaReflection != null) {
                    return makeLoader(applicationContextViaReflection, true);
                }
                throw new RuntimeException("You must call AudienceNetworkAds.buildInitSettings(Context).initialize() before you can use Audience Network SDK.");
            }
            return atomicReference.get();
        }
    }

    private static void mkdirChecked(File file) throws IOException {
        String str;
        file.mkdir();
        if (!file.isDirectory()) {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                str = "Failed to create dir " + file.getPath() + ". Parent file is null.";
            } else {
                str = "Failed to create dir " + file.getPath() + ". parent file is a dir " + parentFile.isDirectory() + ", a file " + parentFile.isFile() + ", exists " + parentFile.exists() + ", readable " + parentFile.canRead() + ", writable " + parentFile.canWrite();
            }
            Log.e(AudienceNetworkAds.TAG, str);
            throw new IOException("Failed to create directory " + file.getPath() + ", detailed message: " + str);
        }
    }

    public static synchronized void setFallbackMode(boolean z8) {
        synchronized (DynamicLoaderFactory.class) {
            try {
                if (z8) {
                    sDynamicLoader.set(DynamicLoaderFallback.makeFallbackLoader());
                    sFallbackMode = true;
                } else {
                    sDynamicLoader.set(null);
                    sFallbackMode = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void setUseLegacyClassLoader(boolean z8) {
        sUseLegacyClassLoader = z8;
    }

    private static String stackTraceToString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    @SuppressLint({"CatchGeneralException"})
    public static synchronized DynamicLoader makeLoader(Context context, boolean z8) {
        DynamicLoader doMakeLoader;
        synchronized (DynamicLoaderFactory.class) {
            Preconditions.checkNotNull(context, "Context can not be null.");
            try {
                doMakeLoader = doMakeLoader(context, z8);
            } catch (Throwable th) {
                Log.e(AudienceNetworkAds.TAG, DEX_LOADING_ERROR_MESSAGE, th);
                DexLoadErrorReporter.reportDexLoadingIssue(context, createErrorMessage(th), 0.1d);
                DynamicLoader makeFallbackLoader = DynamicLoaderFallback.makeFallbackLoader();
                sDynamicLoader.set(makeFallbackLoader);
                sFallbackMode = true;
                return makeFallbackLoader;
            }
        }
        return doMakeLoader;
    }
}
