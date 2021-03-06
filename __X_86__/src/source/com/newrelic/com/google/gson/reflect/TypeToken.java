// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.reflect;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class TypeToken
{

    protected TypeToken()
    {
        type = getSuperclassTypeParameter(getClass());
        rawType = com.newrelic.com.google.gson.internal..Gson.Types.getRawType(type);
        hashCode = type.hashCode();
    }

    TypeToken(Type type1)
    {
        type = com.newrelic.com.google.gson.internal..Gson.Types.canonicalize((Type)com.newrelic.com.google.gson.internal..Gson.Preconditions.checkNotNull(type1));
        rawType = com.newrelic.com.google.gson.internal..Gson.Types.getRawType(type);
        hashCode = type.hashCode();
    }

    private static transient AssertionError buildUnexpectedTypeError(Type type1, Class aclass[])
    {
        StringBuilder stringbuilder = new StringBuilder("Unexpected type. Expected one of: ");
        int i = aclass.length;
        for(int j = 0; j < i; j++)
            stringbuilder.append(aclass[j].getName()).append(", ");

        stringbuilder.append("but got: ").append(type1.getClass().getName()).append(", for type token: ").append(type1.toString()).append('.');
        return new AssertionError(stringbuilder.toString());
    }

    public static TypeToken get(Class class1)
    {
        return new TypeToken(class1);
    }

    public static TypeToken get(Type type1)
    {
        return new TypeToken(type1);
    }

    static Type getSuperclassTypeParameter(Class class1)
    {
        Type type1 = class1.getGenericSuperclass();
        if(type1 instanceof Class)
            throw new RuntimeException("Missing type parameter.");
        else
            return com.newrelic.com.google.gson.internal..Gson.Types.canonicalize(((ParameterizedType)type1).getActualTypeArguments()[0]);
    }

    private static boolean isAssignableFrom(Type type1, GenericArrayType genericarraytype)
    {
        Type type2 = genericarraytype.getGenericComponentType();
        if(!(type2 instanceof ParameterizedType)) goto _L2; else goto _L1
_L1:
        Object obj = type1;
        if(!(type1 instanceof GenericArrayType)) goto _L4; else goto _L3
_L3:
        obj = ((GenericArrayType)type1).getGenericComponentType();
_L5:
        return isAssignableFrom(((Type) (obj)), (ParameterizedType)type2, ((Map) (new HashMap())));
_L4:
        if(type1 instanceof Class)
        {
            Class class1;
            for(class1 = (Class)type1; class1.isArray(); class1 = class1.getComponentType());
            obj = class1;
        }
        if(true) goto _L5; else goto _L2
_L2:
        return true;
    }

    private static boolean isAssignableFrom(Type type1, ParameterizedType parameterizedtype, Map map)
    {
        if(type1 == null)
            return false;
        if(parameterizedtype.equals(type1))
            return true;
        Class class1 = com.newrelic.com.google.gson.internal..Gson.Types.getRawType(type1);
        boolean flag = type1 instanceof ParameterizedType;
        ParameterizedType parameterizedtype1 = null;
        if(flag)
            parameterizedtype1 = (ParameterizedType)type1;
        if(parameterizedtype1 != null)
        {
            Type atype1[] = parameterizedtype1.getActualTypeArguments();
            TypeVariable atypevariable[] = class1.getTypeParameters();
            for(int k = 0; k < atype1.length; k++)
            {
                Type type2 = atype1[k];
                TypeVariable typevariable = atypevariable[k];
                for(; type2 instanceof TypeVariable; type2 = (Type)map.get(((TypeVariable)type2).getName()));
                map.put(typevariable.getName(), type2);
            }

            if(typeEquals(parameterizedtype1, parameterizedtype, map))
                return true;
        }
        Type atype[] = class1.getGenericInterfaces();
        int i = atype.length;
        for(int j = 0; j < i; j++)
            if(isAssignableFrom(atype[j], parameterizedtype, ((Map) (new HashMap(map)))))
                return true;

        return isAssignableFrom(class1.getGenericSuperclass(), parameterizedtype, ((Map) (new HashMap(map))));
    }

    private static boolean matches(Type type1, Type type2, Map map)
    {
        return type2.equals(type1) || (type1 instanceof TypeVariable) && type2.equals(map.get(((TypeVariable)type1).getName()));
    }

    private static boolean typeEquals(ParameterizedType parameterizedtype, ParameterizedType parameterizedtype1, Map map)
    {
        if(!parameterizedtype.getRawType().equals(parameterizedtype1.getRawType())) goto _L2; else goto _L1
_L1:
        Type atype[];
        Type atype1[];
        int i;
        atype = parameterizedtype.getActualTypeArguments();
        atype1 = parameterizedtype1.getActualTypeArguments();
        i = 0;
_L5:
        if(i >= atype.length)
            break; /* Loop/switch isn't completed */
        if(matches(atype[i], atype1[i], map)) goto _L3; else goto _L2
_L2:
        return false;
_L3:
        i++;
        if(true) goto _L5; else goto _L4
_L4:
        return true;
    }

    public final boolean equals(Object obj)
    {
        return (obj instanceof TypeToken) && com.newrelic.com.google.gson.internal..Gson.Types.equals(type, ((TypeToken)obj).type);
    }

    public final Class getRawType()
    {
        return rawType;
    }

    public final Type getType()
    {
        return type;
    }

    public final int hashCode()
    {
        return hashCode;
    }

    public boolean isAssignableFrom(TypeToken typetoken)
    {
        return isAssignableFrom(typetoken.getType());
    }

    public boolean isAssignableFrom(Class class1)
    {
        return isAssignableFrom(((Type) (class1)));
    }

    public boolean isAssignableFrom(Type type1)
    {
        if(type1 == null)
            return false;
        if(type.equals(type1))
            return true;
        if(type instanceof Class)
            return rawType.isAssignableFrom(com.newrelic.com.google.gson.internal..Gson.Types.getRawType(type1));
        if(type instanceof ParameterizedType)
            return isAssignableFrom(type1, (ParameterizedType)type, ((Map) (new HashMap())));
        if(type instanceof GenericArrayType)
        {
            boolean flag;
            if(rawType.isAssignableFrom(com.newrelic.com.google.gson.internal..Gson.Types.getRawType(type1)) && isAssignableFrom(type1, (GenericArrayType)type))
                flag = true;
            else
                flag = false;
            return flag;
        } else
        {
            throw buildUnexpectedTypeError(type, new Class[] {
                java/lang/Class, java/lang/reflect/ParameterizedType, java/lang/reflect/GenericArrayType
            });
        }
    }

    public final String toString()
    {
        return com.newrelic.com.google.gson.internal..Gson.Types.typeToString(type);
    }

    final int hashCode;
    final Class rawType;
    final Type type;
}
