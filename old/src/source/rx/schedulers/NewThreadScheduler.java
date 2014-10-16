// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.schedulers:
//            GenericScheduledExecutorService

public class NewThreadScheduler extends Scheduler
{
    private class EventLoopScheduler extends rx.Scheduler.Inner
        implements Subscription
    {

        public boolean isUnsubscribed()
        {
            return innerSubscription.isUnsubscribed();
        }

        public void schedule(final Action1 action)
        {
            if(innerSubscription.isUnsubscribed())
            {
                return;
            } else
            {
                AtomicReference atomicreference = new AtomicReference();
                Subscription subscription = Subscriptions.from(executor.submit(atomicreference. new Runnable() {

                    public void run()
                    {
                        boolean flag = innerSubscription.isUnsubscribed();
                        if(!flag) goto _L2; else goto _L1
_L1:
                        Subscription subscription2 = (Subscription)sf.get();
                        if(subscription2 != null)
                            innerSubscription.remove(subscription2);
_L4:
                        return;
_L2:
                        action.call(_inner);
                        Subscription subscription1 = (Subscription)sf.get();
                        if(subscription1 == null) goto _L4; else goto _L3
_L3:
                        innerSubscription.remove(subscription1);
                        return;
                        Exception exception;
                        exception;
                        Subscription subscription = (Subscription)sf.get();
                        if(subscription != null)
                            innerSubscription.remove(subscription);
                        throw exception;
                    }

                    final EventLoopScheduler this$1;
                    final Action1 val$action;
                    final AtomicReference val$sf;

            
            {
                this$1 = final_eventloopscheduler;
                action = action1;
                sf = AtomicReference.this;
                super();
            }
                }
));
                atomicreference.set(subscription);
                innerSubscription.add(subscription);
                return;
            }
        }

        public void schedule(final Action1 action, long l, TimeUnit timeunit)
        {
            AtomicReference atomicreference = new AtomicReference();
            Subscription subscription = Subscriptions.from(GenericScheduledExecutorService.getInstance().schedule(atomicreference. new Runnable() {

                public void run()
                {
                    boolean flag = innerSubscription.isUnsubscribed();
                    if(!flag) goto _L2; else goto _L1
_L1:
                    Subscription subscription2 = (Subscription)sf.get();
                    if(subscription2 != null)
                        innerSubscription.remove(subscription2);
_L4:
                    return;
_L2:
                    schedule(action);
                    Subscription subscription1 = (Subscription)sf.get();
                    if(subscription1 == null) goto _L4; else goto _L3
_L3:
                    innerSubscription.remove(subscription1);
                    return;
                    Exception exception;
                    exception;
                    Subscription subscription = (Subscription)sf.get();
                    if(subscription != null)
                        innerSubscription.remove(subscription);
                    throw exception;
                }

                final EventLoopScheduler this$1;
                final Action1 val$action;
                final AtomicReference val$sf;

            
            {
                this$1 = final_eventloopscheduler;
                action = action1;
                sf = AtomicReference.this;
                super();
            }
            }
, l, timeunit));
            atomicreference.set(subscription);
            innerSubscription.add(subscription);
        }

        public void unsubscribe()
        {
            innerSubscription.unsubscribe();
        }

        private final rx.Scheduler.Inner _inner;
        private final ExecutorService executor;
        private final CompositeSubscription innerSubscription;
        final NewThreadScheduler this$0;



        private EventLoopScheduler()
        {
            this$0 = NewThreadScheduler.this;
            super();
            innerSubscription = new CompositeSubscription();
            _inner = this;
            executor = Executors.newSingleThreadExecutor(NewThreadScheduler.THREAD_FACTORY);
        }

    }


    private NewThreadScheduler()
    {
    }

    public static NewThreadScheduler getInstance()
    {
        return INSTANCE;
    }

    static NewThreadScheduler instance()
    {
        return INSTANCE;
    }

    public Subscription schedule(Action1 action1)
    {
        EventLoopScheduler eventloopscheduler = new EventLoopScheduler();
        eventloopscheduler.schedule(action1);
        return eventloopscheduler.innerSubscription;
    }

    public Subscription schedule(Action1 action1, long l, TimeUnit timeunit)
    {
        EventLoopScheduler eventloopscheduler = new EventLoopScheduler();
        eventloopscheduler.schedule(action1, l, timeunit);
        return eventloopscheduler.innerSubscription;
    }

    private static final NewThreadScheduler INSTANCE = new NewThreadScheduler();
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {

        public Thread newThread(Runnable runnable)
        {
            Thread thread = new Thread(runnable, (new StringBuilder()).append("RxNewThreadScheduler-").append(NewThreadScheduler.count.incrementAndGet()).toString());
            thread.setDaemon(true);
            return thread;
        }

    }
;
    private static final AtomicLong count = new AtomicLong();



}
