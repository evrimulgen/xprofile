// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.*;
import rx.observables.ConnectableObservable;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            SafeObserver, SafeObservableSubscription

public class OperationMulticast
{
    private static class MulticastConnectableObservable extends ConnectableObservable
    {

        public Subscription connect()
        {
            synchronized(lock)
            {
                if(subscription == null)
                    subscription = source.unsafeSubscribe(new Subscriber() {

                        public void onCompleted()
                        {
                            subject.onCompleted();
                        }

                        public void onError(Throwable throwable)
                        {
                            subject.onError(throwable);
                        }

                        public void onNext(Object obj)
                        {
                            subject.onNext(obj);
                        }

                        final MulticastConnectableObservable this$0;

            
            {
                this$0 = MulticastConnectableObservable.this;
                Subscriber();
            }
                    }
);
            }
            return Subscriptions.create(new Action0() {

                public void call()
                {
                    synchronized(lock)
                    {
                        if(subscription != null)
                        {
                            subscription.unsubscribe();
                            subscription = null;
                        }
                    }
                    return;
                    exception;
                    obj;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                final MulticastConnectableObservable this$0;

            
            {
                this$0 = MulticastConnectableObservable.this;
                Object();
            }
            }
);
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private final Object lock = new Object();
        private final Observable source;
        private final Subject subject;
        private Subscription subscription;





/*
        static Subscription access$202(MulticastConnectableObservable multicastconnectableobservable, Subscription subscription1)
        {
            multicastconnectableobservable.subscription = subscription1;
            return subscription1;
        }

*/

        public MulticastConnectableObservable(Observable observable, final Subject subject)
        {
            ConnectableObservable(new _cls1());
            source = observable;
            this.subject = subject;
        }
    }

    private static final class MulticastSubscribeFunc
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            MulticastConnectableObservable multicastconnectableobservable;
            Observable observable;
            CompositeSubscription compositesubscription;
            try
            {
                Subject subject = (Subject)subjectFactory.call();
                multicastconnectableobservable = new MulticastConnectableObservable(source, subject);
                observable = (Observable)resultSelector.call(multicastconnectableobservable);
            }
            catch(Throwable throwable)
            {
                observer.onError(throwable);
                return Subscriptions.empty();
            }
            compositesubscription = new CompositeSubscription();
            compositesubscription.add(observable.unsafeSubscribe(new SafeObserver(new SafeObservableSubscription(compositesubscription), observer)));
            compositesubscription.add(multicastconnectableobservable.connect());
            return compositesubscription;
        }

        final Func1 resultSelector;
        final Observable source;
        final Func0 subjectFactory;

        public MulticastSubscribeFunc(Observable observable, Func0 func0, Func1 func1)
        {
            source = observable;
            subjectFactory = func0;
            resultSelector = func1;
        }
    }


    public OperationMulticast()
    {
    }

    public static Observable _mthmulticast(Observable observable, Func0 func0, Func1 func1)
    {
        return Observable.create(new MulticastSubscribeFunc(observable, func0, func1));
    }

    public static ConnectableObservable _mthmulticast(Observable observable, Subject subject)
    {
        return new MulticastConnectableObservable(observable, subject);
    }

    // Unreferenced inner class rx/operators/OperationMulticast$MulticastConnectableObservable$1

/* anonymous class */
    class MulticastConnectableObservable._cls1
        implements rx.Observable.OnSubscribe
    {

        public volatile void call(Object obj)
        {
            call((Subscriber)obj);
        }

        public void call(Subscriber subscriber)
        {
            subject.unsafeSubscribe(subscriber);
        }

        final Subject val$subject;

            
            {
                subject = subject1;
                Object();
            }
    }

}
