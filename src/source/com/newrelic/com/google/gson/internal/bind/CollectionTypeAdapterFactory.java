// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.*;
import com.newrelic.com.google.gson.internal.ConstructorConstructor;
import com.newrelic.com.google.gson.internal.ObjectConstructor;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            TypeAdapterRuntimeTypeWrapper

public final class CollectionTypeAdapterFactory
    implements TypeAdapterFactory
{
    private static final class Adapter extends TypeAdapter
    {

        public volatile Object read(JsonReader jsonreader)
            throws IOException
        {
            return read(jsonreader);
        }

        public Collection read(JsonReader jsonreader)
            throws IOException
        {
            if(jsonreader.peek() == JsonToken.NULL)
            {
                jsonreader.nextNull();
                return null;
            }
            Collection collection = (Collection)constructor.construct();
            jsonreader.beginArray();
            for(; jsonreader.hasNext(); collection.add(elementTypeAdapter.read(jsonreader)));
            jsonreader.endArray();
            return collection;
        }

        public volatile void write(JsonWriter jsonwriter, Object obj)
            throws IOException
        {
            write(jsonwriter, (Collection)obj);
        }

        public void write(JsonWriter jsonwriter, Collection collection)
            throws IOException
        {
            if(collection == null)
            {
                jsonwriter.nullValue();
                return;
            }
            jsonwriter.beginArray();
            Object obj;
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); elementTypeAdapter.write(jsonwriter, obj))
                obj = iterator.next();

            jsonwriter.endArray();
        }

        private final ObjectConstructor constructor;
        private final TypeAdapter elementTypeAdapter;

        public Adapter(Gson gson, Type type, TypeAdapter typeadapter, ObjectConstructor objectconstructor)
        {
            elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeadapter, type);
            constructor = objectconstructor;
        }
    }


    public CollectionTypeAdapterFactory(ConstructorConstructor constructorconstructor)
    {
        constructorConstructor = constructorconstructor;
    }

    public TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        Type type = typetoken.getType();
        Class class1 = typetoken.getRawType();
        if(!java/util/Collection.isAssignableFrom(class1))
        {
            return null;
        } else
        {
            Type type1 = com.newrelic.com.google.gson.internal..Gson.Types.getCollectionElementType(type, class1);
            return new Adapter(gson, type1, gson.getAdapter(TypeToken.get(type1)), constructorConstructor.get(typetoken));
        }
    }

    private final ConstructorConstructor constructorConstructor;
}
