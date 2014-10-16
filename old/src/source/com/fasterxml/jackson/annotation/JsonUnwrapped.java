// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;

public interface JsonUnwrapped
    extends Annotation
{

    public abstract boolean enabled();

    public abstract String prefix();

    public abstract String suffix();
}
