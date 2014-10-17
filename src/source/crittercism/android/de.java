// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;


// Referenced classes of package crittercism.android:
//            bm, aw

public final class de
    implements bm
{

    public de(boolean flag)
    {
        a = flag;
    }

    public final void a(aw aw1, String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        aw1.a(s, s1, Boolean.valueOf(a).booleanValue());
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final Object b()
    {
        return Boolean.valueOf(a);
    }

    public boolean a;
}
