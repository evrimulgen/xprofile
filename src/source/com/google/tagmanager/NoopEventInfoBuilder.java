// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            EventInfoBuilder, NoopDataLayerEventEvaluationInfoBuilder, NoopMacroEvaluationInfoBuilder, DataLayerEventEvaluationInfoBuilder, 
//            MacroEvaluationInfoBuilder

class NoopEventInfoBuilder
    implements EventInfoBuilder
{

    NoopEventInfoBuilder()
    {
    }

    public DataLayerEventEvaluationInfoBuilder createDataLayerEventEvaluationInfoBuilder()
    {
        return new NoopDataLayerEventEvaluationInfoBuilder();
    }

    public MacroEvaluationInfoBuilder createMacroEvaluationInfoBuilder()
    {
        return new NoopMacroEvaluationInfoBuilder();
    }

    public void processEventInfo()
    {
    }
}
