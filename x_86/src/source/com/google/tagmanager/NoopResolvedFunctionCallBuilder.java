// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            ResolvedFunctionCallBuilder, NoopResolvedPropertyBuilder, ResolvedPropertyBuilder

class NoopResolvedFunctionCallBuilder
    implements ResolvedFunctionCallBuilder
{

    NoopResolvedFunctionCallBuilder()
    {
    }

    public ResolvedPropertyBuilder createResolvedPropertyBuilder(String s)
    {
        return new NoopResolvedPropertyBuilder();
    }

    public void setFunctionResult(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
    {
    }
}
