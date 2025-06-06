package com.applovin.impl;

import android.content.Context;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* loaded from: classes.dex */
public abstract class z9 {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f12543a = false;

    /* loaded from: classes.dex */
    public static final class a extends RuntimeException {
        public a(String str) {
            super(str);
        }
    }

    private static void a(int i9, String str, int i10) {
        int glCreateShader = GLES20.glCreateShader(i9);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = {0};
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 1) {
            a(GLES20.glGetShaderInfoLog(glCreateShader) + ", source: " + str);
        }
        GLES20.glAttachShader(i10, glCreateShader);
        GLES20.glDeleteShader(glCreateShader);
        a();
    }

    public static int b() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, IntBuffer.wrap(iArr));
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameteri(36197, 10241, 9729);
        GLES20.glTexParameteri(36197, 10240, 9729);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        a();
        return iArr[0];
    }

    public static boolean c() {
        String eglQueryString;
        if (yp.f12451a < 17 || (eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) == null || !eglQueryString.contains("EGL_KHR_surfaceless_context")) {
            return false;
        }
        return true;
    }

    public static void a() {
        int i9 = 0;
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError == 0) {
                break;
            }
            kc.b("GlUtil", "glError " + GLU.gluErrorString(glGetError));
            i9 = glGetError;
        }
        if (i9 != 0) {
            a("glError " + GLU.gluErrorString(i9));
        }
    }

    public static FloatBuffer a(int i9) {
        return ByteBuffer.allocateDirect(i9 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static FloatBuffer a(float[] fArr) {
        return (FloatBuffer) a(fArr.length).put(fArr).flip();
    }

    public static boolean a(Context context) {
        String eglQueryString;
        int i9 = yp.f12451a;
        if (i9 < 24) {
            return false;
        }
        if (i9 >= 26 || !(Constants.REFERRER_API_SAMSUNG.equals(yp.f12452c) || "XT1650".equals(yp.f12453d))) {
            return (i9 >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) && (eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) != null && eglQueryString.contains("EGL_EXT_protected_content");
        }
        return false;
    }

    public static int a(String[] strArr, String[] strArr2) {
        return a(TextUtils.join("\n", strArr), TextUtils.join("\n", strArr2));
    }

    public static int a(String str, String str2) {
        int glCreateProgram = GLES20.glCreateProgram();
        a();
        a(35633, str, glCreateProgram);
        a(35632, str2, glCreateProgram);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = {0};
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            a("Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(glCreateProgram));
        }
        a();
        return glCreateProgram;
    }

    private static void a(String str) {
        kc.b("GlUtil", str);
        if (f12543a) {
            throw new a(str);
        }
    }
}
