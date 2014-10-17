// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.concurrent.TimeUnit;
import rx.*;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            ChunkedOperation

public final class OperationWindow extends ChunkedOperation
{
    protected static class Window extends ChunkedOperation.Chunk
    {

        public volatile Object getContents()
        {
            return getContents();
        }

        public Observable getContents()
        {
            return Observable.from(contents);
        }

        protected Window()
        {
        }
    }

    private static final class WindowViaObservable
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            CompositeSubscription compositesubscription = new CompositeSubscription();
            SourceObserver sourceobserver = new SourceObserver(observer, compositesubscription);
            try
            {
                observer.onNext(sourceobserver.subject);
            }
            catch(Throwable throwable)
            {
                observer.onError(throwable);
                return Subscriptions.empty();
            }
            compositesubscription.add(source.unsafeSubscribe(sourceobserver));
            if(!compositesubscription.isUnsubscribed())
                compositesubscription.add(boundary.unsafeSubscribe(new BoundaryObserver(sourceobserver)));
            return compositesubscription;
        }

        final Observable boundary;
        final Observable source;

        public WindowViaObservable(Observable observable, Observable observable1)
        {
            source = observable;
            boundary = observable1;
        }
    }

    private static final class WindowViaObservable.BoundaryObserver extends Subscriber
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
            so.replace();
        }

        final WindowViaObservable.SourceObserver so;

        public WindowViaObservable.BoundaryObserver(WindowViaObservable.SourceObserver sourceobserver)
        {
            so = sourceobserver;
        }
    }

    private static final class WindowViaObservable.SourceObserver extends Subscriber
    {

        Subject create()
        {
            return PublishSubject.create();
        }

        public void onCompleted()
        {
label0:
            {
                synchronized(guard)
                {
                    if(subject != null)
                        break label0;
                }
                return;
            }
            Subject subject1 = subject;
            subject = null;
            subject1.onCompleted();
            observer.onCompleted();
            obj;
            JVM INSTR monitorexit ;
            cancel.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onError(Throwable throwable)
        {
label0:
            {
                synchronized(guard)
                {
                    if(subject != null)
                        break label0;
                }
                return;
            }
            Subject subject1 = subject;
            subject = null;
            subject1.onError(throwable);
            observer.onError(throwable);
            obj;
            JVM INSTR monitorexit ;
            cancel.unsubscribe();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onNext(Object obj)
        {
label0:
            {
                synchronized(guard)
                {
                    if(subject != null)
                        break label0;
                }
                return;
            }
            subject.onNext(obj);
            obj1;
            JVM INSTR monitorexit ;
            return;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void replace()
        {
label0:
            {
                synchronized(guard)
                {
                    if(subject != null)
                        break label0;
                }
                return;
            }
            subject.onCompleted();
            subject = create();
            observer.onNext(subject);
            obj;
            JVM INSTR monitorexit ;
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            try
            {
                throw exception;
            }
            catch(Throwable throwable)
            {
                onError(throwable);
            }
            return;
        }

        final Subscription cancel;
        final Object guard = new Object();
        final Observer observer;
        Subject subject;

        public WindowViaObservable.SourceObserver(Observer observer1, Subscription subscription)
        {
            observer = observer1;
            cancel = subscription;
            subject = create();
        }
    }


    public OperationWindow()
    {
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, int i)
    {
        return window(observable, i, i);
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, int i, int j)
    {
        return new rx.Observable.OnSubscribeFunc(i, j, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.SizeBasedChunks sizebasedchunks = new SizeBasedChunks(observer, OperationWindow.windowMaker(), count);
                ChunkedOperation.SkippingChunkCreator skippingchunkcreator = new SkippingChunkCreator(sizebasedchunks, skip);
                return source.unsafeSubscribe(new ChunkObserver(sizebasedchunks, observer, skippingchunkcreator));
            }

            final int val$count;
            final int val$skip;
            final Observable val$source;

            
            {
                count = i;
                skip = j;
                source = observable;
                Object();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, long l, long l1, TimeUnit timeunit)
    {
        return window(observable, l, l1, timeunit, Schedulers.threadPoolForComputation());
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, long l, long l1, TimeUnit timeunit, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(l, timeunit, scheduler, l1, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.TimeBasedChunks timebasedchunks = new TimeBasedChunks(observer, OperationWindow.windowMaker(), timespan, unit, scheduler);
                ChunkedOperation.TimeBasedChunkCreator timebasedchunkcreator = new TimeBasedChunkCreator(timebasedchunks, timeshift, unit, scheduler);
                return source.unsafeSubscribe(new ChunkObserver(timebasedchunks, observer, timebasedchunkcreator));
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
                Object();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, long l, TimeUnit timeunit)
    {
        return window(observable, l, timeunit, Schedulers.threadPoolForComputation());
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, long l, TimeUnit timeunit, int i)
    {
        return window(observable, l, timeunit, i, Schedulers.threadPoolForComputation());
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, long l, TimeUnit timeunit, int i, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(i, l, timeunit, scheduler, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.TimeAndSizeBasedChunks timeandsizebasedchunks = new TimeAndSizeBasedChunks(observer, OperationWindow.windowMaker(), count, timespan, unit, scheduler);
                ChunkedOperation.SingleChunkCreator singlechunkcreator = new SingleChunkCreator(timeandsizebasedchunks);
                return source.unsafeSubscribe(new ChunkObserver(timeandsizebasedchunks, observer, singlechunkcreator));
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
                Object();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return new rx.Observable.OnSubscribeFunc(l, timeunit, scheduler, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.NonOverlappingChunks nonoverlappingchunks = new NonOverlappingChunks(observer, OperationWindow.windowMaker());
                ChunkedOperation.TimeBasedChunkCreator timebasedchunkcreator = new TimeBasedChunkCreator(nonoverlappingchunks, timespan, unit, scheduler);
                return source.unsafeSubscribe(new ChunkObserver(nonoverlappingchunks, observer, timebasedchunkcreator));
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
                Object();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, Observable observable1)
    {
        return new WindowViaObservable(observable, observable1);
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, Observable observable1, Func1 func1)
    {
        return new rx.Observable.OnSubscribeFunc(observable1, func1, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.OverlappingChunks overlappingchunks = new OverlappingChunks(observer, OperationWindow.windowMaker());
                ChunkedOperation.ObservableBasedMultiChunkCreator observablebasedmultichunkcreator = new ObservableBasedMultiChunkCreator(overlappingchunks, windowOpenings, windowClosingSelector);
                return source.unsafeSubscribe(new ChunkObserver(overlappingchunks, observer, observablebasedmultichunkcreator));
            }

            final Observable val$source;
            final Func1 val$windowClosingSelector;
            final Observable val$windowOpenings;

            
            {
                windowOpenings = observable;
                windowClosingSelector = func1;
                source = observable1;
                Object();
            }
        }
;
    }

    public static rx.Observable.OnSubscribeFunc window(Observable observable, Func0 func0)
    {
        return new rx.Observable.OnSubscribeFunc(func0, observable) {

            public Subscription onSubscribe(Observer observer)
            {
                ChunkedOperation.NonOverlappingChunks nonoverlappingchunks = new NonOverlappingChunks(observer, OperationWindow.windowMaker());
                ChunkedOperation.ObservableBasedSingleChunkCreator observablebasedsinglechunkcreator = new ObservableBasedSingleChunkCreator(nonoverlappingchunks, windowClosingSelector);
                return source.unsafeSubscribe(new ChunkObserver(nonoverlappingchunks, observer, observablebasedsinglechunkcreator));
            }

            final Observable val$source;
            final Func0 val$windowClosingSelector;

            
            {
                windowClosingSelector = func0;
                source = observable;
                Object();
            }
        }
;
    }

    public static Func0 windowMaker()
    {
        return new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public Window call()
            {
                return new Window();
            }

        }
;
    }
}
