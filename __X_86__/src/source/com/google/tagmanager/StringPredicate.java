// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            Predicate, Types

abstract class StringPredicate extends Predicate
{

    public StringPredicate(String s)
    {
        super(s);
    }

    protected boolean evaluateNoDefaultValues(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1, Map map)
    {
        String s = Types.valueToString(value);
        String s1 = Types.valueToString(value1);
        if(s == Types.getDefaultString() || s1 == Types.getDefaultString())
            return false;
        else
            return evaluateString(s, s1, map);
    }

    protected abstract boolean evaluateString(String s, String s1, Map map);
}
