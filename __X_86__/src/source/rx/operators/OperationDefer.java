// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.Func0;
import rx.observers.Subscribers;

public final class OperationDefer
{

    public OperationDefer()
    {
    }

    public static rx.Observable.OnSubscribeFunc defer(Func0 func0)
    {
        return new rx.Observable.OnSubscribeFunc(func0) {

            public Subscription onSubscribe(Observer observer)
            {
                return ((Observable)observableFactory.call()).unsafeSubscribe(Subscribers.from(observer));
            }

            final Func0 val$observableFactory;

            
            {
                observableFactory = func0;
                super();
            }
        }
;
    }
}
