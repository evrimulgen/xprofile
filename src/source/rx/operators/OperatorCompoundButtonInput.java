// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import android.widget.CompoundButton;
import java.util.*;
import rx.Subscriber;
import rx.android.observables.Assertions;
import rx.android.subscriptions.AndroidSubscriptions;
import rx.functions.Action0;

public class OperatorCompoundButtonInput
    implements rx.Observable.OnSubscribe
{
    private static class CachedListeners
    {

        public static CompositeOnCheckedChangeListener getFromViewOrCreate(CompoundButton compoundbutton)
        {
            CompositeOnCheckedChangeListener compositeoncheckedchangelistener = (CompositeOnCheckedChangeListener)sCachedListeners.get(compoundbutton);
            if(compositeoncheckedchangelistener != null)
            {
                return compositeoncheckedchangelistener;
            } else
            {
                CompositeOnCheckedChangeListener compositeoncheckedchangelistener1 = new CompositeOnCheckedChangeListener();
                sCachedListeners.put(compoundbutton, compositeoncheckedchangelistener1);
                compoundbutton.setOnCheckedChangeListener(compositeoncheckedchangelistener1);
                return compositeoncheckedchangelistener1;
            }
        }

        private static final Map sCachedListeners = new WeakHashMap();


        private CachedListeners()
        {
        }
    }

    private static class CompositeOnCheckedChangeListener
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        public boolean addOnCheckedChangeListener(android.widget.CompoundButton.OnCheckedChangeListener oncheckedchangelistener)
        {
            return listeners.add(oncheckedchangelistener);
        }

        public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((android.widget.CompoundButton.OnCheckedChangeListener)iterator.next()).onCheckedChanged(compoundbutton, flag));
        }

        public boolean removeOnCheckedChangeListener(android.widget.CompoundButton.OnCheckedChangeListener oncheckedchangelistener)
        {
            return listeners.remove(oncheckedchangelistener);
        }

        private final List listeners;

        private CompositeOnCheckedChangeListener()
        {
            listeners = new ArrayList();
        }

    }


    public OperatorCompoundButtonInput(CompoundButton compoundbutton, boolean flag)
    {
        emitInitialValue = flag;
        button = compoundbutton;
    }

    public volatile void call(Object obj)
    {
        call((Subscriber)obj);
    }

    public void call(final Subscriber observer)
    {
        Assertions.assertUiThread();
        final CompositeOnCheckedChangeListener composite = CachedListeners.getFromViewOrCreate(button);
        final android.widget.CompoundButton.OnCheckedChangeListener listener = new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                observer.onNext(Boolean.valueOf(flag));
            }

            final OperatorCompoundButtonInput this$0;
            final Subscriber val$observer;

            
            {
                this$0 = OperatorCompoundButtonInput.this;
                observer = subscriber;
                Object();
            }
        }
;
        rx.Subscription subscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0() {

            public void call()
            {
                composite.removeOnCheckedChangeListener(listener);
            }

            final OperatorCompoundButtonInput this$0;
            final CompositeOnCheckedChangeListener val$composite;
            final android.widget.CompoundButton.OnCheckedChangeListener val$listener;

            
            {
                this$0 = OperatorCompoundButtonInput.this;
                composite = compositeoncheckedchangelistener;
                listener = oncheckedchangelistener;
                Object();
            }
        }
);
        if(emitInitialValue)
            observer.onNext(Boolean.valueOf(button.isChecked()));
        composite.addOnCheckedChangeListener(listener);
        observer.add(subscription);
    }

    private final CompoundButton button;
    private final boolean emitInitialValue;
}
