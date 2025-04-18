package com.google.common.net;

import com.google.common.annotations.GwtCompatible;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@GwtCompatible
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
@interface ParametricNullness {
}
