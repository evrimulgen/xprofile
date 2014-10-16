// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.LinkedList;
import rx.*;
import rx.observers.SerializedObserver;
import rx.subscriptions.CompositeSubscription;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription, SafeObserver

public final class OperationMergeMaxConcurrent
{
    private static final class MergeObservable
        implements rx.Observable.OnSubscribeFunc
    {

        private boolean isStopped()
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            Exception exception;
            boolean flag;
            if(parentCompleted && activeObservableCount == 0 && pendingObservables.size() == 0)
                flag = true;
            else
                flag = false;
            obj;
            JVM INSTR monitorexit ;
            return flag;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public Subscription onSubscribe(Observer observer)
        {
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription(ourSubscription);
            SerializedObserver serializedobserver = new SerializedObserver(new SafeObserver(safeobservablesubscription, observer));
            ourSubscription.add(sequences.unsafeSubscribe(new ParentObserver(serializedobserver)));
            return safeobservablesubscription;
        }

        private volatile int activeObservableCount;
        private final Object gate;
        private final int maxConcurrent;
        private final CompositeSubscription ourSubscription;
        private volatile boolean parentCompleted;
        private final LinkedList pendingObservables;
        private final Observable sequences;


/*
        static boolean access$102(MergeObservable mergeobservable, boolean flag)
        {
            mergeobservable.parentCompleted = flag;
            return flag;
        }

*/






/*
        static int access$508(MergeObservable mergeobservable)
        {
            int i = mergeobservable.activeObservableCount;
            mergeobservable.activeObservableCount = i + 1;
            return i;
        }

*/


/*
        static int access$510(MergeObservable mergeobservable)
        {
            int i = mergeobservable.activeObservableCount;
            mergeobservable.activeObservableCount = i - 1;
            return i;
        }

*/



        private MergeObservable(Observable observable, int i)
        {
            ourSubscription = new CompositeSubscription();
            parentCompleted = false;
            pendingObservables = new LinkedList();
            activeObservableCount = 0;
            gate = new Object();
            sequences = observable;
            maxConcurrent = i;
        }

    }

    private class MergeObservable.ChildObserver extends Subscriber
    {

        public void onCompleted()
        {
            if(!ourSubscription.isUnsubscribed()) goto _L2; else goto _L1
_L1:
            return;
_L2:
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            Observable observable = (Observable)pendingObservables.poll();
            if(observable != null)
                break MISSING_BLOCK_LABEL_50;
            int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        public void onError(Throwable throwable)
        {
            serializedObserver.onError(throwable);
        }

        public void onNext(Object obj)
        {
            serializedObserver.onNext(obj);
        }

        private final SerializedObserver serializedObserver;
        final MergeObservable this$0;

        public MergeObservable.ChildObserver(SerializedObserver serializedobserver)
        {
            this$0 = MergeObservable.this;
            super();
            serializedObserver = serializedobserver;
        }
    }

    private class MergeObservable.ParentObserver extends Subscriber
    {

        public void onCompleted()
        {
            parentCompleted = true;
            while(ourSubscription.isUnsubscribed() || !isStopped()) 
                return;
            serializedObserver.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            serializedObserver.onError(throwable);
        }

        public volatile void onNext(Object obj)
        {
            onNext((Observable)obj);
        }

        public void onNext(Observable observable)
        {
            if(!ourSubscription.isUnsubscribed()) goto _L2; else goto _L1
_L1:
            return;
_L2:
            Observable observable1;
            if(observable == null)
                throw new IllegalArgumentException("Observable<T> can not be null.");
            observable1 = null;
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            if(activeObservableCount < maxConcurrent) goto _L4; else goto _L3
_L3:
            pendingObservables.add(observable);
_L6:
            if(observable1 != null)
            {
                ourSubscription.add(observable1.unsafeSubscribe(new MergeObservable.ChildObserver(serializedObserver)));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            observable1 = observable;
            int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private final SerializedObserver serializedObserver;
        final MergeObservable this$0;

        public MergeObservable.ParentObserver(SerializedObserver serializedobserver)
        {
            this$0 = MergeObservable.this;
            super();
            serializedObserver = serializedobserver;
        }
    }


    public OperationMergeMaxConcurrent()
    {
    }

    public static rx.Observable.OnSubscribeFunc merge(Observable observable, int i)
    {
        if(i <= 0)
            throw new IllegalArgumentException("maxConcurrent must be positive");
        else
            return new rx.Observable.OnSubscribeFunc(observable, i) {

                public Subscription onSubscribe(Observer observer)
                {
                    return (new MergeObservable(o, maxConcurrent)).onSubscribe(observer);
                }

                final int val$maxConcurrent;
                final Observable val$o;

            
            {
                o = observable;
                maxConcurrent = i;
                super();
            }
            }
;
    }
}
