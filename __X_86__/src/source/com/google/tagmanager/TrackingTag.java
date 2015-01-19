// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types

abstract class TrackingTag extends FunctionCallImplementation
{

    public transient TrackingTag(String s, String as[])
    {
        super(s, as);
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        evaluateTrackingTag(map);
        return Types.getDefaultValue();
    }

    public abstract void evaluateTrackingTag(Map map);

    public boolean isCacheable()
    {
        return false;
    }
}
