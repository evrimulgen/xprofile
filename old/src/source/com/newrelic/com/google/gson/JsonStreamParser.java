// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.internal.Streams;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.MalformedJsonException;
import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.newrelic.com.google.gson:
//            JsonSyntaxException, JsonIOException, JsonParseException, JsonElement

public final class JsonStreamParser
    implements Iterator
{

    public JsonStreamParser(Reader reader)
    {
        parser = new JsonReader(reader);
        parser.setLenient(true);
        lock = new Object();
    }

    public JsonStreamParser(String s)
    {
        this(((Reader) (new StringReader(s))));
    }

    public boolean hasNext()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        JsonToken jsontoken;
        JsonToken jsontoken1;
        jsontoken = parser.peek();
        jsontoken1 = JsonToken.END_DOCUMENT;
        IOException ioexception;
        Exception exception;
        MalformedJsonException malformedjsonexception;
        boolean flag;
        if(jsontoken != jsontoken1)
            flag = true;
        else
            flag = false;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        malformedjsonexception;
        throw new JsonSyntaxException(malformedjsonexception);
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        ioexception;
        throw new JsonIOException(ioexception);
    }

    public JsonElement next()
        throws JsonParseException
    {
        if(!hasNext())
            throw new NoSuchElementException();
        JsonElement jsonelement;
        try
        {
            jsonelement = Streams.parse(parser);
        }
        catch(StackOverflowError stackoverflowerror)
        {
            throw new JsonParseException("Failed parsing JSON source to Json", stackoverflowerror);
        }
        catch(OutOfMemoryError outofmemoryerror)
        {
            throw new JsonParseException("Failed parsing JSON source to Json", outofmemoryerror);
        }
        catch(Object obj)
        {
            if(((JsonParseException) (obj)).getCause() instanceof EOFException)
                obj = new NoSuchElementException();
            throw obj;
        }
        return jsonelement;
    }

    public volatile Object next()
    {
        return next();
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    private final Object lock;
    private final JsonReader parser;
}
