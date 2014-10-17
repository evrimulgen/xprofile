// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import rx.*;
import rx.exceptions.Exceptions;

public class BlockingOperatorToIterator
{

    public BlockingOperatorToIterator()
    {
    }

    public static Iterator toIterator(Observable observable)
    {
        LinkedBlockingQueue linkedblockingqueue = new LinkedBlockingQueue();
        observable.materialize().subscribe(new Subscriber(linkedblockingqueue) {

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
                notifications.offer(notification);
            }

            final BlockingQueue val$notifications;

            
            {
                notifications = blockingqueue;
                super();
            }
        }
);
        return new Iterator(linkedblockingqueue) {

            private Notification take()
            {
                Notification notification;
                try
                {
                    notification = (Notification)notifications.take();
                }
                catch(InterruptedException interruptedexception)
                {
                    throw Exceptions.propagate(interruptedexception);
                }
                return notification;
            }

            public boolean hasNext()
            {
                if(buf == null)
                    buf = take();
                if(buf.isOnError())
                    throw Exceptions.propagate(buf.getThrowable());
                return !buf.isOnCompleted();
            }

            public Object next()
            {
                if(hasNext())
                {
                    Object obj = buf.getValue();
                    buf = null;
                    return obj;
                } else
                {
                    throw new NoSuchElementException();
                }
            }

            public void remove()
            {
                throw new UnsupportedOperationException("Read-only iterator");
            }

            private Notification buf;
            final BlockingQueue val$notifications;

            
            {
                notifications = blockingqueue;
                super();
            }
        }
;
    }
}
