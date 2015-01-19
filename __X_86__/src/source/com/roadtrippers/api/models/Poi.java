// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.roadtrippers.api.requests.CreatePoiRequest;

// Referenced classes of package com.roadtrippers.api.models:
//            Comment, Image

public class Poi extends CreatePoiRequest
{

    public Poi()
    {
    }

    public Poi(String s)
    {
        id = s;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            Poi poi = (Poi)obj;
            if(!id.equals(poi.id))
                return false;
        }
        return true;
    }

    public Comment getLastComment(Context context)
    {
        if(hasComments())
            return comments[0];
        else
            return Comment.defaultCommentWithPoi(context, this);
    }

    public LatLng getLatLng()
    {
        return new LatLng(latitude, longitude);
    }

    public Location getLocation()
    {
        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }

    public String getSecondLine()
    {
        String s = "";
        if(city != null)
            s = (new StringBuilder()).append(s).append(city).append(", ").toString();
        if(state != null)
            s = (new StringBuilder()).append(s).append(state).append(" ").toString();
        if(zip_code != null)
            s = (new StringBuilder()).append(s).append(zip_code).toString();
        return s;
    }

    public String getStatus()
    {
        return status;
    }

    boolean hasComments()
    {
        return comments != null && comments.length > 0;
    }

    public boolean hasMoreComments()
    {
        return comments != null && comments.length > 1;
    }

    public int hashCode()
    {
        return id.hashCode();
    }

    public void setStatus(String s)
    {
        status = s;
    }

    public Comment comments[];
    public String description;
    public int display_rating;
    public String group;
    public String id;
    public Image image;
    public transient boolean isSearchResult;
    public String link;
    public String phone;
    public String status;
    public String subtitle;
    public String website;
}
