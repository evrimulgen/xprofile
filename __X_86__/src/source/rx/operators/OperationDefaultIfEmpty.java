// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public class OperationDefaultIfEmpty
{
    private static class DefaultIfEmpty
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(final Observer observer)
        {
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
            return safeobservablesubscription.wrap(source.unsafeSubscribe(safeobservablesubscription. new Subscriber() {

                public void onCompleted()
                {
                    if(hasEmitted)
                    {
                        observer.onCompleted();
                        return;
                    } else
                    {
                        observer.onNext(defaultValue);
                        observer.onCompleted();
                        return;
                    }
                }

                public void onError(Throwable throwable)
                {
                    observer.onError(throwable);
                }

                public void onNext(Object obj)
                {
                    try
                    {
                        hasEmitted = true;
                        observer.onNext(obj);
                        return;
                    }
                    catch(Throwable throwable)
                    {
                        observer.onError(throwable);
                    }
                    subscription.unsubscribe();
                }

                private volatile boolean hasEmitted;
                final DefaultIfEmpty this$0;
                final Observer val$observer;
                final SafeObservableSubscription val$subscription;

            
            {
                this$0 = final_defaultifempty;
                observer = observer1;
                subscription = SafeObservableSubscription.this;
                super();
                hasEmitted = false;
            }
            }
));
        }

        private final Object defaultValue;
        private final Observable source;


        private DefaultIfEmpty(Observable observable, Object obj)
        {
            source = observable;
            defaultValue = obj;
        }

    }


    public OperationDefaultIfEmpty()
    {
    }

    public static rx.Observable.OnSubscribeFunc defaultIfEmpty(Observable observable, Object obj)
    {
        return new DefaultIfEmpty(observable, obj);
    }
}
