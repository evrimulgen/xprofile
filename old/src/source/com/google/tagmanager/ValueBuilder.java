// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            MacroEvaluationInfoBuilder

interface ValueBuilder
{

    public abstract MacroEvaluationInfoBuilder createValueMacroEvaluationInfoExtension();

    public abstract ValueBuilder getListItem(int i);

    public abstract ValueBuilder getMapKey(int i);

    public abstract ValueBuilder getMapValue(int i);

    public abstract ValueBuilder getTemplateToken(int i);
}
