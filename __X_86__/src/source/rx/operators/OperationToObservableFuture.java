// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class OperationToObservableFuture
{
    static class ToObservableFuture
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            if(time != null) goto _L2; else goto _L1
_L1:
            Object obj1 = that.get();
_L3:
            if(!that.isCancelled())
                observer.onNext(obj1);
            observer.onCompleted();
_L4:
            return Subscriptions.empty();
_L2:
            Object obj = that.get(time.longValue(), unit);
            obj1 = obj;
              goto _L3
            Throwable throwable;
            throwable;
            observer.onError(throwable);
              goto _L4
        }

        private final Future that;
        private final Long time;
        private final TimeUnit unit;

        public ToObservableFuture(Future future)
        {
            that = future;
            time = null;
            unit = null;
        }

        public ToObservableFuture(Future future, long l, TimeUnit timeunit)
        {
            that = future;
            time = Long.valueOf(l);
            unit = timeunit;
        }
    }


    public OperationToObservableFuture()
    {
    }

    public static rx.Observable.OnSubscribeFunc toObservableFuture(Future future)
    {
        return new ToObservableFuture(future);
    }

    public static rx.Observable.OnSubscribeFunc toObservableFuture(Future future, long l, TimeUnit timeunit)
    {
        return new ToObservableFuture(future, l, timeunit);
    }
}
