// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


class TypeOrFailure
{

    public TypeOrFailure(LoadCallback.Failure failure)
    {
        mFailure = failure;
    }

    public TypeOrFailure(Object obj)
    {
        mType = obj;
    }

    public LoadCallback.Failure getFailure()
    {
        return mFailure;
    }

    public Object getType()
    {
        return mType;
    }

    private LoadCallback.Failure mFailure;
    private Object mType;
}
