// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;

public final class OperatorToObservableList
    implements rx.Observable.Operator
{

    public OperatorToObservableList()
    {
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
                    o.onNext(new ArrayList(list));
                    o.onCompleted();
                    return;
                }
                catch(Throwable throwable)
                {
                    onError(throwable);
                }
            }

            public void onError(Throwable throwable)
            {
                o.onError(throwable);
            }

            public void onNext(Object obj)
            {
                list.add(obj);
            }

            final List list = new ArrayList();
            final OperatorToObservableList this$0;
            final Subscriber val$o;

            
            {
                this$0 = OperatorToObservableList.this;
                o = subscriber1;
                super(final_subscriber);
            }
        }
;
    }
}
