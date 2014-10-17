// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.Action0;

public final class OperationFinally
{
    private static class Finally
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return sequence.unsafeSubscribe(new FinallyObserver(observer));
        }

        private final Action0 finalAction;
        private final Observable sequence;


        Finally(Observable observable, Action0 action0)
        {
            sequence = observable;
            finalAction = action0;
        }
    }

    private class Finally.FinallyObserver extends Subscriber
    {

        public void onCompleted()
        {
            observer.onCompleted();
            finalAction.call();
            return;
            Exception exception;
            exception;
            finalAction.call();
            throw exception;
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
            finalAction.call();
            return;
            Exception exception;
            exception;
            finalAction.call();
            throw exception;
        }

        public void onNext(Object obj)
        {
            observer.onNext(obj);
        }

        private final Observer observer;
        final Finally this$0;

        Finally.FinallyObserver(Observer observer1)
        {
            this$0 = Finally.this;
            super();
            observer = observer1;
        }
    }


    public OperationFinally()
    {
    }

    public static rx.Observable.OnSubscribeFunc finallyDo(Observable observable, Action0 action0)
    {
        return new rx.Observable.OnSubscribeFunc(observable, action0) {

            public Subscription onSubscribe(Observer observer)
            {
                return (new Finally(sequence, action)).onSubscribe(observer);
            }

            final Action0 val$action;
            final Observable val$sequence;

            
            {
                sequence = observable;
                action = action0;
                super();
            }
        }
;
    }
}
