// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import butterknife.ButterKnife;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.roadtrippers.RoadTrippersApp;
import com.squareup.otto.Bus;
import dagger.Lazy;

public class BaseFragment extends Fragment
    implements TraceFieldInterface
{

    public BaseFragment()
    {
    }

    public void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("BaseFragment");
        TraceMachine.enterMethod(_nr_trace, "BaseFragment#onCreate", null);
_L1:
        super.onCreate(bundle);
        RoadTrippersApp.from(getActivity()).inject(this);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "BaseFragment#onCreate", null);
          goto _L1
    }

    public void onDestroyView()
    {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    public void onPause()
    {
        super.onPause();
        ((Bus)bus.get()).unregister(this);
    }

    public void onResume()
    {
        super.onResume();
        ((Bus)bus.get()).register(this);
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
        ButterKnife.inject(this, view);
    }

    Lazy bus;
}
