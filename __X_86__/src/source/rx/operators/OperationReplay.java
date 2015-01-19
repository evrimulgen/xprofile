// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import rx.*;
import rx.functions.*;
import rx.observers.Subscribers;
import rx.schedulers.Timestamped;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public final class OperationReplay
{
    static class BaseState
    {

        public void lock()
        {
            lock.lock();
        }

        public void unlock()
        {
            lock.unlock();
        }

        private final Lock lock = new ReentrantLock();

        BaseState()
        {
        }
    }

    public static final class CustomReplaySubject extends Subject
    {

        public static CustomReplaySubject create()
        {
            ReplayState replaystate = new ReplayState(new VirtualArrayList(), Functions.identity());
            return new CustomReplaySubject(new CustomReplaySubjectSubscribeFunc(replaystate), replaystate, Functions.identity());
        }

        public static CustomReplaySubject create(int i)
        {
            ReplayState replaystate = new ReplayState(new VirtualBoundedList(i), Functions.identity());
            return new CustomReplaySubject(new CustomReplaySubjectSubscribeFunc(replaystate), replaystate, Functions.identity());
        }

        public void onCompleted()
        {
            state.lock();
            boolean flag = state.done;
            if(flag)
            {
                state.unlock();
                return;
            }
            state.done = true;
            state.onCompletedAdded.call();
            replayValues();
            state.unlock();
            return;
            Exception exception;
            exception;
            state.unlock();
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            state.lock();
            boolean flag = state.done;
            if(flag)
            {
                state.unlock();
                return;
            }
            state.done = true;
            state.error = throwable;
            state.onErrorAdded.call();
            replayValues();
            state.unlock();
            return;
            Exception exception;
            exception;
            state.unlock();
            throw exception;
        }

        public void onNext(Object obj)
        {
            state.lock();
            boolean flag = state.done;
            if(flag)
            {
                state.unlock();
                return;
            }
            state.add(intermediateSelector.call(obj));
            state.onValueAdded.call();
            replayValues();
            state.unlock();
            return;
            Exception exception;
            exception;
            state.unlock();
            throw exception;
        }

        protected void replayValues()
        {
            int i = state.values.start() + state.values.size();
            for(Iterator iterator = state.replayers().iterator(); iterator.hasNext(); ((ReplayState.Replayer)iterator.next()).replayTill(i));
        }

        protected final Func1 intermediateSelector;
        protected final ReplayState state;

        private CustomReplaySubject(final rx.Observable.OnSubscribeFunc onSubscribe, ReplayState replaystate, Func1 func1)
        {
            Subject(new _cls1());
            state = replaystate;
            intermediateSelector = func1;
        }

    }

    protected static final class CustomReplaySubjectSubscribeFunc
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            state.lock();
            Subscription subscription;
            if(state.done)
                break MISSING_BLOCK_LABEL_49;
            state.onSubscription.call();
            subscription = state.addReplayer(observer);
            state.unlock();
            return subscription;
            VirtualList virtuallist;
            Throwable throwable;
            virtuallist = state.values;
            throwable = state.error;
            state.unlock();
            int i = virtuallist.start();
            do
            {
                if(i >= virtuallist.end())
                    break;
                Exception exception;
                try
                {
                    observer.onNext(state.resultSelector.call(virtuallist.get(i)));
                }
                catch(Throwable throwable1)
                {
                    observer.onError(throwable1);
                    return Subscriptions.empty();
                }
                i++;
            } while(true);
            break MISSING_BLOCK_LABEL_148;
            exception;
            state.unlock();
            throw exception;
            if(throwable != null)
                observer.onError(throwable);
            else
                observer.onCompleted();
            return Subscriptions.empty();
        }

        private final ReplayState state;

        protected CustomReplaySubjectSubscribeFunc(ReplayState replaystate)
        {
            state = replaystate;
        }
    }

    static final class ReplayState extends BaseState
    {

        void add(Object obj)
        {
            values.add(obj);
        }

        Subscription addReplayer(Observer observer)
        {
            Subscription subscription = new Subscription() {

                public boolean isUnsubscribed()
                {
                    return once.get();
                }

                public void unsubscribe()
                {
                    if(once.compareAndSet(false, true))
                        remove(this);
                }

                final AtomicBoolean once = new AtomicBoolean();
                final ReplayState this$0;

            
            {
                this$0 = ReplayState.this;
                Object();
            }
            }
;
            Replayer replayer = new Replayer(observer, subscription);
            replayers.put(subscription, replayer);
            replayer.replayTill(values.start() + values.size());
            return subscription;
        }

        void clearValues()
        {
            lock();
            values.clear();
            unlock();
            return;
            Exception exception;
            exception;
            unlock();
            throw exception;
        }

        void remove(Subscription subscription)
        {
            lock();
            replayers.remove(subscription);
            unlock();
            return;
            Exception exception;
            exception;
            unlock();
            throw exception;
        }

        Collection replayers()
        {
            return new ArrayList(replayers.values());
        }

        boolean done;
        Throwable error;
        protected Action0 onCompletedAdded;
        protected Action0 onErrorAdded;
        protected Action0 onSubscription;
        protected Action0 onValueAdded;
        final Map replayers = new LinkedHashMap();
        final Func1 resultSelector;
        final VirtualList values;

        public ReplayState(VirtualList virtuallist, Func1 func1)
        {
            onValueAdded = new _cls1();
            onErrorAdded = new _cls2();
            onCompletedAdded = new _cls3();
            onSubscription = new _cls4();
            values = virtuallist;
            resultSelector = func1;
        }
    }

    final class ReplayState.Replayer
    {

        void replayTill(int i)
        {
            int j = values.start();
            if(index < j)
                index = j;
_L4:
            if(index >= i) goto _L2; else goto _L1
_L1:
            Object obj;
            obj = values.get(index);
            index = 1 + index;
            wrapped.onNext(resultSelector.call(obj));
            if(true) goto _L4; else goto _L3
_L3:
            Throwable throwable;
            throwable;
            replayers.remove(cancel);
            wrapped.onError(throwable);
_L6:
            return;
_L2:
            if(done)
                if(error != null)
                {
                    wrapped.onError(error);
                    return;
                } else
                {
                    wrapped.onCompleted();
                    return;
                }
            if(true) goto _L6; else goto _L5
_L5:
        }

        protected final Subscription cancel;
        protected int index;
        final ReplayState this$0;
        protected final Observer wrapped;

        protected ReplayState.Replayer(Observer observer, Subscription subscription)
        {
            this$0 = ReplayState.this;
            Object();
            wrapped = observer;
            cancel = subscription;
        }
    }

    public static final class SubjectWrapper extends Subject
    {

        public void onCompleted()
        {
            subject.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            subject.onError(throwable);
        }

        public void onNext(Object obj)
        {
            subject.onNext(obj);
        }

        final Subject subject;

        public SubjectWrapper(rx.Observable.OnSubscribe onsubscribe, Subject subject1)
        {
            Subject(onsubscribe);
            subject = subject1;
        }
    }

    public static final class VirtualArrayList
        implements VirtualList
    {

        public void add(Object obj)
        {
            list.add(obj);
        }

        public void clear()
        {
            startIndex = startIndex + list.size();
            list.clear();
        }

        public int end()
        {
            return startIndex + list.size();
        }

        public Object get(int i)
        {
            return list.get(i - startIndex);
        }

        public void removeBefore(int i)
        {
            int j = i - startIndex;
            if(j > 0 && j <= list.size())
                list.subList(0, j).clear();
            startIndex = i;
        }

        public void reset()
        {
            list.clear();
            startIndex = 0;
        }

        public int size()
        {
            return list.size();
        }

        public int start()
        {
            return startIndex;
        }

        public List toList()
        {
            return new ArrayList(list);
        }

        final List list = new ArrayList();
        int startIndex;

        public VirtualArrayList()
        {
        }
    }

    public static final class VirtualBoundedList
        implements VirtualList
    {

        public void add(Object obj)
        {
            if(list.size() == maxSize)
            {
                list.set(tail, obj);
                head = (1 + head) % maxSize;
                tail = (1 + tail) % maxSize;
                startIndex = 1 + startIndex;
                return;
            } else
            {
                list.add(obj);
                tail = (1 + tail) % maxSize;
                count = 1 + count;
                return;
            }
        }

        public void clear()
        {
            startIndex = startIndex + count;
            list.clear();
            head = 0;
            tail = 0;
            count = 0;
        }

        public int end()
        {
            return startIndex + count;
        }

        public Object get(int i)
        {
            if(i < start() || i >= end())
            {
                throw new ArrayIndexOutOfBoundsException(i);
            } else
            {
                int j = (head + (i - startIndex)) % maxSize;
                return list.get(j);
            }
        }

        public void removeBefore(int i)
        {
            if(i <= start())
                return;
            if(i >= end())
            {
                clear();
                startIndex = i;
                return;
            }
            int j = (i - startIndex) + head;
            for(int k = head; k < j; k++)
            {
                list.set(k % maxSize, null);
                count = -1 + count;
            }

            startIndex = i;
            head = j % maxSize;
        }

        public void reset()
        {
            list.clear();
            count = 0;
            head = 0;
            tail = 0;
        }

        public int size()
        {
            return count;
        }

        public int start()
        {
            return startIndex;
        }

        public List toList()
        {
            ArrayList arraylist = new ArrayList(1 + list.size());
            for(int i = head; i < head + count; i++)
            {
                int j = i % maxSize;
                arraylist.add(list.get(j));
            }

            return arraylist;
        }

        int count;
        int head;
        private final List list = new ArrayList();
        private final int maxSize;
        int startIndex;
        int tail;

        public VirtualBoundedList(int i)
        {
            if(i < 0)
            {
                throw new IllegalArgumentException("maxSize < 0");
            } else
            {
                maxSize = i;
                return;
            }
        }
    }

    public static interface VirtualList
    {

        public abstract void add(Object obj);

        public abstract void clear();

        public abstract int end();

        public abstract Object get(int i);

        public abstract void removeBefore(int i);

        public abstract void reset();

        public abstract int size();

        public abstract int start();

        public abstract List toList();
    }


    private OperationReplay()
    {
        throw new IllegalStateException("No instances!");
    }

    public static Subject createScheduledSubject(Subject subject, Scheduler scheduler)
    {
        return new SubjectWrapper(new rx.Observable.OnSubscribe(subject.observeOn(scheduler)) {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                OperationReplay.subscriberOf(observedOn).onSubscribe(subscriber);
            }

            final Observable val$observedOn;

            
            {
                observedOn = observable;
                Object();
            }
        }
, subject);
    }

    public static Subject replayBuffered(int i)
    {
        return CustomReplaySubject.create(i);
    }

    public static Subject replayWindowed(long l, TimeUnit timeunit, int i, Scheduler scheduler)
    {
        long l1 = timeunit.toMillis(l);
        if(l1 <= 0L)
            throw new IllegalArgumentException("The time window is less than 1 millisecond!");
        Func1 func1 = new Func1(scheduler) {

            public volatile Object call(Object obj)
            {
                return call(obj);
            }

            public Timestamped call(Object obj)
            {
                return new Timestamped(scheduler.now(), obj);
            }

            final Scheduler val$scheduler;

            
            {
                scheduler = scheduler1;
                Object();
            }
        }
;
        Func1 func1_1 = new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Timestamped)obj);
            }

            public Object call(Timestamped timestamped)
            {
                return timestamped.getValue();
            }

        }
;
        ReplayState replaystate;
        if(i >= 0)
            replaystate = new ReplayState(new VirtualBoundedList(i), func1_1);
        else
            replaystate = new ReplayState(new VirtualArrayList(), func1_1);
        replaystate.onValueAdded = new Action0(scheduler, l1, replaystate) {

            public void call()
            {
                long l2 = scheduler.now() - ms;
                int j = fstate.values.start();
                do
                {
label0:
                    {
                        if(j < fstate.values.end())
                        {
                            if(((Timestamped)fstate.values.get(j)).getTimestampMillis() < l2)
                                break label0;
                            fstate.values.removeBefore(j);
                        }
                        return;
                    }
                    j++;
                } while(true);
            }

            final ReplayState val$fstate;
            final long val$ms;
            final Scheduler val$scheduler;

            
            {
                scheduler = scheduler1;
                ms = l;
                fstate = replaystate;
                Object();
            }
        }
;
        replaystate.onSubscription = replaystate.onValueAdded;
        return new CustomReplaySubject(new CustomReplaySubjectSubscribeFunc(replaystate), replaystate, func1);
    }

    public static rx.Observable.OnSubscribeFunc subscriberOf(Observable observable)
    {
        return new rx.Observable.OnSubscribeFunc(observable) {

            public Subscription onSubscribe(Observer observer)
            {
                return target.unsafeSubscribe(Subscribers.from(observer));
            }

            final Observable val$target;

            
            {
                target = observable;
                Object();
            }
        }
;
    }

    // Unreferenced inner class rx/operators/OperationReplay$CustomReplaySubject$1

/* anonymous class */
    class CustomReplaySubject._cls1
        implements rx.Observable.OnSubscribe
    {

        public volatile void call(Object obj)
        {
            call((Subscriber)obj);
        }

        public void call(Subscriber subscriber)
        {
            onSubscribe.onSubscribe(subscriber);
        }

        final rx.Observable.OnSubscribeFunc val$onSubscribe;

            
            {
                onSubscribe = onsubscribefunc;
                Object();
            }
    }


    // Unreferenced inner class rx/operators/OperationReplay$ReplayState$1

/* anonymous class */
    class ReplayState._cls1
        implements Action0
    {

        public void call()
        {
        }

        final ReplayState this$0;

            
            {
                this$0 = ReplayState.this;
                Object();
            }
    }


    // Unreferenced inner class rx/operators/OperationReplay$ReplayState$2

/* anonymous class */
    class ReplayState._cls2
        implements Action0
    {

        public void call()
        {
        }

        final ReplayState this$0;

            
            {
                this$0 = ReplayState.this;
                Object();
            }
    }


    // Unreferenced inner class rx/operators/OperationReplay$ReplayState$3

/* anonymous class */
    class ReplayState._cls3
        implements Action0
    {

        public void call()
        {
        }

        final ReplayState this$0;

            
            {
                this$0 = ReplayState.this;
                Object();
            }
    }


    // Unreferenced inner class rx/operators/OperationReplay$ReplayState$4

/* anonymous class */
    class ReplayState._cls4
        implements Action0
    {

        public void call()
        {
        }

        final ReplayState this$0;

            
            {
                this$0 = ReplayState.this;
                Object();
            }
    }

}
