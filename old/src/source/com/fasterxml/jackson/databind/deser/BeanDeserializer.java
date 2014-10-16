// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            BeanDeserializerBase, ValueInstantiator, SettableBeanProperty, SettableAnyProperty, 
//            BeanDeserializerBuilder

public class BeanDeserializer extends BeanDeserializerBase
    implements Serializable
{

    protected BeanDeserializer(BeanDeserializerBase beandeserializerbase)
    {
        super(beandeserializerbase, beandeserializerbase._ignoreAllUnknown);
    }

    public BeanDeserializer(BeanDeserializerBase beandeserializerbase, ObjectIdReader objectidreader)
    {
        super(beandeserializerbase, objectidreader);
    }

    protected BeanDeserializer(BeanDeserializerBase beandeserializerbase, NameTransformer nametransformer)
    {
        super(beandeserializerbase, nametransformer);
    }

    public BeanDeserializer(BeanDeserializerBase beandeserializerbase, HashSet hashset)
    {
        super(beandeserializerbase, hashset);
    }

    protected BeanDeserializer(BeanDeserializerBase beandeserializerbase, boolean flag)
    {
        super(beandeserializerbase, flag);
    }

    public BeanDeserializer(BeanDeserializerBuilder beandeserializerbuilder, BeanDescription beandescription, BeanPropertyMap beanpropertymap, Map map, HashSet hashset, boolean flag, boolean flag1)
    {
        super(beandeserializerbuilder, beandescription, beanpropertymap, map, hashset, flag, flag1);
    }

    private final Object vanillaDeserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, JsonToken jsontoken)
        throws IOException, JsonProcessingException
    {
        Object obj = _valueInstantiator.createUsingDefault(deserializationcontext);
        while(jsonparser.getCurrentToken() != JsonToken.END_OBJECT) 
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
            if(settablebeanproperty != null)
                try
                {
                    settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                }
                catch(Exception exception)
                {
                    wrapAndThrow(exception, obj, s, deserializationcontext);
                }
            else
                handleUnknownVanilla(jsonparser, deserializationcontext, obj, s);
            jsonparser.nextToken();
        }
        return obj;
    }

    protected final Object _deserializeOther(JsonParser jsonparser, DeserializationContext deserializationcontext, JsonToken jsontoken)
        throws IOException, JsonProcessingException
    {
        if(jsontoken == null)
            return _missingToken(jsonparser, deserializationcontext);
        static class _cls1
        {

            static final int $SwitchMap$com$fasterxml$jackson$core$JsonToken[];

            static 
            {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsontoken.ordinal()])
        {
        default:
            throw deserializationcontext.mappingException(handledType());

        case 1: // '\001'
            return deserializeFromString(jsonparser, deserializationcontext);

        case 2: // '\002'
            return deserializeFromNumber(jsonparser, deserializationcontext);

        case 3: // '\003'
            return deserializeFromDouble(jsonparser, deserializationcontext);

        case 4: // '\004'
            return jsonparser.getEmbeddedObject();

        case 5: // '\005'
        case 6: // '\006'
            return deserializeFromBoolean(jsonparser, deserializationcontext);

        case 7: // '\007'
            return deserializeFromArray(jsonparser, deserializationcontext);

        case 8: // '\b'
        case 9: // '\t'
            break;
        }
        if(_vanillaProcessing)
            return vanillaDeserialize(jsonparser, deserializationcontext, jsontoken);
        if(_objectIdReader != null)
            return deserializeWithObjectId(jsonparser, deserializationcontext);
        else
            return deserializeFromObject(jsonparser, deserializationcontext);
    }

    protected Object _deserializeUsingPropertyBased(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        PropertyBasedCreator propertybasedcreator;
        PropertyValueBuffer propertyvaluebuffer;
        JsonToken jsontoken;
        TokenBuffer tokenbuffer;
        propertybasedcreator = _propertyBasedCreator;
        propertyvaluebuffer = propertybasedcreator.startBuilding(jsonparser, deserializationcontext, _objectIdReader);
        jsontoken = jsonparser.getCurrentToken();
        tokenbuffer = null;
_L5:
        if(jsontoken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
        String s;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        SettableBeanProperty settablebeanproperty = propertybasedcreator.findCreatorProperty(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_175;
        Object obj2 = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
        if(!propertyvaluebuffer.assignParameter(settablebeanproperty.getCreatorIndex(), obj2))
            continue; /* Loop/switch isn't completed */
        jsonparser.nextToken();
        Object obj4 = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        Object obj3 = obj4;
_L4:
        if(obj3.getClass() != _beanType.getRawClass())
            return handlePolymorphic(jsonparser, deserializationcontext, obj3, tokenbuffer);
        break; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        wrapAndThrow(exception1, _beanType.getRawClass(), s, deserializationcontext);
        obj3 = null;
        if(true) goto _L4; else goto _L3
_L3:
        if(tokenbuffer != null)
            obj3 = handleUnknownProperties(deserializationcontext, obj3, tokenbuffer);
        return deserialize(jsonparser, deserializationcontext, obj3);
        if(!propertyvaluebuffer.readIdProperty(s))
        {
            SettableBeanProperty settablebeanproperty1 = _beanProperties.find(s);
            if(settablebeanproperty1 != null)
                propertyvaluebuffer.bufferProperty(settablebeanproperty1, settablebeanproperty1.deserialize(jsonparser, deserializationcontext));
            else
            if(_ignorableProps != null && _ignorableProps.contains(s))
                handleIgnoredProperty(jsonparser, deserializationcontext, handledType(), s);
            else
            if(_anySetter != null)
            {
                propertyvaluebuffer.bufferAnyProperty(_anySetter, s, _anySetter.deserialize(jsonparser, deserializationcontext));
            } else
            {
                if(tokenbuffer == null)
                    tokenbuffer = new TokenBuffer(jsonparser);
                tokenbuffer.writeFieldName(s);
                tokenbuffer.copyCurrentStructure(jsonparser);
            }
        }
        jsontoken = jsonparser.nextToken();
          goto _L5
_L2:
        Object obj1 = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        Object obj = obj1;
_L6:
        Exception exception;
        if(tokenbuffer != null)
        {
            if(obj.getClass() != _beanType.getRawClass())
                return handlePolymorphic(null, deserializationcontext, obj, tokenbuffer);
            else
                return handleUnknownProperties(deserializationcontext, obj, tokenbuffer);
        } else
        {
            return obj;
        }
        exception;
        wrapInstantiationProblem(exception, deserializationcontext);
        obj = null;
          goto _L6
    }

    protected Object _missingToken(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws JsonProcessingException
    {
        throw deserializationcontext.endOfInputException(handledType());
    }

    protected BeanDeserializerBase asArrayDeserializer()
    {
        return new BeanAsArrayDeserializer(this, _beanProperties.getPropertiesInInsertionOrder());
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
        {
            if(_vanillaProcessing)
                return vanillaDeserialize(jsonparser, deserializationcontext, jsonparser.nextToken());
            jsonparser.nextToken();
            if(_objectIdReader != null)
                return deserializeWithObjectId(jsonparser, deserializationcontext);
            else
                return deserializeFromObject(jsonparser, deserializationcontext);
        } else
        {
            return _deserializeOther(jsonparser, deserializationcontext, jsontoken);
        }
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        if(_unwrappedPropertyHandler != null)
        {
            obj = deserializeWithUnwrapped(jsonparser, deserializationcontext, obj);
        } else
        {
            if(_externalTypeIdHandler != null)
                return deserializeWithExternalTypeId(jsonparser, deserializationcontext, obj);
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if(jsontoken == JsonToken.START_OBJECT)
                jsontoken = jsonparser.nextToken();
            if(_needViewProcesing)
            {
                Class class1 = deserializationcontext.getActiveView();
                if(class1 != null)
                    return deserializeWithView(jsonparser, deserializationcontext, obj, class1);
            }
            while(jsontoken == JsonToken.FIELD_NAME) 
            {
                String s = jsonparser.getCurrentName();
                jsonparser.nextToken();
                SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
                if(settablebeanproperty != null)
                    try
                    {
                        settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                    }
                    catch(Exception exception)
                    {
                        wrapAndThrow(exception, obj, s, deserializationcontext);
                    }
                else
                    handleUnknownVanilla(jsonparser, deserializationcontext, obj, s);
                jsontoken = jsonparser.nextToken();
            }
        }
        return obj;
    }

    public Object deserializeFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(!_nonStandardCreation) goto _L2; else goto _L1
_L1:
        if(_unwrappedPropertyHandler == null) goto _L4; else goto _L3
_L3:
        Object obj = deserializeWithUnwrapped(jsonparser, deserializationcontext);
_L6:
        return obj;
_L4:
        if(_externalTypeIdHandler != null)
            return deserializeWithExternalTypeId(jsonparser, deserializationcontext);
        else
            return deserializeFromObjectUsingNonDefault(jsonparser, deserializationcontext);
_L2:
        obj = _valueInstantiator.createUsingDefault(deserializationcontext);
        if(jsonparser.canReadObjectId())
        {
            Object obj1 = jsonparser.getObjectId();
            if(obj1 != null)
                _handleTypedObjectId(jsonparser, deserializationcontext, obj, obj1);
        }
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        if(_needViewProcesing)
        {
            Class class1 = deserializationcontext.getActiveView();
            if(class1 != null)
                return deserializeWithView(jsonparser, deserializationcontext, obj, class1);
        }
        while(jsonparser.getCurrentToken() != JsonToken.END_OBJECT) 
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
            if(settablebeanproperty != null)
                try
                {
                    settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                }
                catch(Exception exception)
                {
                    wrapAndThrow(exception, obj, s, deserializationcontext);
                }
            else
                handleUnknownVanilla(jsonparser, deserializationcontext, obj, s);
            jsonparser.nextToken();
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        ExternalTypeHandler externaltypehandler;
        PropertyBasedCreator propertybasedcreator;
        PropertyValueBuffer propertyvaluebuffer;
        TokenBuffer tokenbuffer;
        JsonToken jsontoken;
        externaltypehandler = _externalTypeIdHandler.start();
        propertybasedcreator = _propertyBasedCreator;
        propertyvaluebuffer = propertybasedcreator.startBuilding(jsonparser, deserializationcontext, _objectIdReader);
        tokenbuffer = new TokenBuffer(jsonparser);
        tokenbuffer.writeStartObject();
        jsontoken = jsonparser.getCurrentToken();
_L2:
        String s;
        SettableBeanProperty settablebeanproperty;
        if(jsontoken != JsonToken.FIELD_NAME)
            break MISSING_BLOCK_LABEL_343;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        settablebeanproperty = propertybasedcreator.findCreatorProperty(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_224;
        if(!externaltypehandler.handlePropertyValue(jsonparser, deserializationcontext, s, propertyvaluebuffer))
            break; /* Loop/switch isn't completed */
_L4:
        jsontoken = jsonparser.nextToken();
        if(true) goto _L2; else goto _L1
_L1:
        Object obj1 = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
        if(!propertyvaluebuffer.assignParameter(settablebeanproperty.getCreatorIndex(), obj1)) goto _L4; else goto _L3
_L3:
        JsonToken jsontoken1 = jsonparser.nextToken();
        Object obj2 = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        for(; jsontoken1 == JsonToken.FIELD_NAME; jsontoken1 = jsonparser.nextToken())
        {
            jsonparser.nextToken();
            tokenbuffer.copyCurrentStructure(jsonparser);
        }

        break MISSING_BLOCK_LABEL_192;
        Exception exception1;
        exception1;
        wrapAndThrow(exception1, _beanType.getRawClass(), s, deserializationcontext);
          goto _L4
        if(obj2.getClass() != _beanType.getRawClass())
            throw deserializationcontext.mappingException("Can not create polymorphic instances with unwrapped values");
        else
            return externaltypehandler.complete(jsonparser, deserializationcontext, obj2);
        if(!propertyvaluebuffer.readIdProperty(s))
        {
            SettableBeanProperty settablebeanproperty1 = _beanProperties.find(s);
            if(settablebeanproperty1 != null)
                propertyvaluebuffer.bufferProperty(settablebeanproperty1, settablebeanproperty1.deserialize(jsonparser, deserializationcontext));
            else
            if(!externaltypehandler.handlePropertyValue(jsonparser, deserializationcontext, s, null))
                if(_ignorableProps != null && _ignorableProps.contains(s))
                    handleIgnoredProperty(jsonparser, deserializationcontext, handledType(), s);
                else
                if(_anySetter != null)
                    propertyvaluebuffer.bufferAnyProperty(_anySetter, s, _anySetter.deserialize(jsonparser, deserializationcontext));
        }
          goto _L4
        Object obj;
        try
        {
            obj = externaltypehandler.complete(jsonparser, deserializationcontext, propertyvaluebuffer, propertybasedcreator);
        }
        catch(Exception exception)
        {
            wrapInstantiationProblem(exception, deserializationcontext);
            return null;
        }
        return obj;
    }

    protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        PropertyBasedCreator propertybasedcreator;
        PropertyValueBuffer propertyvaluebuffer;
        TokenBuffer tokenbuffer;
        JsonToken jsontoken;
        propertybasedcreator = _propertyBasedCreator;
        propertyvaluebuffer = propertybasedcreator.startBuilding(jsonparser, deserializationcontext, _objectIdReader);
        tokenbuffer = new TokenBuffer(jsonparser);
        tokenbuffer.writeStartObject();
        jsontoken = jsonparser.getCurrentToken();
_L2:
        String s;
        JsonToken jsontoken1;
        if(jsontoken != JsonToken.FIELD_NAME)
            break MISSING_BLOCK_LABEL_331;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        SettableBeanProperty settablebeanproperty = propertybasedcreator.findCreatorProperty(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_211;
        Object obj1 = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
        if(!propertyvaluebuffer.assignParameter(settablebeanproperty.getCreatorIndex(), obj1))
            break MISSING_BLOCK_LABEL_155;
        jsontoken1 = jsonparser.nextToken();
        Object obj2 = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        for(; jsontoken1 == JsonToken.FIELD_NAME; jsontoken1 = jsonparser.nextToken())
        {
            jsonparser.nextToken();
            tokenbuffer.copyCurrentStructure(jsonparser);
        }

        break; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        wrapAndThrow(exception1, _beanType.getRawClass(), s, deserializationcontext);
_L3:
        jsontoken = jsonparser.nextToken();
        if(true) goto _L2; else goto _L1
_L1:
        tokenbuffer.writeEndObject();
        if(obj2.getClass() != _beanType.getRawClass())
        {
            tokenbuffer.close();
            throw deserializationcontext.mappingException("Can not create polymorphic instances with unwrapped values");
        } else
        {
            return _unwrappedPropertyHandler.processUnwrapped(jsonparser, deserializationcontext, obj2, tokenbuffer);
        }
        if(!propertyvaluebuffer.readIdProperty(s))
        {
            SettableBeanProperty settablebeanproperty1 = _beanProperties.find(s);
            if(settablebeanproperty1 != null)
                propertyvaluebuffer.bufferProperty(settablebeanproperty1, settablebeanproperty1.deserialize(jsonparser, deserializationcontext));
            else
            if(_ignorableProps != null && _ignorableProps.contains(s))
            {
                handleIgnoredProperty(jsonparser, deserializationcontext, handledType(), s);
            } else
            {
                tokenbuffer.writeFieldName(s);
                tokenbuffer.copyCurrentStructure(jsonparser);
                if(_anySetter != null)
                    propertyvaluebuffer.bufferAnyProperty(_anySetter, s, _anySetter.deserialize(jsonparser, deserializationcontext));
            }
        }
          goto _L3
        Object obj;
        try
        {
            obj = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        }
        catch(Exception exception)
        {
            wrapInstantiationProblem(exception, deserializationcontext);
            return null;
        }
        return _unwrappedPropertyHandler.processUnwrapped(jsonparser, deserializationcontext, obj, tokenbuffer);
    }

    protected Object deserializeWithExternalTypeId(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_propertyBasedCreator != null)
            return deserializeUsingPropertyBasedWithExternalTypeId(jsonparser, deserializationcontext);
        else
            return deserializeWithExternalTypeId(jsonparser, deserializationcontext, _valueInstantiator.createUsingDefault(deserializationcontext));
    }

    protected Object deserializeWithExternalTypeId(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        Class class1;
        ExternalTypeHandler externaltypehandler;
        if(_needViewProcesing)
            class1 = deserializationcontext.getActiveView();
        else
            class1 = null;
        externaltypehandler = _externalTypeIdHandler.start();
        while(jsonparser.getCurrentToken() != JsonToken.END_OBJECT) 
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
            if(settablebeanproperty != null)
            {
                if(jsonparser.getCurrentToken().isScalarValue())
                    externaltypehandler.handleTypePropertyValue(jsonparser, deserializationcontext, s, obj);
                if(class1 != null && !settablebeanproperty.visibleInView(class1))
                    jsonparser.skipChildren();
                else
                    try
                    {
                        settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                    }
                    catch(Exception exception1)
                    {
                        wrapAndThrow(exception1, obj, s, deserializationcontext);
                    }
            } else
            if(_ignorableProps != null && _ignorableProps.contains(s))
                handleIgnoredProperty(jsonparser, deserializationcontext, obj, s);
            else
            if(!externaltypehandler.handlePropertyValue(jsonparser, deserializationcontext, s, obj))
                if(_anySetter != null)
                    try
                    {
                        _anySetter.deserializeAndSet(jsonparser, deserializationcontext, obj, s);
                    }
                    catch(Exception exception)
                    {
                        wrapAndThrow(exception, obj, s, deserializationcontext);
                    }
                else
                    handleUnknownProperty(jsonparser, deserializationcontext, obj, s);
            jsonparser.nextToken();
        }
        return externaltypehandler.complete(jsonparser, deserializationcontext, obj);
    }

    protected Object deserializeWithUnwrapped(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_delegateDeserializer != null)
            return _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
        if(_propertyBasedCreator != null)
            return deserializeUsingPropertyBasedWithUnwrapped(jsonparser, deserializationcontext);
        TokenBuffer tokenbuffer = new TokenBuffer(jsonparser);
        tokenbuffer.writeStartObject();
        Object obj = _valueInstantiator.createUsingDefault(deserializationcontext);
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        Class class1;
        if(_needViewProcesing)
            class1 = deserializationcontext.getActiveView();
        else
            class1 = null;
        while(jsonparser.getCurrentToken() != JsonToken.END_OBJECT) 
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
            if(settablebeanproperty != null)
            {
                if(class1 != null && !settablebeanproperty.visibleInView(class1))
                    jsonparser.skipChildren();
                else
                    try
                    {
                        settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                    }
                    catch(Exception exception1)
                    {
                        wrapAndThrow(exception1, obj, s, deserializationcontext);
                    }
            } else
            if(_ignorableProps != null && _ignorableProps.contains(s))
            {
                handleIgnoredProperty(jsonparser, deserializationcontext, obj, s);
            } else
            {
                tokenbuffer.writeFieldName(s);
                tokenbuffer.copyCurrentStructure(jsonparser);
                if(_anySetter != null)
                    try
                    {
                        _anySetter.deserializeAndSet(jsonparser, deserializationcontext, obj, s);
                    }
                    catch(Exception exception)
                    {
                        wrapAndThrow(exception, obj, s, deserializationcontext);
                    }
            }
            jsonparser.nextToken();
        }
        tokenbuffer.writeEndObject();
        _unwrappedPropertyHandler.processUnwrapped(jsonparser, deserializationcontext, obj, tokenbuffer);
        return obj;
    }

    protected Object deserializeWithUnwrapped(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
            jsontoken = jsonparser.nextToken();
        TokenBuffer tokenbuffer = new TokenBuffer(jsonparser);
        tokenbuffer.writeStartObject();
        Class class1;
        if(_needViewProcesing)
            class1 = deserializationcontext.getActiveView();
        else
            class1 = null;
        while(jsontoken == JsonToken.FIELD_NAME) 
        {
            String s = jsonparser.getCurrentName();
            SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
            jsonparser.nextToken();
            if(settablebeanproperty != null)
            {
                if(class1 != null && !settablebeanproperty.visibleInView(class1))
                    jsonparser.skipChildren();
                else
                    try
                    {
                        settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                    }
                    catch(Exception exception)
                    {
                        wrapAndThrow(exception, obj, s, deserializationcontext);
                    }
            } else
            if(_ignorableProps != null && _ignorableProps.contains(s))
            {
                handleIgnoredProperty(jsonparser, deserializationcontext, obj, s);
            } else
            {
                tokenbuffer.writeFieldName(s);
                tokenbuffer.copyCurrentStructure(jsonparser);
                if(_anySetter != null)
                    _anySetter.deserializeAndSet(jsonparser, deserializationcontext, obj, s);
            }
            jsontoken = jsonparser.nextToken();
        }
        tokenbuffer.writeEndObject();
        _unwrappedPropertyHandler.processUnwrapped(jsonparser, deserializationcontext, obj, tokenbuffer);
        return obj;
    }

    protected final Object deserializeWithView(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, Class class1)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        while(jsontoken == JsonToken.FIELD_NAME) 
        {
            String s = jsonparser.getCurrentName();
            jsonparser.nextToken();
            SettableBeanProperty settablebeanproperty = _beanProperties.find(s);
            if(settablebeanproperty != null)
            {
                if(!settablebeanproperty.visibleInView(class1))
                    jsonparser.skipChildren();
                else
                    try
                    {
                        settablebeanproperty.deserializeAndSet(jsonparser, deserializationcontext, obj);
                    }
                    catch(Exception exception)
                    {
                        wrapAndThrow(exception, obj, s, deserializationcontext);
                    }
            } else
            {
                handleUnknownVanilla(jsonparser, deserializationcontext, obj, s);
            }
            jsontoken = jsonparser.nextToken();
        }
        return obj;
    }

    public JsonDeserializer unwrappingDeserializer(NameTransformer nametransformer)
    {
        if(getClass() != com/fasterxml/jackson/databind/deser/BeanDeserializer)
            return this;
        else
            return new BeanDeserializer(this, nametransformer);
    }

    public BeanDeserializer withIgnorableProperties(HashSet hashset)
    {
        return new BeanDeserializer(this, hashset);
    }

    public volatile BeanDeserializerBase withIgnorableProperties(HashSet hashset)
    {
        return withIgnorableProperties(hashset);
    }

    public BeanDeserializer withObjectIdReader(ObjectIdReader objectidreader)
    {
        return new BeanDeserializer(this, objectidreader);
    }

    public volatile BeanDeserializerBase withObjectIdReader(ObjectIdReader objectidreader)
    {
        return withObjectIdReader(objectidreader);
    }

    private static final long serialVersionUID = 1L;
}
