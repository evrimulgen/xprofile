// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.*;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.fn;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.*;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.google.android.gms.tagmanager:
//            at, db, bh, au, 
//            ab, cy, ap, cb, 
//            av, ci, cd, ch, 
//            cc, cf, da

class cb
    implements at
{
    class a extends cb
    {

        public void aD()
        {
        }

        public ch aE()
        {
            return ci.a(mContext, new av());
        }

        private final Context mContext;

        public a(Context context, cd cd, ca.a a1)
        {
            cb(cd, a1);
            mContext = context;
        }
    }

    class b extends cb
        implements com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
    {

        public void aD()
        {
            synchronized(mg)
            {
                if(nZ.isConnected() || nZ.isConnecting())
                    nZ.disconnect();
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public ch aE()
        {
            obj;
            JVM INSTR monitorenter ;
            ch ch;
            synchronized(mg)
            {
                ch = nZ.aH();
            }
            return ch;
            IllegalStateException illegalstateexception;
            illegalstateexception;
            obj;
            JVM INSTR monitorexit ;
            return null;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onConnected(Bundle bundle)
        {
            start();
        }

        public void onConnectionFailed(ConnectionResult connectionresult)
        {
            nY.a(new cf(0));
        }

        public void onDisconnected()
        {
            da.s("Disconnected from remote ad request service.");
        }

        private final Object mg = new Object();
        private final ca.a nY;
        private final cc nZ;

        public b(Context context, cd cd1, ca.a a1)
        {
            cb(cd1, a1);
            nY = a1;
            nZ = new cc(context, this, this, cd1.kN.pW);
            nZ.connect();
        }
    }


    cb(au au1, Context context)
    {
        cb(au1, context, "gtm_urls.db", 2000);
    }

    cb(au au1, Context context, String s1, int i)
    {
        mContext = context.getApplicationContext();
        ud = s1;
        VN = au1;
        Ty = fn.eI();
        VL = new b(this, mContext, ud);
        VM = new db(new DefaultHttpClient(), mContext, new a(this));
        uf = 0L;
        ug = i;
    }

    private SQLiteDatabase G(String s1)
    {
        SQLiteDatabase sqlitedatabase;
        try
        {
            sqlitedatabase = VL.getWritableDatabase();
        }
        catch(SQLiteException sqliteexception)
        {
            bh.w(s1);
            return null;
        }
        return sqlitedatabase;
    }

    static fl a(cb cb1)
    {
        return cb1.Ty;
    }

    static void a(cb cb1, long l)
    {
        cb1.u(l);
    }

    static void a(cb cb1, long l, long l1)
    {
        cb1.c(l, l1);
    }

    static String b(cb cb1)
    {
        return cb1.ud;
    }

    static Context c(cb cb1)
    {
        return cb1.mContext;
    }

    private void c(long l, long l1)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        String as[];
        sqlitedatabase = G("Error opening database for getNumStoredHits.");
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
            bh.w((new StringBuilder()).append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ").append(l).toString());
            u(l);
            return;
        }
        SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase, "gtm_hits", contentvalues, "hit_id=?", as);
        return;
    }

    private void co()
    {
        int i = 1 + (cq() - ug);
        if(i > 0)
        {
            List list = s(i);
            bh.v((new StringBuilder()).append("Store full, deleting ").append(list.size()).append(" hits to make room.").toString());
            a((String[])list.toArray(new String[0]));
        }
    }

    private void f(long l, String s1)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        sqlitedatabase = G("Error opening database for putHit");
        if(sqlitedatabase == null)
            return;
        contentvalues = new ContentValues();
        contentvalues.put("hit_time", Long.valueOf(l));
        contentvalues.put("hit_url", s1);
        contentvalues.put("hit_first_send_time", Integer.valueOf(0));
        if(sqlitedatabase instanceof SQLiteDatabase)
            break MISSING_BLOCK_LABEL_91;
        sqlitedatabase.insert("gtm_hits", null, contentvalues);
_L1:
        SQLiteException sqliteexception;
        VN.p(false);
        return;
        try
        {
            SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "gtm_hits", null, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch(SQLiteException sqliteexception)
        {
            bh.w("Error storing hit");
            return;
        }
          goto _L1
    }

    static String jt()
    {
        return ua;
    }

    private void u(long l)
    {
        String as[] = new String[1];
        as[0] = String.valueOf(l);
        a(as);
    }

    void a(String as[])
    {
        if(as != null && as.length != 0) goto _L2; else goto _L1
_L1:
        SQLiteDatabase sqlitedatabase;
        return;
_L2:
        if((sqlitedatabase = G("Error opening database for deleteHits.")) == null) goto _L1; else goto _L3
_L3:
        String s1;
        Object aobj[] = new Object[1];
        aobj[0] = TextUtils.join(",", Collections.nCopies(as.length, "?"));
        s1 = String.format("HIT_ID in (%s)", aobj);
        if(sqlitedatabase instanceof SQLiteDatabase)
            break MISSING_BLOCK_LABEL_102;
        sqlitedatabase.delete("gtm_hits", s1, as);
_L4:
        au au1 = VN;
        boolean flag;
        if(cq() == 0)
            flag = true;
        else
            flag = false;
        try
        {
            au1.p(flag);
            return;
        }
        catch(SQLiteException sqliteexception)
        {
            bh.w("Error deleting hits");
        }
        return;
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "gtm_hits", s1, as);
          goto _L4
    }

    public void bp()
    {
        bh.v("GTM Dispatch running...");
        if(VM.bA())
        {
            List list = t(40);
            if(list.isEmpty())
            {
                bh.v("...nothing to dispatch");
                VN.p(true);
                return;
            }
            VM.e(list);
            if(js() > 0)
            {
                cy.kh().bp();
                return;
            }
        }
    }

    int cp()
    {
        boolean flag = true;
        long l = Ty.currentTimeMillis();
        if(l > 0x5265c00L + uf)
        {
            uf = l;
            SQLiteDatabase sqlitedatabase = G("Error opening database for deleteStaleHits.");
            if(sqlitedatabase != null)
            {
                long l1 = Ty.currentTimeMillis() - 0x9a7ec800L;
                String as[] = new String[flag];
                as[0] = Long.toString(l1);
                int i;
                au au1;
                if(!(sqlitedatabase instanceof SQLiteDatabase))
                    i = sqlitedatabase.delete("gtm_hits", "HIT_TIME < ?", as);
                else
                    i = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "gtm_hits", "HIT_TIME < ?", as);
                au1 = VN;
                if(cq() != 0)
                    flag = false;
                au1.p(flag);
                return i;
            }
        }
        return 0;
    }

    int cq()
    {
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        sqlitedatabase = G("Error opening database for getNumStoredHits.");
        if(sqlitedatabase == null)
            return 0;
        boolean flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if(flag) goto _L2; else goto _L1
_L1:
        Cursor cursor2 = sqlitedatabase.rawQuery("SELECT COUNT(*) from gtm_hits", null);
        cursor = cursor2;
_L3:
        long l;
        if(!cursor.moveToFirst())
            break MISSING_BLOCK_LABEL_163;
        l = cursor.getLong(0);
        int i = (int)l;
_L8:
        if(cursor != null)
            cursor.close();
_L4:
        return i;
_L2:
        Cursor cursor3 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT COUNT(*) from gtm_hits", null);
        cursor = cursor3;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Cursor cursor1 = null;
_L7:
        bh.w("Error getting numStoredHits");
        Exception exception;
        Exception exception1;
        SQLiteException sqliteexception1;
        if(cursor1 != null)
        {
            cursor1.close();
            i = 0;
        } else
        {
            i = 0;
        }
          goto _L4
        exception;
_L6:
        if(cursor != null)
            cursor.close();
        throw exception;
        exception1;
        cursor = cursor1;
        exception = exception1;
        if(true) goto _L6; else goto _L5
_L5:
        sqliteexception1;
        cursor1 = cursor;
          goto _L7
        i = 0;
          goto _L8
    }

    public void e(long l, String s1)
    {
        cp();
        co();
        f(l, s1);
    }

    int js()
    {
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        sqlitedatabase = G("Error opening database for getNumStoredHits.");
        if(sqlitedatabase == null)
            return 0;
        String as[] = {
            "hit_id", "hit_first_send_time"
        };
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor4 = sqlitedatabase.query("gtm_hits", as, "hit_first_send_time=0", null, null, null, null);
        Cursor cursor3 = cursor4;
_L3:
        int j = cursor3.getCount();
        int i;
        i = j;
        if(cursor3 != null)
            cursor3.close();
_L4:
        return i;
_L2:
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, "hit_first_send_time=0", null, null, null, null);
        cursor3 = cursor2;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Cursor cursor1 = null;
_L7:
        bh.w("Error getting num untried hits");
        Exception exception;
        Exception exception1;
        SQLiteException sqliteexception1;
        if(cursor1 != null)
        {
            cursor1.close();
            i = 0;
        } else
        {
            i = 0;
        }
          goto _L4
        exception;
_L6:
        if(cursor != null)
            cursor.close();
        throw exception;
        exception;
        cursor = cursor3;
        continue; /* Loop/switch isn't completed */
        exception1;
        cursor = cursor1;
        exception = exception1;
        if(true) goto _L6; else goto _L5
_L5:
        sqliteexception1;
        cursor1 = cursor3;
          goto _L7
    }

    List s(int i)
    {
        Cursor cursor;
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        arraylist = new ArrayList();
        if(i <= 0)
        {
            bh.w("Invalid maxHits specified. Skipping");
            return arraylist;
        }
        sqlitedatabase = G("Error opening database for peekHitIds.");
        if(sqlitedatabase == null)
            return arraylist;
        String as[];
        String s1;
        String s2;
        as = (new String[] {
            "hit_id"
        });
        s1 = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s2 = Integer.toString(i);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase.query("gtm_hits", as, null, null, null, null, s1, s2);
        Cursor cursor1 = cursor3;
_L3:
        boolean flag;
        if(cursor1.moveToFirst())
            do
            {
                arraylist.add(String.valueOf(cursor1.getLong(0)));
                flag = cursor1.moveToNext();
            } while(flag);
        if(cursor1 != null)
            cursor1.close();
_L4:
        return arraylist;
_L2:
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, null, null, null, null, s1, s2);
        cursor1 = cursor2;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        cursor1 = null;
_L7:
        bh.w((new StringBuilder()).append("Error in peekHits fetching hitIds: ").append(sqliteexception.getMessage()).toString());
        if(cursor1 != null)
            cursor1.close();
          goto _L4
        Exception exception;
        exception;
_L6:
        if(cursor != null)
            cursor.close();
        throw exception;
        exception;
        cursor = cursor1;
        if(true) goto _L6; else goto _L5
_L5:
        sqliteexception;
          goto _L7
    }

    public List t(int i)
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        arraylist = new ArrayList();
        sqlitedatabase = G("Error opening database for peekHits");
        if(sqlitedatabase != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist1 = arraylist;
_L13:
        return arraylist1;
_L2:
        Cursor cursor = null;
        String as[];
        String s1;
        String s2;
        as = (new String[] {
            "hit_id", "hit_time", "hit_first_send_time"
        });
        s1 = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s2 = Integer.toString(i);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        Cursor cursor7 = sqlitedatabase.query("gtm_hits", as, null, null, null, null, s1, s2);
        Cursor cursor3 = cursor7;
_L11:
        ArrayList arraylist2 = new ArrayList();
        boolean flag;
        if(cursor3.moveToFirst())
            do
            {
                arraylist2.add(new ap(cursor3.getLong(0), cursor3.getLong(1), cursor3.getLong(2)));
                flag = cursor3.moveToNext();
            } while(flag);
        if(cursor3 != null)
            cursor3.close();
        String as1[];
        String s3;
        String s4;
        as1 = (new String[] {
            "hit_id", "hit_url"
        });
        s3 = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s4 = Integer.toString(i);
        if(sqlitedatabase instanceof SQLiteDatabase) goto _L6; else goto _L5
_L5:
        Cursor cursor6 = sqlitedatabase.query("gtm_hits", as1, null, null, null, null, s3, s4);
        Cursor cursor5 = cursor6;
_L14:
        if(!cursor5.moveToFirst()) goto _L8; else goto _L7
_L7:
        int j = 0;
_L26:
        if(((SQLiteCursor)cursor5).getWindow().getNumRows() <= 0) goto _L10; else goto _L9
_L9:
        ((ap)arraylist2.get(j)).F(cursor5.getString(1));
_L15:
        int k = j + 1;
        boolean flag3 = cursor5.moveToNext();
        if(flag3)
            break MISSING_BLOCK_LABEL_717;
_L8:
        if(cursor5 != null)
            cursor5.close();
        return arraylist2;
_L4:
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, null, null, null, null, s1, s2);
        cursor3 = cursor2;
          goto _L11
        SQLiteException sqliteexception;
        sqliteexception;
        SQLiteException sqliteexception1;
        Cursor cursor1;
        sqliteexception1 = sqliteexception;
        cursor1 = null;
        arraylist1 = arraylist;
_L25:
        bh.w((new StringBuilder()).append("Error in peekHits fetching hitIds: ").append(sqliteexception1.getMessage()).toString());
        if(cursor1 == null) goto _L13; else goto _L12
_L12:
        cursor1.close();
        return arraylist1;
        Exception exception;
        exception;
_L24:
        if(cursor != null)
            cursor.close();
        throw exception;
_L6:
        Cursor cursor4 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as1, null, null, null, null, s3, s4);
        cursor5 = cursor4;
          goto _L14
_L10:
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(((ap)arraylist2.get(j)).ci());
        bh.w(String.format("HitString for hitId %d too large.  Hit will be deleted.", aobj));
          goto _L15
        SQLiteException sqliteexception3;
        sqliteexception3;
        cursor3 = cursor5;
_L23:
        ArrayList arraylist3;
        bh.w((new StringBuilder()).append("Error in peekHits fetching hit url: ").append(sqliteexception3.getMessage()).toString());
        arraylist3 = new ArrayList();
        boolean flag1 = false;
        Iterator iterator = arraylist2.iterator();
_L19:
        if(!iterator.hasNext()) goto _L17; else goto _L16
_L16:
        ap ap1;
        boolean flag2;
        ap1 = (ap)iterator.next();
        flag2 = TextUtils.isEmpty(ap1.jf());
        if(!flag2)
            break MISSING_BLOCK_LABEL_622;
        if(!flag1) goto _L18; else goto _L17
_L17:
        if(cursor3 != null)
            cursor3.close();
        return arraylist3;
_L18:
        flag1 = true;
        arraylist3.add(ap1);
          goto _L19
        Exception exception1;
        exception1;
_L21:
        if(cursor3 != null)
            cursor3.close();
        throw exception1;
        exception1;
        cursor3 = cursor5;
        if(true) goto _L21; else goto _L20
_L20:
        sqliteexception3;
        if(true) goto _L23; else goto _L22
_L22:
        exception;
        cursor = cursor3;
          goto _L24
        exception;
        cursor = cursor1;
          goto _L24
        SQLiteException sqliteexception4;
        sqliteexception4;
        sqliteexception1 = sqliteexception4;
        cursor1 = cursor3;
        arraylist1 = arraylist;
          goto _L25
        SQLiteException sqliteexception2;
        sqliteexception2;
        sqliteexception1 = sqliteexception2;
        cursor1 = cursor3;
        arraylist1 = arraylist2;
          goto _L25
        j = k;
          goto _L26
    }

    private static final String ua = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[] {
        "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"
    });
    private fl Ty;
    private final b VL;
    private volatile ab VM;
    private final au VN;
    private final Context mContext;
    private final String ud;
    private long uf;
    private final int ug;

}
