// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.roadtrippers.api.models:
//            Poi, BucketListAddRequest, Data

public class BucketListEntry
{

    public BucketListEntry()
    {
        additionalProperties = new HashMap();
    }

    public static BucketListEntry fromPoi(Poi poi)
    {
        BucketListEntry bucketlistentry = new BucketListEntry();
        bucketlistentry.setId(Integer.parseInt(poi.id));
        bucketlistentry.setEntry_type("poi");
        bucketlistentry.setEntry_id(bucketlistentry.getId());
        bucketlistentry.setGroup(poi.group);
        bucketlistentry.setLatitude(poi.latitude);
        bucketlistentry.setLongitude(poi.longitude);
        bucketlistentry.setUrl(poi.link);
        return bucketlistentry;
    }

    public BucketListAddRequest asAddRequest()
    {
        BucketListAddRequest bucketlistaddrequest = new BucketListAddRequest();
        bucketlistaddrequest.entry.setEntry_id(getId());
        bucketlistaddrequest.entry.setEntry_type(getEntry_type());
        return bucketlistaddrequest;
    }

    public Poi asPoi()
    {
        Poi poi = new Poi();
        poi.id = String.valueOf(id);
        poi.latitude = latitude;
        poi.longitude = longitude;
        poi.group = "saved_places";
        poi.link = url;
        return poi;
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

    public String getGroup()
    {
        return group;
    }

    public int getId()
    {
        return id;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return url;
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

    public void setGroup(String s)
    {
        group = s;
    }

    public void setId(int i)
    {
        id = i;
    }

    public void setLatitude(double d)
    {
        latitude = d;
    }

    public void setLongitude(double d)
    {
        longitude = d;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setUrl(String s)
    {
        url = s;
    }

    private Map additionalProperties;
    private int entry_id;
    private String entry_type;
    private String group;
    private int id;
    private double latitude;
    private double longitude;
    private String name;
    private String url;
}
