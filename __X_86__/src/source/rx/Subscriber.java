// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx;

import rx.subscriptions.CompositeSubscription;

// Referenced classes of package rx:
//            Observer, Subscription

public abstract class Subscriber
    implements Observer, Subscription
{

    protected Subscriber()
    {
        this(new CompositeSubscription());
    }

    protected Subscriber(Subscriber subscriber)
    {
        this(subscriber.cs);
    }

    protected Subscriber(CompositeSubscription compositesubscription)
    {
        if(compositesubscription == null)
        {
            throw new IllegalArgumentException("The CompositeSubscription can not be null");
        } else
        {
            cs = compositesubscription;
            return;
        }
    }

    public final void add(Subscription subscription)
    {
        cs.add(subscription);
    }

    public final boolean isUnsubscribed()
    {
        return cs.isUnsubscribed();
    }

    public final void unsubscribe()
    {
        cs.unsubscribe();
    }

    private final CompositeSubscription cs;
}
