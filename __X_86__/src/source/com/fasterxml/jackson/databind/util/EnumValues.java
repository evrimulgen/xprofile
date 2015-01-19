// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.util:
//            ClassUtil

public final class EnumValues
{

    private EnumValues(Class class1, Map map)
    {
        _enumClass = class1;
        _values = new EnumMap(map);
    }

    public static EnumValues construct(Class class1, AnnotationIntrospector annotationintrospector)
    {
        return constructFromName(class1, annotationintrospector);
    }

    public static EnumValues constructFromName(Class class1, AnnotationIntrospector annotationintrospector)
    {
        Enum aenum[] = (Enum[])ClassUtil.findEnumType(class1).getEnumConstants();
        if(aenum != null)
        {
            HashMap hashmap = new HashMap();
            int i = aenum.length;
            for(int j = 0; j < i; j++)
            {
                Enum enum = aenum[j];
                hashmap.put(enum, new SerializedString(annotationintrospector.findEnumValue(enum)));
            }

            return new EnumValues(class1, hashmap);
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not determine enum constants for Class ").append(class1.getName()).toString());
        }
    }

    public static EnumValues constructFromToString(Class class1, AnnotationIntrospector annotationintrospector)
    {
        Enum aenum[] = (Enum[])ClassUtil.findEnumType(class1).getEnumConstants();
        if(aenum != null)
        {
            HashMap hashmap = new HashMap();
            int i = aenum.length;
            for(int j = 0; j < i; j++)
            {
                Enum enum = aenum[j];
                hashmap.put(enum, new SerializedString(enum.toString()));
            }

            return new EnumValues(class1, hashmap);
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can not determine enum constants for Class ").append(class1.getName()).toString());
        }
    }

    public Class getEnumClass()
    {
        return _enumClass;
    }

    public EnumMap internalMap()
    {
        return _values;
    }

    public SerializedString serializedValueFor(Enum enum)
    {
        return (SerializedString)_values.get(enum);
    }

    public Collection values()
    {
        return _values.values();
    }

    private final Class _enumClass;
    private final EnumMap _values;
}
