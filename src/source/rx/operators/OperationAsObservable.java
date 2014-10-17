// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.observers.Subscribers;

public final class OperationAsObservable
    implements rx.Observable.OnSubscribeFunc
{

    public OperationAsObservable(Observable observable)
    {
        source = observable;
    }

    public Subscription onSubscribe(Observer observer)
    {
        return source.unsafeSubscribe(Subscribers.from(observer));
    }

    private final Observable source;
}
