// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.activities.base;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.Window;
import butterknife.ButterKnife;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.util.Log;
import com.squareup.otto.Bus;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import io.segment.android.Analytics;

public abstract class BaseActivity extends FragmentActivity
    implements TraceFieldInterface
{

    public BaseActivity()
    {
    }

    public void onBackPressed()
    {
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("BaseActivity");
        TraceMachine.enterMethod(_nr_trace, "BaseActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        Analytics.onCreate(this, "kb41x0vf7i");
        Object aobj[] = new Object[1];
        aobj[0] = getClass().getName();
        Log.d("Activity Created: %s", aobj);
        RoadTrippersApp.from(this).inject(this);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "BaseActivity#onCreate", null);
          goto _L1
    }

    protected void onDestroy()
    {
        ButterKnife.reset(this);
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }

    public void onPause()
    {
        super.onPause();
        try
        {
            ((Bus)bus.get()).unregister(this);
        }
        catch(Exception exception)
        {
            Log.d("An error occurred while attempting to unregister from the bus.");
            exception.printStackTrace();
        }
        Analytics.activityPause(this);
    }

    public void onResume()
    {
        super.onResume();
        ((Bus)bus.get()).register(this);
        Analytics.activityResume(this);
    }

    protected void onStart()
    {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStart();
        Analytics.activityStart(this);
    }

    protected void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        Analytics.activityStop(this);
    }

    protected void setContentFragment(Fragment fragment)
    {
        if(getSupportFragmentManager().findFragmentById(0x1020002) == null)
            getSupportFragmentManager().beginTransaction().add(0x1020002, fragment).commit();
        getWindow().setBackgroundDrawable(null);
    }

    public void setContentView(int i)
    {
        super.setContentView(i);
        ButterKnife.inject(this);
    }

    Lazy bus;
}
