// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observers;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import rx.Observer;
import rx.Subscriber;

// Referenced classes of package rx.observers:
//            TestObserver

public class TestSubscriber extends Subscriber
{

    public TestSubscriber()
    {
        latch = new CountDownLatch(1);
        testObserver = new TestObserver(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
            }

            public void onNext(Object obj)
            {
            }

            final TestSubscriber this$0;

            
            {
                this$0 = TestSubscriber.this;
                super();
            }
        }
);
    }

    public TestSubscriber(Observer observer)
    {
        latch = new CountDownLatch(1);
        testObserver = new TestObserver(observer);
    }

    public TestSubscriber(Subscriber subscriber)
    {
        latch = new CountDownLatch(1);
        testObserver = new TestObserver(subscriber);
    }

    public void assertReceivedOnNext(List list)
    {
        testObserver.assertReceivedOnNext(list);
    }

    public void assertTerminalEvent()
    {
        testObserver.assertTerminalEvent();
    }

    public void assertUnsubscribed()
    {
        if(!isUnsubscribed())
            throw new AssertionError("Not unsubscribed.");
        else
            return;
    }

    public void awaitTerminalEvent()
    {
        try
        {
            latch.await();
            return;
        }
        catch(InterruptedException interruptedexception)
        {
            throw new RuntimeException("Interrupted", interruptedexception);
        }
    }

    public void awaitTerminalEvent(long l, TimeUnit timeunit)
    {
        try
        {
            latch.await(l, timeunit);
            return;
        }
        catch(InterruptedException interruptedexception)
        {
            throw new RuntimeException("Interrupted", interruptedexception);
        }
    }

    public void awaitTerminalEventAndUnsubscribeOnTimeout(long l, TimeUnit timeunit)
    {
        try
        {
            awaitTerminalEvent(l, timeunit);
            return;
        }
        catch(RuntimeException runtimeexception)
        {
            unsubscribe();
        }
    }

    public Thread getLastSeenThread()
    {
        return lastSeenThread;
    }

    public List getOnCompletedEvents()
    {
        return testObserver.getOnCompletedEvents();
    }

    public List getOnErrorEvents()
    {
        return testObserver.getOnErrorEvents();
    }

    public List getOnNextEvents()
    {
        return testObserver.getOnNextEvents();
    }

    public void onCompleted()
    {
        lastSeenThread = Thread.currentThread();
        testObserver.onCompleted();
        latch.countDown();
        return;
        Exception exception;
        exception;
        latch.countDown();
        throw exception;
    }

    public void onError(Throwable throwable)
    {
        lastSeenThread = Thread.currentThread();
        testObserver.onError(throwable);
        latch.countDown();
        return;
        Exception exception;
        exception;
        latch.countDown();
        throw exception;
    }

    public void onNext(Object obj)
    {
        lastSeenThread = Thread.currentThread();
        testObserver.onNext(obj);
    }

    private volatile Thread lastSeenThread;
    private final CountDownLatch latch;
    private final TestObserver testObserver;
}
