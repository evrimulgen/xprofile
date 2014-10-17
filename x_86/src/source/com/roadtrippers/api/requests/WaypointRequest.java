// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;

import android.location.Location;
import com.roadtrippers.api.models.Trip;
import com.roadtrippers.api.models.Waypoint;
import java.util.ArrayList;
import java.util.List;

public class WaypointRequest
{
    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(com/roadtrippers/api/requests/WaypointRequest$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        private static final Type $VALUES[];
        public static final Type byway;
        public static final Type geo;
        public static final Type poi;
        public static final Type via;

        static 
        {
            poi = new Type("poi", 0);
            geo = new Type("geo", 1);
            via = new Type("via", 2);
            byway = new Type("byway", 3);
            Type atype[] = new Type[4];
            atype[0] = poi;
            atype[1] = geo;
            atype[2] = via;
            atype[3] = byway;
            $VALUES = atype;
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public WaypointRequest()
    {
    }

    static WaypointRequest from(Waypoint waypoint)
    {
        WaypointRequest waypointrequest = new WaypointRequest();
        waypointrequest.name = waypoint.name;
        waypointrequest.type = waypoint.type;
        waypointrequest.location = waypoint.location;
        waypointrequest.poi_id = waypoint.poi_id;
        waypointrequest.start_date = waypoint.start_date;
        return waypointrequest;
    }

    public static List listFrom(Trip trip)
    {
        Object obj;
        if(trip == null || trip.waypoints == null)
        {
            obj = null;
        } else
        {
            Waypoint awaypoint[] = trip.waypoints;
            obj = new ArrayList(awaypoint.length);
            int i = awaypoint.length;
            for(int j = 0; j < i; j++)
            {
                Waypoint waypoint = awaypoint[j];
                if(waypoint.location != null)
                    ((List) (obj)).add(from(waypoint));
            }

            if(((List) (obj)).size() == 1 && trip.id != null)
            {
                ((List) (obj)).add(new WaypointRequest());
                return ((List) (obj));
            }
        }
        return ((List) (obj));
    }

    public Location getAndroidLocation()
    {
        if(location != null)
        {
            Location location1 = new Location("");
            location1.setLatitude(location[1]);
            location1.setLongitude(location[0]);
            return location1;
        } else
        {
            return null;
        }
    }

    public String getLocationString()
    {
        if(location != null)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Double.valueOf(location[1]);
            aobj[1] = Double.valueOf(location[0]);
            return String.format("%f,%f", aobj);
        } else
        {
            return null;
        }
    }

    public String toString()
    {
        return name;
    }

    public double location[];
    public String name;
    public String poi_id;
    public String start_date;
    public Type type;
}
