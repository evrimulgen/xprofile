// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;


public class SearchRequest
{

    public SearchRequest(String s, double ad[], String s1, int i)
    {
        categories = s;
        bounds = ad;
        profile = s1;
        per_page = i;
    }

    public final double bounds[];
    public final String categories;
    public final int per_page;
    public final String profile;
}
