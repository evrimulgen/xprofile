// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JavaTypeDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jsontype.*;
import com.fasterxml.jackson.databind.type.*;
import com.fasterxml.jackson.databind.util.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.*;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            DeserializerFactory, CreatorProperty, Deserializers, ValueInstantiator, 
//            BeanDeserializerModifier, AbstractDeserializer, KeyDeserializers, ValueInstantiators

public abstract class BasicDeserializerFactory extends DeserializerFactory
    implements Serializable
{

    protected BasicDeserializerFactory(DeserializerFactoryConfig deserializerfactoryconfig)
    {
        _factoryConfig = deserializerfactoryconfig;
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext deserializationcontext, JavaType javatype)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        BeanDescription beandescription = deserializationconfig.introspect(javatype);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationcontext, beandescription.getClassInfo());
        if(jsondeserializer != null)
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(deserializationconfig, javatype, jsondeserializer);
        Class class1 = javatype.getRawClass();
        if(_findCustomEnumDeserializer(class1, deserializationconfig, beandescription) != null)
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(deserializationconfig, javatype, jsondeserializer);
        EnumResolver enumresolver = constructEnumResolver(class1, deserializationconfig, beandescription.findJsonValueMethod());
        for(Iterator iterator = beandescription.getFactoryMethods().iterator(); iterator.hasNext();)
        {
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator.next();
            if(deserializationconfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedmethod))
                if(annotatedmethod.getParameterCount() == 1 && annotatedmethod.getRawReturnType().isAssignableFrom(class1))
                {
                    if(annotatedmethod.getGenericParameterType(0) != java/lang/String)
                        throw new IllegalArgumentException((new StringBuilder()).append("Parameter #0 type for factory method (").append(annotatedmethod).append(") not suitable, must be java.lang.String").toString());
                    if(deserializationconfig.canOverrideAccessModifiers())
                        ClassUtil.checkAndFixAccess(annotatedmethod.getMember());
                    return StdKeyDeserializers.constructEnumKeyDeserializer(enumresolver, annotatedmethod);
                } else
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Unsuitable method (").append(annotatedmethod).append(") decorated with @JsonCreator (for Enum type ").append(class1.getName()).append(")").toString());
                }
        }

        return StdKeyDeserializers.constructEnumKeyDeserializer(enumresolver);
    }

    private ValueInstantiator _findStdValueInstantiator(DeserializationConfig deserializationconfig, BeanDescription beandescription)
        throws JsonMappingException
    {
        if(beandescription.getBeanClass() == com/fasterxml/jackson/core/JsonLocation)
            return JsonLocationInstantiator.instance;
        else
            return null;
    }

    private JavaType _mapAbstractType2(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
label0:
        {
            Class class1 = javatype.getRawClass();
            if(!_factoryConfig.hasAbstractTypeResolvers())
                break label0;
            Iterator iterator = _factoryConfig.abstractTypeResolvers().iterator();
            JavaType javatype1;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                javatype1 = ((AbstractTypeResolver)iterator.next()).findTypeMapping(deserializationconfig, javatype);
            } while(javatype1 == null || javatype1.getRawClass() == class1);
            return javatype1;
        }
        return null;
    }

    protected void _addDeserializerConstructors(DeserializationContext deserializationcontext, BeanDescription beandescription, VisibilityChecker visibilitychecker, AnnotationIntrospector annotationintrospector, CreatorCollector creatorcollector)
        throws JsonMappingException
    {
        PropertyName apropertyname[];
        AnnotatedConstructor annotatedconstructor1;
        Iterator iterator;
        AnnotatedConstructor annotatedconstructor = beandescription.findDefaultConstructor();
        if(annotatedconstructor != null && (!creatorcollector.hasDefaultCreator() || annotationintrospector.hasCreatorAnnotation(annotatedconstructor)))
            creatorcollector.setDefaultCreator(annotatedconstructor);
        apropertyname = null;
        annotatedconstructor1 = null;
        iterator = beandescription.findProperties().iterator();
_L3:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        BeanPropertyDefinition beanpropertydefinition = (BeanPropertyDefinition)iterator.next();
        if(beanpropertydefinition.getConstructorParameter() != null)
        {
            AnnotatedParameter annotatedparameter2 = beanpropertydefinition.getConstructorParameter();
            com.fasterxml.jackson.databind.introspect.AnnotatedWithParams annotatedwithparams = annotatedparameter2.getOwner();
            if(annotatedwithparams instanceof AnnotatedConstructor)
            {
                Iterator iterator1;
                AnnotatedConstructor annotatedconstructor2;
                int i;
                boolean flag;
                boolean flag1;
                AnnotatedParameter annotatedparameter;
                int j;
                int k;
                CreatorProperty acreatorproperty[];
                int l;
                AnnotatedParameter annotatedparameter1;
                AnnotatedConstructor annotatedconstructor3;
                PropertyName propertyname;
                Object obj;
                PropertyName propertyname1;
                PropertyName propertyname2;
                AnnotatedConstructor annotatedconstructor4;
                PropertyName apropertyname1[];
                if(annotatedconstructor1 == null)
                {
                    annotatedconstructor4 = (AnnotatedConstructor)annotatedwithparams;
                    apropertyname1 = new PropertyName[annotatedconstructor4.getParameterCount()];
                } else
                {
                    annotatedconstructor4 = annotatedconstructor1;
                    apropertyname1 = apropertyname;
                }
                apropertyname1[annotatedparameter2.getIndex()] = beanpropertydefinition.getFullName();
                annotatedconstructor1 = annotatedconstructor4;
                apropertyname = apropertyname1;
            }
        }
        if(true) goto _L3; else goto _L2
_L2:
        iterator1 = beandescription.getConstructors().iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            annotatedconstructor2 = (AnnotatedConstructor)iterator1.next();
            i = annotatedconstructor2.getParameterCount();
            if(annotationintrospector.hasCreatorAnnotation(annotatedconstructor2) || annotatedconstructor2 == annotatedconstructor1)
                flag = true;
            else
                flag = false;
            flag1 = visibilitychecker.isCreatorVisible(annotatedconstructor2);
            if(i == 1)
            {
                if(annotatedconstructor2 == annotatedconstructor1)
                    propertyname2 = apropertyname[0];
                else
                    propertyname2 = null;
                _handleSingleArgumentConstructor(deserializationcontext, beandescription, visibilitychecker, annotationintrospector, creatorcollector, annotatedconstructor2, flag, flag1, propertyname2);
                continue;
            }
            if(!flag && !flag1)
                continue;
            annotatedparameter = null;
            j = 0;
            k = 0;
            acreatorproperty = new CreatorProperty[i];
            l = 0;
            while(l < i) 
            {
                annotatedparameter1 = annotatedconstructor2.getParameter(l);
                annotatedconstructor3 = annotatedconstructor1;
                propertyname = null;
                if(annotatedconstructor2 == annotatedconstructor3)
                    propertyname = apropertyname[l];
                if(propertyname == null)
                {
                    if(annotatedparameter1 == null)
                        propertyname1 = null;
                    else
                        propertyname1 = annotationintrospector.findNameForDeserialization(annotatedparameter1);
                    propertyname = propertyname1;
                }
                obj = annotationintrospector.findInjectableValueId(annotatedparameter1);
                if(propertyname != null && propertyname.hasSimpleName())
                {
                    j++;
                    acreatorproperty[l] = constructCreatorProperty(deserializationcontext, beandescription, propertyname, l, annotatedparameter1, obj);
                    annotatedparameter1 = annotatedparameter;
                } else
                if(obj != null)
                {
                    k++;
                    acreatorproperty[l] = constructCreatorProperty(deserializationcontext, beandescription, propertyname, l, annotatedparameter1, obj);
                    annotatedparameter1 = annotatedparameter;
                } else
                if(annotationintrospector.findUnwrappingNameTransformer(annotatedparameter1) != null)
                {
                    acreatorproperty[l] = constructCreatorProperty(deserializationcontext, beandescription, UNWRAPPED_CREATOR_PARAM_NAME, l, annotatedparameter1, null);
                    j++;
                    annotatedparameter1 = annotatedparameter;
                } else
                if(annotatedparameter != null)
                    annotatedparameter1 = annotatedparameter;
                l++;
                annotatedparameter = annotatedparameter1;
            }
            if(flag || j > 0 || k > 0)
                if(j + k == i)
                    creatorcollector.addPropertyCreator(annotatedconstructor2, acreatorproperty);
                else
                if(j == 0 && k + 1 == i)
                    creatorcollector.addDelegatingCreator(annotatedconstructor2, acreatorproperty);
                else
                    creatorcollector.addIncompeteParameter(annotatedparameter);
        } while(true);
        return;
    }

    protected void _addDeserializerFactoryMethods(DeserializationContext deserializationcontext, BeanDescription beandescription, VisibilityChecker visibilitychecker, AnnotationIntrospector annotationintrospector, CreatorCollector creatorcollector)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        Iterator iterator = beandescription.getFactoryMethods().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator.next();
            boolean flag = annotationintrospector.hasCreatorAnnotation(annotatedmethod);
            int i = annotatedmethod.getParameterCount();
            if(i == 0)
            {
                if(flag)
                    creatorcollector.setDefaultCreator(annotatedmethod);
                continue;
            }
            if(i == 1)
            {
                AnnotatedParameter annotatedparameter2 = annotatedmethod.getParameter(0);
                PropertyName propertyname1;
                String s;
                if(annotatedparameter2 == null)
                    propertyname1 = null;
                else
                    propertyname1 = annotationintrospector.findNameForDeserialization(annotatedparameter2);
                if(propertyname1 == null)
                    s = null;
                else
                    s = propertyname1.getSimpleName();
                if(annotationintrospector.findInjectableValueId(annotatedparameter2) == null && (s == null || s.length() == 0))
                {
                    _handleSingleArgumentFactory(deserializationconfig, beandescription, visibilitychecker, annotationintrospector, creatorcollector, annotatedmethod, flag);
                    continue;
                }
            } else
            if(!annotationintrospector.hasCreatorAnnotation(annotatedmethod))
                continue;
            AnnotatedParameter annotatedparameter = null;
            CreatorProperty acreatorproperty[] = new CreatorProperty[i];
            int j = 0;
            int k = 0;
            int l = 0;
            while(l < i) 
            {
                AnnotatedParameter annotatedparameter1 = annotatedmethod.getParameter(l);
                PropertyName propertyname;
                Object obj;
                if(annotatedparameter1 == null)
                    propertyname = null;
                else
                    propertyname = annotationintrospector.findNameForDeserialization(annotatedparameter1);
                obj = annotationintrospector.findInjectableValueId(annotatedparameter1);
                if(propertyname != null && propertyname.hasSimpleName())
                {
                    j++;
                    acreatorproperty[l] = constructCreatorProperty(deserializationcontext, beandescription, propertyname, l, annotatedparameter1, obj);
                    annotatedparameter1 = annotatedparameter;
                } else
                if(obj != null)
                {
                    k++;
                    acreatorproperty[l] = constructCreatorProperty(deserializationcontext, beandescription, propertyname, l, annotatedparameter1, obj);
                    annotatedparameter1 = annotatedparameter;
                } else
                if(annotationintrospector.findUnwrappingNameTransformer(annotatedparameter1) != null)
                {
                    acreatorproperty[l] = constructCreatorProperty(deserializationcontext, beandescription, UNWRAPPED_CREATOR_PARAM_NAME, l, annotatedparameter1, null);
                    j++;
                    annotatedparameter1 = annotatedparameter;
                } else
                if(annotatedparameter != null)
                    annotatedparameter1 = annotatedparameter;
                l++;
                annotatedparameter = annotatedparameter1;
            }
            if(flag || j > 0 || k > 0)
                if(j + k == i)
                    creatorcollector.addPropertyCreator(annotatedmethod, acreatorproperty);
                else
                if(j == 0 && k + 1 == i)
                    creatorcollector.addDelegatingCreator(annotatedmethod, acreatorproperty);
                else
                    throw new IllegalArgumentException((new StringBuilder()).append("Argument #").append(annotatedparameter.getIndex()).append(" of factory method ").append(annotatedmethod).append(" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator").toString());
        } while(true);
    }

    protected ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext deserializationcontext, BeanDescription beandescription)
        throws JsonMappingException
    {
        CreatorCollector creatorcollector = new CreatorCollector(beandescription, deserializationcontext.canOverrideAccessModifiers());
        AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        VisibilityChecker visibilitychecker = deserializationconfig.getDefaultVisibilityChecker();
        VisibilityChecker visibilitychecker1 = annotationintrospector.findAutoDetectVisibility(beandescription.getClassInfo(), visibilitychecker);
        _addDeserializerFactoryMethods(deserializationcontext, beandescription, visibilitychecker1, annotationintrospector, creatorcollector);
        if(beandescription.getType().isConcrete())
            _addDeserializerConstructors(deserializationcontext, beandescription, visibilitychecker1, annotationintrospector, creatorcollector);
        return creatorcollector.constructValueInstantiator(deserializationconfig);
    }

    protected JsonDeserializer _findCustomArrayDeserializer(ArrayType arraytype, DeserializationConfig deserializationconfig, BeanDescription beandescription, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for(Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findArrayDeserializer(arraytype, deserializationconfig, beandescription, typedeserializer, jsondeserializer);
            if(jsondeserializer1 != null)
                return jsondeserializer1;
        }

        return null;
    }

    protected JsonDeserializer _findCustomCollectionDeserializer(CollectionType collectiontype, DeserializationConfig deserializationconfig, BeanDescription beandescription, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for(Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findCollectionDeserializer(collectiontype, deserializationconfig, beandescription, typedeserializer, jsondeserializer);
            if(jsondeserializer1 != null)
                return jsondeserializer1;
        }

        return null;
    }

    protected JsonDeserializer _findCustomCollectionLikeDeserializer(CollectionLikeType collectionliketype, DeserializationConfig deserializationconfig, BeanDescription beandescription, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for(Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findCollectionLikeDeserializer(collectionliketype, deserializationconfig, beandescription, typedeserializer, jsondeserializer);
            if(jsondeserializer1 != null)
                return jsondeserializer1;
        }

        return null;
    }

    protected JsonDeserializer _findCustomEnumDeserializer(Class class1, DeserializationConfig deserializationconfig, BeanDescription beandescription)
        throws JsonMappingException
    {
        for(Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer = ((Deserializers)iterator.next()).findEnumDeserializer(class1, deserializationconfig, beandescription);
            if(jsondeserializer != null)
                return jsondeserializer;
        }

        return null;
    }

    protected JsonDeserializer _findCustomMapDeserializer(MapType maptype, DeserializationConfig deserializationconfig, BeanDescription beandescription, KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for(Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findMapDeserializer(maptype, deserializationconfig, beandescription, keydeserializer, typedeserializer, jsondeserializer);
            if(jsondeserializer1 != null)
                return jsondeserializer1;
        }

        return null;
    }

    protected JsonDeserializer _findCustomMapLikeDeserializer(MapLikeType mapliketype, DeserializationConfig deserializationconfig, BeanDescription beandescription, KeyDeserializer keydeserializer, TypeDeserializer typedeserializer, JsonDeserializer jsondeserializer)
        throws JsonMappingException
    {
        for(Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer1 = ((Deserializers)iterator.next()).findMapLikeDeserializer(mapliketype, deserializationconfig, beandescription, keydeserializer, typedeserializer, jsondeserializer);
            if(jsondeserializer1 != null)
                return jsondeserializer1;
        }

        return null;
    }

    protected JsonDeserializer _findCustomTreeNodeDeserializer(Class class1, DeserializationConfig deserializationconfig, BeanDescription beandescription)
        throws JsonMappingException
    {
        for(Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer = ((Deserializers)iterator.next()).findTreeNodeDeserializer(class1, deserializationconfig, beandescription);
            if(jsondeserializer != null)
                return jsondeserializer;
        }

        return null;
    }

    protected AnnotatedMethod _findJsonValueFor(DeserializationConfig deserializationconfig, JavaType javatype)
    {
        if(javatype == null)
            return null;
        else
            return deserializationconfig.introspect(javatype).findJsonValueMethod();
    }

    protected boolean _handleSingleArgumentConstructor(DeserializationContext deserializationcontext, BeanDescription beandescription, VisibilityChecker visibilitychecker, AnnotationIntrospector annotationintrospector, CreatorCollector creatorcollector, AnnotatedConstructor annotatedconstructor, boolean flag, 
            boolean flag1, PropertyName propertyname)
        throws JsonMappingException
    {
        AnnotatedParameter annotatedparameter = annotatedconstructor.getParameter(0);
        PropertyName propertyname1;
        Class class1;
        if(propertyname == null)
        {
            Object obj;
            CreatorProperty acreatorproperty[];
            PropertyName propertyname2;
            if(annotatedparameter == null)
                propertyname2 = null;
            else
                propertyname2 = annotationintrospector.findNameForDeserialization(annotatedparameter);
            propertyname1 = propertyname2;
        } else
        {
            propertyname1 = propertyname;
        }
        obj = annotationintrospector.findInjectableValueId(annotatedparameter);
        if(obj != null || propertyname1 != null && propertyname1.hasSimpleName())
        {
            acreatorproperty = new CreatorProperty[1];
            acreatorproperty[0] = constructCreatorProperty(deserializationcontext, beandescription, propertyname1, 0, annotatedparameter, obj);
            creatorcollector.addPropertyCreator(annotatedconstructor, acreatorproperty);
            return true;
        }
        class1 = annotatedconstructor.getRawParameterType(0);
        if(class1 == java/lang/String)
        {
            if(flag || flag1)
                creatorcollector.addStringCreator(annotatedconstructor);
            return true;
        }
        if(class1 == Integer.TYPE || class1 == java/lang/Integer)
        {
            if(flag || flag1)
                creatorcollector.addIntCreator(annotatedconstructor);
            return true;
        }
        if(class1 == Long.TYPE || class1 == java/lang/Long)
        {
            if(flag || flag1)
                creatorcollector.addLongCreator(annotatedconstructor);
            return true;
        }
        if(class1 == Double.TYPE || class1 == java/lang/Double)
        {
            if(flag || flag1)
                creatorcollector.addDoubleCreator(annotatedconstructor);
            return true;
        }
        if(flag)
        {
            creatorcollector.addDelegatingCreator(annotatedconstructor, null);
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean _handleSingleArgumentFactory(DeserializationConfig deserializationconfig, BeanDescription beandescription, VisibilityChecker visibilitychecker, AnnotationIntrospector annotationintrospector, CreatorCollector creatorcollector, AnnotatedMethod annotatedmethod, boolean flag)
        throws JsonMappingException
    {
        Class class1 = annotatedmethod.getRawParameterType(0);
        if(class1 != java/lang/String) goto _L2; else goto _L1
_L1:
        if(flag || visibilitychecker.isCreatorVisible(annotatedmethod))
            creatorcollector.addStringCreator(annotatedmethod);
_L4:
        return true;
_L2:
        if(class1 != Integer.TYPE && class1 != java/lang/Integer)
            break; /* Loop/switch isn't completed */
        if(flag || visibilitychecker.isCreatorVisible(annotatedmethod))
        {
            creatorcollector.addIntCreator(annotatedmethod);
            return true;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(class1 != Long.TYPE && class1 != java/lang/Long)
            break; /* Loop/switch isn't completed */
        if(flag || visibilitychecker.isCreatorVisible(annotatedmethod))
        {
            creatorcollector.addLongCreator(annotatedmethod);
            return true;
        }
        if(true) goto _L4; else goto _L5
_L5:
        if(class1 != Double.TYPE && class1 != java/lang/Double)
            break; /* Loop/switch isn't completed */
        if(flag || visibilitychecker.isCreatorVisible(annotatedmethod))
        {
            creatorcollector.addDoubleCreator(annotatedmethod);
            return true;
        }
        if(true) goto _L4; else goto _L6
_L6:
        if(class1 == Boolean.TYPE || class1 == java/lang/Boolean)
        {
            if(flag || visibilitychecker.isCreatorVisible(annotatedmethod))
            {
                creatorcollector.addBooleanCreator(annotatedmethod);
                return true;
            }
        } else
        if(annotationintrospector.hasCreatorAnnotation(annotatedmethod))
        {
            creatorcollector.addDelegatingCreator(annotatedmethod, null);
            return true;
        } else
        {
            return false;
        }
        if(true) goto _L4; else goto _L7
_L7:
    }

    protected CollectionType _mapAbstractCollectionType(JavaType javatype, DeserializationConfig deserializationconfig)
    {
        Class class1 = javatype.getRawClass();
        Class class2 = (Class)_collectionFallbacks.get(class1.getName());
        if(class2 == null)
            return null;
        else
            return (CollectionType)deserializationconfig.constructSpecializedType(javatype, class2);
    }

    public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig deserializationconfig, Annotated annotated, Object obj)
        throws JsonMappingException
    {
        if(obj == null)
            return null;
        if(obj instanceof ValueInstantiator)
            return (ValueInstantiator)obj;
        if(!(obj instanceof Class))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned key deserializer definition of type ").append(obj.getClass().getName()).append("; expected type KeyDeserializer or Class<KeyDeserializer> instead").toString());
        Class class1 = (Class)obj;
        if(class1 == com/fasterxml/jackson/databind/annotation/NoClass)
            return null;
        if(!com/fasterxml/jackson/databind/deser/ValueInstantiator.isAssignableFrom(class1))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Class ").append(class1.getName()).append("; expected Class<ValueInstantiator>").toString());
        HandlerInstantiator handlerinstantiator = deserializationconfig.getHandlerInstantiator();
        if(handlerinstantiator != null)
        {
            ValueInstantiator valueinstantiator = handlerinstantiator.valueInstantiatorInstance(deserializationconfig, annotated, class1);
            if(valueinstantiator != null)
                return valueinstantiator;
        }
        return (ValueInstantiator)ClassUtil.createInstance(class1, deserializationconfig.canOverrideAccessModifiers());
    }

    protected CreatorProperty constructCreatorProperty(DeserializationContext deserializationcontext, BeanDescription beandescription, PropertyName propertyname, int i, AnnotatedParameter annotatedparameter, Object obj)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
        Boolean boolean1;
        boolean flag;
        String s;
        PropertyMetadata propertymetadata;
        JavaType javatype;
        com.fasterxml.jackson.databind.BeanProperty.Std std;
        JavaType javatype1;
        com.fasterxml.jackson.databind.BeanProperty.Std std1;
        JsonDeserializer jsondeserializer;
        JavaType javatype2;
        TypeDeserializer typedeserializer;
        TypeDeserializer typedeserializer1;
        CreatorProperty creatorproperty;
        if(annotationintrospector == null)
            boolean1 = null;
        else
            boolean1 = annotationintrospector.hasRequiredMarker(annotatedparameter);
        if(boolean1 != null && boolean1.booleanValue())
            flag = true;
        else
            flag = false;
        if(annotationintrospector == null)
            s = null;
        else
            s = annotationintrospector.findPropertyDescription(annotatedparameter);
        propertymetadata = PropertyMetadata.construct(flag, s);
        javatype = deserializationconfig.getTypeFactory().constructType(annotatedparameter.getParameterType(), beandescription.bindingsForBeanType());
        std = new com.fasterxml.jackson.databind.BeanProperty.Std(propertyname, javatype, annotationintrospector.findWrapperName(annotatedparameter), beandescription.getClassAnnotations(), annotatedparameter, propertymetadata);
        javatype1 = resolveType(deserializationcontext, beandescription, javatype, annotatedparameter);
        if(javatype1 != javatype)
            std1 = std.withType(javatype1);
        else
            std1 = std;
        jsondeserializer = findDeserializerFromAnnotation(deserializationcontext, annotatedparameter);
        javatype2 = modifyTypeByAnnotation(deserializationcontext, annotatedparameter, javatype1);
        typedeserializer = (TypeDeserializer)javatype2.getTypeHandler();
        if(typedeserializer == null)
            typedeserializer1 = findTypeDeserializer(deserializationconfig, javatype2);
        else
            typedeserializer1 = typedeserializer;
        creatorproperty = new CreatorProperty(propertyname, javatype2, std1.getWrapperName(), typedeserializer1, beandescription.getClassAnnotations(), annotatedparameter, i, obj, propertymetadata);
        if(jsondeserializer != null)
            creatorproperty = creatorproperty.withValueDeserializer(jsondeserializer);
        return creatorproperty;
    }

    protected EnumResolver constructEnumResolver(Class class1, DeserializationConfig deserializationconfig, AnnotatedMethod annotatedmethod)
    {
        if(annotatedmethod != null)
        {
            java.lang.reflect.Method method = annotatedmethod.getAnnotated();
            if(deserializationconfig.canOverrideAccessModifiers())
                ClassUtil.checkAndFixAccess(method);
            return EnumResolver.constructUnsafeUsingMethod(class1, method);
        }
        if(deserializationconfig.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING))
            return EnumResolver.constructUnsafeUsingToString(class1);
        else
            return EnumResolver.constructUnsafe(class1, deserializationconfig.getAnnotationIntrospector());
    }

    public JsonDeserializer createArrayDeserializer(DeserializationContext deserializationcontext, ArrayType arraytype, BeanDescription beandescription)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        JavaType javatype = arraytype.getContentType();
        JsonDeserializer jsondeserializer = (JsonDeserializer)javatype.getValueHandler();
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype.getTypeHandler();
        TypeDeserializer typedeserializer1;
        Object obj;
        Iterator iterator;
        Object obj1;
        Class class1;
        if(typedeserializer == null)
            typedeserializer1 = findTypeDeserializer(deserializationconfig, javatype);
        else
            typedeserializer1 = typedeserializer;
        obj = _findCustomArrayDeserializer(arraytype, deserializationconfig, beandescription, typedeserializer1, jsondeserializer);
        if(obj != null) goto _L2; else goto _L1
_L1:
        if(jsondeserializer != null) goto _L4; else goto _L3
_L3:
        class1 = javatype.getRawClass();
        if(!javatype.isPrimitive()) goto _L6; else goto _L5
_L5:
        obj1 = PrimitiveArrayDeserializers.forType(class1);
_L8:
        return ((JsonDeserializer) (obj1));
_L6:
        if(class1 == java/lang/String)
            return StringArrayDeserializer.instance;
_L4:
        obj = new ObjectArrayDeserializer(arraytype, jsondeserializer, typedeserializer1);
_L2:
        if(_factoryConfig.hasDeserializerModifiers())
        {
            iterator = _factoryConfig.deserializerModifiers().iterator();
            obj1 = obj;
            while(iterator.hasNext()) 
                obj1 = ((BeanDeserializerModifier)iterator.next()).modifyArrayDeserializer(deserializationconfig, arraytype, beandescription, ((JsonDeserializer) (obj1)));
        } else
        {
            return ((JsonDeserializer) (obj));
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    public JsonDeserializer createCollectionDeserializer(DeserializationContext deserializationcontext, CollectionType collectiontype, BeanDescription beandescription)
        throws JsonMappingException
    {
        JavaType javatype = collectiontype.getContentType();
        JsonDeserializer jsondeserializer = (JsonDeserializer)javatype.getValueHandler();
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype.getTypeHandler();
        TypeDeserializer typedeserializer1;
        Object obj;
        CollectionType collectiontype1;
        if(typedeserializer == null)
            typedeserializer1 = findTypeDeserializer(deserializationconfig, javatype);
        else
            typedeserializer1 = typedeserializer;
        obj = _findCustomCollectionDeserializer(collectiontype, deserializationconfig, beandescription, typedeserializer1, jsondeserializer);
        if(obj == null)
        {
            Class class1 = collectiontype.getRawClass();
            if(jsondeserializer == null && java/util/EnumSet.isAssignableFrom(class1))
                obj = new EnumSetDeserializer(javatype, null);
        }
        if(obj == null)
        {
            Object obj1;
            if(collectiontype.isInterface() || collectiontype.isAbstract())
            {
                collectiontype1 = _mapAbstractCollectionType(collectiontype, deserializationconfig);
                if(collectiontype1 == null)
                {
                    if(collectiontype.getTypeHandler() == null)
                        throw new IllegalArgumentException((new StringBuilder()).append("Can not find a deserializer for non-concrete Collection type ").append(collectiontype).toString());
                    obj = AbstractDeserializer.constructForNonPOJO(beandescription);
                    collectiontype1 = collectiontype;
                } else
                {
                    beandescription = deserializationconfig.introspectForCreation(collectiontype1);
                }
            } else
            {
                collectiontype1 = collectiontype;
            }
            if(obj == null)
            {
                ValueInstantiator valueinstantiator = findValueInstantiator(deserializationcontext, beandescription);
                if(!valueinstantiator.canCreateUsingDefault() && collectiontype1.getRawClass() == java/util/concurrent/ArrayBlockingQueue)
                    return new ArrayBlockingQueueDeserializer(collectiontype1, jsondeserializer, typedeserializer1, valueinstantiator, null);
                Iterator iterator;
                if(javatype.getRawClass() == java/lang/String)
                    obj = new StringCollectionDeserializer(collectiontype1, jsondeserializer, valueinstantiator);
                else
                    obj = new CollectionDeserializer(collectiontype1, jsondeserializer, typedeserializer1, valueinstantiator);
            }
        } else
        {
            collectiontype1 = collectiontype;
        }
        if(_factoryConfig.hasDeserializerModifiers())
        {
            iterator = _factoryConfig.deserializerModifiers().iterator();
            for(obj1 = obj; iterator.hasNext(); obj1 = ((BeanDeserializerModifier)iterator.next()).modifyCollectionDeserializer(deserializationconfig, collectiontype1, beandescription, ((JsonDeserializer) (obj1))));
        } else
        {
            obj1 = obj;
        }
        return ((JsonDeserializer) (obj1));
    }

    public JsonDeserializer createCollectionLikeDeserializer(DeserializationContext deserializationcontext, CollectionLikeType collectionliketype, BeanDescription beandescription)
        throws JsonMappingException
    {
        JavaType javatype = collectionliketype.getContentType();
        JsonDeserializer jsondeserializer = (JsonDeserializer)javatype.getValueHandler();
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype.getTypeHandler();
        TypeDeserializer typedeserializer1;
        JsonDeserializer jsondeserializer1;
        if(typedeserializer == null)
            typedeserializer1 = findTypeDeserializer(deserializationconfig, javatype);
        else
            typedeserializer1 = typedeserializer;
        jsondeserializer1 = _findCustomCollectionLikeDeserializer(collectionliketype, deserializationconfig, beandescription, typedeserializer1, jsondeserializer);
        if(jsondeserializer1 != null && _factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator = _factoryConfig.deserializerModifiers().iterator();
            JsonDeserializer jsondeserializer2;
            for(jsondeserializer2 = jsondeserializer1; iterator.hasNext(); jsondeserializer2 = ((BeanDeserializerModifier)iterator.next()).modifyCollectionLikeDeserializer(deserializationconfig, collectionliketype, beandescription, jsondeserializer2));
            jsondeserializer1 = jsondeserializer2;
        }
        return jsondeserializer1;
    }

    public JsonDeserializer createEnumDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig;
        Class class1;
        JsonDeserializer jsondeserializer;
        Iterator iterator1;
        deserializationconfig = deserializationcontext.getConfig();
        class1 = javatype.getRawClass();
        jsondeserializer = _findCustomEnumDeserializer(class1, deserializationconfig, beandescription);
        if(jsondeserializer != null)
            break MISSING_BLOCK_LABEL_257;
        iterator1 = beandescription.getFactoryMethods().iterator();
_L4:
        if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator1.next();
        if(!deserializationcontext.getAnnotationIntrospector().hasCreatorAnnotation(annotatedmethod)) goto _L4; else goto _L3
_L3:
        if(annotatedmethod.getParameterCount() != 1 || !annotatedmethod.getRawReturnType().isAssignableFrom(class1)) goto _L6; else goto _L5
_L5:
        Object obj = EnumDeserializer.deserializerForCreator(deserializationconfig, class1, annotatedmethod);
_L7:
        if(obj == null)
            obj = new EnumDeserializer(constructEnumResolver(class1, deserializationconfig, beandescription.findJsonValueMethod()));
        break MISSING_BLOCK_LABEL_132;
_L6:
        throw new IllegalArgumentException((new StringBuilder()).append("Unsuitable method (").append(annotatedmethod).append(") decorated with @JsonCreator (for Enum type ").append(class1.getName()).append(")").toString());
_L8:
        Object obj1;
        if(_factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator = _factoryConfig.deserializerModifiers().iterator();
            for(obj1 = obj; iterator.hasNext(); obj1 = ((BeanDeserializerModifier)iterator.next()).modifyEnumDeserializer(deserializationconfig, javatype, beandescription, ((JsonDeserializer) (obj1))));
        } else
        {
            obj1 = obj;
        }
        return ((JsonDeserializer) (obj1));
_L2:
        obj = jsondeserializer;
          goto _L7
        obj = jsondeserializer;
          goto _L8
    }

    public KeyDeserializer createKeyDeserializer(DeserializationContext deserializationcontext, JavaType javatype)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig;
        KeyDeserializer keydeserializer;
        deserializationconfig = deserializationcontext.getConfig();
        boolean flag = _factoryConfig.hasKeyDeserializers();
        keydeserializer = null;
        if(flag)
        {
            BeanDescription beandescription = deserializationconfig.introspectClassAnnotations(javatype.getRawClass());
            Iterator iterator1 = _factoryConfig.keyDeserializers().iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                keydeserializer = ((KeyDeserializers)iterator1.next()).findKeyDeserializer(javatype, deserializationconfig, beandescription);
            } while(keydeserializer == null);
        }
        if(keydeserializer != null) goto _L2; else goto _L1
_L1:
        if(!javatype.isEnumType()) goto _L4; else goto _L3
_L3:
        keydeserializer = _createEnumKeyDeserializer(deserializationcontext, javatype);
_L6:
        return keydeserializer;
_L4:
        keydeserializer = StdKeyDeserializers.findStringBasedKeyDeserializer(deserializationconfig, javatype);
_L2:
        if(keydeserializer != null && _factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator = _factoryConfig.deserializerModifiers().iterator();
            KeyDeserializer keydeserializer1;
            for(keydeserializer1 = keydeserializer; iterator.hasNext(); keydeserializer1 = ((BeanDeserializerModifier)iterator.next()).modifyKeyDeserializer(deserializationconfig, javatype, keydeserializer1));
            return keydeserializer1;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public JsonDeserializer createMapDeserializer(DeserializationContext deserializationcontext, MapType maptype, BeanDescription beandescription)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        JavaType javatype = maptype.getKeyType();
        JavaType javatype1 = maptype.getContentType();
        JsonDeserializer jsondeserializer = (JsonDeserializer)javatype1.getValueHandler();
        KeyDeserializer keydeserializer = (KeyDeserializer)javatype.getValueHandler();
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype1.getTypeHandler();
        TypeDeserializer typedeserializer1;
        Object obj;
        MapType maptype1;
        Iterator iterator;
        Class class1;
        Class class2;
        MapType maptype2;
        Class class3;
        if(typedeserializer == null)
            typedeserializer1 = findTypeDeserializer(deserializationconfig, javatype1);
        else
            typedeserializer1 = typedeserializer;
        obj = _findCustomMapDeserializer(maptype, deserializationconfig, beandescription, keydeserializer, typedeserializer1, jsondeserializer);
        if(obj != null) goto _L2; else goto _L1
_L1:
        class1 = maptype.getRawClass();
        if(java/util/EnumMap.isAssignableFrom(class1))
        {
            class3 = javatype.getRawClass();
            if(class3 == null || !class3.isEnum())
                throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
            obj = new EnumMapDeserializer(maptype, null, jsondeserializer, typedeserializer1);
        }
        if(obj != null) goto _L2; else goto _L3
_L3:
        if(maptype.isInterface() || maptype.isAbstract())
        {
            class2 = (Class)_mapFallbacks.get(class1.getName());
            if(class2 != null)
            {
                maptype2 = (MapType)deserializationconfig.constructSpecializedType(maptype, class2);
                beandescription = deserializationconfig.introspectForCreation(maptype2);
                maptype1 = maptype2;
            } else
            {
                if(maptype.getTypeHandler() == null)
                    throw new IllegalArgumentException((new StringBuilder()).append("Can not find a deserializer for non-concrete Map type ").append(maptype).toString());
                obj = AbstractDeserializer.constructForNonPOJO(beandescription);
                maptype1 = maptype;
            }
        } else
        {
            maptype1 = maptype;
        }
        if(obj == null)
        {
            obj = new MapDeserializer(maptype1, findValueInstantiator(deserializationcontext, beandescription), keydeserializer, jsondeserializer, typedeserializer1);
            ((MapDeserializer) (obj)).setIgnorableProperties(deserializationconfig.getAnnotationIntrospector().findPropertiesToIgnore(beandescription.getClassInfo()));
        }
_L5:
        if(_factoryConfig.hasDeserializerModifiers())
            for(iterator = _factoryConfig.deserializerModifiers().iterator(); iterator.hasNext();)
                obj = ((BeanDeserializerModifier)iterator.next()).modifyMapDeserializer(deserializationconfig, maptype1, beandescription, ((JsonDeserializer) (obj)));

        return ((JsonDeserializer) (obj));
_L2:
        maptype1 = maptype;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public JsonDeserializer createMapLikeDeserializer(DeserializationContext deserializationcontext, MapLikeType mapliketype, BeanDescription beandescription)
        throws JsonMappingException
    {
        JavaType javatype = mapliketype.getKeyType();
        JavaType javatype1 = mapliketype.getContentType();
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        JsonDeserializer jsondeserializer = (JsonDeserializer)javatype1.getValueHandler();
        KeyDeserializer keydeserializer = (KeyDeserializer)javatype.getValueHandler();
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype1.getTypeHandler();
        TypeDeserializer typedeserializer1;
        JsonDeserializer jsondeserializer1;
        if(typedeserializer == null)
            typedeserializer1 = findTypeDeserializer(deserializationconfig, javatype1);
        else
            typedeserializer1 = typedeserializer;
        jsondeserializer1 = _findCustomMapLikeDeserializer(mapliketype, deserializationconfig, beandescription, keydeserializer, typedeserializer1, jsondeserializer);
        if(jsondeserializer1 != null && _factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator = _factoryConfig.deserializerModifiers().iterator();
            JsonDeserializer jsondeserializer2;
            for(jsondeserializer2 = jsondeserializer1; iterator.hasNext(); jsondeserializer2 = ((BeanDeserializerModifier)iterator.next()).modifyMapLikeDeserializer(deserializationconfig, mapliketype, beandescription, jsondeserializer2));
            jsondeserializer1 = jsondeserializer2;
        }
        return jsondeserializer1;
    }

    public JsonDeserializer createTreeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        Class class1 = javatype.getRawClass();
        JsonDeserializer jsondeserializer = _findCustomTreeNodeDeserializer(class1, deserializationconfig, beandescription);
        if(jsondeserializer != null)
            return jsondeserializer;
        else
            return JsonNodeDeserializer.getDeserializer(class1);
    }

    public JsonDeserializer findDefaultDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        Class class1;
        String s;
        class1 = javatype.getRawClass();
        s = class1.getName();
        if(!class1.isPrimitive() && !s.startsWith("java."))
            break MISSING_BLOCK_LABEL_160;
        if(class1 != CLASS_OBJECT) goto _L2; else goto _L1
_L1:
        Object obj = new UntypedObjectDeserializer();
_L4:
        return ((JsonDeserializer) (obj));
_L2:
        if(class1 == CLASS_STRING || class1 == CLASS_CHAR_BUFFER)
            return StringDeserializer.instance;
        if(class1 == CLASS_ITERABLE)
        {
            TypeFactory typefactory = deserializationcontext.getTypeFactory();
            JavaType javatype1;
            if(javatype.containedTypeCount() > 0)
                javatype1 = javatype.containedType(0);
            else
                javatype1 = TypeFactory.unknownType();
            return createCollectionDeserializer(deserializationcontext, typefactory.constructCollectionType(java/util/Collection, javatype1), beandescription);
        }
        obj = NumberDeserializers.find(class1, s);
        if(obj != null) goto _L4; else goto _L3
_L3:
        obj = DateDeserializers.find(class1, s);
        if(obj != null) goto _L4; else goto _L5
_L5:
        return JdkDeserializers.find(class1, s);
        if(s.startsWith("com.fasterxml."))
        {
            if(class1 == com/fasterxml/jackson/databind/util/TokenBuffer)
                return TokenBufferDeserializer.instance;
            if(com/fasterxml/jackson/databind/JavaType.isAssignableFrom(class1))
                return JavaTypeDeserializer.instance;
        }
        return null;
    }

    protected JsonDeserializer findDeserializerFromAnnotation(DeserializationContext deserializationcontext, Annotated annotated)
        throws JsonMappingException
    {
        Object obj = deserializationcontext.getAnnotationIntrospector().findDeserializer(annotated);
        if(obj == null)
            return null;
        else
            return deserializationcontext.deserializerInstance(annotated, obj);
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, AnnotatedMember annotatedmember)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findPropertyContentTypeResolver(deserializationconfig, annotatedmember, javatype);
        JavaType javatype1 = javatype.getContentType();
        if(typeresolverbuilder == null)
            return findTypeDeserializer(deserializationconfig, javatype1);
        else
            return typeresolverbuilder.buildTypeDeserializer(deserializationconfig, javatype1, deserializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedmember, deserializationconfig, annotationintrospector, javatype1));
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype, AnnotatedMember annotatedmember)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findPropertyTypeResolver(deserializationconfig, annotatedmember, javatype);
        if(typeresolverbuilder == null)
            return findTypeDeserializer(deserializationconfig, javatype);
        else
            return typeresolverbuilder.buildTypeDeserializer(deserializationconfig, javatype, deserializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedmember, deserializationconfig, annotationintrospector, javatype));
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        com.fasterxml.jackson.databind.introspect.AnnotatedClass annotatedclass = deserializationconfig.introspectClassAnnotations(javatype.getRawClass()).getClassInfo();
        AnnotationIntrospector annotationintrospector = deserializationconfig.getAnnotationIntrospector();
        TypeResolverBuilder typeresolverbuilder = annotationintrospector.findTypeResolver(deserializationconfig, annotatedclass, javatype);
        Collection collection;
        if(typeresolverbuilder == null)
        {
            typeresolverbuilder = deserializationconfig.getDefaultTyper(javatype);
            collection = null;
            if(typeresolverbuilder == null)
                return null;
        } else
        {
            collection = deserializationconfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedclass, deserializationconfig, annotationintrospector);
        }
        if(typeresolverbuilder.getDefaultImpl() == null && javatype.isAbstract())
        {
            JavaType javatype1 = mapAbstractType(deserializationconfig, javatype);
            if(javatype1 != null && javatype1.getRawClass() != javatype.getRawClass())
                typeresolverbuilder = typeresolverbuilder.defaultImpl(javatype1.getRawClass());
        }
        return typeresolverbuilder.buildTypeDeserializer(deserializationconfig, javatype, collection);
    }

    public ValueInstantiator findValueInstantiator(DeserializationContext deserializationcontext, BeanDescription beandescription)
        throws JsonMappingException
    {
        ValueInstantiator valueinstantiator1;
label0:
        {
            DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
            com.fasterxml.jackson.databind.introspect.AnnotatedClass annotatedclass = beandescription.getClassInfo();
            Object obj = deserializationcontext.getAnnotationIntrospector().findValueInstantiator(annotatedclass);
            ValueInstantiator valueinstantiator = null;
            if(obj != null)
                valueinstantiator = _valueInstantiatorInstance(deserializationconfig, annotatedclass, obj);
            if(valueinstantiator == null)
            {
                valueinstantiator = _findStdValueInstantiator(deserializationconfig, beandescription);
                if(valueinstantiator == null)
                    valueinstantiator = _constructDefaultValueInstantiator(deserializationcontext, beandescription);
            }
            if(_factoryConfig.hasValueInstantiators())
            {
                Iterator iterator = _factoryConfig.valueInstantiators().iterator();
                valueinstantiator1 = valueinstantiator;
                ValueInstantiators valueinstantiators;
                do
                {
                    if(!iterator.hasNext())
                        break label0;
                    valueinstantiators = (ValueInstantiators)iterator.next();
                    valueinstantiator1 = valueinstantiators.findValueInstantiator(deserializationconfig, beandescription, valueinstantiator1);
                } while(valueinstantiator1 != null);
                throw new JsonMappingException((new StringBuilder()).append("Broken registered ValueInstantiators (of type ").append(valueinstantiators.getClass().getName()).append("): returned null ValueInstantiator").toString());
            }
            valueinstantiator1 = valueinstantiator;
        }
        if(valueinstantiator1.getIncompleteParameter() != null)
        {
            AnnotatedParameter annotatedparameter = valueinstantiator1.getIncompleteParameter();
            com.fasterxml.jackson.databind.introspect.AnnotatedWithParams annotatedwithparams = annotatedparameter.getOwner();
            throw new IllegalArgumentException((new StringBuilder()).append("Argument #").append(annotatedparameter.getIndex()).append(" of constructor ").append(annotatedwithparams).append(" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator").toString());
        } else
        {
            return valueinstantiator1;
        }
    }

    public DeserializerFactoryConfig getFactoryConfig()
    {
        return _factoryConfig;
    }

    public JavaType mapAbstractType(DeserializationConfig deserializationconfig, JavaType javatype)
        throws JsonMappingException
    {
        do
        {
            JavaType javatype1 = _mapAbstractType2(deserializationconfig, javatype);
            if(javatype1 == null)
                return javatype;
            Class class1 = javatype.getRawClass();
            Class class2 = javatype1.getRawClass();
            if(class1 == class2 || !class1.isAssignableFrom(class2))
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid abstract type resolution from ").append(javatype).append(" to ").append(javatype1).append(": latter is not a subtype of former").toString());
            javatype = javatype1;
        } while(true);
    }

    protected JavaType modifyTypeByAnnotation(DeserializationContext deserializationcontext, Annotated annotated, JavaType javatype)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
        Class class1 = annotationintrospector.findDeserializationType(annotated, javatype);
        JavaType javatype1;
        if(class1 != null)
        {
            JavaType javatype5;
            try
            {
                javatype5 = javatype.narrowBy(class1);
            }
            catch(IllegalArgumentException illegalargumentexception2)
            {
                throw new JsonMappingException((new StringBuilder()).append("Failed to narrow type ").append(javatype).append(" with concrete-type annotation (value ").append(class1.getName()).append("), method '").append(annotated.getName()).append("': ").append(illegalargumentexception2.getMessage()).toString(), null, illegalargumentexception2);
            }
            javatype1 = javatype5;
        } else
        {
            javatype1 = javatype;
        }
        if(javatype1.isContainerType())
        {
            Class class2 = annotationintrospector.findDeserializationKeyType(annotated, javatype1.getKeyType());
            Object obj;
            Class class3;
            if(class2 != null)
            {
                if(!(javatype1 instanceof MapLikeType))
                    throw new JsonMappingException((new StringBuilder()).append("Illegal key-type annotation: type ").append(javatype1).append(" is not a Map(-like) type").toString());
                JavaType javatype2;
                JsonDeserializer jsondeserializer;
                JavaType javatype3;
                KeyDeserializer keydeserializer;
                JavaType javatype4;
                try
                {
                    javatype4 = ((MapLikeType)javatype1).narrowKey(class2);
                }
                catch(IllegalArgumentException illegalargumentexception1)
                {
                    throw new JsonMappingException((new StringBuilder()).append("Failed to narrow key type ").append(javatype1).append(" with key-type annotation (").append(class2.getName()).append("): ").append(illegalargumentexception1.getMessage()).toString(), null, illegalargumentexception1);
                }
                obj = javatype4;
            } else
            {
                obj = javatype1;
            }
            javatype2 = ((JavaType) (obj)).getKeyType();
            if(javatype2 != null && javatype2.getValueHandler() == null)
            {
                keydeserializer = deserializationcontext.keyDeserializerInstance(annotated, annotationintrospector.findKeyDeserializer(annotated));
                if(keydeserializer != null)
                {
                    obj = ((MapLikeType)obj).withKeyValueHandler(keydeserializer);
                    ((JavaType) (obj)).getKeyType();
                }
            }
            class3 = annotationintrospector.findDeserializationContentType(annotated, ((JavaType) (obj)).getContentType());
            if(class3 != null)
            {
                try
                {
                    javatype3 = ((JavaType) (obj)).narrowContentsBy(class3);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    throw new JsonMappingException((new StringBuilder()).append("Failed to narrow content type ").append(obj).append(" with content-type annotation (").append(class3.getName()).append("): ").append(illegalargumentexception.getMessage()).toString(), null, illegalargumentexception);
                }
                obj = javatype3;
            }
            if(((JavaType) (obj)).getContentType().getValueHandler() == null)
            {
                jsondeserializer = deserializationcontext.deserializerInstance(annotated, annotationintrospector.findContentDeserializer(annotated));
                if(jsondeserializer != null)
                    obj = ((JavaType) (obj)).withContentValueHandler(jsondeserializer);
            }
            return ((JavaType) (obj));
        } else
        {
            return javatype1;
        }
    }

    protected JavaType resolveType(DeserializationContext deserializationcontext, BeanDescription beandescription, JavaType javatype, AnnotatedMember annotatedmember)
        throws JsonMappingException
    {
        if(javatype.isContainerType())
        {
            AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
            if(javatype.getKeyType() != null)
            {
                KeyDeserializer keydeserializer = deserializationcontext.keyDeserializerInstance(annotatedmember, annotationintrospector.findKeyDeserializer(annotatedmember));
                if(keydeserializer != null)
                {
                    javatype = ((MapLikeType)javatype).withKeyValueHandler(keydeserializer);
                    javatype.getKeyType();
                }
            }
            JsonDeserializer jsondeserializer = deserializationcontext.deserializerInstance(annotatedmember, annotationintrospector.findContentDeserializer(annotatedmember));
            if(jsondeserializer != null)
                javatype = javatype.withContentValueHandler(jsondeserializer);
            if(annotatedmember instanceof AnnotatedMember)
            {
                TypeDeserializer typedeserializer1 = findPropertyContentTypeDeserializer(deserializationcontext.getConfig(), javatype, annotatedmember);
                if(typedeserializer1 != null)
                    javatype = javatype.withContentTypeHandler(typedeserializer1);
            }
        }
        TypeDeserializer typedeserializer;
        if(annotatedmember instanceof AnnotatedMember)
            typedeserializer = findPropertyTypeDeserializer(deserializationcontext.getConfig(), javatype, annotatedmember);
        else
            typedeserializer = findTypeDeserializer(deserializationcontext.getConfig(), javatype);
        if(typedeserializer != null)
            javatype = javatype.withTypeHandler(typedeserializer);
        return javatype;
    }

    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver abstracttyperesolver)
    {
        return withConfig(_factoryConfig.withAbstractTypeResolver(abstracttyperesolver));
    }

    public final DeserializerFactory withAdditionalDeserializers(Deserializers deserializers)
    {
        return withConfig(_factoryConfig.withAdditionalDeserializers(deserializers));
    }

    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers keydeserializers)
    {
        return withConfig(_factoryConfig.withAdditionalKeyDeserializers(keydeserializers));
    }

    protected abstract DeserializerFactory withConfig(DeserializerFactoryConfig deserializerfactoryconfig);

    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier beandeserializermodifier)
    {
        return withConfig(_factoryConfig.withDeserializerModifier(beandeserializermodifier));
    }

    public final DeserializerFactory withValueInstantiators(ValueInstantiators valueinstantiators)
    {
        return withConfig(_factoryConfig.withValueInstantiators(valueinstantiators));
    }

    private static final Class CLASS_CHAR_BUFFER = java/lang/CharSequence;
    private static final Class CLASS_ITERABLE = java/lang/Iterable;
    private static final Class CLASS_OBJECT = java/lang/Object;
    private static final Class CLASS_STRING = java/lang/String;
    protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    static final HashMap _collectionFallbacks;
    static final HashMap _mapFallbacks;
    protected final DeserializerFactoryConfig _factoryConfig;

    static 
    {
        _mapFallbacks = new HashMap();
        _mapFallbacks.put(java/util/Map.getName(), java/util/LinkedHashMap);
        _mapFallbacks.put(java/util/concurrent/ConcurrentMap.getName(), java/util/concurrent/ConcurrentHashMap);
        _mapFallbacks.put(java/util/SortedMap.getName(), java/util/TreeMap);
        _mapFallbacks.put("java.util.NavigableMap", java/util/TreeMap);
        try
        {
            _mapFallbacks.put(java/util/concurrent/ConcurrentNavigableMap.getName(), java/util/concurrent/ConcurrentSkipListMap);
        }
        catch(Throwable throwable)
        {
            System.err.println((new StringBuilder()).append("Problems with (optional) types: ").append(throwable).toString());
        }
        _collectionFallbacks = new HashMap();
        _collectionFallbacks.put(java/util/Collection.getName(), java/util/ArrayList);
        _collectionFallbacks.put(java/util/List.getName(), java/util/ArrayList);
        _collectionFallbacks.put(java/util/Set.getName(), java/util/HashSet);
        _collectionFallbacks.put(java/util/SortedSet.getName(), java/util/TreeSet);
        _collectionFallbacks.put(java/util/Queue.getName(), java/util/LinkedList);
        _collectionFallbacks.put("java.util.Deque", java/util/LinkedList);
        _collectionFallbacks.put("java.util.NavigableSet", java/util/TreeSet);
    }
}
