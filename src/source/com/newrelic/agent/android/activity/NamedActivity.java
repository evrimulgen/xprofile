// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.activity;


// Referenced classes of package com.newrelic.agent.android.activity:
//            BaseMeasuredActivity

public class NamedActivity extends BaseMeasuredActivity
{

    public NamedActivity(String s)
    {
        setName(s);
        setAutoInstrumented(false);
    }

    public void rename(String s)
    {
        setName(s);
    }
}
