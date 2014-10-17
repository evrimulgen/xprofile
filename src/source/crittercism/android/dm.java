// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;


// Referenced classes of package crittercism.android:
//            cq

public final class dm extends Thread
{

    public dm(Runnable runnable)
    {
        super(new cq(runnable));
    }

    public dm(Runnable runnable, String s)
    {
        super(new cq(runnable), s);
    }
}
