// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observables;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.operators.*;

public class BlockingObservable
{

    private BlockingObservable(Observable observable)
    {
        o = observable;
    }

    public static BlockingObservable from(Observable observable)
    {
        return new BlockingObservable(observable);
    }

    public Object first()
    {
        return from(o.first()).single();
    }

    public Object first(Func1 func1)
    {
        return from(o.first(func1)).single();
    }

    public Object firstOrDefault(Object obj)
    {
        return from(o.take(1)).singleOrDefault(obj);
    }

    public Object firstOrDefault(Object obj, Func1 func1)
    {
        return from(o.filter(func1)).firstOrDefault(obj);
    }

    public void forEach(final Action1 onNext)
    {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference exceptionFromOnError = new AtomicReference();
        o.subscribe(new Subscriber() {

            public void onCompleted()
            {
                latch.countDown();
            }

            public void onError(Throwable throwable)
            {
                exceptionFromOnError.set(throwable);
                latch.countDown();
            }

            public void onNext(Object obj)
            {
                onNext.call(obj);
            }

            final BlockingObservable this$0;
            final AtomicReference val$exceptionFromOnError;
            final CountDownLatch val$latch;
            final Action1 val$onNext;

            
            {
                this$0 = BlockingObservable.this;
                latch = countdownlatch;
                exceptionFromOnError = atomicreference;
                onNext = action1;
                super();
            }
        }
);
        try
        {
            latch.await();
        }
        catch(InterruptedException interruptedexception)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for subscription to complete.", interruptedexception);
        }
        if(exceptionFromOnError.get() != null)
        {
            if(exceptionFromOnError.get() instanceof RuntimeException)
                throw (RuntimeException)exceptionFromOnError.get();
            else
                throw new RuntimeException((Throwable)exceptionFromOnError.get());
        } else
        {
            return;
        }
    }

    public Iterator getIterator()
    {
        return BlockingOperatorToIterator.toIterator(o);
    }

    public Object last()
    {
        return from(o.last()).single();
    }

    public Object last(Func1 func1)
    {
        return from(o.last(func1)).single();
    }

    public Object lastOrDefault(Object obj)
    {
        return from(o.takeLast(1)).singleOrDefault(obj);
    }

    public Object lastOrDefault(Object obj, Func1 func1)
    {
        return from(o.filter(func1)).lastOrDefault(obj);
    }

    public Iterable latest()
    {
        return BlockingOperatorLatest.latest(o);
    }

    public Iterable mostRecent(Object obj)
    {
        return BlockingOperatorMostRecent.mostRecent(o, obj);
    }

    public Iterable next()
    {
        return BlockingOperatorNext.next(o);
    }

    public Object single()
    {
        return from(o.single()).toIterable().iterator().next();
    }

    public Object single(Func1 func1)
    {
        return from(o.single(func1)).toIterable().iterator().next();
    }

    public Object singleOrDefault(Object obj)
    {
        Iterator iterator = toIterable().iterator();
        if(!iterator.hasNext())
            return obj;
        Object obj1 = iterator.next();
        if(iterator.hasNext())
            throw new IllegalArgumentException("Sequence contains too many elements");
        else
            return obj1;
    }

    public Object singleOrDefault(Object obj, Func1 func1)
    {
        return from(o.filter(func1)).singleOrDefault(obj);
    }

    public Future toFuture()
    {
        return BlockingOperatorToFuture.toFuture(o);
    }

    public Iterable toIterable()
    {
        return new Iterable() {

            public Iterator iterator()
            {
                return getIterator();
            }

            final BlockingObservable this$0;

            
            {
                this$0 = BlockingObservable.this;
                super();
            }
        }
;
    }

    private final Observable o;
}
