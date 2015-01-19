// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.api.v1;

import com.newrelic.agent.android.api.common.ConnectionState;
import java.util.EventObject;

public final class ConnectionEvent extends EventObject
{

    public ConnectionEvent(Object obj)
    {
        this(obj, null);
    }

    public ConnectionEvent(Object obj, ConnectionState connectionstate)
    {
        super(obj);
        connectionState = connectionstate;
    }

    public ConnectionState getConnectionState()
    {
        return connectionState;
    }

    private static final long serialVersionUID = 1L;
    private final ConnectionState connectionState;
}
