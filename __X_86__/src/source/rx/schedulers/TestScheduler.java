// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;

import java.util.*;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.BooleanSubscription;

public class TestScheduler extends Scheduler
{
    private static class CompareActionsByTime
        implements Comparator
    {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((TimedAction)obj, (TimedAction)obj1);
        }

        public int compare(TimedAction timedaction, TimedAction timedaction1)
        {
            if(timedaction.time == timedaction1.time)
                return Long.valueOf(timedaction.count).compareTo(Long.valueOf(timedaction1.count));
            else
                return Long.valueOf(timedaction.time).compareTo(Long.valueOf(timedaction1.time));
        }

        private CompareActionsByTime()
        {
        }

    }

    private final class InnerTestScheduler extends rx.Scheduler.Inner
    {

        public boolean isUnsubscribed()
        {
            return s.isUnsubscribed();
        }

        public void schedule(Action1 action1)
        {
            TimedAction timedaction = new TimedAction(this, 0L, action1);
            queue.add(timedaction);
        }

        public void schedule(Action1 action1, long l, TimeUnit timeunit)
        {
            TimedAction timedaction = new TimedAction(this, time + timeunit.toNanos(l), action1);
            queue.add(timedaction);
        }

        public void unsubscribe()
        {
            s.unsubscribe();
        }

        private BooleanSubscription s;
        final TestScheduler this$0;

        private InnerTestScheduler()
        {
            this$0 = TestScheduler.this;
            super();
            s = new BooleanSubscription();
        }

    }

    private static class TimedAction
    {

        public String toString()
        {
            Object aobj[] = new Object[2];
            aobj[0] = Long.valueOf(time);
            aobj[1] = action.toString();
            return String.format("TimedAction(time = %d, action = %s)", aobj);
        }

        private final Action1 action;
        private final long count;
        private final rx.Scheduler.Inner scheduler;
        private final long time;





        private TimedAction(rx.Scheduler.Inner inner, long l, Action1 action1)
        {
            count = long l = 
// JavaClassFileOutputException: get_constant: invalid tag

        TimedAction(rx.Scheduler.Inner inner, long l, Action1 action1, _cls1 _pcls1)
        {
            TimedAction(inner, l, action1);
        }
    }


    public TestScheduler()
    {
    }

    private void triggerActions(long l)
    {
        do
        {
            TimedAction timedaction;
label0:
            {
                if(!queue.isEmpty())
                {
                    timedaction = (TimedAction)queue.peek();
                    if(timedaction.time <= l)
                        break label0;
                }
                time = l;
                return;
            }
            time = timedaction.time;
            queue.remove();
            if(!timedaction.scheduler.isUnsubscribed())
                timedaction.action.call(timedaction.scheduler);
        } while(true);
    }

    public void advanceTimeBy(long l, TimeUnit timeunit)
    {
        advanceTimeTo(time + timeunit.toNanos(l), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long l, TimeUnit timeunit)
    {
        triggerActions(timeunit.toNanos(l));
    }

    public rx.Scheduler.Inner createInnerScheduler()
    {
        return new InnerTestScheduler();
    }

    public long now()
    {
        return TimeUnit.NANOSECONDS.toMillis(time);
    }

    public Subscription schedule(Action1 action1)
    {
        rx.Scheduler.Inner inner = createInnerScheduler();
        TimedAction timedaction = new TimedAction(inner, 0L, action1);
        queue.add(timedaction);
        return inner;
    }

    public Subscription schedule(Action1 action1, long l, TimeUnit timeunit)
    {
        rx.Scheduler.Inner inner = createInnerScheduler();
        TimedAction timedaction = new TimedAction(inner, time + timeunit.toNanos(l), action1);
        queue.add(timedaction);
        return inner;
    }

    public void triggerActions()
    {
        triggerActions(time);
    }

    private static long counter = 0L;
    private final Queue queue = new PriorityQueue(11, new CompareActionsByTime());
    private long time;



/*
    static long access$108()
    {
        long l = counter;
        counter = 1L + l;
        return l;
    }

*/


}
