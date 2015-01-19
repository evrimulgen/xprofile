// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdScalarSerializer

public class EnumSerializer extends StdScalarSerializer
    implements ContextualSerializer
{

    public EnumSerializer(EnumValues enumvalues)
    {
        this(enumvalues, null);
    }

    public EnumSerializer(EnumValues enumvalues, Boolean boolean1)
    {
        super(java/lang/Enum, false);
        _values = enumvalues;
        _serializeAsIndex = boolean1;
    }

    protected static Boolean _isShapeWrittenUsingIndex(Class class1, com.fasterxml.jackson.annotation.JsonFormat.Value value, boolean flag)
    {
        com.fasterxml.jackson.annotation.JsonFormat.Shape shape;
        if(value == null)
            shape = null;
        else
            shape = value.getShape();
        while(shape == null || shape == com.fasterxml.jackson.annotation.JsonFormat.Shape.ANY || shape == com.fasterxml.jackson.annotation.JsonFormat.Shape.SCALAR) 
            return null;
        if(shape == com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
            return Boolean.FALSE;
        if(shape.isNumeric())
            return Boolean.TRUE;
        StringBuilder stringbuilder = (new StringBuilder()).append("Unsupported serialization shape (").append(shape).append(") for Enum ").append(class1.getName()).append(", not supported as ");
        String s;
        if(flag)
            s = "class";
        else
            s = "property";
        throw new IllegalArgumentException(stringbuilder.append(s).append(" annotation").toString());
    }

    public static EnumSerializer construct(Class class1, SerializationConfig serializationconfig, BeanDescription beandescription)
    {
        return construct(class1, serializationconfig, beandescription, beandescription.findExpectedFormat(null));
    }

    public static EnumSerializer construct(Class class1, SerializationConfig serializationconfig, BeanDescription beandescription, com.fasterxml.jackson.annotation.JsonFormat.Value value)
    {
        AnnotationIntrospector annotationintrospector = serializationconfig.getAnnotationIntrospector();
        EnumValues enumvalues;
        if(serializationconfig.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING))
            enumvalues = EnumValues.constructFromToString(class1, annotationintrospector);
        else
            enumvalues = EnumValues.constructFromName(class1, annotationintrospector);
        return new EnumSerializer(enumvalues, _isShapeWrittenUsingIndex(class1, value, true));
    }

    protected final boolean _serializeAsIndex(SerializerProvider serializerprovider)
    {
        if(_serializeAsIndex != null)
            return _serializeAsIndex.booleanValue();
        else
            return serializerprovider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        if(jsonformatvisitorwrapper.getProvider().isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX))
        {
            JsonIntegerFormatVisitor jsonintegerformatvisitor = jsonformatvisitorwrapper.expectIntegerFormat(javatype);
            if(jsonintegerformatvisitor != null)
                jsonintegerformatvisitor.numberType(com.fasterxml.jackson.core.JsonParser.NumberType.INT);
        } else
        {
            JsonStringFormatVisitor jsonstringformatvisitor = jsonformatvisitorwrapper.expectStringFormat(javatype);
            if(javatype != null && jsonstringformatvisitor != null && javatype.isEnumType())
            {
                LinkedHashSet linkedhashset = new LinkedHashSet();
                for(Iterator iterator = _values.values().iterator(); iterator.hasNext(); linkedhashset.add(((SerializedString)iterator.next()).getValue()));
                jsonstringformatvisitor.enumTypes(linkedhashset);
                return;
            }
        }
    }

    public JsonSerializer createContextual(SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(beanproperty != null)
        {
            com.fasterxml.jackson.annotation.JsonFormat.Value value = serializerprovider.getAnnotationIntrospector().findFormat(beanproperty.getMember());
            if(value != null)
            {
                Boolean boolean1 = _isShapeWrittenUsingIndex(beanproperty.getType().getRawClass(), value, false);
                if(boolean1 != _serializeAsIndex)
                    this = new EnumSerializer(_values, boolean1);
            }
        }
        return this;
    }

    public EnumValues getEnumValues()
    {
        return _values;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        if(_serializeAsIndex(serializerprovider))
            return createSchemaNode("integer", true);
        ObjectNode objectnode = createSchemaNode("string", true);
        if(type != null && serializerprovider.constructType(type).isEnumType())
        {
            ArrayNode arraynode = objectnode.putArray("enum");
            for(Iterator iterator = _values.values().iterator(); iterator.hasNext(); arraynode.add(((SerializedString)iterator.next()).getValue()));
        }
        return objectnode;
    }

    public final void serialize(Enum enum, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(_serializeAsIndex(serializerprovider))
        {
            jsongenerator.writeNumber(enum.ordinal());
            return;
        } else
        {
            jsongenerator.writeString(_values.serializedValueFor(enum));
            return;
        }
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Enum)obj, jsongenerator, serializerprovider);
    }

    protected final Boolean _serializeAsIndex;
    protected final EnumValues _values;
}
