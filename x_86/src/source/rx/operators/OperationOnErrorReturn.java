// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import rx.*;
import rx.exceptions.CompositeException;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public final class OperationOnErrorReturn
{
    private static class OnErrorReturn
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(final Observer observer)
        {
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
            AtomicReference atomicreference = new AtomicReference(safeobservablesubscription);
            safeobservablesubscription.wrap(originalSequence.unsafeSubscribe(atomicreference. new Subscriber() {

                public void onCompleted()
                {
                    observer.onCompleted();
                }

                public void onError(Throwable throwable)
                {
                    SafeObservableSubscription safeobservablesubscription;
                    safeobservablesubscription = (SafeObservableSubscription)subscriptionRef.get();
                    if(safeobservablesubscription == null)
                        break MISSING_BLOCK_LABEL_40;
                    onNext(resumeFunction.call(throwable));
                    onCompleted();
                    safeobservablesubscription.unsubscribe();
                    return;
                    Throwable throwable1;
                    throwable1;
                    observer.onError(new CompositeException("OnErrorReturn function failed", Arrays.asList(new Throwable[] {
                        throwable, throwable1
                    })));
                    return;
                }

                public void onNext(Object obj)
                {
                    observer.onNext(obj);
                }

                final OnErrorReturn this$0;
                final Observer val$observer;
                final AtomicReference val$subscriptionRef;

            
            {
                this$0 = final_onerrorreturn;
                observer = observer1;
                subscriptionRef = AtomicReference.this;
                Subscriber();
            }
            }
));
            return Subscriptions.create(atomicreference. new Action0() {

                public void call()
                {
                    Subscription subscription = (Subscription)subscriptionRef.getAndSet(null);
                    if(subscription != null)
                        subscription.unsubscribe();
                }

                final OnErrorReturn this$0;
                final AtomicReference val$subscriptionRef;

            
            {
                this$0 = final_onerrorreturn;
                subscriptionRef = AtomicReference.this;
                Object();
            }
            }
);
        }

        private final Observable originalSequence;
        private final Func1 resumeFunction;


        public OnErrorReturn(Observable observable, Func1 func1)
        {
            resumeFunction = func1;
            originalSequence = observable;
        }
    }


    public OperationOnErrorReturn()
    {
    }

    public static rx.Observable.OnSubscribeFunc onErrorReturn(Observable observable, Func1 func1)
    {
        return new OnErrorReturn(observable, func1);
    }
}
