// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.Annotations;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            SettableBeanProperty, BeanDeserializer, AbstractDeserializer, BuilderBasedDeserializer, 
//            SettableAnyProperty, ValueInstantiator

public class BeanDeserializerBuilder
{

    public BeanDeserializerBuilder(BeanDescription beandescription, DeserializationConfig deserializationconfig)
    {
        _properties = new LinkedHashMap();
        _beanDesc = beandescription;
        _defaultViewInclusion = deserializationconfig.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
    }

    protected BeanDeserializerBuilder(BeanDeserializerBuilder beandeserializerbuilder)
    {
        _properties = new LinkedHashMap();
        _beanDesc = beandeserializerbuilder._beanDesc;
        _defaultViewInclusion = beandeserializerbuilder._defaultViewInclusion;
        _anySetter = beandeserializerbuilder._anySetter;
        _ignoreAllUnknown = beandeserializerbuilder._ignoreAllUnknown;
        _properties.putAll(beandeserializerbuilder._properties);
        _backRefProperties = _copy(beandeserializerbuilder._backRefProperties);
        _ignorableProps = beandeserializerbuilder._ignorableProps;
        _valueInstantiator = beandeserializerbuilder._valueInstantiator;
        _objectIdReader = beandeserializerbuilder._objectIdReader;
        _buildMethod = beandeserializerbuilder._buildMethod;
        _builderConfig = beandeserializerbuilder._builderConfig;
    }

    private static HashMap _copy(HashMap hashmap)
    {
        if(hashmap == null)
            return null;
        else
            return new HashMap(hashmap);
    }

    public void addBackReferenceProperty(String s, SettableBeanProperty settablebeanproperty)
    {
        if(_backRefProperties == null)
            _backRefProperties = new HashMap(4);
        _backRefProperties.put(s, settablebeanproperty);
        if(_properties != null)
            _properties.remove(settablebeanproperty.getName());
    }

    public void addCreatorProperty(SettableBeanProperty settablebeanproperty)
    {
        addProperty(settablebeanproperty);
    }

    public void addIgnorable(String s)
    {
        if(_ignorableProps == null)
            _ignorableProps = new HashSet();
        _ignorableProps.add(s);
    }

    public void addInjectable(PropertyName propertyname, JavaType javatype, Annotations annotations, AnnotatedMember annotatedmember, Object obj)
    {
        if(_injectables == null)
            _injectables = new ArrayList();
        _injectables.add(new ValueInjector(propertyname, javatype, annotations, annotatedmember, obj));
    }

    public void addInjectable(String s, JavaType javatype, Annotations annotations, AnnotatedMember annotatedmember, Object obj)
    {
        addInjectable(new PropertyName(s), javatype, annotations, annotatedmember, obj);
    }

    public void addOrReplaceProperty(SettableBeanProperty settablebeanproperty, boolean flag)
    {
        _properties.put(settablebeanproperty.getName(), settablebeanproperty);
    }

    public void addProperty(SettableBeanProperty settablebeanproperty)
    {
        SettableBeanProperty settablebeanproperty1 = (SettableBeanProperty)_properties.put(settablebeanproperty.getName(), settablebeanproperty);
        if(settablebeanproperty1 != null && settablebeanproperty1 != settablebeanproperty)
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate property '").append(settablebeanproperty.getName()).append("' for ").append(_beanDesc.getType()).toString());
        else
            return;
    }

    public JsonDeserializer build()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        Collection collection = _properties.values();
        BeanPropertyMap beanpropertymap = new BeanPropertyMap(collection);
        beanpropertymap.assignIndexes();
        Iterator iterator;
        if(!_defaultViewInclusion)
            flag1 = flag;
        else
            flag1 = false;
        if(flag1) goto _L2; else goto _L1
_L1:
        iterator = collection.iterator();
_L5:
        if(!iterator.hasNext()) goto _L2; else goto _L3
_L3:
        if(!((SettableBeanProperty)iterator.next()).hasViews()) goto _L5; else goto _L4
_L4:
        if(_objectIdReader != null)
            beanpropertymap = beanpropertymap.withProperty(new ObjectIdValueProperty(_objectIdReader, PropertyMetadata.STD_REQUIRED));
        return new BeanDeserializer(this, _beanDesc, beanpropertymap, _backRefProperties, _ignorableProps, _ignoreAllUnknown, flag);
_L2:
        flag = flag1;
        if(true) goto _L4; else goto _L6
_L6:
    }

    public AbstractDeserializer buildAbstract()
    {
        return new AbstractDeserializer(this, _beanDesc, _backRefProperties);
    }

    public JsonDeserializer buildBuilderBased(JavaType javatype, String s)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        if(_buildMethod == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Builder class ").append(_beanDesc.getBeanClass().getName()).append(" does not have build method '").append(s).append("()'").toString());
        Class class1 = _buildMethod.getRawReturnType();
        if(!javatype.getRawClass().isAssignableFrom(class1))
            throw new IllegalArgumentException((new StringBuilder()).append("Build method '").append(_buildMethod.getFullName()).append(" has bad return type (").append(class1.getName()).append("), not compatible with POJO type (").append(javatype.getRawClass().getName()).append(")").toString());
        Collection collection = _properties.values();
        BeanPropertyMap beanpropertymap = new BeanPropertyMap(collection);
        beanpropertymap.assignIndexes();
        Iterator iterator;
        if(!_defaultViewInclusion)
            flag1 = flag;
        else
            flag1 = false;
        if(flag1) goto _L2; else goto _L1
_L1:
        iterator = collection.iterator();
_L5:
        if(!iterator.hasNext()) goto _L2; else goto _L3
_L3:
        if(!((SettableBeanProperty)iterator.next()).hasViews()) goto _L5; else goto _L4
_L4:
        if(_objectIdReader != null)
            beanpropertymap = beanpropertymap.withProperty(new ObjectIdValueProperty(_objectIdReader, PropertyMetadata.STD_REQUIRED));
        return new BuilderBasedDeserializer(this, _beanDesc, beanpropertymap, _backRefProperties, _ignorableProps, _ignoreAllUnknown, flag);
_L2:
        flag = flag1;
        if(true) goto _L4; else goto _L6
_L6:
    }

    public SettableBeanProperty findProperty(PropertyName propertyname)
    {
        return (SettableBeanProperty)_properties.get(propertyname.getSimpleName());
    }

    public SettableBeanProperty findProperty(String s)
    {
        return (SettableBeanProperty)_properties.get(s);
    }

    public SettableAnyProperty getAnySetter()
    {
        return _anySetter;
    }

    public AnnotatedMethod getBuildMethod()
    {
        return _buildMethod;
    }

    public com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value getBuilderConfig()
    {
        return _builderConfig;
    }

    public List getInjectables()
    {
        return _injectables;
    }

    public ObjectIdReader getObjectIdReader()
    {
        return _objectIdReader;
    }

    public Iterator getProperties()
    {
        return _properties.values().iterator();
    }

    public ValueInstantiator getValueInstantiator()
    {
        return _valueInstantiator;
    }

    public boolean hasProperty(PropertyName propertyname)
    {
        return findProperty(propertyname) != null;
    }

    public boolean hasProperty(String s)
    {
        return findProperty(s) != null;
    }

    public SettableBeanProperty removeProperty(PropertyName propertyname)
    {
        return (SettableBeanProperty)_properties.remove(propertyname.getSimpleName());
    }

    public SettableBeanProperty removeProperty(String s)
    {
        return (SettableBeanProperty)_properties.remove(s);
    }

    public void setAnySetter(SettableAnyProperty settableanyproperty)
    {
        if(_anySetter != null && settableanyproperty != null)
        {
            throw new IllegalStateException("_anySetter already set to non-null");
        } else
        {
            _anySetter = settableanyproperty;
            return;
        }
    }

    public void setIgnoreUnknownProperties(boolean flag)
    {
        _ignoreAllUnknown = flag;
    }

    public void setObjectIdReader(ObjectIdReader objectidreader)
    {
        _objectIdReader = objectidreader;
    }

    public void setPOJOBuilder(AnnotatedMethod annotatedmethod, com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value value)
    {
        _buildMethod = annotatedmethod;
        _builderConfig = value;
    }

    public void setValueInstantiator(ValueInstantiator valueinstantiator)
    {
        _valueInstantiator = valueinstantiator;
    }

    protected SettableAnyProperty _anySetter;
    protected HashMap _backRefProperties;
    protected final BeanDescription _beanDesc;
    protected AnnotatedMethod _buildMethod;
    protected com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value _builderConfig;
    protected final boolean _defaultViewInclusion;
    protected HashSet _ignorableProps;
    protected boolean _ignoreAllUnknown;
    protected List _injectables;
    protected ObjectIdReader _objectIdReader;
    protected final Map _properties;
    protected ValueInstantiator _valueInstantiator;
}
