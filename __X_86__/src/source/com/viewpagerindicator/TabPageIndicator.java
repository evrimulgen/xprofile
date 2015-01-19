// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator, IcsLinearLayout, IconPagerAdapter

public class TabPageIndicator extends HorizontalScrollView
    implements PageIndicator
{
    public static interface OnTabReselectedListener
    {

        public abstract void onTabReselected(int i);
    }

    private class TabView extends TextView
    {

        public int getIndex()
        {
            return mIndex;
        }

        public void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            if(mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth)
                super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(mMaxTabWidth, 0x40000000), j);
        }

        private int mIndex;
        final TabPageIndicator this$0;


/*
        static int access$302(TabView tabview, int i)
        {
            tabview.mIndex = i;
            return i;
        }

*/

        public TabView(Context context)
        {
            this$0 = TabPageIndicator.this;
            super(context, null, R.attr.vpiTabPageIndicatorStyle);
        }
    }


    public TabPageIndicator(Context context)
    {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTabClickListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                TabView tabview = (TabView)view;
                int i = mViewPager.getCurrentItem();
                int j = tabview.getIndex();
                mViewPager.setCurrentItem(j);
                if(i == j && mTabReselectedListener != null)
                    mTabReselectedListener.onTabReselected(j);
            }

            final TabPageIndicator this$0;

            
            {
                this$0 = TabPageIndicator.this;
                super();
            }
        }
;
        setHorizontalScrollBarEnabled(false);
        mTabLayout = new IcsLinearLayout(context, R.attr.vpiTabPageIndicatorStyle);
        addView(mTabLayout, new android.view.ViewGroup.LayoutParams(-2, -1));
    }

    private void addTab(int i, CharSequence charsequence, int j)
    {
        TabView tabview = new TabView(getContext());
        tabview.mIndex = i;
        tabview.setFocusable(true);
        tabview.setOnClickListener(mTabClickListener);
        tabview.setText(charsequence);
        if(j != 0)
            tabview.setCompoundDrawablesWithIntrinsicBounds(j, 0, 0, 0);
        mTabLayout.addView(tabview, new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F));
    }

    private void animateToTab(int i)
    {
        final View tabView = mTabLayout.getChildAt(i);
        if(mTabSelector != null)
            removeCallbacks(mTabSelector);
        mTabSelector = new Runnable() {

            public void run()
            {
                int j = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(j, 0);
                mTabSelector = null;
            }

            final TabPageIndicator this$0;
            final View val$tabView;

            
            {
                this$0 = TabPageIndicator.this;
                tabView = view;
                super();
            }
        }
;
        post(mTabSelector);
    }

    public void notifyDataSetChanged()
    {
        mTabLayout.removeAllViews();
        PagerAdapter pageradapter = mViewPager.getAdapter();
        boolean flag = pageradapter instanceof IconPagerAdapter;
        IconPagerAdapter iconpageradapter = null;
        if(flag)
            iconpageradapter = (IconPagerAdapter)pageradapter;
        int i = pageradapter.getCount();
        for(int j = 0; j < i; j++)
        {
            CharSequence charsequence = pageradapter.getPageTitle(j);
            if(charsequence == null)
                charsequence = EMPTY_TITLE;
            int k = 0;
            if(iconpageradapter != null)
                k = iconpageradapter.getIconResId(j);
            addTab(j, charsequence, k);
        }

        if(mSelectedTabIndex > i)
            mSelectedTabIndex = i - 1;
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mTabSelector != null)
            post(mTabSelector);
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mTabSelector != null)
            removeCallbacks(mTabSelector);
    }

    public void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        boolean flag;
        int l;
        int i1;
        int j1;
        if(k == 0x40000000)
            flag = true;
        else
            flag = false;
        setFillViewport(flag);
        l = mTabLayout.getChildCount();
        if(l > 1 && (k == 0x40000000 || k == 0x80000000))
        {
            if(l > 2)
                mMaxTabWidth = (int)(0.4F * (float)android.view.View.MeasureSpec.getSize(i));
            else
                mMaxTabWidth = android.view.View.MeasureSpec.getSize(i) / 2;
        } else
        {
            mMaxTabWidth = -1;
        }
        i1 = getMeasuredWidth();
        super.onMeasure(i, j);
        j1 = getMeasuredWidth();
        if(flag && i1 != j1)
            setCurrentItem(mSelectedTabIndex);
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
        mSelectedTabIndex = i;
        mViewPager.setCurrentItem(i);
        int j = mTabLayout.getChildCount();
        int k = 0;
        while(k < j) 
        {
            View view = mTabLayout.getChildAt(k);
            boolean flag;
            if(k == i)
                flag = true;
            else
                flag = false;
            view.setSelected(flag);
            if(flag)
                animateToTab(i);
            k++;
        }
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        mListener = onpagechangelistener;
    }

    public void setOnTabReselectedListener(OnTabReselectedListener ontabreselectedlistener)
    {
        mTabReselectedListener = ontabreselectedlistener;
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

    private static final CharSequence EMPTY_TITLE = "";
    private android.support.v4.view.ViewPager.OnPageChangeListener mListener;
    private int mMaxTabWidth;
    private int mSelectedTabIndex;
    private final android.view.View.OnClickListener mTabClickListener;
    private final IcsLinearLayout mTabLayout;
    private OnTabReselectedListener mTabReselectedListener;
    private Runnable mTabSelector;
    private ViewPager mViewPager;





/*
    static Runnable access$202(TabPageIndicator tabpageindicator, Runnable runnable)
    {
        tabpageindicator.mTabSelector = runnable;
        return runnable;
    }

*/

}
