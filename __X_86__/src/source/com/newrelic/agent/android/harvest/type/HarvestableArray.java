// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonArray;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            BaseHarvestable

public abstract class HarvestableArray extends BaseHarvestable
{

    public HarvestableArray()
    {
        super(Harvestable.Type.ARRAY);
    }

    public abstract JsonArray asJsonArray();
}
