// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.crittercism.app.CritterRateMyAppButtons;
import java.util.concurrent.*;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            au, av, aw, e, 
//            dk, ck, dn, az, 
//            d, cs, h, f, 
//            di, dm, dg, dj, 
//            bj, bi, dd, db, 
//            da, at, b, cv, 
//            cr, df, cx, cj, 
//            cp, co, bc, bx, 
//            cf

public final class ay
    implements au, av, aw, e
{

    private ay()
    {
        b = false;
        c = null;
        d = null;
        f = new dk();
        n = new ck();
        o = null;
        p = null;
        q = Executors.newCachedThreadPool(new dn());
        r = Executors.newSingleThreadExecutor(new dn());
        t = false;
        u = "";
        v = new az();
        w = new d(r);
    }

    public static ay r()
    {
        if(a == null)
            a = new ay();
        return a;
    }

    private String u()
    {
        try
        {
            if(u == null || u.equals(""))
                u = c.getPackageName();
        }
        catch(Exception exception)
        {
            Log.w("CrittercismInstance", "Call to getPackageName() failed.  Please contact us at support@crittercism.com.");
            u = new String();
        }
        return u;
    }

    public final AlertDialog a(Context context, String s1, String s2)
    {
        boolean flag = false;
        if(f.d())
            Log.e("CrittercismInstance", "User has opted out of crittercism.  generateRateMyAppAlertDialog returning null.");
        else
        if(!(context instanceof Activity))
        {
            Log.e("CrittercismInstance", "Context object must be an instance of Activity for AlertDialog to form correctly.  generateRateMyAppAlertDialog returning null.");
            flag = false;
        } else
        if(s2 == null || s2 != null && s2.length() == 0)
        {
            Log.e("CrittercismInstance", "Message has to be a non-empty string.  generateRateMyAppAlertDialog returning null.");
            flag = false;
        } else
        if(android.os.Build.VERSION.SDK_INT < 5)
        {
            Log.e("Crittercism", "Rate my app not supported below api level 5");
            flag = false;
        } else
        {
            flag = true;
        }
        if(!flag)
            return null;
        String s3 = t();
        if(s3 == null)
        {
            Log.e("Crittercism", "Cannot create proper URI to open app market.  Returning null.");
            return null;
        }
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(s1).setMessage(s2);
        AlertDialog alertdialog;
        try
        {
            alertdialog = builder.create();
        }
        catch(Exception exception)
        {
            Log.e("Crittercism", "Failed to create AlertDialog instance from AlertDialog.Builder.  Did you remember to call Looper.prepare() before calling Crittercism.generateRateMyAppAlertDialog()?");
            return null;
        }
        alertdialog.setButton(-1, "Yes", new android.content.DialogInterface.OnClickListener(s3) {

            public final void onClick(DialogInterface dialoginterface, int i1)
            {
                try
                {
                    ay.r().a(a);
                    return;
                }
                catch(Exception exception1)
                {
                    Log.w("CrittercismInstance", "YES button failed.  Email support@crittercism.com.");
                }
            }

            final String a;
            final ay b;

            
            {
                b = ay.this;
                a = s1;
                super();
            }
        }
);
        alertdialog.setButton(-2, "No", new android.content.DialogInterface.OnClickListener() {

            public final void onClick(DialogInterface dialoginterface, int i1)
            {
                try
                {
                    ay.r().s();
                    return;
                }
                catch(Exception exception1)
                {
                    Log.w("CrittercismInstance", "NO button failed.  Email support@crittercism.com.");
                }
            }

            final ay a;

            
            {
                a = ay.this;
                super();
            }
        }
);
        alertdialog.setButton(-3, "Maybe Later", new android.content.DialogInterface.OnClickListener() {

            public final void onClick(DialogInterface dialoginterface, int i1)
            {
                try
                {
                    ay.r();
                    return;
                }
                catch(Exception exception1)
                {
                    Log.w("CrittercismInstance", "MAYBE LATER button failed.  Email support@crittercism.com.");
                }
            }

            final ay a;

            
            {
                a = ay.this;
                super();
            }
        }
);
        return alertdialog;
    }

    public final String a()
    {
        if(b)
            return d;
        else
            return new String();
    }

    public final String a(String s1, String s2)
    {
        SharedPreferences sharedpreferences = c.getSharedPreferences(s1, 0);
        String s3 = null;
        if(sharedpreferences != null)
            s3 = sharedpreferences.getString(s2, null);
        return s3;
    }

    public final void a(b b1)
    {
        cv cv = new cv(b1) {

            public final void a()
            {
                b.m.a(a);
            }

            final b a;
            final ay b;

            
            {
                b = ay.this;
                a = b1;
                super();
            }
        }
;
        if(!o.a(cv))
            r.execute(cv);
    }

    public final void a(h h1)
    {
        while(!t || !v.isOptmzEnabled() || !h1.a || h1.c) 
            return;
        Log.i("Crittercism", "Enabling OPTMZ");
        p.a(h1.d, TimeUnit.SECONDS);
        p.a();
    }

    public final void a(String s1)
    {
        f.b().b = true;
        (new dm(new cv() {

            public final void a()
            {
                a.f.b().a(ay.a, cf.h.a(), cf.h.b());
            }

            final ay a;

            
            {
                a = ay.this;
                super();
            }
        }
)).start();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(0x10000000);
        intent.setData(Uri.parse(s1));
        c.startActivity(intent);
    }

    public final void a(String s1, String s2, int i1)
    {
        SharedPreferences sharedpreferences = c.getSharedPreferences(s1, 0);
        if(sharedpreferences != null)
        {
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            if(editor != null)
            {
                editor.remove(s2);
                editor.putInt(s2, i1);
                editor.commit();
            }
        }
    }

    public final void a(String s1, String s2, String s3)
    {
        SharedPreferences sharedpreferences = c.getSharedPreferences(s1, 0);
        if(sharedpreferences != null)
        {
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            if(editor != null)
            {
                editor.remove(s2);
                editor.putString(s2, s3);
                editor.commit();
            }
        }
    }

    public final void a(String s1, String s2, boolean flag)
    {
        SharedPreferences sharedpreferences = c.getSharedPreferences(s1, 0);
        if(sharedpreferences != null)
        {
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            if(editor != null)
            {
                editor.remove(s2);
                editor.putBoolean(s2, flag);
                editor.commit();
            }
        }
    }

    public final void a(Throwable throwable)
    {
        this;
        JVM INSTR monitorenter ;
        cv cv = new cv(throwable, Thread.currentThread().getId()) {

            public final void a()
            {
                bc bc1;
                if(!c.f.d())
                    if(c.h.a(bc1 = new bc(a, b, bc.a.b, c.k, c.l, c.m)) && c.n.a())
                    {
                        cr cr1 = new cr(c.c, ay.a);
                        cr1.a(c.h, new cn.a(), c.v.a());
                        cr1.a(c.o, c.q);
                        c.n.b();
                        return;
                    }
            }

            final Throwable a;
            final long b;
            final ay c;

            
            {
                c = ay.this;
                a = throwable;
                b = l1;
                super();
            }
        }
;
        if(!o.a(cv))
            r.execute(cv);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final int b(String s1, String s2)
    {
        SharedPreferences sharedpreferences = c.getSharedPreferences(s1, 0);
        int i1 = 0;
        if(sharedpreferences != null)
            i1 = sharedpreferences.getInt(s2, 0);
        return i1;
    }

    public final String b()
    {
        return (new bq.c()).a;
    }

    public final String c()
    {
        String s1 = "";
        if(x != null)
            s1 = x.a();
        return s1;
    }

    public final boolean c(String s1, String s2)
    {
        SharedPreferences sharedpreferences = c.getSharedPreferences(s1, 0);
        boolean flag = false;
        if(sharedpreferences != null)
            flag = sharedpreferences.getBoolean(s2, false);
        return flag;
    }

    public final int d()
    {
        int i1 = -1;
        if(f != null)
            i1 = Integer.valueOf(f.c().a).intValue();
        return i1;
    }

    public final String e()
    {
        return (new bq.f()).a;
    }

    public final String f()
    {
        return "Android";
    }

    public final String g()
    {
        return Build.MODEL;
    }

    public final String h()
    {
        return android.os.Build.VERSION.RELEASE;
    }

    public final dk i()
    {
        return f;
    }

    public final bj j()
    {
        return g;
    }

    public final bj k()
    {
        return h;
    }

    public final bj l()
    {
        return i;
    }

    public final bj m()
    {
        return j;
    }

    public final bj n()
    {
        return k;
    }

    public final bj o()
    {
        return m;
    }

    public final bj p()
    {
        return l;
    }

    public final void q()
    {
        k = new bj(c, bi.e);
        l = new bj(c, bi.g);
        m = new bj(c, bi.f);
        g = new bj(c, bi.a);
        h = new bj(c, bi.b);
        i = new bj(c, bi.c);
        j = new bj(c, bi.d);
    }

    public final void s()
    {
        f.b().b = true;
        (new dm(new cv() {

            public final void a()
            {
                a.f.b().a(ay.a, cf.h.a(), cf.h.b());
            }

            final ay a;

            
            {
                a = ay.this;
                super();
            }
        }
)).start();
    }

    public final String t()
    {
label0:
        {
            PackageManager packagemanager = c.getPackageManager();
            String s1 = u();
            String s2 = null;
            if(s1 != null)
            {
                int i1 = s1.length();
                s2 = null;
                if(i1 > 0)
                {
                    db db1 = dd.a(packagemanager.getInstallerPackageName(s1));
                    if(db1 == null)
                        break label0;
                    s2 = db1.a(s1).a();
                }
            }
            return s2;
        }
        Log.w("Crittercism", "Could not find app market for this app.  Will try rate-my-app test target in config.");
        return v.getRateMyAppTestTarget();
    }

    static ay a;
    public boolean b;
    public Context c;
    public String d;
    public Thread.UncaughtExceptionHandler e;
    public dk f;
    bj g;
    bj h;
    bj i;
    bj j;
    bj k;
    bj l;
    bj m;
    ck n;
    public cs o;
    public f p;
    public ExecutorService q;
    public ExecutorService r;
    public at s;
    public boolean t;
    public String u;
    public az v;
    public d w;
    public dg x;

    // Unreferenced inner class crittercism/android/ay$1

/* anonymous class */
    public final class _cls1 extends cv
    {

        public final void a()
        {
            if(a.f.d())
            {
                return;
            } else
            {
                cr cr1 = new cr(a.c, ay.a);
                cr1.a(a.g, new ci.a(), a.v.a());
                cr1.a(a.h, new cn.a(), a.v.a());
                cr1.a(a.i, new cn.a(), a.v.a());
                cr1.a(a.j, new cn.a(), a.v.a());
                cr1.a(a.o, a.q);
                return;
            }
        }

        final ay a;

            public 
            {
                a = ay.this;
                super();
            }
    }


    // Unreferenced inner class crittercism/android/ay$2

/* anonymous class */
    public final class _cls2 extends cv
    {

        public final void a()
        {
            dk dk1 = ay.a.f;
            if(dk1.d())
            {
                return;
            } else
            {
                df df1 = dk1.e();
                df1.a(a);
                (new cx((new cj(ay.a)).a("metadata", df1.a()), new cp((new co(b.v.a(), "/android_v2/update_user_metadata")).a()))).run();
                return;
            }
        }

        final JSONObject a;
        final ay b;

            public 
            {
                b = ay.this;
                a = jsonobject;
                super();
            }
    }


    // Unreferenced inner class crittercism/android/ay$3

/* anonymous class */
    public final class _cls3 extends cv
    {

        public final void a()
        {
            dk dk1 = ay.a.f;
            if(dk1.d())
            {
                return;
            } else
            {
                df df1 = dk1.e();
                df1.a("username", a);
                (new cx((new cj(ay.a)).a("metadata", df1.a()), new cp((new co(b.v.a(), "/android_v2/update_user_metadata")).a()))).run();
                return;
            }
        }

        final String a;
        final ay b;

            public 
            {
                b = ay.this;
                a = s1;
                super();
            }
    }


    // Unreferenced inner class crittercism/android/ay$4

/* anonymous class */
    public static final class _cls4
    {

        public static final int a[];

        static 
        {
            a = new int[CritterRateMyAppButtons.values().length];
            try
            {
                a[CritterRateMyAppButtons.YES.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            try
            {
                a[CritterRateMyAppButtons.NO.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                a[CritterRateMyAppButtons.LATER.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror2)
            {
                return;
            }
        }
    }


    // Unreferenced inner class crittercism/android/ay$6

/* anonymous class */
    public final class _cls6 extends cv
    {

        public final void a()
        {
            b.k.a(a);
        }

        final bx a;
        final ay b;

            public 
            {
                b = ay.this;
                a = bx;
                super();
            }
    }

}
