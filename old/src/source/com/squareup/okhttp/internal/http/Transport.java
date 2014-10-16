// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.internal.okio.Sink;
import com.squareup.okhttp.internal.okio.Source;
import java.io.IOException;
import java.net.CacheRequest;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            Request, HttpEngine, RetryableSink

interface Transport
{

    public abstract boolean canReuseConnection();

    public abstract Sink createRequestBody(Request request)
        throws IOException;

    public abstract void disconnect(HttpEngine httpengine)
        throws IOException;

    public abstract void emptyTransferStream()
        throws IOException;

    public abstract void flushRequest()
        throws IOException;

    public abstract Source getTransferStream(CacheRequest cacherequest)
        throws IOException;

    public abstract Response.Builder readResponseHeaders()
        throws IOException;

    public abstract void releaseConnectionOnIdle()
        throws IOException;

    public abstract void writeRequestBody(RetryableSink retryablesink)
        throws IOException;

    public abstract void writeRequestHeaders(Request request)
        throws IOException;

    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;
}
