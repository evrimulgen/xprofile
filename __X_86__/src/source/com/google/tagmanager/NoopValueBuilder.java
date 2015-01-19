// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            ValueBuilder, NoopMacroEvaluationInfoBuilder, MacroEvaluationInfoBuilder

class NoopValueBuilder
    implements ValueBuilder
{

    NoopValueBuilder()
    {
    }

    public MacroEvaluationInfoBuilder createValueMacroEvaluationInfoExtension()
    {
        return new NoopMacroEvaluationInfoBuilder();
    }

    public ValueBuilder getListItem(int i)
    {
        return new NoopValueBuilder();
    }

    public ValueBuilder getMapKey(int i)
    {
        return new NoopValueBuilder();
    }

    public ValueBuilder getMapValue(int i)
    {
        return new NoopValueBuilder();
    }

    public ValueBuilder getTemplateToken(int i)
    {
        return new NoopValueBuilder();
    }
}
