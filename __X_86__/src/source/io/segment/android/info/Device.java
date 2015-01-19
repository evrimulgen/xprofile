// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;
import android.os.Build;
import io.segment.android.models.EasyJSONObject;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.info:
//            Info

public class Device
    implements Info
{

    public Device()
    {
    }

    public volatile Object get(Context context)
    {
        return get(context);
    }

    public JSONObject get(Context context)
    {
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        easyjsonobject.put("sdk", android.os.Build.VERSION.SDK_INT);
        easyjsonobject.put("release", android.os.Build.VERSION.RELEASE);
        easyjsonobject.put("brand", Build.BRAND);
        easyjsonobject.put("model", Build.MODEL);
        easyjsonobject.put("manufacturer", Build.MANUFACTURER);
        return easyjsonobject;
    }

    public String getKey()
    {
        return "device";
    }
}
