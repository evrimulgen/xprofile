// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.*;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.SerializedObserver;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;

public final class OperationDebounce
{
    private static class Debounce
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return items.unsafeSubscribe(new DebounceObserver(observer, timeout, unit, scheduler));
        }

        private final Observable items;
        private final Scheduler scheduler;
        private final long timeout;
        private final TimeUnit unit;

        public Debounce(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            items = observable;
            timeout = l;
            unit = timeunit;
            scheduler = scheduler1;
        }
    }

    private static class DebounceObserver extends Subscriber
    {

        public void onCompleted()
        {
            ((Subscription)lastScheduledNotification.get()).unsubscribe();
            observer.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            ((Subscription)lastScheduledNotification.get()).unsubscribe();
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            Subscription subscription = (Subscription)lastScheduledNotification.getAndSet(scheduler.schedule(((_cls1) (obj)). new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    observer.onNext(v);
                }

                final DebounceObserver this$0;
                final Object val$v;

            
            {
                this$0 = final_debounceobserver;
                v = Object.this;
                super();
            }
            }
, timeout, unit));
            if(subscription != null)
                subscription.unsubscribe();
        }

        private final AtomicReference lastScheduledNotification = new AtomicReference();
        private final Observer observer;
        private final Scheduler scheduler;
        private final long timeout;
        private final TimeUnit unit;


        public DebounceObserver(Observer observer1, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            observer = new SerializedObserver(observer1);
            timeout = l;
            unit = timeunit;
            scheduler = scheduler1;
        }
    }

    private static final class DebounceSelector
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            compositesubscription.add(source.unsafeSubscribe(new SourceObserver(observer, debounceSelector, compositesubscription)));
            return compositesubscription;
        }

        final Func1 debounceSelector;
        final Observable source;

        public DebounceSelector(Observable observable, Func1 func1)
        {
            source = observable;
            debounceSelector = func1;
        }
    }

    private static final class DebounceSelector.DebounceObserver extends Subscriber
    {

        public void onCompleted()
        {
            synchronized(parent.guard)
            {
                if(parent.hasValue && parent.index == currentIndex)
                    parent.observer.onNext(value);
                parent.hasValue = false;
            }
            cancel.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            synchronized(parent.guard)
            {
                parent.observer.onError(throwable);
            }
            parent.cancel.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            onCompleted();
        }

        final Subscription cancel;
        final long currentIndex;
        final DebounceSelector.SourceObserver parent;
        final Object value;

        public DebounceSelector.DebounceObserver(DebounceSelector.SourceObserver sourceobserver, Subscription subscription, Object obj, long l)
        {
            parent = sourceobserver;
            cancel = subscription;
            value = obj;
            currentIndex = l;
        }
    }

    private static final class DebounceSelector.SourceObserver extends Subscriber
    {

        public void onCompleted()
        {
            ssub.unsubscribe();
            Object obj = guard;
            obj;
            JVM INSTR monitorenter ;
            boolean flag = hasValue;
            if(!flag)
                break MISSING_BLOCK_LABEL_38;
            observer.onNext(value);
            observer.onCompleted();
            hasValue = false;
            value = null;
            index = 1L + index;
            obj;
            JVM INSTR monitorexit ;
            cancel.unsubscribe();
            return;
            Throwable throwable;
            throwable;
            observer.onError(throwable);
            obj;
            JVM INSTR monitorexit ;
            cancel.unsubscribe();
            return;
            Exception exception1;
            exception1;
            obj;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            cancel.unsubscribe();
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            ssub.unsubscribe();
            synchronized(guard)
            {
                observer.onError(throwable);
                hasValue = false;
                value = null;
                index = 1L + index;
            }
            cancel.unsubscribe();
            return;
            exception1;
            obj;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            cancel.unsubscribe();
            throw exception;
        }

        public void onNext(Object obj)
        {
            Observable observable;
            long l;
            SerialSubscription serialsubscription;
            try
            {
                observable = (Observable)debounceSelector.call(obj);
            }
            catch(Throwable throwable)
            {
                synchronized(guard)
                {
                    observer.onError(throwable);
                }
                cancel.unsubscribe();
                return;
            }
            synchronized(guard)
            {
                hasValue = true;
                value = obj;
                l = 1L + index;
                index = l;
            }
            serialsubscription = new SerialSubscription();
            ssub.set(serialsubscription);
            serialsubscription.set(observable.unsafeSubscribe(new DebounceSelector.DebounceObserver(this, serialsubscription, obj, l)));
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
            exception1;
            obj2;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        final CompositeSubscription cancel;
        final Func1 debounceSelector;
        final Object guard = new Object();
        boolean hasValue;
        long index;
        final Observer observer;
        final SerialSubscription ssub = new SerialSubscription();
        Object value;

        public DebounceSelector.SourceObserver(Observer observer1, Func1 func1, CompositeSubscription compositesubscription)
        {
            observer = observer1;
            debounceSelector = func1;
            cancel = compositesubscription;
            cancel.add(ssub);
        }
    }


    public OperationDebounce()
    {
    }

    public static rx.Observable.OnSubscribeFunc debounce(Observable observable, long l, TimeUnit timeunit)
    {
        return debounce(observable, l, timeunit, Schedulers.computation());
    }

    public static rx.Observable.OnSubscribeFunc debounce(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(observable, l, timeunit, scheduler) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new Debounce(items, timeout, unit, scheduler)).onSubscribe(observer);
            }

            final Observable val$items;
            final Scheduler val$scheduler;
            final long val$timeout;
            final TimeUnit val$unit;

            
            {
                items = observable;
                timeout = l;
                unit = timeunit;
                scheduler = scheduler1;
                super();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc debounceSelector(Observable observable, Func1 func1)
    {
        return new DebounceSelector(observable, func1);
    }
}
