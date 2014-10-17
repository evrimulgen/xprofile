// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.schedulers;


public final class Timestamped
{

    public Timestamped(long l, Object obj)
    {
        value = obj;
        timestampMillis = l;
    }

    public boolean equals(Object obj)
    {
        if(this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        Timestamped timestamped;
        if(!(obj instanceof Timestamped))
            return false;
        timestamped = (Timestamped)obj;
        if(timestampMillis != timestamped.timestampMillis)
            return false;
        if(value != null)
            continue; /* Loop/switch isn't completed */
        if(timestamped.value == null) goto _L1; else goto _L3
_L3:
        return false;
        if(value.equals(timestamped.value)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public long getTimestampMillis()
    {
        return timestampMillis;
    }

    public Object getValue()
    {
        return value;
    }

    public int hashCode()
    {
        int i = 31 * (31 + (int)(timestampMillis ^ timestampMillis));
        int j;
        if(value == null)
            j = 0;
        else
            j = value.hashCode();
        return i + j;
    }

    public String toString()
    {
        Object aobj[] = new Object[2];
        aobj[0] = Long.valueOf(timestampMillis);
        aobj[1] = value.toString();
        return String.format("Timestamped(timestampMillis = %d, value = %s)", aobj);
    }

    private final long timestampMillis;
    private final Object value;
}
