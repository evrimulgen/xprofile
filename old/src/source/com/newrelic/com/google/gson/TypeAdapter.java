// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;

import com.newrelic.com.google.gson.internal.bind.JsonTreeReader;
import com.newrelic.com.google.gson.internal.bind.JsonTreeWriter;
import com.newrelic.com.google.gson.stream.JsonReader;
import com.newrelic.com.google.gson.stream.JsonToken;
import com.newrelic.com.google.gson.stream.JsonWriter;
import java.io.*;

// Referenced classes of package com.newrelic.com.google.gson:
//            JsonIOException, JsonElement

public abstract class TypeAdapter
{

    public TypeAdapter()
    {
    }

    public final Object fromJson(Reader reader)
        throws IOException
    {
        return read(new JsonReader(reader));
    }

    public final Object fromJson(String s)
        throws IOException
    {
        return fromJson(((Reader) (new StringReader(s))));
    }

    public final Object fromJsonTree(JsonElement jsonelement)
    {
        Object obj;
        try
        {
            obj = read(new JsonTreeReader(jsonelement));
        }
        catch(IOException ioexception)
        {
            throw new JsonIOException(ioexception);
        }
        return obj;
    }

    public final TypeAdapter nullSafe()
    {
        return new TypeAdapter() {

            public Object read(JsonReader jsonreader)
                throws IOException
            {
                if(jsonreader.peek() == JsonToken.NULL)
                {
                    jsonreader.nextNull();
                    return null;
                } else
                {
                    return TypeAdapter.this.read(jsonreader);
                }
            }

            public void write(JsonWriter jsonwriter, Object obj)
                throws IOException
            {
                if(obj == null)
                {
                    jsonwriter.nullValue();
                    return;
                } else
                {
                    TypeAdapter.this.write(jsonwriter, obj);
                    return;
                }
            }

            final TypeAdapter this$0;

            
            {
                this$0 = TypeAdapter.this;
                super();
            }
        }
;
    }

    public abstract Object read(JsonReader jsonreader)
        throws IOException;

    public final String toJson(Object obj)
        throws IOException
    {
        StringWriter stringwriter = new StringWriter();
        toJson(((Writer) (stringwriter)), obj);
        return stringwriter.toString();
    }

    public final void toJson(Writer writer, Object obj)
        throws IOException
    {
        write(new JsonWriter(writer), obj);
    }

    public final JsonElement toJsonTree(Object obj)
    {
        JsonElement jsonelement;
        try
        {
            JsonTreeWriter jsontreewriter = new JsonTreeWriter();
            write(jsontreewriter, obj);
            jsonelement = jsontreewriter.get();
        }
        catch(IOException ioexception)
        {
            throw new JsonIOException(ioexception);
        }
        return jsonelement;
    }

    public abstract void write(JsonWriter jsonwriter, Object obj)
        throws IOException;
}
