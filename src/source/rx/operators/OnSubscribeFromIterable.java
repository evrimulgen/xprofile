// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Iterator;
import rx.Subscriber;

public final class OnSubscribeFromIterable
    implements rx.Observable.OnSubscribe
{

    public OnSubscribeFromIterable(Iterable iterable)
    {
        is = iterable;
    }

    public volatile void call(Object obj)
    {
        call((Subscriber)obj);
    }

    public void call(Subscriber subscriber)
    {
        Iterator iterator = is.iterator();
_L7:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj = iterator.next();
        if(!subscriber.isUnsubscribed()) goto _L4; else goto _L3
_L3:
        return;
_L4:
        subscriber.onNext(obj);
        continue; /* Loop/switch isn't completed */
_L2:
        if(subscriber.isUnsubscribed()) goto _L3; else goto _L5
_L5:
        subscriber.onCompleted();
        return;
        if(true) goto _L7; else goto _L6
_L6:
    }

    final Iterable is;
}
