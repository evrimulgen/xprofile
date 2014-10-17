// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import rx.*;
import rx.functions.Action1;

// Referenced classes of package rx.operators:
//            OperatorTimeoutBase

public final class OperatorTimeout extends OperatorTimeoutBase
{

    public OperatorTimeout(final long timeout, final TimeUnit timeUnit, Observable observable, final Scheduler scheduler)
    {
        super(new OperatorTimeoutBase.FirstTimeoutStub() {

            public volatile Object call(Object obj, Object obj1)
            {
                return call((OperatorTimeoutBase.TimeoutSubscriber)obj, (Long)obj1);
            }

            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber timeoutSubscriber, Long long1)
            {
                return scheduler.schedule(long1. new Action1() {

                    public volatile void call(Object obj)
                    {
                        call((rx.Scheduler.Inner)obj);
                    }

                    public void call(rx.Scheduler.Inner inner)
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
, timeout, timeUnit);
            }

            final Scheduler val$scheduler;
            final TimeUnit val$timeUnit;
            final long val$timeout;

            
            {
                scheduler = scheduler1;
                timeout = l;
                timeUnit = timeunit;
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
                return scheduler.schedule(long1. new Action1() {

                    public volatile void call(Object obj)
                    {
                        call((rx.Scheduler.Inner)obj);
                    }

                    public void call(rx.Scheduler.Inner inner)
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
, timeout, timeUnit);
            }

            final Scheduler val$scheduler;
            final TimeUnit val$timeUnit;
            final long val$timeout;

            
            {
                scheduler = scheduler1;
                timeout = l;
                timeUnit = timeunit;
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
