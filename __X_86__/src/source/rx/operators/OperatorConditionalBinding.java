// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import android.util.Log;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Functions;

public final class OperatorConditionalBinding
    implements rx.Observable.Operator
{

    public OperatorConditionalBinding(Object obj)
    {
        boundRef = obj;
        predicate = Functions.alwaysTrue();
    }

    public OperatorConditionalBinding(Object obj, Func1 func1)
    {
        boundRef = obj;
        predicate = func1;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        return new Subscriber(final_subscriber) {

            private void handleLostBinding(String s)
            {
                log((new StringBuilder()).append("bound object has become invalid; skipping ").append(s).toString());
                log("unsubscribing...");
                boundRef = null;
                unsubscribe();
            }

            private void log(String s)
            {
                if(Log.isLoggable("ConditionalBinding", 3))
                    Log.d("ConditionalBinding", s);
            }

            private boolean shouldForwardNotification()
            {
                return boundRef != null && ((Boolean)predicate.call(boundRef)).booleanValue();
            }

            public void onCompleted()
            {
                if(shouldForwardNotification())
                {
                    child.onCompleted();
                    return;
                } else
                {
                    handleLostBinding("onCompleted");
                    return;
                }
            }

            public void onError(Throwable throwable)
            {
                if(shouldForwardNotification())
                {
                    child.onError(throwable);
                    return;
                } else
                {
                    handleLostBinding("onError");
                    return;
                }
            }

            public void onNext(Object obj)
            {
                if(shouldForwardNotification())
                {
                    child.onNext(obj);
                    return;
                } else
                {
                    handleLostBinding("onNext");
                    return;
                }
            }

            final OperatorConditionalBinding this$0;
            final Subscriber val$child;

            
            {
                this$0 = OperatorConditionalBinding.this;
                child = subscriber1;
                Subscriber(final_subscriber);
            }
        }
;
    }

    Object getBoundRef()
    {
        return boundRef;
    }

    private static final String LOG_TAG = "ConditionalBinding";
    private Object boundRef;
    private final Func1 predicate;



/*
    static Object access$002(OperatorConditionalBinding operatorconditionalbinding, Object obj)
    {
        operatorconditionalbinding.boundRef = obj;
        return obj;
    }

*/

}
