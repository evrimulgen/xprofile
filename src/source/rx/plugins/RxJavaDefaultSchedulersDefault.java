// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.plugins;

import rx.Scheduler;

// Referenced classes of package rx.plugins:
//            RxJavaDefaultSchedulers

public class RxJavaDefaultSchedulersDefault extends RxJavaDefaultSchedulers
{

    public RxJavaDefaultSchedulersDefault()
    {
    }

    public static RxJavaDefaultSchedulers getInstance()
    {
        return INSTANCE;
    }

    public Scheduler getComputationScheduler()
    {
        return null;
    }

    public Scheduler getIOScheduler()
    {
        return null;
    }

    public Scheduler getNewThreadScheduler()
    {
        return null;
    }

    private static RxJavaDefaultSchedulersDefault INSTANCE = new RxJavaDefaultSchedulersDefault();

}
