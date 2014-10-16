// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.BooleanSubscription;

// Referenced classes of package rx.schedulers:
//            SleepingAction

public class TrampolineScheduler extends Scheduler
{
    private class InnerCurrentThreadScheduler extends rx.Scheduler.Inner
        implements Subscription
    {

        private void enqueue(Action1 action1, long l)
        {
            if(!innerSubscription.isUnsubscribed()) goto _L2; else goto _L1
_L1:
            return;
_L2:
            PriorityQueue priorityqueue = (PriorityQueue)TrampolineScheduler.QUEUE.get();
            boolean flag;
            if(priorityqueue == null)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                priorityqueue = new PriorityQueue();
                TrampolineScheduler.QUEUE.set(priorityqueue);
            }
            priorityqueue.add(new TimedAction(action1, Long.valueOf(l), Integer.valueOf(counter.incrementAndGet())));
            if(flag)
label0:
                do
                {
label1:
                    {
                        if(priorityqueue.isEmpty())
                            break label1;
                        if(innerSubscription.isUnsubscribed())
                            break label0;
                        ((TimedAction)priorityqueue.poll()).action.call(this);
                    }
                } while(true);
            if(true) goto _L1; else goto _L3
_L3:
            TrampolineScheduler.QUEUE.set(null);
            return;
        }

        public boolean isUnsubscribed()
        {
            return innerSubscription.isUnsubscribed();
        }

        public void schedule(Action1 action1)
        {
            enqueue(action1, now());
        }

        public void schedule(Action1 action1, long l, TimeUnit timeunit)
        {
            long l1 = now() + timeunit.toMillis(l);
            enqueue(new SleepingAction(action1, TrampolineScheduler.this, l1), l1);
        }

        public void unsubscribe()
        {
            TrampolineScheduler.QUEUE.set(null);
            innerSubscription.unsubscribe();
        }

        private final BooleanSubscription innerSubscription;
        final TrampolineScheduler this$0;


        private InnerCurrentThreadScheduler()
        {
            this$0 = TrampolineScheduler.this;
            super();
            innerSubscription = new BooleanSubscription();
        }

    }

    private static class TimedAction
        implements Comparable
    {

        public volatile int compareTo(Object obj)
        {
            return compareTo((TimedAction)obj);
        }

        public int compareTo(TimedAction timedaction)
        {
            int i = execTime.compareTo(timedaction.execTime);
            if(i == 0)
                i = count.compareTo(timedaction.count);
            return i;
        }

        final Action1 action;
        final Integer count;
        final Long execTime;

        private TimedAction(Action1 action1, Long long1, Integer integer)
        {
            action = action1;
            execTime = long1;
            count = integer;
        }

    }


    TrampolineScheduler()
    {
    }

    public static TrampolineScheduler getInstance()
    {
        return INSTANCE;
    }

    static TrampolineScheduler instance()
    {
        return INSTANCE;
    }

    public Subscription schedule(Action1 action1)
    {
        InnerCurrentThreadScheduler innercurrentthreadscheduler = new InnerCurrentThreadScheduler();
        innercurrentthreadscheduler.schedule(action1);
        return innercurrentthreadscheduler.innerSubscription;
    }

    public Subscription schedule(Action1 action1, long l, TimeUnit timeunit)
    {
        InnerCurrentThreadScheduler innercurrentthreadscheduler = new InnerCurrentThreadScheduler();
        innercurrentthreadscheduler.schedule(action1, l, timeunit);
        return innercurrentthreadscheduler.innerSubscription;
    }

    private static final TrampolineScheduler INSTANCE = new TrampolineScheduler();
    private static final ThreadLocal QUEUE = new ThreadLocal();
    private final AtomicInteger counter = new AtomicInteger(0);



}
