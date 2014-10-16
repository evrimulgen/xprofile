// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.tagmanager:
//            RuleEvaluationStepInfoBuilder, DebugResolvedRuleBuilder, ResolvedRuleBuilder

class DebugRuleEvaluationStepInfoBuilder
    implements RuleEvaluationStepInfoBuilder
{

    public DebugRuleEvaluationStepInfoBuilder(com.google.analytics.containertag.proto.MutableDebug.RuleEvaluationStepInfo ruleevaluationstepinfo)
    {
        ruleEvaluationStepInfo = ruleevaluationstepinfo;
    }

    public ResolvedRuleBuilder createResolvedRuleBuilder()
    {
        return new DebugResolvedRuleBuilder(ruleEvaluationStepInfo.addRules());
    }

    public void setEnabledFunctions(Set set)
    {
        ResourceUtil.ExpandedFunctionCall expandedfunctioncall;
        for(Iterator iterator = set.iterator(); iterator.hasNext(); ruleEvaluationStepInfo.addEnabledFunctions(DebugResolvedRuleBuilder.translateExpandedFunctionCall(expandedfunctioncall)))
            expandedfunctioncall = (ResourceUtil.ExpandedFunctionCall)iterator.next();

    }

    private com.google.analytics.containertag.proto.MutableDebug.RuleEvaluationStepInfo ruleEvaluationStepInfo;
}
