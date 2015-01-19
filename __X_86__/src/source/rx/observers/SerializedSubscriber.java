// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observers;

import rx.Observer;
import rx.Subscriber;

// Referenced classes of package rx.observers:
//            SerializedObserver

public class SerializedSubscriber extends Subscriber
{

    public SerializedSubscriber(Subscriber subscriber)
    {
        super(subscriber);
        s = new SerializedObserver(subscriber);
    }

    public void onCompleted()
    {
        s.onCompleted();
    }

    public void onError(Throwable throwable)
    {
        s.onError(throwable);
    }

    public void onNext(Object obj)
    {
        s.onNext(obj);
    }

    private final Observer s;
}
