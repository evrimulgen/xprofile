// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            ResolvedPropertyBuilder, DebugValueBuilder, ValueBuilder

class DebugResolvedPropertyBuilder
    implements ResolvedPropertyBuilder
{

    public DebugResolvedPropertyBuilder(com.google.analytics.containertag.proto.MutableDebug.ResolvedProperty resolvedproperty)
    {
        resolvedProperty = resolvedproperty;
    }

    public ValueBuilder createPropertyValueBuilder(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
    {
        com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value1 = DebugValueBuilder.copyImmutableValue(value);
        resolvedProperty.setValue(value1);
        return new DebugValueBuilder(value1);
    }

    private com.google.analytics.containertag.proto.MutableDebug.ResolvedProperty resolvedProperty;
}
