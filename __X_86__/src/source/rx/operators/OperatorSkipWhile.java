// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

public final class OperatorSkipWhile
    implements rx.Observable.Operator
{

    public OperatorSkipWhile(Func2 func2)
    {
        predicate = func2;
    }

    public static Func2 toPredicate2(Func1 func1)
    {
        return new Func2(func1) {

            public Boolean call(Object obj, Integer integer)
            {
                return (Boolean)predicate.call(obj);
            }

            public volatile Object call(Object obj, Object obj1)
            {
                return call(obj, (Integer)obj1);
            }

            final Func1 val$predicate;

            
            {
                predicate = func1;
                super();
            }
        }
;
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
                if(!skipping)
                {
                    child.onNext(obj);
                } else
                {
                    Func2 func2 = predicate;
                    int i = index;
                    index = i + 1;
                    if(!((Boolean)func2.call(obj, Integer.valueOf(i))).booleanValue())
                    {
                        skipping = false;
                        child.onNext(obj);
                        return;
                    }
                }
            }

            int index;
            boolean skipping;
            final OperatorSkipWhile this$0;
            final Subscriber val$child;

            
            {
                this$0 = OperatorSkipWhile.this;
                child = subscriber1;
                super(final_subscriber);
                skipping = true;
            }
        }
;
    }

    private final Func2 predicate;

}
