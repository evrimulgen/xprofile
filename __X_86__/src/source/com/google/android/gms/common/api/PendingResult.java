// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.common.api:
//            Result, ResultCallback, Status

public interface PendingResult
{
    public static interface a
    {

        public abstract void k(Status status);
    }


    public abstract Result await();

    public abstract Result await(long l, TimeUnit timeunit);

    public abstract void setResultCallback(ResultCallback resultcallback);

    public abstract void setResultCallback(ResultCallback resultcallback, long l, TimeUnit timeunit);
}
