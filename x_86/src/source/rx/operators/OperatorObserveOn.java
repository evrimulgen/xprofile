// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.ImmediateScheduler;
import rx.schedulers.TrampolineScheduler;

// Referenced classes of package rx.operators:
//            NotificationLite

public class OperatorObserveOn
    implements rx.Observable.Operator
{
    private class ObserveOnSubscriber extends Subscriber
    {

        private void pollQueue()
        {
            do
            {
                Object obj = queue.poll();
                on.accept(observer, obj);
            } while(counter.decrementAndGet() > 0L);
        }

        public void onCompleted()
        {
            queue.offer(on.completed());
            schedule();
        }

        public void onError(Throwable throwable)
        {
            queue.offer(on.error(throwable));
            schedule();
        }

        public void onNext(Object obj)
        {
            queue.offer(on.next(obj));
            schedule();
        }

        protected void schedule()
        {
label0:
            {
                if(counter.getAndIncrement() == 0L)
                {
                    if(recursiveScheduler != null)
                        break label0;
                    add(scheduler.schedule(new Action1() {

                        public volatile void call(Object obj)
                        {
                            call((rx.Scheduler.Inner)obj);
                        }

                        public void call(rx.Scheduler.Inner inner)
                        {
                            recursiveScheduler = inner;
                            pollQueue();
                        }

                        final ObserveOnSubscriber this$1;

            
            {
                this$1 = ObserveOnSubscriber.this;
                super();
            }
                    }
));
                }
                return;
            }
            recursiveScheduler.schedule(new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    pollQueue();
                }

                final ObserveOnSubscriber this$1;

            
            {
                this$1 = ObserveOnSubscriber.this;
                super();
            }
            }
);
        }

        final AtomicLong counter = new AtomicLong(0L);
        final Subscriber observer;
        private final ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        private volatile rx.Scheduler.Inner recursiveScheduler;
        final OperatorObserveOn this$0;


/*
        static rx.Scheduler.Inner access$102(ObserveOnSubscriber observeonsubscriber, rx.Scheduler.Inner inner)
        {
            observeonsubscriber.recursiveScheduler = inner;
            return inner;
        }

*/


        public ObserveOnSubscriber(Subscriber subscriber)
        {
            this$0 = OperatorObserveOn.this;
            super(subscriber);
            observer = subscriber;
        }
    }


    public OperatorObserveOn(Scheduler scheduler1)
    {
        scheduler = scheduler1;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(Subscriber subscriber)
    {
        while((scheduler instanceof ImmediateScheduler) || (scheduler instanceof TrampolineScheduler)) 
            return subscriber;
        return new ObserveOnSubscriber(subscriber);
    }

    private final NotificationLite on = NotificationLite.instance();
    private final Scheduler scheduler;


}
