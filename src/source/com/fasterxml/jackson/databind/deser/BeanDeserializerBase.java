// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.InnerClassProperty;
import com.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.*;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            ContextualDeserializer, ResolvableDeserializer, BeanDeserializerBuilder, ValueInstantiator, 
//            SettableBeanProperty, SettableAnyProperty

public abstract class BeanDeserializerBase extends StdDeserializer
    implements ContextualDeserializer, ResolvableDeserializer, Serializable
{

    protected BeanDeserializerBase(BeanDeserializerBase beandeserializerbase)
    {
        this(beandeserializerbase, beandeserializerbase._ignoreAllUnknown);
    }

    public BeanDeserializerBase(BeanDeserializerBase beandeserializerbase, ObjectIdReader objectidreader)
    {
        super(beandeserializerbase._beanType);
        _classAnnotations = beandeserializerbase._classAnnotations;
        _beanType = beandeserializerbase._beanType;
        _valueInstantiator = beandeserializerbase._valueInstantiator;
        _delegateDeserializer = beandeserializerbase._delegateDeserializer;
        _propertyBasedCreator = beandeserializerbase._propertyBasedCreator;
        _backRefs = beandeserializerbase._backRefs;
        _ignorableProps = beandeserializerbase._ignorableProps;
        _ignoreAllUnknown = beandeserializerbase._ignoreAllUnknown;
        _anySetter = beandeserializerbase._anySetter;
        _injectables = beandeserializerbase._injectables;
        _nonStandardCreation = beandeserializerbase._nonStandardCreation;
        _unwrappedPropertyHandler = beandeserializerbase._unwrappedPropertyHandler;
        _needViewProcesing = beandeserializerbase._needViewProcesing;
        _serializationShape = beandeserializerbase._serializationShape;
        _objectIdReader = objectidreader;
        if(objectidreader == null)
        {
            _beanProperties = beandeserializerbase._beanProperties;
            _vanillaProcessing = beandeserializerbase._vanillaProcessing;
            return;
        } else
        {
            ObjectIdValueProperty objectidvalueproperty = new ObjectIdValueProperty(objectidreader, PropertyMetadata.STD_REQUIRED);
            _beanProperties = beandeserializerbase._beanProperties.withProperty(objectidvalueproperty);
            _vanillaProcessing = false;
            return;
        }
    }

    protected BeanDeserializerBase(BeanDeserializerBase beandeserializerbase, NameTransformer nametransformer)
    {
        super(beandeserializerbase._beanType);
        _classAnnotations = beandeserializerbase._classAnnotations;
        _beanType = beandeserializerbase._beanType;
        _valueInstantiator = beandeserializerbase._valueInstantiator;
        _delegateDeserializer = beandeserializerbase._delegateDeserializer;
        _propertyBasedCreator = beandeserializerbase._propertyBasedCreator;
        _backRefs = beandeserializerbase._backRefs;
        _ignorableProps = beandeserializerbase._ignorableProps;
        boolean flag;
        UnwrappedPropertyHandler unwrappedpropertyhandler;
        if(nametransformer != null || beandeserializerbase._ignoreAllUnknown)
            flag = true;
        else
            flag = false;
        _ignoreAllUnknown = flag;
        _anySetter = beandeserializerbase._anySetter;
        _injectables = beandeserializerbase._injectables;
        _objectIdReader = beandeserializerbase._objectIdReader;
        _nonStandardCreation = beandeserializerbase._nonStandardCreation;
        unwrappedpropertyhandler = beandeserializerbase._unwrappedPropertyHandler;
        if(nametransformer != null)
        {
            if(unwrappedpropertyhandler != null)
                unwrappedpropertyhandler = unwrappedpropertyhandler.renameAll(nametransformer);
            _beanProperties = beandeserializerbase._beanProperties.renameAll(nametransformer);
        } else
        {
            _beanProperties = beandeserializerbase._beanProperties;
        }
        _unwrappedPropertyHandler = unwrappedpropertyhandler;
        _needViewProcesing = beandeserializerbase._needViewProcesing;
        _serializationShape = beandeserializerbase._serializationShape;
        _vanillaProcessing = false;
    }

    public BeanDeserializerBase(BeanDeserializerBase beandeserializerbase, HashSet hashset)
    {
        super(beandeserializerbase._beanType);
        _classAnnotations = beandeserializerbase._classAnnotations;
        _beanType = beandeserializerbase._beanType;
        _valueInstantiator = beandeserializerbase._valueInstantiator;
        _delegateDeserializer = beandeserializerbase._delegateDeserializer;
        _propertyBasedCreator = beandeserializerbase._propertyBasedCreator;
        _backRefs = beandeserializerbase._backRefs;
        _ignorableProps = hashset;
        _ignoreAllUnknown = beandeserializerbase._ignoreAllUnknown;
        _anySetter = beandeserializerbase._anySetter;
        _injectables = beandeserializerbase._injectables;
        _nonStandardCreation = beandeserializerbase._nonStandardCreation;
        _unwrappedPropertyHandler = beandeserializerbase._unwrappedPropertyHandler;
        _needViewProcesing = beandeserializerbase._needViewProcesing;
        _serializationShape = beandeserializerbase._serializationShape;
        _vanillaProcessing = beandeserializerbase._vanillaProcessing;
        _objectIdReader = beandeserializerbase._objectIdReader;
        _beanProperties = beandeserializerbase._beanProperties;
    }

    protected BeanDeserializerBase(BeanDeserializerBase beandeserializerbase, boolean flag)
    {
        super(beandeserializerbase._beanType);
        _classAnnotations = beandeserializerbase._classAnnotations;
        _beanType = beandeserializerbase._beanType;
        _valueInstantiator = beandeserializerbase._valueInstantiator;
        _delegateDeserializer = beandeserializerbase._delegateDeserializer;
        _propertyBasedCreator = beandeserializerbase._propertyBasedCreator;
        _beanProperties = beandeserializerbase._beanProperties;
        _backRefs = beandeserializerbase._backRefs;
        _ignorableProps = beandeserializerbase._ignorableProps;
        _ignoreAllUnknown = flag;
        _anySetter = beandeserializerbase._anySetter;
        _injectables = beandeserializerbase._injectables;
        _objectIdReader = beandeserializerbase._objectIdReader;
        _nonStandardCreation = beandeserializerbase._nonStandardCreation;
        _unwrappedPropertyHandler = beandeserializerbase._unwrappedPropertyHandler;
        _needViewProcesing = beandeserializerbase._needViewProcesing;
        _serializationShape = beandeserializerbase._serializationShape;
        _vanillaProcessing = beandeserializerbase._vanillaProcessing;
    }

    protected BeanDeserializerBase(BeanDeserializerBuilder beandeserializerbuilder, BeanDescription beandescription, BeanPropertyMap beanpropertymap, Map map, HashSet hashset, boolean flag, boolean flag1)
    {
        boolean flag2 = true;
        super(beandescription.getType());
        _classAnnotations = beandescription.getClassInfo().getAnnotations();
        _beanType = beandescription.getType();
        _valueInstantiator = beandeserializerbuilder.getValueInstantiator();
        _beanProperties = beanpropertymap;
        _backRefs = map;
        _ignorableProps = hashset;
        _ignoreAllUnknown = flag;
        _anySetter = beandeserializerbuilder.getAnySetter();
        List list = beandeserializerbuilder.getInjectables();
        ValueInjector avalueinjector[];
        boolean flag3;
        com.fasterxml.jackson.annotation.JsonFormat.Value value;
        com.fasterxml.jackson.annotation.JsonFormat.Shape shape;
        if(list == null || list.isEmpty())
            avalueinjector = null;
        else
            avalueinjector = (ValueInjector[])list.toArray(new ValueInjector[list.size()]);
        _injectables = avalueinjector;
        _objectIdReader = beandeserializerbuilder.getObjectIdReader();
        if(_unwrappedPropertyHandler != null || _valueInstantiator.canCreateUsingDelegate() || _valueInstantiator.canCreateFromObjectWith() || !_valueInstantiator.canCreateUsingDefault())
            flag3 = flag2;
        else
            flag3 = false;
        _nonStandardCreation = flag3;
        value = beandescription.findExpectedFormat(null);
        shape = null;
        if(value != null)
            shape = value.getShape();
        _serializationShape = shape;
        _needViewProcesing = flag1;
        if(_nonStandardCreation || _injectables != null || _needViewProcesing || _objectIdReader != null)
            flag2 = false;
        _vanillaProcessing = flag2;
    }

    private Throwable throwOrReturnThrowable(Throwable throwable, DeserializationContext deserializationcontext)
        throws IOException
    {
        Throwable throwable1;
        for(throwable1 = throwable; (throwable1 instanceof InvocationTargetException) && throwable1.getCause() != null; throwable1 = throwable1.getCause());
        if(throwable1 instanceof Error)
            throw (Error)throwable1;
        boolean flag;
        if(deserializationcontext == null || deserializationcontext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))
            flag = true;
        else
            flag = false;
        if(throwable1 instanceof IOException)
        {
            if(!flag || !(throwable1 instanceof JsonProcessingException))
                throw (IOException)throwable1;
        } else
        if(!flag && (throwable1 instanceof RuntimeException))
            throw (RuntimeException)throwable1;
        return throwable1;
    }

    protected Object _convertObjectId(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, JsonDeserializer jsondeserializer)
        throws IOException, JsonProcessingException
    {
        TokenBuffer tokenbuffer = new TokenBuffer(jsonparser);
        JsonParser jsonparser1;
        if(obj instanceof String)
            tokenbuffer.writeString((String)obj);
        else
        if(obj instanceof Long)
            tokenbuffer.writeNumber(((Long)obj).longValue());
        else
        if(obj instanceof Integer)
            tokenbuffer.writeNumber(((Integer)obj).intValue());
        else
            tokenbuffer.writeObject(obj);
        jsonparser1 = tokenbuffer.asParser();
        jsonparser1.nextToken();
        return jsondeserializer.deserialize(jsonparser1, deserializationcontext);
    }

    protected abstract Object _deserializeUsingPropertyBased(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException;

    protected JsonDeserializer _findSubclassDeserializer(DeserializationContext deserializationcontext, Object obj, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        this;
        JVM INSTR monitorenter ;
        if(_subDeserializers != null)
            break MISSING_BLOCK_LABEL_22;
        JsonDeserializer jsondeserializer = null;
_L1:
        this;
        JVM INSTR monitorexit ;
        if(jsondeserializer != null)
            return jsondeserializer;
        break MISSING_BLOCK_LABEL_55;
        jsondeserializer = (JsonDeserializer)_subDeserializers.get(new ClassKey(obj.getClass()));
          goto _L1
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        JsonDeserializer jsondeserializer1;
        jsondeserializer1 = deserializationcontext.findRootValueDeserializer(deserializationcontext.constructType(obj.getClass()));
        if(jsondeserializer1 == null)
            break MISSING_BLOCK_LABEL_127;
        this;
        JVM INSTR monitorenter ;
        if(_subDeserializers == null)
            _subDeserializers = new HashMap();
        _subDeserializers.put(new ClassKey(obj.getClass()), jsondeserializer1);
        this;
        JVM INSTR monitorexit ;
        return jsondeserializer1;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
        return jsondeserializer1;
    }

    protected Object _handleTypedObjectId(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, Object obj1)
        throws IOException, JsonProcessingException
    {
        JsonDeserializer jsondeserializer = _objectIdReader.getDeserializer();
        SettableBeanProperty settablebeanproperty;
        if(jsondeserializer.handledType() != obj1.getClass())
            obj1 = _convertObjectId(jsonparser, deserializationcontext, obj1, jsondeserializer);
        deserializationcontext.findObjectId(obj1, _objectIdReader.generator).bindItem(obj);
        settablebeanproperty = _objectIdReader.idProperty;
        if(settablebeanproperty != null)
            obj = settablebeanproperty.setAndReturn(obj, obj1);
        return obj;
    }

    protected SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationContext deserializationcontext, SettableBeanProperty settablebeanproperty)
    {
        JsonDeserializer jsondeserializer = settablebeanproperty.getValueDeserializer();
        if(!(jsondeserializer instanceof BeanDeserializerBase) || ((BeanDeserializerBase)jsondeserializer).getValueInstantiator().canCreateUsingDefault()) goto _L2; else goto _L1
_L1:
        Class class1;
        Class class2;
        class1 = settablebeanproperty.getType().getRawClass();
        class2 = ClassUtil.getOuterClass(class1);
        if(class2 == null || class2 != _beanType.getRawClass()) goto _L2; else goto _L3
_L3:
        Constructor aconstructor[];
        int i;
        int j;
        aconstructor = class1.getConstructors();
        i = aconstructor.length;
        j = 0;
_L8:
        if(j >= i) goto _L2; else goto _L4
_L4:
        Constructor constructor;
        Class aclass[];
        constructor = aconstructor[j];
        aclass = constructor.getParameterTypes();
        if(aclass.length != 1 || aclass[0] != class2) goto _L6; else goto _L5
_L5:
        if(deserializationcontext.getConfig().canOverrideAccessModifiers())
            ClassUtil.checkAndFixAccess(constructor);
        settablebeanproperty = new InnerClassProperty(settablebeanproperty, constructor);
_L2:
        return settablebeanproperty;
_L6:
        j++;
        if(true) goto _L8; else goto _L7
_L7:
    }

    protected SettableBeanProperty _resolveManagedReferenceProperty(DeserializationContext deserializationcontext, SettableBeanProperty settablebeanproperty)
    {
        String s = settablebeanproperty.getManagedReferenceName();
        if(s == null)
            return settablebeanproperty;
        SettableBeanProperty settablebeanproperty1 = settablebeanproperty.getValueDeserializer().findBackReference(s);
        if(settablebeanproperty1 == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Can not handle managed/back reference '").append(s).append("': no back reference property found from type ").append(settablebeanproperty.getType()).toString());
        JavaType javatype = _beanType;
        JavaType javatype1 = settablebeanproperty1.getType();
        boolean flag = settablebeanproperty.getType().isContainerType();
        if(!javatype1.getRawClass().isAssignableFrom(javatype.getRawClass()))
            throw new IllegalArgumentException((new StringBuilder()).append("Can not handle managed/back reference '").append(s).append("': back reference type (").append(javatype1.getRawClass().getName()).append(") not compatible with managed type (").append(javatype.getRawClass().getName()).append(")").toString());
        else
            return new ManagedReferenceProperty(settablebeanproperty, s, settablebeanproperty1, _classAnnotations, flag);
    }

    protected SettableBeanProperty _resolveUnwrappedProperty(DeserializationContext deserializationcontext, SettableBeanProperty settablebeanproperty)
    {
        com.fasterxml.jackson.databind.introspect.AnnotatedMember annotatedmember = settablebeanproperty.getMember();
        if(annotatedmember != null)
        {
            NameTransformer nametransformer = deserializationcontext.getAnnotationIntrospector().findUnwrappingNameTransformer(annotatedmember);
            if(nametransformer != null)
            {
                JsonDeserializer jsondeserializer = settablebeanproperty.getValueDeserializer();
                JsonDeserializer jsondeserializer1 = jsondeserializer.unwrappingDeserializer(nametransformer);
                if(jsondeserializer1 != jsondeserializer && jsondeserializer1 != null)
                    return settablebeanproperty.withValueDeserializer(jsondeserializer1);
            }
        }
        return null;
    }

    protected abstract BeanDeserializerBase asArrayDeserializer();

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        ObjectIdReader objectidreader = _objectIdReader;
        AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
        Object obj;
        if(beanproperty == null || annotationintrospector == null)
            obj = null;
        else
            obj = beanproperty.getMember();
        ObjectIdReader objectidreader1;
        String as[];
        if(beanproperty != null && annotationintrospector != null)
        {
            String as1[] = annotationintrospector.findPropertiesToIgnore(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)));
            ObjectIdInfo objectidinfo = annotationintrospector.findObjectIdInfo(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)));
            BeanDeserializerBase beandeserializerbase;
            com.fasterxml.jackson.annotation.JsonFormat.Shape shape;
            if(objectidinfo != null)
            {
                ObjectIdInfo objectidinfo1 = annotationintrospector.findObjectReferenceInfo(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)), objectidinfo);
                Class class1 = objectidinfo1.getGeneratorType();
                com.fasterxml.jackson.annotation.JsonFormat.Value value;
                Object obj1;
                JavaType javatype2;
                SettableBeanProperty settablebeanproperty;
                JsonDeserializer jsondeserializer;
                if(class1 == com/fasterxml/jackson/annotation/ObjectIdGenerators$PropertyGenerator)
                {
                    PropertyName propertyname = objectidinfo1.getPropertyName();
                    settablebeanproperty = findProperty(propertyname);
                    if(settablebeanproperty == null)
                        throw new IllegalArgumentException((new StringBuilder()).append("Invalid Object Id definition for ").append(handledType().getName()).append(": can not find property with name '").append(propertyname).append("'").toString());
                    javatype2 = settablebeanproperty.getType();
                    obj1 = new PropertyBasedObjectIdGenerator(objectidinfo1.getScope());
                } else
                {
                    JavaType javatype = deserializationcontext.constructType(class1);
                    JavaType javatype1 = deserializationcontext.getTypeFactory().findTypeParameters(javatype, com/fasterxml/jackson/annotation/ObjectIdGenerator)[0];
                    obj1 = deserializationcontext.objectIdGeneratorInstance(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)), objectidinfo1);
                    javatype2 = javatype1;
                    settablebeanproperty = null;
                }
                jsondeserializer = deserializationcontext.findRootValueDeserializer(javatype2);
                objectidreader1 = ObjectIdReader.construct(javatype2, objectidinfo1.getPropertyName(), ((ObjectIdGenerator) (obj1)), jsondeserializer, settablebeanproperty);
                as = as1;
            } else
            {
                objectidreader1 = objectidreader;
                as = as1;
            }
        } else
        {
            objectidreader1 = objectidreader;
            as = null;
        }
        if(objectidreader1 != null && objectidreader1 != _objectIdReader)
            beandeserializerbase = withObjectIdReader(objectidreader1);
        else
            beandeserializerbase = this;
        if(as != null && as.length != 0)
            beandeserializerbase = beandeserializerbase.withIgnorableProperties(ArrayBuilders.setAndArray(beandeserializerbase._ignorableProps, as));
        if(obj == null) goto _L2; else goto _L1
_L1:
        value = annotationintrospector.findFormat(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)));
        if(value == null) goto _L2; else goto _L3
_L3:
        shape = value.getShape();
_L5:
        if(shape == null)
            shape = _serializationShape;
        if(shape == com.fasterxml.jackson.annotation.JsonFormat.Shape.ARRAY)
            return beandeserializerbase.asArrayDeserializer();
        else
            return beandeserializerbase;
_L2:
        shape = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public Iterator creatorProperties()
    {
        if(_propertyBasedCreator == null)
            return Collections.emptyList().iterator();
        else
            return _propertyBasedCreator.properties().iterator();
    }

    public Object deserializeFromArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_delegateDeserializer == null)
            break MISSING_BLOCK_LABEL_50;
        Object obj;
        obj = _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
        if(_injectables != null)
            injectValues(deserializationcontext, obj);
        return obj;
        Exception exception;
        exception;
        wrapInstantiationProblem(exception, deserializationcontext);
        throw deserializationcontext.mappingException(getBeanClass());
    }

    public Object deserializeFromBoolean(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_delegateDeserializer != null && !_valueInstantiator.canCreateFromBoolean())
        {
            Object obj = _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
            if(_injectables != null)
                injectValues(deserializationcontext, obj);
            return obj;
        }
        boolean flag;
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_TRUE)
            flag = true;
        else
            flag = false;
        return _valueInstantiator.createFromBoolean(deserializationcontext, flag);
    }

    public Object deserializeFromDouble(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        static class _cls1
        {

            static final int $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[];

            static 
            {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType = new int[com.fasterxml.jackson.core.JsonParser.NumberType.values().length];
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.INT.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.LONG.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.FLOAT.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[com.fasterxml.jackson.core.JsonParser.NumberType.DOUBLE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.fasterxml.jackson.core.JsonParser.NumberType[jsonparser.getNumberType().ordinal()];
        JVM INSTR tableswitch 3 4: default 32
    //                   3 59
    //                   4 59;
           goto _L1 _L2 _L2
_L1:
        if(_delegateDeserializer == null) goto _L4; else goto _L3
_L3:
        Object obj = _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
_L5:
        return obj;
_L2:
        if(_delegateDeserializer != null && !_valueInstantiator.canCreateFromDouble())
        {
            obj = _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
            if(_injectables != null)
            {
                injectValues(deserializationcontext, obj);
                return obj;
            }
        } else
        {
            return _valueInstantiator.createFromDouble(deserializationcontext, jsonparser.getDoubleValue());
        }
        if(true) goto _L5; else goto _L4
_L4:
        throw deserializationcontext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON floating-point number");
    }

    public Object deserializeFromNumber(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(_objectIdReader == null) goto _L2; else goto _L1
_L1:
        Object obj = deserializeFromObjectId(jsonparser, deserializationcontext);
_L4:
        return obj;
_L2:
        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonParser.NumberType[jsonparser.getNumberType().ordinal()])
        {
        default:
            if(_delegateDeserializer != null)
            {
                obj = _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
                if(_injectables != null)
                {
                    injectValues(deserializationcontext, obj);
                    return obj;
                }
            } else
            {
                throw deserializationcontext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON integer number");
            }
            break;

        case 2: // '\002'
            break; /* Loop/switch isn't completed */

        case 1: // '\001'
            if(_delegateDeserializer != null && !_valueInstantiator.canCreateFromInt())
            {
                obj = _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
                if(_injectables != null)
                {
                    injectValues(deserializationcontext, obj);
                    return obj;
                }
            } else
            {
                return _valueInstantiator.createFromInt(deserializationcontext, jsonparser.getIntValue());
            }
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(_delegateDeserializer != null && !_valueInstantiator.canCreateFromInt())
        {
            obj = _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
            if(_injectables != null)
            {
                injectValues(deserializationcontext, obj);
                return obj;
            }
        } else
        {
            return _valueInstantiator.createFromLong(deserializationcontext, jsonparser.getLongValue());
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public abstract Object deserializeFromObject(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException;

    protected Object deserializeFromObjectId(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj = _objectIdReader.readObjectReference(jsonparser, deserializationcontext);
        Object obj1 = deserializationcontext.findObjectId(obj, _objectIdReader.generator).item;
        if(obj1 == null)
            throw new IllegalStateException((new StringBuilder()).append("Could not resolve Object Id [").append(obj).append("] (for ").append(_beanType).append(") -- unresolved forward-reference?").toString());
        else
            return obj1;
    }

    protected Object deserializeFromObjectUsingNonDefault(JsonParser jsonparser, DeserializationContext deserializationcontext)
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

    public Object deserializeFromString(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj;
        if(_objectIdReader != null)
            obj = deserializeFromObjectId(jsonparser, deserializationcontext);
        else
        if(_delegateDeserializer != null && !_valueInstantiator.canCreateFromString())
        {
            obj = _valueInstantiator.createUsingDelegate(deserializationcontext, _delegateDeserializer.deserialize(jsonparser, deserializationcontext));
            if(_injectables != null)
            {
                injectValues(deserializationcontext, obj);
                return obj;
            }
        } else
        {
            return _valueInstantiator.createFromString(deserializationcontext, jsonparser.getText());
        }
        return obj;
    }

    protected Object deserializeWithObjectId(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        String s = _objectIdReader.propertyName.getSimpleName();
        if(s.equals(jsonparser.getCurrentName()) || jsonparser.canReadObjectId())
            return deserializeFromObject(jsonparser, deserializationcontext);
        TokenBuffer tokenbuffer = new TokenBuffer(jsonparser);
        TokenBuffer tokenbuffer1 = null;
        while(jsonparser.getCurrentToken() != JsonToken.END_OBJECT) 
        {
            String s1 = jsonparser.getCurrentName();
            if(tokenbuffer1 == null)
            {
                if(s.equals(s1))
                {
                    tokenbuffer1 = new TokenBuffer(jsonparser);
                    tokenbuffer1.writeFieldName(s1);
                    jsonparser.nextToken();
                    tokenbuffer1.copyCurrentStructure(jsonparser);
                    tokenbuffer1.append(tokenbuffer);
                    tokenbuffer = null;
                } else
                {
                    tokenbuffer.writeFieldName(s1);
                    jsonparser.nextToken();
                    tokenbuffer.copyCurrentStructure(jsonparser);
                }
            } else
            {
                tokenbuffer1.writeFieldName(s1);
                jsonparser.nextToken();
                tokenbuffer1.copyCurrentStructure(jsonparser);
            }
            jsonparser.nextToken();
        }
        JsonParser jsonparser1;
        if(tokenbuffer1 != null)
            tokenbuffer = tokenbuffer1;
        tokenbuffer.writeEndObject();
        jsonparser1 = tokenbuffer.asParser();
        jsonparser1.nextToken();
        return deserializeFromObject(jsonparser1, deserializationcontext);
    }

    public final Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        if(_objectIdReader != null)
        {
            if(jsonparser.canReadObjectId())
            {
                Object obj = jsonparser.getObjectId();
                if(obj != null)
                    return _handleTypedObjectId(jsonparser, deserializationcontext, typedeserializer.deserializeTypedFromObject(jsonparser, deserializationcontext), obj);
            }
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if(jsontoken != null && jsontoken.isScalarValue())
                return deserializeFromObjectId(jsonparser, deserializationcontext);
        }
        return typedeserializer.deserializeTypedFromObject(jsonparser, deserializationcontext);
    }

    public SettableBeanProperty findBackReference(String s)
    {
        if(_backRefs == null)
            return null;
        else
            return (SettableBeanProperty)_backRefs.get(s);
    }

    protected JsonDeserializer findConvertingDeserializer(DeserializationContext deserializationcontext, SettableBeanProperty settablebeanproperty)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
        if(annotationintrospector != null)
        {
            Object obj = annotationintrospector.findDeserializationConverter(settablebeanproperty.getMember());
            if(obj != null)
            {
                Converter converter = deserializationcontext.converterInstance(settablebeanproperty.getMember(), obj);
                JavaType javatype = converter.getInputType(deserializationcontext.getTypeFactory());
                return new StdDelegatingDeserializer(converter, javatype, deserializationcontext.findContextualValueDeserializer(javatype, settablebeanproperty));
            }
        }
        return null;
    }

    public SettableBeanProperty findProperty(int i)
    {
        SettableBeanProperty settablebeanproperty;
        if(_beanProperties == null)
            settablebeanproperty = null;
        else
            settablebeanproperty = _beanProperties.find(i);
        if(settablebeanproperty == null && _propertyBasedCreator != null)
            settablebeanproperty = _propertyBasedCreator.findCreatorProperty(i);
        return settablebeanproperty;
    }

    public SettableBeanProperty findProperty(PropertyName propertyname)
    {
        return findProperty(propertyname.getSimpleName());
    }

    public SettableBeanProperty findProperty(String s)
    {
        SettableBeanProperty settablebeanproperty;
        if(_beanProperties == null)
            settablebeanproperty = null;
        else
            settablebeanproperty = _beanProperties.find(s);
        if(settablebeanproperty == null && _propertyBasedCreator != null)
            settablebeanproperty = _propertyBasedCreator.findCreatorProperty(s);
        return settablebeanproperty;
    }

    public final Class getBeanClass()
    {
        return _beanType.getRawClass();
    }

    public Collection getKnownPropertyNames()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = _beanProperties.iterator(); iterator.hasNext(); arraylist.add(((SettableBeanProperty)iterator.next()).getName()));
        return arraylist;
    }

    public ObjectIdReader getObjectIdReader()
    {
        return _objectIdReader;
    }

    public int getPropertyCount()
    {
        return _beanProperties.size();
    }

    public ValueInstantiator getValueInstantiator()
    {
        return _valueInstantiator;
    }

    public JavaType getValueType()
    {
        return _beanType;
    }

    protected void handleIgnoredProperty(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        if(deserializationcontext.isEnabled(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES))
        {
            throw IgnoredPropertyException.from(jsonparser, obj, s, getKnownPropertyNames());
        } else
        {
            jsonparser.skipChildren();
            return;
        }
    }

    protected Object handlePolymorphic(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        JsonDeserializer jsondeserializer = _findSubclassDeserializer(deserializationcontext, obj, tokenbuffer);
        if(jsondeserializer != null)
        {
            Object obj1;
            if(tokenbuffer != null)
            {
                tokenbuffer.writeEndObject();
                JsonParser jsonparser1 = tokenbuffer.asParser();
                jsonparser1.nextToken();
                obj1 = jsondeserializer.deserialize(jsonparser1, deserializationcontext, obj);
            } else
            {
                obj1 = obj;
            }
            if(jsonparser != null)
                obj1 = jsondeserializer.deserialize(jsonparser, deserializationcontext, obj1);
        } else
        {
            if(tokenbuffer != null)
                obj1 = handleUnknownProperties(deserializationcontext, obj, tokenbuffer);
            else
                obj1 = obj;
            if(jsonparser != null)
                return deserialize(jsonparser, deserializationcontext, obj1);
        }
        return obj1;
    }

    protected Object handleUnknownProperties(DeserializationContext deserializationcontext, Object obj, TokenBuffer tokenbuffer)
        throws IOException, JsonProcessingException
    {
        tokenbuffer.writeEndObject();
        String s;
        for(JsonParser jsonparser = tokenbuffer.asParser(); jsonparser.nextToken() != JsonToken.END_OBJECT; handleUnknownProperty(jsonparser, deserializationcontext, obj, s))
        {
            s = jsonparser.getCurrentName();
            jsonparser.nextToken();
        }

        return obj;
    }

    protected void handleUnknownProperty(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        if(_ignoreAllUnknown)
        {
            jsonparser.skipChildren();
            return;
        }
        if(_ignorableProps != null && _ignorableProps.contains(s))
            handleIgnoredProperty(jsonparser, deserializationcontext, obj, s);
        super.handleUnknownProperty(jsonparser, deserializationcontext, obj, s);
    }

    protected void handleUnknownVanilla(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj, String s)
        throws IOException, JsonProcessingException
    {
        if(_ignorableProps != null && _ignorableProps.contains(s))
        {
            handleIgnoredProperty(jsonparser, deserializationcontext, obj, s);
            return;
        }
        if(_anySetter != null)
        {
            try
            {
                _anySetter.deserializeAndSet(jsonparser, deserializationcontext, obj, s);
                return;
            }
            catch(Exception exception)
            {
                wrapAndThrow(exception, obj, s, deserializationcontext);
            }
            return;
        } else
        {
            handleUnknownProperty(jsonparser, deserializationcontext, obj, s);
            return;
        }
    }

    public Class handledType()
    {
        return _beanType.getRawClass();
    }

    public boolean hasProperty(String s)
    {
        return _beanProperties.find(s) != null;
    }

    public boolean hasViews()
    {
        return _needViewProcesing;
    }

    protected void injectValues(DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException
    {
        ValueInjector avalueinjector[] = _injectables;
        int i = avalueinjector.length;
        for(int j = 0; j < i; j++)
            avalueinjector[j].inject(deserializationcontext, obj);

    }

    public boolean isCachable()
    {
        return true;
    }

    public Iterator properties()
    {
        if(_beanProperties == null)
            throw new IllegalStateException("Can only call after BeanDeserializer has been resolved");
        else
            return _beanProperties.iterator();
    }

    public void replaceProperty(SettableBeanProperty settablebeanproperty, SettableBeanProperty settablebeanproperty1)
    {
        _beanProperties.replace(settablebeanproperty1);
    }

    public void resolve(DeserializationContext deserializationcontext)
        throws JsonMappingException
    {
        Iterator iterator;
        UnwrappedPropertyHandler unwrappedpropertyhandler;
        com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder builder1;
        com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder builder;
        if(_valueInstantiator.canCreateFromObjectWith())
        {
            SettableBeanProperty asettablebeanproperty[] = _valueInstantiator.getFromObjectArguments(deserializationcontext.getConfig());
            _propertyBasedCreator = PropertyBasedCreator.construct(deserializationcontext, _valueInstantiator, asettablebeanproperty);
            Iterator iterator1 = _propertyBasedCreator.properties().iterator();
            builder = null;
            do
            {
                if(!iterator1.hasNext())
                    break;
                SettableBeanProperty settablebeanproperty5 = (SettableBeanProperty)iterator1.next();
                if(settablebeanproperty5.hasValueTypeDeserializer())
                {
                    TypeDeserializer typedeserializer1 = settablebeanproperty5.getValueTypeDeserializer();
                    if(typedeserializer1.getTypeInclusion() == com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY)
                    {
                        if(builder == null)
                            builder = new com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder();
                        builder.addExternal(settablebeanproperty5, typedeserializer1);
                    }
                }
            } while(true);
        } else
        {
            builder = null;
        }
        iterator = _beanProperties.iterator();
        unwrappedpropertyhandler = null;
        builder1 = builder;
_L3:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        SettableBeanProperty settablebeanproperty = (SettableBeanProperty)iterator.next();
        SettableBeanProperty settablebeanproperty1;
        SettableBeanProperty settablebeanproperty2;
        SettableBeanProperty settablebeanproperty3;
        if(!settablebeanproperty.hasValueDeserializer())
        {
            JsonDeserializer jsondeserializer2 = findConvertingDeserializer(deserializationcontext, settablebeanproperty);
            if(jsondeserializer2 == null)
                jsondeserializer2 = findDeserializer(deserializationcontext, settablebeanproperty.getType(), settablebeanproperty);
            settablebeanproperty1 = settablebeanproperty.withValueDeserializer(jsondeserializer2);
        } else
        {
            JsonDeserializer jsondeserializer = settablebeanproperty.getValueDeserializer();
            JsonDeserializer jsondeserializer1 = deserializationcontext.handlePrimaryContextualization(jsondeserializer, settablebeanproperty);
            boolean flag;
            JavaType javatype;
            com.fasterxml.jackson.databind.introspect.AnnotatedWithParams annotatedwithparams;
            SettableBeanProperty settablebeanproperty4;
            TypeDeserializer typedeserializer;
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder builder2;
            if(jsondeserializer1 != jsondeserializer)
                settablebeanproperty1 = settablebeanproperty.withValueDeserializer(jsondeserializer1);
            else
                settablebeanproperty1 = settablebeanproperty;
        }
        settablebeanproperty2 = _resolveManagedReferenceProperty(deserializationcontext, settablebeanproperty1);
        settablebeanproperty3 = _resolveUnwrappedProperty(deserializationcontext, settablebeanproperty2);
        if(settablebeanproperty3 != null)
        {
            UnwrappedPropertyHandler unwrappedpropertyhandler1;
            if(unwrappedpropertyhandler == null)
                unwrappedpropertyhandler1 = new UnwrappedPropertyHandler();
            else
                unwrappedpropertyhandler1 = unwrappedpropertyhandler;
            unwrappedpropertyhandler1.addProperty(settablebeanproperty3);
            unwrappedpropertyhandler = unwrappedpropertyhandler1;
        } else
        {
            settablebeanproperty4 = _resolveInnerClassValuedProperty(deserializationcontext, settablebeanproperty2);
            if(settablebeanproperty4 != settablebeanproperty)
                _beanProperties.replace(settablebeanproperty4);
            if(settablebeanproperty4.hasValueTypeDeserializer())
            {
                typedeserializer = settablebeanproperty4.getValueTypeDeserializer();
                if(typedeserializer.getTypeInclusion() == com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY)
                {
                    if(builder1 == null)
                        builder2 = new com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder();
                    else
                        builder2 = builder1;
                    builder2.addExternal(settablebeanproperty4, typedeserializer);
                    _beanProperties.remove(settablebeanproperty4);
                    builder1 = builder2;
                }
            }
        }
        if(true) goto _L3; else goto _L2
_L2:
        if(_anySetter != null && !_anySetter.hasValueDeserializer())
            _anySetter = _anySetter.withValueDeserializer(findDeserializer(deserializationcontext, _anySetter.getType(), _anySetter.getProperty()));
        if(_valueInstantiator.canCreateUsingDelegate())
        {
            javatype = _valueInstantiator.getDelegateType(deserializationcontext.getConfig());
            if(javatype == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid delegate-creator definition for ").append(_beanType).append(": value instantiator (").append(_valueInstantiator.getClass().getName()).append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'").toString());
            annotatedwithparams = _valueInstantiator.getDelegateCreator();
            _delegateDeserializer = findDeserializer(deserializationcontext, javatype, new com.fasterxml.jackson.databind.BeanProperty.Std(TEMP_PROPERTY_NAME, javatype, null, _classAnnotations, annotatedwithparams, PropertyMetadata.STD_OPTIONAL));
        }
        if(builder1 != null)
        {
            _externalTypeIdHandler = builder1.build();
            _nonStandardCreation = true;
        }
        _unwrappedPropertyHandler = unwrappedpropertyhandler;
        if(unwrappedpropertyhandler != null)
            _nonStandardCreation = true;
        if(_vanillaProcessing && !_nonStandardCreation)
            flag = true;
        else
            flag = false;
        _vanillaProcessing = flag;
        return;
    }

    public abstract JsonDeserializer unwrappingDeserializer(NameTransformer nametransformer);

    public abstract BeanDeserializerBase withIgnorableProperties(HashSet hashset);

    public abstract BeanDeserializerBase withObjectIdReader(ObjectIdReader objectidreader);

    public void wrapAndThrow(Throwable throwable, Object obj, int i, DeserializationContext deserializationcontext)
        throws IOException
    {
        throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(throwable, deserializationcontext), obj, i);
    }

    public void wrapAndThrow(Throwable throwable, Object obj, String s, DeserializationContext deserializationcontext)
        throws IOException
    {
        throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(throwable, deserializationcontext), obj, s);
    }

    protected void wrapInstantiationProblem(Throwable throwable, DeserializationContext deserializationcontext)
        throws IOException
    {
        Throwable throwable1;
        for(throwable1 = throwable; (throwable1 instanceof InvocationTargetException) && throwable1.getCause() != null; throwable1 = throwable1.getCause());
        if(throwable1 instanceof Error)
            throw (Error)throwable1;
        boolean flag;
        if(deserializationcontext == null || deserializationcontext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))
            flag = true;
        else
            flag = false;
        if(throwable1 instanceof IOException)
            throw (IOException)throwable1;
        if(!flag && (throwable1 instanceof RuntimeException))
            throw (RuntimeException)throwable1;
        else
            throw deserializationcontext.instantiationException(_beanType.getRawClass(), throwable1);
    }

    protected static final PropertyName TEMP_PROPERTY_NAME = new PropertyName("#temporary-name");
    private static final long serialVersionUID = 0x291476536acb2fd2L;
    protected SettableAnyProperty _anySetter;
    protected final Map _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    private final transient Annotations _classAnnotations;
    protected JsonDeserializer _delegateDeserializer;
    protected ExternalTypeHandler _externalTypeIdHandler;
    protected final HashSet _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final ValueInjector _injectables[];
    protected final boolean _needViewProcesing;
    protected boolean _nonStandardCreation;
    protected final ObjectIdReader _objectIdReader;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected final com.fasterxml.jackson.annotation.JsonFormat.Shape _serializationShape;
    protected transient HashMap _subDeserializers;
    protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
    protected final ValueInstantiator _valueInstantiator;
    protected boolean _vanillaProcessing;

}
