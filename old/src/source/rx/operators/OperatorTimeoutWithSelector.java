// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            OperatorTimeoutBase

public class OperatorTimeoutWithSelector extends OperatorTimeoutBase
{

    public OperatorTimeoutWithSelector(final Func0 firstTimeoutSelector, final Func1 timeoutSelector, Observable observable)
    {
        super(new OperatorTimeoutBase.FirstTimeoutStub() {

            public volatile Object call(Object obj, Object obj1)
            {
                return call((OperatorTimeoutBase.TimeoutSubscriber)obj, (Long)obj1);
            }

            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber timeoutSubscriber, Long long1)
            {
                if(firstTimeoutSelector != null)
                {
                    Observable observable1;
                    try
                    {
                        observable1 = (Observable)firstTimeoutSelector.call();
                    }
                    catch(Throwable throwable)
                    {
                        Exceptions.throwIfFatal(throwable);
                        timeoutSubscriber.onError(throwable);
                        return Subscriptions.empty();
                    }
                    return observable1.unsafeSubscribe(long1. new Subscriber() {

                        public void onCompleted()
                        {
                            timeoutSubscriber.onTimeout(seqId.longValue());
                        }

                        public void onError(Throwable throwable)
                        {
                            timeoutSubscriber.onError(throwable);
                        }

                        public void onNext(Object obj)
                        {
                            timeoutSubscriber.onTimeout(seqId.longValue());
                        }

                        final _cls1 this$1;
                        final Long val$seqId;
                        final OperatorTimeoutBase.TimeoutSubscriber val$timeoutSubscriber;

            
            {
                this$1 = final__pcls1;
                timeoutSubscriber = timeoutsubscriber;
                seqId = Long.this;
                super();
            }
                    }
);
                } else
                {
                    return Subscriptions.empty();
                }
            }

            final Func0 val$firstTimeoutSelector;

            
            {
                firstTimeoutSelector = func0;
                super();
            }
        }
, new OperatorTimeoutBase.TimeoutStub() {

            public volatile Object call(Object obj, Object obj1, Object obj2)
            {
                return call((OperatorTimeoutBase.TimeoutSubscriber)obj, (Long)obj1, obj2);
            }

            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber timeoutSubscriber, Long long1, Object obj)
            {
                Observable observable1;
                try
                {
                    observable1 = (Observable)timeoutSelector.call(obj);
                }
                catch(Throwable throwable)
                {
                    Exceptions.throwIfFatal(throwable);
                    timeoutSubscriber.onError(throwable);
                    return Subscriptions.empty();
                }
                return observable1.unsafeSubscribe(long1. new Subscriber() {

                    public void onCompleted()
                    {
                        timeoutSubscriber.onTimeout(seqId.longValue());
                    }

                    public void onError(Throwable throwable)
                    {
                        timeoutSubscriber.onError(throwable);
                    }

                    public void onNext(Object obj)
                    {
                        timeoutSubscriber.onTimeout(seqId.longValue());
                    }

                    final _cls2 this$1;
                    final Long val$seqId;
                    final OperatorTimeoutBase.TimeoutSubscriber val$timeoutSubscriber;

            
            {
                this$1 = final__pcls2;
                timeoutSubscriber = timeoutsubscriber;
                seqId = Long.this;
                super();
            }
                }
);
            }

            final Func1 val$timeoutSelector;

            
            {
                timeoutSelector = func1;
                super();
            }
        }
, observable);
    }

    public volatile Subscriber call(Subscriber subscriber)
    {
        return super.call(subscriber);
    }
}
