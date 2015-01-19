// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import rx.*;
import rx.schedulers.Timestamped;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public final class OperationTakeLast
{
    private static class TakeLast
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            if(count < 0)
                throw new IndexOutOfBoundsException("count could not be negative");
            else
                return subscription.wrap(items.unsafeSubscribe(new ItemObserver(observer)));
        }

        private final int count;
        private final Observable items;
        private final SafeObservableSubscription subscription = new SafeObservableSubscription();



        TakeLast(Observable observable, int i)
        {
            count = i;
            items = observable;
        }
    }

    private class TakeLast.ItemObserver extends Subscriber
    {

        public void onCompleted()
        {
            try
            {
                Object obj;
                for(Iterator iterator = deque.iterator(); iterator.hasNext(); observer.onNext(obj))
                    obj = iterator.next();

            }
            catch(Throwable throwable)
            {
                observer.onError(throwable);
                return;
            }
            observer.onCompleted();
            return;
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            if(count == 0)
                return;
            lock.lock();
            deque.offerLast(obj);
            if(deque.size() > count)
                deque.removeFirst();
            lock.unlock();
            return;
            Throwable throwable;
            throwable;
            observer.onError(throwable);
            subscription.unsubscribe();
            lock.unlock();
            return;
            Exception exception;
            exception;
            lock.unlock();
            throw exception;
        }

        private Deque deque;
        private final ReentrantLock lock = new ReentrantLock();
        private final Observer observer;
        final TakeLast this$0;

        public TakeLast.ItemObserver(Observer observer1)
        {
            this$0 = TakeLast.this;
            Subscriber();
            deque = new LinkedList();
            observer = observer1;
        }
    }

    static final class TakeLastTimed
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
            safeobservablesubscription.wrap(source.unsafeSubscribe(new TakeLastTimedObserver(observer, safeobservablesubscription, count, ageMillis, scheduler)));
            return safeobservablesubscription;
        }

        final long ageMillis;
        final int count;
        final Scheduler scheduler;
        final Observable source;

        public TakeLastTimed(Observable observable, int i, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            source = observable;
            ageMillis = timeunit.toMillis(l);
            scheduler = scheduler1;
            count = i;
        }
    }

    static final class TakeLastTimedObserver extends Subscriber
    {

        protected boolean emitBuffer()
        {
            Iterator iterator = buffer.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Timestamped timestamped = (Timestamped)iterator.next();
                try
                {
                    observer.onNext(timestamped.getValue());
                }
                catch(Throwable throwable)
                {
                    buffer.clear();
                    observer.onError(throwable);
                    return false;
                }
            } while(true);
            buffer.clear();
            return true;
        }

        public void onCompleted()
        {
            runEvictionPolicy(scheduler.now());
            if(emitBuffer())
                observer.onCompleted();
            cancel.unsubscribe();
        }

        public void onError(Throwable throwable)
        {
            buffer.clear();
            observer.onError(throwable);
            cancel.unsubscribe();
        }

        public void onNext(Object obj)
        {
            long l = scheduler.now();
            buffer.add(new Timestamped(l, obj));
            runEvictionPolicy(l);
        }

        protected void runEvictionPolicy(long l)
        {
            for(; count >= 0 && buffer.size() > count; buffer.pollFirst());
            for(; !buffer.isEmpty() && ((Timestamped)buffer.peekFirst()).getTimestampMillis() < l - ageMillis; buffer.pollFirst());
        }

        final long ageMillis;
        final Deque buffer = new LinkedList();
        final Subscription cancel;
        final int count;
        final Observer observer;
        final Scheduler scheduler;

        public TakeLastTimedObserver(Observer observer1, Subscription subscription, int i, long l, Scheduler scheduler1)
        {
            observer = observer1;
            cancel = subscription;
            ageMillis = l;
            scheduler = scheduler1;
            count = i;
        }
    }


    public OperationTakeLast()
    {
    }

    public static rx.Observable.OnSubscribeFunc takeLast(Observable observable, int i)
    {
        return new rx.Observable.OnSubscribeFunc(observable, i) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new TakeLast(items, count)).onSubscribe(observer);
            }

            final int val$count;
            final Observable val$items;

            
            {
                items = observable;
                count = i;
                Object();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc takeLast(Observable observable, int i, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new TakeLastTimed(observable, i, l, timeunit, scheduler);
    }

    public static rx.Observable.OnSubscribeFunc takeLast(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new TakeLastTimed(observable, -1, l, timeunit, scheduler);
    }
}
