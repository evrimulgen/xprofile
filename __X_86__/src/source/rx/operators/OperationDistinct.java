// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import rx.*;
import rx.functions.*;
import rx.subscriptions.Subscriptions;

public final class OperationDistinct
{
    private static class Distinct
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
                    Object obj1 = keySelector.call(obj);
                    if(!emittedKeys.contains(obj1))
                    {
                        emittedKeys.add(obj1);
                        observer.onNext(obj);
                    }
                }

                private final Set emittedKeys = new HashSet();
                final Distinct this$0;
                final Observer val$observer;

            
            {
                this$0 = final_distinct1;
                observer = Observer.this;
                super();
            }
            }
). new Action0() {

                public void call()
                {
                    sourceSub.unsubscribe();
                }

                final Distinct this$0;
                final Subscription val$sourceSub;

            
            {
                this$0 = final_distinct1;
                sourceSub = Subscription.this;
                super();
            }
            }
);
        }

        private final Func1 keySelector;
        private final Observable source;


        private Distinct(Observable observable, Func1 func1)
        {
            source = observable;
            keySelector = func1;
        }

    }

    private static class DistinctWithComparator
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return Subscriptions.create(source.unsafeSubscribe(observer. new Subscriber() {

                private boolean alreadyEmitted(Object obj)
                {
                    for(Iterator iterator = emittedKeys.iterator(); iterator.hasNext();)
                    {
                        Object obj1 = iterator.next();
                        if(equalityComparator.compare(obj1, obj) == 0)
                            return true;
                    }

                    return false;
                }

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
                    Object obj1 = keySelector.call(obj);
                    if(!alreadyEmitted(obj1))
                    {
                        emittedKeys.add(obj1);
                        observer.onNext(obj);
                    }
                }

                private final List emittedKeys = new ArrayList();
                final DistinctWithComparator this$0;
                final Observer val$observer;

            
            {
                this$0 = final_distinctwithcomparator;
                observer = Observer.this;
                super();
            }
            }
). new Action0() {

                public void call()
                {
                    sourceSub.unsubscribe();
                }

                final DistinctWithComparator this$0;
                final Subscription val$sourceSub;

            
            {
                this$0 = final_distinctwithcomparator;
                sourceSub = Subscription.this;
                super();
            }
            }
);
        }

        private final Comparator equalityComparator;
        private final Func1 keySelector;
        private final Observable source;



        private DistinctWithComparator(Observable observable, Func1 func1, Comparator comparator)
        {
            source = observable;
            keySelector = func1;
            equalityComparator = comparator;
        }

    }


    public OperationDistinct()
    {
    }

    public static rx.Observable.OnSubscribeFunc distinct(Observable observable)
    {
        return new Distinct(observable, Functions.identity());
    }

    public static rx.Observable.OnSubscribeFunc distinct(Observable observable, Comparator comparator)
    {
        return new DistinctWithComparator(observable, Functions.identity(), comparator);
    }

    public static rx.Observable.OnSubscribeFunc distinct(Observable observable, Func1 func1)
    {
        return new Distinct(observable, func1);
    }

    public static rx.Observable.OnSubscribeFunc distinct(Observable observable, Func1 func1, Comparator comparator)
    {
        return new DistinctWithComparator(observable, func1, comparator);
    }
}
