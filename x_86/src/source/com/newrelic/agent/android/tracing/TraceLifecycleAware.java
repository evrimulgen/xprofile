// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.tracing;


// Referenced classes of package com.newrelic.agent.android.tracing:
//            ActivityTrace

public interface TraceLifecycleAware
{

    public abstract void onEnterMethod();

    public abstract void onExitMethod();

    public abstract void onTraceComplete(ActivityTrace activitytrace);

    public abstract void onTraceStart(ActivityTrace activitytrace);
}
