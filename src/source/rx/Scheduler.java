// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx;

import java.util.concurrent.TimeUnit;
import rx.functions.Action1;

// Referenced classes of package rx:
//            Subscription

public abstract class Scheduler
{
    public static abstract class Inner
        implements Subscription
    {

        public long now()
        {
            return System.currentTimeMillis();
        }

        public abstract void schedule(Action1 action1);

        public abstract void schedule(Action1 action1, long l, TimeUnit timeunit);

        public Inner()
        {
        }
    }

    public static final class Recurse
    {

        public final void schedule()
        {
            inner.schedule(new Action1() {

                public volatile void call(Object obj)
                {
                    call((Inner)obj);
                }

                public void call(Inner inner)
                {
                    action.call(self);
                }

                final Recurse this$0;
                final Recurse val$self;

            
            {
                this$0 = final_recurse;
                self = Recurse.this;
                super();
            }
            }
);
        }

        public final void schedule(long l, TimeUnit timeunit)
        {
            inner.schedule(new Action1() {

                public volatile void call(Object obj)
                {
                    call((Inner)obj);
                }

                public void call(Inner inner)
                {
                    action.call(self);
                }

                final Recurse this$0;
                final Recurse val$self;

            
            {
                this$0 = final_recurse;
                self = Recurse.this;
                super();
            }
            }
, l, timeunit);
        }

        private final Action1 action;
        private final Inner inner;


        private Recurse(Inner inner1, Action1 action1)
        {
            inner = inner1;
            action = action1;
        }

    }


    public Scheduler()
    {
    }

    public int degreeOfParallelism()
    {
        return Runtime.getRuntime().availableProcessors();
    }

    public long now()
    {
        return System.currentTimeMillis();
    }

    public abstract Subscription schedule(Action1 action1);

    public abstract Subscription schedule(Action1 action1, long l, TimeUnit timeunit);

    public Subscription schedulePeriodically(final Action1 action, long l, long l1, TimeUnit timeunit)
    {
        return schedule(new Action1() {

            public volatile void call(Object obj)
            {
                call((Inner)obj);
            }

            public void call(Inner inner)
            {
                if(!inner.isUnsubscribed())
                {
                    long l2 = now();
                    action.call(inner);
                    long l3 = TimeUnit.MILLISECONDS.toNanos(now() - l2);
                    inner.schedule(this, periodInNanos - l3, TimeUnit.NANOSECONDS);
                }
            }

            final Scheduler this$0;
            final Action1 val$action;
            final long val$periodInNanos;

            
            {
                this$0 = Scheduler.this;
                action = action1;
                periodInNanos = l;
                super();
            }
        }
, l, timeunit);
    }

    public final Subscription scheduleRecursive(final Action1 action)
    {
        return schedule(new Action1() {

            public volatile void call(Object obj)
            {
                call((Inner)obj);
            }

            public void call(Inner inner)
            {
                action.call(new Recurse(inner, action));
            }

            final Scheduler this$0;
            final Action1 val$action;

            
            {
                this$0 = Scheduler.this;
                action = action1;
                super();
            }
        }
);
    }
}
