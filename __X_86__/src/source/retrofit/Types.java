// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.NoSuchElementException;

final class Types
{
    private static final class GenericArrayTypeImpl
        implements GenericArrayType
    {

        public boolean equals(Object obj)
        {
            return (obj instanceof GenericArrayType) && Types.equals(this, (GenericArrayType)obj);
        }

        public Type getGenericComponentType()
        {
            return componentType;
        }

        public int hashCode()
        {
            return componentType.hashCode();
        }

        public String toString()
        {
            return (new StringBuilder()).append(Types.typeToString(componentType)).append("[]").toString();
        }

        private final Type componentType;

        public GenericArrayTypeImpl(Type type)
        {
            componentType = type;
        }
    }

    private static final class ParameterizedTypeImpl
        implements ParameterizedType
    {

        public boolean equals(Object obj)
        {
            return (obj instanceof ParameterizedType) && Types.equals(this, (ParameterizedType)obj);
        }

        public Type[] getActualTypeArguments()
        {
            return (Type[])typeArguments.clone();
        }

        public Type getOwnerType()
        {
            return ownerType;
        }

        public Type getRawType()
        {
            return rawType;
        }

        public int hashCode()
        {
            return Arrays.hashCode(typeArguments) ^ rawType.hashCode() ^ Types.hashCodeOrZero(ownerType);
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(30 * (1 + typeArguments.length));
            stringbuilder.append(Types.typeToString(rawType));
            if(typeArguments.length == 0)
                return stringbuilder.toString();
            stringbuilder.append("<").append(Types.typeToString(typeArguments[0]));
            for(int i = 1; i < typeArguments.length; i++)
                stringbuilder.append(", ").append(Types.typeToString(typeArguments[i]));

            return stringbuilder.append(">").toString();
        }

        private final Type ownerType;
        private final Type rawType;
        private final Type typeArguments[];

        public transient ParameterizedTypeImpl(Type type, Type type1, Type atype[])
        {
            boolean flag = true;
            int i = 0;
            super();
            if(type1 instanceof Class)
            {
                boolean flag1;
                if(type == null)
                    flag1 = flag;
                else
                    flag1 = false;
                if(((Class)type1).getEnclosingClass() != null)
                    flag = false;
                if(flag1 != flag)
                    throw new IllegalArgumentException();
            }
            ownerType = type;
            rawType = type1;
            typeArguments = (Type[])atype.clone();
            Type atype1[] = typeArguments;
            for(int j = atype1.length; i < j; i++)
            {
                Type type2 = atype1[i];
                if(type2 == null)
                    throw new NullPointerException();
                Types.checkNotPrimitive(type2);
            }

        }
    }

    private static final class WildcardTypeImpl
        implements WildcardType
    {

        public boolean equals(Object obj)
        {
            return (obj instanceof WildcardType) && Types.equals(this, (WildcardType)obj);
        }

        public Type[] getLowerBounds()
        {
            if(lowerBound != null)
            {
                Type atype[] = new Type[1];
                atype[0] = lowerBound;
                return atype;
            } else
            {
                return Types.EMPTY_TYPE_ARRAY;
            }
        }

        public Type[] getUpperBounds()
        {
            Type atype[] = new Type[1];
            atype[0] = upperBound;
            return atype;
        }

        public int hashCode()
        {
            int i;
            if(lowerBound != null)
                i = 31 + lowerBound.hashCode();
            else
                i = 1;
            return i ^ 31 + upperBound.hashCode();
        }

        public String toString()
        {
            if(lowerBound != null)
                return (new StringBuilder()).append("? super ").append(Types.typeToString(lowerBound)).toString();
            if(upperBound == java/lang/Object)
                return "?";
            else
                return (new StringBuilder()).append("? extends ").append(Types.typeToString(upperBound)).toString();
        }

        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type atype[], Type atype1[])
        {
            if(atype1.length > 1)
                throw new IllegalArgumentException();
            if(atype.length != 1)
                throw new IllegalArgumentException();
            if(atype1.length == 1)
            {
                if(atype1[0] == null)
                    throw new NullPointerException();
                Types.checkNotPrimitive(atype1[0]);
                if(atype[0] != java/lang/Object)
                {
                    throw new IllegalArgumentException();
                } else
                {
                    lowerBound = atype1[0];
                    upperBound = java/lang/Object;
                    return;
                }
            }
            if(atype[0] == null)
            {
                throw new NullPointerException();
            } else
            {
                Types.checkNotPrimitive(atype[0]);
                lowerBound = null;
                upperBound = atype[0];
                return;
            }
        }
    }


    private Types()
    {
    }

    private static void checkNotPrimitive(Type type)
    {
        if((type instanceof Class) && ((Class)type).isPrimitive())
            throw new IllegalArgumentException();
        else
            return;
    }

    private static Class declaringClassOf(TypeVariable typevariable)
    {
        java.lang.reflect.GenericDeclaration genericdeclaration = typevariable.getGenericDeclaration();
        if(genericdeclaration instanceof Class)
            return (Class)genericdeclaration;
        else
            return null;
    }

    private static boolean equal(Object obj, Object obj1)
    {
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    public static boolean equals(Type type, Type type1)
    {
        boolean flag = true;
        if(type != type1) goto _L2; else goto _L1
_L1:
        boolean flag2 = flag;
_L4:
        return flag2;
_L2:
        if(type instanceof Class)
            return type.equals(type1);
        if(!(type instanceof ParameterizedType))
            break; /* Loop/switch isn't completed */
        boolean flag6 = type1 instanceof ParameterizedType;
        flag2 = false;
        if(flag6)
        {
            ParameterizedType parameterizedtype = (ParameterizedType)type;
            ParameterizedType parameterizedtype1 = (ParameterizedType)type1;
            if(!equal(parameterizedtype.getOwnerType(), parameterizedtype1.getOwnerType()) || !parameterizedtype.getRawType().equals(parameterizedtype1.getRawType()) || !Arrays.equals(parameterizedtype.getActualTypeArguments(), parameterizedtype1.getActualTypeArguments()))
                flag = false;
            return flag;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(!(type instanceof GenericArrayType))
            break; /* Loop/switch isn't completed */
        boolean flag5 = type1 instanceof GenericArrayType;
        flag2 = false;
        if(flag5)
        {
            GenericArrayType genericarraytype = (GenericArrayType)type;
            GenericArrayType genericarraytype1 = (GenericArrayType)type1;
            return equals(genericarraytype.getGenericComponentType(), genericarraytype1.getGenericComponentType());
        }
        if(true) goto _L4; else goto _L5
_L5:
        if(!(type instanceof WildcardType))
            break; /* Loop/switch isn't completed */
        boolean flag4 = type1 instanceof WildcardType;
        flag2 = false;
        if(flag4)
        {
            WildcardType wildcardtype = (WildcardType)type;
            WildcardType wildcardtype1 = (WildcardType)type1;
            if(!Arrays.equals(wildcardtype.getUpperBounds(), wildcardtype1.getUpperBounds()) || !Arrays.equals(wildcardtype.getLowerBounds(), wildcardtype1.getLowerBounds()))
                flag = false;
            return flag;
        }
        if(true) goto _L4; else goto _L6
_L6:
        boolean flag1 = type instanceof TypeVariable;
        flag2 = false;
        if(flag1)
        {
            boolean flag3 = type1 instanceof TypeVariable;
            flag2 = false;
            if(flag3)
            {
                TypeVariable typevariable = (TypeVariable)type;
                TypeVariable typevariable1 = (TypeVariable)type1;
                if(typevariable.getGenericDeclaration() != typevariable1.getGenericDeclaration() || !typevariable.getName().equals(typevariable1.getName()))
                    flag = false;
                return flag;
            }
        }
        if(true) goto _L4; else goto _L7
_L7:
    }

    static Type getGenericSupertype(Type type, Class class1, Class class2)
    {
        if(class2 == class1)
            return type;
        if(class2.isInterface())
        {
            Class aclass[] = class1.getInterfaces();
            int i = 0;
            for(int j = aclass.length; i < j; i++)
            {
                if(aclass[i] == class2)
                    return class1.getGenericInterfaces()[i];
                if(class2.isAssignableFrom(aclass[i]))
                    return getGenericSupertype(class1.getGenericInterfaces()[i], aclass[i], class2);
            }

        }
        if(!class1.isInterface())
        {
            Class class3;
            for(; class1 != java/lang/Object; class1 = class3)
            {
                class3 = class1.getSuperclass();
                if(class3 == class2)
                    return class1.getGenericSuperclass();
                if(class2.isAssignableFrom(class3))
                    return getGenericSupertype(class1.getGenericSuperclass(), class3, class2);
            }

        }
        return class2;
    }

    public static Class getRawType(Type type)
    {
        if(type instanceof Class)
            return (Class)type;
        if(type instanceof ParameterizedType)
        {
            Type type1 = ((ParameterizedType)type).getRawType();
            if(!(type1 instanceof Class))
                throw new IllegalArgumentException();
            else
                return (Class)type1;
        }
        if(type instanceof GenericArrayType)
            return Array.newInstance(getRawType(((GenericArrayType)type).getGenericComponentType()), 0).getClass();
        if(type instanceof TypeVariable)
            return java/lang/Object;
        if(type instanceof WildcardType)
            return getRawType(((WildcardType)type).getUpperBounds()[0]);
        String s;
        if(type == null)
            s = "null";
        else
            s = type.getClass().getName();
        throw new IllegalArgumentException((new StringBuilder()).append("Expected a Class, ParameterizedType, or GenericArrayType, but <").append(type).append("> is of type ").append(s).toString());
    }

    public static Type getSupertype(Type type, Class class1, Class class2)
    {
        if(!class2.isAssignableFrom(class1))
            throw new IllegalArgumentException();
        else
            return resolve(type, class1, getGenericSupertype(type, class1, class2));
    }

    private static int hashCodeOrZero(Object obj)
    {
        if(obj != null)
            return obj.hashCode();
        else
            return 0;
    }

    private static int indexOf(Object aobj[], Object obj)
    {
        for(int i = 0; i < aobj.length; i++)
            if(obj.equals(aobj[i]))
                return i;

        throw new NoSuchElementException();
    }

    public static Type resolve(Type type, Class class1, Type type1)
    {
_L4:
        if(!(type1 instanceof TypeVariable)) goto _L2; else goto _L1
_L1:
        TypeVariable typevariable;
        typevariable = (TypeVariable)type1;
        type1 = resolveTypeVariable(type, class1, typevariable);
        if(type1 != typevariable) goto _L4; else goto _L3
_L3:
        Object obj = type1;
_L6:
        return ((Type) (obj));
_L2:
        if((type1 instanceof Class) && ((Class)type1).isArray())
        {
            Object obj1 = (Class)type1;
            Class class2 = ((Class) (obj1)).getComponentType();
            Type type9 = resolve(type, class1, ((Type) (class2)));
            if(class2 != type9)
                obj1 = new GenericArrayTypeImpl(type9);
            return ((Type) (obj1));
        }
        if(!(type1 instanceof GenericArrayType))
            break; /* Loop/switch isn't completed */
        obj = (GenericArrayType)type1;
        Type type7 = ((GenericArrayType) (obj)).getGenericComponentType();
        Type type8 = resolve(type, class1, type7);
        if(type7 != type8)
            return new GenericArrayTypeImpl(type8);
        if(true) goto _L6; else goto _L5
_L5:
        if(!(type1 instanceof ParameterizedType))
            break; /* Loop/switch isn't completed */
        obj = (ParameterizedType)type1;
        Type type4 = ((ParameterizedType) (obj)).getOwnerType();
        Type type5 = resolve(type, class1, type4);
        boolean flag;
        Type atype2[];
        int i;
        if(type5 != type4)
            flag = true;
        else
            flag = false;
        atype2 = ((ParameterizedType) (obj)).getActualTypeArguments();
        i = 0;
        for(int j = atype2.length; i < j; i++)
        {
            Type type6 = resolve(type, class1, atype2[i]);
            if(type6 == atype2[i])
                continue;
            if(!flag)
            {
                atype2 = (Type[])atype2.clone();
                flag = true;
            }
            atype2[i] = type6;
        }

        if(flag)
        {
            ParameterizedTypeImpl parameterizedtypeimpl = new ParameterizedTypeImpl(type5, ((ParameterizedType) (obj)).getRawType(), atype2);
            return parameterizedtypeimpl;
        }
        if(true) goto _L6; else goto _L7
_L7:
        Type atype1[];
        if(!(type1 instanceof WildcardType))
            break; /* Loop/switch isn't completed */
        obj = (WildcardType)type1;
        Type atype[] = ((WildcardType) (obj)).getLowerBounds();
        atype1 = ((WildcardType) (obj)).getUpperBounds();
        if(atype.length != 1)
            continue; /* Loop/switch isn't completed */
        Type type3 = resolve(type, class1, atype[0]);
        if(type3 != atype[0])
            return new WildcardTypeImpl(new Type[] {
                java/lang/Object
            }, new Type[] {
                type3
            });
        continue; /* Loop/switch isn't completed */
        if(atype1.length != 1) goto _L6; else goto _L8
_L8:
        Type type2 = resolve(type, class1, atype1[0]);
        if(type2 != atype1[0])
            return new WildcardTypeImpl(new Type[] {
                type2
            }, EMPTY_TYPE_ARRAY);
        if(true) goto _L6; else goto _L9
_L9:
        return type1;
    }

    private static Type resolveTypeVariable(Type type, Class class1, TypeVariable typevariable)
    {
        Class class2 = declaringClassOf(typevariable);
        Type type1;
        if(class2 != null)
            if((type1 = getGenericSupertype(type, class1, class2)) instanceof ParameterizedType)
            {
                int i = indexOf(class2.getTypeParameters(), typevariable);
                return ((ParameterizedType)type1).getActualTypeArguments()[i];
            }
        return typevariable;
    }

    public static String typeToString(Type type)
    {
        if(type instanceof Class)
            return ((Class)type).getName();
        else
            return type.toString();
    }

    private static final Type EMPTY_TYPE_ARRAY[] = new Type[0];




}
