// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observables;

import rx.Observable;
import rx.Subscription;
import rx.operators.OperationRefCount;

public abstract class ConnectableObservable extends Observable
{

    protected ConnectableObservable(rx.Observable.OnSubscribe onsubscribe)
    {
        super(onsubscribe);
    }

    public abstract Subscription connect();

    public Observable refCount()
    {
        return Observable.create(OperationRefCount.refCount(this));
    }
}
