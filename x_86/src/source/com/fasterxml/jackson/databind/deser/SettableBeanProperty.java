// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.FailingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.NullProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ViewMatcher;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public abstract class SettableBeanProperty
    implements BeanProperty, Serializable
{

    protected SettableBeanProperty(PropertyName propertyname, JavaType javatype, PropertyMetadata propertymetadata, JsonDeserializer jsondeserializer)
    {
        _propertyIndex = -1;
        if(propertyname == null)
            _propName = PropertyName.NO_NAME;
        else
            _propName = propertyname.internSimpleName();
        _type = javatype;
        _wrapperName = null;
        _metadata = propertymetadata;
        _contextAnnotations = null;
        _viewMatcher = null;
        _nullProvider = null;
        _valueTypeDeserializer = null;
        _valueDeserializer = jsondeserializer;
    }

    protected SettableBeanProperty(PropertyName propertyname, JavaType javatype, PropertyName propertyname1, TypeDeserializer typedeserializer, Annotations annotations, PropertyMetadata propertymetadata)
    {
        _propertyIndex = -1;
        if(propertyname == null)
            _propName = PropertyName.NO_NAME;
        else
            _propName = propertyname.internSimpleName();
        _type = javatype;
        _wrapperName = propertyname1;
        _metadata = propertymetadata;
        _contextAnnotations = annotations;
        _viewMatcher = null;
        _nullProvider = null;
        if(typedeserializer != null)
            typedeserializer = typedeserializer.forProperty(this);
        _valueTypeDeserializer = typedeserializer;
        _valueDeserializer = MISSING_VALUE_DESERIALIZER;
    }

    protected SettableBeanProperty(SettableBeanProperty settablebeanproperty)
    {
        _propertyIndex = -1;
        _propName = settablebeanproperty._propName;
        _type = settablebeanproperty._type;
        _wrapperName = settablebeanproperty._wrapperName;
        _metadata = settablebeanproperty._metadata;
        _contextAnnotations = settablebeanproperty._contextAnnotations;
        _valueDeserializer = settablebeanproperty._valueDeserializer;
        _valueTypeDeserializer = settablebeanproperty._valueTypeDeserializer;
        _nullProvider = settablebeanproperty._nullProvider;
        _managedReferenceName = settablebeanproperty._managedReferenceName;
        _propertyIndex = settablebeanproperty._propertyIndex;
        _viewMatcher = settablebeanproperty._viewMatcher;
    }

    protected SettableBeanProperty(SettableBeanProperty settablebeanproperty, JsonDeserializer jsondeserializer)
    {
        _propertyIndex = -1;
        _propName = settablebeanproperty._propName;
        _type = settablebeanproperty._type;
        _wrapperName = settablebeanproperty._wrapperName;
        _metadata = settablebeanproperty._metadata;
        _contextAnnotations = settablebeanproperty._contextAnnotations;
        _valueTypeDeserializer = settablebeanproperty._valueTypeDeserializer;
        _managedReferenceName = settablebeanproperty._managedReferenceName;
        _propertyIndex = settablebeanproperty._propertyIndex;
        if(jsondeserializer == null)
        {
            _nullProvider = null;
            _valueDeserializer = MISSING_VALUE_DESERIALIZER;
        } else
        {
            Object obj = jsondeserializer.getNullValue();
            NullProvider nullprovider = null;
            if(obj != null)
                nullprovider = new NullProvider(_type, obj);
            _nullProvider = nullprovider;
            _valueDeserializer = jsondeserializer;
        }
        _viewMatcher = settablebeanproperty._viewMatcher;
    }

    protected SettableBeanProperty(SettableBeanProperty settablebeanproperty, PropertyName propertyname)
    {
        _propertyIndex = -1;
        _propName = propertyname;
        _type = settablebeanproperty._type;
        _wrapperName = settablebeanproperty._wrapperName;
        _metadata = settablebeanproperty._metadata;
        _contextAnnotations = settablebeanproperty._contextAnnotations;
        _valueDeserializer = settablebeanproperty._valueDeserializer;
        _valueTypeDeserializer = settablebeanproperty._valueTypeDeserializer;
        _nullProvider = settablebeanproperty._nullProvider;
        _managedReferenceName = settablebeanproperty._managedReferenceName;
        _propertyIndex = settablebeanproperty._propertyIndex;
        _viewMatcher = settablebeanproperty._viewMatcher;
    }

    protected SettableBeanProperty(SettableBeanProperty settablebeanproperty, String s)
    {
        this(settablebeanproperty, new PropertyName(s));
    }

    protected SettableBeanProperty(BeanPropertyDefinition beanpropertydefinition, JavaType javatype, TypeDeserializer typedeserializer, Annotations annotations)
    {
        this(beanpropertydefinition.getFullName(), javatype, beanpropertydefinition.getWrapperName(), typedeserializer, annotations, beanpropertydefinition.getMetadata());
    }

    protected SettableBeanProperty(String s, JavaType javatype, PropertyName propertyname, TypeDeserializer typedeserializer, Annotations annotations)
    {
        this(new PropertyName(s), javatype, propertyname, typedeserializer, annotations, PropertyMetadata.STD_OPTIONAL);
    }

    protected SettableBeanProperty(String s, JavaType javatype, PropertyName propertyname, TypeDeserializer typedeserializer, Annotations annotations, boolean flag)
    {
        this(new PropertyName(s), javatype, propertyname, typedeserializer, annotations, PropertyMetadata.construct(flag, null));
    }

    protected IOException _throwAsIOE(Exception exception)
        throws IOException
    {
        if(exception instanceof IOException)
            throw (IOException)exception;
        if(exception instanceof RuntimeException)
            throw (RuntimeException)exception;
        for(; exception.getCause() != null; exception = exception.getCause());
        throw new JsonMappingException(exception.getMessage(), null, exception);
    }

    protected void _throwAsIOE(Exception exception, Object obj)
        throws IOException
    {
        if(exception instanceof IllegalArgumentException)
        {
            String s;
            StringBuilder stringbuilder;
            String s1;
            if(obj == null)
                s = "[NULL]";
            else
                s = obj.getClass().getName();
            stringbuilder = (new StringBuilder("Problem deserializing property '")).append(getName());
            stringbuilder.append("' (expected type: ").append(getType());
            stringbuilder.append("; actual type: ").append(s).append(")");
            s1 = exception.getMessage();
            if(s1 != null)
                stringbuilder.append(", problem: ").append(s1);
            else
                stringbuilder.append(" (no error message provided)");
            throw new JsonMappingException(stringbuilder.toString(), null, exception);
        } else
        {
            _throwAsIOE(exception);
            return;
        }
    }

    public void assignIndex(int i)
    {
        if(_propertyIndex != -1)
        {
            throw new IllegalStateException((new StringBuilder()).append("Property '").append(getName()).append("' already had index (").append(_propertyIndex).append("), trying to assign ").append(i).toString());
        } else
        {
            _propertyIndex = i;
            return;
        }
    }

    public void depositSchemaProperty(JsonObjectFormatVisitor jsonobjectformatvisitor)
        throws JsonMappingException
    {
        if(isRequired())
        {
            jsonobjectformatvisitor.property(this);
            return;
        } else
        {
            jsonobjectformatvisitor.optionalProperty(this);
            return;
        }
    }

    public final Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
            if(_nullProvider == null)
                return null;
            else
                return _nullProvider.nullValue(deserializationcontext);
        if(_valueTypeDeserializer != null)
            return _valueDeserializer.deserializeWithType(jsonparser, deserializationcontext, _valueTypeDeserializer);
        else
            return _valueDeserializer.deserialize(jsonparser, deserializationcontext);
    }

    public abstract void deserializeAndSet(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException;

    public abstract Object deserializeSetAndReturn(JsonParser jsonparser, DeserializationContext deserializationcontext, Object obj)
        throws IOException, JsonProcessingException;

    public abstract Annotation getAnnotation(Class class1);

    public Annotation getContextAnnotation(Class class1)
    {
        return _contextAnnotations.get(class1);
    }

    public int getCreatorIndex()
    {
        return -1;
    }

    protected final Class getDeclaringClass()
    {
        return getMember().getDeclaringClass();
    }

    public PropertyName getFullName()
    {
        return _propName;
    }

    public Object getInjectableValueId()
    {
        return null;
    }

    public String getManagedReferenceName()
    {
        return _managedReferenceName;
    }

    public abstract AnnotatedMember getMember();

    public PropertyMetadata getMetadata()
    {
        return _metadata;
    }

    public final String getName()
    {
        return _propName.getSimpleName();
    }

    public int getPropertyIndex()
    {
        return _propertyIndex;
    }

    public JavaType getType()
    {
        return _type;
    }

    public JsonDeserializer getValueDeserializer()
    {
        JsonDeserializer jsondeserializer = _valueDeserializer;
        if(jsondeserializer == MISSING_VALUE_DESERIALIZER)
            jsondeserializer = null;
        return jsondeserializer;
    }

    public TypeDeserializer getValueTypeDeserializer()
    {
        return _valueTypeDeserializer;
    }

    public PropertyName getWrapperName()
    {
        return _wrapperName;
    }

    public boolean hasValueDeserializer()
    {
        return _valueDeserializer != null && _valueDeserializer != MISSING_VALUE_DESERIALIZER;
    }

    public boolean hasValueTypeDeserializer()
    {
        return _valueTypeDeserializer != null;
    }

    public boolean hasViews()
    {
        return _viewMatcher != null;
    }

    public boolean isRequired()
    {
        return _metadata.isRequired();
    }

    public abstract void set(Object obj, Object obj1)
        throws IOException;

    public abstract Object setAndReturn(Object obj, Object obj1)
        throws IOException;

    public void setManagedReferenceName(String s)
    {
        _managedReferenceName = s;
    }

    public void setViews(Class aclass[])
    {
        if(aclass == null)
        {
            _viewMatcher = null;
            return;
        } else
        {
            _viewMatcher = ViewMatcher.construct(aclass);
            return;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[property '").append(getName()).append("']").toString();
    }

    public boolean visibleInView(Class class1)
    {
        return _viewMatcher == null || _viewMatcher.isVisibleForView(class1);
    }

    public abstract SettableBeanProperty withName(PropertyName propertyname);

    public SettableBeanProperty withName(String s)
    {
        return withName(new PropertyName(s));
    }

    public SettableBeanProperty withSimpleName(String s)
    {
        PropertyName propertyname;
        if(_propName == null)
            propertyname = new PropertyName(s);
        else
            propertyname = _propName.withSimpleName(s);
        if(propertyname == _propName)
            return this;
        else
            return withName(propertyname);
    }

    public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer jsondeserializer);

    protected static final JsonDeserializer MISSING_VALUE_DESERIALIZER = new FailingDeserializer("No _valueDeserializer assigned");
    private static final long serialVersionUID = 0xf1c0dac671bc3573L;
    protected final transient Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected final PropertyMetadata _metadata;
    protected final NullProvider _nullProvider;
    protected final PropertyName _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected final JsonDeserializer _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected ViewMatcher _viewMatcher;
    protected final PropertyName _wrapperName;

}
