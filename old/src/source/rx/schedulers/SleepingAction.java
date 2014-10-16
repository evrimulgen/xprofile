// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;

import rx.Scheduler;
import rx.functions.Action1;

class SleepingAction
    implements Action1
{

    public SleepingAction(Action1 action1, Scheduler scheduler1, long l)
    {
        underlying = action1;
        scheduler = scheduler1;
        execTime = l;
    }

    public volatile void call(Object obj)
    {
        call((rx.Scheduler.Inner)obj);
    }

    public void call(rx.Scheduler.Inner inner)
    {
        if(!inner.isUnsubscribed()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(execTime <= scheduler.now())
            continue; /* Loop/switch isn't completed */
        long l = execTime - scheduler.now();
        if(l <= 0L)
            continue; /* Loop/switch isn't completed */
        try
        {
            Thread.sleep(l);
        }
        catch(InterruptedException interruptedexception)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException(interruptedexception);
        }
        if(inner.isUnsubscribed()) goto _L1; else goto _L3
_L3:
        underlying.call(inner);
        return;
    }

    private final long execTime;
    private final Scheduler scheduler;
    private final Action1 underlying;
}
