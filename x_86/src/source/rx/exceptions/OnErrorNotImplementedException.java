// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.exceptions;


public class OnErrorNotImplementedException extends RuntimeException
{

    public OnErrorNotImplementedException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public OnErrorNotImplementedException(Throwable throwable)
    {
        super(throwable.getMessage(), throwable);
    }

    private static final long serialVersionUID = 0xa895f6ec1a8e9594L;
}
