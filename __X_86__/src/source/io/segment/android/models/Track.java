// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;

import java.util.Calendar;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.models:
//            BasePayload, Props, Context

public class Track extends BasePayload
{

    public Track(String s, String s1, String s2, Props props, Calendar calendar, Context context)
    {
        super(s, s1, calendar, context);
        put("action", "track");
        setEvent(s2);
        setProperties(props);
    }

    public Track(JSONObject jsonobject)
    {
        super(jsonobject);
    }

    public String getEvent()
    {
        return optString("event", null);
    }

    public Props getProperties()
    {
        JSONObject jsonobject = getObject("properties");
        if(jsonobject == null)
            return null;
        else
            return new Props(jsonobject);
    }

    public void setEvent(String s)
    {
        put("event", s);
    }

    public void setProperties(Props props)
    {
        put("properties", props);
    }

    public static final String ACTION = "track";
    private static final String EVENT_KEY = "event";
    private static final String PROPERTIES_KEY = "properties";
}
