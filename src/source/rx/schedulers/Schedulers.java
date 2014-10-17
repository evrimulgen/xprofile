// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Scheduler;
import rx.plugins.RxJavaDefaultSchedulers;
import rx.plugins.RxJavaPlugins;

// Referenced classes of package rx.schedulers:
//            NewThreadScheduler, TrampolineScheduler, ExecutorScheduler, ImmediateScheduler, 
//            TestScheduler

public class Schedulers
{

    private Schedulers()
    {
        Scheduler scheduler = RxJavaPlugins.getInstance().getDefaultSchedulers().getComputationScheduler();
        Scheduler scheduler1;
        Scheduler scheduler2;
        if(scheduler != null)
            computationScheduler = scheduler;
        else
            computationScheduler = executor(createComputationExecutor());
        scheduler1 = RxJavaPlugins.getInstance().getDefaultSchedulers().getIOScheduler();
        if(scheduler1 != null)
            ioScheduler = scheduler1;
        else
            ioScheduler = executor(createIOExecutor());
        scheduler2 = RxJavaPlugins.getInstance().getDefaultSchedulers().getNewThreadScheduler();
        if(scheduler2 != null)
        {
            newThreadScheduler = scheduler2;
            return;
        } else
        {
            newThreadScheduler = NewThreadScheduler.instance();
            return;
        }
    }

    public static Scheduler computation()
    {
        return INSTANCE.computationScheduler;
    }

    private static ScheduledExecutorService createComputationExecutor()
    {
        return Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {

            public Thread newThread(Runnable runnable)
            {
                Thread thread = new Thread(runnable, (new StringBuilder()).append("RxComputationThreadPool-").append(counter.incrementAndGet()).toString());
                thread.setDaemon(true);
                return thread;
            }

            final AtomicInteger counter = new AtomicInteger();

        }
);
    }

    private static Executor createIOExecutor()
    {
        return Executors.newCachedThreadPool(new ThreadFactory() {

            public Thread newThread(Runnable runnable)
            {
                Thread thread = new Thread(runnable, (new StringBuilder()).append("RxIOThreadPool-").append(counter.incrementAndGet()).toString());
                thread.setDaemon(true);
                return thread;
            }

            final AtomicLong counter = new AtomicLong();

        }
);
    }

    public static Scheduler currentThread()
    {
        return TrampolineScheduler.instance();
    }

    public static Scheduler executor(Executor executor1)
    {
        return new ExecutorScheduler(executor1);
    }

    public static Scheduler executor(ScheduledExecutorService scheduledexecutorservice)
    {
        return new ExecutorScheduler(scheduledexecutorservice);
    }

    public static Scheduler immediate()
    {
        return ImmediateScheduler.instance();
    }

    public static Scheduler io()
    {
        return INSTANCE.ioScheduler;
    }

    public static Scheduler newThread()
    {
        return INSTANCE.newThreadScheduler;
    }

    public static TestScheduler test()
    {
        return new TestScheduler();
    }

    public static Scheduler threadPoolForComputation()
    {
        return computation();
    }

    public static Scheduler threadPoolForIO()
    {
        return io();
    }

    public static Scheduler trampoline()
    {
        return TrampolineScheduler.instance();
    }

    private static final Schedulers INSTANCE = new Schedulers();
    private final Scheduler computationScheduler;
    private final Scheduler ioScheduler;
    private final Scheduler newThreadScheduler;

}
