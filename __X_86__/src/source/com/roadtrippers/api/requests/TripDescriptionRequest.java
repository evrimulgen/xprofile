// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;


public class TripDescriptionRequest
{

    public TripDescriptionRequest()
    {
    }

    public void setName(String s)
    {
        name = s;
        display_name = s;
    }

    public String description;
    public String display_name;
    public String name;
    public int privacy_level;
}
