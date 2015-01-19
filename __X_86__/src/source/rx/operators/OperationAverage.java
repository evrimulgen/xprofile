// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.*;
import rx.functions.Func1;
import rx.functions.Func2;

public final class OperationAverage
{
    public static final class AverageDoubleExtractor
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return source.unsafeSubscribe(new AverageObserver(observer));
        }

        final Observable source;
        final Func1 valueExtractor;

        public AverageDoubleExtractor(Observable observable, Func1 func1)
        {
            source = observable;
            valueExtractor = func1;
        }
    }

    private final class AverageDoubleExtractor.AverageObserver extends Subscriber
    {

        public void onCompleted()
        {
            if(count > 0)
            {
                try
                {
                    observer.onNext(Double.valueOf(sum / (double)count));
                }
                catch(Throwable throwable)
                {
                    observer.onError(throwable);
                    return;
                }
                observer.onCompleted();
                return;
            } else
            {
                observer.onError(new IllegalArgumentException("Sequence contains no elements"));
                return;
            }
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            sum = sum + ((Double)valueExtractor.call(obj)).doubleValue();
            count = 1 + count;
        }

        int count;
        final Observer observer;
        double sum;
        final AverageDoubleExtractor this$0;

        public AverageDoubleExtractor.AverageObserver(Observer observer1)
        {
            this$0 = AverageDoubleExtractor.this;
            super();
            observer = observer1;
        }
    }

    public static final class AverageFloatExtractor
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return source.unsafeSubscribe(new AverageObserver(observer));
        }

        final Observable source;
        final Func1 valueExtractor;

        public AverageFloatExtractor(Observable observable, Func1 func1)
        {
            source = observable;
            valueExtractor = func1;
        }
    }

    private final class AverageFloatExtractor.AverageObserver extends Subscriber
    {

        public void onCompleted()
        {
            if(count > 0)
            {
                try
                {
                    observer.onNext(Float.valueOf(sum / (float)count));
                }
                catch(Throwable throwable)
                {
                    observer.onError(throwable);
                    return;
                }
                observer.onCompleted();
                return;
            } else
            {
                observer.onError(new IllegalArgumentException("Sequence contains no elements"));
                return;
            }
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            sum = sum + ((Float)valueExtractor.call(obj)).floatValue();
            count = 1 + count;
        }

        int count;
        final Observer observer;
        float sum;
        final AverageFloatExtractor this$0;

        public AverageFloatExtractor.AverageObserver(Observer observer1)
        {
            this$0 = AverageFloatExtractor.this;
            super();
            observer = observer1;
        }
    }

    public static final class AverageIntegerExtractor
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return source.unsafeSubscribe(new AverageObserver(observer));
        }

        final Observable source;
        final Func1 valueExtractor;

        public AverageIntegerExtractor(Observable observable, Func1 func1)
        {
            source = observable;
            valueExtractor = func1;
        }
    }

    private final class AverageIntegerExtractor.AverageObserver extends Subscriber
    {

        public void onCompleted()
        {
            if(count > 0)
            {
                try
                {
                    observer.onNext(Integer.valueOf(sum / count));
                }
                catch(Throwable throwable)
                {
                    observer.onError(throwable);
                    return;
                }
                observer.onCompleted();
                return;
            } else
            {
                observer.onError(new IllegalArgumentException("Sequence contains no elements"));
                return;
            }
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            sum = sum + ((Integer)valueExtractor.call(obj)).intValue();
            count = 1 + count;
        }

        int count;
        final Observer observer;
        int sum;
        final AverageIntegerExtractor this$0;

        public AverageIntegerExtractor.AverageObserver(Observer observer1)
        {
            this$0 = AverageIntegerExtractor.this;
            super();
            observer = observer1;
        }
    }

    public static final class AverageLongExtractor
        implements rx.Observable.OnSubscribeFunc
    {

        public Subscription onSubscribe(Observer observer)
        {
            return source.unsafeSubscribe(new AverageObserver(observer));
        }

        final Observable source;
        final Func1 valueExtractor;

        public AverageLongExtractor(Observable observable, Func1 func1)
        {
            source = observable;
            valueExtractor = func1;
        }
    }

    private final class AverageLongExtractor.AverageObserver extends Subscriber
    {

        public void onCompleted()
        {
            if(count > 0)
            {
                try
                {
                    observer.onNext(Long.valueOf(sum / (long)count));
                }
                catch(Throwable throwable)
                {
                    observer.onError(throwable);
                    return;
                }
                observer.onCompleted();
                return;
            } else
            {
                observer.onError(new IllegalArgumentException("Sequence contains no elements"));
                return;
            }
        }

        public void onError(Throwable throwable)
        {
            observer.onError(throwable);
        }

        public void onNext(Object obj)
        {
            sum = sum + ((Long)valueExtractor.call(obj)).longValue();
            count = 1 + count;
        }

        int count;
        final Observer observer;
        long sum;
        final AverageLongExtractor this$0;

        public AverageLongExtractor.AverageObserver(Observer observer1)
        {
            this$0 = AverageLongExtractor.this;
            super();
            observer = observer1;
        }
    }

    private static final class Tuple2
    {

        private final Integer count;
        private final Object current;



        private Tuple2(Object obj, Integer integer)
        {
            current = obj;
            count = integer;
        }

    }


    public OperationAverage()
    {
    }

    public static Observable average(Observable observable)
    {
        return observable.reduce(new Tuple2(Integer.valueOf(0), Integer.valueOf(0)), new Func2() {

            public volatile Object call(Object obj, Object obj1)
            {
                return call((Tuple2)obj, (Integer)obj1);
            }

            public Tuple2 call(Tuple2 tuple2, Integer integer)
            {
                return new Tuple2(Integer.valueOf(((Integer)tuple2.current).intValue() + integer.intValue()), Integer.valueOf(1 + tuple2.count.intValue()));
            }

        }
).map(new Func1() {

            public Integer call(Tuple2 tuple2)
            {
                if(tuple2.count.intValue() == 0)
                    throw new IllegalArgumentException("Sequence contains no elements");
                else
                    return Integer.valueOf(((Integer)tuple2.current).intValue() / tuple2.count.intValue());
            }

            public volatile Object call(Object obj)
            {
                return call((Tuple2)obj);
            }

        }
);
    }

    public static Observable averageDoubles(Observable observable)
    {
        return observable.reduce(new Tuple2(Double.valueOf(0.0D), Integer.valueOf(0)), new Func2() {

            public volatile Object call(Object obj, Object obj1)
            {
                return call((Tuple2)obj, (Double)obj1);
            }

            public Tuple2 call(Tuple2 tuple2, Double double1)
            {
                return new Tuple2(Double.valueOf(((Double)tuple2.current).doubleValue() + double1.doubleValue()), Integer.valueOf(1 + tuple2.count.intValue()));
            }

        }
).map(new Func1() {

            public Double call(Tuple2 tuple2)
            {
                if(tuple2.count.intValue() == 0)
                    throw new IllegalArgumentException("Sequence contains no elements");
                else
                    return Double.valueOf(((Double)tuple2.current).doubleValue() / (double)tuple2.count.intValue());
            }

            public volatile Object call(Object obj)
            {
                return call((Tuple2)obj);
            }

        }
);
    }

    public static Observable averageFloats(Observable observable)
    {
        return observable.reduce(new Tuple2(Float.valueOf(0.0F), Integer.valueOf(0)), new Func2() {

            public volatile Object call(Object obj, Object obj1)
            {
                return call((Tuple2)obj, (Float)obj1);
            }

            public Tuple2 call(Tuple2 tuple2, Float float1)
            {
                return new Tuple2(Float.valueOf(((Float)tuple2.current).floatValue() + float1.floatValue()), Integer.valueOf(1 + tuple2.count.intValue()));
            }

        }
).map(new Func1() {

            public Float call(Tuple2 tuple2)
            {
                if(tuple2.count.intValue() == 0)
                    throw new IllegalArgumentException("Sequence contains no elements");
                else
                    return Float.valueOf(((Float)tuple2.current).floatValue() / (float)tuple2.count.intValue());
            }

            public volatile Object call(Object obj)
            {
                return call((Tuple2)obj);
            }

        }
);
    }

    public static Observable averageLongs(Observable observable)
    {
        return observable.reduce(new Tuple2(Long.valueOf(0L), Integer.valueOf(0)), new Func2() {

            public volatile Object call(Object obj, Object obj1)
            {
                return call((Tuple2)obj, (Long)obj1);
            }

            public Tuple2 call(Tuple2 tuple2, Long long1)
            {
                return new Tuple2(Long.valueOf(((Long)tuple2.current).longValue() + long1.longValue()), Integer.valueOf(1 + tuple2.count.intValue()));
            }

        }
).map(new Func1() {

            public Long call(Tuple2 tuple2)
            {
                if(tuple2.count.intValue() == 0)
                    throw new IllegalArgumentException("Sequence contains no elements");
                else
                    return Long.valueOf(((Long)tuple2.current).longValue() / (long)tuple2.count.intValue());
            }

            public volatile Object call(Object obj)
            {
                return call((Tuple2)obj);
            }

        }
);
    }
}
