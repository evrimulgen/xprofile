// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.regex.Pattern;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            ToStringSerializer, UUIDSerializer, FileSerializer, ClassSerializer, 
//            NullSerializer, StdScalarSerializer, StdSerializer

public class StdJdkSerializers
{
    public static final class AtomicBooleanSerializer extends StdScalarSerializer
    {

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            jsonformatvisitorwrapper.expectBooleanFormat(javatype);
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("boolean", true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((AtomicBoolean)obj, jsongenerator, serializerprovider);
        }

        public void serialize(AtomicBoolean atomicboolean, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeBoolean(atomicboolean.get());
        }

        public AtomicBooleanSerializer()
        {
            super(java/util/concurrent/atomic/AtomicBoolean, false);
        }
    }

    public static final class AtomicIntegerSerializer extends StdScalarSerializer
    {

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            JsonIntegerFormatVisitor jsonintegerformatvisitor = jsonformatvisitorwrapper.expectIntegerFormat(javatype);
            if(jsonintegerformatvisitor != null)
                jsonintegerformatvisitor.numberType(com.fasterxml.jackson.core.JsonParser.NumberType.INT);
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("integer", true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((AtomicInteger)obj, jsongenerator, serializerprovider);
        }

        public void serialize(AtomicInteger atomicinteger, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeNumber(atomicinteger.get());
        }

        public AtomicIntegerSerializer()
        {
            super(java/util/concurrent/atomic/AtomicInteger, false);
        }
    }

    public static final class AtomicLongSerializer extends StdScalarSerializer
    {

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            JsonIntegerFormatVisitor jsonintegerformatvisitor = jsonformatvisitorwrapper.expectIntegerFormat(javatype);
            if(jsonintegerformatvisitor != null)
                jsonintegerformatvisitor.numberType(com.fasterxml.jackson.core.JsonParser.NumberType.LONG);
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("integer", true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((AtomicLong)obj, jsongenerator, serializerprovider);
        }

        public void serialize(AtomicLong atomiclong, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeNumber(atomiclong.get());
        }

        public AtomicLongSerializer()
        {
            super(java/util/concurrent/atomic/AtomicLong, false);
        }
    }

    public static final class AtomicReferenceSerializer extends StdSerializer
    {

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            jsonformatvisitorwrapper.expectAnyFormat(javatype);
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            return createSchemaNode("any", true);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((AtomicReference)obj, jsongenerator, serializerprovider);
        }

        public void serialize(AtomicReference atomicreference, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializerprovider.defaultSerializeValue(atomicreference.get(), jsongenerator);
        }

        public AtomicReferenceSerializer()
        {
            super(java/util/concurrent/atomic/AtomicReference, false);
        }
    }


    public StdJdkSerializers()
    {
    }

    public static Collection all()
    {
        HashMap hashmap = new HashMap();
        ToStringSerializer tostringserializer = ToStringSerializer.instance;
        hashmap.put(java/net/URL, tostringserializer);
        hashmap.put(java/net/URI, tostringserializer);
        hashmap.put(java/util/Currency, tostringserializer);
        hashmap.put(java/util/UUID, new UUIDSerializer());
        hashmap.put(java/util/regex/Pattern, tostringserializer);
        hashmap.put(java/util/Locale, tostringserializer);
        hashmap.put(java/util/Locale, tostringserializer);
        hashmap.put(java/util/concurrent/atomic/AtomicReference, com/fasterxml/jackson/databind/ser/std/StdJdkSerializers$AtomicReferenceSerializer);
        hashmap.put(java/util/concurrent/atomic/AtomicBoolean, com/fasterxml/jackson/databind/ser/std/StdJdkSerializers$AtomicBooleanSerializer);
        hashmap.put(java/util/concurrent/atomic/AtomicInteger, com/fasterxml/jackson/databind/ser/std/StdJdkSerializers$AtomicIntegerSerializer);
        hashmap.put(java/util/concurrent/atomic/AtomicLong, com/fasterxml/jackson/databind/ser/std/StdJdkSerializers$AtomicLongSerializer);
        hashmap.put(java/io/File, com/fasterxml/jackson/databind/ser/std/FileSerializer);
        hashmap.put(java/lang/Class, com/fasterxml/jackson/databind/ser/std/ClassSerializer);
        hashmap.put(Void.TYPE, com/fasterxml/jackson/databind/ser/std/NullSerializer);
        return hashmap.entrySet();
    }
}
