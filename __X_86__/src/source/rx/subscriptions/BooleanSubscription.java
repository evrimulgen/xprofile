// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subscriptions;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscription;
import rx.functions.Action0;

public final class BooleanSubscription
    implements Subscription
{

    public BooleanSubscription()
    {
        unsubscribed = new AtomicBoolean(false);
        action = null;
    }

    private BooleanSubscription(Action0 action0)
    {
        unsubscribed = new AtomicBoolean(false);
        action = action0;
    }

    public static BooleanSubscription create()
    {
        return new BooleanSubscription();
    }

    public static BooleanSubscription create(Action0 action0)
    {
        return new BooleanSubscription(action0);
    }

    public boolean isUnsubscribed()
    {
        return unsubscribed.get();
    }

    public final void unsubscribe()
    {
        if(unsubscribed.compareAndSet(false, true) && action != null)
            action.call();
    }

    private final Action0 action;
    private final AtomicBoolean unsubscribed;
}
