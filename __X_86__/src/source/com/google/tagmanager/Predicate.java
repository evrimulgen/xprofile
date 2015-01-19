// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.Key;
import java.util.*;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types

abstract class Predicate extends FunctionCallImplementation
{

    public Predicate(String s)
    {
        String as[] = new String[2];
        as[0] = ARG0;
        as[1] = ARG1;
        super(s, as);
    }

    public static String getArg0Key()
    {
        return ARG0;
    }

    public static String getArg1Key()
    {
        return ARG1;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        for(Iterator iterator = map.values().iterator(); iterator.hasNext();)
            if((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)iterator.next() == Types.getDefaultValue())
                return Types.objectToValue(Boolean.valueOf(false));

        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ARG0);
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ARG1);
        boolean flag = false;
        if(value != null)
        {
            flag = false;
            if(value1 != null)
                flag = evaluateNoDefaultValues(value, value1, map);
        }
        return Types.objectToValue(Boolean.valueOf(flag));
    }

    protected abstract boolean evaluateNoDefaultValues(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1, Map map);

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ARG0;
    private static final String ARG1;

    static 
    {
        ARG0 = Key.ARG0.toString();
        ARG1 = Key.ARG1.toString();
    }
}
