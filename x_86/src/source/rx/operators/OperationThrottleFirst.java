// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import rx.*;
import rx.functions.Func1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

public final class OperationThrottleFirst
{

    public OperationThrottleFirst()
    {
    }

    public static rx.Observable.OnSubscribeFunc throttleFirst(Observable observable, long l, TimeUnit timeunit)
    {
        return throttleFirst(observable, l, timeunit, Schedulers.computation());
    }

    public static rx.Observable.OnSubscribeFunc throttleFirst(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(timeunit, l, observable, scheduler) {

            public Subscription onSubscribe(Observer observer)
            {
                final AtomicLong lastOnNext = new AtomicLong(0L);
                long l1 = unit.toMillis(windowDuration);
                return items.filter(l1. new Func1() {

                    public Boolean call(Object obj)
                    {
                        long l = scheduler.now();
                        if(lastOnNext.get() == 0L || l - lastOnNext.get() >= timeInMilliseconds)
                        {
                            lastOnNext.set(l);
                            return Boolean.TRUE;
                        } else
                        {
                            return Boolean.FALSE;
                        }
                    }

                    public volatile Object call(Object obj)
                    {
                        return call(obj);
                    }

                    final _cls1 this$0;
                    final AtomicLong val$lastOnNext;
                    final long val$timeInMilliseconds;

            
            {
                this$0 = final__pcls1;
                lastOnNext = atomiclong;
                timeInMilliseconds = J.this;
                Object();
            }
                }
).unsafeSubscribe(Subscribers.from(observer));
            }

            final Observable val$items;
            final Scheduler val$scheduler;
            final TimeUnit val$unit;
            final long val$windowDuration;

            
            {
                unit = timeunit;
                windowDuration = l;
                items = observable;
                scheduler = scheduler1;
                Object();
            }
        }
;
    }
}
