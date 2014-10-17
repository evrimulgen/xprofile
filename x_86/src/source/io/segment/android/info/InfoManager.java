// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;
import io.segment.android.Options;
import io.segment.android.models.EasyJSONObject;
import java.util.*;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.info:
//            Build, Device, Display, Locale, 
//            Location, Telephony, Wifi, Info

public class InfoManager
{

    public InfoManager(Options options)
    {
        managers = new LinkedList();
        managers.add(new Build());
        managers.add(new Device());
        managers.add(new Display());
        managers.add(new Locale());
        if(options.shouldSendLocation())
            managers.add(new Location());
        managers.add(new Telephony());
        managers.add(new Wifi());
    }

    public JSONObject build(Context context)
    {
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        Iterator iterator = managers.iterator();
        do
        {
            String s;
            Object obj;
            do
            {
                if(!iterator.hasNext())
                    return easyjsonobject;
                Info info = (Info)iterator.next();
                s = info.getKey();
                obj = info.get(context);
            } while(obj == null);
            easyjsonobject.putObject(s, obj);
        } while(true);
    }

    private List managers;
}
