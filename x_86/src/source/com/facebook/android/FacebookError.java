// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.android;


public class FacebookError extends RuntimeException
{

    public FacebookError(String s)
    {
        super(s);
        mErrorCode = 0;
    }

    public FacebookError(String s, String s1, int i)
    {
        super(s);
        mErrorCode = 0;
        mErrorType = s1;
        mErrorCode = i;
    }

    public int getErrorCode()
    {
        return mErrorCode;
    }

    public String getErrorType()
    {
        return mErrorType;
    }

    private static final long serialVersionUID = 1L;
    private int mErrorCode;
    private String mErrorType;
}
