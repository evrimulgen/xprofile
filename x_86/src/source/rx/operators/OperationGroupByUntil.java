// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import rx.*;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;

public class OperationGroupByUntil
    implements rx.Observable.OnSubscribeFunc
{
    public static class GroupSubject extends Subscriber
    {

        public void onCompleted()
        {
            publish.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            publish.onError(throwable);
        }

        public void onNext(Object obj)
        {
            publish.onNext(obj);
        }

        public GroupedObservable toObservable()
        {
            return new GroupedObservable(key, new rx.Observable.OnSubscribe() {

                public volatile void call(Object obj)
                {
                    call((Subscriber)obj);
                }

                public void call(Subscriber subscriber)
                {
                    publish.unsafeSubscribe(subscriber);
                }

                final GroupSubject this$0;

            
            {
                this$0 = GroupSubject.this;
                super();
            }
            }
);
        }

        private final Object key;
        protected final Subject publish;

        public GroupSubject(Object obj, Subject subject)
        {
            key = obj;
            publish = subject;
        }
    }

    class ResultSink extends Subscriber
    {

        public GroupSubject create(Object obj)
        {
            return new GroupSubject(obj, PublishSubject.create());
        }

        public void expire(Object obj, Subscription subscription)
        {
            Object obj1 = gate;
            obj1;
            JVM INSTR monitorenter ;
            GroupSubject groupsubject = (GroupSubject)map.remove(obj);
            if(groupsubject == null)
                break MISSING_BLOCK_LABEL_32;
            groupsubject.onCompleted();
            obj1;
            JVM INSTR monitorexit ;
            subscription.unsubscribe();
            return;
            Exception exception;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onCompleted()
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            ArrayList arraylist = new ArrayList(map.values());
            map.clear();
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((GroupSubject)iterator.next()).onCompleted());
            break MISSING_BLOCK_LABEL_72;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            observer.onCompleted();
            obj;
            JVM INSTR monitorexit ;
            cancel.unsubscribe();
            return;
        }

        public void onError(Throwable throwable)
        {
            Object obj = gate;
            obj;
            JVM INSTR monitorenter ;
            ArrayList arraylist = new ArrayList(map.values());
            map.clear();
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((GroupSubject)iterator.next()).onError(throwable));
            break MISSING_BLOCK_LABEL_75;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            observer.onError(throwable);
            obj;
            JVM INSTR monitorexit ;
            cancel.unsubscribe();
            return;
        }

        public void onNext(Object obj)
        {
            Object obj1;
            Object obj2;
            Object obj3;
            GroupSubject groupsubject;
            boolean flag;
            try
            {
                obj1 = keySelector.call(obj);
                obj2 = valueSelector.call(obj);
            }
            catch(Throwable throwable)
            {
                onError(throwable);
                return;
            }
            obj3 = gate;
            obj3;
            JVM INSTR monitorenter ;
            groupsubject = (GroupSubject)map.get(obj1);
            flag = false;
            if(groupsubject != null)
                break MISSING_BLOCK_LABEL_84;
            groupsubject = create(obj1);
            map.put(obj1, groupsubject);
            flag = true;
            obj3;
            JVM INSTR monitorexit ;
            if(flag)
            {
                Exception exception;
                Observable observable;
                SerialSubscription serialsubscription;
                try
                {
                    observable = (Observable)durationSelector.call(groupsubject.toObservable());
                }
                catch(Throwable throwable1)
                {
                    onError(throwable1);
                    return;
                }
                synchronized(gate)
                {
                    observer.onNext(groupsubject.toObservable());
                }
                serialsubscription = new SerialSubscription();
                group.add(serialsubscription);
                serialsubscription.set(observable.unsafeSubscribe(new DurationObserver(obj1, serialsubscription)));
            }
            synchronized(gate)
            {
                groupsubject.onNext(obj2);
            }
            return;
            exception;
            obj3;
            JVM INSTR monitorexit ;
            throw exception;
            exception2;
            obj5;
            JVM INSTR monitorexit ;
            throw exception2;
            exception1;
            obj4;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        public Subscription run()
        {
            SerialSubscription serialsubscription = new SerialSubscription();
            group.add(serialsubscription);
            serialsubscription.set(source.unsafeSubscribe(this));
            return group;
        }

        protected final Subscription cancel;
        protected final Object gate = new Object();
        protected final CompositeSubscription group = new CompositeSubscription();
        protected final Map map = new HashMap();
        protected final Observer observer;
        final OperationGroupByUntil this$0;

        public ResultSink(Observer observer1, Subscription subscription)
        {
            this$0 = OperationGroupByUntil.this;
            super();
            observer = observer1;
            cancel = subscription;
        }
    }

    class ResultSink.DurationObserver extends Subscriber
    {

        public void onCompleted()
        {
            expire(key, handle);
        }

        public void onError(Throwable throwable)
        {
            ResultSink.this.onError(throwable);
        }

        public void onNext(Object obj)
        {
            expire(key, handle);
        }

        final Subscription handle;
        final Object key;
        final ResultSink this$1;

        public ResultSink.DurationObserver(Object obj, Subscription subscription)
        {
            this$1 = ResultSink.this;
            super();
            key = obj;
            handle = subscription;
        }
    }


    public OperationGroupByUntil(Observable observable, Func1 func1, Func1 func1_1, Func1 func1_2)
    {
        source = observable;
        keySelector = func1;
        valueSelector = func1_1;
        durationSelector = func1_2;
    }

    public Subscription onSubscribe(Observer observer)
    {
        SerialSubscription serialsubscription = new SerialSubscription();
        serialsubscription.set((new ResultSink(observer, serialsubscription)).run());
        return serialsubscription;
    }

    final Func1 durationSelector;
    final Func1 keySelector;
    final Observable source;
    final Func1 valueSelector;
}
