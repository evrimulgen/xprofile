// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;

public final class OnSubscribeRange
    implements rx.Observable.OnSubscribe
{

    public OnSubscribeRange(int i, int j)
    {
        start = i;
        end = j;
    }

    public volatile void call(Object obj)
    {
        call((Subscriber)obj);
    }

    public void call(Subscriber subscriber)
    {
        for(int i = start; i <= end; i++)
        {
            if(subscriber.isUnsubscribed())
                return;
            subscriber.onNext(Integer.valueOf(i));
        }

        subscriber.onCompleted();
    }

    private final int end;
    private final int start;
}
