// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observables;

import rx.Observable;
import rx.Subscriber;

public class GroupedObservable extends Observable
{

    public GroupedObservable(Object obj, rx.Observable.OnSubscribe onsubscribe)
    {
        super(onsubscribe);
        key = obj;
    }

    public static GroupedObservable from(Object obj, Observable observable)
    {
        return new GroupedObservable(obj, new rx.Observable.OnSubscribe(observable) {

            public volatile void call(Object obj1)
            {
                call((Subscriber)obj1);
            }

            public void call(Subscriber subscriber)
            {
                o.unsafeSubscribe(subscriber);
            }

            final Observable val$o;

            
            {
                o = observable;
                super();
            }
        }
);
    }

    public Object getKey()
    {
        return key;
    }

    private final Object key;
}
