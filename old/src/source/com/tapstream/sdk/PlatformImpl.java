// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadFactory;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

// Referenced classes of package com.tapstream.sdk:
//            Platform, Logging, WorkerThread, Response

class PlatformImpl
    implements Platform
{

    public PlatformImpl(Context context1)
    {
        context = context1;
    }

    public String getAndroidId()
    {
        return android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public String getAppName()
    {
        PackageManager packagemanager = context.getPackageManager();
        String s;
        try
        {
            s = packagemanager.getApplicationLabel(packagemanager.getApplicationInfo(context.getPackageName(), 0)).toString();
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return context.getPackageName();
        }
        return s;
    }

    public String getDeviceId()
    {
        String s;
        try
        {
            s = ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
        }
        catch(SecurityException securityexception)
        {
            Logging.log(6, "Tapstream Error: Failed to get device id - you need to add the READ_PHONE_STATE permission to your manifest.", new Object[0]);
            return null;
        }
        return s;
    }

    public String getLocale()
    {
        return Locale.getDefault().toString();
    }

    public String getManufacturer()
    {
        String s;
        try
        {
            s = Build.MANUFACTURER;
        }
        catch(Exception exception)
        {
            return null;
        }
        return s;
    }

    public String getModel()
    {
        return Build.MODEL;
    }

    public String getOs()
    {
        Locale locale = Locale.US;
        Object aobj[] = new Object[1];
        aobj[0] = android.os.Build.VERSION.RELEASE;
        return String.format(locale, "Android %s", aobj);
    }

    public String getPackageName()
    {
        return context.getPackageName();
    }

    public String getResolution()
    {
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        Locale locale = Locale.US;
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(displaymetrics.widthPixels);
        aobj[1] = Integer.valueOf(displaymetrics.heightPixels);
        return String.format(locale, "%dx%d", aobj);
    }

    public String getWifiMac()
    {
        String s;
        try
        {
            s = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        }
        catch(SecurityException securityexception)
        {
            Logging.log(6, "Tapstream Error: Failed to get wifi mac address - you need to add the ACCESS_WIFI_STATE permission to your manifest.", new Object[0]);
            return null;
        }
        return s;
    }

    public Set loadFiredEvents()
    {
        return new HashSet(context.getApplicationContext().getSharedPreferences("TapstreamSDKFiredEvents", 0).getAll().keySet());
    }

    public String loadUuid()
    {
        SharedPreferences sharedpreferences = context.getApplicationContext().getSharedPreferences("TapstreamSDKUUID", 0);
        String s = sharedpreferences.getString("uuid", null);
        if(s == null)
        {
            s = UUID.randomUUID().toString();
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("uuid", s);
            editor.commit();
        }
        return s;
    }

    public ThreadFactory makeWorkerThreadFactory()
    {
        return new WorkerThread.Factory();
    }

    public Response request(String s, String s1, String s2)
    {
        Object obj;
        DefaultHttpClient defaulthttpclient;
        HttpResponse httpresponse1;
        StatusLine statusline;
        InputStream inputstream;
        StringBuilder stringbuilder;
        WorkerThread workerthread = (WorkerThread)Thread.currentThread();
        if(s2 == "POST")
        {
            obj = new HttpPost(s);
            if(s1 != null)
            {
                Exception exception1;
                BufferedReader bufferedreader;
                Exception exception2;
                String s3;
                HttpResponse httpresponse2;
                StringEntity stringentity;
                try
                {
                    stringentity = new StringEntity(s1);
                }
                catch(UnsupportedEncodingException unsupportedencodingexception)
                {
                    return new Response(-1, unsupportedencodingexception.toString(), null);
                }
                stringentity.setContentType("application/x-www-form-urlencoded");
                ((HttpPost)obj).setEntity(stringentity);
            }
        } else
        {
            obj = new HttpGet(s);
        }
        ((HttpRequestBase) (obj)).getParams().setBooleanParameter("http.protocol.expect-continue", false);
        defaulthttpclient = workerthread.client;
        if(defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        httpresponse2 = defaulthttpclient.execute(((org.apache.http.client.methods.HttpUriRequest) (obj)));
        httpresponse1 = httpresponse2;
_L5:
        statusline = httpresponse1.getStatusLine();
        try
        {
            inputstream = httpresponse1.getEntity().getContent();
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception1)
        {
            return new Response(-1, exception1.toString(), null);
        }
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        stringbuilder = new StringBuilder();
_L3:
        s3 = bufferedreader.readLine();
        if(s3 == null)
            break; /* Loop/switch isn't completed */
        stringbuilder.append(s3);
          goto _L3
        exception2;
        inputstream.close();
        throw exception2;
_L2:
        HttpResponse httpresponse;
        try
        {
            httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, ((org.apache.http.client.methods.HttpUriRequest) (obj)));
        }
        catch(Exception exception)
        {
            return new Response(-1, exception.toString(), null);
        }
        httpresponse1 = httpresponse;
        if(true) goto _L5; else goto _L4
_L4:
        String s4 = stringbuilder.toString();
        inputstream.close();
        if(statusline.getStatusCode() == 200)
            return new Response(200, null, s4);
        else
            return new Response(statusline.getStatusCode(), statusline.getReasonPhrase(), null);
    }

    public void saveFiredEvents(Set set)
    {
        android.content.SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences("TapstreamSDKFiredEvents", 0).edit();
        editor.clear();
        for(Iterator iterator = set.iterator(); iterator.hasNext(); editor.putString((String)iterator.next(), ""));
        editor.commit();
    }

    private static final String FIRED_EVENTS_KEY = "TapstreamSDKFiredEvents";
    private static final String UUID_KEY = "TapstreamSDKUUID";
    private Context context;
}
