// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.*;
import java.io.Closeable;
import java.io.IOException;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind:
//            JsonMappingException, RuntimeJsonMappingException, JsonDeserializer, DeserializationContext, 
//            JavaType

public class MappingIterator
    implements Iterator, Closeable
{

    protected MappingIterator(JavaType javatype, JsonParser jsonparser, DeserializationContext deserializationcontext, JsonDeserializer jsondeserializer)
    {
        this(javatype, jsonparser, deserializationcontext, jsondeserializer, true, null);
    }

    protected MappingIterator(JavaType javatype, JsonParser jsonparser, DeserializationContext deserializationcontext, JsonDeserializer jsondeserializer, boolean flag, Object obj)
    {
        _type = javatype;
        _parser = jsonparser;
        _context = deserializationcontext;
        _deserializer = jsondeserializer;
        _closeParser = flag;
        if(obj == null)
            _updatedValue = null;
        else
            _updatedValue = obj;
        if(flag && jsonparser != null && jsonparser.getCurrentToken() == JsonToken.START_ARRAY)
            jsonparser.clearCurrentToken();
    }

    protected static MappingIterator emptyIterator()
    {
        return EMPTY_ITERATOR;
    }

    public void close()
        throws IOException
    {
        if(_parser != null)
            _parser.close();
    }

    public JsonLocation getCurrentLocation()
    {
        return _parser.getCurrentLocation();
    }

    public JsonParser getParser()
    {
        return _parser;
    }

    public FormatSchema getParserSchema()
    {
        return _parser.getSchema();
    }

    public boolean hasNext()
    {
        boolean flag;
        try
        {
            flag = hasNextValue();
        }
        catch(JsonMappingException jsonmappingexception)
        {
            throw new RuntimeJsonMappingException(jsonmappingexception.getMessage(), jsonmappingexception);
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException(ioexception.getMessage(), ioexception);
        }
        return flag;
    }

    public boolean hasNextValue()
        throws IOException
    {
        if(_parser != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if(_hasNextChecked)
            break; /* Loop/switch isn't completed */
        JsonToken jsontoken = _parser.getCurrentToken();
        _hasNextChecked = true;
        if(jsontoken != null)
            break; /* Loop/switch isn't completed */
        JsonToken jsontoken1 = _parser.nextToken();
        if(jsontoken1 != null && jsontoken1 != JsonToken.END_ARRAY)
            break; /* Loop/switch isn't completed */
        JsonParser jsonparser = _parser;
        _parser = null;
        if(_closeParser)
        {
            jsonparser.close();
            return false;
        }
        if(true) goto _L1; else goto _L3
_L3:
        return true;
    }

    public Object next()
    {
        Object obj;
        try
        {
            obj = nextValue();
        }
        catch(JsonMappingException jsonmappingexception)
        {
            throw new RuntimeJsonMappingException(jsonmappingexception.getMessage(), jsonmappingexception);
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException(ioexception.getMessage(), ioexception);
        }
        return obj;
    }

    public Object nextValue()
        throws IOException
    {
        if(!_hasNextChecked && !hasNextValue())
            throw new NoSuchElementException();
        if(_parser == null)
            throw new NoSuchElementException();
        _hasNextChecked = false;
        Object obj;
        if(_updatedValue == null)
        {
            obj = _deserializer.deserialize(_parser, _context);
        } else
        {
            _deserializer.deserialize(_parser, _context, _updatedValue);
            obj = _updatedValue;
        }
        _parser.clearCurrentToken();
        return obj;
    }

    public List readAll()
        throws IOException
    {
        return readAll(((List) (new ArrayList())));
    }

    public List readAll(List list)
        throws IOException
    {
        for(; hasNextValue(); list.add(nextValue()));
        return list;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    protected static final MappingIterator EMPTY_ITERATOR = new MappingIterator(null, null, null, null, false, null);
    protected final boolean _closeParser;
    protected final DeserializationContext _context;
    protected final JsonDeserializer _deserializer;
    protected boolean _hasNextChecked;
    protected JsonParser _parser;
    protected final JavaType _type;
    protected final Object _updatedValue;

}
