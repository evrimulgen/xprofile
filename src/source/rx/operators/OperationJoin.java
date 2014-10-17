// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import rx.*;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;

public class OperationJoin
    implements rx.Observable.OnSubscribeFunc
{
    class ResultSink
    {

        public Subscription run()
        {
            SerialSubscription serialsubscription = new SerialSubscription();
            SerialSubscription serialsubscription1 = new SerialSubscription();
            group.add(serialsubscription);
            group.add(serialsubscription1);
            serialsubscription.setSubscription(left.unsafeSubscribe(new LeftObserver(serialsubscription)));
            serialsubscription1.setSubscription(right.unsafeSubscribe(new RightObserver(serialsubscription1)));
            return group;
        }

        final Subscription cancel;
        final Object gate = new Object();
        final CompositeSubscription group = new CompositeSubscription();
        boolean leftDone;
        int leftId;
        final Map leftMap = new HashMap();
        final Observer observer;
        boolean rightDone;
        int rightId;
        final Map rightMap = new HashMap();
        final OperationJoin this$0;

        public ResultSink(Observer observer1, Subscription subscription)
        {
            this$0 = OperationJoin.this;
            super();
            observer = observer1;
            cancel = subscription;
        }
    }

    class ResultSink.LeftObserver extends Subscriber
    {

        protected void expire(int i, Subscription subscription)
        {
            synchronized(gate)
            {
                if(leftMap.remove(Integer.valueOf(i)) != null && leftMap.isEmpty() && leftDone)
                {
                    observer.onCompleted();
                    cancel.unsubscribe();
                }
            }
            group.remove(subscription);
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onCompleted()
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            leftDone = true;
            if(!rightDone && !leftMap.isEmpty())
                break MISSING_BLOCK_LABEL_70;
            observer.onCompleted();
            cancel.unsubscribe();
_L2:
            return;
            self.unsubscribe();
            if(true) goto _L2; else goto _L1
_L1:
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
                cancel.unsubscribe();
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            Object obj2;
            int i;
            int j;
            synchronized(gate)
            {
                ResultSink resultsink = ResultSink.this;
                i = resultsink.leftId;
                resultsink.leftId = i + 1;
                leftMap.put(Integer.valueOf(i), obj);
                j = rightId;
            }
            SerialSubscription serialsubscription = new SerialSubscription();
            group.add(serialsubscription);
            Observable observable;
            Exception exception1;
            Iterator iterator;
            java.util.Map.Entry entry;
            Object obj3;
            Object obj4;
            try
            {
                observable = (Observable)leftDurationSelector.call(obj);
            }
            catch(Throwable throwable)
            {
                observer.onError(throwable);
                cancel.unsubscribe();
                return;
            }
            serialsubscription.setSubscription(observable.unsafeSubscribe(new LeftDurationObserver(i, serialsubscription)));
            obj2 = gate;
            obj2;
            JVM INSTR monitorenter ;
            iterator = rightMap.entrySet().iterator();
_L2:
            if(!iterator.hasNext())
                break; /* Loop/switch isn't completed */
            entry = (java.util.Map.Entry)iterator.next();
            if(((Integer)entry.getKey()).intValue() >= j)
                continue; /* Loop/switch isn't completed */
            obj3 = entry.getValue();
            obj4 = resultSelector.call(obj, obj3);
            observer.onNext(obj4);
            if(true) goto _L2; else goto _L1
            exception1;
            obj2;
            JVM INSTR monitorexit ;
            throw exception1;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
            Throwable throwable1;
            throwable1;
            observer.onError(throwable1);
            cancel.unsubscribe();
            obj2;
            JVM INSTR monitorexit ;
            return;
_L1:
            obj2;
            JVM INSTR monitorexit ;
        }

        final Subscription self;
        final ResultSink this$1;

        public ResultSink.LeftObserver(Subscription subscription)
        {
            this$1 = ResultSink.this;
            super();
            self = subscription;
        }
    }

    class ResultSink.LeftObserver.LeftDurationObserver extends Subscriber
    {

        public void onCompleted()
        {
            expire(id, handle);
        }

        public void onError(Throwable throwable)
        {
            ResultSink.LeftObserver.this.onError(throwable);
        }

        public void onNext(Object obj)
        {
            expire(id, handle);
        }

        final Subscription handle;
        final int id;
        final ResultSink.LeftObserver this$2;

        public ResultSink.LeftObserver.LeftDurationObserver(int i, Subscription subscription)
        {
            this$2 = ResultSink.LeftObserver.this;
            super();
            id = i;
            handle = subscription;
        }
    }

    class ResultSink.RightObserver extends Subscriber
    {

        void expire(int i, Subscription subscription)
        {
            synchronized(gate)
            {
                if(rightMap.remove(Integer.valueOf(i)) != null && rightMap.isEmpty() && rightDone)
                {
                    observer.onCompleted();
                    cancel.unsubscribe();
                }
            }
            group.remove(subscription);
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onCompleted()
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            rightDone = true;
            if(!leftDone && !rightMap.isEmpty())
                break MISSING_BLOCK_LABEL_70;
            observer.onCompleted();
            cancel.unsubscribe();
_L2:
            return;
            self.unsubscribe();
            if(true) goto _L2; else goto _L1
_L1:
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
                cancel.unsubscribe();
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            Object obj1 = gate;
            obj1;
            JVM INSTR monitorenter ;
            int i;
            ResultSink resultsink = ResultSink.this;
            i = resultsink.rightId;
            resultsink.rightId = i + 1;
            int j;
            rightMap.put(Integer.valueOf(i), obj);
            j = leftId;
            obj1;
            JVM INSTR monitorexit ;
            Exception exception;
            Object obj2;
            SerialSubscription serialsubscription = new SerialSubscription();
            group.add(serialsubscription);
            Observable observable;
            Exception exception1;
            Iterator iterator;
            java.util.Map.Entry entry;
            Object obj3;
            Object obj4;
            try
            {
                observable = (Observable)rightDurationSelector.call(obj);
            }
            catch(Throwable throwable)
            {
                observer.onError(throwable);
                cancel.unsubscribe();
                return;
            }
            serialsubscription.setSubscription(observable.unsafeSubscribe(new RightDurationObserver(i, serialsubscription)));
            obj2 = gate;
            obj2;
            JVM INSTR monitorenter ;
            iterator = leftMap.entrySet().iterator();
_L2:
            if(!iterator.hasNext())
                break; /* Loop/switch isn't completed */
            entry = (java.util.Map.Entry)iterator.next();
            if(((Integer)entry.getKey()).intValue() >= j)
                continue; /* Loop/switch isn't completed */
            obj3 = entry.getValue();
            obj4 = resultSelector.call(obj3, obj);
            observer.onNext(obj4);
            if(true) goto _L2; else goto _L1
            exception1;
            obj2;
            JVM INSTR monitorexit ;
            throw exception1;
            exception;
_L4:
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
            Throwable throwable1;
            throwable1;
            observer.onError(throwable1);
            cancel.unsubscribe();
            obj2;
            JVM INSTR monitorexit ;
            return;
_L1:
            obj2;
            JVM INSTR monitorexit ;
            return;
            exception;
            if(true) goto _L4; else goto _L3
_L3:
        }

        final Subscription self;
        final ResultSink this$1;

        public ResultSink.RightObserver(Subscription subscription)
        {
            this$1 = ResultSink.this;
            super();
            self = subscription;
        }
    }

    class ResultSink.RightObserver.RightDurationObserver extends Subscriber
    {

        public void onCompleted()
        {
            expire(id, handle);
        }

        public void onError(Throwable throwable)
        {
            ResultSink.RightObserver.this.onError(throwable);
        }

        public void onNext(Object obj)
        {
            expire(id, handle);
        }

        final Subscription handle;
        final int id;
        final ResultSink.RightObserver this$2;

        public ResultSink.RightObserver.RightDurationObserver(int i, Subscription subscription)
        {
            this$2 = ResultSink.RightObserver.this;
            super();
            id = i;
            handle = subscription;
        }
    }


    public OperationJoin(Observable observable, Observable observable1, Func1 func1, Func1 func1_1, Func2 func2)
    {
        left = observable;
        right = observable1;
        leftDurationSelector = func1;
        rightDurationSelector = func1_1;
        resultSelector = func2;
    }

    public Subscription onSubscribe(Observer observer)
    {
        SerialSubscription serialsubscription = new SerialSubscription();
        serialsubscription.setSubscription((new ResultSink(observer, serialsubscription)).run());
        return serialsubscription;
    }

    final Observable left;
    final Func1 leftDurationSelector;
    final Func2 resultSelector;
    final Observable right;
    final Func1 rightDurationSelector;
}
