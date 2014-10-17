// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            DateTimeSerializerBase

public class CalendarSerializer extends DateTimeSerializerBase
{

    public CalendarSerializer()
    {
        this(false, null);
    }

    public CalendarSerializer(boolean flag, DateFormat dateformat)
    {
        super(java/util/Calendar, flag, dateformat);
    }

    protected volatile long _timestamp(Object obj)
    {
        return _timestamp((Calendar)obj);
    }

    protected long _timestamp(Calendar calendar)
    {
        if(calendar == null)
            return 0L;
        else
            return calendar.getTimeInMillis();
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Calendar)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Calendar calendar, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(_useTimestamp)
        {
            jsongenerator.writeNumber(_timestamp(calendar));
            return;
        }
        if(_customFormat != null)
        {
            synchronized(_customFormat)
            {
                jsongenerator.writeString(_customFormat.format(calendar));
            }
            return;
        } else
        {
            serializerprovider.defaultSerializeDateValue(calendar.getTime(), jsongenerator);
            return;
        }
        exception;
        dateformat;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public CalendarSerializer withFormat(boolean flag, DateFormat dateformat)
    {
        if(flag)
            return new CalendarSerializer(true, null);
        else
            return new CalendarSerializer(false, dateformat);
    }

    public volatile DateTimeSerializerBase withFormat(boolean flag, DateFormat dateformat)
    {
        return withFormat(flag, dateformat);
    }

    public static final CalendarSerializer instance = new CalendarSerializer();

}
