// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.content.Context;
import java.util.*;
import java.util.concurrent.ExecutorService;

// Referenced classes of package crittercism.android:
//            cv, bj, au, cm, 
//            ct, co, cs

public final class cr
{

    public cr(Context context, au au1)
    {
        a = context;
        b = au1;
        c = new ArrayList();
    }

    public final void a()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = c.iterator(); iterator.hasNext(); arraylist.add(new Thread((cv)iterator.next())));
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); ((Thread)iterator1.next()).start());
        for(Iterator iterator2 = arraylist.iterator(); iterator2.hasNext(); ((Thread)iterator2.next()).join());
    }

    public final void a(bj bj1, cm cm1, String s)
    {
        this;
        JVM INSTR monitorenter ;
        if(bj1.c() > 0)
        {
            bj bj2 = bj1.a(a);
            cl cl = cm1.a(bj2, bj1, s, a, b, b.i());
            ct ct1 = new ct(bj2, bj1, b, (new co(s, bj2.f())).a(), cl);
            c.add(ct1);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void a(cs cs1, ExecutorService executorservice)
    {
        Iterator iterator = c.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            cv cv1 = (cv)iterator.next();
            if(!cs1.a(cv1))
                executorservice.execute(cv1);
        } while(true);
    }

    private Context a;
    private au b;
    private List c;
}
