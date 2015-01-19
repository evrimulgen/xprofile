// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class MainThreadExecutor
    implements Executor
{

    public MainThreadExecutor()
    {
    }

    public void execute(Runnable runnable)
    {
        handler.post(runnable);
    }

    private final Handler handler = new Handler(Looper.getMainLooper());
}
