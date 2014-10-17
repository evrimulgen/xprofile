// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observers;

import rx.Observer;
import rx.Subscriber;

// Referenced classes of package rx.observers:
//            SynchronizedObserver

public final class SynchronizedSubscriber extends Subscriber
{

    public SynchronizedSubscriber(Subscriber subscriber)
    {
        this(subscriber, new Object());
    }

    public SynchronizedSubscriber(Subscriber subscriber, Object obj)
    {
        super(subscriber);
        observer = new SynchronizedObserver(subscriber, obj);
    }

    public void onCompleted()
    {
        observer.onCompleted();
    }

    public void onError(Throwable throwable)
    {
        observer.onError(throwable);
    }

    public void onNext(Object obj)
    {
        observer.onNext(obj);
    }

    private final Observer observer;
}
