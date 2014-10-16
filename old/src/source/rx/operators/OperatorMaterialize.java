// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Notification;
import rx.Subscriber;

public final class OperatorMaterialize
    implements rx.Observable.Operator
{

    public OperatorMaterialize()
    {
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        return new Subscriber(final_subscriber) {

            public void onCompleted()
            {
                child.onNext(Notification.createOnCompleted());
                child.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                child.onNext(Notification.createOnError(throwable));
                child.onCompleted();
            }

            public void onNext(Object obj)
            {
                child.onNext(Notification.createOnNext(obj));
            }

            final OperatorMaterialize this$0;
            final Subscriber val$child;

            
            {
                this$0 = OperatorMaterialize.this;
                child = subscriber1;
                super(final_subscriber);
            }
        }
;
    }
}
