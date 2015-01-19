// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ArrayBuilders;

// Referenced classes of package com.fasterxml.jackson.databind.ser:
//            BeanPropertyWriter, BeanSerializerFactory

public class PropertyBuilder
{

    public PropertyBuilder(SerializationConfig serializationconfig, BeanDescription beandescription)
    {
        _config = serializationconfig;
        _beanDesc = beandescription;
        _outputProps = beandescription.findSerializationInclusion(serializationconfig.getSerializationInclusion());
        _annotationIntrospector = _config.getAnnotationIntrospector();
    }

    protected Object _throwWrapped(Exception exception, String s, Object obj)
    {
        Object obj1;
        for(obj1 = exception; ((Throwable) (obj1)).getCause() != null; obj1 = ((Throwable) (obj1)).getCause());
        if(obj1 instanceof Error)
            throw (Error)obj1;
        if(obj1 instanceof RuntimeException)
            throw (RuntimeException)obj1;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to get property '").append(s).append("' of default ").append(obj.getClass().getName()).append(" instance").toString());
    }

    protected BeanPropertyWriter buildWriter(SerializerProvider serializerprovider, BeanPropertyDefinition beanpropertydefinition, JavaType javatype, JsonSerializer jsonserializer, TypeSerializer typeserializer, TypeSerializer typeserializer1, AnnotatedMember annotatedmember, 
            boolean flag)
        throws JsonMappingException
    {
        JavaType javatype1 = findSerializationType(annotatedmember, flag, javatype);
        static class _cls1
        {

            static final int $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[];

            static 
            {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = new int[com.fasterxml.jackson.annotation.JsonInclude.Include.values().length];
                try
                {
                    $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3)
                {
                    return;
                }
            }
        }

        JavaType javatype2;
        com.fasterxml.jackson.annotation.JsonInclude.Include include;
        boolean flag1;
        Object obj;
        boolean flag2;
        BeanPropertyWriter beanpropertywriter;
        Object obj1;
        com.fasterxml.jackson.databind.util.NameTransformer nametransformer;
        int i;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        if(typeserializer1 != null)
        {
            if(javatype1 == null)
                javatype1 = javatype;
            if(javatype1.getContentType() == null)
                throw new IllegalStateException((new StringBuilder()).append("Problem trying to create BeanPropertyWriter for property '").append(beanpropertydefinition.getName()).append("' (of type ").append(_beanDesc.getType()).append("); serialization type ").append(javatype1).append(" has no content").toString());
            javatype2 = javatype1.withContentTypeHandler(typeserializer1);
            javatype2.getContentType();
        } else
        {
            javatype2 = javatype1;
        }
        include = _annotationIntrospector.findSerializationInclusion(annotatedmember, _outputProps);
        flag1 = false;
        obj = null;
        if(include == null) goto _L2; else goto _L1
_L1:
        i = _cls1..SwitchMap.com.fasterxml.jackson.annotation.JsonInclude.Include[include.ordinal()];
        obj = null;
        flag1 = false;
        i;
        JVM INSTR tableswitch 1 4: default 184
    //                   1 275
    //                   2 329
    //                   3 340
    //                   4 343;
           goto _L2 _L3 _L4 _L5 _L6
_L2:
        flag2 = flag1;
_L9:
        beanpropertywriter = new BeanPropertyWriter(beanpropertydefinition, annotatedmember, _beanDesc.getClassAnnotations(), javatype, jsonserializer, typeserializer, javatype2, flag2, obj);
        obj1 = _annotationIntrospector.findNullSerializer(annotatedmember);
        if(obj1 != null)
            beanpropertywriter.assignNullSerializer(serializerprovider.serializerInstance(annotatedmember, obj1));
        nametransformer = _annotationIntrospector.findUnwrappingNameTransformer(annotatedmember);
        if(nametransformer != null)
            beanpropertywriter = beanpropertywriter.unwrappingWriter(nametransformer);
        return beanpropertywriter;
_L3:
        obj = getDefaultValue(beanpropertydefinition.getName(), annotatedmember);
        if(obj != null) goto _L8; else goto _L7
_L7:
        flag2 = true;
          goto _L9
_L8:
        flag5 = obj.getClass().isArray();
        flag1 = false;
        if(!flag5) goto _L2; else goto _L10
_L10:
        obj = ArrayBuilders.getArrayComparator(obj);
        flag2 = false;
          goto _L9
_L4:
        obj = BeanPropertyWriter.MARKER_FOR_EMPTY;
        flag2 = true;
          goto _L9
_L5:
        flag1 = true;
_L6:
        flag3 = javatype.isContainerType();
        obj = null;
        if(!flag3) goto _L2; else goto _L11
_L11:
        flag4 = _config.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        obj = null;
        if(flag4) goto _L2; else goto _L12
_L12:
        obj = BeanPropertyWriter.MARKER_FOR_EMPTY;
        flag2 = flag1;
          goto _L9
    }

    protected final BeanPropertyWriter buildWriter(BeanPropertyDefinition beanpropertydefinition, JavaType javatype, JsonSerializer jsonserializer, TypeSerializer typeserializer, TypeSerializer typeserializer1, AnnotatedMember annotatedmember, boolean flag)
    {
        throw new IllegalStateException();
    }

    protected JavaType findSerializationType(Annotated annotated, boolean flag, JavaType javatype)
    {
        boolean flag1 = true;
        Class class1 = _annotationIntrospector.findSerializationType(annotated);
        JavaType javatype1;
        if(class1 != null)
        {
            Class class2 = javatype.getRawClass();
            JavaType javatype2;
            com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing typing;
            if(class1.isAssignableFrom(class2))
            {
                javatype1 = javatype.widenBy(class1);
            } else
            {
                if(!class2.isAssignableFrom(class1))
                    throw new IllegalArgumentException((new StringBuilder()).append("Illegal concrete-type annotation for method '").append(annotated.getName()).append("': class ").append(class1.getName()).append(" not a super-type of (declared) class ").append(class2.getName()).toString());
                javatype1 = _config.constructSpecializedType(javatype, class1);
            }
            flag = flag1;
        } else
        {
            javatype1 = javatype;
        }
        javatype2 = BeanSerializerFactory.modifySecondaryTypesByAnnotation(_config, annotated, javatype1);
        if(javatype2 != javatype1)
        {
            javatype1 = javatype2;
            flag = flag1;
        }
        typing = _annotationIntrospector.findSerializationTyping(annotated);
        if(typing != null && typing != com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing.DEFAULT_TYPING)
        {
            if(typing != com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing.STATIC)
                flag1 = false;
            flag = flag1;
        }
        if(flag)
            return javatype1;
        else
            return null;
    }

    public Annotations getClassAnnotations()
    {
        return _beanDesc.getClassAnnotations();
    }

    protected Object getDefaultBean()
    {
        if(_defaultBean == null)
        {
            _defaultBean = _beanDesc.instantiateBean(_config.canOverrideAccessModifiers());
            if(_defaultBean == null)
            {
                Class class1 = _beanDesc.getClassInfo().getAnnotated();
                throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.getName()).append(" has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation").toString());
            }
        }
        return _defaultBean;
    }

    protected Object getDefaultValue(String s, AnnotatedMember annotatedmember)
    {
        Object obj = getDefaultBean();
        Object obj1;
        try
        {
            obj1 = annotatedmember.getValue(obj);
        }
        catch(Exception exception)
        {
            return _throwWrapped(exception, s, obj);
        }
        return obj1;
    }

    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final com.fasterxml.jackson.annotation.JsonInclude.Include _outputProps;
}
