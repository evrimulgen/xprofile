// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonObject;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            BaseHarvestable

public abstract class HarvestableObject extends BaseHarvestable
{

    public HarvestableObject()
    {
        super(Harvestable.Type.OBJECT);
    }

    public static HarvestableObject fromMap(Map map)
    {
        return new HarvestableObject(map) {

            public JsonObject asJsonObject()
            {
                return (JsonObject)(new Gson()).toJsonTree(map, GSON_STRING_MAP_TYPE);
            }

            final Map val$map;

            
            {
                map = map1;
                super();
            }
        }
;
    }

    public abstract JsonObject asJsonObject();
}
