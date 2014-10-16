// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.*;

// Referenced classes of package com.mixpanel.android.mpmetrics:
//            AnalyticsMessages, SystemInformation, WaitingPeopleRecord, ConfigurationChecker

public class MixpanelAPI
{
    static interface InstanceProcessor
    {

        public abstract void process(MixpanelAPI mixpanelapi);
    }

    public static interface People
    {

        public abstract void append(String s, Object obj);

        public abstract void clearCharges();

        public abstract void clearPushRegistrationId();

        public abstract void deleteUser();

        public abstract String getDistinctId();

        public abstract void identify(String s);

        public abstract void increment(String s, double d);

        public abstract void increment(Map map);

        public abstract void initPushHandling(String s);

        public abstract void set(String s, Object obj);

        public abstract void set(JSONObject jsonobject);

        public abstract void setPushRegistrationId(String s);

        public abstract void trackCharge(double d, JSONObject jsonobject);
    }

    private class PeopleImpl
        implements People
    {

        public void append(String s, Object obj)
        {
            try
            {
                JSONObject jsonobject = new JSONObject();
                jsonobject.put(s, obj);
                append(jsonobject);
                return;
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "Exception appending a property", jsonexception);
            }
        }

        void append(JSONObject jsonobject)
        {
            if(mPeopleDistinctId == null)
            {
                if(mWaitingPeopleRecord == null)
                    mWaitingPeopleRecord = new WaitingPeopleRecord();
                mWaitingPeopleRecord.appendToWaitingPeopleRecord(jsonobject);
                return;
            }
            try
            {
                JSONObject jsonobject1 = stdPeopleMessage("$append", jsonobject);
                mMessages.peopleMessage(jsonobject1);
                return;
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "Can't create append message", jsonexception);
            }
            return;
        }

        public void clearCharges()
        {
            set("$transactions", new JSONArray());
        }

        public void clearPushRegistrationId()
        {
            mStoredPreferences.edit().remove("push_id").commit();
            set("$android_devices", new JSONArray());
        }

        public void deleteUser()
        {
            if(mPeopleDistinctId == null)
                return;
            try
            {
                JSONObject jsonobject = stdPeopleMessage("$add", null);
                mMessages.peopleMessage(jsonobject);
                return;
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "Exception deleting a user");
            }
        }

        public String getDistinctId()
        {
            return mPeopleDistinctId;
        }

        public String getPushId()
        {
            return mStoredPreferences.getString("push_id", null);
        }

        public void identify(String s)
        {
            mPeopleDistinctId = s;
            writeIdentities();
            if(mWaitingPeopleRecord != null)
                pushWaitingPeopleRecord();
        }

        public void increment(String s, double d)
        {
            HashMap hashmap = new HashMap();
            hashmap.put(s, Double.valueOf(d));
            increment(((Map) (hashmap)));
        }

        public void increment(Map map)
        {
            JSONObject jsonobject = new JSONObject(map);
            if(mPeopleDistinctId == null)
            {
                if(mWaitingPeopleRecord == null)
                    mWaitingPeopleRecord = new WaitingPeopleRecord();
                mWaitingPeopleRecord.incrementToWaitingPeopleRecord(map);
                return;
            }
            try
            {
                JSONObject jsonobject1 = stdPeopleMessage("$add", jsonobject);
                mMessages.peopleMessage(jsonobject1);
                return;
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "Exception incrementing properties", jsonexception);
            }
            return;
        }

        public void initPushHandling(String s)
        {
            if(!ConfigurationChecker.checkPushConfiguration(mContext))
            {
                Log.i("MixpanelAPI", "Can't start push notification service. Push notifications will not work.");
                Log.i("MixpanelAPI", (new StringBuilder()).append("See log tagged ").append(ConfigurationChecker.LOGTAG).append(" above for details.").toString());
                return;
            }
            String s1 = getPushId();
            if(s1 == null)
            {
                try
                {
                    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent.putExtra("app", PendingIntent.getBroadcast(mContext, 0, new Intent(), 0));
                    intent.putExtra("sender", s);
                    mContext.startService(intent);
                    return;
                }
                catch(SecurityException securityexception)
                {
                    Log.w("MixpanelAPI", securityexception);
                }
                return;
            } else
            {
                MixpanelAPI.allInstances(s1. new InstanceProcessor() {

                    public void process(MixpanelAPI mixpanelapi)
                    {
                        mixpanelapi.getPeople().setPushRegistrationId(pushId);
                    }

                    final PeopleImpl this$1;
                    final String val$pushId;

            
            {
                this$1 = final_peopleimpl;
                pushId = String.this;
                super();
            }
                }
);
                return;
            }
        }

        public void set(String s, Object obj)
        {
            try
            {
                set((new JSONObject()).put(s, obj));
                return;
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "set", jsonexception);
            }
        }

        public void set(JSONObject jsonobject)
        {
            if(mPeopleDistinctId == null)
            {
                if(mWaitingPeopleRecord == null)
                    mWaitingPeopleRecord = new WaitingPeopleRecord();
                mWaitingPeopleRecord.setOnWaitingPeopleRecord(jsonobject);
                writeIdentities();
                return;
            }
            try
            {
                JSONObject jsonobject1 = stdPeopleMessage("$set", jsonobject);
                mMessages.peopleMessage(jsonobject1);
                return;
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "Exception setting people properties");
            }
            return;
        }

        public void setPushRegistrationId(String s)
        {
            if(mPeopleDistinctId == null)
                return;
            mStoredPreferences.edit().putString("push_id", s).commit();
            try
            {
                JSONObject jsonobject = new JSONObject();
                JSONArray _tmp = JVM INSTR new #84  <Class JSONArray>;
                JSONObject jsonobject1 = stdPeopleMessage("$union", jsonobject.put("$android_devices", JSONArrayInstrumentation.init((new StringBuilder()).append("[").append(s).append("]").toString())));
                mMessages.peopleMessage(jsonobject1);
                return;
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "set push registration id error", jsonexception);
            }
        }

        public JSONObject stdPeopleMessage(String s, JSONObject jsonobject)
            throws JSONException
        {
            JSONObject jsonobject1 = new JSONObject();
            jsonobject1.put(s, jsonobject);
            jsonobject1.put("$token", mToken);
            jsonobject1.put("$distinct_id", mPeopleDistinctId);
            jsonobject1.put("$time", System.currentTimeMillis());
            return jsonobject1;
        }

        public void trackCharge(double d, JSONObject jsonobject)
        {
            JSONObject jsonobject1;
            Date date = new Date();
            Iterator iterator;
            String s;
            try
            {
                jsonobject1 = new JSONObject();
                jsonobject1.put("$amount", d);
                jsonobject1.put("$time", MixpanelAPI.ENGAGE_DATE_FORMAT.format(date));
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "Exception creating new charge", jsonexception);
                return;
            }
            if(jsonobject == null)
                break MISSING_BLOCK_LABEL_108;
            for(iterator = jsonobject.keys(); iterator.hasNext(); jsonobject1.put(s, jsonobject.get(s)))
                s = (String)iterator.next();

            append("$transactions", jsonobject1);
            return;
        }

        final MixpanelAPI this$0;

        private PeopleImpl()
        {
            this$0 = MixpanelAPI.this;
            super();
        }

    }


    MixpanelAPI(Context context, String s)
    {
        mContext = context;
        mToken = s;
        mStoredPreferences = context.getSharedPreferences((new StringBuilder()).append("com.mixpanel.android.mpmetrics.MixpanelAPI_").append(s).toString(), 0);
        readSuperProperties();
        readIdentities();
    }

    static void allInstances(InstanceProcessor instanceprocessor)
    {
        Map map = sInstanceMap;
        map;
        JVM INSTR monitorenter ;
        for(Iterator iterator = sInstanceMap.values().iterator(); iterator.hasNext();)
        {
            Iterator iterator1 = ((Map)iterator.next()).values().iterator();
            while(iterator1.hasNext()) 
                instanceprocessor.process((MixpanelAPI)iterator1.next());
        }

        break MISSING_BLOCK_LABEL_84;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        map;
        JVM INSTR monitorexit ;
    }

    public static void enableFallbackServer(Context context, boolean flag)
    {
        AnalyticsMessages analyticsmessages = AnalyticsMessages.getInstance(context);
        if(flag)
        {
            analyticsmessages.setFallbackHost("http://api.mixpanel.com");
            return;
        } else
        {
            analyticsmessages.setFallbackHost(null);
            return;
        }
    }

    private JSONObject getDefaultEventProperties()
        throws JSONException
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("mp_lib", "android");
        jsonobject.put("$lib_version", "3.3.2");
        jsonobject.put("$os", "Android");
        String s;
        String s1;
        String s2;
        String s3;
        DisplayMetrics displaymetrics;
        String s4;
        Boolean boolean1;
        Boolean boolean2;
        String s5;
        Boolean boolean3;
        String s6;
        BluetoothAdapter bluetoothadapter;
        if(android.os.Build.VERSION.RELEASE == null)
            s = "UNKNOWN";
        else
            s = android.os.Build.VERSION.RELEASE;
        jsonobject.put("$os_version", s);
        if(Build.MANUFACTURER == null)
            s1 = "UNKNOWN";
        else
            s1 = Build.MANUFACTURER;
        jsonobject.put("$manufacturer", s1);
        if(Build.BRAND == null)
            s2 = "UNKNOWN";
        else
            s2 = Build.BRAND;
        jsonobject.put("$brand", s2);
        if(Build.MODEL == null)
            s3 = "UNKNOWN";
        else
            s3 = Build.MODEL;
        jsonobject.put("$model", s3);
        displaymetrics = mSystemInformation.getDisplayMetrics();
        jsonobject.put("$screen_dpi", displaymetrics.densityDpi);
        jsonobject.put("$screen_height", displaymetrics.heightPixels);
        jsonobject.put("$screen_width", displaymetrics.widthPixels);
        s4 = mSystemInformation.getAppVersionName();
        if(s4 != null)
            jsonobject.put("$app_version", s4);
        boolean1 = Boolean.valueOf(mSystemInformation.hasNFC());
        if(boolean1 != null)
            jsonobject.put("$has_nfc", boolean1.booleanValue());
        boolean2 = Boolean.valueOf(mSystemInformation.hasTelephony());
        if(boolean2 != null)
            jsonobject.put("$has_telephone", boolean2.booleanValue());
        s5 = mSystemInformation.getCurrentNetworkOperator();
        if(s5 != null)
            jsonobject.put("$carrier", s5);
        boolean3 = mSystemInformation.isWifiConnected();
        if(boolean3 != null)
            jsonobject.put("$wifi", boolean3.booleanValue());
        if(android.os.Build.VERSION.SDK_INT < 8)
            break MISSING_BLOCK_LABEL_368;
        s6 = "none";
        if(android.os.Build.VERSION.SDK_INT >= 18 && mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le"))
            s6 = "ble";
        else
        if(mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth"))
            s6 = "classic";
        jsonobject.put("$bluetooth_version", s6);
        try
        {
            bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
        }
        catch(SecurityException securityexception)
        {
            return jsonobject;
        }
        if(bluetoothadapter == null)
            break MISSING_BLOCK_LABEL_368;
        jsonobject.put("$bluetooth_enabled", bluetoothadapter.isEnabled());
        return jsonobject;
    }

    public static MixpanelAPI getInstance(Context context, String s)
    {
        Map map = sInstanceMap;
        map;
        JVM INSTR monitorenter ;
        Context context1;
        Object obj;
        context1 = context.getApplicationContext();
        obj = (Map)sInstanceMap.get(s);
        if(obj != null)
            break MISSING_BLOCK_LABEL_52;
        obj = new HashMap();
        sInstanceMap.put(s, obj);
        MixpanelAPI mixpanelapi = (MixpanelAPI)((Map) (obj)).get(context1);
        if(mixpanelapi != null)
            break MISSING_BLOCK_LABEL_95;
        mixpanelapi = new MixpanelAPI(context1, s);
        ((Map) (obj)).put(context1, mixpanelapi);
        map;
        JVM INSTR monitorexit ;
        return mixpanelapi;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void pushWaitingPeopleRecord()
    {
        if(mWaitingPeopleRecord != null && mPeopleDistinctId != null)
        {
            JSONObject jsonobject = mWaitingPeopleRecord.setMessage();
            Map map = mWaitingPeopleRecord.incrementMessage();
            List list = mWaitingPeopleRecord.appendMessages();
            getPeople().set(jsonobject);
            getPeople().increment(map);
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                JSONObject jsonobject1 = (JSONObject)iterator.next();
                Iterator iterator1 = jsonobject1.keys();
                while(iterator1.hasNext()) 
                    try
                    {
                        String s = (String)iterator1.next();
                        Object obj = jsonobject1.get(s);
                        getPeople().append(s, obj);
                    }
                    catch(JSONException jsonexception)
                    {
                        Log.e("MixpanelAPI", "Couldn't send stored append", jsonexception);
                    }
            }

        }
        mWaitingPeopleRecord = null;
        writeIdentities();
    }

    private void readIdentities()
    {
        mEventsDistinctId = mStoredPreferences.getString("events_distinct_id", null);
        mPeopleDistinctId = mStoredPreferences.getString("people_distinct_id", null);
        mWaitingPeopleRecord = null;
        String s = mStoredPreferences.getString("waiting_people_record", null);
        if(s != null)
            try
            {
                mWaitingPeopleRecord = new WaitingPeopleRecord();
                mWaitingPeopleRecord.readFromJSONString(s);
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", (new StringBuilder()).append("Could not interpret waiting people JSON record ").append(s).toString());
            }
        if(mEventsDistinctId == null)
        {
            mEventsDistinctId = UUID.randomUUID().toString();
            writeIdentities();
        }
        if(mWaitingPeopleRecord != null && mPeopleDistinctId != null)
            pushWaitingPeopleRecord();
    }

    private void readSuperProperties()
    {
        String s = mStoredPreferences.getString("super_properties", "{}");
        try
        {
            JSONObject _tmp = JVM INSTR new #197 <Class JSONObject>;
            mSuperProperties = JSONObjectInstrumentation.init(s);
            return;
        }
        catch(JSONException jsonexception)
        {
            Log.e("MixpanelAPI", "Cannot parse stored superProperties");
        }
        mSuperProperties = new JSONObject();
        storeSuperProperties();
    }

    public static void setFlushInterval(Context context, long l)
    {
        AnalyticsMessages.getInstance(context).setFlushInterval(l);
    }

    private void storeSuperProperties()
    {
        JSONObject jsonobject = mSuperProperties;
        String s;
        android.content.SharedPreferences.Editor editor;
        if(!(jsonobject instanceof JSONObject))
            s = jsonobject.toString();
        else
            s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        editor = mStoredPreferences.edit();
        editor.putString("super_properties", s);
        editor.commit();
    }

    private void writeIdentities()
    {
        android.content.SharedPreferences.Editor editor = mStoredPreferences.edit();
        editor.putString("events_distinct_id", mEventsDistinctId);
        editor.putString("people_distinct_id", mPeopleDistinctId);
        if(mWaitingPeopleRecord == null)
            editor.remove("waiting_people_record");
        else
            editor.putString("waiting_people_record", mWaitingPeopleRecord.toJSONString());
        editor.commit();
    }

    void clearPreferences()
    {
        mStoredPreferences.edit().clear().commit();
        readSuperProperties();
        readIdentities();
    }

    public void clearSuperProperties()
    {
        mSuperProperties = new JSONObject();
    }

    public void flush()
    {
        mMessages.postToServer();
    }

    AnalyticsMessages getAnalyticsMessages()
    {
        return AnalyticsMessages.getInstance(mContext);
    }

    public String getDistinctId()
    {
        return mEventsDistinctId;
    }

    public People getPeople()
    {
        return mPeople;
    }

    SystemInformation getSystemInformation()
    {
        return new SystemInformation(mContext);
    }

    public void identify(String s)
    {
        mEventsDistinctId = s;
        writeIdentities();
    }

    public void logPosts()
    {
        mMessages.logPosts();
    }

    public void registerSuperProperties(JSONObject jsonobject)
    {
        for(Iterator iterator = jsonobject.keys(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            try
            {
                mSuperProperties.put(s, jsonobject.get(s));
            }
            catch(JSONException jsonexception)
            {
                Log.e("MixpanelAPI", "Exception registering super property.", jsonexception);
            }
        }

        storeSuperProperties();
    }

    public void registerSuperPropertiesOnce(JSONObject jsonobject)
    {
        Iterator iterator = jsonobject.keys();
        do
        {
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            if(!mSuperProperties.has(s))
                try
                {
                    mSuperProperties.put(s, jsonobject.get(s));
                }
                catch(JSONException jsonexception)
                {
                    Log.e("MixpanelAPI", "Exception registering super property.", jsonexception);
                }
        } while(true);
        storeSuperProperties();
    }

    public void track(String s, JSONObject jsonobject)
    {
        JSONObject jsonobject1;
        JSONObject jsonobject2;
        try
        {
            long l = System.currentTimeMillis() / 1000L;
            jsonobject1 = new JSONObject();
            jsonobject1.put("event", s);
            jsonobject2 = getDefaultEventProperties();
            jsonobject2.put("token", mToken);
            jsonobject2.put("time", l);
            String s3;
            for(Iterator iterator = mSuperProperties.keys(); iterator.hasNext(); jsonobject2.put(s3, mSuperProperties.get(s3)))
                s3 = (String)iterator.next();

        }
        catch(JSONException jsonexception)
        {
            Log.e("MixpanelAPI", (new StringBuilder()).append("Exception tracking event ").append(s).toString(), jsonexception);
            return;
        }
        String s1 = getDistinctId();
        if(s1 == null)
            break MISSING_BLOCK_LABEL_160;
        jsonobject2.put("distinct_id", s1);
        if(jsonobject == null)
            break MISSING_BLOCK_LABEL_209;
        String s2;
        for(Iterator iterator1 = jsonobject.keys(); iterator1.hasNext(); jsonobject2.put(s2, jsonobject.get(s2)))
            s2 = (String)iterator1.next();

        jsonobject1.put("properties", jsonobject2);
        mMessages.eventsMessage(jsonobject1);
        return;
    }

    public void unregisterSuperProperty(String s)
    {
        mSuperProperties.remove(s);
        storeSuperProperties();
    }

    private static final DateFormat ENGAGE_DATE_FORMAT;
    private static final String LOGTAG = "MixpanelAPI";
    public static final String VERSION = "3.3.2";
    private static Map sInstanceMap = new HashMap();
    private final Context mContext;
    private String mEventsDistinctId;
    private final AnalyticsMessages mMessages = getAnalyticsMessages();
    private final PeopleImpl mPeople = new PeopleImpl();
    private String mPeopleDistinctId;
    private final SharedPreferences mStoredPreferences;
    private JSONObject mSuperProperties;
    private final SystemInformation mSystemInformation = getSystemInformation();
    private final String mToken;
    private WaitingPeopleRecord mWaitingPeopleRecord;

    static 
    {
        ENGAGE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        ENGAGE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }



/*
    static String access$102(MixpanelAPI mixpanelapi, String s)
    {
        mixpanelapi.mPeopleDistinctId = s;
        return s;
    }

*/




/*
    static WaitingPeopleRecord access$302(MixpanelAPI mixpanelapi, WaitingPeopleRecord waitingpeoplerecord)
    {
        mixpanelapi.mWaitingPeopleRecord = waitingpeoplerecord;
        return waitingpeoplerecord;
    }

*/






}
