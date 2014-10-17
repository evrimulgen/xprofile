// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.stats;

import java.util.concurrent.atomic.AtomicLong;

class AtomicDouble extends Number
{

    public AtomicDouble()
    {
        this(0.0D);
    }

    public AtomicDouble(double d)
    {
        bits = new AtomicLong(Double.doubleToLongBits(d));
    }

    public final double addAndGet(double d)
    {
        return Double.longBitsToDouble(bits.addAndGet(Double.doubleToLongBits(d)));
    }

    public final boolean compareAndSet(double d, double d1)
    {
        return bits.compareAndSet(Double.doubleToLongBits(d), Double.doubleToLongBits(d1));
    }

    public double doubleValue()
    {
        return (double)floatValue();
    }

    public float floatValue()
    {
        return (float)get();
    }

    public final double get()
    {
        return Double.longBitsToDouble(bits.get());
    }

    public final double getAndSet(double d)
    {
        return Double.longBitsToDouble(bits.getAndSet(Double.doubleToLongBits(d)));
    }

    public int intValue()
    {
        return (int)get();
    }

    public long longValue()
    {
        return (long)get();
    }

    public final void set(double d)
    {
        bits.set(Double.doubleToLongBits(d));
    }

    public final boolean weakCompareAndSet(float f, float f1)
    {
        return bits.weakCompareAndSet(Double.doubleToLongBits(f), Double.doubleToLongBits(f1));
    }

    private static final long serialVersionUID = 0xdd9350ecdd29a680L;
    private AtomicLong bits;
}
