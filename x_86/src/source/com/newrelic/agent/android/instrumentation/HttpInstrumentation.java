// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.httpclient.ResponseHandlerImpl;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            TransactionStateUtil, TransactionState, HttpsURLConnectionExtension, HttpURLConnectionExtension

public final class HttpInstrumentation
{

    private HttpInstrumentation()
    {
    }

    private static HttpRequest _(HttpHost httphost, HttpRequest httprequest, TransactionState transactionstate)
    {
        return TransactionStateUtil.inspectAndInstrument(transactionstate, httphost, httprequest);
    }

    private static HttpResponse _(HttpResponse httpresponse, TransactionState transactionstate)
    {
        return TransactionStateUtil.inspectAndInstrument(transactionstate, httpresponse);
    }

    private static ResponseHandler _(ResponseHandler responsehandler, TransactionState transactionstate)
    {
        return ResponseHandlerImpl.wrap(responsehandler, transactionstate);
    }

    private static HttpUriRequest _(HttpUriRequest httpurirequest, TransactionState transactionstate)
    {
        return TransactionStateUtil.inspectAndInstrument(transactionstate, httpurirequest);
    }

    public static Object execute(HttpClient httpclient, HttpHost httphost, HttpRequest httprequest, ResponseHandler responsehandler)
        throws IOException, ClientProtocolException
    {
        TransactionState transactionstate = new TransactionState();
        Object obj;
        try
        {
            obj = httpclient.execute(httphost, _(httphost, httprequest, transactionstate), _(responsehandler, transactionstate));
        }
        catch(ClientProtocolException clientprotocolexception)
        {
            httpClientError(transactionstate, clientprotocolexception);
            throw clientprotocolexception;
        }
        catch(IOException ioexception)
        {
            httpClientError(transactionstate, ioexception);
            throw ioexception;
        }
        return obj;
    }

    public static Object execute(HttpClient httpclient, HttpHost httphost, HttpRequest httprequest, ResponseHandler responsehandler, HttpContext httpcontext)
        throws IOException, ClientProtocolException
    {
        TransactionState transactionstate = new TransactionState();
        Object obj;
        try
        {
            obj = httpclient.execute(httphost, _(httphost, httprequest, transactionstate), _(responsehandler, transactionstate), httpcontext);
        }
        catch(ClientProtocolException clientprotocolexception)
        {
            httpClientError(transactionstate, clientprotocolexception);
            throw clientprotocolexception;
        }
        catch(IOException ioexception)
        {
            httpClientError(transactionstate, ioexception);
            throw ioexception;
        }
        return obj;
    }

    public static Object execute(HttpClient httpclient, HttpUriRequest httpurirequest, ResponseHandler responsehandler)
        throws IOException, ClientProtocolException
    {
        TransactionState transactionstate = new TransactionState();
        Object obj;
        try
        {
            obj = httpclient.execute(_(httpurirequest, transactionstate), _(responsehandler, transactionstate));
        }
        catch(ClientProtocolException clientprotocolexception)
        {
            httpClientError(transactionstate, clientprotocolexception);
            throw clientprotocolexception;
        }
        catch(IOException ioexception)
        {
            httpClientError(transactionstate, ioexception);
            throw ioexception;
        }
        return obj;
    }

    public static Object execute(HttpClient httpclient, HttpUriRequest httpurirequest, ResponseHandler responsehandler, HttpContext httpcontext)
        throws IOException, ClientProtocolException
    {
        TransactionState transactionstate = new TransactionState();
        Object obj;
        try
        {
            obj = httpclient.execute(_(httpurirequest, transactionstate), _(responsehandler, transactionstate), httpcontext);
        }
        catch(ClientProtocolException clientprotocolexception)
        {
            httpClientError(transactionstate, clientprotocolexception);
            throw clientprotocolexception;
        }
        catch(IOException ioexception)
        {
            httpClientError(transactionstate, ioexception);
            throw ioexception;
        }
        return obj;
    }

    public static HttpResponse execute(HttpClient httpclient, HttpHost httphost, HttpRequest httprequest)
        throws IOException
    {
        TransactionState transactionstate = new TransactionState();
        HttpResponse httpresponse;
        try
        {
            httpresponse = _(httpclient.execute(httphost, _(httphost, httprequest, transactionstate)), transactionstate);
        }
        catch(IOException ioexception)
        {
            httpClientError(transactionstate, ioexception);
            throw ioexception;
        }
        return httpresponse;
    }

    public static HttpResponse execute(HttpClient httpclient, HttpHost httphost, HttpRequest httprequest, HttpContext httpcontext)
        throws IOException
    {
        TransactionState transactionstate = new TransactionState();
        HttpResponse httpresponse;
        try
        {
            httpresponse = _(httpclient.execute(httphost, _(httphost, httprequest, transactionstate), httpcontext), transactionstate);
        }
        catch(IOException ioexception)
        {
            httpClientError(transactionstate, ioexception);
            throw ioexception;
        }
        return httpresponse;
    }

    public static HttpResponse execute(HttpClient httpclient, HttpUriRequest httpurirequest)
        throws IOException
    {
        TransactionState transactionstate = new TransactionState();
        HttpResponse httpresponse;
        try
        {
            httpresponse = _(httpclient.execute(_(httpurirequest, transactionstate)), transactionstate);
        }
        catch(IOException ioexception)
        {
            httpClientError(transactionstate, ioexception);
            throw ioexception;
        }
        return httpresponse;
    }

    public static HttpResponse execute(HttpClient httpclient, HttpUriRequest httpurirequest, HttpContext httpcontext)
        throws IOException
    {
        TransactionState transactionstate = new TransactionState();
        HttpResponse httpresponse;
        try
        {
            httpresponse = _(httpclient.execute(_(httpurirequest, transactionstate), httpcontext), transactionstate);
        }
        catch(IOException ioexception)
        {
            httpClientError(transactionstate, ioexception);
            throw ioexception;
        }
        return httpresponse;
    }

    private static void httpClientError(TransactionState transactionstate, Exception exception)
    {
        if(!transactionstate.isComplete())
        {
            TransactionStateUtil.setErrorCodeFromException(transactionstate, exception);
            TransactionData transactiondata = transactionstate.end();
            TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        }
    }

    public static URLConnection openConnection(URLConnection urlconnection)
    {
        if(urlconnection instanceof HttpsURLConnection)
            urlconnection = new HttpsURLConnectionExtension((HttpsURLConnection)urlconnection);
        else
        if(urlconnection instanceof HttpURLConnection)
            return new HttpURLConnectionExtension((HttpURLConnection)urlconnection);
        return urlconnection;
    }

    public static URLConnection openConnectionWithProxy(URLConnection urlconnection)
    {
        if(urlconnection instanceof HttpsURLConnection)
            urlconnection = new HttpsURLConnectionExtension((HttpsURLConnection)urlconnection);
        else
        if(urlconnection instanceof HttpURLConnection)
            return new HttpURLConnectionExtension((HttpURLConnection)urlconnection);
        return urlconnection;
    }
}
