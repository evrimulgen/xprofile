// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.plugins;

import rx.Observable;
import rx.Subscription;

public abstract class RxJavaObservableExecutionHook
{

    public RxJavaObservableExecutionHook()
    {
    }

    public rx.Observable.OnSubscribe onCreate(rx.Observable.OnSubscribe onsubscribe)
    {
        return onsubscribe;
    }

    public rx.Observable.Operator onLift(rx.Observable.Operator operator)
    {
        return operator;
    }

    public Throwable onSubscribeError(Throwable throwable)
    {
        return throwable;
    }

    public Subscription onSubscribeReturn(Subscription subscription)
    {
        return subscription;
    }

    public rx.Observable.OnSubscribe onSubscribeStart(Observable observable, rx.Observable.OnSubscribe onsubscribe)
    {
        return onsubscribe;
    }
}
