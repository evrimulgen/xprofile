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

public class ProgressListFragment extends Fragment
    implements TraceFieldInterface
{

    public ProgressListFragment()
    {
    }

    private void ensureList()
    {
        if(mListView != null)
            return;
        View view = getView();
        if(view == null)
            throw new IllegalStateException("Content view not yet created");
        if(view instanceof ListView)
        {
            mListView = (ListView)view;
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
            mListContainer = view.findViewById(R.id.list_container);
            view2 = view.findViewById(0x102000a);
            if(!(view2 instanceof ListView))
                throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
            mListView = (ListView)view2;
            if(mListView == null)
                throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
            if(mEmptyView != null)
                mListView.setEmptyView(mEmptyView);
            else
            if(mEmptyText != null)
            {
                mStandardEmptyView.setText(mEmptyText);
                mListView.setEmptyView(mStandardEmptyView);
            }
        }
        mListShown = true;
        mListView.setOnItemClickListener(mOnClickListener);
        if(mAdapter != null)
        {
            ListAdapter listadapter = mAdapter;
            mAdapter = null;
            setListAdapter(listadapter);
        } else
        if(mProgressContainer != null)
            setListShown(false, false);
        mHandler.post(mRequestFocus);
    }

    private void setListShown(boolean flag, boolean flag1)
    {
        ensureList();
        if(mProgressContainer == null)
            throw new IllegalStateException("Can't be used with a custom content view");
        if(mListShown == flag)
            return;
        mListShown = flag;
        if(flag)
        {
            if(flag1)
            {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0001));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0000));
            } else
            {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(8);
            mListContainer.setVisibility(0);
            return;
        }
        if(flag1)
        {
            mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0000));
            mListContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 0x10a0001));
        } else
        {
            mProgressContainer.clearAnimation();
            mListContainer.clearAnimation();
        }
        mProgressContainer.setVisibility(0);
        mListContainer.setVisibility(8);
    }

    public ListAdapter getListAdapter()
    {
        return mAdapter;
    }

    public ListView getListView()
    {
        ensureList();
        return mListView;
    }

    public long getSelectedItemId()
    {
        ensureList();
        return mListView.getSelectedItemId();
    }

    public int getSelectedItemPosition()
    {
        ensureList();
        return mListView.getSelectedItemPosition();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "ProgressListFragment#onCreateView", null);
_L1:
        View view = layoutinflater.inflate(R.layout.fragment_progress_list, viewgroup, false);
        TraceMachine.exitMethod();
        return view;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "ProgressListFragment#onCreateView", null);
          goto _L1
    }

    public void onDestroyView()
    {
        mHandler.removeCallbacks(mRequestFocus);
        mListView = null;
        mListShown = false;
        mListContainer = null;
        mProgressContainer = null;
        mEmptyView = null;
        mStandardEmptyView = null;
        super.onDestroyView();
    }

    public void onListItemClick(ListView listview, View view, int i, long l)
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
            mListView.setEmptyView(mStandardEmptyView);
        mEmptyText = charsequence;
    }

    public void setListAdapter(ListAdapter listadapter)
    {
        boolean flag;
        if(mAdapter != null)
            flag = true;
        else
            flag = false;
        mAdapter = listadapter;
        if(mListView != null)
        {
            mListView.setAdapter(listadapter);
            if(!mListShown && !flag)
            {
                android.os.IBinder ibinder = getView().getWindowToken();
                boolean flag1 = false;
                if(ibinder != null)
                    flag1 = true;
                setListShown(true, flag1);
            }
        }
    }

    public void setListShown(boolean flag)
    {
        setListShown(flag, true);
    }

    public void setListShownNoAnimation(boolean flag)
    {
        setListShown(flag, false);
    }

    public void setSelection(int i)
    {
        ensureList();
        mListView.setSelection(i);
    }

    private ListAdapter mAdapter;
    private CharSequence mEmptyText;
    private View mEmptyView;
    private final Handler mHandler = new Handler();
    private View mListContainer;
    private boolean mListShown;
    private ListView mListView;
    private final android.widget.AdapterView.OnItemClickListener mOnClickListener = new android.widget.AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            onListItemClick((ListView)adapterview, view, i, l);
        }

        final ProgressListFragment this$0;

            
            {
                this$0 = ProgressListFragment.this;
                super();
            }
    }
;
    private View mProgressContainer;
    private final Runnable mRequestFocus = new Runnable() {

        public void run()
        {
            mListView.focusableViewAvailable(mListView);
        }

        final ProgressListFragment this$0;

            
            {
                this$0 = ProgressListFragment.this;
                super();
            }
    }
;
    private TextView mStandardEmptyView;

}
