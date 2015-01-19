// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import com.crittercism.app.CrittercismNDK;
import java.io.File;
import java.util.*;

// Referenced classes of package crittercism.android:
//            cv, az, av, bd, 
//            bj, cr, ay, dg, 
//            cu, au, dk, bi, 
//            h, bq, bw, de, 
//            bx, aw

public final class cs extends cv
{

    public cs(az az1, Context context, av av1, aw aw, au au1)
    {
        a = new ConditionVariable();
        g = new ArrayList();
        h = false;
        j = null;
        b = az1;
        c = context;
        d = av1;
        e = aw;
        f = au1;
        i = false;
    }

    private void a(File file)
    {
        if(file.getAbsolutePath().contains("com.crittercism"))
        {
            if(file.isDirectory())
            {
                File afile[] = file.listFiles();
                int k = afile.length;
                for(int l = 0; l < k; l++)
                    a(afile[l]);

            }
            if(file.delete())
            {
                (new StringBuilder("Deleted ")).append(file.getName());
                return;
            }
        }
    }

    private void c()
    {
        this;
        JVM INSTR monitorenter ;
        h = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean d()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = h;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private File e()
    {
        int k;
        File file;
        k = 0;
        file = new File((new StringBuilder()).append(c.getFilesDir().getAbsolutePath()).append("/").append(b.getNativeDumpPath()).toString());
        if(file.exists() && file.isDirectory()) goto _L2; else goto _L1
_L1:
        File afile[];
        return null;
_L2:
        if((afile = file.listFiles()) != null)
        {
            if(afile.length != 1)
                continue; /* Loop/switch isn't completed */
            File file2 = afile[0];
            file2.isFile();
            if(file2.isFile())
                return file2;
        }
        continue; /* Loop/switch isn't completed */
        if(afile.length <= 1) goto _L1; else goto _L3
_L3:
        int l = afile.length;
        while(k < l) 
        {
            File file1 = afile[k];
            file1.isFile();
            file1.delete();
            k++;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    private void f()
    {
        boolean _tmp = i;
        bj bj1 = d.j();
        bj bj2 = d.k();
        bj bj3 = d.l();
        bj bj4 = d.m();
        b.a();
        bj1.a(new bd());
        if(!b.delaySendingAppLoad())
        {
            cr cr1 = new cr(c, f);
            cr1.a(bj1, new ci.a(), b.a());
            cr1.a(bj2, new cn.a(), b.a());
            cr1.a(bj3, new cn.a(), b.a());
            cr1.a(bj4, new cn.a(), b.a());
            cr1.a();
        }
    }

    public final void a()
    {
        File file1;
        File file = new File((new StringBuilder()).append(c.getFilesDir().getAbsolutePath()).append("/com.crittercism/pending").toString());
        if(!file.exists() || file.isDirectory())
            a(file);
        ay.r().x.a();
        (new cu(c, f, e)).run();
        i = f.i().d();
        file1 = e();
        if(!i) goto _L2; else goto _L1
_L1:
        i;
        if(file1 == null)
            break MISSING_BLOCK_LABEL_140;
        if(file1.exists())
            file1.isFile();
        (new bj(c, bi.a)).a();
        (new bj(c, bi.b)).a();
        (new bj(c, bi.c)).a();
        (new bj(c, bi.d)).a();
        (new bj(c, bi.e)).a();
        (new bj(c, bi.g)).a();
        (new bj(c, bi.f)).a();
        if(file1 == null)
            break MISSING_BLOCK_LABEL_270;
        file1.delete();
        crittercism.android.h.b(c);
_L14:
        c();
        for(Iterator iterator = g.iterator(); iterator.hasNext(); ((Runnable)iterator.next()).run());
        break MISSING_BLOCK_LABEL_794;
        Exception exception1;
        exception1;
        (new StringBuilder("Exception in run(): ")).append(exception1.getMessage());
        j = exception1;
        a.open();
        return;
_L2:
        h h1;
        SharedPreferences sharedpreferences;
        Context context = c;
        h1 = new h(context);
        sharedpreferences = context.getSharedPreferences("com.crittercism.optmz.config", 0);
        if(!sharedpreferences.contains("interval")) goto _L4; else goto _L3
_L3:
        h1.d = sharedpreferences.getInt("interval", 10);
        if(!sharedpreferences.contains("kill")) goto _L6; else goto _L5
_L5:
        h1.c = sharedpreferences.getBoolean("kill", false);
        if(!sharedpreferences.contains("persist")) goto _L8; else goto _L7
_L7:
        h1.b = sharedpreferences.getBoolean("persist", false);
        if(!sharedpreferences.contains("enabled")) goto _L10; else goto _L9
_L9:
        h1.a = sharedpreferences.getBoolean("enabled", false);
_L11:
        if(h1 == null)
            break MISSING_BLOCK_LABEL_509;
        ay.r().a(h1);
        i;
        d.q();
        i;
        bq.a(f.i().e());
        i;
        if(file1 == null)
            break MISSING_BLOCK_LABEL_567;
        if(file1.exists())
            file1.isFile();
        dk dk1;
        bj bj1;
        bj bj2;
        bj bj3;
        bj bj4;
        d;
        dk1 = f.i();
        bj1 = d.n();
        bj2 = d.o();
        bj3 = d.p();
        bj4 = d.l();
        if(file1 == null)
            break MISSING_BLOCK_LABEL_672;
        bj4.a(new bw(file1, bj1, bj3, bj2));
        file1.delete();
        dk1.a(new de(true));
        boolean flag;
        bj3.a();
        bj2.a();
        bj1.a(bj3);
        bj1.a(bx.a);
        flag = b.isNdkCrashReportingEnabled();
        if(!flag)
            break MISSING_BLOCK_LABEL_726;
        CrittercismNDK.installNdkLib(c, b.getNativeDumpPath());
_L12:
        f();
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        a.open();
        throw exception;
_L4:
        h1 = null;
          goto _L11
_L6:
        h1 = null;
          goto _L11
_L8:
        h1 = null;
          goto _L11
_L10:
        h1 = null;
          goto _L11
        Throwable throwable;
        throwable;
        (new StringBuilder("Exception installing ndk library: ")).append(throwable.getClass().getName());
          goto _L12
        a.open();
        return;
        if(true) goto _L14; else goto _L13
_L13:
    }

    public final boolean a(Runnable runnable)
    {
        this;
        JVM INSTR monitorenter ;
        if(d()) goto _L2; else goto _L1
_L1:
        g.add(runnable);
        boolean flag = true;
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final void b()
    {
        a.block();
    }

    private ConditionVariable a;
    private az b;
    private Context c;
    private av d;
    private aw e;
    private au f;
    private List g;
    private boolean h;
    private boolean i;
    private Exception j;
}
