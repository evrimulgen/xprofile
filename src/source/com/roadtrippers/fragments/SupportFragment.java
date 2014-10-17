// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.app.ActivityManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.*;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.OkHttpInstrumentation;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.roadtrippers.adapters.LeftDrawableAdapter;
import com.roadtrippers.api.models.User;
import com.roadtrippers.events.CropImageFailError;
import com.roadtrippers.events.TicketSubmittedEvent;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.fragments.base.BaseProgressFragment;
import com.roadtrippers.util.Persistence;
import com.roadtrippers.util.RTAnalytics;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import java.io.*;
import java.net.*;
import org.json.JSONObject;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package com.roadtrippers.fragments:
//            SettingsFragment

public class SupportFragment extends BaseFragment
{

    public SupportFragment()
    {
    }

    private void remove(final Fragment spinner)
    {
        ViewPropertyAnimator.animate(spinner.getView()).translationY(-spinner.getView().getHeight()).setListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                getChildFragmentManager().beginTransaction().remove(spinner).commit();
            }

            final SupportFragment this$0;
            final Fragment val$spinner;

            
            {
                this$0 = SupportFragment.this;
                spinner = fragment;
                super();
            }
        }
);
        ViewPropertyAnimator.animate(upArrow).rotation(0.0F).start();
    }

    private void showSpinner()
    {
        ListFragment listfragment = new ListFragment() {

            public volatile ListAdapter getListAdapter()
            {
                return getListAdapter();
            }

            public LeftDrawableAdapter getListAdapter()
            {
                return (LeftDrawableAdapter)super.getListAdapter();
            }

            public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
            {
                View view = super.onCreateView(layoutinflater, viewgroup, bundle);
                view.setBackgroundResource(0x7f080034);
                return view;
            }

            public void onListItemClick(ListView listview, View view, int i, long l)
            {
                spinnerFrame.setBackgroundResource(0x7f020194);
                spinnerImage.setVisibility(4);
                selected.setText(getListAdapter().getItem(i));
                Drawable drawable = getListAdapter().getDrawable(i).mutate();
                drawable.setColorFilter(getResources().getColor(0x7f080034), android.graphics.PorterDuff.Mode.SRC_ATOP);
                selected.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                remove(this);
            }

            public void onViewCreated(View view, Bundle bundle)
            {
                super.onViewCreated(view, bundle);
                setListAdapter(new LeftDrawableAdapter(getActivity(), getResources().getStringArray(0x7f060004), getResources().obtainTypedArray(0x7f060003)));
            }

            final SupportFragment this$0;

            
            {
                this$0 = SupportFragment.this;
                super();
            }
        }
;
        getChildFragmentManager().beginTransaction().setCustomAnimations(0x7f04000d, 0x7f040012).replace(0x7f090139, listfragment).commit();
        ViewPropertyAnimator.animate(upArrow).rotation(180F).start();
    }

    void back()
    {
        getActivity().finish();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f03005f, viewgroup, false);
    }

    public void onCropFailure(CropImageFailError cropimagefailerror)
    {
        Crouton.cancelAllCroutons();
        Crouton.showText(getActivity(), 0x7f0c00ae, Style.ALERT, supportContainer);
    }

    public void onTicketSubmitted(TicketSubmittedEvent ticketsubmittedevent)
    {
        Crouton.cancelAllCroutons();
        if(ticketsubmittedevent.isOk())
        {
            Crouton.showText(getActivity(), 0x7f0c00ef, Style.CONFIRM, supportContainer);
            supportText.setText(null);
            return;
        } else
        {
            Crouton.showText(getActivity(), 0x7f0c00ee, Style.ALERT, supportContainer);
            return;
        }
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        inputMethod = (InputMethodManager)getActivity().getSystemService("input_method");
        BaseProgressFragment.setColorFilter(back, 0xffcccccc);
        StateListDrawable statelistdrawable = new StateListDrawable();
        statelistdrawable.addState(new int[] {
            0x10100a7
        }, new ColorDrawable(getResources().getColor(0x7f080037)));
        statelistdrawable.addState(new int[0], supportText.getBackground());
        supportText.setBackgroundDrawable(statelistdrawable);
        view.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                Fragment fragment = getChildFragmentManager().findFragmentById(0x7f090139);
                if(fragment != null)
                    remove(fragment);
                inputMethod.hideSoftInputFromWindow(supportText.getWindowToken(), 0);
            }

            final SupportFragment this$0;

            
            {
                this$0 = SupportFragment.this;
                super();
            }
        }
);
    }

    void spinnerClicked()
    {
        Fragment fragment = getChildFragmentManager().findFragmentById(0x7f090139);
        if(fragment == null)
        {
            showSpinner();
            return;
        } else
        {
            remove(fragment);
            return;
        }
    }

    void submit()
    {
        Crouton.cancelAllCroutons();
        if(ActivityManager.isUserAMonkey())
            return;
        if(selected.getCompoundDrawables()[0] == null)
        {
            Crouton.showText(getActivity(), 0x7f0c00cd, Style.INFO, supportContainer);
            showSpinner();
            return;
        }
        if(supportText.length() == 0)
        {
            Crouton.showText(getActivity(), 0x7f0c00cc, Style.INFO, supportContainer);
            return;
        } else
        {
            RTAnalytics.logEvent(getActivity(), 0x7f0c0070, 0x7f0c0041, SettingsFragment.trim(selected));
            Crouton.showText(getActivity(), 0x7f0c00e3, Style.INFO, supportContainer);
            ((Persistence)persistenceLazy.get()).getUser().flatMap(new Func1() {

                public volatile Object call(Object obj)
                {
                    return call((User)obj);
                }

                public Observable call(User user)
                {
                    return Observable.create(user. new rx.Observable.OnSubscribeFunc() {

                        public Subscription onSubscribe(Observer observer)
                        {
                            HttpURLConnection httpurlconnection;
                            BufferedReader bufferedreader;
                            StringBuilder stringbuilder;
                            Object aobj[] = new Object[3];
                            aobj[0] = android.os.Build.VERSION.RELEASE;
                            aobj[1] = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
                            aobj[2] = SettingsFragment.trim(selected);
                            String s = String.format("Android %s Support v%s - %s", aobj);
                            Object aobj1[] = new Object[3];
                            aobj1[0] = URLEncoder.encode(SettingsFragment.trim(supportText), "UTF-8");
                            aobj1[1] = URLEncoder.encode(user.email, "UTF-8");
                            aobj1[2] = URLEncoder.encode(s, "UTF-8");
                            String s1 = String.format("description=%s&email=%s&subject=%s&set_tags=dropbox", aobj1);
                            httpurlconnection = OkHttpInstrumentation.open(((OkHttpClient)httpClientLazy.get()).open(new URL("https://roadtrippers.zendesk.com/requests/mobile_api/create.json")));
                            httpurlconnection.setRequestMethod("POST");
                            httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            httpurlconnection.setRequestProperty("Content-Length", String.valueOf(s1.getBytes().length));
                            httpurlconnection.addRequestProperty("X-Zendesk-Mobile-API", "1.0");
                            httpurlconnection.setUseCaches(false);
                            httpurlconnection.setDoInput(true);
                            httpurlconnection.setDoOutput(true);
                            DataOutputStream dataoutputstream = new DataOutputStream(httpurlconnection.getOutputStream());
                            dataoutputstream.writeBytes(s1);
                            dataoutputstream.flush();
                            dataoutputstream.close();
                            bufferedreader = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()), 8192);
                            stringbuilder = new StringBuilder();
_L3:
                            String s2 = bufferedreader.readLine();
                            if(s2 == null) goto _L2; else goto _L1
_L1:
                            Throwable throwable;
                            stringbuilder.append(s2);
                              goto _L3
_L5:
                            return Subscriptions.empty();
_L2:
                            try
                            {
                                JVM INSTR new #229 <Class JSONObject>;
                                observer.onNext(JSONObjectInstrumentation.init(stringbuilder.toString()));
                                bufferedreader.close();
                                httpurlconnection.disconnect();
                            }
                            // Misplaced declaration of an exception variable
                            catch(Throwable throwable)
                            {
                                observer.onError(throwable);
                            }
                            if(true) goto _L5; else goto _L4
_L4:
                        }

                        final _cls5 this$1;
                        final User val$user;

            
            {
                this$1 = final__pcls5;
                user = User.this;
                super();
            }
                    }
);
                }

                final SupportFragment this$0;

            
            {
                this$0 = SupportFragment.this;
                super();
            }
            }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

                public void onCompleted()
                {
                }

                public void onError(Throwable throwable)
                {
                    ((Bus)busLazy.get()).post(new TicketSubmittedEvent(throwable));
                }

                public volatile void onNext(Object obj)
                {
                    onNext((JSONObject)obj);
                }

                public void onNext(JSONObject jsonobject)
                {
                    ((Bus)busLazy.get()).post(new TicketSubmittedEvent(jsonobject));
                }

                final SupportFragment this$0;

            
            {
                this$0 = SupportFragment.this;
                super();
            }
            }
);
            return;
        }
    }

    static final String UTF_8 = "UTF-8";
    ImageView back;
    Lazy busLazy;
    Lazy httpClientLazy;
    InputMethodManager inputMethod;
    Lazy persistenceLazy;
    TextView selected;
    View spinnerFrame;
    View spinnerImage;
    FrameLayout supportContainer;
    EditText supportText;
    ImageView upArrow;

}
