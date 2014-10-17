// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.util.concurrent.*;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.Subscriptions;

// Referenced classes of package retrofit:
//            ErrorHandler, RetrofitError, ResponseWrapper

final class RxSupport
{

    RxSupport(Executor executor1, ErrorHandler errorhandler)
    {
        executor = executor1;
        errorHandler = errorhandler;
    }

    private Runnable getRunnable(final Subscriber subscriber, final Callable request)
    {
        return new Runnable() {

            public void run()
            {
                if(subscriber.isUnsubscribed())
                    return;
                try
                {
                    ResponseWrapper responsewrapper = (ResponseWrapper)request.call();
                    subscriber.onNext(responsewrapper.responseBody);
                    subscriber.onCompleted();
                    return;
                }
                catch(RetrofitError retrofiterror)
                {
                    subscriber.onError(errorHandler.handleError(retrofiterror));
                }
                catch(Exception exception)
                {
                    throw new RuntimeException(exception);
                }
                return;
            }

            final RxSupport this$0;
            final Callable val$request;
            final Subscriber val$subscriber;

            
            {
                this$0 = RxSupport.this;
                subscriber = subscriber1;
                request = callable;
                super();
            }
        }
;
    }

    Observable createRequestObservable(final Callable request)
    {
        return Observable.create(new rx.Observable.OnSubscribe() {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                if(subscriber.isUnsubscribed())
                {
                    return;
                } else
                {
                    FutureTask futuretask = new FutureTask(getRunnable(subscriber, request), null);
                    subscriber.add(Subscriptions.from(futuretask));
                    executor.execute(futuretask);
                    return;
                }
            }

            final RxSupport this$0;
            final Callable val$request;

            
            {
                this$0 = RxSupport.this;
                request = callable;
                super();
            }
        }
);
    }

    private final ErrorHandler errorHandler;
    private final Executor executor;



}
