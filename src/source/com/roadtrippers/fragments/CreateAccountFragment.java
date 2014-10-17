// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.CreateUserResponse;
import com.roadtrippers.api.models.Errors;
import com.roadtrippers.api.requests.CreateUserRequest;
import com.roadtrippers.events.CancelButtonClick;
import com.roadtrippers.fragments.base.FacebookProgressFragment;
import com.roadtrippers.util.*;
import com.squareup.otto.Bus;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package com.roadtrippers.fragments:
//            LoginFragment, SettingsFragment

public class CreateAccountFragment extends FacebookProgressFragment
{

    public CreateAccountFragment()
    {
    }

    static String capitalize(String s)
    {
        if(s.length() > 1)
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        else
            return s.toUpperCase(Locale.ENGLISH);
    }

    public static void setError(String as[], EditText edittext)
    {
        if(edittext != null && as != null && as.length > 0)
        {
            edittext.setError(capitalize(as[0]));
            return;
        } else
        {
            Log.d("User error, can't display");
            return;
        }
    }

    void abortRegistration()
    {
        if(registration != null)
        {
            registration.unsubscribe();
            registration = null;
        }
    }

    void onCancel()
    {
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
                    onCreateAccountClick();
                    return true;
                } else
                {
                    return false;
                }
            }

            final CreateAccountFragment this$0;

            
            {
                this$0 = CreateAccountFragment.this;
                super();
            }
        }
);
        restoreProgressState(bundle);
        LoginFragment.setTermSpans(terms);
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
                    validationSucceded();
                }

                final CreateAccountFragment this$0;

            
            {
                this$0 = CreateAccountFragment.this;
                super();
            }
            }
);
        }
    }

    void onCreateAccountClick()
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

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f030032);
    }

    public void onDestroy()
    {
        super.onDestroy();
        abortRegistration();
    }

    protected void onRetryButtonClick()
    {
        super.onRetryButtonClick();
        validator.validate();
    }

    void restoreProgressState(Bundle bundle)
    {
        if(bundle != null)
        {
            if(bundle.getBoolean("shown", true))
            {
                setContentShownNoAnimation(true);
                return;
            } else
            {
                setContentShownNoAnimation(false);
                return;
            }
        } else
        {
            setContentShownNoAnimation(true);
            return;
        }
    }

    public void setContentShown(boolean flag)
    {
        super.setContentShown(flag);
        if(!flag)
        {
            username.setError(null);
            email.setError(null);
            password.setError(null);
        }
    }

    void validationSucceded()
    {
        setContentShown(false);
        registration = Observable.create(new rx.Observable.OnSubscribeFunc() {

            public Subscription onSubscribe(Observer observer)
            {
                try
                {
                    CreateUserRequest createuserrequest = new CreateUserRequest(SettingsFragment.trim(username), SettingsFragment.trim(email), SettingsFragment.trim(password), SettingsFragment.trim(password), 1);
                    observer.onNext(((Roadtrippers)roadtrippers.get()).createUser(createuserrequest));
                }
                catch(Exception exception)
                {
                    observer.onError(exception);
                }
                return Subscriptions.empty();
            }

            final CreateAccountFragment this$0;

            
            {
                this$0 = CreateAccountFragment.this;
                super();
            }
        }
).subscribeOn(Schedulers.io()).flatMap(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((Response)obj);
            }

            public Observable call(Response response)
            {
                return Observable.create(response. new rx.Observable.OnSubscribeFunc() {

                    public Subscription onSubscribe(Observer observer)
                    {
                        CreateUserResponse createuserresponse = (CreateUserResponse)((ObjectMapper)objectMapperLazy.get()).readValue(response.getBody().in(), com/roadtrippers/api/models/CreateUserResponse);
                        Pattern pattern = Pattern.compile("auth_token=([\\w-]+);");
                        Iterator iterator = response.getHeaders().iterator();
                        do
                        {
                            if(!iterator.hasNext())
                                break;
                            Header header = (Header)iterator.next();
                            if(!"Set-Cookie".equals(header.getName()) || !header.getValue().contains("auth_token"))
                                continue;
                            Matcher matcher = pattern.matcher(header.getValue());
                            if(!matcher.find())
                                continue;
                            createuserresponse.auth_token = matcher.group(1);
                            break;
                        } while(true);
                        observer.onNext(createuserresponse);
_L2:
                        return Subscriptions.empty();
                        Throwable throwable;
                        throwable;
                        observer.onError(throwable);
                        if(true) goto _L2; else goto _L1
_L1:
                    }

                    final _cls4 this$1;
                    final Response val$response;

            
            {
                this$1 = final__pcls4;
                response = Response.this;
                super();
            }
                }
);
            }

            final CreateAccountFragment this$0;

            
            {
                this$0 = CreateAccountFragment.this;
                super();
            }
        }
).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                if(getView() != null)
                {
                    Crouton.cancelAllCroutons();
                    Crouton.showText(getActivity(), 0x7f0c00b4, Style.ALERT);
                    setContentShown(true);
                }
            }

            public void onNext(CreateUserResponse createuserresponse)
            {
                Errors errors = createuserresponse.errors;
                if(errors == null)
                {
                    RTAnalytics.logEvent(getActivity(), 0x7f0c0069, 0x7f0c003f);
                    ((Bus)bus.get()).post(createuserresponse);
                    return;
                } else
                {
                    RTAnalytics.logEvent(getActivity(), 0x7f0c0069, 0x7f0c0040);
                    CreateAccountFragment.setError(errors.username, username);
                    CreateAccountFragment.setError(errors.email, email);
                    CreateAccountFragment.setError(errors.password, password);
                    setContentShown(true);
                    return;
                }
            }

            public volatile void onNext(Object obj)
            {
                onNext((CreateUserResponse)obj);
            }

            final CreateAccountFragment this$0;

            
            {
                this$0 = CreateAccountFragment.this;
                super();
            }
        }
);
    }

    private static final int TRACKING_CATEGORY = 0x7f0c0069;
    Lazy bus;
    EditText email;
    Lazy objectMapperLazy;
    EditText password;
    Subscription registration;
    Lazy roadtrippers;
    TextView terms;
    EditText username;
    Validator validator;
}
