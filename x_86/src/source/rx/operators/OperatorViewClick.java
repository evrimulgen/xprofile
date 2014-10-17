// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import android.view.View;
import java.util.*;
import rx.Subscriber;
import rx.android.observables.Assertions;
import rx.android.subscriptions.AndroidSubscriptions;
import rx.functions.Action0;

public final class OperatorViewClick
    implements rx.Observable.OnSubscribe
{
    private static class CachedListeners
    {

        public static CompositeOnClickListener getFromViewOrCreate(View view1)
        {
            CompositeOnClickListener compositeonclicklistener = (CompositeOnClickListener)sCachedListeners.get(view1);
            if(compositeonclicklistener != null)
            {
                return compositeonclicklistener;
            } else
            {
                CompositeOnClickListener compositeonclicklistener1 = new CompositeOnClickListener();
                sCachedListeners.put(view1, compositeonclicklistener1);
                view1.setOnClickListener(compositeonclicklistener1);
                return compositeonclicklistener1;
            }
        }

        private static final Map sCachedListeners = new WeakHashMap();


        private CachedListeners()
        {
        }
    }

    private static class CompositeOnClickListener
        implements android.view.View.OnClickListener
    {

        public boolean addOnClickListener(android.view.View.OnClickListener onclicklistener)
        {
            return listeners.add(onclicklistener);
        }

        public void onClick(View view1)
        {
            for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((android.view.View.OnClickListener)iterator.next()).onClick(view1));
        }

        public boolean removeOnClickListener(android.view.View.OnClickListener onclicklistener)
        {
            return listeners.remove(onclicklistener);
        }

        private final List listeners;

        private CompositeOnClickListener()
        {
            listeners = new ArrayList();
        }

    }


    public OperatorViewClick(View view1, boolean flag)
    {
        emitInitialValue = flag;
        view = view1;
    }

    public volatile void call(Object obj)
    {
        call((Subscriber)obj);
    }

    public void call(final Subscriber observer)
    {
        Assertions.assertUiThread();
        final CompositeOnClickListener composite = CachedListeners.getFromViewOrCreate(view);
        final android.view.View.OnClickListener listener = new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                observer.onNext(view);
            }

            final OperatorViewClick this$0;
            final Subscriber val$observer;

            
            {
                this$0 = OperatorViewClick.this;
                observer = subscriber;
                super();
            }
        }
;
        rx.Subscription subscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0() {

            public void call()
            {
                composite.removeOnClickListener(listener);
            }

            final OperatorViewClick this$0;
            final CompositeOnClickListener val$composite;
            final android.view.View.OnClickListener val$listener;

            
            {
                this$0 = OperatorViewClick.this;
                composite = compositeonclicklistener;
                listener = onclicklistener;
                super();
            }
        }
);
        if(emitInitialValue)
            observer.onNext(view);
        composite.addOnClickListener(listener);
        observer.add(subscription);
    }

    private final boolean emitInitialValue;
    private final View view;

}
