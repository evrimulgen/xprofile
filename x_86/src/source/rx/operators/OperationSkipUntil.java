// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.observers.Subscribers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;

public class OperationSkipUntil
    implements rx.Observable.OnSubscribeFunc
{
    private class ResultManager
        implements Subscription, Observer
    {

        public ResultManager init()
        {
            SerialSubscription serialsubscription = new SerialSubscription();
            SerialSubscription serialsubscription1 = new SerialSubscription();
            cancel.add(serialsubscription);
            cancel.add(serialsubscription1);
            serialsubscription.setSubscription(source.unsafeSubscribe(Subscribers.from(this)));
            serialsubscription1.setSubscription(other.unsafeSubscribe(new OtherObserver(serialsubscription1)));
            return this;
        }

        public boolean isUnsubscribed()
        {
            return cancel.isUnsubscribed();
        }

        public void onCompleted()
        {
            synchronized(guard)
            {
                observer.onCompleted();
                unsubscribe();
            }
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
                unsubscribe();
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            if(running.get())
                observer.onNext(obj);
        }

        public void unsubscribe()
        {
            cancel.unsubscribe();
        }

        final CompositeSubscription cancel = new CompositeSubscription();
        final Object guard = new Object();
        final Observer observer;
        final AtomicBoolean running = new AtomicBoolean();
        final OperationSkipUntil this$0;

        public ResultManager(Observer observer1)
        {
            this$0 = OperationSkipUntil.this;
            Object();
            observer = observer1;
        }
    }

    private class ResultManager.OtherObserver extends Subscriber
    {

        public void onCompleted()
        {
            self.unsubscribe();
        }

        public void onError(Throwable throwable)
        {
            ResultManager.this.onError(throwable);
        }

        public void onNext(Object obj)
        {
            running.set(true);
            self.unsubscribe();
        }

        final Subscription self;
        final ResultManager this$1;

        public ResultManager.OtherObserver(Subscription subscription)
        {
            this$1 = ResultManager.this;
            Subscriber();
            self = subscription;
        }
    }


    public OperationSkipUntil(Observable observable, Observable observable1)
    {
        source = observable;
        other = observable1;
    }

    public Subscription onSubscribe(Observer observer)
    {
        return (new ResultManager(observer)).init();
    }

    protected final Observable other;
    protected final Observable source;
}
