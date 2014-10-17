// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import com.roadtrippers.db.models.Search;

public class GooglePlace
{

    public GooglePlace()
    {
    }

    static String[] splitOnFirst(String s, char c)
    {
        int i = s.indexOf(c);
        if(i != -1)
            return (new String[] {
                s.substring(0, i), s.substring(i + 1)
            });
        else
            return (new String[] {
                s, null
            });
    }

    public String[] split()
    {
        return splitOnFirst(description, ',');
    }

    public String toString()
    {
        return description;
    }

    public Long _id;
    public String description;
    public String reference;
    public Search search;
}
