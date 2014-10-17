// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

// Referenced classes of package rx.subscriptions:
//            Subscriptions

public final class MultipleAssignmentSubscription
    implements Subscription
{
    private static final class State
    {

        State set(Subscription subscription1)
        {
            return new State(isUnsubscribed, subscription1);
        }

        State unsubscribe()
        {
            return new State(true, subscription);
        }

        final boolean isUnsubscribed;
        final Subscription subscription;

        State(boolean flag, Subscription subscription1)
        {
            isUnsubscribed = flag;
            subscription = subscription1;
        }
    }


    public MultipleAssignmentSubscription()
    {
    }

    public Subscription get()
    {
        return ((State)state.get()).subscription;
    }

    public Subscription getSubscription()
    {
        return get();
    }

    public boolean isUnsubscribed()
    {
        return ((State)state.get()).isUnsubscribed;
    }

    public void set(Subscription subscription)
    {
        if(subscription == null)
            throw new IllegalArgumentException("Subscription can not be null");
        State state1;
        State state2;
        do
        {
            state1 = (State)state.get();
            if(state1.isUnsubscribed)
            {
                subscription.unsubscribe();
                return;
            }
            state2 = state1.set(subscription);
        } while(!state.compareAndSet(state1, state2));
    }

    public void setSubscription(Subscription subscription)
    {
        set(subscription);
    }

    public void unsubscribe()
    {
        State state1;
        State state2;
        do
        {
            state1 = (State)state.get();
            if(state1.isUnsubscribed)
                return;
            state2 = state1.unsubscribe();
        } while(!state.compareAndSet(state1, state2));
        state1.subscription.unsubscribe();
    }

    private final AtomicReference state = new AtomicReference(new State(false, Subscriptions.empty()));
}
