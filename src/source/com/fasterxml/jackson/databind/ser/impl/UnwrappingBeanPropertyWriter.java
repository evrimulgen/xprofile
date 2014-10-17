// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.util.Iterator;

// Referenced classes of package com.fasterxml.jackson.databind.ser.impl:
//            UnwrappingBeanSerializer, PropertySerializerMap

public class UnwrappingBeanPropertyWriter extends BeanPropertyWriter
{

    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanpropertywriter, NameTransformer nametransformer)
    {
        super(beanpropertywriter);
        _nameTransformer = nametransformer;
    }

    private UnwrappingBeanPropertyWriter(UnwrappingBeanPropertyWriter unwrappingbeanpropertywriter, NameTransformer nametransformer, SerializedString serializedstring)
    {
        super(unwrappingbeanpropertywriter, serializedstring);
        _nameTransformer = nametransformer;
    }

    protected void _depositSchemaProperty(ObjectNode objectnode, JsonNode jsonnode)
    {
        JsonNode jsonnode1 = jsonnode.get("properties");
        if(jsonnode1 != null)
        {
            java.util.Map.Entry entry;
            String s;
            for(Iterator iterator = jsonnode1.fields(); iterator.hasNext(); objectnode.put(s, (JsonNode)entry.getValue()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                s = (String)entry.getKey();
                if(_nameTransformer != null)
                    s = _nameTransformer.transform(s);
            }

        }
    }

    protected JsonSerializer _findAndAddDynamic(PropertySerializerMap propertyserializermap, Class class1, SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        JsonSerializer jsonserializer;
        NameTransformer nametransformer;
        NameTransformer nametransformer1;
        JsonSerializer jsonserializer1;
        if(_nonTrivialBaseType != null)
            jsonserializer = serializerprovider.findValueSerializer(serializerprovider.constructSpecializedType(_nonTrivialBaseType, class1), this);
        else
            jsonserializer = serializerprovider.findValueSerializer(class1, this);
        nametransformer = _nameTransformer;
        if(jsonserializer.isUnwrappingSerializer())
            nametransformer1 = NameTransformer.chainedTransformer(nametransformer, ((UnwrappingBeanSerializer)jsonserializer)._nameTransformer);
        else
            nametransformer1 = nametransformer;
        jsonserializer1 = jsonserializer.unwrappingSerializer(nametransformer1);
        _dynamicSerializers = _dynamicSerializers.newWith(class1, jsonserializer1);
        return jsonserializer1;
    }

    public void assignSerializer(JsonSerializer jsonserializer)
    {
        super.assignSerializer(jsonserializer);
        if(_serializer != null)
        {
            NameTransformer nametransformer = _nameTransformer;
            NameTransformer nametransformer1;
            if(_serializer.isUnwrappingSerializer())
                nametransformer1 = NameTransformer.chainedTransformer(nametransformer, ((UnwrappingBeanSerializer)_serializer)._nameTransformer);
            else
                nametransformer1 = nametransformer;
            _serializer = _serializer.unwrappingSerializer(nametransformer1);
        }
    }

    public boolean isUnwrapping()
    {
        return true;
    }

    public volatile BeanPropertyWriter rename(NameTransformer nametransformer)
    {
        return rename(nametransformer);
    }

    public UnwrappingBeanPropertyWriter rename(NameTransformer nametransformer)
    {
        String s = nametransformer.transform(_name.getValue());
        return new UnwrappingBeanPropertyWriter(this, NameTransformer.chainedTransformer(nametransformer, _nameTransformer), new SerializedString(s));
    }

    public void serializeAsField(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws Exception
    {
        Object obj1 = get(obj);
        if(obj1 != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        JsonSerializer jsonserializer;
        jsonserializer = _serializer;
        if(jsonserializer == null)
        {
            Class class1 = obj1.getClass();
            PropertySerializerMap propertyserializermap = _dynamicSerializers;
            jsonserializer = propertyserializermap.serializerFor(class1);
            if(jsonserializer == null)
                jsonserializer = _findAndAddDynamic(propertyserializermap, class1, serializerprovider);
        }
        if(_suppressableValue == null)
            break; /* Loop/switch isn't completed */
        if(MARKER_FOR_EMPTY != _suppressableValue)
            break MISSING_BLOCK_LABEL_135;
        if(jsonserializer.isEmpty(obj1)) goto _L1; else goto _L3
_L3:
        if(obj1 == obj)
            _handleSelfReference(obj, jsonserializer);
        if(!jsonserializer.isUnwrappingSerializer())
            jsongenerator.writeFieldName(_name);
        if(_typeSerializer == null)
        {
            jsonserializer.serialize(obj1, jsongenerator, serializerprovider);
            return;
        } else
        {
            jsonserializer.serializeWithType(obj1, jsongenerator, serializerprovider, _typeSerializer);
            return;
        }
        if(_suppressableValue.equals(obj1))
            return;
          goto _L3
    }

    protected final NameTransformer _nameTransformer;
}
