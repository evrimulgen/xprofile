// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import rx.*;
import rx.functions.Action1;

public final class OperationTimer
{
    public static class TimerOnce
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return scheduler.schedule(observer. new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    t1.onNext(Long.valueOf(0L));
                    t1.onCompleted();
                }

                final TimerOnce this$0;
                final Observer val$t1;

            
            {
                this$0 = final_timeronce;
                t1 = Observer.this;
                Object();
            }
            }
, dueTime, dueUnit);
        }

        private final long dueTime;
        private final TimeUnit dueUnit;
        private final Scheduler scheduler;

        public TimerOnce(long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            scheduler = scheduler1;
            dueTime = l;
            dueUnit = timeunit;
        }
    }

    public static class TimerPeriodically
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return scheduler.schedulePeriodically(observer. new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    Observer observer = t1;
                    long l = count;
                    count = 1L + l;
                    observer.onNext(Long.valueOf(l));
                }

                long count;
                final TimerPeriodically this$0;
                final Observer val$t1;

            
            {
                this$0 = final_timerperiodically;
                t1 = Observer.this;
                Object();
            }
            }
, initialDelay, period, unit);
        }

        private final long initialDelay;
        private final long period;
        private final Scheduler scheduler;
        private final TimeUnit unit;

        public TimerPeriodically(long l, long l1, TimeUnit timeunit, Scheduler scheduler1)
        {
            scheduler = scheduler1;
            initialDelay = l;
            period = l1;
            unit = timeunit;
        }
    }


    private OperationTimer()
    {
        throw new IllegalStateException("No instances!");
    }
}
