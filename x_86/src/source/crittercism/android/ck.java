// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;


public final class ck
{

    public ck()
    {
        a = 0L;
        b = 0xdf8475800L;
    }

    public final boolean a()
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        long l1;
        l = System.nanoTime() - a;
        l1 = b;
        boolean flag;
        if(l > l1)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public final void b()
    {
        this;
        JVM INSTR monitorenter ;
        a = System.nanoTime();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private long a;
    private long b;
}
