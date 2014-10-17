// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.*;

public class BufferUntilSubscriber extends Observable
    implements Observer
{
    private static class BufferedObserver extends Subscriber
    {

        public void onCompleted()
        {
            buffer.add(BufferUntilSubscriber.COMPLETE_SENTINEL);
        }

        public void onError(Throwable throwable)
        {
            buffer.add(new ErrorSentinel(throwable));
        }

        public void onNext(Object obj)
        {
            if(obj == null)
            {
                buffer.add(BufferUntilSubscriber.NULL_SENTINEL);
                return;
            } else
            {
                buffer.add(obj);
                return;
            }
        }

        private final ConcurrentLinkedQueue buffer;


        private BufferedObserver()
        {
            buffer = new ConcurrentLinkedQueue();
        }

    }

    private static class ErrorSentinel extends Sentinel
    {

        final Throwable e;

        ErrorSentinel(Throwable throwable)
        {
            e = throwable;
        }
    }

    private static class PassThruObserver extends Subscriber
    {

        private void drainIfNeededAndSwitchToActual()
        {
            do
            {
                Object obj = buffer.poll();
                if(obj != null)
                {
                    BufferUntilSubscriber.emit(this, obj);
                } else
                {
                    observerRef.set(actual);
                    return;
                }
            } while(true);
        }

        public void onCompleted()
        {
            drainIfNeededAndSwitchToActual();
            actual.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            drainIfNeededAndSwitchToActual();
            actual.onError(throwable);
        }

        public void onNext(Object obj)
        {
            drainIfNeededAndSwitchToActual();
            actual.onNext(obj);
        }

        private final Observer actual;
        private final ConcurrentLinkedQueue buffer;
        private final AtomicReference observerRef;

        PassThruObserver(Observer observer, ConcurrentLinkedQueue concurrentlinkedqueue, AtomicReference atomicreference)
        {
            actual = observer;
            buffer = concurrentlinkedqueue;
            observerRef = atomicreference;
        }
    }

    private static class Sentinel
    {

        private Sentinel()
        {
        }

    }


    private BufferUntilSubscriber(final AtomicReference observerRef)
    {
        super(new rx.Observable.OnSubscribe() {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                BufferedObserver bufferedobserver = (BufferedObserver)observerRef.get();
                do
                {
                    Object obj = bufferedobserver.buffer.poll();
                    if(obj != null)
                    {
                        BufferUntilSubscriber.emit(subscriber, obj);
                    } else
                    {
                        observerRef.set(new PassThruObserver(subscriber, bufferedobserver.buffer, observerRef));
                        return;
                    }
                } while(true);
            }

            final AtomicReference val$observerRef;

            
            {
                observerRef = atomicreference;
                super();
            }
        }
);
        this.observerRef = observerRef;
    }

    public static BufferUntilSubscriber create()
    {
        return new BufferUntilSubscriber(new AtomicReference(new BufferedObserver()));
    }

    private static final void emit(Observer observer, Object obj)
    {
        if(!(obj instanceof Sentinel))
            break MISSING_BLOCK_LABEL_57;
        if(obj != NULL_SENTINEL) goto _L2; else goto _L1
_L1:
        observer.onNext(null);
_L4:
        return;
_L2:
        if(obj == COMPLETE_SENTINEL)
        {
            observer.onCompleted();
            return;
        }
        if(!(obj instanceof ErrorSentinel)) goto _L4; else goto _L3
_L3:
        observer.onError(((ErrorSentinel)obj).e);
        return;
        observer.onNext(obj);
        return;
    }

    public void onCompleted()
    {
        ((Observer)observerRef.get()).onCompleted();
    }

    public void onError(Throwable throwable)
    {
        ((Observer)observerRef.get()).onError(throwable);
    }

    public void onNext(Object obj)
    {
        ((Observer)observerRef.get()).onNext(obj);
    }

    private static Sentinel COMPLETE_SENTINEL = new Sentinel();
    private static Sentinel NULL_SENTINEL = new Sentinel();
    private final AtomicReference observerRef;




}
