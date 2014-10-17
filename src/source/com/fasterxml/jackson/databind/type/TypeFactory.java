// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.type:
//            SimpleType, TypeParser, CollectionType, MapType, 
//            HierarchicType, TypeModifier, ArrayType, ClassKey, 
//            TypeBindings, CollectionLikeType, MapLikeType

public final class TypeFactory
    implements Serializable
{

    private TypeFactory()
    {
        _typeCache = new LRUMap(16, 100);
        _parser = new TypeParser(this);
        _modifiers = null;
    }

    protected TypeFactory(TypeParser typeparser, TypeModifier atypemodifier[])
    {
        _typeCache = new LRUMap(16, 100);
        _parser = typeparser;
        _modifiers = atypemodifier;
    }

    private JavaType _collectionType(Class class1)
    {
        JavaType ajavatype[] = findTypeParameters(class1, java/util/Collection);
        if(ajavatype == null)
            return CollectionType.construct(class1, _unknownType());
        if(ajavatype.length != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Strange Collection type ").append(class1.getName()).append(": can not determine type parameters").toString());
        else
            return CollectionType.construct(class1, ajavatype[0]);
    }

    private JavaType _mapType(Class class1)
    {
        JavaType ajavatype[] = findTypeParameters(class1, java/util/Map);
        if(ajavatype == null)
            return MapType.construct(class1, _unknownType(), _unknownType());
        if(ajavatype.length != 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Strange Map type ").append(class1.getName()).append(": can not determine type parameters").toString());
        else
            return MapType.construct(class1, ajavatype[0], ajavatype[1]);
    }

    public static TypeFactory defaultInstance()
    {
        return instance;
    }

    public static Class rawClass(Type type)
    {
        if(type instanceof Class)
            return (Class)type;
        else
            return defaultInstance().constructType(type).getRawClass();
    }

    public static JavaType unknownType()
    {
        return defaultInstance()._unknownType();
    }

    protected HierarchicType _arrayListSuperInterfaceChain(HierarchicType hierarchictype)
    {
        this;
        JVM INSTR monitorenter ;
        if(_cachedArrayListType == null)
        {
            HierarchicType hierarchictype2 = hierarchictype.deepCloneWithoutSubtype();
            _doFindSuperInterfaceChain(hierarchictype2, java/util/List);
            _cachedArrayListType = hierarchictype2.getSuperType();
        }
        HierarchicType hierarchictype1 = _cachedArrayListType.deepCloneWithoutSubtype();
        hierarchictype.setSuperType(hierarchictype1);
        hierarchictype1.setSubType(hierarchictype);
        this;
        JVM INSTR monitorexit ;
        return hierarchictype;
        Exception exception;
        exception;
        throw exception;
    }

    protected JavaType _constructType(Type type, TypeBindings typebindings)
    {
        if(!(type instanceof Class)) goto _L2; else goto _L1
_L1:
        JavaType javatype = _fromClass((Class)type, typebindings);
_L4:
        if(_modifiers != null && !javatype.isContainerType())
        {
            TypeModifier atypemodifier[] = _modifiers;
            int i = atypemodifier.length;
            for(int j = 0; j < i;)
            {
                JavaType javatype1 = atypemodifier[j].modifyType(javatype, type, typebindings, this);
                j++;
                javatype = javatype1;
            }

        }
        break MISSING_BLOCK_LABEL_222;
_L2:
        if(type instanceof ParameterizedType)
        {
            javatype = _fromParamType((ParameterizedType)type, typebindings);
            continue; /* Loop/switch isn't completed */
        }
        if(type instanceof JavaType)
            return (JavaType)type;
        if(type instanceof GenericArrayType)
        {
            javatype = _fromArrayType((GenericArrayType)type, typebindings);
            continue; /* Loop/switch isn't completed */
        }
        if(type instanceof TypeVariable)
        {
            javatype = _fromVariable((TypeVariable)type, typebindings);
            continue; /* Loop/switch isn't completed */
        }
        if(!(type instanceof WildcardType))
            break; /* Loop/switch isn't completed */
        javatype = _fromWildcard((WildcardType)type, typebindings);
        if(true) goto _L4; else goto _L3
_L3:
        StringBuilder stringbuilder = (new StringBuilder()).append("Unrecognized Type: ");
        String s;
        if(type == null)
            s = "[null]";
        else
            s = type.toString();
        throw new IllegalArgumentException(stringbuilder.append(s).toString());
        return javatype;
    }

    protected HierarchicType _doFindSuperInterfaceChain(HierarchicType hierarchictype, Class class1)
    {
        Class class2 = hierarchictype.getRawClass();
        Type atype[] = class2.getGenericInterfaces();
        if(atype != null)
        {
            int i = atype.length;
            for(int j = 0; j < i; j++)
            {
                HierarchicType hierarchictype2 = _findSuperInterfaceChain(atype[j], class1);
                if(hierarchictype2 != null)
                {
                    hierarchictype2.setSubType(hierarchictype);
                    hierarchictype.setSuperType(hierarchictype2);
                    return hierarchictype;
                }
            }

        }
        Type type = class2.getGenericSuperclass();
        if(type != null)
        {
            HierarchicType hierarchictype1 = _findSuperInterfaceChain(type, class1);
            if(hierarchictype1 != null)
            {
                hierarchictype1.setSubType(hierarchictype);
                hierarchictype.setSuperType(hierarchictype1);
                return hierarchictype;
            }
        }
        return null;
    }

    protected HierarchicType _findSuperClassChain(Type type, Class class1)
    {
        HierarchicType hierarchictype = new HierarchicType(type);
        Class class2 = hierarchictype.getRawClass();
        if(class2 == class1)
            return hierarchictype;
        Type type1 = class2.getGenericSuperclass();
        if(type1 != null)
        {
            HierarchicType hierarchictype1 = _findSuperClassChain(type1, class1);
            if(hierarchictype1 != null)
            {
                hierarchictype1.setSubType(hierarchictype);
                hierarchictype.setSuperType(hierarchictype1);
                return hierarchictype;
            }
        }
        return null;
    }

    protected HierarchicType _findSuperInterfaceChain(Type type, Class class1)
    {
        HierarchicType hierarchictype = new HierarchicType(type);
        Class class2 = hierarchictype.getRawClass();
        if(class2 == class1)
            return new HierarchicType(type);
        if(class2 == java/util/HashMap && class1 == java/util/Map)
            return _hashMapSuperInterfaceChain(hierarchictype);
        if(class2 == java/util/ArrayList && class1 == java/util/List)
            return _arrayListSuperInterfaceChain(hierarchictype);
        else
            return _doFindSuperInterfaceChain(hierarchictype, class1);
    }

    protected HierarchicType _findSuperTypeChain(Class class1, Class class2)
    {
        if(class2.isInterface())
            return _findSuperInterfaceChain(class1, class2);
        else
            return _findSuperClassChain(class1, class2);
    }

    protected JavaType _fromArrayType(GenericArrayType genericarraytype, TypeBindings typebindings)
    {
        return ArrayType.construct(_constructType(genericarraytype.getGenericComponentType(), typebindings), null, null);
    }

    protected JavaType _fromClass(Class class1, TypeBindings typebindings)
    {
        if(class1 != java/lang/String) goto _L2; else goto _L1
_L1:
        Object obj = CORE_TYPE_STRING;
_L4:
        return ((JavaType) (obj));
_L2:
        ClassKey classkey;
        if(class1 == Boolean.TYPE)
            return CORE_TYPE_BOOL;
        if(class1 == Integer.TYPE)
            return CORE_TYPE_INT;
        if(class1 == Long.TYPE)
            return CORE_TYPE_LONG;
        classkey = new ClassKey(class1);
        synchronized(_typeCache)
        {
            obj = (JavaType)_typeCache.get(classkey);
        }
        if(obj != null) goto _L4; else goto _L3
_L3:
        Object obj1;
        if(class1.isArray())
            obj1 = ArrayType.construct(_constructType(class1.getComponentType(), null), null, null);
        else
        if(class1.isEnum())
            obj1 = new SimpleType(class1);
        else
        if(java/util/Map.isAssignableFrom(class1))
            obj1 = _mapType(class1);
        else
        if(java/util/Collection.isAssignableFrom(class1))
            obj1 = _collectionType(class1);
        else
            obj1 = new SimpleType(class1);
        synchronized(_typeCache)
        {
            _typeCache.put(classkey, obj1);
        }
        return ((JavaType) (obj1));
        exception1;
        lrumap1;
        JVM INSTR monitorexit ;
        throw exception1;
        exception;
        lrumap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected JavaType _fromParamType(ParameterizedType parameterizedtype, TypeBindings typebindings)
    {
        Class class1 = (Class)parameterizedtype.getRawType();
        Type atype[] = parameterizedtype.getActualTypeArguments();
        int i;
        JavaType ajavatype[];
        if(atype == null)
            i = 0;
        else
            i = atype.length;
        if(i == 0)
        {
            ajavatype = NO_TYPES;
        } else
        {
            ajavatype = new JavaType[i];
            int j = 0;
            while(j < i) 
            {
                ajavatype[j] = _constructType(atype[j], typebindings);
                j++;
            }
        }
        if(java/util/Map.isAssignableFrom(class1))
        {
            JavaType ajavatype2[] = findTypeParameters(constructSimpleType(class1, ajavatype), java/util/Map);
            if(ajavatype2.length != 2)
                throw new IllegalArgumentException((new StringBuilder()).append("Could not find 2 type parameters for Map class ").append(class1.getName()).append(" (found ").append(ajavatype2.length).append(")").toString());
            else
                return MapType.construct(class1, ajavatype2[0], ajavatype2[1]);
        }
        if(java/util/Collection.isAssignableFrom(class1))
        {
            JavaType ajavatype1[] = findTypeParameters(constructSimpleType(class1, ajavatype), java/util/Collection);
            if(ajavatype1.length != 1)
                throw new IllegalArgumentException((new StringBuilder()).append("Could not find 1 type parameter for Collection class ").append(class1.getName()).append(" (found ").append(ajavatype1.length).append(")").toString());
            else
                return CollectionType.construct(class1, ajavatype1[0]);
        }
        if(i == 0)
            return new SimpleType(class1);
        else
            return constructSimpleType(class1, ajavatype);
    }

    protected JavaType _fromParameterizedClass(Class class1, List list)
    {
        if(class1.isArray())
            return ArrayType.construct(_constructType(class1.getComponentType(), null), null, null);
        if(class1.isEnum())
            return new SimpleType(class1);
        if(java/util/Map.isAssignableFrom(class1))
            if(list.size() > 0)
            {
                JavaType javatype = (JavaType)list.get(0);
                JavaType javatype1;
                if(list.size() >= 2)
                    javatype1 = (JavaType)list.get(1);
                else
                    javatype1 = _unknownType();
                return MapType.construct(class1, javatype, javatype1);
            } else
            {
                return _mapType(class1);
            }
        if(java/util/Collection.isAssignableFrom(class1))
            if(list.size() >= 1)
                return CollectionType.construct(class1, (JavaType)list.get(0));
            else
                return _collectionType(class1);
        if(list.size() == 0)
            return new SimpleType(class1);
        else
            return constructSimpleType(class1, (JavaType[])list.toArray(new JavaType[list.size()]));
    }

    protected JavaType _fromVariable(TypeVariable typevariable, TypeBindings typebindings)
    {
        JavaType javatype;
        if(typebindings == null)
        {
            javatype = _unknownType();
        } else
        {
            String s = typevariable.getName();
            javatype = typebindings.findType(s);
            if(javatype == null)
            {
                Type atype[] = typevariable.getBounds();
                typebindings._addPlaceholder(s);
                return _constructType(atype[0], typebindings);
            }
        }
        return javatype;
    }

    protected JavaType _fromWildcard(WildcardType wildcardtype, TypeBindings typebindings)
    {
        return _constructType(wildcardtype.getUpperBounds()[0], typebindings);
    }

    protected HierarchicType _hashMapSuperInterfaceChain(HierarchicType hierarchictype)
    {
        this;
        JVM INSTR monitorenter ;
        if(_cachedHashMapType == null)
        {
            HierarchicType hierarchictype2 = hierarchictype.deepCloneWithoutSubtype();
            _doFindSuperInterfaceChain(hierarchictype2, java/util/Map);
            _cachedHashMapType = hierarchictype2.getSuperType();
        }
        HierarchicType hierarchictype1 = _cachedHashMapType.deepCloneWithoutSubtype();
        hierarchictype.setSuperType(hierarchictype1);
        hierarchictype1.setSubType(hierarchictype);
        this;
        JVM INSTR monitorexit ;
        return hierarchictype;
        Exception exception;
        exception;
        throw exception;
    }

    protected JavaType _resolveVariableViaSubTypes(HierarchicType hierarchictype, String s, TypeBindings typebindings)
    {
        if(hierarchictype != null && hierarchictype.isGeneric())
        {
            TypeVariable atypevariable[] = hierarchictype.getRawClass().getTypeParameters();
            int i = 0;
            for(int j = atypevariable.length; i < j; i++)
                if(s.equals(atypevariable[i].getName()))
                {
                    Type type = hierarchictype.asGeneric().getActualTypeArguments()[i];
                    if(type instanceof TypeVariable)
                        return _resolveVariableViaSubTypes(hierarchictype.getSubType(), ((TypeVariable)type).getName(), typebindings);
                    else
                        return _constructType(type, typebindings);
                }

        }
        return _unknownType();
    }

    protected JavaType _unknownType()
    {
        return new SimpleType(java/lang/Object);
    }

    public ArrayType constructArrayType(JavaType javatype)
    {
        return ArrayType.construct(javatype, null, null);
    }

    public ArrayType constructArrayType(Class class1)
    {
        return ArrayType.construct(_constructType(class1, null), null, null);
    }

    public CollectionLikeType constructCollectionLikeType(Class class1, JavaType javatype)
    {
        return CollectionLikeType.construct(class1, javatype);
    }

    public CollectionLikeType constructCollectionLikeType(Class class1, Class class2)
    {
        return CollectionLikeType.construct(class1, constructType(class2));
    }

    public CollectionType constructCollectionType(Class class1, JavaType javatype)
    {
        return CollectionType.construct(class1, javatype);
    }

    public CollectionType constructCollectionType(Class class1, Class class2)
    {
        return CollectionType.construct(class1, constructType(class2));
    }

    public JavaType constructFromCanonical(String s)
        throws IllegalArgumentException
    {
        return _parser.parse(s);
    }

    public MapLikeType constructMapLikeType(Class class1, JavaType javatype, JavaType javatype1)
    {
        return MapLikeType.construct(class1, javatype, javatype1);
    }

    public MapLikeType constructMapLikeType(Class class1, Class class2, Class class3)
    {
        return MapType.construct(class1, constructType(class2), constructType(class3));
    }

    public MapType constructMapType(Class class1, JavaType javatype, JavaType javatype1)
    {
        return MapType.construct(class1, javatype, javatype1);
    }

    public MapType constructMapType(Class class1, Class class2, Class class3)
    {
        return MapType.construct(class1, constructType(class2), constructType(class3));
    }

    public transient JavaType constructParametricType(Class class1, JavaType ajavatype[])
    {
        if(class1.isArray())
            if(ajavatype.length != 1)
                throw new IllegalArgumentException((new StringBuilder()).append("Need exactly 1 parameter type for arrays (").append(class1.getName()).append(")").toString());
            else
                return constructArrayType(ajavatype[0]);
        if(java/util/Map.isAssignableFrom(class1))
            if(ajavatype.length != 2)
                throw new IllegalArgumentException((new StringBuilder()).append("Need exactly 2 parameter types for Map types (").append(class1.getName()).append(")").toString());
            else
                return constructMapType(class1, ajavatype[0], ajavatype[1]);
        if(java/util/Collection.isAssignableFrom(class1))
        {
            if(ajavatype.length != 1)
                throw new IllegalArgumentException((new StringBuilder()).append("Need exactly 1 parameter type for Collection types (").append(class1.getName()).append(")").toString());
            else
                return constructCollectionType(class1, ajavatype[0]);
        } else
        {
            return constructSimpleType(class1, ajavatype);
        }
    }

    public transient JavaType constructParametricType(Class class1, Class aclass[])
    {
        int i = aclass.length;
        JavaType ajavatype[] = new JavaType[i];
        for(int j = 0; j < i; j++)
            ajavatype[j] = _fromClass(aclass[j], null);

        return constructParametricType(class1, ajavatype);
    }

    public CollectionLikeType constructRawCollectionLikeType(Class class1)
    {
        return CollectionLikeType.construct(class1, unknownType());
    }

    public CollectionType constructRawCollectionType(Class class1)
    {
        return CollectionType.construct(class1, unknownType());
    }

    public MapLikeType constructRawMapLikeType(Class class1)
    {
        return MapLikeType.construct(class1, unknownType(), unknownType());
    }

    public MapType constructRawMapType(Class class1)
    {
        return MapType.construct(class1, unknownType(), unknownType());
    }

    public JavaType constructSimpleType(Class class1, JavaType ajavatype[])
    {
        TypeVariable atypevariable[] = class1.getTypeParameters();
        if(atypevariable.length != ajavatype.length)
            throw new IllegalArgumentException((new StringBuilder()).append("Parameter type mismatch for ").append(class1.getName()).append(": expected ").append(atypevariable.length).append(" parameters, was given ").append(ajavatype.length).toString());
        String as[] = new String[atypevariable.length];
        int i = atypevariable.length;
        for(int j = 0; j < i; j++)
            as[j] = atypevariable[j].getName();

        return new SimpleType(class1, as, ajavatype, null, null, false);
    }

    public JavaType constructSpecializedType(JavaType javatype, Class class1)
    {
        if(javatype.getRawClass() == class1)
            return javatype;
        if((javatype instanceof SimpleType) && (class1.isArray() || java/util/Map.isAssignableFrom(class1) || java/util/Collection.isAssignableFrom(class1)))
        {
            if(!javatype.getRawClass().isAssignableFrom(class1))
                throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.getClass().getName()).append(" not subtype of ").append(javatype).toString());
            JavaType javatype1 = _fromClass(class1, new TypeBindings(this, javatype.getRawClass()));
            Object obj = javatype.getValueHandler();
            if(obj != null)
                javatype1 = javatype1.withValueHandler(obj);
            Object obj1 = javatype.getTypeHandler();
            if(obj1 != null)
                javatype1 = javatype1.withTypeHandler(obj1);
            return javatype1;
        } else
        {
            return javatype.narrowBy(class1);
        }
    }

    public JavaType constructType(TypeReference typereference)
    {
        return _constructType(typereference.getType(), null);
    }

    public JavaType constructType(Type type)
    {
        return _constructType(type, null);
    }

    public JavaType constructType(Type type, JavaType javatype)
    {
        TypeBindings typebindings;
        if(javatype == null)
            typebindings = null;
        else
            typebindings = new TypeBindings(this, javatype);
        return _constructType(type, typebindings);
    }

    public JavaType constructType(Type type, TypeBindings typebindings)
    {
        return _constructType(type, typebindings);
    }

    public JavaType constructType(Type type, Class class1)
    {
        TypeBindings typebindings;
        if(class1 == null)
            typebindings = null;
        else
            typebindings = new TypeBindings(this, class1);
        return _constructType(type, typebindings);
    }

    public JavaType[] findTypeParameters(JavaType javatype, Class class1)
    {
        Class class2 = javatype.getRawClass();
        if(class2 == class1)
        {
            int i = javatype.containedTypeCount();
            JavaType ajavatype[];
            if(i == 0)
            {
                ajavatype = null;
            } else
            {
                ajavatype = new JavaType[i];
                int j = 0;
                while(j < i) 
                {
                    ajavatype[j] = javatype.containedType(j);
                    j++;
                }
            }
            return ajavatype;
        } else
        {
            return findTypeParameters(class2, class1, new TypeBindings(this, javatype));
        }
    }

    public JavaType[] findTypeParameters(Class class1, Class class2)
    {
        return findTypeParameters(class1, class2, new TypeBindings(this, class1));
    }

    public JavaType[] findTypeParameters(Class class1, Class class2, TypeBindings typebindings)
    {
        HierarchicType hierarchictype;
        TypeBindings typebindings1;
        hierarchictype = _findSuperTypeChain(class1, class2);
        if(hierarchictype == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.getName()).append(" is not a subtype of ").append(class2.getName()).toString());
_L4:
        if(hierarchictype.getSuperType() == null)
            break; /* Loop/switch isn't completed */
        hierarchictype = hierarchictype.getSuperType();
        Class class3 = hierarchictype.getRawClass();
        typebindings1 = new TypeBindings(this, class3);
        if(hierarchictype.isGeneric())
        {
            Type atype[] = hierarchictype.asGeneric().getActualTypeArguments();
            TypeVariable atypevariable[] = class3.getTypeParameters();
            int i = atype.length;
            int j = 0;
            while(j < i) 
            {
                typebindings1.addBinding(atypevariable[j].getName(), _constructType(atype[j], typebindings));
                j++;
            }
        }
        if(true) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        typebindings = typebindings1;
        if(true) goto _L4; else goto _L3
_L3:
        if(!hierarchictype.isGeneric())
            return null;
        else
            return typebindings.typesAsArray();
    }

    public JavaType moreSpecificType(JavaType javatype, JavaType javatype1)
    {
        if(javatype == null)
            javatype = javatype1;
        else
        if(javatype1 != null)
        {
            Class class1 = javatype.getRawClass();
            Class class2 = javatype1.getRawClass();
            if(class1 != class2 && class1.isAssignableFrom(class2))
                return javatype1;
        }
        return javatype;
    }

    public JavaType uncheckedSimpleType(Class class1)
    {
        return new SimpleType(class1);
    }

    public TypeFactory withModifier(TypeModifier typemodifier)
    {
        if(_modifiers == null)
            return new TypeFactory(_parser, new TypeModifier[] {
                typemodifier
            });
        else
            return new TypeFactory(_parser, (TypeModifier[])ArrayBuilders.insertInListNoDup(_modifiers, typemodifier));
    }

    protected static final SimpleType CORE_TYPE_BOOL;
    protected static final SimpleType CORE_TYPE_INT;
    protected static final SimpleType CORE_TYPE_LONG;
    protected static final SimpleType CORE_TYPE_STRING = new SimpleType(java/lang/String);
    private static final JavaType NO_TYPES[] = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    private static final long serialVersionUID = 1L;
    protected transient HierarchicType _cachedArrayListType;
    protected transient HierarchicType _cachedHashMapType;
    protected final TypeModifier _modifiers[];
    protected final TypeParser _parser;
    protected final LRUMap _typeCache;

    static 
    {
        CORE_TYPE_BOOL = new SimpleType(Boolean.TYPE);
        CORE_TYPE_INT = new SimpleType(Integer.TYPE);
        CORE_TYPE_LONG = new SimpleType(Long.TYPE);
    }
}
