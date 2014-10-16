// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package eu.janmuller.android.simplecropimage;

import android.app.Activity;
import android.os.Bundle;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Iterator;

public class MonitoredActivity extends Activity
    implements TraceFieldInterface
{
    public static class LifeCycleAdapter
        implements LifeCycleListener
    {

        public void onActivityCreated(MonitoredActivity monitoredactivity)
        {
        }

        public void onActivityDestroyed(MonitoredActivity monitoredactivity)
        {
        }

        public void onActivityPaused(MonitoredActivity monitoredactivity)
        {
        }

        public void onActivityResumed(MonitoredActivity monitoredactivity)
        {
        }

        public void onActivityStarted(MonitoredActivity monitoredactivity)
        {
        }

        public void onActivityStopped(MonitoredActivity monitoredactivity)
        {
        }

        public LifeCycleAdapter()
        {
        }
    }

    public static interface LifeCycleListener
    {

        public abstract void onActivityCreated(MonitoredActivity monitoredactivity);

        public abstract void onActivityDestroyed(MonitoredActivity monitoredactivity);

        public abstract void onActivityPaused(MonitoredActivity monitoredactivity);

        public abstract void onActivityResumed(MonitoredActivity monitoredactivity);

        public abstract void onActivityStarted(MonitoredActivity monitoredactivity);

        public abstract void onActivityStopped(MonitoredActivity monitoredactivity);
    }


    public MonitoredActivity()
    {
    }

    public void addLifeCycleListener(LifeCycleListener lifecyclelistener)
    {
        if(mListeners.contains(lifecyclelistener))
        {
            return;
        } else
        {
            mListeners.add(lifecyclelistener);
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("MonitoredActivity");
        TraceMachine.enterMethod(_nr_trace, "MonitoredActivity#onCreate", null);
_L2:
        super.onCreate(bundle);
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((LifeCycleListener)iterator.next()).onActivityCreated(this));
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "MonitoredActivity#onCreate", null);
        if(true) goto _L2; else goto _L1
_L1:
        TraceMachine.exitMethod();
        return;
    }

    protected void onDestroy()
    {
        super.onDestroy();
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((LifeCycleListener)iterator.next()).onActivityDestroyed(this));
    }

    protected void onStart()
    {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStart();
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((LifeCycleListener)iterator.next()).onActivityStarted(this));
    }

    protected void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((LifeCycleListener)iterator.next()).onActivityStopped(this));
    }

    public void removeLifeCycleListener(LifeCycleListener lifecyclelistener)
    {
        mListeners.remove(lifecyclelistener);
    }

    private final ArrayList mListeners = new ArrayList();
}
