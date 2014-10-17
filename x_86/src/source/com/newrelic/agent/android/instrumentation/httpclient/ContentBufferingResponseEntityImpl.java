// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import java.io.*;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class ContentBufferingResponseEntityImpl
    implements HttpEntity
{

    public ContentBufferingResponseEntityImpl(HttpEntity httpentity)
    {
        if(httpentity == null)
        {
            throw new IllegalArgumentException("Missing wrapped entity");
        } else
        {
            impl = httpentity;
            return;
        }
    }

    public void consumeContent()
        throws IOException
    {
        impl.consumeContent();
    }

    public InputStream getContent()
        throws IOException, IllegalStateException
    {
        if(contentStream != null)
        {
            return contentStream;
        } else
        {
            contentStream = new CountingInputStream(impl.getContent(), true);
            return contentStream;
        }
    }

    public Header getContentEncoding()
    {
        return impl.getContentEncoding();
    }

    public long getContentLength()
    {
        return impl.getContentLength();
    }

    public Header getContentType()
    {
        return impl.getContentType();
    }

    public boolean isChunked()
    {
        return impl.isChunked();
    }

    public boolean isRepeatable()
    {
        return impl.isRepeatable();
    }

    public boolean isStreaming()
    {
        return impl.isStreaming();
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        impl.writeTo(outputstream);
    }

    private CountingInputStream contentStream;
    final HttpEntity impl;
}
