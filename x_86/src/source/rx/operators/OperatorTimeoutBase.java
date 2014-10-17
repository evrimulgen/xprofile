// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.*;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

class OperatorTimeoutBase
    implements rx.Observable.Operator
{
    static interface FirstTimeoutStub
        extends Func2
    {
    }

    static interface TimeoutStub
        extends Func3
    {
    }

    static class TimeoutSubscriber extends Subscriber
    {

        public void onCompleted()
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            boolean flag = terminated.getAndSet(true);
            boolean flag1;
            flag1 = false;
            if(!flag)
                flag1 = true;
            obj;
            JVM INSTR monitorexit ;
            if(flag1)
            {
                serial.unsubscribe();
                serializedSubscriber.onCompleted();
            }
            return;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            boolean flag = terminated.getAndSet(true);
            boolean flag1;
            flag1 = false;
            if(!flag)
                flag1 = true;
            obj;
            JVM INSTR monitorexit ;
            if(flag1)
            {
                serial.unsubscribe();
                serializedSubscriber.onError(throwable);
            }
            return;
            Exception exception;
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
            boolean flag = terminated.get();
            boolean flag1;
            flag1 = false;
            if(flag)
                break MISSING_BLOCK_LABEL_35;
            actual.incrementAndGet();
            flag1 = true;
            obj1;
            JVM INSTR monitorexit ;
            if(flag1)
            {
                serializedSubscriber.onNext(obj);
                serial.set((Subscription)timeoutStub.call(this, Long.valueOf(actual.get()), obj));
            }
            return;
            Exception exception;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onTimeout(long l)
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            int i = l != actual.get();
            boolean flag;
            flag = false;
            if(i != 0)
                break MISSING_BLOCK_LABEL_47;
            boolean flag1 = terminated.getAndSet(true);
            flag = false;
            if(!flag1)
                flag = true;
            obj;
            JVM INSTR monitorexit ;
            if(flag)
            {
                if(other != null)
                    break MISSING_BLOCK_LABEL_83;
                serializedSubscriber.onError(new TimeoutException());
            }
            return;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            other.unsafeSubscribe(serializedSubscriber);
            serial.set(serializedSubscriber);
            return;
        }

        private final AtomicLong actual;
        private final Object gate;
        private final Observable other;
        private final SerialSubscription serial;
        private final SerializedSubscriber serializedSubscriber;
        private final AtomicBoolean terminated;
        private final TimeoutStub timeoutStub;

        private TimeoutSubscriber(SerializedSubscriber serializedsubscriber, TimeoutStub timeoutstub, SerialSubscription serialsubscription, Observable observable)
        {
            super(serializedsubscriber);
            terminated = new AtomicBoolean(false);
            actual = new AtomicLong(0L);
            gate = new Object();
            serializedSubscriber = serializedsubscriber;
            timeoutStub = timeoutstub;
            serial = serialsubscription;
            other = observable;
        }

    }


    OperatorTimeoutBase(FirstTimeoutStub firsttimeoutstub, TimeoutStub timeoutstub, Observable observable)
    {
        firstTimeoutStub = firsttimeoutstub;
        timeoutStub = timeoutstub;
        other = observable;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(Subscriber subscriber)
    {
        SerialSubscription serialsubscription = new SerialSubscription();
        subscriber.add(serialsubscription);
        TimeoutSubscriber timeoutsubscriber = new TimeoutSubscriber(new SerializedSubscriber(subscriber), timeoutStub, serialsubscription, other);
        serialsubscription.set((Subscription)firstTimeoutStub.call(timeoutsubscriber, Long.valueOf(0L)));
        return timeoutsubscriber;
    }

    private final FirstTimeoutStub firstTimeoutStub;
    private final Observable other;
    private final TimeoutStub timeoutStub;
}
