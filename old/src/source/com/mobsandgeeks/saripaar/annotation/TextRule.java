// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobsandgeeks.saripaar.annotation;

import java.lang.annotation.Annotation;

public interface TextRule
    extends Annotation
{

    public abstract int maxLength();

    public abstract String message();

    public abstract int messageResId();

    public abstract int minLength();

    public abstract int order();

    public abstract boolean trim();
}
