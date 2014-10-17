// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.impl:
//            ObjectIdReader, ReadableObjectId, PropertyValue

public final class PropertyValueBuffer
{

    public PropertyValueBuffer(JsonParser jsonparser, DeserializationContext deserializationcontext, int i, ObjectIdReader objectidreader)
    {
        _parser = jsonparser;
        _context = deserializationcontext;
        _paramsNeeded = i;
        _objectIdReader = objectidreader;
        _creatorParameters = new Object[i];
    }

    public boolean assignParameter(int i, Object obj)
    {
        _creatorParameters[i] = obj;
        int j = -1 + _paramsNeeded;
        _paramsNeeded = j;
        return j <= 0;
    }

    public void bufferAnyProperty(SettableAnyProperty settableanyproperty, String s, Object obj)
    {
        _buffered = new PropertyValue.Any(_buffered, obj, settableanyproperty, s);
    }

    public void bufferMapProperty(Object obj, Object obj1)
    {
        _buffered = new PropertyValue.Map(_buffered, obj1, obj);
    }

    public void bufferProperty(SettableBeanProperty settablebeanproperty, Object obj)
    {
        _buffered = new PropertyValue.Regular(_buffered, obj, settablebeanproperty);
    }

    protected PropertyValue buffered()
    {
        return _buffered;
    }

    protected final Object[] getParameters(Object aobj[])
    {
        if(aobj != null)
        {
            int i = 0;
            for(int j = _creatorParameters.length; i < j; i++)
            {
                if(_creatorParameters[i] != null)
                    continue;
                Object obj = aobj[i];
                if(obj != null)
                    _creatorParameters[i] = obj;
            }

        }
        return _creatorParameters;
    }

    public Object handleIdValue(DeserializationContext deserializationcontext, Object obj)
        throws IOException
    {
        if(_objectIdReader != null && _idValue != null)
        {
            deserializationcontext.findObjectId(_idValue, _objectIdReader.generator).bindItem(obj);
            SettableBeanProperty settablebeanproperty = _objectIdReader.idProperty;
            if(settablebeanproperty != null)
                obj = settablebeanproperty.setAndReturn(obj, _idValue);
        }
        return obj;
    }

    public void inject(SettableBeanProperty asettablebeanproperty[])
    {
        int i = 0;
        for(int j = asettablebeanproperty.length; i < j; i++)
        {
            SettableBeanProperty settablebeanproperty = asettablebeanproperty[i];
            if(settablebeanproperty != null)
                _creatorParameters[i] = _context.findInjectableValue(settablebeanproperty.getInjectableValueId(), settablebeanproperty, null);
        }

    }

    public boolean isComplete()
    {
        return _paramsNeeded <= 0;
    }

    public boolean readIdProperty(String s)
        throws IOException
    {
        if(_objectIdReader != null && s.equals(_objectIdReader.propertyName.getSimpleName()))
        {
            _idValue = _objectIdReader.readObjectReference(_parser, _context);
            return true;
        } else
        {
            return false;
        }
    }

    private PropertyValue _buffered;
    protected final DeserializationContext _context;
    protected final Object _creatorParameters[];
    private Object _idValue;
    protected final ObjectIdReader _objectIdReader;
    private int _paramsNeeded;
    protected final JsonParser _parser;
}
