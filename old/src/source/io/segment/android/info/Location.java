// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import io.segment.android.models.EasyJSONObject;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.info:
//            Info

public class Location
    implements Info
{

    public Location()
    {
    }

    public volatile Object get(Context context)
    {
        return get(context);
    }

    public JSONObject get(Context context)
    {
        LocationManager locationmanager;
        String s;
        EasyJSONObject easyjsonobject;
        locationmanager = (LocationManager)context.getSystemService("location");
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(1);
        criteria.setAccuracy(2);
        s = locationmanager.getBestProvider(criteria, true);
        easyjsonobject = new EasyJSONObject();
        if(s == null) goto _L2; else goto _L1
_L1:
        android.location.Location location1 = locationmanager.getLastKnownLocation(s);
        android.location.Location location = location1;
_L4:
        if(location != null)
        {
            easyjsonobject.put("latitude", location.getLatitude());
            easyjsonobject.put("longitude", location.getLongitude());
            easyjsonobject.put("speed", location.getSpeed());
        }
_L2:
        return easyjsonobject;
        SecurityException securityexception;
        securityexception;
        location = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getKey()
    {
        return "location";
    }
}
