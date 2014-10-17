// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import com.roadtrippers.db.models.Search;

public class RTAutocompleteSuggestion
{

    public RTAutocompleteSuggestion()
    {
    }

    public String getAddress1()
    {
        return address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public String getCity()
    {
        return city;
    }

    public Integer getId()
    {
        return id;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public String getMongo_id()
    {
        return mongo_id;
    }

    public String getName()
    {
        return name;
    }

    public String getState()
    {
        return state;
    }

    public void setAddress1(String s)
    {
        address1 = s;
    }

    public void setAddress2(String s)
    {
        address2 = s;
    }

    public void setCity(String s)
    {
        city = s;
    }

    public void setId(Integer integer)
    {
        id = integer;
    }

    public void setLatitude(Double double1)
    {
        latitude = double1;
    }

    public void setLongitude(Double double1)
    {
        longitude = double1;
    }

    public void setMongo_id(String s)
    {
        mongo_id = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setState(String s)
    {
        state = s;
    }

    public String toString()
    {
        return name;
    }

    private String address1;
    private String address2;
    private String city;
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String mongo_id;
    private String name;
    public Search search;
    private String state;
}
