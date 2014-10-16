// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.TextView;
import com.nineoldandroids.view.ViewHelper;
import com.roadtrippers.adapters.IntroPagerAdapter;
import com.roadtrippers.events.IntroComplete;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.util.Persistence;
import com.roadtrippers.util.RTAnalytics;
import com.squareup.otto.Bus;
import com.viewpagerindicator.CirclePageIndicator;
import dagger.Lazy;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class IntroFragment extends BaseFragment
{

    public IntroFragment()
    {
    }

    private void logEvent(int i, int j)
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c0072, i, String.valueOf(j));
    }

    void finish()
    {
        logEvent(0x7f0c004d, 1 + pager.getCurrentItem());
        ((Persistence)persistenceLazy.get()).setIntroWasShown(true).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() {

            public void call(Boolean boolean1)
            {
                ((Bus)busLazy.get()).post(new IntroComplete());
            }

            public volatile void call(Object obj)
            {
                call((Boolean)obj);
            }

            final IntroFragment this$0;

            
            {
                this$0 = IntroFragment.this;
                super();
            }
        }
);
    }

    void next()
    {
        if(pager != null)
        {
            pager.setCurrentItem(1 + pager.getCurrentItem());
            return;
        } else
        {
            finish();
            return;
        }
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030045, viewgroup, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        pager.setAdapter(new IntroPagerAdapter(getActivity(), getChildFragmentManager()));
        indicator.setViewPager(pager);
        indicator.setOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int i)
            {
            }

            public void onPageScrolled(int i, float f, int j)
            {
                if(i == 3 && getView() != null)
                    ViewHelper.setAlpha(getView(), 1.0F - f);
            }

            public void onPageSelected(int i)
            {
                if(i == 3)
                    next.setText(0x7f0c00ab);
                else
                    next.setText(0x7f0c00c4);
                if(i >= 4)
                    finish();
            }

            final IntroFragment this$0;

            
            {
                this$0 = IntroFragment.this;
                super();
            }
        }
);
    }

    void skip()
    {
        finish();
    }

    private static final int TRACKING_CATEGORY = 0x7f0c0072;
    Lazy busLazy;
    CirclePageIndicator indicator;
    TextView next;
    ViewPager pager;
    Lazy persistenceLazy;
}
