// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.*;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            NotificationLite

public class OperatorObserveOnBounded
    implements rx.Observable.Operator
{
    private static class InterruptibleBlockingQueue
    {

        private boolean offer(Object obj)
        {
            long l = tail.get();
            if(l - head.get() == (long)capacity)
            {
                return false;
            } else
            {
                int i = (int)(l & (long)mask);
                buffer[i] = obj;
                tail.lazySet(1L + l);
                return true;
            }
        }

        public void addBlocking(Object obj)
            throws InterruptedException
        {
            if(interrupted)
                throw new InterruptedException("Interrupted by Unsubscribe");
            semaphore.acquire();
            if(interrupted)
                throw new InterruptedException("Interrupted by Unsubscribe");
            if(obj == null)
                throw new IllegalArgumentException("Can not put null");
            if(offer(obj))
                return;
            else
                throw new IllegalStateException("Queue is full");
        }

        public void interrupt()
        {
            interrupted = true;
            semaphore.release();
        }

        public Object poll()
        {
            Object obj;
            if(interrupted)
            {
                obj = null;
            } else
            {
                long l = head.get();
                if(tail.get() == l)
                    return null;
                int i = (int)(l & (long)mask);
                obj = buffer[i];
                buffer[i] = null;
                head.lazySet(1L + l);
                if(obj != null)
                {
                    semaphore.release();
                    return obj;
                }
            }
            return obj;
        }

        public int size()
        {
            int i;
            do
            {
                long l = head.get();
                i = (int)(tail.get() - l);
            } while(i > buffer.length);
            return i;
        }

        private final Object buffer[];
        private final int capacity;
        private AtomicLong head;
        private volatile boolean interrupted;
        private final int mask;
        private final Semaphore semaphore;
        private AtomicLong tail;

        public InterruptibleBlockingQueue(int i)
        {
            interrupted = false;
            tail = new AtomicLong();
            head = new AtomicLong();
            semaphore = new Semaphore(i);
            capacity = i;
            mask = i - 1;
            buffer = (Object[])new Object[i];
        }
    }

    private class ObserveOnSubscriber extends Subscriber
    {

        private void pollQueue()
        {
            do
            {
                Object obj = queue.poll();
                on.accept(observer, obj);
            } while(counter.decrementAndGet() > 0L);
        }

        public void onCompleted()
        {
            try
            {
                queue.addBlocking(on.completed());
                schedule();
                return;
            }
            catch(InterruptedException interruptedexception)
            {
                onError(interruptedexception);
            }
        }

        public void onError(Throwable throwable)
        {
            try
            {
                queue.addBlocking(on.error(throwable));
                schedule();
                return;
            }
            catch(InterruptedException interruptedexception)
            {
                observer.onError(interruptedexception);
            }
        }

        public void onNext(Object obj)
        {
            queue.addBlocking(on.next(obj));
            schedule();
_L1:
            return;
            InterruptedException interruptedexception;
            interruptedexception;
            if(!isUnsubscribed())
            {
                onError(interruptedexception);
                return;
            }
              goto _L1
        }

        protected void schedule()
        {
label0:
            {
                if(counter.getAndIncrement() == 0L)
                {
                    if(recursiveScheduler != null)
                        break label0;
                    add(Subscriptions.create(new Action0() {

                        public void call()
                        {
                            queue.interrupt();
                        }

                        final ObserveOnSubscriber this$1;

            
            {
                this$1 = ObserveOnSubscriber.this;
                super();
            }
                    }
));
                    add(scheduler.schedule(new Action1() {

                        public volatile void call(Object obj)
                        {
                            call((rx.Scheduler.Inner)obj);
                        }

                        public void call(rx.Scheduler.Inner inner)
                        {
                            recursiveScheduler = inner;
                            pollQueue();
                        }

                        final ObserveOnSubscriber this$1;

            
            {
                this$1 = ObserveOnSubscriber.this;
                super();
            }
                    }
));
                }
                return;
            }
            recursiveScheduler.schedule(new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    pollQueue();
                }

                final ObserveOnSubscriber this$1;

            
            {
                this$1 = ObserveOnSubscriber.this;
                super();
            }
            }
);
        }

        final AtomicLong counter = new AtomicLong(0L);
        final Subscriber observer;
        private final InterruptibleBlockingQueue queue;
        private volatile rx.Scheduler.Inner recursiveScheduler;
        final OperatorObserveOnBounded this$0;



/*
        static rx.Scheduler.Inner access$302(ObserveOnSubscriber observeonsubscriber, rx.Scheduler.Inner inner)
        {
            observeonsubscriber.recursiveScheduler = inner;
            return inner;
        }

*/


        public ObserveOnSubscriber(Subscriber subscriber)
        {
            this$0 = OperatorObserveOnBounded.this;
            super(subscriber);
            queue = new InterruptibleBlockingQueue(bufferSize);
            observer = subscriber;
        }
    }


    public OperatorObserveOnBounded(Scheduler scheduler1)
    {
        this(scheduler1, 1);
    }

    public OperatorObserveOnBounded(Scheduler scheduler1, int i)
    {
        on = NotificationLite.instance();
        scheduler = scheduler1;
        bufferSize = roundToNextPowerOfTwoIfNecessary(i);
    }

    private static int roundToNextPowerOfTwoIfNecessary(int i)
    {
        if((i & -i) == i)
            return i;
        int j;
        for(j = 1; i != 0; j <<= 1)
            i >>= 1;

        return j;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(Subscriber subscriber)
    {
        while((scheduler instanceof ImmediateScheduler) || (scheduler instanceof TrampolineScheduler) || (scheduler instanceof TestScheduler)) 
            return subscriber;
        return new ObserveOnSubscriber(subscriber);
    }

    private final int bufferSize;
    private final NotificationLite on;
    private final Scheduler scheduler;



}
