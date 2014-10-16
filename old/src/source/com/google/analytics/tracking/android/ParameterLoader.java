// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;


interface ParameterLoader
{

    public abstract boolean getBoolean(String s);

    public abstract Double getDoubleFromString(String s);

    public abstract int getInt(String s, int i);

    public abstract String getString(String s);

    public abstract boolean isBooleanKeyPresent(String s);

    public abstract void setResourcePackageName(String s);
}
