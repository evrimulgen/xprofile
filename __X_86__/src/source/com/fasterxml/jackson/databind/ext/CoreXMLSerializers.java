// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.*;
import java.io.IOException;
import java.lang.reflect.Type;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLSerializers extends com.fasterxml.jackson.databind.ser.Serializers.Base
{
    public static class XMLGregorianCalendarSerializer extends StdSerializer
    {

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
            throws JsonMappingException
        {
            CalendarSerializer.instance.acceptJsonFormatVisitor(jsonformatvisitorwrapper, null);
        }

        public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
            throws JsonMappingException
        {
            return CalendarSerializer.instance.getSchema(serializerprovider, type);
        }

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((XMLGregorianCalendar)obj, jsongenerator, serializerprovider);
        }

        public void serialize(XMLGregorianCalendar xmlgregoriancalendar, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            CalendarSerializer.instance.serialize(xmlgregoriancalendar.toGregorianCalendar(), jsongenerator, serializerprovider);
        }

        public static final XMLGregorianCalendarSerializer instance = new XMLGregorianCalendarSerializer();


        public XMLGregorianCalendarSerializer()
        {
            super(javax/xml/datatype/XMLGregorianCalendar);
        }
    }


    public CoreXMLSerializers()
    {
    }

    public JsonSerializer findSerializer(SerializationConfig serializationconfig, JavaType javatype, BeanDescription beandescription)
    {
        Class class1 = javatype.getRawClass();
        if(javax/xml/datatype/Duration.isAssignableFrom(class1) || javax/xml/namespace/QName.isAssignableFrom(class1))
            return ToStringSerializer.instance;
        if(javax/xml/datatype/XMLGregorianCalendar.isAssignableFrom(class1))
            return XMLGregorianCalendarSerializer.instance;
        else
            return null;
    }
}
