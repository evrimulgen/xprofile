// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.*;
import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.util.*;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.google.tagmanager:
//            HitStore, SimpleNetworkDispatcher, Log, HitStoreStateListener, 
//            Dispatcher, Clock, ServiceManagerImpl, Hit, 
//            FutureApis

class PersistentHitStore
    implements HitStore
{
    class StoreDispatchListener
        implements SimpleNetworkDispatcher.DispatchListener
    {

        public void onHitDispatched(Hit hit)
        {
            deleteHit(hit.getHitId());
        }

        public void onHitPermanentDispatchFailure(Hit hit)
        {
            deleteHit(hit.getHitId());
            Log.v((new StringBuilder()).append("Permanent failure dispatching hitId: ").append(hit.getHitId()).toString());
        }

        public void onHitTransientDispatchFailure(Hit hit)
        {
            long l = hit.getHitFirstDispatchTime();
            if(l == 0L)
                setHitFirstDispatchTime(hit.getHitId(), mClock.currentTimeMillis());
            else
            if(0xdbba00L + l < mClock.currentTimeMillis())
            {
                deleteHit(hit.getHitId());
                Log.v((new StringBuilder()).append("Giving up on failed hitId: ").append(hit.getHitId()).toString());
                return;
            }
        }

        final PersistentHitStore this$0;

        StoreDispatchListener()
        {
            this$0 = PersistentHitStore.this;
            super();
        }
    }

    class UrlDatabaseHelper extends SQLiteOpenHelper
    {

        private boolean tablePresent(String s, SQLiteDatabase sqlitedatabase)
        {
            Cursor cursor = null;
            String as[];
            String as1[];
            as = (new String[] {
                "name"
            });
            as1 = (new String[] {
                s
            });
            if(sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
            cursor = sqlitedatabase.query("SQLITE_MASTER", as, "name=?", as1, null, null, null);
_L3:
            boolean flag = cursor.moveToFirst();
            if(cursor != null)
                cursor.close();
            return flag;
_L2:
            Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "SQLITE_MASTER", as, "name=?", as1, null, null, null);
            cursor = cursor1;
              goto _L3
            SQLiteException sqliteexception;
            sqliteexception;
            Log.w((new StringBuilder()).append("Error querying for table ").append(s).toString());
            if(cursor != null)
                cursor.close();
            return false;
            Exception exception;
            exception;
            if(cursor != null)
                cursor.close();
            throw exception;
        }

        private void validateColumnsPresent(SQLiteDatabase sqlitedatabase)
        {
            Cursor cursor;
            HashSet hashset;
            String as[];
            int i;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                cursor = sqlitedatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
            else
                cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT * FROM gtm_hits WHERE 0", null);
            hashset = new HashSet();
            as = cursor.getColumnNames();
            i = 0;
_L2:
            if(i >= as.length)
                break; /* Loop/switch isn't completed */
            hashset.add(as[i]);
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            cursor.close();
            if(!hashset.remove("hit_id") || !hashset.remove("hit_url") || !hashset.remove("hit_time") || !hashset.remove("hit_first_send_time"))
                throw new SQLiteException("Database column missing");
            break MISSING_BLOCK_LABEL_145;
            Exception exception;
            exception;
            cursor.close();
            throw exception;
            if(!hashset.isEmpty())
                throw new SQLiteException("Database has extra columns");
            else
                return;
        }

        public SQLiteDatabase getWritableDatabase()
        {
            if(mBadDatabase && 0x36ee80L + mLastDatabaseCheckTime > mClock.currentTimeMillis())
                throw new SQLiteException("Database creation failed");
            mBadDatabase = true;
            mLastDatabaseCheckTime = mClock.currentTimeMillis();
            SQLiteDatabase sqlitedatabase1 = super.getWritableDatabase();
            SQLiteDatabase sqlitedatabase = sqlitedatabase1;
_L2:
            if(sqlitedatabase == null)
                sqlitedatabase = super.getWritableDatabase();
            mBadDatabase = false;
            return sqlitedatabase;
            SQLiteException sqliteexception;
            sqliteexception;
            mContext.getDatabasePath(mDatabaseName).delete();
            sqlitedatabase = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        boolean isBadDatabase()
        {
            return mBadDatabase;
        }

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            FutureApis.setOwnerOnlyReadWrite(sqlitedatabase.getPath());
        }

        public void onOpen(SQLiteDatabase sqlitedatabase)
        {
            Cursor cursor;
            if(android.os.Build.VERSION.SDK_INT >= 15)
                break MISSING_BLOCK_LABEL_36;
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                cursor = sqlitedatabase.rawQuery("PRAGMA journal_mode=memory", null);
            else
                cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "PRAGMA journal_mode=memory", null);
            cursor.moveToFirst();
            cursor.close();
            if(!tablePresent("gtm_hits", sqlitedatabase))
            {
                String s = PersistentHitStore.CREATE_HITS_TABLE;
                Exception exception;
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                {
                    sqlitedatabase.execSQL(s);
                    return;
                } else
                {
                    SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s);
                    return;
                }
            } else
            {
                validateColumnsPresent(sqlitedatabase);
                return;
            }
            exception;
            cursor.close();
            throw exception;
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
        }

        void setBadDatabase(boolean flag)
        {
            mBadDatabase = flag;
        }

        private boolean mBadDatabase;
        private long mLastDatabaseCheckTime;
        final PersistentHitStore this$0;

        UrlDatabaseHelper(Context context, String s)
        {
            this$0 = PersistentHitStore.this;
            super(context, s, null, 1);
            mLastDatabaseCheckTime = 0L;
        }
    }


    PersistentHitStore(HitStoreStateListener hitstorestatelistener, Context context)
    {
        this(hitstorestatelistener, context, "gtm_urls.db");
    }

    PersistentHitStore(HitStoreStateListener hitstorestatelistener, Context context, String s)
    {
        mContext = context.getApplicationContext();
        mDatabaseName = s;
        mListener = hitstorestatelistener;
        mDbHelper = new UrlDatabaseHelper(mContext, mDatabaseName);
        mDispatcher = new SimpleNetworkDispatcher(new DefaultHttpClient(), mContext, new StoreDispatchListener());
        mLastDeleteStaleHitsTime = 0L;
    }

    private void deleteHit(long l)
    {
        String as[] = new String[1];
        as[0] = String.valueOf(l);
        deleteHits(as);
    }

    private SQLiteDatabase getWritableDatabase(String s)
    {
        SQLiteDatabase sqlitedatabase;
        try
        {
            sqlitedatabase = mDbHelper.getWritableDatabase();
        }
        catch(SQLiteException sqliteexception)
        {
            Log.w(s);
            return null;
        }
        return sqlitedatabase;
    }

    private void removeOldHitIfFull()
    {
        int i = 1 + (-2000 + getNumStoredHits());
        if(i > 0)
        {
            List list = peekHitIds(i);
            Log.v((new StringBuilder()).append("Store full, deleting ").append(list.size()).append(" hits to make room.").toString());
            deleteHits((String[])list.toArray(new String[0]));
        }
    }

    private void setHitFirstDispatchTime(long l, long l1)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        String as[];
        sqlitedatabase = getWritableDatabase("Error opening database for getNumStoredHits.");
        if(sqlitedatabase == null)
            return;
        contentvalues = new ContentValues();
        contentvalues.put("hit_first_send_time", Long.valueOf(l1));
        try
        {
            as = new String[1];
            as[0] = String.valueOf(l);
            if(!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.update("gtm_hits", contentvalues, "hit_id=?", as);
                return;
            }
        }
        catch(SQLiteException sqliteexception)
        {
            Log.w((new StringBuilder()).append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ").append(l).toString());
            deleteHit(l);
            return;
        }
        SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase, "gtm_hits", contentvalues, "hit_id=?", as);
        return;
    }

    private void writeHitToDatabase(long l, String s)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        sqlitedatabase = getWritableDatabase("Error opening database for putHit");
        if(sqlitedatabase == null)
            return;
        contentvalues = new ContentValues();
        contentvalues.put("hit_time", Long.valueOf(l));
        contentvalues.put("hit_url", s);
        contentvalues.put("hit_first_send_time", Integer.valueOf(0));
        if(sqlitedatabase instanceof SQLiteDatabase)
            break MISSING_BLOCK_LABEL_91;
        sqlitedatabase.insert("gtm_hits", null, contentvalues);
_L1:
        SQLiteException sqliteexception;
        mListener.reportStoreIsEmpty(false);
        return;
        try
        {
            SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "gtm_hits", null, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch(SQLiteException sqliteexception)
        {
            Log.w("Error storing hit");
            return;
        }
          goto _L1
    }

    public void close()
    {
        try
        {
            mDbHelper.getWritableDatabase().close();
            mDispatcher.close();
            return;
        }
        catch(SQLiteException sqliteexception)
        {
            Log.w("Error opening database for close");
        }
    }

    void deleteHits(String as[])
    {
        boolean flag = true;
        if(as != null && as.length != 0) goto _L2; else goto _L1
_L1:
        SQLiteDatabase sqlitedatabase;
        return;
_L2:
        if((sqlitedatabase = getWritableDatabase("Error opening database for deleteHits.")) == null) goto _L1; else goto _L3
_L3:
        String s;
        Object aobj[] = new Object[flag];
        aobj[0] = TextUtils.join(",", Collections.nCopies(as.length, "?"));
        s = String.format("HIT_ID in (%s)", aobj);
        if(sqlitedatabase instanceof SQLiteDatabase)
            break MISSING_BLOCK_LABEL_106;
        sqlitedatabase.delete("gtm_hits", s, as);
_L4:
        HitStoreStateListener hitstorestatelistener = mListener;
        SQLiteException sqliteexception;
        if(getNumStoredHits() != 0)
            flag = false;
        hitstorestatelistener.reportStoreIsEmpty(flag);
        return;
        try
        {
            SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "gtm_hits", s, as);
        }
        // Misplaced declaration of an exception variable
        catch(SQLiteException sqliteexception)
        {
            Log.w("Error deleting hits");
            return;
        }
          goto _L4
    }

    int deleteStaleHits()
    {
        boolean flag = true;
        long l = mClock.currentTimeMillis();
        if(l > 0x5265c00L + mLastDeleteStaleHitsTime)
        {
            mLastDeleteStaleHitsTime = l;
            SQLiteDatabase sqlitedatabase = getWritableDatabase("Error opening database for deleteStaleHits.");
            if(sqlitedatabase != null)
            {
                long l1 = mClock.currentTimeMillis() - 0x9a7ec800L;
                String as[] = new String[flag];
                as[0] = Long.toString(l1);
                int i;
                HitStoreStateListener hitstorestatelistener;
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    i = sqlitedatabase.delete("gtm_hits", "HIT_TIME < ?", as);
                else
                    i = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "gtm_hits", "HIT_TIME < ?", as);
                hitstorestatelistener = mListener;
                if(getNumStoredHits() != 0)
                    flag = false;
                hitstorestatelistener.reportStoreIsEmpty(flag);
                return i;
            }
        }
        return 0;
    }

    public void dispatch()
    {
        Log.v("GTM Dispatch running...");
        if(mDispatcher.okToDispatch())
        {
            List list = peekHits(40);
            if(list.isEmpty())
            {
                Log.v("...nothing to dispatch");
                mListener.reportStoreIsEmpty(true);
                return;
            }
            mDispatcher.dispatchHits(list);
            if(getNumStoredUntriedHits() > 0)
            {
                ServiceManagerImpl.getInstance().dispatch();
                return;
            }
        }
    }

    public UrlDatabaseHelper getDbHelper()
    {
        return mDbHelper;
    }

    public Dispatcher getDispatcher()
    {
        return mDispatcher;
    }

    UrlDatabaseHelper getHelper()
    {
        return mDbHelper;
    }

    int getNumStoredHits()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getWritableDatabase("Error opening database for getNumStoredHits.");
        if(sqlitedatabase == null)
            return 0;
        cursor = null;
        boolean flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag) goto _L2; else goto _L1
_L1:
        cursor = sqlitedatabase.rawQuery("SELECT COUNT(*) from gtm_hits", null);
_L3:
        boolean flag1 = cursor.moveToFirst();
        int i;
        i = 0;
        if(!flag1)
            break MISSING_BLOCK_LABEL_67;
        long l = cursor.getLong(0);
        i = (int)l;
        if(cursor != null)
            cursor.close();
_L4:
        return i;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT COUNT(*) from gtm_hits", null);
        cursor = cursor1;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Log.w("Error getting numStoredHits");
        i = 0;
        if(cursor != null)
        {
            cursor.close();
            i = 0;
        }
          goto _L4
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
          goto _L3
    }

    int getNumStoredUntriedHits()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getWritableDatabase("Error opening database for getNumStoredHits.");
        if(sqlitedatabase == null)
            return 0;
        cursor = null;
        String as[];
        boolean flag;
        as = (new String[] {
            "hit_id", "hit_first_send_time"
        });
        flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag) goto _L2; else goto _L1
_L1:
        cursor = sqlitedatabase.query("gtm_hits", as, "hit_first_send_time=0", null, null, null, null);
_L3:
        int j = cursor.getCount();
        int i;
        i = j;
        if(cursor != null)
            cursor.close();
_L4:
        return i;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, "hit_first_send_time=0", null, null, null, null);
        cursor = cursor1;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Log.w("Error getting num untried hits");
        i = 0;
        if(cursor != null)
        {
            cursor.close();
            i = 0;
        }
          goto _L4
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
          goto _L3
    }

    List peekHitIds(int i)
    {
        ArrayList arraylist = new ArrayList();
        if(i > 0) goto _L2; else goto _L1
_L1:
        Log.w("Invalid maxHits specified. Skipping");
_L4:
        return arraylist;
_L2:
        SQLiteDatabase sqlitedatabase = getWritableDatabase("Error opening database for peekHitIds.");
        if(sqlitedatabase == null) goto _L4; else goto _L3
_L3:
        Cursor cursor = null;
        String as[];
        String s;
        String s1;
        boolean flag;
        as = (new String[] {
            "hit_id"
        });
        s = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s1 = Integer.toString(i);
        flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag) goto _L6; else goto _L5
_L5:
        cursor = sqlitedatabase.query("gtm_hits", as, null, null, null, null, s, s1);
_L8:
        boolean flag1;
        if(cursor.moveToFirst())
            do
            {
                arraylist.add(String.valueOf(cursor.getLong(0)));
                flag1 = cursor.moveToNext();
            } while(flag1);
        if(cursor == null) goto _L4; else goto _L7
_L7:
        cursor.close();
        return arraylist;
_L6:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, null, null, null, null, s, s1);
        cursor = cursor1;
          goto _L8
        SQLiteException sqliteexception;
        sqliteexception;
        Log.w((new StringBuilder()).append("Error in peekHits fetching hitIds: ").append(sqliteexception.getMessage()).toString());
        if(cursor == null) goto _L4; else goto _L9
_L9:
        cursor.close();
        return arraylist;
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    public List peekHits(int i)
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        arraylist = new ArrayList();
        sqlitedatabase = getWritableDatabase("Error opening database for peekHits");
        if(sqlitedatabase == null)
            return arraylist;
        cursor = null;
        String as[];
        String s;
        String s1;
        boolean flag;
        as = (new String[] {
            "hit_id", "hit_time", "hit_first_send_time"
        });
        s = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s1 = Integer.toString(i);
        flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag) goto _L2; else goto _L1
_L1:
        cursor = sqlitedatabase.query("gtm_hits", as, null, null, null, null, s, s1);
_L9:
        ArrayList arraylist1 = new ArrayList();
        boolean flag1;
        if(cursor.moveToFirst())
            do
            {
                arraylist1.add(new Hit(cursor.getLong(0), cursor.getLong(1), cursor.getLong(2)));
                flag1 = cursor.moveToNext();
            } while(flag1);
        if(cursor != null)
            cursor.close();
        String as1[];
        String s2;
        String s3;
        as1 = (new String[] {
            "hit_id", "hit_url"
        });
        s2 = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s3 = Integer.toString(i);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        cursor = sqlitedatabase.query("gtm_hits", as1, null, null, null, null, s2, s3);
_L10:
        boolean flag4 = cursor.moveToFirst();
        int j = 0;
        if(!flag4) goto _L6; else goto _L5
_L5:
        if(((SQLiteCursor)cursor).getWindow().getNumRows() <= 0) goto _L8; else goto _L7
_L7:
        ((Hit)arraylist1.get(j)).setHitUrl(cursor.getString(1));
_L11:
        j++;
        boolean flag5 = cursor.moveToNext();
        if(flag5) goto _L5; else goto _L6
_L6:
        if(cursor != null)
            cursor.close();
        return arraylist1;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, null, null, null, null, s, s1);
        cursor = cursor1;
          goto _L9
        SQLiteException sqliteexception;
        sqliteexception;
_L17:
        Log.w((new StringBuilder()).append("Error in peekHits fetching hitIds: ").append(sqliteexception.getMessage()).toString());
        if(cursor != null)
            cursor.close();
        return arraylist;
        Exception exception;
        exception;
_L16:
        if(cursor != null)
            cursor.close();
        throw exception;
_L4:
        cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as1, null, null, null, null, s2, s3);
          goto _L10
_L8:
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(((Hit)arraylist1.get(j)).getHitId());
        Log.w(String.format("HitString for hitId %d too large.  Hit will be deleted.", aobj));
          goto _L11
        SQLiteException sqliteexception1;
        sqliteexception1;
        ArrayList arraylist2;
        Log.w((new StringBuilder()).append("Error in peekHits fetching hit url: ").append(sqliteexception1.getMessage()).toString());
        arraylist2 = new ArrayList();
        boolean flag2 = false;
        Iterator iterator = arraylist1.iterator();
_L15:
        if(!iterator.hasNext()) goto _L13; else goto _L12
_L12:
        Hit hit;
        boolean flag3;
        hit = (Hit)iterator.next();
        flag3 = TextUtils.isEmpty(hit.getHitUrl());
        if(!flag3)
            break MISSING_BLOCK_LABEL_599;
        if(!flag2) goto _L14; else goto _L13
_L13:
        if(cursor != null)
            cursor.close();
        return arraylist2;
_L14:
        flag2 = true;
        arraylist2.add(hit);
          goto _L15
        Exception exception1;
        exception1;
        if(cursor != null)
            cursor.close();
        throw exception1;
        exception;
          goto _L16
        sqliteexception;
        arraylist = arraylist1;
          goto _L17
    }

    public void putHit(long l, String s)
    {
        deleteStaleHits();
        removeOldHitIfFull();
        writeHitToDatabase(l, s);
    }

    public void setClock(Clock clock)
    {
        mClock = clock;
    }

    void setDispatcher(Dispatcher dispatcher)
    {
        mDispatcher = dispatcher;
    }

    void setLastDeleteStaleHitsTime(long l)
    {
        mLastDeleteStaleHitsTime = l;
    }

    private static final String CREATE_HITS_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[] {
        "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"
    });
    private static final String DATABASE_FILENAME = "gtm_urls.db";
    static final String HITS_TABLE = "gtm_hits";
    static final long HIT_DISPATCH_RETRY_WINDOW = 0xdbba00L;
    static final String HIT_FIRST_DISPATCH_TIME = "hit_first_send_time";
    static final String HIT_ID = "hit_id";
    private static final String HIT_ID_WHERE_CLAUSE = "hit_id=?";
    static final String HIT_TIME = "hit_time";
    static final String HIT_URL = "hit_url";
    private Clock mClock = new Clock() {

        public long currentTimeMillis()
        {
            return System.currentTimeMillis();
        }

        final PersistentHitStore this$0;

            
            {
                this$0 = PersistentHitStore.this;
                super();
            }
    }
;
    private final Context mContext;
    private final String mDatabaseName;
    private final UrlDatabaseHelper mDbHelper;
    private volatile Dispatcher mDispatcher;
    private long mLastDeleteStaleHitsTime;
    private final HitStoreStateListener mListener;







}
