// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Connection;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.internal.okio.Sink;
import com.squareup.okhttp.internal.okio.Source;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.Proxy;

// Referenced classes of package com.squareup.okhttp.internal.http:
//            Transport, HttpEngine, Request, Response, 
//            HttpConnection, OkHeaders, RetryableSink, RequestLine

public final class HttpTransport
    implements Transport
{

    public HttpTransport(HttpEngine httpengine, HttpConnection httpconnection)
    {
        httpEngine = httpengine;
        httpConnection = httpconnection;
    }

    public boolean canReuseConnection()
    {
        while("close".equalsIgnoreCase(httpEngine.getRequest().header("Connection")) || "close".equalsIgnoreCase(httpEngine.getResponse().header("Connection")) || httpConnection.isClosed()) 
            return false;
        return true;
    }

    public Sink createRequestBody(Request request)
        throws IOException
    {
        long l = OkHeaders.contentLength(request);
        if(httpEngine.bufferRequestBody)
        {
            if(l > 0x7fffffffL)
                throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
            if(l != -1L)
            {
                writeRequestHeaders(request);
                return new RetryableSink((int)l);
            } else
            {
                return new RetryableSink();
            }
        }
        if("chunked".equalsIgnoreCase(request.header("Transfer-Encoding")))
        {
            writeRequestHeaders(request);
            return httpConnection.newChunkedSink();
        }
        if(l != -1L)
        {
            writeRequestHeaders(request);
            return httpConnection.newFixedLengthSink(l);
        } else
        {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
    }

    public void disconnect(HttpEngine httpengine)
        throws IOException
    {
        httpConnection.closeIfOwnedBy(httpengine);
    }

    public void emptyTransferStream()
        throws IOException
    {
        httpConnection.emptyResponseBody();
    }

    public void flushRequest()
        throws IOException
    {
        httpConnection.flush();
    }

    public Source getTransferStream(CacheRequest cacherequest)
        throws IOException
    {
        if(!httpEngine.hasResponseBody())
            return httpConnection.newFixedLengthSource(cacherequest, 0L);
        if("chunked".equalsIgnoreCase(httpEngine.getResponse().header("Transfer-Encoding")))
            return httpConnection.newChunkedSource(cacherequest, httpEngine);
        long l = OkHeaders.contentLength(httpEngine.getResponse());
        if(l != -1L)
            return httpConnection.newFixedLengthSource(cacherequest, l);
        else
            return httpConnection.newUnknownLengthSource(cacherequest);
    }

    public Response.Builder readResponseHeaders()
        throws IOException
    {
        return httpConnection.readResponse();
    }

    public void releaseConnectionOnIdle()
        throws IOException
    {
        if(canReuseConnection())
        {
            httpConnection.poolOnIdle();
            return;
        } else
        {
            httpConnection.closeOnIdle();
            return;
        }
    }

    public void writeRequestBody(RetryableSink retryablesink)
        throws IOException
    {
        httpConnection.writeRequestBody(retryablesink);
    }

    public void writeRequestHeaders(Request request)
        throws IOException
    {
        httpEngine.writingRequestHeaders();
        String s = RequestLine.get(request, httpEngine.getConnection().getRoute().getProxy().type(), httpEngine.getConnection().getHttpMinorVersion());
        httpConnection.writeRequest(request.getHeaders(), s);
    }

    private final HttpConnection httpConnection;
    private final HttpEngine httpEngine;
}
