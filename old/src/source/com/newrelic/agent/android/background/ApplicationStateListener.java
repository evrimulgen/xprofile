// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.background;


// Referenced classes of package com.newrelic.agent.android.background:
//            ApplicationStateEvent

public interface ApplicationStateListener
{

    public abstract void applicationBackgrounded(ApplicationStateEvent applicationstateevent);

    public abstract void applicationForegrounded(ApplicationStateEvent applicationstateevent);
}
