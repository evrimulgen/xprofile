// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.*;
import java.io.IOException;
import java.net.ProtocolException;

final class RetryableSink
    implements Sink
{

    public RetryableSink()
    {
        this(-1);
    }

    public RetryableSink(int i)
    {
        content = new OkBuffer();
        limit = i;
    }

    public void close()
        throws IOException
    {
        if(!closed)
        {
            closed = true;
            if(content.size() < (long)limit)
                throw new ProtocolException((new StringBuilder()).append("content-length promised ").append(limit).append(" bytes, but received ").append(content.size()).toString());
        }
    }

    public long contentLength()
        throws IOException
    {
        return content.size();
    }

    public Sink deadline(Deadline deadline1)
    {
        return this;
    }

    public void flush()
        throws IOException
    {
    }

    public void write(OkBuffer okbuffer, long l)
        throws IOException
    {
        if(closed)
            throw new IllegalStateException("closed");
        Util.checkOffsetAndCount(okbuffer.size(), 0L, l);
        if(limit != -1 && content.size() > (long)limit - l)
        {
            throw new ProtocolException((new StringBuilder()).append("exceeded content-length limit of ").append(limit).append(" bytes").toString());
        } else
        {
            content.write(okbuffer, l);
            return;
        }
    }

    public void writeToSocket(BufferedSink bufferedsink)
        throws IOException
    {
        bufferedsink.write(content.clone(), content.size());
    }

    private boolean closed;
    private final OkBuffer content;
    private final int limit;
}
