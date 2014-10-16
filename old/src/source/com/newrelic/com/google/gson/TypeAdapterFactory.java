// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.reflect.TypeToken;

// Referenced classes of package com.newrelic.com.google.gson:
//            Gson, TypeAdapter

public interface TypeAdapterFactory
{

    public abstract TypeAdapter create(Gson gson, TypeToken typetoken);
}
