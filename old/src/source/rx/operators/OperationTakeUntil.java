// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.Func1;

public class OperationTakeUntil
{
    private static class Notification
    {

        public static Notification halt()
        {
            return new Notification(true, null);
        }

        public static Notification value(Object obj)
        {
            return new Notification(false, obj);
        }

        private final boolean halt;
        private final Object value;



        private Notification(boolean flag, Object obj)
        {
            halt = flag;
            value = obj;
        }
    }

    private static class OtherObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return sequence.unsafeSubscribe(observer. new Subscriber() {

                public void onCompleted()
                {
                    notificationObserver.onNext(Notification.halt());
                }

                public void onError(Throwable throwable)
                {
                    notificationObserver.onError(throwable);
                }

                public void onNext(Object obj)
                {
                    notificationObserver.onNext(Notification.halt());
                }

                final OtherObservable this$0;
                final Observer val$notificationObserver;

            
            {
                this$0 = final_otherobservable;
                notificationObserver = Observer.this;
                Subscriber();
            }
            }
);
        }

        private final Observable sequence;

        private OtherObservable(Observable observable)
        {
            sequence = observable;
        }

    }

    private static class SourceObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return sequence.unsafeSubscribe(observer. new Subscriber() {

                public void onCompleted()
                {
                    notificationObserver.onNext(Notification.halt());
                }

                public void onError(Throwable throwable)
                {
                    notificationObserver.onError(throwable);
                }

                public void onNext(Object obj)
                {
                    notificationObserver.onNext(Notification.value(obj));
                }

                final SourceObservable this$0;
                final Observer val$notificationObserver;

            
            {
                this$0 = final_sourceobservable;
                notificationObserver = Observer.this;
                Subscriber();
            }
            }
);
        }

        private final Observable sequence;

        private SourceObservable(Observable observable)
        {
            sequence = observable;
        }

    }


    public OperationTakeUntil()
    {
    }

    public static Observable takeUntil(Observable observable, Observable observable1)
    {
        return Observable.merge(Observable.create(new SourceObservable(observable)), Observable.create(new OtherObservable(observable1))).takeWhile(new Func1() {

            public Boolean call(Notification notification)
            {
                boolean flag;
                if(!notification.halt)
                    flag = true;
                else
                    flag = false;
                return Boolean.valueOf(flag);
            }

            public volatile Object call(Object obj)
            {
                return call((Notification)obj);
            }

        }
).map(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Notification)obj);
            }

            public Object call(Notification notification)
            {
                return notification.value;
            }

        }
);
    }
}
