// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.amplitude.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.newrelic.agent.android.instrumentation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.*;

// Referenced classes of package com.amplitude.api:
//            Constants, DatabaseThread, DatabaseHelper, HTTPThread

public class Amplitude
{

    private Amplitude()
    {
    }

    private static void addBoilerplate(JSONObject jsonobject)
        throws JSONException
    {
        jsonobject.put("timestamp", System.currentTimeMillis());
        Object obj;
        JSONObject jsonobject1;
        Location location;
        if(userId == null)
            obj = replaceWithJSONNull(deviceId);
        else
            obj = replaceWithJSONNull(userId);
        jsonobject.put("user_id", obj);
        jsonobject.put("device_id", replaceWithJSONNull(deviceId));
        jsonobject.put("session_id", sessionId);
        jsonobject.put("version_code", versionCode);
        jsonobject.put("version_name", replaceWithJSONNull(versionName));
        jsonobject.put("build_version_sdk", buildVersionSdk);
        jsonobject.put("build_version_release", replaceWithJSONNull(buildVersionRelease));
        jsonobject.put("phone_brand", replaceWithJSONNull(phoneBrand));
        jsonobject.put("phone_manufacturer", replaceWithJSONNull(phoneManufacturer));
        jsonobject.put("phone_model", replaceWithJSONNull(phoneModel));
        jsonobject.put("phone_carrier", replaceWithJSONNull(phoneCarrier));
        jsonobject.put("country", replaceWithJSONNull(country));
        jsonobject.put("language", replaceWithJSONNull(language));
        jsonobject.put("client", "android");
        jsonobject1 = jsonobject.getJSONObject("api_properties");
        location = getMostRecentLocation();
        if(location != null)
        {
            JSONObject jsonobject2 = new JSONObject();
            jsonobject2.put("lat", location.getLatitude());
            jsonobject2.put("lng", location.getLongitude());
            jsonobject1.put("location", jsonobject2);
        }
        if(sessionStarted)
            refreshSessionTime();
    }

    public static String bytesToHexString(byte abyte0[])
    {
        char ac[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            'a', 'b', 'c', 'd', 'e', 'f'
        };
        char ac1[] = new char[2 * abyte0.length];
        for(int i = 0; i < abyte0.length; i++)
        {
            int j = 0xff & abyte0[i];
            ac1[i * 2] = ac[j >>> 4];
            ac1[1 + i * 2] = ac[j & 0xf];
        }

        return new String(ac1);
    }

    private static boolean contextAndApiKeySet(String s)
    {
        if(context == null)
        {
            Log.e("com.amplitude.api.Amplitude", (new StringBuilder()).append("context cannot be null, set context with initialize() before calling ").append(s).toString());
            return false;
        }
        if(TextUtils.isEmpty(apiKey))
        {
            Log.e("com.amplitude.api.Amplitude", (new StringBuilder()).append("apiKey cannot be null or empty, set apiKey with initialize() before calling ").append(s).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public static void enableCampaignTracking(Context context1, String s)
    {
        if(context1 == null)
        {
            Log.e("com.amplitude.api.Amplitude", "Argument context cannot be null in enableCampaignTracking()");
            return;
        }
        if(TextUtils.isEmpty(s))
        {
            Log.e("com.amplitude.api.Amplitude", "Argument apiKey cannot be null or blank in enableCampaignTracking()");
            return;
        } else
        {
            Context context2 = context1.getApplicationContext();
            context = context2;
            apiKey = s;
            campaignInformation = context2.getSharedPreferences(getSharedPreferencesName(), 0).getString(Constants.PREFKEY_CAMPAIGN_INFORMATION, "{\"tracked\": false}");
            trackCampaignSource();
            return;
        }
    }

    public static void endSession()
    {
        if(!contextAndApiKeySet("endSession()"))
            return;
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("special", "session_end");
        }
        catch(JSONException jsonexception) { }
        logEvent("session_end", null, jsonobject);
        sessionStarted = false;
        turnOffSessionLater();
        uploadEvents();
    }

    public static JSONObject getCampaignInformation()
    {
        if(!contextAndApiKeySet("getCampaignInformation()"))
            return new JSONObject();
        JSONObject jsonobject;
        try
        {
            JSONObject _tmp = JVM INSTR new #129 <Class JSONObject>;
            jsonobject = JSONObjectInstrumentation.init(campaignInformation);
        }
        catch(JSONException jsonexception)
        {
            Log.e("com.amplitude.api.Amplitude", jsonexception.toString());
            return new JSONObject();
        }
        return jsonobject;
    }

    private static String getDeviceId()
    {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(getSharedPreferencesName(), 0);
        String s = sharedpreferences.getString(Constants.PREFKEY_DEVICE_ID, null);
        if(!TextUtils.isEmpty(s))
            return s;
        String s1 = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
        if(!TextUtils.isEmpty(s1) && !s1.equals("9774d56d682e549c"))
        {
            sharedpreferences.edit().putString(Constants.PREFKEY_DEVICE_ID, s1).commit();
            return s1;
        }
        String s4;
        s4 = (String)android/os/Build.getField("SERIAL").get(null);
        if(TextUtils.isEmpty(s4))
            break MISSING_BLOCK_LABEL_136;
        sharedpreferences.edit().putString(Constants.PREFKEY_DEVICE_ID, s4).commit();
        return s4;
        Exception exception;
        exception;
        if(permissionGranted("android.permission.READ_PHONE_STATE") && context.getPackageManager().hasSystemFeature("android.hardware.telephony"))
        {
            String s3 = ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
            if(!TextUtils.isEmpty(s3))
            {
                sharedpreferences.edit().putString(Constants.PREFKEY_DEVICE_ID, s3).commit();
                return s3;
            }
        }
        String s2 = UUID.randomUUID().toString();
        sharedpreferences.edit().putString(Constants.PREFKEY_DEVICE_ID, s2).commit();
        return s2;
    }

    private static Location getMostRecentLocation()
    {
        LocationManager locationmanager = (LocationManager)context.getSystemService("location");
        List list = locationmanager.getProviders(true);
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Location location3 = locationmanager.getLastKnownLocation((String)iterator.next());
            if(location3 != null)
                arraylist.add(location3);
        } while(true);
        long l = -1L;
        Location location = null;
        Iterator iterator1 = arraylist.iterator();
        while(iterator1.hasNext()) 
        {
            Location location1 = (Location)iterator1.next();
            long l1;
            Location location2;
            if(location1.getTime() > l)
            {
                long l2 = location1.getTime();
                location2 = location1;
                l1 = l2;
            } else
            {
                l1 = l;
                location2 = location;
            }
            location = location2;
            l = l1;
        }
        return location;
    }

    private static String getSharedPreferencesName()
    {
        return (new StringBuilder()).append(Constants.SHARED_PREFERENCES_NAME_PREFIX).append(".").append(context.getPackageName()).toString();
    }

    public static void initialize(Context context1, String s)
    {
        initialize(context1, s, ((String) (null)));
    }

    public static void initialize(Context context1, String s, String s1)
    {
        initialize(context1, s, s1, false);
    }

    public static void initialize(Context context1, String s, String s1, boolean flag)
    {
        if(context1 == null)
        {
            Log.e("com.amplitude.api.Amplitude", "Argument context cannot be null in initialize()");
        } else
        {
            if(TextUtils.isEmpty(s))
            {
                Log.e("com.amplitude.api.Amplitude", "Argument apiKey cannot be null or blank in initialize()");
                return;
            }
            context = context1.getApplicationContext();
            apiKey = s;
            SharedPreferences sharedpreferences = context1.getSharedPreferences(getSharedPreferencesName(), 0);
            if(s1 != null)
            {
                userId = s1;
                sharedpreferences.edit().putString(Constants.PREFKEY_USER_ID, s1).commit();
            } else
            {
                userId = sharedpreferences.getString(Constants.PREFKEY_USER_ID, null);
            }
            deviceId = getDeviceId();
            campaignInformation = sharedpreferences.getString(Constants.PREFKEY_CAMPAIGN_INFORMATION, "{\"tracked\": false}");
            try
            {
                PackageInfo packageinfo = context1.getPackageManager().getPackageInfo(context1.getPackageName(), 0);
                versionCode = packageinfo.versionCode;
                versionName = packageinfo.versionName;
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
            buildVersionSdk = android.os.Build.VERSION.SDK_INT;
            buildVersionRelease = android.os.Build.VERSION.RELEASE;
            phoneBrand = Build.BRAND;
            phoneManufacturer = Build.MANUFACTURER;
            phoneModel = Build.MODEL;
            phoneCarrier = ((TelephonyManager)context1.getSystemService("phone")).getNetworkOperatorName();
            country = Locale.getDefault().getDisplayCountry();
            language = Locale.getDefault().getDisplayLanguage();
            if(flag)
            {
                trackCampaignSource();
                return;
            }
        }
    }

    public static void initialize(Context context1, String s, boolean flag)
    {
        initialize(context1, s, null, flag);
    }

    public static void logEvent(String s)
    {
        logEvent(s, null);
    }

    public static void logEvent(String s, JSONObject jsonobject)
    {
        logEvent(s, jsonobject, null);
    }

    private static void logEvent(String s, JSONObject jsonobject, JSONObject jsonobject1)
    {
        if(!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        Log.e("com.amplitude.api.Amplitude", "Argument eventType cannot be null or blank in logEvent()");
_L4:
        return;
_L2:
        if(!contextAndApiKeySet("logEvent()")) goto _L4; else goto _L3
_L3:
        JSONObject jsonobject2 = new JSONObject();
        jsonobject2.put("event_type", replaceWithJSONNull(s));
        if(jsonobject != null)
            break MISSING_BLOCK_LABEL_58;
        jsonobject = new JSONObject();
        jsonobject2.put("custom_properties", jsonobject);
        if(jsonobject1 != null)
            break MISSING_BLOCK_LABEL_79;
        jsonobject1 = new JSONObject();
        jsonobject2.put("api_properties", jsonobject1);
        if(globalProperties != null) goto _L6; else goto _L5
_L5:
        JSONObject jsonobject3 = new JSONObject();
_L7:
        jsonobject2.put("global_properties", jsonobject3);
        addBoilerplate(jsonobject2);
_L8:
        DatabaseThread.post(new Runnable(jsonobject2) {

            public void run()
            {
                DatabaseHelper databasehelper = DatabaseHelper.getDatabaseHelper(Amplitude.context);
                JSONObject jsonobject4 = event;
                String s1;
                if(!(jsonobject4 instanceof JSONObject))
                    s1 = jsonobject4.toString();
                else
                    s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject4);
                databasehelper.addEvent(s1);
                if(databasehelper.getNumberRows() >= 1020L)
                    databasehelper.removeEvents(databasehelper.getNthEventId(20L));
                if(databasehelper.getNumberRows() >= 10L)
                {
                    Amplitude.updateServer();
                    return;
                } else
                {
                    Amplitude.updateServerLater();
                    return;
                }
            }

            final JSONObject val$event;

            
            {
                event = jsonobject;
                super();
            }
        }
);
        return;
_L6:
        jsonobject3 = globalProperties;
          goto _L7
        JSONException jsonexception;
        jsonexception;
        Log.e("com.amplitude.api.Amplitude", jsonexception.toString());
          goto _L8
    }

    public static void logRevenue(double d)
    {
        if(!contextAndApiKeySet("logRevenue()"))
            return;
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("special", "revenue_amount");
            jsonobject.put("revenue", d);
        }
        catch(JSONException jsonexception) { }
        logEvent("revenue_amount", null, jsonobject);
    }

    private static JSONObject makeCampaignTrackingPostRequest(String s, String s1)
        throws ClientProtocolException, IOException, JSONException
    {
        HttpPost httppost = new HttpPost(s);
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("key", apiKey));
        arraylist.add(new BasicNameValuePair("fingerprint", s1));
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
        HttpResponse httpresponse;
        String s2;
        if(!(defaulthttpclient instanceof HttpClient))
            httpresponse = defaulthttpclient.execute(httppost);
        else
            httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httppost);
        s2 = EntityUtils.toString(httpresponse.getEntity());
        JSONObject _tmp = JVM INSTR new #129 <Class JSONObject>;
        return JSONObjectInstrumentation.init(s2);
    }

    private static void makeEventUploadPostRequest(String s, String s1, long l)
    {
        HttpPost httppost;
        ArrayList arraylist;
        String s2;
        String s3;
        httppost = new HttpPost(s);
        arraylist = new ArrayList();
        s2 = (new StringBuilder()).append("").append(System.currentTimeMillis()).toString();
        s3 = "";
        String s6;
        String s5 = (new StringBuilder()).append("2").append(apiKey).append(s1).append(s2).toString();
        s6 = bytesToHexString(MessageDigest.getInstance("MD5").digest(s5.getBytes("UTF-8")));
        s3 = s6;
_L5:
        DefaultHttpClient defaulthttpclient;
        boolean flag;
        HttpResponse httpresponse;
        String s4;
        arraylist.add(new BasicNameValuePair("v", "2"));
        arraylist.add(new BasicNameValuePair("client", apiKey));
        arraylist.add(new BasicNameValuePair("e", s1));
        arraylist.add(new BasicNameValuePair("upload_time", s2));
        arraylist.add(new BasicNameValuePair("checksum", s3));
        UnsupportedEncodingException unsupportedencodingexception;
        boolean flag3;
        NoSuchAlgorithmException nosuchalgorithmexception;
        try
        {
            httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception1)
        {
            Log.e("com.amplitude.api.Amplitude", unsupportedencodingexception1.toString());
        }
        defaulthttpclient = new DefaultHttpClient();
        if(defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        httpresponse = defaulthttpclient.execute(httppost);
_L6:
        s4 = EntityUtils.toString(httpresponse.getEntity());
        flag3 = s4.equals("success");
        if(!flag3) goto _L4; else goto _L3
_L3:
        flag = true;
        DatabaseThread.post(new Runnable(l) {

            public void run()
            {
                DatabaseHelper databasehelper = DatabaseHelper.getDatabaseHelper(Amplitude.context);
                databasehelper.removeEvents(maxId);
                Amplitude.uploadingCurrently.set(false);
                if(databasehelper.getNumberRows() > 0L)
                    DatabaseThread.post(new Runnable() {

                        public void run()
                        {
                            Amplitude.updateServer(false);
                        }

                        final _cls7 this$0;

            
            {
                this$0 = _cls7.this;
                super();
            }
                    }
);
            }

            final long val$maxId;

            
            {
                maxId = l;
                super();
            }
        }
);
_L7:
        if(!flag)
            uploadingCurrently.set(false);
        return;
        nosuchalgorithmexception;
        Log.e("com.amplitude.api.Amplitude", nosuchalgorithmexception.toString());
          goto _L5
        unsupportedencodingexception;
        Log.e("com.amplitude.api.Amplitude", unsupportedencodingexception.toString());
          goto _L5
_L2:
        httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httppost);
          goto _L6
_L4:
        if(!s4.equals("invalid_api_key"))
            break MISSING_BLOCK_LABEL_395;
        Log.e("com.amplitude.api.Amplitude", "Invalid API key, make sure your API key is correct in initialize()");
        flag = false;
          goto _L7
        if(!s4.equals("bad_checksum"))
            break MISSING_BLOCK_LABEL_421;
        Log.w("com.amplitude.api.Amplitude", "Bad checksum, post request was mangled in transit, will attempt to reupload later");
        flag = false;
          goto _L7
        if(!s4.equals("request_db_write_failed"))
            break MISSING_BLOCK_LABEL_447;
        Log.w("com.amplitude.api.Amplitude", "Couldn't write to request database on server, will attempt to reupload later");
        flag = false;
          goto _L7
        Log.w("com.amplitude.api.Amplitude", (new StringBuilder()).append("Upload failed, ").append(s4).append(", will attempt to reupload later").toString());
        flag = false;
          goto _L7
        ClientProtocolException clientprotocolexception;
        clientprotocolexception;
        boolean flag2 = false;
_L9:
        Log.e("com.amplitude.api.Amplitude", clientprotocolexception.toString());
        flag = flag2;
          goto _L7
        IOException ioexception;
        ioexception;
        boolean flag1 = false;
_L8:
        Log.e("com.amplitude.api.Amplitude", ioexception.toString());
        flag = flag1;
          goto _L7
        IOException ioexception1;
        ioexception1;
        flag1 = flag;
        ioexception = ioexception1;
          goto _L8
        ClientProtocolException clientprotocolexception1;
        clientprotocolexception1;
        flag2 = flag;
        clientprotocolexception = clientprotocolexception1;
          goto _L9
        UnknownHostException unknownhostexception;
        unknownhostexception;
        flag = false;
          goto _L7
        UnknownHostException unknownhostexception1;
        unknownhostexception1;
          goto _L7
        HttpHostConnectException httphostconnectexception;
        httphostconnectexception;
        flag = false;
          goto _L7
        HttpHostConnectException httphostconnectexception1;
        httphostconnectexception1;
          goto _L7
    }

    private static boolean permissionGranted(String s)
    {
        return context.checkCallingOrSelfPermission(s) == 0;
    }

    private static void refreshSessionTime()
    {
        long l = System.currentTimeMillis();
        context.getSharedPreferences(getSharedPreferencesName(), 0).edit().putLong(Constants.PREFKEY_PREVIOUS_SESSION_TIME, l).commit();
    }

    private static Object replaceWithJSONNull(Object obj)
    {
        if(obj == null)
            obj = JSONObject.NULL;
        return obj;
    }

    public static void setGlobalUserProperties(JSONObject jsonobject)
    {
        globalProperties = jsonobject;
    }

    public static void setUserId(String s)
    {
        if(!contextAndApiKeySet("setUserId()"))
        {
            return;
        } else
        {
            userId = s;
            context.getSharedPreferences(getSharedPreferencesName(), 0).edit().putString(Constants.PREFKEY_USER_ID, s).commit();
            return;
        }
    }

    public static void startSession()
    {
        if(!contextAndApiKeySet("startSession()"))
            return;
        DatabaseThread.removeCallbacks(setSessionIdRunnable);
        if(!sessionStarted)
        {
            long l = System.currentTimeMillis();
            SharedPreferences sharedpreferences = context.getSharedPreferences(getSharedPreferencesName(), 0);
            JSONObject jsonobject;
            if(l - sharedpreferences.getLong(Constants.PREFKEY_PREVIOUS_SESSION_TIME, -1L) < 10000L)
            {
                sessionId = sharedpreferences.getLong(Constants.PREFKEY_PREVIOUS_SESSION_ID, l);
            } else
            {
                sessionId = l;
                sharedpreferences.edit().putLong(Constants.PREFKEY_PREVIOUS_SESSION_ID, sessionId).commit();
            }
            sessionStarted = true;
        }
        jsonobject = new JSONObject();
        try
        {
            jsonobject.put("special", "session_start");
        }
        catch(JSONException jsonexception) { }
        logEvent("session_start", null, jsonobject);
        uploadEvents();
    }

    private static void trackCampaignSource()
    {
        if(!context.getSharedPreferences(getSharedPreferencesName(), 0).getBoolean(Constants.PREFKEY_HAS_TRACKED_CAMPAIGN, false) && !isCurrentlyTrackingCampaign)
        {
            isCurrentlyTrackingCampaign = true;
            DatabaseThread.post(new Runnable() {

                public void run()
                {
                    JSONObject jsonobject;
                    jsonobject = new JSONObject();
                    jsonobject.put("device_id", Amplitude.replaceWithJSONNull(Amplitude.getDeviceId()));
                    jsonobject.put("client", "android");
                    jsonobject.put("country", Amplitude.replaceWithJSONNull(Locale.getDefault().getDisplayCountry()));
                    jsonobject.put("language", Amplitude.replaceWithJSONNull(Locale.getDefault().getDisplayLanguage()));
                    jsonobject.put("device", Amplitude.replaceWithJSONNull(Build.DEVICE));
                    jsonobject.put("display", Amplitude.replaceWithJSONNull(Build.DISPLAY));
                    jsonobject.put("product", Amplitude.replaceWithJSONNull(Build.PRODUCT));
                    jsonobject.put("brand", Amplitude.replaceWithJSONNull(Build.BRAND));
                    jsonobject.put("model", Amplitude.replaceWithJSONNull(Build.MODEL));
                    jsonobject.put("manufacturer", Amplitude.replaceWithJSONNull(Build.MANUFACTURER));
                    if(jsonobject instanceof JSONObject) goto _L2; else goto _L1
_L1:
                    String s = jsonobject.toString();
_L7:
                    JSONObject jsonobject1;
                    android.content.SharedPreferences.Editor editor;
                    String s1;
                    jsonobject1 = Amplitude.makeCampaignTrackingPostRequest("http://ref.amplitude.com/install", s);
                    editor = Amplitude.context.getSharedPreferences(Amplitude.getSharedPreferencesName(), 0).edit();
                    editor.putBoolean(Constants.PREFKEY_HAS_TRACKED_CAMPAIGN, true);
                    s1 = Constants.PREFKEY_CAMPAIGN_INFORMATION;
                    if(jsonobject1 instanceof JSONObject) goto _L4; else goto _L3
_L3:
                    String s2 = jsonobject1.toString();
_L8:
                    editor.putString(s1, s2);
                    editor.commit();
                    if(jsonobject1 instanceof JSONObject) goto _L6; else goto _L5
_L5:
                    String s4 = jsonobject1.toString();
_L9:
                    Amplitude.campaignInformation = s4;
_L10:
                    Amplitude.isCurrentlyTrackingCampaign = false;
                    return;
_L2:
                    s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
                      goto _L7
_L4:
                    s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
                      goto _L8
_L6:
                    String s3 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
                    s4 = s3;
                      goto _L9
                    Exception exception;
                    exception;
                    Log.e("com.amplitude.api.Amplitude", exception.toString());
                      goto _L10
                    UnknownHostException unknownhostexception;
                    unknownhostexception;
                      goto _L10
                    HttpHostConnectException httphostconnectexception;
                    httphostconnectexception;
                      goto _L10
                }

            }
);
        }
    }

    private static void turnOffSessionLater()
    {
        setSessionIdRunnable = new Runnable() {

            public void run()
            {
                if(!Amplitude.sessionStarted)
                    Amplitude.sessionId = -1L;
            }

        }
;
        DatabaseThread.postDelayed(setSessionIdRunnable, 10000L);
    }

    private static void updateServer()
    {
        updateServer(true);
    }

    private static void updateServer(boolean flag)
    {
        DatabaseHelper databasehelper;
        if(uploadingCurrently.getAndSet(true))
            break MISSING_BLOCK_LABEL_57;
        databasehelper = DatabaseHelper.getDatabaseHelper(context);
        Pair pair = databasehelper.getEvents(flag);
        long l = ((Long)pair.first).longValue();
        HTTPThread.post(new Runnable((JSONArray)pair.second, l) {

            public void run()
            {
                JSONArray jsonarray = events;
                String s;
                if(!(jsonarray instanceof JSONArray))
                    s = jsonarray.toString();
                else
                    s = JSONArrayInstrumentation.toString((JSONArray)jsonarray);
                Amplitude.makeEventUploadPostRequest("https://api.amplitude.com/", s, maxId);
            }

            final JSONArray val$events;
            final long val$maxId;

            
            {
                events = jsonarray;
                maxId = l;
                super();
            }
        }
);
        return;
        JSONException jsonexception;
        jsonexception;
        uploadingCurrently.set(false);
        Log.e("com.amplitude.api.Amplitude", jsonexception.toString());
        return;
    }

    private static void updateServerLater()
    {
        if(!updateScheduled.getAndSet(true))
            DatabaseThread.postDelayed(new Runnable() {

                public void run()
                {
                    Amplitude.updateScheduled.set(false);
                    Amplitude.updateServer();
                }

            }
, 10000L);
    }

    public static void uploadEvents()
    {
        if(!contextAndApiKeySet("uploadEvents()"))
        {
            return;
        } else
        {
            DatabaseThread.post(new Runnable() {

                public void run()
                {
                    Amplitude.updateServer();
                }

            }
);
            return;
        }
    }

    public static final String TAG = "com.amplitude.api.Amplitude";
    private static String apiKey;
    private static String buildVersionRelease;
    private static int buildVersionSdk;
    private static String campaignInformation;
    private static Context context;
    private static String country;
    private static String deviceId;
    private static JSONObject globalProperties;
    private static boolean isCurrentlyTrackingCampaign = false;
    private static String language;
    private static String phoneBrand;
    private static String phoneCarrier;
    private static String phoneManufacturer;
    private static String phoneModel;
    private static long sessionId = -1L;
    private static boolean sessionStarted = false;
    private static Runnable setSessionIdRunnable;
    private static AtomicBoolean updateScheduled = new AtomicBoolean(false);
    private static AtomicBoolean uploadingCurrently = new AtomicBoolean(false);
    private static String userId;
    private static int versionCode;
    private static String versionName;






/*
    static long access$1102(long l)
    {
        sessionId = l;
        return l;
    }

*/








/*
    static String access$502(String s)
    {
        campaignInformation = s;
        return s;
    }

*/


/*
    static boolean access$602(boolean flag)
    {
        isCurrentlyTrackingCampaign = flag;
        return flag;
    }

*/



}
