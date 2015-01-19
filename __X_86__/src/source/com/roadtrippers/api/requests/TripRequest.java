// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.requests;

import com.roadtrippers.api.models.Trip;
import io.segment.android.models.EasyJSONObject;
import io.segment.android.models.Props;
import java.util.List;

// Referenced classes of package com.roadtrippers.api.requests:
//            TripDescriptionRequest, WaypointRequest

public class TripRequest extends TripDescriptionRequest
{

    public TripRequest()
    {
    }

    public static TripRequest from(Trip trip)
    {
        TripRequest triprequest = new TripRequest();
        triprequest.name = trip.name;
        triprequest.display_name = trip.display_name;
        triprequest.privacy_level = trip.privacy_level;
        triprequest.description = trip.description;
        triprequest.waypoints = WaypointRequest.listFrom(trip);
        return triprequest;
    }

    public Props getAnalyticsProps()
    {
        boolean flag = true;
        Props props = new Props();
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        boolean flag1;
        List list;
        int i;
        if(privacy_level == 0)
            flag1 = flag;
        else
            flag1 = false;
        easyjsonobject.put("discoverable", flag1);
        if(description == null || description.length() <= 0)
            flag = false;
        easyjsonobject.put("has_description", flag);
        list = waypoints;
        i = 0;
        if(list != null)
            i = waypoints.size();
        easyjsonobject.put("item_count", i);
        props.put("label", easyjsonobject.toString());
        return props;
    }

    public List waypoints;
}
