// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.fasterxml.jackson.databind.deser.impl:
//            PropertyBasedCreator, PropertyValueBuffer

public class ExternalTypeHandler
{
    public static class Builder
    {

        public void addExternal(SettableBeanProperty settablebeanproperty, TypeDeserializer typedeserializer)
        {
            Integer integer = Integer.valueOf(_properties.size());
            _properties.add(new ExtTypedProperty(settablebeanproperty, typedeserializer));
            _nameToPropertyIndex.put(settablebeanproperty.getName(), integer);
            _nameToPropertyIndex.put(typedeserializer.getPropertyName(), integer);
        }

        public ExternalTypeHandler build()
        {
            return new ExternalTypeHandler((ExtTypedProperty[])_properties.toArray(new ExtTypedProperty[_properties.size()]), _nameToPropertyIndex, null, null);
        }

        private final HashMap _nameToPropertyIndex = new HashMap();
        private final ArrayList _properties = new ArrayList();

        public Builder()
        {
        }
    }

    private static final class ExtTypedProperty
    {

        public String getDefaultTypeId()
        {
            Class class1 = _typeDeserializer.getDefaultImpl();
            if(class1 == null)
                return null;
            else
                return _typeDeserializer.getTypeIdResolver().idFromValueAndType(null, class1);
        }

        public SettableBeanProperty getProperty()
        {
            return _property;
        }

        public String getTypePropertyName()
        {
            return _typePropertyName;
        }

        public boolean hasDefaultType()
        {
            return _typeDeserializer.getDefaultImpl() != null;
        }

        public boolean hasTypePropertyName(String s)
        {
            return s.equals(_typePropertyName);
        }

        private final SettableBeanProperty _property;
        private final TypeDeserializer _typeDeserializer;
        private final String _typePropertyName;

        public ExtTypedProperty(SettableBeanProperty settablebeanproperty, TypeDeserializer typedeserializer)
        {
            _property = settablebeanproperty;
            _typeDeserializer = typedeserializer;
            _typePropertyName = typedeserializer.getPropertyName();
        }
    }


    protected ExternalTypeHandler(ExternalTypeHandler externaltypehandler)
    {
        _properties = externaltypehandler._properties;
        _nameToPropertyIndex = externaltypehandler._nameToPropertyIndex;
        int i = _properties.length;
        _typeIds = new String[i];
        _tokens = new TokenBuffer[i];
    }

    protected ExternalTypeHandler(ExtTypedProperty aexttypedproperty[], HashMap hashmap, String as[], TokenBuffer atokenbuffer[])
    {
        _properties = aexttypedproperty;
        _nameToPropertyIndex = hashmap;
        _typeIds = as;
        _tokens = atokenbuffer;
    }

    protected final Object _deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, int i, String s)
        throws IOException, JsonProcessingException
    {
        TokenBuffer tokenbuffer = new TokenBuffer(jsonparser);
        tokenbuffer.writeStartArray();
        tokenbuffer.writeString(s);
        JsonParser jsonparser1 = _tokens[i].asParser(jsonparser);
        jsonparser1.nextToken();
        tokenbuffer.copyCurrentStructure(jsonparser1);
        tokenbuffer.writeEndArray();
        JsonParser jsonparser2 = tokenbuffer.asParser(jsonparser);
        jsonparser2.nextToken();
        return _properties[i].getProperty().deserialize(jsonparser2, deserializationcontext);
    }

    protected final void _deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, int i, String s)
        throws IOException, JsonProcessingException
    {
        TokenBuffer tokenbuffer = new TokenBuffer(jsonparser);
        tokenbuffer.writeStartArray();
        tokenbuffer.writeString(s);
        JsonParser jsonparser1 = _tokens[i].asParser(jsonparser);
        jsonparser1.nextToken();
        tokenbuffer.copyCurrentStructure(jsonparser1);
        tokenbuffer.writeEndArray();
        JsonParser jsonparser2 = tokenbuffer.asParser(jsonparser);
        jsonparser2.nextToken();
        _properties[i].getProperty().deserializeAndSet(jsonparser2, deserializationcontext, obj);
    }

    public Object complete(JsonParser jsonparser, DeserializationContext deserializationcontext, PropertyValueBuffer propertyvaluebuffer, PropertyBasedCreator propertybasedcreator)
        throws IOException, JsonProcessingException
    {
        int i;
        Object aobj[];
        int j;
        i = _properties.length;
        aobj = new Object[i];
        j = 0;
_L5:
        String s;
        if(j >= i)
            break MISSING_BLOCK_LABEL_203;
        s = _typeIds[j];
        if(s != null) goto _L2; else goto _L1
_L1:
        if(_tokens[j] != null) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
_L4:
        if(!_properties[j].hasDefaultType())
            throw deserializationcontext.mappingException((new StringBuilder()).append("Missing external type id property '").append(_properties[j].getTypePropertyName()).append("'").toString());
        s = _properties[j].getDefaultTypeId();
_L7:
        aobj[j] = _deserialize(jsonparser, deserializationcontext, j, s);
          goto _L3
_L2:
        if(_tokens[j] != null) goto _L7; else goto _L6
_L6:
        SettableBeanProperty settablebeanproperty2 = _properties[j].getProperty();
        throw deserializationcontext.mappingException((new StringBuilder()).append("Missing property '").append(settablebeanproperty2.getName()).append("' for external type id '").append(_properties[j].getTypePropertyName()).toString());
        for(int k = 0; k < i; k++)
        {
            SettableBeanProperty settablebeanproperty1 = _properties[k].getProperty();
            if(propertybasedcreator.findCreatorProperty(settablebeanproperty1.getName()) != null)
                propertyvaluebuffer.assignParameter(settablebeanproperty1.getCreatorIndex(), aobj[k]);
        }

        Object obj = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        for(int l = 0; l < i; l++)
        {
            SettableBeanProperty settablebeanproperty = _properties[l].getProperty();
            if(propertybasedcreator.findCreatorProperty(settablebeanproperty.getName()) == null)
                settablebeanproperty.set(obj, aobj[l]);
        }

        return obj;
    }

    public Object complete(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        int i;
        int j;
        i = 0;
        j = _properties.length;
_L5:
        if(i >= j) goto _L2; else goto _L1
_L1:
        String s = _typeIds[i];
        if(s != null) goto _L4; else goto _L3
_L3:
        TokenBuffer tokenbuffer = _tokens[i];
        if(tokenbuffer != null)
        {
label0:
            {
                JsonToken jsontoken = tokenbuffer.firstToken();
                if(jsontoken == null || !jsontoken.isScalarValue())
                    break MISSING_BLOCK_LABEL_189;
                JsonParser jsonparser1 = tokenbuffer.asParser(jsonparser);
                jsonparser1.nextToken();
                SettableBeanProperty settablebeanproperty1 = _properties[i].getProperty();
                Object obj1 = TypeDeserializer.deserializeIfNatural(jsonparser1, deserializationcontext, settablebeanproperty1.getType());
                if(obj1 == null)
                    break label0;
                settablebeanproperty1.set(obj, obj1);
            }
        }
_L6:
        i++;
          goto _L5
        if(!_properties[i].hasDefaultType())
            throw deserializationcontext.mappingException((new StringBuilder()).append("Missing external type id property '").append(_properties[i].getTypePropertyName()).append("'").toString());
        s = _properties[i].getDefaultTypeId();
        String s1 = s;
_L7:
        _deserializeAndSet(jsonparser, deserializationcontext, obj, i, s1);
          goto _L6
_L4:
        if(_tokens[i] == null)
        {
            SettableBeanProperty settablebeanproperty = _properties[i].getProperty();
            throw deserializationcontext.mappingException((new StringBuilder()).append("Missing property '").append(settablebeanproperty.getName()).append("' for external type id '").append(_properties[i].getTypePropertyName()).toString());
        }
        break MISSING_BLOCK_LABEL_277;
_L2:
        return obj;
        s1 = s;
          goto _L7
    }

    public boolean handlePropertyValue(JsonParser jsonparser, DeserializationContext deserializationcontext, String s, Object obj)
        throws IOException, JsonProcessingException
    {
        Integer integer = (Integer)_nameToPropertyIndex.get(s);
        if(integer == null)
            return false;
        int i = integer.intValue();
        boolean flag1;
        if(_properties[i].hasTypePropertyName(s))
        {
            _typeIds[i] = jsonparser.getText();
            jsonparser.skipChildren();
            String s1;
            if(obj != null && _tokens[i] != null)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            TokenBuffer tokenbuffer = new TokenBuffer(jsonparser);
            tokenbuffer.copyCurrentStructure(jsonparser);
            _tokens[i] = tokenbuffer;
            boolean flag = false;
            if(obj != null)
            {
                String s2 = _typeIds[i];
                flag = false;
                if(s2 != null)
                    flag = true;
            }
            flag1 = flag;
        }
        if(flag1)
        {
            s1 = _typeIds[i];
            _typeIds[i] = null;
            _deserializeAndSet(jsonparser, deserializationcontext, obj, i, s1);
            _tokens[i] = null;
        }
        return true;
    }

    public boolean handleTypePropertyValue(JsonParser jsonparser, DeserializationContext deserializationcontext, String s, Object obj)
        throws IOException, JsonProcessingException
    {
        Integer integer = (Integer)_nameToPropertyIndex.get(s);
        if(integer == null)
            return false;
        int i = integer.intValue();
        if(!_properties[i].hasTypePropertyName(s))
            return false;
        String s1 = jsonparser.getText();
        boolean flag = false;
        if(obj != null)
        {
            TokenBuffer tokenbuffer = _tokens[i];
            flag = false;
            if(tokenbuffer != null)
                flag = true;
        }
        if(flag)
        {
            _deserializeAndSet(jsonparser, deserializationcontext, obj, i, s1);
            _tokens[i] = null;
        } else
        {
            _typeIds[i] = s1;
        }
        return true;
    }

    public ExternalTypeHandler start()
    {
        return new ExternalTypeHandler(this);
    }

    private final HashMap _nameToPropertyIndex;
    private final ExtTypedProperty _properties[];
    private final TokenBuffer _tokens[];
    private final String _typeIds[];
}
