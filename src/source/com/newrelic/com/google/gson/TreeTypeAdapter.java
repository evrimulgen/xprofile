// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.internal.Streams;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.io.IOException;

// Referenced classes of package com.newrelic.com.google.gson:
//            TypeAdapter, Gson, JsonElement, JsonDeserializer, 
//            JsonSerializer, TypeAdapterFactory

final class TreeTypeAdapter extends TypeAdapter
{
    private static class SingleTypeFactory
        implements TypeAdapterFactory
    {

        public TypeAdapter create(Gson gson1, TypeToken typetoken)
        {
            boolean flag;
            if(exactType != null)
            {
                if(exactType.equals(typetoken) || matchRawType && exactType.getType() == typetoken.getRawType())
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = hierarchyType.isAssignableFrom(typetoken.getRawType());
            }
            if(flag)
                return new TreeTypeAdapter(serializer, deserializer, gson1, typetoken, this);
            else
                return null;
        }

        private final JsonDeserializer deserializer;
        private final TypeToken exactType;
        private final Class hierarchyType;
        private final boolean matchRawType;
        private final JsonSerializer serializer;

        private SingleTypeFactory(Object obj, TypeToken typetoken, boolean flag, Class class1)
        {
            JsonSerializer jsonserializer;
            JsonDeserializer jsondeserializer;
            boolean flag1;
            if(obj instanceof JsonSerializer)
                jsonserializer = (JsonSerializer)obj;
            else
                jsonserializer = null;
            serializer = jsonserializer;
            if(obj instanceof JsonDeserializer)
                jsondeserializer = (JsonDeserializer)obj;
            else
                jsondeserializer = null;
            deserializer = jsondeserializer;
            if(serializer != null || deserializer != null)
                flag1 = true;
            else
                flag1 = false;
            com.newrelic.com.google.gson.internal..Gson.Preconditions.checkArgument(flag1);
            exactType = typetoken;
            matchRawType = flag;
            hierarchyType = class1;
        }

    }


    private TreeTypeAdapter(JsonSerializer jsonserializer, JsonDeserializer jsondeserializer, Gson gson1, TypeToken typetoken, TypeAdapterFactory typeadapterfactory)
    {
        serializer = jsonserializer;
        deserializer = jsondeserializer;
        gson = gson1;
        typeToken = typetoken;
        skipPast = typeadapterfactory;
    }


    private TypeAdapter _mthdelegate()
    {
        TypeAdapter typeadapter = _flddelegate;
        if(typeadapter != null)
        {
            return typeadapter;
        } else
        {
            TypeAdapter typeadapter1 = gson.getDelegateAdapter(skipPast, typeToken);
            _flddelegate = typeadapter1;
            return typeadapter1;
        }
    }

    public static TypeAdapterFactory newFactory(TypeToken typetoken, Object obj)
    {
        return new SingleTypeFactory(obj, typetoken, false, null);
    }

    public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken typetoken, Object obj)
    {
        boolean flag;
        if(typetoken.getType() == typetoken.getRawType())
            flag = true;
        else
            flag = false;
        return new SingleTypeFactory(obj, typetoken, flag, null);
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class class1, Object obj)
    {
        return new SingleTypeFactory(obj, null, false, class1);
    }

    public Object read(JsonReader jsonreader)
        throws IOException
    {
        if(deserializer == null)
            return _mthdelegate().read(jsonreader);
        JsonElement jsonelement = Streams.parse(jsonreader);
        if(jsonelement.isJsonNull())
            return null;
        else
            return deserializer.deserialize(jsonelement, typeToken.getType(), gson.deserializationContext);
    }

    public void write(JsonWriter jsonwriter, Object obj)
        throws IOException
    {
        if(serializer == null)
        {
            _mthdelegate().write(jsonwriter, obj);
            return;
        }
        if(obj == null)
        {
            jsonwriter.nullValue();
            return;
        } else
        {
            Streams.write(serializer.serialize(obj, typeToken.getType(), gson.serializationContext), jsonwriter);
            return;
        }
    }

    private TypeAdapter _flddelegate;
    private final JsonDeserializer deserializer;
    private final Gson gson;
    private final JsonSerializer serializer;
    private final TypeAdapterFactory skipPast;
    private final TypeToken typeToken;
}
