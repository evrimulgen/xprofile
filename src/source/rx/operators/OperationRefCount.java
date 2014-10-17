// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action0;
import rx.observables.ConnectableObservable;
import rx.observers.Subscribers;
import rx.subscriptions.Subscriptions;

public final class OperationRefCount
{
    private static class RefCount
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            Subscription subscription = innerConnectableObservable.unsafeSubscribe(Subscribers.from(observer));
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            int i;
            i = count;
            count = i + 1;
            if(i != 0)
                break MISSING_BLOCK_LABEL_49;
            connection = innerConnectableObservable.connect();
            obj;
            JVM INSTR monitorexit ;
            return Subscriptions.create(subscription. new Action0() {

                public void call()
                {
                    synchronized(gate)
                    {
                        if(int i = -1 + 
// JavaClassFileOutputException: get_constant: invalid tag

                final RefCount this$0;
                final Subscription val$subscription;

            
            {
                this$0 = final_refcount;
                subscription = Subscription.this;
                Object();
            }
            }
);
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private Subscription connection;
        private int count;
        private final Object gate = new Object();
        private final ConnectableObservable innerConnectableObservable;



/*
        static int access$106(RefCount refcount)
        {
            int i = -1 + refcount.count;
            refcount.count = i;
            return i;
        }

*/



/*
        static Subscription access$202(RefCount refcount, Subscription subscription)
        {
            refcount.connection = subscription;
            return subscription;
        }

*/

        public RefCount(ConnectableObservable connectableobservable)
        {
            count = 0;
            connection = null;
            innerConnectableObservable = connectableobservable;
        }
    }


    public OperationRefCount()
    {
    }

    public static rx.Observable.OnSubscribeFunc refCount(ConnectableObservable connectableobservable)
    {
        return new RefCount(connectableobservable);
    }
}
