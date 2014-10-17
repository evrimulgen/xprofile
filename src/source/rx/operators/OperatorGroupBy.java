// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            BufferUntilSubscriber

public final class OperatorGroupBy
    implements rx.Observable.Operator
{

    public OperatorGroupBy(Func1 func1)
    {
        keySelector = func1;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(Subscriber subscriber)
    {
        return new Subscriber(subscriber) {

            private void completeInner()
            {
                if(completionCounter.decrementAndGet() <= 0 && (terminated.get() || childObserver.isUnsubscribed()) && completionEmitted.compareAndSet(false, true))
                {
                    if(childObserver.isUnsubscribed())
                        unsubscribe();
                    childObserver.onCompleted();
                }
            }

            public void onCompleted()
            {
                if(terminated.compareAndSet(false, true))
                {
                    for(Iterator iterator = groups.values().iterator(); iterator.hasNext(); ((BufferUntilSubscriber)iterator.next()).onCompleted());
                    if(completionCounter.get() == 0 && completionEmitted.compareAndSet(false, true))
                        childObserver.onCompleted();
                }
            }

            public void onError(Throwable throwable)
            {
                if(terminated.compareAndSet(false, true))
                    childObserver.onError(throwable);
            }

            public void onNext(Object obj)
            {
                Object obj1;
                BufferUntilSubscriber bufferuntilsubscriber;
                GroupedObservable groupedobservable;
                try
                {
                    obj1 = keySelector.call(obj);
                    bufferuntilsubscriber = (BufferUntilSubscriber)groups.get(obj1);
                }
                catch(Throwable throwable)
                {
                    onError(OnErrorThrowable.addValueAsLastCause(throwable, obj));
                    return;
                }
                if(bufferuntilsubscriber != null)
                    break MISSING_BLOCK_LABEL_92;
                if(childObserver.isUnsubscribed())
                    return;
                bufferuntilsubscriber = BufferUntilSubscriber.create();
                groupedobservable = new GroupedObservable(obj1, bufferuntilsubscriber. new rx.Observable.OnSubscribe() {

                    public volatile void call(Object obj)
                    {
                        call((Subscriber)obj);
                    }

                    public void call(Subscriber subscriber)
                    {
                        completionCounter.incrementAndGet();
                        subscriber.add(Subscriptions.create(new Action0() {

                            public void call()
                            {
                                completeInner();
                            }

                            final _cls1 this$2;

            
            {
                this$2 = _cls1.this;
                Object();
            }
                        }
));
                        _gps.unsafeSubscribe(subscriber. new Subscriber(subscriber) {

                            public void onCompleted()
                            {
                                o.onCompleted();
                                completeInner();
                            }

                            public void onError(Throwable throwable)
                            {
                                o.onError(throwable);
                            }

                            public void onNext(Object obj)
                            {
                                o.onNext(obj);
                            }

                            final _cls1 this$2;
                            final Subscriber val$o;

            
            {
                this$2 = final__pcls1;
                o = subscriber1;
                Subscriber(Subscriber.this);
            }
                        }
);
                    }

                    final _cls1 this$1;
                    final BufferUntilSubscriber val$_gps;

            
            {
                this$1 = final__pcls1;
                _gps = BufferUntilSubscriber.this;
                Object();
            }
                }
);
                groups.put(obj1, bufferuntilsubscriber);
                childObserver.onNext(groupedobservable);
                bufferuntilsubscriber.onNext(obj);
                return;
            }

            private final AtomicInteger completionCounter = new AtomicInteger(0);
            private final AtomicBoolean completionEmitted = new AtomicBoolean(false);
            private final Map groups = new HashMap();
            private final AtomicBoolean terminated = new AtomicBoolean(false);
            final OperatorGroupBy this$0;
            final Subscriber val$childObserver;



            
            {
                this$0 = OperatorGroupBy.this;
                childObserver = subscriber;
                Subscriber(final_compositesubscription);
            }
        }
;
    }

    final Func1 keySelector;
}
