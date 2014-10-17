// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public class OperationSingle
{

    public OperationSingle()
    {
    }

    public static rx.Observable.OnSubscribeFunc single(Observable observable)
    {
        return single(observable, false, null);
    }

    private static rx.Observable.OnSubscribeFunc single(Observable observable, boolean flag, Object obj)
    {
        return new rx.Observable.OnSubscribeFunc(observable, flag, obj) {

            public Subscription onSubscribe(final Observer observer)
            {
                SafeObservableSubscription safeobservablesubscription = new SafeObservableSubscription();
                safeobservablesubscription.wrap(source.unsafeSubscribe(safeobservablesubscription. new Subscriber() {

                    public void onCompleted()
                    {
                        if(hasTooManyElemenets)
                            return;
                        if(isEmpty)
                        {
                            if(hasDefaultValue)
                            {
                                observer.onNext(defaultValue);
                                observer.onCompleted();
                                return;
                            } else
                            {
                                observer.onError(new IllegalArgumentException("Sequence contains no elements"));
                                return;
                            }
                        } else
                        {
                            observer.onNext(value);
                            observer.onCompleted();
                            return;
                        }
                    }

                    public void onError(Throwable throwable)
                    {
                        observer.onError(throwable);
                    }

                    public void onNext(Object obj)
                    {
                        if(isEmpty)
                        {
                            value = obj;
                            isEmpty = false;
                            return;
                        } else
                        {
                            hasTooManyElemenets = true;
                            observer.onError(new IllegalArgumentException("Sequence contains too many elements"));
                            subscription.unsubscribe();
                            return;
                        }
                    }

                    private boolean hasTooManyElemenets;
                    private boolean isEmpty;
                    final _cls1 this$0;
                    final Observer val$observer;
                    final SafeObservableSubscription val$subscription;
                    private Object value;

            
            {
                this$0 = final__pcls1;
                observer = observer1;
                subscription = SafeObservableSubscription.this;
                Subscriber();
                isEmpty = true;
            }
                }
));
                return safeobservablesubscription;
            }

            final Object val$defaultValue;
            final boolean val$hasDefaultValue;
            final Observable val$source;

            
            {
                source = observable;
                hasDefaultValue = flag;
                defaultValue = obj;
                Object();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc singleOrDefault(Observable observable, Object obj)
    {
        return single(observable, true, obj);
    }
}
