// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.localytics.android;

import android.app.PendingIntent;
import android.content.*;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.*;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

// Referenced classes of package com.localytics.android:
//            DatapointHelper, Constants, LocalyticsProvider

public class LocalyticsSession
{
    private static final class Pair
    {

        public final Object first;
        public final Object second;

        public Pair(Object obj, Object obj1)
        {
            first = obj;
            second = obj1;
        }
    }

    static final class SessionHandler extends Handler
    {

        static int[] $SWITCH_TABLE$android$database$CursorJoiner$Result()
        {
            int ai[] = $SWITCH_TABLE$android$database$CursorJoiner$Result;
            if(ai != null)
                return ai;
            int ai1[] = new int[android.database.CursorJoiner.Result.values().length];
            try
            {
                ai1[android.database.CursorJoiner.Result.BOTH.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            try
            {
                ai1[android.database.CursorJoiner.Result.LEFT.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai1[android.database.CursorJoiner.Result.RIGHT.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            $SWITCH_TABLE$android$database$CursorJoiner$Result = ai1;
            return ai1;
        }

        private void conditionallyAddFlowEvent()
        {
            boolean flag;
            Cursor cursor;
            Cursor cursor1;
            flag = false;
            cursor = null;
            cursor1 = null;
            Iterator iterator;
            cursor = mProvider.query("events", PROJECTION_FLOW_EVENTS, SELECTION_FLOW_EVENTS, SELECTION_ARGS_FLOW_EVENTS, EVENTS_SORT_ORDER);
            cursor1 = mProvider.query("upload_blob_events", PROJECTION_FLOW_BLOBS, null, null, UPLOAD_BLOBS_EVENTS_SORT_ORDER);
            iterator = (new CursorJoiner(cursor, PROJECTION_FLOW_EVENTS, cursor1, PROJECTION_FLOW_BLOBS)).iterator();
_L4:
            boolean flag1 = iterator.hasNext();
            if(!flag1)
            {
                if(cursor != null)
                    cursor.close();
                if(cursor1 != null)
                    cursor1.close();
                if(!flag)
                    tagEvent(LocalyticsSession.FLOW_EVENT, null);
                return;
            }
            int i;
            android.database.CursorJoiner.Result result = (android.database.CursorJoiner.Result)iterator.next();
            i = $SWITCH_TABLE$android$database$CursorJoiner$Result()[result.ordinal()];
            i;
            JVM INSTR tableswitch 1 2: default 164
        //                       1 68
        //                       2 167;
               goto _L1 _L1 _L2
_L1:
            if(true) goto _L4; else goto _L3
_L3:
_L2:
            flag = true;
              goto _L4
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            if(cursor1 != null)
                cursor1.close();
            throw exception;
        }

        static String getFBAttribution(LocalyticsProvider localyticsprovider)
        {
            Cursor cursor = null;
            String s;
            cursor = localyticsprovider.query("info", null, null, null, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_64;
            s = cursor.getString(cursor.getColumnIndexOrThrow("fb_attribution"));
            if(cursor != null)
                cursor.close();
            return s;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            if(cursor != null)
                cursor.close();
            return null;
        }

        static String getInstallationId(LocalyticsProvider localyticsprovider, String s)
        {
            Cursor cursor = null;
            String s1;
            cursor = localyticsprovider.query("api_keys", PROJECTION_GET_INSTALLATION_ID, SELECTION_GET_INSTALLATION_ID, new String[] {
                s
            }, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_76;
            s1 = cursor.getString(cursor.getColumnIndexOrThrow("uuid"));
            if(cursor != null)
                cursor.close();
            return s1;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            if(cursor != null)
                cursor.close();
            return null;
        }

        static long getNumberOfSessions(LocalyticsProvider localyticsprovider)
        {
            Cursor cursor = null;
            int i;
            cursor = localyticsprovider.query("sessions", PROJECTION_GET_NUMBER_OF_SESSIONS, null, null, null);
            i = cursor.getCount();
            long l = i;
            if(cursor != null)
                cursor.close();
            return l;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        static Long getOpenSessionId(LocalyticsProvider localyticsprovider)
        {
            Cursor cursor = null;
            Long long1;
            cursor = localyticsprovider.query("sessions", PROJECTION_GET_OPEN_SESSION_ID_SESSION_ID, null, null, "_id");
            if(!cursor.moveToLast())
                break MISSING_BLOCK_LABEL_143;
            long1 = Long.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow("_id")));
            Cursor cursor1;
            if(cursor != null)
                cursor.close();
            cursor1 = null;
            int i;
            String as[] = PROJECTION_GET_OPEN_SESSION_ID_EVENT_COUNT;
            String s = SELECTION_GET_OPEN_SESSION_ID_EVENT_COUNT;
            String as1[] = new String[2];
            as1[0] = long1.toString();
            as1[1] = LocalyticsSession.CLOSE_EVENT;
            cursor1 = localyticsprovider.query("events", as, s, as1, null);
            if(!cursor1.moveToFirst())
                break MISSING_BLOCK_LABEL_185;
            i = cursor1.getInt(0);
            if(i == 0)
            {
                if(cursor1 != null)
                    cursor1.close();
                return long1;
            }
            break MISSING_BLOCK_LABEL_185;
            if(cursor != null)
                cursor.close();
            return null;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            Exception exception1;
            exception1;
            if(cursor1 != null)
                cursor1.close();
            throw exception1;
            if(cursor1 != null)
                cursor1.close();
            return null;
        }

        static boolean isOptedOut(LocalyticsProvider localyticsprovider, String s)
        {
            Cursor cursor;
            if(localyticsprovider == null)
                throw new IllegalArgumentException("provider cannot be null");
            if(s == null)
                throw new IllegalArgumentException("apiKey cannot be null");
            cursor = null;
            int i;
            cursor = localyticsprovider.query("api_keys", PROJECTION_IS_OPTED_OUT, SELECTION_IS_OPTED_OUT, new String[] {
                s
            }, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_120;
            i = cursor.getInt(cursor.getColumnIndexOrThrow("opt_out"));
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            if(cursor != null)
                cursor.close();
            return flag;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            if(cursor != null)
                cursor.close();
            return false;
        }

        private void openClosedSession(long l)
        {
            String as[];
            Cursor cursor;
            as = new String[1];
            as[0] = Long.toString(l);
            cursor = null;
            LocalyticsProvider localyticsprovider;
            String s;
            cursor = mProvider.query("events", PROJECTION_OPEN_CLOSED_SESSION, SELECTION_OPEN_CLOSED_SESSION, as, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_173;
            localyticsprovider = mProvider;
            s = SELECTION_OPEN_CLOSED_SESSION_ATTRIBUTES;
            if(localyticsprovider instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
            localyticsprovider.delete("attributes", s, as);
_L5:
            LocalyticsProvider localyticsprovider1;
            String s1;
            localyticsprovider1 = mProvider;
            s1 = SELECTION_OPEN_CLOSED_SESSION;
            if(localyticsprovider1 instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
            localyticsprovider1.delete("events", s1, as);
_L6:
            if(cursor != null)
                cursor.close();
            return;
_L2:
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider, "attributes", s, as);
              goto _L5
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
_L4:
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider1, "events", s1, as);
              goto _L6
            openNewSession(null);
              goto _L6
        }

        private void openNewSession(Map map)
        {
            TelephonyManager telephonymanager;
            ContentValues contentvalues;
            String s;
            Cursor cursor;
            telephonymanager = (TelephonyManager)mContext.getSystemService("phone");
            contentvalues = new ContentValues();
            contentvalues.put("api_key_ref", Long.valueOf(mApiKeyId));
            contentvalues.put("session_start_wall_time", Long.valueOf(System.currentTimeMillis()));
            contentvalues.put("uuid", UUID.randomUUID().toString());
            contentvalues.put("app_version", DatapointHelper.getAppVersion(mContext));
            contentvalues.put("android_sdk", Integer.valueOf(Constants.CURRENT_API_LEVEL));
            contentvalues.put("android_version", android.os.Build.VERSION.RELEASE);
            s = DatapointHelper.getAndroidIdHashOrNull(mContext);
            if(s != null)
                break MISSING_BLOCK_LABEL_199;
            cursor = null;
            String s2;
            LocalyticsProvider localyticsprovider = mProvider;
            String s1 = SELECTION_OPEN_NEW_SESSION;
            String as[] = new String[1];
            as[0] = mApiKey;
            cursor = localyticsprovider.query("api_keys", null, s1, as, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_187;
            s2 = cursor.getString(cursor.getColumnIndexOrThrow("uuid"));
            s = s2;
            if(cursor != null)
                cursor.close();
            contentvalues.put("device_android_id_hash", s);
            contentvalues.put("device_android_id", DatapointHelper.getAndroidIdOrNull(mContext));
            contentvalues.put("device_country", telephonymanager.getSimCountryIso());
            contentvalues.put("device_manufacturer", DatapointHelper.getManufacturer());
            contentvalues.put("device_model", Build.MODEL);
            contentvalues.put("device_serial_number_hash", DatapointHelper.getSerialNumberHashOrNull());
            contentvalues.put("device_telephony_id", DatapointHelper.getTelephonyDeviceIdOrNull(mContext));
            contentvalues.putNull("device_telephony_id_hash");
            contentvalues.put("device_wifi_mac_hash", DatapointHelper.getWifiMacHashOrNull(mContext));
            contentvalues.put("locale_country", Locale.getDefault().getCountry());
            contentvalues.put("locale_language", Locale.getDefault().getLanguage());
            contentvalues.put("localytics_library_version", "android_2.16");
            contentvalues.put("iu", getInstallationId(mProvider, mApiKey));
            contentvalues.putNull("latitude");
            contentvalues.putNull("longitude");
            contentvalues.put("network_carrier", telephonymanager.getNetworkOperatorName());
            contentvalues.put("network_country", telephonymanager.getNetworkCountryIso());
            contentvalues.put("network_type", DatapointHelper.getNetworkType(mContext, telephonymanager));
            Exception exception;
            if(mProvider.insert("sessions", contentvalues) == -1L)
            {
                throw new AssertionError("session insert failed");
            } else
            {
                tagEvent(LocalyticsSession.OPEN_EVENT, map);
                LocalyticsProvider.deleteOldFiles(mContext);
                return;
            }
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        static void preUploadBuildBlobs(LocalyticsProvider localyticsprovider)
        {
            HashSet hashset;
            Cursor cursor;
            Cursor cursor1;
            hashset = new HashSet();
            cursor = null;
            cursor1 = null;
            int i;
            Iterator iterator;
            cursor = localyticsprovider.query("events", PROJECTION_UPLOAD_EVENTS, null, null, EVENTS_SORT_ORDER);
            cursor1 = localyticsprovider.query("upload_blob_events", PROJECTION_UPLOAD_BLOBS, null, null, UPLOAD_BLOBS_EVENTS_SORT_ORDER);
            i = cursor.getColumnIndexOrThrow("_id");
            iterator = (new CursorJoiner(cursor, JOINER_ARG_UPLOAD_EVENTS_COLUMNS, cursor1, PROJECTION_UPLOAD_BLOBS)).iterator();
_L10:
            boolean flag = iterator.hasNext();
            if(flag) goto _L2; else goto _L1
_L1:
            if(cursor != null)
                cursor.close();
            if(cursor1 != null)
                cursor1.close();
            if(hashset.size() <= 0) goto _L4; else goto _L3
_L3:
            ContentValues contentvalues;
            Long long1;
            Iterator iterator1;
            contentvalues = new ContentValues();
            contentvalues.put("uuid", UUID.randomUUID().toString());
            long1 = Long.valueOf(localyticsprovider.insert("upload_blobs", contentvalues));
            contentvalues.clear();
            iterator1 = hashset.iterator();
_L11:
            if(iterator1.hasNext()) goto _L6; else goto _L5
_L5:
            contentvalues.put("processed_in_blob", long1);
            String s = SELECTION_UPLOAD_NULL_BLOBS;
            Exception exception;
            android.database.CursorJoiner.Result result;
            Long long2;
            if(!(localyticsprovider instanceof SQLiteDatabase))
                localyticsprovider.update("event_history", contentvalues, s, null);
            else
                SQLiteInstrumentation.update((SQLiteDatabase)localyticsprovider, "event_history", contentvalues, s, null);
            contentvalues.clear();
_L4:
            return;
_L2:
            result = (android.database.CursorJoiner.Result)iterator.next();
            $SWITCH_TABLE$android$database$CursorJoiner$Result()[result.ordinal()];
            JVM INSTR tableswitch 1 2: default 422
        //                       1 74
        //                       2 260;
               goto _L7 _L7 _L8
_L7:
            if(true) goto _L10; else goto _L9
_L9:
_L8:
            if(!LocalyticsSession.CLOSE_EVENT.equals(cursor.getString(cursor.getColumnIndexOrThrow("event_name"))) || System.currentTimeMillis() - cursor.getLong(cursor.getColumnIndexOrThrow("wall_time")) >= 15000L)
                hashset.add(Long.valueOf(cursor.getLong(i)));
              goto _L10
            exception;
            if(cursor != null)
                cursor.close();
            if(cursor1 != null)
                cursor1.close();
            throw exception;
_L6:
            long2 = (Long)iterator1.next();
            contentvalues.put("upload_blobs_key_ref", long1);
            contentvalues.put("events_key_ref", long2);
            localyticsprovider.insert("upload_blob_events", contentvalues);
            contentvalues.clear();
              goto _L11
        }

        void close(Map map)
        {
            if(getOpenSessionId(mProvider) == null)
            {
                return;
            } else
            {
                tagEvent(LocalyticsSession.CLOSE_EVENT, map);
                return;
            }
        }

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 0 10: default 68
        //                       0 79
        //                       1 112
        //                       2 129
        //                       3 146
        //                       4 337
        //                       5 364
        //                       6 84
        //                       7 207
        //                       8 234
        //                       9 283
        //                       10 310;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
            throw new RuntimeException("Fell through switch statement");
_L2:
            init();
            return;
_L8:
            Runnable runnable;
            String s;
            String s1;
            Pair pair;
            final String key;
            String s2;
            String s3;
            Triple triple;
            final String event;
            final Map attributes;
            Long long1;
            boolean flag;
            if(message.arg1 == 0)
                flag = false;
            else
                flag = true;
            try
            {
                mProvider.runBatchTransaction(flag. new Runnable() {

                    public void run()
                    {
                        optOut(isOptingOut);
                    }

                    final SessionHandler this$1;
                    private final boolean val$isOptingOut;

            
            {
                this$1 = final_sessionhandler;
                isOptingOut = Z.this;
                super();
            }
                }
);
                return;
            }
            catch(Exception exception)
            {
                return;
            }
_L3:
            mProvider.runBatchTransaction(message. new Runnable() {

                public void run()
                {
                    open(false, (Map)msg.obj);
                }

                final SessionHandler this$1;
                private final Message val$msg;

            
            {
                this$1 = final_sessionhandler;
                msg = Message.this;
                super();
            }
            }
);
            return;
_L4:
            mProvider.runBatchTransaction(message. new Runnable() {

                public void run()
                {
                    close((Map)msg.obj);
                }

                final SessionHandler this$1;
                private final Message val$msg;

            
            {
                this$1 = final_sessionhandler;
                msg = Message.this;
                super();
            }
            }
);
            return;
_L5:
            triple = (Triple)message.obj;
            event = (String)triple.first;
            attributes = (Map)triple.second;
            long1 = (Long)triple.third;
            mProvider.runBatchTransaction(long1. new Runnable() {

                public void run()
                {
                    if(SessionHandler.getOpenSessionId(mProvider) != null)
                    {
                        tagEvent(event, attributes, clv);
                        return;
                    }
                    if(attributes != null) goto _L2; else goto _L1
_L1:
                    Object obj = null;
_L4:
                    open(false, ((Map) (obj)));
                    tagEvent(event, attributes, clv);
                    close(((Map) (obj)));
                    return;
_L2:
                    if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9) || attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10))
                    {
                        obj = new TreeMap();
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9));
                        if(attributes.containsKey(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10))
                            ((Map) (obj)).put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10, (String)attributes.get(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10));
                    } else
                    {
                        obj = null;
                    }
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final SessionHandler this$1;
                private final Map val$attributes;
                private final Long val$clv;
                private final String val$event;

            
            {
                this$1 = final_sessionhandler;
                event = s;
                attributes = map;
                clv = Long.this;
                super();
            }
            }
);
            return;
_L9:
            s3 = (String)message.obj;
            mProvider.runBatchTransaction(s3. new Runnable() {

                public void run()
                {
                    tagScreen(screen);
                }

                final SessionHandler this$1;
                private final String val$screen;

            
            {
                this$1 = final_sessionhandler;
                screen = String.this;
                super();
            }
            }
);
            return;
_L10:
            pair = (Pair)message.obj;
            key = (String)pair.first;
            s2 = (String)pair.second;
            mProvider.runBatchTransaction(s2. new Runnable() {

                public void run()
                {
                    setIdentifier(key, value);
                }

                final SessionHandler this$1;
                private final String val$key;
                private final String val$value;

            
            {
                this$1 = final_sessionhandler;
                key = s;
                value = String.this;
                super();
            }
            }
);
            return;
_L11:
            s1 = (String)message.obj;
            mProvider.runBatchTransaction(s1. new Runnable() {

                public void run()
                {
                    Cursor cursor = null;
                    boolean flag;
                    cursor = mProvider.query("info", null, null, null, null);
                    flag = cursor.moveToFirst();
                    String s;
                    String s1;
                    s = null;
                    s1 = null;
                    if(!flag)
                        break MISSING_BLOCK_LABEL_72;
                    String s2;
                    s1 = cursor.getString(cursor.getColumnIndexOrThrow("registration_version"));
                    s2 = cursor.getString(cursor.getColumnIndexOrThrow("registration_id"));
                    s = s2;
                    if(cursor != null)
                        cursor.close();
                    String s3 = DatapointHelper.getAppVersion(mContext);
                    if(s == null || TextUtils.isEmpty(s) || !s3.equals(s1))
                    {
                        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                        intent.putExtra("app", PendingIntent.getBroadcast(mContext, 0, new Intent(), 0));
                        intent.putExtra("sender", senderId);
                        mContext.startService(intent);
                    }
                    return;
                    Exception exception;
                    exception;
                    if(cursor != null)
                        cursor.close();
                    throw exception;
                }

                final SessionHandler this$1;
                private final String val$senderId;

            
            {
                this$1 = final_sessionhandler;
                senderId = String.this;
                super();
            }
            }
);
            return;
_L12:
            s = (String)message.obj;
            mProvider.runBatchTransaction(s. new Runnable() {

                public void run()
                {
                    setPushRegistrationId(pushRegId);
                }

                final SessionHandler this$1;
                private final String val$pushRegId;

            
            {
                this$1 = final_sessionhandler;
                pushRegId = String.this;
                super();
            }
            }
);
            return;
_L6:
            runnable = (Runnable)message.obj;
            mProvider.runBatchTransaction(runnable. new Runnable() {

                public void run()
                {
                    upload(callback);
                }

                final SessionHandler this$1;
                private final Runnable val$callback;

            
            {
                this$1 = final_sessionhandler;
                callback = Runnable.this;
                super();
            }
            }
);
            return;
_L7:
            LocalyticsSession.sIsUploadingMap.put(mApiKey, Boolean.FALSE);
            return;
        }

        void init()
        {
            Cursor cursor;
            mProvider = LocalyticsProvider.getInstance(mContext, mApiKey);
            cursor = null;
            LocalyticsProvider localyticsprovider = mProvider;
            String as[] = PROJECTION_INIT_API_KEY;
            String s = SELECTION_INIT_API_KEY;
            String as1[] = new String[1];
            as1[0] = mApiKey;
            cursor = localyticsprovider.query("api_keys", as, s, as1, null);
            if(!cursor.moveToFirst()) goto _L2; else goto _L1
_L1:
            mApiKeyId = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
_L4:
            if(cursor != null)
                cursor.close();
            if(!LocalyticsSession.sIsUploadingMap.containsKey(mApiKey))
                LocalyticsSession.sIsUploadingMap.put(mApiKey, Boolean.FALSE);
            mUploadHandler = new UploadHandler(mContext, this, mApiKey, getInstallationId(mProvider, mApiKey), LocalyticsSession.sUploadHandlerThread.getLooper());
            return;
_L2:
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("api_key", mApiKey);
            contentvalues.put("uuid", UUID.randomUUID().toString());
            contentvalues.put("opt_out", Boolean.FALSE);
            contentvalues.put("created_time", Long.valueOf(System.currentTimeMillis()));
            mApiKeyId = mProvider.insert("api_keys", contentvalues);
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        void open(boolean flag, Map map)
        {
_L2:
            return;
            if(getOpenSessionId(mProvider) != null || isOptedOut(mProvider, mApiKey)) goto _L2; else goto _L1
_L1:
            long l;
            Cursor cursor;
            Cursor cursor1;
            l = -1L;
            cursor = null;
            cursor1 = null;
            int i;
            Iterator iterator;
            LocalyticsProvider localyticsprovider = mProvider;
            String as[] = PROJECTION_OPEN_EVENT_ID;
            String s = SELECTION_OPEN;
            String as1[] = new String[2];
            as1[0] = LocalyticsSession.CLOSE_EVENT;
            as1[1] = Long.toString(System.currentTimeMillis() - 15000L);
            cursor = localyticsprovider.query("events", as, s, as1, EVENTS_SORT_ORDER);
            cursor1 = mProvider.query("upload_blob_events", PROJECTION_OPEN_BLOB_EVENTS, null, null, UPLOAD_BLOBS_EVENTS_SORT_ORDER);
            i = cursor.getColumnIndexOrThrow("_id");
            CursorJoiner cursorjoiner = new CursorJoiner(cursor, PROJECTION_OPEN_EVENT_ID, cursor1, PROJECTION_OPEN_BLOB_EVENTS);
            iterator = cursorjoiner.iterator();
_L5:
            boolean flag1 = iterator.hasNext();
            if(flag1) goto _L4; else goto _L3
_L4:
            result = (android.database.CursorJoiner.Result)iterator.next();
            $SWITCH_TABLE$android$database$CursorJoiner$Result()[result.ordinal()];
            JVM INSTR tableswitch 1 2: default 881
        //                       1 154
        //                       2 248;
               goto _L5 _L5 _L6
_L6:
            if(-1L == l) goto _L8; else goto _L7
_L7:
            l1 = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
            if(l1 > l)
                l = l1;
_L8:
            if(-1L != l) goto _L5; else goto _L9
_L9:
            l2 = cursor.getLong(i);
            l = l2;
              goto _L5
            exception;
            if(cursor != null)
                cursor.close();
            if(cursor1 != null)
                cursor1.close();
            throw exception;
_L3:
            Cursor cursor2;
            if(cursor != null)
                cursor.close();
            if(cursor1 != null)
                cursor1.close();
            Exception exception;
            android.database.CursorJoiner.Result result;
            long l1;
            long l2;
            if(-1L != l)
            {
                openClosedSession(l);
                return;
            }
            cursor2 = null;
            cursor2 = mProvider.query("sessions", PROJECTION_OPEN_SESSIONS, null, null, "_id");
            if(!cursor2.moveToLast()) goto _L11; else goto _L10
_L10:
            long l3;
            long l4;
            int j = cursor2.getColumnIndexOrThrow("session_start_wall_time");
            l3 = cursor2.getLong(j);
            l4 = System.currentTimeMillis();
            if(l3 < l4 - 15000L) goto _L13; else goto _L12
_L12:
            if(cursor2 != null)
            {
                cursor2.close();
                return;
            }
              goto _L2
_L13:
            Cursor cursor3 = null;
            String as2[];
            int k = cursor2.getColumnIndexOrThrow("_id");
            as2 = (new String[] {
                Long.toString(cursor2.getLong(k))
            });
            cursor3 = mProvider.query("events", PROJECTION_OPEN_DELETE_EMPTIES_EVENT_ID, SELECTION_OPEN_DELETE_EMPTIES_EVENTS_SESSION_KEY_REF, as2, null);
            if(cursor3.getCount() != 0) goto _L15; else goto _L14
_L14:
            LinkedList linkedlist = new LinkedList();
            Cursor cursor4 = null;
            cursor4 = mProvider.query("event_history", PROJECTION_OPEN_DELETE_EMPTIES_PROCESSED_IN_BLOB, SELECTION_OPEN_DELETE_EMPTIES_EVENT_HISTORY_SESSION_KEY_REF, as2, null);
_L28:
            boolean flag2 = cursor4.moveToNext();
            if(flag2) goto _L17; else goto _L16
_L16:
            if(cursor4 == null) goto _L19; else goto _L18
_L18:
            cursor4.close();
_L19:
            LocalyticsProvider localyticsprovider1;
            String s1;
            localyticsprovider1 = mProvider;
            s1 = SELECTION_OPEN_DELETE_EMPTIES_EVENT_HISTORY_SESSION_KEY_REF;
            if(localyticsprovider1 instanceof SQLiteDatabase) goto _L21; else goto _L20
_L20:
            localyticsprovider1.delete("event_history", s1, as2);
_L33:
            Iterator iterator1 = linkedlist.iterator();
_L36:
            if(iterator1.hasNext()) goto _L23; else goto _L22
_L22:
            LocalyticsProvider localyticsprovider3;
            String s3;
            localyticsprovider3 = mProvider;
            s3 = SELECTION_OPEN_DELETE_EMPTIES_SESSIONS_ID;
            if(localyticsprovider3 instanceof SQLiteDatabase) goto _L25; else goto _L24
_L24:
            localyticsprovider3.delete("sessions", s3, as2);
_L15:
            if(cursor3 == null) goto _L11; else goto _L26
_L26:
            cursor3.close();
_L11:
            if(cursor2 != null)
                cursor2.close();
            if(!flag && getNumberOfSessions(mProvider) >= 10L) goto _L2; else goto _L27
_L27:
            openNewSession(map);
            return;
_L17:
            linkedlist.add(Long.valueOf(cursor4.getLong(cursor4.getColumnIndexOrThrow("processed_in_blob"))));
              goto _L28
            Exception exception3;
            exception3;
            if(cursor4 == null) goto _L30; else goto _L29
_L29:
            cursor4.close();
_L30:
            throw exception3;
            Exception exception2;
            exception2;
            if(cursor3 == null) goto _L32; else goto _L31
_L31:
            cursor3.close();
_L32:
            throw exception2;
            Exception exception1;
            exception1;
            if(cursor2 != null)
                cursor2.close();
            throw exception1;
_L21:
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider1, "event_history", s1, as2);
              goto _L33
_L23:
            LocalyticsProvider localyticsprovider2;
            String s2;
            String as3[];
            long l5 = ((Long)iterator1.next()).longValue();
            localyticsprovider2 = mProvider;
            s2 = SELECTION_OPEN_DELETE_EMPTIES_UPLOAD_BLOBS_ID;
            as3 = new String[1];
            as3[0] = Long.toString(l5);
            if(localyticsprovider2 instanceof SQLiteDatabase) goto _L35; else goto _L34
_L34:
            localyticsprovider2.delete("upload_blobs", s2, as3);
              goto _L36
_L35:
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider2, "upload_blobs", s2, as3);
              goto _L36
_L25:
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider3, "sessions", s3, as2);
              goto _L15
        }

        void optOut(boolean flag)
        {
            if(isOptedOut(mProvider, mApiKey) == flag)
                return;
            ContentValues contentvalues;
            LocalyticsProvider localyticsprovider;
            String s1;
            String as[];
            if(getOpenSessionId(mProvider) == null)
            {
                open(true, null);
                String s2;
                if(flag)
                    s2 = LocalyticsSession.OPT_OUT_EVENT;
                else
                    s2 = LocalyticsSession.OPT_IN_EVENT;
                tagEvent(s2, null);
                close(null);
            } else
            {
                String s;
                if(flag)
                    s = LocalyticsSession.OPT_OUT_EVENT;
                else
                    s = LocalyticsSession.OPT_IN_EVENT;
                tagEvent(s, null);
            }
            contentvalues = new ContentValues();
            contentvalues.put("opt_out", Boolean.valueOf(flag));
            localyticsprovider = mProvider;
            s1 = SELECTION_OPT_IN_OUT;
            as = new String[1];
            as[0] = Long.toString(mApiKeyId);
            if(!(localyticsprovider instanceof SQLiteDatabase))
            {
                localyticsprovider.update("api_keys", contentvalues, s1, as);
                return;
            } else
            {
                SQLiteInstrumentation.update((SQLiteDatabase)localyticsprovider, "api_keys", contentvalues, s1, as);
                return;
            }
        }

        void setIdentifier(String s, String s1)
        {
            Cursor cursor = null;
            cursor = mProvider.query("identifiers", PROJECTION_SET_IDENTIFIER, SELECTION_SET_IDENTIFIER, new String[] {
                s
            }, null);
            if(!cursor.moveToFirst()) goto _L2; else goto _L1
_L1:
            if(s1 != null) goto _L4; else goto _L3
_L3:
            LocalyticsProvider localyticsprovider;
            String s2;
            String as[];
            localyticsprovider = mProvider;
            s2 = String.format("%s = ?", new Object[] {
                "key"
            });
            as = new String[1];
            as[0] = cursor.getString(cursor.getColumnIndexOrThrow("key"));
            if(localyticsprovider instanceof SQLiteDatabase) goto _L6; else goto _L5
_L5:
            localyticsprovider.delete("identifiers", s2, as);
_L7:
            if(cursor != null)
                cursor.close();
            return;
_L6:
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider, "identifiers", s2, as);
              goto _L7
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
_L4:
            ContentValues contentvalues;
            LocalyticsProvider localyticsprovider1;
            String s3;
            String as1[];
label0:
            {
                contentvalues = new ContentValues();
                contentvalues.put("key", s);
                contentvalues.put("value", s1);
                localyticsprovider1 = mProvider;
                s3 = SELECTION_SET_IDENTIFIER;
                as1 = (new String[] {
                    s
                });
                if(localyticsprovider1 instanceof SQLiteDatabase)
                    break label0;
                localyticsprovider1.update("identifiers", contentvalues, s3, as1);
            }
              goto _L7
            SQLiteInstrumentation.update((SQLiteDatabase)localyticsprovider1, "identifiers", contentvalues, s3, as1);
              goto _L7
_L2:
            if(s1 == null) goto _L7; else goto _L8
_L8:
            ContentValues contentvalues1 = new ContentValues();
            contentvalues1.put("key", s);
            contentvalues1.put("value", s1);
            mProvider.insert("identifiers", contentvalues1);
              goto _L7
        }

        void setPushRegistrationId(String s)
        {
            ContentValues contentvalues = new ContentValues();
            if(s == null)
                s = "";
            contentvalues.put("registration_id", s);
            contentvalues.put("registration_version", DatapointHelper.getAppVersion(mContext));
            LocalyticsProvider localyticsprovider = mProvider;
            if(!(localyticsprovider instanceof SQLiteDatabase))
            {
                localyticsprovider.update("info", contentvalues, null, null);
                return;
            } else
            {
                SQLiteInstrumentation.update((SQLiteDatabase)localyticsprovider, "info", contentvalues, null, null);
                return;
            }
        }

        void tagEvent(String s, Map map)
        {
            tagEvent(s, map, null);
        }

        void tagEvent(String s, Map map, Long long1)
        {
            Long long2 = getOpenSessionId(mProvider);
            if(long2 != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            long l;
            Cursor cursor;
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("session_key_ref", long2);
            contentvalues.put("uuid", UUID.randomUUID().toString());
            contentvalues.put("event_name", s);
            contentvalues.put("real_time", Long.valueOf(SystemClock.elapsedRealtime()));
            contentvalues.put("wall_time", Long.valueOf(System.currentTimeMillis()));
            LocalyticsProvider localyticsprovider;
            String as[];
            String s2;
            String as1[];
            if(long1 != null)
                contentvalues.put("clv_increase", long1);
            else
                contentvalues.put("clv_increase", Integer.valueOf(0));
            if(LocalyticsSession.lastLocation != null)
            {
                contentvalues.put("event_lat", Double.valueOf(LocalyticsSession.lastLocation.getLatitude()));
                contentvalues.put("event_lng", Double.valueOf(LocalyticsSession.lastLocation.getLongitude()));
            }
            if(!LocalyticsSession.OPEN_EVENT.equals(s))
                break MISSING_BLOCK_LABEL_243;
            cursor = null;
            localyticsprovider = mProvider;
            as = PROJECTION_TAG_EVENT;
            s2 = SELECTION_TAG_EVENT;
            as1 = new String[1];
            as1[0] = long2.toString();
            cursor = localyticsprovider.query("sessions", as, s2, as1, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_292;
            contentvalues.put("wall_time", Long.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow("session_start_wall_time"))));
            if(cursor != null)
                cursor.close();
            l = mProvider.insert("events", contentvalues);
            if(-1L == l)
                throw new RuntimeException("Inserting event failed");
            break MISSING_BLOCK_LABEL_320;
            throw new AssertionError("During tag of open event, session didn't exist");
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            ContentValues contentvalues1;
            String s1;
            int i;
            Iterator iterator;
            if(map == null)
                continue; /* Loop/switch isn't completed */
            contentvalues1 = new ContentValues();
            Object aobj[] = new Object[2];
            aobj[0] = mContext.getPackageName();
            aobj[1] = "";
            s1 = String.format("%s:%s", aobj);
            i = 0;
            iterator = map.entrySet().iterator();
_L4:
            if(iterator.hasNext())
                break MISSING_BLOCK_LABEL_524;
            if(LocalyticsSession.OPEN_EVENT.equals(s) || LocalyticsSession.CLOSE_EVENT.equals(s) || LocalyticsSession.OPT_IN_EVENT.equals(s) || LocalyticsSession.OPT_OUT_EVENT.equals(s) || LocalyticsSession.FLOW_EVENT.equals(s)) goto _L1; else goto _L3
_L3:
            ContentValues contentvalues2 = new ContentValues();
            contentvalues2.put("name", s.substring(1 + mContext.getPackageName().length(), s.length()));
            contentvalues2.put("type", Integer.valueOf(0));
            contentvalues2.put("session_key_ref", long2);
            contentvalues2.putNull("processed_in_blob");
            mProvider.insert("event_history", contentvalues2);
            conditionallyAddFlowEvent();
            return;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(!((String)entry.getKey()).startsWith(s1) || ++i <= 50)
            {
                contentvalues1.put("events_key_ref", Long.valueOf(l));
                contentvalues1.put("attribute_key", (String)entry.getKey());
                contentvalues1.put("attribute_value", (String)entry.getValue());
                if(-1L == mProvider.insert("attributes", contentvalues1))
                    throw new AssertionError("Inserting attribute failed");
                contentvalues1.clear();
            }
              goto _L4
        }

        void tagScreen(String s)
        {
            Long long1 = getOpenSessionId(mProvider);
            if(long1 != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            Cursor cursor = null;
            boolean flag;
            LocalyticsProvider localyticsprovider = mProvider;
            String as[] = PROJECTION_TAG_SCREEN;
            String s1 = SELECTION_TAG_SCREEN;
            String as1[] = new String[2];
            as1[0] = Integer.toString(1);
            as1[1] = long1.toString();
            cursor = localyticsprovider.query("event_history", as, s1, as1, SORT_ORDER_TAG_SCREEN);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_131;
            flag = s.equals(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            if(!flag)
                break MISSING_BLOCK_LABEL_131;
            if(cursor == null) goto _L1; else goto _L3
_L3:
            cursor.close();
            return;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            if(cursor != null)
                cursor.close();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("name", s);
            contentvalues.put("type", Integer.valueOf(1));
            contentvalues.put("session_key_ref", long1);
            contentvalues.putNull("processed_in_blob");
            mProvider.insert("event_history", contentvalues);
            conditionallyAddFlowEvent();
            return;
        }

        void upload(Runnable runnable)
        {
            if(((Boolean)LocalyticsSession.sIsUploadingMap.get(mApiKey)).booleanValue())
            {
                mUploadHandler.sendMessage(mUploadHandler.obtainMessage(2, runnable));
            } else
            {
                try
                {
                    preUploadBuildBlobs(mProvider);
                    LocalyticsSession.sIsUploadingMap.put(mApiKey, Boolean.TRUE);
                    mUploadHandler.sendMessage(mUploadHandler.obtainMessage(1, runnable));
                    return;
                }
                catch(Exception exception)
                {
                    LocalyticsSession.sIsUploadingMap.put(mApiKey, Boolean.FALSE);
                }
                if(runnable != null)
                {
                    (new Thread(runnable, "upload_callback")).start();
                    return;
                }
            }
        }

        private static int $SWITCH_TABLE$android$database$CursorJoiner$Result[];
        private static final String EVENTS_SORT_ORDER = String.format("CAST(%s as TEXT)", new Object[] {
            "_id"
        });
        private static final String JOINER_ARG_UPLOAD_EVENTS_COLUMNS[] = {
            "_id"
        };
        public static final int MESSAGE_CLOSE = 2;
        public static final int MESSAGE_INIT = 0;
        public static final int MESSAGE_OPEN = 1;
        public static final int MESSAGE_OPT_OUT = 6;
        public static final int MESSAGE_REGISTER_PUSH = 9;
        public static final int MESSAGE_SET_IDENTIFIER = 8;
        public static final int MESSAGE_SET_PUSH_REGID = 10;
        public static final int MESSAGE_TAG_EVENT = 3;
        public static final int MESSAGE_TAG_SCREEN = 7;
        public static final int MESSAGE_UPLOAD = 4;
        public static final int MESSAGE_UPLOAD_CALLBACK = 5;
        private static final String PROJECTION_FLOW_BLOBS[] = {
            "events_key_ref"
        };
        private static final String PROJECTION_FLOW_EVENTS[] = {
            "_id"
        };
        private static final String PROJECTION_GET_INSTALLATION_ID[] = {
            "uuid"
        };
        private static final String PROJECTION_GET_NUMBER_OF_SESSIONS[] = {
            "_id"
        };
        private static final String PROJECTION_GET_OPEN_SESSION_ID_EVENT_COUNT[] = {
            "_count"
        };
        private static final String PROJECTION_GET_OPEN_SESSION_ID_SESSION_ID[] = {
            "_id"
        };
        private static final String PROJECTION_INIT_API_KEY[] = {
            "_id", "opt_out", "uuid"
        };
        private static final String PROJECTION_IS_OPTED_OUT[] = {
            "opt_out"
        };
        private static final String PROJECTION_OPEN_BLOB_EVENTS[] = {
            "events_key_ref"
        };
        private static final String PROJECTION_OPEN_CLOSED_SESSION[] = {
            "session_key_ref"
        };
        private static final String PROJECTION_OPEN_DELETE_EMPTIES_EVENT_ID[] = {
            "_id"
        };
        private static final String PROJECTION_OPEN_DELETE_EMPTIES_PROCESSED_IN_BLOB[] = {
            "processed_in_blob"
        };
        private static final String PROJECTION_OPEN_EVENT_ID[] = {
            "_id"
        };
        private static final String PROJECTION_OPEN_SESSIONS[] = {
            "_id", "session_start_wall_time"
        };
        private static final String PROJECTION_SET_IDENTIFIER[] = {
            "value"
        };
        private static final String PROJECTION_TAG_EVENT[] = {
            "session_start_wall_time"
        };
        private static final String PROJECTION_TAG_SCREEN[] = {
            "name"
        };
        private static final String PROJECTION_UPLOAD_BLOBS[] = {
            "events_key_ref"
        };
        private static final String PROJECTION_UPLOAD_EVENTS[] = {
            "_id", "event_name", "wall_time"
        };
        private static final String SELECTION_ARGS_FLOW_EVENTS[];
        private static final String SELECTION_FLOW_EVENTS = String.format("%s = ?", new Object[] {
            "event_name"
        });
        private static final String SELECTION_GET_INSTALLATION_ID = String.format("%s = ?", new Object[] {
            "api_key"
        });
        private static final String SELECTION_GET_OPEN_SESSION_ID_EVENT_COUNT = String.format("%s = ? AND %s = ?", new Object[] {
            "session_key_ref", "event_name"
        });
        private static final String SELECTION_INIT_API_KEY = String.format("%s = ?", new Object[] {
            "api_key"
        });
        private static final String SELECTION_IS_OPTED_OUT = String.format("%s = ?", new Object[] {
            "api_key"
        });
        private static final String SELECTION_OPEN = String.format("%s = ? AND %s >= ?", new Object[] {
            "event_name", "wall_time"
        });
        private static final String SELECTION_OPEN_CLOSED_SESSION = String.format("%s = ?", new Object[] {
            "_id"
        });
        private static final String SELECTION_OPEN_CLOSED_SESSION_ATTRIBUTES = String.format("%s = ?", new Object[] {
            "events_key_ref"
        });
        private static final String SELECTION_OPEN_DELETE_EMPTIES_EVENTS_SESSION_KEY_REF = String.format("%s = ?", new Object[] {
            "session_key_ref"
        });
        private static final String SELECTION_OPEN_DELETE_EMPTIES_EVENT_HISTORY_SESSION_KEY_REF = String.format("%s = ?", new Object[] {
            "session_key_ref"
        });
        private static final String SELECTION_OPEN_DELETE_EMPTIES_SESSIONS_ID = String.format("%s = ?", new Object[] {
            "_id"
        });
        private static final String SELECTION_OPEN_DELETE_EMPTIES_UPLOAD_BLOBS_ID = String.format("%s = ?", new Object[] {
            "_id"
        });
        private static final String SELECTION_OPEN_NEW_SESSION = String.format("%s = ?", new Object[] {
            "api_key"
        });
        private static final String SELECTION_OPT_IN_OUT = String.format("%s = ?", new Object[] {
            "_id"
        });
        private static final String SELECTION_SET_IDENTIFIER = String.format("%s = ?", new Object[] {
            "key"
        });
        private static final String SELECTION_TAG_EVENT = String.format("%s = ?", new Object[] {
            "_id"
        });
        private static final String SELECTION_TAG_SCREEN = String.format("%s = ? AND %s = ?", new Object[] {
            "type", "session_key_ref"
        });
        private static final String SELECTION_UPLOAD_NULL_BLOBS = String.format("%s IS NULL", new Object[] {
            "processed_in_blob"
        });
        private static final String SORT_ORDER_TAG_SCREEN = String.format("%s DESC", new Object[] {
            "_id"
        });
        private static final String UPLOAD_BLOBS_EVENTS_SORT_ORDER = String.format("CAST(%s AS TEXT)", new Object[] {
            "events_key_ref"
        });
        private final String mApiKey;
        private long mApiKeyId;
        private final Context mContext;
        protected LocalyticsProvider mProvider;
        private Handler mUploadHandler;

        static 
        {
            String as[] = new String[1];
            as[0] = LocalyticsSession.FLOW_EVENT;
            SELECTION_ARGS_FLOW_EVENTS = as;
        }


        public SessionHandler(Context context, String s, Looper looper)
        {
            super(looper);
            if(context == null)
                throw new IllegalArgumentException("context cannot be null");
            if(TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("key cannot be null or empty");
            } else
            {
                mContext = context;
                mApiKey = s;
                return;
            }
        }
    }

    private static final class Triple
    {

        public final Object first;
        public final Object second;
        public final Object third;

        public Triple(Object obj, Object obj1, Object obj2)
        {
            first = obj;
            second = obj1;
            third = obj2;
        }
    }

    static final class UploadHandler extends Handler
    {

        static JSONObject convertAttributesToJson(LocalyticsProvider localyticsprovider, Context context, long l)
            throws JSONException
        {
            Cursor cursor = null;
            int i;
            String s = String.format("%s = ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ?", new Object[] {
                "events_key_ref", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", 
                "attribute_key"
            });
            String as[] = new String[11];
            as[0] = Long.toString(l);
            as[1] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1;
            as[2] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2;
            as[3] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3;
            as[4] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4;
            as[5] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5;
            as[6] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6;
            as[7] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7;
            as[8] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8;
            as[9] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9;
            as[10] = LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10;
            cursor = localyticsprovider.query("attributes", null, s, as, null);
            i = cursor.getCount();
            if(i != 0) goto _L2; else goto _L1
_L1:
            JSONObject jsonobject;
            jsonobject = null;
            if(cursor != null)
                cursor.close();
_L4:
            return jsonobject;
_L2:
            int j;
            int k;
            jsonobject = new JSONObject();
            j = cursor.getColumnIndexOrThrow("attribute_key");
            k = cursor.getColumnIndexOrThrow("attribute_value");
_L5:
            boolean flag = cursor.moveToNext();
            if(flag)
                break MISSING_BLOCK_LABEL_271;
            if(cursor == null) goto _L4; else goto _L3
_L3:
            cursor.close();
            return jsonobject;
            String s1 = cursor.getString(j);
            String s2 = cursor.getString(k);
            jsonobject.put(s1.substring(1 + context.getPackageName().length(), s1.length()), s2);
              goto _L5
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        static List convertDatabaseToJson(Context context, LocalyticsProvider localyticsprovider, String s)
        {
            LinkedList linkedlist;
            Cursor cursor;
            linkedlist = new LinkedList();
            cursor = null;
            long l;
            int i;
            int j;
            cursor = localyticsprovider.query("upload_blobs", null, null, null, null);
            l = getApiKeyCreationTime(localyticsprovider, s);
            i = cursor.getColumnIndexOrThrow("_id");
            j = cursor.getColumnIndexOrThrow("uuid");
_L2:
            boolean flag = cursor.moveToNext();
            if(!flag)
            {
                if(cursor != null)
                    cursor.close();
                return linkedlist;
            }
            JSONObject jsonobject;
            JSONObject jsonobject1;
            jsonobject = new JSONObject();
            jsonobject.put("dt", "h");
            jsonobject.put("pa", l);
            jsonobject.put("seq", cursor.getLong(i));
            jsonobject.put("u", cursor.getString(j));
            jsonobject.put("attrs", getAttributesFromSession(localyticsprovider, s, getSessionIdForBlobId(localyticsprovider, cursor.getLong(i))));
            jsonobject1 = getIdentifiers(localyticsprovider);
            if(jsonobject1 == null)
                break MISSING_BLOCK_LABEL_190;
            jsonobject.put("ids", jsonobject1);
            linkedlist.add(jsonobject);
            Cursor cursor1 = null;
            int k;
            String as[] = {
                "events_key_ref"
            };
            String s1 = String.format("%s = ?", new Object[] {
                "upload_blobs_key_ref"
            });
            String as1[] = new String[1];
            as1[0] = Long.toString(cursor.getLong(i));
            cursor1 = localyticsprovider.query("upload_blob_events", as, s1, as1, "events_key_ref");
            k = cursor1.getColumnIndexOrThrow("events_key_ref");
_L3:
            boolean flag1 = cursor1.moveToNext();
            if(flag1)
                break MISSING_BLOCK_LABEL_312;
            if(cursor1 == null) goto _L2; else goto _L1
_L1:
            try
            {
                cursor1.close();
            }
            catch(JSONException jsonexception) { }
              goto _L2
            linkedlist.add(convertEventToJson(localyticsprovider, context, cursor1.getLong(k), cursor.getLong(i), s));
              goto _L3
            Exception exception1;
            exception1;
            if(cursor1 == null)
                break MISSING_BLOCK_LABEL_360;
            cursor1.close();
            throw exception1;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
              goto _L2
        }

        static JSONObject convertEventToJson(LocalyticsProvider localyticsprovider, Context context, long l, long l1, String s)
            throws JSONException
        {
            JSONObject jsonobject;
            Cursor cursor;
            jsonobject = new JSONObject();
            cursor = null;
            String s2;
            long l2;
            String s3;
            long l3;
            String s1 = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as[] = new String[1];
            as[0] = Long.toString(l);
            cursor = localyticsprovider.query("events", null, s1, as, "_id");
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_2832;
            s2 = cursor.getString(cursor.getColumnIndexOrThrow("event_name"));
            l2 = getSessionIdForEventId(localyticsprovider, l);
            s3 = getSessionUuid(localyticsprovider, l2);
            l3 = getSessionStartTime(localyticsprovider, l2);
            if(!LocalyticsSession.OPEN_EVENT.equals(s2)) goto _L2; else goto _L1
_L1:
            jsonobject.put("dt", "s");
            jsonobject.put("ct", Math.round((double)cursor.getLong(cursor.getColumnIndex("wall_time")) / 1000D));
            jsonobject.put("u", s3);
            jsonobject.put("nth", l2);
            if(cursor.isNull(cursor.getColumnIndexOrThrow("event_lat")) || cursor.isNull(cursor.getColumnIndexOrThrow("event_lng"))) goto _L4; else goto _L3
_L3:
            double d4;
            double d5;
            d4 = cursor.getDouble(cursor.getColumnIndexOrThrow("event_lat"));
            d5 = cursor.getDouble(cursor.getColumnIndexOrThrow("event_lng"));
            if(d4 == 0.0D || d5 == 0.0D) goto _L4; else goto _L5
_L5:
            jsonobject.put("lat", d4);
            jsonobject.put("lng", d5);
_L4:
            Cursor cursor6 = null;
            int k1;
            int i2;
            String s16 = String.format("%s = ?", new Object[] {
                "events_key_ref"
            });
            String as9[] = new String[1];
            as9[0] = Long.toString(l);
            cursor6 = localyticsprovider.query("attributes", null, s16, as9, null);
            k1 = cursor6.getColumnIndexOrThrow("attribute_key");
            i2 = cursor6.getColumnIndexOrThrow("attribute_value");
_L8:
            boolean flag2 = cursor6.moveToNext();
            if(flag2) goto _L7; else goto _L6
_L6:
            if(cursor6 == null)
                break MISSING_BLOCK_LABEL_391;
            cursor6.close();
_L19:
            if(cursor != null)
                cursor.close();
            return jsonobject;
_L7:
            String s17;
            String s18;
            s17 = cursor6.getString(k1);
            s18 = cursor6.getString(i2);
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1.equals(s17))
                break MISSING_BLOCK_LABEL_487;
            jsonobject.put("c0", s18);
              goto _L8
            Exception exception6;
            exception6;
            if(cursor6 == null)
                break MISSING_BLOCK_LABEL_467;
            cursor6.close();
            throw exception6;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
label0:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2.equals(s17))
                    break label0;
                jsonobject.put("c1", s18);
            }
              goto _L8
label1:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3.equals(s17))
                    break label1;
                jsonobject.put("c2", s18);
            }
              goto _L8
label2:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4.equals(s17))
                    break label2;
                jsonobject.put("c3", s18);
            }
              goto _L8
label3:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5.equals(s17))
                    break label3;
                jsonobject.put("c4", s18);
            }
              goto _L8
label4:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6.equals(s17))
                    break label4;
                jsonobject.put("c5", s18);
            }
              goto _L8
label5:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7.equals(s17))
                    break label5;
                jsonobject.put("c6", s18);
            }
              goto _L8
label6:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8.equals(s17))
                    break label6;
                jsonobject.put("c7", s18);
            }
              goto _L8
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9.equals(s17)) goto _L10; else goto _L9
_L9:
            jsonobject.put("c8", s18);
              goto _L8
_L10:
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10.equals(s17)) goto _L8; else goto _L11
_L11:
            jsonobject.put("c9", s18);
              goto _L8
_L2:
            if(!LocalyticsSession.CLOSE_EVENT.equals(s2))
                break MISSING_BLOCK_LABEL_1687;
            jsonobject.put("dt", "c");
            jsonobject.put("u", cursor.getString(cursor.getColumnIndexOrThrow("uuid")));
            jsonobject.put("su", s3);
            jsonobject.put("ss", Math.round((double)l3 / 1000D));
            jsonobject.put("ct", Math.round((double)cursor.getLong(cursor.getColumnIndex("wall_time")) / 1000D));
            Cursor cursor3 = null;
            String as4[] = {
                "session_start_wall_time"
            };
            String s11 = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as5[] = new String[1];
            as5[0] = Long.toString(cursor.getLong(cursor.getColumnIndexOrThrow("session_key_ref")));
            cursor3 = localyticsprovider.query("sessions", as4, s11, as5, null);
            if(!cursor3.moveToFirst()) goto _L13; else goto _L12
_L12:
            long l5 = Math.round((double)cursor.getLong(cursor.getColumnIndex("wall_time")) / 1000D);
            int k = cursor3.getColumnIndexOrThrow("session_start_wall_time");
            jsonobject.put("ctl", l5 - Math.round((double)cursor3.getLong(k) / 1000D));
            if(cursor3 == null)
                break MISSING_BLOCK_LABEL_986;
            cursor3.close();
            Cursor cursor4 = null;
            JSONArray jsonarray2;
            String as6[] = {
                "name"
            };
            String s12 = String.format("%s = ? AND %s = ?", new Object[] {
                "session_key_ref", "type"
            });
            String as7[] = new String[2];
            as7[0] = Long.toString(l2);
            as7[1] = Integer.toString(1);
            cursor4 = localyticsprovider.query("event_history", as6, s12, as7, "_id");
            jsonarray2 = new JSONArray();
_L20:
            if(cursor4.moveToNext()) goto _L15; else goto _L14
_L14:
            if(jsonarray2.length() > 0)
                jsonobject.put("fl", jsonarray2);
            if(cursor4 == null)
                break MISSING_BLOCK_LABEL_1115;
            cursor4.close();
            double d2;
            double d3;
            if(cursor.isNull(cursor.getColumnIndexOrThrow("event_lat")) || cursor.isNull(cursor.getColumnIndexOrThrow("event_lng")))
                break MISSING_BLOCK_LABEL_1229;
            d2 = cursor.getDouble(cursor.getColumnIndexOrThrow("event_lat"));
            d3 = cursor.getDouble(cursor.getColumnIndexOrThrow("event_lng"));
            if(d2 == 0.0D || d3 == 0.0D)
                break MISSING_BLOCK_LABEL_1229;
            jsonobject.put("lat", d2);
            jsonobject.put("lng", d3);
            Cursor cursor5 = null;
            int i1;
            int j1;
            String s13 = String.format("%s = ?", new Object[] {
                "events_key_ref"
            });
            String as8[] = new String[1];
            as8[0] = Long.toString(l);
            cursor5 = localyticsprovider.query("attributes", null, s13, as8, null);
            i1 = cursor5.getColumnIndexOrThrow("attribute_key");
            j1 = cursor5.getColumnIndexOrThrow("attribute_value");
_L21:
            boolean flag1 = cursor5.moveToNext();
            if(flag1) goto _L17; else goto _L16
_L16:
            if(cursor5 == null) goto _L19; else goto _L18
_L18:
            cursor5.close();
              goto _L19
_L13:
            throw new RuntimeException("Session didn't exist");
            Exception exception3;
            exception3;
            if(cursor3 == null)
                break MISSING_BLOCK_LABEL_1352;
            cursor3.close();
            throw exception3;
_L15:
            jsonarray2.put(cursor4.getString(cursor4.getColumnIndexOrThrow("name")));
              goto _L20
            Exception exception4;
            exception4;
            if(cursor4 == null)
                break MISSING_BLOCK_LABEL_1395;
            cursor4.close();
            throw exception4;
_L17:
            String s14;
            String s15;
            s14 = cursor5.getString(i1);
            s15 = cursor5.getString(j1);
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1.equals(s14))
                break MISSING_BLOCK_LABEL_1462;
            jsonobject.put("c0", s15);
              goto _L21
            Exception exception5;
            exception5;
            if(cursor5 == null)
                break MISSING_BLOCK_LABEL_1459;
            cursor5.close();
            throw exception5;
label7:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2.equals(s14))
                    break label7;
                jsonobject.put("c1", s15);
            }
              goto _L21
label8:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3.equals(s14))
                    break label8;
                jsonobject.put("c2", s15);
            }
              goto _L21
label9:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4.equals(s14))
                    break label9;
                jsonobject.put("c3", s15);
            }
              goto _L21
label10:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5.equals(s14))
                    break label10;
                jsonobject.put("c4", s15);
            }
              goto _L21
label11:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6.equals(s14))
                    break label11;
                jsonobject.put("c5", s15);
            }
              goto _L21
label12:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7.equals(s14))
                    break label12;
                jsonobject.put("c6", s15);
            }
              goto _L21
label13:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8.equals(s14))
                    break label13;
                jsonobject.put("c7", s15);
            }
              goto _L21
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9.equals(s14)) goto _L23; else goto _L22
_L22:
            jsonobject.put("c8", s15);
              goto _L21
_L23:
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10.equals(s14)) goto _L21; else goto _L24
_L24:
            jsonobject.put("c9", s15);
              goto _L21
            String s4;
            if(!LocalyticsSession.OPT_IN_EVENT.equals(s2) && !LocalyticsSession.OPT_OUT_EVENT.equals(s2))
                break MISSING_BLOCK_LABEL_1806;
            jsonobject.put("dt", "o");
            jsonobject.put("u", s);
            if(!LocalyticsSession.OPT_OUT_EVENT.equals(s2))
                break MISSING_BLOCK_LABEL_1795;
            s4 = Boolean.TRUE.toString();
_L25:
            jsonobject.put("out", s4);
            jsonobject.put("ct", Math.round((double)cursor.getLong(cursor.getColumnIndex("wall_time")) / 1000D));
              goto _L19
            s4 = Boolean.FALSE.toString();
              goto _L25
            if(!LocalyticsSession.FLOW_EVENT.equals(s2))
                break MISSING_BLOCK_LABEL_2165;
            jsonobject.put("dt", "f");
            jsonobject.put("u", cursor.getString(cursor.getColumnIndexOrThrow("uuid")));
            jsonobject.put("ss", Math.round((double)l3 / 1000D));
            Cursor cursor2 = null;
            JSONArray jsonarray;
            JSONArray jsonarray1;
            String as2[] = {
                "type", "processed_in_blob", "name"
            };
            String s8 = String.format("%s = ? AND %s <= ?", new Object[] {
                "session_key_ref", "processed_in_blob"
            });
            String as3[] = new String[2];
            as3[0] = Long.toString(l2);
            as3[1] = Long.toString(l1);
            cursor2 = localyticsprovider.query("event_history", as2, s8, as3, "_id");
            jsonarray = new JSONArray();
            jsonarray1 = new JSONArray();
_L29:
            if(cursor2.moveToNext()) goto _L27; else goto _L26
_L26:
            jsonobject.put("nw", jsonarray);
            jsonobject.put("od", jsonarray1);
            if(cursor2 == null) goto _L19; else goto _L28
_L28:
            cursor2.close();
              goto _L19
_L27:
            String s9 = cursor2.getString(cursor2.getColumnIndexOrThrow("name"));
            Exception exception2;
            String s10;
            if(cursor2.getInt(cursor2.getColumnIndexOrThrow("type")) == 0)
                s10 = "e";
            else
                s10 = "s";
            if(l1 != cursor2.getLong(cursor2.getColumnIndexOrThrow("processed_in_blob")))
                break MISSING_BLOCK_LABEL_2142;
            jsonarray.put((new JSONObject()).put(s10, s9));
              goto _L29
            exception2;
            if(cursor2 == null)
                break MISSING_BLOCK_LABEL_2132;
            cursor2.close();
            throw exception2;
            jsonarray1.put((new JSONObject()).put(s10, s9));
              goto _L29
            long l4;
            jsonobject.put("dt", "e");
            jsonobject.put("ct", Math.round((double)cursor.getLong(cursor.getColumnIndex("wall_time")) / 1000D));
            jsonobject.put("u", cursor.getString(cursor.getColumnIndexOrThrow("uuid")));
            jsonobject.put("su", s3);
            jsonobject.put("n", s2.substring(1 + context.getPackageName().length(), s2.length()));
            l4 = cursor.getLong(cursor.getColumnIndex("clv_increase"));
            if(l4 == 0L)
                break MISSING_BLOCK_LABEL_2308;
            jsonobject.put("v", l4);
            double d;
            double d1;
            if(cursor.isNull(cursor.getColumnIndexOrThrow("event_lat")) || cursor.isNull(cursor.getColumnIndexOrThrow("event_lng")))
                break MISSING_BLOCK_LABEL_2422;
            d = cursor.getDouble(cursor.getColumnIndexOrThrow("event_lat"));
            d1 = cursor.getDouble(cursor.getColumnIndexOrThrow("event_lng"));
            if(d == 0.0D || d1 == 0.0D)
                break MISSING_BLOCK_LABEL_2422;
            jsonobject.put("lat", d);
            jsonobject.put("lng", d1);
            Cursor cursor1 = null;
            int i;
            int j;
            String s5 = String.format("%s = ?", new Object[] {
                "events_key_ref"
            });
            String as1[] = new String[1];
            as1[0] = Long.toString(l);
            cursor1 = localyticsprovider.query("attributes", null, s5, as1, null);
            i = cursor1.getColumnIndexOrThrow("attribute_key");
            j = cursor1.getColumnIndexOrThrow("attribute_value");
_L31:
            boolean flag = cursor1.moveToNext();
            if(flag)
                break MISSING_BLOCK_LABEL_2543;
            if(cursor1 == null)
                break MISSING_BLOCK_LABEL_2517;
            cursor1.close();
            JSONObject jsonobject1 = convertAttributesToJson(localyticsprovider, context, l);
            if(jsonobject1 == null) goto _L19; else goto _L30
_L30:
            jsonobject.put("attrs", jsonobject1);
              goto _L19
            String s6;
            String s7;
            s6 = cursor1.getString(i);
            s7 = cursor1.getString(j);
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1.equals(s6))
                break MISSING_BLOCK_LABEL_2607;
            jsonobject.put("c0", s7);
              goto _L31
            Exception exception1;
            exception1;
            if(cursor1 == null)
                break MISSING_BLOCK_LABEL_2604;
            cursor1.close();
            throw exception1;
label14:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2.equals(s6))
                    break label14;
                jsonobject.put("c1", s7);
            }
              goto _L31
label15:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3.equals(s6))
                    break label15;
                jsonobject.put("c2", s7);
            }
              goto _L31
label16:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4.equals(s6))
                    break label16;
                jsonobject.put("c3", s7);
            }
              goto _L31
label17:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5.equals(s6))
                    break label17;
                jsonobject.put("c4", s7);
            }
              goto _L31
label18:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6.equals(s6))
                    break label18;
                jsonobject.put("c5", s7);
            }
              goto _L31
label19:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7.equals(s6))
                    break label19;
                jsonobject.put("c6", s7);
            }
              goto _L31
label20:
            {
                if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8.equals(s6))
                    break label20;
                jsonobject.put("c7", s7);
            }
              goto _L31
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9.equals(s6)) goto _L33; else goto _L32
_L32:
            jsonobject.put("c8", s7);
              goto _L31
_L33:
            if(!LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10.equals(s6)) goto _L31; else goto _L34
_L34:
            jsonobject.put("c9", s7);
              goto _L31
            throw new RuntimeException();
              goto _L8
        }

        static void deleteBlobsAndSessions(LocalyticsProvider localyticsprovider)
        {
            LinkedList linkedlist;
            HashSet hashset;
            Cursor cursor;
            linkedlist = new LinkedList();
            hashset = new HashSet();
            cursor = null;
            int i;
            int j;
            int k;
            cursor = localyticsprovider.query("upload_blob_events", new String[] {
                "_id", "events_key_ref", "upload_blobs_key_ref"
            }, null, null, null);
            i = cursor.getColumnIndexOrThrow("upload_blobs_key_ref");
            j = cursor.getColumnIndexOrThrow("_id");
            k = cursor.getColumnIndexOrThrow("events_key_ref");
_L11:
            boolean flag = cursor.moveToNext();
            if(flag) goto _L2; else goto _L1
_L1:
            Iterator iterator;
            if(cursor != null)
                cursor.close();
            iterator = hashset.iterator();
_L15:
            if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
            Iterator iterator1 = linkedlist.iterator();
_L16:
            if(!iterator1.hasNext())
                return;
            break MISSING_BLOCK_LABEL_699;
_L2:
            long l;
            long l2;
            String s;
            String as[];
            l = cursor.getLong(i);
            long l1 = cursor.getLong(j);
            l2 = cursor.getLong(k);
            s = String.format("%s = ?", new Object[] {
                "_id"
            });
            as = new String[1];
            as[0] = Long.toString(l1);
            if(localyticsprovider instanceof SQLiteDatabase) goto _L6; else goto _L5
_L5:
            localyticsprovider.delete("upload_blob_events", s, as);
_L12:
            String s1;
            String as1[];
            hashset.add(Long.valueOf(l));
            s1 = String.format("%s = ?", new Object[] {
                "events_key_ref"
            });
            as1 = new String[1];
            as1[0] = Long.toString(l2);
            if(localyticsprovider instanceof SQLiteDatabase) goto _L8; else goto _L7
_L7:
            localyticsprovider.delete("attributes", s1, as1);
_L13:
            Cursor cursor1 = null;
            String as2[] = {
                "session_key_ref"
            };
            String s2 = String.format("%s = ? AND %s = ?", new Object[] {
                "_id", "event_name"
            });
            String as3[] = new String[2];
            as3[0] = Long.toString(l2);
            as3[1] = LocalyticsSession.CLOSE_EVENT;
            cursor1 = localyticsprovider.query("events", as2, s2, as3, null);
            if(!cursor1.moveToFirst()) goto _L10; else goto _L9
_L9:
            String s4;
            String as5[];
            long l3 = cursor1.getLong(cursor1.getColumnIndexOrThrow("session_key_ref"));
            s4 = String.format("%s = ?", new Object[] {
                "session_key_ref"
            });
            as5 = new String[1];
            as5[0] = Long.toString(l3);
            if(localyticsprovider instanceof SQLiteDatabase)
                break MISSING_BLOCK_LABEL_563;
            localyticsprovider.delete("event_history", s4, as5);
_L14:
            linkedlist.add(Long.valueOf(cursor1.getLong(cursor1.getColumnIndexOrThrow("session_key_ref"))));
_L10:
            if(cursor1 == null)
                break MISSING_BLOCK_LABEL_462;
            cursor1.close();
            String s3;
            String as4[];
            s3 = String.format("%s = ?", new Object[] {
                "_id"
            });
            as4 = new String[1];
            as4[0] = Long.toString(l2);
            if(localyticsprovider instanceof SQLiteDatabase)
                break MISSING_BLOCK_LABEL_598;
            localyticsprovider.delete("events", s3, as4);
              goto _L11
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
_L6:
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider, "upload_blob_events", s, as);
              goto _L12
_L8:
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider, "attributes", s1, as1);
              goto _L13
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider, "event_history", s4, as5);
              goto _L14
            Exception exception1;
            exception1;
            if(cursor1 == null)
                break MISSING_BLOCK_LABEL_595;
            cursor1.close();
            throw exception1;
            SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider, "events", s3, as4);
              goto _L11
_L4:
            long l4 = ((Long)iterator.next()).longValue();
            String s5 = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as6[] = new String[1];
            as6[0] = Long.toString(l4);
            if(!(localyticsprovider instanceof SQLiteDatabase))
                localyticsprovider.delete("upload_blobs", s5, as6);
            else
                SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider, "upload_blobs", s5, as6);
              goto _L15
            long l5 = ((Long)iterator1.next()).longValue();
            String s6 = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as7[] = new String[1];
            as7[0] = Long.toString(l5);
            if(!(localyticsprovider instanceof SQLiteDatabase))
                localyticsprovider.delete("sessions", s6, as7);
            else
                SQLiteInstrumentation.delete((SQLiteDatabase)localyticsprovider, "sessions", s6, as7);
              goto _L16
        }

        static long getApiKeyCreationTime(LocalyticsProvider localyticsprovider, String s)
        {
            Cursor cursor = null;
            int i;
            cursor = localyticsprovider.query("api_keys", null, String.format("%s = ?", new Object[] {
                "api_key"
            }), new String[] {
                s
            }, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_87;
            i = Math.round((float)cursor.getLong(cursor.getColumnIndexOrThrow("created_time")) / 1000F);
            long l = i;
            if(cursor != null)
                cursor.close();
            return l;
            throw new RuntimeException("API key entry couldn't be found");
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        static JSONObject getAttributesFromSession(LocalyticsProvider localyticsprovider, String s, long l)
            throws JSONException
        {
            Cursor cursor = null;
            String s1 = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as[] = new String[1];
            as[0] = Long.toString(l);
            cursor = localyticsprovider.query("sessions", null, s1, as, null);
            if(!cursor.moveToFirst()) goto _L2; else goto _L1
_L1:
            JSONObject jsonobject;
            jsonobject = new JSONObject();
            jsonobject.put("av", cursor.getString(cursor.getColumnIndexOrThrow("app_version")));
            jsonobject.put("dac", cursor.getString(cursor.getColumnIndexOrThrow("network_type")));
            jsonobject.put("du", cursor.getString(cursor.getColumnIndexOrThrow("device_android_id_hash")));
            jsonobject.put("dc", cursor.getString(cursor.getColumnIndexOrThrow("device_country")));
            jsonobject.put("dma", cursor.getString(cursor.getColumnIndexOrThrow("device_manufacturer")));
            jsonobject.put("dmo", cursor.getString(cursor.getColumnIndexOrThrow("device_model")));
            jsonobject.put("dov", cursor.getString(cursor.getColumnIndexOrThrow("android_version")));
            jsonobject.put("dp", "Android");
            if(!cursor.isNull(cursor.getColumnIndexOrThrow("device_serial_number_hash"))) goto _L4; else goto _L3
_L3:
            Object obj = JSONObject.NULL;
_L11:
            jsonobject.put("dms", obj);
            jsonobject.put("dsdk", cursor.getString(cursor.getColumnIndexOrThrow("android_sdk")));
            if(!cursor.isNull(cursor.getColumnIndexOrThrow("device_wifi_mac_hash"))) goto _L6; else goto _L5
_L5:
            Object obj1 = JSONObject.NULL;
_L12:
            jsonobject.put("wmac", obj1);
            jsonobject.put("au", s);
            jsonobject.put("lv", cursor.getString(cursor.getColumnIndexOrThrow("localytics_library_version")));
            jsonobject.put("dt", "a");
            if(!cursor.isNull(cursor.getColumnIndexOrThrow("device_telephony_id"))) goto _L8; else goto _L7
_L7:
            Object obj2 = JSONObject.NULL;
_L13:
            jsonobject.put("ctdid", obj2);
            if(!cursor.isNull(cursor.getColumnIndexOrThrow("device_android_id"))) goto _L10; else goto _L9
_L9:
            Object obj3 = JSONObject.NULL;
_L14:
            String s2;
            jsonobject.put("caid", obj3);
            s2 = cursor.getString(cursor.getColumnIndexOrThrow("iu"));
            if(s2 == null)
                break MISSING_BLOCK_LABEL_513;
            jsonobject.put("iu", s2);
            String s3;
            jsonobject.put("dlc", cursor.getString(cursor.getColumnIndexOrThrow("locale_country")));
            jsonobject.put("dll", cursor.getString(cursor.getColumnIndexOrThrow("locale_language")));
            jsonobject.put("nca", cursor.getString(cursor.getColumnIndexOrThrow("network_carrier")));
            jsonobject.put("nc", cursor.getString(cursor.getColumnIndexOrThrow("network_country")));
            s3 = getStringFromAppInfo(localyticsprovider, "fb_attribution");
            if(s3 == null)
                break MISSING_BLOCK_LABEL_642;
            jsonobject.put("fbat", s3);
            String s4 = getStringFromAppInfo(localyticsprovider, "play_attribution");
            if(s4 == null)
                break MISSING_BLOCK_LABEL_667;
            jsonobject.put("aurl", s4);
            String s5 = getStringFromAppInfo(localyticsprovider, "registration_id");
            if(s5 == null)
                break MISSING_BLOCK_LABEL_692;
            jsonobject.put("push", s5);
            String s6 = getStringFromAppInfo(localyticsprovider, "first_android_id");
            if(s6 == null)
                break MISSING_BLOCK_LABEL_717;
            jsonobject.put("aid", s6);
            String s7 = getStringFromAppInfo(localyticsprovider, "first_telephony_id");
            if(s7 == null)
                break MISSING_BLOCK_LABEL_742;
            jsonobject.put("tdid", s7);
            String s8 = getStringFromAppInfo(localyticsprovider, "package_name");
            if(s8 == null)
                break MISSING_BLOCK_LABEL_767;
            jsonobject.put("pkg", s8);
            if(cursor != null)
                cursor.close();
            return jsonobject;
_L4:
            obj = cursor.getString(cursor.getColumnIndexOrThrow("device_serial_number_hash"));
              goto _L11
_L6:
            obj1 = cursor.getString(cursor.getColumnIndexOrThrow("device_wifi_mac_hash"));
              goto _L12
_L8:
            obj2 = cursor.getString(cursor.getColumnIndexOrThrow("device_telephony_id"));
              goto _L13
_L10:
            obj3 = cursor.getString(cursor.getColumnIndexOrThrow("device_android_id"));
              goto _L14
_L2:
            throw new RuntimeException("No session exists");
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
              goto _L11
        }

        static JSONObject getIdentifiers(LocalyticsProvider localyticsprovider)
            throws JSONException
        {
            Cursor cursor = null;
            cursor = localyticsprovider.query("identifiers", null, null, null, null);
            JSONObject jsonobject = null;
_L1:
            boolean flag = cursor.moveToNext();
            if(!flag)
            {
                if(cursor != null)
                    cursor.close();
                return jsonobject;
            }
            if(jsonobject != null)
                break MISSING_BLOCK_LABEL_53;
            jsonobject = new JSONObject();
            jsonobject.put(cursor.getString(cursor.getColumnIndexOrThrow("key")), cursor.getString(cursor.getColumnIndexOrThrow("value")));
              goto _L1
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        static long getSessionIdForBlobId(LocalyticsProvider localyticsprovider, long l)
        {
            Cursor cursor = null;
            long l1;
            String as[] = {
                "events_key_ref"
            };
            String s = String.format("%s = ?", new Object[] {
                "upload_blobs_key_ref"
            });
            String as1[] = new String[1];
            as1[0] = Long.toString(l);
            cursor = localyticsprovider.query("upload_blob_events", as, s, as1, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_197;
            l1 = cursor.getLong(cursor.getColumnIndexOrThrow("events_key_ref"));
            Cursor cursor1;
            if(cursor != null)
                cursor.close();
            cursor1 = null;
            long l2;
            String as2[] = {
                "session_key_ref"
            };
            String s1 = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as3[] = new String[1];
            as3[0] = Long.toString(l1);
            cursor1 = localyticsprovider.query("events", as2, s1, as3, null);
            if(!cursor1.moveToFirst())
                break MISSING_BLOCK_LABEL_223;
            l2 = cursor1.getLong(cursor1.getColumnIndexOrThrow("session_key_ref"));
            if(cursor1 != null)
                cursor1.close();
            return l2;
            throw new RuntimeException("No events associated with blob");
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            throw new RuntimeException("No session associated with event");
            Exception exception1;
            exception1;
            if(cursor1 != null)
                cursor1.close();
            throw exception1;
        }

        static long getSessionIdForEventId(LocalyticsProvider localyticsprovider, long l)
        {
            Cursor cursor = null;
            long l1;
            String as[] = {
                "session_key_ref"
            };
            String s = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as1[] = new String[1];
            as1[0] = Long.toString(l);
            cursor = localyticsprovider.query("events", as, s, as1, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_97;
            l1 = cursor.getLong(cursor.getColumnIndexOrThrow("session_key_ref"));
            if(cursor != null)
                cursor.close();
            return l1;
            throw new RuntimeException();
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        static long getSessionStartTime(LocalyticsProvider localyticsprovider, long l)
        {
            Cursor cursor = null;
            long l1;
            String as[] = {
                "session_start_wall_time"
            };
            String s = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as1[] = new String[1];
            as1[0] = Long.toString(l);
            cursor = localyticsprovider.query("sessions", as, s, as1, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_98;
            l1 = cursor.getLong(cursor.getColumnIndexOrThrow("session_start_wall_time"));
            if(cursor != null)
                cursor.close();
            return l1;
            throw new RuntimeException();
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        static String getSessionUuid(LocalyticsProvider localyticsprovider, long l)
        {
            Cursor cursor = null;
            String s1;
            String as[] = {
                "uuid"
            };
            String s = String.format("%s = ?", new Object[] {
                "_id"
            });
            String as1[] = new String[1];
            as1[0] = Long.toString(l);
            cursor = localyticsprovider.query("sessions", as, s, as1, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_96;
            s1 = cursor.getString(cursor.getColumnIndexOrThrow("uuid"));
            if(cursor != null)
                cursor.close();
            return s1;
            throw new RuntimeException();
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        static String getStringFromAppInfo(LocalyticsProvider localyticsprovider, String s)
        {
            Cursor cursor = null;
            String s1;
            cursor = localyticsprovider.query("info", null, null, null, null);
            if(!cursor.moveToFirst())
                break MISSING_BLOCK_LABEL_64;
            s1 = cursor.getString(cursor.getColumnIndexOrThrow(s));
            if(cursor != null)
                cursor.close();
            return s1;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            if(cursor != null)
                cursor.close();
            return null;
        }

        static boolean uploadSessions(String s, String s1, String s2, String s3)
        {
            if(s == null)
                throw new IllegalArgumentException("url cannot be null");
            if(s1 == null)
                throw new IllegalArgumentException("body cannot be null");
            if(DatapointHelper.getApiLevel() < 9) goto _L2; else goto _L1
_L1:
            GZIPOutputStream gzipoutputstream2 = null;
            byte abyte1[];
            ByteArrayOutputStream bytearrayoutputstream1;
            GZIPOutputStream gzipoutputstream3;
            abyte1 = s1.getBytes("UTF-8");
            bytearrayoutputstream1 = new ByteArrayOutputStream(abyte1.length);
            gzipoutputstream3 = new GZIPOutputStream(bytearrayoutputstream1);
            byte abyte2[];
            gzipoutputstream3.write(abyte1);
            gzipoutputstream3.finish();
            if(DatapointHelper.getApiLevel() < 19)
                gzipoutputstream3.flush();
            abyte2 = bytearrayoutputstream1.toByteArray();
            Exception exception1;
            HttpURLConnection httpurlconnection;
            OutputStream outputstream;
            IOException ioexception8;
            IOException ioexception9;
            IOException ioexception10;
            UnsupportedEncodingException unsupportedencodingexception2;
            IOException ioexception11;
            URL url;
            int j;
            if(gzipoutputstream3 != null)
                try
                {
                    gzipoutputstream3.close();
                }
                catch(IOException ioexception14)
                {
                    return false;
                }
            httpurlconnection = null;
            Exception exception3;
            try
            {
                url = new URL(s);
                httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection(url.openConnection());
                httpurlconnection.setDoOutput(true);
                httpurlconnection.setRequestProperty("Content-Type", "application/x-gzip");
                httpurlconnection.setRequestProperty("Content-Encoding", "gzip");
                httpurlconnection.setRequestProperty("x-upload-time", Long.toString(Math.round((double)System.currentTimeMillis() / 1000D)));
                httpurlconnection.setRequestProperty("x-install-id", s2);
                httpurlconnection.setRequestProperty("x-app-id", s3);
                httpurlconnection.setRequestProperty("x-client-version", "android_2.16");
                httpurlconnection.setFixedLengthStreamingMode(abyte2.length);
            }
            catch(MalformedURLException malformedurlexception)
            {
                if(httpurlconnection != null)
                    httpurlconnection.disconnect();
                return false;
            }
            catch(IOException ioexception13)
            {
                if(httpurlconnection != null)
                    httpurlconnection.disconnect();
                return false;
            }
            outputstream = null;
            outputstream = httpurlconnection.getOutputStream();
            outputstream.write(abyte2);
            if(outputstream == null)
                break MISSING_BLOCK_LABEL_260;
            outputstream.flush();
            outputstream.close();
            j = httpurlconnection.getResponseCode();
            break MISSING_BLOCK_LABEL_267;
            unsupportedencodingexception2;
_L17:
            if(gzipoutputstream2 != null)
                try
                {
                    gzipoutputstream2.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception11)
                {
                    return false;
                }
            return false;
            ioexception9;
_L15:
            if(gzipoutputstream2 != null)
                try
                {
                    gzipoutputstream2.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception10)
                {
                    return false;
                }
            return false;
            exception1;
_L13:
            if(gzipoutputstream2 != null)
                try
                {
                    gzipoutputstream2.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception8)
                {
                    return false;
                }
            throw exception1;
            exception3;
            if(outputstream == null)
                break MISSING_BLOCK_LABEL_371;
            outputstream.flush();
            outputstream.close();
            throw exception3;
            exception2;
            if(httpurlconnection != null)
                httpurlconnection.disconnect();
            throw exception2;
            Exception exception2;
            if(j >= 500 && j <= 599)
            {
                if(httpurlconnection != null)
                    httpurlconnection.disconnect();
                return false;
            }
            if(httpurlconnection != null)
                httpurlconnection.disconnect();
_L7:
            return true;
_L2:
            DefaultHttpClient defaulthttpclient;
            HttpPost httppost;
            GZIPOutputStream gzipoutputstream;
            defaulthttpclient = new DefaultHttpClient();
            httppost = new HttpPost(s);
            httppost.addHeader("Content-Type", "application/x-gzip");
            httppost.addHeader("Content-Encoding", "gzip");
            httppost.addHeader("x-upload-time", Long.toString(Math.round((double)System.currentTimeMillis() / 1000D)));
            httppost.addHeader("x-install-id", s2);
            httppost.addHeader("x-app-id", s3);
            httppost.addHeader("x-client-version", "android_2.16");
            gzipoutputstream = null;
            byte abyte0[];
            ByteArrayOutputStream bytearrayoutputstream;
            GZIPOutputStream gzipoutputstream1;
            abyte0 = s1.getBytes("UTF-8");
            bytearrayoutputstream = new ByteArrayOutputStream(abyte0.length);
            gzipoutputstream1 = new GZIPOutputStream(bytearrayoutputstream);
            gzipoutputstream1.write(abyte0);
            gzipoutputstream1.finish();
            gzipoutputstream1.flush();
            httppost.setEntity(new ByteArrayEntity(bytearrayoutputstream.toByteArray()));
            if(defaulthttpclient instanceof HttpClient) goto _L4; else goto _L3
_L3:
            HttpResponse httpresponse1 = defaulthttpclient.execute(httppost);
_L6:
            int i = httpresponse1.getStatusLine().getStatusCode();
              goto _L5
_L4:
            httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httppost);
            httpresponse1 = httpresponse;
              goto _L6
            unsupportedencodingexception;
_L11:
            Exception exception;
            IOException ioexception1;
            ClientProtocolException clientprotocolexception;
            if(gzipoutputstream != null)
                try
                {
                    gzipoutputstream.close();
                }
                catch(IOException ioexception4) { }
            return false;
            clientprotocolexception;
_L10:
            if(gzipoutputstream != null)
                try
                {
                    gzipoutputstream.close();
                }
                catch(IOException ioexception3) { }
            return false;
            ioexception1;
_L9:
            if(gzipoutputstream != null)
                try
                {
                    gzipoutputstream.close();
                }
                catch(IOException ioexception2) { }
            return false;
            exception;
_L8:
            if(gzipoutputstream != null)
                try
                {
                    gzipoutputstream.close();
                }
                catch(IOException ioexception) { }
            throw exception;
_L5:
            UnsupportedEncodingException unsupportedencodingexception;
            if(i >= 500 && i <= 599)
            {
                HttpResponse httpresponse;
                if(gzipoutputstream1 != null)
                    try
                    {
                        gzipoutputstream1.close();
                    }
                    catch(IOException ioexception7) { }
                return false;
            }
            if(gzipoutputstream1 != null)
                try
                {
                    gzipoutputstream1.close();
                }
                catch(IOException ioexception6) { }
            if(true) goto _L7; else goto _L4
            exception;
            gzipoutputstream = gzipoutputstream1;
              goto _L8
            IOException ioexception5;
            ioexception5;
            gzipoutputstream = gzipoutputstream1;
              goto _L9
            ClientProtocolException clientprotocolexception1;
            clientprotocolexception1;
            gzipoutputstream = gzipoutputstream1;
              goto _L10
            UnsupportedEncodingException unsupportedencodingexception1;
            unsupportedencodingexception1;
            gzipoutputstream = gzipoutputstream1;
              goto _L11
            exception1;
            gzipoutputstream2 = gzipoutputstream3;
            if(true) goto _L13; else goto _L12
_L12:
            IOException ioexception12;
            ioexception12;
            gzipoutputstream2 = gzipoutputstream3;
            if(true) goto _L15; else goto _L14
_L14:
            UnsupportedEncodingException unsupportedencodingexception3;
            unsupportedencodingexception3;
            gzipoutputstream2 = gzipoutputstream3;
            if(true) goto _L17; else goto _L16
_L16:
        }

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 1 2: default 32
        //                       1 43
        //                       2 310;
               goto _L1 _L2 _L3
_L1:
            throw new RuntimeException("Fell through switch statement");
_L2:
            Runnable runnable = (Runnable)message.obj;
            List list = convertDatabaseToJson(mContext, mProvider, mApiKey);
            if(list.isEmpty()) goto _L5; else goto _L4
_L4:
            StringBuilder stringbuilder;
            Iterator iterator;
            stringbuilder = new StringBuilder();
            iterator = list.iterator();
_L12:
            if(iterator.hasNext()) goto _L7; else goto _L6
_L6:
            String s2;
            String s3;
            s2 = mApiKey;
            s3 = DatapointHelper.getLocalyticsRollupKeyOrNull(mContext);
            if(s3 == null)
                break MISSING_BLOCK_LABEL_139;
            if(!TextUtils.isEmpty(s3))
                s2 = s3;
            if(uploadSessions(String.format("http://analytics.localytics.com/api/v2/applications/%s/uploads", new Object[] {
        s2
    }), stringbuilder.toString(), mInstallId, s2))
                mProvider.runBatchTransaction(new Runnable() {

                    public void run()
                    {
                        UploadHandler.deleteBlobsAndSessions(mProvider);
                    }

                    final UploadHandler this$1;

            
            {
                this$1 = UploadHandler.this;
                super();
            }
                }
);
_L5:
            if(runnable == null) goto _L9; else goto _L8
_L8:
            (new Thread(runnable, "upload_callback")).start();
_L9:
            mSessionHandler.sendEmptyMessage(5);
            return;
_L7:
            JSONObject jsonobject = (JSONObject)iterator.next();
            if(jsonobject instanceof JSONObject) goto _L11; else goto _L10
_L10:
            String s1 = jsonobject.toString();
_L15:
            stringbuilder.append(s1);
            stringbuilder.append('\n');
              goto _L12
            Exception exception1;
            exception1;
            if(runnable == null) goto _L14; else goto _L13
_L13:
            (new Thread(runnable, "upload_callback")).start();
_L14:
            mSessionHandler.sendEmptyMessage(5);
            throw exception1;
_L11:
            String s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            s1 = s;
              goto _L15
_L3:
            try
            {
                mSessionHandler.sendMessage(mSessionHandler.obtainMessage(4, message.obj));
                return;
            }
            catch(Exception exception)
            {
                return;
            }
        }

        private static final String ANALYTICS_URL_HTTP = "http://analytics.localytics.com/api/v2/applications/%s/uploads";
        private static final String ANALYTICS_URL_HTTPS = "https://analytics.localytics.com/api/v2/uploads";
        public static final int MESSAGE_RETRY_UPLOAD_REQUEST = 2;
        public static final int MESSAGE_UPLOAD = 1;
        private static final String UPLOAD_CALLBACK_THREAD_NAME = "upload_callback";
        private final String mApiKey;
        private final Context mContext;
        private final String mInstallId;
        protected final LocalyticsProvider mProvider;
        private final Handler mSessionHandler;

        public UploadHandler(Context context, Handler handler, String s, String s1, Looper looper)
        {
            super(looper);
            mContext = context;
            mProvider = LocalyticsProvider.getInstance(context, s);
            mSessionHandler = handler;
            mApiKey = s;
            mInstallId = s1;
        }
    }


    public LocalyticsSession(Context context)
    {
        this(context, null);
    }

    public LocalyticsSession(Context context, String s)
    {
        String s1;
        if(context == null)
            throw new IllegalArgumentException("context cannot be null");
        s1 = s;
        if(TextUtils.isEmpty(s1))
            s1 = DatapointHelper.getLocalyticsAppKeyOrNull(context);
        if(TextUtils.isEmpty(s1))
            throw new IllegalArgumentException("key cannot be null or empty");
        if("com.localytics.android".equals(context.getPackageName()) && !context.getClass().getName().equals("android.test.IsolatedContext") && !context.getClass().getName().equals("android.test.RenamingDelegatingContext"))
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = context.getPackageName();
            throw new IllegalArgumentException(String.format("context.getPackageName() returned %s", aobj1));
        }
        if(!context.getClass().getName().equals("android.test.RenamingDelegatingContext") && Constants.CURRENT_API_LEVEL >= 8)
            context = context.getApplicationContext();
        mContext = context;
        Object aobj[] = sLocalyticsSessionIntrinsicLock;
        aobj;
        JVM INSTR monitorenter ;
        SessionHandler sessionhandler = (SessionHandler)sLocalyticsSessionHandlerMap.get(s1);
        if(sessionhandler != null)
            break MISSING_BLOCK_LABEL_224;
        sessionhandler = new SessionHandler(mContext, s1, sSessionHandlerThread.getLooper());
        sLocalyticsSessionHandlerMap.put(s1, sessionhandler);
        sessionhandler.sendMessage(sessionhandler.obtainMessage(0));
        mSessionHandler = sessionhandler;
        aobj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        aobj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static Map convertDimensionsToAttributes(List list)
    {
        TreeMap treemap = new TreeMap();
        if(list == null) goto _L2; else goto _L1
_L1:
        int i;
        Iterator iterator;
        i = 0;
        iterator = list.iterator();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        return treemap;
_L3:
        String s;
        s = (String)iterator.next();
        if(i != 0)
            break; /* Loop/switch isn't completed */
        treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1, s);
_L6:
        i++;
        if(true) goto _L5; else goto _L4
_L4:
        if(1 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2, s);
        else
        if(2 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3, s);
        else
        if(3 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4, s);
        else
        if(4 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5, s);
        else
        if(5 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6, s);
        else
        if(6 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7, s);
        else
        if(7 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8, s);
        else
        if(8 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9, s);
        else
        if(9 == i)
            treemap.put(LocalyticsProvider.AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10, s);
          goto _L6
        if(true) goto _L5; else goto _L7
_L7:
    }

    public static String createRangedAttribute(int i, int j, int k, int l)
    {
        while(l < 1 || j >= k) 
            return null;
        int i1 = (l + (k - j)) / l;
        int ai[] = new int[i1 + 1];
        int j1 = 0;
        do
        {
            if(j1 > i1)
                return createRangedAttribute(i, ai);
            ai[j1] = j + j1 * l;
            j1++;
        } while(true);
    }

    public static String createRangedAttribute(int i, int ai[])
    {
        if(ai == null)
            throw new IllegalArgumentException("steps cannot be null");
        if(ai.length == 0)
            throw new IllegalArgumentException("steps length must be greater than 0");
        if(i < ai[0])
            return (new StringBuilder("less than ")).append(ai[0]).toString();
        if(i >= ai[-1 + ai.length])
            return (new StringBuilder(String.valueOf(ai[-1 + ai.length]))).append(" and above").toString();
        int j = Arrays.binarySearch(ai, i);
        if(j < 0)
            j = -2 + -j;
        if(ai[j] == -1 + ai[j + 1])
            return Integer.toString(ai[j]);
        else
            return (new StringBuilder(String.valueOf(ai[j]))).append("-").append(-1 + ai[j + 1]).toString();
    }

    private static HandlerThread getHandlerThread(String s)
    {
        HandlerThread handlerthread = new HandlerThread(s, 10);
        handlerthread.start();
        return handlerthread;
    }

    public void close()
    {
        close(null);
    }

    public void close(List list)
    {
        if(list == null) goto _L2; else goto _L1
_L1:
        Iterator iterator;
        if(!list.isEmpty());
        if(list.size() <= 10);
        iterator = list.iterator();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        String s;
        if(list == null || list.isEmpty())
        {
            mSessionHandler.sendEmptyMessage(2);
            return;
        } else
        {
            mSessionHandler.sendMessage(mSessionHandler.obtainMessage(2, new TreeMap(convertDimensionsToAttributes(list))));
            return;
        }
_L3:
        s = (String)iterator.next();
        if(s == null)
            throw new IllegalArgumentException("customDimensions cannot contain null elements");
        if(s.length() == 0)
            throw new IllegalArgumentException("customDimensions cannot contain empty elements");
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void handlePushReceived(Intent intent)
    {
        handlePushReceived(intent, null);
    }

    public void handlePushReceived(Intent intent, List list)
    {
        if(intent != null && intent.getExtras() != null) goto _L2; else goto _L1
_L1:
        String s;
        return;
_L2:
        if((s = intent.getExtras().getString("ll")) == null) goto _L1; else goto _L3
_L3:
        String s1;
        String s2;
        HashMap hashmap;
        try
        {
            JVM INSTR new #346 <Class JSONObject>;
            JSONObject jsonobject = JSONObjectInstrumentation.init(s);
            s1 = jsonobject.getString("ca");
            s2 = jsonobject.getString("cr");
        }
        catch(JSONException jsonexception)
        {
            return;
        }
        if(s1 == null || s2 == null)
            break MISSING_BLOCK_LABEL_105;
        hashmap = new HashMap();
        hashmap.put("Campaign ID", s1);
        hashmap.put("Creative ID", s2);
        tagEvent("Localytics Push Opened", hashmap, list);
        intent.removeExtra("ll");
        return;
    }

    public void open()
    {
        open(null);
    }

    public void open(List list)
    {
        if(list == null) goto _L2; else goto _L1
_L1:
        Iterator iterator;
        if(!list.isEmpty());
        if(list.size() <= 10);
        iterator = list.iterator();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        String s;
        if(list == null || list.isEmpty())
        {
            mSessionHandler.sendEmptyMessage(1);
            return;
        } else
        {
            mSessionHandler.sendMessage(mSessionHandler.obtainMessage(1, new TreeMap(convertDimensionsToAttributes(list))));
            return;
        }
_L3:
        s = (String)iterator.next();
        if(s == null)
            throw new IllegalArgumentException("customDimensions cannot contain null elements");
        if(s.length() == 0)
            throw new IllegalArgumentException("customDimensions cannot contain empty elements");
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void registerPush(String s)
    {
        if(DatapointHelper.getApiLevel() >= 8);
        mSessionHandler.sendMessage(mSessionHandler.obtainMessage(9, s));
    }

    public void setCustomerData(String s, String s1)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("key cannot be null");
        } else
        {
            mSessionHandler.sendMessage(mSessionHandler.obtainMessage(8, new Pair(s, s1)));
            return;
        }
    }

    public void setCustomerEmail(String s)
    {
        setCustomerData("email", s);
    }

    public void setCustomerId(String s)
    {
        setCustomerData("customer_id", s);
    }

    public void setCustomerName(String s)
    {
        setCustomerData("customer_name", s);
    }

    public void setLocation(Location location)
    {
        lastLocation = location;
    }

    public void setOptOut(boolean flag)
    {
        Handler handler = mSessionHandler;
        Handler handler1 = mSessionHandler;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        handler.sendMessage(handler1.obtainMessage(6, i, 0));
    }

    public void setPushRegistrationId(String s)
    {
        mSessionHandler.sendMessage(mSessionHandler.obtainMessage(10, s));
    }

    public void tagEvent(String s)
    {
        tagEvent(s, null);
    }

    public void tagEvent(String s, Map map)
    {
        tagEvent(s, map, null);
    }

    public void tagEvent(String s, Map map, List list)
    {
        tagEvent(s, map, list, 0L);
    }

    public void tagEvent(String s, Map map, List list, long l)
    {
        if(s == null)
            throw new IllegalArgumentException("event cannot be null");
        if(s.length() == 0)
            throw new IllegalArgumentException("event cannot be empty");
        if(map == null) goto _L2; else goto _L1
_L1:
        Iterator iterator2;
        if(!map.isEmpty());
        if(map.size() <= 50);
        iterator2 = map.entrySet().iterator();
_L15:
        if(iterator2.hasNext()) goto _L3; else goto _L2
_L2:
        if(list == null) goto _L5; else goto _L4
_L4:
        Iterator iterator1;
        if(!list.isEmpty());
        if(list.size() <= 10);
        iterator1 = list.iterator();
_L8:
        if(iterator1.hasNext()) goto _L6; else goto _L5
_L5:
        String s1;
        Object aobj[] = new Object[2];
        aobj[0] = mContext.getPackageName();
        aobj[1] = s;
        s1 = String.format("%s:%s", aobj);
        if(map == null && list == null)
        {
            mSessionHandler.sendMessage(mSessionHandler.obtainMessage(3, new Triple(s1, null, Long.valueOf(l))));
            return;
        }
        break; /* Loop/switch isn't completed */
_L3:
        java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator2.next();
        String s4 = (String)entry1.getKey();
        String s5 = (String)entry1.getValue();
        if(s4 == null)
            throw new IllegalArgumentException("attributes cannot contain null keys");
        if(s5 == null)
            throw new IllegalArgumentException("attributes cannot contain null values");
        if(s4.length() == 0)
            throw new IllegalArgumentException("attributes cannot contain empty keys");
        if(s5.length() == 0)
            throw new IllegalArgumentException("attributes cannot contain empty values");
        continue; /* Loop/switch isn't completed */
_L6:
        String s3 = (String)iterator1.next();
        if(s3 == null)
            throw new IllegalArgumentException("customDimensions cannot contain null elements");
        if(s3.length() == 0)
            throw new IllegalArgumentException("customDimensions cannot contain empty elements");
        if(true) goto _L8; else goto _L7
_L7:
        TreeMap treemap = new TreeMap();
        if(map == null) goto _L10; else goto _L9
_L9:
        String s2;
        Iterator iterator;
        s2 = mContext.getPackageName();
        iterator = map.entrySet().iterator();
_L13:
        if(iterator.hasNext()) goto _L11; else goto _L10
_L10:
        if(list != null)
            treemap.putAll(convertDimensionsToAttributes(list));
        mSessionHandler.sendMessage(mSessionHandler.obtainMessage(3, new Triple(s1, new TreeMap(treemap), Long.valueOf(l))));
        return;
_L11:
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        Object aobj1[] = new Object[2];
        aobj1[0] = s2;
        aobj1[1] = entry.getKey();
        treemap.put(String.format("%s:%s", aobj1), (String)entry.getValue());
        if(true) goto _L13; else goto _L12
_L12:
        if(true) goto _L15; else goto _L14
_L14:
    }

    public void tagScreen(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("event cannot be null");
        if(s.length() == 0)
        {
            throw new IllegalArgumentException("event cannot be empty");
        } else
        {
            mSessionHandler.sendMessage(mSessionHandler.obtainMessage(7, s));
            return;
        }
    }

    public void upload()
    {
        mSessionHandler.sendMessage(mSessionHandler.obtainMessage(4, null));
    }

    static final String CAMPAIGN_ID_ATTRIBUTE = "Campaign ID";
    static final String CLOSE_EVENT = String.format("%s:%s", new Object[] {
        "com.localytics.android", "close"
    });
    static final String CREATIVE_ID_ATTRIBUTE = "Creative ID";
    static final String EVENT_FORMAT = "%s:%s";
    static final String FLOW_EVENT = String.format("%s:%s", new Object[] {
        "com.localytics.android", "flow"
    });
    static final String OPEN_EVENT = String.format("%s:%s", new Object[] {
        "com.localytics.android", "open"
    });
    static final String OPT_IN_EVENT = String.format("%s:%s", new Object[] {
        "com.localytics.android", "opt_in"
    });
    static final String OPT_OUT_EVENT = String.format("%s:%s", new Object[] {
        "com.localytics.android", "opt_out"
    });
    static final String PUSH_OPENED_EVENT = "Localytics Push Opened";
    private static Location lastLocation = null;
    protected static final Map sIsUploadingMap = new HashMap();
    private static final Map sLocalyticsSessionHandlerMap = new HashMap();
    private static final Object sLocalyticsSessionIntrinsicLock[] = new Object[0];
    private static final HandlerThread sSessionHandlerThread = getHandlerThread(com/localytics/android/LocalyticsSession$SessionHandler.getSimpleName());
    protected static final HandlerThread sUploadHandlerThread = getHandlerThread(com/localytics/android/LocalyticsSession$UploadHandler.getSimpleName());
    private final Context mContext;
    private final Handler mSessionHandler;


}
