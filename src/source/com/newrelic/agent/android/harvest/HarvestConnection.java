// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.Harvestable;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.stats.TicToc;
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;
import javax.net.ssl.SSLException;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestResponse, ConnectInformation

public class HarvestConnection
{

    public HarvestConnection()
    {
        int i = (int)TimeUnit.MILLISECONDS.convert(20L, TimeUnit.SECONDS);
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, i);
        HttpConnectionParams.setSoTimeout(basichttpparams, i);
        HttpConnectionParams.setTcpNoDelay(basichttpparams, true);
        HttpConnectionParams.setSocketBufferSize(basichttpparams, 8192);
        collectorClient = new DefaultHttpClient(basichttpparams);
    }

    private byte[] deflate(String s)
    {
        Deflater deflater = new Deflater();
        deflater.setInput(s.getBytes());
        deflater.finish();
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[8192];
        int i;
        for(; !deflater.finished(); bytearrayoutputstream.write(abyte0, 0, i))
        {
            i = deflater.deflate(abyte0);
            if(i <= 0)
                log.error("HTTP request contains an incomplete payload");
        }

        deflater.end();
        return bytearrayoutputstream.toByteArray();
    }

    private int exceptionToErrorCode(Exception exception)
    {
        char c = '\uFC17';
        if(exception instanceof ClientProtocolException)
        {
            c = '\uFC0D';
        } else
        {
            if(exception instanceof UnknownHostException)
                return -1006;
            if(!(exception instanceof SocketTimeoutException) && !(exception instanceof ConnectTimeoutException))
            {
                if(exception instanceof ConnectException)
                    return -1004;
                if(exception instanceof MalformedURLException)
                    return -1000;
                return !(exception instanceof SSLException) ? -1 : -1200;
            }
        }
        return c;
    }

    private String getCollectorConnectUri()
    {
        return getCollectorUri("/mobile/v2/connect");
    }

    private String getCollectorDataUri()
    {
        return getCollectorUri("/mobile/v2/data");
    }

    private String getCollectorUri(String s)
    {
        String s1;
        if(useSsl)
            s1 = "https://";
        else
            s1 = "http://";
        return (new StringBuilder()).append(s1).append(collectorHost).append(s).toString();
    }

    public static String readResponse(HttpResponse httpresponse)
        throws IOException
    {
        char ac[];
        StringBuilder stringbuilder;
        InputStream inputstream;
        ac = new char[8192];
        stringbuilder = new StringBuilder();
        inputstream = httpresponse.getEntity().getContent();
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
_L1:
        int i = bufferedreader.read(ac);
        if(i < 0)
        {
            inputstream.close();
            return stringbuilder.toString();
        }
        stringbuilder.append(ac, 0, i);
          goto _L1
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
    }

    private void recordCollectorError(Exception exception)
    {
        StatsEngine.get().inc((new StringBuilder()).append("Supportability/AgentHealth/Collector/ResponseErrorCodes/").append(exceptionToErrorCode(exception)).toString());
    }

    public HttpPost createConnectPost(String s)
    {
        return createPost(getCollectorConnectUri(), s);
    }

    public HttpPost createDataPost(String s)
    {
        return createPost(getCollectorDataUri(), s);
    }

    public HttpPost createPost(String s, String s1)
    {
        String s2;
        HttpPost httppost;
        if(s1.length() <= 512 || DISABLE_COMPRESSION_FOR_DEBUGGING.booleanValue())
            s2 = "identity";
        else
            s2 = "deflate";
        httppost = new HttpPost(s);
        httppost.addHeader("Content-Type", "application/json");
        httppost.addHeader("Content-Encoding", s2);
        httppost.addHeader("User-Agent", System.getProperty("http.agent"));
        if(applicationToken == null)
        {
            log.error("Cannot create POST without an Application Token.");
            return null;
        }
        httppost.addHeader("X-App-License-Key", applicationToken);
        if(serverTimestamp != 0L)
            httppost.addHeader("X-NewRelic-Connect-Time", Long.valueOf(serverTimestamp).toString());
        if("deflate".equals(s2))
        {
            httppost.setEntity(new ByteArrayEntity(deflate(s1)));
            return httppost;
        }
        try
        {
            httppost.setEntity(new StringEntity(s1, "utf-8"));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            log.error("UTF-8 is unsupported");
            throw new IllegalArgumentException(unsupportedencodingexception);
        }
        return httppost;
    }

    public ConnectInformation getConnectInformation()
    {
        return connectInformation;
    }

    public HarvestResponse send(HttpPost httppost)
    {
        HarvestResponse harvestresponse = new HarvestResponse();
        HttpResponse httpresponse;
        try
        {
            TicToc tictoc = new TicToc();
            tictoc.tic();
            httpresponse = collectorClient.execute(httppost);
            harvestresponse.setResponseTime(tictoc.toc());
        }
        catch(Exception exception)
        {
            log.error((new StringBuilder()).append("Failed to send POST to collector: ").append(exception.getMessage()).toString());
            recordCollectorError(exception);
            return null;
        }
        harvestresponse.setStatusCode(httpresponse.getStatusLine().getStatusCode());
        try
        {
            harvestresponse.setResponseBody(readResponse(httpresponse));
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
            log.error((new StringBuilder()).append("Failed to retrieve collector response: ").append(ioexception.getMessage()).toString());
            return harvestresponse;
        }
        return harvestresponse;
    }

    public HarvestResponse sendConnect()
    {
        if(connectInformation == null)
            throw new IllegalArgumentException();
        HttpPost httppost = createConnectPost(connectInformation.toJsonString());
        if(httppost == null)
        {
            log.error("Failed to create connect POST");
            return null;
        } else
        {
            TicToc tictoc = new TicToc();
            tictoc.tic();
            HarvestResponse harvestresponse = send(httppost);
            StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Connect", tictoc.toc());
            return harvestresponse;
        }
    }

    public HarvestResponse sendData(Harvestable harvestable)
    {
        if(harvestable == null)
            throw new IllegalArgumentException();
        HttpPost httppost = createDataPost(harvestable.toJsonString());
        if(httppost == null)
        {
            log.error("Failed to create data POST");
            return null;
        } else
        {
            return send(httppost);
        }
    }

    public void setApplicationToken(String s)
    {
        applicationToken = s;
    }

    public void setCollectorHost(String s)
    {
        collectorHost = s;
    }

    public void setConnectInformation(ConnectInformation connectinformation)
    {
        connectInformation = connectinformation;
    }

    public void setServerTimestamp(long l)
    {
        log.debug((new StringBuilder()).append("Setting server timestamp: ").append(l).toString());
        serverTimestamp = l;
    }

    public void useSsl(boolean flag)
    {
        useSsl = flag;
    }

    private static final String APPLICATION_TOKEN_HEADER = "X-App-License-Key";
    private static final String COLLECTOR_CONNECT_URI = "/mobile/v2/connect";
    private static final String COLLECTOR_DATA_URI = "/mobile/v2/data";
    private static final String CONNECT_TIME_HEADER = "X-NewRelic-Connect-Time";
    private static final Boolean DISABLE_COMPRESSION_FOR_DEBUGGING = Boolean.valueOf(false);
    public static final int NSURLErrorBadServerResponse = -1011;
    public static final int NSURLErrorBadURL = -1000;
    public static final int NSURLErrorCannotConnectToHost = -1004;
    public static final int NSURLErrorDNSLookupFailed = -1006;
    public static final int NSURLErrorSecureConnectionFailed = -1200;
    public static final int NSURLErrorTimedOut = -1001;
    public static final int NSURLErrorUnknown = -1;
    private String applicationToken;
    private final HttpClient collectorClient;
    private String collectorHost;
    private ConnectInformation connectInformation;
    private final AgentLog log = AgentLogManager.getAgentLog();
    private long serverTimestamp;
    private boolean useSsl;

}
