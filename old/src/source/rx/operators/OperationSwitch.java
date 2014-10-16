// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public final class OperationSwitch
{
    private static class Switch
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
            SerialSubscription serialsubscription = new SerialSubscription();
            safeobservablesubscription.wrap(sequences.unsafeSubscribe(new SwitchObserver(observer, safeobservablesubscription, serialsubscription)));
            return new CompositeSubscription(new Subscription[] {
                safeobservablesubscription, serialsubscription
            });
        }

        private final Observable sequences;

        public Switch(Observable observable)
        {
            sequences = observable;
        }
    }

    private static class SwitchObserver extends Subscriber
    {

        public void onCompleted()
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            boolean flag;
            stopped = true;
            flag = hasLatest;
            SafeObservableSubscription safeobservablesubscription;
            safeobservablesubscription = null;
            if(flag)
                break MISSING_BLOCK_LABEL_39;
            observer.onCompleted();
            safeobservablesubscription = parent;
            obj;
            JVM INSTR monitorexit ;
            if(safeobservablesubscription != null)
                safeobservablesubscription.unsubscribe();
            return;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            synchronized(gate)
            {
                observer.onError(throwable);
            }
            parent.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public volatile void onNext(Object obj)
        {
            onNext((Observable)obj);
        }

        public void onNext(Observable observable)
        {
            final long id;
            synchronized(gate)
            {
                id = 1L + latest;
                latest = id;
                hasLatest = true;
            }
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
            safeobservablesubscription.wrap(observable.unsafeSubscribe(safeobservablesubscription. new Subscriber() {

                public void onCompleted()
                {
                    sub.unsubscribe();
                    Object obj = gate;
                    obj;
                    JVM INSTR monitorenter ;
                    int i = latest != id;
                    SafeObservableSubscription safeobservablesubscription;
                    safeobservablesubscription = null;
                    if(i != 0)
                        break MISSING_BLOCK_LABEL_84;
                    boolean flag;
                    hasLatest = false;
                    flag = stopped;
                    safeobservablesubscription = null;
                    if(!flag)
                        break MISSING_BLOCK_LABEL_84;
                    observer.onCompleted();
                    safeobservablesubscription = parent;
                    obj;
                    JVM INSTR monitorexit ;
                    if(safeobservablesubscription != null)
                        safeobservablesubscription.unsubscribe();
                    return;
                    Exception exception;
                    exception;
                    obj;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                public void onError(Throwable throwable)
                {
                    sub.unsubscribe();
                    Object obj = gate;
                    obj;
                    JVM INSTR monitorenter ;
                    int i = latest != id;
                    SafeObservableSubscription safeobservablesubscription;
                    safeobservablesubscription = null;
                    if(i != 0)
                        break MISSING_BLOCK_LABEL_61;
                    observer.onError(throwable);
                    safeobservablesubscription = parent;
                    obj;
                    JVM INSTR monitorexit ;
                    if(safeobservablesubscription != null)
                        safeobservablesubscription.unsubscribe();
                    return;
                    Exception exception;
                    exception;
                    obj;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                public void onNext(Object obj)
                {
                    synchronized(gate)
                    {
                        if(latest == id)
                            observer.onNext(obj);
                    }
                    return;
                    exception;
                    obj1;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                final SwitchObserver this$0;
                final long val$id;
                final SafeObservableSubscription val$sub;

            
            {
                this$0 = final_switchobserver;
                id = l;
                sub = SafeObservableSubscription.this;
                Subscriber();
            }
            }
));
            child.setSubscription(safeobservablesubscription);
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private final SerialSubscription child;
        private final Object gate = new Object();
        private boolean hasLatest;
        private long latest;
        private final Observer observer;
        private final SafeObservableSubscription parent;
        private boolean stopped;






/*
        static boolean access$402(SwitchObserver switchobserver, boolean flag)
        {
            switchobserver.hasLatest = flag;
            return flag;
        }

*/


        public SwitchObserver(Observer observer1, SafeObservableSubscription safeobservablesubscription, SerialSubscription serialsubscription)
        {
            observer = observer1;
            parent = safeobservablesubscription;
            child = serialsubscription;
        }
    }


    public OperationSwitch()
    {
    }

    public static rx.Observable.OnSubscribeFunc switchDo(Observable observable)
    {
        return new rx.Observable.OnSubscribeFunc(observable) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new Switch(sequences)).onSubscribe(observer);
            }

            final Observable val$sequences;

            
            {
                sequences = observable;
                Object();
            }
        }
;
    }
}
