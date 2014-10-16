// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observers;

import rx.Observer;

public final class SynchronizedObserver
    implements Observer
{

    public SynchronizedObserver(Observer observer1)
    {
        isTerminated = false;
        observer = observer1;
        lock = this;
    }

    public SynchronizedObserver(Observer observer1, Object obj)
    {
        isTerminated = false;
        observer = observer1;
        lock = obj;
    }

    public void onCompleted()
    {
        synchronized(lock)
        {
            if(!isTerminated)
            {
                isTerminated = true;
                observer.onCompleted();
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
        synchronized(lock)
        {
            if(!isTerminated)
            {
                isTerminated = true;
                observer.onError(throwable);
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
        synchronized(lock)
        {
            if(!isTerminated)
                observer.onNext(obj);
        }
        return;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private boolean isTerminated;
    private final Object lock;
    private final Observer observer;
}
