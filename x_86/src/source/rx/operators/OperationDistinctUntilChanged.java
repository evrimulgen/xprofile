// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Comparator;
import rx.*;
import rx.functions.*;
import rx.subscriptions.Subscriptions;

public final class OperationDistinctUntilChanged
{
    private static class DefaultEqualityComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            if(obj != null) goto _L2; else goto _L1
_L1:
            if(obj1 != null) goto _L4; else goto _L3
_L3:
            return 0;
_L4:
            return 1;
_L2:
            if(!obj.equals(obj1))
                return 1;
            if(true) goto _L3; else goto _L5
_L5:
        }

        private DefaultEqualityComparator()
        {
        }

    }

    private static class DistinctUntilChanged
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return Subscriptions.create(source.unsafeSubscribe(observer. new Subscriber() {

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
                    Object obj1 = lastEmittedKey;
                    Object obj2 = keySelector.call(obj);
                    lastEmittedKey = obj2;
                    if(!hasEmitted)
                    {
                        hasEmitted = true;
                        observer.onNext(obj);
                    } else
                    if(equalityComparator.compare(obj1, obj2) != 0)
                    {
                        observer.onNext(obj);
                        return;
                    }
                }

                private boolean hasEmitted;
                private Object lastEmittedKey;
                final DistinctUntilChanged this$0;
                final Observer val$observer;

            
            {
                this$0 = final_distinctuntilchanged;
                observer = Observer.this;
                super();
            }
            }
). new Action0() {

                public void call()
                {
                    sourceSub.unsubscribe();
                }

                final DistinctUntilChanged this$0;
                final Subscription val$sourceSub;

            
            {
                this$0 = final_distinctuntilchanged;
                sourceSub = Subscription.this;
                super();
            }
            }
);
        }

        private final Comparator equalityComparator;
        private final Func1 keySelector;
        private final Observable source;



        private DistinctUntilChanged(Observable observable, Func1 func1, Comparator comparator)
        {
            source = observable;
            keySelector = func1;
            equalityComparator = comparator;
        }

    }


    public OperationDistinctUntilChanged()
    {
    }

    public static rx.Observable.OnSubscribeFunc distinctUntilChanged(Observable observable)
    {
        return new DistinctUntilChanged(observable, Functions.identity(), new DefaultEqualityComparator());
    }

    public static rx.Observable.OnSubscribeFunc distinctUntilChanged(Observable observable, Comparator comparator)
    {
        return new DistinctUntilChanged(observable, Functions.identity(), comparator);
    }

    public static rx.Observable.OnSubscribeFunc distinctUntilChanged(Observable observable, Func1 func1)
    {
        return new DistinctUntilChanged(observable, func1, new DefaultEqualityComparator());
    }

    public static rx.Observable.OnSubscribeFunc distinctUntilChanged(Observable observable, Func1 func1, Comparator comparator)
    {
        return new DistinctUntilChanged(observable, func1, comparator);
    }
}
