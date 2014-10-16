// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonPrimitive;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            HarvestableValue

public class HarvestableDouble extends HarvestableValue
{

    public HarvestableDouble()
    {
    }

    public HarvestableDouble(double d)
    {
        this();
        value = d;
    }

    public JsonPrimitive asJsonPrimitive()
    {
        return new JsonPrimitive(Double.valueOf(value));
    }

    private double value;
}
