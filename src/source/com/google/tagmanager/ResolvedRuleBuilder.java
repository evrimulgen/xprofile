// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            ResolvedFunctionCallBuilder, ResolvedFunctionCallTranslatorList

interface ResolvedRuleBuilder
{

    public abstract ResolvedFunctionCallBuilder createNegativePredicate();

    public abstract ResolvedFunctionCallBuilder createPositivePredicate();

    public abstract ResolvedFunctionCallTranslatorList getAddedMacroFunctions();

    public abstract ResolvedFunctionCallTranslatorList getAddedTagFunctions();

    public abstract ResolvedFunctionCallTranslatorList getRemovedMacroFunctions();

    public abstract ResolvedFunctionCallTranslatorList getRemovedTagFunctions();

    public abstract void setValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value);
}
