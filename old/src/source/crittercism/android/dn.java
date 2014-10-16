// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import java.util.concurrent.ThreadFactory;

// Referenced classes of package crittercism.android:
//            dm

public final class dn
    implements ThreadFactory
{

    public dn()
    {
    }

    public final Thread newThread(Runnable runnable)
    {
        return new dm(runnable);
    }
}
