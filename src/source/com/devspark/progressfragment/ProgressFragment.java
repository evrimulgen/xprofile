// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.devspark.progressfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

public class ProgressFragment extends Fragment
    implements TraceFieldInterface
{

    public ProgressFragment()
    {
    }

    private void ensureContent()
    {
        if(mContentContainer == null || mProgressContainer == null)
        {
            View view = getView();
            if(view == null)
                throw new IllegalStateException("Content view not yet created");
            mProgressContainer = view.findViewById(R.id.progress_container);
            if(mProgressContainer == null)
                throw new RuntimeException("Your content must have a ViewGroup whose id attribute is 'R.id.progress_container'");
            mContentContainer = view.findViewById(R.id.content_container);
            if(mContentContainer == null)
                throw new RuntimeException("Your content must have a ViewGroup whose id attribute is 'R.id.content_container'");
            mEmptyView = view.findViewById(0x1020004);
            if(mEmptyView != null)
                mEmptyView.setVisibility(8);
            mContentShown = true;
            if(mContentView == null)
            {
                setContentShown(false, false);
                return;
            }
        }
    }

    private void setContentShown(boolean flag, boolean flag1)
    {
        ensureContent();
        if(mContentShown == flag)
            return;
        mContentShown = flag;
        if(flag)
        {
            if(flag1)
            {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0001));
                mContentContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0000));
            } else
            {
                mProgressContainer.clearAnimation();
                mContentContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(8);
            mContentContainer.setVisibility(0);
            return;
        }
        if(flag1)
        {
            mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0000));
            mContentContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0001));
        } else
        {
            mProgressContainer.clearAnimation();
            mContentContainer.clearAnimation();
        }
        mProgressContainer.setVisibility(0);
        mContentContainer.setVisibility(8);
    }

    public View getContentView()
    {
        return mContentView;
    }

    public boolean isContentEmpty()
    {
        return mIsContentEmpty;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "ProgressFragment#onCreateView", null);
_L1:
        View view = layoutinflater.inflate(R.layout.fragment_progress, viewgroup, false);
        TraceMachine.exitMethod();
        return view;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "ProgressFragment#onCreateView", null);
          goto _L1
    }

    public void onDestroyView()
    {
        mContentShown = false;
        mIsContentEmpty = false;
        mEmptyView = null;
        mContentView = null;
        mContentContainer = null;
        mProgressContainer = null;
        super.onDestroyView();
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        ensureContent();
    }

    public void setContentEmpty(boolean flag)
    {
        ensureContent();
        if(mContentView == null)
            throw new IllegalStateException("Content view must be initialized before");
        if(flag)
        {
            mEmptyView.setVisibility(0);
            mContentView.setVisibility(8);
        } else
        {
            mEmptyView.setVisibility(8);
            mContentView.setVisibility(0);
        }
        mIsContentEmpty = flag;
    }

    public void setContentShown(boolean flag)
    {
        setContentShown(flag, true);
    }

    public void setContentShownNoAnimation(boolean flag)
    {
        setContentShown(flag, false);
    }

    public void setContentView(int i)
    {
        setContentView(LayoutInflater.from(getActivity()).inflate(i, null));
    }

    public void setContentView(View view)
    {
        ensureContent();
        if(view == null)
            throw new IllegalArgumentException("Content view can't be null");
        if(mContentContainer instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)mContentContainer;
            if(mContentView == null)
            {
                viewgroup.addView(view);
            } else
            {
                int i = viewgroup.indexOfChild(mContentView);
                viewgroup.removeView(mContentView);
                viewgroup.addView(view, i);
            }
            mContentView = view;
            return;
        } else
        {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
    }

    public void setEmptyText(int i)
    {
        setEmptyText(((CharSequence) (getString(i))));
    }

    public void setEmptyText(CharSequence charsequence)
    {
        ensureContent();
        if(mEmptyView != null && (mEmptyView instanceof TextView))
        {
            ((TextView)mEmptyView).setText(charsequence);
            return;
        } else
        {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
    }

    private View mContentContainer;
    private boolean mContentShown;
    private View mContentView;
    private View mEmptyView;
    private boolean mIsContentEmpty;
    private View mProgressContainer;
}
