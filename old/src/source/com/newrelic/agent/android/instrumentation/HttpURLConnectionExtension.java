// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.io.CountingOutputStream;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.*;
import java.net.*;
import java.security.Permission;
import java.util.Map;
import java.util.TreeMap;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            TransactionStateUtil, TransactionState

public class HttpURLConnectionExtension extends HttpURLConnection
{

    public HttpURLConnectionExtension(HttpURLConnection httpurlconnection)
    {
        super(httpurlconnection.getURL());
        impl = httpurlconnection;
        TransactionStateUtil.setCrossProcessHeader(httpurlconnection);
    }

    private void addTransactionAndErrorData(TransactionState transactionstate)
    {
        TransactionData transactiondata = transactionstate.end();
        TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        if((long)transactionstate.getStatusCode() >= 400L)
        {
            StringBuilder stringbuilder = new StringBuilder();
            TreeMap treemap;
            String s;
            try
            {
                InputStream inputstream = getErrorStream();
                if(inputstream instanceof CountingInputStream)
                    stringbuilder.append(((CountingInputStream)inputstream).getBufferAsString());
            }
            catch(Exception exception)
            {
                log.error(exception.toString());
            }
            treemap = new TreeMap();
            s = impl.getContentType();
            if(s != null && !"".equals(s))
                treemap.put("content_type", s);
            treemap.put("content_length", (new StringBuilder()).append(transactionstate.getBytesReceived()).append("").toString());
            Measurements.addHttpError(transactiondata.getUrl(), transactiondata.getStatusCode(), stringbuilder.toString(), treemap);
        }
    }

    private void checkResponse()
    {
        if(!getTransactionState().isComplete())
            TransactionStateUtil.inspectAndInstrumentResponse(getTransactionState(), impl);
    }

    private void error(Exception exception)
    {
        TransactionState transactionstate = getTransactionState();
        TransactionStateUtil.setErrorCodeFromException(transactionstate, exception);
        if(!transactionstate.isComplete())
        {
            TransactionStateUtil.inspectAndInstrumentResponse(transactionstate, impl);
            TransactionData transactiondata = transactionstate.end();
            TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        }
    }

    private TransactionState getTransactionState()
    {
        if(transactionState == null)
        {
            transactionState = new TransactionState();
            TransactionStateUtil.inspectAndInstrument(transactionState, impl);
        }
        return transactionState;
    }

    public void addRequestProperty(String s, String s1)
    {
        impl.addRequestProperty(s, s1);
    }

    public void connect()
        throws IOException
    {
        getTransactionState();
        try
        {
            impl.connect();
            return;
        }
        catch(IOException ioexception)
        {
            error(ioexception);
            throw ioexception;
        }
    }

    public void disconnect()
    {
        if(transactionState != null && !transactionState.isComplete())
            addTransactionAndErrorData(transactionState);
        impl.disconnect();
    }

    public boolean getAllowUserInteraction()
    {
        return impl.getAllowUserInteraction();
    }

    public int getConnectTimeout()
    {
        return impl.getConnectTimeout();
    }

    public Object getContent()
        throws IOException
    {
        getTransactionState();
        Object obj;
        int i;
        try
        {
            obj = impl.getContent();
        }
        catch(IOException ioexception)
        {
            error(ioexception);
            throw ioexception;
        }
        i = impl.getContentLength();
        if(i >= 0)
        {
            TransactionState transactionstate = getTransactionState();
            if(!transactionstate.isComplete())
            {
                transactionstate.setBytesReceived(i);
                addTransactionAndErrorData(transactionstate);
            }
        }
        return obj;
    }

    public Object getContent(Class aclass[])
        throws IOException
    {
        getTransactionState();
        Object obj;
        try
        {
            obj = impl.getContent(aclass);
        }
        catch(IOException ioexception)
        {
            error(ioexception);
            throw ioexception;
        }
        checkResponse();
        return obj;
    }

    public String getContentEncoding()
    {
        getTransactionState();
        String s = impl.getContentEncoding();
        checkResponse();
        return s;
    }

    public int getContentLength()
    {
        getTransactionState();
        int i = impl.getContentLength();
        checkResponse();
        return i;
    }

    public String getContentType()
    {
        getTransactionState();
        String s = impl.getContentType();
        checkResponse();
        return s;
    }

    public long getDate()
    {
        getTransactionState();
        long l = impl.getDate();
        checkResponse();
        return l;
    }

    public boolean getDefaultUseCaches()
    {
        return impl.getDefaultUseCaches();
    }

    public boolean getDoInput()
    {
        return impl.getDoInput();
    }

    public boolean getDoOutput()
    {
        return impl.getDoOutput();
    }

    public InputStream getErrorStream()
    {
        getTransactionState();
        CountingInputStream countinginputstream;
        try
        {
            countinginputstream = new CountingInputStream(impl.getErrorStream(), true);
        }
        catch(Exception exception)
        {
            log.error(exception.toString());
            return impl.getErrorStream();
        }
        return countinginputstream;
    }

    public long getExpiration()
    {
        getTransactionState();
        long l = impl.getExpiration();
        checkResponse();
        return l;
    }

    public String getHeaderField(int i)
    {
        getTransactionState();
        String s = impl.getHeaderField(i);
        checkResponse();
        return s;
    }

    public String getHeaderField(String s)
    {
        getTransactionState();
        String s1 = impl.getHeaderField(s);
        checkResponse();
        return s1;
    }

    public long getHeaderFieldDate(String s, long l)
    {
        getTransactionState();
        long l1 = impl.getHeaderFieldDate(s, l);
        checkResponse();
        return l1;
    }

    public int getHeaderFieldInt(String s, int i)
    {
        getTransactionState();
        int j = impl.getHeaderFieldInt(s, i);
        checkResponse();
        return j;
    }

    public String getHeaderFieldKey(int i)
    {
        getTransactionState();
        String s = impl.getHeaderFieldKey(i);
        checkResponse();
        return s;
    }

    public Map getHeaderFields()
    {
        getTransactionState();
        Map map = impl.getHeaderFields();
        checkResponse();
        return map;
    }

    public long getIfModifiedSince()
    {
        getTransactionState();
        long l = impl.getIfModifiedSince();
        checkResponse();
        return l;
    }

    public InputStream getInputStream()
        throws IOException
    {
        final TransactionState transactionState = getTransactionState();
        CountingInputStream countinginputstream;
        try
        {
            countinginputstream = new CountingInputStream(impl.getInputStream());
            TransactionStateUtil.inspectAndInstrumentResponse(transactionState, impl);
        }
        catch(IOException ioexception)
        {
            error(ioexception);
            throw ioexception;
        }
        countinginputstream.addStreamCompleteListener(new StreamCompleteListener() {

            public void streamComplete(StreamCompleteEvent streamcompleteevent)
            {
                if(!transactionState.isComplete())
                {
                    long l = impl.getContentLength();
                    long l1 = streamcompleteevent.getBytes();
                    if(l >= 0L)
                        l1 = l;
                    transactionState.setBytesReceived(l1);
                    addTransactionAndErrorData(transactionState);
                }
            }

            public void streamError(StreamCompleteEvent streamcompleteevent)
            {
                if(!transactionState.isComplete())
                    transactionState.setBytesReceived(streamcompleteevent.getBytes());
                error(streamcompleteevent.getException());
            }

            final HttpURLConnectionExtension this$0;
            final TransactionState val$transactionState;

            
            {
                this$0 = HttpURLConnectionExtension.this;
                transactionState = transactionstate;
                super();
            }
        }
);
        return countinginputstream;
    }

    public boolean getInstanceFollowRedirects()
    {
        return impl.getInstanceFollowRedirects();
    }

    public long getLastModified()
    {
        getTransactionState();
        long l = impl.getLastModified();
        checkResponse();
        return l;
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        final TransactionState transactionState = getTransactionState();
        CountingOutputStream countingoutputstream;
        try
        {
            countingoutputstream = new CountingOutputStream(impl.getOutputStream());
        }
        catch(IOException ioexception)
        {
            error(ioexception);
            throw ioexception;
        }
        countingoutputstream.addStreamCompleteListener(new StreamCompleteListener() {

            public void streamComplete(StreamCompleteEvent streamcompleteevent)
            {
                if(transactionState.isComplete()) goto _L2; else goto _L1
_L1:
                String s;
                long l;
                s = impl.getRequestProperty("content-length");
                l = streamcompleteevent.getBytes();
                if(s == null)
                    break MISSING_BLOCK_LABEL_41;
                long l1 = Long.parseLong(s);
                l = l1;
_L4:
                transactionState.setBytesSent(l);
                addTransactionAndErrorData(transactionState);
_L2:
                return;
                NumberFormatException numberformatexception;
                numberformatexception;
                if(true) goto _L4; else goto _L3
_L3:
            }

            public void streamError(StreamCompleteEvent streamcompleteevent)
            {
                if(!transactionState.isComplete())
                    transactionState.setBytesSent(streamcompleteevent.getBytes());
                error(streamcompleteevent.getException());
            }

            final HttpURLConnectionExtension this$0;
            final TransactionState val$transactionState;

            
            {
                this$0 = HttpURLConnectionExtension.this;
                transactionState = transactionstate;
                super();
            }
        }
);
        return countingoutputstream;
    }

    public Permission getPermission()
        throws IOException
    {
        return impl.getPermission();
    }

    public int getReadTimeout()
    {
        return impl.getReadTimeout();
    }

    public String getRequestMethod()
    {
        return impl.getRequestMethod();
    }

    public Map getRequestProperties()
    {
        return impl.getRequestProperties();
    }

    public String getRequestProperty(String s)
    {
        return impl.getRequestProperty(s);
    }

    public int getResponseCode()
        throws IOException
    {
        getTransactionState();
        int i;
        try
        {
            i = impl.getResponseCode();
        }
        catch(IOException ioexception)
        {
            error(ioexception);
            throw ioexception;
        }
        checkResponse();
        return i;
    }

    public String getResponseMessage()
        throws IOException
    {
        getTransactionState();
        String s;
        try
        {
            s = impl.getResponseMessage();
        }
        catch(IOException ioexception)
        {
            error(ioexception);
            throw ioexception;
        }
        checkResponse();
        return s;
    }

    public URL getURL()
    {
        return impl.getURL();
    }

    public boolean getUseCaches()
    {
        return impl.getUseCaches();
    }

    public void setAllowUserInteraction(boolean flag)
    {
        impl.setAllowUserInteraction(flag);
    }

    public void setChunkedStreamingMode(int i)
    {
        impl.setChunkedStreamingMode(i);
    }

    public void setConnectTimeout(int i)
    {
        impl.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean flag)
    {
        impl.setDefaultUseCaches(flag);
    }

    public void setDoInput(boolean flag)
    {
        impl.setDoInput(flag);
    }

    public void setDoOutput(boolean flag)
    {
        impl.setDoOutput(flag);
    }

    public void setFixedLengthStreamingMode(int i)
    {
        impl.setFixedLengthStreamingMode(i);
    }

    public void setIfModifiedSince(long l)
    {
        impl.setIfModifiedSince(l);
    }

    public void setInstanceFollowRedirects(boolean flag)
    {
        impl.setInstanceFollowRedirects(flag);
    }

    public void setReadTimeout(int i)
    {
        impl.setReadTimeout(i);
    }

    public void setRequestMethod(String s)
        throws ProtocolException
    {
        getTransactionState();
        try
        {
            impl.setRequestMethod(s);
            return;
        }
        catch(ProtocolException protocolexception)
        {
            error(protocolexception);
            throw protocolexception;
        }
    }

    public void setRequestProperty(String s, String s1)
    {
        impl.setRequestProperty(s, s1);
    }

    public void setUseCaches(boolean flag)
    {
        impl.setUseCaches(flag);
    }

    public String toString()
    {
        return impl.toString();
    }

    public boolean usingProxy()
    {
        return impl.usingProxy();
    }

    private static final AgentLog log = AgentLogManager.getAgentLog();
    private HttpURLConnection impl;
    private TransactionState transactionState;




}
