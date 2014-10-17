// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subscriptions;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

// Referenced classes of package rx.subscriptions:
//            Subscriptions

public final class RefCountSubscription
    implements Subscription
{
    private final class InnerSubscription
        implements Subscription
    {

        public boolean isUnsubscribed()
        {
            return innerDone.get();
        }

        public void unsubscribe()
        {
            if(innerDone.compareAndSet(false, true))
            {
                State state1;
                State state2;
                do
                {
                    state1 = (State)state.get();
                    state2 = state1.removeChild();
                } while(!state.compareAndSet(state1, state2));
                unsubscribeActualIfApplicable(state2);
            }
        }

        final AtomicBoolean innerDone;
        final RefCountSubscription this$0;

        private InnerSubscription()
        {
            this$0 = RefCountSubscription.this;
            super();
            innerDone = new AtomicBoolean();
        }

    }

    private static final class State
    {

        State addChild()
        {
            return new State(isUnsubscribed, 1 + children);
        }

        State removeChild()
        {
            return new State(isUnsubscribed, -1 + children);
        }

        State unsubscribe()
        {
            return new State(true, children);
        }

        final int children;
        final boolean isUnsubscribed;

        State(boolean flag, int i)
        {
            isUnsubscribed = flag;
            children = i;
        }
    }


    public RefCountSubscription(Subscription subscription)
    {
        if(subscription == null)
        {
            throw new IllegalArgumentException("s");
        } else
        {
            actual = subscription;
            return;
        }
    }

    private void unsubscribeActualIfApplicable(State state1)
    {
        if(state1.isUnsubscribed && state1.children == 0)
            actual.unsubscribe();
    }

    public Subscription get()
    {
        State state1;
        State state2;
        do
        {
            state1 = (State)state.get();
            if(state1.isUnsubscribed)
                return Subscriptions.empty();
            state2 = state1.addChild();
        } while(!state.compareAndSet(state1, state2));
        return new InnerSubscription();
    }

    public Subscription getSubscription()
    {
        return get();
    }

    public boolean isUnsubscribed()
    {
        return ((State)state.get()).isUnsubscribed;
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
        unsubscribeActualIfApplicable(state2);
    }

    private final Subscription actual;
    private final AtomicReference state = new AtomicReference(new State(false, 0));


}
