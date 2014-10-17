// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            DataLayerEventEvaluationInfoBuilder, NoopResolvedFunctionCallBuilder, NoopRuleEvaluationStepInfoBuilder, ResolvedFunctionCallBuilder, 
//            RuleEvaluationStepInfoBuilder

class NoopDataLayerEventEvaluationInfoBuilder
    implements DataLayerEventEvaluationInfoBuilder
{

    NoopDataLayerEventEvaluationInfoBuilder()
    {
    }

    public ResolvedFunctionCallBuilder createAndAddResult()
    {
        return new NoopResolvedFunctionCallBuilder();
    }

    public RuleEvaluationStepInfoBuilder createRulesEvaluation()
    {
        return new NoopRuleEvaluationStepInfoBuilder();
    }
}
