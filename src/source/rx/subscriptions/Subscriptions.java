// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subscriptions;

import java.util.concurrent.Future;
import rx.Subscription;
import rx.functions.Action0;
import rx.operators.SafeObservableSubscription;

// Referenced classes of package rx.subscriptions:
//            CompositeSubscription

public final class Subscriptions
{

    public Subscriptions()
    {
    }

    public static Subscription create(Action0 action0)
    {
        return new SafeObservableSubscription(new Subscription(action0) {

            public boolean isUnsubscribed()
            {
                return unsubscribed;
            }

            public void unsubscribe()
            {
                unsubscribed = true;
                unsubscribe.call();
            }

            private volatile boolean unsubscribed;
            final Action0 val$unsubscribe;

            
            {
                unsubscribe = action0;
                super();
                unsubscribed = false;
            }
        }
);
    }

    public static Subscription empty()
    {
        return EMPTY;
    }

    public static Subscription from(Future future)
    {
        return new Subscription(future) {

            public boolean isUnsubscribed()
            {
                return f.isCancelled();
            }

            public void unsubscribe()
            {
                f.cancel(true);
            }

            final Future val$f;

            
            {
                f = future;
                super();
            }
        }
;
    }

    public static transient CompositeSubscription from(Subscription asubscription[])
    {
        return new CompositeSubscription(asubscription);
    }

    private static Subscription EMPTY = new Subscription() {

        public boolean isUnsubscribed()
        {
            return false;
        }

        public void unsubscribe()
        {
        }

    }
;

}
