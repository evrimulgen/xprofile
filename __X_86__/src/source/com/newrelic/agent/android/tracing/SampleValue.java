// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.tracing;


public class SampleValue
{

    public SampleValue(double d)
    {
        value = Double.valueOf(0.0D);
        setValue(d);
    }

    public SampleValue(long l)
    {
        value = Double.valueOf(0.0D);
        setValue(l);
    }

    public Double asDouble()
    {
        return value;
    }

    public Long asLong()
    {
        return Long.valueOf(value.longValue());
    }

    public Number getValue()
    {
        if(isDouble)
            return asDouble();
        else
            return asLong();
    }

    public boolean isDouble()
    {
        return isDouble;
    }

    public void setDouble(boolean flag)
    {
        isDouble = flag;
    }

    public void setValue(double d)
    {
        value = Double.valueOf(d);
        isDouble = true;
    }

    public void setValue(long l)
    {
        value = Double.valueOf(l);
        isDouble = false;
    }

    private boolean isDouble;
    private Double value;
}
