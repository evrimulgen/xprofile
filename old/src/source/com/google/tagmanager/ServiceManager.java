// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


abstract class ServiceManager
{

    ServiceManager()
    {
    }

    abstract void dispatch();

    abstract void onRadioPowered();

    abstract void setDispatchPeriod(int i);

    abstract void updateConnectivityStatus(boolean flag);
}
