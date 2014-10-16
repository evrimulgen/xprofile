// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

public class OperationParallelMerge
{

    public OperationParallelMerge()
    {
    }

    public static Observable parallelMerge(Observable observable, int i)
    {
        return parallelMerge(observable, i, Schedulers.immediate());
    }

    public static Observable parallelMerge(Observable observable, int i, Scheduler scheduler)
    {
        return observable.groupBy(new Func1(i) {

            public Integer call(Observable observable1)
            {
                return Integer.valueOf((int)rollingCount.incrementAndGet() % parallelObservables);
            }

            public volatile Object call(Object obj)
            {
                return call((Observable)obj);
            }

            final AtomicLong rollingCount = new AtomicLong();
            final int val$parallelObservables;

            
            {
                parallelObservables = i;
                Object();
            }
        }
).map(new Func1(scheduler) {

            public volatile Object call(Object obj)
            {
                return call((GroupedObservable)obj);
            }

            public Observable call(GroupedObservable groupedobservable)
            {
                return Observable.merge(groupedobservable).observeOn(scheduler);
            }

            final Scheduler val$scheduler;

            
            {
                scheduler = scheduler1;
                Object();
            }
        }
);
    }
}
