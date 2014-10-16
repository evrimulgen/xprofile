// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import com.roadtrippers.api.requests.TripDescriptionRequest;
import com.roadtrippers.api.requests.TripRequest;

// Referenced classes of package com.roadtrippers.api.models:
//            Waypoint, Image, TripLeg

public class Trip extends TripRequest
    implements Cloneable
{

    public Trip()
    {
    }

    public Trip clone()
    {
        Trip trip = new Trip();
        trip.id = id;
        trip.name = name;
        trip.display_name = display_name;
        trip.description = description;
        trip.privacy_level = privacy_level;
        trip.image = image;
        trip.encoded_polyline = encoded_polyline;
        trip.polylines = polylines;
        trip.message = message;
        if(waypoints != null)
        {
            trip.waypoints = new Waypoint[waypoints.length];
            for(int i = 0; i < waypoints.length; i++)
                trip.waypoints[i] = waypoints[i].clone();

        }
        return trip;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            Trip trip = (Trip)obj;
            if(id == null ? trip.id != null : !id.equals(trip.id))
                return false;
        }
        return true;
    }

    public TripDescriptionRequest getTripDescriptionRequest()
    {
        TripDescriptionRequest tripdescriptionrequest = new TripDescriptionRequest();
        tripdescriptionrequest.setName(name);
        tripdescriptionrequest.description = description;
        tripdescriptionrequest.privacy_level = privacy_level;
        return tripdescriptionrequest;
    }

    public int hashCode()
    {
        if(id != null)
            return id.hashCode();
        else
            return 0;
    }

    public String encoded_polyline;
    public String id;
    public Image image;
    public TripLeg legs[];
    public String message;
    public String polylines[];
    public Waypoint waypoints[];
}
