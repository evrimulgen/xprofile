// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.*;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.Command;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.util.*;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.google.analytics.tracking.android:
//            AnalyticsStore, SimpleNetworkDispatcher, HitBuilder, Log, 
//            AnalyticsStoreStateListener, Dispatcher, Hit, Clock, 
//            GAServiceManager, NoopDispatcher, FutureApis

class PersistentAnalyticsStore
    implements AnalyticsStore
{
    class AnalyticsDatabaseHelper extends SQLiteOpenHelper
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
                cursor = sqlitedatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
            else
                cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT * FROM hits2 WHERE 0", null);
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
            if(!hashset.remove("hit_id") || !hashset.remove("hit_url") || !hashset.remove("hit_string") || !hashset.remove("hit_time"))
                throw new SQLiteException("Database column missing");
            break MISSING_BLOCK_LABEL_145;
            Exception exception;
            exception;
            cursor.close();
            throw exception;
label0:
            {
                boolean flag;
                if(!hashset.remove("hit_app_id"))
                    flag = true;
                else
                    flag = false;
                if(!hashset.isEmpty())
                    throw new SQLiteException("Database has extra columns");
                if(flag)
                {
                    if(sqlitedatabase instanceof SQLiteDatabase)
                        break label0;
                    sqlitedatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
                }
                return;
            }
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "ALTER TABLE hits2 ADD COLUMN hit_app_id");
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
            if(!tablePresent("hits2", sqlitedatabase))
            {
                String s = PersistentAnalyticsStore.CREATE_HITS_TABLE;
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
        final PersistentAnalyticsStore this$0;

        AnalyticsDatabaseHelper(Context context, String s)
        {
            this$0 = PersistentAnalyticsStore.this;
            super(context, s, null, 1);
            mLastDatabaseCheckTime = 0L;
        }
    }


    PersistentAnalyticsStore(AnalyticsStoreStateListener analyticsstorestatelistener, Context context)
    {
        this(analyticsstorestatelistener, context, "google_analytics_v2.db");
    }

    PersistentAnalyticsStore(AnalyticsStoreStateListener analyticsstorestatelistener, Context context, String s)
    {
        mContext = context.getApplicationContext();
        mDatabaseName = s;
        mListener = analyticsstorestatelistener;
        mDbHelper = new AnalyticsDatabaseHelper(mContext, mDatabaseName);
        mDispatcher = new SimpleNetworkDispatcher(new DefaultHttpClient(), mContext);
        mLastDeleteStaleHitsTime = 0L;
    }

    private void fillVersionParameter(Map map, Collection collection)
    {
label0:
        {
            String s = "&_v".substring(1);
            if(collection == null)
                break label0;
            Iterator iterator = collection.iterator();
            Command command;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                command = (Command)iterator.next();
            } while(!"appendVersion".equals(command.getId()));
            map.put(s, command.getValue());
        }
    }

    static String generateHitString(Map map)
    {
        ArrayList arraylist = new ArrayList(map.size());
        java.util.Map.Entry entry;
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); arraylist.add((new StringBuilder()).append(HitBuilder.encode((String)entry.getKey())).append("=").append(HitBuilder.encode((String)entry.getValue())).toString()))
            entry = (java.util.Map.Entry)iterator.next();

        return TextUtils.join("&", arraylist);
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

    private void writeHitToDatabase(Map map, long l, String s)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        long l1;
        sqlitedatabase = getWritableDatabase("Error opening database for putHit");
        if(sqlitedatabase == null)
            return;
        contentvalues = new ContentValues();
        contentvalues.put("hit_string", generateHitString(map));
        contentvalues.put("hit_time", Long.valueOf(l));
        l1 = 0L;
        if(!map.containsKey("AppUID"))
            break MISSING_BLOCK_LABEL_82;
        long l2 = Long.parseLong((String)map.get("AppUID"));
        l1 = l2;
_L3:
        contentvalues.put("hit_app_id", Long.valueOf(l1));
        if(s == null)
            s = "http://www.google-analytics.com/collect";
        if(s.length() == 0)
        {
            Log.w("Empty path: not sending hit");
            return;
        }
        contentvalues.put("hit_url", s);
        if(sqlitedatabase instanceof SQLiteDatabase)
            break MISSING_BLOCK_LABEL_167;
        sqlitedatabase.insert("hits2", null, contentvalues);
_L1:
        SQLiteException sqliteexception;
        mListener.reportStoreIsEmpty(false);
        return;
        try
        {
            SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "hits2", null, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch(SQLiteException sqliteexception)
        {
            Log.w("Error storing hit");
            return;
        }
          goto _L1
        NumberFormatException numberformatexception;
        numberformatexception;
        if(true) goto _L3; else goto _L2
_L2:
    }

    public void clearHits(long l)
    {
        boolean flag = true;
        SQLiteDatabase sqlitedatabase = getWritableDatabase("Error opening database for clearHits");
        if(sqlitedatabase != null)
        {
            AnalyticsStoreStateListener analyticsstorestatelistener;
            if(l == 0L)
            {
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.delete("hits2", null, null);
                else
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "hits2", null, null);
            } else
            {
                String as[] = new String[flag];
                as[0] = Long.valueOf(l).toString();
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    sqlitedatabase.delete("hits2", "hit_app_id = ?", as);
                else
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "hits2", "hit_app_id = ?", as);
            }
            analyticsstorestatelistener = mListener;
            if(getNumStoredHits() != 0)
                flag = false;
            analyticsstorestatelistener.reportStoreIsEmpty(flag);
        }
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

    void deleteHits(Collection collection)
    {
        if(collection == null || collection.isEmpty())
        {
            Log.w("Empty/Null collection passed to deleteHits.");
            return;
        }
        String as[] = new String[collection.size()];
        int i = 0;
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            Hit hit = (Hit)iterator.next();
            int j = i + 1;
            as[i] = String.valueOf(hit.getHitId());
            i = j;
        }

        deleteHits(as);
    }

    void deleteHits(String as[])
    {
        boolean flag = true;
        if(as != null && as.length != 0) goto _L2; else goto _L1
_L1:
        Log.w("Empty hitIds passed to deleteHits.");
_L4:
        return;
_L2:
        SQLiteDatabase sqlitedatabase = getWritableDatabase("Error opening database for deleteHits.");
        if(sqlitedatabase == null) goto _L4; else goto _L3
_L3:
        String s;
        Object aobj[] = new Object[flag];
        aobj[0] = TextUtils.join(",", Collections.nCopies(as.length, "?"));
        s = String.format("HIT_ID in (%s)", aobj);
        if(sqlitedatabase instanceof SQLiteDatabase)
            break MISSING_BLOCK_LABEL_129;
        sqlitedatabase.delete("hits2", s, as);
_L5:
        AnalyticsStoreStateListener analyticsstorestatelistener = mListener;
        SQLiteException sqliteexception;
        if(getNumStoredHits() != 0)
            flag = false;
        analyticsstorestatelistener.reportStoreIsEmpty(flag);
        return;
        try
        {
            SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "hits2", s, as);
        }
        // Misplaced declaration of an exception variable
        catch(SQLiteException sqliteexception)
        {
            Log.w((new StringBuilder()).append("Error deleting hits ").append(as).toString());
            return;
        }
          goto _L5
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
                AnalyticsStoreStateListener analyticsstorestatelistener;
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    i = sqlitedatabase.delete("hits2", "HIT_TIME < ?", as);
                else
                    i = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "hits2", "HIT_TIME < ?", as);
                analyticsstorestatelistener = mListener;
                if(getNumStoredHits() != 0)
                    flag = false;
                analyticsstorestatelistener.reportStoreIsEmpty(flag);
                return i;
            }
        }
        return 0;
    }

    public void dispatch()
    {
        Log.v("Dispatch running...");
        if(mDispatcher.okToDispatch())
        {
            List list = peekHits(40);
            if(list.isEmpty())
            {
                Log.v("...nothing to dispatch");
                mListener.reportStoreIsEmpty(true);
                return;
            }
            int i = mDispatcher.dispatchHits(list);
            Log.v((new StringBuilder()).append("sent ").append(i).append(" of ").append(list.size()).append(" hits").toString());
            deleteHits(list.subList(0, Math.min(i, list.size())));
            if(i == list.size() && getNumStoredHits() > 0)
            {
                GAServiceManager.getInstance().dispatchLocalHits();
                return;
            }
        }
    }

    public AnalyticsDatabaseHelper getDbHelper()
    {
        return mDbHelper;
    }

    public Dispatcher getDispatcher()
    {
        return mDispatcher;
    }

    AnalyticsDatabaseHelper getHelper()
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
        cursor = sqlitedatabase.rawQuery("SELECT COUNT(*) from hits2", null);
_L3:
        boolean flag1 = cursor.moveToFirst();
        int i;
        i = 0;
        if(!flag1)
            break MISSING_BLOCK_LABEL_68;
        long l = cursor.getLong(0);
        i = (int)l;
        if(cursor != null)
            cursor.close();
_L4:
        return i;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT COUNT(*) from hits2", null);
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
        cursor = sqlitedatabase.query("hits2", as, null, null, null, null, s, s1);
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
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "hits2", as, null, null, null, null, s, s1);
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
            "hit_id", "hit_time"
        });
        s = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s1 = Integer.toString(i);
        flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag) goto _L2; else goto _L1
_L1:
        cursor = sqlitedatabase.query("hits2", as, null, null, null, null, s, s1);
_L9:
        ArrayList arraylist1 = new ArrayList();
        boolean flag1;
        if(cursor.moveToFirst())
            do
            {
                arraylist1.add(new Hit(null, cursor.getLong(0), cursor.getLong(1)));
                flag1 = cursor.moveToNext();
            } while(flag1);
        if(cursor != null)
            cursor.close();
        String as1[];
        String s2;
        String s3;
        as1 = (new String[] {
            "hit_id", "hit_string", "hit_url"
        });
        s2 = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s3 = Integer.toString(i);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        cursor = sqlitedatabase.query("hits2", as1, null, null, null, null, s2, s3);
_L10:
        boolean flag4 = cursor.moveToFirst();
        int j = 0;
        if(!flag4) goto _L6; else goto _L5
_L5:
        if(((SQLiteCursor)cursor).getWindow().getNumRows() <= 0) goto _L8; else goto _L7
_L7:
        ((Hit)arraylist1.get(j)).setHitString(cursor.getString(1));
        ((Hit)arraylist1.get(j)).setHitUrl(cursor.getString(2));
_L11:
        j++;
        boolean flag5 = cursor.moveToNext();
        if(flag5) goto _L5; else goto _L6
_L6:
        if(cursor != null)
            cursor.close();
        return arraylist1;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "hits2", as, null, null, null, null, s, s1);
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
        cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "hits2", as1, null, null, null, null, s2, s3);
          goto _L10
_L8:
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(((Hit)arraylist1.get(j)).getHitId());
        Log.w(String.format("HitString for hitId %d too large.  Hit will be deleted.", aobj));
          goto _L11
        SQLiteException sqliteexception1;
        sqliteexception1;
        ArrayList arraylist2;
        Log.w((new StringBuilder()).append("Error in peekHits fetching hitString: ").append(sqliteexception1.getMessage()).toString());
        arraylist2 = new ArrayList();
        boolean flag2 = false;
        Iterator iterator = arraylist1.iterator();
_L15:
        if(!iterator.hasNext()) goto _L13; else goto _L12
_L12:
        Hit hit;
        boolean flag3;
        hit = (Hit)iterator.next();
        flag3 = TextUtils.isEmpty(hit.getHitParams());
        if(!flag3)
            break MISSING_BLOCK_LABEL_615;
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

    public void putHit(Map map, long l, String s, Collection collection)
    {
        deleteStaleHits();
        removeOldHitIfFull();
        fillVersionParameter(map, collection);
        writeHitToDatabase(map, l, s);
    }

    public void setClock(Clock clock)
    {
        mClock = clock;
    }

    public void setDispatch(boolean flag)
    {
        Object obj;
        if(flag)
            obj = new SimpleNetworkDispatcher(new DefaultHttpClient(), mContext);
        else
            obj = new NoopDispatcher();
        mDispatcher = ((Dispatcher) (obj));
    }

    void setDispatcher(Dispatcher dispatcher)
    {
        mDispatcher = dispatcher;
    }

    void setLastDeleteStaleHitsTime(long l)
    {
        mLastDeleteStaleHitsTime = l;
    }

    private static final String CREATE_HITS_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[] {
        "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"
    });
    private static final String DATABASE_FILENAME = "google_analytics_v2.db";
    static final String HITS_TABLE = "hits2";
    static final String HIT_APP_ID = "hit_app_id";
    static final String HIT_ID = "hit_id";
    static final String HIT_STRING = "hit_string";
    static final String HIT_TIME = "hit_time";
    static final String HIT_URL = "hit_url";
    private Clock mClock = new Clock() {

        public long currentTimeMillis()
        {
            return System.currentTimeMillis();
        }

        final PersistentAnalyticsStore this$0;

            
            {
                this$0 = PersistentAnalyticsStore.this;
                super();
            }
    }
;
    private final Context mContext;
    private final String mDatabaseName;
    private final AnalyticsDatabaseHelper mDbHelper;
    private volatile Dispatcher mDispatcher;
    private long mLastDeleteStaleHitsTime;
    private final AnalyticsStoreStateListener mListener;





}
