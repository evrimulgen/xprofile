// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.utils;

import io.segment.android.models.EasyJSONObject;
import java.util.*;
import org.json.JSONObject;

public class Parameters
{

    public Parameters()
    {
    }

    public static EasyJSONObject move(JSONObject jsonobject, Map map)
    {
        EasyJSONObject easyjsonobject = new EasyJSONObject(jsonobject);
        Iterator iterator = map.keySet().iterator();
        do
        {
            String s;
            do
            {
                if(!iterator.hasNext())
                    return easyjsonobject;
                s = (String)iterator.next();
            } while(!easyjsonobject.has(s));
            String s1 = (String)map.get(s);
            Object obj = easyjsonobject.get(s);
            easyjsonobject.remove(s);
            easyjsonobject.put(s1, obj);
        } while(true);
    }
}
