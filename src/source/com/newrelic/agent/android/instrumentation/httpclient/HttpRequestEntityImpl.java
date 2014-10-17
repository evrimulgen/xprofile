// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.io.*;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.*;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public final class HttpRequestEntityImpl
    implements HttpEntity, StreamCompleteListener
{

    public HttpRequestEntityImpl(HttpEntity httpentity, TransactionState transactionstate)
    {
        impl = httpentity;
        transactionState = transactionstate;
    }

    public void consumeContent()
        throws IOException
    {
        try
        {
            impl.consumeContent();
            return;
        }
        catch(IOException ioexception)
        {
            TransactionStateUtil.setErrorCodeFromException(transactionState, ioexception);
            if(!transactionState.isComplete())
            {
                TransactionData transactiondata = transactionState.end();
                TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
            }
            throw ioexception;
        }
    }

    public InputStream getContent()
        throws IOException, IllegalStateException
    {
        CountingInputStream countinginputstream;
        if(transactionState.isSent())
            break MISSING_BLOCK_LABEL_37;
        countinginputstream = new CountingInputStream(impl.getContent());
        countinginputstream.addStreamCompleteListener(this);
        return countinginputstream;
        InputStream inputstream;
        try
        {
            inputstream = impl.getContent();
        }
        catch(IOException ioexception)
        {
            TransactionStateUtil.setErrorCodeFromException(transactionState, ioexception);
            if(!transactionState.isComplete())
            {
                TransactionData transactiondata1 = transactionState.end();
                TaskQueue.queue(new HttpTransactionMeasurement(transactiondata1.getUrl(), transactiondata1.getStatusCode(), transactiondata1.getErrorCode(), transactiondata1.getTimestamp(), transactiondata1.getTime(), transactiondata1.getBytesSent(), transactiondata1.getBytesReceived(), transactiondata1.getAppData()));
            }
            throw ioexception;
        }
        catch(IllegalStateException illegalstateexception)
        {
            TransactionStateUtil.setErrorCodeFromException(transactionState, illegalstateexception);
            if(!transactionState.isComplete())
            {
                TransactionData transactiondata = transactionState.end();
                TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
            }
            throw illegalstateexception;
        }
        return inputstream;
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

    public void streamComplete(StreamCompleteEvent streamcompleteevent)
    {
        ((StreamCompleteListenerSource)streamcompleteevent.getSource()).removeStreamCompleteListener(this);
        transactionState.setBytesSent(streamcompleteevent.getBytes());
    }

    public void streamError(StreamCompleteEvent streamcompleteevent)
    {
        ((StreamCompleteListenerSource)streamcompleteevent.getSource()).removeStreamCompleteListener(this);
        TransactionStateUtil.setErrorCodeFromException(transactionState, streamcompleteevent.getException());
        if(!transactionState.isComplete())
        {
            transactionState.setBytesSent(streamcompleteevent.getBytes());
            TransactionData transactiondata = transactionState.end();
            TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        }
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        try
        {
            if(!transactionState.isSent())
            {
                CountingOutputStream countingoutputstream = new CountingOutputStream(outputstream);
                impl.writeTo(countingoutputstream);
                transactionState.setBytesSent(countingoutputstream.getCount());
                return;
            }
        }
        catch(IOException ioexception)
        {
            TransactionStateUtil.setErrorCodeFromException(transactionState, ioexception);
            if(!transactionState.isComplete())
            {
                TransactionData transactiondata = transactionState.end();
                TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
            }
            throw ioexception;
        }
        impl.writeTo(outputstream);
        return;
    }

    private final HttpEntity impl;
    private final TransactionState transactionState;
}
