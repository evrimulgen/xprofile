// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.*;

// Referenced classes of package com.quantcast.measurement.service:
//            QCEvent, QCLog, QCPolicy

class QCDatabaseDAO extends SQLiteOpenHelper
{

    QCDatabaseDAO(Context context)
    {
        super(context, "Quantcast.db", null, 2);
        m_numOpenDBs = 0;
    }

    private static void addEventIdIndex(SQLiteDatabase sqlitedatabase)
    {
        if(!(sqlitedatabase instanceof SQLiteDatabase))
        {
            sqlitedatabase.execSQL("CREATE INDEX event_id_idx ON event (eventid)");
            return;
        } else
        {
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "CREATE INDEX event_id_idx ON event (eventid)");
            return;
        }
    }

    private static String generateDeleteClause(String s, String s1, String s2)
    {
        return (new StringBuilder()).append("delete from ").append(s).append(" where ").append(s1).append(" in (").append(s2).append(");").toString();
    }

    public void close()
    {
        m_numOpenDBs = -1 + m_numOpenDBs;
        if(m_numOpenDBs == 0)
        {
            super.close();
            m_openDB = null;
        }
    }

    void deleteDB(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        close();
        context.deleteDatabase("Quantcast.db");
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    List getEvents(int i, QCPolicy qcpolicy)
    {
        this;
        JVM INSTR monitorenter ;
        List list;
        list = getEvents(getReadableDatabase(), i, qcpolicy);
        close();
        this;
        JVM INSTR monitorexit ;
        return list;
        Exception exception;
        exception;
        throw exception;
    }

    List getEvents(SQLiteDatabase sqlitedatabase, int i, QCPolicy qcpolicy)
    {
        this;
        JVM INSTR monitorenter ;
        if(sqlitedatabase == null) goto _L2; else goto _L1
_L1:
        if(!sqlitedatabase.isOpen()) goto _L2; else goto _L3
_L3:
        ArrayList arraylist1 = new ArrayList();
        if(i <= 0)
            break MISSING_BLOCK_LABEL_323;
        String as[];
        String s;
        as = (new String[] {
            "id"
        });
        s = Integer.toString(i);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L5; else goto _L4
_L4:
        Cursor cursor = sqlitedatabase.query("events", as, null, null, null, null, "id", s);
_L10:
        if(!cursor.moveToFirst()) goto _L7; else goto _L6
_L6:
        long l;
        String as1[];
        String as2[];
        l = cursor.getLong(0);
        as1 = (new String[] {
            "name", "value"
        });
        as2 = new String[1];
        as2[0] = Long.toString(l);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L9; else goto _L8
_L8:
        Cursor cursor2 = sqlitedatabase.query("event", as1, "eventid=?", as2, null, null, null);
_L11:
        HashMap hashmap = new HashMap();
        if(cursor2.moveToFirst())
            do
                hashmap.put(cursor2.getString(0), cursor2.getString(1));
            while(cursor2.moveToNext());
        cursor2.close();
        arraylist1.add(QCEvent.dataBaseEventWithPolicyCheck(Long.valueOf(l), hashmap, qcpolicy));
        if(cursor.moveToNext()) goto _L6; else goto _L7
_L7:
        cursor.close();
        ArrayList arraylist = arraylist1;
_L12:
        this;
        JVM INSTR monitorexit ;
        return arraylist;
_L5:
        cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "events", as, null, null, null, null, "id", s);
          goto _L10
_L9:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "event", as1, "eventid=?", as2, null, null, null);
        cursor2 = cursor1;
          goto _L11
_L2:
        QCLog.e(TAG, "Database could not be opened.(1)");
        arraylist = null;
          goto _L12
        Exception exception;
        exception;
_L14:
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        if(true) goto _L14; else goto _L13
_L13:
        arraylist = arraylist1;
          goto _L12
    }

    public SQLiteDatabase getReadableDatabase()
    {
        return getWritableDatabase();
    }

    public SQLiteDatabase getWritableDatabase()
    {
        if(m_openDB == null || !m_openDB.isOpen())
        {
            m_numOpenDBs = 0;
            m_openDB = super.getWritableDatabase();
        }
        m_numOpenDBs = 1 + m_numOpenDBs;
        return m_openDB;
    }

    long numberOfEvents()
    {
        long l = rowCountForTable(getReadableDatabase(), "events");
        close();
        return l;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        sqlitedatabase.execSQL("PRAGMA foreign_keys = ON;");
_L5:
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        sqlitedatabase.execSQL("create table events (id integer primary key autoincrement,doh integer);");
_L6:
        if(sqlitedatabase instanceof SQLiteDatabase)
            break MISSING_BLOCK_LABEL_102;
        sqlitedatabase.execSQL("create table event (eventid integer,name varchar not null,value varchar not null,FOREIGN KEY(eventid) REFERENCES events(id));");
_L7:
        addEventIdIndex(sqlitedatabase);
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
_L2:
        SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "PRAGMA foreign_keys = ON;");
          goto _L5
        SQLException sqlexception;
        sqlexception;
        QCLog.e(TAG, "Unable to create events related tables", sqlexception);
        sqlitedatabase.endTransaction();
        return;
_L4:
        SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "create table events (id integer primary key autoincrement,doh integer);");
          goto _L6
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
        SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "create table event (eventid integer,name varchar not null,value varchar not null,FOREIGN KEY(eventid) REFERENCES events(id));");
          goto _L7
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        sqlitedatabase.beginTransaction();
        if(i > 1)
            break MISSING_BLOCK_LABEL_13;
        addEventIdIndex(sqlitedatabase);
        sqlitedatabase.endTransaction();
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    void removeAllEvents()
    {
        this;
        JVM INSTR monitorenter ;
        removeAllEvents(getWritableDatabase());
        close();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void removeAllEvents(SQLiteDatabase sqlitedatabase)
    {
        this;
        JVM INSTR monitorenter ;
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_114;
        sqlitedatabase.beginTransaction();
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        sqlitedatabase.delete("event", null, null);
_L5:
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        sqlitedatabase.delete("events", null, null);
_L7:
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
_L6:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "event", null, null);
          goto _L5
        Exception exception2;
        exception2;
        QCLog.e(TAG, "Cannot clear events.", exception2);
        sqlitedatabase.endTransaction();
          goto _L6
        Exception exception;
        exception;
        throw exception;
_L4:
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "events", null, null);
          goto _L7
        Exception exception1;
        exception1;
        sqlitedatabase.endTransaction();
        throw exception1;
        QCLog.e(TAG, "Database could not be opened.(3)");
          goto _L6
    }

    boolean removeEvents(SQLiteDatabase sqlitedatabase, Collection collection)
    {
        this;
        JVM INSTR monitorenter ;
        if(sqlitedatabase == null) goto _L2; else goto _L1
_L1:
        if(!sqlitedatabase.isOpen()) goto _L2; else goto _L3
_L3:
        boolean flag1 = collection.isEmpty();
        boolean flag = false;
        if(flag1) goto _L5; else goto _L4
_L4:
        ArrayList arraylist;
        arraylist = new ArrayList(collection.size());
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); arraylist.add(((QCEvent)iterator.next()).getEventId()));
        break MISSING_BLOCK_LABEL_92;
        Exception exception;
        exception;
        throw exception;
        String s;
        s = TextUtils.join(",", arraylist);
        sqlitedatabase.beginTransaction();
        String s1 = generateDeleteClause("events", "id", s);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L7; else goto _L6
_L6:
        sqlitedatabase.execSQL(s1);
_L10:
        String s2 = generateDeleteClause("event", "eventid", s);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L9; else goto _L8
_L8:
        sqlitedatabase.execSQL(s2);
_L11:
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        flag = true;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L7:
        SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s1);
          goto _L10
        Exception exception1;
        exception1;
        sqlitedatabase.endTransaction();
        throw exception1;
_L9:
        SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s2);
          goto _L11
_L2:
        QCLog.e(TAG, "Database could not be opened.(2)");
        flag = false;
          goto _L5
    }

    boolean removeEvents(Collection collection)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        flag = removeEvents(getWritableDatabase(), collection);
        close();
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    long rowCountForTable(SQLiteDatabase sqlitedatabase, String s)
    {
        if(sqlitedatabase != null && sqlitedatabase.isOpen())
        {
            return DatabaseUtils.queryNumEntries(sqlitedatabase, s);
        } else
        {
            QCLog.e(TAG, "Database could not be opened.(6)");
            return 0L;
        }
    }

    int writeEvents(SQLiteDatabase sqlitedatabase, Collection collection)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = collection.isEmpty();
        int i = 0;
        if(flag) goto _L2; else goto _L1
_L1:
        if(sqlitedatabase == null) goto _L4; else goto _L3
_L3:
        if(!sqlitedatabase.isOpen()) goto _L4; else goto _L5
_L5:
        SQLiteStatement sqlitestatement;
        SQLiteStatement sqlitestatement1;
        sqlitestatement = sqlitedatabase.compileStatement("INSERT INTO events ( doh ) VALUES ( ? )");
        sqlitestatement1 = sqlitedatabase.compileStatement("INSERT INTO event ( eventid,name,value ) VALUES ( ? , ? , ?)");
        i = 0;
        if(sqlitestatement == null) goto _L2; else goto _L6
_L6:
        i = 0;
        if(sqlitestatement1 == null) goto _L2; else goto _L7
_L7:
        sqlitedatabase.beginTransaction();
        Iterator iterator = collection.iterator();
_L10:
        if(!iterator.hasNext()) goto _L9; else goto _L8
_L8:
        QCEvent qcevent;
        long l;
        qcevent = (QCEvent)iterator.next();
        sqlitestatement.clearBindings();
        sqlitestatement.bindLong(1, 0L);
        l = sqlitestatement.executeInsert();
        if(l >= 0L)
            break MISSING_BLOCK_LABEL_183;
        QCLog.e(TAG, (new StringBuilder()).append("Unable to save ").append(qcevent).append(". See DatabaseUtils logs for a detailed stack trace.").toString());
          goto _L10
        Exception exception1;
        exception1;
        sqlitedatabase.endTransaction();
        sqlitestatement.close();
        sqlitestatement1.close();
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        for(Iterator iterator1 = qcevent.getParameters().entrySet().iterator(); iterator1.hasNext(); sqlitestatement1.execute())
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            String s = (String)entry.getKey();
            String s1 = (String)entry.getValue();
            sqlitestatement1.clearBindings();
            sqlitestatement1.bindLong(1, l);
            sqlitestatement1.bindString(2, s);
            sqlitestatement1.bindString(3, s1);
        }

        break MISSING_BLOCK_LABEL_321;
_L9:
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        sqlitestatement.close();
        sqlitestatement1.close();
_L2:
        this;
        JVM INSTR monitorexit ;
        return i;
_L4:
        QCLog.e(TAG, "Database could not be opened.(4)");
        i = 0;
          goto _L2
        i++;
          goto _L10
    }

    int writeEvents(Collection collection)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = writeEvents(getWritableDatabase(), collection);
        close();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    static final String EVENTS_COLUMN_DOH = "doh";
    static final String EVENTS_COLUMN_ID = "id";
    static final String EVENTS_TABLE = "events";
    static final String EVENT_PARAMETERS_COLUMN_EVENT_ID = "eventid";
    static final String EVENT_PARAMETERS_COLUMN_NAME = "name";
    static final String EVENT_PARAMETERS_COLUMN_VALUE = "value";
    static final String EVENT_PARAMETERS_EVENT_ID_INDEX_NAME = "event_id_idx";
    static final String EVENT_PARAMETERS_TABLE = "event";
    static final String NAME = "Quantcast.db";
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCDatabaseDAO);
    private static final int VERSION = 2;
    private int m_numOpenDBs;
    private SQLiteDatabase m_openDB;

}
