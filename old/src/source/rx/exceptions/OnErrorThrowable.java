// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.exceptions;


// Referenced classes of package rx.exceptions:
//            Exceptions

public class OnErrorThrowable extends RuntimeException
{
    public static class OnNextValue extends RuntimeException
    {

        public Object getValue()
        {
            return value;
        }

        private static final long serialVersionUID = 0xd00f486d07cc9535L;
        private final Object value;

        public OnNextValue(Object obj)
        {
            super((new StringBuilder()).append("OnError while emitting onNext value: ").append(obj).toString());
            value = obj;
        }
    }


    private OnErrorThrowable(Throwable throwable)
    {
        super(throwable);
        hasValue = false;
        value = null;
    }

    private OnErrorThrowable(Throwable throwable, Object obj)
    {
        super(throwable);
        hasValue = true;
        value = obj;
    }

    public static Throwable addValueAsLastCause(Throwable throwable, Object obj)
    {
        Throwable throwable1 = Exceptions.getFinalCause(throwable);
        if(throwable1 != null && (throwable1 instanceof OnNextValue) && ((OnNextValue)throwable1).getValue() == obj)
        {
            return throwable;
        } else
        {
            Exceptions.addCause(throwable, new OnNextValue(obj));
            return throwable;
        }
    }

    public static OnErrorThrowable from(Throwable throwable)
    {
        Throwable throwable1 = Exceptions.getFinalCause(throwable);
        if(throwable1 instanceof OnNextValue)
            return new OnErrorThrowable(throwable, ((OnNextValue)throwable1).getValue());
        else
            return new OnErrorThrowable(throwable);
    }

    public Object getValue()
    {
        return value;
    }

    public boolean isValueNull()
    {
        return hasValue;
    }

    private static final long serialVersionUID = 0xf81885d29b9202c2L;
    private final boolean hasValue;
    private final Object value;
}
