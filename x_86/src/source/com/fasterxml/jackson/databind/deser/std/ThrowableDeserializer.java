// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.HashSet;

public class ThrowableDeserializer extends BeanDeserializer
{

    public ThrowableDeserializer(BeanDeserializer beandeserializer)
    {
        super(beandeserializer);
        _vanillaProcessing = false;
    }

    protected ThrowableDeserializer(BeanDeserializer beandeserializer, NameTransformer nametransformer)
    {
        super(beandeserializer, nametransformer);
    }

    public Object deserializeFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_propertyBasedCreator == null) goto _L2; else goto _L1
_L1:
        Object obj = _deserializeUsingPropertyBased(jsonparser, deserializationcontext);
_L7:
        return obj;
_L2:
        boolean flag;
        int i;
        Object aobj[];
        if(_delegateDeserializer != null)
            return _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
        if(_beanType.isAbstract())
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Can not instantiate abstract type ").append(_beanType).append(" (need to add/enable type information?)").toString());
        flag = _valueInstantiator.canCreateFromString();
        boolean flag1 = _valueInstantiator.canCreateUsingDefault();
        if(!flag && !flag1)
            throw new JsonMappingException((new StringBuilder()).append("Can not deserialize Throwable of type ").append(_beanType).append(" without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator").toString());
        i = 0;
        aobj = null;
        obj = null;
_L4:
        String s;
        int k;
        Object aobj1[];
        Object obj1;
        if(jsonparser.getCurrentToken() == JsonToken.END_OBJECT)
            continue; /* Loop/switch isn't completed */
        s = jsonparser.getCurrentName();
        SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
        jsonparser.nextToken();
        if(settablebeanproperty == null)
            break; /* Loop/switch isn't completed */
        if(obj != null)
        {
            settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
            k = i;
            aobj1 = aobj;
            obj1 = obj;
        } else
        {
            if(aobj == null)
            {
                int j1 = _beanProperties.size();
                aobj = new Object[j1 + j1];
            }
            int i1 = i + 1;
            aobj[i] = settablebeanproperty;
            k = i1 + 1;
            aobj[i1] = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
            aobj1 = aobj;
            obj1 = obj;
        }
_L5:
        jsonparser.nextToken();
        obj = obj1;
        aobj = aobj1;
        i = k;
        if(true) goto _L4; else goto _L3
_L3:
        if("message".equals(s) && flag)
        {
            obj = _valueInstantiator.createFromString(deserializationcontext, jsonparser.getText());
            if(aobj == null)
                break MISSING_BLOCK_LABEL_471;
            for(int l = 0; l < i; l += 2)
                ((SettableBeanProperty)aobj[l]).set(obj, aobj[l + 1]);

            k = i;
            obj1 = obj;
            aobj1 = null;
        } else
        if(_ignorableProps != null && _ignorableProps.contains(s))
        {
            jsonparser.skipChildren();
            k = i;
            aobj1 = aobj;
            obj1 = obj;
        } else
        {
label0:
            {
                if(_anySetter == null)
                    break label0;
                _anySetter.deserializeAndSet(jsonparser, deserializationcontext, obj, s);
                k = i;
                aobj1 = aobj;
                obj1 = obj;
            }
        }
          goto _L5
        handleUnknownProperty(jsonparser, deserializationcontext, obj, s);
        k = i;
        aobj1 = aobj;
        obj1 = obj;
          goto _L5
        if(obj != null) goto _L7; else goto _L6
_L6:
        int j;
        if(flag)
            obj = _valueInstantiator.createFromString(deserializationcontext, null);
        else
            obj = _valueInstantiator.createUsingDefault(deserializationcontext);
        j = 0;
        if(aobj != null)
            while(j < i) 
            {
                ((SettableBeanProperty)aobj[j]).set(obj, aobj[j + 1]);
                j += 2;
            }
        if(true) goto _L7; else goto _L8
_L8:
    }

    public JsonDeserializer unwrappingDeserializer(NameTransformer nametransformer)
    {
        if(getClass() != com/fasterxml/jackson/databind/deser/std/ThrowableDeserializer)
            return this;
        else
            return new ThrowableDeserializer(this, nametransformer);
    }

    protected static final String PROP_NAME_MESSAGE = "message";
    private static final long serialVersionUID = 1L;
}
