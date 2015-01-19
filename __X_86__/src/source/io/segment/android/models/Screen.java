// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;

import java.util.Calendar;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.models:
//            Track, Props, Context

public class Screen extends Track
{

    public Screen(String s, String s1, String s2, Props props, Calendar calendar, Context context)
    {
        super(s, s1, (new StringBuilder("Viewed ")).append(s2).toString(), props, calendar, context);
        put("action", "screen");
        setName(s2);
    }

    public Screen(JSONObject jsonobject)
    {
        super(jsonobject);
    }

    public String getName()
    {
        return optString("name", null);
    }

    public void setName(String s)
    {
        put("name", s);
    }

    public static final String ACTION = "screen";
    private static final String NAME_KEY = "name";
}
