// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.functions.Action0;
import rx.observers.Subscribers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public final class OperationConcat
{
    private static class Concat
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(final Observer observer)
        {
            final AtomicBoolean completedOrErred = new AtomicBoolean(false);
            AtomicBoolean atomicboolean = new AtomicBoolean(false);
            final ConcurrentLinkedQueue nextSequences = new ConcurrentLinkedQueue();
            final SafeObservableSubscription outerSubscription = new SafeObservableSubscription();
            final Observer reusableObserver = atomicboolean. new Observer() {

                public void onCompleted()
                {
                    Queue queue = nextSequences;
                    queue;
                    JVM INSTR monitorenter ;
                    if(!nextSequences.isEmpty())
                        break MISSING_BLOCK_LABEL_62;
                    innerSubscription = null;
                    if(allSequencesReceived.get() && completedOrErred.compareAndSet(false, true))
                        observer.onCompleted();
_L2:
                    return;
                    innerSubscription = new SafeObservableSubscription();
                    innerSubscription.wrap(((Observable)nextSequences.poll()).unsafeSubscribe(Subscribers.from(this)));
                    if(true) goto _L2; else goto _L1
_L1:
                    Exception exception;
                    exception;
                    queue;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                public void onError(Throwable throwable)
                {
                    if(completedOrErred.compareAndSet(false, true))
                    {
                        outerSubscription.unsubscribe();
                        observer.onError(throwable);
                    }
                }

                public void onNext(Object obj)
                {
                    observer.onNext(obj);
                }

                final Concat this$0;
                final AtomicBoolean val$allSequencesReceived;
                final AtomicBoolean val$completedOrErred;
                final Queue val$nextSequences;
                final Observer val$observer;
                final SafeObservableSubscription val$outerSubscription;

            
            {
                this$0 = final_concat1;
                observer = observer1;
                completedOrErred = atomicboolean;
                outerSubscription = safeobservablesubscription;
                nextSequences = queue;
                allSequencesReceived = AtomicBoolean.this;
                super();
            }
            }
;
            outerSubscription.wrap(sequences.unsafeSubscribe(atomicboolean. new Subscriber() {

                public void onCompleted()
                {
                    allSequencesReceived.set(true);
                    SafeObservableSubscription safeobservablesubscription;
                    synchronized(nextSequences)
                    {
                        safeobservablesubscription = innerSubscription;
                    }
                    if(safeobservablesubscription == null && completedOrErred.compareAndSet(false, true))
                        observer.onCompleted();
                    return;
                    exception;
                    queue;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                public void onError(Throwable throwable)
                {
                    if(completedOrErred.compareAndSet(false, true))
                    {
                        SafeObservableSubscription safeobservablesubscription;
                        synchronized(nextSequences)
                        {
                            safeobservablesubscription = innerSubscription;
                        }
                        if(safeobservablesubscription != null)
                            safeobservablesubscription.unsubscribe();
                        observer.onError(throwable);
                    }
                    return;
                    exception;
                    queue;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                public volatile void onNext(Object obj)
                {
                    onNext((Observable)obj);
                }

                public void onNext(Observable observable)
                {
                    Queue queue = nextSequences;
                    queue;
                    JVM INSTR monitorenter ;
                    if(innerSubscription != null)
                        break MISSING_BLOCK_LABEL_57;
                    innerSubscription = new SafeObservableSubscription();
                    innerSubscription.wrap(observable.unsafeSubscribe(Subscribers.from(reusableObserver)));
_L2:
                    return;
                    nextSequences.add(observable);
                    if(true) goto _L2; else goto _L1
_L1:
                    Exception exception;
                    exception;
                    queue;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                final Concat this$0;
                final AtomicBoolean val$allSequencesReceived;
                final AtomicBoolean val$completedOrErred;
                final Queue val$nextSequences;
                final Observer val$observer;
                final Observer val$reusableObserver;

            
            {
                this$0 = final_concat1;
                nextSequences = queue;
                reusableObserver = observer1;
                completedOrErred = atomicboolean;
                observer = observer2;
                allSequencesReceived = AtomicBoolean.this;
                super();
            }
            }
));
            return Subscriptions.create(outerSubscription. new Action0() {

                public void call()
                {
                    SafeObservableSubscription safeobservablesubscription;
                    synchronized(nextSequences)
                    {
                        safeobservablesubscription = innerSubscription;
                    }
                    if(safeobservablesubscription != null)
                        safeobservablesubscription.unsubscribe();
                    outerSubscription.unsubscribe();
                    return;
                    exception;
                    queue;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                final Concat this$0;
                final Queue val$nextSequences;
                final SafeObservableSubscription val$outerSubscription;

            
            {
                this$0 = final_concat1;
                nextSequences = queue;
                outerSubscription = SafeObservableSubscription.this;
                super();
            }
            }
);
        }

        private SafeObservableSubscription innerSubscription;
        private Observable sequences;



/*
        static SafeObservableSubscription access$002(Concat concat1, SafeObservableSubscription safeobservablesubscription)
        {
            concat1.innerSubscription = safeobservablesubscription;
            return safeobservablesubscription;
        }

*/

        public Concat(Observable observable)
        {
            innerSubscription = null;
            sequences = observable;
        }
    }


    public OperationConcat()
    {
    }

    public static rx.Observable.OnSubscribeFunc concat(Iterable iterable)
    {
        return concat(Observable.from(iterable));
    }

    public static rx.Observable.OnSubscribeFunc concat(Observable observable)
    {
        return new rx.Observable.OnSubscribeFunc(observable) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new Concat(sequences)).onSubscribe(observer);
            }

            final Observable val$sequences;

            
            {
                sequences = observable;
                super();
            }
        }
;
    }

    public static transient rx.Observable.OnSubscribeFunc concat(Observable aobservable[])
    {
        return concat(Observable.from(aobservable));
    }
}
