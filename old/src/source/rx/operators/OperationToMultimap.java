// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import rx.*;
import rx.functions.*;
import rx.subscriptions.Subscriptions;

public class OperationToMultimap
{
    public static class DefaultMultimapCollectionFactory
        implements Func1
    {

        public volatile Object call(Object obj)
        {
            return call(obj);
        }

        public Collection call(Object obj)
        {
            return new ArrayList();
        }

        public DefaultMultimapCollectionFactory()
        {
        }
    }

    public static class DefaultToMultimapFactory
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

        public DefaultToMultimapFactory()
        {
        }
    }

    public static class ToMultimap
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
            return source.unsafeSubscribe(new ToMultimapObserver(observer, keySelector, valueSelector, map, collectionFactory));
        }

        private final Func1 collectionFactory;
        private final Func1 keySelector;
        private final Func0 mapFactory;
        private final Observable source;
        private final Func1 valueSelector;

        public ToMultimap(Observable observable, Func1 func1, Func1 func1_1, Func0 func0, Func1 func1_2)
        {
            source = observable;
            keySelector = func1;
            valueSelector = func1_1;
            mapFactory = func0;
            collectionFactory = func1_2;
        }
    }

    public static class ToMultimap.ToMultimapObserver extends Subscriber
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
            Collection collection = (Collection)map.get(obj1);
            if(collection == null)
            {
                collection = (Collection)collectionFactory.call(obj1);
                map.put(obj1, collection);
            }
            collection.add(obj2);
        }

        private final Func1 collectionFactory;
        private final Func1 keySelector;
        private Map map;
        private Observer t1;
        private final Func1 valueSelector;

        public ToMultimap.ToMultimapObserver(Observer observer, Func1 func1, Func1 func1_1, Map map1, Func1 func1_2)
        {
            t1 = observer;
            keySelector = func1;
            valueSelector = func1_1;
            collectionFactory = func1_2;
            map = map1;
        }
    }


    public OperationToMultimap()
    {
    }

    public static rx.Observable.OnSubscribeFunc toMultimap(Observable observable, Func1 func1)
    {
        return new ToMultimap(observable, func1, Functions.identity(), new DefaultToMultimapFactory(), new DefaultMultimapCollectionFactory());
    }

    public static rx.Observable.OnSubscribeFunc toMultimap(Observable observable, Func1 func1, Func1 func1_1)
    {
        return new ToMultimap(observable, func1, func1_1, new DefaultToMultimapFactory(), new DefaultMultimapCollectionFactory());
    }

    public static rx.Observable.OnSubscribeFunc toMultimap(Observable observable, Func1 func1, Func1 func1_1, Func0 func0)
    {
        return new ToMultimap(observable, func1, func1_1, func0, new DefaultMultimapCollectionFactory());
    }

    public static rx.Observable.OnSubscribeFunc toMultimap(Observable observable, Func1 func1, Func1 func1_1, Func0 func0, Func1 func1_2)
    {
        return new ToMultimap(observable, func1, func1_1, func0, func1_2);
    }
}
