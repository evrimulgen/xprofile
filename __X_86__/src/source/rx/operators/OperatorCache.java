// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Subscriber;
import rx.observers.Subscribers;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

public final class OperatorCache
    implements rx.Observable.OnSubscribe
{

    public OperatorCache(Observable observable)
    {
        OperatorCache(observable, ((Subject) (ReplaySubject.create())));
    }

    OperatorCache(Observable observable, Subject subject)
    {
        source = observable;
        cache = subject;
        sourceSubscribed = new AtomicBoolean();
    }

    public volatile void call(Object obj)
    {
        call((Subscriber)obj);
    }

    public void call(Subscriber subscriber)
    {
        if(sourceSubscribed.compareAndSet(false, true))
            source.unsafeSubscribe(Subscribers.from(cache));
        cache.unsafeSubscribe(subscriber);
    }

    protected final Subject cache;
    protected final Observable source;
    protected final AtomicBoolean sourceSubscribed;
}
