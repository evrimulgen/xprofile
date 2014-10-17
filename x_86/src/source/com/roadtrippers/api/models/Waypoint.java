// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import com.google.android.gms.maps.model.LatLng;
import com.roadtrippers.api.requests.WaypointRequest;

// Referenced classes of package com.roadtrippers.api.models:
//            Poi

public class Waypoint extends WaypointRequest
{

    public Waypoint()
    {
    }

    public static Waypoint from(Poi poi1)
    {
        Waypoint waypoint = new Waypoint();
        waypoint.type = com.roadtrippers.api.requests.WaypointRequest.Type.poi;
        waypoint.poi = poi1;
        waypoint.poi_id = poi1.id;
        waypoint.name = poi1.name;
        double ad[] = new double[2];
        ad[0] = poi1.longitude;
        ad[1] = poi1.latitude;
        waypoint.location = ad;
        return waypoint;
    }

    public Waypoint clone()
    {
        Waypoint waypoint = new Waypoint();
        waypoint.id = id;
        waypoint.name = name;
        waypoint.type = type;
        waypoint.location = location;
        waypoint.poi_id = poi_id;
        waypoint.poi = poi;
        waypoint.start_date = start_date;
        return waypoint;
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
            Waypoint waypoint = (Waypoint)obj;
            if(id == null ? waypoint.id != null : !id.equals(waypoint.id))
                return false;
            if(name == null ? waypoint.name != null : !name.equals(waypoint.name))
                return false;
        }
        return true;
    }

    public String getCellText()
    {
        static class _cls1
        {

            static final int $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type[];

            static 
            {
                $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type = new int[com.roadtrippers.api.requests.WaypointRequest.Type.values().length];
                try
                {
                    $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type[com.roadtrippers.api.requests.WaypointRequest.Type.poi.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type[com.roadtrippers.api.requests.WaypointRequest.Type.geo.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$roadtrippers$api$requests$WaypointRequest$Type[com.roadtrippers.api.requests.WaypointRequest.Type.via.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.roadtrippers.api.requests.WaypointRequest.Type[type.ordinal()])
        {
        default:
            return null;

        case 1: // '\001'
            return poi.name;

        case 2: // '\002'
            return name;

        case 3: // '\003'
            return "Via:";
        }
    }

    public int getImageResource()
    {
        switch(_cls1..SwitchMap.com.roadtrippers.api.requests.WaypointRequest.Type[type.ordinal()])
        {
        default:
            return 0;

        case 1: // '\001'
            throw new RuntimeException("Load the remote image, you fool");

        case 2: // '\002'
            return 0x7f0200ec;

        case 3: // '\003'
            return 0x7f0201c0;
        }
    }

    public LatLng getLatLng()
    {
        if(location != null)
            return new LatLng(location[1], location[0]);
        else
            return null;
    }

    public String getSubText()
    {
        Object aobj[];
        switch(_cls1..SwitchMap.com.roadtrippers.api.requests.WaypointRequest.Type[type.ordinal()])
        {
        default:
            return null;

        case 1: // '\001'
            return poi.subtitle;

        case 2: // '\002'
            return "";

        case 3: // '\003'
            aobj = new Object[2];
            break;
        }
        aobj[0] = Double.valueOf(location[1]);
        aobj[1] = Double.valueOf(location[0]);
        return String.format("%f %f", aobj);
    }

    public int hashCode()
    {
        if(id != null)
            return id.hashCode();
        else
            return 0;
    }

    public String id;
    public Poi poi;
}
