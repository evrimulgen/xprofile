// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import rx.*;
import rx.exceptions.Exceptions;

public final class BlockingOperatorLatest
{
    static final class LatestObserverIterator extends Subscriber
        implements Iterator
    {

        public boolean hasNext()
        {
            if(iNotif != null && iNotif.isOnError())
                throw Exceptions.propagate(iNotif.getThrowable());
            if((iNotif == null || !iNotif.isOnCompleted()) && iNotif == null)
            {
                try
                {
                    notify.acquire();
                }
                catch(InterruptedException interruptedexception)
                {
                    Thread.currentThread().interrupt();
                    iNotif = new Notification(interruptedexception);
                    throw Exceptions.propagate(interruptedexception);
                }
                iNotif = (Notification)reference.getAndSet(null);
                if(iNotif.isOnError())
                    throw Exceptions.propagate(iNotif.getThrowable());
            }
            return !iNotif.isOnCompleted();
        }

        public Object next()
        {
            if(hasNext() && iNotif.isOnNext())
            {
                Object obj = iNotif.getValue();
                iNotif = null;
                return obj;
            } else
            {
                throw new NoSuchElementException();
            }
        }

        public void onCompleted()
        {
        }

        public void onError(Throwable throwable)
        {
        }

        public volatile void onNext(Object obj)
        {
            onNext((Notification)obj);
        }

        public void onNext(Notification notification)
        {
            boolean flag;
            if(reference.getAndSet(notification) == null)
                flag = true;
            else
                flag = false;
            if(flag)
                notify.release();
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Read-only iterator.");
        }

        Notification iNotif;
        final Semaphore notify = new Semaphore(0);
        final AtomicReference reference = new AtomicReference();

        LatestObserverIterator()
        {
        }
    }


    private BlockingOperatorLatest()
    {
        throw new IllegalStateException("No instances!");
    }

    public static Iterable latest(Observable observable)
    {
        return new Iterable(observable) {

            public Iterator iterator()
            {
                LatestObserverIterator latestobserveriterator = new LatestObserverIterator();
                source.materialize().subscribe(latestobserveriterator);
                return latestobserveriterator;
            }

            final Observable val$source;

            
            {
                source = observable;
                super();
            }
        }
;
    }
}
