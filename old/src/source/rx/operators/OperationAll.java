// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.functions.Func1;

public class OperationAll
{
    private static class AllObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return sequence.unsafeSubscribe(new AllObserver(observer));
        }

        private final Func1 predicate;
        private final Observable sequence;


        private AllObservable(Observable observable, Func1 func1)
        {
            sequence = observable;
            predicate = func1;
        }

    }

    private class AllObservable.AllObserver extends Subscriber
    {

        public void onCompleted()
        {
            if(status.get())
            {
                underlying.onNext(Boolean.valueOf(true));
                underlying.onCompleted();
            }
        }

        public void onError(Throwable throwable)
        {
            underlying.onError(throwable);
        }

        public void onNext(Object obj)
        {
            boolean flag = ((Boolean)predicate.call(obj)).booleanValue();
            if(status.compareAndSet(true, flag) && !flag)
            {
                underlying.onNext(Boolean.valueOf(false));
                underlying.onCompleted();
                unsubscribe();
            }
        }

        private final AtomicBoolean status = new AtomicBoolean(true);
        final AllObservable this$0;
        private final Observer underlying;

        public AllObservable.AllObserver(Observer observer)
        {
            this$0 = AllObservable.this;
            super();
            underlying = observer;
        }
    }


    public OperationAll()
    {
    }

    public static rx.Observable.OnSubscribeFunc all(Observable observable, Func1 func1)
    {
        return new AllObservable(observable, func1);
    }
}
