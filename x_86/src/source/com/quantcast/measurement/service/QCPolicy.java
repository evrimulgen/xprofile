// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.*;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.*;
import java.util.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

// Referenced classes of package com.quantcast.measurement.service:
//            QCOptOutUtility, QCReachability, QCLog, QCUtility, 
//            QCMeasurement, QCNotificationCenter

class QCPolicy
{

    private QCPolicy(Context context, String s)
    {
        m_policyURL = s;
        m_policyIsLoaded = false;
        if(QCOptOutUtility.isOptedOut(context))
        {
            m_policyIsLoaded = false;
            return;
        }
        if(QCReachability.isConnected(context))
        {
            getPolicy(context, m_policyURL);
            return;
        } else
        {
            QCLog.i(TAG, "No connection.  Policy could not be downloaded. Using cache");
            m_policyIsLoaded = checkPolicy(context, true);
            return;
        }
    }

    private boolean checkPolicy(Context context, boolean flag)
    {
        File file;
        boolean flag1;
        boolean flag2;
        file = new File(context.getDir("com.quantcast", 0), "qc-policy.json");
        flag1 = file.exists();
        flag2 = false;
        if(!flag1) goto _L2; else goto _L1
_L1:
        long l;
        FileInputStream fileinputstream;
        l = file.lastModified();
        fileinputstream = null;
        FileInputStream fileinputstream1 = new FileInputStream(file);
        flag2 = parsePolicy(readStreamToString(fileinputstream1));
        if(!flag2) goto _L4; else goto _L3
_L3:
        if(flag) goto _L6; else goto _L5
_L5:
        long l1;
        long l2;
        l1 = System.currentTimeMillis() - l;
        l2 = POLICY_CACHE_LENGTH;
        if(l1 >= l2) goto _L4; else goto _L6
_L6:
        flag2 = true;
_L7:
        Exception exception;
        Exception exception1;
        if(fileinputstream1 != null)
            try
            {
                fileinputstream1.close();
            }
            catch(IOException ioexception2)
            {
                return flag2;
            }
_L2:
        return flag2;
_L4:
        flag2 = false;
          goto _L7
        exception;
_L11:
        QCLog.e(TAG, "Could not read from policy cache", exception);
        if(fileinputstream == null) goto _L2; else goto _L8
_L8:
        try
        {
            fileinputstream.close();
        }
        catch(IOException ioexception1)
        {
            return flag2;
        }
        return flag2;
        exception1;
_L10:
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            catch(IOException ioexception) { }
        throw exception1;
        exception1;
        fileinputstream = fileinputstream1;
        if(true) goto _L10; else goto _L9
_L9:
        exception;
        fileinputstream = fileinputstream1;
          goto _L11
    }

    private void getPolicy(final Context context, String s)
    {
        if(isBlackedOut())
            return;
        TraceFieldInterface tracefieldinterface = new TraceFieldInterface() {

            public void _nr_setTrace(Trace trace)
            {
                try
                {
                    _nr_trace = trace;
                    return;
                }
                catch(Exception exception)
                {
                    return;
                }
            }

            protected transient Boolean doInBackground(String as1[])
            {
                boolean flag = checkPolicy(context, false);
                if(flag) goto _L2; else goto _L1
_L1:
                DefaultHttpClient defaulthttpclient;
                InputStream inputstream;
                defaulthttpclient = new DefaultHttpClient();
                inputstream = null;
                HttpGet httpget;
                boolean flag1;
                httpget = new HttpGet(as1[0]);
                flag1 = defaulthttpclient instanceof HttpClient;
                inputstream = null;
                if(flag1) goto _L4; else goto _L3
_L3:
                HttpResponse httpresponse = defaulthttpclient.execute(httpget);
_L5:
                String s2;
                inputstream = httpresponse.getEntity().getContent();
                s2 = readStreamToString(inputstream);
                String s1 = s2;
                Exception exception;
                Exception exception1;
                IOException ioexception1;
                HttpResponse httpresponse1;
                if(inputstream != null)
                    try
                    {
                        inputstream.close();
                    }
                    catch(IOException ioexception2) { }
                if(s1 != null)
                {
                    savePolicy(context, s1);
                    flag = parsePolicy(s1);
                }
_L2:
                return Boolean.valueOf(flag);
_L4:
                httpresponse1 = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httpget);
                httpresponse = httpresponse1;
                  goto _L5
                exception1;
                QCLog.e(QCPolicy.TAG, "Could not download policy", exception1);
                QCMeasurement.INSTANCE.logSDKError("policy-download-failure", exception1.getMessage(), null);
                s1 = null;
                if(inputstream == null)
                    break MISSING_BLOCK_LABEL_101;
                try
                {
                    inputstream.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception1)
                {
                    s1 = null;
                    break MISSING_BLOCK_LABEL_101;
                }
                s1 = null;
                break MISSING_BLOCK_LABEL_101;
                exception;
                if(inputstream != null)
                    try
                    {
                        inputstream.close();
                    }
                    catch(IOException ioexception) { }
                throw exception;
                  goto _L5
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                TraceMachine.enterMethod(_nr_trace, "QCPolicy$1#doInBackground", null);
_L1:
                Boolean boolean1 = doInBackground((String[])aobj);
                TraceMachine.exitMethod();
                TraceMachine.unloadTraceContext(this);
                return boolean1;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCPolicy$1#doInBackground", null);
                  goto _L1
            }

            protected void onPostExecute(Boolean boolean1)
            {
                m_policyIsLoaded = boolean1.booleanValue();
                QCNotificationCenter.INSTANCE.postNotification("QC_PU", null);
            }

            protected volatile void onPostExecute(Object obj)
            {
                TraceMachine.enterMethod(_nr_trace, "QCPolicy$1#onPostExecute", null);
_L1:
                onPostExecute((Boolean)obj);
                TraceMachine.exitMethod();
                return;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCPolicy$1#onPostExecute", null);
                  goto _L1
            }

            public Trace _nr_trace;
            final QCPolicy this$0;
            final Context val$context;

            
            {
                this$0 = QCPolicy.this;
                context = context1;
                super();
            }
        }
;
        String as[] = {
            s
        };
        if(!(tracefieldinterface instanceof AsyncTask))
        {
            tracefieldinterface.execute(as);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)tracefieldinterface, as);
            return;
        }
    }

    public static QCPolicy getQuantcastPolicy(Context context, String s, String s1, String s2, boolean flag)
    {
        android.net.Uri.Builder builder = Uri.parse(QCUtility.addScheme("m.quantcount.com/policy.json")).buildUpon();
        builder.appendQueryParameter("v", "1_1_0");
        builder.appendQueryParameter("t", "ANDROID");
        TelephonyManager telephonymanager = (TelephonyManager)context.getSystemService("phone");
        String s3 = null;
        if(telephonymanager != null)
        {
            s3 = telephonymanager.getNetworkCountryIso();
            if(s3 == null)
                s3 = telephonymanager.getSimCountryIso();
        }
        if(s3 == null)
            s3 = Locale.getDefault().getCountry();
        if(s3 != null)
            builder.appendQueryParameter("c", s3);
        Uri uri;
        if(s != null)
        {
            builder.appendQueryParameter("a", s);
        } else
        {
            builder.appendQueryParameter("n", s1);
            builder.appendQueryParameter("p", s2);
        }
        if(flag)
            builder.appendQueryParameter("k", "YES");
        uri = builder.build();
        if(uri != null)
        {
            return new QCPolicy(context, uri.toString());
        } else
        {
            QCLog.e(TAG, "Policy URL was not built correctly for some reason.  Should not happen");
            return null;
        }
    }

    private boolean parsePolicy(String s)
    {
        m_blacklist = null;
        m_salt = null;
        m_blackoutUntil = 0L;
        m_sessionTimeout = null;
        if("".equals(s)) goto _L2; else goto _L1
_L1:
        JSONObject jsonobject;
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        JSONException jsonexception3;
        JSONException jsonexception4;
        JSONArray jsonarray;
        int i;
        try
        {
            JVM INSTR new #288 <Class JSONObject>;
            jsonobject = JSONObjectInstrumentation.init(s);
            flag = jsonobject.has("blacklist");
        }
        catch(JSONException jsonexception)
        {
            QCLog.w(TAG, (new StringBuilder()).append("Failed to parse JSON from string: ").append(s).toString());
            return false;
        }
        if(!flag) goto _L4; else goto _L3
_L3:
        jsonarray = jsonobject.getJSONArray("blacklist");
        if(jsonarray.length() <= 0) goto _L4; else goto _L5
_L5:
        if(m_blacklist == null)
            m_blacklist = new HashSet(jsonarray.length());
          goto _L6
_L12:
        if(i >= jsonarray.length()) goto _L4; else goto _L7
_L7:
        m_blacklist.add(jsonarray.getString(i));
        i++;
        continue; /* Loop/switch isn't completed */
        jsonexception4;
        QCLog.w(TAG, "Failed to parse blacklist from JSON.", jsonexception4);
_L4:
        flag1 = jsonobject.has("salt");
        if(!flag1)
            break MISSING_BLOCK_LABEL_185;
        m_salt = jsonobject.getString("salt");
        if("MSG".equals(m_salt))
            m_salt = null;
_L9:
        flag2 = jsonobject.has("blackout");
        if(!flag2)
            break MISSING_BLOCK_LABEL_210;
        m_blackoutUntil = jsonobject.getLong("blackout");
_L10:
        flag3 = jsonobject.has("sessionTimeOutSeconds");
        if(!flag3) goto _L2; else goto _L8
_L8:
        m_sessionTimeout = Long.valueOf(jsonobject.getLong("sessionTimeOutSeconds"));
        if(m_sessionTimeout.longValue() <= 0L)
            m_sessionTimeout = null;
_L2:
        return true;
        jsonexception3;
        QCLog.w(TAG, "Failed to parse salt from JSON.", jsonexception3);
          goto _L9
        JSONException jsonexception2;
        jsonexception2;
        QCLog.w(TAG, "Failed to parse blackout from JSON.", jsonexception2);
          goto _L10
        JSONException jsonexception1;
        jsonexception1;
        QCLog.w(TAG, "Failed to parse session timeout from JSON.", jsonexception1);
        return true;
_L6:
        i = 0;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private String readStreamToString(InputStream inputstream)
        throws IOException
    {
        StringBuilder stringbuilder = new StringBuilder();
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
_L3:
        String s = bufferedreader.readLine();
        if(s == null) goto _L2; else goto _L1
_L1:
        stringbuilder.append(s);
          goto _L3
        Exception exception;
        exception;
        BufferedReader bufferedreader1 = bufferedreader;
_L5:
        IOException ioexception1;
        if(bufferedreader1 != null)
            try
            {
                bufferedreader1.close();
            }
            catch(IOException ioexception) { }
        throw exception;
_L2:
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception1) { }
        return stringbuilder.toString();
        exception;
        bufferedreader1 = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void savePolicy(Context context, String s)
    {
        File file;
        FileOutputStream fileoutputstream;
        file = new File(context.getDir("com.quantcast", 0), "qc-policy.json");
        fileoutputstream = null;
        FileOutputStream fileoutputstream1 = new FileOutputStream(file);
        fileoutputstream1.write(s.getBytes());
        if(fileoutputstream1 == null)
            break MISSING_BLOCK_LABEL_118;
        fileoutputstream1.close();
_L2:
        return;
        IOException ioexception2;
        ioexception2;
        return;
        Exception exception;
        exception;
_L5:
        QCLog.e(TAG, "Could not write policy", exception);
        if(fileoutputstream == null) goto _L2; else goto _L1
_L1:
        try
        {
            fileoutputstream.close();
            return;
        }
        catch(IOException ioexception1)
        {
            return;
        }
        Exception exception1;
        exception1;
_L4:
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(IOException ioexception) { }
        throw exception1;
        exception1;
        fileoutputstream = fileoutputstream1;
        if(true) goto _L4; else goto _L3
_L3:
        exception;
        fileoutputstream = fileoutputstream1;
          goto _L5
    }

    String getSalt()
    {
        return m_salt;
    }

    Long getSessionTimeout()
    {
        return m_sessionTimeout;
    }

    boolean hasSessionTimeout()
    {
        return m_sessionTimeout != null;
    }

    boolean isBlackedOut()
    {
        return policyIsLoaded() && System.currentTimeMillis() <= m_blackoutUntil;
    }

    boolean isBlacklisted(String s)
    {
        boolean flag;
        if(s == null)
        {
            flag = true;
        } else
        {
            Set set = m_blacklist;
            flag = false;
            if(set != null)
                return m_blacklist.contains(s);
        }
        return flag;
    }

    public boolean policyIsLoaded()
    {
        return m_policyIsLoaded;
    }

    public void updatePolicy(Context context)
    {
        if(QCReachability.isConnected(context))
        {
            getPolicy(context, m_policyURL);
            return;
        } else
        {
            QCLog.i(TAG, "No connection.  Policy could not be updated. Using cache.");
            m_policyIsLoaded = checkPolicy(context, true);
            return;
        }
    }

    private static final String BLACKLIST_KEY = "blacklist";
    private static final String BLACKOUT_KEY = "blackout";
    static long POLICY_CACHE_LENGTH = 0L;
    static final String POLICY_DIRECTORY = "com.quantcast";
    static final String POLICY_FILENAME = "qc-policy.json";
    private static final String POLICY_REQUEST_API_KEY_PARAMETER = "a";
    private static final String POLICY_REQUEST_API_VERSION_PARAMETER = "v";
    private static final String POLICY_REQUEST_BASE_WITHOUT_SCHEME = "m.quantcount.com/policy.json";
    private static final String POLICY_REQUEST_DEVICE_COUNTRY = "c";
    private static final String POLICY_REQUEST_DEVICE_TYPE = "ANDROID";
    private static final String POLICY_REQUEST_DEVICE_TYPE_PARAMETER = "t";
    private static final String POLICY_REQUEST_KID_DIRECTED_PARAMETER = "k";
    private static final String POLICY_REQUEST_NETWORK_CODE_PARAMETER = "n";
    private static final String POLICY_REQUEST_PACKAGE_PARAMETER = "p";
    public static final String QC_NOTIF_POLICY_UPDATE = "QC_PU";
    private static final String SALT_KEY = "salt";
    private static final String SESSION_TIMEOUT_KEY = "sessionTimeOutSeconds";
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCPolicy);
    private static final String USE_NO_SALT = "MSG";
    private Set m_blacklist;
    private long m_blackoutUntil;
    private boolean m_policyIsLoaded;
    private final String m_policyURL;
    private String m_salt;
    private Long m_sessionTimeout;

    static 
    {
        POLICY_CACHE_LENGTH = 0x1b7740L;
    }







/*
    static boolean access$502(QCPolicy qcpolicy, boolean flag)
    {
        qcpolicy.m_policyIsLoaded = flag;
        return flag;
    }

*/
}
