// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.List;

// Referenced classes of package com.google.tagmanager:
//            ResolvedRuleBuilder, NoopResolvedFunctionCallBuilder, ResolvedFunctionCallBuilder, ResolvedFunctionCallTranslatorList

class NoopResolvedRuleBuilder
    implements ResolvedRuleBuilder
{
    public class NoopResolvedFunctionCallTranslatorList
        implements ResolvedFunctionCallTranslatorList
    {

        public void translateAndAddAll(List list, List list1)
        {
        }

        final NoopResolvedRuleBuilder this$0;

        public NoopResolvedFunctionCallTranslatorList()
        {
            this$0 = NoopResolvedRuleBuilder.this;
            super();
        }
    }


    NoopResolvedRuleBuilder()
    {
    }

    public ResolvedFunctionCallBuilder createNegativePredicate()
    {
        return new NoopResolvedFunctionCallBuilder();
    }

    public ResolvedFunctionCallBuilder createPositivePredicate()
    {
        return new NoopResolvedFunctionCallBuilder();
    }

    public ResolvedFunctionCallTranslatorList getAddedMacroFunctions()
    {
        return new NoopResolvedFunctionCallTranslatorList();
    }

    public ResolvedFunctionCallTranslatorList getAddedTagFunctions()
    {
        return new NoopResolvedFunctionCallTranslatorList();
    }

    public ResolvedFunctionCallTranslatorList getRemovedMacroFunctions()
    {
        return new NoopResolvedFunctionCallTranslatorList();
    }

    public ResolvedFunctionCallTranslatorList getRemovedTagFunctions()
    {
        return new NoopResolvedFunctionCallTranslatorList();
    }

    public void setValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
    {
    }
}
