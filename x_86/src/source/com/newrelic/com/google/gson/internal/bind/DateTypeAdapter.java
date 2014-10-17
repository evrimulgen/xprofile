// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.*;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.*;
import java.io.IOException;
import java.text.*;
import java.util.*;

public final class DateTypeAdapter extends TypeAdapter
{

    public DateTypeAdapter()
    {
        enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    }

    private static DateFormat buildIso8601Format()
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpledateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpledateformat;
    }

    private Date deserializeToDate(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Date date3 = localFormat.parse(s);
        Date date1 = date3;
_L1:
        this;
        JVM INSTR monitorexit ;
        return date1;
        ParseException parseexception;
        parseexception;
        Date date2 = enUsFormat.parse(s);
        date1 = date2;
          goto _L1
        ParseException parseexception1;
        parseexception1;
        Date date = iso8601Format.parse(s);
        date1 = date;
          goto _L1
        ParseException parseexception2;
        parseexception2;
        throw new JsonSyntaxException(s, parseexception2);
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public volatile Object read(JsonReader jsonreader)
        throws IOException
    {
        return read(jsonreader);
    }

    public Date read(JsonReader jsonreader)
        throws IOException
    {
        if(jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        } else
        {
            return deserializeToDate(jsonreader.nextString());
        }
    }

    public volatile void write(JsonWriter jsonwriter, Object obj)
        throws IOException
    {
        write(jsonwriter, (Date)obj);
    }

    public void write(JsonWriter jsonwriter, Date date)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(date != null)
            break MISSING_BLOCK_LABEL_14;
        jsonwriter.nullValue();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        jsonwriter.value(enUsFormat.format(date));
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

        public TypeAdapter create(Gson gson, TypeToken typetoken)
        {
            if(typetoken.getRawType() == java/util/Date)
                return new DateTypeAdapter();
            else
                return null;
        }

    }
;
    private final DateFormat enUsFormat;
    private final DateFormat iso8601Format = buildIso8601Format();
    private final DateFormat localFormat = DateFormat.getDateTimeInstance(2, 2);

}
