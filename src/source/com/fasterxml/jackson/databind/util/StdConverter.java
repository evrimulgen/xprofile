// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

// Referenced classes of package com.fasterxml.jackson.databind.util:
//            Converter

public abstract class StdConverter
    implements Converter
{

    public StdConverter()
    {
    }

    public abstract Object convert(Object obj);

    public JavaType getInputType(TypeFactory typefactory)
    {
        JavaType ajavatype[] = typefactory.findTypeParameters(getClass(), com/fasterxml/jackson/databind/util/Converter);
        if(ajavatype == null || ajavatype.length < 2)
            throw new IllegalStateException((new StringBuilder()).append("Can not find OUT type parameter for Converter of type ").append(getClass().getName()).toString());
        else
            return ajavatype[0];
    }

    public JavaType getOutputType(TypeFactory typefactory)
    {
        JavaType ajavatype[] = typefactory.findTypeParameters(getClass(), com/fasterxml/jackson/databind/util/Converter);
        if(ajavatype == null || ajavatype.length < 2)
            throw new IllegalStateException((new StringBuilder()).append("Can not find OUT type parameter for Converter of type ").append(getClass().getName()).toString());
        else
            return ajavatype[1];
    }
}
