// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import java.lang.annotation.Annotation;

public interface WrapReturn
    extends Annotation
{

    public abstract String className();

    public abstract String methodDesc();

    public abstract String methodName();
}
