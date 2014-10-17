// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import rx.Scheduler;

// Referenced classes of package rx.android.schedulers:
//            HandlerThreadScheduler

public class AndroidSchedulers
{

    private AndroidSchedulers()
    {
    }

    public static Scheduler handlerThread(Handler handler)
    {
        return new HandlerThreadScheduler(handler);
    }

    public static Scheduler mainThread()
    {
        return MAIN_THREAD_SCHEDULER;
    }

    private static final Scheduler MAIN_THREAD_SCHEDULER = new HandlerThreadScheduler(new Handler(Looper.getMainLooper()));

}
