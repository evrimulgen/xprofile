// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.Action1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

public class OperatorRepeat
    implements rx.Observable.Operator
{

    public OperatorRepeat()
    {
        this(-1L, Schedulers.trampoline());
    }

    public OperatorRepeat(long l)
    {
        this(l, Schedulers.trampoline());
    }

    public OperatorRepeat(long l, Scheduler scheduler1)
    {
        scheduler = scheduler1;
        count = l;
    }

    public OperatorRepeat(Scheduler scheduler1)
    {
        this(-1L, scheduler1);
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        if(count == 0L)
        {
            final_subscriber.onCompleted();
            return Subscribers.empty();
        } else
        {
            return new Subscriber(final_subscriber) {

                public void onCompleted()
                {
                }

                public void onError(Throwable throwable)
                {
                    child.onError(new IllegalStateException("Error received on nested Observable.", throwable));
                }

                public volatile void onNext(Object obj)
                {
                    onNext((Observable)obj);
                }

                public void onNext(Observable observable)
                {
                    scheduler.schedule(observable. new Action1() {

                        public volatile void call(Object obj)
                        {
                            call((rx.Scheduler.Inner)obj);
                        }

                        public void call(rx.Scheduler.Inner inner)
                        {
                            _cls1 _lcls1 = _cls1.this;
                            _lcls1.executionCount = 1 + _lcls1.executionCount;
                            t.unsafeSubscribe(child. new Subscriber(inner) {

                                public void onCompleted()
                                {
                                    if(count == -1L || (long)executionCount < count)
                                    {
                                        inner.schedule(self);
                                        return;
                                    } else
                                    {
                                        child.onCompleted();
                                        return;
                                    }
                                }

                                public void onError(Throwable throwable)
                                {
                                    child.onError(throwable);
                                }

                                public void onNext(Object obj)
                                {
                                    child.onNext(obj);
                                }

                                final _cls1 this$2;
                                final rx.Scheduler.Inner val$inner;

            
            {
                this$2 = final__pcls1;
                inner = inner1;
                super(Subscriber.this);
            }
                            }
);
                        }

                        final Action1 self = this;
                        final _cls1 this$1;
                        final Observable val$t;

            
            {
                this$1 = final__pcls1;
                t = Observable.this;
                super();
            }
                    }
);
                }

                int executionCount;
                final OperatorRepeat this$0;
                final Subscriber val$child;

            
            {
                this$0 = OperatorRepeat.this;
                child = subscriber1;
                super(final_subscriber);
                executionCount = 0;
            }
            }
;
        }
    }

    private final long count;
    private final Scheduler scheduler;


}
