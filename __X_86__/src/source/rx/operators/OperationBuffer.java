// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.*;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

// Referenced classes of package rx.operators:
//            ChunkedOperation

public final class OperationBuffer extends ChunkedOperation
{
    protected static class Buffer extends ChunkedOperation.Chunk
    {

        public volatile Object getContents()
        {
            return getContents();
        }

        public List getContents()
        {
            return contents;
        }

        protected Buffer()
        {
        }
    }

    private static final class BufferWithObservableBoundary
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            SourceObserver sourceobserver = new SourceObserver(observer, initialCapacity, compositesubscription);
            compositesubscription.add(source.unsafeSubscribe(sourceobserver));
            compositesubscription.add(boundary.unsafeSubscribe(new BoundaryObserver(sourceobserver)));
            return compositesubscription;
        }

        final Observable boundary;
        final int initialCapacity;
        final Observable source;

        public BufferWithObservableBoundary(Observable observable, Observable observable1, int i)
        {
            source = observable;
            boundary = observable1;
            initialCapacity = i;
        }
    }

    private static final class BufferWithObservableBoundary.BoundaryObserver extends Subscriber
    {

        public void onCompleted()
        {
            so.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            so.onError(throwable);
        }

        public void onNext(Object obj)
        {
            so.emitAndReplace();
        }

        final BufferWithObservableBoundary.SourceObserver so;

        public BufferWithObservableBoundary.BoundaryObserver(BufferWithObservableBoundary.SourceObserver sourceobserver)
        {
            so = sourceobserver;
        }
    }

    private static final class BufferWithObservableBoundary.SourceObserver extends Subscriber
    {

        void emitAndComplete()
        {
label0:
            {
                synchronized(guard)
                {
                    if(buffer != null)
                        break label0;
                }
                return;
            }
            List list;
            list = buffer;
            buffer = null;
            obj;
            JVM INSTR monitorexit ;
            observer.onNext(list);
            observer.onCompleted();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        void emitAndReplace()
        {
label0:
            {
                synchronized(guard)
                {
                    if(buffer != null)
                        break label0;
                }
                return;
            }
            List list;
            list = buffer;
            buffer = new ArrayList(initialCapacity);
            obj;
            JVM INSTR monitorexit ;
            observer.onNext(list);
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onCompleted()
        {
            emitAndComplete();
            cancel.unsubscribe();
        }

        public void onError(Throwable throwable)
        {
label0:
            {
                synchronized(guard)
                {
                    if(buffer != null)
                        break label0;
                }
                return;
            }
            buffer = null;
            obj;
            JVM INSTR monitorexit ;
            observer.onError(throwable);
            cancel.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
            synchronized(guard)
            {
                buffer.add(obj);
            }
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        List buffer;
        final Subscription cancel;
        final Object guard = new Object();
        final int initialCapacity;
        final Observer observer;

        public BufferWithObservableBoundary.SourceObserver(Observer observer1, int i, Subscription subscription)
        {
            observer = observer1;
            initialCapacity = i;
            cancel = subscription;
            buffer = new ArrayList(i);
        }
    }

    private static class ChunkToSubscription
        implements Subscription
    {

        public boolean isUnsubscribed()
        {
            return done.get();
        }

        public void unsubscribe()
        {
            if(done.compareAndSet(false, true))
            {
                ChunkedOperation.ChunkCreator chunkcreator = cc;
                cc = null;
                chunkcreator.stop();
            }
        }

        private ChunkedOperation.ChunkCreator cc;
        private final AtomicBoolean done = new AtomicBoolean();

        public ChunkToSubscription(ChunkedOperation.ChunkCreator chunkcreator)
        {
            cc = chunkcreator;
        }
    }


    public OperationBuffer()
    {
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, int i)
    {
        return buffer(observable, i, i);
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, int i, int j)
    {
        return new rx.Observable.OnSubscribeFunc(i, j, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.SizeBasedChunks sizebasedchunks = new ChunkedOperation.SizeBasedChunks(observer, OperationBuffer.bufferMaker(), count);
                ChunkedOperation.SkippingChunkCreator skippingchunkcreator = new ChunkedOperation.SkippingChunkCreator(sizebasedchunks, skip);
                Subscription asubscription[] = new Subscription[2];
                asubscription[0] = new ChunkToSubscription(skippingchunkcreator);
                asubscription[1] = source.unsafeSubscribe(new ChunkedOperation.ChunkObserver(sizebasedchunks, observer, skippingchunkcreator));
                return new CompositeSubscription(asubscription);
            }

            final int val$count;
            final int val$skip;
            final Observable val$source;

            
            {
                count = i;
                skip = j;
                source = observable;
                super();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, long l, long l1, TimeUnit timeunit)
    {
        return buffer(observable, l, l1, timeunit, Schedulers.threadPoolForComputation());
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, long l, long l1, TimeUnit timeunit, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(l, timeunit, scheduler, l1, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.TimeBasedChunks timebasedchunks = new ChunkedOperation.TimeBasedChunks(observer, OperationBuffer.bufferMaker(), timespan, unit, scheduler);
                ChunkedOperation.TimeBasedChunkCreator timebasedchunkcreator = new ChunkedOperation.TimeBasedChunkCreator(timebasedchunks, timeshift, unit, scheduler);
                Subscription asubscription[] = new Subscription[3];
                asubscription[0] = timebasedchunks;
                asubscription[1] = new ChunkToSubscription(timebasedchunkcreator);
                asubscription[2] = source.unsafeSubscribe(new ChunkedOperation.ChunkObserver(timebasedchunks, observer, timebasedchunkcreator));
                return new CompositeSubscription(asubscription);
            }

            final Scheduler val$scheduler;
            final Observable val$source;
            final long val$timeshift;
            final long val$timespan;
            final TimeUnit val$unit;

            
            {
                timespan = l;
                unit = timeunit;
                scheduler = scheduler1;
                timeshift = l1;
                source = observable;
                super();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, long l, TimeUnit timeunit)
    {
        return buffer(observable, l, timeunit, Schedulers.threadPoolForComputation());
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, long l, TimeUnit timeunit, int i)
    {
        return buffer(observable, l, timeunit, i, Schedulers.threadPoolForComputation());
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, long l, TimeUnit timeunit, int i, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(i, l, timeunit, scheduler, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.TimeAndSizeBasedChunks timeandsizebasedchunks = new ChunkedOperation.TimeAndSizeBasedChunks(observer, OperationBuffer.bufferMaker(), count, timespan, unit, scheduler);
                ChunkedOperation.SingleChunkCreator singlechunkcreator = new ChunkedOperation.SingleChunkCreator(timeandsizebasedchunks);
                Subscription asubscription[] = new Subscription[3];
                asubscription[0] = timeandsizebasedchunks;
                asubscription[1] = new ChunkToSubscription(singlechunkcreator);
                asubscription[2] = source.unsafeSubscribe(new ChunkedOperation.ChunkObserver(timeandsizebasedchunks, observer, singlechunkcreator));
                return new CompositeSubscription(asubscription);
            }

            final int val$count;
            final Scheduler val$scheduler;
            final Observable val$source;
            final long val$timespan;
            final TimeUnit val$unit;

            
            {
                count = i;
                timespan = l;
                unit = timeunit;
                scheduler = scheduler1;
                source = observable;
                super();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(l, timeunit, scheduler, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.NonOverlappingChunks nonoverlappingchunks = new ChunkedOperation.NonOverlappingChunks(observer, OperationBuffer.bufferMaker());
                ChunkedOperation.TimeBasedChunkCreator timebasedchunkcreator = new ChunkedOperation.TimeBasedChunkCreator(nonoverlappingchunks, timespan, unit, scheduler);
                Subscription asubscription[] = new Subscription[2];
                asubscription[0] = new ChunkToSubscription(timebasedchunkcreator);
                asubscription[1] = source.unsafeSubscribe(new ChunkedOperation.ChunkObserver(nonoverlappingchunks, observer, timebasedchunkcreator));
                return new CompositeSubscription(asubscription);
            }

            final Scheduler val$scheduler;
            final Observable val$source;
            final long val$timespan;
            final TimeUnit val$unit;

            
            {
                timespan = l;
                unit = timeunit;
                scheduler = scheduler1;
                source = observable;
                super();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, Observable observable1, Func1 func1)
    {
        return new rx.Observable.OnSubscribeFunc(observable1, func1, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.OverlappingChunks overlappingchunks = new ChunkedOperation.OverlappingChunks(observer, OperationBuffer.bufferMaker());
                ChunkedOperation.ObservableBasedMultiChunkCreator observablebasedmultichunkcreator = new ChunkedOperation.ObservableBasedMultiChunkCreator(overlappingchunks, bufferOpenings, bufferClosingSelector);
                Subscription asubscription[] = new Subscription[2];
                asubscription[0] = new ChunkToSubscription(observablebasedmultichunkcreator);
                asubscription[1] = source.unsafeSubscribe(new ChunkedOperation.ChunkObserver(overlappingchunks, observer, observablebasedmultichunkcreator));
                return new CompositeSubscription(asubscription);
            }

            final Func1 val$bufferClosingSelector;
            final Observable val$bufferOpenings;
            final Observable val$source;

            
            {
                bufferOpenings = observable;
                bufferClosingSelector = func1;
                source = observable1;
                super();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc buffer(Observable observable, Func0 func0)
    {
        return new rx.Observable.OnSubscribeFunc(func0, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.NonOverlappingChunks nonoverlappingchunks = new ChunkedOperation.NonOverlappingChunks(observer, OperationBuffer.bufferMaker());
                ChunkedOperation.ObservableBasedSingleChunkCreator observablebasedsinglechunkcreator = new ChunkedOperation.ObservableBasedSingleChunkCreator(nonoverlappingchunks, bufferClosingSelector);
                Subscription asubscription[] = new Subscription[2];
                asubscription[0] = new ChunkToSubscription(observablebasedsinglechunkcreator);
                asubscription[1] = source.unsafeSubscribe(new ChunkedOperation.ChunkObserver(nonoverlappingchunks, observer, observablebasedsinglechunkcreator));
                return new CompositeSubscription(asubscription);
            }

            final Func0 val$bufferClosingSelector;
            final Observable val$source;

            
            {
                bufferClosingSelector = func0;
                source = observable;
                super();
            }
        }
;
    }

    private static Func0 bufferMaker()
    {
        return new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public Buffer call()
            {
                return new Buffer();
            }

        }
;
    }

    public static rx.Observable.OnSubscribeFunc bufferWithBoundaryObservable(Observable observable, Observable observable1)
    {
        return new BufferWithObservableBoundary(observable, observable1, 16);
    }

    public static rx.Observable.OnSubscribeFunc bufferWithBoundaryObservable(Observable observable, Observable observable1, int i)
    {
        if(i <= 0)
            throw new IllegalArgumentException("initialCapacity > 0 required");
        else
            return new BufferWithObservableBoundary(observable, observable1, i);
    }

}
