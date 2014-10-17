// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;

import java.util.Calendar;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.models:
//            BasePayload, Context

public class Alias extends BasePayload
{

    public Alias(String s, String s1, Calendar calendar, Context context)
    {
        super(null, null, calendar, context);
        put("action", "alias");
        setFrom(s);
        setTo(s1);
    }

    public Alias(JSONObject jsonobject)
    {
        super(jsonobject);
    }

    public String getFrom()
    {
        return optString("from", null);
    }

    public String getTo()
    {
        return optString("to", null);
    }

    public void setFrom(String s)
    {
        put("from", s);
    }

    public void setTo(String s)
    {
        put("to", s);
    }

    public static final String ACTION = "alias";
    private static final String FROM_KEY = "from";
    private static final String TO_KEY = "to";
}
