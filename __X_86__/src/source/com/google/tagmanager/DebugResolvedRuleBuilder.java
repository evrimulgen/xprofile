// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.*;

// Referenced classes of package com.google.tagmanager:
//            ResolvedRuleBuilder, DebugValueBuilder, DebugResolvedFunctionCallBuilder, ResolvedFunctionCallTranslatorList, 
//            ResolvedFunctionCallBuilder

class DebugResolvedRuleBuilder
    implements ResolvedRuleBuilder
{
    class DebugResolvedFunctionCallListTranslator
        implements ResolvedFunctionCallTranslatorList
    {

        public void translateAndAddAll(List list, List list1)
        {
            int i = 0;
            while(i < list.size()) 
            {
                toBuild.add(DebugResolvedRuleBuilder.translateExpandedFunctionCall((ResourceUtil.ExpandedFunctionCall)list.get(i)));
                if(i < list1.size())
                    ((com.google.analytics.containertag.proto.MutableDebug.ResolvedFunctionCall)toBuild.get(i)).setAssociatedRuleName((String)list1.get(i));
                else
                    ((com.google.analytics.containertag.proto.MutableDebug.ResolvedFunctionCall)toBuild.get(i)).setAssociatedRuleName("Unknown");
                i++;
            }
        }

        final DebugResolvedRuleBuilder this$0;
        List toBuild;

        DebugResolvedFunctionCallListTranslator(List list)
        {
            this$0 = DebugResolvedRuleBuilder.this;
            super();
            toBuild = list;
        }
    }


    public DebugResolvedRuleBuilder(com.google.analytics.containertag.proto.MutableDebug.ResolvedRule resolvedrule)
    {
        resolvedRule = resolvedrule;
        addMacrosHolder = new DebugResolvedFunctionCallListTranslator(resolvedRule.getMutableAddMacrosList());
        removeMacrosHolder = new DebugResolvedFunctionCallListTranslator(resolvedRule.getMutableRemoveMacrosList());
        addTagsHolder = new DebugResolvedFunctionCallListTranslator(resolvedRule.getMutableAddTagsList());
        removeTagsHolder = new DebugResolvedFunctionCallListTranslator(resolvedRule.getMutableRemoveTagsList());
    }

    public static com.google.analytics.containertag.proto.MutableDebug.ResolvedFunctionCall translateExpandedFunctionCall(ResourceUtil.ExpandedFunctionCall expandedfunctioncall)
    {
        com.google.analytics.containertag.proto.MutableDebug.ResolvedFunctionCall resolvedfunctioncall = com.google.analytics.containertag.proto.MutableDebug.ResolvedFunctionCall.newMessage();
        com.google.analytics.containertag.proto.MutableDebug.ResolvedProperty resolvedproperty;
        for(Iterator iterator = expandedfunctioncall.getProperties().entrySet().iterator(); iterator.hasNext(); resolvedfunctioncall.addProperties(resolvedproperty))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            resolvedproperty = com.google.analytics.containertag.proto.MutableDebug.ResolvedProperty.newMessage();
            resolvedproperty.setKey((String)entry.getKey());
            resolvedproperty.setValue(DebugValueBuilder.copyImmutableValue((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)entry.getValue()));
        }

        return resolvedfunctioncall;
    }

    public ResolvedFunctionCallBuilder createNegativePredicate()
    {
        return new DebugResolvedFunctionCallBuilder(resolvedRule.addNegativePredicates());
    }

    public ResolvedFunctionCallBuilder createPositivePredicate()
    {
        return new DebugResolvedFunctionCallBuilder(resolvedRule.addPositivePredicates());
    }

    public ResolvedFunctionCallTranslatorList getAddedMacroFunctions()
    {
        return addMacrosHolder;
    }

    public ResolvedFunctionCallTranslatorList getAddedTagFunctions()
    {
        return addTagsHolder;
    }

    public ResolvedFunctionCallTranslatorList getRemovedMacroFunctions()
    {
        return removeMacrosHolder;
    }

    public ResolvedFunctionCallTranslatorList getRemovedTagFunctions()
    {
        return removeTagsHolder;
    }

    public void setValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
    {
        resolvedRule.setResult(DebugValueBuilder.copyImmutableValue(value));
    }

    ResolvedFunctionCallTranslatorList addMacrosHolder;
    ResolvedFunctionCallTranslatorList addTagsHolder;
    ResolvedFunctionCallTranslatorList removeMacrosHolder;
    ResolvedFunctionCallTranslatorList removeTagsHolder;
    com.google.analytics.containertag.proto.MutableDebug.ResolvedRule resolvedRule;
}
