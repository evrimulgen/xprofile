// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.*;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            TypeAdapterRuntimeTypeWrapper

public final class ArrayTypeAdapter extends TypeAdapter
{

    public ArrayTypeAdapter(Gson gson, TypeAdapter typeadapter, Class class1)
    {
        componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeadapter, class1);
        componentType = class1;
    }

    public Object read(JsonReader jsonreader)
        throws IOException
    {
        Object obj;
        if(jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            obj = null;
        } else
        {
            ArrayList arraylist = new ArrayList();
            jsonreader.beginArray();
            for(; jsonreader.hasNext(); arraylist.add(componentTypeAdapter.read(jsonreader)));
            jsonreader.endArray();
            obj = Array.newInstance(componentType, arraylist.size());
            int i = 0;
            while(i < arraylist.size()) 
            {
                Array.set(obj, i, arraylist.get(i));
                i++;
            }
        }
        return obj;
    }

    public void write(JsonWriter jsonwriter, Object obj)
        throws IOException
    {
        if(obj == null)
        {
            jsonwriter.nullValue();
            return;
        }
        jsonwriter.beginArray();
        int i = 0;
        for(int j = Array.getLength(obj); i < j; i++)
        {
            Object obj1 = Array.get(obj, i);
            componentTypeAdapter.write(jsonwriter, obj1);
        }

        jsonwriter.endArray();
    }

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

        public TypeAdapter create(Gson gson, TypeToken typetoken)
        {
            java.lang.reflect.Type type = typetoken.getType();
            if(!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class)type).isArray()))
            {
                return null;
            } else
            {
                java.lang.reflect.Type type1 = com.newrelic.com.google.gson.internal..Gson.Types.getArrayComponentType(type);
                return new ArrayTypeAdapter(gson, gson.getAdapter(TypeToken.get(type1)), com.newrelic.com.google.gson.internal..Gson.Types.getRawType(type1));
            }
        }

    }
;
    private final Class componentType;
    private final TypeAdapter componentTypeAdapter;

}
