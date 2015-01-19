// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.io.*;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public final class HttpResponseEntityImpl
    implements HttpEntity, StreamCompleteListener
{

    public HttpResponseEntityImpl(HttpEntity httpentity, TransactionState transactionstate, long l)
    {
        impl = httpentity;
        transactionState = transactionstate;
        contentLengthFromHeader = l;
    }

    private void addTransactionAndErrorData(TransactionState transactionstate)
    {
        TransactionData transactiondata = transactionstate.end();
        TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        if((long)transactionstate.getStatusCode() >= 400L)
        {
            StringBuilder stringbuilder = new StringBuilder();
            Header header;
            TreeMap treemap;
            try
            {
                InputStream inputstream = getContent();
                if(inputstream instanceof CountingInputStream)
                    stringbuilder.append(((CountingInputStream)inputstream).getBufferAsString());
            }
            catch(Exception exception)
            {
                log.error(exception.toString());
            }
            header = impl.getContentType();
            treemap = new TreeMap();
            if(header != null && header.getValue() != null && !"".equals(header.getValue()))
                treemap.put("content_type", header.getValue());
            treemap.put("content_length", (new StringBuilder()).append(transactionstate.getBytesReceived()).append("").toString());
            Measurements.addHttpError(transactiondata, stringbuilder.toString(), treemap);
        }
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
        if(contentStream != null)
            return contentStream;
        CountingInputStream countinginputstream;
        try
        {
            contentStream = new CountingInputStream(impl.getContent(), true);
            contentStream.addStreamCompleteListener(this);
            countinginputstream = contentStream;
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
        return countinginputstream;
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
        if(!transactionState.isComplete())
        {
            if(contentLengthFromHeader >= 0L)
                transactionState.setBytesReceived(contentLengthFromHeader);
            else
                transactionState.setBytesReceived(streamcompleteevent.getBytes());
            addTransactionAndErrorData(transactionState);
        }
    }

    public void streamError(StreamCompleteEvent streamcompleteevent)
    {
        ((StreamCompleteListenerSource)streamcompleteevent.getSource()).removeStreamCompleteListener(this);
        TransactionStateUtil.setErrorCodeFromException(transactionState, streamcompleteevent.getException());
        if(!transactionState.isComplete())
            transactionState.setBytesReceived(streamcompleteevent.getBytes());
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        if(!transactionState.isComplete())
        {
            CountingOutputStream countingoutputstream = new CountingOutputStream(outputstream);
            try
            {
                impl.writeTo(countingoutputstream);
            }
            catch(IOException ioexception)
            {
                TransactionStateUtil.setErrorCodeFromException(transactionState, ioexception);
                if(!transactionState.isComplete())
                {
                    transactionState.setBytesReceived(countingoutputstream.getCount());
                    TransactionData transactiondata = transactionState.end();
                    TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
                }
                ioexception.printStackTrace();
                throw ioexception;
            }
            if(!transactionState.isComplete())
            {
                if(contentLengthFromHeader >= 0L)
                    transactionState.setBytesReceived(contentLengthFromHeader);
                else
                    transactionState.setBytesReceived(countingoutputstream.getCount());
                addTransactionAndErrorData(transactionState);
            }
            return;
        } else
        {
            impl.writeTo(outputstream);
            return;
        }
    }

    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final long contentLengthFromHeader;
    private CountingInputStream contentStream;
    private final HttpEntity impl;
    private final TransactionState transactionState;

}
