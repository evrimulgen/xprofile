// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.ser.impl:
//            ObjectIdWriter

public class BeanAsArraySerializer extends BeanSerializerBase
{

    public BeanAsArraySerializer(BeanSerializerBase beanserializerbase)
    {
        super(beanserializerbase, (ObjectIdWriter)null);
        _defaultSerializer = beanserializerbase;
    }

    protected BeanAsArraySerializer(BeanSerializerBase beanserializerbase, ObjectIdWriter objectidwriter, Object obj)
    {
        super(beanserializerbase, objectidwriter, obj);
        _defaultSerializer = beanserializerbase;
    }

    protected BeanAsArraySerializer(BeanSerializerBase beanserializerbase, String as[])
    {
        super(beanserializerbase, as);
        _defaultSerializer = beanserializerbase;
    }

    private boolean hasSingleElement(SerializerProvider serializerprovider)
    {
        BeanPropertyWriter abeanpropertywriter[];
        if(_filteredProps != null && serializerprovider.getActiveView() != null)
            abeanpropertywriter = _filteredProps;
        else
            abeanpropertywriter = _props;
        return abeanpropertywriter.length == 1;
    }

    protected BeanSerializerBase asArraySerializer()
    {
        return this;
    }

    public boolean isUnwrappingSerializer()
    {
        return false;
    }

    public final void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(serializerprovider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && hasSingleElement(serializerprovider))
        {
            serializeAsArray(obj, jsongenerator, serializerprovider);
            return;
        } else
        {
            jsongenerator.writeStartArray();
            serializeAsArray(obj, jsongenerator, serializerprovider);
            jsongenerator.writeEndArray();
            return;
        }
    }

    protected final void serializeAsArray(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        BeanPropertyWriter abeanpropertywriter[];
        int i;
        BeanPropertyWriter beanpropertywriter;
        int j;
        if(_filteredProps != null && serializerprovider.getActiveView() != null)
            abeanpropertywriter = _filteredProps;
        else
            abeanpropertywriter = _props;
        i = 0;
        j = abeanpropertywriter.length;
_L2:
        if(i >= j)
            break MISSING_BLOCK_LABEL_101;
        beanpropertywriter = abeanpropertywriter[i];
        if(beanpropertywriter != null)
            break; /* Loop/switch isn't completed */
        jsongenerator.writeNull();
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        beanpropertywriter.serializeAsElement(obj, jsongenerator, serializerprovider);
          goto _L3
        Exception exception;
        exception;
        String s1;
        if(i == abeanpropertywriter.length)
            s1 = "[anySetter]";
        else
            s1 = abeanpropertywriter[i].getName();
        wrapAndThrow(serializerprovider, exception, obj, s1);
        return;
        StackOverflowError stackoverflowerror;
        stackoverflowerror;
        JsonMappingException jsonmappingexception = new JsonMappingException("Infinite recursion (StackOverflowError)", stackoverflowerror);
        String s;
        if(i == abeanpropertywriter.length)
            s = "[anySetter]";
        else
            s = abeanpropertywriter[i].getName();
        jsonmappingexception.prependPath(new com.fasterxml.jackson.databind.JsonMappingException.Reference(obj, s));
        throw jsonmappingexception;
    }

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        _defaultSerializer.serializeWithType(obj, jsongenerator, serializerprovider, typeserializer);
    }

    public String toString()
    {
        return (new StringBuilder()).append("BeanAsArraySerializer for ").append(handledType().getName()).toString();
    }

    public JsonSerializer unwrappingSerializer(NameTransformer nametransformer)
    {
        return _defaultSerializer.unwrappingSerializer(nametransformer);
    }

    protected BeanSerializerBase withFilterId(Object obj)
    {
        return new BeanAsArraySerializer(this, _objectIdWriter, obj);
    }

    protected BeanAsArraySerializer withIgnorals(String as[])
    {
        return new BeanAsArraySerializer(this, as);
    }

    protected volatile BeanSerializerBase withIgnorals(String as[])
    {
        return withIgnorals(as);
    }

    public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectidwriter)
    {
        return _defaultSerializer.withObjectIdWriter(objectidwriter);
    }

    protected final BeanSerializerBase _defaultSerializer;
}
