// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.*;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.httpclient.ContentBufferingResponseEntityImpl;
import com.newrelic.agent.android.instrumentation.httpclient.HttpRequestEntityImpl;
import com.newrelic.agent.android.instrumentation.httpclient.HttpResponseEntityImpl;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.IOException;
import java.net.*;
import java.text.MessageFormat;
import java.util.*;
import javax.net.ssl.SSLException;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            TransactionState

public class TransactionStateUtil
{

    public TransactionStateUtil()
    {
    }

    private static void addCrossProcessIdHeader(HttpRequest httprequest)
    {
        String s = Agent.getCrossProcessId();
        if(s != null)
        {
            TraceMachine.setCurrentTraceParam("cross_process_data", s);
            httprequest.setHeader("X-NewRelic-ID", s);
        }
    }

    private static void addTransactionAndErrorData(TransactionState transactionstate, HttpResponse httpresponse)
    {
        TransactionData transactiondata = transactionstate.end();
        if(transactiondata != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        if((long)transactionstate.getStatusCode() < 400L) goto _L1; else goto _L3
_L3:
        StringBuilder stringbuilder = new StringBuilder();
        java.io.InputStream inputstream;
        if(!(httpresponse.getEntity() instanceof HttpRequestEntityImpl))
            httpresponse.setEntity(new ContentBufferingResponseEntityImpl(httpresponse.getEntity()));
        inputstream = httpresponse.getEntity().getContent();
        if(!(inputstream instanceof CountingInputStream)) goto _L5; else goto _L4
_L4:
        stringbuilder.append(((CountingInputStream)inputstream).getBufferAsString());
_L7:
        Header aheader[] = httpresponse.getHeaders("Content-Type");
        String s = null;
        if(aheader != null)
        {
            int i = aheader.length;
            s = null;
            if(i > 0)
            {
                boolean flag = "".equals(aheader[0].getValue());
                s = null;
                if(!flag)
                    s = aheader[0].getValue();
            }
        }
        TreeMap treemap = new TreeMap();
        if(s != null && s.length() > 0)
            treemap.put("content_type", s);
        treemap.put("content_length", (new StringBuilder()).append(transactionstate.getBytesReceived()).append("").toString());
        Measurements.addHttpError(transactiondata.getUrl(), transactiondata.getStatusCode(), stringbuilder.toString(), treemap);
        return;
_L5:
        try
        {
            log.error("Unable to wrap content stream for entity");
        }
        catch(IllegalStateException illegalstateexception)
        {
            log.error(illegalstateexception.toString());
        }
        catch(IOException ioexception)
        {
            log.error(ioexception.toString());
        }
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static HttpRequest inspectAndInstrument(TransactionState transactionstate, HttpHost httphost, HttpRequest httprequest)
    {
        addCrossProcessIdHeader(httprequest);
        RequestLine requestline = httprequest.getRequestLine();
        if(requestline != null)
        {
            String s = requestline.getUri();
            boolean flag;
            if(s != null && s.length() >= 10 && s.substring(0, 10).indexOf("://") >= 0)
                flag = true;
            else
                flag = false;
            if(!flag && s != null && httphost != null)
            {
                String s1 = httphost.toURI().toString();
                StringBuilder stringbuilder = (new StringBuilder()).append(s1);
                Exception exception;
                AgentLog agentlog;
                Object aobj[];
                String s2;
                if(s1.endsWith("/") || s.startsWith("/"))
                    s2 = "";
                else
                    s2 = "/";
                transactionstate.setUrl(stringbuilder.append(s2).append(s).toString());
            } else
            if(flag)
                transactionstate.setUrl(s);
        }
        if(transactionstate.getUrl() == null)
        {
            try
            {
                throw new Exception("TransactionData constructor was not provided with a valid URL or host");
            }
            // Misplaced declaration of an exception variable
            catch(Exception exception)
            {
                agentlog = AgentLogManager.getAgentLog();
                aobj = new Object[3];
                aobj[0] = httprequest.getClass().getCanonicalName();
                aobj[1] = httphost;
                aobj[2] = requestline;
                agentlog.error(MessageFormat.format("TransactionStateUtil.inspectAndInstrument(...) for {0} could not determine request URL [host={1}, requestLine={2}]", aobj), exception);
                return httprequest;
            }
        } else
        {
            transactionstate.setCarrier(Agent.getActiveNetworkCarrier());
            wrapRequestEntity(transactionstate, httprequest);
            return httprequest;
        }
    }

    public static HttpResponse inspectAndInstrument(TransactionState transactionstate, HttpResponse httpresponse)
    {
        transactionstate.setStatusCode(httpresponse.getStatusLine().getStatusCode());
        Header aheader[] = httpresponse.getHeaders("X-NewRelic-App-Data");
        if(aheader != null && aheader.length > 0 && !"".equals(aheader[0].getValue()))
            transactionstate.setAppData(aheader[0].getValue());
        Header aheader1[] = httpresponse.getHeaders("Content-Length");
        if(aheader1 != null && aheader1.length > 0)
        {
            try
            {
                transactionstate.setBytesReceived(Long.parseLong(aheader1[0].getValue()));
                addTransactionAndErrorData(transactionstate, httpresponse);
            }
            catch(NumberFormatException numberformatexception)
            {
                log.warning((new StringBuilder()).append("Failed to parse content length: ").append(numberformatexception.toString()).toString());
                return httpresponse;
            }
            return httpresponse;
        }
        if(httpresponse.getEntity() != null)
        {
            httpresponse.setEntity(new HttpResponseEntityImpl(httpresponse.getEntity(), transactionstate, -1L));
            return httpresponse;
        } else
        {
            transactionstate.setBytesReceived(0L);
            addTransactionAndErrorData(transactionstate, null);
            return httpresponse;
        }
    }

    public static HttpUriRequest inspectAndInstrument(TransactionState transactionstate, HttpUriRequest httpurirequest)
    {
        addCrossProcessIdHeader(httpurirequest);
        transactionstate.setUrl(httpurirequest.getURI().toString());
        transactionstate.setCarrier(Agent.getActiveNetworkCarrier());
        wrapRequestEntity(transactionstate, httpurirequest);
        return httpurirequest;
    }

    public static void inspectAndInstrument(TransactionState transactionstate, HttpURLConnection httpurlconnection)
    {
        transactionstate.setUrl(httpurlconnection.getURL().toString());
        transactionstate.setCarrier(Agent.getActiveNetworkCarrier());
    }

    public static void inspectAndInstrumentResponse(TransactionState transactionstate, HttpURLConnection httpurlconnection)
    {
        String s = httpurlconnection.getHeaderField("X-NewRelic-App-Data");
        if(s != null && !"".equals(s))
            transactionstate.setAppData(s);
        int i = httpurlconnection.getContentLength();
        if(i >= 0)
            transactionstate.setBytesReceived(i);
        int k = httpurlconnection.getResponseCode();
        int j = k;
_L2:
        transactionstate.setStatusCode(j);
        return;
        IOException ioexception;
        ioexception;
        log.error("Failed to retrieve response code due to an I/O exception", ioexception);
        j = 0;
        continue; /* Loop/switch isn't completed */
        NullPointerException nullpointerexception;
        nullpointerexception;
        log.error("Failed to retrieve response code due to underlying (Harmony?) NPE", nullpointerexception);
        j = 0;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void setCrossProcessHeader(HttpURLConnection httpurlconnection)
    {
        String s = Agent.getCrossProcessId();
        if(s != null)
            httpurlconnection.setRequestProperty("X-NewRelic-ID".toLowerCase(Locale.ENGLISH), s);
    }

    public static void setErrorCodeFromException(TransactionState transactionstate, Exception exception)
    {
        if(exception instanceof UnknownHostException)
        {
            transactionstate.setErrorCode(-1006);
            return;
        }
        if((exception instanceof SocketTimeoutException) || (exception instanceof ConnectTimeoutException))
        {
            transactionstate.setErrorCode(-1001);
            return;
        }
        if(exception instanceof ConnectException)
        {
            transactionstate.setErrorCode(-1004);
            return;
        }
        if(exception instanceof MalformedURLException)
        {
            transactionstate.setErrorCode(-1000);
            return;
        }
        if(exception instanceof SSLException)
        {
            transactionstate.setErrorCode(-1200);
            return;
        }
        if(exception instanceof HttpResponseException)
        {
            transactionstate.setStatusCode(((HttpResponseException)exception).getStatusCode());
            return;
        }
        if(exception instanceof ClientProtocolException)
        {
            transactionstate.setErrorCode(-1011);
            return;
        } else
        {
            transactionstate.setErrorCode(-1);
            return;
        }
    }

    private static void wrapRequestEntity(TransactionState transactionstate, HttpRequest httprequest)
    {
        if(httprequest instanceof HttpEntityEnclosingRequest)
        {
            HttpEntityEnclosingRequest httpentityenclosingrequest = (HttpEntityEnclosingRequest)httprequest;
            if(httpentityenclosingrequest.getEntity() != null)
                httpentityenclosingrequest.setEntity(new HttpRequestEntityImpl(httpentityenclosingrequest.getEntity(), transactionstate));
        }
    }

    public static final String APP_DATA_HEADER = "X-NewRelic-App-Data";
    private static final String CONTENT_LENGTH_HEADER = "Content-Length";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String CROSS_PROCESS_ID_HEADER = "X-NewRelic-ID";
    public static final int NSURLErrorBadServerResponse = -1011;
    public static final int NSURLErrorBadURL = -1000;
    public static final int NSURLErrorCannotConnectToHost = -1004;
    public static final int NSURLErrorDNSLookupFailed = -1006;
    public static final int NSURLErrorSecureConnectionFailed = -1200;
    public static final int NSURLErrorTimedOut = -1001;
    public static final int NSURLErrorUnknown = -1;
    private static final AgentLog log = AgentLogManager.getAgentLog();

}
