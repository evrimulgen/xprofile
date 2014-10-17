// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.functions.FuncN;
import rx.functions.Function;
import rx.functions.Functions;
import rx.joins.Pattern2;
import rx.joins.Plan0;
import rx.observables.BlockingObservable;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;
import rx.operators.OnSubscribeFromIterable;
import rx.operators.OnSubscribeRange;
import rx.operators.OperationAll;
import rx.operators.OperationAny;
import rx.operators.OperationAsObservable;
import rx.operators.OperationAverage;
import rx.operators.OperationBuffer;
import rx.operators.OperationCombineLatest;
import rx.operators.OperationConcat;
import rx.operators.OperationDebounce;
import rx.operators.OperationDefaultIfEmpty;
import rx.operators.OperationDefer;
import rx.operators.OperationDelay;
import rx.operators.OperationDematerialize;
import rx.operators.OperationDistinct;
import rx.operators.OperationDistinctUntilChanged;
import rx.operators.OperationFinally;
import rx.operators.OperationFlatMap;
import rx.operators.OperationGroupByUntil;
import rx.operators.OperationGroupJoin;
import rx.operators.OperationInterval;
import rx.operators.OperationJoin;
import rx.operators.OperationJoinPatterns;
import rx.operators.OperationMergeDelayError;
import rx.operators.OperationMergeMaxConcurrent;
import rx.operators.OperationMinMax;
import rx.operators.OperationMulticast;
import rx.operators.OperationOnErrorResumeNextViaObservable;
import rx.operators.OperationOnErrorReturn;
import rx.operators.OperationOnExceptionResumeNextViaObservable;
import rx.operators.OperationParallelMerge;
import rx.operators.OperationReplay;
import rx.operators.OperationSample;
import rx.operators.OperationSequenceEqual;
import rx.operators.OperationSingle;
import rx.operators.OperationSkipLast;
import rx.operators.OperationSkipUntil;
import rx.operators.OperationSum;
import rx.operators.OperationSwitch;
import rx.operators.OperationTakeLast;
import rx.operators.OperationTakeUntil;
import rx.operators.OperationTakeWhile;
import rx.operators.OperationThrottleFirst;
import rx.operators.OperationTimeInterval;
import rx.operators.OperationToMap;
import rx.operators.OperationToMultimap;
import rx.operators.OperationToObservableFuture;
import rx.operators.OperationUsing;
import rx.operators.OperationWindow;
import rx.operators.OperatorAmb;
import rx.operators.OperatorCache;
import rx.operators.OperatorCast;
import rx.operators.OperatorDoOnEach;
import rx.operators.OperatorElementAt;
import rx.operators.OperatorFilter;
import rx.operators.OperatorGroupBy;
import rx.operators.OperatorMap;
import rx.operators.OperatorMaterialize;
import rx.operators.OperatorMerge;
import rx.operators.OperatorObserveOn;
import rx.operators.OperatorOnErrorFlatMap;
import rx.operators.OperatorOnErrorResumeNextViaFunction;
import rx.operators.OperatorParallel;
import rx.operators.OperatorPivot;
import rx.operators.OperatorRepeat;
import rx.operators.OperatorRetry;
import rx.operators.OperatorScan;
import rx.operators.OperatorSerialize;
import rx.operators.OperatorSkip;
import rx.operators.OperatorSkipWhile;
import rx.operators.OperatorSubscribeOn;
import rx.operators.OperatorSynchronize;
import rx.operators.OperatorTake;
import rx.operators.OperatorTimeout;
import rx.operators.OperatorTimeoutWithSelector;
import rx.operators.OperatorTimestamp;
import rx.operators.OperatorToObservableList;
import rx.operators.OperatorToObservableSortedList;
import rx.operators.OperatorUnsubscribeOn;
import rx.operators.OperatorZip;
import rx.operators.OperatorZipIterable;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx:
//            Subscriber, Scheduler, Observer, Subscription, 
//            Notification

public class Observable
{
    private static class NeverObservable extends Observable
    {

        public NeverObservable()
        {
            super(new _cls1());
        }
    }

    public static interface OnSubscribe
        extends Action1
    {
    }

    public static interface OnSubscribeFunc
        extends Function
    {

        public abstract Subscription onSubscribe(Observer observer);
    }

    public static interface Operator
        extends Func1
    {
    }

    private static class ThrowObservable extends Observable
    {

        public ThrowObservable(final Throwable exception)
        {
            super(new _cls1());
        }
    }


    protected Observable(OnSubscribe onsubscribe)
    {
        onSubscribe = onsubscribe;
    }

    public static final Observable amb(Iterable iterable)
    {
        return create(OperatorAmb.amb(iterable));
    }

    public static final Observable amb(Observable observable, Observable observable1)
    {
        return create(OperatorAmb.amb(observable, observable1));
    }

    public static final Observable amb(Observable observable, Observable observable1, Observable observable2)
    {
        return create(OperatorAmb.amb(observable, observable1, observable2));
    }

    public static final Observable amb(Observable observable, Observable observable1, Observable observable2, Observable observable3)
    {
        return create(OperatorAmb.amb(observable, observable1, observable2, observable3));
    }

    public static final Observable amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4)
    {
        return create(OperatorAmb.amb(observable, observable1, observable2, observable3, observable4));
    }

    public static final Observable amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5)
    {
        return create(OperatorAmb.amb(observable, observable1, observable2, observable3, observable4, observable5));
    }

    public static final Observable amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6)
    {
        return create(OperatorAmb.amb(observable, observable1, observable2, observable3, observable4, observable5, observable6));
    }

    public static final Observable amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7)
    {
        return create(OperatorAmb.amb(observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7));
    }

    public static final Observable amb(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Observable observable8)
    {
        return create(OperatorAmb.amb(observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7, observable8));
    }

    public static final Observable average(Observable observable)
    {
        return OperationAverage.average(observable);
    }

    public static final Observable averageDouble(Observable observable)
    {
        return OperationAverage.averageDoubles(observable);
    }

    public static final Observable averageFloat(Observable observable)
    {
        return OperationAverage.averageFloats(observable);
    }

    public static final Observable averageInteger(Observable observable)
    {
        return OperationAverage.average(observable);
    }

    public static final Observable averageLong(Observable observable)
    {
        return OperationAverage.averageLongs(observable);
    }

    public static final Observable combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Observable observable8, Func9 func9)
    {
        return create(OperationCombineLatest.combineLatest(observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7, observable8, func9));
    }

    public static final Observable combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Func8 func8)
    {
        return create(OperationCombineLatest.combineLatest(observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7, func8));
    }

    public static final Observable combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Func7 func7)
    {
        return create(OperationCombineLatest.combineLatest(observable, observable1, observable2, observable3, observable4, observable5, observable6, func7));
    }

    public static final Observable combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Func6 func6)
    {
        return create(OperationCombineLatest.combineLatest(observable, observable1, observable2, observable3, observable4, observable5, func6));
    }

    public static final Observable combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Func5 func5)
    {
        return create(OperationCombineLatest.combineLatest(observable, observable1, observable2, observable3, observable4, func5));
    }

    public static final Observable combineLatest(Observable observable, Observable observable1, Observable observable2, Observable observable3, Func4 func4)
    {
        return create(OperationCombineLatest.combineLatest(observable, observable1, observable2, observable3, func4));
    }

    public static final Observable combineLatest(Observable observable, Observable observable1, Observable observable2, Func3 func3)
    {
        return create(OperationCombineLatest.combineLatest(observable, observable1, observable2, func3));
    }

    public static final Observable combineLatest(Observable observable, Observable observable1, Func2 func2)
    {
        return create(OperationCombineLatest.combineLatest(observable, observable1, func2));
    }

    public static final Observable concat(Observable observable)
    {
        return create(OperationConcat.concat(observable));
    }

    public static final Observable concat(Observable observable, Observable observable1)
    {
        return create(OperationConcat.concat(new Observable[] {
            observable, observable1
        }));
    }

    public static final Observable concat(Observable observable, Observable observable1, Observable observable2)
    {
        return create(OperationConcat.concat(new Observable[] {
            observable, observable1, observable2
        }));
    }

    public static final Observable concat(Observable observable, Observable observable1, Observable observable2, Observable observable3)
    {
        return create(OperationConcat.concat(new Observable[] {
            observable, observable1, observable2, observable3
        }));
    }

    public static final Observable concat(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4)
    {
        return create(OperationConcat.concat(new Observable[] {
            observable, observable1, observable2, observable3, observable4
        }));
    }

    public static final Observable concat(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5)
    {
        return create(OperationConcat.concat(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5
        }));
    }

    public static final Observable concat(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6)
    {
        return create(OperationConcat.concat(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6
        }));
    }

    public static final Observable concat(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7)
    {
        return create(OperationConcat.concat(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7
        }));
    }

    public static final Observable concat(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Observable observable8)
    {
        return create(OperationConcat.concat(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7, observable8
        }));
    }

    public static final Observable create(OnSubscribe onsubscribe)
    {
        return new Observable(onsubscribe);
    }

    public static final Observable create(OnSubscribeFunc onsubscribefunc)
    {
        return new Observable(new OnSubscribe(onsubscribefunc) {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                Subscription subscription = f.onSubscribe(subscriber);
                if(subscription != null && subscription != subscriber)
                    subscriber.add(subscription);
            }

            final OnSubscribeFunc val$f;

            
            {
                f = onsubscribefunc;
                super();
            }
        }
);
    }

    public static final Observable defer(Func0 func0)
    {
        return create(OperationDefer.defer(func0));
    }

    public static final Observable empty()
    {
        return from(new ArrayList());
    }

    public static final Observable empty(Scheduler scheduler)
    {
        return empty().subscribeOn(scheduler);
    }

    public static final Observable error(Throwable throwable)
    {
        return new ThrowObservable(throwable);
    }

    public static final Observable error(Throwable throwable, Scheduler scheduler)
    {
        return error(throwable).subscribeOn(scheduler);
    }

    public static final Observable from(Iterable iterable)
    {
        return create(new OnSubscribeFromIterable(iterable));
    }

    public static final Observable from(Iterable iterable, Scheduler scheduler)
    {
        return create(new OnSubscribeFromIterable(iterable)).subscribeOn(scheduler);
    }

    public static final Observable from(Object obj)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj
        }))));
    }

    public static final Observable from(Object obj, Object obj1)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1
        }))));
    }

    public static final Observable from(Object obj, Object obj1, Object obj2)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1, obj2
        }))));
    }

    public static final Observable from(Object obj, Object obj1, Object obj2, Object obj3)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1, obj2, obj3
        }))));
    }

    public static final Observable from(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1, obj2, obj3, obj4
        }))));
    }

    public static final Observable from(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1, obj2, obj3, obj4, obj5
        }))));
    }

    public static final Observable from(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1, obj2, obj3, obj4, obj5, obj6
        }))));
    }

    public static final Observable from(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7
        }))));
    }

    public static final Observable from(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
            Object obj8)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8
        }))));
    }

    public static final Observable from(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
            Object obj8, Object obj9)
    {
        return from(((Iterable) (Arrays.asList(new Object[] {
            obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9
        }))));
    }

    public static final Observable from(Future future)
    {
        return create(OperationToObservableFuture.toObservableFuture(future));
    }

    public static final Observable from(Future future, long l, TimeUnit timeunit)
    {
        return create(OperationToObservableFuture.toObservableFuture(future, l, timeunit));
    }

    public static final Observable from(Future future, Scheduler scheduler)
    {
        return create(OperationToObservableFuture.toObservableFuture(future)).subscribeOn(scheduler);
    }

    public static final transient Observable from(Object aobj[])
    {
        return from(((Iterable) (Arrays.asList(aobj))));
    }

    public static final Observable from(Object aobj[], Scheduler scheduler)
    {
        return from(((Iterable) (Arrays.asList(aobj))), scheduler);
    }

    public static final Observable interval(long l, TimeUnit timeunit)
    {
        return create(OperationInterval.interval(l, timeunit));
    }

    public static final Observable interval(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationInterval.interval(l, timeunit, scheduler));
    }

    public static final Observable just(Object obj)
    {
        return create(new OnSubscribe(obj) {

            public volatile void call(Object obj1)
            {
                call((Subscriber)obj1);
            }

            public void call(Subscriber subscriber)
            {
                if(!subscriber.isUnsubscribed())
                {
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                }
            }

            final Object val$value;

            
            {
                value = obj;
                super();
            }
        }
);
    }

    public static final Observable just(Object obj, Scheduler scheduler)
    {
        return just(obj).subscribeOn(scheduler);
    }

    public static final Observable max(Observable observable)
    {
        return OperationMinMax.max(observable);
    }

    public static final Observable merge(Iterable iterable)
    {
        return merge(from(iterable));
    }

    public static final Observable merge(Iterable iterable, int i)
    {
        return merge(from(iterable), i);
    }

    public static final Observable merge(Iterable iterable, int i, Scheduler scheduler)
    {
        return merge(from(iterable, scheduler), i);
    }

    public static final Observable merge(Iterable iterable, Scheduler scheduler)
    {
        return merge(from(iterable, scheduler));
    }

    public static final Observable merge(Observable observable)
    {
        return observable.lift(new OperatorMerge());
    }

    public static final Observable merge(Observable observable, int i)
    {
        return create(OperationMergeMaxConcurrent.merge(observable, i));
    }

    public static final Observable merge(Observable observable, Observable observable1)
    {
        return merge(from(Arrays.asList(new Observable[] {
            observable, observable1
        })));
    }

    public static final Observable merge(Observable observable, Observable observable1, Observable observable2)
    {
        return merge(from(Arrays.asList(new Observable[] {
            observable, observable1, observable2
        })));
    }

    public static final Observable merge(Observable observable, Observable observable1, Observable observable2, Observable observable3)
    {
        return merge(from(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3
        })));
    }

    public static final Observable merge(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4)
    {
        return merge(from(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4
        })));
    }

    public static final Observable merge(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5)
    {
        return merge(from(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5
        })));
    }

    public static final Observable merge(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6)
    {
        return merge(from(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6
        })));
    }

    public static final Observable merge(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7)
    {
        return merge(from(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7
        })));
    }

    public static final Observable merge(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Observable observable8)
    {
        return merge(from(Arrays.asList(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7, observable8
        })));
    }

    public static final Observable merge(Observable aobservable[])
    {
        return merge(from(aobservable));
    }

    public static final Observable merge(Observable aobservable[], Scheduler scheduler)
    {
        return merge(from(aobservable, scheduler));
    }

    public static final Observable mergeDelayError(Observable observable)
    {
        return create(OperationMergeDelayError.mergeDelayError(observable));
    }

    public static final Observable mergeDelayError(Observable observable, Observable observable1)
    {
        return create(OperationMergeDelayError.mergeDelayError(new Observable[] {
            observable, observable1
        }));
    }

    public static final Observable mergeDelayError(Observable observable, Observable observable1, Observable observable2)
    {
        return create(OperationMergeDelayError.mergeDelayError(new Observable[] {
            observable, observable1, observable2
        }));
    }

    public static final Observable mergeDelayError(Observable observable, Observable observable1, Observable observable2, Observable observable3)
    {
        return create(OperationMergeDelayError.mergeDelayError(new Observable[] {
            observable, observable1, observable2, observable3
        }));
    }

    public static final Observable mergeDelayError(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4)
    {
        return create(OperationMergeDelayError.mergeDelayError(new Observable[] {
            observable, observable1, observable2, observable3, observable4
        }));
    }

    public static final Observable mergeDelayError(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5)
    {
        return create(OperationMergeDelayError.mergeDelayError(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5
        }));
    }

    public static final Observable mergeDelayError(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6)
    {
        return create(OperationMergeDelayError.mergeDelayError(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6
        }));
    }

    public static final Observable mergeDelayError(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7)
    {
        return create(OperationMergeDelayError.mergeDelayError(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7
        }));
    }

    public static final Observable mergeDelayError(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Observable observable8)
    {
        return create(OperationMergeDelayError.mergeDelayError(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7, observable8
        }));
    }

    public static final Observable min(Observable observable)
    {
        return OperationMinMax.min(observable);
    }

    public static final Observable never()
    {
        return new NeverObservable();
    }

    public static final Observable parallelMerge(Observable observable, int i)
    {
        return OperationParallelMerge.parallelMerge(observable, i);
    }

    public static final Observable parallelMerge(Observable observable, int i, Scheduler scheduler)
    {
        return OperationParallelMerge.parallelMerge(observable, i, scheduler);
    }

    public static final Observable pivot(Observable observable)
    {
        return observable.lift(new OperatorPivot());
    }

    public static final Observable range(int i, int j)
    {
        if(j < 0)
            throw new IllegalArgumentException("Count can not be negative");
        if(i + j > 0x7fffffff)
            throw new IllegalArgumentException("start + count can not exceed Integer.MAX_VALUE");
        else
            return create(new OnSubscribeRange(i, i + (j - 1)));
    }

    public static final Observable range(int i, int j, Scheduler scheduler)
    {
        return range(i, j).subscribeOn(scheduler);
    }

    public static final Observable sequenceEqual(Observable observable, Observable observable1)
    {
        return sequenceEqual(observable, observable1, new Func2() {

            public final Boolean call(Object obj, Object obj1)
            {
                if(obj == null)
                {
                    boolean flag;
                    if(obj1 == null)
                        flag = true;
                    else
                        flag = false;
                    return Boolean.valueOf(flag);
                } else
                {
                    return Boolean.valueOf(obj.equals(obj1));
                }
            }

            public volatile Object call(Object obj, Object obj1)
            {
                return call(obj, obj1);
            }

        }
);
    }

    public static final Observable sequenceEqual(Observable observable, Observable observable1, Func2 func2)
    {
        return OperationSequenceEqual.sequenceEqual(observable, observable1, func2);
    }

    public static final Observable sumDouble(Observable observable)
    {
        return OperationSum.sumDoubles(observable);
    }

    public static final Observable sumFloat(Observable observable)
    {
        return OperationSum.sumFloats(observable);
    }

    public static final Observable sumInteger(Observable observable)
    {
        return OperationSum.sumIntegers(observable);
    }

    public static final Observable sumLong(Observable observable)
    {
        return OperationSum.sumLongs(observable);
    }

    public static final Observable switchDo(Observable observable)
    {
        return create(OperationSwitch.switchDo(observable));
    }

    public static final Observable switchLatest(Observable observable)
    {
        return create(OperationSwitch.switchDo(observable));
    }

    public static final Observable switchOnNext(Observable observable)
    {
        return create(OperationSwitch.switchDo(observable));
    }

    public static final Observable synchronize(Observable observable)
    {
        return observable.synchronize();
    }

    public static final Observable timer(long l, long l1, TimeUnit timeunit)
    {
        return timer(l, l1, timeunit, Schedulers.computation());
    }

    public static final Observable timer(long l, long l1, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(new rx.operators.OperationTimer.TimerPeriodically(l, l1, timeunit, scheduler));
    }

    public static final Observable timer(long l, TimeUnit timeunit)
    {
        return timer(l, timeunit, Schedulers.computation());
    }

    public static final Observable timer(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(new rx.operators.OperationTimer.TimerOnce(l, timeunit, scheduler));
    }

    public static final Observable using(Func0 func0, Func1 func1)
    {
        return create(OperationUsing.using(func0, func1));
    }

    public static final Observable when(Iterable iterable)
    {
        if(iterable == null)
            throw new NullPointerException("plans");
        else
            return create(OperationJoinPatterns.when(iterable));
    }

    public static final Observable when(Plan0 plan0)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0
        }));
    }

    public static final Observable when(Plan0 plan0, Plan0 plan0_1)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0, plan0_1
        }));
    }

    public static final Observable when(Plan0 plan0, Plan0 plan0_1, Plan0 plan0_2)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0, plan0_1, plan0_2
        }));
    }

    public static final Observable when(Plan0 plan0, Plan0 plan0_1, Plan0 plan0_2, Plan0 plan0_3)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0, plan0_1, plan0_2, plan0_3
        }));
    }

    public static final Observable when(Plan0 plan0, Plan0 plan0_1, Plan0 plan0_2, Plan0 plan0_3, Plan0 plan0_4)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0, plan0_1, plan0_2, plan0_3, plan0_4
        }));
    }

    public static final Observable when(Plan0 plan0, Plan0 plan0_1, Plan0 plan0_2, Plan0 plan0_3, Plan0 plan0_4, Plan0 plan0_5)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0, plan0_1, plan0_2, plan0_3, plan0_4, plan0_5
        }));
    }

    public static final Observable when(Plan0 plan0, Plan0 plan0_1, Plan0 plan0_2, Plan0 plan0_3, Plan0 plan0_4, Plan0 plan0_5, Plan0 plan0_6)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0, plan0_1, plan0_2, plan0_3, plan0_4, plan0_5, plan0_6
        }));
    }

    public static final Observable when(Plan0 plan0, Plan0 plan0_1, Plan0 plan0_2, Plan0 plan0_3, Plan0 plan0_4, Plan0 plan0_5, Plan0 plan0_6, Plan0 plan0_7)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0, plan0_1, plan0_2, plan0_3, plan0_4, plan0_5, plan0_6, plan0_7
        }));
    }

    public static final Observable when(Plan0 plan0, Plan0 plan0_1, Plan0 plan0_2, Plan0 plan0_3, Plan0 plan0_4, Plan0 plan0_5, Plan0 plan0_6, Plan0 plan0_7, 
            Plan0 plan0_8)
    {
        return create(OperationJoinPatterns.when(new Plan0[] {
            plan0, plan0_1, plan0_2, plan0_3, plan0_4, plan0_5, plan0_6, plan0_7, plan0_8
        }));
    }

    public static final transient Observable when(Plan0 aplan0[])
    {
        return create(OperationJoinPatterns.when(aplan0));
    }

    public static final Observable zip(Iterable iterable, FuncN funcn)
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = iterable.iterator(); iterator.hasNext(); arraylist.add((Observable)iterator.next()));
        return just(((Object) (arraylist.toArray(new Observable[arraylist.size()])))).lift(new OperatorZip(funcn));
    }

    public static final Observable zip(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Observable observable8, Func9 func9)
    {
        return just(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7, observable8
        }).lift(new OperatorZip(func9));
    }

    public static final Observable zip(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Observable observable7, 
            Func8 func8)
    {
        return just(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6, observable7
        }).lift(new OperatorZip(func8));
    }

    public static final Observable zip(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, Func7 func7)
    {
        return just(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5, observable6
        }).lift(new OperatorZip(func7));
    }

    public static final Observable zip(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Func6 func6)
    {
        return just(new Observable[] {
            observable, observable1, observable2, observable3, observable4, observable5
        }).lift(new OperatorZip(func6));
    }

    public static final Observable zip(Observable observable, Observable observable1, Observable observable2, Observable observable3, Observable observable4, Func5 func5)
    {
        return just(new Observable[] {
            observable, observable1, observable2, observable3, observable4
        }).lift(new OperatorZip(func5));
    }

    public static final Observable zip(Observable observable, Observable observable1, Observable observable2, Observable observable3, Func4 func4)
    {
        return just(new Observable[] {
            observable, observable1, observable2, observable3
        }).lift(new OperatorZip(func4));
    }

    public static final Observable zip(Observable observable, Observable observable1, Observable observable2, Func3 func3)
    {
        return just(new Observable[] {
            observable, observable1, observable2
        }).lift(new OperatorZip(func3));
    }

    public static final Observable zip(Observable observable, Observable observable1, Func2 func2)
    {
        return just(new Observable[] {
            observable, observable1
        }).lift(new OperatorZip(func2));
    }

    public static final Observable zip(Observable observable, FuncN funcn)
    {
        return observable.toList().map(new Func1() {

            public volatile Object call(Object obj)
            {
                return call((List)obj);
            }

            public Observable[] call(List list)
            {
                return (Observable[])list.toArray(new Observable[list.size()]);
            }

        }
).lift(new OperatorZip(funcn));
    }

    public final Observable aggregate(Object obj, Func2 func2)
    {
        return reduce(obj, func2);
    }

    public final Observable aggregate(Func2 func2)
    {
        return reduce(func2);
    }

    public final Observable all(Func1 func1)
    {
        return create(OperationAll.all(this, func1));
    }

    public final Pattern2 and(Observable observable)
    {
        return OperationJoinPatterns.and(this, observable);
    }

    public final Observable asObservable()
    {
        return create(new OperationAsObservable(this));
    }

    public final Observable averageDouble(Func1 func1)
    {
        return create(new rx.operators.OperationAverage.AverageDoubleExtractor(this, func1));
    }

    public final Observable averageFloat(Func1 func1)
    {
        return create(new rx.operators.OperationAverage.AverageFloatExtractor(this, func1));
    }

    public final Observable averageInteger(Func1 func1)
    {
        return create(new rx.operators.OperationAverage.AverageIntegerExtractor(this, func1));
    }

    public final Observable averageLong(Func1 func1)
    {
        return create(new rx.operators.OperationAverage.AverageLongExtractor(this, func1));
    }

    public final Observable buffer(int i)
    {
        return create(OperationBuffer.buffer(this, i));
    }

    public final Observable buffer(int i, int j)
    {
        return create(OperationBuffer.buffer(this, i, j));
    }

    public final Observable buffer(long l, long l1, TimeUnit timeunit)
    {
        return create(OperationBuffer.buffer(this, l, l1, timeunit));
    }

    public final Observable buffer(long l, long l1, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationBuffer.buffer(this, l, l1, timeunit, scheduler));
    }

    public final Observable buffer(long l, TimeUnit timeunit)
    {
        return create(OperationBuffer.buffer(this, l, timeunit));
    }

    public final Observable buffer(long l, TimeUnit timeunit, int i)
    {
        return create(OperationBuffer.buffer(this, l, timeunit, i));
    }

    public final Observable buffer(long l, TimeUnit timeunit, int i, Scheduler scheduler)
    {
        return create(OperationBuffer.buffer(this, l, timeunit, i, scheduler));
    }

    public final Observable buffer(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationBuffer.buffer(this, l, timeunit, scheduler));
    }

    public final Observable buffer(Observable observable)
    {
        return create(OperationBuffer.bufferWithBoundaryObservable(this, observable));
    }

    public final Observable buffer(Observable observable, int i)
    {
        return create(OperationBuffer.bufferWithBoundaryObservable(this, observable, i));
    }

    public final Observable buffer(Observable observable, Func1 func1)
    {
        return create(OperationBuffer.buffer(this, observable, func1));
    }

    public final Observable buffer(Func0 func0)
    {
        return create(OperationBuffer.buffer(this, func0));
    }

    public final Observable cache()
    {
        return create(new OperatorCache(this));
    }

    public final Observable cast(Class class1)
    {
        return lift(new OperatorCast(class1));
    }

    public final Observable collect(Object obj, final Action2 collector)
    {
        return reduce(obj, new Func2() {

            public final Object call(Object obj1, Object obj2)
            {
                collector.call(obj1, obj2);
                return obj1;
            }

            final Observable this$0;
            final Action2 val$collector;

            
            {
                this$0 = Observable.this;
                collector = action2;
                super();
            }
        }
);
    }

    public final Observable concatMap(Func1 func1)
    {
        return concat(map(func1));
    }

    public final Observable contains(final Object element)
    {
        return exists(new Func1() {

            public final Boolean call(Object obj)
            {
                boolean flag;
                if(element == null)
                {
                    if(obj == null)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = element.equals(obj);
                }
                return Boolean.valueOf(flag);
            }

            public volatile Object call(Object obj)
            {
                return call(obj);
            }

            final Observable this$0;
            final Object val$element;

            
            {
                this$0 = Observable.this;
                element = obj;
                super();
            }
        }
);
    }

    public final Observable count()
    {
        return reduce(Integer.valueOf(0), new Func2() {

            public final Integer call(Integer integer, Object obj)
            {
                return Integer.valueOf(1 + integer.intValue());
            }

            public volatile Object call(Object obj, Object obj1)
            {
                return call((Integer)obj, obj1);
            }

            final Observable this$0;

            
            {
                this$0 = Observable.this;
                super();
            }
        }
);
    }

    public final Observable debounce(long l, TimeUnit timeunit)
    {
        return create(OperationDebounce.debounce(this, l, timeunit));
    }

    public final Observable debounce(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationDebounce.debounce(this, l, timeunit, scheduler));
    }

    public final Observable debounce(Func1 func1)
    {
        return create(OperationDebounce.debounceSelector(this, func1));
    }

    public final Observable defaultIfEmpty(Object obj)
    {
        return create(OperationDefaultIfEmpty.defaultIfEmpty(this, obj));
    }

    public final Observable delay(long l, TimeUnit timeunit)
    {
        return OperationDelay.delay(this, l, timeunit, Schedulers.computation());
    }

    public final Observable delay(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return OperationDelay.delay(this, l, timeunit, scheduler);
    }

    public final Observable delay(Func0 func0, Func1 func1)
    {
        return create(OperationDelay.delay(this, func0, func1));
    }

    public final Observable delay(Func1 func1)
    {
        return create(OperationDelay.delay(this, func1));
    }

    public final Observable delaySubscription(long l, TimeUnit timeunit)
    {
        return delaySubscription(l, timeunit, Schedulers.computation());
    }

    public final Observable delaySubscription(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationDelay.delaySubscription(this, l, timeunit, scheduler));
    }

    public final Observable dematerialize()
    {
        return create(OperationDematerialize.dematerialize(this));
    }

    public final Observable distinct()
    {
        return create(OperationDistinct.distinct(this));
    }

    public final Observable distinct(Func1 func1)
    {
        return create(OperationDistinct.distinct(this, func1));
    }

    public final Observable distinctUntilChanged()
    {
        return create(OperationDistinctUntilChanged.distinctUntilChanged(this));
    }

    public final Observable distinctUntilChanged(Func1 func1)
    {
        return create(OperationDistinctUntilChanged.distinctUntilChanged(this, func1));
    }

    public final Observable doOnCompleted(final Action0 onCompleted)
    {
        return lift(new OperatorDoOnEach(new Observer() {

            public final void onCompleted()
            {
                onCompleted.call();
            }

            public final void onError(Throwable throwable)
            {
            }

            public final void onNext(Object obj)
            {
            }

            final Observable this$0;
            final Action0 val$onCompleted;

            
            {
                this$0 = Observable.this;
                onCompleted = action0;
                super();
            }
        }
));
    }

    public final Observable doOnEach(Observer observer)
    {
        return lift(new OperatorDoOnEach(observer));
    }

    public final Observable doOnEach(final Action1 onNotification)
    {
        return lift(new OperatorDoOnEach(new Observer() {

            public final void onCompleted()
            {
                onNotification.call(Notification.createOnCompleted());
            }

            public final void onError(Throwable throwable)
            {
                onNotification.call(Notification.createOnError(throwable));
            }

            public final void onNext(Object obj)
            {
                onNotification.call(Notification.createOnNext(obj));
            }

            final Observable this$0;
            final Action1 val$onNotification;

            
            {
                this$0 = Observable.this;
                onNotification = action1;
                super();
            }
        }
));
    }

    public final Observable doOnError(final Action1 onError)
    {
        return lift(new OperatorDoOnEach(new Observer() {

            public final void onCompleted()
            {
            }

            public final void onError(Throwable throwable)
            {
                onError.call(throwable);
            }

            public final void onNext(Object obj)
            {
            }

            final Observable this$0;
            final Action1 val$onError;

            
            {
                this$0 = Observable.this;
                onError = action1;
                super();
            }
        }
));
    }

    public final Observable doOnNext(final Action1 onNext)
    {
        return lift(new OperatorDoOnEach(new Observer() {

            public final void onCompleted()
            {
            }

            public final void onError(Throwable throwable)
            {
            }

            public final void onNext(Object obj)
            {
                onNext.call(obj);
            }

            final Observable this$0;
            final Action1 val$onNext;

            
            {
                this$0 = Observable.this;
                onNext = action1;
                super();
            }
        }
));
    }

    public final Observable doOnTerminate(final Action0 onTerminate)
    {
        return lift(new OperatorDoOnEach(new Observer() {

            public final void onCompleted()
            {
                onTerminate.call();
            }

            public final void onError(Throwable throwable)
            {
                onTerminate.call();
            }

            public final void onNext(Object obj)
            {
            }

            final Observable this$0;
            final Action0 val$onTerminate;

            
            {
                this$0 = Observable.this;
                onTerminate = action0;
                super();
            }
        }
));
    }

    public final Observable elementAt(int i)
    {
        return lift(new OperatorElementAt(i));
    }

    public final Observable elementAtOrDefault(int i, Object obj)
    {
        return lift(new OperatorElementAt(i, obj));
    }

    public final Observable exists(Func1 func1)
    {
        return create(OperationAny.exists(this, func1));
    }

    public final Observable filter(Func1 func1)
    {
        return lift(new OperatorFilter(func1));
    }

    public final Observable finallyDo(Action0 action0)
    {
        return create(OperationFinally.finallyDo(this, action0));
    }

    public final Observable first()
    {
        return take(1).single();
    }

    public final Observable first(Func1 func1)
    {
        return takeFirst(func1).single();
    }

    public final Observable firstOrDefault(Object obj)
    {
        return take(1).singleOrDefault(obj);
    }

    public final Observable firstOrDefault(Object obj, Func1 func1)
    {
        return takeFirst(func1).singleOrDefault(obj);
    }

    public final Observable flatMap(Func1 func1)
    {
        return mergeMap(func1);
    }

    public final Observable groupBy(Func1 func1)
    {
        return lift(new OperatorGroupBy(func1));
    }

    public final Observable groupByUntil(Func1 func1, Func1 func1_1)
    {
        return groupByUntil(func1, Functions.identity(), func1_1);
    }

    public final Observable groupByUntil(Func1 func1, Func1 func1_1, Func1 func1_2)
    {
        return create(new OperationGroupByUntil(this, func1, func1_1, func1_2));
    }

    public final Observable groupJoin(Observable observable, Func1 func1, Func1 func1_1, Func2 func2)
    {
        return create(new OperationGroupJoin(this, observable, func1, func1_1, func2));
    }

    public final Observable ignoreElements()
    {
        return filter(Functions.alwaysFalse());
    }

    public final Observable isEmpty()
    {
        return create(OperationAny.isEmpty(this));
    }

    public final Observable join(Observable observable, Func1 func1, Func1 func1_1, Func2 func2)
    {
        return create(new OperationJoin(this, observable, func1, func1_1, func2));
    }

    public final Observable last()
    {
        return takeLast(1).single();
    }

    public final Observable last(Func1 func1)
    {
        return filter(func1).takeLast(1).single();
    }

    public final Observable lastOrDefault(Object obj)
    {
        return takeLast(1).singleOrDefault(obj);
    }

    public final Observable lastOrDefault(Object obj, Func1 func1)
    {
        return filter(func1).takeLast(1).singleOrDefault(obj);
    }

    public Observable lift(final Operator lift)
    {
        return new Observable(new OnSubscribe() {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                try
                {
                    onSubscribe.call(hook.onLift(lift).call(subscriber));
                    return;
                }
                catch(Throwable throwable)
                {
                    if(throwable instanceof OnErrorNotImplementedException)
                    {
                        throw (OnErrorNotImplementedException)throwable;
                    } else
                    {
                        subscriber.onError(throwable);
                        return;
                    }
                }
            }

            final Observable this$0;
            final Operator val$lift;

            
            {
                this$0 = Observable.this;
                lift = operator;
                super();
            }
        }
);
    }

    public final Observable longCount()
    {
        return reduce(Long.valueOf(0L), new Func2() {

            public final Long call(Long long1, Object obj)
            {
                return Long.valueOf(1L + long1.longValue());
            }

            public volatile Object call(Object obj, Object obj1)
            {
                return call((Long)obj, obj1);
            }

            final Observable this$0;

            
            {
                this$0 = Observable.this;
                super();
            }
        }
);
    }

    public final Observable map(Func1 func1)
    {
        return lift(new OperatorMap(func1));
    }

    public final Observable mapMany(Func1 func1)
    {
        return mergeMap(func1);
    }

    public final Observable materialize()
    {
        return lift(new OperatorMaterialize());
    }

    public final Observable max(Comparator comparator)
    {
        return OperationMinMax.max(this, comparator);
    }

    public final Observable maxBy(Func1 func1)
    {
        return OperationMinMax.maxBy(this, func1);
    }

    public final Observable maxBy(Func1 func1, Comparator comparator)
    {
        return OperationMinMax.maxBy(this, func1, comparator);
    }

    public final Observable mergeMap(Func1 func1)
    {
        return merge(map(func1));
    }

    public final Observable mergeMap(Func1 func1, Func1 func1_1, Func0 func0)
    {
        return create(OperationFlatMap.flatMap(this, func1, func1_1, func0));
    }

    public final Observable mergeMap(Func1 func1, Func2 func2)
    {
        return create(OperationFlatMap.flatMap(this, func1, func2));
    }

    public final Observable mergeMapIterable(Func1 func1)
    {
        return merge(map(OperationFlatMap.flatMapIterableFunc(func1)));
    }

    public final Observable mergeMapIterable(Func1 func1, Func2 func2)
    {
        return mergeMap(OperationFlatMap.flatMapIterableFunc(func1), func2);
    }

    public final Observable min(Comparator comparator)
    {
        return OperationMinMax.min(this, comparator);
    }

    public final Observable minBy(Func1 func1)
    {
        return OperationMinMax.minBy(this, func1);
    }

    public final Observable minBy(Func1 func1, Comparator comparator)
    {
        return OperationMinMax.minBy(this, func1, comparator);
    }

    public final Observable _mthmulticast(Func0 func0, Func1 func1)
    {
        return OperationMulticast._mthmulticast(this, func0, func1);
    }

    public final ConnectableObservable _mthmulticast(Subject subject)
    {
        return OperationMulticast._mthmulticast(this, subject);
    }

    public final Observable nest()
    {
        return just(this);
    }

    public final Observable observeOn(Scheduler scheduler)
    {
        return lift(new OperatorObserveOn(scheduler));
    }

    public final Observable ofType(final Class klass)
    {
        return filter(new Func1() {

            public final Boolean call(Object obj)
            {
                return Boolean.valueOf(klass.isInstance(obj));
            }

            public volatile Object call(Object obj)
            {
                return call(obj);
            }

            final Observable this$0;
            final Class val$klass;

            
            {
                this$0 = Observable.this;
                klass = class1;
                super();
            }
        }
).cast(klass);
    }

    public final Observable onErrorFlatMap(Func1 func1)
    {
        return lift(new OperatorOnErrorFlatMap(func1));
    }

    public final Observable onErrorResumeNext(Observable observable)
    {
        return create(OperationOnErrorResumeNextViaObservable.onErrorResumeNextViaObservable(this, observable));
    }

    public final Observable onErrorResumeNext(Func1 func1)
    {
        return lift(new OperatorOnErrorResumeNextViaFunction(func1));
    }

    public final Observable onErrorReturn(Func1 func1)
    {
        return create(OperationOnErrorReturn.onErrorReturn(this, func1));
    }

    public final Observable onExceptionResumeNext(Observable observable)
    {
        return create(OperationOnExceptionResumeNextViaObservable.onExceptionResumeNextViaObservable(this, observable));
    }

    public final Observable parallel(Func1 func1)
    {
        return lift(new OperatorParallel(func1, Schedulers.newThread()));
    }

    public final Observable parallel(Func1 func1, Scheduler scheduler)
    {
        return lift(new OperatorParallel(func1, scheduler));
    }

    public final Observable publish(Func1 func1)
    {
        return _mthmulticast(new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public final Subject call()
            {
                return PublishSubject.create();
            }

            final Observable this$0;

            
            {
                this$0 = Observable.this;
                super();
            }
        }
, func1);
    }

    public final Observable publish(Func1 func1, final Object initialValue)
    {
        return _mthmulticast(new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public final Subject call()
            {
                return BehaviorSubject.create(initialValue);
            }

            final Observable this$0;
            final Object val$initialValue;

            
            {
                this$0 = Observable.this;
                initialValue = obj;
                super();
            }
        }
, func1);
    }

    public final ConnectableObservable publish()
    {
        return OperationMulticast._mthmulticast(this, PublishSubject.create());
    }

    public final ConnectableObservable publish(Object obj)
    {
        return OperationMulticast._mthmulticast(this, BehaviorSubject.create(obj));
    }

    public final Observable publishLast(Func1 func1)
    {
        return _mthmulticast(new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public final Subject call()
            {
                return AsyncSubject.create();
            }

            final Observable this$0;

            
            {
                this$0 = Observable.this;
                super();
            }
        }
, func1);
    }

    public final ConnectableObservable publishLast()
    {
        return OperationMulticast._mthmulticast(this, AsyncSubject.create());
    }

    public final Observable reduce(Object obj, Func2 func2)
    {
        return scan(obj, func2).takeLast(1);
    }

    public final Observable reduce(Func2 func2)
    {
        return scan(func2).last();
    }

    public final Observable repeat()
    {
        return nest().lift(new OperatorRepeat());
    }

    public final Observable repeat(long l)
    {
        if(l < 0L)
            throw new IllegalArgumentException("count >= 0 expected");
        else
            return nest().lift(new OperatorRepeat(l));
    }

    public final Observable repeat(long l, Scheduler scheduler)
    {
        return nest().lift(new OperatorRepeat(l, scheduler));
    }

    public final Observable repeat(Scheduler scheduler)
    {
        return nest().lift(new OperatorRepeat(scheduler));
    }

    public final Observable replay(Func1 func1)
    {
        return OperationMulticast._mthmulticast(this, new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public final Subject call()
            {
                return ReplaySubject.create();
            }

            final Observable this$0;

            
            {
                this$0 = Observable.this;
                super();
            }
        }
, func1);
    }

    public final Observable replay(Func1 func1, final int bufferSize)
    {
        return OperationMulticast._mthmulticast(this, new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public final Subject call()
            {
                return OperationReplay.replayBuffered(bufferSize);
            }

            final Observable this$0;
            final int val$bufferSize;

            
            {
                this$0 = Observable.this;
                bufferSize = i;
                super();
            }
        }
, func1);
    }

    public final Observable replay(Func1 func1, int i, long l, TimeUnit timeunit)
    {
        return replay(func1, i, l, timeunit, Schedulers.computation());
    }

    public final Observable replay(Func1 func1, final int bufferSize, final long time, final TimeUnit unit, final Scheduler scheduler)
    {
        if(bufferSize < 0)
            throw new IllegalArgumentException("bufferSize < 0");
        else
            return OperationMulticast._mthmulticast(this, new Func0() {

                public volatile Object call()
                {
                    return call();
                }

                public final Subject call()
                {
                    return OperationReplay.replayWindowed(time, unit, bufferSize, scheduler);
                }

                final Observable this$0;
                final int val$bufferSize;
                final Scheduler val$scheduler;
                final long val$time;
                final TimeUnit val$unit;

            
            {
                this$0 = Observable.this;
                time = l;
                unit = timeunit;
                bufferSize = i;
                scheduler = scheduler1;
                super();
            }
            }
, func1);
    }

    public final Observable replay(Func1 func1, final int bufferSize, final Scheduler scheduler)
    {
        return OperationMulticast._mthmulticast(this, new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public final Subject call()
            {
                return OperationReplay.createScheduledSubject(OperationReplay.replayBuffered(bufferSize), scheduler);
            }

            final Observable this$0;
            final int val$bufferSize;
            final Scheduler val$scheduler;

            
            {
                this$0 = Observable.this;
                bufferSize = i;
                scheduler = scheduler1;
                super();
            }
        }
, func1);
    }

    public final Observable replay(Func1 func1, long l, TimeUnit timeunit)
    {
        return replay(func1, l, timeunit, Schedulers.computation());
    }

    public final Observable replay(Func1 func1, final long time, final TimeUnit unit, final Scheduler scheduler)
    {
        return OperationMulticast._mthmulticast(this, new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public final Subject call()
            {
                return OperationReplay.replayWindowed(time, unit, -1, scheduler);
            }

            final Observable this$0;
            final Scheduler val$scheduler;
            final long val$time;
            final TimeUnit val$unit;

            
            {
                this$0 = Observable.this;
                time = l;
                unit = timeunit;
                scheduler = scheduler1;
                super();
            }
        }
, func1);
    }

    public final Observable replay(Func1 func1, final Scheduler scheduler)
    {
        return OperationMulticast._mthmulticast(this, new Func0() {

            public volatile Object call()
            {
                return call();
            }

            public final Subject call()
            {
                return OperationReplay.createScheduledSubject(ReplaySubject.create(), scheduler);
            }

            final Observable this$0;
            final Scheduler val$scheduler;

            
            {
                this$0 = Observable.this;
                scheduler = scheduler1;
                super();
            }
        }
, func1);
    }

    public final ConnectableObservable replay()
    {
        return OperationMulticast._mthmulticast(this, ReplaySubject.create());
    }

    public final ConnectableObservable replay(int i)
    {
        return OperationMulticast._mthmulticast(this, OperationReplay.replayBuffered(i));
    }

    public final ConnectableObservable replay(int i, long l, TimeUnit timeunit)
    {
        return replay(i, l, timeunit, Schedulers.computation());
    }

    public final ConnectableObservable replay(int i, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        if(i < 0)
            throw new IllegalArgumentException("bufferSize < 0");
        else
            return OperationMulticast._mthmulticast(this, OperationReplay.replayWindowed(l, timeunit, i, scheduler));
    }

    public final ConnectableObservable replay(int i, Scheduler scheduler)
    {
        return OperationMulticast._mthmulticast(this, OperationReplay.createScheduledSubject(OperationReplay.replayBuffered(i), scheduler));
    }

    public final ConnectableObservable replay(long l, TimeUnit timeunit)
    {
        return replay(l, timeunit, Schedulers.computation());
    }

    public final ConnectableObservable replay(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return OperationMulticast._mthmulticast(this, OperationReplay.replayWindowed(l, timeunit, -1, scheduler));
    }

    public final ConnectableObservable replay(Scheduler scheduler)
    {
        return OperationMulticast._mthmulticast(this, OperationReplay.createScheduledSubject(ReplaySubject.create(), scheduler));
    }

    public final Observable retry()
    {
        return nest().lift(new OperatorRetry());
    }

    public final Observable retry(int i)
    {
        return nest().lift(new OperatorRetry(i));
    }

    public final Observable sample(long l, TimeUnit timeunit)
    {
        return create(OperationSample.sample(this, l, timeunit));
    }

    public final Observable sample(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationSample.sample(this, l, timeunit, scheduler));
    }

    public final Observable sample(Observable observable)
    {
        return create(new rx.operators.OperationSample.SampleWithObservable(this, observable));
    }

    public final Observable scan(Object obj, Func2 func2)
    {
        return lift(new OperatorScan(obj, func2));
    }

    public final Observable scan(Func2 func2)
    {
        return lift(new OperatorScan(func2));
    }

    public final Observable serialize()
    {
        return lift(new OperatorSerialize());
    }

    public final Observable single()
    {
        return create(OperationSingle.single(this));
    }

    public final Observable single(Func1 func1)
    {
        return filter(func1).single();
    }

    public final Observable singleOrDefault(Object obj)
    {
        return create(OperationSingle.singleOrDefault(this, obj));
    }

    public final Observable singleOrDefault(Object obj, Func1 func1)
    {
        return filter(func1).singleOrDefault(obj);
    }

    public final Observable skip(int i)
    {
        return lift(new OperatorSkip(i));
    }

    public final Observable skip(long l, TimeUnit timeunit)
    {
        return skip(l, timeunit, Schedulers.computation());
    }

    public final Observable skip(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(new rx.operators.OperationSkip.SkipTimed(this, l, timeunit, scheduler));
    }

    public final Observable skipLast(int i)
    {
        return create(OperationSkipLast.skipLast(this, i));
    }

    public final Observable skipLast(long l, TimeUnit timeunit)
    {
        return skipLast(l, timeunit, Schedulers.computation());
    }

    public final Observable skipLast(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(new rx.operators.OperationSkipLast.SkipLastTimed(this, l, timeunit, scheduler));
    }

    public final Observable skipUntil(Observable observable)
    {
        return create(new OperationSkipUntil(this, observable));
    }

    public final Observable skipWhile(Func1 func1)
    {
        return lift(new OperatorSkipWhile(OperatorSkipWhile.toPredicate2(func1)));
    }

    public final Observable skipWhileWithIndex(Func2 func2)
    {
        return lift(new OperatorSkipWhile(func2));
    }

    public final Observable startWith(Iterable iterable)
    {
        return concat(from(iterable), this);
    }

    public final Observable startWith(Iterable iterable, Scheduler scheduler)
    {
        return concat(from(iterable, scheduler), this);
    }

    public final Observable startWith(Object obj)
    {
        return concat(from(obj), this);
    }

    public final Observable startWith(Object obj, Object obj1)
    {
        return concat(from(obj, obj1), this);
    }

    public final Observable startWith(Object obj, Object obj1, Object obj2)
    {
        return concat(from(obj, obj1, obj2), this);
    }

    public final Observable startWith(Object obj, Object obj1, Object obj2, Object obj3)
    {
        return concat(from(obj, obj1, obj2, obj3), this);
    }

    public final Observable startWith(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
    {
        return concat(from(obj, obj1, obj2, obj3, obj4), this);
    }

    public final Observable startWith(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
    {
        return concat(from(obj, obj1, obj2, obj3, obj4, obj5), this);
    }

    public final Observable startWith(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)
    {
        return concat(from(obj, obj1, obj2, obj3, obj4, obj5, obj6), this);
    }

    public final Observable startWith(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, 
            Object obj7)
    {
        return concat(from(obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7), this);
    }

    public final Observable startWith(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, 
            Object obj7, Object obj8)
    {
        return concat(from(obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8), this);
    }

    public final Observable startWith(Observable observable)
    {
        return concat(observable, this);
    }

    public final Observable startWith(Object aobj[], Scheduler scheduler)
    {
        return startWith(((Iterable) (Arrays.asList(aobj))), scheduler);
    }

    public final Subscription subscribe()
    {
        return subscribe(((Subscriber) (new Subscriber() {

            public final void onCompleted()
            {
            }

            public final void onError(Throwable throwable)
            {
                throw new OnErrorNotImplementedException(throwable);
            }

            public final void onNext(Object obj)
            {
            }

            final Observable this$0;

            
            {
                this$0 = Observable.this;
                super();
            }
        }
)));
    }

    public final Subscription subscribe(final Observer observer)
    {
        return subscribe(((Subscriber) (new Subscriber() {

            public void onCompleted()
            {
                observer.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                observer.onError(throwable);
            }

            public void onNext(Object obj)
            {
                observer.onNext(obj);
            }

            final Observable this$0;
            final Observer val$observer;

            
            {
                this$0 = Observable.this;
                observer = observer1;
                super();
            }
        }
)));
    }

    public final Subscription subscribe(Observer observer, Scheduler scheduler)
    {
        return subscribeOn(scheduler).subscribe(observer);
    }

    public final Subscription subscribe(Subscriber subscriber)
    {
        OnSubscribe onsubscribe = hook.onSubscribeStart(this, onSubscribe);
        if(subscriber == null)
            throw new IllegalArgumentException("observer can not be null");
        if(onsubscribe == null)
            throw new IllegalStateException("onSubscribe function can not be null.");
        Subscription subscription;
        try
        {
            if(!(subscriber instanceof SafeSubscriber))
                subscriber = new SafeSubscriber(subscriber);
            onsubscribe.call(subscriber);
            subscription = Subscriptions.create(new Action0() {

                public void call()
                {
                    returnSubscription.unsubscribe();
                }

                final Observable this$0;
                final Subscription val$returnSubscription;

            
            {
                this$0 = Observable.this;
                returnSubscription = subscription;
                super();
            }
            }
);
        }
        catch(Throwable throwable)
        {
            Exceptions.throwIfFatal(throwable);
            try
            {
                subscriber.onError(hook.onSubscribeError(throwable));
            }
            catch(OnErrorNotImplementedException onerrornotimplementedexception)
            {
                throw onerrornotimplementedexception;
            }
            catch(Throwable throwable1)
            {
                RuntimeException runtimeexception = new RuntimeException((new StringBuilder()).append("Error occurred attempting to subscribe [").append(throwable.getMessage()).append("] and then again while trying to pass to onError.").toString(), throwable1);
                hook.onSubscribeError(runtimeexception);
                throw runtimeexception;
            }
            return Subscriptions.empty();
        }
        return subscription;
    }

    public final Subscription subscribe(Subscriber subscriber, Scheduler scheduler)
    {
        return subscribeOn(scheduler).subscribe(subscriber);
    }

    public final Subscription subscribe(final Action1 onNext)
    {
        if(onNext == null)
            throw new IllegalArgumentException("onNext can not be null");
        else
            return subscribe(((Subscriber) (new Subscriber() {

                public final void onCompleted()
                {
                }

                public final void onError(Throwable throwable)
                {
                    throw new OnErrorNotImplementedException(throwable);
                }

                public final void onNext(Object obj)
                {
                    onNext.call(obj);
                }

                final Observable this$0;
                final Action1 val$onNext;

            
            {
                this$0 = Observable.this;
                onNext = action1;
                super();
            }
            }
)));
    }

    public final Subscription subscribe(Action1 action1, Scheduler scheduler)
    {
        return subscribeOn(scheduler).subscribe(action1);
    }

    public final Subscription subscribe(final Action1 onNext, final Action1 onError)
    {
        if(onNext == null)
            throw new IllegalArgumentException("onNext can not be null");
        if(onError == null)
            throw new IllegalArgumentException("onError can not be null");
        else
            return subscribe(((Subscriber) (new Subscriber() {

                public final void onCompleted()
                {
                }

                public final void onError(Throwable throwable)
                {
                    onError.call(throwable);
                }

                public final void onNext(Object obj)
                {
                    onNext.call(obj);
                }

                final Observable this$0;
                final Action1 val$onError;
                final Action1 val$onNext;

            
            {
                this$0 = Observable.this;
                onError = action1;
                onNext = action1_1;
                super();
            }
            }
)));
    }

    public final Subscription subscribe(Action1 action1, Action1 action1_1, Scheduler scheduler)
    {
        return subscribeOn(scheduler).subscribe(action1, action1_1);
    }

    public final Subscription subscribe(final Action1 onNext, final Action1 onError, final Action0 onComplete)
    {
        if(onNext == null)
            throw new IllegalArgumentException("onNext can not be null");
        if(onError == null)
            throw new IllegalArgumentException("onError can not be null");
        if(onComplete == null)
            throw new IllegalArgumentException("onComplete can not be null");
        else
            return subscribe(((Subscriber) (new Subscriber() {

                public final void onCompleted()
                {
                    onComplete.call();
                }

                public final void onError(Throwable throwable)
                {
                    onError.call(throwable);
                }

                public final void onNext(Object obj)
                {
                    onNext.call(obj);
                }

                final Observable this$0;
                final Action0 val$onComplete;
                final Action1 val$onError;
                final Action1 val$onNext;

            
            {
                this$0 = Observable.this;
                onComplete = action0;
                onError = action1;
                onNext = action1_1;
                super();
            }
            }
)));
    }

    public final Subscription subscribe(Action1 action1, Action1 action1_1, Action0 action0, Scheduler scheduler)
    {
        return subscribeOn(scheduler).subscribe(action1, action1_1, action0);
    }

    public final Observable subscribeOn(Scheduler scheduler)
    {
        return nest().lift(new OperatorSubscribeOn(scheduler));
    }

    public final Observable sumDouble(Func1 func1)
    {
        return OperationSum.sumAtLeastOneDoubles(map(func1));
    }

    public final Observable sumFloat(Func1 func1)
    {
        return OperationSum.sumAtLeastOneFloats(map(func1));
    }

    public final Observable sumInteger(Func1 func1)
    {
        return OperationSum.sumAtLeastOneIntegers(map(func1));
    }

    public final Observable sumLong(Func1 func1)
    {
        return OperationSum.sumAtLeastOneLongs(map(func1));
    }

    public final Observable switchMap(Func1 func1)
    {
        return switchOnNext(map(func1));
    }

    public final Observable synchronize()
    {
        return lift(new OperatorSynchronize());
    }

    public final Observable synchronize(Object obj)
    {
        return lift(new OperatorSynchronize(obj));
    }

    public final Observable take(int i)
    {
        return lift(new OperatorTake(i));
    }

    public final Observable take(long l, TimeUnit timeunit)
    {
        return take(l, timeunit, Schedulers.computation());
    }

    public final Observable take(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(new rx.operators.OperationTakeTimed.TakeTimed(this, l, timeunit, scheduler));
    }

    public final Observable takeFirst()
    {
        return take(1);
    }

    public final Observable takeFirst(Func1 func1)
    {
        return filter(func1).take(1);
    }

    public final Observable takeLast(int i)
    {
        return create(OperationTakeLast.takeLast(this, i));
    }

    public final Observable takeLast(int i, long l, TimeUnit timeunit)
    {
        return takeLast(i, l, timeunit, Schedulers.computation());
    }

    public final Observable takeLast(int i, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        if(i < 0)
            throw new IllegalArgumentException("count >= 0 required");
        else
            return create(OperationTakeLast.takeLast(this, i, l, timeunit, scheduler));
    }

    public final Observable takeLast(long l, TimeUnit timeunit)
    {
        return takeLast(l, timeunit, Schedulers.computation());
    }

    public final Observable takeLast(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationTakeLast.takeLast(this, l, timeunit, scheduler));
    }

    public final Observable takeLastBuffer(int i)
    {
        return takeLast(i).toList();
    }

    public final Observable takeLastBuffer(int i, long l, TimeUnit timeunit)
    {
        return takeLast(i, l, timeunit).toList();
    }

    public final Observable takeLastBuffer(int i, long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return takeLast(i, l, timeunit, scheduler).toList();
    }

    public final Observable takeLastBuffer(long l, TimeUnit timeunit)
    {
        return takeLast(l, timeunit).toList();
    }

    public final Observable takeLastBuffer(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return takeLast(l, timeunit, scheduler).toList();
    }

    public final Observable takeUntil(Observable observable)
    {
        return OperationTakeUntil.takeUntil(this, observable);
    }

    public final Observable takeWhile(Func1 func1)
    {
        return create(OperationTakeWhile.takeWhile(this, func1));
    }

    public final Observable takeWhileWithIndex(Func2 func2)
    {
        return create(OperationTakeWhile.takeWhileWithIndex(this, func2));
    }

    public final Plan0 then(Func1 func1)
    {
        return OperationJoinPatterns.then(this, func1);
    }

    public final Observable throttleFirst(long l, TimeUnit timeunit)
    {
        return create(OperationThrottleFirst.throttleFirst(this, l, timeunit));
    }

    public final Observable throttleFirst(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationThrottleFirst.throttleFirst(this, l, timeunit, scheduler));
    }

    public final Observable throttleLast(long l, TimeUnit timeunit)
    {
        return sample(l, timeunit);
    }

    public final Observable throttleLast(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return sample(l, timeunit, scheduler);
    }

    public final Observable throttleWithTimeout(long l, TimeUnit timeunit)
    {
        return create(OperationDebounce.debounce(this, l, timeunit));
    }

    public final Observable throttleWithTimeout(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationDebounce.debounce(this, l, timeunit, scheduler));
    }

    public final Observable timeInterval()
    {
        return create(OperationTimeInterval.timeInterval(this));
    }

    public final Observable timeInterval(Scheduler scheduler)
    {
        return create(OperationTimeInterval.timeInterval(this, scheduler));
    }

    public final Observable timeout(long l, TimeUnit timeunit)
    {
        return timeout(l, timeunit, null, Schedulers.computation());
    }

    public final Observable timeout(long l, TimeUnit timeunit, Observable observable)
    {
        return timeout(l, timeunit, observable, Schedulers.computation());
    }

    public final Observable timeout(long l, TimeUnit timeunit, Observable observable, Scheduler scheduler)
    {
        return lift(new OperatorTimeout(l, timeunit, observable, scheduler));
    }

    public final Observable timeout(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return timeout(l, timeunit, null, scheduler);
    }

    public final Observable timeout(Func0 func0, Func1 func1)
    {
        return timeout(func0, func1, ((Observable) (null)));
    }

    public final Observable timeout(Func0 func0, Func1 func1, Observable observable)
    {
        if(func1 == null)
            throw new NullPointerException("timeoutSelector is null");
        else
            return lift(new OperatorTimeoutWithSelector(func0, func1, observable));
    }

    public final Observable timeout(Func1 func1)
    {
        return timeout(((Func0) (null)), func1, ((Observable) (null)));
    }

    public final Observable timeout(Func1 func1, Observable observable)
    {
        return timeout(((Func0) (null)), func1, observable);
    }

    public final Observable timestamp()
    {
        return timestamp(Schedulers.immediate());
    }

    public final Observable timestamp(Scheduler scheduler)
    {
        return lift(new OperatorTimestamp(scheduler));
    }

    public final BlockingObservable toBlockingObservable()
    {
        return BlockingObservable.from(this);
    }

    public final Observable toList()
    {
        return lift(new OperatorToObservableList());
    }

    public final Observable toMap(Func1 func1)
    {
        return create(OperationToMap.toMap(this, func1));
    }

    public final Observable toMap(Func1 func1, Func1 func1_1)
    {
        return create(OperationToMap.toMap(this, func1, func1_1));
    }

    public final Observable toMap(Func1 func1, Func1 func1_1, Func0 func0)
    {
        return create(OperationToMap.toMap(this, func1, func1_1, func0));
    }

    public final Observable toMultimap(Func1 func1)
    {
        return create(OperationToMultimap.toMultimap(this, func1));
    }

    public final Observable toMultimap(Func1 func1, Func1 func1_1)
    {
        return create(OperationToMultimap.toMultimap(this, func1, func1_1));
    }

    public final Observable toMultimap(Func1 func1, Func1 func1_1, Func0 func0)
    {
        return create(OperationToMultimap.toMultimap(this, func1, func1_1, func0));
    }

    public final Observable toMultimap(Func1 func1, Func1 func1_1, Func0 func0, Func1 func1_2)
    {
        return create(OperationToMultimap.toMultimap(this, func1, func1_1, func0, func1_2));
    }

    public final Observable toSortedList()
    {
        return lift(new OperatorToObservableSortedList());
    }

    public final Observable toSortedList(Func2 func2)
    {
        return lift(new OperatorToObservableSortedList(func2));
    }

    public final Subscription unsafeSubscribe(Subscriber subscriber)
    {
        onSubscribe.call(subscriber);
        return subscriber;
    }

    public final Observable unsubscribeOn(Scheduler scheduler)
    {
        return lift(new OperatorUnsubscribeOn(scheduler));
    }

    public final Observable where(Func1 func1)
    {
        return filter(func1);
    }

    public final Observable window(int i)
    {
        return create(OperationWindow.window(this, i));
    }

    public final Observable window(int i, int j)
    {
        return create(OperationWindow.window(this, i, j));
    }

    public final Observable window(long l, long l1, TimeUnit timeunit)
    {
        return create(OperationWindow.window(this, l, l1, timeunit));
    }

    public final Observable window(long l, long l1, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationWindow.window(this, l, l1, timeunit, scheduler));
    }

    public final Observable window(long l, TimeUnit timeunit)
    {
        return create(OperationWindow.window(this, l, timeunit));
    }

    public final Observable window(long l, TimeUnit timeunit, int i)
    {
        return create(OperationWindow.window(this, l, timeunit, i));
    }

    public final Observable window(long l, TimeUnit timeunit, int i, Scheduler scheduler)
    {
        return create(OperationWindow.window(this, l, timeunit, i, scheduler));
    }

    public final Observable window(long l, TimeUnit timeunit, Scheduler scheduler)
    {
        return create(OperationWindow.window(this, l, timeunit, scheduler));
    }

    public final Observable window(Observable observable)
    {
        return create(OperationWindow.window(this, observable));
    }

    public final Observable window(Observable observable, Func1 func1)
    {
        return create(OperationWindow.window(this, observable, func1));
    }

    public final Observable window(Func0 func0)
    {
        return create(OperationWindow.window(this, func0));
    }

    public final Observable zip(Iterable iterable, Func2 func2)
    {
        return lift(new OperatorZipIterable(iterable, func2));
    }

    public final Observable zip(Observable observable, Func2 func2)
    {
        return zip(this, observable, func2);
    }

    private final RxJavaObservableExecutionHook hook = RxJavaPlugins.getInstance().getObservableExecutionHook();
    final OnSubscribe onSubscribe;


    // Unreferenced inner class rx/Observable$NeverObservable$1

/* anonymous class */
    class NeverObservable._cls1
        implements OnSubscribe
    {

        public volatile void call(Object obj)
        {
            call((Subscriber)obj);
        }

        public void call(Subscriber subscriber)
        {
        }

    }


    // Unreferenced inner class rx/Observable$ThrowObservable$1

/* anonymous class */
    class ThrowObservable._cls1
        implements OnSubscribe
    {

        public volatile void call(Object obj)
        {
            call((Subscriber)obj);
        }

        public void call(Subscriber subscriber)
        {
            subscriber.onError(exception);
        }

        final Throwable val$exception;

            
            {
                exception = throwable;
                super();
            }
    }

}
