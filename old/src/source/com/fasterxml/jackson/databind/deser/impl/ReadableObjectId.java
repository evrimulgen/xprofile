// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;

public class ReadableObjectId
{

    public ReadableObjectId(Object obj)
    {
        id = obj;
    }

    public void bindItem(Object obj)
        throws IOException
    {
        if(item != null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Already had POJO for id (").append(id.getClass().getName()).append(") [").append(id).append("]").toString());
        } else
        {
            item = obj;
            return;
        }
    }

    public final Object id;
    public Object item;
}
