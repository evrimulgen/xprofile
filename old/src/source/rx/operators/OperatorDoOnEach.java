// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Observer;
import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;

public class OperatorDoOnEach
    implements rx.Observable.Operator
{

    public OperatorDoOnEach(Observer observer)
    {
        doOnEachObserver = observer;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        return new Subscriber(final_subscriber) {

            public void onCompleted()
            {
                try
                {
                    doOnEachObserver.onCompleted();
                }
                catch(Throwable throwable)
                {
                    onError(throwable);
                    return;
                }
                observer.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                try
                {
                    doOnEachObserver.onError(throwable);
                }
                catch(Throwable throwable1)
                {
                    observer.onError(throwable1);
                    return;
                }
                observer.onError(throwable);
            }

            public void onNext(Object obj)
            {
                try
                {
                    doOnEachObserver.onNext(obj);
                }
                catch(Throwable throwable)
                {
                    onError(OnErrorThrowable.addValueAsLastCause(throwable, obj));
                    return;
                }
                observer.onNext(obj);
            }

            final OperatorDoOnEach this$0;
            final Subscriber val$observer;

            
            {
                this$0 = OperatorDoOnEach.this;
                observer = subscriber1;
                Subscriber(final_subscriber);
            }
        }
;
    }

    private final Observer doOnEachObserver;

}
