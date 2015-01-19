// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.LoginResponse;
import com.roadtrippers.api.requests.LoginRequest;
import com.roadtrippers.events.CancelButtonClick;
import com.roadtrippers.fragments.base.FacebookProgressFragment;
import com.roadtrippers.util.*;
import com.squareup.otto.Bus;
import dagger.Lazy;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package com.roadtrippers.fragments:
//            LoginFragment, SettingsFragment

public class LoginFieldsFragment extends FacebookProgressFragment
{

    public LoginFieldsFragment()
    {
    }

    private void logEvent(int i)
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0069, i);
    }

    void login()
    {
        if(getView() != null)
            setContentShown(false);
        subscription = Observable.create(new rx.Observable.OnSubscribeFunc() {

            public Subscription onSubscribe(Observer observer)
            {
                try
                {
                    observer.onNext(((Roadtrippers)roadtrippers.get()).login(new LoginRequest(SettingsFragment.trim(username), SettingsFragment.trim(password))));
                }
                catch(Throwable throwable)
                {
                    observer.onError(throwable);
                }
                return Subscriptions.empty();
            }

            final LoginFieldsFragment this$0;

            
            {
                this$0 = LoginFieldsFragment.this;
                super();
            }
        }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                if(getView() != null)
                    setContentShown(true);
            }

            public void onNext(LoginResponse loginresponse)
            {
                if(loginresponse.errors != null)
                {
                    logEvent(0x7f0c001f);
                    setContentShown(true);
                    ((Bus)bus.get()).post(loginresponse.errors);
                    return;
                } else
                {
                    logEvent(0x7f0c001d);
                    ((Bus)bus.get()).post(loginresponse);
                    return;
                }
            }

            public volatile void onNext(Object obj)
            {
                onNext((LoginResponse)obj);
            }

            final LoginFieldsFragment this$0;

            
            {
                this$0 = LoginFieldsFragment.this;
                super();
            }
        }
);
    }

    public void onCancelClick()
    {
        logEvent(0x7f0c001c);
        ((Bus)bus.get()).post(CancelButtonClick.INSTANCE);
    }

    protected void onContentCreated(Bundle bundle)
    {
        super.onContentCreated(bundle);
        password.setOnEditorActionListener(new android.widget.TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
            {
                if(i == 2)
                {
                    onLoginButtonClicked();
                    return true;
                } else
                {
                    return false;
                }
            }

            final LoginFieldsFragment this$0;

            
            {
                this$0 = LoginFieldsFragment.this;
                super();
            }
        }
);
        LoginFragment.setTermSpans(terms);
        setContentShownNoAnimation(true);
        getView().setBackgroundColor(0);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(validator == null)
        {
            validator = new Validator(this);
            validator.setValidationListener(new com.mobsandgeeks.saripaar.Validator.ValidationListener() {

                public void onValidationFailed(View view, Rule rule)
                {
                    if(view instanceof EditText)
                        ((EditText)view).setError(rule.getFailureMessage());
                }

                public void onValidationSucceeded()
                {
                    login();
                }

                final LoginFieldsFragment this$0;

            
            {
                this$0 = LoginFieldsFragment.this;
                super();
            }
            }
);
        }
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f030049);
    }

    public void onDestroy()
    {
        super.onDestroy();
        if(subscription != null)
        {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    public void onErrors(String as[])
    {
        username.setError(as[0]);
    }

    void onForgotPasswordClick()
    {
        logEvent(0x7f0c0017);
        startActivity(IntentUtils.openLink("https://roadtrippers.com/password_resets/new"));
    }

    public void onLoginButtonClicked()
    {
        if(NetworkState.isOnline(getActivity()))
        {
            validator.validate();
            return;
        } else
        {
            Croutons.showNetworkUnavailableError((ViewGroup)getView(), getActivity());
            return;
        }
    }

    protected void onRetryButtonClick()
    {
    }

    public static final int TRACKING_CATEGORY = 0x7f0c0069;
    Lazy bus;
    EditText password;
    Lazy roadtrippers;
    Subscription subscription;
    TextView terms;
    EditText username;
    Validator validator;

}
