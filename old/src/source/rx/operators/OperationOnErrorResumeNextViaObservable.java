// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.*;
import rx.functions.Action0;
import rx.observers.Subscribers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public final class OperationOnErrorResumeNextViaObservable
{
    private static class OnErrorResumeNextViaObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            final SafeObservableSubscription subscription = new SafeObservableSubscription();
            final AtomicReference subscriptionRef = new AtomicReference(subscription);
            subscription.wrap(originalSequence.unsafeSubscribe(observer. new Subscriber() {

                public void onCompleted()
                {
                    if(subscriptionRef.get() == subscription)
                        observer.onCompleted();
                }

                public void onError(Throwable throwable)
                {
                    SafeObservableSubscription safeobservablesubscription = (SafeObservableSubscription)subscriptionRef.get();
                    if(safeobservablesubscription == subscription)
                    {
                        SafeObservableSubscription safeobservablesubscription1 = new SafeObservableSubscription(resumeSequence.unsafeSubscribe(Subscribers.from(observer)));
                        if(!subscriptionRef.compareAndSet(safeobservablesubscription, safeobservablesubscription1))
                            safeobservablesubscription1.unsubscribe();
                    }
                }

                public void onNext(Object obj)
                {
                    if(subscriptionRef.get() == subscription)
                        observer.onNext(obj);
                }

                final OnErrorResumeNextViaObservable this$0;
                final Observer val$observer;
                final SafeObservableSubscription val$subscription;
                final AtomicReference val$subscriptionRef;

            
            {
                this$0 = final_onerrorresumenextviaobservable;
                subscriptionRef = atomicreference;
                subscription = safeobservablesubscription;
                observer = Observer.this;
                Subscriber();
            }
            }
));
            return Subscriptions.create(subscriptionRef. new Action0() {

                public void call()
                {
                    Subscription subscription = (Subscription)subscriptionRef.getAndSet(null);
                    if(subscription != null)
                        subscription.unsubscribe();
                }

                final OnErrorResumeNextViaObservable this$0;
                final AtomicReference val$subscriptionRef;

            
            {
                this$0 = final_onerrorresumenextviaobservable;
                subscriptionRef = AtomicReference.this;
                Object();
            }
            }
);
        }

        private final Observable originalSequence;
        private final Observable resumeSequence;


        public OnErrorResumeNextViaObservable(Observable observable, Observable observable1)
        {
            resumeSequence = observable1;
            originalSequence = observable;
        }
    }


    public OperationOnErrorResumeNextViaObservable()
    {
    }

    public static rx.Observable.OnSubscribeFunc onErrorResumeNextViaObservable(Observable observable, Observable observable1)
    {
        return new OnErrorResumeNextViaObservable(observable, observable1);
    }
}
