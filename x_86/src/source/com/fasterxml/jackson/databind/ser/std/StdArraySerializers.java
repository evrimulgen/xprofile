// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            ArraySerializerBase, StdSerializer

public class StdArraySerializers
{
    public static final class BooleanArraySerializer extends ArraySerializerBase
    {

        public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return this;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            if(jsonformatvisitorwrapper != null)
            {
                JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
                if(jsonarrayformatvisitor != null)
                    jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.BOOLEAN);
            }
        }

        public JsonSerializer getContentSerializer()
        {
            return null;
        }

        public JavaType getContentType()
        {
            return VALUE_TYPE;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("boolean"));
            return objectnode;
        }

        public volatile boolean hasSingleElement(Object obj)
        {
            return hasSingleElement((boolean[])obj);
        }

        public boolean hasSingleElement(boolean aflag[])
        {
            return aflag.length == 1;
        }

        public volatile boolean isEmpty(Object obj)
        {
            return isEmpty((boolean[])obj);
        }

        public boolean isEmpty(boolean aflag[])
        {
            return aflag == null || aflag.length == 0;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((boolean[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(boolean aflag[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for(int j = aflag.length; i < j; i++)
                jsongenerator.writeBoolean(aflag[i]);

        }

        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(java/lang/Boolean);


        public BooleanArraySerializer()
        {
            super([Z, null);
        }
    }

    public static final class ByteArraySerializer extends StdSerializer
    {

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            if(jsonformatvisitorwrapper != null)
            {
                JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
                if(jsonarrayformatvisitor != null)
                    jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.STRING);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("string"));
            return objectnode;
        }

        public volatile boolean isEmpty(Object obj)
        {
            return isEmpty((byte[])obj);
        }

        public boolean isEmpty(byte abyte0[])
        {
            return abyte0 == null || abyte0.length == 0;
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((byte[])obj, jsongenerator, serializerprovider);
        }

        public void serialize(byte abyte0[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeBinary(serializerprovider.getConfig().getBase64Variant(), abyte0, 0, abyte0.length);
        }

        public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonProcessingException
        {
            serializeWithType((byte[])obj, jsongenerator, serializerprovider, typeserializer);
        }

        public void serializeWithType(byte abyte0[], JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            typeserializer.writeTypePrefixForScalar(abyte0, jsongenerator);
            jsongenerator.writeBinary(serializerprovider.getConfig().getBase64Variant(), abyte0, 0, abyte0.length);
            typeserializer.writeTypeSuffixForScalar(abyte0, jsongenerator);
        }

        public ByteArraySerializer()
        {
            super([B);
        }
    }

    public static final class CharArraySerializer extends StdSerializer
    {

        private final void _writeArrayContents(JsonGenerator jsongenerator, char ac[])
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for(int j = ac.length; i < j; i++)
                jsongenerator.writeString(ac, i, 1);

        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            if(jsonformatvisitorwrapper != null)
            {
                JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
                if(jsonarrayformatvisitor != null)
                    jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.STRING);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            ObjectNode objectnode1 = createSchemaNode("string");
            objectnode1.put("type", "string");
            objectnode.put("items", objectnode1);
            return objectnode;
        }

        public volatile boolean isEmpty(Object obj)
        {
            return isEmpty((char[])obj);
        }

        public boolean isEmpty(char ac[])
        {
            return ac == null || ac.length == 0;
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((char[])obj, jsongenerator, serializerprovider);
        }

        public void serialize(char ac[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            if(serializerprovider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS))
            {
                jsongenerator.writeStartArray();
                _writeArrayContents(jsongenerator, ac);
                jsongenerator.writeEndArray();
                return;
            } else
            {
                jsongenerator.writeString(ac, 0, ac.length);
                return;
            }
        }

        public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonProcessingException
        {
            serializeWithType((char[])obj, jsongenerator, serializerprovider, typeserializer);
        }

        public void serializeWithType(char ac[], JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
            throws IOException, JsonGenerationException
        {
            if(serializerprovider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS))
            {
                typeserializer.writeTypePrefixForArray(ac, jsongenerator);
                _writeArrayContents(jsongenerator, ac);
                typeserializer.writeTypeSuffixForArray(ac, jsongenerator);
                return;
            } else
            {
                typeserializer.writeTypePrefixForScalar(ac, jsongenerator);
                jsongenerator.writeString(ac, 0, ac.length);
                typeserializer.writeTypeSuffixForScalar(ac, jsongenerator);
                return;
            }
        }

        public CharArraySerializer()
        {
            super([C);
        }
    }

    public static final class DoubleArraySerializer extends ArraySerializerBase
    {

        public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return this;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            if(jsonformatvisitorwrapper != null)
            {
                JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
                if(jsonarrayformatvisitor != null)
                    jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.NUMBER);
            }
        }

        public JsonSerializer getContentSerializer()
        {
            return null;
        }

        public JavaType getContentType()
        {
            return VALUE_TYPE;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("number"));
            return objectnode;
        }

        public volatile boolean hasSingleElement(Object obj)
        {
            return hasSingleElement((double[])obj);
        }

        public boolean hasSingleElement(double ad[])
        {
            return ad.length == 1;
        }

        public volatile boolean isEmpty(Object obj)
        {
            return isEmpty((double[])obj);
        }

        public boolean isEmpty(double ad[])
        {
            return ad == null || ad.length == 0;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((double[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(double ad[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for(int j = ad.length; i < j; i++)
                jsongenerator.writeNumber(ad[i]);

        }

        private static final JavaType VALUE_TYPE;

        static 
        {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Double.TYPE);
        }

        public DoubleArraySerializer()
        {
            super([D, null);
        }
    }

    public static final class FloatArraySerializer extends TypedPrimitiveArraySerializer
    {

        public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new FloatArraySerializer(this, _property, typeserializer);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            if(jsonformatvisitorwrapper != null)
            {
                JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
                if(jsonarrayformatvisitor != null)
                    jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.NUMBER);
            }
        }

        public JsonSerializer getContentSerializer()
        {
            return null;
        }

        public JavaType getContentType()
        {
            return VALUE_TYPE;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("number"));
            return objectnode;
        }

        public volatile boolean hasSingleElement(Object obj)
        {
            return hasSingleElement((float[])obj);
        }

        public boolean hasSingleElement(float af[])
        {
            return af.length == 1;
        }

        public volatile boolean isEmpty(Object obj)
        {
            return isEmpty((float[])obj);
        }

        public boolean isEmpty(float af[])
        {
            return af == null || af.length == 0;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((float[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(float af[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            if(_valueTypeSerializer != null)
            {
                for(int k = af.length; i < k; i++)
                {
                    _valueTypeSerializer.writeTypePrefixForScalar(null, jsongenerator, Float.TYPE);
                    jsongenerator.writeNumber(af[i]);
                    _valueTypeSerializer.writeTypeSuffixForScalar(null, jsongenerator);
                }

            } else
            {
                for(int j = af.length; i < j; i++)
                    jsongenerator.writeNumber(af[i]);

            }
        }

        private static final JavaType VALUE_TYPE;

        static 
        {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Float.TYPE);
        }

        public FloatArraySerializer()
        {
            super([F);
        }

        public FloatArraySerializer(FloatArraySerializer floatarrayserializer, BeanProperty beanproperty, TypeSerializer typeserializer)
        {
            super(floatarrayserializer, beanproperty, typeserializer);
        }
    }

    public static final class IntArraySerializer extends ArraySerializerBase
    {

        public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return this;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            if(jsonformatvisitorwrapper != null)
            {
                JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
                if(jsonarrayformatvisitor != null)
                    jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.INTEGER);
            }
        }

        public JsonSerializer getContentSerializer()
        {
            return null;
        }

        public JavaType getContentType()
        {
            return VALUE_TYPE;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("integer"));
            return objectnode;
        }

        public volatile boolean hasSingleElement(Object obj)
        {
            return hasSingleElement((int[])obj);
        }

        public boolean hasSingleElement(int ai[])
        {
            return ai.length == 1;
        }

        public volatile boolean isEmpty(Object obj)
        {
            return isEmpty((int[])obj);
        }

        public boolean isEmpty(int ai[])
        {
            return ai == null || ai.length == 0;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((int[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(int ai[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            for(int j = ai.length; i < j; i++)
                jsongenerator.writeNumber(ai[i]);

        }

        private static final JavaType VALUE_TYPE;

        static 
        {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Integer.TYPE);
        }

        public IntArraySerializer()
        {
            super([I, null);
        }
    }

    public static final class LongArraySerializer extends TypedPrimitiveArraySerializer
    {

        public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new LongArraySerializer(this, _property, typeserializer);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            if(jsonformatvisitorwrapper != null)
            {
                JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
                if(jsonarrayformatvisitor != null)
                    jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.NUMBER);
            }
        }

        public JsonSerializer getContentSerializer()
        {
            return null;
        }

        public JavaType getContentType()
        {
            return VALUE_TYPE;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("number", true));
            return objectnode;
        }

        public volatile boolean hasSingleElement(Object obj)
        {
            return hasSingleElement((long[])obj);
        }

        public boolean hasSingleElement(long al[])
        {
            return al.length == 1;
        }

        public volatile boolean isEmpty(Object obj)
        {
            return isEmpty((long[])obj);
        }

        public boolean isEmpty(long al[])
        {
            return al == null || al.length == 0;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((long[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(long al[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            if(_valueTypeSerializer != null)
            {
                for(int k = al.length; i < k; i++)
                {
                    _valueTypeSerializer.writeTypePrefixForScalar(null, jsongenerator, Long.TYPE);
                    jsongenerator.writeNumber(al[i]);
                    _valueTypeSerializer.writeTypeSuffixForScalar(null, jsongenerator);
                }

            } else
            {
                for(int j = al.length; i < j; i++)
                    jsongenerator.writeNumber(al[i]);

            }
        }

        private static final JavaType VALUE_TYPE;

        static 
        {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Long.TYPE);
        }

        public LongArraySerializer()
        {
            super([J);
        }

        public LongArraySerializer(LongArraySerializer longarrayserializer, BeanProperty beanproperty, TypeSerializer typeserializer)
        {
            super(longarrayserializer, beanproperty, typeserializer);
        }
    }

    public static final class ShortArraySerializer extends TypedPrimitiveArraySerializer
    {

        public ContainerSerializer _withValueTypeSerializer(TypeSerializer typeserializer)
        {
            return new ShortArraySerializer(this, _property, typeserializer);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            if(jsonformatvisitorwrapper != null)
            {
                JsonArrayFormatVisitor jsonarrayformatvisitor = jsonformatvisitorwrapper.expectArrayFormat(javatype);
                if(jsonarrayformatvisitor != null)
                    jsonarrayformatvisitor.itemsFormat(JsonFormatTypes.INTEGER);
            }
        }

        public JsonSerializer getContentSerializer()
        {
            return null;
        }

        public JavaType getContentType()
        {
            return VALUE_TYPE;
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        {
            ObjectNode objectnode = createSchemaNode("array", true);
            objectnode.put("items", createSchemaNode("integer"));
            return objectnode;
        }

        public volatile boolean hasSingleElement(Object obj)
        {
            return hasSingleElement((short[])obj);
        }

        public boolean hasSingleElement(short aword0[])
        {
            return aword0.length == 1;
        }

        public volatile boolean isEmpty(Object obj)
        {
            return isEmpty((short[])obj);
        }

        public boolean isEmpty(short aword0[])
        {
            return aword0 == null || aword0.length == 0;
        }

        public volatile void serializeContents(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializeContents((short[])obj, jsongenerator, serializerprovider);
        }

        public void serializeContents(short aword0[], JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            int i = 0;
            if(_valueTypeSerializer != null)
            {
                for(int k = aword0.length; i < k; i++)
                {
                    _valueTypeSerializer.writeTypePrefixForScalar(null, jsongenerator, Short.TYPE);
                    jsongenerator.writeNumber(aword0[i]);
                    _valueTypeSerializer.writeTypeSuffixForScalar(null, jsongenerator);
                }

            } else
            {
                for(int j = aword0.length; i < j; i++)
                    jsongenerator.writeNumber(aword0[i]);

            }
        }

        private static final JavaType VALUE_TYPE;

        static 
        {
            VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);
        }

        public ShortArraySerializer()
        {
            super([S);
        }

        public ShortArraySerializer(ShortArraySerializer shortarrayserializer, BeanProperty beanproperty, TypeSerializer typeserializer)
        {
            super(shortarrayserializer, beanproperty, typeserializer);
        }
    }

    protected static abstract class TypedPrimitiveArraySerializer extends ArraySerializerBase
    {

        protected final TypeSerializer _valueTypeSerializer;

        protected TypedPrimitiveArraySerializer(TypedPrimitiveArraySerializer typedprimitivearrayserializer, BeanProperty beanproperty, TypeSerializer typeserializer)
        {
            super(typedprimitivearrayserializer, beanproperty);
            _valueTypeSerializer = typeserializer;
        }

        protected TypedPrimitiveArraySerializer(Class class1)
        {
            super(class1);
            _valueTypeSerializer = null;
        }
    }


    protected StdArraySerializers()
    {
    }

    public static JsonSerializer findStandardImpl(Class class1)
    {
        return (JsonSerializer)_arraySerializers.get(class1.getName());
    }

    protected static final HashMap _arraySerializers;

    static 
    {
        _arraySerializers = new HashMap();
        _arraySerializers.put([Z.getName(), new BooleanArraySerializer());
        _arraySerializers.put([B.getName(), new ByteArraySerializer());
        _arraySerializers.put([C.getName(), new CharArraySerializer());
        _arraySerializers.put([S.getName(), new ShortArraySerializer());
        _arraySerializers.put([I.getName(), new IntArraySerializer());
        _arraySerializers.put([J.getName(), new LongArraySerializer());
        _arraySerializers.put([F.getName(), new FloatArraySerializer());
        _arraySerializers.put([D.getName(), new DoubleArraySerializer());
    }
}
