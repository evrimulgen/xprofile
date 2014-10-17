// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;

import java.util.Calendar;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.models:
//            BasePayload, Traits, Context

public class Group extends BasePayload
{

    public Group(String s, String s1, String s2, Traits traits, Calendar calendar, Context context)
    {
        super(s, s1, calendar, context);
        put("action", "group");
        setGroupId(s2);
        setTraits(traits);
    }

    public Group(JSONObject jsonobject)
    {
        super(jsonobject);
    }

    public String getGroupId()
    {
        return optString("groupId", null);
    }

    public Traits getTraits()
    {
        JSONObject jsonobject = getObject("traits");
        if(jsonobject == null)
            return null;
        else
            return new Traits(jsonobject);
    }

    public void setGroupId(String s)
    {
        put("groupId", s);
    }

    public void setTraits(Traits traits)
    {
        put("traits", traits);
    }

    public static final String ACTION = "group";
    private static final String GROUP_ID_KEY = "groupId";
    private static final String TRAITS_KEY = "traits";
}
