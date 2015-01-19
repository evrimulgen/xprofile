// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;


// Referenced classes of package io.segment.android.models:
//            EasyJSONObject

public class Providers extends EasyJSONObject
{

    public Providers()
    {
    }

    public Providers setDefault(boolean flag)
    {
        put("all", flag);
        return this;
    }

    public Providers setEnabled(String s, boolean flag)
    {
        put(s, flag);
        return this;
    }
}
