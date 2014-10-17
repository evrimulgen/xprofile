// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Iterator;
import rx.Subscriber;
import rx.functions.Func2;
import rx.observers.Subscribers;

public final class OperatorZipIterable
    implements rx.Observable.Operator
{

    public OperatorZipIterable(Iterable iterable1, Func2 func2)
    {
        iterable = iterable1;
        zipFunction = func2;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber subscriber)
    {
        final Iterator iterator = iterable.iterator();
        Subscriber subscriber1;
        if(iterator.hasNext())
            break MISSING_BLOCK_LABEL_37;
        subscriber.onCompleted();
        subscriber1 = Subscribers.empty();
        return subscriber1;
        Throwable throwable;
        throwable;
        subscriber.onError(throwable);
        return new Subscriber() {

            public void onCompleted()
            {
                subscriber.onCompleted();
            }

            public void onError(Throwable throwable1)
            {
                subscriber.onError(throwable1);
            }

            public void onNext(Object obj)
            {
                try
                {
                    subscriber.onNext(zipFunction.call(obj, iterator.next()));
                    if(!iterator.hasNext())
                        onCompleted();
                    return;
                }
                catch(Throwable throwable1)
                {
                    onError(throwable1);
                }
            }

            final OperatorZipIterable this$0;
            final Iterator val$iterator;
            final Subscriber val$subscriber;

            
            {
                this$0 = OperatorZipIterable.this;
                subscriber = subscriber1;
                iterator = iterator1;
                super();
            }
        }
;
    }

    final Iterable iterable;
    final Func2 zipFunction;
}
