// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.*;

public class UnwrappedPropertyHandler
{

    public UnwrappedPropertyHandler()
    {
        _properties = new ArrayList();
    }

    protected UnwrappedPropertyHandler(List list)
    {
        _properties = list;
    }

    public void addProperty(SettableBeanProperty settablebeanproperty)
    {
        _properties.add(settablebeanproperty);
    }

    public Object processUnwrapped(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        int i = _properties.size();
        for(int j = 0; j < i; j++)
        {
            SettableBeanProperty settablebeanproperty = (SettableBeanProperty)_properties.get(j);
            JsonParser jsonparser1 = tokenbuffer.asParser();
            jsonparser1.nextToken();
            settablebeanproperty.deserializeAndSet(jsonparser1, deserializationcontext, obj);
        }

        return obj;
    }

    public UnwrappedPropertyHandler renameAll(NameTransformer nametransformer)
    {
        ArrayList arraylist = new ArrayList(_properties.size());
        SettableBeanProperty settablebeanproperty1;
        for(Iterator iterator = _properties.iterator(); iterator.hasNext(); arraylist.add(settablebeanproperty1))
        {
            SettableBeanProperty settablebeanproperty = (SettableBeanProperty)iterator.next();
            settablebeanproperty1 = settablebeanproperty.withSimpleName(nametransformer.transform(settablebeanproperty.getName()));
            JsonDeserializer jsondeserializer = settablebeanproperty1.getValueDeserializer();
            if(jsondeserializer == null)
                continue;
            JsonDeserializer jsondeserializer1 = jsondeserializer.unwrappingDeserializer(nametransformer);
            if(jsondeserializer1 != jsondeserializer)
                settablebeanproperty1 = settablebeanproperty1.withValueDeserializer(jsondeserializer1);
        }

        return new UnwrappedPropertyHandler(arraylist);
    }

    protected final List _properties;
}
