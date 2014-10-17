// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.Action1;

public class OperatorSubscribeOn
    implements rx.Observable.Operator
{

    public OperatorSubscribeOn(Scheduler scheduler1)
    {
        scheduler = scheduler1;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber1)
    {
        return new Subscriber(final_subscriber1) {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                subscriber.onError(throwable);
            }

            public volatile void onNext(Object obj)
            {
                onNext((Observable)obj);
            }

            public void onNext(Observable observable)
            {
                subscriber.add(scheduler.schedule(observable. new Action1() {

                    public volatile void call(Object obj)
                    {
                        call((rx.Scheduler.Inner)obj);
                    }

                    public void call(rx.Scheduler.Inner inner)
                    {
                        o.unsafeSubscribe(subscriber);
                    }

                    final _cls1 this$1;
                    final Observable val$o;

            
            {
                this$1 = final__pcls1;
                o = Observable.this;
                super();
            }
                }
));
            }

            final OperatorSubscribeOn this$0;
            final Subscriber val$subscriber;

            
            {
                this$0 = OperatorSubscribeOn.this;
                subscriber = subscriber2;
                super(final_subscriber1);
            }
        }
;
    }

    private final Scheduler scheduler;

}
