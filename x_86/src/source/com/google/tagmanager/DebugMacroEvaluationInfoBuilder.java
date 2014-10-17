// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            MacroEvaluationInfoBuilder, DebugResolvedFunctionCallBuilder, DebugRuleEvaluationStepInfoBuilder, ResolvedFunctionCallBuilder, 
//            RuleEvaluationStepInfoBuilder

class DebugMacroEvaluationInfoBuilder
    implements MacroEvaluationInfoBuilder
{

    public DebugMacroEvaluationInfoBuilder(com.google.analytics.containertag.proto.MutableDebug.MacroEvaluationInfo macroevaluationinfo)
    {
        macroEvaluationInfo = macroevaluationinfo;
    }

    public ResolvedFunctionCallBuilder createResult()
    {
        return new DebugResolvedFunctionCallBuilder(macroEvaluationInfo.getMutableResult());
    }

    public RuleEvaluationStepInfoBuilder createRulesEvaluation()
    {
        return new DebugRuleEvaluationStepInfoBuilder(macroEvaluationInfo.getMutableRulesEvaluation());
    }

    private com.google.analytics.containertag.proto.MutableDebug.MacroEvaluationInfo macroEvaluationInfo;
}
