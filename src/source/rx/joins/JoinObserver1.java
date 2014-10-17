// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.functions.Action1;
import rx.observers.SafeSubscriber;

// Referenced classes of package rx.joins:
//            JoinObserver, ActivePlan0

public final class JoinObserver1 extends Subscriber
    implements JoinObserver
{
    private final class InnerObserver extends Subscriber
    {

        public void onCompleted()
        {
        }

        public void onError(Throwable throwable)
        {
        }

        public volatile void onNext(Object obj)
        {
            onNext((Notification)obj);
        }

        public void onNext(Notification notification)
        {
label0:
            {
                synchronized(gate)
                {
                    if(isUnsubscribed())
                        break MISSING_BLOCK_LABEL_107;
                    if(!notification.isOnError())
                        break label0;
                    JoinObserver1.this.onError.call(notification.getThrowable());
                }
                return;
            }
            queue.add(notification);
            for(Iterator iterator = (new ArrayList(activePlans)).iterator(); iterator.hasNext(); ((ActivePlan0)iterator.next()).match());
            break MISSING_BLOCK_LABEL_107;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            obj;
            JVM INSTR monitorexit ;
        }

        final JoinObserver1 this$0;

        private InnerObserver()
        {
            this$0 = JoinObserver1.this;
            super();
        }

    }


    public JoinObserver1(Observable observable, Action1 action1)
    {
        source = observable;
        onError = action1;
        add(safeObserver);
    }

    public void addActivePlan(ActivePlan0 activeplan0)
    {
        activePlans.add(activeplan0);
    }

    public void dequeue()
    {
        queue.remove();
    }

    public void onCompleted()
    {
        safeObserver.onCompleted();
    }

    public void onError(Throwable throwable)
    {
        safeObserver.onError(throwable);
    }

    public volatile void onNext(Object obj)
    {
        onNext((Notification)obj);
    }

    public void onNext(Notification notification)
    {
        safeObserver.onNext(notification);
    }

    public Queue queue()
    {
        return queue;
    }

    void removeActivePlan(ActivePlan0 activeplan0)
    {
        activePlans.remove(activeplan0);
        if(activePlans.isEmpty())
            unsubscribe();
    }

    public void subscribe(Object obj)
    {
        if(subscribed.compareAndSet(false, true))
        {
            gate = obj;
            source.materialize().unsafeSubscribe(this);
            return;
        } else
        {
            throw new IllegalStateException("Can only be subscribed to once.");
        }
    }

    private final List activePlans = new ArrayList();
    private Object gate;
    private final Action1 onError;
    private final Queue queue = new LinkedList();
    private final SafeSubscriber safeObserver = new SafeSubscriber(new InnerObserver());
    private final Observable source;
    private final AtomicBoolean subscribed = new AtomicBoolean(false);




}
