// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


class ObjectAndStatic
{

    ObjectAndStatic(Object obj, boolean flag)
    {
        mObject = obj;
        mIsStatic = flag;
    }

    public Object getObject()
    {
        return mObject;
    }

    public boolean isStatic()
    {
        return mIsStatic;
    }

    private final boolean mIsStatic;
    private final Object mObject;
}
