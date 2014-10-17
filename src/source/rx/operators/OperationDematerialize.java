// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;

public final class OperationDematerialize
{
    private static class DematerializeObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return sequence.unsafeSubscribe(observer. new Subscriber() {

                public void onCompleted()
                {
                    observer.onCompleted();
                }

                public void onError(Throwable throwable)
                {
                    observer.onError(throwable);
                }

                public volatile void onNext(Object obj)
                {
                    onNext((Notification)obj);
                }

                public void onNext(Notification notification)
                {
                    static class _cls1
                    {

                        static final int $SwitchMap$rx$Notification$Kind[];

                        static 
                        {
                            $SwitchMap$rx$Notification$Kind = new int[rx.Notification.Kind.values().length];
                            try
                            {
                                $SwitchMap$rx$Notification$Kind[rx.Notification.Kind.OnNext.ordinal()] = 1;
                            }
                            catch(NoSuchFieldError nosuchfielderror) { }
                            try
                            {
                                $SwitchMap$rx$Notification$Kind[rx.Notification.Kind.OnError.ordinal()] = 2;
                            }
                            catch(NoSuchFieldError nosuchfielderror1) { }
                            try
                            {
                                $SwitchMap$rx$Notification$Kind[rx.Notification.Kind.OnCompleted.ordinal()] = 3;
                            }
                            catch(NoSuchFieldError nosuchfielderror2)
                            {
                                return;
                            }
                        }
                    }

                    switch(_cls1..SwitchMap.rx.Notification.Kind[notification.getKind().ordinal()])
                    {
                    default:
                        return;

                    case 1: // '\001'
                        observer.onNext(notification.getValue());
                        return;

                    case 2: // '\002'
                        observer.onError(notification.getThrowable());
                        return;

                    case 3: // '\003'
                        observer.onCompleted();
                        break;
                    }
                }

                final DematerializeObservable this$0;
                final Observer val$observer;

            
            {
                this$0 = final_dematerializeobservable;
                observer = Observer.this;
                super();
            }
            }
);
        }

        private final Observable sequence;

        public DematerializeObservable(Observable observable)
        {
            sequence = observable;
        }
    }


    public OperationDematerialize()
    {
    }

    public static rx.Observable.OnSubscribeFunc dematerialize(Observable observable)
    {
        return new DematerializeObservable(observable);
    }
}
