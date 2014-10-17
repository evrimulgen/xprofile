// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android;

import android.app.Activity;
import android.os.Bundle;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package io.segment.android:
//            Analytics

public class TrackedActivity extends Activity
    implements TraceFieldInterface
{

    public TrackedActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("TrackedActivity");
        TraceMachine.enterMethod(_nr_trace, "TrackedActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        Analytics.onCreate(this);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "TrackedActivity#onCreate", null);
          goto _L1
    }

    protected void onPause()
    {
        Analytics.activityPause(this);
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
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
}
