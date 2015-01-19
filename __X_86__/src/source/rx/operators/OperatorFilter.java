// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

public final class OperatorFilter
    implements rx.Observable.Operator
{

    public OperatorFilter(Func1 func1)
    {
        predicate = func1;
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
                child.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                child.onError(throwable);
            }

            public void onNext(Object obj)
            {
                try
                {
                    if(((Boolean)predicate.call(obj)).booleanValue())
                        child.onNext(obj);
                    return;
                }
                catch(Throwable throwable)
                {
                    child.onError(OnErrorThrowable.addValueAsLastCause(throwable, obj));
                }
            }

            final OperatorFilter this$0;
            final Subscriber val$child;

            
            {
                this$0 = OperatorFilter.this;
                child = subscriber1;
                Subscriber(final_subscriber);
            }
        }
;
    }

    private final Func1 predicate;

}
