// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.testandtarget;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.adobe.adms.testandtarget:
//            Mbox

public final class MboxFactory
{

    public MboxFactory(Context context, String s)
    {
        mboxList = new ConcurrentHashMap();
        parentContext = context;
        clientCode = s;
        disableDuration = 0xdbba0L;
        factoryEnabled = true;
        mboxServerURL = (new StringBuilder()).append("http://").append(s).append(".tt.omtrdc.net").toString();
        preferences = context.getSharedPreferences((new StringBuilder()).append("TestAndTarget").append(s).toString(), 0);
        loadCookie("mboxPC");
        loadCookie("mboxSession");
    }

    private void deleteCookie(String s)
    {
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.remove((new StringBuilder()).append(s).append("_Value").toString());
        editor.remove((new StringBuilder()).append(s).append("_Expires").toString());
        editor.commit();
    }

    private void loadCookie(String s)
    {
label0:
        {
            String s1;
label1:
            {
                long l = System.currentTimeMillis();
                long l1 = preferences.getLong((new StringBuilder()).append(s).append("_Expires").toString(), 0L);
                if(l1 > 0L)
                {
                    if(l1 <= l)
                        break label0;
                    s1 = preferences.getString((new StringBuilder()).append(s).append("_Value").toString(), "");
                    if(cookies != null)
                        break label1;
                    cookies = (new StringBuilder()).append(s).append("=").append(s1).toString();
                }
                return;
            }
            cookies = (new StringBuilder()).append(cookies).append("; ").append(s).append("=").append(s1).toString();
            return;
        }
        deleteCookie(s);
    }

    private void saveCookies(CookieStore cookiestore)
    {
        Iterator iterator = cookiestore.getCookies().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Cookie cookie = (Cookie)iterator.next();
            String s = cookie.getName();
            if(s.equals("mboxSession") || s.equals("mboxPC"))
                storeCookie(s, cookie);
        } while(true);
        loadCookie("mboxPC");
        loadCookie("mboxSession");
    }

    private void storeCookie(String s, Cookie cookie)
    {
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString((new StringBuilder()).append(s).append("_Value").toString(), cookie.getValue());
        editor.putLong((new StringBuilder()).append(s).append("_Expires").toString(), cookie.getExpiryDate().getTime());
        editor.commit();
    }

    public void clearCookies()
    {
        this;
        JVM INSTR monitorenter ;
        cookies = null;
        deleteCookie("mboxPC");
        deleteCookie("mboxSession");
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Mbox create(String s)
    {
        Mbox mbox = new Mbox(this, s);
        mboxList.put(s, mbox);
        return mbox;
    }

    public void disable()
    {
        this;
        JVM INSTR monitorenter ;
        factoryEnabled = false;
        long l = System.currentTimeMillis();
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("DisableTime", l);
        editor.commit();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected String encode(String s)
    {
        String s1;
        try
        {
            s1 = URLEncoder.encode(s, "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            Log.e("MboxFactory", unsupportedencodingexception.toString());
            return s;
        }
        return s1;
    }

    protected String getBaseRequestURL()
    {
        return (new StringBuilder()).append(mboxServerURL).append("/m2/").append(clientCode).append("/ubox/raw?").toString();
    }

    protected String getCookies()
    {
        return cookies;
    }

    protected String getMboxResponse(Mbox mbox, String s)
    {
        this;
        JVM INSTR monitorenter ;
        DefaultHttpClient defaulthttpclient;
        HttpGet httpget;
        defaulthttpclient = new DefaultHttpClient();
        httpget = new HttpGet(s);
        httpget.setHeader("User-Agent", "Apache-HttpClient (Test&Target Android SDK)");
        if(cookies != null)
            httpget.setHeader("Cookie", cookies);
        if(defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        HttpResponse httpresponse = defaulthttpclient.execute(httpget);
_L6:
        int i;
        String s2;
        i = httpresponse.getStatusLine().getStatusCode();
        s2 = httpresponse.getFirstHeader("Content-Type").getValue();
        saveCookies(defaulthttpclient.getCookieStore());
        if(i != 200) goto _L4; else goto _L3
_L3:
        boolean flag = s2.equals("image/gif");
        if(!flag) goto _L5; else goto _L4
_L4:
        String s1 = null;
_L8:
        this;
        JVM INSTR monitorexit ;
        return s1;
_L2:
        httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httpget);
          goto _L6
_L5:
        InputStream inputstream;
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        inputstream = httpresponse.getEntity().getContent();
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[512];
_L7:
        int j = inputstream.read(abyte0);
        if(j == -1)
            break MISSING_BLOCK_LABEL_242;
        bytearrayoutputstream.write(abyte0, 0, j);
          goto _L7
        IOException ioexception;
        ioexception;
        Log.e("MboxFactory", (new StringBuilder()).append("ERROR: ").append(ioexception.toString()).toString());
        s1 = null;
          goto _L8
        s1 = new String(bytearrayoutputstream.toByteArray(), "UTF-8");
          goto _L8
        Exception exception;
        exception;
        throw exception;
          goto _L6
    }

    protected boolean isEnabled()
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        if(preferences == null)
            preferences = parentContext.getSharedPreferences((new StringBuilder()).append("TestAndTarget").append(clientCode).toString(), 0);
        l = preferences.getLong("DisableTime", 0L);
        if(l <= 0L)
            break MISSING_BLOCK_LABEL_125;
        long l1 = System.currentTimeMillis() - l;
        if(l1 >= disableDuration)
            break MISSING_BLOCK_LABEL_136;
        Log.w("MboxFactory", (new StringBuilder()).append("WARNING: ").append(String.valueOf(disableDuration - l1)).append("ms until MboxFactory is re-enabled.").toString());
        factoryEnabled = false;
_L1:
        boolean flag = factoryEnabled;
        this;
        JVM INSTR monitorexit ;
        return flag;
        factoryEnabled = true;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void recordEvent(String s)
    {
        this;
        JVM INSTR monitorenter ;
        DefaultHttpClient defaulthttpclient;
        HttpGet httpget;
        String s1 = String.valueOf(System.currentTimeMillis());
        String s2 = (new StringBuilder()).append(getBaseRequestURL()).append("mbox=").append(encode(s)).append("&mboxDefault=").append(encode("/images/log.gif")).append("&mboxTime=").append(s1).toString();
        defaulthttpclient = new DefaultHttpClient();
        httpget = new HttpGet(s2);
        httpget.setHeader("User-Agent", "Apache-HttpClient (Test&Target Android SDK)");
        if(cookies != null)
            httpget.setHeader("Cookie", cookies);
        if(defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        defaulthttpclient.execute(httpget);
_L3:
        saveCookies(defaulthttpclient.getCookieStore());
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        HttpInstrumentation.execute((HttpClient)defaulthttpclient, httpget);
          goto _L3
        ClientProtocolException clientprotocolexception;
        clientprotocolexception;
        Log.e("MboxFactory", (new StringBuilder()).append("EXCEPTION: ").append(clientprotocolexception.toString()).toString());
          goto _L4
        Exception exception;
        exception;
        throw exception;
        IOException ioexception;
        ioexception;
        Log.e("MboxFactory", (new StringBuilder()).append("EXCEPTION: ").append(ioexception.toString()).toString());
          goto _L4
    }

    public void setDisableDuration(long l)
    {
        this;
        JVM INSTR monitorenter ;
        disableDuration = l;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setMboxServer(String s)
        throws IllegalArgumentException
    {
        String s1 = s.toLowerCase();
        if(s1.startsWith("http://") || s1.startsWith("https://"))
        {
            mboxServerURL = s1;
            return;
        } else
        {
            throw new IllegalArgumentException("ERROR: mbox server URL must begin with \"http://\" or \"https://\".");
        }
    }

    private static final int BUFFER_SIZE = 512;
    private static final String COOKIE_EXPIRES_KEY_SUFFIX = "_Expires";
    private static final String COOKIE_VALUE_KEY_SUFFIX = "_Value";
    private static final long DEFAULT_DISABLE_DURATION = 0xdbba0L;
    private static final String LOG_TAG = "MboxFactory";
    private static final String MBOX_DEFAULT = "/images/log.gif";
    private static final String MBOX_DEFAULT_CONTENT_TYPE = "image/gif";
    private static final String MBOX_SERVER_PROTOCOL = "http://";
    private static final String MBOX_SERVER_SUFFIX = ".tt.omtrdc.net";
    private static final String OFFER_ENCODING = "UTF-8";
    private static final String PREFERENCES_DISABLE_KEY = "DisableTime";
    private static final String PREFERENCES_PREFIX = "TestAndTarget";
    protected static final String USER_AGENT = "Apache-HttpClient (Test&Target Android SDK)";
    private String clientCode;
    private String cookies;
    private long disableDuration;
    private boolean factoryEnabled;
    private ConcurrentHashMap mboxList;
    private String mboxServerURL;
    private Context parentContext;
    private SharedPreferences preferences;
}
