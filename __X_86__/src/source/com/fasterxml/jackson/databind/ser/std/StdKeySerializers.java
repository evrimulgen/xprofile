// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdKeySerializer, StdSerializer

public class StdKeySerializers
{
    public static class CalendarKeySerializer extends StdSerializer
    {

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Calendar)obj, jsongenerator, serializerprovider);
        }

        public void serialize(Calendar calendar, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializerprovider.defaultSerializeDateKey(calendar.getTimeInMillis(), jsongenerator);
        }

        protected static final JsonSerializer instance = new CalendarKeySerializer();


        public CalendarKeySerializer()
        {
            super(java/util/Calendar);
        }
    }

    public static class DateKeySerializer extends StdSerializer
    {

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((Date)obj, jsongenerator, serializerprovider);
        }

        public void serialize(Date date, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serializerprovider.defaultSerializeDateKey(date, jsongenerator);
        }

        protected static final JsonSerializer instance = new DateKeySerializer();


        public DateKeySerializer()
        {
            super(java/util/Date);
        }
    }

    public static class StringKeySerializer extends StdSerializer
    {

        public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            serialize((String)obj, jsongenerator, serializerprovider);
        }

        public void serialize(String s, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeFieldName(s);
        }

        public StringKeySerializer()
        {
            super(java/lang/String);
        }
    }


    private StdKeySerializers()
    {
    }

    public static JsonSerializer getStdKeySerializer(JavaType javatype)
    {
        if(javatype == null)
            return DEFAULT_KEY_SERIALIZER;
        Class class1 = javatype.getRawClass();
        if(class1 == java/lang/String)
            return DEFAULT_STRING_SERIALIZER;
        if(class1 == java/lang/Object)
            return DEFAULT_KEY_SERIALIZER;
        if(java/util/Date.isAssignableFrom(class1))
            return DateKeySerializer.instance;
        if(java/util/Calendar.isAssignableFrom(class1))
            return CalendarKeySerializer.instance;
        else
            return DEFAULT_KEY_SERIALIZER;
    }

    protected static final JsonSerializer DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
    protected static final JsonSerializer DEFAULT_STRING_SERIALIZER = new StringKeySerializer();

}
