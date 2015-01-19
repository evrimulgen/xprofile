// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func2;

public final class OperatorScan
    implements rx.Observable.Operator
{

    public OperatorScan(Object obj, Func2 func2)
    {
        initialValue = obj;
        accumulator = func2;
    }

    public OperatorScan(Func2 func2)
    {
        this(NO_INITIAL_VALUE, func2);
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        if(initialValue != NO_INITIAL_VALUE)
            final_subscriber.onNext(initialValue);
        return new Subscriber(final_subscriber) {

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
                if(value == OperatorScan.NO_INITIAL_VALUE)
                    value = obj;
                else
                    try
                    {
                        value = accumulator.call(value, obj);
                    }
                    catch(Throwable throwable)
                    {
                        observer.onError(OnErrorThrowable.addValueAsLastCause(throwable, obj));
                    }
                observer.onNext(value);
            }

            final OperatorScan this$0;
            final Subscriber val$observer;
            private Object value;

            
            {
                this$0 = OperatorScan.this;
                observer = subscriber1;
                super(final_subscriber);
                value = initialValue;
            }
        }
;
    }

    private static final Object NO_INITIAL_VALUE = new Object();
    private final Func2 accumulator;
    private final Object initialValue;




}
