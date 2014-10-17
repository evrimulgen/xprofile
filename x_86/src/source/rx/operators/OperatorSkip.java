// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;

public final class OperatorSkip
    implements rx.Observable.Operator
{

    public OperatorSkip(int i)
    {
        n = i;
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
                if(skipped >= n)
                {
                    child.onNext(obj);
                    return;
                } else
                {
                    skipped = 1 + skipped;
                    return;
                }
            }

            int skipped;
            final OperatorSkip this$0;
            final Subscriber val$child;

            
            {
                this$0 = OperatorSkip.this;
                child = subscriber1;
                super(final_subscriber);
                skipped = 0;
            }
        }
;
    }

    final int n;
}
