// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;


public interface HarvestLifecycleAware
{

    public abstract void onHarvest();

    public abstract void onHarvestBefore();

    public abstract void onHarvestComplete();

    public abstract void onHarvestConnected();

    public abstract void onHarvestDisabled();

    public abstract void onHarvestDisconnected();

    public abstract void onHarvestError();

    public abstract void onHarvestFinalize();

    public abstract void onHarvestSendFailed();

    public abstract void onHarvestStart();

    public abstract void onHarvestStop();
}
