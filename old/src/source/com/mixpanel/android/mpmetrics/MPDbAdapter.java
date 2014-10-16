// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.*;
import java.io.File;
import org.json.*;

class MPDbAdapter
{
    private static class MPDatabaseHelper extends SQLiteOpenHelper
    {

        public void deleteDatabase()
        {
            close();
            mDatabaseFile.delete();
        }

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            String s = MPDbAdapter.CREATE_EVENTS_TABLE;
            String s1;
            String s2;
            String s3;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s);
            s1 = MPDbAdapter.CREATE_PEOPLE_TABLE;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s1);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s1);
            s2 = MPDbAdapter.EVENTS_TIME_INDEX;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s2);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s2);
            s3 = MPDbAdapter.PEOPLE_TIME_INDEX;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.execSQL(s3);
                return;
            } else
            {
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s3);
                return;
            }
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            String s = (new StringBuilder()).append("DROP TABLE IF EXISTS ").append(Table.EVENTS.getName()).toString();
            String s1;
            String s2;
            String s3;
            String s4;
            String s5;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s);
            s1 = (new StringBuilder()).append("DROP TABLE IF EXISTS ").append(Table.PEOPLE.getName()).toString();
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s1);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s1);
            s2 = MPDbAdapter.CREATE_EVENTS_TABLE;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s2);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s2);
            s3 = MPDbAdapter.CREATE_PEOPLE_TABLE;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s3);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s3);
            s4 = MPDbAdapter.EVENTS_TIME_INDEX;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s4);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s4);
            s5 = MPDbAdapter.PEOPLE_TIME_INDEX;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.execSQL(s5);
                return;
            } else
            {
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s5);
                return;
            }
        }

        private final File mDatabaseFile;

        MPDatabaseHelper(Context context, String s)
        {
            super(context, s, null, 4);
            mDatabaseFile = context.getDatabasePath(s);
        }
    }

    public static final class Table extends Enum
    {

        public static Table valueOf(String s)
        {
            return (Table)Enum.valueOf(com/mixpanel/android/mpmetrics/MPDbAdapter$Table, s);
        }

        public static Table[] values()
        {
            return (Table[])$VALUES.clone();
        }

        public String getName()
        {
            return mTableName;
        }

        private static final Table $VALUES[];
        public static final Table EVENTS;
        public static final Table PEOPLE;
        private final String mTableName;

        static 
        {
            EVENTS = new Table("EVENTS", 0, "events");
            PEOPLE = new Table("PEOPLE", 1, "people");
            Table atable[] = new Table[2];
            atable[0] = EVENTS;
            atable[1] = PEOPLE;
            $VALUES = atable;
        }

        private Table(String s, int i, String s1)
        {
            super(s, i);
            mTableName = s1;
        }
    }


    public MPDbAdapter(Context context)
    {
        this(context, "mixpanel");
    }

    public MPDbAdapter(Context context, String s)
    {
        mDb = new MPDatabaseHelper(context, s);
    }

    public int addJSON(JSONObject jsonobject, Table table)
    {
        String s;
        Cursor cursor;
        int i;
        s = table.getName();
        cursor = null;
        i = -1;
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        boolean flag;
        sqlitedatabase = mDb.getWritableDatabase();
        contentvalues = new ContentValues();
        flag = jsonobject instanceof JSONObject;
        cursor = null;
        if(flag) goto _L2; else goto _L1
_L1:
        String s1 = jsonobject.toString();
_L7:
        boolean flag1;
        contentvalues.put("data", s1);
        contentvalues.put("created_at", Long.valueOf(System.currentTimeMillis()));
        flag1 = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag1) goto _L4; else goto _L3
_L3:
        sqlitedatabase.insert(s, null, contentvalues);
_L8:
        String s2;
        boolean flag2;
        s2 = (new StringBuilder()).append("SELECT COUNT(*) FROM ").append(s).toString();
        flag2 = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag2) goto _L6; else goto _L5
_L5:
        cursor = sqlitedatabase.rawQuery(s2, null);
_L11:
        int j;
        cursor.moveToFirst();
        j = cursor.getInt(0);
        i = j;
        mDb.close();
        if(cursor != null)
            cursor.close();
_L10:
        return i;
_L2:
        s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
          goto _L7
_L4:
        SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, s, null, contentvalues);
          goto _L8
        SQLiteException sqliteexception;
        sqliteexception;
        Log.e("MixpanelAPI", (new StringBuilder()).append("addJSON ").append(s).append(" FAILED. Deleting DB.").toString(), sqliteexception);
        mDb.deleteDatabase();
        mDb.close();
        if(cursor == null) goto _L10; else goto _L9
_L9:
        cursor.close();
        return i;
_L6:
        Cursor cursor1 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, s2, null);
        cursor = cursor1;
          goto _L11
        Exception exception;
        exception;
        mDb.close();
        if(cursor != null)
            cursor.close();
        throw exception;
          goto _L7
    }

    public void cleanupEvents(long l, Table table)
    {
        String s = table.getName();
        SQLiteDatabase sqlitedatabase;
        String s1;
        sqlitedatabase = mDb.getWritableDatabase();
        s1 = (new StringBuilder()).append("created_at <= ").append(l).toString();
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        sqlitedatabase.delete(s, s1, null);
_L4:
        mDb.close();
        return;
_L2:
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, s, s1, null);
        if(true) goto _L4; else goto _L3
_L3:
        SQLiteException sqliteexception;
        sqliteexception;
        Log.e("MixpanelAPI", (new StringBuilder()).append("cleanupEvents ").append(s).append(" by time FAILED. Deleting DB.").toString(), sqliteexception);
        mDb.deleteDatabase();
        mDb.close();
        return;
        Exception exception;
        exception;
        mDb.close();
        throw exception;
    }

    public void cleanupEvents(String s, Table table)
    {
        String s1 = table.getName();
        SQLiteDatabase sqlitedatabase;
        String s2;
        sqlitedatabase = mDb.getWritableDatabase();
        s2 = (new StringBuilder()).append("_id <= ").append(s).toString();
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        sqlitedatabase.delete(s1, s2, null);
_L4:
        mDb.close();
        return;
_L2:
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, s1, s2, null);
        if(true) goto _L4; else goto _L3
_L3:
        SQLiteException sqliteexception;
        sqliteexception;
        Log.e("MixpanelAPI", (new StringBuilder()).append("cleanupEvents ").append(s1).append(" by id FAILED. Deleting DB.").toString(), sqliteexception);
        mDb.deleteDatabase();
        mDb.close();
        return;
        Exception exception;
        exception;
        mDb.close();
        throw exception;
    }

    public void deleteDB()
    {
        mDb.deleteDatabase();
    }

    public String[] generateDataString(Table table)
    {
        Cursor cursor;
        String s;
        String s1;
        cursor = null;
        s = null;
        s1 = table.getName();
        SQLiteDatabase sqlitedatabase;
        String s3;
        boolean flag;
        sqlitedatabase = mDb.getReadableDatabase();
        s3 = (new StringBuilder()).append("SELECT * FROM ").append(s1).append(" ORDER BY ").append("created_at").append(" ASC LIMIT 50").toString();
        flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag) goto _L2; else goto _L1
_L1:
        cursor = sqlitedatabase.rawQuery(s3, null);
_L7:
        JSONArray jsonarray = new JSONArray();
_L6:
        if(!cursor.moveToNext())
            break; /* Loop/switch isn't completed */
        if(!cursor.isLast()) goto _L4; else goto _L3
_L3:
        String s6 = cursor.getString(cursor.getColumnIndex("_id"));
        s = s6;
_L4:
        JVM INSTR new #102 <Class JSONObject>;
        jsonarray.put(JSONObjectInstrumentation.init(cursor.getString(cursor.getColumnIndex("data"))));
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        if(true) goto _L6; else goto _L5
_L2:
        cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, s3, null);
          goto _L7
_L5:
        int i = jsonarray.length();
        String s2 = null;
        if(i <= 0) goto _L9; else goto _L8
_L8:
        if(jsonarray instanceof JSONArray) goto _L11; else goto _L10
_L10:
        String s5 = jsonarray.toString();
        s2 = s5;
_L9:
        mDb.close();
        if(cursor != null)
            cursor.close();
_L13:
        String as[] = null;
        if(s != null)
        {
            as = null;
            if(s2 != null)
                as = (new String[] {
                    s, s2
                });
        }
        return as;
_L11:
        String s4 = JSONArrayInstrumentation.toString((JSONArray)jsonarray);
        s2 = s4;
        continue; /* Loop/switch isn't completed */
        SQLiteException sqliteexception;
        sqliteexception;
        Log.e("MixpanelAPI", (new StringBuilder()).append("generateDataString ").append(s1).toString(), sqliteexception);
        mDb.close();
        s2 = null;
        s = null;
        if(cursor != null)
        {
            cursor.close();
            s2 = null;
            s = null;
        }
        if(true) goto _L13; else goto _L12
_L12:
        Exception exception;
        exception;
        mDb.close();
        if(cursor != null)
            cursor.close();
        throw exception;
        if(true) goto _L9; else goto _L14
_L14:
    }

    private static final String CREATE_EVENTS_TABLE;
    private static final String CREATE_PEOPLE_TABLE;
    private static final String DATABASE_NAME = "mixpanel";
    private static final int DATABASE_VERSION = 4;
    private static final String EVENTS_TIME_INDEX;
    public static final String KEY_CREATED_AT = "created_at";
    public static final String KEY_DATA = "data";
    private static final String LOGTAG = "MixpanelAPI";
    private static final String PEOPLE_TIME_INDEX;
    private final MPDatabaseHelper mDb;

    static 
    {
        CREATE_EVENTS_TABLE = (new StringBuilder()).append("CREATE TABLE ").append(Table.EVENTS.getName()).append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ").append("data").append(" STRING NOT NULL, ").append("created_at").append(" INTEGER NOT NULL);").toString();
        CREATE_PEOPLE_TABLE = (new StringBuilder()).append("CREATE TABLE ").append(Table.PEOPLE.getName()).append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ").append("data").append(" STRING NOT NULL, ").append("created_at").append(" INTEGER NOT NULL);").toString();
        EVENTS_TIME_INDEX = (new StringBuilder()).append("CREATE INDEX IF NOT EXISTS time_idx ON ").append(Table.EVENTS.getName()).append(" (").append("created_at").append(");").toString();
        PEOPLE_TIME_INDEX = (new StringBuilder()).append("CREATE INDEX IF NOT EXISTS time_idx ON ").append(Table.PEOPLE.getName()).append(" (").append("created_at").append(");").toString();
    }




}
