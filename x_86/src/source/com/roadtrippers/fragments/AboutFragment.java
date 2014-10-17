// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.*;
import android.widget.*;
import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.nineoldandroids.view.ViewHelper;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.fragments.base.BaseProgressFragment;
import com.roadtrippers.util.RTAnalytics;
import com.roadtrippers.widget.NotifyingScrollView;

public class AboutFragment extends BaseFragment
{

    public AboutFragment()
    {
    }

    void onBack()
    {
        getActivity().finish();
    }

    void onBlog()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0065, 0x7f0c0010, "blog");
        startActivity(IntentUtils.openLink("http://roadtrippers.com/blogs"));
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030018, viewgroup, false);
    }

    void onEmail()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0065, 0x7f0c0010, "email");
        startActivity(IntentUtils.sendEmail("support@roadtrippers.com", getString(0x7f0c0056), null));
    }

    void onFacebook()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0065, 0x7f0c0010, "facebook");
        startActivity(IntentUtils.openLink("https://facebook.com/roadtrippers"));
    }

    void onGoogle()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0065, 0x7f0c0010, "google+");
        startActivity(IntentUtils.openLink("https://plus.google.com/u/0/101053635723793509573/"));
    }

    void onPinterest()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0065, 0x7f0c0010, "pinterest");
        startActivity(IntentUtils.openLink("http://pinterest.com/roadtrippers/"));
    }

    void onTwitter()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0065, 0x7f0c0010, "twitter");
        startActivity(IntentUtils.openLink("https://twitter.com/roadtrippers"));
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        SpannableString spannablestring;
        String s;
        super.onViewCreated(view, bundle);
        spannablestring = new SpannableString(getString(0x7f0c000d));
        spannablestring.setSpan(new ImageSpan(getActivity(), 0x7f020169), 0, 1, 17);
        s = "";
        String s1;
        s = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        if(RoadTrippersApp.from(getActivity()).isStage())
            s = (new StringBuilder()).append(s).append(" STAGE").toString();
        s1 = (new StringBuilder()).append("\n\nVersion: ").append(s).toString();
        s = s1;
_L2:
        aboutText.setText((new StringBuilder()).append(spannablestring).append(s).toString());
        aboutScrollView.setOnScrollChangedListener(new com.roadtrippers.widget.NotifyingScrollView.OnScrollChangedListener() {

            public void onScrollChanged(ScrollView scrollview, int i, int j, int k, int l)
            {
                ViewHelper.setTranslationY(aboutHeaderImage, (float)(-j) / 2.0F);
            }

            final AboutFragment this$0;

            
            {
                this$0 = AboutFragment.this;
                super();
            }
        }
);
        BaseProgressFragment.setColorFilter(back, 0xffcccccc);
        BaseProgressFragment.setColorFilter(blog, 0xffcccccc);
        BaseProgressFragment.setColorFilter(email, 0xffcccccc);
        BaseProgressFragment.setColorFilter(facebook, 0xffcccccc);
        BaseProgressFragment.setColorFilter(twitter, 0xffcccccc);
        BaseProgressFragment.setColorFilter(pinterest, 0xffcccccc);
        BaseProgressFragment.setColorFilter(google, 0xffcccccc);
        return;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    ImageView aboutHeaderImage;
    NotifyingScrollView aboutScrollView;
    TextView aboutText;
    ImageView back;
    ImageView blog;
    ImageView email;
    ImageView facebook;
    ImageView google;
    ImageView pinterest;
    ImageView twitter;
}
