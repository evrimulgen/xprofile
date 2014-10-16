// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;


public class Image
{

    public Image()
    {
    }

    public String getIphone_thumb_short()
    {
        if(iphone_thumb_short != null && iphone_thumb_short.startsWith("/"))
            return (new StringBuilder()).append("http://roadtrippers.com").append(iphone_thumb_short).toString();
        else
            return iphone_thumb_short;
    }

    public String iphone_detail;
    public String iphone_thumb_short;
}
