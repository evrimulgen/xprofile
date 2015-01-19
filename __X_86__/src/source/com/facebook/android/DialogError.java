// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.android;


public class DialogError extends Throwable
{

    public DialogError(String s, int i, String s1)
    {
        super(s);
        mErrorCode = i;
        mFailingUrl = s1;
    }

    public int getErrorCode()
    {
        return mErrorCode;
    }

    public String getFailingUrl()
    {
        return mFailingUrl;
    }

    private static final long serialVersionUID = 1L;
    private int mErrorCode;
    private String mFailingUrl;
}
