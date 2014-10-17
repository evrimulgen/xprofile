// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import rx.*;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subscriptions.*;

public class OperationGroupJoin
    implements rx.Observable.OnSubscribeFunc
{
    class ResultManager
        implements Subscription
    {

        void groupsOnCompleted()
        {
            ArrayList arraylist = new ArrayList(leftMap.values());
            leftMap.clear();
            rightMap.clear();
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((Observer)iterator.next()).onCompleted());
        }

        public void init()
        {
            SerialSubscription serialsubscription = new SerialSubscription();
            SerialSubscription serialsubscription1 = new SerialSubscription();
            group.add(serialsubscription);
            group.add(serialsubscription1);
            serialsubscription.setSubscription(left.unsafeSubscribe(new LeftObserver(serialsubscription)));
            serialsubscription1.setSubscription(right.unsafeSubscribe(new RightObserver(serialsubscription1)));
        }

        public boolean isUnsubscribed()
        {
            return cancel.isUnsubscribed();
        }

        public void unsubscribe()
        {
            cancel.unsubscribe();
        }

        final RefCountSubscription cancel;
        final CompositeSubscription group = new CompositeSubscription();
        final Object guard = new Object();
        boolean leftDone;
        int leftIds;
        final Map leftMap = new HashMap();
        final Observer observer;
        boolean rightDone;
        int rightIds;
        final Map rightMap = new HashMap();
        final OperationGroupJoin this$0;

        public ResultManager(Observer observer1)
        {
            this$0 = OperationGroupJoin.this;
            super();
            observer = observer1;
            cancel = new RefCountSubscription(group);
        }
    }

    class ResultManager.LeftDurationObserver extends Subscriber
    {

        public void onCompleted()
        {
            synchronized(guard)
            {
                if(leftMap.remove(Integer.valueOf(id)) != null)
                    gr.onCompleted();
            }
            group.remove(sduration);
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            synchronized(guard)
            {
                observer.onError(throwable);
            }
            cancel.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            onCompleted();
        }

        final Observer gr;
        final int id;
        final Subscription sduration;
        final ResultManager this$1;

        public ResultManager.LeftDurationObserver(int i, Subscription subscription, Observer observer)
        {
            this$1 = ResultManager.this;
            super();
            id = i;
            sduration = subscription;
            gr = observer;
        }
    }

    class ResultManager.LeftObserver extends Subscriber
    {

        public void onCompleted()
        {
            synchronized(guard)
            {
                leftDone = true;
                if(rightDone)
                {
                    groupsOnCompleted();
                    observer.onCompleted();
                    cancel.unsubscribe();
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
            Object obj = guard;
            obj;
            JVM INSTR monitorenter ;
            for(Iterator iterator = leftMap.values().iterator(); iterator.hasNext(); ((Observer)iterator.next()).onError(throwable));
            break MISSING_BLOCK_LABEL_63;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            observer.onError(throwable);
            cancel.unsubscribe();
            obj;
            JVM INSTR monitorexit ;
        }

        public void onNext(Object obj)
        {
            PublishSubject publishsubject = PublishSubject.create();
            int i;
            synchronized(guard)
            {
                ResultManager resultmanager = ResultManager.this;
                i = resultmanager.leftIds;
                resultmanager.leftIds = i + 1;
                leftMap.put(Integer.valueOf(i), publishsubject);
            }
            Object obj2;
            Observable observable = Observable.create(new WindowObservableFunc(publishsubject, cancel));
            Observable observable1 = (Observable)leftDuration.call(obj);
            SerialSubscription serialsubscription = new SerialSubscription();
            group.add(serialsubscription);
            serialsubscription.setSubscription(observable1.unsafeSubscribe(new ResultManager.LeftDurationObserver(i, serialsubscription, publishsubject)));
            obj2 = resultSelector.call(obj, observable);
            Object obj3 = guard;
            obj3;
            JVM INSTR monitorenter ;
            observer.onNext(obj2);
            for(Iterator iterator = rightMap.values().iterator(); iterator.hasNext(); publishsubject.onNext(iterator.next()));
            break MISSING_BLOCK_LABEL_260;
            Exception exception1;
            exception1;
            obj3;
            JVM INSTR monitorexit ;
            try
            {
                throw exception1;
            }
            catch(Throwable throwable)
            {
                onError(throwable);
            }
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
            obj3;
            JVM INSTR monitorexit ;
        }

        final ResultManager this$1;
        final Subscription tosource;

        public ResultManager.LeftObserver(Subscription subscription)
        {
            this$1 = ResultManager.this;
            super();
            tosource = subscription;
        }
    }

    class ResultManager.RightDurationObserver extends Subscriber
    {

        public void onCompleted()
        {
            synchronized(guard)
            {
                rightMap.remove(Integer.valueOf(id));
            }
            group.remove(sduration);
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            synchronized(guard)
            {
                observer.onError(throwable);
            }
            cancel.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            onCompleted();
        }

        final int id;
        final Subscription sduration;
        final ResultManager this$1;

        public ResultManager.RightDurationObserver(int i, Subscription subscription)
        {
            this$1 = ResultManager.this;
            super();
            id = i;
            sduration = subscription;
        }
    }

    class ResultManager.RightObserver extends Subscriber
    {

        public void onCompleted()
        {
            synchronized(guard)
            {
                rightDone = true;
                if(leftDone)
                {
                    groupsOnCompleted();
                    observer.onCompleted();
                    cancel.unsubscribe();
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
            Object obj = guard;
            obj;
            JVM INSTR monitorenter ;
            for(Iterator iterator = leftMap.values().iterator(); iterator.hasNext(); ((Observer)iterator.next()).onError(throwable));
            break MISSING_BLOCK_LABEL_63;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            observer.onError(throwable);
            cancel.unsubscribe();
            obj;
            JVM INSTR monitorexit ;
        }

        public void onNext(Object obj)
        {
            int i;
            synchronized(guard)
            {
                ResultManager resultmanager = ResultManager.this;
                i = resultmanager.rightIds;
                resultmanager.rightIds = i + 1;
                rightMap.put(Integer.valueOf(i), obj);
            }
            Observable observable = (Observable)rightDuration.call(obj);
            SerialSubscription serialsubscription = new SerialSubscription();
            group.add(serialsubscription);
            serialsubscription.setSubscription(observable.unsafeSubscribe(new ResultManager.RightDurationObserver(i, serialsubscription)));
            Object obj2 = guard;
            obj2;
            JVM INSTR monitorenter ;
            for(Iterator iterator = leftMap.values().iterator(); iterator.hasNext(); ((Observer)iterator.next()).onNext(obj));
            break MISSING_BLOCK_LABEL_202;
            Exception exception1;
            exception1;
            obj2;
            JVM INSTR monitorexit ;
            try
            {
                throw exception1;
            }
            catch(Throwable throwable)
            {
                onError(throwable);
            }
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
            obj2;
            JVM INSTR monitorexit ;
        }

        final ResultManager this$1;
        final Subscription tosource;

        public ResultManager.RightObserver(Subscription subscription)
        {
            this$1 = ResultManager.this;
            super();
            tosource = subscription;
        }
    }

    static class WindowObservableFunc
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            compositesubscription.add(refCount.getSubscription());
            WindowObserver windowobserver = new WindowObserver(observer, compositesubscription);
            compositesubscription.add(underlying.unsafeSubscribe(windowobserver));
            return compositesubscription;
        }

        final RefCountSubscription refCount;
        final Observable underlying;

        public WindowObservableFunc(Observable observable, RefCountSubscription refcountsubscription)
        {
            refCount = refcountsubscription;
            underlying = observable;
        }
    }

    class WindowObservableFunc.WindowObserver extends Subscriber
    {

        public void onCompleted()
        {
            observer.onCompleted();
            self.unsubscribe();
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
            self.unsubscribe();
        }

        public void onNext(Object obj)
        {
            observer.onNext(obj);
        }

        final Observer observer;
        final Subscription self;
        final WindowObservableFunc this$0;

        public WindowObservableFunc.WindowObserver(Observer observer1, Subscription subscription)
        {
            this$0 = WindowObservableFunc.this;
            super();
            observer = observer1;
            self = subscription;
        }
    }


    public OperationGroupJoin(Observable observable, Observable observable1, Func1 func1, Func1 func1_1, Func2 func2)
    {
        left = observable;
        right = observable1;
        leftDuration = func1;
        rightDuration = func1_1;
        resultSelector = func2;
    }

    public Subscription onSubscribe(Observer observer)
    {
        ResultManager resultmanager = new ResultManager(observer);
        resultmanager.init();
        return resultmanager;
    }

    protected final Observable left;
    protected final Func1 leftDuration;
    protected final Func2 resultSelector;
    protected final Observable right;
    protected final Func1 rightDuration;
}
