// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.*;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.*;
import java.io.IOException;
import java.sql.Time;
import java.text.*;
import java.util.Date;

public final class TimeTypeAdapter extends TypeAdapter
{

    public TimeTypeAdapter()
    {
    }

    public volatile Object read(JsonReader jsonreader)
        throws IOException
    {
        return read(jsonreader);
    }

    public Time read(JsonReader jsonreader)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(jsonreader.peek() != JsonToken.NULL) goto _L2; else goto _L1
_L1:
        jsonreader.nextNull();
        Time time = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return time;
_L2:
        time = new Time(format.parse(jsonreader.nextString()).getTime());
        if(true) goto _L4; else goto _L3
_L3:
        ParseException parseexception;
        parseexception;
        throw new JsonSyntaxException(parseexception);
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public volatile void write(JsonWriter jsonwriter, Object obj)
        throws IOException
    {
        write(jsonwriter, (Time)obj);
    }

    public void write(JsonWriter jsonwriter, Time time)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(time != null) goto _L2; else goto _L1
_L1:
        String s1 = null;
_L4:
        jsonwriter.value(s1);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        String s = format.format(time);
        s1 = s;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

        public TypeAdapter create(Gson gson, TypeToken typetoken)
        {
            if(typetoken.getRawType() == java/sql/Time)
                return new TimeTypeAdapter();
            else
                return null;
        }

    }
;
    private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");

}
