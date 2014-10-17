// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import dagger.Lazy;
import dagger.MembersInjector;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Set;
import javax.inject.Provider;
import javax.inject.Qualifier;

// Referenced classes of package dagger.internal:
//            Memoizer

public final class Keys
{

    Keys()
    {
    }

    private static Type boxIfPrimitive(Type type)
    {
        if(type == Byte.TYPE)
        {
            type = java/lang/Byte;
        } else
        {
            if(type == Short.TYPE)
                return java/lang/Short;
            if(type == Integer.TYPE)
                return java/lang/Integer;
            if(type == Long.TYPE)
                return java/lang/Long;
            if(type == Character.TYPE)
                return java/lang/Character;
            if(type == Boolean.TYPE)
                return java/lang/Boolean;
            if(type == Float.TYPE)
                return java/lang/Float;
            if(type == Double.TYPE)
                return java/lang/Double;
            if(type == Void.TYPE)
                return java/lang/Void;
        }
        return type;
    }

    private static String extractKey(String s, int i, String s1, String s2)
    {
        return (new StringBuilder()).append(s1).append(s.substring(i + s2.length(), -1 + s.length())).toString();
    }

    private static Annotation extractQualifier(Annotation aannotation[], Object obj)
    {
        Annotation annotation = null;
        int i = aannotation.length;
        int j = 0;
        while(j < i) 
        {
            Annotation annotation1 = aannotation[j];
            if(((Boolean)IS_QUALIFIER_ANNOTATION.get(annotation1.annotationType())).booleanValue())
            {
                if(annotation != null)
                    throw new IllegalArgumentException((new StringBuilder()).append("Too many qualifier annotations on ").append(obj).toString());
                annotation = annotation1;
            }
            j++;
        }
        return annotation;
    }

    public static String get(Type type)
    {
        return get(type, null);
    }

    private static String get(Type type, Annotation annotation)
    {
        Type type1 = boxIfPrimitive(type);
        if(annotation == null && (type1 instanceof Class) && !((Class)type1).isArray())
            return ((Class)type1).getName();
        StringBuilder stringbuilder = new StringBuilder();
        if(annotation != null)
            stringbuilder.append(annotation).append("/");
        typeToString(type1, stringbuilder, true);
        return stringbuilder.toString();
    }

    public static String get(Type type, Annotation aannotation[], Object obj)
    {
        return get(type, extractQualifier(aannotation, obj));
    }

    static String getBuiltInBindingsKey(String s)
    {
        int i = startOfType(s);
        if(substringStartsWith(s, i, PROVIDER_PREFIX))
            return extractKey(s, i, s.substring(0, i), PROVIDER_PREFIX);
        if(substringStartsWith(s, i, MEMBERS_INJECTOR_PREFIX))
            return extractKey(s, i, "members/", MEMBERS_INJECTOR_PREFIX);
        else
            return null;
    }

    public static String getClassName(String s)
    {
        int i;
label0:
        {
            if(!s.startsWith("@"))
            {
                boolean flag = s.startsWith("members/");
                i = 0;
                if(!flag)
                    break label0;
            }
            i = 1 + s.lastIndexOf('/');
        }
        if(s.indexOf('<', i) == -1 && s.indexOf('[', i) == -1)
            return s.substring(i);
        else
            return null;
    }

    static String getLazyKey(String s)
    {
        int i = startOfType(s);
        if(substringStartsWith(s, i, LAZY_PREFIX))
            return extractKey(s, i, s.substring(0, i), LAZY_PREFIX);
        else
            return null;
    }

    public static String getMembersKey(Class class1)
    {
        return "members/".concat(class1.getName());
    }

    public static String getSetKey(Type type, Annotation aannotation[], Object obj)
    {
        Annotation annotation = extractQualifier(aannotation, obj);
        Type type1 = boxIfPrimitive(type);
        StringBuilder stringbuilder = new StringBuilder();
        if(annotation != null)
            stringbuilder.append(annotation).append("/");
        stringbuilder.append(SET_PREFIX);
        typeToString(type1, stringbuilder, true);
        stringbuilder.append(">");
        return stringbuilder.toString();
    }

    public static boolean isAnnotated(String s)
    {
        return s.startsWith("@");
    }

    public static boolean isPlatformType(String s)
    {
        return s.startsWith("java.") || s.startsWith("javax.") || s.startsWith("android.");
    }

    private static int startOfType(String s)
    {
        if(s.startsWith("@"))
            return 1 + s.lastIndexOf('/');
        else
            return 0;
    }

    private static boolean substringStartsWith(String s, int i, String s1)
    {
        return s.regionMatches(i, s1, 0, s1.length());
    }

    private static void typeToString(Type type, StringBuilder stringbuilder, boolean flag)
    {
        if(type instanceof Class)
        {
            Class class1 = (Class)type;
            if(class1.isArray())
            {
                typeToString(((Type) (class1.getComponentType())), stringbuilder, false);
                stringbuilder.append("[]");
                return;
            }
            if(class1.isPrimitive())
            {
                if(flag)
                {
                    throw new UnsupportedOperationException((new StringBuilder()).append("Uninjectable type ").append(class1.getName()).toString());
                } else
                {
                    stringbuilder.append(class1.getName());
                    return;
                }
            } else
            {
                stringbuilder.append(class1.getName());
                return;
            }
        }
        if(type instanceof ParameterizedType)
        {
            ParameterizedType parameterizedtype = (ParameterizedType)type;
            typeToString(parameterizedtype.getRawType(), stringbuilder, true);
            Type atype[] = parameterizedtype.getActualTypeArguments();
            stringbuilder.append("<");
            for(int i = 0; i < atype.length; i++)
            {
                if(i != 0)
                    stringbuilder.append(", ");
                typeToString(atype[i], stringbuilder, true);
            }

            stringbuilder.append(">");
            return;
        }
        if(type instanceof GenericArrayType)
        {
            typeToString(((GenericArrayType)type).getGenericComponentType(), stringbuilder, false);
            stringbuilder.append("[]");
            return;
        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Uninjectable type ").append(type).toString());
        }
    }

    private static final Memoizer IS_QUALIFIER_ANNOTATION = new Memoizer() {

        protected Boolean create(Class class1)
        {
            return Boolean.valueOf(class1.isAnnotationPresent(javax/inject/Qualifier));
        }

        protected volatile Object create(Object obj)
        {
            return create((Class)obj);
        }

    }
;
    private static final String LAZY_PREFIX = (new StringBuilder()).append(dagger/Lazy.getCanonicalName()).append("<").toString();
    private static final String MEMBERS_INJECTOR_PREFIX = (new StringBuilder()).append(dagger/MembersInjector.getCanonicalName()).append("<").toString();
    private static final String PROVIDER_PREFIX = (new StringBuilder()).append(javax/inject/Provider.getCanonicalName()).append("<").toString();
    private static final String SET_PREFIX = (new StringBuilder()).append(java/util/Set.getCanonicalName()).append("<").toString();

}
