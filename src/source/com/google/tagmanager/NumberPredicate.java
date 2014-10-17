// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            Predicate, Types, TypedNumber

abstract class NumberPredicate extends Predicate
{

    public NumberPredicate(String s)
    {
        super(s);
    }

    protected boolean evaluateNoDefaultValues(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1, Map map)
    {
        TypedNumber typednumber = Types.valueToNumber(value);
        TypedNumber typednumber1 = Types.valueToNumber(value1);
        if(typednumber == Types.getDefaultNumber() || typednumber1 == Types.getDefaultNumber())
            return false;
        else
            return evaluateNumber(typednumber, typednumber1, map);
    }

    protected abstract boolean evaluateNumber(TypedNumber typednumber, TypedNumber typednumber1, Map map);
}
