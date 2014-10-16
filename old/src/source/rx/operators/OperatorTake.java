// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public final class OperatorTake
    implements rx.Observable.Operator
{

    public OperatorTake(int i)
    {
        limit = i;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(Subscriber subscriber)
    {
        final CompositeSubscription final_compositesubscription = new CompositeSubscription();
        if(limit == 0)
        {
            subscriber.onCompleted();
            final_compositesubscription.unsubscribe();
        }
        subscriber.add(final_compositesubscription);
        return new Subscriber(subscriber) {

            public void onCompleted()
            {
                if(!completed)
                    child.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                if(!completed)
                    child.onError(throwable);
            }

            public void onNext(Object obj)
            {
                if(!isUnsubscribed())
                {
                    child.onNext(obj);
                    int i = 1 + count;
                    count = i;
                    if(i >= limit)
                    {
                        completed = true;
                        child.onCompleted();
                        unsubscribe();
                    }
                }
            }

            boolean completed;
            int count;
            final OperatorTake this$0;
            final Subscriber val$child;

            
            {
                this$0 = OperatorTake.this;
                child = subscriber;
                super(final_compositesubscription);
                count = 0;
                completed = false;
            }
        }
;
    }

    final int limit;
}
