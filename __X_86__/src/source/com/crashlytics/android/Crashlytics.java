// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.crashlytics.android;

import android.app.Activity;
import android.content.*;
import android.content.pm.*;
import com.crashlytics.android.internal.A;
import com.crashlytics.android.internal.D;
import com.crashlytics.android.internal.aM;
import com.crashlytics.android.internal.aP;
import com.crashlytics.android.internal.aQ;
import com.crashlytics.android.internal.aR;
import com.crashlytics.android.internal.aS;
import com.crashlytics.android.internal.aX;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.af;
import com.crashlytics.android.internal.ag;
import com.crashlytics.android.internal.ai;
import com.crashlytics.android.internal.ao;
import com.crashlytics.android.internal.av;
import com.crashlytics.android.internal.ax;
import com.crashlytics.android.internal.ay;
import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.u;
import com.crashlytics.android.internal.v;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

// Referenced classes of package com.crashlytics.android:
//            b, v, c, t, 
//            Y, T, aa, ad, 
//            X, u, p, k, 
//            CrashlyticsMissingDependencyException, CrashTest, l, m, 
//            n, V, o, PinningInfoProvider, 
//            CrashlyticsListener

public final class Crashlytics extends u
{

    public Crashlytics()
    {
        e = null;
        f = null;
        g = null;
        h = null;
    }

    static int a(Crashlytics crashlytics, float f1, int i1)
    {
        return (int)(f1 * (float)i1);
    }

    private b a(Y y)
    {
        String as[] = new String[1];
        as[0] = i;
        String s1 = ab.a(as);
        int i1 = ai.a(l).a();
        return new b(p, k, o, n, s1, m, i1, q, "0", y);
    }

    static com.crashlytics.android.v a(Crashlytics crashlytics)
    {
        return crashlytics.d;
    }

    private static void a(int i1, String s1, String s2)
    {
        Crashlytics crashlytics = getInstance();
        if(crashlytics == null || crashlytics.d == null)
        {
            com.crashlytics.android.internal.v.a().b().a(s1, "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging messages.", null);
            return;
        } else
        {
            long l1 = System.currentTimeMillis() - crashlytics.a;
            crashlytics.d.a(l1, (new StringBuilder()).append(ab.b(i1)).append("/").append(s1).append(" ").append(s2).toString());
            return;
        }
    }

    static void a(String s1)
    {
        D d1 = (D)com.crashlytics.android.internal.v.a().a(com/crashlytics/android/internal/D);
        if(d1 != null)
            d1.a(new ag(s1));
    }

    private void a(String s1, Context context, float f1)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if(j == null) goto _L2; else goto _L1
_L1:
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Crashlytics already started, ignoring re-initialization attempt.");
_L6:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        p = s1;
        j = new ContextWrapper(context.getApplicationContext());
        t = new av(com.crashlytics.android.internal.v.a().b());
        com.crashlytics.android.internal.v.a().b().b("Crashlytics", (new StringBuilder("Initializing Crashlytics ")).append(getCrashlyticsVersion()).toString());
        PackageInfo packageinfo;
        k = j.getPackageName();
        PackageManager packagemanager = j.getPackageManager();
        l = packagemanager.getInstallerPackageName(k);
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", (new StringBuilder("Installer package name is: ")).append(l).toString());
        packageinfo = packagemanager.getPackageInfo(k, 0);
        n = Integer.toString(packageinfo.versionCode);
        if(packageinfo.versionName != null) goto _L4; else goto _L3
_L3:
        String s2 = "0.0";
_L8:
        o = s2;
        m = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
        q = Integer.toString(context.getApplicationInfo().targetSdkVersion);
        i = ab.i(context);
_L9:
        e = new ao(j);
        e.h();
        (new c(i, ab.a(j, "com.crashlytics.RequireBuildId", true))).a(s1, k);
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Installing exception handler...");
        d = new com.crashlytics.android.v(Thread.getDefaultUncaughtExceptionHandler(), c, i);
        flag = d.f();
        d.d();
        d.c();
        d.h();
        Thread.setDefaultUncaughtExceptionHandler(d);
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Successfully installed exception handler.");
_L10:
        CountDownLatch countdownlatch;
        countdownlatch = new CountDownLatch(1);
        (new Thread(new t(this, context, f1, countdownlatch), "Crashlytics Initializer")).start();
        if(!flag) goto _L6; else goto _L5
_L5:
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        if(countdownlatch.await(4000L, TimeUnit.MILLISECONDS)) goto _L6; else goto _L7
_L7:
        com.crashlytics.android.internal.v.a().b().c("Crashlytics", "Crashlytics initialization was not completed in the allotted time.");
          goto _L6
        InterruptedException interruptedexception;
        interruptedexception;
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Crashlytics was interrupted during initialization.", interruptedexception);
          goto _L6
        Exception exception;
        exception;
        throw exception;
_L4:
        s2 = packageinfo.versionName;
          goto _L8
        Exception exception1;
        exception1;
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Error setting up app properties", exception1);
          goto _L9
        Exception exception2;
        exception2;
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "There was a problem installing the exception handler.", exception2);
          goto _L10
    }

    static void a(boolean flag)
    {
        ab.a().edit().putBoolean("always_send_reports_opt_in", true).commit();
    }

    private boolean a(Context context, float f1)
    {
        boolean flag;
        String s1;
        flag = true;
        s1 = ab.g(getContext());
        aX ax2;
        aS.a().a(context, t, n, o, i()).c();
        ax2 = aS.a().b();
        aX ax1 = ax2;
_L7:
        if(ax1 == null)
            break MISSING_BLOCK_LABEL_436;
        aM am = ax1.a;
        if(!"new".equals(am.a)) goto _L2; else goto _L1
_L1:
        b b2 = a(Y.a(getContext(), s1));
        if(!(new T(i(), am.b, t)).a(b2)) goto _L4; else goto _L3
_L3:
        boolean flag5 = aS.a().d();
        boolean flag4 = flag5;
_L8:
        boolean flag2 = flag4;
_L9:
        boolean flag1;
        boolean flag3;
        V v1;
        try
        {
            flag1 = ax1.d.b;
        }
        catch(Exception exception3)
        {
            com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Error getting collect reports setting.", exception3);
            flag1 = false;
        }
_L11:
        if(!flag2 || !flag1) goto _L6; else goto _L5
_L5:
        flag = true & d.b();
        v1 = q();
        flag3 = false;
        Exception exception;
        Exception exception2;
        b b1;
        if(v1 != null)
            try
            {
                (new aa(v1)).a(f1);
            }
            catch(Exception exception1)
            {
                com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Error sending crash report", exception1);
                flag3 = false;
            }
_L10:
        if(flag3)
            com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Crash reporting disabled.");
        return flag;
        exception;
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Error dealing with settings", exception);
        ax1 = null;
          goto _L7
_L4:
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Failed to create app with Crashlytics service.", null);
        flag4 = false;
          goto _L8
_L2:
label0:
        {
            if(!"configured".equals(am.a))
                break label0;
            flag4 = aS.a().d();
        }
          goto _L8
        if(am.d)
        {
            com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Server says an update is required - forcing a full App update.");
            b1 = a(Y.a(getContext(), s1));
            (new ad(i(), am.b, t)).a(b1);
        }
        flag4 = flag;
          goto _L8
        exception2;
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Error performing auto configuration.", exception2);
        flag2 = false;
          goto _L9
_L6:
        flag3 = flag;
          goto _L10
        flag1 = false;
        flag2 = false;
          goto _L11
    }

    static boolean a(Crashlytics crashlytics, Activity activity, aQ aq)
    {
        X x = new X(activity, aq);
        com.crashlytics.android.u u1 = new com.crashlytics.android.u(crashlytics, (byte)0);
        activity.runOnUiThread(new p(crashlytics, activity, u1, x, aq));
        com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Waiting for user opt-in.");
        u1.b();
        return u1.a();
    }

    static boolean a(Crashlytics crashlytics, Context context, float f1)
    {
        return crashlytics.a(context, f1);
    }

    static void b(String s1)
    {
        D d1 = (D)com.crashlytics.android.internal.v.a().a(com/crashlytics/android/internal/D);
        if(d1 != null)
            d1.a(new af(s1));
    }

    private static String c(String s1)
    {
        if(s1 != null)
        {
            s1 = s1.trim();
            if(s1.length() > 1024)
                s1 = s1.substring(0, 1024);
        }
        return s1;
    }

    static String d()
    {
        return k;
    }

    static String e()
    {
        return l;
    }

    static String f()
    {
        return o;
    }

    static String g()
    {
        return n;
    }

    public static String getCrashlyticsVersion()
    {
        return getInstance().getVersion();
    }

    public static Crashlytics getInstance()
    {
        com/crashlytics/android/Crashlytics;
        JVM INSTR monitorenter ;
        Crashlytics crashlytics = (Crashlytics)com.crashlytics.android.internal.v.a().a(com/crashlytics/android/Crashlytics);
        if(crashlytics == null) goto _L2; else goto _L1
_L1:
        com/crashlytics/android/Crashlytics;
        JVM INSTR monitorexit ;
        return crashlytics;
_L2:
        if(v == null)
            v = new Crashlytics();
        crashlytics = v;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public static PinningInfoProvider getPinningInfoProvider()
    {
        return s;
    }

    static String h()
    {
        return m;
    }

    static String i()
    {
        return ab.a(j, "com.crashlytics.ApiEndpoint");
    }

    static boolean k()
    {
        return ab.a().getBoolean("always_send_reports_opt_in", false);
    }

    public static void log(int i1, String s1, String s2)
    {
        a(i1, s1, s2);
        com.crashlytics.android.internal.v.a().b().a(i1, (new StringBuilder()).append(s1).toString(), (new StringBuilder()).append(s2).toString(), true);
    }

    public static void log(String s1)
    {
        a(3, "Crashlytics", s1);
    }

    public static void logException(Throwable throwable)
    {
        Crashlytics crashlytics = getInstance();
        if(crashlytics == null || crashlytics.d == null)
        {
            com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging exceptions.", null);
            return;
        }
        if(throwable == null)
        {
            com.crashlytics.android.internal.v.a().b().a(5, "Crashlytics", "Crashlytics is ignoring a request to log a null exception.");
            return;
        } else
        {
            crashlytics.d.a(Thread.currentThread(), throwable);
            return;
        }
    }

    static av s()
    {
        return t;
    }

    public static void setApplicationInstallationIdentifier(String s1)
    {
        com.crashlytics.android.internal.v.a().a(c(s1));
    }

    public static void setBool(String s1, boolean flag)
    {
        setString(s1, Boolean.toString(flag));
    }

    public static void setDouble(String s1, double d1)
    {
        setString(s1, Double.toString(d1));
    }

    public static void setFloat(String s1, float f1)
    {
        setString(s1, Float.toString(f1));
    }

    public static void setInt(String s1, int i1)
    {
        setString(s1, Integer.toString(i1));
    }

    public static void setLong(String s1, long l1)
    {
        setString(s1, Long.toString(l1));
    }

    public static void setPinningInfoProvider(PinningInfoProvider pinninginfoprovider)
    {
label0:
        {
            if(s != pinninginfoprovider)
            {
                s = pinninginfoprovider;
                if(t != null)
                {
                    if(pinninginfoprovider != null)
                        break label0;
                    t.a(null);
                }
            }
            return;
        }
        t.a(new k(pinninginfoprovider));
    }

    public static void setString(String s1, String s2)
    {
        if(s1 == null)
            if(j != null && ab.f(j))
            {
                throw new IllegalArgumentException("Custom attribute key cannot be null.");
            } else
            {
                com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Attempting to set custom attribute with null key, ignoring.", null);
                return;
            }
        String s3 = c(s1);
        if(getInstance().b.size() < 64 || getInstance().b.containsKey(s3))
        {
            String s4;
            if(s2 == null)
                s4 = "";
            else
                s4 = c(s2);
            getInstance().b.put(s3, s4);
            return;
        } else
        {
            com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Exceeded maximum number of custom attributes (64)");
            return;
        }
    }

    public static void setUserEmail(String s1)
    {
        getInstance().g = c(s1);
    }

    public static void setUserIdentifier(String s1)
    {
        getInstance().f = c(s1);
    }

    public static void setUserName(String s1)
    {
        getInstance().h = c(s1);
    }

    public static void start(Context context)
    {
        start(context, 1.0F);
    }

    public static void start(Context context, float f1)
    {
        u = f1;
        if(!ab.d(context))
            com.crashlytics.android.internal.v.a().a(new A());
        u au[] = new u[2];
        au[0] = getInstance();
        au[1] = new D();
        com.crashlytics.android.internal.v.a(context, au);
    }

    final Map a()
    {
        return Collections.unmodifiableMap(b);
    }

    final ao b()
    {
        return e;
    }

    protected final void c()
    {
        Context context = super.getContext();
        String s1 = com.crashlytics.android.internal.r.a(context, false);
        if(s1 == null)
            return;
        try
        {
            a(s1, context, u);
            return;
        }
        catch(CrashlyticsMissingDependencyException crashlyticsmissingdependencyexception)
        {
            throw crashlyticsmissingdependencyexception;
        }
        catch(Exception exception)
        {
            com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Crashlytics was not started due to an exception during initialization", exception);
        }
    }

    public final void crash()
    {
        (new CrashTest()).indexOutOfBounds();
    }

    public final boolean getDebugMode()
    {
        return com.crashlytics.android.internal.v.a().f();
    }

    public final String getVersion()
    {
        return com.crashlytics.android.internal.v.a().getVersion();
    }

    final boolean j()
    {
        return ((Boolean)aS.a().a(new l(this), Boolean.valueOf(false))).booleanValue();
    }

    final com.crashlytics.android.v l()
    {
        return d;
    }

    final String m()
    {
        if(e.a())
            return f;
        else
            return null;
    }

    final String n()
    {
        if(e.a())
            return g;
        else
            return null;
    }

    final String o()
    {
        if(e.a())
            return h;
        else
            return null;
    }

    final boolean p()
    {
        return ((Boolean)aS.a().a(new m(this), Boolean.valueOf(true))).booleanValue();
    }

    final V q()
    {
        return (V)aS.a().a(new n(this), null);
    }

    final aR r()
    {
        return (aR)aS.a().a(new o(this), null);
    }

    public final void setDebugMode(boolean flag)
    {
        com.crashlytics.android.internal.v.a().a(flag);
    }

    public final void setListener(CrashlyticsListener crashlyticslistener)
    {
        c = crashlyticslistener;
    }

    public final boolean verifyPinning(URL url)
    {
label0:
        {
            try
            {
                if(getPinningInfoProvider() == null)
                    break label0;
                ay ay1 = t.a(ax.a, url.toString());
                ((HttpsURLConnection)ay1.a()).setInstanceFollowRedirects(false);
                ay1.b();
            }
            catch(Exception exception)
            {
                com.crashlytics.android.internal.v.a().b().a("Crashlytics", "Could not verify SSL pinning", exception);
                return false;
            }
            return true;
        }
        return false;
    }

    public static final String TAG = "Crashlytics";
    private static ContextWrapper j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static boolean r = false;
    private static PinningInfoProvider s = null;
    private static av t;
    private static float u;
    private static Crashlytics v;
    private final long a = System.currentTimeMillis();
    private final ConcurrentHashMap b = new ConcurrentHashMap();
    private CrashlyticsListener c;
    private com.crashlytics.android.v d;
    private ao e;
    private String f;
    private String g;
    private String h;
    private String i;

}
