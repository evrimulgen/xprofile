// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.plugins;

import rx.Scheduler;

public abstract class RxJavaDefaultSchedulers
{

    public RxJavaDefaultSchedulers()
    {
    }

    public abstract Scheduler getComputationScheduler();

    public abstract Scheduler getIOScheduler();

    public abstract Scheduler getNewThreadScheduler();
}
