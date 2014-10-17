// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.internal.LinkedTreeMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.newrelic.com.google.gson:
//            JsonElement, JsonNull, JsonPrimitive, JsonArray

public final class JsonObject extends JsonElement
{

    public JsonObject()
    {
    }

    private JsonElement createJsonElement(Object obj)
    {
        if(obj == null)
            return JsonNull.INSTANCE;
        else
            return new JsonPrimitive(obj);
    }

    public void add(String s, JsonElement jsonelement)
    {
        if(jsonelement == null)
            jsonelement = JsonNull.INSTANCE;
        members.put(s, jsonelement);
    }

    public void addProperty(String s, Boolean boolean1)
    {
        add(s, createJsonElement(boolean1));
    }

    public void addProperty(String s, Character character)
    {
        add(s, createJsonElement(character));
    }

    public void addProperty(String s, Number number)
    {
        add(s, createJsonElement(number));
    }

    public void addProperty(String s, String s1)
    {
        add(s, createJsonElement(s1));
    }

    volatile JsonElement deepCopy()
    {
        return deepCopy();
    }

    JsonObject deepCopy()
    {
        JsonObject jsonobject = new JsonObject();
        java.util.Map.Entry entry;
        for(Iterator iterator = members.entrySet().iterator(); iterator.hasNext(); jsonobject.add((String)entry.getKey(), ((JsonElement)entry.getValue()).deepCopy()))
            entry = (java.util.Map.Entry)iterator.next();

        return jsonobject;
    }

    public Set entrySet()
    {
        return members.entrySet();
    }

    public boolean equals(Object obj)
    {
        return obj == this || (obj instanceof JsonObject) && ((JsonObject)obj).members.equals(members);
    }

    public JsonElement get(String s)
    {
        return (JsonElement)members.get(s);
    }

    public JsonArray getAsJsonArray(String s)
    {
        return (JsonArray)members.get(s);
    }

    public JsonObject getAsJsonObject(String s)
    {
        return (JsonObject)members.get(s);
    }

    public JsonPrimitive getAsJsonPrimitive(String s)
    {
        return (JsonPrimitive)members.get(s);
    }

    public boolean has(String s)
    {
        return members.containsKey(s);
    }

    public int hashCode()
    {
        return members.hashCode();
    }

    public JsonElement remove(String s)
    {
        return (JsonElement)members.remove(s);
    }

    private final LinkedTreeMap members = new LinkedTreeMap();
}
