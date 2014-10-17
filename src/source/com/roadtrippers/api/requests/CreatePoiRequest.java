// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;


public class CreatePoiRequest
{

    public CreatePoiRequest()
    {
    }

    public CreatePoiRequest(String s, String s1, String s2, String s3, String s4, double d, 
            double d1, String s5, String s6)
    {
        name = s;
        latitude = d;
        longitude = d1;
        address1 = s1;
        city = s2;
        state = s3;
        country = s5;
        zip_code = s4;
        status = s6;
    }

    public String address1;
    public String city;
    public String country;
    public double latitude;
    public double longitude;
    public String name;
    public String primary_image_data;
    public String state;
    public String status;
    public String zip_code;
}
