// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.exceptions;


public class OnErrorFailedException extends RuntimeException
{

    public OnErrorFailedException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public OnErrorFailedException(Throwable throwable)
    {
        super(throwable.getMessage(), throwable);
    }

    private static final long serialVersionUID = 0xfa2e62320074c675L;
}
