// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.util.Log;
import android.util.Pair;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import io.segment.android.*;
import io.segment.android.models.BasePayload;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package io.segment.android.db:
//            JsonPayloadSerializer, IPayloadSerializer

public class PayloadDatabase extends SQLiteOpenHelper
{

    private PayloadDatabase(Context context)
    {
        super(context, io.segment.android.Constants.Database.NAME, null, 1);
        serializer = new JsonPayloadSerializer();
        count = new AtomicLong();
    }

    private long countRows()
    {
        String s;
        long l;
        s = String.format("SELECT COUNT(*) FROM %s", new Object[] {
            "payload_table"
        });
        l = 0L;
        SQLiteDatabase sqlitedatabase = null;
        this;
        JVM INSTR monitorenter ;
        long l1;
        sqlitedatabase = getWritableDatabase();
        l1 = sqlitedatabase.compileStatement(s).simpleQueryForLong();
        l = l1;
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_52;
        sqlitedatabase.close();
_L2:
        this;
        JVM INSTR monitorexit ;
        return l;
        SQLiteException sqliteexception;
        sqliteexception;
        Logger.e((new StringBuilder("Failed to ensure row count in the Segment.io payload db: ")).append(Log.getStackTraceString(sqliteexception)).toString());
        if(sqlitedatabase == null) goto _L2; else goto _L1
_L1:
        sqlitedatabase.close();
          goto _L2
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_113;
        sqlitedatabase.close();
        throw exception;
    }

    private void ensureCount()
    {
        if(!initialCount)
        {
            count.set(countRows());
            initialCount = true;
        }
    }

    public static PayloadDatabase getInstance(Context context)
    {
        if(instance == null)
            instance = new PayloadDatabase(context);
        return instance;
    }

    public boolean addPayload(BasePayload basepayload)
    {
        boolean flag;
        ensureCount();
        if(getRowCount() >= (long)Analytics.getOptions().getMaxQueueSize())
        {
            Logger.w("Cant add action, the database is larger than max queue size.");
            return false;
        }
        flag = false;
        String s = serializer.serialize(basepayload);
        this;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = null;
        ContentValues contentvalues;
        boolean flag1;
        sqlitedatabase = getWritableDatabase();
        contentvalues = new ContentValues();
        contentvalues.put("payload", s);
        flag1 = sqlitedatabase instanceof SQLiteDatabase;
        flag = false;
        if(flag1) goto _L2; else goto _L1
_L1:
        long l = sqlitedatabase.insert("payload_table", null, contentvalues);
_L5:
        int i;
        i = l != -1L;
        flag = false;
        if(i != 0) goto _L4; else goto _L3
_L3:
        Logger.w((new StringBuilder("Database insert failed. Result: ")).append(l).toString());
_L6:
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_138;
        sqlitedatabase.close();
_L8:
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
_L2:
        l = SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "payload_table", null, contentvalues);
          goto _L5
_L4:
        flag = true;
        count.addAndGet(1L);
          goto _L6
        SQLiteException sqliteexception;
        sqliteexception;
        Logger.e((new StringBuilder("Failed to open or write to Segment.io payload db: ")).append(Log.getStackTraceString(sqliteexception)).toString());
        if(sqlitedatabase == null) goto _L8; else goto _L7
_L7:
        sqlitedatabase.close();
          goto _L8
        Exception exception;
        exception;
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_231;
        sqlitedatabase.close();
        throw exception;
          goto _L5
    }

    public List getEvents(int i)
    {
        LinkedList linkedlist;
        SQLiteDatabase sqlitedatabase;
        linkedlist = new LinkedList();
        sqlitedatabase = null;
        Cursor cursor = null;
        this;
        JVM INSTR monitorenter ;
        String as[];
        String s;
        boolean flag;
        sqlitedatabase = getWritableDatabase();
        as = io.segment.android.Constants.Database.PayloadTable.FIELD_NAMES;
        s = (new StringBuilder()).append(i).toString();
        flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag) goto _L2; else goto _L1
_L1:
        cursor = sqlitedatabase.query("payload_table", as, null, null, null, null, "id ASC", s);
_L5:
        boolean flag1 = cursor.moveToNext();
        if(flag1) goto _L4; else goto _L3
_L3:
        if(cursor == null)
            break MISSING_BLOCK_LABEL_99;
        cursor.close();
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_107;
        sqlitedatabase.close();
_L10:
        this;
        JVM INSTR monitorexit ;
        return linkedlist;
_L2:
        cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "payload_table", as, null, null, null, null, "id ASC", s);
          goto _L5
_L4:
        long l;
        BasePayload basepayload;
        l = cursor.getLong(0);
        String s1 = cursor.getString(1);
        basepayload = serializer.deseralize(s1);
        if(basepayload == null) goto _L5; else goto _L6
_L6:
        linkedlist.add(new Pair(Long.valueOf(l), basepayload));
          goto _L5
        SQLiteException sqliteexception;
        sqliteexception;
        Logger.e((new StringBuilder("Failed to open or read from the Segment.io payload db: ")).append(Log.getStackTraceString(sqliteexception)).toString());
        if(cursor == null) goto _L8; else goto _L7
_L7:
        cursor.close();
_L8:
        if(sqlitedatabase == null) goto _L10; else goto _L9
_L9:
        sqlitedatabase.close();
          goto _L10
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        if(cursor == null)
            break MISSING_BLOCK_LABEL_266;
        cursor.close();
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_274;
        sqlitedatabase.close();
        throw exception;
          goto _L5
    }

    public long getRowCount()
    {
        if(!initialCount)
            ensureCount();
        return count.get();
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        String s = String.format("CREATE TABLE IF NOT EXISTS %s (%s %s, %s %s);", new Object[] {
            "payload_table", "id", "INTEGER PRIMARY KEY AUTOINCREMENT", "payload", " TEXT"
        });
        if(!(sqlitedatabase instanceof SQLiteDatabase))
        {
            sqlitedatabase.execSQL(s);
            return;
        }
        try
        {
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s);
            return;
        }
        catch(SQLException sqlexception)
        {
            Logger.e((new StringBuilder("Failed to create Segment.io SQL lite database: ")).append(Log.getStackTraceString(sqlexception)).toString());
        }
        return;
    }

    public void onOpen(SQLiteDatabase sqlitedatabase)
    {
        super.onOpen(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
    }

    public int removeEvents(long l, long l1)
    {
        SQLiteDatabase sqlitedatabase;
        String s;
        ensureCount();
        sqlitedatabase = null;
        Object aobj[] = new Object[4];
        aobj[0] = "id";
        aobj[1] = Long.valueOf(l);
        aobj[2] = "id";
        aobj[3] = Long.valueOf(l1);
        s = String.format("%s >= %d AND %s <= %d", aobj);
        int i = -1;
        this;
        JVM INSTR monitorenter ;
        sqlitedatabase = getWritableDatabase();
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        i = sqlitedatabase.delete("payload_table", s, null);
_L3:
        count.addAndGet(-i);
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_104;
        sqlitedatabase.close();
_L5:
        this;
        JVM INSTR monitorexit ;
        return i;
_L2:
        int j = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "payload_table", s, null);
        i = j;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Logger.e((new StringBuilder("Failed to remove items from the Segment.io payload db: ")).append(Log.getStackTraceString(sqliteexception)).toString());
        if(sqlitedatabase == null) goto _L5; else goto _L4
_L4:
        sqlitedatabase.close();
          goto _L5
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_189;
        sqlitedatabase.close();
        throw exception;
          goto _L3
    }

    private static PayloadDatabase instance;
    private AtomicLong count;
    private boolean initialCount;
    private IPayloadSerializer serializer;
}
