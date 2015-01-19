// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            EventInfoDistributor, DebugEventInfoBuilder, DebugInformationHandler, EventInfoBuilder

class DebugEventInfoDistributor
    implements EventInfoDistributor
{

    public DebugEventInfoDistributor(DebugInformationHandler debuginformationhandler, String s, String s1)
    {
        handler = debuginformationhandler;
        containerVersion = s;
        containerId = s1;
    }

    public EventInfoBuilder createDataLayerEventEvaluationEventInfo(String s)
    {
        return new DebugEventInfoBuilder(com.google.analytics.containertag.proto.MutableDebug.EventInfo.EventType.DATA_LAYER_EVENT, containerVersion, containerId, s, handler);
    }

    public EventInfoBuilder createMacroEvalutionEventInfo(String s)
    {
        return new DebugEventInfoBuilder(com.google.analytics.containertag.proto.MutableDebug.EventInfo.EventType.MACRO_REFERENCE, containerVersion, containerId, s, handler);
    }

    public boolean debugMode()
    {
        return true;
    }

    private String containerId;
    private String containerVersion;
    private DebugInformationHandler handler;
}
