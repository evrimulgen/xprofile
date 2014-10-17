// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.android.observables;

import android.os.Looper;

public class Assertions
{

    public Assertions()
    {
    }

    public static void assertUiThread()
    {
        if(Looper.getMainLooper() != Looper.myLooper())
            throw new IllegalStateException((new StringBuilder()).append("Observers must subscribe from the main UI thread, but was ").append(Thread.currentThread()).toString());
        else
            return;
    }
}
