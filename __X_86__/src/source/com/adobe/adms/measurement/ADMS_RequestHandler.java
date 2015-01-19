// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.measurement;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.*;
import javax.net.ssl.*;

// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_Measurement

final class ADMS_RequestHandler
{

    ADMS_RequestHandler()
    {
    }

    private static HttpURLConnection requestConnect(String s)
    {
        URL url;
        HttpsURLConnection httpsurlconnection;
        url = new URL(s);
        if(s.indexOf("https://") < 0)
            break MISSING_BLOCK_LABEL_45;
        httpsurlconnection = (HttpsURLConnection)HttpInstrumentation.openConnection(url.openConnection());
        httpsurlconnection.setHostnameVerifier(new HostnameVerifier() {

            public boolean verify(String s1, SSLSession sslsession)
            {
                return true;
            }

        }
);
        return httpsurlconnection;
        HttpURLConnection httpurlconnection;
        try
        {
            httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection(url.openConnection());
        }
        catch(Exception exception)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("Exception opening URL : ").append(exception.getMessage()).toString());
            return null;
        }
        return httpurlconnection;
    }

    protected static boolean sendRequest(String s, Hashtable hashtable)
    {
        if(s == null)
            return false;
        HttpURLConnection httpurlconnection = requestConnect(s);
        boolean flag = false;
        if(httpurlconnection == null) goto _L2; else goto _L1
_L1:
        httpurlconnection.setConnectTimeout(5000);
        httpurlconnection.setReadTimeout(5000);
        if(hashtable == null) goto _L4; else goto _L3
_L3:
        Iterator iterator = hashtable.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s1 = (String)entry.getValue();
            if(s1.trim().length() > 0)
                httpurlconnection.setRequestProperty((String)entry.getKey(), s1);
        } while(true);
          goto _L4
        SocketTimeoutException sockettimeoutexception;
        sockettimeoutexception;
        flag = false;
        ADMS_Measurement.sharedInstance().debugLog("Timed out sending request.");
_L2:
        return flag;
_L4:
        ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("Request Sent : ").append(s).toString());
        httpurlconnection.getResponseCode();
        httpurlconnection.getInputStream().close();
        httpurlconnection.disconnect();
        flag = true;
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("IOException while sending request, retrying. Exception: ").append(ioexception.getMessage()).toString());
        flag = false;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        flag = true;
        ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("Exception while attempting to send hit : ").append(exception.getMessage()).toString());
        if(true) goto _L2; else goto _L5
_L5:
    }
}
