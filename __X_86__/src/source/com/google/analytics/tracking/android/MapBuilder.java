// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.analytics.tracking.android:
//            GAUsage, Log, Utils

public class MapBuilder
{

    public MapBuilder()
    {
        map = new HashMap();
    }

    static String booleanToString(Boolean boolean1)
    {
        if(boolean1 == null)
            return null;
        if(boolean1.booleanValue())
            return "1";
        else
            return "0";
    }

    public static MapBuilder createAppView()
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.CONSTRUCT_APP_VIEW);
        MapBuilder mapbuilder = new MapBuilder();
        mapbuilder.set("&t", "appview");
        return mapbuilder;
    }

    public static MapBuilder createEvent(String s, String s1, String s2, Long long1)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.CONSTRUCT_EVENT);
        MapBuilder mapbuilder = new MapBuilder();
        mapbuilder.set("&t", "event");
        mapbuilder.set("&ec", s);
        mapbuilder.set("&ea", s1);
        mapbuilder.set("&el", s2);
        String s3;
        if(long1 == null)
            s3 = null;
        else
            s3 = Long.toString(long1.longValue());
        mapbuilder.set("&ev", s3);
        return mapbuilder;
    }

    public static MapBuilder createException(String s, Boolean boolean1)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.CONSTRUCT_EXCEPTION);
        MapBuilder mapbuilder = new MapBuilder();
        mapbuilder.set("&t", "exception");
        mapbuilder.set("&exd", s);
        mapbuilder.set("&exf", booleanToString(boolean1));
        return mapbuilder;
    }

    public static MapBuilder createItem(String s, String s1, String s2, String s3, Double double1, Long long1, String s4)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.CONSTRUCT_ITEM);
        MapBuilder mapbuilder = new MapBuilder();
        mapbuilder.set("&t", "item");
        mapbuilder.set("&ti", s);
        mapbuilder.set("&ic", s2);
        mapbuilder.set("&in", s1);
        mapbuilder.set("&iv", s3);
        String s5;
        String s6;
        if(double1 == null)
            s5 = null;
        else
            s5 = Double.toString(double1.doubleValue());
        mapbuilder.set("&ip", s5);
        s6 = null;
        if(long1 != null)
            s6 = Long.toString(long1.longValue());
        mapbuilder.set("&iq", s6);
        mapbuilder.set("&cu", s4);
        return mapbuilder;
    }

    public static MapBuilder createSocial(String s, String s1, String s2)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.CONSTRUCT_SOCIAL);
        MapBuilder mapbuilder = new MapBuilder();
        mapbuilder.set("&t", "social");
        mapbuilder.set("&sn", s);
        mapbuilder.set("&sa", s1);
        mapbuilder.set("&st", s2);
        return mapbuilder;
    }

    public static MapBuilder createTiming(String s, Long long1, String s1, String s2)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.CONSTRUCT_TIMING);
        MapBuilder mapbuilder = new MapBuilder();
        mapbuilder.set("&t", "timing");
        mapbuilder.set("&utc", s);
        String s3 = null;
        if(long1 != null)
            s3 = Long.toString(long1.longValue());
        mapbuilder.set("&utt", s3);
        mapbuilder.set("&utv", s1);
        mapbuilder.set("&utl", s2);
        return mapbuilder;
    }

    public static MapBuilder createTransaction(String s, String s1, Double double1, Double double2, Double double3, String s2)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.CONSTRUCT_TRANSACTION);
        MapBuilder mapbuilder = new MapBuilder();
        mapbuilder.set("&t", "transaction");
        mapbuilder.set("&ti", s);
        mapbuilder.set("&ta", s1);
        String s3;
        String s4;
        String s5;
        if(double1 == null)
            s3 = null;
        else
            s3 = Double.toString(double1.doubleValue());
        mapbuilder.set("&tr", s3);
        if(double2 == null)
            s4 = null;
        else
            s4 = Double.toString(double2.doubleValue());
        mapbuilder.set("&tt", s4);
        s5 = null;
        if(double3 != null)
            s5 = Double.toString(double3.doubleValue());
        mapbuilder.set("&ts", s5);
        mapbuilder.set("&cu", s2);
        return mapbuilder;
    }

    public Map build()
    {
        return new HashMap(map);
    }

    public String get(String s)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.MAP_BUILDER_GET);
        return (String)map.get(s);
    }

    public MapBuilder set(String s, String s1)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.MAP_BUILDER_SET);
        if(s != null)
        {
            map.put(s, s1);
            return this;
        } else
        {
            Log.w(" MapBuilder.set() called with a null paramName.");
            return this;
        }
    }

    public MapBuilder setAll(Map map1)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.MAP_BUILDER_SET_ALL);
        if(map1 == null)
        {
            return this;
        } else
        {
            map.putAll(new HashMap(map1));
            return this;
        }
    }

    public MapBuilder setCampaignParamsFromUrl(String s)
    {
        GAUsage.getInstance().setUsage(GAUsage.Field.MAP_BUILDER_SET_CAMPAIGN_PARAMS);
        String s1 = Utils.filterCampaign(s);
        if(TextUtils.isEmpty(s1))
        {
            return this;
        } else
        {
            Map map1 = Utils.parseURLParameters(s1);
            set("&cc", (String)map1.get("utm_content"));
            set("&cm", (String)map1.get("utm_medium"));
            set("&cn", (String)map1.get("utm_campaign"));
            set("&cs", (String)map1.get("utm_source"));
            set("&ck", (String)map1.get("utm_term"));
            set("&ci", (String)map1.get("utm_id"));
            set("&gclid", (String)map1.get("gclid"));
            set("&dclid", (String)map1.get("dclid"));
            set("&gmob_t", (String)map1.get("gmob_t"));
            return this;
        }
    }

    private Map map;
}
