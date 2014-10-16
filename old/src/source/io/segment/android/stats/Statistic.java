// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.stats;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package io.segment.android.stats:
//            AtomicDouble

public class Statistic
{

    public Statistic()
    {
        sum = new AtomicDouble(0.0D);
        count = new AtomicInteger(0);
        last = new AtomicDouble(0.0D);
        lock = new AtomicBoolean(false);
    }

    public void clear()
    {
        count.set(0);
        sum.set(0.0D);
        last.set(0.0D);
        min = 0.0D;
        max = 0.0D;
        oldM = 0.0D;
        newM = 0.0D;
        oldS = 0.0D;
        newS = 0.0D;
    }

    public double getAverage()
    {
        if(count.get() > 0)
            return sum.get() / (double)count.get();
        else
            return 0.0D;
    }

    public int getCount()
    {
        return count.get();
    }

    public double getLast()
    {
        return last.get();
    }

    public double getMax()
    {
        return max;
    }

    public double getMin()
    {
        return min;
    }

    public double getStandardDeviation()
    {
        return Math.sqrt(getVariance());
    }

    public double getSum()
    {
        return sum.get();
    }

    public double getVariance()
    {
        if(count.get() > 1)
            return newS / (double)(-1 + count.get());
        else
            return 1.0D;
    }

    public String toString()
    {
        if(min == 1.0D && max == 1.0D)
        {
            return (new StringBuilder()).append(getCount()).toString();
        } else
        {
            Object aobj[] = new Object[5];
            aobj[0] = Integer.valueOf(getCount());
            aobj[1] = Double.valueOf(getMin());
            aobj[2] = Double.valueOf(getMax());
            aobj[3] = Double.valueOf(getAverage());
            aobj[4] = Double.valueOf(getStandardDeviation());
            return String.format("[Count : %d], [Min : %s], [Max : %s], [Average : %s], [Std. Dev. : %s]", aobj);
        }
    }

    public void update(double d)
    {
        int i = count.addAndGet(1);
        if(lock.compareAndSet(false, true))
        {
            if(i == 1)
            {
                newM = d;
                oldM = d;
                oldS = 0.0D;
                min = d;
                max = d;
            } else
            {
                newM = oldM + (d - oldM) / (double)i;
                newS = oldS + (d - oldM) * (d * newM);
                oldM = newM;
                oldS = newS;
            }
            if(d < min)
                min = d;
            if(d > max)
                max = d;
            lock.set(false);
        }
        sum.addAndGet(d);
        last.set(d);
    }

    private AtomicInteger count;
    private AtomicDouble last;
    private AtomicBoolean lock;
    private double max;
    private double min;
    private double newM;
    private double newS;
    private double oldM;
    private double oldS;
    private AtomicDouble sum;
}
