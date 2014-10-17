// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;


public class TimeInterval
{

    public TimeInterval(long l, Object obj)
    {
        value = obj;
        intervalInMilliseconds = l;
    }

    public boolean equals(Object obj)
    {
        if(this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        TimeInterval timeinterval;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        timeinterval = (TimeInterval)obj;
        if(intervalInMilliseconds != timeinterval.intervalInMilliseconds)
            return false;
        if(value != null)
            continue; /* Loop/switch isn't completed */
        if(timeinterval.value == null) goto _L1; else goto _L3
_L3:
        return false;
        if(value.equals(timeinterval.value)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public long getIntervalInMilliseconds()
    {
        return intervalInMilliseconds;
    }

    public Object getValue()
    {
        return value;
    }

    public int hashCode()
    {
        int i = 31 * (31 + (int)(intervalInMilliseconds ^ intervalInMilliseconds >>> 32));
        int j;
        if(value == null)
            j = 0;
        else
            j = value.hashCode();
        return i + j;
    }

    public String toString()
    {
        return (new StringBuilder()).append("TimeInterval [intervalInMilliseconds=").append(intervalInMilliseconds).append(", value=").append(value).append("]").toString();
    }

    private final long intervalInMilliseconds;
    private final Object value;
}
