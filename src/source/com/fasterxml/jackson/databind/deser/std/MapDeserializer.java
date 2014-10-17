// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            ContainerDeserializerBase

public class MapDeserializer extends ContainerDeserializerBase
    implements ContextualDeserializer, ResolvableDeserializer
{

    public MapDeserializer(JavaType javatype, ValueInstantiator valueinstantiator, KeyDeserializer keydeserializer, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer)
    {
        super(javatype);
        _mapType = javatype;
        _keyDeserializer = keydeserializer;
        _valueDeserializer = jsondeserializer;
        _valueTypeDeserializer = typedeserializer;
        _valueInstantiator = valueinstantiator;
        _hasDefaultCreator = valueinstantiator.canCreateUsingDefault();
        _delegateDeserializer = null;
        _propertyBasedCreator = null;
        _standardStringKey = _isStdKeyDeser(javatype, keydeserializer);
    }

    protected MapDeserializer(MapDeserializer mapdeserializer)
    {
        super(mapdeserializer._mapType);
        _mapType = mapdeserializer._mapType;
        _keyDeserializer = mapdeserializer._keyDeserializer;
        _valueDeserializer = mapdeserializer._valueDeserializer;
        _valueTypeDeserializer = mapdeserializer._valueTypeDeserializer;
        _valueInstantiator = mapdeserializer._valueInstantiator;
        _propertyBasedCreator = mapdeserializer._propertyBasedCreator;
        _delegateDeserializer = mapdeserializer._delegateDeserializer;
        _hasDefaultCreator = mapdeserializer._hasDefaultCreator;
        _ignorableProperties = mapdeserializer._ignorableProperties;
        _standardStringKey = mapdeserializer._standardStringKey;
    }

    protected MapDeserializer(MapDeserializer mapdeserializer, KeyDeserializer keydeserializer, JsonDeserializer jsondeserializer, TypeDeserializer typedeserializer, HashSet hashset)
    {
        super(mapdeserializer._mapType);
        _mapType = mapdeserializer._mapType;
        _keyDeserializer = keydeserializer;
        _valueDeserializer = jsondeserializer;
        _valueTypeDeserializer = typedeserializer;
        _valueInstantiator = mapdeserializer._valueInstantiator;
        _propertyBasedCreator = mapdeserializer._propertyBasedCreator;
        _delegateDeserializer = mapdeserializer._delegateDeserializer;
        _hasDefaultCreator = mapdeserializer._hasDefaultCreator;
        _ignorableProperties = hashset;
        _standardStringKey = _isStdKeyDeser(_mapType, keydeserializer);
    }

    public Map _deserializeUsingCreator(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        PropertyBasedCreator propertybasedcreator = _propertyBasedCreator;
        PropertyValueBuffer propertyvaluebuffer = propertybasedcreator.startBuilding(jsonparser, deserializationcontext, null);
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
            jsontoken = jsonparser.nextToken();
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        while(jsontoken == JsonToken.FIELD_NAME) 
        {
            String s = jsonparser.getCurrentName();
            JsonToken jsontoken1 = jsonparser.nextToken();
            if(_ignorableProperties != null && _ignorableProperties.contains(s))
            {
                jsonparser.skipChildren();
            } else
            {
                SettableBeanProperty settablebeanproperty = propertybasedcreator.findCreatorProperty(s);
                if(settablebeanproperty != null)
                {
                    Object obj2 = settablebeanproperty.deserialize(jsonparser, deserializationcontext);
                    if(propertyvaluebuffer.assignParameter(settablebeanproperty.getCreatorIndex(), obj2))
                    {
                        jsonparser.nextToken();
                        Map map1;
                        try
                        {
                            map1 = (Map)propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
                        }
                        catch(Exception exception1)
                        {
                            wrapAndThrow(exception1, _mapType.getRawClass());
                            return null;
                        }
                        _readAndBind(jsonparser, deserializationcontext, map1);
                        return map1;
                    }
                } else
                {
                    String s1 = jsonparser.getCurrentName();
                    Object obj = _keyDeserializer.deserializeKey(s1, deserializationcontext);
                    Object obj1;
                    if(jsontoken1 == JsonToken.VALUE_NULL)
                        obj1 = null;
                    else
                    if(typedeserializer == null)
                        obj1 = jsondeserializer.deserialize(jsonparser, deserializationcontext);
                    else
                        obj1 = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
                    propertyvaluebuffer.bufferMapProperty(obj, obj1);
                }
            }
            jsontoken = jsonparser.nextToken();
        }
        Map map;
        try
        {
            map = (Map)propertybasedcreator.build(deserializationcontext, propertyvaluebuffer);
        }
        catch(Exception exception)
        {
            wrapAndThrow(exception, _mapType.getRawClass());
            return null;
        }
        return map;
    }

    protected final boolean _isStdKeyDeser(JavaType javatype, KeyDeserializer keydeserializer)
    {
        JavaType javatype1;
        Class class1;
        if(keydeserializer != null)
            if((javatype1 = javatype.getKeyType()) != null && ((class1 = javatype1.getRawClass()) != java/lang/String && class1 != java/lang/Object || !isDefaultKeyDeserializer(keydeserializer)))
                return false;
        return true;
    }

    protected final void _readAndBind(JsonParser jsonparser, DeserializationContext deserializationcontext, Map map)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
            jsontoken = jsonparser.nextToken();
        KeyDeserializer keydeserializer = _keyDeserializer;
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        while(jsontoken == JsonToken.FIELD_NAME) 
        {
            String s = jsonparser.getCurrentName();
            Object obj = keydeserializer.deserializeKey(s, deserializationcontext);
            JsonToken jsontoken1 = jsonparser.nextToken();
            if(_ignorableProperties != null && _ignorableProperties.contains(s))
            {
                jsonparser.skipChildren();
            } else
            {
                Object obj1;
                if(jsontoken1 == JsonToken.VALUE_NULL)
                    obj1 = null;
                else
                if(typedeserializer == null)
                    obj1 = jsondeserializer.deserialize(jsonparser, deserializationcontext);
                else
                    obj1 = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
                map.put(obj, obj1);
            }
            jsontoken = jsonparser.nextToken();
        }
    }

    protected final void _readAndBindStringMap(JsonParser jsonparser, DeserializationContext deserializationcontext, Map map)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken == JsonToken.START_OBJECT)
            jsontoken = jsonparser.nextToken();
        JsonDeserializer jsondeserializer = _valueDeserializer;
        TypeDeserializer typedeserializer = _valueTypeDeserializer;
        while(jsontoken == JsonToken.FIELD_NAME) 
        {
            String s = jsonparser.getCurrentName();
            JsonToken jsontoken1 = jsonparser.nextToken();
            if(_ignorableProperties != null && _ignorableProperties.contains(s))
            {
                jsonparser.skipChildren();
            } else
            {
                Object obj;
                if(jsontoken1 == JsonToken.VALUE_NULL)
                    obj = null;
                else
                if(typedeserializer == null)
                    obj = jsondeserializer.deserialize(jsonparser, deserializationcontext);
                else
                    obj = jsondeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
                map.put(s, obj);
            }
            jsontoken = jsonparser.nextToken();
        }
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        KeyDeserializer keydeserializer;
        JsonDeserializer jsondeserializer1;
        TypeDeserializer typedeserializer;
        HashSet hashset1;
label0:
        {
            keydeserializer = _keyDeserializer;
            JsonDeserializer jsondeserializer;
            HashSet hashset;
            AnnotationIntrospector annotationintrospector;
            if(keydeserializer == null)
                keydeserializer = deserializationcontext.findKeyDeserializer(_mapType.getKeyType(), beanproperty);
            else
            if(keydeserializer instanceof ContextualKeyDeserializer)
                keydeserializer = ((ContextualKeyDeserializer)keydeserializer).createContextual(deserializationcontext, beanproperty);
            jsondeserializer = findConvertingContentDeserializer(deserializationcontext, beanproperty, _valueDeserializer);
            if(jsondeserializer == null)
                jsondeserializer1 = deserializationcontext.findContextualValueDeserializer(_mapType.getContentType(), beanproperty);
            else
                jsondeserializer1 = deserializationcontext.handleSecondaryContextualization(jsondeserializer, beanproperty);
            typedeserializer = _valueTypeDeserializer;
            if(typedeserializer != null)
                typedeserializer = typedeserializer.forProperty(beanproperty);
            hashset = _ignorableProperties;
            annotationintrospector = deserializationcontext.getAnnotationIntrospector();
            if(annotationintrospector != null && beanproperty != null)
            {
                String as[] = annotationintrospector.findPropertiesToIgnore(beanproperty.getMember());
                if(as != null)
                {
                    int i;
                    if(hashset == null)
                        hashset1 = new HashSet();
                    else
                        hashset1 = new HashSet(hashset);
                    i = as.length;
                    for(int j = 0; j < i; j++)
                        hashset1.add(as[j]);

                    break label0;
                }
            }
            hashset1 = hashset;
        }
        return withResolved(keydeserializer, typedeserializer, jsondeserializer1, hashset1);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext, (Map)obj);
    }

    public Map deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_propertyBasedCreator != null)
            return _deserializeUsingCreator(jsonparser, deserializationcontext);
        if(_delegateDeserializer != null)
            return (Map)_valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
        if(!_hasDefaultCreator)
            throw deserializationcontext.instantiationException(getMapClass(), "No default constructor found");
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken != JsonToken.START_OBJECT && jsontoken != JsonToken.FIELD_NAME && jsontoken != JsonToken.END_OBJECT)
            if(jsontoken == JsonToken.VALUE_STRING)
                return (Map)_valueInstantiator.createFromString(deserializationcontext, jsonparser.getText());
            else
                throw deserializationcontext.mappingException(getMapClass());
        Map map = (Map)_valueInstantiator.createUsingDefault(deserializationcontext);
        if(_standardStringKey)
        {
            _readAndBindStringMap(jsonparser, deserializationcontext, map);
            return map;
        } else
        {
            _readAndBind(jsonparser, deserializationcontext, map);
            return map;
        }
    }

    public Map deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext, Map map)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken != JsonToken.START_OBJECT && jsontoken != JsonToken.FIELD_NAME)
            throw deserializationcontext.mappingException(getMapClass());
        if(_standardStringKey)
        {
            _readAndBindStringMap(jsonparser, deserializationcontext, map);
            return map;
        } else
        {
            _readAndBind(jsonparser, deserializationcontext, map);
            return map;
        }
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromObject(jsonparser, deserializationcontext);
    }

    public JsonDeserializer getContentDeserializer()
    {
        return _valueDeserializer;
    }

    public JavaType getContentType()
    {
        return _mapType.getContentType();
    }

    public final Class getMapClass()
    {
        return _mapType.getRawClass();
    }

    public JavaType getValueType()
    {
        return _mapType;
    }

    public void resolve(DeserializationContext deserializationcontext)
        throws JsonMappingException
    {
        if(_valueInstantiator.canCreateUsingDelegate())
        {
            JavaType javatype = _valueInstantiator.getDelegateType(deserializationcontext.getConfig());
            if(javatype == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid delegate-creator definition for ").append(_mapType).append(": value instantiator (").append(_valueInstantiator.getClass().getName()).append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'").toString());
            _delegateDeserializer = findDeserializer(deserializationcontext, javatype, null);
        }
        if(_valueInstantiator.canCreateFromObjectWith())
        {
            SettableBeanProperty asettablebeanproperty[] = _valueInstantiator.getFromObjectArguments(deserializationcontext.getConfig());
            _propertyBasedCreator = PropertyBasedCreator.construct(deserializationcontext, _valueInstantiator, asettablebeanproperty);
        }
        _standardStringKey = _isStdKeyDeser(_mapType, _keyDeserializer);
    }

    public void setIgnorableProperties(String as[])
    {
        HashSet hashset;
        if(as == null || as.length == 0)
            hashset = null;
        else
            hashset = ArrayBuilders.arrayToSet(as);
        _ignorableProperties = hashset;
    }

    protected MapDeserializer withResolved(KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer, HashSet hashset)
    {
        if(_keyDeserializer == keydeserializer && _valueDeserializer == jsondeserializer && _valueTypeDeserializer == typedeserializer && _ignorableProperties == hashset)
            return this;
        else
            return new MapDeserializer(this, keydeserializer, jsondeserializer, typedeserializer, hashset);
    }

    protected void wrapAndThrow(Throwable throwable, Object obj)
        throws IOException
    {
        Throwable throwable1;
        for(throwable1 = throwable; (throwable1 instanceof InvocationTargetException) && throwable1.getCause() != null; throwable1 = throwable1.getCause());
        if(throwable1 instanceof Error)
            throw (Error)throwable1;
        if((throwable1 instanceof IOException) && !(throwable1 instanceof JsonMappingException))
            throw (IOException)throwable1;
        else
            throw JsonMappingException.wrapWithPath(throwable1, obj, null);
    }

    private static final long serialVersionUID = 0xd11c9bd2fbfb27d0L;
    protected JsonDeserializer _delegateDeserializer;
    protected final boolean _hasDefaultCreator;
    protected HashSet _ignorableProperties;
    protected final KeyDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected boolean _standardStringKey;
    protected final JsonDeserializer _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;
}
