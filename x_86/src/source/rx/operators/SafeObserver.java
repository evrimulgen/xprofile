// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.exceptions.CompositeException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public class SafeObserver extends Subscriber
{

    public SafeObserver(Observer observer)
    {
        isFinished = new AtomicBoolean(false);
        subscription = Subscriptions.empty();
        actual = observer;
    }

    public SafeObserver(SafeObservableSubscription safeobservablesubscription, Observer observer)
    {
        isFinished = new AtomicBoolean(false);
        subscription = safeobservablesubscription;
        actual = observer;
    }

    protected void _onError(Throwable throwable)
    {
        try
        {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable);
            actual.onError(throwable);
        }
        catch(Throwable throwable1)
        {
            if(throwable1 instanceof OnErrorNotImplementedException)
            {
                try
                {
                    subscription.unsubscribe();
                }
                catch(Throwable throwable3)
                {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable3);
                    throw new RuntimeException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(new Throwable[] {
                        throwable, throwable3
                    })));
                }
                throw (OnErrorNotImplementedException)throwable1;
            }
            RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable1);
            try
            {
                subscription.unsubscribe();
            }
            catch(Throwable throwable2)
            {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable2);
                throw new RuntimeException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(new Throwable[] {
                    throwable, throwable1, throwable2
                })));
            }
            throw new RuntimeException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(new Throwable[] {
                throwable, throwable1
            })));
        }
        try
        {
            subscription.unsubscribe();
            return;
        }
        catch(RuntimeException runtimeexception)
        {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(runtimeexception);
            throw runtimeexception;
        }
    }

    public void onCompleted()
    {
        if(!isFinished.compareAndSet(false, true))
            break MISSING_BLOCK_LABEL_30;
        actual.onCompleted();
        subscription.unsubscribe();
        return;
        Throwable throwable;
        throwable;
        _onError(throwable);
        subscription.unsubscribe();
        return;
        Exception exception;
        exception;
        subscription.unsubscribe();
        throw exception;
    }

    public void onError(Throwable throwable)
    {
        if(isFinished.compareAndSet(false, true))
            _onError(throwable);
    }

    public void onNext(Object obj)
    {
        try
        {
            if(!isFinished.get())
                actual.onNext(obj);
            return;
        }
        catch(Throwable throwable)
        {
            onError(throwable);
        }
    }

    private final Observer actual;
    private final AtomicBoolean isFinished;
    private final Subscription subscription;
}
