// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


interface HitSendingThread
{

    public abstract void queueToThread(Runnable runnable);

    public abstract void sendHit(String s);
}
