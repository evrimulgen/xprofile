// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;


public class AddGalleryImageRequest
{

    public AddGalleryImageRequest(String s, String s1)
    {
        caption = s;
        image_data = s1;
    }

    public String caption;
    public String image_data;
}
