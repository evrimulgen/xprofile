// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import rx.Subscriber;
import rx.functions.Func2;

public final class OperatorToObservableSortedList
    implements rx.Observable.Operator
{
    private static class DefaultComparableFunction
        implements Func2
    {

        public Integer call(Object obj, Object obj1)
        {
            return Integer.valueOf(((Comparable)obj).compareTo((Comparable)obj1));
        }

        public volatile Object call(Object obj, Object obj1)
        {
            return call(obj, obj1);
        }

        private DefaultComparableFunction()
        {
        }

    }


    public OperatorToObservableSortedList()
    {
        sortFunction = defaultSortFunction;
    }

    public OperatorToObservableSortedList(Func2 func2)
    {
        sortFunction = func2;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        return new Subscriber(final_subscriber) {

            public void onCompleted()
            {
                try
                {
                    Collections.sort(list, new Comparator() {

                        public int compare(Object obj, Object obj1)
                        {
                            return ((Integer)sortFunction.call(obj, obj1)).intValue();
                        }

                        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                    }
);
                    o.onNext(Collections.unmodifiableList(list));
                    o.onCompleted();
                    return;
                }
                catch(Throwable throwable)
                {
                    onError(throwable);
                }
            }

            public void onError(Throwable throwable)
            {
                o.onError(throwable);
            }

            public void onNext(Object obj)
            {
                list.add(obj);
            }

            final List list = new ArrayList();
            final OperatorToObservableSortedList this$0;
            final Subscriber val$o;

            
            {
                this$0 = OperatorToObservableSortedList.this;
                o = subscriber1;
                super(final_subscriber);
            }
        }
;
    }

    private static Func2 defaultSortFunction = new DefaultComparableFunction();
    private final Func2 sortFunction;


}
