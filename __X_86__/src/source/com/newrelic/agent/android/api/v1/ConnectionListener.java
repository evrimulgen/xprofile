// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.api.v1;


// Referenced classes of package com.newrelic.agent.android.api.v1:
//            ConnectionEvent

public interface ConnectionListener
{

    public abstract void connected(ConnectionEvent connectionevent);

    public abstract void disconnected(ConnectionEvent connectionevent);
}
