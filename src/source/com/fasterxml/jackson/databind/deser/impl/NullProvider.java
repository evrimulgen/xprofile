// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import java.io.Serializable;

public final class NullProvider
    implements Serializable
{

    public NullProvider(JavaType javatype, Object obj)
    {
        _nullValue = obj;
        _isPrimitive = javatype.isPrimitive();
        _rawType = javatype.getRawClass();
    }

    public Object nullValue(DeserializationContext deserializationcontext)
        throws JsonProcessingException
    {
        if(_isPrimitive && deserializationcontext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES))
            throw deserializationcontext.mappingException((new StringBuilder()).append("Can not map JSON null into type ").append(_rawType.getName()).append(" (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)").toString());
        else
            return _nullValue;
    }

    private static final long serialVersionUID = 1L;
    private final boolean _isPrimitive;
    private final Object _nullValue;
    private final Class _rawType;
}
