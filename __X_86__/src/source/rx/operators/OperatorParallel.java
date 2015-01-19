// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

// Referenced classes of package rx.operators:
//            OperatorGroupBy, OperatorMap, OperatorMerge

public final class OperatorParallel
    implements rx.Observable.Operator
{

    public OperatorParallel(Func1 func1, Scheduler scheduler1)
    {
        scheduler = scheduler1;
        f = func1;
        degreeOfParallelism = scheduler1.degreeOfParallelism();
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(Subscriber subscriber)
    {
        return (Subscriber)(new OperatorGroupBy(new Func1() {

            public Long call(Object obj)
            {
                long l = i;
                i = 1L + l;
                return Long.valueOf(l % (long)degreeOfParallelism);
            }

            public volatile Object call(Object obj)
            {
                return call(obj);
            }

            long i;
            final OperatorParallel this$0;

            
            {
                this$0 = OperatorParallel.this;
                super();
                i = 0L;
            }
        }
)).call((new OperatorMap(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((GroupedObservable)obj);
            }

            public Observable call(GroupedObservable groupedobservable)
            {
                return (Observable)f.call(groupedobservable.observeOn(scheduler));
            }

            final OperatorParallel this$0;

            
            {
                this$0 = OperatorParallel.this;
                super();
            }
        }
)).call((new OperatorMerge()).call(subscriber)));
    }

    private final int degreeOfParallelism;
    private final Func1 f;
    private final Scheduler scheduler;



}
