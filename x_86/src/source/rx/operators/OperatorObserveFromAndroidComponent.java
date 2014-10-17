// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class OperatorObserveFromAndroidComponent
{
    private static class OnSubscribeBase
        implements rx.Observable.OnSubscribe
    {

        private void log(String s)
        {
            if(Log.isLoggable("AndroidObserver", 3))
            {
                String s1 = Thread.currentThread().getName();
                Log.d("AndroidObserver", (new StringBuilder()).append("[").append(s1).append("] componentRef = ").append(componentRef).append("; observerRef = ").append(observerRef).toString());
                Log.d("AndroidObserver", (new StringBuilder()).append("[").append(s1).append("]").append(s).toString());
            }
        }

        private void releaseReferences()
        {
            observerRef = null;
            componentRef = null;
        }

        public volatile void call(Object obj)
        {
            call((Subscriber)obj);
        }

        public void call(Subscriber subscriber)
        {
            observerRef = subscriber;
            source.observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber(subscriber) {

                public void onCompleted()
                {
                    if(componentRef != null && isComponentValid(componentRef))
                    {
                        observerRef.onCompleted();
                        return;
                    } else
                    {
                        unsubscribe();
                        log("onComplete: target component released or detached; dropping message");
                        return;
                    }
                }

                public void onError(Throwable throwable)
                {
                    if(componentRef != null && isComponentValid(componentRef))
                    {
                        observerRef.onError(throwable);
                        return;
                    } else
                    {
                        unsubscribe();
                        log("onError: target component released or detached; dropping message");
                        return;
                    }
                }

                public void onNext(Object obj)
                {
                    if(componentRef != null && isComponentValid(componentRef))
                    {
                        observerRef.onNext(obj);
                        return;
                    } else
                    {
                        unsubscribe();
                        log("onNext: target component released or detached; dropping message");
                        return;
                    }
                }

                final OnSubscribeBase this$0;

            
            {
                this$0 = OnSubscribeBase.this;
                super(subscriber);
            }
            }
);
            subscriber.add(Subscriptions.create(new Action0() {

                public void call()
                {
                    log("unsubscribing from source sequence");
                    releaseReferences();
                }

                final OnSubscribeBase this$0;

            
            {
                this$0 = OnSubscribeBase.this;
                super();
            }
            }
));
        }

        protected boolean isComponentValid(Object obj)
        {
            return true;
        }

        private static final String LOG_TAG = "AndroidObserver";
        private volatile Object componentRef;
        private volatile Observer observerRef;
        private final Observable source;





        private OnSubscribeBase(Observable observable, Object obj)
        {
            source = observable;
            componentRef = obj;
        }

    }

    private static final class OnSubscribeFragment extends OnSubscribeBase
    {

        protected boolean isComponentValid(Fragment fragment)
        {
            return fragment.isAdded();
        }

        protected volatile boolean isComponentValid(Object obj)
        {
            return isComponentValid((Fragment)obj);
        }

        private OnSubscribeFragment(Observable observable, Fragment fragment)
        {
            super(observable, fragment);
        }

    }

    private static final class OnSubscribeSupportFragment extends OnSubscribeBase
    {

        protected boolean isComponentValid(android.support.v4.app.Fragment fragment)
        {
            return fragment.isAdded();
        }

        protected volatile boolean isComponentValid(Object obj)
        {
            return isComponentValid((android.support.v4.app.Fragment)obj);
        }

        private OnSubscribeSupportFragment(Observable observable, android.support.v4.app.Fragment fragment)
        {
            super(observable, fragment);
        }

    }


    public OperatorObserveFromAndroidComponent()
    {
    }

    public static Observable observeFromAndroidComponent(Observable observable, Activity activity)
    {
        return Observable.create(new OnSubscribeBase(observable, activity));
    }

    public static Observable observeFromAndroidComponent(Observable observable, Fragment fragment)
    {
        return Observable.create(new OnSubscribeFragment(observable, fragment));
    }

    public static Observable observeFromAndroidComponent(Observable observable, android.support.v4.app.Fragment fragment)
    {
        return Observable.create(new OnSubscribeSupportFragment(observable, fragment));
    }
}
