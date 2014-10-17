// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            EventInfoBuilder

interface EventInfoDistributor
{

    public abstract EventInfoBuilder createDataLayerEventEvaluationEventInfo(String s);

    public abstract EventInfoBuilder createMacroEvalutionEventInfo(String s);

    public abstract boolean debugMode();
}
