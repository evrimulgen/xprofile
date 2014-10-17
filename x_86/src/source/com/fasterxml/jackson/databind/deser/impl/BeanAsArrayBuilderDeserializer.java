// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;

// Referenced classes of package com.fasterxml.jackson.databind.deser.impl:
//            PropertyBasedCreator, PropertyValueBuffer, ObjectIdReader

public class BeanAsArrayBuilderDeserializer extends BeanDeserializerBase
{

    public BeanAsArrayBuilderDeserializer(BeanDeserializerBase beandeserializerbase, SettableBeanProperty asettablebeanproperty[], AnnotatedMethod annotatedmethod)
    {
        super(beandeserializerbase);
        _delegate = beandeserializerbase;
        _orderedProperties = asettablebeanproperty;
        _buildMethod = annotatedmethod;
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
                    settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
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
_L3:
        SettableBeanProperty settablebeanproperty;
        if(jsonparser.nextToken() == JsonToken.END_ARRAY)
            break MISSING_BLOCK_LABEL_291;
        if(j < i)
            settablebeanproperty = asettablebeanproperty[j];
        else
            settablebeanproperty = null;
        if(settablebeanproperty != null) goto _L2; else goto _L1
_L1:
        jsonparser.skipChildren();
_L4:
        j++;
          goto _L3
_L2:
        if(obj == null)
            break MISSING_BLOCK_LABEL_122;
        Object obj4 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        obj = obj4;
          goto _L4
        Exception exception2;
        exception2;
        wrapAndThrow(exception2, obj, settablebeanproperty.getName(), deserializationcontext);
          goto _L4
        String s;
        SettableBeanProperty settablebeanproperty1;
        Object obj2;
        s = settablebeanproperty.getName();
        settablebeanproperty1 = propertybasedcreator.findCreatorProperty(s);
        if(settablebeanproperty1 == null)
            break MISSING_BLOCK_LABEL_264;
        obj2 = settablebeanproperty1.deserialize(jsonparser, deserializationcontext);
        if(!propertyvaluebuffer.assignParameter(settablebeanproperty1.getCreatorIndex(), obj2)) goto _L4; else goto _L5
_L5:
        Object obj3 = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        obj = obj3;
        if(obj.getClass() != _beanType.getRawClass())
            throw deserializationcontext.mappingException((new StringBuilder()).append("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ").append(_beanType.getRawClass().getName()).append(", actual type ").append(obj.getClass().getName()).toString());
          goto _L4
        Exception exception1;
        exception1;
        wrapAndThrow(exception1, _beanType.getRawClass(), s, deserializationcontext);
          goto _L4
        if(!propertyvaluebuffer.readIdProperty(s))
            propertyvaluebuffer.bufferProperty(settablebeanproperty, settablebeanproperty.deserialize(jsonparser, deserializationcontext));
          goto _L4
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

    protected volatile BeanDeserializerBase asArrayDeserializer()
    {
        return asArrayDeserializer();
    }

    protected BeanAsArrayBuilderDeserializer asArrayDeserializer()
    {
        return this;
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj;
        SettableBeanProperty asettablebeanproperty[];
        int i;
        int j;
        if(jsonparser.getCurrentToken() != JsonToken.START_ARRAY)
            return finishBuild(deserializationcontext, _deserializeFromNonArray(jsonparser, deserializationcontext));
        if(!_vanillaProcessing)
            return finishBuild(deserializationcontext, _deserializeNonVanilla(jsonparser, deserializationcontext));
        obj = _valueInstantiator.createUsingDefault(deserializationcontext);
        asettablebeanproperty = _orderedProperties;
        i = 0;
        j = asettablebeanproperty.length;
_L2:
        SettableBeanProperty settablebeanproperty;
        if(jsonparser.nextToken() == JsonToken.END_ARRAY)
            return finishBuild(deserializationcontext, obj);
        if(i == j)
        {
            if(!_ignoreAllUnknown)
                throw deserializationcontext.mappingException((new StringBuilder()).append("Unexpected JSON values; expected at most ").append(j).append(" properties (in JSON Array)").toString());
            break MISSING_BLOCK_LABEL_182;
        }
        settablebeanproperty = asettablebeanproperty[i];
        if(settablebeanproperty == null)
            break; /* Loop/switch isn't completed */
        Object obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        obj = obj1;
_L3:
        i++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        wrapAndThrow(exception, obj, settablebeanproperty.getName(), deserializationcontext);
          goto _L3
_L1:
        jsonparser.skipChildren();
          goto _L3
        for(; jsonparser.nextToken() != JsonToken.END_ARRAY; jsonparser.skipChildren());
        return finishBuild(deserializationcontext, obj);
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
_L2:
        SettableBeanProperty settablebeanproperty;
        if(jsonparser.nextToken() == JsonToken.END_ARRAY)
            return finishBuild(deserializationcontext, obj);
        if(i == j)
        {
            if(!_ignoreAllUnknown)
                throw deserializationcontext.mappingException((new StringBuilder()).append("Unexpected JSON values; expected at most ").append(j).append(" properties (in JSON Array)").toString());
            break MISSING_BLOCK_LABEL_145;
        }
        settablebeanproperty = asettablebeanproperty[i];
        if(settablebeanproperty == null)
            break; /* Loop/switch isn't completed */
        Object obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        obj = obj1;
_L3:
        i++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        wrapAndThrow(exception, obj, settablebeanproperty.getName(), deserializationcontext);
          goto _L3
_L1:
        jsonparser.skipChildren();
          goto _L3
        for(; jsonparser.nextToken() != JsonToken.END_ARRAY; jsonparser.skipChildren());
        return finishBuild(deserializationcontext, obj);
    }

    public Object deserializeFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserializeFromNonArray(jsonparser, deserializationcontext);
    }

    protected final Object finishBuild(DeserializationContext deserializationcontext, Object obj)
        throws IOException
    {
        Object obj1;
        try
        {
            obj1 = _buildMethod.getMember().invoke(obj, new Object[0]);
        }
        catch(Exception exception)
        {
            wrapInstantiationProblem(exception, deserializationcontext);
            return null;
        }
        return obj1;
    }

    public JsonDeserializer unwrappingDeserializer(NameTransformer nametransformer)
    {
        return _delegate.unwrappingDeserializer(nametransformer);
    }

    public volatile BeanDeserializerBase withIgnorableProperties(HashSet hashset)
    {
        return withIgnorableProperties(hashset);
    }

    public BeanAsArrayBuilderDeserializer withIgnorableProperties(HashSet hashset)
    {
        return new BeanAsArrayBuilderDeserializer(_delegate.withIgnorableProperties(hashset), _orderedProperties, _buildMethod);
    }

    public volatile BeanDeserializerBase withObjectIdReader(ObjectIdReader objectidreader)
    {
        return withObjectIdReader(objectidreader);
    }

    public BeanAsArrayBuilderDeserializer withObjectIdReader(ObjectIdReader objectidreader)
    {
        return new BeanAsArrayBuilderDeserializer(_delegate.withObjectIdReader(objectidreader), _orderedProperties, _buildMethod);
    }

    private static final long serialVersionUID = 1L;
    protected final AnnotatedMethod _buildMethod;
    protected final BeanDeserializerBase _delegate;
    protected final SettableBeanProperty _orderedProperties[];
}
