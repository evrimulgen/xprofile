// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import rx.*;

public class BlockingOperatorToFuture
{

    public BlockingOperatorToFuture()
    {
    }

    public static Future toFuture(Observable observable)
    {
        CountDownLatch countdownlatch = new CountDownLatch(1);
        AtomicReference atomicreference = new AtomicReference();
        AtomicReference atomicreference1 = new AtomicReference();
        return new Future(countdownlatch, observable.subscribe(new Subscriber(countdownlatch, atomicreference1, atomicreference) {

            public void onCompleted()
            {
                finished.countDown();
            }

            public void onError(Throwable throwable)
            {
                error.compareAndSet(null, throwable);
                finished.countDown();
            }

            public void onNext(Object obj)
            {
                if(!value.compareAndSet(null, obj))
                {
                    error.compareAndSet(null, new IllegalStateException("Observable.toFuture() only supports sequences with a single value. Use .toList().toFuture() if multiple values are expected."));
                    finished.countDown();
                }
            }

            final AtomicReference val$error;
            final CountDownLatch val$finished;
            final AtomicReference val$value;

            
            {
                finished = countdownlatch;
                error = atomicreference;
                value = atomicreference1;
                super();
            }
        }
), atomicreference1, atomicreference) {

            private Object getValue()
                throws ExecutionException
            {
                if(error.get() != null)
                    throw new ExecutionException("Observable onError", (Throwable)error.get());
                if(cancelled)
                    throw new CancellationException("Subscription unsubscribed");
                else
                    return value.get();
            }

            public boolean cancel(boolean flag)
            {
                if(finished.getCount() > 0L)
                {
                    cancelled = true;
                    s.unsubscribe();
                    finished.countDown();
                    return true;
                } else
                {
                    return false;
                }
            }

            public Object get()
                throws InterruptedException, ExecutionException
            {
                finished.await();
                return getValue();
            }

            public Object get(long l, TimeUnit timeunit)
                throws InterruptedException, ExecutionException, TimeoutException
            {
                if(finished.await(l, timeunit))
                    return getValue();
                else
                    throw new TimeoutException((new StringBuilder()).append("Timed out after ").append(timeunit.toMillis(l)).append("ms waiting for underlying Observable.").toString());
            }

            public boolean isCancelled()
            {
                return cancelled;
            }

            public boolean isDone()
            {
                return finished.getCount() == 0L;
            }

            private volatile boolean cancelled;
            final AtomicReference val$error;
            final CountDownLatch val$finished;
            final Subscription val$s;
            final AtomicReference val$value;

            
            {
                finished = countdownlatch;
                s = subscription;
                error = atomicreference;
                value = atomicreference1;
                super();
                cancelled = false;
            }
        }
;
    }
}
