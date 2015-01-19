// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import rx.*;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public final class OperationInterval
{
    private static class Interval
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return Subscriptions.create(scheduler.schedulePeriodically(observer. new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    observer.onNext(Long.valueOf(currentValue));
                    long l = ((Interval._cls1) (this)).call;
                }

                final Interval this$0;
                final Observer val$observer;

            
            {
                this$0 = final_interval1;
                observer = Observer.this;
                super();
            }
            }
, period, period, unit). new Action0() {

                public void call()
                {
                    wrapped.unsubscribe();
                }

                final Interval this$0;
                final Subscription val$wrapped;

            
            {
                this$0 = final_interval1;
                wrapped = Subscription.this;
                super();
            }
            }
);
        }

        private long currentValue;
        private final long period;
        private final Scheduler scheduler;
        private final TimeUnit unit;



/*
        static long access$108(Interval interval1)
        {
            long l = interval1.currentValue;
            interval1.currentValue = 1L + l;
            return l;
        }

*/

        private Interval(long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            period = l;
            unit = timeunit;
            scheduler = scheduler1;
        }

        Interval(long l, TimeUnit timeunit, Scheduler scheduler1, _cls1 _pcls1)
        {
            this(l, timeunit, scheduler1);
        }
    }


    public OperationInterval()
    {
    }

    public static rx.Observable.OnSubscribeFunc interval(long l, TimeUnit timeunit)
    {
        return interval(l, timeunit, Schedulers.computation());
    }

    public static rx.Observable.OnSubscribeFunc interval(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(l, timeunit, scheduler) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new Interval(interval, unit, scheduler)).onSubscribe(observer);
            }

            final long val$interval;
            final Scheduler val$scheduler;
            final TimeUnit val$unit;

            
            {
                interval = l;
                unit = timeunit;
                scheduler = scheduler1;
                super();
            }
        }
;
    }
}
