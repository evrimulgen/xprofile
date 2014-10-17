// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.util.*;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types, Log

class CustomFunctionCall extends FunctionCallImplementation
{
    public static interface CustomEvaluator
    {

        public abstract Object evaluate(String s, Map map);
    }


    public CustomFunctionCall(CustomEvaluator customevaluator)
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = FUNCTION_CALL_NAME;
        super(s, as);
        mFunctionCallEvaluator = customevaluator;
    }

    public static String getAdditionalParamsKey()
    {
        return ADDITIONAL_PARAMS;
    }

    public static String getFunctionCallNameKey()
    {
        return FUNCTION_CALL_NAME;
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        String s = Types.valueToString((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(FUNCTION_CALL_NAME));
        HashMap hashmap = new HashMap();
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ADDITIONAL_PARAMS);
        if(value != null)
        {
            Object obj = Types.valueToObject(value);
            if(!(obj instanceof Map))
            {
                Log.w("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return Types.getDefaultValue();
            }
            java.util.Map.Entry entry;
            for(Iterator iterator = ((Map)obj).entrySet().iterator(); iterator.hasNext(); hashmap.put(entry.getKey().toString(), entry.getValue()))
                entry = (java.util.Map.Entry)iterator.next();

        }
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1;
        try
        {
            value1 = Types.objectToValue(mFunctionCallEvaluator.evaluate(s, hashmap));
        }
        catch(Exception exception)
        {
            Log.w((new StringBuilder()).append("Custom macro/tag ").append(s).append(" threw exception ").append(exception.getMessage()).toString());
            return Types.getDefaultValue();
        }
        return value1;
    }

    public boolean isCacheable()
    {
        return false;
    }

    private static final String ADDITIONAL_PARAMS;
    private static final String FUNCTION_CALL_NAME;
    private static final String ID;
    private final CustomEvaluator mFunctionCallEvaluator;

    static 
    {
        ID = FunctionType.FUNCTION_CALL.toString();
        FUNCTION_CALL_NAME = Key.FUNCTION_CALL_NAME.toString();
        ADDITIONAL_PARAMS = Key.ADDITIONAL_PARAMS.toString();
    }
}
