// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Timestamped;

public final class OperatorTimestamp
    implements rx.Observable.Operator
{

    public OperatorTimestamp(Scheduler scheduler1)
    {
        scheduler = scheduler1;
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
                o.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                o.onError(throwable);
            }

            public void onNext(Object obj)
            {
                o.onNext(new Timestamped(scheduler.now(), obj));
            }

            final OperatorTimestamp this$0;
            final Subscriber val$o;

            
            {
                this$0 = OperatorTimestamp.this;
                o = subscriber1;
                super(final_subscriber);
            }
        }
;
    }

    private final Scheduler scheduler;

}
