// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind:
//            PropertyName, JavaType

public abstract class AnnotationIntrospector
    implements Versioned, Serializable
{
    public static class ReferenceProperty
    {

        public static ReferenceProperty back(String s)
        {
            return new ReferenceProperty(Type.BACK_REFERENCE, s);
        }

        public static ReferenceProperty managed(String s)
        {
            return new ReferenceProperty(Type.MANAGED_REFERENCE, s);
        }

        public String getName()
        {
            return _name;
        }

        public Type getType()
        {
            return _type;
        }

        public boolean isBackReference()
        {
            return _type == Type.BACK_REFERENCE;
        }

        public boolean isManagedReference()
        {
            return _type == Type.MANAGED_REFERENCE;
        }

        private final String _name;
        private final Type _type;

        public ReferenceProperty(Type type, String s)
        {
            _type = type;
            _name = s;
        }
    }

    public static final class ReferenceProperty.Type extends Enum
    {

        public static ReferenceProperty.Type valueOf(String s)
        {
            return (ReferenceProperty.Type)Enum.valueOf(com/fasterxml/jackson/databind/AnnotationIntrospector$ReferenceProperty$Type, s);
        }

        public static ReferenceProperty.Type[] values()
        {
            return (ReferenceProperty.Type[])$VALUES.clone();
        }

        private static final ReferenceProperty.Type $VALUES[];
        public static final ReferenceProperty.Type BACK_REFERENCE;
        public static final ReferenceProperty.Type MANAGED_REFERENCE;

        static 
        {
            MANAGED_REFERENCE = new ReferenceProperty.Type("MANAGED_REFERENCE", 0);
            BACK_REFERENCE = new ReferenceProperty.Type("BACK_REFERENCE", 1);
            ReferenceProperty.Type atype[] = new ReferenceProperty.Type[2];
            atype[0] = MANAGED_REFERENCE;
            atype[1] = BACK_REFERENCE;
            $VALUES = atype;
        }

        private ReferenceProperty.Type(String s, int i)
        {
            super(s, i);
        }
    }


    public AnnotationIntrospector()
    {
    }

    public static AnnotationIntrospector nopInstance()
    {
        return NopAnnotationIntrospector.instance;
    }

    public static AnnotationIntrospector pair(AnnotationIntrospector annotationintrospector, AnnotationIntrospector annotationintrospector1)
    {
        return new AnnotationIntrospectorPair(annotationintrospector, annotationintrospector1);
    }

    public Collection allIntrospectors()
    {
        return Collections.singletonList(this);
    }

    public Collection allIntrospectors(Collection collection)
    {
        collection.add(this);
        return collection;
    }

    public VisibilityChecker findAutoDetectVisibility(AnnotatedClass annotatedclass, VisibilityChecker visibilitychecker)
    {
        return visibilitychecker;
    }

    public Object findContentDeserializer(Annotated annotated)
    {
        return null;
    }

    public Object findContentSerializer(Annotated annotated)
    {
        return null;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedmember)
    {
        return null;
    }

    public Class findDeserializationContentType(Annotated annotated, JavaType javatype)
    {
        return null;
    }

    public Object findDeserializationConverter(Annotated annotated)
    {
        return null;
    }

    public Class findDeserializationKeyType(Annotated annotated, JavaType javatype)
    {
        return null;
    }

    public String findDeserializationName(AnnotatedField annotatedfield)
    {
        return null;
    }

    public String findDeserializationName(AnnotatedMethod annotatedmethod)
    {
        return null;
    }

    public String findDeserializationName(AnnotatedParameter annotatedparameter)
    {
        return null;
    }

    public Class findDeserializationType(Annotated annotated, JavaType javatype)
    {
        return null;
    }

    public Object findDeserializer(Annotated annotated)
    {
        return null;
    }

    public String findEnumValue(Enum enum)
    {
        return enum.name();
    }

    public Object findFilterId(Annotated annotated)
    {
        return null;
    }

    public Object findFilterId(AnnotatedClass annotatedclass)
    {
        return findFilterId(((Annotated) (annotatedclass)));
    }

    public com.fasterxml.jackson.annotation.JsonFormat.Value findFormat(Annotated annotated)
    {
        return null;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedmember)
    {
        return null;
    }

    public Object findKeyDeserializer(Annotated annotated)
    {
        return null;
    }

    public Object findKeySerializer(Annotated annotated)
    {
        return null;
    }

    public PropertyName findNameForDeserialization(Annotated annotated)
    {
        String s;
label0:
        {
            PropertyName propertyname;
            if(annotated instanceof AnnotatedField)
                s = findDeserializationName((AnnotatedField)annotated);
            else
            if(annotated instanceof AnnotatedMethod)
                s = findDeserializationName((AnnotatedMethod)annotated);
            else
            if(annotated instanceof AnnotatedParameter)
                s = findDeserializationName((AnnotatedParameter)annotated);
            else
                s = null;
            propertyname = null;
            if(s != null)
            {
                if(s.length() != 0)
                    break label0;
                propertyname = PropertyName.USE_DEFAULT;
            }
            return propertyname;
        }
        return new PropertyName(s);
    }

    public PropertyName findNameForSerialization(Annotated annotated)
    {
        String s;
label0:
        {
            PropertyName propertyname;
            if(annotated instanceof AnnotatedField)
                s = findSerializationName((AnnotatedField)annotated);
            else
            if(annotated instanceof AnnotatedMethod)
                s = findSerializationName((AnnotatedMethod)annotated);
            else
                s = null;
            propertyname = null;
            if(s != null)
            {
                if(s.length() != 0)
                    break label0;
                propertyname = PropertyName.USE_DEFAULT;
            }
            return propertyname;
        }
        return new PropertyName(s);
    }

    public Object findNamingStrategy(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public Object findNullSerializer(Annotated annotated)
    {
        return null;
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated)
    {
        return null;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectidinfo)
    {
        return objectidinfo;
    }

    public Class findPOJOBuilder(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public String[] findPropertiesToIgnore(Annotated annotated)
    {
        return null;
    }

    public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
    {
        return null;
    }

    public String findPropertyDescription(Annotated annotated)
    {
        return null;
    }

    public TypeResolverBuilder findPropertyTypeResolver(MapperConfig mapperconfig, AnnotatedMember annotatedmember, JavaType javatype)
    {
        return null;
    }

    public ReferenceProperty findReferenceType(AnnotatedMember annotatedmember)
    {
        return null;
    }

    public PropertyName findRootName(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedmember)
    {
        return null;
    }

    public Class findSerializationContentType(Annotated annotated, JavaType javatype)
    {
        return null;
    }

    public Object findSerializationConverter(Annotated annotated)
    {
        return null;
    }

    public com.fasterxml.jackson.annotation.JsonInclude.Include findSerializationInclusion(Annotated annotated, com.fasterxml.jackson.annotation.JsonInclude.Include include)
    {
        return include;
    }

    public Class findSerializationKeyType(Annotated annotated, JavaType javatype)
    {
        return null;
    }

    public String findSerializationName(AnnotatedField annotatedfield)
    {
        return null;
    }

    public String findSerializationName(AnnotatedMethod annotatedmethod)
    {
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public Class findSerializationType(Annotated annotated)
    {
        return null;
    }

    public com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing findSerializationTyping(Annotated annotated)
    {
        return null;
    }

    public Object findSerializer(Annotated annotated)
    {
        return null;
    }

    public List findSubtypes(Annotated annotated)
    {
        return null;
    }

    public String findTypeName(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public TypeResolverBuilder findTypeResolver(MapperConfig mapperconfig, AnnotatedClass annotatedclass, JavaType javatype)
    {
        return null;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedmember)
    {
        return null;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public Class[] findViews(Annotated annotated)
    {
        return null;
    }

    public PropertyName findWrapperName(Annotated annotated)
    {
        return null;
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedmethod)
    {
        return false;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedmethod)
    {
        return false;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedmethod)
    {
        return false;
    }

    public boolean hasCreatorAnnotation(Annotated annotated)
    {
        return false;
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedmember)
    {
        return false;
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedmember)
    {
        return null;
    }

    public boolean isAnnotationBundle(Annotation annotation)
    {
        return false;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedclass)
    {
        return null;
    }

    public Boolean isTypeId(AnnotatedMember annotatedmember)
    {
        return null;
    }

    public abstract Version version();
}
