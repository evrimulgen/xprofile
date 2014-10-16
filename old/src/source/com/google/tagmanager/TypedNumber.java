// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


class TypedNumber extends Number
    implements Comparable
{

    private TypedNumber(double d)
    {
        mDouble = d;
        mIsInt64 = false;
    }

    private TypedNumber(long l)
    {
        mInt64 = l;
        mIsInt64 = true;
    }

    public static TypedNumber numberWithDouble(Double double1)
    {
        return new TypedNumber(double1.doubleValue());
    }

    public static TypedNumber numberWithInt64(long l)
    {
        return new TypedNumber(l);
    }

    public static TypedNumber numberWithString(String s)
        throws NumberFormatException
    {
        TypedNumber typednumber;
        try
        {
            typednumber = new TypedNumber(Long.parseLong(s));
        }
        catch(NumberFormatException numberformatexception)
        {
            TypedNumber typednumber1;
            try
            {
                typednumber1 = new TypedNumber(Double.parseDouble(s));
            }
            catch(NumberFormatException numberformatexception1)
            {
                throw new NumberFormatException((new StringBuilder()).append(s).append(" is not a valid TypedNumber").toString());
            }
            return typednumber1;
        }
        return typednumber;
    }

    public byte byteValue()
    {
        return (byte)(int)longValue();
    }

    public int compareTo(TypedNumber typednumber)
    {
        if(isInt64() && typednumber.isInt64())
            return (new Long(mInt64)).compareTo(Long.valueOf(typednumber.mInt64));
        else
            return Double.compare(doubleValue(), typednumber.doubleValue());
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((TypedNumber)obj);
    }

    public double doubleValue()
    {
        if(isInt64())
            return (double)mInt64;
        else
            return mDouble;
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof TypedNumber) && compareTo((TypedNumber)obj) == 0;
    }

    public float floatValue()
    {
        return (float)doubleValue();
    }

    public int hashCode()
    {
        return (new Long(longValue())).hashCode();
    }

    public short int16Value()
    {
        return (short)(int)longValue();
    }

    public int int32Value()
    {
        return (int)longValue();
    }

    public long int64Value()
    {
        if(isInt64())
            return mInt64;
        else
            return (long)mDouble;
    }

    public int intValue()
    {
        return int32Value();
    }

    public boolean isDouble()
    {
        return !isInt64();
    }

    public boolean isInt64()
    {
        return mIsInt64;
    }

    public long longValue()
    {
        return int64Value();
    }

    public short shortValue()
    {
        return int16Value();
    }

    public String toString()
    {
        if(isInt64())
            return Long.toString(mInt64);
        else
            return Double.toString(mDouble);
    }

    private double mDouble;
    private long mInt64;
    private boolean mIsInt64;
}
