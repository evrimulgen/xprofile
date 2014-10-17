// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;

public class OperationTimeInterval
{
    private static class TimeIntervalObserver extends Subscriber
    {

        public void onCompleted()
        {
            observer.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            observer.onCompleted();
        }

        public void onNext(Object obj)
        {
            long l = scheduler.now();
            observer.onNext(new TimeInterval(l - lastTimestamp, obj));
            lastTimestamp = l;
        }

        private long lastTimestamp;
        private final Observer observer;
        private final Scheduler scheduler;

        public TimeIntervalObserver(Observer observer1, Scheduler scheduler1)
        {
            observer = observer1;
            scheduler = scheduler1;
            lastTimestamp = scheduler1.now();
        }
    }


    public OperationTimeInterval()
    {
    }

    public static rx.Observable.OnSubscribeFunc timeInterval(Observable observable)
    {
        return timeInterval(observable, Schedulers.immediate());
    }

    public static rx.Observable.OnSubscribeFunc timeInterval(Observable observable, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(observable, scheduler) {

            public Subscription onSubscribe(Observer observer)
            {
                return source.unsafeSubscribe(new TimeIntervalObserver(observer, scheduler));
            }

            final Scheduler val$scheduler;
            final Observable val$source;

            
            {
                source = observable;
                scheduler = scheduler1;
                Object();
            }
        }
;
    }
}
