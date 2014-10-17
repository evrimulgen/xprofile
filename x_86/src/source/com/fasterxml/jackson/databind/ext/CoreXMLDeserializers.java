// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.util.GregorianCalendar;
import javax.xml.datatype.*;
import javax.xml.namespace.QName;

public class CoreXMLDeserializers extends com.fasterxml.jackson.databind.deser.Deserializers.Base
{
    public static class DurationDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected Duration _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return CoreXMLDeserializers._dataTypeFactory.newDuration(s);
        }

        public static final DurationDeserializer instance = new DurationDeserializer();
        private static final long serialVersionUID = 1L;


        public DurationDeserializer()
        {
            super(javax/xml/datatype/Duration);
        }
    }

    public static class GregorianCalendarDeserializer extends StdScalarDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public XMLGregorianCalendar deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            java.util.Date date = _parseDate(jsonparser, deserializationcontext);
            if(date == null)
                return null;
            GregorianCalendar gregoriancalendar = new GregorianCalendar();
            gregoriancalendar.setTime(date);
            java.util.TimeZone timezone = deserializationcontext.getTimeZone();
            if(timezone != null)
                gregoriancalendar.setTimeZone(timezone);
            return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(gregoriancalendar);
        }

        public static final GregorianCalendarDeserializer instance = new GregorianCalendarDeserializer();
        private static final long serialVersionUID = 1L;


        public GregorianCalendarDeserializer()
        {
            super(javax/xml/datatype/XMLGregorianCalendar);
        }
    }

    public static class QNameDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected QName _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return QName.valueOf(s);
        }

        public static final QNameDeserializer instance = new QNameDeserializer();
        private static final long serialVersionUID = 1L;


        public QNameDeserializer()
        {
            super(javax/xml/namespace/QName);
        }
    }


    public CoreXMLDeserializers()
    {
    }

    public JsonDeserializer findBeanDeserializer(JavaType javatype, DeserializationConfig deserializationconfig, BeanDescription beandescription)
    {
        Class class1 = javatype.getRawClass();
        if(class1 == javax/xml/namespace/QName)
            return QNameDeserializer.instance;
        if(class1 == javax/xml/datatype/XMLGregorianCalendar)
            return GregorianCalendarDeserializer.instance;
        if(class1 == javax/xml/datatype/Duration)
            return DurationDeserializer.instance;
        else
            return null;
    }

    static final DatatypeFactory _dataTypeFactory;

    static 
    {
        try
        {
            _dataTypeFactory = DatatypeFactory.newInstance();
        }
        catch(DatatypeConfigurationException datatypeconfigurationexception)
        {
            throw new RuntimeException(datatypeconfigurationexception);
        }
    }
}
