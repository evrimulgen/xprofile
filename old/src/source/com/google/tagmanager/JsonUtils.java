// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.Key;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.*;
import org.json.*;

// Referenced classes of package com.google.tagmanager:
//            ConstantMacro, Types

class JsonUtils
{

    private JsonUtils()
    {
    }

    public static ResourceUtil.ExpandedResource expandedResourceFromJsonString(String s)
        throws JSONException
    {
        JSONObject _tmp = JVM INSTR new #14  <Class JSONObject>;
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = jsonObjectToValue(JSONObjectInstrumentation.init(s));
        ResourceUtil.ExpandedResourceBuilder expandedresourcebuilder = ResourceUtil.ExpandedResource.newBuilder();
        for(int i = 0; i < value.getMapKeyCount(); i++)
            expandedresourcebuilder.addMacro(ResourceUtil.ExpandedFunctionCall.newBuilder().addProperty(Key.INSTANCE_NAME.toString(), value.getMapKey(i)).addProperty(Key.FUNCTION.toString(), Types.functionIdToValue(ConstantMacro.getFunctionId())).addProperty(ConstantMacro.getValueKey(), value.getMapValue(i)).build());

        return expandedresourcebuilder.build();
    }

    static Object jsonObjectToObject(Object obj)
        throws JSONException
    {
        if(obj instanceof JSONArray)
            throw new RuntimeException("JSONArrays are not supported");
        if(JSONObject.NULL.equals(obj))
            throw new RuntimeException("JSON nulls are not supported");
        Object obj1;
        if(obj instanceof JSONObject)
        {
            JSONObject jsonobject = (JSONObject)obj;
            obj1 = new HashMap();
            String s;
            for(Iterator iterator = jsonobject.keys(); iterator.hasNext(); ((Map) (obj1)).put(s, jsonObjectToObject(jsonobject.get(s))))
                s = (String)iterator.next();

        } else
        {
            obj1 = obj;
        }
        return obj1;
    }

    private static com.google.analytics.midtier.proto.containertag.TypeSystem.Value jsonObjectToValue(Object obj)
        throws JSONException
    {
        return Types.objectToValue(jsonObjectToObject(obj));
    }
}
