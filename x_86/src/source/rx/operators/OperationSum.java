// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Observable;
import rx.functions.Func2;

public final class OperationSum
{

    public OperationSum()
    {
    }

    public static Observable sumAtLeastOneDoubles(Observable observable)
    {
        return observable.reduce(ACCUM_DOUBLE);
    }

    public static Observable sumAtLeastOneFloats(Observable observable)
    {
        return observable.reduce(ACCUM_FLOAT);
    }

    public static Observable sumAtLeastOneIntegers(Observable observable)
    {
        return observable.reduce(ACCUM_INT);
    }

    public static Observable sumAtLeastOneLongs(Observable observable)
    {
        return observable.reduce(ACCUM_LONG);
    }

    public static Observable sumDoubles(Observable observable)
    {
        return observable.reduce(Double.valueOf(0.0D), ACCUM_DOUBLE);
    }

    public static Observable sumFloats(Observable observable)
    {
        return observable.reduce(Float.valueOf(0.0F), ACCUM_FLOAT);
    }

    public static Observable sumIntegers(Observable observable)
    {
        return observable.reduce(Integer.valueOf(0), ACCUM_INT);
    }

    public static Observable sumLongs(Observable observable)
    {
        return observable.reduce(Long.valueOf(0L), ACCUM_LONG);
    }

    private static final Func2 ACCUM_DOUBLE = new Func2() {

        public Double call(Double double1, Double double2)
        {
            return Double.valueOf(double1.doubleValue() + double2.doubleValue());
        }

        public volatile Object call(Object obj, Object obj1)
        {
            return call((Double)obj, (Double)obj1);
        }

    }
;
    private static final Func2 ACCUM_FLOAT = new Func2() {

        public Float call(Float float1, Float float2)
        {
            return Float.valueOf(float1.floatValue() + float2.floatValue());
        }

        public volatile Object call(Object obj, Object obj1)
        {
            return call((Float)obj, (Float)obj1);
        }

    }
;
    private static final Func2 ACCUM_INT = new Func2() {

        public Integer call(Integer integer, Integer integer1)
        {
            return Integer.valueOf(integer.intValue() + integer1.intValue());
        }

        public volatile Object call(Object obj, Object obj1)
        {
            return call((Integer)obj, (Integer)obj1);
        }

    }
;
    private static final Func2 ACCUM_LONG = new Func2() {

        public Long call(Long long1, Long long2)
        {
            return Long.valueOf(long1.longValue() + long2.longValue());
        }

        public volatile Object call(Object obj, Object obj1)
        {
            return call((Long)obj, (Long)obj1);
        }

    }
;

}
