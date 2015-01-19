// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobsandgeeks.saripaar;


public abstract class Rule
{

    public Rule(String s)
    {
        mFailureMessage = s;
    }

    public String getFailureMessage()
    {
        return mFailureMessage;
    }

    public abstract boolean isValid(Object obj);

    public void setFailureMessage(String s)
    {
        mFailureMessage = s;
    }

    private String mFailureMessage;
}
