// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import rx.*;
import rx.functions.*;
import rx.observables.ConnectableObservable;
import rx.observers.Subscribers;
import rx.subscriptions.*;

public final class OperationDelay
{
    private static final class DelaySubscribeFunc
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            final SerialSubscription ssub = new SerialSubscription();
            ssub.set(scheduler.schedule(observer. new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    if(!ssub.isUnsubscribed())
                        ssub.set(source.unsafeSubscribe(Subscribers.from(t1)));
                }

                final DelaySubscribeFunc this$0;
                final SerialSubscription val$ssub;
                final Observer val$t1;

            
            {
                this$0 = final_delaysubscribefunc;
                ssub = serialsubscription;
                t1 = Observer.this;
                super();
            }
            }
, time, unit));
            return ssub;
        }

        final Scheduler scheduler;
        final Observable source;
        final long time;
        final TimeUnit unit;

        public DelaySubscribeFunc(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            source = observable;
            scheduler = scheduler1;
            time = l;
            unit = timeunit;
        }
    }

    private static final class DelayViaObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            SerialSubscription serialsubscription = new SerialSubscription();
            compositesubscription.add(serialsubscription);
            SourceObserver sourceobserver = new SourceObserver(observer, itemDelay, compositesubscription, serialsubscription);
            if(subscriptionDelay == null)
            {
                serialsubscription.set(source.unsafeSubscribe(sourceobserver));
                return compositesubscription;
            }
            Observable observable;
            SerialSubscription serialsubscription1;
            try
            {
                observable = (Observable)subscriptionDelay.call();
            }
            catch(Throwable throwable)
            {
                observer.onError(throwable);
                return Subscriptions.empty();
            }
            serialsubscription1 = new SerialSubscription();
            compositesubscription.add(serialsubscription1);
            serialsubscription1.set(observable.unsafeSubscribe(new SubscribeDelay(source, sourceobserver, compositesubscription, serialsubscription1)));
            return compositesubscription;
        }

        final Func1 itemDelay;
        final Observable source;
        final Func0 subscriptionDelay;

        public DelayViaObservable(Observable observable, Func0 func0, Func1 func1)
        {
            source = observable;
            subscriptionDelay = func0;
            itemDelay = func1;
        }
    }

    private static final class DelayViaObservable.DelayObserver extends Subscriber
    {

        public void onCompleted()
        {
            parent.emit(value, token);
        }

        public void onError(Throwable throwable)
        {
            parent.onError(throwable);
        }

        public void onNext(Object obj)
        {
            parent.emit(value, token);
        }

        final DelayViaObservable.SourceObserver parent;
        final Subscription token;
        final Object value;

        public DelayViaObservable.DelayObserver(Object obj, DelayViaObservable.SourceObserver sourceobserver, Subscription subscription)
        {
            value = obj;
            parent = sourceobserver;
            token = subscription;
        }
    }

    private static final class DelayViaObservable.SourceObserver extends Subscriber
    {

        boolean checkDone()
        {
            if(done && wip == 0)
            {
                observer.onCompleted();
                return true;
            } else
            {
                return false;
            }
        }

        void emit(Object obj, Subscription subscription)
        {
            boolean flag;
            synchronized(guard)
            {
                observer.onNext(obj);
                wip = -1 + wip;
                flag = checkDone();
            }
            if(flag)
            {
                csub.unsubscribe();
                return;
            } else
            {
                csub.remove(subscription);
                return;
            }
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onCompleted()
        {
            boolean flag;
            synchronized(guard)
            {
                done = true;
                flag = checkDone();
            }
            if(flag)
            {
                csub.unsubscribe();
                return;
            } else
            {
                self.unsubscribe();
                return;
            }
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            synchronized(guard)
            {
                observer.onError(throwable);
            }
            csub.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            Observable observable;
            SerialSubscription serialsubscription;
            try
            {
                observable = (Observable)itemDelay.call(obj);
            }
            catch(Throwable throwable)
            {
                onError(throwable);
                return;
            }
            synchronized(guard)
            {
                wip = 1 + wip;
            }
            serialsubscription = new SerialSubscription();
            csub.add(serialsubscription);
            serialsubscription.set(observable.unsafeSubscribe(new DelayViaObservable.DelayObserver(obj, this, serialsubscription)));
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final CompositeSubscription csub;
        boolean done;
        final Object guard = new Object();
        final Func1 itemDelay;
        final Observer observer;
        final SerialSubscription self;
        int wip;

        public DelayViaObservable.SourceObserver(Observer observer1, Func1 func1, CompositeSubscription compositesubscription, SerialSubscription serialsubscription)
        {
            observer = observer1;
            itemDelay = func1;
            csub = compositesubscription;
            self = serialsubscription;
        }
    }

    private static final class DelayViaObservable.SubscribeDelay extends Subscriber
    {

        public void onCompleted()
        {
            subscribed = true;
            csub.remove(self);
            so.self.set(source.unsafeSubscribe(so));
        }

        public void onError(Throwable throwable)
        {
            if(!subscribed)
            {
                so.observer.onError(throwable);
                csub.unsubscribe();
            }
        }

        public void onNext(Object obj)
        {
            onCompleted();
        }

        final CompositeSubscription csub;
        final Subscription self;
        final DelayViaObservable.SourceObserver so;
        final Observable source;
        boolean subscribed;

        public DelayViaObservable.SubscribeDelay(Observable observable, DelayViaObservable.SourceObserver sourceobserver, CompositeSubscription compositesubscription, Subscription subscription)
        {
            source = observable;
            so = sourceobserver;
            csub = compositesubscription;
            self = subscription;
        }
    }


    public OperationDelay()
    {
    }

    public static rx.Observable.OnSubscribeFunc delay(Observable observable, Func0 func0, Func1 func1)
    {
        return new DelayViaObservable(observable, func0, func1);
    }

    public static rx.Observable.OnSubscribeFunc delay(Observable observable, Func1 func1)
    {
        return new DelayViaObservable(observable, null, func1);
    }

    public static Observable delay(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return Observable.concat(observable.map(new Func1(l, timeunit, scheduler) {

            public volatile Object call(Object obj)
            {
                return call(obj);
            }

            public Observable call(Object obj)
            {
                ConnectableObservable connectableobservable = Observable.timer(delay, unit, scheduler).map(((_cls1) (obj)). new Func1() {

                    public Object call(Long long1)
                    {
                        return x;
                    }

                    public volatile Object call(Object obj)
                    {
                        return call((Long)obj);
                    }

                    final _cls1 this$0;
                    final Object val$x;

            
            {
                this$0 = final__pcls1;
                x = Object.this;
                super();
            }
                }
).replay();
                connectableobservable.connect();
                return connectableobservable;
            }

            final long val$delay;
            final Scheduler val$scheduler;
            final TimeUnit val$unit;

            
            {
                delay = l;
                unit = timeunit;
                scheduler = scheduler1;
                super();
            }
        }
));
    }

    public static rx.Observable.OnSubscribeFunc delaySubscription(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new DelaySubscribeFunc(observable, l, timeunit, scheduler);
    }
}
