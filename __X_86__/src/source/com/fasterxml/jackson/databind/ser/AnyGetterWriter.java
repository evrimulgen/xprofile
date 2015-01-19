// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import java.util.Map;

// Referenced classes of package com.fasterxml.jackson.databind.ser:
//            PropertyFilter

public class AnyGetterWriter
{

    public AnyGetterWriter(BeanProperty beanproperty, AnnotatedMember annotatedmember, MapSerializer mapserializer)
    {
        _accessor = annotatedmember;
        _property = beanproperty;
        _serializer = mapserializer;
    }

    public void getAndFilter(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, PropertyFilter propertyfilter)
        throws Exception
    {
        Object obj1 = _accessor.getValue(obj);
        if(obj1 == null)
            return;
        if(!(obj1 instanceof Map))
        {
            throw new JsonMappingException((new StringBuilder()).append("Value returned by 'any-getter' (").append(_accessor.getName()).append("()) not java.util.Map but ").append(obj1.getClass().getName()).toString());
        } else
        {
            _serializer.serializeFilteredFields((Map)obj1, jsongenerator, serializerprovider, propertyfilter);
            return;
        }
    }

    public void getAndSerialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws Exception
    {
        Object obj1 = _accessor.getValue(obj);
        if(obj1 == null)
            return;
        if(!(obj1 instanceof Map))
        {
            throw new JsonMappingException((new StringBuilder()).append("Value returned by 'any-getter' (").append(_accessor.getName()).append("()) not java.util.Map but ").append(obj1.getClass().getName()).toString());
        } else
        {
            _serializer.serializeFields((Map)obj1, jsongenerator, serializerprovider);
            return;
        }
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        _serializer = (MapSerializer)serializerprovider.handlePrimaryContextualization(_serializer, _property);
    }

    protected final AnnotatedMember _accessor;
    protected final BeanProperty _property;
    protected MapSerializer _serializer;
}
