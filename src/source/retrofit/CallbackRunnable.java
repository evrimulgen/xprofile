// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.util.concurrent.Executor;

// Referenced classes of package retrofit:
//            RetrofitError, ErrorHandler, Callback, ResponseWrapper

abstract class CallbackRunnable
    implements Runnable
{

    CallbackRunnable(Callback callback1, Executor executor, ErrorHandler errorhandler)
    {
        callback = callback1;
        callbackExecutor = executor;
        errorHandler = errorhandler;
    }

    public abstract ResponseWrapper obtainResponse();

    public final void run()
    {
        try
        {
            final ResponseWrapper wrapper = obtainResponse();
            callbackExecutor.execute(new Runnable() {

                public void run()
                {
                    callback.success(wrapper.responseBody, wrapper.response);
                }

                final CallbackRunnable this$0;
                final ResponseWrapper val$wrapper;

            
            {
                this$0 = CallbackRunnable.this;
                wrapper = responsewrapper;
                super();
            }
            }
);
            return;
        }
        catch(RetrofitError retrofiterror)
        {
            Throwable throwable = errorHandler.handleError(retrofiterror);
            final RetrofitError handled;
            if(throwable == retrofiterror)
                handled = retrofiterror;
            else
                handled = RetrofitError.unexpectedError(retrofiterror.getUrl(), throwable);
            callbackExecutor.execute(new Runnable() {

                public void run()
                {
                    callback.failure(handled);
                }

                final CallbackRunnable this$0;
                final RetrofitError val$handled;

            
            {
                this$0 = CallbackRunnable.this;
                handled = retrofiterror;
                super();
            }
            }
);
            return;
        }
    }

    private final Callback callback;
    private final Executor callbackExecutor;
    private final ErrorHandler errorHandler;

}
