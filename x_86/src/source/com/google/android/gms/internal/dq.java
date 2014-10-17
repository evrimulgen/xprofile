// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.*;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.common.api.Status;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package com.google.android.gms.internal:
//            eh, du, dr, ds, 
//            dt, en

public final class dq extends eh
{
    private static final class a
        implements com.google.android.gms.cast.Cast.ApplicationConnectionResult
    {

        public ApplicationMetadata getApplicationMetadata()
        {
            return yb;
        }

        public String getApplicationStatus()
        {
            return yc;
        }

        public String getSessionId()
        {
            return pz;
        }

        public Status getStatus()
        {
            return vl;
        }

        public boolean getWasLaunched()
        {
            return yd;
        }

        private final String pz;
        private final Status vl;
        private final ApplicationMetadata yb;
        private final String yc;
        private final boolean yd;

        public a(Status status)
        {
            this(status, null, null, null, false);
        }

        public a(Status status, ApplicationMetadata applicationmetadata, String s, String s1, boolean flag)
        {
            vl = status;
            yb = applicationmetadata;
            yc = s;
            pz = s1;
            yd = flag;
        }
    }


    public dq(Context context, Looper looper, CastDevice castdevice, long l, com.google.android.gms.cast.Cast.Listener listener, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, 
            com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, (String[])null);
        xG = castdevice;
        wz = listener;
        xJ = l;
        mHandler = new Handler(looper);
        xM = false;
        xF = null;
        xK = null;
        xe = 0.0D;
        xf = false;
        xR = new HashMap();
    }

    static ApplicationMetadata a(dq dq1, ApplicationMetadata applicationmetadata)
    {
        dq1.xF = applicationmetadata;
        return applicationmetadata;
    }

    static com.google.android.gms.common.api.a.c a(dq dq1, com.google.android.gms.common.api.a.c c1)
    {
        dq1.xS = c1;
        return c1;
    }

    static String a(dq dq1, String s)
    {
        dq1.xO = s;
        return s;
    }

    static void a(dq dq1, String s, double d1, boolean flag)
    {
        dq1.a(s, d1, flag);
    }

    private void a(String s, double d1, boolean flag)
    {
        boolean flag1;
        boolean flag2;
        du du1;
        Object aobj[];
        if(!dr.a(s, xK))
        {
            xK = s;
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if(wz != null && (flag1 || xL))
            wz.onApplicationStatusChanged();
        if(d1 != xe)
        {
            xe = d1;
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if(flag != xf)
        {
            xf = flag;
            flag2 = true;
        }
        du1 = xE;
        aobj = new Object[2];
        aobj[0] = Boolean.valueOf(flag2);
        aobj[1] = Boolean.valueOf(xL);
        du1.b("hasChange=%b, mFirstStatusUpdate=%b", aobj);
        if(wz != null && (flag2 || xL))
            wz.onVolumeChanged();
        xL = false;
    }

    static boolean a(dq dq1, boolean flag)
    {
        dq1.xM = flag;
        return flag;
    }

    static com.google.android.gms.common.api.a.c b(dq dq1)
    {
        return dq1.xS;
    }

    static com.google.android.gms.common.api.a.c b(dq dq1, com.google.android.gms.common.api.a.c c1)
    {
        dq1.xT = c1;
        return c1;
    }

    static String b(dq dq1, String s)
    {
        dq1.xP = s;
        return s;
    }

    static com.google.android.gms.cast.Cast.Listener c(dq dq1)
    {
        return dq1.wz;
    }

    static Handler d(dq dq1)
    {
        return dq1.mHandler;
    }

    private void d(com.google.android.gms.common.api.a.c c1)
    {
        synchronized(xU)
        {
            if(xS != null)
                xS.b(new a(new Status(2002)));
            xS = c1;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void db()
        throws IllegalStateException
    {
        if(!xM)
            throw new IllegalStateException("not connected to a device");
        else
            return;
    }

    static du dc()
    {
        return xE;
    }

    static Object dd()
    {
        return xU;
    }

    static Object de()
    {
        return xV;
    }

    static Map e(dq dq1)
    {
        return dq1.xI;
    }

    static CastDevice f(dq dq1)
    {
        return dq1.xG;
    }

    private void f(com.google.android.gms.common.api.a.c c1)
    {
label0:
        {
            synchronized(xV)
            {
                if(xT == null)
                    break label0;
                c1.b(new Status(2001));
            }
            return;
        }
        xT = c1;
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static Map g(dq dq1)
    {
        return dq1.xR;
    }

    static com.google.android.gms.common.api.a.c h(dq dq1)
    {
        return dq1.xT;
    }

    public void Q(String s)
        throws IllegalArgumentException, RemoteException
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        com.google.android.gms.cast.Cast.MessageReceivedCallback messagereceivedcallback;
        synchronized(xI)
        {
            messagereceivedcallback = (com.google.android.gms.cast.Cast.MessageReceivedCallback)xI.remove(s);
        }
        if(messagereceivedcallback == null)
            break MISSING_BLOCK_LABEL_59;
        ((ds)eb()).T(s);
        return;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        du du1 = xE;
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = illegalstateexception.getMessage();
        du1.a(illegalstateexception, "Error unregistering namespace (%s): %s", aobj);
        return;
    }

    public void a(double d1)
        throws IllegalArgumentException, IllegalStateException, RemoteException
    {
        if(Double.isInfinite(d1) || Double.isNaN(d1))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Volume cannot be ").append(d1).toString());
        } else
        {
            ((ds)eb()).a(d1, xe, xf);
            return;
        }
    }

    protected void a(int i, IBinder ibinder, Bundle bundle)
    {
        if(i == 0 || i == 1001)
        {
            xM = true;
            xL = true;
        } else
        {
            xM = false;
        }
        if(i == 1001)
        {
            xQ = new Bundle();
            xQ.putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            i = 0;
        }
        super.a(i, ibinder, bundle);
    }

    protected void a(en en1, eh.e e1)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        du du1 = xE;
        Object aobj[] = new Object[2];
        aobj[0] = xO;
        aobj[1] = xP;
        du1.b("getServiceFromBroker(): mLastApplicationId=%s, mLastSessionId=%s", aobj);
        xG.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", xJ);
        if(xO != null)
        {
            bundle.putString("last_application_id", xO);
            if(xP != null)
                bundle.putString("last_session_id", xP);
        }
        en1.a(e1, 0x41f6b8, getContext().getPackageName(), xH.asBinder(), bundle);
    }

    public void a(String s, com.google.android.gms.cast.Cast.MessageReceivedCallback messagereceivedcallback)
        throws IllegalArgumentException, IllegalStateException, RemoteException
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        Q(s);
        if(messagereceivedcallback != null)
        {
            synchronized(xI)
            {
                xI.put(s, messagereceivedcallback);
            }
            ((ds)eb()).S(s);
        }
        return;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(String s, com.google.android.gms.common.api.a.c c1)
        throws IllegalStateException, RemoteException
    {
        f(c1);
        ((ds)eb()).R(s);
    }

    public void a(String s, String s1, com.google.android.gms.common.api.a.c c1)
        throws IllegalArgumentException, IllegalStateException, RemoteException
    {
        if(TextUtils.isEmpty(s1))
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        if(s == null || s.length() > 128)
            throw new IllegalArgumentException("Invalid namespace length");
        if(s1.length() > 0x10000)
        {
            throw new IllegalArgumentException("Message exceeds maximum size");
        } else
        {
            db();
            long l = xN.incrementAndGet();
            ((ds)eb()).a(s, s1, l);
            xR.put(Long.valueOf(l), c1);
            return;
        }
    }

    public void a(String s, boolean flag, com.google.android.gms.common.api.a.c c1)
        throws IllegalStateException, RemoteException
    {
        d(c1);
        ((ds)eb()).e(s, flag);
    }

    protected String aF()
    {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    protected String aG()
    {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    public void b(String s, String s1, com.google.android.gms.common.api.a.c c1)
        throws IllegalStateException, RemoteException
    {
        d(c1);
        ((ds)eb()).e(s, s1);
    }

    public Bundle cY()
    {
        if(xQ != null)
        {
            Bundle bundle = xQ;
            xQ = null;
            return bundle;
        } else
        {
            return super.cY();
        }
    }

    public void cZ()
        throws IllegalStateException, RemoteException
    {
        ((ds)eb()).cZ();
    }

    public double da()
        throws IllegalStateException
    {
        db();
        return xe;
    }

    public void disconnect()
    {
        if(!isConnected())
            break MISSING_BLOCK_LABEL_40;
        synchronized(xI)
        {
            xI.clear();
        }
        ((ds)eb()).disconnect();
_L2:
        super.disconnect();
        return;
        exception1;
        map;
        JVM INSTR monitorexit ;
        throw exception1;
        RemoteException remoteexception;
        remoteexception;
        du du1 = xE;
        Object aobj[] = new Object[1];
        aobj[0] = remoteexception.getMessage();
        du1.b("Error while disconnecting the controller interface: %s", aobj);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        super.disconnect();
        throw exception;
    }

    public void e(com.google.android.gms.common.api.a.c c1)
        throws IllegalStateException, RemoteException
    {
        f(c1);
        ((ds)eb()).df();
    }

    public ApplicationMetadata getApplicationMetadata()
        throws IllegalStateException
    {
        db();
        return xF;
    }

    public String getApplicationStatus()
        throws IllegalStateException
    {
        db();
        return xK;
    }

    public boolean isMute()
        throws IllegalStateException
    {
        db();
        return xf;
    }

    protected IInterface p(IBinder ibinder)
    {
        return v(ibinder);
    }

    public void t(boolean flag)
        throws IllegalStateException, RemoteException
    {
        ((ds)eb()).a(flag, xe, xf);
    }

    protected ds v(IBinder ibinder)
    {
        return ds.a.w(ibinder);
    }

    private static final du xE = new du("CastClientImpl");
    private static final Object xU = new Object();
    private static final Object xV = new Object();
    private final Handler mHandler;
    private final com.google.android.gms.cast.Cast.Listener wz;
    private ApplicationMetadata xF;
    private final CastDevice xG;
    private final dt xH = new dt.a() {

        private boolean D(int i)
        {
            Object obj = dq.de();
            obj;
            JVM INSTR monitorenter ;
            if(dq.h(xW) == null)
                break MISSING_BLOCK_LABEL_49;
            dq.h(xW).b(new Status(i));
            dq.b(xW, null);
            return true;
            obj;
            JVM INSTR monitorexit ;
            return false;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private void b(long l1, int i)
        {
            com.google.android.gms.common.api.a.c c1;
            synchronized(dq.g(xW))
            {
                c1 = (com.google.android.gms.common.api.a.c)dq.g(xW).remove(Long.valueOf(l1));
            }
            if(c1 != null)
                c1.b(new Status(i));
            return;
            exception;
            map;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void A(int i)
        {
            synchronized(dq.dd())
            {
                if(dq.b(xW) != null)
                {
                    dq.b(xW).b(new a(new Status(i)));
                    dq.a(xW, null);
                }
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void B(int i)
        {
            D(i);
        }

        public void C(int i)
        {
            D(i);
        }

        public void a(ApplicationMetadata applicationmetadata, String s, String s1, boolean flag)
        {
            dq.a(xW, applicationmetadata);
            dq.a(xW, applicationmetadata.getApplicationId());
            dq.b(xW, s1);
            synchronized(dq.dd())
            {
                if(dq.b(xW) != null)
                {
                    dq.b(xW).b(new a(new Status(0), applicationmetadata, s, s1, flag));
                    dq.a(xW, null);
                }
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void a(String s, long l1)
        {
            b(l1, 0);
        }

        public void a(String s, long l1, int i)
        {
            b(l1, i);
        }

        public void b(String s, double d1, boolean flag)
        {
            dq.d(xW).post(new Runnable(this, s, d1, flag) {

                public void run()
                {
                    dq.a(xY.xW, xZ, xs, xt);
                }

                final _cls1 xY;
                final String xZ;
                final double xs;
                final boolean xt;

            
            {
                xY = _pcls1;
                xZ = s;
                xs = d1;
                xt = flag;
                super();
            }
            }
);
        }

        public void b(String s, byte abyte0[])
        {
            du du1 = dq.dc();
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(abyte0.length);
            du1.b("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", aobj);
        }

        public void d(String s, String s1)
        {
            dq.dc().b("Receive (type=text, ns=%s) %s", new Object[] {
                s, s1
            });
            dq.d(xW).post(new Runnable(this, s, s1) {

                public void run()
                {
                    com.google.android.gms.cast.Cast.MessageReceivedCallback messagereceivedcallback;
                    synchronized(dq.e(xY.xW))
                    {
                        messagereceivedcallback = (com.google.android.gms.cast.Cast.MessageReceivedCallback)dq.e(xY.xW).get(wp);
                    }
                    if(messagereceivedcallback != null)
                    {
                        messagereceivedcallback.onMessageReceived(dq.f(xY.xW), wp, ya);
                        return;
                    } else
                    {
                        du du1 = dq.dc();
                        Object aobj[] = new Object[1];
                        aobj[0] = wp;
                        du1.b("Discarded message for unknown namespace '%s'", aobj);
                        return;
                    }
                    exception;
                    map;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                final String wp;
                final _cls1 xY;
                final String ya;

            
            {
                xY = _pcls1;
                wp = s;
                ya = s1;
                super();
            }
            }
);
        }

        public void onApplicationDisconnected(int i)
        {
            dq.a(xW, null);
            dq.b(xW, null);
            while(D(i) || dq.c(xW) == null) 
                return;
            dq.d(xW).post(new Runnable(this, i) {

                public void run()
                {
                    if(dq.c(xY.xW) != null)
                        dq.c(xY.xW).onApplicationDisconnected(xX);
                }

                final int xX;
                final _cls1 xY;

            
            {
                xY = _pcls1;
                xX = i;
                super();
            }
            }
);
        }

        public void z(int i)
        {
            du du1 = dq.dc();
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            du1.b("ICastDeviceControllerListener.onDisconnected: %d", aobj);
            dq.a(xW, false);
            dq.a(xW, null);
            if(i != 0)
                xW.O(2);
        }

        final dq xW;

            
            {
                xW = dq.this;
                super();
            }
    }
;
    private final Map xI = new HashMap();
    private final long xJ;
    private String xK;
    private boolean xL;
    private boolean xM;
    private final AtomicLong xN = new AtomicLong(0L);
    private String xO;
    private String xP;
    private Bundle xQ;
    private Map xR;
    private com.google.android.gms.common.api.a.c xS;
    private com.google.android.gms.common.api.a.c xT;
    private double xe;
    private boolean xf;

}
