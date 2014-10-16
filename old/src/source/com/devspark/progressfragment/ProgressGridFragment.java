// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.devspark.progressfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

public class ProgressGridFragment extends Fragment
    implements TraceFieldInterface
{

    public ProgressGridFragment()
    {
    }

    private void ensureList()
    {
        if(mGridView != null)
            return;
        View view = getView();
        if(view == null)
            throw new IllegalStateException("Content view not yet created");
        if(view instanceof GridView)
        {
            mGridView = (GridView)view;
        } else
        {
            View view1 = view.findViewById(0x1020004);
            View view2;
            if(view1 != null)
            {
                if(view1 instanceof TextView)
                    mStandardEmptyView = (TextView)view1;
                else
                    mEmptyView = view1;
            } else
            {
                mStandardEmptyView.setVisibility(8);
            }
            mProgressContainer = view.findViewById(R.id.progress_container);
            mGridContainer = view.findViewById(R.id.grid_container);
            view2 = view.findViewById(R.id.grid);
            if(!(view2 instanceof GridView))
                throw new RuntimeException("Content has view with id attribute 'R.id.grid' that is not a GridView class");
            mGridView = (GridView)view2;
            if(mGridView == null)
                throw new RuntimeException("Your content must have a GridView whose id attribute is 'R.id.grid'");
            if(mEmptyView != null)
                mGridView.setEmptyView(mEmptyView);
            else
            if(mEmptyText != null)
            {
                mStandardEmptyView.setText(mEmptyText);
                mGridView.setEmptyView(mStandardEmptyView);
            }
        }
        mGridShown = true;
        mGridView.setOnItemClickListener(mOnClickListener);
        if(mAdapter != null)
        {
            ListAdapter listadapter = mAdapter;
            mAdapter = null;
            setGridAdapter(listadapter);
        } else
        if(mProgressContainer != null)
            setGridShown(false, false);
        mHandler.post(mRequestFocus);
    }

    private void setGridShown(boolean flag, boolean flag1)
    {
        ensureList();
        if(mProgressContainer == null)
            throw new IllegalStateException("Can't be used with a custom content view");
        if(mGridShown == flag)
            return;
        mGridShown = flag;
        if(flag)
        {
            if(flag1)
            {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0001));
                mGridContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0000));
            } else
            {
                mProgressContainer.clearAnimation();
                mGridContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(8);
            mGridContainer.setVisibility(0);
            return;
        }
        if(flag1)
        {
            mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0000));
            mGridContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0001));
        } else
        {
            mProgressContainer.clearAnimation();
            mGridContainer.clearAnimation();
        }
        mProgressContainer.setVisibility(0);
        mGridContainer.setVisibility(8);
    }

    public ListAdapter getGridAdapter()
    {
        return mAdapter;
    }

    public GridView getGridView()
    {
        ensureList();
        return mGridView;
    }

    public long getSelectedItemId()
    {
        ensureList();
        return mGridView.getSelectedItemId();
    }

    public int getSelectedItemPosition()
    {
        ensureList();
        return mGridView.getSelectedItemPosition();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "ProgressGridFragment#onCreateView", null);
_L1:
        View view = layoutinflater.inflate(R.layout.fragment_progress_grid, viewgroup, false);
        TraceMachine.exitMethod();
        return view;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "ProgressGridFragment#onCreateView", null);
          goto _L1
    }

    public void onDestroyView()
    {
        mHandler.removeCallbacks(mRequestFocus);
        mGridView = null;
        mGridShown = false;
        mGridContainer = null;
        mProgressContainer = null;
        mEmptyView = null;
        mStandardEmptyView = null;
        super.onDestroyView();
    }

    public void onGridItemClick(GridView gridview, View view, int i, long l)
    {
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
        ensureList();
    }

    public void setEmptyText(int i)
    {
        setEmptyText(((CharSequence) (getString(i))));
    }

    public void setEmptyText(CharSequence charsequence)
    {
        ensureList();
        if(mStandardEmptyView == null)
            throw new IllegalStateException("Can't be used with a custom content view");
        mStandardEmptyView.setText(charsequence);
        if(mEmptyText == null)
            mGridView.setEmptyView(mStandardEmptyView);
        mEmptyText = charsequence;
    }

    public void setGridAdapter(ListAdapter listadapter)
    {
        boolean flag;
        if(mAdapter != null)
            flag = true;
        else
            flag = false;
        mAdapter = listadapter;
        if(mGridView != null)
        {
            mGridView.setAdapter(listadapter);
            if(!mGridShown && !flag)
            {
                android.os.IBinder ibinder = getView().getWindowToken();
                boolean flag1 = false;
                if(ibinder != null)
                    flag1 = true;
                setGridShown(true, flag1);
            }
        }
    }

    public void setGridShown(boolean flag)
    {
        setGridShown(flag, true);
    }

    public void setGridShownNoAnimation(boolean flag)
    {
        setGridShown(flag, false);
    }

    public void setSelection(int i)
    {
        ensureList();
        mGridView.setSelection(i);
    }

    private ListAdapter mAdapter;
    private CharSequence mEmptyText;
    private View mEmptyView;
    private View mGridContainer;
    private boolean mGridShown;
    private GridView mGridView;
    private final Handler mHandler = new Handler();
    private final android.widget.AdapterView.OnItemClickListener mOnClickListener = new android.widget.AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            onGridItemClick((GridView)adapterview, view, i, l);
        }

        final ProgressGridFragment this$0;

            
            {
                this$0 = ProgressGridFragment.this;
                super();
            }
    }
;
    private View mProgressContainer;
    private final Runnable mRequestFocus = new Runnable() {

        public void run()
        {
            mGridView.focusableViewAvailable(mGridView);
        }

        final ProgressGridFragment this$0;

            
            {
                this$0 = ProgressGridFragment.this;
                super();
            }
    }
;
    private TextView mStandardEmptyView;

}
