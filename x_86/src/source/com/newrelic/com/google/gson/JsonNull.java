// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;


// Referenced classes of package com.newrelic.com.google.gson:
//            JsonElement

public final class JsonNull extends JsonElement
{

    public JsonNull()
    {
    }

    volatile JsonElement deepCopy()
    {
        return deepCopy();
    }

    JsonNull deepCopy()
    {
        return INSTANCE;
    }

    public boolean equals(Object obj)
    {
        return this == obj || (obj instanceof JsonNull);
    }

    public int hashCode()
    {
        return com/newrelic/com/google/gson/JsonNull.hashCode();
    }

    public static final JsonNull INSTANCE = new JsonNull();

}
