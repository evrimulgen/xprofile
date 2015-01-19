// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import io.segment.android.models.EasyJSONObject;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.info:
//            Info

public class Wifi
    implements Info
{

    public Wifi()
    {
    }

    public volatile Object get(Context context)
    {
        return get(context);
    }

    public JSONObject get(Context context)
    {
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        if(context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
        {
            NetworkInfo networkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(1);
            easyjsonobject.put("available", networkinfo.isAvailable());
            easyjsonobject.put("connected", networkinfo.isConnected());
        }
        return easyjsonobject;
    }

    public String getKey()
    {
        return "wifi";
    }
}
