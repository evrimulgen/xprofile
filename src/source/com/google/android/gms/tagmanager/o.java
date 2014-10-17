// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.fn;

// Referenced classes of package com.google.android.gms.tagmanager:
//            ca, ce, cq, cp, 
//            bf, Container, TagManager, n, 
//            bh, cg, r, ContainerHolder, 
//            m, o, c, OnContentsResponse, 
//            OnDownloadProgressResponse, bg, OpenContentsRequest, u, 
//            CloseContentsRequest, ak

class o extends ca
{
    static interface a extends m
    {

        public Result d(Status status)
        {
            return f(status);
        }

        public Status f(Status status)
        {
            return status;
        }

        final o DJ;

        private a(o o1)
        {
            DJ = o1;
            super();
        }

        a(o o1, _cls1 _pcls1)
        {
            this(o1);
        }
    }

    private class b extends m
    {

        public Result d(Status status)
        {
            return f(status);
        }

        public Status f(Status status)
        {
            return status;
        }

        final o DJ;

        private b(o o1)
        {
            DJ = o1;
            super();
        }

        b(o o1, _cls1 _pcls1)
        {
            this(o1);
        }
    }

    private class c extends com.google.android.gms.drive.internal.c
    {

        public void a(OnContentsResponse oncontentsresponse)
            throws RemoteException
        {
            vj.b(new l.a(Status.zQ, oncontentsresponse.eX()));
        }

        public void a(OnDownloadProgressResponse ondownloadprogressresponse)
            throws RemoteException
        {
            if(DL != null)
                DL.onProgress(ondownloadprogressresponse.eY(), ondownloadprogressresponse.eZ());
        }

        public void l(Status status)
            throws RemoteException
        {
            vj.b(new l.a(status, null));
        }

        private final com.google.android.gms.drive.DriveFile.DownloadProgressListener DL;
        private final com.google.android.gms.common.api.a.c vj;

        public c(com.google.android.gms.common.api.a.c c1, com.google.android.gms.drive.DriveFile.DownloadProgressListener downloadprogresslistener)
        {
            vj = c1;
            DL = downloadprogresslistener;
        }
    }

    private class d extends m
    {

        public Result d(Status status)
        {
            return n(status);
        }

        public com.google.android.gms.drive.DriveApi.ContentsResult n(Status status)
        {
            return new l.a(status, null);
        }

        final o DJ;

        private d(o o1)
        {
            DJ = o1;
            super();
        }

        d(o o1, _cls1 _pcls1)
        {
            this(o1);
        }
    }

    static interface e
        extends Releasable
    {

        public abstract void a(bg bg);

        public abstract void bf(String s1);

        public abstract void d(long l, String s1);
    }

    static interface f
        extends Releasable
    {

        public abstract void a(bg bg);

        public abstract void b(com.google.android.gms.internal.jd.a a1);

        public abstract cr.c bP(int i);

        public abstract void iN();
    }


    o(Context context, TagManager tagmanager, Looper looper, String s1, int i, f f1, e e1, 
            fl fl1, cg cg)
    {
        Looper looper1;
        if(looper == null)
            looper1 = Looper.getMainLooper();
        else
            looper1 = looper;
        super(looper1);
        mContext = context;
        TY = tagmanager;
        if(looper == null)
            looper = Looper.getMainLooper();
        zs = looper;
        TM = s1;
        Ud = i;
        Ue = f1;
        Uj = e1;
        Ub = new d(this);
        Uh = new com.google.android.gms.internal.c.j();
        Ty = fl1;
        Uc = cg;
        if(iL())
            bc(ce.ju().jw());
    }

    public o(Context context, TagManager tagmanager, Looper looper, String s1, int i, r r)
    {
        this(context, tagmanager, looper, s1, i, ((f) (new cq(context, s1))), ((e) (new cp(context, s1, r))), fn.eI(), ((cg) (new bf(30, 0xdbba0L, 5000L, "refreshing", fn.eI()))));
    }

    static fl a(o o1)
    {
        return o1.Ty;
    }

    private void a(com.google.android.gms.internal.c.j j)
    {
        this;
        JVM INSTR monitorenter ;
        if(Ue != null)
        {
            com.google.android.gms.internal.jd.a a1 = new com.google.android.gms.internal.jd.a();
            a1.Yb = TR;
            a1.fV = new com.google.android.gms.internal.c.f();
            a1.Yc = j;
            Ue.b(a1);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void a(com.google.android.gms.internal.c.j j, long l, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if(!flag) goto _L2; else goto _L1
_L1:
        boolean flag1 = Ug;
        if(!flag1) goto _L2; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Container container;
        if(isReady())
            if(Uf != null);
        Uh = j;
        TR = l;
        s(Math.max(0L, Math.min(0x2932e00L, (0x2932e00L + TR) - Ty.currentTimeMillis())));
        container = new Container(mContext, TY.getDataLayer(), TM, l, j);
        if(Uf != null)
            break; /* Loop/switch isn't completed */
        Uf = new n(TY, zs, container, Ub);
_L5:
        if(!isReady() && Uk.b(container))
            a(((Result) (Uf)));
        if(true) goto _L3; else goto _L4
        Exception exception;
        exception;
        throw exception;
_L4:
        Uf.a(container);
          goto _L5
    }

    static void a(o o1, long l)
    {
        o1.s(l);
    }

    static void a(o o1, com.google.android.gms.internal.c.j j)
    {
        o1.a(j);
    }

    static void a(o o1, com.google.android.gms.internal.c.j j, long l, boolean flag)
    {
        o1.a(j, l, flag);
    }

    static boolean b(o o1)
    {
        return o1.Ug;
    }

    static com.google.android.gms.internal.c.j c(o o1)
    {
        return o1.Uh;
    }

    static long d(o o1)
    {
        return o1.TR;
    }

    static boolean e(o o1)
    {
        return o1.iL();
    }

    static n f(o o1)
    {
        return o1.Uf;
    }

    static cg g(o o1)
    {
        return o1.Uc;
    }

    private boolean iL()
    {
        ce ce1 = ce.ju();
        return (ce1.jv() == ce.a.VX || ce1.jv() == ce.a.VY) && TM.equals(ce1.getContainerId());
    }

    private void s(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(Uj != null)
            break MISSING_BLOCK_LABEL_17;
        bh.w("Refresh requested, but no network load scheduler.");
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        Uj.d(l, Uh.fW);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private void z(boolean flag)
    {
        Ue.a(new b(this));
        Uj.a(new c(this));
        cr.c c1 = Ue.bP(Ud);
        if(c1 != null)
            Uf = new n(TY, zs, new Container(mContext, TY.getDataLayer(), TM, 0L, c1), Ub);
        Uk = new b(this, flag) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((n)a1);
            }

            protected void a(n n1)
                throws RemoteException
            {
                Dw.close();
                n1.eT().a(new CloseContentsRequest(Dw, true), new ak(this));
            }

            final o DJ;
            final Contents Dw;

            
            {
                DJ = o1;
                Dw = contents;
                super(o1, null);
            }
        }
;
        if(iL())
        {
            Uj.d(0L, "");
            return;
        } else
        {
            Ue.iN();
            return;
        }
    }

    protected ContainerHolder O(Status status)
    {
        if(Uf != null)
            return Uf;
        if(status == Status.zS)
            bh.t("timer expired: setting result to failure");
        return new n(status);
    }

    void bc(String s1)
    {
        this;
        JVM INSTR monitorenter ;
        Ui = s1;
        if(Uj != null)
            Uj.bf(s1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected Result d(Status status)
    {
        return O(status);
    }

    String iF()
    {
        this;
        JVM INSTR monitorenter ;
        String s1 = Ui;
        this;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        throw exception;
    }

    public void iI()
    {
        cr.c c1 = Ue.bP(Ud);
        if(c1 != null)
        {
            Container container = new Container(mContext, TY.getDataLayer(), TM, 0L, c1);
            a(new n(TY, zs, container, new d(this) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((n)a1);
                }

                protected void a(n n1)
                    throws RemoteException
                {
                    n1.eT().a(new OpenContentsRequest(DJ.getDriveId(), DH), new c(this, DI));
                }

                final int DH;
                final com.google.android.gms.drive.DriveFile.DownloadProgressListener DI;
                final o DJ;

            
            {
                DJ = o1;
                DH = i;
                DI = downloadprogresslistener;
                super(o1);
            }
            }
));
        } else
        {
            bh.t("Default was requested, but no default container was found");
            a(O(new Status(10, "Default was requested, but no default container was found", null)));
        }
        Uj = null;
        Ue = null;
    }

    public void iJ()
    {
        z(false);
    }

    public void iK()
    {
        z(true);
    }

    private final String TM;
    private long TR;
    private final TagManager TY;
    private final fl Ty;
    private final d Ub;
    private final cg Uc;
    private final int Ud;
    private f Ue;
    private volatile n Uf;
    private volatile boolean Ug;
    private com.google.android.gms.internal.c.j Uh;
    private String Ui;
    private e Uj;
    private a Uk;
    private final Context mContext;
    private final Looper zs;
}
