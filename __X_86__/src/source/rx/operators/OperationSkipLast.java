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

public class OperationSkipLast
{
    private static class SkipLast
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(final Observer observer)
        {
            if(count < 0)
            {
                throw new IndexOutOfBoundsException("count could not be negative");
            } else
            {
                SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
                return safeobservablesubscription.wrap(source.unsafeSubscribe(safeobservablesubscription. new Subscriber() {

                    public void onCompleted()
                    {
                        observer.onCompleted();
                    }

                    public void onError(Throwable throwable)
                    {
                        observer.onError(throwable);
                    }

                    public void onNext(Object obj)
                    {
                        if(count == 0)
                        {
                            try
                            {
                                observer.onNext(obj);
                                return;
                            }
                            catch(Throwable throwable1)
                            {
                                observer.onError(throwable1);
                            }
                            subscription.unsubscribe();
                            return;
                        }
                        lock.lock();
                        deque.offerLast(obj);
                        if(deque.size() > count)
                            observer.onNext(deque.removeFirst());
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

                    private final Deque deque = new LinkedList();
                    private final ReentrantLock lock = new ReentrantLock();
                    final SkipLast this$0;
                    final Observer val$observer;
                    final SafeObservableSubscription val$subscription;

            
            {
                this$0 = final_skiplast;
                observer = observer1;
                subscription = SafeObservableSubscription.this;
                Subscriber();
            }
                }
));
            }
        }

        private final int count;
        private final Observable source;


        private SkipLast(Observable observable, int i)
        {
            count = i;
            source = observable;
        }

    }

    public static final class SkipLastTimed
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return source.unsafeSubscribe(new SourceObserver(observer, timeInMillis, scheduler));
        }

        final Scheduler scheduler;
        final Observable source;
        final long timeInMillis;

        public SkipLastTimed(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            source = observable;
            timeInMillis = timeunit.toMillis(l);
            scheduler = scheduler1;
        }
    }

    private static final class SkipLastTimed.SourceObserver extends Subscriber
    {

        public void onCompleted()
        {
            long l = scheduler.now() - timeInMillis;
            Iterator iterator = buffer.iterator();
_L1:
            Timestamped timestamped;
            long l1;
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_107;
            timestamped = (Timestamped)iterator.next();
            l1 = timestamped.getTimestampMillis();
            if(l1 >= l)
                break MISSING_BLOCK_LABEL_98;
            observer.onNext(timestamped.getValue());
              goto _L1
            Throwable throwable;
            throwable;
            observer.onError(throwable);
            buffer = Collections.emptyList();
            return;
            observer.onCompleted();
            buffer = Collections.emptyList();
            return;
            Exception exception;
            exception;
            buffer = Collections.emptyList();
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            buffer = Collections.emptyList();
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            buffer.add(new Timestamped(scheduler.now(), obj));
        }

        List buffer;
        final Observer observer;
        final Scheduler scheduler;
        final long timeInMillis;

        public SkipLastTimed.SourceObserver(Observer observer1, long l, Scheduler scheduler1)
        {
            buffer = new ArrayList();
            observer = observer1;
            timeInMillis = l;
            scheduler = scheduler1;
        }
    }


    public OperationSkipLast()
    {
    }

    public static rx.Observable.OnSubscribeFunc skipLast(Observable observable, int i)
    {
        return new SkipLast(observable, i);
    }
}
