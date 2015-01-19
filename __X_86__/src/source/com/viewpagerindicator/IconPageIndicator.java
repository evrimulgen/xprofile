// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator, IcsLinearLayout, IconPagerAdapter

public class IconPageIndicator extends HorizontalScrollView
    implements PageIndicator
{

    public IconPageIndicator(Context context)
    {
        this(context, null);
    }

    public IconPageIndicator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setHorizontalScrollBarEnabled(false);
        mIconsLayout = new IcsLinearLayout(context, R.attr.vpiIconPageIndicatorStyle);
        addView(mIconsLayout, new android.widget.FrameLayout.LayoutParams(-2, -1, 17));
    }

    private void animateToIcon(int i)
    {
        final View iconView = mIconsLayout.getChildAt(i);
        if(mIconSelector != null)
            removeCallbacks(mIconSelector);
        mIconSelector = new Runnable() {

            public void run()
            {
                int j = iconView.getLeft() - (getWidth() - iconView.getWidth()) / 2;
                smoothScrollTo(j, 0);
                mIconSelector = null;
            }

            final IconPageIndicator this$0;
            final View val$iconView;

            
            {
                this$0 = IconPageIndicator.this;
                iconView = view;
                super();
            }
        }
;
        post(mIconSelector);
    }

    public void notifyDataSetChanged()
    {
        mIconsLayout.removeAllViews();
        IconPagerAdapter iconpageradapter = (IconPagerAdapter)mViewPager.getAdapter();
        int i = iconpageradapter.getCount();
        for(int j = 0; j < i; j++)
        {
            ImageView imageview = new ImageView(getContext(), null, R.attr.vpiIconPageIndicatorStyle);
            imageview.setImageResource(iconpageradapter.getIconResId(j));
            mIconsLayout.addView(imageview);
        }

        if(mSelectedIndex > i)
            mSelectedIndex = i - 1;
        setCurrentItem(mSelectedIndex);
        requestLayout();
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mIconSelector != null)
            post(mIconSelector);
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mIconSelector != null)
            removeCallbacks(mIconSelector);
    }

    public void onPageScrollStateChanged(int i)
    {
        if(mListener != null)
            mListener.onPageScrollStateChanged(i);
    }

    public void onPageScrolled(int i, float f, int j)
    {
        if(mListener != null)
            mListener.onPageScrolled(i, f, j);
    }

    public void onPageSelected(int i)
    {
        setCurrentItem(i);
        if(mListener != null)
            mListener.onPageSelected(i);
    }

    public void setCurrentItem(int i)
    {
        if(mViewPager == null)
            throw new IllegalStateException("ViewPager has not been bound.");
        mSelectedIndex = i;
        mViewPager.setCurrentItem(i);
        int j = mIconsLayout.getChildCount();
        int k = 0;
        while(k < j) 
        {
            View view = mIconsLayout.getChildAt(k);
            boolean flag;
            if(k == i)
                flag = true;
            else
                flag = false;
            view.setSelected(flag);
            if(flag)
                animateToIcon(i);
            k++;
        }
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        mListener = onpagechangelistener;
    }

    public void setViewPager(ViewPager viewpager)
    {
        if(mViewPager == viewpager)
            return;
        if(mViewPager != null)
            mViewPager.setOnPageChangeListener(null);
        if(viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            mViewPager = viewpager;
            viewpager.setOnPageChangeListener(this);
            notifyDataSetChanged();
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int i)
    {
        setViewPager(viewpager);
        setCurrentItem(i);
    }

    private Runnable mIconSelector;
    private final IcsLinearLayout mIconsLayout;
    private android.support.v4.view.ViewPager.OnPageChangeListener mListener;
    private int mSelectedIndex;
    private ViewPager mViewPager;


/*
    static Runnable access$002(IconPageIndicator iconpageindicator, Runnable runnable)
    {
        iconpageindicator.mIconSelector = runnable;
        return runnable;
    }

*/
}
