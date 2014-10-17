// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.*;
import com.newrelic.com.google.gson.annotations.SerializedName;
import com.newrelic.com.google.gson.internal.*;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.newrelic.com.google.gson.internal.bind:
//            TypeAdapterRuntimeTypeWrapper

public final class ReflectiveTypeAdapterFactory
    implements TypeAdapterFactory
{
    public static final class Adapter extends TypeAdapter
    {

        public Object read(JsonReader jsonreader)
            throws IOException
        {
            Object obj;
            if(jsonreader.peek() == JsonToken.NULL)
            {
                jsonreader.nextNull();
                return null;
            }
            obj = constructor.construct();
            jsonreader.beginObject();
_L1:
            BoundField boundfield;
            if(!jsonreader.hasNext())
                break MISSING_BLOCK_LABEL_111;
            String s = jsonreader.nextName();
            boundfield = (BoundField)boundFields.get(s);
            if(boundfield == null)
                break MISSING_BLOCK_LABEL_72;
            if(boundfield.deserialized)
                break MISSING_BLOCK_LABEL_91;
            IllegalStateException illegalstateexception;
            jsonreader.skipValue();
              goto _L1
            try
            {
                boundfield.read(jsonreader, obj);
            }
            // Misplaced declaration of an exception variable
            catch(IllegalStateException illegalstateexception)
            {
                throw new JsonSyntaxException(illegalstateexception);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError(illegalaccessexception);
            }
              goto _L1
            jsonreader.endObject();
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
            jsonwriter.beginObject();
            try
            {
                Iterator iterator = boundFields.values().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    BoundField boundfield = (BoundField)iterator.next();
                    if(boundfield.serialized)
                    {
                        jsonwriter.name(boundfield.name);
                        boundfield.write(jsonwriter, obj);
                    }
                } while(true);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError();
            }
            jsonwriter.endObject();
        }

        private final Map boundFields;
        private final ObjectConstructor constructor;

        private Adapter(ObjectConstructor objectconstructor, Map map)
        {
            constructor = objectconstructor;
            boundFields = map;
        }

    }

    static abstract class BoundField
    {

        abstract void read(JsonReader jsonreader, Object obj)
            throws IOException, IllegalAccessException;

        abstract void write(JsonWriter jsonwriter, Object obj)
            throws IOException, IllegalAccessException;

        final boolean deserialized;
        final String name;
        final boolean serialized;

        protected BoundField(String s, boolean flag, boolean flag1)
        {
            name = s;
            serialized = flag;
            deserialized = flag1;
        }
    }


    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorconstructor, FieldNamingStrategy fieldnamingstrategy, Excluder excluder1)
    {
        constructorConstructor = constructorconstructor;
        fieldNamingPolicy = fieldnamingstrategy;
        excluder = excluder1;
    }

    private BoundField createBoundField(final Gson context, Field field, final String final_s, TypeToken typetoken, final boolean final_flag, final boolean final_flag1)
    {
        return new BoundField(typetoken, field, Primitives.isPrimitive(typetoken.getRawType())) {

            void read(JsonReader jsonreader, Object obj)
                throws IOException, IllegalAccessException
            {
                Object obj1 = typeAdapter.read(jsonreader);
                if(obj1 != null || !isPrimitive)
                    field.set(obj, obj1);
            }

            void write(JsonWriter jsonwriter, Object obj)
                throws IOException, IllegalAccessException
            {
                Object obj1 = field.get(obj);
                (new TypeAdapterRuntimeTypeWrapper(context, typeAdapter, fieldType.getType())).write(jsonwriter, obj1);
            }

            final ReflectiveTypeAdapterFactory this$0;
            final TypeAdapter typeAdapter;
            final Gson val$context;
            final Field val$field;
            final TypeToken val$fieldType;
            final boolean val$isPrimitive;

            
            {
                this$0 = ReflectiveTypeAdapterFactory.this;
                context = gson;
                fieldType = typetoken;
                field = field1;
                isPrimitive = flag2;
                super(final_s, final_flag, final_flag1);
                typeAdapter = context.getAdapter(fieldType);
            }
        }
;
    }

    private Map getBoundFields(Gson gson, TypeToken typetoken, Class class1)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        if(!class1.isInterface()) goto _L2; else goto _L1
_L1:
        return linkedhashmap;
_L2:
        java.lang.reflect.Type type = typetoken.getType();
_L8:
        Field afield[];
        int i;
        int j;
        if(class1 == java/lang/Object)
            continue; /* Loop/switch isn't completed */
        afield = class1.getDeclaredFields();
        i = afield.length;
        j = 0;
_L4:
        Field field;
        boolean flag;
        boolean flag1;
        if(j >= i)
            break MISSING_BLOCK_LABEL_206;
        field = afield[j];
        flag = excludeField(field, true);
        flag1 = excludeField(field, false);
        if(flag || flag1)
            break; /* Loop/switch isn't completed */
_L6:
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        BoundField boundfield1;
        field.setAccessible(true);
        java.lang.reflect.Type type3 = typetoken.getType();
        java.lang.reflect.Type type4 = field.getGenericType();
        java.lang.reflect.Type type5 = com.newrelic.com.google.gson.internal..Gson.Types.resolve(type3, class1, type4);
        BoundField boundfield = createBoundField(gson, field, getFieldName(field), TypeToken.get(type5), flag, flag1);
        boundfield1 = (BoundField)linkedhashmap.put(boundfield.name, boundfield);
        if(boundfield1 == null) goto _L6; else goto _L5
_L5:
        throw new IllegalArgumentException((new StringBuilder()).append(type).append(" declares multiple JSON fields named ").append(boundfield1.name).toString());
        java.lang.reflect.Type type1 = typetoken.getType();
        java.lang.reflect.Type type2 = class1.getGenericSuperclass();
        typetoken = TypeToken.get(com.newrelic.com.google.gson.internal..Gson.Types.resolve(type1, class1, type2));
        class1 = typetoken.getRawType();
        if(true) goto _L8; else goto _L7
_L7:
        if(true) goto _L1; else goto _L9
_L9:
    }

    private String getFieldName(Field field)
    {
        SerializedName serializedname = (SerializedName)field.getAnnotation(com/newrelic/com/google/gson/annotations/SerializedName);
        if(serializedname == null)
            return fieldNamingPolicy.translateName(field);
        else
            return serializedname.value();
    }

    public TypeAdapter create(Gson gson, TypeToken typetoken)
    {
        Class class1 = typetoken.getRawType();
        if(!java/lang/Object.isAssignableFrom(class1))
            return null;
        else
            return new Adapter(constructorConstructor.get(typetoken), getBoundFields(gson, typetoken, class1));
    }

    public boolean excludeField(Field field, boolean flag)
    {
        return !excluder.excludeClass(field.getType(), flag) && !excluder.excludeField(field, flag);
    }

    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
}
