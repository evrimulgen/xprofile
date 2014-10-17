// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.localytics.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.provider.BaseColumns;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.util.*;

// Referenced classes of package com.localytics.android:
//            DatapointHelper

final class LocalyticsProvider
{
    public static final class ApiKeysDbColumns
        implements BaseColumns
    {

        public static final String API_KEY = "api_key";
        public static final String CREATED_TIME = "created_time";
        public static final String OPT_OUT = "opt_out";
        public static final String TABLE_NAME = "api_keys";
        public static final String UUID = "uuid";

        private ApiKeysDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    public static final class AttributesDbColumns
        implements BaseColumns
    {

        static final String ATTRIBUTE_CUSTOM_DIMENSION_1 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_0"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_10 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_9"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_2 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_1"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_3 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_2"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_4 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_3"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_5 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_4"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_6 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_5"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_7 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_6"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_8 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_7"
        });
        static final String ATTRIBUTE_CUSTOM_DIMENSION_9 = String.format("%s:%s", new Object[] {
            "com.localytics.android", "custom_dimension_8"
        });
        static final String ATTRIBUTE_FORMAT = "%s:%s";
        public static final String ATTRIBUTE_KEY = "attribute_key";
        public static final String ATTRIBUTE_VALUE = "attribute_value";
        public static final String EVENTS_KEY_REF = "events_key_ref";
        public static final String TABLE_NAME = "attributes";


        private AttributesDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    private static final class DatabaseHelper extends SQLiteOpenHelper
    {

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            if(sqlitedatabase == null)
                throw new IllegalArgumentException("db cannot be null");
            String s = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL, %s TEXT UNIQUE NOT NULL, %s INTEGER NOT NULL CHECK (%s >= 0), %s INTEGER NOT NULL CHECK(%s IN (%s, %s)));", new Object[] {
                "api_keys", "_id", "api_key", "uuid", "created_time", "created_time", "opt_out", "opt_out", "0", "1"
            });
            String s1;
            String s2;
            Object aobj[];
            String s3;
            String s4;
            String s5;
            String s6;
            String s7;
            ContentValues contentvalues;
            String s8;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s);
            s1 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER REFERENCES %s(%s) NOT NULL, %s TEXT UNIQUE NOT NULL, %s INTEGER NOT NULL CHECK (%s >= 0), %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT);", new Object[] {
                "sessions", "_id", "api_key_ref", "api_keys", "_id", "uuid", "session_start_wall_time", "session_start_wall_time", "localytics_library_version", "iu", 
                "app_version", "android_version", "android_sdk", "device_model", "device_manufacturer", "device_android_id_hash", "device_telephony_id", "device_telephony_id_hash", "device_serial_number_hash", "device_wifi_mac_hash", 
                "locale_language", "locale_country", "network_carrier", "network_country", "network_type", "device_country", "latitude", "longitude", "device_android_id"
            });
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s1);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s1);
            s2 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER REFERENCES %s(%s) NOT NULL, %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL CHECK (%s >= 0), %s INTEGER NOT NULL CHECK (%s >= 0), %s INTEGER NOT NULL DEFAULT 0, %s REAL, %s REAL);", new Object[] {
                "events", "_id", "session_key_ref", "sessions", "_id", "uuid", "event_name", "real_time", "real_time", "wall_time", 
                "wall_time", "clv_increase", "event_lat", "event_lng"
            });
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s2);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s2);
            aobj = new Object[11];
            aobj[0] = "event_history";
            aobj[1] = "_id";
            aobj[2] = "session_key_ref";
            aobj[3] = "sessions";
            aobj[4] = "_id";
            aobj[5] = "type";
            aobj[6] = "type";
            aobj[7] = Integer.valueOf(0);
            aobj[8] = Integer.valueOf(1);
            aobj[9] = "name";
            aobj[10] = "processed_in_blob";
            s3 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER REFERENCES %s(%s) NOT NULL, %s TEXT NOT NULL CHECK(%s IN (%s, %s)), %s TEXT NOT NULL, %s INTEGER);", aobj);
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s3);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s3);
            s4 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER REFERENCES %s(%s) NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL);", new Object[] {
                "attributes", "_id", "events_key_ref", "events", "_id", "attribute_key", "attribute_value"
            });
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s4);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s4);
            s5 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL);", new Object[] {
                "upload_blobs", "_id", "uuid"
            });
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s5);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s5);
            s6 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER REFERENCES %s(%s) NOT NULL, %s INTEGER REFERENCES %s(%s) NOT NULL);", new Object[] {
                "upload_blob_events", "_id", "upload_blobs_key_ref", "upload_blobs", "_id", "events_key_ref", "events", "_id"
            });
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s6);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s6);
            s7 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER);", new Object[] {
                "info", "_id", "fb_attribution", "play_attribution", "registration_id", "registration_version", "first_android_id", "first_telephony_id", "package_name", "first_run"
            });
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s7);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s7);
            contentvalues = new ContentValues();
            contentvalues.put("fb_attribution", DatapointHelper.getFBAttribution(mContext));
            contentvalues.put("first_run", Boolean.TRUE);
            contentvalues.put("first_android_id", DatapointHelper.getAndroidIdOrNull(mContext));
            contentvalues.put("first_telephony_id", DatapointHelper.getTelephonyDeviceIdOrNull(mContext));
            contentvalues.put("package_name", mContext.getPackageName());
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.insertOrThrow("info", null, contentvalues);
            else
                SQLiteInstrumentation.insertOrThrow((SQLiteDatabase)sqlitedatabase, "info", null, contentvalues);
            s8 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL);", new Object[] {
                "identifiers", "_id", "key", "value"
            });
            if(!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.execSQL(s8);
                return;
            } else
            {
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s8);
                return;
            }
        }

        public void onOpen(SQLiteDatabase sqlitedatabase)
        {
label0:
            {
                super.onOpen(sqlitedatabase);
                if(!sqlitedatabase.isReadOnly())
                {
                    if(sqlitedatabase instanceof SQLiteDatabase)
                        break label0;
                    sqlitedatabase.execSQL("PRAGMA foreign_keys = ON;");
                }
                return;
            }
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "PRAGMA foreign_keys = ON;");
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            String s1;
            Cursor cursor;
            String as[];
            int k;
            int l;
            ContentValues contentvalues2;
            String s12;
            String as1[];
            if(i < 3)
            {
                boolean flag;
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.delete("upload_blob_events", null, null);
                else
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "upload_blob_events", null, null);
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.delete("event_history", null, null);
                else
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "event_history", null, null);
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.delete("upload_blobs", null, null);
                else
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "upload_blobs", null, null);
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.delete("attributes", null, null);
                else
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "attributes", null, null);
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.delete("events", null, null);
                else
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "events", null, null);
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.delete("sessions", null, null);
                else
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "sessions", null, null);
            }
            if(i < 4)
            {
                String s14 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "sessions", "iu"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s14);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s14);
            }
            if(i < 5)
            {
                String s13 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "sessions", "device_wifi_mac_hash"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s13);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s13);
            }
            if(i >= 6) goto _L2; else goto _L1
_L1:
            cursor = null;
            as = (new String[] {
                "_id", "attribute_key"
            });
            if(sqlitedatabase instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
            cursor = sqlitedatabase.query("attributes", as, null, null, null, null, null);
_L7:
            k = cursor.getColumnIndexOrThrow("_id");
            l = cursor.getColumnIndexOrThrow("attribute_key");
            contentvalues2 = new ContentValues();
            s12 = String.format("%s = ?", new Object[] {
                "_id"
            });
            as1 = new String[1];
            cursor.moveToPosition(-1);
_L8:
            flag = cursor.moveToNext();
            if(flag) goto _L6; else goto _L5
_L5:
            if(cursor != null)
                cursor.close();
_L2:
            if(i < 7)
            {
                String s11 = String.format("CREATE TABLE %s (%s TEXT, %s INTEGER);", new Object[] {
                    "info", "fb_attribution", "first_run"
                });
                ContentValues contentvalues1;
                Exception exception;
                Object aobj[];
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s11);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s11);
                contentvalues1 = new ContentValues();
                contentvalues1.putNull("fb_attribution");
                contentvalues1.put("first_run", Boolean.FALSE);
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.insertOrThrow("info", null, contentvalues1);
                else
                    SQLiteInstrumentation.insertOrThrow((SQLiteDatabase)sqlitedatabase, "info", null, contentvalues1);
            }
            if(i < 8)
            {
                String s10 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL);", new Object[] {
                    "identifiers", "_id", "key", "value"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s10);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s10);
            }
            if(i < 9)
            {
                String s9 = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER NOT NULL DEFAULT 0;", new Object[] {
                    "events", "clv_increase"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s9);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s9);
            }
            if(i < 10)
            {
                String s8 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "info", "play_attribution"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s8);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s8);
            }
            if(i < 11)
            {
                String s6 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "info", "registration_id"
                });
                String s7;
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s6);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s6);
                s7 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "info", "registration_version"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s7);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s7);
            }
            if(i < 12)
            {
                String s2 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "info", "first_android_id"
                });
                String s3;
                String s4;
                ContentValues contentvalues;
                String s5;
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s2);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s2);
                s3 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "info", "first_telephony_id"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s3);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s3);
                s4 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "info", "package_name"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s4);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s4);
                contentvalues = new ContentValues();
                contentvalues.put("first_android_id", DatapointHelper.getAndroidIdOrNull(mContext));
                contentvalues.put("first_telephony_id", DatapointHelper.getTelephonyDeviceIdOrNull(mContext));
                contentvalues.put("package_name", mContext.getPackageName());
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.update("info", contentvalues, null, null);
                else
                    SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase, "info", contentvalues, null, null);
                s5 = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[] {
                    "sessions", "device_android_id"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s5);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s5);
            }
            if(i < 13)
            {
                String s = String.format("ALTER TABLE %s ADD COLUMN %s REAL;", new Object[] {
                    "events", "event_lat"
                });
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.execSQL(s);
                else
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s);
                s1 = String.format("ALTER TABLE %s ADD COLUMN %s REAL;", new Object[] {
                    "events", "event_lng"
                });
                if(sqlitedatabase instanceof SQLiteDatabase)
                    break MISSING_BLOCK_LABEL_1322;
                sqlitedatabase.execSQL(s1);
            }
            return;
_L4:
            cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "attributes", as, null, null, null, null, null);
              goto _L7
_L6:
            aobj = new Object[2];
            aobj[0] = mContext.getPackageName();
            aobj[1] = cursor.getString(l);
            contentvalues2.put("attribute_key", String.format("%s:%s", aobj));
            as1[0] = Long.toString(cursor.getLong(k));
            if(sqlitedatabase instanceof SQLiteDatabase)
                break MISSING_BLOCK_LABEL_1138;
            sqlitedatabase.update("attributes", contentvalues2, s12, as1);
_L9:
            contentvalues2.clear();
              goto _L8
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
            SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase, "attributes", contentvalues2, s12, as1);
              goto _L9
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s1);
            return;
              goto _L7
        }

        private static final String SQLITE_BOOLEAN_FALSE = "0";
        private static final String SQLITE_BOOLEAN_TRUE = "1";
        private final Context mContext;

        public DatabaseHelper(Context context, String s, int i)
        {
            super(context, s, null, i);
            mContext = context;
        }
    }

    public static final class EventHistoryDbColumns
        implements BaseColumns
    {

        public static final String NAME = "name";
        public static final String PROCESSED_IN_BLOB = "processed_in_blob";
        public static final String SESSION_KEY_REF = "session_key_ref";
        public static final String TABLE_NAME = "event_history";
        public static final String TYPE = "type";
        public static final int TYPE_EVENT = 0;
        public static final int TYPE_SCREEN = 1;

        private EventHistoryDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    public static final class EventsDbColumns
        implements BaseColumns
    {

        public static final String CLV_INCREASE = "clv_increase";
        public static final String EVENT_NAME = "event_name";
        public static final String LAT_NAME = "event_lat";
        public static final String LNG_NAME = "event_lng";
        public static final String REAL_TIME = "real_time";
        public static final String SESSION_KEY_REF = "session_key_ref";
        public static final String TABLE_NAME = "events";
        public static final String UUID = "uuid";
        public static final String WALL_TIME = "wall_time";

        private EventsDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    public static final class IdentifiersDbColumns
        implements BaseColumns
    {

        public static final String KEY = "key";
        public static final String TABLE_NAME = "identifiers";
        public static final String VALUE = "value";

        private IdentifiersDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    public static final class InfoDbColumns
        implements BaseColumns
    {

        public static final String FB_ATTRIBUTION = "fb_attribution";
        public static final String FIRST_ANDROID_ID = "first_android_id";
        public static final String FIRST_RUN = "first_run";
        public static final String FIRST_TELEPHONY_ID = "first_telephony_id";
        public static final String PACKAGE_NAME = "package_name";
        public static final String PLAY_ATTRIBUTION = "play_attribution";
        public static final String REGISTRATION_ID = "registration_id";
        public static final String REGISTRATION_VERSION = "registration_version";
        public static final String TABLE_NAME = "info";

        private InfoDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    public static final class SessionsDbColumns
        implements BaseColumns
    {

        public static final String ANDROID_SDK = "android_sdk";
        public static final String ANDROID_VERSION = "android_version";
        public static final String API_KEY_REF = "api_key_ref";
        public static final String APP_VERSION = "app_version";
        public static final String DEVICE_ANDROID_ID = "device_android_id";
        public static final String DEVICE_ANDROID_ID_HASH = "device_android_id_hash";
        public static final String DEVICE_COUNTRY = "device_country";
        public static final String DEVICE_MANUFACTURER = "device_manufacturer";
        public static final String DEVICE_MODEL = "device_model";
        public static final String DEVICE_SERIAL_NUMBER_HASH = "device_serial_number_hash";
        public static final String DEVICE_TELEPHONY_ID = "device_telephony_id";
        public static final String DEVICE_TELEPHONY_ID_HASH = "device_telephony_id_hash";
        public static final String DEVICE_WIFI_MAC_HASH = "device_wifi_mac_hash";
        public static final String LATITUDE = "latitude";
        public static final String LOCALE_COUNTRY = "locale_country";
        public static final String LOCALE_LANGUAGE = "locale_language";
        public static final String LOCALYTICS_INSTALLATION_ID = "iu";
        public static final String LOCALYTICS_LIBRARY_VERSION = "localytics_library_version";
        public static final String LONGITUDE = "longitude";
        public static final String NETWORK_CARRIER = "network_carrier";
        public static final String NETWORK_COUNTRY = "network_country";
        public static final String NETWORK_TYPE = "network_type";
        public static final String SESSION_START_WALL_TIME = "session_start_wall_time";
        public static final String TABLE_NAME = "sessions";
        public static final String UUID = "uuid";

        private SessionsDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    public static final class UploadBlobEventsDbColumns
        implements BaseColumns
    {

        public static final String EVENTS_KEY_REF = "events_key_ref";
        public static final String TABLE_NAME = "upload_blob_events";
        public static final String UPLOAD_BLOBS_KEY_REF = "upload_blobs_key_ref";

        private UploadBlobEventsDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    public static final class UploadBlobsDbColumns
        implements BaseColumns
    {

        public static final String TABLE_NAME = "upload_blobs";
        public static final String UUID = "uuid";

        private UploadBlobsDbColumns()
        {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }


    private LocalyticsProvider(Context context, String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = DatapointHelper.getSha256_buggy(s);
        mDb = (new DatabaseHelper(context, String.format("com.localytics.android.%s.sqlite", aobj), 13)).getWritableDatabase();
    }

    private static boolean deleteDirectory(File file)
    {
        if(!file.exists() || !file.isDirectory()) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        int j;
        as = file.list();
        i = as.length;
        j = 0;
_L7:
        if(j < i) goto _L3; else goto _L2
_L2:
        boolean flag = file.delete();
_L5:
        return flag;
_L3:
        boolean flag1;
        flag1 = deleteDirectory(new File(file, as[j]));
        flag = false;
        if(!flag1) goto _L5; else goto _L4
_L4:
        j++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    static void deleteOldFiles(Context context)
    {
        if(context == null)
        {
            throw new IllegalArgumentException("context cannot be null");
        } else
        {
            deleteDirectory(new File(context.getFilesDir(), "localytics"));
            return;
        }
    }

    private static HashMap getCountProjectionMap()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("_count", "COUNT(*)");
        return hashmap;
    }

    public static LocalyticsProvider getInstance(Context context, String s)
    {
        if(context == null)
            throw new IllegalArgumentException("context cannot be null");
        if(context.getClass().getName().equals("android.test.RenamingDelegatingContext"))
            return new LocalyticsProvider(context, s);
        Object aobj[] = sLocalyticsProviderIntrinsicLock;
        aobj;
        JVM INSTR monitorenter ;
        LocalyticsProvider localyticsprovider = (LocalyticsProvider)sLocalyticsProviderMap.get(s);
        if(localyticsprovider != null)
            break MISSING_BLOCK_LABEL_87;
        localyticsprovider = new LocalyticsProvider(context, s);
        sLocalyticsProviderMap.put(s, localyticsprovider);
        aobj;
        JVM INSTR monitorexit ;
        return localyticsprovider;
        Exception exception;
        exception;
        aobj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static Set getValidTables()
    {
        HashSet hashset = new HashSet();
        hashset.add("api_keys");
        hashset.add("attributes");
        hashset.add("events");
        hashset.add("event_history");
        hashset.add("sessions");
        hashset.add("upload_blobs");
        hashset.add("upload_blob_events");
        hashset.add("info");
        hashset.add("identifiers");
        return hashset;
    }

    private static boolean isValidTable(String s)
    {
        if(s == null)
            return false;
        else
            return sValidTables.contains(s);
    }

    void close()
    {
        Object aobj[] = sLocalyticsProviderIntrinsicLock;
        aobj;
        JVM INSTR monitorenter ;
        Iterator iterator = sLocalyticsProviderMap.entrySet().iterator();
_L5:
        boolean flag = iterator.hasNext();
        String s = null;
        if(flag) goto _L2; else goto _L1
_L1:
        if(s == null)
            break MISSING_BLOCK_LABEL_52;
        sLocalyticsProviderMap.remove(s);
        aobj;
        JVM INSTR monitorexit ;
        mDb.close();
        return;
_L2:
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        if(this != entry.getValue())
            break; /* Loop/switch isn't completed */
        s = (String)entry.getKey();
        if(true) goto _L1; else goto _L3
_L3:
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        aobj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int delete(String s, String s1, String as[])
    {
        if(!isValidTable(s))
            throw new IllegalArgumentException(String.format("tableName %s is invalid", new Object[] {
                s
            }));
        if(s1 == null)
        {
            SQLiteDatabase sqlitedatabase1 = mDb;
            if(!(sqlitedatabase1 instanceof SQLiteDatabase))
                return sqlitedatabase1.delete(s, "1", null);
            else
                return SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase1, s, "1", null);
        }
        SQLiteDatabase sqlitedatabase = mDb;
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            return sqlitedatabase.delete(s, s1, as);
        else
            return SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, s, s1, as);
    }

    public long insert(String s, ContentValues contentvalues)
    {
        if(!isValidTable(s))
            throw new IllegalArgumentException(String.format("tableName %s is invalid", new Object[] {
                s
            }));
        if(contentvalues == null)
            throw new IllegalArgumentException("values cannot be null");
        SQLiteDatabase sqlitedatabase = mDb;
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            return sqlitedatabase.insertOrThrow(s, null, contentvalues);
        else
            return SQLiteInstrumentation.insertOrThrow((SQLiteDatabase)sqlitedatabase, s, null, contentvalues);
    }

    public Cursor query(String s, String as[], String s1, String as1[], String s2)
    {
        if(!isValidTable(s))
            throw new IllegalArgumentException(String.format("tableName %s is invalid", new Object[] {
                s
            }));
        SQLiteQueryBuilder sqlitequerybuilder = new SQLiteQueryBuilder();
        sqlitequerybuilder.setTables(s);
        if(as != null && 1 == as.length && "_count".equals(as[0]))
            sqlitequerybuilder.setProjectionMap(sCountProjectionMap);
        return sqlitequerybuilder.query(mDb, as, s1, as1, null, null, s2);
    }

    public void runBatchTransaction(Runnable runnable)
    {
        if(runnable == null)
            throw new IllegalArgumentException("runnable cannot be null");
        mDb.beginTransaction();
        runnable.run();
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        return;
        Exception exception;
        exception;
        mDb.endTransaction();
        throw exception;
    }

    public int update(String s, ContentValues contentvalues, String s1, String as[])
    {
        if(!isValidTable(s))
            throw new IllegalArgumentException(String.format("tableName %s is invalid", new Object[] {
                s
            }));
        SQLiteDatabase sqlitedatabase = mDb;
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            return sqlitedatabase.update(s, contentvalues, s1, as);
        else
            return SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase, s, contentvalues, s1, as);
    }

    static final String DATABASE_FILE = "com.localytics.android.%s.sqlite";
    private static final int DATABASE_VERSION = 13;
    private static final Map sCountProjectionMap = Collections.unmodifiableMap(getCountProjectionMap());
    private static final Object sLocalyticsProviderIntrinsicLock[] = new Object[0];
    private static final Map sLocalyticsProviderMap = new HashMap();
    private static final Set sValidTables = Collections.unmodifiableSet(getValidTables());
    private final SQLiteDatabase mDb;

}
