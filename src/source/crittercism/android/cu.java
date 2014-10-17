// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.content.Context;

// Referenced classes of package crittercism.android:
//            cv, au, dk, cf, 
//            aw, di, dj

public final class cu extends cv
{

    public cu(Context context, au au1, aw aw1)
    {
        a = context;
        b = aw1;
        c = au1.i();
    }

    public final void a()
    {
        dk dk1 = c;
        aw aw1 = b;
        Context _tmp = a;
        dk1.b(aw1);
        b.a(cf.i.a(), cf.i.b(), false);
        if(c.d())
        {
            return;
        } else
        {
            c.b().a(b, cf.h.a(), cf.h.b());
            c.c().a(b, cf.k.a(), cf.k.b());
            return;
        }
    }

    private Context a;
    private aw b;
    private dk c;
}
