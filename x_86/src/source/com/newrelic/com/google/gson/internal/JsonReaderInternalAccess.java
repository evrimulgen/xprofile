// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal;

import com.newrelic.com.google.gson.stream.JsonReader;
import java.io.IOException;

public abstract class JsonReaderInternalAccess
{

    public JsonReaderInternalAccess()
    {
    }

    public abstract void promoteNameToValue(JsonReader jsonreader)
        throws IOException;

    public static JsonReaderInternalAccess INSTANCE;
}
