// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

public final class SafeObservableSubscription
    implements Subscription
{

    public SafeObservableSubscription()
    {
        actualSubscription = new AtomicReference();
    }

    public SafeObservableSubscription(Subscription subscription)
    {
        actualSubscription = new AtomicReference();
        actualSubscription.set(subscription);
    }

    public boolean isUnsubscribed()
    {
        return actualSubscription.get() == UNSUBSCRIBED;
    }

    public void unsubscribe()
    {
        Subscription subscription = (Subscription)actualSubscription.getAndSet(UNSUBSCRIBED);
        if(subscription != null)
            subscription.unsubscribe();
    }

    public SafeObservableSubscription wrap(Subscription subscription)
    {
label0:
        {
            if(!actualSubscription.compareAndSet(null, subscription))
            {
                if(actualSubscription.get() != UNSUBSCRIBED)
                    break label0;
                subscription.unsubscribe();
            }
            return this;
        }
        throw new IllegalStateException("Can not set subscription more than once.");
    }

    private static final Subscription UNSUBSCRIBED = new Subscription() {

        public boolean isUnsubscribed()
        {
            return true;
        }

        public void unsubscribe()
        {
        }

    }
;
    private final AtomicReference actualSubscription;

}
