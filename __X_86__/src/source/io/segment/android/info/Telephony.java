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

public class Telephony
    implements Info
{

    public Telephony()
    {
    }

    private String getRadio(TelephonyManager telephonymanager)
    {
        switch(telephonymanager.getPhoneType())
        {
        default:
            return null;

        case 0: // '\0'
            return "none";

        case 1: // '\001'
            return "gsm";

        case 2: // '\002'
            return "cdma";

        case 3: // '\003'
            return "sip";
        }
    }

    public volatile Object get(Context context)
    {
        return get(context);
    }

    public JSONObject get(Context context)
    {
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        TelephonyManager telephonymanager = (TelephonyManager)context.getSystemService("phone");
        if(telephonymanager != null)
        {
            easyjsonobject.put("carrier", telephonymanager.getNetworkOperatorName());
            easyjsonobject.put("radio", getRadio(telephonymanager));
        }
        return easyjsonobject;
    }

    public String getKey()
    {
        return "telephony";
    }
}
