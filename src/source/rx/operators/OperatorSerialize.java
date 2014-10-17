// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;
import rx.observers.SerializedSubscriber;

public final class OperatorSerialize
    implements rx.Observable.Operator
{

    public OperatorSerialize()
    {
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        return new SerializedSubscriber(new Subscriber(final_subscriber) {

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

            final OperatorSerialize this$0;
            final Subscriber val$s;

            
            {
                this$0 = OperatorSerialize.this;
                s = subscriber1;
                super(final_subscriber);
            }
        }
);
    }
}
