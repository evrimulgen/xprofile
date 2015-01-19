// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;

public final class OperatorMerge
    implements rx.Observable.Operator
{
    final class InnerObserver extends Subscriber
    {

        private void cleanup()
        {
            class _cls1 extends Subscriber
            {

                public void onCompleted()
                {
                    completed = true;
                    if(runningCount.get() == 0)
                        o.onCompleted();
                }

                public void onError(Throwable throwable)
                {
                    o.onError(throwable);
                }

                public volatile void onNext(Object obj)
                {
                    onNext((Observable)obj);
                }

                public void onNext(Observable observable)
                {
                    runningCount.incrementAndGet();
                    InnerObserver innerobserver = new InnerObserver();
                    childrenSubscriptions.add(innerobserver);
                    observable.unsafeSubscribe(innerobserver);
                }

                private volatile boolean completed;
                private final AtomicInteger runningCount = new AtomicInteger();
                final OperatorMerge this$0;
                final CompositeSubscription val$childrenSubscriptions;
                final Subscriber val$o;



            
            {
                this$0 = OperatorMerge.this;
                o = subscriber1;
                childrenSubscriptions = compositesubscription;
                super(final_subscriber);
                completed = false;
            }
            }

            childrenSubscriptions.remove(this);
        }

        public void onCompleted()
        {
            if(!innerCompleted)
            {
                innerCompleted = true;
                if(runningCount.decrementAndGet() == 0 && completed)
                    o.onCompleted();
                cleanup();
            }
        }

        public void onError(Throwable throwable)
        {
            o.onError(throwable);
            cleanup();
        }

        public void onNext(Object obj)
        {
            o.onNext(obj);
        }

        private boolean innerCompleted;
        final _cls1 this$1;

        public InnerObserver()
        {
            this$1 = _cls1.this;
            super();
            innerCompleted = false;
        }
    }


    public OperatorMerge()
    {
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        final SerializedSubscriber o = new SerializedSubscriber(final_subscriber);
        CompositeSubscription compositesubscription = new CompositeSubscription();
        final_subscriber.add(compositesubscription);
        return new _cls1(compositesubscription);
    }
}
