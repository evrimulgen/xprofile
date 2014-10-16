// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.TypeAdapter;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeAdapterRuntimeTypeWrapper extends TypeAdapter
{

    TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter typeadapter, Type type1)
    {
        context = gson;
        _flddelegate = typeadapter;
        type = type1;
    }

    private Type getRuntimeTypeIfMoreSpecific(Type type1, Object obj)
    {
        if(obj != null && (type1 == java/lang/Object || (type1 instanceof TypeVariable) || (type1 instanceof Class)))
            type1 = obj.getClass();
        return type1;
    }

    public Object read(JsonReader jsonreader)
        throws IOException
    {
        return _flddelegate.read(jsonreader);
    }

    public void write(JsonWriter jsonwriter, Object obj)
        throws IOException
    {
        TypeAdapter typeadapter = _flddelegate;
        Type type1 = getRuntimeTypeIfMoreSpecific(type, obj);
        if(type1 != type)
        {
            TypeAdapter typeadapter1 = context.getAdapter(TypeToken.get(type1));
            if(!(typeadapter1 instanceof ReflectiveTypeAdapterFactory.Adapter))
                typeadapter = typeadapter1;
            else
            if(!(_flddelegate instanceof ReflectiveTypeAdapterFactory.Adapter))
                typeadapter = _flddelegate;
            else
                typeadapter = typeadapter1;
        }
        typeadapter.write(jsonwriter, obj);
    }

    private final Gson context;
    private final TypeAdapter _flddelegate;
    private final Type type;
}
