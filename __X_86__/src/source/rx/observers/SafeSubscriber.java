// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observers;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscriber;
import rx.exceptions.*;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public class SafeSubscriber extends Subscriber
{

    public SafeSubscriber(Subscriber subscriber)
    {
        super(subscriber);
        actual = subscriber;
    }

    private void handlePluginException(Throwable throwable)
    {
        System.err.println((new StringBuilder()).append("RxJavaErrorHandler threw an Exception. It shouldn't. => ").append(throwable.getMessage()).toString());
        throwable.printStackTrace();
    }

    protected void _onError(Throwable throwable)
    {
        try
        {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable);
        }
        catch(Throwable throwable1)
        {
            handlePluginException(throwable1);
        }
        try
        {
            actual.onError(throwable);
        }
        catch(Throwable throwable2)
        {
            if(throwable2 instanceof OnErrorNotImplementedException)
            {
                try
                {
                    unsubscribe();
                }
                catch(Throwable throwable6)
                {
                    try
                    {
                        RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable6);
                    }
                    catch(Throwable throwable7)
                    {
                        handlePluginException(throwable7);
                    }
                    throw new RuntimeException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(new Throwable[] {
                        throwable, throwable6
                    })));
                }
                throw (OnErrorNotImplementedException)throwable2;
            }
            try
            {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable2);
            }
            catch(Throwable throwable3)
            {
                handlePluginException(throwable3);
            }
            try
            {
                unsubscribe();
            }
            catch(Throwable throwable4)
            {
                try
                {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable4);
                }
                catch(Throwable throwable5)
                {
                    handlePluginException(throwable5);
                }
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(new Throwable[] {
                    throwable, throwable2, throwable4
                })));
            }
            throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(new Throwable[] {
                throwable, throwable2
            })));
        }
        try
        {
            unsubscribe();
            return;
        }
        catch(RuntimeException runtimeexception)
        {
            try
            {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(runtimeexception);
            }
            catch(Throwable throwable8)
            {
                handlePluginException(throwable8);
            }
        }
        throw new OnErrorFailedException(runtimeexception);
    }

    public Subscriber getActual()
    {
        return actual;
    }

    public void onCompleted()
    {
        if(!isFinished.compareAndSet(false, true))
            break MISSING_BLOCK_LABEL_23;
        actual.onCompleted();
        unsubscribe();
        return;
        Throwable throwable;
        throwable;
        Exceptions.throwIfFatal(throwable);
        _onError(throwable);
        unsubscribe();
        return;
        Exception exception;
        exception;
        unsubscribe();
        throw exception;
    }

    public void onError(Throwable throwable)
    {
        Exceptions.throwIfFatal(throwable);
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
            Exceptions.throwIfFatal(throwable);
            onError(throwable);
            return;
        }
    }

    private final Subscriber actual;
    private final AtomicBoolean isFinished = new AtomicBoolean(false);
}
