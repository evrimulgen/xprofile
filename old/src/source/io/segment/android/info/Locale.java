// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;
import android.telephony.TelephonyManager;
import io.segment.android.models.EasyJSONObject;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.info:
//            Info

public class Locale
    implements Info
{

    public Locale()
    {
    }

    public volatile Object get(Context context)
    {
        return get(context);
    }

    public JSONObject get(Context context)
    {
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        easyjsonobject.put("carrier", ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName());
        easyjsonobject.put("country", java.util.Locale.getDefault().getDisplayCountry());
        easyjsonobject.put("language", java.util.Locale.getDefault().getDisplayLanguage());
        return easyjsonobject;
    }

    public String getKey()
    {
        return "locale";
    }
}
