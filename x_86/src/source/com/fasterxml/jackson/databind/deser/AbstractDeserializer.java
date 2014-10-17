// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            BeanDeserializerBuilder, SettableBeanProperty

public class AbstractDeserializer extends JsonDeserializer
    implements Serializable
{

    protected AbstractDeserializer(BeanDescription beandescription)
    {
label0:
        {
            super();
            _baseType = beandescription.getType();
            _objectIdReader = null;
            _backRefProperties = null;
            Class class1 = _baseType.getRawClass();
            _acceptString = class1.isAssignableFrom(java/lang/String);
            boolean flag;
            boolean flag1;
            boolean flag2;
            if(class1 == Boolean.TYPE || class1.isAssignableFrom(java/lang/Boolean))
                flag = true;
            else
                flag = false;
            _acceptBoolean = flag;
            if(class1 == Integer.TYPE || class1.isAssignableFrom(java/lang/Integer))
                flag1 = true;
            else
                flag1 = false;
            _acceptInt = flag1;
            if(class1 != Double.TYPE)
            {
                boolean flag3 = class1.isAssignableFrom(java/lang/Double);
                flag2 = false;
                if(!flag3)
                    break label0;
            }
            flag2 = true;
        }
        _acceptDouble = flag2;
    }

    public AbstractDeserializer(BeanDeserializerBuilder beandeserializerbuilder, BeanDescription beandescription, Map map)
    {
label0:
        {
            super();
            _baseType = beandescription.getType();
            _objectIdReader = beandeserializerbuilder.getObjectIdReader();
            _backRefProperties = map;
            Class class1 = _baseType.getRawClass();
            _acceptString = class1.isAssignableFrom(java/lang/String);
            boolean flag;
            boolean flag1;
            boolean flag2;
            if(class1 == Boolean.TYPE || class1.isAssignableFrom(java/lang/Boolean))
                flag = true;
            else
                flag = false;
            _acceptBoolean = flag;
            if(class1 == Integer.TYPE || class1.isAssignableFrom(java/lang/Integer))
                flag1 = true;
            else
                flag1 = false;
            _acceptInt = flag1;
            if(class1 != Double.TYPE)
            {
                boolean flag3 = class1.isAssignableFrom(java/lang/Double);
                flag2 = false;
                if(!flag3)
                    break label0;
            }
            flag2 = true;
        }
        _acceptDouble = flag2;
    }

    public static AbstractDeserializer constructForNonPOJO(BeanDescription beandescription)
    {
        return new AbstractDeserializer(beandescription);
    }

    protected Object _deserializeFromObjectId(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj = _objectIdReader.readObjectReference(jsonparser, deserializationcontext);
        Object obj1 = deserializationcontext.findObjectId(obj, _objectIdReader.generator).item;
        if(obj1 == null)
            throw new IllegalStateException((new StringBuilder()).append("Could not resolve Object Id [").append(obj).append("] -- unresolved forward-reference?").toString());
        else
            return obj1;
    }

    protected Object _deserializeIfNatural(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken.isScalarValue())
            if(jsontoken == JsonToken.VALUE_STRING)
            {
                if(_acceptString)
                    return jsonparser.getText();
            } else
            if(jsontoken == JsonToken.VALUE_NUMBER_INT)
            {
                if(_acceptInt)
                    return Integer.valueOf(jsonparser.getIntValue());
            } else
            if(jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            {
                if(_acceptDouble)
                    return Double.valueOf(jsonparser.getDoubleValue());
            } else
            if(jsontoken == JsonToken.VALUE_TRUE)
            {
                if(_acceptBoolean)
                    return Boolean.TRUE;
            } else
            if(jsontoken == JsonToken.VALUE_FALSE && _acceptBoolean)
                return Boolean.FALSE;
        return null;
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        throw deserializationcontext.instantiationException(_baseType.getRawClass(), "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        if(_objectIdReader == null) goto _L2; else goto _L1
_L1:
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == null || !jsontoken.isScalarValue()) goto _L2; else goto _L3
_L3:
        Object obj = _deserializeFromObjectId(jsonparser, deserializationcontext);
_L5:
        return obj;
_L2:
        obj = _deserializeIfNatural(jsonparser, deserializationcontext);
        if(obj == null)
            return typedeserializer.deserializeTypedFromObject(jsonparser, deserializationcontext);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public SettableBeanProperty findBackReference(String s)
    {
        if(_backRefProperties == null)
            return null;
        else
            return (SettableBeanProperty)_backRefProperties.get(s);
    }

    public ObjectIdReader getObjectIdReader()
    {
        return _objectIdReader;
    }

    public Class handledType()
    {
        return _baseType.getRawClass();
    }

    public boolean isCachable()
    {
        return true;
    }

    private static final long serialVersionUID = 0xd639177cf6e7c61eL;
    protected final boolean _acceptBoolean;
    protected final boolean _acceptDouble;
    protected final boolean _acceptInt;
    protected final boolean _acceptString;
    protected final Map _backRefProperties;
    protected final JavaType _baseType;
    protected final ObjectIdReader _objectIdReader;
}
