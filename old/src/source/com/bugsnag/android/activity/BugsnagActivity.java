// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.bugsnag.android.Bugsnag;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

public class BugsnagActivity extends Activity
    implements TraceFieldInterface
{

    public BugsnagActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("BugsnagActivity");
        TraceMachine.enterMethod(_nr_trace, "BugsnagActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        Bugsnag.onActivityCreate(this);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "BugsnagActivity#onCreate", null);
          goto _L1
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Bugsnag.onActivityDestroy(this);
    }

    protected void onPause()
    {
        super.onPause();
        Bugsnag.onActivityPause(this);
    }

    protected void onResume()
    {
        super.onResume();
        Bugsnag.onActivityResume(this);
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
}
