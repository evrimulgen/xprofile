// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.*;
import com.newrelic.com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            Harvestable

public class BaseHarvestable
    implements Harvestable
{

    public BaseHarvestable(Harvestable.Type type1)
    {
        type = type1;
    }

    public JsonElement asJson()
    {
        static class _cls2
        {

            static final int $SwitchMap$com$newrelic$agent$android$harvest$type$Harvestable$Type[];

            static 
            {
                $SwitchMap$com$newrelic$agent$android$harvest$type$Harvestable$Type = new int[Harvestable.Type.values().length];
                try
                {
                    $SwitchMap$com$newrelic$agent$android$harvest$type$Harvestable$Type[Harvestable.Type.OBJECT.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$newrelic$agent$android$harvest$type$Harvestable$Type[Harvestable.Type.ARRAY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$newrelic$agent$android$harvest$type$Harvestable$Type[Harvestable.Type.VALUE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        switch(_cls2..SwitchMap.com.newrelic.agent.android.harvest.type.Harvestable.Type[type.ordinal()])
        {
        default:
            return null;

        case 1: // '\001'
            return asJsonObject();

        case 2: // '\002'
            return asJsonArray();

        case 3: // '\003'
            return asJsonPrimitive();
        }
    }

    public JsonArray asJsonArray()
    {
        return null;
    }

    public JsonObject asJsonObject()
    {
        return null;
    }

    public JsonPrimitive asJsonPrimitive()
    {
        return null;
    }

    public Harvestable.Type getType()
    {
        return type;
    }

    protected void notEmpty(String s)
    {
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("Missing Harvestable field.");
        else
            return;
    }

    protected void notNull(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("Null field in Harvestable object");
        else
            return;
    }

    protected String optional(String s)
    {
        if(s == null)
            s = "";
        return s;
    }

    public String toJsonString()
    {
        return asJson().toString();
    }

    protected static final Type GSON_STRING_MAP_TYPE = (new TypeToken() {

    }
).getType();
    private final Harvestable.Type type;

}
