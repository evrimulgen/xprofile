// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;
import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.roadtrippers.events.CreateAccountButtonClicked;
import com.roadtrippers.events.LoginButtonClicked;
import com.roadtrippers.fragments.base.FacebookProgressFragment;
import com.roadtrippers.util.CustomTypefaceSpan;
import com.roadtrippers.util.RTAnalytics;
import com.squareup.otto.Bus;
import dagger.Lazy;

public class LoginFragment extends FacebookProgressFragment
{

    public LoginFragment()
    {
    }

    public static void setTermSpans(TextView textview)
    {
        SpannableString spannablestring = new SpannableString("Signing in means you agree with our Terms.");
        spannablestring.setSpan(new CustomTypefaceSpan(Typeface.createFromAsset(textview.getResources().getAssets(), "fonts/HVD Fonts - BrandonText-Bold.otf")), 35, spannablestring.length(), 33);
        textview.setText(spannablestring);
        textview.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                RTAnalytics.logEvent(view.getContext(), 0x7f0c0069, 0x7f0c0042);
                view.getContext().startActivity(IntentUtils.openLink("https://roadtrippers.com/tos"));
            }

        }
);
    }

    protected void onContentCreated(Bundle bundle)
    {
        super.onContentCreated(bundle);
        setTermSpans(terms);
        getView().setBackgroundColor(0);
    }

    public void onCreateAccount()
    {
        ((Bus)bus.get()).post(CreateAccountButtonClicked.INSTANCE);
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f03004a);
        setContentShownNoAnimation(true);
    }

    public void onLoginClick()
    {
        ((Bus)bus.get()).post(LoginButtonClicked.INSTANCE);
    }

    private static final int TRACKING_CATEGORY = 0x7f0c0069;
    Lazy bus;
    TextView terms;
}
