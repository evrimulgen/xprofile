// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Subscriber;

public final class OperatorElementAt
    implements rx.Observable.Operator
{

    public OperatorElementAt(int i)
    {
        OperatorElementAt(i, null, false);
    }

    public OperatorElementAt(int i, Object obj)
    {
        OperatorElementAt(i, obj, true);
    }

    private OperatorElementAt(int i, Object obj, boolean flag)
    {
        if(i < 0)
        {
            throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(" is out of bounds").toString());
        } else
        {
            index = i;
            defaultValue = obj;
            hasDefault = flag;
            return;
        }
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber1)
    {
        return new Subscriber(final_subscriber1) {

            public void onCompleted()
            {
label0:
                {
                    if(currentIndex <= index)
                    {
                        if(!hasDefault)
                            break label0;
                        subscriber.onNext(defaultValue);
                        subscriber.onCompleted();
                    }
                    return;
                }
                subscriber.onError(new IndexOutOfBoundsException((new StringBuilder()).append(index).append(" is out of bounds").toString()));
            }

            public void onError(Throwable throwable)
            {
                subscriber.onError(throwable);
            }

            public void onNext(Object obj)
            {
                if(currentIndex == index)
                {
                    subscriber.onNext(obj);
                    subscriber.onCompleted();
                }
                currentIndex = 1 + currentIndex;
            }

            private int currentIndex;
            final OperatorElementAt this$0;
            final Subscriber val$subscriber;

            
            {
                this$0 = OperatorElementAt.this;
                subscriber = subscriber2;
                Subscriber(final_subscriber1);
                currentIndex = 0;
            }
        }
;
    }

    private final Object defaultValue;
    private final boolean hasDefault;
    private final int index;



}
