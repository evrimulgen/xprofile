// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            BeanDeserializerBase, BeanDeserializerBuilder, ValueInstantiator, SettableBeanProperty, 
//            SettableAnyProperty

public class BuilderBasedDeserializer extends BeanDeserializerBase
{

    public BuilderBasedDeserializer(BeanDeserializerBuilder beandeserializerbuilder, BeanDescription beandescription, BeanPropertyMap beanpropertymap, Map map, HashSet hashset, boolean flag, boolean flag1)
    {
        super(beandeserializerbuilder, beandescription, beanpropertymap, map, hashset, flag, flag1);
        _buildMethod = beandeserializerbuilder.getBuildMethod();
        if(_objectIdReader != null)
            throw new IllegalArgumentException((new StringBuilder()).append("Can not use Object Id with Builder-based deserialization (type ").append(beandescription.getType()).append(")").toString());
        else
            return;
    }

    protected BuilderBasedDeserializer(BuilderBasedDeserializer builderbaseddeserializer)
    {
        this(builderbaseddeserializer, builderbaseddeserializer._ignoreAllUnknown);
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderbaseddeserializer, ObjectIdReader objectidreader)
    {
        super(builderbaseddeserializer, objectidreader);
        _buildMethod = builderbaseddeserializer._buildMethod;
    }

    protected BuilderBasedDeserializer(BuilderBasedDeserializer builderbaseddeserializer, NameTransformer nametransformer)
    {
        super(builderbaseddeserializer, nametransformer);
        _buildMethod = builderbaseddeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderbaseddeserializer, HashSet hashset)
    {
        super(builderbaseddeserializer, hashset);
        _buildMethod = builderbaseddeserializer._buildMethod;
    }

    protected BuilderBasedDeserializer(BuilderBasedDeserializer builderbaseddeserializer, boolean flag)
    {
        super(builderbaseddeserializer, flag);
        _buildMethod = builderbaseddeserializer._buildMethod;
    }

    private final Object vanillaDeserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, JsonToken jsontoken)
        throws IOException, JsonProcessingException
    {
        Object obj = _valueInstantiator.createUsingDefault(deserializationcontext);
_L2:
        String s;
        SettableBeanProperty settablebeanproperty;
        if(jsonparser.getCurrentToken() == JsonToken.END_OBJECT)
            break MISSING_BLOCK_LABEL_99;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        settablebeanproperty = _beanProperties.find(s);
        if(settablebeanproperty == null)
            break; /* Loop/switch isn't completed */
        Object obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        obj = obj1;
_L3:
        jsonparser.nextToken();
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        wrapAndThrow(exception, obj, s, deserializationcontext);
          goto _L3
_L1:
        handleUnknownVanilla(jsonparser, deserializationcontext, obj, s);
          goto _L3
        return obj;
    }

    protected final Object _deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        if(_unwrappedPropertyHandler == null) goto _L2; else goto _L1
_L1:
        obj = deserializeWithUnwrapped(jsonparser, deserializationcontext, obj);
_L4:
        return obj;
_L2:
        JsonToken jsontoken;
        if(_externalTypeIdHandler != null)
            return deserializeWithExternalTypeId(jsonparser, deserializationcontext, obj);
        if(_needViewProcesing)
        {
            Class class1 = deserializationcontext.getActiveView();
            if(class1 != null)
                return deserializeWithView(jsonparser, deserializationcontext, obj, class1);
        }
        jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
            jsontoken = jsonparser.nextToken();
_L5:
        if(jsontoken != JsonToken.FIELD_NAME) goto _L4; else goto _L3
_L3:
        String s;
        SettableBeanProperty settablebeanproperty;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        settablebeanproperty = _beanProperties.find(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_165;
        Object obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        obj = obj1;
_L6:
        jsontoken = jsonparser.nextToken();
          goto _L5
          goto _L4
        Exception exception;
        exception;
        wrapAndThrow(exception, obj, s, deserializationcontext);
          goto _L6
        handleUnknownVanilla(jsonparser, deserializationcontext, handledType(), s);
          goto _L6
    }

    protected final Object _deserializeUsingPropertyBased(JsonParser jsonparser, DeserializationContext deserializationcontext)
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
_L2:
        String s;
        if(jsontoken != JsonToken.FIELD_NAME)
            break MISSING_BLOCK_LABEL_312;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        SettableBeanProperty settablebeanproperty = propertybasedcreator.findCreatorProperty(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_174;
        Object obj1 = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
        if(!propertyvaluebuffer.assignParameter(settablebeanproperty.getCreatorIndex(), obj1))
            break MISSING_BLOCK_LABEL_140;
        jsonparser.nextToken();
        Object obj2 = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        if(obj2.getClass() != _beanType.getRawClass())
            return handlePolymorphic(jsonparser, deserializationcontext, obj2, tokenbuffer);
        break; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        wrapAndThrow(exception1, _beanType.getRawClass(), s, deserializationcontext);
_L3:
        jsontoken = jsonparser.nextToken();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        Object obj;
        SettableBeanProperty settablebeanproperty1;
        Object obj3;
        if(tokenbuffer != null)
            obj3 = handleUnknownProperties(deserializationcontext, obj2, tokenbuffer);
        else
            obj3 = obj2;
        return _deserialize(jsonparser, deserializationcontext, obj3);
        if(!propertyvaluebuffer.readIdProperty(s))
        {
            settablebeanproperty1 = _beanProperties.find(s);
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
          goto _L3
        try
        {
            obj = propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            wrapInstantiationProblem(exception, deserializationcontext);
            return null;
        }
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
    }

    protected volatile BeanDeserializerBase asArrayDeserializer()
    {
        return asArrayDeserializer();
    }

    protected BeanAsArrayBuilderDeserializer asArrayDeserializer()
    {
        return new BeanAsArrayBuilderDeserializer(this, _beanProperties.getPropertiesInInsertionOrder(), _buildMethod);
    }

    public final Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
        {
            JsonToken jsontoken1 = jsonparser.nextToken();
            if(_vanillaProcessing)
                return finishBuild(deserializationcontext, vanillaDeserialize(jsonparser, deserializationcontext, jsontoken1));
            else
                return finishBuild(deserializationcontext, deserializeFromObject(jsonparser, deserializationcontext));
        }
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
            return finishBuild(deserializationcontext, deserializeFromString(jsonparser, deserializationcontext));

        case 2: // '\002'
            return finishBuild(deserializationcontext, deserializeFromNumber(jsonparser, deserializationcontext));

        case 3: // '\003'
            return finishBuild(deserializationcontext, deserializeFromDouble(jsonparser, deserializationcontext));

        case 4: // '\004'
            return jsonparser.getEmbeddedObject();

        case 5: // '\005'
        case 6: // '\006'
            return finishBuild(deserializationcontext, deserializeFromBoolean(jsonparser, deserializationcontext));

        case 7: // '\007'
            return finishBuild(deserializationcontext, deserializeFromArray(jsonparser, deserializationcontext));

        case 8: // '\b'
        case 9: // '\t'
            return finishBuild(deserializationcontext, deserializeFromObject(jsonparser, deserializationcontext));
        }
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        return finishBuild(deserializationcontext, _deserialize(jsonparser, deserializationcontext, obj));
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
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        if(_needViewProcesing)
        {
            Class class1 = deserializationcontext.getActiveView();
            if(class1 != null)
                return deserializeWithView(jsonparser, deserializationcontext, obj, class1);
        }
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        String s;
        wrapAndThrow(exception, obj, s, deserializationcontext);
_L7:
        jsonparser.nextToken();
        if(jsonparser.getCurrentToken() == JsonToken.END_OBJECT) goto _L6; else goto _L5
_L5:
        SettableBeanProperty settablebeanproperty;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        settablebeanproperty = _beanProperties.find(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_164;
        Object obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        obj = obj1;
          goto _L7
        handleUnknownVanilla(jsonparser, deserializationcontext, obj, s);
          goto _L7
    }

    protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        throw new IllegalStateException("Deserialization with Builder, External type id, @JsonCreator not yet implemented");
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
            break MISSING_BLOCK_LABEL_326;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        SettableBeanProperty settablebeanproperty = propertybasedcreator.findCreatorProperty(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_206;
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
            throw deserializationcontext.mappingException("Can not create polymorphic instances with unwrapped values");
        else
            return _unwrappedPropertyHandler.processUnwrapped(jsonparser, deserializationcontext, obj2, tokenbuffer);
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
        ExternalTypeHandler externaltypehandler;
        String s;
        SettableBeanProperty settablebeanproperty;
        Class class1;
        if(_needViewProcesing)
            class1 = deserializationcontext.getActiveView();
        else
            class1 = null;
        externaltypehandler = _externalTypeIdHandler.start();
        if(jsonparser.getCurrentToken() == JsonToken.END_OBJECT)
            break MISSING_BLOCK_LABEL_217;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        settablebeanproperty = _beanProperties.find(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_124;
        if(class1 == null || settablebeanproperty.visibleInView(class1))
            break; /* Loop/switch isn't completed */
        jsonparser.skipChildren();
_L4:
        jsonparser.nextToken();
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_22;
_L1:
        Object obj1;
        try
        {
            obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        }
        catch(Exception exception1)
        {
            wrapAndThrow(exception1, obj, s, deserializationcontext);
            continue; /* Loop/switch isn't completed */
        }
        obj = obj1;
        continue; /* Loop/switch isn't completed */
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
        if(true) goto _L4; else goto _L3
_L3:
        return externaltypehandler.complete(jsonparser, deserializationcontext, obj);
    }

    protected Object deserializeWithUnwrapped(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        TokenBuffer tokenbuffer;
        Object obj;
        String s;
        SettableBeanProperty settablebeanproperty;
        if(_delegateDeserializer != null)
            return _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
        if(_propertyBasedCreator != null)
            return deserializeUsingPropertyBasedWithUnwrapped(jsonparser, deserializationcontext);
        tokenbuffer = new TokenBuffer(jsonparser);
        tokenbuffer.writeStartObject();
        obj = _valueInstantiator.createUsingDefault(deserializationcontext);
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        Class class1;
        if(_needViewProcesing)
            class1 = deserializationcontext.getActiveView();
        else
            class1 = null;
        if(jsonparser.getCurrentToken() == JsonToken.END_OBJECT)
            break MISSING_BLOCK_LABEL_276;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        settablebeanproperty = _beanProperties.find(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_194;
        if(class1 == null || settablebeanproperty.visibleInView(class1))
            break; /* Loop/switch isn't completed */
        jsonparser.skipChildren();
_L4:
        jsonparser.nextToken();
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_89;
_L1:
        Object obj1;
        try
        {
            obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        }
        catch(Exception exception1)
        {
            wrapAndThrow(exception1, obj, s, deserializationcontext);
            continue; /* Loop/switch isn't completed */
        }
        obj = obj1;
        continue; /* Loop/switch isn't completed */
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
        if(true) goto _L4; else goto _L3
_L3:
        tokenbuffer.writeEndObject();
        _unwrappedPropertyHandler.processUnwrapped(jsonparser, deserializationcontext, obj, tokenbuffer);
        return obj;
    }

    protected Object deserializeWithUnwrapped(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        TokenBuffer tokenbuffer;
        String s;
        SettableBeanProperty settablebeanproperty;
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
            jsontoken = jsonparser.nextToken();
        tokenbuffer = new TokenBuffer(jsonparser);
        tokenbuffer.writeStartObject();
        Class class1;
        if(_needViewProcesing)
            class1 = deserializationcontext.getActiveView();
        else
            class1 = null;
        if(jsontoken != JsonToken.FIELD_NAME)
            break MISSING_BLOCK_LABEL_215;
        s = jsonparser.getCurrentName();
        settablebeanproperty = _beanProperties.find(s);
        jsonparser.nextToken();
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_149;
        if(class1 == null || settablebeanproperty.visibleInView(class1))
            break; /* Loop/switch isn't completed */
        jsonparser.skipChildren();
_L4:
        jsontoken = jsonparser.nextToken();
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_48;
_L1:
        Object obj1;
        try
        {
            obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        }
        catch(Exception exception)
        {
            wrapAndThrow(exception, obj, s, deserializationcontext);
            continue; /* Loop/switch isn't completed */
        }
        obj = obj1;
        continue; /* Loop/switch isn't completed */
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
        if(true) goto _L4; else goto _L3
_L3:
        tokenbuffer.writeEndObject();
        _unwrappedPropertyHandler.processUnwrapped(jsonparser, deserializationcontext, obj, tokenbuffer);
        return obj;
    }

    protected final Object deserializeWithView(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, Class class1)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
_L2:
        String s;
        SettableBeanProperty settablebeanproperty;
        if(jsontoken != JsonToken.FIELD_NAME)
            break MISSING_BLOCK_LABEL_108;
        s = jsonparser.getCurrentName();
        jsonparser.nextToken();
        settablebeanproperty = _beanProperties.find(s);
        if(settablebeanproperty == null)
            break MISSING_BLOCK_LABEL_96;
        if(settablebeanproperty.visibleInView(class1))
            break; /* Loop/switch isn't completed */
        jsonparser.skipChildren();
_L3:
        jsontoken = jsonparser.nextToken();
        if(true) goto _L2; else goto _L1
_L1:
        Object obj1 = settablebeanproperty.deserializeSetAndReturn(jsonparser, deserializationcontext, obj);
        obj = obj1;
          goto _L3
        Exception exception;
        exception;
        wrapAndThrow(exception, obj, s, deserializationcontext);
          goto _L3
        handleUnknownVanilla(jsonparser, deserializationcontext, obj, s);
          goto _L3
        return obj;
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
        return new BuilderBasedDeserializer(this, nametransformer);
    }

    public volatile BeanDeserializerBase withIgnorableProperties(HashSet hashset)
    {
        return withIgnorableProperties(hashset);
    }

    public BuilderBasedDeserializer withIgnorableProperties(HashSet hashset)
    {
        return new BuilderBasedDeserializer(this, hashset);
    }

    public volatile BeanDeserializerBase withObjectIdReader(ObjectIdReader objectidreader)
    {
        return withObjectIdReader(objectidreader);
    }

    public BuilderBasedDeserializer withObjectIdReader(ObjectIdReader objectidreader)
    {
        return new BuilderBasedDeserializer(this, objectidreader);
    }

    private static final long serialVersionUID = 1L;
    protected final AnnotatedMethod _buildMethod;
}
