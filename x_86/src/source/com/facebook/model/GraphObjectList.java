// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.model;

import java.util.List;
import org.json.JSONArray;

public interface GraphObjectList
    extends List
{

    public abstract GraphObjectList castToListOf(Class class1);

    public abstract JSONArray getInnerJSONArray();
}
