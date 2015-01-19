// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.HashSet;

// Referenced classes of package com.fasterxml.jackson.databind.deser.impl:
//            PropertyBasedCreator, PropertyValueBuffer, ObjectIdReader

public class BeanAsArrayDeserializer extends BeanDeserializerBase
{

    public BeanAsArrayDeserializer(BeanDeserializerBase beandeserializerbase, SettableBeanProperty asettablebeanproperty[])
    {
        super(beandeserializerbase);
        _delegate = beandeserializerbase;
        _orderedProperties = asettablebeanproperty;
    }

    protected Object _deserializeFromNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        throw deserializationcontext.mappingException((new StringBuilder()).append("Can not deserialize a POJO (of type ").append(_beanType.getRawClass().getName()).append(") from non-Array representation (token: ").append(jsonparser.getCurrentToken()).append("): type/property designed to be serialized as JSON Array").toString());
    }

    protected Object _deserializeNonVanilla(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_nonStandardCreation)
            return _deserializeWithCreator(jsonparser, deserializationcontext);
        Object obj = _valueInstantiator.createUsingDefault(deserializationcontext);
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        Class class1;
        SettableBeanProperty asettablebeanproperty[];
        int i;
        int j;
        if(_needViewProcesing)
            class1 = deserializationcontext.getActiveView();
        else
            class1 = null;
        asettablebeanproperty = _orderedProperties;
        i = 0;
        j = asettablebeanproperty.length;
        do
        {
            if(jsonparser.nextToken() == JsonToken.END_ARRAY)
                return obj;
            if(i == j)
            {
                if(!_ignoreAllUnknown)
                    throw deserializationcontext.mappingException((new StringBuilder()).append("Unexpected JSON values; expected at most ").append(j).append(" properties (in JSON Array)").toString());
                break;
            }
            SettableBeanProperty settablebeanproperty = asettablebeanproperty[i];
            i++;
            if(settablebeanproperty != null && (class1 == null || settablebeanproperty.visibleInView(class1)))
                try
                {
                    settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                }
                catch(Exception exception)
                {
                    wrapAndThrow(exception, obj, settablebeanproperty.getName(), deserializationcontext);
                }
            else
                jsonparser.skipChildren();
        } while(true);
        for(; jsonparser.nextToken() != JsonToken.END_ARRAY; jsonparser.skipChildren());
        return obj;
    }

    protected final Object _deserializeUsingPropertyBased(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        PropertyBasedCreator propertybasedcreator;
        PropertyValueBuffer propertyvaluebuffer;
        SettableBeanProperty asettablebeanproperty[];
        int i;
        int j;
        Object obj;
        propertybasedcreator = _propertyBasedCreator;
        propertyvaluebuffer = propertybasedcreator.startBuilding(jsonparser, deserializationcontext, _objectIdReader);
        asettablebeanproperty = _orderedProperties;
        i = asettablebeanproperty.length;
        j = 0;
        obj = null;
_L1:
        SettableBeanProperty settablebeanproperty;
        if(jsonparser.nextToken() == JsonToken.END_ARRAY)
            break MISSING_BLOCK_LABEL_285;
        if(j < i)
            settablebeanproperty = asettablebeanproperty[j];
        else
            settablebeanproperty = null;
        if(settablebeanproperty == null)
        {
            jsonparser.skipChildren();
        } else
        {
label0:
            {
                if(obj == null)
                    break label0;
                try
                {
                    settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                }
                catch(Exception exception2)
                {
                    wrapAndThrow(exception2, obj, settablebeanproperty.getName(), deserializationcontext);
                }
            }
        }
_L3:
        j++;
          goto _L1
        String s;
        SettableBeanProperty settablebeanproperty1;
        Object obj2;
        s = settablebeanproperty.getName();
        settablebeanproperty1 = propertybasedcreator.findCreatorProperty(s);
        if(settablebeanproperty1 == null)
            break MISSING_BLOCK_LABEL_258;
        obj2 = settablebeanproperty1.deserialize(jsonparser, deserializationcontext);
        if(!propertyvaluebuffer.assignParameter(settablebeanproperty1.getCreatorIndex(), obj2)) goto _L3; else goto _L2
_L2:
        Object obj3 = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        obj = obj3;
        if(obj.getClass() != _beanType.getRawClass())
            throw deserializationcontext.mappingException((new StringBuilder()).append("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ").append(_beanType.getRawClass().getName()).append(", actual type ").append(obj.getClass().getName()).toString());
          goto _L3
        Exception exception1;
        exception1;
        wrapAndThrow(exception1, _beanType.getRawClass(), s, deserializationcontext);
          goto _L3
        if(!propertyvaluebuffer.readIdProperty(s))
            propertyvaluebuffer.bufferProperty(settablebeanproperty, settablebeanproperty.deserialize(jsonparser, deserializationcontext));
          goto _L3
        if(obj == null)
        {
            Object obj1;
            try
            {
                obj1 = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
            }
            catch(Exception exception)
            {
                wrapInstantiationProblem(exception, deserializationcontext);
                return null;
            }
            obj = obj1;
        }
        return obj;
    }

    protected Object _deserializeWithCreator(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_delegateDeserializer != null)
            return _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
        if(_propertyBasedCreator != null)
            return _deserializeUsingPropertyBased(jsonparser, deserializationcontext);
        if(_beanType.isAbstract())
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Can not instantiate abstract type ").append(_beanType).append(" (need to add/enable type information?)").toString());
        else
            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("No suitable constructor found for type ").append(_beanType).append(": can not instantiate from JSON object (need to add/enable type information?)").toString());
    }

    protected BeanDeserializerBase asArrayDeserializer()
    {
        return this;
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.getCurrentToken() != JsonToken.START_ARRAY)
            return _deserializeFromNonArray(jsonparser, deserializationcontext);
        if(!_vanillaProcessing)
            return _deserializeNonVanilla(jsonparser, deserializationcontext);
        Object obj = _valueInstantiator.createUsingDefault(deserializationcontext);
        SettableBeanProperty asettablebeanproperty[] = _orderedProperties;
        int i = 0;
        int j = asettablebeanproperty.length;
        do
        {
            if(jsonparser.nextToken() == JsonToken.END_ARRAY)
                return obj;
            if(i == j)
            {
                if(!_ignoreAllUnknown)
                    throw deserializationcontext.mappingException((new StringBuilder()).append("Unexpected JSON values; expected at most ").append(j).append(" properties (in JSON Array)").toString());
                break;
            }
            SettableBeanProperty settablebeanproperty = asettablebeanproperty[i];
            if(settablebeanproperty != null)
                try
                {
                    settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                }
                catch(Exception exception)
                {
                    wrapAndThrow(exception, obj, settablebeanproperty.getName(), deserializationcontext);
                }
            else
                jsonparser.skipChildren();
            i++;
        } while(true);
        for(; jsonparser.nextToken() != JsonToken.END_ARRAY; jsonparser.skipChildren());
        return obj;
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        SettableBeanProperty asettablebeanproperty[];
        int i;
        int j;
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        asettablebeanproperty = _orderedProperties;
        i = 0;
        j = asettablebeanproperty.length;
_L3:
        if(jsonparser.nextToken() != JsonToken.END_ARRAY) goto _L2; else goto _L1
_L1:
        return obj;
_L2:
label0:
        {
            if(i == j)
            {
                if(!_ignoreAllUnknown)
                    throw deserializationcontext.mappingException((new StringBuilder()).append("Unexpected JSON values; expected at most ").append(j).append(" properties (in JSON Array)").toString());
                break label0;
            }
            SettableBeanProperty settablebeanproperty = asettablebeanproperty[i];
            if(settablebeanproperty != null)
                try
                {
                    settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                }
                catch(Exception exception)
                {
                    wrapAndThrow(exception, obj, settablebeanproperty.getName(), deserializationcontext);
                }
            else
                jsonparser.skipChildren();
            i++;
        }
          goto _L3
        while(jsonparser.nextToken() != JsonToken.END_ARRAY) 
            jsonparser.skipChildren();
          goto _L1
    }

    public Object deserializeFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserializeFromNonArray(jsonparser, deserializationcontext);
    }

    public JsonDeserializer unwrappingDeserializer(NameTransformer nametransformer)
    {
        return _delegate.unwrappingDeserializer(nametransformer);
    }

    public volatile BeanDeserializerBase withIgnorableProperties(HashSet hashset)
    {
        return withIgnorableProperties(hashset);
    }

    public BeanAsArrayDeserializer withIgnorableProperties(HashSet hashset)
    {
        return new BeanAsArrayDeserializer(_delegate.withIgnorableProperties(hashset), _orderedProperties);
    }

    public volatile BeanDeserializerBase withObjectIdReader(ObjectIdReader objectidreader)
    {
        return withObjectIdReader(objectidreader);
    }

    public BeanAsArrayDeserializer withObjectIdReader(ObjectIdReader objectidreader)
    {
        return new BeanAsArrayDeserializer(_delegate.withObjectIdReader(objectidreader), _orderedProperties);
    }

    private static final long serialVersionUID = 1L;
    protected final BeanDeserializerBase _delegate;
    protected final SettableBeanProperty _orderedProperties[];
}
