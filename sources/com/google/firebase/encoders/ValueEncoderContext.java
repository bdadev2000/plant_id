package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* loaded from: classes3.dex */
public interface ValueEncoderContext {
    @NonNull
    ValueEncoderContext add(double d) throws IOException;

    @NonNull
    ValueEncoderContext add(float f2) throws IOException;

    @NonNull
    ValueEncoderContext add(int i2) throws IOException;

    @NonNull
    ValueEncoderContext add(long j2) throws IOException;

    @NonNull
    ValueEncoderContext add(@Nullable String str) throws IOException;

    @NonNull
    ValueEncoderContext add(boolean z2) throws IOException;

    @NonNull
    ValueEncoderContext add(@NonNull byte[] bArr) throws IOException;
}
