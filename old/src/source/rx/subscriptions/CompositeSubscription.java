// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subscriptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.exceptions.CompositeException;

public final class CompositeSubscription
    implements Subscription
{
    private static final class State
    {

        State add(Subscription subscription)
        {
            int i = subscriptions.length;
            Subscription asubscription[] = new Subscription[i + 1];
            System.arraycopy(subscriptions, 0, asubscription, 0, i);
            asubscription[i] = subscription;
            return new State(isUnsubscribed, asubscription);
        }

        State clear()
        {
            if(isUnsubscribed)
                return CompositeSubscription.CLEAR_STATE_UNSUBSCRIBED;
            else
                return CompositeSubscription.CLEAR_STATE;
        }

        State remove(Subscription subscription)
        {
            if((subscriptions.length != 1 || !subscriptions[0].equals(subscription)) && subscriptions.length != 0) goto _L2; else goto _L1
_L1:
            this = clear();
_L6:
            return this;
_L2:
            Subscription asubscription[];
            int i;
            Subscription asubscription1[];
            int j;
            int k;
            asubscription = new Subscription[-1 + subscriptions.length];
            i = 0;
            asubscription1 = subscriptions;
            j = asubscription1.length;
            k = 0;
_L7:
            if(k >= j) goto _L4; else goto _L3
_L3:
            Subscription subscription1;
            subscription1 = asubscription1[k];
            if(subscription1.equals(subscription))
                continue; /* Loop/switch isn't completed */
            if(i == subscriptions.length) goto _L6; else goto _L5
_L5:
            asubscription[i] = subscription1;
            i++;
            k++;
              goto _L7
_L4:
            if(i == 0)
                return clear();
            if(i < asubscription.length)
            {
                Subscription asubscription2[] = new Subscription[i];
                System.arraycopy(asubscription, 0, asubscription2, 0, i);
                return new State(isUnsubscribed, asubscription2);
            } else
            {
                return new State(isUnsubscribed, asubscription);
            }
        }

        State unsubscribe()
        {
            return CompositeSubscription.CLEAR_STATE_UNSUBSCRIBED;
        }

        final boolean isUnsubscribed;
        final Subscription subscriptions[];

        State(boolean flag, Subscription asubscription[])
        {
            isUnsubscribed = flag;
            subscriptions = asubscription;
        }
    }


    public CompositeSubscription()
    {
        state = new AtomicReference();
        state.set(CLEAR_STATE);
    }

    public transient CompositeSubscription(Subscription asubscription[])
    {
        state = new AtomicReference();
        state.set(new State(false, asubscription));
    }

    private static void unsubscribeFromAll(Subscription asubscription[])
    {
        ArrayList arraylist = new ArrayList();
        int i = asubscription.length;
        int j = 0;
        while(j < i) 
        {
            Subscription subscription = asubscription[j];
            try
            {
                subscription.unsubscribe();
            }
            catch(Throwable throwable1)
            {
                arraylist.add(throwable1);
            }
            j++;
        }
        if(!arraylist.isEmpty())
        {
            if(arraylist.size() == 1)
            {
                Throwable throwable = (Throwable)arraylist.get(0);
                if(throwable instanceof RuntimeException)
                    throw (RuntimeException)throwable;
                else
                    throw new CompositeException("Failed to unsubscribe to 1 or more subscriptions.", arraylist);
            } else
            {
                throw new CompositeException("Failed to unsubscribe to 2 or more subscriptions.", arraylist);
            }
        } else
        {
            return;
        }
    }

    public void add(Subscription subscription)
    {
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
            state2 = state1.add(subscription);
        } while(!state.compareAndSet(state1, state2));
    }

    public void clear()
    {
        State state1;
        State state2;
        do
        {
            state1 = (State)state.get();
            if(state1.isUnsubscribed)
                return;
            state2 = state1.clear();
        } while(!state.compareAndSet(state1, state2));
        unsubscribeFromAll(state1.subscriptions);
    }

    public boolean isUnsubscribed()
    {
        return ((State)state.get()).isUnsubscribed;
    }

    public void remove(Subscription subscription)
    {
        State state1;
        State state2;
        do
        {
            state1 = (State)state.get();
            if(state1.isUnsubscribed)
                return;
            state2 = state1.remove(subscription);
        } while(!state.compareAndSet(state1, state2));
        subscription.unsubscribe();
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
        unsubscribeFromAll(state1.subscriptions);
    }

    private static final State CLEAR_STATE;
    private static final State CLEAR_STATE_UNSUBSCRIBED;
    private final AtomicReference state;

    static 
    {
        Subscription asubscription[] = new Subscription[0];
        CLEAR_STATE = new State(false, asubscription);
        CLEAR_STATE_UNSUBSCRIBED = new State(true, asubscription);
    }


}
