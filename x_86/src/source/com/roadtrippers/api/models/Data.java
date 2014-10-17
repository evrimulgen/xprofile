// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import java.util.HashMap;
import java.util.Map;

class Data
{

    Data()
    {
        additionalProperties = new HashMap();
    }

    public Map getAdditionalProperties()
    {
        return additionalProperties;
    }

    public int getEntry_id()
    {
        return entry_id;
    }

    public String getEntry_type()
    {
        return entry_type;
    }

    public void setAdditionalProperty(String s, Object obj)
    {
        additionalProperties.put(s, obj);
    }

    public void setEntry_id(int i)
    {
        entry_id = i;
    }

    public void setEntry_type(String s)
    {
        entry_type = s;
    }

    private Map additionalProperties;
    private int entry_id;
    private String entry_type;
}
