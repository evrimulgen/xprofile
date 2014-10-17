// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation.io;


// Referenced classes of package com.newrelic.agent.android.instrumentation.io:
//            StreamCompleteEvent

public interface StreamCompleteListener
{

    public abstract void streamComplete(StreamCompleteEvent streamcompleteevent);

    public abstract void streamError(StreamCompleteEvent streamcompleteevent);
}
