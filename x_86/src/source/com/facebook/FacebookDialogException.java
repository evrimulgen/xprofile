// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;


// Referenced classes of package com.facebook:
//            FacebookException

public class FacebookDialogException extends FacebookException
{

    public FacebookDialogException(String s, int i, String s1)
    {
        super(s);
        errorCode = i;
        failingUrl = s1;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public String getFailingUrl()
    {
        return failingUrl;
    }

    static final long serialVersionUID = 1L;
    private int errorCode;
    private String failingUrl;
}
