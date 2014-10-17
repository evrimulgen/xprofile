// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observers.Subscribers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription, SafeObserver

public class OperationUsing
{

    public OperationUsing()
    {
    }

    public static rx.Observable.OnSubscribeFunc using(Func0 func0, Func1 func1)
    {
        return new rx.Observable.OnSubscribeFunc(func0, func1) {

            public Subscription onSubscribe(Observer observer)
            {
                Subscription subscription = Subscriptions.empty();
                Subscription subscription1;
                Observable observable;
                SafeObservableSubscription safeobservablesubscription;
                Subscription asubscription[];
                SafeObservableSubscription safeobservablesubscription1;
                try
                {
                    subscription1 = (Subscription)resourceFactory.call();
                }
                catch(Throwable throwable)
                {
                    subscription.unsubscribe();
                    return Observable.error(throwable).unsafeSubscribe(Subscribers.from(observer));
                }
                if(subscription1 != null)
                    subscription = subscription1;
                observable = (Observable)observableFactory.call(subscription1);
                safeobservablesubscription = new SafeObservableSubscription();
                asubscription = new Subscription[2];
                asubscription[0] = observable.unsafeSubscribe(new SafeObserver(safeobservablesubscription, observer));
                asubscription[1] = subscription;
                safeobservablesubscription1 = safeobservablesubscription.wrap(new CompositeSubscription(asubscription));
                return safeobservablesubscription1;
            }

            final Func1 val$observableFactory;
            final Func0 val$resourceFactory;

            
            {
                resourceFactory = func0;
                observableFactory = func1;
                Object();
            }
        }
;
    }
}
