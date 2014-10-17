// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import java.util.*;
import nl.qbusict.cupboard.convert.Converter;
import nl.qbusict.cupboard.convert.ConverterHolder;

class BaseCompartment
{

    protected BaseCompartment(Map map)
    {
        mConverters = map;
    }

    protected void createAllConverters()
    {
        for(Iterator iterator = mConverters.values().iterator(); iterator.hasNext(); ((ConverterHolder)iterator.next()).get());
    }

    protected Converter getConverter(Class class1)
    {
        ConverterHolder converterholder = (ConverterHolder)mConverters.get(class1);
        if(converterholder == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.toString()).append(" isn't registered.").toString());
        else
            return converterholder.get();
    }

    protected final Map mConverters;
}
