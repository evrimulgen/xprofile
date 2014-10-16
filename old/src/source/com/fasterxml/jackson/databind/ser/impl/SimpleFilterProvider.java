// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.ser.*;
import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.ser.impl:
//            SimpleBeanPropertyFilter

public class SimpleFilterProvider extends FilterProvider
    implements Serializable
{

    public SimpleFilterProvider()
    {
        this(((Map) (new HashMap())));
    }

    public SimpleFilterProvider(Map map)
    {
        _cfgFailOnUnknownId = true;
        for(Iterator iterator = map.values().iterator(); iterator.hasNext();)
            if(!(iterator.next() instanceof PropertyFilter))
            {
                _filtersById = _convert(map);
                return;
            }

        _filtersById = map;
    }

    private static final PropertyFilter _convert(BeanPropertyFilter beanpropertyfilter)
    {
        return SimpleBeanPropertyFilter.from(beanpropertyfilter);
    }

    private static final Map _convert(Map map)
    {
        HashMap hashmap = new HashMap();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Object obj = entry.getValue();
            if(obj instanceof PropertyFilter)
                hashmap.put(entry.getKey(), (PropertyFilter)obj);
            else
            if(obj instanceof BeanPropertyFilter)
                hashmap.put(entry.getKey(), _convert((BeanPropertyFilter)obj));
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unrecognized filter type (").append(obj.getClass().getName()).append(")").toString());
        }

        return hashmap;
    }

    public SimpleFilterProvider addFilter(String s, BeanPropertyFilter beanpropertyfilter)
    {
        _filtersById.put(s, _convert(beanpropertyfilter));
        return this;
    }

    public SimpleFilterProvider addFilter(String s, PropertyFilter propertyfilter)
    {
        _filtersById.put(s, propertyfilter);
        return this;
    }

    public SimpleFilterProvider addFilter(String s, SimpleBeanPropertyFilter simplebeanpropertyfilter)
    {
        _filtersById.put(s, simplebeanpropertyfilter);
        return this;
    }

    public BeanPropertyFilter findFilter(Object obj)
    {
        throw new UnsupportedOperationException("Access to deprecated filters not supported");
    }

    public PropertyFilter findPropertyFilter(Object obj, Object obj1)
    {
        PropertyFilter propertyfilter = (PropertyFilter)_filtersById.get(obj);
        if(propertyfilter == null)
        {
            propertyfilter = _defaultFilter;
            if(propertyfilter == null && _cfgFailOnUnknownId)
                throw new IllegalArgumentException((new StringBuilder()).append("No filter configured with id '").append(obj).append("' (type ").append(obj.getClass().getName()).append(")").toString());
        }
        return propertyfilter;
    }

    public PropertyFilter getDefaultFilter()
    {
        return _defaultFilter;
    }

    public PropertyFilter removeFilter(String s)
    {
        return (PropertyFilter)_filtersById.remove(s);
    }

    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter beanpropertyfilter)
    {
        _defaultFilter = SimpleBeanPropertyFilter.from(beanpropertyfilter);
        return this;
    }

    public SimpleFilterProvider setDefaultFilter(PropertyFilter propertyfilter)
    {
        _defaultFilter = propertyfilter;
        return this;
    }

    public SimpleFilterProvider setDefaultFilter(SimpleBeanPropertyFilter simplebeanpropertyfilter)
    {
        _defaultFilter = simplebeanpropertyfilter;
        return this;
    }

    public SimpleFilterProvider setFailOnUnknownId(boolean flag)
    {
        _cfgFailOnUnknownId = flag;
        return this;
    }

    public boolean willFailOnUnknownId()
    {
        return _cfgFailOnUnknownId;
    }

    private static final long serialVersionUID = 0xa87d654736710511L;
    protected boolean _cfgFailOnUnknownId;
    protected PropertyFilter _defaultFilter;
    protected final Map _filtersById;
}
