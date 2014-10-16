// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments.base;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.*;
import com.facebook.widget.LoginButton;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.CreateUserResponse;
import com.roadtrippers.api.requests.FacebookLoginRequest;
import com.roadtrippers.util.Log;
import com.roadtrippers.util.RTAnalytics;
import com.squareup.otto.Bus;
import dagger.Lazy;
import java.util.Arrays;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package com.roadtrippers.fragments.base:
//            RetainedProgressFragment

public abstract class FacebookProgressFragment extends RetainedProgressFragment
{

    public FacebookProgressFragment()
    {
    }

    void login(final Session session)
    {
        if(getView() != null)
        {
            setContentShown(false);
            subscription = Observable.create(new rx.Observable.OnSubscribeFunc() {

                public Subscription onSubscribe(Observer observer)
                {
                    CreateUserResponse createuserresponse = ((Roadtrippers)roadtrippers.get()).facebookLogin(new FacebookLoginRequest(session.getAccessToken()));
                    if(createuserresponse.errors != null) goto _L2; else goto _L1
_L1:
                    observer.onNext(createuserresponse);
_L4:
                    return Subscriptions.empty();
_L2:
                    try
                    {
                        observer.onError(createuserresponse.errors);
                    }
                    catch(Throwable throwable)
                    {
                        observer.onError(throwable);
                    }
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final FacebookProgressFragment this$0;
                final Session val$session;

            
            {
                this$0 = FacebookProgressFragment.this;
                session = session1;
                super();
            }
            }
).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

                public void onCompleted()
                {
                }

                public void onError(Throwable throwable)
                {
                    if(getView() != null)
                    {
                        setContentEmpty(true);
                        setContentShown(true);
                    }
                }

                public void onNext(CreateUserResponse createuserresponse)
                {
                    RTAnalytics.logEvent(getActivity(), 0x7f0c0069, 0x7f0c001e);
                    ((Bus)busLazy.get()).post(createuserresponse);
                }

                public volatile void onNext(Object obj)
                {
                    onNext((CreateUserResponse)obj);
                }

                final FacebookProgressFragment this$0;

            
            {
                this$0 = FacebookProgressFragment.this;
                super();
            }
            }
);
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if(uiHelper != null)
        {
            Log.d(intent.toString());
            uiHelper.onActivityResult(i, j, intent);
        }
    }

    protected void onContentCreated(Bundle bundle)
    {
        facebook.setFragment(this);
        facebook.setReadPermissions(Arrays.asList(new String[] {
            "email"
        }));
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        uiHelper = new UiLifecycleHelper(getActivity(), new com.facebook.Session.StatusCallback() {

            public void call(Session session, SessionState sessionstate, Exception exception)
            {
                Log.d(session.getState().toString());
                Log.e(exception);
                if(session.isOpened())
                    login(session);
            }

            final FacebookProgressFragment this$0;

            
            {
                this$0 = FacebookProgressFragment.this;
                super();
            }
        }
);
        uiHelper.onCreate(bundle);
    }

    public void onDestroy()
    {
        uiHelper.onDestroy();
        if(subscription != null)
        {
            subscription.unsubscribe();
            subscription = null;
        }
        super.onDestroy();
    }

    public void onPause()
    {
        super.onPause();
        uiHelper.onPause();
    }

    public void onResume()
    {
        super.onResume();
        uiHelper.onResume();
    }

    protected void onRetryButtonClick()
    {
        login(Session.getActiveSession());
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        uiHelper.onSaveInstanceState(bundle);
    }

    private static final int TRACKING_CATEGORY = 0x7f0c0069;
    LoginButton facebook;
    Subscription subscription;
    UiLifecycleHelper uiHelper;
}
