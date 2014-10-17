// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.exceptions.Exceptions;

public final class BlockingOperatorNext
{
    static final class NextIterator
        implements Iterator
    {

        private boolean moveToNext()
        {
            Notification notification;
            notification = observer.takeNext();
            if(!notification.isOnNext())
                break MISSING_BLOCK_LABEL_30;
            isNextConsumed = false;
            next = notification.getValue();
            return true;
            InterruptedException interruptedexception;
            hasNext = false;
            if(notification.isOnCompleted())
                break MISSING_BLOCK_LABEL_95;
            if(notification.isOnError())
            {
                error = notification.getThrowable();
                throw Exceptions.propagate(error);
            }
            try
            {
                throw new IllegalStateException("Should not reach here");
            }
            // Misplaced declaration of an exception variable
            catch(InterruptedException interruptedexception)
            {
                Thread.currentThread().interrupt();
                error = interruptedexception;
                throw Exceptions.propagate(error);
            }
            return false;
        }

        public boolean hasNext()
        {
            if(error != null)
                throw Exceptions.propagate(error);
            if(!hasNext)
                return false;
            if(!isNextConsumed)
                return true;
            else
                return moveToNext();
        }

        public Object next()
        {
            if(error != null)
                throw Exceptions.propagate(error);
            if(hasNext())
            {
                isNextConsumed = true;
                return next;
            } else
            {
                throw new NoSuchElementException("No more elements");
            }
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Read only iterator");
        }

        void setWaiting(boolean flag)
        {
            observer.waiting.set(flag);
        }

        private Throwable error;
        private boolean hasNext;
        private boolean isNextConsumed;
        private Object next;
        private final NextObserver observer;

        private NextIterator(NextObserver nextobserver)
        {
            hasNext = true;
            isNextConsumed = true;
            error = null;
            observer = nextobserver;
        }

    }

    private static class NextObserver extends Subscriber
    {

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
            if(waiting.getAndSet(false) || !notification.isOnNext())
            {
                Notification notification1 = notification;
                do
                {
                    if(buf.offer(notification1))
                        break;
                    Notification notification2 = (Notification)buf.poll();
                    if(notification2 != null && !notification2.isOnNext())
                        notification1 = notification2;
                } while(true);
            }
        }

        public Notification takeNext()
            throws InterruptedException
        {
            waiting.set(true);
            return (Notification)buf.take();
        }

        private final BlockingQueue buf;
        private final AtomicBoolean waiting;


        private NextObserver()
        {
            buf = new ArrayBlockingQueue(1);
            waiting = new AtomicBoolean(false);
        }

    }


    public BlockingOperatorNext()
    {
    }

    public static Iterable next(Observable observable)
    {
        return new Iterable(observable) {

            public Iterator iterator()
            {
                NextObserver nextobserver = new NextObserver();
                NextIterator nextiterator = new NextIterator(nextobserver);
                items.materialize().subscribe(nextobserver);
                return nextiterator;
            }

            final Observable val$items;

            
            {
                items = observable;
                super();
            }
        }
;
    }
}
