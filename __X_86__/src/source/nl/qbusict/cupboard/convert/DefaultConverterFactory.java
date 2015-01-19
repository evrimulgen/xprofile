// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard.convert;

import java.util.Map;

// Referenced classes of package nl.qbusict.cupboard.convert:
//            ConverterFactory, DefaultConverter, Converter

public class DefaultConverterFactory
    implements ConverterFactory
{

    public DefaultConverterFactory()
    {
        this(false);
    }

    public DefaultConverterFactory(boolean flag)
    {
        mUseAnnotations = flag;
    }

    public Converter newConverter(Class class1, Map map)
    {
        return new DefaultConverter(class1, map, mUseAnnotations);
    }

    private final boolean mUseAnnotations;
}
