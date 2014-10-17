// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.crittercism.app;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import crittercism.android.*;
import java.io.File;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

// Referenced classes of package com.crittercism.app:
//            CrittercismConfig, CritterRateMyAppButtons

public class Crittercism
{

    private Crittercism()
    {
    }

    public static AlertDialog generateRateMyAppAlertDialog(Context context)
    {
        ay ay1;
        di di1;
        String s;
        String s1;
        AlertDialog alertdialog;
        try
        {
            ay1 = ay.r();
            di1 = ay1.f.b();
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable)
        {
            return null;
        }
        if(di1 == null) goto _L2; else goto _L1
_L1:
        s = di1.f;
_L3:
        if(di1 == null)
            break MISSING_BLOCK_LABEL_57;
        s1 = di1.g;
_L4:
        alertdialog = ay1.a(context, s1, s);
        return alertdialog;
_L2:
        s = null;
          goto _L3
        s1 = null;
          goto _L4
    }

    public static AlertDialog generateRateMyAppAlertDialog(Context context, String s, String s1)
    {
        AlertDialog alertdialog;
        try
        {
            alertdialog = ay.r().a(context, s, s1);
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable)
        {
            return null;
        }
        return alertdialog;
    }

    public static void initialize(Context context, String s)
    {
        com/crittercism/app/Crittercism;
        JVM INSTR monitorenter ;
        initialize(context, s, new CrittercismConfig());
        com/crittercism/app/Crittercism;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void initialize(Context context, String s, CrittercismConfig crittercismconfig)
    {
        com/crittercism/app/Crittercism;
        JVM INSTR monitorenter ;
        if(!s.contains("CRITTERCISM_APP_ID")) goto _L2; else goto _L1
_L1:
        Log.e("Crittercism", "ERROR: Crittercism will not work unless you enter a valid Crittercism App ID. Check your settings page to find the ID.");
_L8:
        com/crittercism/app/Crittercism;
        JVM INSTR monitorexit ;
        return;
_L2:
        boolean flag = ay.r().b;
        if(flag)
            continue; /* Loop/switch isn't completed */
        ay ay1;
        ay1 = ay.r();
        Log.i("CrittercismInstance", "Initializing Crittercism...");
        ay1.d = s;
        ay1.v = new az(crittercismconfig);
        ay1.c = context;
        ay1.s = new at(ay1.c, ay1.v);
        ay1.u = context.getPackageName();
        ay1.x = new dg(context);
        bq.a(ay1.s);
        bq.a(ay1.c);
        bq.a(new bu());
        bq.a(new bb(ay1.c, ay1.v));
        if(!h.a(ay1.c).exists() && ay1.v.isOptmzEnabled()) goto _L4; else goto _L3
_L3:
        ay1.o = new cs(ay1.v, context, ay1, ay1, ay1);
        ay1.e = Thread.getDefaultUncaughtExceptionHandler();
        if(!(ay1.e instanceof ax))
            Thread.setDefaultUncaughtExceptionHandler(new ax(ay1.e));
        (new dm(ay1.o)).start();
        ay1.b = true;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        (new StringBuilder("Exception in init > getInstance().initialize(..): ")).append(exception1.getClass().getName());
        continue; /* Loop/switch isn't completed */
        ThreadDeath threaddeath;
        threaddeath;
        throw threaddeath;
        Exception exception;
        exception;
        com/crittercism/app/Crittercism;
        JVM INSTR monitorexit ;
        throw exception;
_L4:
        ay1.w.a(ay1.v.getOptmzBlackListURLPatterns());
        ay1.w.b(ay1.v.getPreserveQueryStringPatterns());
        c c1 = new c(ay1.c);
        i j = new i(ay1.w, c1);
        ay1.p = new f(ay1, new URL((new StringBuilder()).append(ay1.v.b()).append("/api/apm/network").toString()));
        ay1.w.a(ay1.p);
        ay1.w.a(ay1);
        (new dm(ay1.p, "OPTMZ")).start();
        ay1.t = j.a();
_L6:
        (new StringBuilder("installedApm = ")).append(ay1.t);
        if(true) goto _L3; else goto _L5
_L5:
        Exception exception2;
        exception2;
        (new StringBuilder("Exception in startApm: ")).append(exception2.getClass().getName());
          goto _L6
        Throwable throwable;
        throwable;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static void leaveBreadcrumb(String s)
    {
        if(!ay.r().b)
        {
            Log.w("Crittercism", "Crittercism not initialized yet.  Returning.");
            return;
        }
        if(s == null)
        {
            ay ay1;
            crittercism.android.ay._cls6 _lcls6;
            try
            {
                Log.w("Crittercism", "Cannot leave null breadcrumb");
                return;
            }
            catch(ThreadDeath threaddeath)
            {
                throw threaddeath;
            }
            catch(Throwable throwable) { }
            break MISSING_BLOCK_LABEL_87;
        }
        ay1 = ay.r();
        _lcls6 = new crittercism.android.ay._cls6(ay1, new bx(s));
        if(!ay1.o.a(_lcls6))
        {
            ay1.r.execute(_lcls6);
            return;
        }
    }

    public static void logHandledException(Throwable throwable)
    {
        try
        {
            if(!ay.r().f.d())
                ay.r().a(throwable);
            return;
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable1)
        {
            return;
        }
    }

    public static void performRateMyAppButtonAction(CritterRateMyAppButtons critterratemyappbuttons)
    {
        ay ay1;
        String s;
        int j;
        Exception exception1;
        try
        {
            if(ay.r().f.d())
            {
                Log.w("Crittercism", "User has opted out of crittercism.  performRateMyAppButtonAction exiting.");
                return;
            }
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable)
        {
            return;
        }
        ay1 = ay.r();
        if(android.os.Build.VERSION.SDK_INT < 5)
        {
            Log.w("Crittercism", "Rate my app not supported below api level 5");
            return;
        }
        s = ay1.t();
        if(s != null)
            break MISSING_BLOCK_LABEL_67;
        Log.e("Crittercism", "Cannot create proper URI to open app market.  Returning null.");
        return;
        j = crittercism.android.ay._cls4.a[critterratemyappbuttons.ordinal()];
        j;
        JVM INSTR tableswitch 1 2: default 100
    //                   1 101
    //                   2 120;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        ay1.a(s);
        return;
        exception1;
        Log.w("Crittercism", "performRateMyAppButtonAction(CritterRateMyAppButtons.YES) failed.  Email support@crittercism.com.");
        return;
_L3:
        try
        {
            ay1.s();
            return;
        }
        catch(Exception exception) { }
        Log.w("Crittercism", "performRateMyAppButtonAction(CritterRateMyAppButtons.NO) failed.  Email support@crittercism.com.");
        return;
    }

    public static void sendAppLoadData()
    {
        ay ay1;
        if(!ay.r().v.delaySendingAppLoad())
            break MISSING_BLOCK_LABEL_85;
        if(ay.r().f.d())
            break MISSING_BLOCK_LABEL_96;
        ay1 = ay.r();
        if(!ay1.v.delaySendingAppLoad())
        {
            Log.w("CrittercismInstance", "CrittercismConfig instance not set to delay sending app loads.");
            return;
        }
        try
        {
            crittercism.android.ay._cls1 _lcls1 = new crittercism.android.ay._cls1(ay1);
            if(!ay1.o.a(_lcls1))
            {
                ay1.r.execute(_lcls1);
                return;
            }
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable) { }
        break MISSING_BLOCK_LABEL_96;
        Log.i("Crittercism", "sendAppLoadData() will only send data to Crittercism if \"delaySendingAppLoad\" is set to true in the configuration settings you include in the init call.");
        return;
    }

    public static void setMetadata(JSONObject jsonobject)
    {
        if(!ay.r().b)
        {
            Log.w("Crittercism", "Crittercism not initialized yet.  Returning.");
            return;
        }
        try
        {
            ay ay1 = ay.r();
            crittercism.android.ay._cls2 _lcls2 = new crittercism.android.ay._cls2(ay1, jsonobject);
            if(!ay1.o.a(_lcls2))
            {
                ay1.q.execute(_lcls2);
                return;
            }
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable) { }
        return;
    }

    public static void setOptOutStatus(boolean flag)
    {
        if(!ay.r().b)
        {
            Log.w("Crittercism", "Crittercism not initialized yet.  Returning.");
            return;
        }
        try
        {
            ay ay1 = ay.r();
            cy cy1 = new cy(ay1.c, ay1, flag);
            if(!ay1.o.a(cy1))
            {
                ay1.r.execute(cy1);
                return;
            }
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable) { }
        return;
    }

    public static void setUsername(String s)
    {
        if(!ay.r().b)
        {
            Log.w("Crittercism", "Crittercism not initialized yet.  Returning.");
            return;
        }
        try
        {
            ay ay1 = ay.r();
            crittercism.android.ay._cls3 _lcls3 = new crittercism.android.ay._cls3(ay1, s);
            if(!ay1.o.a(_lcls3))
            {
                ay1.q.execute(_lcls3);
                return;
            }
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable) { }
        return;
    }

    public static void updateLocation(Location location)
    {
        if(!ay.r().b)
        {
            Log.w("Crittercism", "Crittercism not initialized yet.  Returning.");
            return;
        }
        if(location == null)
        {
            Log.w("Crittercism", "Cannot leave null location");
            return;
        } else
        {
            ba.a(location);
            return;
        }
    }
}
