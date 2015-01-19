// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.exceptions.CompositeException;
import rx.observers.SerializedObserver;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.CompositeSubscription;

public final class OperationMergeDelayError
{
    private static final class MergeDelayErrorObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            SerializedObserver serializedobserver = new SerializedObserver(observer);
            compositesubscription.add(sequences.unsafeSubscribe(new ParentObserver(serializedobserver)));
            return compositesubscription;
        }

        private final ConcurrentHashMap childObservers;
        private final ConcurrentHashMap childSubscriptions;
        private ConcurrentLinkedQueue onErrorReceived;
        private final MergeSubscription ourSubscription;
        private volatile boolean parentCompleted;
        private final Observable sequences;
        private AtomicBoolean stopped;





/*
        static boolean access$402(MergeDelayErrorObservable mergedelayerrorobservable, boolean flag)
        {
            mergedelayerrorobservable.parentCompleted = flag;
            return flag;
        }

*/




        private MergeDelayErrorObservable(Observable observable)
        {
            ourSubscription = new MergeSubscription();
            stopped = new AtomicBoolean(false);
            parentCompleted = false;
            childObservers = new ConcurrentHashMap();
            childSubscriptions = new ConcurrentHashMap();
            onErrorReceived = new ConcurrentLinkedQueue();
            sequences = observable;
        }

    }

    private class MergeDelayErrorObservable.ChildObserver extends Subscriber
    {

        private void finishObserver()
        {
label0:
            {
                if(childObservers.size() == 0 && parentCompleted && ourSubscription.stop())
                {
                    if(onErrorReceived.size() != 1)
                        break label0;
                    actualObserver.onError((Throwable)onErrorReceived.peek());
                }
                return;
            }
            if(onErrorReceived.size() > 1)
            {
                actualObserver.onError(new CompositeException(onErrorReceived));
                return;
            } else
            {
                actualObserver.onCompleted();
                return;
            }
        }

        public void onCompleted()
        {
            childObservers.remove(this);
            if(!stopped.get())
                finishObserver();
        }

        public void onError(Throwable throwable)
        {
            if(!stopped.get())
            {
                onErrorReceived.add(throwable);
                childObservers.remove(this);
                finished = true;
                finishObserver();
            }
        }

        public void onNext(Object obj)
        {
            if(!stopped.get() && !finished)
                actualObserver.onNext(obj);
        }

        private final Observer actualObserver;
        private volatile boolean finished;
        final MergeDelayErrorObservable this$0;

        public MergeDelayErrorObservable.ChildObserver(Observer observer)
        {
            this$0 = MergeDelayErrorObservable.this;
            super();
            finished = false;
            actualObserver = observer;
        }
    }

    private class MergeDelayErrorObservable.MergeSubscription
        implements Subscription
    {

        public boolean isUnsubscribed()
        {
            return stopped.get();
        }

        public boolean stop()
        {
            boolean flag = true;
            if(stopped.compareAndSet(false, flag))
            {
                for(Iterator iterator = childSubscriptions.values().iterator(); iterator.hasNext(); ((Subscription)iterator.next()).unsubscribe());
            } else
            {
                flag = false;
            }
            return flag;
        }

        public void unsubscribe()
        {
            stop();
        }

        final MergeDelayErrorObservable this$0;

        private MergeDelayErrorObservable.MergeSubscription()
        {
            this$0 = MergeDelayErrorObservable.this;
            super();
        }

    }

    private class MergeDelayErrorObservable.ParentObserver extends Subscriber
    {

        public void onCompleted()
        {
label0:
            {
                parentCompleted = true;
                if(childObservers.size() == 0 && !stopped.get() && ourSubscription.stop())
                {
                    if(onErrorReceived.size() != 1)
                        break label0;
                    actualObserver.onError((Throwable)onErrorReceived.peek());
                }
                return;
            }
            if(onErrorReceived.size() > 1)
            {
                actualObserver.onError(new CompositeException(onErrorReceived));
                return;
            } else
            {
                actualObserver.onCompleted();
                return;
            }
        }

        public void onError(Throwable throwable)
        {
            actualObserver.onError(throwable);
        }

        public volatile void onNext(Object obj)
        {
            onNext((Observable)obj);
        }

        public void onNext(Observable observable)
        {
            if(stopped.get())
                return;
            if(observable == null)
            {
                throw new IllegalArgumentException("Observable<T> can not be null.");
            } else
            {
                MergeDelayErrorObservable.ChildObserver childobserver = new MergeDelayErrorObservable.ChildObserver(actualObserver);
                childObservers.put(childobserver, childobserver);
                Subscription subscription = observable.unsafeSubscribe(childobserver);
                childSubscriptions.put(childobserver, subscription);
                return;
            }
        }

        private final Observer actualObserver;
        final MergeDelayErrorObservable this$0;

        public MergeDelayErrorObservable.ParentObserver(Observer observer)
        {
            this$0 = MergeDelayErrorObservable.this;
            super();
            actualObserver = observer;
        }
    }


    public OperationMergeDelayError()
    {
    }

    public static rx.Observable.OnSubscribeFunc mergeDelayError(List list)
    {
        return mergeDelayError(Observable.create(new rx.Observable.OnSubscribeFunc(list) {

            public Subscription onSubscribe(Observer observer)
            {
                Iterator iterator = sequences.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    Observable observable = (Observable)iterator.next();
                    if(s.isUnsubscribed())
                        break;
                    observer.onNext(observable);
                } while(true);
                if(!s.isUnsubscribed())
                    observer.onCompleted();
                return s;
            }

            private final BooleanSubscription s = new BooleanSubscription();
            final List val$sequences;

            
            {
                sequences = list;
                super();
            }
        }
));
    }

    public static rx.Observable.OnSubscribeFunc mergeDelayError(Observable observable)
    {
        return new rx.Observable.OnSubscribeFunc(observable) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new MergeDelayErrorObservable(sequences)).onSubscribe(observer);
            }

            final Observable val$sequences;

            
            {
                sequences = observable;
                super();
            }
        }
;
    }

    public static transient rx.Observable.OnSubscribeFunc mergeDelayError(Observable aobservable[])
    {
        return mergeDelayError(Observable.create(new rx.Observable.OnSubscribeFunc(aobservable) {

            public Subscription onSubscribe(Observer observer)
            {
                Observable aobservable1[] = sequences;
                int i = aobservable1.length;
                int j = 0;
                do
                {
                    if(j >= i)
                        break;
                    Observable observable = aobservable1[j];
                    if(s.isUnsubscribed())
                        break;
                    observer.onNext(observable);
                    j++;
                } while(true);
                if(!s.isUnsubscribed())
                    observer.onCompleted();
                return s;
            }

            private final BooleanSubscription s = new BooleanSubscription();
            final Observable val$sequences[];

            
            {
                sequences = aobservable;
                super();
            }
        }
));
    }
}
