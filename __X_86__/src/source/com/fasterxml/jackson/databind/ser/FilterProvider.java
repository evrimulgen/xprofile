// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

// Referenced classes of package com.fasterxml.jackson.databind.ser:
//            BeanPropertyFilter, PropertyFilter

public abstract class FilterProvider
{

    public FilterProvider()
    {
    }

    public abstract BeanPropertyFilter findFilter(Object obj);

    public PropertyFilter findPropertyFilter(Object obj, Object obj1)
    {
        BeanPropertyFilter beanpropertyfilter = findFilter(obj);
        if(beanpropertyfilter == null)
            return null;
        else
            return SimpleBeanPropertyFilter.from(beanpropertyfilter);
    }
}
