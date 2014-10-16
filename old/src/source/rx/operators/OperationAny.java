// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.functions.Func1;
import rx.functions.Functions;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public final class OperationAny
{
    private static class Any
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(final Observer observer)
        {
            SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
            return safeobservablesubscription.wrap(source.unsafeSubscribe(safeobservablesubscription. new Subscriber() {

                public void onCompleted()
                {
                    if(!hasEmitted.get())
                    {
                        observer.onNext(Boolean.valueOf(returnOnEmpty));
                        observer.onCompleted();
                    }
                }

                public void onError(Throwable throwable)
                {
                    observer.onError(throwable);
                }

                public void onNext(Object obj)
                {
                    Observer observer1;
                    if(hasEmitted.get() || !((Boolean)predicate.call(obj)).booleanValue() || hasEmitted.getAndSet(true))
                        break MISSING_BLOCK_LABEL_89;
                    observer1 = observer;
                    boolean flag;
                    if(!returnOnEmpty)
                        flag = true;
                    else
                        flag = false;
                    observer1.onNext(Boolean.valueOf(flag));
                    observer.onCompleted();
                    subscription.unsubscribe();
                    return;
                    Throwable throwable;
                    throwable;
                    observer.onError(throwable);
                    subscription.unsubscribe();
                    return;
                }

                private final AtomicBoolean hasEmitted = new AtomicBoolean(false);
                final Any this$0;
                final Observer val$observer;
                final SafeObservableSubscription val$subscription;

            
            {
                this$0 = final_any1;
                observer = observer1;
                subscription = SafeObservableSubscription.this;
                super();
            }
            }
));
        }

        private final Func1 predicate;
        private final boolean returnOnEmpty;
        private final Observable source;



        private Any(Observable observable, Func1 func1, boolean flag)
        {
            source = observable;
            predicate = func1;
            returnOnEmpty = flag;
        }

    }


    public OperationAny()
    {
    }

    public static rx.Observable.OnSubscribeFunc any(Observable observable)
    {
        return new Any(observable, Functions.alwaysTrue(), false);
    }

    public static rx.Observable.OnSubscribeFunc any(Observable observable, Func1 func1)
    {
        return new Any(observable, func1, false);
    }

    public static rx.Observable.OnSubscribeFunc exists(Observable observable, Func1 func1)
    {
        return any(observable, func1);
    }

    public static rx.Observable.OnSubscribeFunc isEmpty(Observable observable)
    {
        return new Any(observable, Functions.alwaysTrue(), true);
    }
}
