// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Named;
import java.lang.annotation.Annotation;

// Referenced classes of package com.fasterxml.jackson.databind:
//            JsonMappingException, PropertyName, PropertyMetadata, JavaType

public interface BeanProperty
    extends Named
{
    public static class Std
        implements BeanProperty
    {

        public void depositSchemaProperty(JsonObjectFormatVisitor jsonobjectformatvisitor)
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Instances of ").append(getClass().getName()).append(" should not get visited").toString());
        }

        public Annotation getAnnotation(Class class1)
        {
            if(_member == null)
                return null;
            else
                return _member.getAnnotation(class1);
        }

        public Annotation getContextAnnotation(Class class1)
        {
            if(_contextAnnotations == null)
                return null;
            else
                return _contextAnnotations.get(class1);
        }

        public PropertyName getFullName()
        {
            return _name;
        }

        public AnnotatedMember getMember()
        {
            return _member;
        }

        public PropertyMetadata getMetadata()
        {
            return _metadata;
        }

        public String getName()
        {
            return _name.getSimpleName();
        }

        public JavaType getType()
        {
            return _type;
        }

        public PropertyName getWrapperName()
        {
            return _wrapperName;
        }

        public boolean isRequired()
        {
            return _metadata.isRequired();
        }

        public Std withType(JavaType javatype)
        {
            return new Std(_name, javatype, _wrapperName, _contextAnnotations, _member, _metadata);
        }

        protected final Annotations _contextAnnotations;
        protected final AnnotatedMember _member;
        protected final PropertyMetadata _metadata;
        protected final PropertyName _name;
        protected final JavaType _type;
        protected final PropertyName _wrapperName;

        public Std(PropertyName propertyname, JavaType javatype, PropertyName propertyname1, Annotations annotations, AnnotatedMember annotatedmember, PropertyMetadata propertymetadata)
        {
            _name = propertyname;
            _type = javatype;
            _wrapperName = propertyname1;
            _metadata = propertymetadata;
            _member = annotatedmember;
            _contextAnnotations = annotations;
        }

        public Std(String s, JavaType javatype, PropertyName propertyname, Annotations annotations, AnnotatedMember annotatedmember, boolean flag)
        {
            PropertyName propertyname1 = new PropertyName(s);
            PropertyMetadata propertymetadata;
            if(flag)
                propertymetadata = PropertyMetadata.STD_REQUIRED;
            else
                propertymetadata = PropertyMetadata.STD_OPTIONAL;
            this(propertyname1, javatype, propertyname, annotations, annotatedmember, propertymetadata);
        }
    }


    public abstract void depositSchemaProperty(JsonObjectFormatVisitor jsonobjectformatvisitor)
        throws JsonMappingException;

    public abstract Annotation getAnnotation(Class class1);

    public abstract Annotation getContextAnnotation(Class class1);

    public abstract PropertyName getFullName();

    public abstract AnnotatedMember getMember();

    public abstract PropertyMetadata getMetadata();

    public abstract String getName();

    public abstract JavaType getType();

    public abstract PropertyName getWrapperName();

    public abstract boolean isRequired();
}
