// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.*;
import rx.functions.*;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;

public final class OperationFlatMap
{
    private static final class FlatMapPairSelector
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            compositesubscription.add(source.unsafeSubscribe(new SourceObserver(observer, collectionSelector, resultSelector, compositesubscription)));
            return compositesubscription;
        }

        final Func1 collectionSelector;
        final Func2 resultSelector;
        final Observable source;

        public FlatMapPairSelector(Observable observable, Func1 func1, Func2 func2)
        {
            source = observable;
            collectionSelector = func1;
            resultSelector = func2;
        }
    }

    private static final class FlatMapPairSelector.CollectionObserver extends Subscriber
    {

        public void onCompleted()
        {
            so.complete(cancel);
        }

        public void onError(Throwable throwable)
        {
            so.onError(throwable);
        }

        public void onNext(Object obj)
        {
            so.emit(value, obj);
        }

        final Subscription cancel;
        final FlatMapPairSelector.SourceObserver so;
        final Object value;

        public FlatMapPairSelector.CollectionObserver(FlatMapPairSelector.SourceObserver sourceobserver, Object obj, Subscription subscription)
        {
            so = sourceobserver;
            value = obj;
            cancel = subscription;
        }
    }

    private static final class FlatMapPairSelector.SourceObserver extends Subscriber
    {

        void complete(Subscription subscription)
        {
            csub.remove(subscription);
            onCompleted();
        }

        void emit(Object obj, Object obj1)
        {
            Object obj2;
label0:
            {
                try
                {
                    obj2 = resultSelector.call(obj, obj1);
                }
                catch(Throwable throwable)
                {
                    onError(throwable);
                    return;
                }
                synchronized(guard)
                {
                    if(!done)
                        break label0;
                }
                return;
            }
            observer.onNext(obj2);
            obj3;
            JVM INSTR monitorexit ;
            return;
            exception;
            obj3;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onCompleted()
        {
label0:
            {
                if(wip.decrementAndGet() != 0)
                    break MISSING_BLOCK_LABEL_56;
                synchronized(guard)
                {
                    if(!done)
                        break label0;
                }
                return;
            }
            done = true;
            observer.onCompleted();
            obj;
            JVM INSTR monitorexit ;
            csub.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
label0:
            {
                synchronized(guard)
                {
                    if(!done)
                        break label0;
                }
                return;
            }
            done = true;
            observer.onError(throwable);
            obj;
            JVM INSTR monitorexit ;
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
                observable = (Observable)collectionSelector.call(obj);
            }
            catch(Throwable throwable)
            {
                onError(throwable);
                return;
            }
            serialsubscription = new SerialSubscription();
            csub.add(serialsubscription);
            wip.incrementAndGet();
            serialsubscription.set(observable.unsafeSubscribe(new FlatMapPairSelector.CollectionObserver(this, obj, serialsubscription)));
        }

        final Func1 collectionSelector;
        final CompositeSubscription csub;
        boolean done;
        final Object guard = new Object();
        final Observer observer;
        final Func2 resultSelector;
        final AtomicInteger wip = new AtomicInteger(1);

        public FlatMapPairSelector.SourceObserver(Observer observer1, Func1 func1, Func2 func2, CompositeSubscription compositesubscription)
        {
            observer = observer1;
            collectionSelector = func1;
            resultSelector = func2;
            csub = compositesubscription;
        }
    }

    private static final class FlatMapTransform
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            compositesubscription.add(source.unsafeSubscribe(new SourceObserver(observer, onNext, onError, onCompleted, compositesubscription)));
            return compositesubscription;
        }

        final Func0 onCompleted;
        final Func1 onError;
        final Func1 onNext;
        final Observable source;

        public FlatMapTransform(Observable observable, Func1 func1, Func1 func1_1, Func0 func0)
        {
            source = observable;
            onNext = func1;
            onError = func1_1;
            onCompleted = func0;
        }
    }

    private static final class FlatMapTransform.CollectionObserver extends Subscriber
    {

        public void onCompleted()
        {
            parent.csub.remove(cancel);
            parent.finish();
        }

        public void onError(Throwable throwable)
        {
            synchronized(parent.guard)
            {
                parent.observer.onError(throwable);
            }
            parent.csub.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            synchronized(parent.guard)
            {
                parent.observer.onNext(obj);
            }
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final Subscription cancel;
        final FlatMapTransform.SourceObserver parent;

        public FlatMapTransform.CollectionObserver(FlatMapTransform.SourceObserver sourceobserver, Subscription subscription)
        {
            parent = sourceobserver;
            cancel = subscription;
        }
    }

    private static final class FlatMapTransform.SourceObserver extends Subscriber
    {

        void finish()
        {
            if(wip.decrementAndGet() == 0)
            {
                synchronized(guard)
                {
                    observer.onCompleted();
                }
                csub.unsubscribe();
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onCompleted()
        {
            Observable observable;
            try
            {
                observable = (Observable)onCompleted.call();
            }
            catch(Throwable throwable)
            {
                synchronized(guard)
                {
                    observer.onError(throwable);
                }
                csub.unsubscribe();
                return;
            }
            subscribeInner(observable);
            done = true;
            finish();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            Observable observable;
            try
            {
                observable = (Observable)onError.call(throwable);
            }
            catch(Throwable throwable1)
            {
                synchronized(guard)
                {
                    observer.onError(throwable1);
                }
                csub.unsubscribe();
                return;
            }
            subscribeInner(observable);
            done = true;
            finish();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            Observable observable;
            try
            {
                observable = (Observable)onNext.call(obj);
            }
            catch(Throwable throwable)
            {
                synchronized(guard)
                {
                    observer.onError(throwable);
                }
                csub.unsubscribe();
                return;
            }
            subscribeInner(observable);
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        void subscribeInner(Observable observable)
        {
            SerialSubscription serialsubscription = new SerialSubscription();
            wip.incrementAndGet();
            csub.add(serialsubscription);
            serialsubscription.set(observable.unsafeSubscribe(new FlatMapTransform.CollectionObserver(this, serialsubscription)));
        }

        final CompositeSubscription csub;
        volatile boolean done;
        final Object guard = new Object();
        final Observer observer;
        final Func0 onCompleted;
        final Func1 onError;
        final Func1 onNext;
        final AtomicInteger wip = new AtomicInteger(1);

        public FlatMapTransform.SourceObserver(Observer observer1, Func1 func1, Func1 func1_1, Func0 func0, CompositeSubscription compositesubscription)
        {
            observer = observer1;
            onNext = func1;
            onError = func1_1;
            onCompleted = func0;
            csub = compositesubscription;
        }
    }

    private static final class IterableToObservableFunc
        implements Func1
    {

        public volatile Object call(Object obj)
        {
            return call(obj);
        }

        public Observable call(Object obj)
        {
            return Observable.from((Iterable)func.call(obj));
        }

        final Func1 func;

        public IterableToObservableFunc(Func1 func1)
        {
            func = func1;
        }
    }


    private OperationFlatMap()
    {
        throw new IllegalStateException("No instances!");
    }

    public static rx.Observable.OnSubscribeFunc flatMap(Observable observable, Func1 func1, Func1 func1_1, Func0 func0)
    {
        return new FlatMapTransform(observable, func1, func1_1, func0);
    }

    public static rx.Observable.OnSubscribeFunc flatMap(Observable observable, Func1 func1, Func2 func2)
    {
        return new FlatMapPairSelector(observable, func1, func2);
    }

    public static Func1 flatMapIterableFunc(Func1 func1)
    {
        return new IterableToObservableFunc(func1);
    }
}
