// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.*;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            OperationInterval

public final class OperationSample
{
    private static class Sample
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            final Subscription clockSubscription = Observable.create(OperationInterval.interval(period, unit, scheduler)).unsafeSubscribe(observer. new Subscriber() {

                public void onCompleted()
                {
                }

                public void onError(Throwable throwable)
                {
                }

                public void onNext(Long long1)
                {
                    if(hasValue.get())
                        observer.onNext(latestValue.get());
                }

                public volatile void onNext(Object obj)
                {
                    onNext((Long)obj);
                }

                final Sample this$0;
                final Observer val$observer;

            
            {
                this$0 = final_sample1;
                observer = Observer.this;
                Subscriber();
            }
            }
);
            return Subscriptions.create(source.unsafeSubscribe(observer. new Subscriber() {

                public void onCompleted()
                {
                    clockSubscription.unsubscribe();
                    observer.onCompleted();
                }

                public void onError(Throwable throwable)
                {
                    clockSubscription.unsubscribe();
                    observer.onError(throwable);
                }

                public void onNext(Object obj)
                {
                    latestValue.set(obj);
                    hasValue.set(true);
                }

                final Sample this$0;
                final Subscription val$clockSubscription;
                final Observer val$observer;

            
            {
                this$0 = final_sample1;
                clockSubscription = subscription;
                observer = Observer.this;
                Subscriber();
            }
            }
). new Action0() {

                public void call()
                {
                    clockSubscription.unsubscribe();
                    sourceSubscription.unsubscribe();
                }

                final Sample this$0;
                final Subscription val$clockSubscription;
                final Subscription val$sourceSubscription;

            
            {
                this$0 = final_sample1;
                clockSubscription = subscription;
                sourceSubscription = Subscription.this;
                Object();
            }
            }
);
        }

        private final AtomicBoolean hasValue;
        private final AtomicReference latestValue;
        private final long period;
        private final Scheduler scheduler;
        private final Observable source;
        private final TimeUnit unit;



        private Sample(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            hasValue = new AtomicBoolean();
            latestValue = new AtomicReference();
            source = observable;
            period = l;
            unit = timeunit;
            scheduler = scheduler1;
        }

        Sample(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler1, _cls1 _pcls1)
        {
            Sample(observable, l, timeunit, scheduler1);
        }
    }

    public static class SampleWithObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return (new ResultManager(observer)).init();
        }

        final Observable sampler;
        final Observable source;

        public SampleWithObservable(Observable observable, Observable observable1)
        {
            source = observable;
            sampler = observable1;
        }
    }

    class SampleWithObservable.ResultManager extends Subscriber
    {

        public Subscription init()
        {
            cancel.add(source.unsafeSubscribe(this));
            cancel.add(sampler.unsafeSubscribe(new Sampler()));
            return cancel;
        }

        public void onCompleted()
        {
            synchronized(guard)
            {
                if(!done)
                {
                    done = true;
                    observer.onCompleted();
                    cancel.unsubscribe();
                }
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            synchronized(guard)
            {
                if(!done)
                {
                    done = true;
                    observer.onError(throwable);
                    cancel.unsubscribe();
                }
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            synchronized(guard)
            {
                valueTaken = false;
                value = obj;
            }
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final CompositeSubscription cancel = new CompositeSubscription();
        boolean done;
        final Object guard = new Object();
        final Observer observer;
        final SampleWithObservable this$0;
        Object value;
        boolean valueTaken;

        public SampleWithObservable.ResultManager(Observer observer1)
        {
            this$0 = SampleWithObservable.this;
            Subscriber();
            valueTaken = true;
            observer = observer1;
        }
    }

    class SampleWithObservable.ResultManager.Sampler extends Subscriber
    {

        public void onCompleted()
        {
            SampleWithObservable.ResultManager.this.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            SampleWithObservable.ResultManager.this.onError(throwable);
        }

        public void onNext(Object obj)
        {
            synchronized(guard)
            {
                if(!valueTaken && !done)
                {
                    valueTaken = true;
                    observer.onNext(value);
                }
            }
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final SampleWithObservable.ResultManager this$1;

        SampleWithObservable.ResultManager.Sampler()
        {
            this$1 = SampleWithObservable.ResultManager.this;
            Subscriber();
        }
    }


    public OperationSample()
    {
    }

    public static rx.Observable.OnSubscribeFunc sample(Observable observable, long l, TimeUnit timeunit)
    {
        return new Sample(observable, l, timeunit, Schedulers.computation());
    }

    public static rx.Observable.OnSubscribeFunc sample(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new Sample(observable, l, timeunit, scheduler);
    }
}
