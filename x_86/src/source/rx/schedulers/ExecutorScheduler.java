// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;

import java.util.concurrent.*;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.schedulers:
//            GenericScheduledExecutorService

public class ExecutorScheduler extends Scheduler
{
    private class InnerExecutorScheduler extends rx.Scheduler.Inner
    {

        public boolean isUnsubscribed()
        {
            return innerSubscription.isUnsubscribed();
        }

        public void schedule(final Action1 action)
        {
            if(innerSubscription.isUnsubscribed())
                return;
            Runnable runnable = new Runnable() {

                public void run()
                {
                    if(innerSubscription.isUnsubscribed())
                    {
                        return;
                    } else
                    {
                        action.call(_inner);
                        return;
                    }
                }

                final InnerExecutorScheduler this$1;
                final rx.Scheduler.Inner val$_inner;
                final Action1 val$action;

            
            {
                this$1 = final_innerexecutorscheduler;
                action = action1;
                _inner = rx.Scheduler.Inner.this;
                super();
            }
            }
;
            if(executor instanceof ExecutorService)
            {
                java.util.concurrent.Future future = ((ExecutorService)executor).submit(runnable);
                innerSubscription.set(Subscriptions.from(future));
                return;
            } else
            {
                executor.execute(runnable);
                return;
            }
        }

        public void schedule(final Action1 action, long l, TimeUnit timeunit)
        {
            if(innerSubscription.isUnsubscribed())
                return;
            if(executor instanceof ScheduledExecutorService)
            {
                java.util.concurrent.ScheduledFuture scheduledfuture1 = ((ScheduledExecutorService)executor).schedule(new Runnable() {

                    public void run()
                    {
                        if(innerSubscription.isUnsubscribed())
                        {
                            return;
                        } else
                        {
                            action.call(_inner);
                            return;
                        }
                    }

                    final InnerExecutorScheduler this$1;
                    final rx.Scheduler.Inner val$_inner;
                    final Action1 val$action;

            
            {
                this$1 = final_innerexecutorscheduler;
                action = action1;
                _inner = rx.Scheduler.Inner.this;
                super();
            }
                }
, l, timeunit);
                innerSubscription.set(Subscriptions.from(scheduledfuture1));
                return;
            }
            if(l == 0L)
            {
                schedule(action);
                return;
            } else
            {
                java.util.concurrent.ScheduledFuture scheduledfuture = GenericScheduledExecutorService.getInstance().schedule(action. new Runnable() {

                    public void run()
                    {
                        if(innerSubscription.isUnsubscribed())
                        {
                            return;
                        } else
                        {
                            schedule(action);
                            return;
                        }
                    }

                    final InnerExecutorScheduler this$1;
                    final Action1 val$action;

            
            {
                this$1 = final_innerexecutorscheduler;
                action = Action1.this;
                super();
            }
                }
, l, timeunit);
                innerSubscription.set(Subscriptions.from(scheduledfuture));
                return;
            }
        }

        public void unsubscribe()
        {
            innerSubscription.unsubscribe();
        }

        private final MultipleAssignmentSubscription innerSubscription;
        final ExecutorScheduler this$0;


        private InnerExecutorScheduler()
        {
            this$0 = ExecutorScheduler.this;
            super();
            innerSubscription = new MultipleAssignmentSubscription();
        }

    }


    public ExecutorScheduler(Executor executor1)
    {
        executor = executor1;
    }

    public ExecutorScheduler(ScheduledExecutorService scheduledexecutorservice)
    {
        executor = scheduledexecutorservice;
    }

    public Subscription schedule(Action1 action1)
    {
        InnerExecutorScheduler innerexecutorscheduler = new InnerExecutorScheduler();
        innerexecutorscheduler.schedule(action1);
        return innerexecutorscheduler.innerSubscription;
    }

    public Subscription schedule(Action1 action1, long l, TimeUnit timeunit)
    {
        InnerExecutorScheduler innerexecutorscheduler = new InnerExecutorScheduler();
        innerexecutorscheduler.schedule(action1, l, timeunit);
        return innerexecutorscheduler.innerSubscription;
    }

    public Subscription schedulePeriodically(final Action1 action, long l, long l1, TimeUnit timeunit)
    {
        if(executor instanceof ScheduledExecutorService)
        {
            final InnerExecutorScheduler inner = new InnerExecutorScheduler();
            java.util.concurrent.ScheduledFuture scheduledfuture = ((ScheduledExecutorService)executor).scheduleAtFixedRate(new Runnable() {

                public void run()
                {
                    if(inner.isUnsubscribed())
                    {
                        return;
                    } else
                    {
                        action.call(inner);
                        return;
                    }
                }

                final ExecutorScheduler this$0;
                final Action1 val$action;
                final InnerExecutorScheduler val$inner;

            
            {
                this$0 = ExecutorScheduler.this;
                inner = innerexecutorscheduler;
                action = action1;
                super();
            }
            }
, l, l1, timeunit);
            inner.innerSubscription.set(Subscriptions.from(scheduledfuture));
            return inner;
        } else
        {
            return super.schedulePeriodically(action, l, l1, timeunit);
        }
    }

    private final Executor executor;

}
