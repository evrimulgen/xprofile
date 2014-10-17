// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.flurry.sdk;

import android.content.Context;
import android.location.Location;
import android.os.*;
import android.text.TextUtils;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.flurry.sdk:
//            by, ex, cn, ep, 
//            bx, cb, em, ej, 
//            cd, cc, cm, fh, 
//            eh, ei, eg, et, 
//            ca, ck, cj, fi, 
//            eo

public class cl
    implements cn.b, ei.a
{
    public static interface a
    {

        public abstract void d(String s1);
    }


    public cl(Context context, String s1, a a1)
    {
        k = null;
        l = false;
        s = new ArrayList();
        y = "";
        z = "";
        A = -1;
        T = false;
        ex.a(4, g, "Initializing new Flurry session");
        HandlerThread handlerthread = new HandlerThread((new StringBuilder()).append("FlurryAgentSession_").append(s1).toString());
        handlerthread.start();
        P = new Handler(handlerthread.getLooper());
        x();
        Q = new cn(this);
        R = a1;
        m = s1;
        k = context.getFileStreamPath(A());
        n = ep.a();
        w = -1L;
        L = 0;
        z = TimeZone.getDefault().getID();
        y = (new StringBuilder()).append(Locale.getDefault().getLanguage()).append("_").append(Locale.getDefault().getCountry()).toString();
        I = true;
        J = 0;
        M = 0;
        t();
    }

    private String A()
    {
        return (new StringBuilder()).append(".flurryagent.").append(Integer.toString(m.hashCode(), 16)).toString();
    }

    private int B()
    {
        return h.incrementAndGet();
    }

    private int C()
    {
        return i.incrementAndGet();
    }

    private String D()
    {
        return t;
    }

    private Location E()
    {
        return bx.a().n();
    }

    static String a(cl cl1, String s1)
    {
        cl1.t = s1;
        return s1;
    }

    private void a(long l1)
    {
        Iterator iterator = H.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            cb cb1 = (cb)iterator.next();
            if(cb1.a() && !cb1.b())
                cb1.a(l1);
        } while(true);
    }

    private void a(Context context)
    {
        byte abyte0[];
        try
        {
            abyte0 = em.a();
        }
        catch(Throwable throwable)
        {
            ex.a(6, g, "", throwable);
            return;
        }
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_35;
        ex.a(3, g, "Fetched hashed IMEI");
        p.put(ej.b, ByteBuffer.wrap(abyte0));
        c(context);
        return;
    }

    static void a(cl cl1, Context context)
    {
        cl1.d(context);
    }

    private void a(byte abyte0[])
    {
        cd cd1 = bx.a().o();
        String s1 = (new StringBuilder()).append("").append(bx.a().b()).toString();
        cd1.b(abyte0, m, s1);
    }

    static boolean a(cl cl1)
    {
        return cl1.l;
    }

    static List b(cl cl1)
    {
        return cl1.s;
    }

    private void b(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if(s.size() > 0)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        if(!flag)
            break MISSING_BLOCK_LABEL_29;
        c(context);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch(Throwable throwable)
        {
            ex.a(6, g, "", throwable);
        }
        return;
    }

    static void b(cl cl1, Context context)
    {
        cl1.a(context);
    }

    private void c(Context context)
    {
        cc cc1;
        ex.a(3, g, "generating agent report");
        cc1 = new cc(m, n, D(), q, r, u, s, p, N.a(false), b(), System.currentTimeMillis());
        o = new ArrayList(s);
        if(cc1 == null)
            break MISSING_BLOCK_LABEL_178;
        if(cc1.a() != null)
        {
            ex.a(3, g, (new StringBuilder()).append("generated report of size ").append(cc1.a().length).append(" with ").append(s.size()).append(" reports.").toString());
            a(cc1.a());
            s.removeAll(o);
            o = null;
            z();
            return;
        }
        try
        {
            ex.e(g, "Error generating report");
            return;
        }
        catch(Throwable throwable)
        {
            ex.a(6, g, "", throwable);
        }
        return;
    }

    static void c(cl cl1)
    {
        cl1.z();
    }

    static void c(cl cl1, Context context)
    {
        cl1.b(context);
    }

    private void d(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        if(!k.exists()) goto _L2; else goto _L1
_L1:
        ex.a(4, g, (new StringBuilder()).append("loading persistent data: ").append(k.getAbsolutePath()).toString());
        DataInputStream datainputstream = new DataInputStream(new FileInputStream(k));
        cm cm1 = new cm();
        l = cm1.a(datainputstream, m);
        if(l)
        {
            q = cm1.a();
            r = cm1.c();
            s = cm1.b();
        }
_L7:
        fh.a(datainputstream);
_L8:
        if(l) goto _L4; else goto _L3
_L3:
        if(!k.delete()) goto _L6; else goto _L5
_L5:
        ex.a(3, g, "Deleted persistence file");
_L4:
        if(!l)
        {
            q = false;
            r = u;
            l = true;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
          goto _L7
        Throwable throwable;
        throwable;
_L10:
        ex.c(g, "Error when loading persistent file", throwable);
        fh.a(datainputstream);
          goto _L8
        Exception exception;
        exception;
        throw exception;
        Exception exception1;
        exception1;
        datainputstream = null;
_L9:
        fh.a(datainputstream);
        throw exception1;
_L6:
        ex.a(6, g, "Cannot delete persistence file");
          goto _L4
        Throwable throwable1;
        throwable1;
        ex.a(6, g, "", throwable1);
          goto _L4
_L2:
        ex.a(4, g, "Agent cache file doesn't exist.");
          goto _L4
        exception1;
          goto _L9
        throwable;
        datainputstream = null;
          goto _L10
    }

    private void r()
    {
        if(Q.b())
            Q.a();
        S = 1 + S;
    }

    private void s()
    {
        S = -1 + S;
    }

    private void t()
    {
        ei ei1 = eh.a();
        D = ((Byte)ei1.a("Gender")).byteValue();
        ei1.a("Gender", this);
        ex.a(4, g, (new StringBuilder()).append("initSettings, Gender = ").append(D).toString());
        C = (String)ei1.a("UserId");
        ei1.a("UserId", this);
        ex.a(4, g, (new StringBuilder()).append("initSettings, UserId = ").append(C).toString());
        B = ((Boolean)ei1.a("LogEvents")).booleanValue();
        ei1.a("LogEvents", this);
        ex.a(4, g, (new StringBuilder()).append("initSettings, LogEvents = ").append(B).toString());
        E = ((Long)ei1.a("Age")).longValue();
        ei1.a("Age", this);
        ex.a(4, g, (new StringBuilder()).append("initSettings, BirthDate = ").append(E).toString());
        F = ((Long)ei1.a("ContinueSessionMillis")).longValue();
        ei1.a("ContinueSessionMillis", this);
        ex.a(4, g, (new StringBuilder()).append("initSettings, ContinueSessionMillis = ").append(F).toString());
    }

    private void u()
    {
        ex.e(g, "Start session");
        long l1 = SystemClock.elapsedRealtime();
        u = System.currentTimeMillis();
        v = l1;
        a(new fi() {

            public void a()
            {
                if(!cl.a(a))
                    cl.a(a, eg.a().b());
                cl.b(a, eg.a().b());
            }

            final cl a;

            
            {
                a = cl.this;
                super();
            }
        }
);
    }

    private void v()
    {
        ex.e(g, "Continuing previous session");
        if(!s.isEmpty())
            s.remove(-1 + s.size());
    }

    private void w()
    {
        if(a() != 0)
            ex.a(6, g, (new StringBuilder()).append("Error! Session with apiKey = ").append(k()).append(" was ended while getSessionCount() is not 0").toString());
        f();
    }

    private void x()
    {
        if(TextUtils.isEmpty(t))
            a(new fi() {

                public void a()
                {
                    cl.a(a, eo.a());
                }

                final cl a;

            
            {
                a = cl.this;
                super();
            }
            }
);
    }

    private void y()
    {
        a(new fi(eg.a().b()) {

            public void a()
            {
                cj cj1 = b.g();
                cl.b(b).add(cj1);
                cl.c(b);
                cl.c(b, a);
            }

            final Context a;
            final cl b;

            
            {
                b = cl.this;
                a = context;
                super();
            }
        }
);
    }

    private void z()
    {
        this;
        JVM INSTR monitorenter ;
        if(et.a(k)) goto _L2; else goto _L1
_L1:
        ex.e(g, "Error persisting report: could not create directory");
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(k));
        cm cm1 = new cm();
        cm1.a(q);
        cm1.a(r);
        cm1.a(s);
        cm1.a(dataoutputstream, m, D());
          goto _L3
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception.printStackTrace();
          goto _L3
        Exception exception;
        exception;
        throw exception;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
          goto _L3
    }

    int a()
    {
        return S;
    }

    public void a(fi fi)
    {
        P.post(fi);
    }

    public void a(String s1, Object obj)
    {
        if(s1.equals("Gender"))
        {
            D = ((Byte)obj).byteValue();
            ex.a(4, g, (new StringBuilder()).append("onSettingUpdate, Gender = ").append(D).toString());
            return;
        }
        if(s1.equals("UserId"))
        {
            C = (String)obj;
            ex.a(4, g, (new StringBuilder()).append("onSettingUpdate, UserId = ").append(C).toString());
            return;
        }
        if(s1.equals("LogEvents"))
        {
            B = ((Boolean)obj).booleanValue();
            ex.a(4, g, (new StringBuilder()).append("onSettingUpdate, LogEvents = ").append(B).toString());
            return;
        }
        if(s1.equals("Age"))
        {
            E = ((Long)obj).longValue();
            ex.a(4, g, (new StringBuilder()).append("onSettingUpdate, Birthdate = ").append(E).toString());
            return;
        }
        if(s1.equals("ContinueSessionMillis"))
        {
            F = ((Long)obj).longValue();
            ex.a(4, g, (new StringBuilder()).append("onSettingUpdate, ContinueSessionMillis = ").append(F).toString());
            return;
        } else
        {
            ex.a(6, g, "onSettingUpdate internal error!");
            return;
        }
    }

    public void a(String s1, String s2, String s3, Throwable throwable)
    {
        this;
        JVM INSTR monitorenter ;
        if(s1 == null) goto _L2; else goto _L1
_L1:
        if(!"uncaught".equals(s1)) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L6:
        L = 1 + L;
        if(K.size() >= e) goto _L5; else goto _L4
_L4:
        Long long2 = Long.valueOf(System.currentTimeMillis());
        ca ca3 = new ca(C(), long2.longValue(), s1, s2, s3, throwable);
        K.add(ca3);
        ex.e(g, (new StringBuilder()).append("Error logged: ").append(ca3.c()).toString());
_L8:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        flag = false;
          goto _L6
_L5:
        int i1;
        if(!flag)
            break MISSING_BLOCK_LABEL_247;
        i1 = 0;
_L9:
        if(i1 >= K.size()) goto _L8; else goto _L7
_L7:
        ca ca1 = (ca)K.get(i1);
        if(ca1.c() == null || "uncaught".equals(ca1.c()))
            break MISSING_BLOCK_LABEL_241;
        Long long1 = Long.valueOf(System.currentTimeMillis());
        ca ca2 = new ca(C(), long1.longValue(), s1, s2, s3, throwable);
        K.set(i1, ca2);
          goto _L8
        Exception exception;
        exception;
        throw exception;
        i1++;
          goto _L9
        ex.e(g, "Max errors logged. No more errors logged.");
          goto _L8
    }

    public void a(String s1, Map map)
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = H.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        cb cb1 = (cb)iterator.next();
        if(!cb1.a(s1)) goto _L4; else goto _L3
_L3:
        long l1 = SystemClock.elapsedRealtime() - v;
        if(map == null) goto _L6; else goto _L5
_L5:
        if(map.size() <= 0 || J >= d) goto _L6; else goto _L7
_L7:
        int i1;
        HashMap hashmap;
        i1 = J - cb1.d();
        hashmap = new HashMap(cb1.c());
        cb1.a(map);
        if(i1 + cb1.d() > d)
            break MISSING_BLOCK_LABEL_211;
        if(cb1.c().size() <= b) goto _L9; else goto _L8
_L8:
        ex.e(g, (new StringBuilder()).append("MaxEventParams exceeded on endEvent: ").append(cb1.c().size()).toString());
        cb1.b(hashmap);
_L6:
        cb1.a(l1);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L9:
        J = i1 + cb1.d();
          goto _L6
        Exception exception;
        exception;
        throw exception;
        cb1.b(hashmap);
        I = false;
        J = d;
        ex.e(g, "Event Log size exceeded. No more event details logged.");
          goto _L6
    }

    public void a(String s1, Map map, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        long l1;
        String s2;
        int i1;
        l1 = SystemClock.elapsedRealtime() - v;
        s2 = fh.a(s1);
        i1 = s2.length();
        if(i1 != 0) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        bx.a a1 = (bx.a)G.get(s2);
        if(a1 != null) goto _L4; else goto _L3
_L3:
        if(G.size() >= a) goto _L6; else goto _L5
_L5:
        bx.a a2 = new bx.a();
        a2.a = 1;
        G.put(s2, a2);
        ex.e(g, (new StringBuilder()).append("Event count started: ").append(s2).toString());
_L11:
        if(!B || H.size() >= c || J >= d) goto _L8; else goto _L7
_L7:
        if(map != null)
            break MISSING_BLOCK_LABEL_390;
        Map map1 = Collections.emptyMap();
_L12:
        if(map1.size() <= b) goto _L10; else goto _L9
_L9:
        ex.e(g, (new StringBuilder()).append("MaxEventParams exceeded: ").append(map1.size()).toString());
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L6:
        ex.e(g, (new StringBuilder()).append("Too many different events. Event not counted: ").append(s2).toString());
          goto _L11
_L4:
        a1.a = 1 + a1.a;
        ex.e(g, (new StringBuilder()).append("Event count incremented: ").append(s2).toString());
          goto _L11
_L10:
label0:
        {
            cb cb1 = new cb(B(), s2, map1, l1, flag);
            if(cb1.d() + J > d)
                break label0;
            H.add(cb1);
            J = J + cb1.d();
        }
          goto _L1
        J = d;
        I = false;
        ex.e(g, "Event Log size exceeded. No more event details logged.");
          goto _L1
_L8:
        I = false;
          goto _L1
        map1 = map;
          goto _L12
    }

    public void a(Map map)
    {
        O = map;
    }

    Map b()
    {
        return O;
    }

    public void c()
    {
        q = true;
    }

    public void d()
    {
        r();
        if(!T)
        {
            u();
            T = true;
            return;
        } else
        {
            v();
            return;
        }
    }

    public void e()
    {
        ex.e(g, "Trying to end session");
        if(T)
        {
            if(a() > 0)
                s();
            if(a() == 0)
            {
                Q.a(F);
                return;
            }
        }
    }

    public void f()
    {
        if(!T)
            return;
        ex.e(g, "Ending session");
        S = 0;
        if(Q.b())
            Q.a();
        w = SystemClock.elapsedRealtime() - v;
        a(w);
        y();
        if(R != null)
            R.d(k());
        eh.a().b("Gender", this);
        eh.a().b("UserId", this);
        eh.a().b("Age", this);
        eh.a().b("LogEvents", this);
        eh.a().b("ContinueSessionMillis", this);
        P.getLooper().quit();
    }

    cj g()
    {
        this;
        JVM INSTR monitorenter ;
        ck ck1;
        ck1 = new ck();
        ck1.a(n);
        ck1.a(u);
        ck1.b(w);
        ck1.c(x);
        ck1.b(l());
        ck1.c(m());
        ck1.a(A);
        ck1.d(j());
        ck1.a(E());
        ck1.b(i());
        ck1.a(D);
        ck1.a(Long.valueOf(E));
        ck1.a(q());
        ck1.a(o());
        ck1.a(I);
        ck1.b(p());
        ck1.c(L);
        cj cj1 = new cj(ck1);
_L2:
        if(cj1 != null)
            break MISSING_BLOCK_LABEL_171;
        ex.e(g, "New session report wasn't created");
        this;
        JVM INSTR monitorexit ;
        return cj1;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        cj1 = null;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public void h()
    {
        this;
        JVM INSTR monitorenter ;
        M = 1 + M;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    int i()
    {
        return M;
    }

    String j()
    {
        if(C == null)
            return "";
        else
            return C;
    }

    public String k()
    {
        return m;
    }

    public String l()
    {
        return y;
    }

    public String m()
    {
        return z;
    }

    public void n()
    {
        w();
    }

    List o()
    {
        return H;
    }

    List p()
    {
        return K;
    }

    Map q()
    {
        return G;
    }

    static int a = 100;
    static int b = 10;
    static int c = 1000;
    static int d = 0x27100;
    static int e = 50;
    static int f = 20;
    private static final String g = com/flurry/sdk/cl.getSimpleName();
    private byte A;
    private boolean B;
    private String C;
    private byte D;
    private long E;
    private long F;
    private final Map G = new HashMap();
    private final List H = new ArrayList();
    private boolean I;
    private int J;
    private final List K = new ArrayList();
    private int L;
    private int M;
    private final by N = new by();
    private Map O;
    private final Handler P;
    private cn Q;
    private a R;
    private int S;
    private boolean T;
    private final AtomicInteger h = new AtomicInteger(0);
    private final AtomicInteger i = new AtomicInteger(0);
    private final AtomicInteger j = new AtomicInteger(0);
    private File k;
    private volatile boolean l;
    private String m;
    private String n;
    private List o;
    private final Map p = new HashMap();
    private boolean q;
    private long r;
    private List s;
    private String t;
    private long u;
    private long v;
    private long w;
    private long x;
    private String y;
    private String z;

}
