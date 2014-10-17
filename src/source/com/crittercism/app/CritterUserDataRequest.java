// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.crittercism.app;

import crittercism.android.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.crittercism.app:
//            CritterCallback, CritterUserData

public class CritterUserDataRequest
{

    public CritterUserDataRequest(CritterCallback crittercallback)
    {
        a = crittercallback;
        b = ay.r();
        c = b.o;
        d = new HashMap();
        e = new cz(b);
    }

    static cz a(CritterUserDataRequest critteruserdatarequest)
    {
        return critteruserdatarequest.e;
    }

    static Map a(CritterUserDataRequest critteruserdatarequest, Map map)
    {
        critteruserdatarequest.d = map;
        return map;
    }

    static ay b(CritterUserDataRequest critteruserdatarequest)
    {
        return critteruserdatarequest.b;
    }

    static Map c(CritterUserDataRequest critteruserdatarequest)
    {
        return critteruserdatarequest.d;
    }

    static CritterCallback d(CritterUserDataRequest critteruserdatarequest)
    {
        return critteruserdatarequest.a;
    }

    public void makeRequest()
    {
        this;
        JVM INSTR monitorenter ;
        Runnable runnable = new Runnable() {

            public final void run()
            {
                CritterUserDataRequest.a(a).run();
                CritterUserDataRequest.a(a, CritterUserDataRequest.a(a).a);
                boolean flag = CritterUserDataRequest.b(a).f.d();
                CritterUserDataRequest.d(a).onCritterDataReceived(new CritterUserData(CritterUserDataRequest.c(a), flag));
            }

            final CritterUserDataRequest a;

            
            {
                a = CritterUserDataRequest.this;
                super();
            }
        }
;
        if(!c.a(runnable))
            (new dm(runnable)).start();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public CritterUserDataRequest requestDidCrashOnLastLoad()
    {
        e.c();
        return this;
    }

    public CritterUserDataRequest requestOptOutStatus()
    {
        e.b();
        return this;
    }

    public CritterUserDataRequest requestRateMyAppInfo()
    {
        e.e();
        return this;
    }

    public CritterUserDataRequest requestUserUUID()
    {
        e.d();
        return this;
    }

    private final CritterCallback a;
    private ay b;
    private cs c;
    private Map d;
    private cz e;
}
