// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            ResolvedPropertyBuilder

interface ResolvedFunctionCallBuilder
{

    public abstract ResolvedPropertyBuilder createResolvedPropertyBuilder(String s);

    public abstract void setFunctionResult(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value);
}
