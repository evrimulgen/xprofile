// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.BooleanSubscription;

// Referenced classes of package rx.schedulers:
//            SleepingAction

public final class ImmediateScheduler extends Scheduler
{
    private class InnerImmediateScheduler extends rx.Scheduler.Inner
        implements Subscription
    {

        public boolean isUnsubscribed()
        {
            return innerSubscription.isUnsubscribed();
        }

        public void schedule(Action1 action1)
        {
            action1.call(this);
        }

        public void schedule(Action1 action1, long l, TimeUnit timeunit)
        {
            long l1 = now() + timeunit.toMillis(l);
            schedule(((Action1) (new SleepingAction(action1, ImmediateScheduler.this, l1))));
        }

        public void unsubscribe()
        {
            innerSubscription.unsubscribe();
        }

        final BooleanSubscription innerSubscription;
        final ImmediateScheduler this$0;

        private InnerImmediateScheduler()
        {
            this$0 = ImmediateScheduler.this;
            super();
            innerSubscription = new BooleanSubscription();
        }

    }


    ImmediateScheduler()
    {
    }

    public static ImmediateScheduler getInstance()
    {
        return INSTANCE;
    }

    static ImmediateScheduler instance()
    {
        return INSTANCE;
    }

    public Subscription schedule(Action1 action1)
    {
        InnerImmediateScheduler innerimmediatescheduler = new InnerImmediateScheduler();
        innerimmediatescheduler.schedule(action1);
        return innerimmediatescheduler.innerSubscription;
    }

    public Subscription schedule(Action1 action1, long l, TimeUnit timeunit)
    {
        InnerImmediateScheduler innerimmediatescheduler = new InnerImmediateScheduler();
        innerimmediatescheduler.schedule(action1, l, timeunit);
        return innerimmediatescheduler.innerSubscription;
    }

    private static final ImmediateScheduler INSTANCE = new ImmediateScheduler();

}
