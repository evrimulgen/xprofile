// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class OperationMinMax
{

    public OperationMinMax()
    {
    }

    public static Observable max(Observable observable)
    {
        return minMax(observable, 1L);
    }

    public static Observable max(Observable observable, Comparator comparator)
    {
        return minMax(observable, comparator, 1L);
    }

    public static Observable maxBy(Observable observable, Func1 func1)
    {
        return minMaxBy(observable, func1, 1L);
    }

    public static Observable maxBy(Observable observable, Func1 func1, Comparator comparator)
    {
        return minMaxBy(observable, func1, comparator, 1L);
    }

    public static Observable min(Observable observable)
    {
        return minMax(observable, -1L);
    }

    public static Observable min(Observable observable, Comparator comparator)
    {
        return minMax(observable, comparator, -1L);
    }

    public static Observable minBy(Observable observable, Func1 func1)
    {
        return minMaxBy(observable, func1, -1L);
    }

    public static Observable minBy(Observable observable, Func1 func1, Comparator comparator)
    {
        return minMaxBy(observable, func1, comparator, -1L);
    }

    private static Observable minMax(Observable observable, long l)
    {
        return observable.reduce(new Func2(l) {

            public Comparable call(Comparable comparable, Comparable comparable1)
            {
                if(flag * (long)comparable.compareTo(comparable1) > 0L)
                    return comparable;
                else
                    return comparable1;
            }

            public volatile Object call(Object obj, Object obj1)
            {
                return call((Comparable)obj, (Comparable)obj1);
            }

            final long val$flag;

            
            {
                flag = l;
                Object();
            }
        }
);
    }

    private static Observable minMax(Observable observable, Comparator comparator, long l)
    {
        return observable.reduce(new Func2(l, comparator) {

            public Object call(Object obj, Object obj1)
            {
                if(flag * (long)comparator.compare(obj, obj1) > 0L)
                    return obj;
                else
                    return obj1;
            }

            final Comparator val$comparator;
            final long val$flag;

            
            {
                flag = l;
                comparator = comparator1;
                Object();
            }
        }
);
    }

    private static Observable minMaxBy(Observable observable, Func1 func1, long l)
    {
        return observable.reduce(new ArrayList(), new Func2(func1, l) {

            public volatile Object call(Object obj, Object obj1)
            {
                return call((List)obj, obj1);
            }

            public List call(List list, Object obj)
            {
                if(list.isEmpty())
                {
                    list.add(obj);
                } else
                {
                    int i = ((Comparable)selector.call(list.get(0))).compareTo(selector.call(obj));
                    if(i == 0)
                    {
                        list.add(obj);
                        return list;
                    }
                    if(flag * (long)i < 0L)
                    {
                        list.clear();
                        list.add(obj);
                        return list;
                    }
                }
                return list;
            }

            final long val$flag;
            final Func1 val$selector;

            
            {
                selector = func1;
                flag = l;
                Object();
            }
        }
);
    }

    private static Observable minMaxBy(Observable observable, Func1 func1, Comparator comparator, long l)
    {
        return observable.reduce(new ArrayList(), new Func2(comparator, func1, l) {

            public volatile Object call(Object obj, Object obj1)
            {
                return call((List)obj, obj1);
            }

            public List call(List list, Object obj)
            {
                if(list.isEmpty())
                {
                    list.add(obj);
                } else
                {
                    int i = comparator.compare(selector.call(list.get(0)), selector.call(obj));
                    if(i == 0)
                    {
                        list.add(obj);
                        return list;
                    }
                    if(flag * (long)i < 0L)
                    {
                        list.clear();
                        list.add(obj);
                        return list;
                    }
                }
                return list;
            }

            final Comparator val$comparator;
            final long val$flag;
            final Func1 val$selector;

            
            {
                comparator = comparator1;
                selector = func1;
                flag = l;
                Object();
            }
        }
);
    }
}
