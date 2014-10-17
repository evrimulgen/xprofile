// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation.io;

import java.util.EventObject;

public final class StreamCompleteEvent extends EventObject
{

    public StreamCompleteEvent(Object obj, long l)
    {
        this(obj, l, null);
    }

    public StreamCompleteEvent(Object obj, long l, Exception exception1)
    {
        super(obj);
        bytes = l;
        exception = exception1;
    }

    public long getBytes()
    {
        return bytes;
    }

    public Exception getException()
    {
        return exception;
    }

    public boolean isError()
    {
        return exception != null;
    }

    private static final long serialVersionUID = 1L;
    private final long bytes;
    private final Exception exception;
}
