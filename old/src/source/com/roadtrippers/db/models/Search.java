// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.db.models;


public class Search
{

    public Search()
    {
    }

    public Search withQuery(String s)
    {
        query = s;
        return this;
    }

    public Long _id;
    public String query;
}
