// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.*;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.SerialSubscription;

public class OperatorRetry
    implements rx.Observable.Operator
{

    public OperatorRetry()
    {
        this(-1);
    }

    public OperatorRetry(int i)
    {
        retryCount = i;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        SerialSubscription serialsubscription = new SerialSubscription();
        final_subscriber.add(serialsubscription);
        return new Subscriber(serialsubscription) {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                child.onError(throwable);
            }

            public volatile void onNext(Object obj)
            {
                onNext((Observable)obj);
            }

            public void onNext(Observable observable)
            {
                Schedulers.trampoline().schedule(observable. new Action1() {

                    public volatile void call(Object obj)
                    {
                        call((rx.Scheduler.Inner)obj);
                    }

                    public void call(final rx.Scheduler.Inner inner)
                    {
                        attempts.incrementAndGet();
                        Subscriber subscriber = new Subscriber() {

                            public void onCompleted()
                            {
                                child.onCompleted();
                            }

                            public void onError(Throwable throwable)
                            {
                                if((retryCount == -1 || attempts.get() <= retryCount) && !inner.isUnsubscribed())
                                {
                                    inner.schedule(_self);
                                    return;
                                } else
                                {
                                    child.onError(throwable);
                                    return;
                                }
                            }

                            public void onNext(Object obj)
                            {
                                child.onNext(obj);
                            }

                            final _cls1 this$2;
                            final Action1 val$_self;
                            final rx.Scheduler.Inner val$inner;

            
            {
                this$2 = final__pcls1;
                inner = inner1;
                _self = Action1.this;
                super();
            }
                        }
;
                        serialSubscription.set(subscriber);
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
);
            }

            final AtomicInteger attempts = new AtomicInteger(0);
            final OperatorRetry this$0;
            final Subscriber val$child;
            final SerialSubscription val$serialSubscription;

            
            {
                this$0 = OperatorRetry.this;
                child = subscriber1;
                serialSubscription = serialsubscription;
                super(final_subscriber);
            }
        }
;
    }

    private static final int INFINITE_RETRY = -1;
    private final int retryCount;

}
