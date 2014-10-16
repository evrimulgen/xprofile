// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.HashMap;
import java.util.Map;
import rx.*;
import rx.functions.*;
import rx.subscriptions.Subscriptions;

public class OperationToMap
{
    public static class DefaultToMapFactory
        implements Func0
    {

        public volatile Object call()
        {
            return call();
        }

        public Map call()
        {
            return new HashMap();
        }

        public DefaultToMapFactory()
        {
        }
    }

    public static class ToMap
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            Map map;
            try
            {
                map = (Map)mapFactory.call();
            }
            catch(Throwable throwable)
            {
                observer.onError(throwable);
                return Subscriptions.empty();
            }
            return source.unsafeSubscribe(new ToMapObserver(observer, keySelector, valueSelector, map));
        }

        private final Func1 keySelector;
        private final Func0 mapFactory;
        private final Observable source;
        private final Func1 valueSelector;

        public ToMap(Observable observable, Func1 func1, Func1 func1_1, Func0 func0)
        {
            source = observable;
            keySelector = func1;
            valueSelector = func1_1;
            mapFactory = func0;
        }
    }

    public static class ToMap.ToMapObserver extends Subscriber
    {

        public void onCompleted()
        {
            Map map1 = map;
            map = null;
            t1.onNext(map1);
            t1.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            map = null;
            t1.onError(throwable);
        }

        public void onNext(Object obj)
        {
            Object obj1 = keySelector.call(obj);
            Object obj2 = valueSelector.call(obj);
            map.put(obj1, obj2);
        }

        private final Func1 keySelector;
        Map map;
        private final Observer t1;
        private final Func1 valueSelector;

        public ToMap.ToMapObserver(Observer observer, Func1 func1, Func1 func1_1, Map map1)
        {
            map = map1;
            t1 = observer;
            keySelector = func1;
            valueSelector = func1_1;
        }
    }


    public OperationToMap()
    {
    }

    public static rx.Observable.OnSubscribeFunc toMap(Observable observable, Func1 func1)
    {
        return new ToMap(observable, func1, Functions.identity(), new DefaultToMapFactory());
    }

    public static rx.Observable.OnSubscribeFunc toMap(Observable observable, Func1 func1, Func1 func1_1)
    {
        return new ToMap(observable, func1, func1_1, new DefaultToMapFactory());
    }

    public static rx.Observable.OnSubscribeFunc toMap(Observable observable, Func1 func1, Func1 func1_1, Func0 func0)
    {
        return new ToMap(observable, func1, func1_1, func0);
    }
}
