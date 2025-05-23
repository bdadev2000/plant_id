package com.google.android.gms.ads.internal.util;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.ads.zzbeg;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzbfm;
import com.google.android.gms.internal.ads.zzbgt;
import com.google.android.gms.internal.ads.zzbyf;
import com.google.android.gms.internal.ads.zzcgu;
import com.google.android.gms.internal.ads.zzcig;
import com.google.android.gms.internal.ads.zzdsg;
import com.google.android.gms.internal.ads.zzfgt;
import com.google.android.gms.internal.ads.zzfgw;
import com.google.android.gms.internal.ads.zzfuv;
import com.google.android.gms.internal.ads.zzfxr;
import com.google.android.gms.internal.ads.zzfyt;
import com.google.android.gms.internal.ads.zzgft;
import com.google.android.gms.internal.ads.zzhlh;
import com.mbridge.msdk.foundation.download.Command;
import com.mbridge.msdk.foundation.entity.o;
import f4.c;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import u.C2738g;
import u.C2739h;

/* loaded from: classes2.dex */
public final class zzt {
    public static final zzfuv zza = new zzf(Looper.getMainLooper());
    private String zzh;
    private volatile String zzi;
    private final AtomicReference zzb = new AtomicReference(null);
    private final AtomicReference zzc = new AtomicReference(null);
    private final AtomicReference zzd = new AtomicReference(new Bundle());
    private final AtomicBoolean zze = new AtomicBoolean();
    private boolean zzf = true;
    private final Object zzg = new Object();
    private boolean zzj = false;
    private boolean zzk = false;
    private final Executor zzl = Executors.newSingleThreadExecutor();

    public static final boolean zzA(Context context, String str) {
        Context zza2 = zzbyf.zza(context);
        if (Wrappers.packageManager(zza2).checkPermission(str, zza2.getPackageName()) == 0) {
            return true;
        }
        return false;
    }

    public static final boolean zzB(Context context) {
        try {
            return DeviceProperties.isBstar(context);
        } catch (NoSuchMethodError unused) {
            return false;
        }
    }

    public static final boolean zzC(String str) {
        if (!com.google.android.gms.ads.internal.util.client.zzl.zzk()) {
            return false;
        }
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzeV)).booleanValue()) {
            return false;
        }
        String str2 = (String) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzeX);
        if (!str2.isEmpty()) {
            for (String str3 : str2.split(";")) {
                if (str3.equals(str)) {
                    return false;
                }
            }
        }
        String str4 = (String) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzeW);
        if (str4.isEmpty()) {
            return true;
        }
        for (String str5 : str4.split(";")) {
            if (str5.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean zzD(Context context) {
        KeyguardManager zzX;
        if (context == null || (zzX = zzX(context)) == null || !zzX.isKeyguardLocked()) {
            return false;
        }
        return true;
    }

    public static final boolean zzE(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.util.client.zzm.zzh("Error loading class.", th);
            com.google.android.gms.ads.internal.zzu.zzo().zzw(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static final boolean zzF() {
        int myUid = Process.myUid();
        if (myUid != 0 && myUid != 1000) {
            return false;
        }
        return true;
    }

    public static final boolean zzG(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        PowerManager powerManager;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && (powerManager = (PowerManager) context.getSystemService("power")) != null) {
                        if (powerManager.isScreenOn()) {
                            return false;
                        }
                        return true;
                    }
                    return true;
                }
            }
            return true;
        } catch (Throwable unused) {
        }
        return false;
    }

    public static final boolean zzH(Context context) {
        try {
            Bundle zzY = zzY(context);
            String string = zzY.getString("com.google.android.gms.ads.INTEGRATION_MANAGER");
            if (TextUtils.isEmpty(zzZ(zzY))) {
                if (!TextUtils.isEmpty(string)) {
                    return true;
                }
            }
        } catch (RemoteException unused) {
        }
        return false;
    }

    public static final boolean zzI(Context context) {
        Window window;
        if ((context instanceof Activity) && (window = ((Activity) context).getWindow()) != null && window.getDecorView() != null) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            window.getDecorView().getGlobalVisibleRect(rect, null);
            window.getDecorView().getWindowVisibleDisplayFrame(rect2);
            if (rect.bottom != 0 && rect2.bottom != 0 && rect.top == rect2.top) {
                return true;
            }
        }
        return false;
    }

    public static final void zzJ(View view, int i9, MotionEvent motionEvent) {
        String str;
        int i10;
        int i11;
        int i12;
        String str2;
        zzfgt zzD;
        zzfgw zzR;
        View view2 = view;
        int[] iArr = new int[2];
        Rect rect = new Rect();
        try {
            String packageName = view.getContext().getPackageName();
            if (view2 instanceof zzdsg) {
                view2 = ((zzdsg) view2).getChildAt(0);
            }
            if (!(view2 instanceof com.google.android.gms.ads.formats.zzj) && !(view2 instanceof NativeAdView)) {
                str = "UNKNOWN";
                i10 = 0;
            } else {
                str = "NATIVE";
                i10 = 1;
            }
            if (view2.getLocalVisibleRect(rect)) {
                i12 = rect.width();
                i11 = rect.height();
            } else {
                i11 = 0;
                i12 = 0;
            }
            com.google.android.gms.ads.internal.zzu.zzp();
            long zzw = zzw(view2);
            view2.getLocationOnScreen(iArr);
            int i13 = iArr[0];
            int i14 = iArr[1];
            String str3 = "none";
            if (!(view2 instanceof zzcig) || (zzR = ((zzcig) view2).zzR()) == null) {
                str2 = "none";
            } else {
                str2 = zzR.zzb;
                view2.setContentDescription(str2 + ":" + view2.hashCode());
            }
            if ((view2 instanceof zzcgu) && (zzD = ((zzcgu) view2).zzD()) != null) {
                str = zzfgt.zza(zzD.zzb);
                i10 = zzD.zzf;
                str3 = zzD.zzF;
            }
            Locale locale = Locale.US;
            com.google.android.gms.ads.internal.util.client.zzm.zzi("<Ad hashCode=" + view2.hashCode() + ", package=" + packageName + ", adNetCls=" + str3 + ", gwsQueryId=" + str2 + ", format=" + str + ", impType=" + i10 + ", class=" + view2.getClass().getName() + ", x=" + i13 + ", y=" + i14 + ", width=" + view2.getWidth() + ", height=" + view2.getHeight() + ", vWidth=" + i12 + ", vHeight=" + i11 + ", alpha=" + zzw + ", state=" + Integer.toString(i9, 2) + ">");
        } catch (Exception e4) {
            com.google.android.gms.ads.internal.util.client.zzm.zzh("Failure getting view location.", e4);
        }
    }

    public static final AlertDialog.Builder zzK(Context context) {
        com.google.android.gms.ads.internal.zzu.zzq();
        return new AlertDialog.Builder(context, R.style.Theme.Material.Dialog.Alert);
    }

    public static final void zzL(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            new zzca(context, str, (String) it.next()).zzb();
        }
    }

    public static final void zzM(Context context, Throwable th) {
        if (context != null) {
            try {
                if (((Boolean) zzbgt.zzb.zze()).booleanValue()) {
                    CrashUtils.addDynamiteErrorToDropBox(context, th);
                }
            } catch (IllegalStateException unused) {
            }
        }
    }

    public static final String zzN(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(FragmentTransaction.TRANSIT_EXIT_MASK);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read != -1) {
                sb.append(cArr, 0, read);
            } else {
                return sb.toString();
            }
        }
    }

    public static final int zzO(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e4) {
            com.google.android.gms.ads.internal.util.client.zzm.zzj("Could not parse value:".concat(e4.toString()));
            return 0;
        }
    }

    @Nullable
    public static final Map zzP(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : uri.getQueryParameterNames()) {
            if (!TextUtils.isEmpty(str)) {
                hashMap.put(str, uri.getQueryParameter(str));
            }
        }
        return hashMap;
    }

    public static final int[] zzQ(Activity activity) {
        View findViewById;
        Window window = activity.getWindow();
        if (window != null && (findViewById = window.findViewById(R.id.content)) != null) {
            return new int[]{findViewById.getWidth(), findViewById.getHeight()};
        }
        return zzu();
    }

    public static final int[] zzR(Activity activity) {
        int[] zzu;
        View findViewById;
        Window window = activity.getWindow();
        if (window != null && (findViewById = window.findViewById(R.id.content)) != null) {
            zzu = new int[]{findViewById.getTop(), findViewById.getBottom()};
        } else {
            zzu = zzu();
        }
        return new int[]{com.google.android.gms.ads.internal.client.zzay.zzb().zzb(activity, zzu[0]), com.google.android.gms.ads.internal.client.zzay.zzb().zzb(activity, zzu[1])};
    }

    public static final boolean zzS(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z8;
        if (com.google.android.gms.ads.internal.zzu.zzp().zzf || keyguardManager == null || !keyguardManager.inKeyguardRestrictedInputMode() || zzn(view)) {
            z8 = true;
        } else {
            z8 = false;
        }
        long zzw = zzw(view);
        if (view.getVisibility() == 0 && view.isShown() && ((powerManager == null || powerManager.isScreenOn()) && z8)) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzbm)).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect())) {
                if (!((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzkD)).booleanValue()) {
                    return true;
                }
                if (zzw >= ((Integer) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzkF)).intValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final void zzT(Context context, Intent intent) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzkX)).booleanValue()) {
            try {
                zzab(context, intent);
                return;
            } catch (SecurityException e4) {
                com.google.android.gms.ads.internal.util.client.zzm.zzk("", e4);
                com.google.android.gms.ads.internal.zzu.zzo().zzw(e4, "AdUtil.startActivityWithUnknownContext");
                return;
            }
        }
        zzab(context, intent);
    }

    public static final void zzU(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            zzo(context, intent);
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            com.google.android.gms.ads.internal.util.client.zzm.zze("Opening " + uri.toString() + " in a new browser.");
        } catch (ActivityNotFoundException e4) {
            com.google.android.gms.ads.internal.util.client.zzm.zzh("No browser is found.", e4);
        }
    }

    public static final int[] zzV(Activity activity) {
        int[] zzQ = zzQ(activity);
        return new int[]{com.google.android.gms.ads.internal.client.zzay.zzb().zzb(activity, zzQ[0]), com.google.android.gms.ads.internal.client.zzay.zzb().zzb(activity, zzQ[1])};
    }

    public static final boolean zzW(View view, Context context) {
        PowerManager powerManager;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            powerManager = (PowerManager) applicationContext.getSystemService("power");
        } else {
            powerManager = null;
        }
        return zzS(view, powerManager, zzX(context));
    }

    @Nullable
    private static KeyguardManager zzX(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService != null && (systemService instanceof KeyguardManager)) {
            return (KeyguardManager) systemService;
        }
        return null;
    }

    @Nullable
    private static Bundle zzY(Context context) throws RemoteException {
        try {
            return Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException | NullPointerException e4) {
            zze.zzb("Error getting metadata", e4);
            return null;
        }
    }

    private static String zzZ(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
            if (!TextUtils.isEmpty(string)) {
                if (string.matches("^ca-app-pub-[0-9]{16}~[0-9]{10}$") || string.matches("^/\\d+~.+$")) {
                    return string;
                }
                return "";
            }
            return "";
        }
        return "";
    }

    public static int zza(int i9) {
        if (i9 >= 5000) {
            return i9;
        }
        if (i9 > 0) {
            com.google.android.gms.ads.internal.util.client.zzm.zzj("HTTP timeout too low: " + i9 + " milliseconds. Reverting to default timeout: 60000 milliseconds.");
            return 60000;
        }
        return 60000;
    }

    private static boolean zzaa(String str, AtomicReference atomicReference, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Pattern pattern = (Pattern) atomicReference.get();
            if (pattern == null || !str2.equals(pattern.pattern())) {
                pattern = Pattern.compile(str2);
                atomicReference.set(pattern);
            }
            return pattern.matcher(str).matches();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }

    private static final void zzab(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    private static final String zzac(final Context context, String str) {
        String str2;
        String str3;
        if (str != null) {
            try {
                zzck zza2 = zzck.zza();
                if (TextUtils.isEmpty(zza2.zza)) {
                    if (ClientLibraryUtils.isPackageSide()) {
                        str3 = (String) zzch.zza(context, new Callable() { // from class: com.google.android.gms.ads.internal.util.zzci
                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                Context context2 = context;
                                SharedPreferences sharedPreferences = context2.getSharedPreferences("admob_user_agent", 0);
                                String string = sharedPreferences.getString("user_agent", "");
                                if (TextUtils.isEmpty(string)) {
                                    zze.zza("User agent is not initialized on Google Play Services. Initializing.");
                                    String defaultUserAgent = WebSettings.getDefaultUserAgent(context2);
                                    SharedPreferencesUtils.publishWorldReadableSharedPreferences(context2, sharedPreferences.edit().putString("user_agent", defaultUserAgent), "admob_user_agent");
                                    return defaultUserAgent;
                                }
                                zze.zza("User agent is already initialized on Google Play Services.");
                                return string;
                            }
                        });
                    } else {
                        final Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                        str3 = (String) zzch.zza(context, new Callable() { // from class: com.google.android.gms.ads.internal.util.zzcj
                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                SharedPreferences sharedPreferences;
                                Context context2 = remoteContext;
                                Context context3 = context;
                                boolean z8 = false;
                                if (context2 != null) {
                                    zze.zza("Attempting to read user agent from Google Play Services.");
                                    sharedPreferences = context2.getSharedPreferences("admob_user_agent", 0);
                                } else {
                                    zze.zza("Attempting to read user agent from local cache.");
                                    sharedPreferences = context3.getSharedPreferences("admob_user_agent", 0);
                                    z8 = true;
                                }
                                String string = sharedPreferences.getString("user_agent", "");
                                if (TextUtils.isEmpty(string)) {
                                    zze.zza("Reading user agent from WebSettings");
                                    string = WebSettings.getDefaultUserAgent(context3);
                                    if (z8) {
                                        sharedPreferences.edit().putString("user_agent", string).apply();
                                        zze.zza("Persisting user agent.");
                                    }
                                }
                                return string;
                            }
                        });
                    }
                    zza2.zza = str3;
                }
                str2 = zza2.zza;
            } catch (Exception unused) {
                str2 = null;
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = WebSettings.getDefaultUserAgent(context);
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = zzq();
            }
            String k6 = o.k(str2, " (Mobile; ", str);
            try {
                if (Wrappers.packageManager(context).isCallerInstantApp()) {
                    k6 = k6 + ";aia";
                }
            } catch (Exception e4) {
                com.google.android.gms.ads.internal.zzu.zzo().zzw(e4, "AdUtil.getUserAgent");
            }
            return k6.concat(")");
        }
        return zzq();
    }

    public static List zzd() {
        zzbeg zzbegVar = zzbep.zza;
        List zzb = com.google.android.gms.ads.internal.client.zzba.zza().zzb();
        ArrayList arrayList = new ArrayList();
        Iterator it = zzb.iterator();
        while (it.hasNext()) {
            Iterator it2 = zzfyt.zzc(zzfxr.zzc(',')).zzd((String) it.next()).iterator();
            while (it2.hasNext()) {
                try {
                    arrayList.add(Long.valueOf((String) it2.next()));
                } catch (NumberFormatException unused) {
                    zze.zza("Experiment ID is not a number");
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0016 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean zzn(android.view.View r2) {
        /*
            android.view.View r2 = r2.getRootView()
            r0 = 0
            if (r2 != 0) goto L9
        L7:
            r2 = r0
            goto L13
        L9:
            android.content.Context r2 = r2.getContext()
            boolean r1 = r2 instanceof android.app.Activity
            if (r1 == 0) goto L7
            android.app.Activity r2 = (android.app.Activity) r2
        L13:
            r1 = 0
            if (r2 != 0) goto L17
            return r1
        L17:
            android.view.Window r2 = r2.getWindow()
            if (r2 != 0) goto L1e
            goto L22
        L1e:
            android.view.WindowManager$LayoutParams r0 = r2.getAttributes()
        L22:
            if (r0 == 0) goto L2d
            int r2 = r0.flags
            r0 = 524288(0x80000, float:7.34684E-40)
            r2 = r2 & r0
            if (r2 == 0) goto L2d
            r2 = 1
            return r2
        L2d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.zzt.zzn(android.view.View):boolean");
    }

    public static final void zzo(Context context, Intent intent) {
        Bundle bundle;
        if (intent == null) {
            return;
        }
        if (intent.getExtras() != null) {
            bundle = intent.getExtras();
        } else {
            bundle = new Bundle();
        }
        bundle.putBinder("android.support.customtabs.extra.SESSION", null);
        bundle.putString("com.android.browser.application_id", context.getPackageName());
        intent.putExtras(bundle);
    }

    public static final String zzp(Context context) throws RemoteException {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        return zzZ(zzY(context));
    }

    public static final String zzq() {
        StringBuilder sb = new StringBuilder(NotificationCompat.FLAG_LOCAL_ONLY);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        String str = Build.VERSION.RELEASE;
        if (str != null) {
            sb.append(" ");
            sb.append(str);
        }
        sb.append("; ");
        sb.append(Locale.getDefault());
        String str2 = Build.DEVICE;
        if (str2 != null) {
            sb.append("; ");
            sb.append(str2);
            String str3 = Build.DISPLAY;
            if (str3 != null) {
                sb.append(" Build/");
                sb.append(str3);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    public static final String zzr() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        return o.k(str, " ", str2);
    }

    @Nullable
    public static final Integer zzs(Context context) {
        Object systemService = context.getSystemService("display");
        if (systemService instanceof DisplayManager) {
            return Integer.valueOf(((DisplayManager) systemService).getDisplays().length);
        }
        return null;
    }

    public static final DisplayMetrics zzt(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static final int[] zzu() {
        return new int[]{0, 0};
    }

    public static final Map zzv(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                HashSet hashSet = new HashSet();
                JSONArray optJSONArray = jSONObject.optJSONArray(next);
                if (optJSONArray != null) {
                    for (int i9 = 0; i9 < optJSONArray.length(); i9++) {
                        String optString = optJSONArray.optString(i9);
                        if (optString != null) {
                            hashSet.add(optString);
                        }
                    }
                    hashMap.put(next, hashSet);
                }
            }
            return hashMap;
        } catch (JSONException e4) {
            com.google.android.gms.ads.internal.zzu.zzo().zzw(e4, "AdUtil.getMapOfFileNamesToKeysFromJsonString");
            return hashMap;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v6, types: [android.view.ViewParent] */
    public static final long zzw(View view) {
        float f9;
        float f10 = Float.MAX_VALUE;
        do {
            f9 = 0.0f;
            if (!(view instanceof View)) {
                break;
            }
            View view2 = (View) view;
            f10 = Math.min(f10, view2.getAlpha());
            view = view2.getParent();
        } while (f10 > 0.0f);
        if (f10 >= 0.0f) {
            f9 = f10;
        }
        return Math.round(f9 * 100.0f);
    }

    @Nullable
    public static final WebResourceResponse zzx(Context context, String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(Command.HTTP_HEADER_USER_AGENT, com.google.android.gms.ads.internal.zzu.zzp().zzc(context, str));
            hashMap.put("Cache-Control", "max-stale=3600");
            String str3 = (String) new zzbq(context).zzb(0, str2, hashMap, null).get(60L, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e4) {
            com.google.android.gms.ads.internal.util.client.zzm.zzk("Could not fetch MRAID JS.", e4);
        }
        return null;
    }

    public static final String zzy() {
        Resources zze = com.google.android.gms.ads.internal.zzu.zzo().zze();
        if (zze != null) {
            return zze.getString(com.google.android.gms.ads.impl.R.string.f13887s7);
        }
        return "Test Ad";
    }

    @Nullable
    public static final zzbt zzz(Context context) {
        try {
            Object newInstance = context.getClassLoader().loadClass("com.google.android.gms.ads.internal.util.WorkManagerUtil").getDeclaredConstructor(null).newInstance(null);
            if (!(newInstance instanceof IBinder)) {
                com.google.android.gms.ads.internal.util.client.zzm.zzg("Instantiated WorkManagerUtil not instance of IBinder.");
                return null;
            }
            IBinder iBinder = (IBinder) newInstance;
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.util.IWorkManagerUtil");
            if (queryLocalInterface instanceof zzbt) {
                return (zzbt) queryLocalInterface;
            }
            return new zzbr(iBinder);
        } catch (Exception e4) {
            com.google.android.gms.ads.internal.zzu.zzo().zzw(e4, "Failed to instantiate WorkManagerUtil");
            return null;
        }
    }

    public final c zzb(final Uri uri) {
        return zzgft.zzj(new Callable() { // from class: com.google.android.gms.ads.internal.util.zzn
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzfuv zzfuvVar = zzt.zza;
                com.google.android.gms.ads.internal.zzu.zzp();
                return zzt.zzP(uri);
            }
        }, this.zzl);
    }

    public final String zzc(Context context, String str) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzlk)).booleanValue()) {
            if (this.zzi != null) {
                return this.zzi;
            }
            this.zzi = zzac(context, str);
            return this.zzi;
        }
        synchronized (this.zzg) {
            try {
                String str2 = this.zzh;
                if (str2 != null) {
                    return str2;
                }
                String zzac = zzac(context, str);
                this.zzh = zzac;
                return zzac;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzf(Context context, String str, boolean z8, HttpURLConnection httpURLConnection, boolean z9, int i9) {
        int zza2 = zza(i9);
        com.google.android.gms.ads.internal.util.client.zzm.zzi("HTTP timeout: " + zza2 + " milliseconds.");
        httpURLConnection.setConnectTimeout(zza2);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(zza2);
        if (TextUtils.isEmpty(httpURLConnection.getRequestProperty(Command.HTTP_HEADER_USER_AGENT))) {
            httpURLConnection.setRequestProperty(Command.HTTP_HEADER_USER_AGENT, zzc(context, str));
        }
        httpURLConnection.setUseCaches(false);
    }

    public final /* synthetic */ void zzg(Context context, String str, SharedPreferences sharedPreferences, String str2) {
        this.zzd.set(zzad.zzb(context, str));
    }

    public final void zzh(final Context context, @Nullable final String str, String str2, Bundle bundle, boolean z8) {
        com.google.android.gms.ads.internal.zzu.zzp();
        bundle.putString("device", zzr());
        zzbeg zzbegVar = zzbep.zza;
        bundle.putString("eids", TextUtils.join(",", com.google.android.gms.ads.internal.client.zzba.zza().zza()));
        if (bundle.isEmpty()) {
            com.google.android.gms.ads.internal.util.client.zzm.zze("Empty or null bundle.");
        } else {
            final String str3 = (String) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzkA);
            if (!this.zze.getAndSet(true)) {
                this.zzd.set(zzad.zza(context, str3, new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.gms.ads.internal.util.zzm
                    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
                    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str4) {
                        zzt.this.zzg(context, str3, sharedPreferences, str4);
                    }
                }));
            }
            bundle.putAll((Bundle) this.zzd.get());
        }
        com.google.android.gms.ads.internal.client.zzay.zzb();
        com.google.android.gms.ads.internal.util.client.zzf.zzx(context, str, "gmob-apps", bundle, true, new com.google.android.gms.ads.internal.util.client.zze() { // from class: com.google.android.gms.ads.internal.util.zzl
            @Override // com.google.android.gms.ads.internal.util.client.zze
            public final boolean zza(String str4) {
                zzfuv zzfuvVar = zzt.zza;
                com.google.android.gms.ads.internal.zzu.zzp();
                zzt.zzL(context, str, str4);
                return true;
            }
        });
    }

    public final boolean zzi(String str) {
        return zzaa(str, this.zzb, (String) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzab));
    }

    public final boolean zzj(String str) {
        return zzaa(str, this.zzc, (String) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzac));
    }

    @SuppressLint({"UnprotectedReceiver"})
    public final boolean zzk(Context context) {
        if (this.zzk) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        zzbep.zza(context);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzkW)).booleanValue() && Build.VERSION.SDK_INT >= 33) {
            context.getApplicationContext().registerReceiver(new zzq(this, null), intentFilter, 4);
        } else {
            context.getApplicationContext().registerReceiver(new zzq(this, null), intentFilter);
        }
        this.zzk = true;
        return true;
    }

    @SuppressLint({"UnprotectedReceiver"})
    public final boolean zzl(Context context) {
        if (this.zzj) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        zzbep.zza(context);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbep.zzkW)).booleanValue() && Build.VERSION.SDK_INT >= 33) {
            context.getApplicationContext().registerReceiver(new zzs(this, null), intentFilter, 4);
        } else {
            context.getApplicationContext().registerReceiver(new zzs(this, null), intentFilter);
        }
        this.zzj = true;
        return true;
    }

    public final int zzm(Context context, Uri uri) {
        int i9;
        if (context == null) {
            zze.zza("Trying to open chrome custom tab on a null context");
            return 3;
        }
        if (!(context instanceof Activity)) {
            zze.zza("Chrome Custom Tabs can only work with Activity context.");
            i9 = 2;
        } else {
            i9 = 0;
        }
        zzbeg zzbegVar = zzbep.zzeC;
        Boolean bool = (Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbegVar);
        zzbeg zzbegVar2 = zzbep.zzeD;
        if (true == bool.equals(com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbegVar2))) {
            i9 = 9;
        }
        if (i9 != 0) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            intent.addFlags(268435456);
            context.startActivity(intent);
            return i9;
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbegVar)).booleanValue()) {
            zzbfm zzbfmVar = new zzbfm();
            zzbfmVar.zze(new zzo(this, zzbfmVar, context, uri));
            zzbfmVar.zzb((Activity) context);
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(zzbegVar2)).booleanValue()) {
            C2739h a6 = new C2738g().a();
            a6.f23048a.setPackage(zzhlh.zza(context));
            a6.a(context, uri);
            return 5;
        }
        return 5;
    }
}
