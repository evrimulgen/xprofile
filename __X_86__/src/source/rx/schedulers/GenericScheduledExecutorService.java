// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class GenericScheduledExecutorService
{

    private GenericScheduledExecutorService()
    {
        int i = Runtime.getRuntime().availableProcessors();
        if(i > 8)
            i /= 2;
        if(i > 8)
            i = 8;
        executor = Executors.newScheduledThreadPool(i, new ThreadFactory() {

            public Thread newThread(Runnable runnable)
            {
                Thread thread = new Thread(runnable, (new StringBuilder()).append("RxScheduledExecutorPool-").append(counter.incrementAndGet()).toString());
                thread.setDaemon(true);
                return thread;
            }

            final AtomicInteger counter = new AtomicInteger();
            final GenericScheduledExecutorService this$0;

            
            {
                this$0 = GenericScheduledExecutorService.this;
                super();
            }
        }
);
    }

    public static ScheduledExecutorService getInstance()
    {
        return INSTANCE.executor;
    }

    private static final GenericScheduledExecutorService INSTANCE = new GenericScheduledExecutorService();
    private final ScheduledExecutorService executor;

}
