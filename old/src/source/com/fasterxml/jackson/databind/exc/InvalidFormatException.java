// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;

public class InvalidFormatException extends JsonMappingException
{

    public InvalidFormatException(String s, JsonLocation jsonlocation, Object obj, Class class1)
    {
        super(s, jsonlocation);
        _value = obj;
        _targetType = class1;
    }

    public InvalidFormatException(String s, Object obj, Class class1)
    {
        super(s);
        _value = obj;
        _targetType = class1;
    }

    public static InvalidFormatException from(JsonParser jsonparser, String s, Object obj, Class class1)
    {
        return new InvalidFormatException(s, jsonparser.getTokenLocation(), obj, class1);
    }

    public Class getTargetType()
    {
        return _targetType;
    }

    public Object getValue()
    {
        return _value;
    }

    private static final long serialVersionUID = 1L;
    protected final Class _targetType;
    protected final Object _value;
}
