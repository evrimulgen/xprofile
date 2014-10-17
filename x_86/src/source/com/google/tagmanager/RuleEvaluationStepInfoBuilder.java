// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.util.Set;

// Referenced classes of package com.google.tagmanager:
//            ResolvedRuleBuilder

interface RuleEvaluationStepInfoBuilder
{

    public abstract ResolvedRuleBuilder createResolvedRuleBuilder();

    public abstract void setEnabledFunctions(Set set);
}
