// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal;

import com.newrelic.com.google.gson.*;
import com.newrelic.com.google.gson.internal.bind.TypeAdapters;
import com.newrelic.com.google.gson.stream.*;
import java.io.*;

public final class Streams
{
    private static final class AppendableWriter extends Writer
    {

        public void close()
        {
        }

        public void flush()
        {
        }

        public void write(int i)
            throws IOException
        {
            appendable.append((char)i);
        }

        public void write(char ac[], int i, int j)
            throws IOException
        {
            currentWrite.chars = ac;
            appendable.append(currentWrite, i, i + j);
        }

        private final Appendable appendable;
        private final CurrentWrite currentWrite;

        private AppendableWriter(Appendable appendable1)
        {
            currentWrite = new CurrentWrite();
            appendable = appendable1;
        }

    }

    static class AppendableWriter.CurrentWrite
        implements CharSequence
    {

        public char charAt(int i)
        {
            return chars[i];
        }

        public int length()
        {
            return chars.length;
        }

        public CharSequence subSequence(int i, int j)
        {
            return new String(chars, i, j - i);
        }

        char chars[];

        AppendableWriter.CurrentWrite()
        {
        }
    }


    public Streams()
    {
    }

    public static JsonElement parse(JsonReader jsonreader)
        throws JsonParseException
    {
        boolean flag = true;
        JsonElement jsonelement;
        try
        {
            jsonreader.peek();
        }
        catch(EOFException eofexception)
        {
            if(flag)
                return JsonNull.INSTANCE;
            else
                throw new JsonSyntaxException(eofexception);
        }
        catch(MalformedJsonException malformedjsonexception)
        {
            throw new JsonSyntaxException(malformedjsonexception);
        }
        catch(IOException ioexception)
        {
            throw new JsonIOException(ioexception);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new JsonSyntaxException(numberformatexception);
        }
        flag = false;
        jsonelement = (JsonElement)TypeAdapters.JSON_ELEMENT.read(jsonreader);
        return jsonelement;
    }

    public static void write(JsonElement jsonelement, JsonWriter jsonwriter)
        throws IOException
    {
        TypeAdapters.JSON_ELEMENT.write(jsonwriter, jsonelement);
    }

    public static Writer writerForAppendable(Appendable appendable)
    {
        if(appendable instanceof Writer)
            return (Writer)appendable;
        else
            return new AppendableWriter(appendable);
    }
}
