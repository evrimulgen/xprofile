// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.*;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public final class OperationTakeTimed
{
    private static class Take
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            if(num < 1)
            {
                items.unsafeSubscribe(new Subscriber() {

                    public void onCompleted()
                    {
                    }

                    public void onError(Throwable throwable)
                    {
                    }

                    public void onNext(Object obj)
                    {
                    }

                    final Take this$0;

            
            {
                this$0 = Take.this;
                Subscriber();
            }
                }
).unsubscribe();
                observer.onCompleted();
                return Subscriptions.empty();
            } else
            {
                return subscription.wrap(items.unsafeSubscribe(new ItemObserver(observer)));
            }
        }

        private final Observable items;
        private final int num;
        private final SafeObservableSubscription subscription;



        private Take(Observable observable, int i)
        {
            subscription = new SafeObservableSubscription();
            items = observable;
            num = i;
        }

    }

    private class Take.ItemObserver extends Subscriber
    {

        public void onCompleted()
        {
            while(hasEmitedError || counter.getAndSet(num) >= num) 
                return;
            observer.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            while(hasEmitedError || counter.getAndSet(num) >= num) 
                return;
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            if(!hasEmitedError) goto _L2; else goto _L1
_L1:
            return;
_L2:
            int i = counter.incrementAndGet();
            if(i > num)
                continue; /* Loop/switch isn't completed */
            try
            {
                observer.onNext(obj);
            }
            catch(Throwable throwable)
            {
                hasEmitedError = true;
                observer.onError(throwable);
                subscription.unsubscribe();
                return;
            }
            if(i == num)
                observer.onCompleted();
            if(i < num) goto _L1; else goto _L3
_L3:
            subscription.unsubscribe();
            return;
        }

        private final AtomicInteger counter = new AtomicInteger();
        private volatile boolean hasEmitedError;
        private final Observer observer;
        final Take this$0;

        public Take.ItemObserver(Observer observer1)
        {
            this$0 = Take.this;
            Subscriber();
            hasEmitedError = false;
            observer = observer1;
        }
    }

    public static final class TakeTimed
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
            SafeObservableSubscription safeobservablesubscription1 = new SafeObservableSubscription();
            CompositeSubscription compositesubscription = new CompositeSubscription(new Subscription[] {
                safeobservablesubscription, safeobservablesubscription1
            });
            SourceObserver sourceobserver = new SourceObserver(observer, compositesubscription);
            safeobservablesubscription1.wrap(source.unsafeSubscribe(sourceobserver));
            if(!safeobservablesubscription1.isUnsubscribed())
                safeobservablesubscription.wrap(scheduler.schedule(sourceobserver, time, unit));
            return compositesubscription;
        }

        final Scheduler scheduler;
        final Observable source;
        final long time;
        final TimeUnit unit;

        public TakeTimed(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            source = observable;
            time = l;
            unit = timeunit;
            scheduler = scheduler1;
        }
    }

    private static final class TakeTimed.SourceObserver extends Subscriber
        implements Action1
    {

        public volatile void call(Object obj)
        {
            call((rx.Scheduler.Inner)obj);
        }

        public void call(rx.Scheduler.Inner inner)
        {
            onCompleted();
        }

        public void onCompleted()
        {
            int i;
            do
            {
                i = state.get();
                if(i == 2)
                    return;
            } while(i == 1 || !state.compareAndSet(i, 2));
            observer.onCompleted();
            cancel.unsubscribe();
            return;
            Exception exception;
            exception;
            cancel.unsubscribe();
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            int i;
            do
            {
                i = state.get();
                if(i == 2)
                    return;
            } while(i == 1 || !state.compareAndSet(i, 2));
            observer.onError(throwable);
            cancel.unsubscribe();
            return;
            Exception exception;
            exception;
            cancel.unsubscribe();
            throw exception;
        }

        public void onNext(Object obj)
        {
            int i;
            do
            {
                i = state.get();
                if(i == 2)
                    return;
            } while(!state.compareAndSet(i, 1));
            observer.onNext(obj);
            state.set(0);
            return;
            Exception exception;
            exception;
            state.set(0);
            return;
        }

        static final int ACTIVE = 0;
        static final int DONE = 2;
        static final int NEXT = 1;
        final Subscription cancel;
        final Observer observer;
        final AtomicInteger state = new AtomicInteger();

        public TakeTimed.SourceObserver(Observer observer1, Subscription subscription)
        {
            observer = observer1;
            cancel = subscription;
        }
    }


    public OperationTakeTimed()
    {
    }

    public static rx.Observable.OnSubscribeFunc take(Observable observable, int i)
    {
        return new rx.Observable.OnSubscribeFunc(observable, i) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new Take(items, num)).onSubscribe(observer);
            }

            final Observable val$items;
            final int val$num;

            
            {
                items = observable;
                num = i;
                Object();
            }
        }
;
    }
}
