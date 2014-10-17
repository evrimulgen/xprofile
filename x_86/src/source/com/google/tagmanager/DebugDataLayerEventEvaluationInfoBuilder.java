// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            DataLayerEventEvaluationInfoBuilder, DebugResolvedFunctionCallBuilder, DebugRuleEvaluationStepInfoBuilder, ResolvedFunctionCallBuilder, 
//            RuleEvaluationStepInfoBuilder

class DebugDataLayerEventEvaluationInfoBuilder
    implements DataLayerEventEvaluationInfoBuilder
{

    public DebugDataLayerEventEvaluationInfoBuilder(com.google.analytics.containertag.proto.MutableDebug.DataLayerEventEvaluationInfo datalayereventevaluationinfo)
    {
        dataLayerEvent = datalayereventevaluationinfo;
    }

    public ResolvedFunctionCallBuilder createAndAddResult()
    {
        return new DebugResolvedFunctionCallBuilder(dataLayerEvent.addResults());
    }

    public RuleEvaluationStepInfoBuilder createRulesEvaluation()
    {
        return new DebugRuleEvaluationStepInfoBuilder(dataLayerEvent.getMutableRulesEvaluation());
    }

    private com.google.analytics.containertag.proto.MutableDebug.DataLayerEventEvaluationInfo dataLayerEvent;
}
