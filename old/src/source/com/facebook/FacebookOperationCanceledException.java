// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;


// Referenced classes of package com.facebook:
//            FacebookException

public class FacebookOperationCanceledException extends FacebookException
{

    public FacebookOperationCanceledException()
    {
    }

    public FacebookOperationCanceledException(String s)
    {
        super(s);
    }

    public FacebookOperationCanceledException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public FacebookOperationCanceledException(Throwable throwable)
    {
        super(throwable);
    }

    static final long serialVersionUID = 1L;
}
