// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import rx.*;
import rx.functions.*;
import rx.subscriptions.CompositeSubscription;

// Referenced classes of package rx.operators:
//            SafeObservableSubscription

public class ChunkedOperation
{
    protected static abstract class Chunk
    {

        public abstract Object getContents();

        public void pushValue(Object obj)
        {
            contents.add(obj);
        }

        public int size()
        {
            return contents.size();
        }

        protected final List contents = new ArrayList();

        protected Chunk()
        {
        }
    }

    protected static interface ChunkCreator
    {

        public abstract void onValuePushed();

        public abstract void stop();
    }

    protected static class ChunkObserver extends Subscriber
    {

        public void onCompleted()
        {
            creator.stop();
            chunks.emitAllChunks();
            observer.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            creator.stop();
            chunks.emitAllChunks();
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            creator.onValuePushed();
            chunks.pushValue(obj);
        }

        private final Chunks chunks;
        private final ChunkCreator creator;
        private final Observer observer;

        public ChunkObserver(Chunks chunks1, Observer observer1, ChunkCreator chunkcreator)
        {
            observer = observer1;
            creator = chunkcreator;
            chunks = chunks1;
        }
    }

    protected static class Chunks
    {

        public Chunk createChunk()
        {
            Chunk chunk = (Chunk)chunkMaker.call();
            chunks.add(chunk);
            return chunk;
        }

        public void emitAllChunks()
        {
            do
            {
                Chunk chunk = (Chunk)chunks.poll();
                if(chunk != null)
                    observer.onNext(chunk.getContents());
                else
                    return;
            } while(true);
        }

        public void emitChunk(Chunk chunk)
        {
            if(!chunks.remove(chunk))
            {
                return;
            } else
            {
                observer.onNext(chunk.getContents());
                return;
            }
        }

        public Chunk getChunk()
        {
            return (Chunk)chunks.peek();
        }

        public void pushValue(Object obj)
        {
            for(Iterator iterator = (new ArrayList(chunks)).iterator(); iterator.hasNext(); ((Chunk)iterator.next()).pushValue(obj));
        }

        private final Func0 chunkMaker;
        private final Queue chunks = new ConcurrentLinkedQueue();
        private final Observer observer;

        public Chunks(Observer observer1, Func0 func0)
        {
            observer = observer1;
            chunkMaker = func0;
        }
    }

    protected static class NonOverlappingChunks extends Chunks
    {

        public Chunk emitAndReplaceChunk()
        {
            Chunk chunk;
            synchronized(lock)
            {
                emitChunk(getChunk());
                chunk = createChunk();
            }
            return chunk;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void pushValue(Object obj)
        {
            synchronized(lock)
            {
                pushValue(obj);
            }
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private final Object lock = new Object();

        public NonOverlappingChunks(Observer observer, Func0 func0)
        {
            Chunks(observer, func0);
        }
    }

    protected static class ObservableBasedMultiChunkCreator
        implements ChunkCreator
    {

        public void onValuePushed()
        {
        }

        public void stop()
        {
            subscription.unsubscribe();
        }

        private final CompositeSubscription subscription = new CompositeSubscription();

        public ObservableBasedMultiChunkCreator(OverlappingChunks overlappingchunks, Observable observable, Func1 func1)
        {
            observable.unsafeSubscribe(overlappingchunks. new _cls1(func1));
        }
    }

    protected static class ObservableBasedSingleChunkCreator
        implements ChunkCreator
    {

        private void listenForChunkEnd()
        {
            ((Observable)chunkClosingSelector.call()).unsafeSubscribe(new Subscriber() {

                public void onCompleted()
                {
                }

                public void onError(Throwable throwable)
                {
                }

                public void onNext(Object obj)
                {
                    chunks.emitAndReplaceChunk();
                    listenForChunkEnd();
                }

                final ObservableBasedSingleChunkCreator this$0;

            
            {
                this$0 = ObservableBasedSingleChunkCreator.this;
                Subscriber();
            }
            }
);
        }

        public void onValuePushed()
        {
        }

        public void stop()
        {
            subscription.unsubscribe();
        }

        private final Func0 chunkClosingSelector;
        private final NonOverlappingChunks chunks;
        private final SafeObservableSubscription subscription = new SafeObservableSubscription();



        public ObservableBasedSingleChunkCreator(NonOverlappingChunks nonoverlappingchunks, Func0 func0)
        {
            chunks = nonoverlappingchunks;
            chunkClosingSelector = func0;
            nonoverlappingchunks.createChunk();
            listenForChunkEnd();
        }
    }

    protected static class OverlappingChunks extends Chunks
    {

        public OverlappingChunks(Observer observer, Func0 func0)
        {
            Chunks(observer, func0);
        }
    }

    protected static class SingleChunkCreator
        implements ChunkCreator
    {

        public void onValuePushed()
        {
        }

        public void stop()
        {
        }

        public SingleChunkCreator(Chunks chunks)
        {
            chunks.createChunk();
        }
    }

    protected static class SizeBasedChunks extends Chunks
    {

        public void pushValue(Object obj)
        {
            super.pushValue(obj);
            do
            {
                Chunk chunk = getChunk();
                if(chunk != null && chunk.size() >= size)
                    emitChunk(chunk);
                else
                    return;
            } while(true);
        }

        private final int size;

        public SizeBasedChunks(Observer observer, Func0 func0, int i)
        {
            Chunks(observer, func0);
            size = i;
        }
    }

    protected static class SkippingChunkCreator
        implements ChunkCreator
    {

        public void onValuePushed()
        {
            if(skipped.decrementAndGet() == 0)
            {
                skipped.set(skip);
                chunks.createChunk();
            }
        }

        public void stop()
        {
        }

        private final Chunks chunks;
        private final int skip;
        private final AtomicInteger skipped = new AtomicInteger(1);

        public SkippingChunkCreator(Chunks chunks1, int i)
        {
            chunks = chunks1;
            skip = i;
        }
    }

    protected static class TimeAndSizeBasedChunks extends Chunks
        implements Subscription
    {

        public Chunk createChunk()
        {
            Chunk chunk = super.createChunk();
            subscriptions.put(chunk, scheduler.schedule(chunk. new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    emitChunk(chunk);
                }

                final TimeAndSizeBasedChunks this$0;
                final Chunk val$chunk;

            
            {
                this$0 = final_timeandsizebasedchunks;
                chunk = Chunk.this;
                Object();
            }
            }
, maxTime, unit));
            return chunk;
        }

        public void emitChunk(Chunk chunk)
        {
            Subscription subscription = (Subscription)subscriptions.remove(chunk);
            if(subscription == null)
            {
                return;
            } else
            {
                super.emitChunk(chunk);
                subscription.unsubscribe();
                createChunk();
                return;
            }
        }

        public boolean isUnsubscribed()
        {
            return unsubscribed;
        }

        public void pushValue(Object obj)
        {
            super.pushValue(obj);
            do
            {
                Chunk chunk = getChunk();
                if(chunk != null && chunk.size() >= maxSize)
                    emitChunk(chunk);
                else
                    return;
            } while(true);
        }

        public void unsubscribe()
        {
            unsubscribed = true;
            for(Iterator iterator = subscriptions.values().iterator(); iterator.hasNext(); ((Subscription)iterator.next()).unsubscribe());
        }

        private final int maxSize;
        private final long maxTime;
        private final Scheduler scheduler;
        private final ConcurrentMap subscriptions = new ConcurrentHashMap();
        private final TimeUnit unit;
        private volatile boolean unsubscribed;

        public TimeAndSizeBasedChunks(Observer observer, Func0 func0, int i, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            Chunks(observer, func0);
            unsubscribed = false;
            maxSize = i;
            maxTime = l;
            unit = timeunit;
            scheduler = scheduler1;
        }
    }

    protected static class TimeBasedChunkCreator
        implements ChunkCreator
    {

        public void onValuePushed()
        {
        }

        public void stop()
        {
            subscription.unsubscribe();
        }

        private final SafeObservableSubscription subscription;

        public TimeBasedChunkCreator(NonOverlappingChunks nonoverlappingchunks, long l, TimeUnit timeunit, Scheduler scheduler)
        {
            subscription = new SafeObservableSubscription();
            subscription.wrap(scheduler.schedulePeriodically(nonoverlappingchunks. new _cls1(), 0L, l, timeunit));
        }

        public TimeBasedChunkCreator(OverlappingChunks overlappingchunks, long l, TimeUnit timeunit, Scheduler scheduler)
        {
            subscription = new SafeObservableSubscription();
            subscription.wrap(scheduler.schedulePeriodically(overlappingchunks. new _cls2(), 0L, l, timeunit));
        }
    }

    protected static class TimeBasedChunks extends OverlappingChunks
        implements Subscription
    {

        public Chunk createChunk()
        {
            Chunk chunk = super.createChunk();
            subscriptions.put(chunk, scheduler.schedule(chunk. new Action1() {

                public volatile void call(Object obj)
                {
                    call((rx.Scheduler.Inner)obj);
                }

                public void call(rx.Scheduler.Inner inner)
                {
                    emitChunk(chunk);
                }

                final TimeBasedChunks this$0;
                final Chunk val$chunk;

            
            {
                this$0 = final_timebasedchunks;
                chunk = Chunk.this;
                Object();
            }
            }
, time, unit));
            return chunk;
        }

        public void emitChunk(Chunk chunk)
        {
            subscriptions.remove(chunk);
            super.emitChunk(chunk);
        }

        public boolean isUnsubscribed()
        {
            return unsubscribed;
        }

        public void unsubscribe()
        {
            unsubscribed = true;
            for(Iterator iterator = subscriptions.values().iterator(); iterator.hasNext(); ((Subscription)iterator.next()).unsubscribe());
        }

        private final Scheduler scheduler;
        private final ConcurrentMap subscriptions = new ConcurrentHashMap();
        private final long time;
        private final TimeUnit unit;
        private volatile boolean unsubscribed;

        public TimeBasedChunks(Observer observer, Func0 func0, long l, TimeUnit timeunit, Scheduler scheduler1)
        {
            OverlappingChunks(observer, func0);
            unsubscribed = false;
            time = l;
            unit = timeunit;
            scheduler = scheduler1;
        }
    }


    public ChunkedOperation()
    {
    }

    // Unreferenced inner class rx/operators/ChunkedOperation$ObservableBasedMultiChunkCreator$1

/* anonymous class */
    class ObservableBasedMultiChunkCreator._cls1 extends Subscriber
    {

        public void onCompleted()
        {
        }

        public void onError(Throwable throwable)
        {
        }

        public void onNext(Object obj)
        {
            Chunk chunk = chunks.createChunk();
            ((Observable)chunkClosingSelector.call(obj)).unsafeSubscribe(chunk. new Subscriber() {

                public void onCompleted()
                {
                }

                public void onError(Throwable throwable)
                {
                }

                public void onNext(Object obj)
                {
    class ObservableBasedMultiChunkCreator._cls1 extends Subscriber
    {

        public void onCompleted()
        {
        }

        public void onError(Throwable throwable)
        {
        }

        public void onNext(Object obj)
        {
            Chunk chunk = chunks.createChunk();
            ((Observable)chunkClosingSelector.call(obj)).unsafeSubscribe(chunk. new Subscriber()                     chunks.emitChunk(chunk);
                }

                final ObservableBasedMultiChunkCreator._cls1 this$1;
                final Chunk val$chunk;

            
            {
                this$1 = final__pcls1;
                chunk = Chunk.this;
                Subscriber();
            }
            }
);
        }

        final ObservableBasedMultiChunkCreator this$0;
        final Func1 val$chunkClosingSelector;
        final OverlappingChunks val$chunks;

            
            {
                this$0 = final_observablebasedmultichunkcreator;
                chunks = OverlappingChunks.this;
                chunkClosingSelector = func1;
                Subscriber(final_compositesubscription);
            }
    }


    // Unreferenced inner class rx/operators/ChunkedOperation$TimeBasedChunkCreator$1

/* anonymous class */
    class TimeBasedChunkCreator._cls1
        implements Action1
    {

        public volatile void call(Object obj)
        {
            call((rx.Scheduler.Inner)obj);
        }

        public void call(rx.Scheduler.Inner inner)
        {
            chunks.emitAndReplaceChunk();
        }

        final TimeBasedChunkCreator this$0;
        final NonOverlappingChunks val$chunks;

            
            {
                this$0 = final_timebasedchunkcreator;
                chunks = NonOverlappingChunks.this;
                Object();
            }
    }


    // Unreferenced inner class rx/operators/ChunkedOperation$TimeBasedChunkCreator$2

/* anonymous class */
    class TimeBasedChunkCreator._cls2
        implements Action1
    {

        public volatile void call(Object obj)
        {
            call((rx.Scheduler.Inner)obj);
        }

        public void call(rx.Scheduler.Inner inner)
        {
            chunks.createChunk();
        }

        final TimeBasedChunkCreator this$0;
        final OverlappingChunks val$chunks;

            
            {
                this$0 = final_timebasedchunkcreator;
                chunks = OverlappingChunks.this;
                Object();
            }
    }

}
