// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;

public class OperatorCast
    implements rx.Observable.Operator
{

    public OperatorCast(Class class1)
    {
        castClass = class1;
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
                o.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                o.onError(throwable);
            }

            public void onNext(Object obj)
            {
                try
                {
                    o.onNext(castClass.cast(obj));
                    return;
                }
                catch(Throwable throwable)
                {
                    onError(OnErrorThrowable.addValueAsLastCause(throwable, obj));
                }
            }

            final OperatorCast this$0;
            final Subscriber val$o;

            
            {
                this$0 = OperatorCast.this;
                o = subscriber1;
                Subscriber(final_subscriber);
            }
        }
;
    }

    private final Class castClass;

}
