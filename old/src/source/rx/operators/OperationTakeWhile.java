// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.*;
import rx.functions.Func1;
import rx.functions.Func2;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription, SafeObserver

public final class OperationTakeWhile
{
    private static class TakeWhile
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return subscription.wrap(items.unsafeSubscribe(new ItemObserver(observer)));
        }

        private final Observable items;
        private final Func2 predicate;
        private final SafeObservableSubscription subscription;



        private TakeWhile(Observable observable, Func2 func2)
        {
            subscription = new SafeObservableSubscription();
            items = observable;
            predicate = func2;
        }

    }

    private class TakeWhile.ItemObserver extends Subscriber
    {

        public void onCompleted()
        {
            observer.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            Boolean boolean1;
            try
            {
                boolean1 = (Boolean)predicate.call(obj, Integer.valueOf(counter.getAndIncrement()));
            }
            catch(Throwable throwable)
            {
                observer.onError(throwable);
                return;
            }
            if(boolean1.booleanValue())
            {
                observer.onNext(obj);
                return;
            } else
            {
                observer.onCompleted();
                subscription.unsubscribe();
                return;
            }
        }

        private final AtomicInteger counter = new AtomicInteger();
        private final Observer observer;
        final TakeWhile this$0;

        public TakeWhile.ItemObserver(Observer observer1)
        {
            this$0 = TakeWhile.this;
            Subscriber();
            observer = new SafeObserver(subscription, observer1);
        }
    }


    public OperationTakeWhile()
    {
    }

    private static Func2 skipIndex(Func1 func1)
    {
        return new Func2(func1) {

            public Boolean call(Object obj, Integer integer)
            {
                return (Boolean)underlying.call(obj);
            }

            public volatile Object call(Object obj, Object obj1)
            {
                return call(obj, (Integer)obj1);
            }

            final Func1 val$underlying;

            
            {
                underlying = func1;
                Object();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc takeWhile(Observable observable, Func1 func1)
    {
        return takeWhileWithIndex(observable, skipIndex(func1));
    }

    public static rx.Observable.OnSubscribeFunc takeWhileWithIndex(Observable observable, Func2 func2)
    {
        return new rx.Observable.OnSubscribeFunc(observable, func2) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new TakeWhile(items, predicate)).onSubscribe(observer);
            }

            final Observable val$items;
            final Func2 val$predicate;

            
            {
                items = observable;
                predicate = func2;
                Object();
            }
        }
;
    }
}
