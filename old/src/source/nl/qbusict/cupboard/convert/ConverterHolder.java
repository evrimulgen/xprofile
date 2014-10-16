// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard.convert;

import java.util.Map;

// Referenced classes of package nl.qbusict.cupboard.convert:
//            ConverterFactory, Converter

public class ConverterHolder
{

    public ConverterHolder(Class class1, ConverterFactory converterfactory, Map map)
    {
        mEntityClass = class1;
        mFactory = converterfactory;
        mEntities = map;
    }

    public Converter get()
    {
        if(mTranslator == null)
            mTranslator = mFactory.newConverter(mEntityClass, mEntities);
        return mTranslator;
    }

    private final Map mEntities;
    private final Class mEntityClass;
    private final ConverterFactory mFactory;
    private Converter mTranslator;
}
