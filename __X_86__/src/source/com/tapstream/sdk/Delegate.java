// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;


interface Delegate
{

    public abstract int getDelay();

    public abstract boolean isRetryAllowed();

    public abstract void setDelay(int i);
}
