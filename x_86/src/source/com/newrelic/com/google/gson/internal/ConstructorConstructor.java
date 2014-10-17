// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal;

import com.newrelic.com.google.gson.InstanceCreator;
import com.newrelic.com.google.gson.JsonIOException;
import com.newrelic.com.google.gson.reflect.TypeToken;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.newrelic.com.google.gson.internal:
//            ObjectConstructor, LinkedTreeMap, UnsafeAllocator

public final class ConstructorConstructor
{

    public ConstructorConstructor(Map map)
    {
        instanceCreators = map;
    }

    private ObjectConstructor newDefaultConstructor(Class class1)
    {
        ObjectConstructor objectconstructor;
        try
        {
            final Constructor constructor = class1.getDeclaredConstructor(new Class[0]);
            if(!constructor.isAccessible())
                constructor.setAccessible(true);
            objectconstructor = new ObjectConstructor() {

                public Object construct()
                {
                    Object obj;
                    try
                    {
                        obj = constructor.newInstance(null);
                    }
                    catch(InstantiationException instantiationexception)
                    {
                        throw new RuntimeException((new StringBuilder()).append("Failed to invoke ").append(constructor).append(" with no args").toString(), instantiationexception);
                    }
                    catch(InvocationTargetException invocationtargetexception)
                    {
                        throw new RuntimeException((new StringBuilder()).append("Failed to invoke ").append(constructor).append(" with no args").toString(), invocationtargetexception.getTargetException());
                    }
                    catch(IllegalAccessException illegalaccessexception)
                    {
                        throw new AssertionError(illegalaccessexception);
                    }
                    return obj;
                }

                final ConstructorConstructor this$0;
                final Constructor val$constructor;

            
            {
                this$0 = ConstructorConstructor.this;
                constructor = constructor1;
                super();
            }
            }
;
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            return null;
        }
        return objectconstructor;
    }

    private ObjectConstructor newDefaultImplementationConstructor(final Type type, Class class1)
    {
        if(java/util/Collection.isAssignableFrom(class1))
        {
            if(java/util/SortedSet.isAssignableFrom(class1))
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        return new TreeSet();
                    }

                    final ConstructorConstructor this$0;

            
            {
                this$0 = ConstructorConstructor.this;
                super();
            }
                }
;
            if(java/util/EnumSet.isAssignableFrom(class1))
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        if(type instanceof ParameterizedType)
                        {
                            Type type1 = ((ParameterizedType)type).getActualTypeArguments()[0];
                            if(type1 instanceof Class)
                                return EnumSet.noneOf((Class)type1);
                            else
                                throw new JsonIOException((new StringBuilder()).append("Invalid EnumSet type: ").append(type.toString()).toString());
                        } else
                        {
                            throw new JsonIOException((new StringBuilder()).append("Invalid EnumSet type: ").append(type.toString()).toString());
                        }
                    }

                    final ConstructorConstructor this$0;
                    final Type val$type;

            
            {
                this$0 = ConstructorConstructor.this;
                type = type1;
                super();
            }
                }
;
            if(java/util/Set.isAssignableFrom(class1))
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        return new LinkedHashSet();
                    }

                    final ConstructorConstructor this$0;

            
            {
                this$0 = ConstructorConstructor.this;
                super();
            }
                }
;
            if(java/util/Queue.isAssignableFrom(class1))
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        return new LinkedList();
                    }

                    final ConstructorConstructor this$0;

            
            {
                this$0 = ConstructorConstructor.this;
                super();
            }
                }
;
            else
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        return new ArrayList();
                    }

                    final ConstructorConstructor this$0;

            
            {
                this$0 = ConstructorConstructor.this;
                super();
            }
                }
;
        }
        if(java/util/Map.isAssignableFrom(class1))
        {
            if(java/util/SortedMap.isAssignableFrom(class1))
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        return new TreeMap();
                    }

                    final ConstructorConstructor this$0;

            
            {
                this$0 = ConstructorConstructor.this;
                super();
            }
                }
;
            if((type instanceof ParameterizedType) && !java/lang/String.isAssignableFrom(TypeToken.get(((ParameterizedType)type).getActualTypeArguments()[0]).getRawType()))
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        return new LinkedHashMap();
                    }

                    final ConstructorConstructor this$0;

            
            {
                this$0 = ConstructorConstructor.this;
                super();
            }
                }
;
            else
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        return new LinkedTreeMap();
                    }

                    final ConstructorConstructor this$0;

            
            {
                this$0 = ConstructorConstructor.this;
                super();
            }
                }
;
        } else
        {
            return null;
        }
    }

    private ObjectConstructor newUnsafeAllocator(final Type type, final Class rawType)
    {
        return new ObjectConstructor() {

            public Object construct()
            {
                Object obj;
                try
                {
                    obj = unsafeAllocator.newInstance(rawType);
                }
                catch(Exception exception)
                {
                    throw new RuntimeException((new StringBuilder()).append("Unable to invoke no-args constructor for ").append(type).append(". ").append("Register an InstanceCreator with Gson for this type may fix this problem.").toString(), exception);
                }
                return obj;
            }

            final ConstructorConstructor this$0;
            private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
            final Class val$rawType;
            final Type val$type;

            
            {
                this$0 = ConstructorConstructor.this;
                rawType = class1;
                type = type1;
                super();
            }
        }
;
    }

    public ObjectConstructor get(TypeToken typetoken)
    {
        final Type type = typetoken.getType();
        Class class1 = typetoken.getRawType();
        final InstanceCreator typeCreator = (InstanceCreator)instanceCreators.get(type);
        ObjectConstructor objectconstructor;
        if(typeCreator != null)
        {
            objectconstructor = new ObjectConstructor() {

                public Object construct()
                {
                    return typeCreator.createInstance(type);
                }

                final ConstructorConstructor this$0;
                final Type val$type;
                final InstanceCreator val$typeCreator;

            
            {
                this$0 = ConstructorConstructor.this;
                typeCreator = instancecreator;
                type = type1;
                super();
            }
            }
;
        } else
        {
            final InstanceCreator rawTypeCreator = (InstanceCreator)instanceCreators.get(class1);
            if(rawTypeCreator != null)
                return new ObjectConstructor() {

                    public Object construct()
                    {
                        return rawTypeCreator.createInstance(type);
                    }

                    final ConstructorConstructor this$0;
                    final InstanceCreator val$rawTypeCreator;
                    final Type val$type;

            
            {
                this$0 = ConstructorConstructor.this;
                rawTypeCreator = instancecreator;
                type = type1;
                super();
            }
                }
;
            objectconstructor = newDefaultConstructor(class1);
            if(objectconstructor == null)
            {
                ObjectConstructor objectconstructor1 = newDefaultImplementationConstructor(type, class1);
                if(objectconstructor1 != null)
                    return objectconstructor1;
                else
                    return newUnsafeAllocator(type, class1);
            }
        }
        return objectconstructor;
    }

    public String toString()
    {
        return instanceCreators.toString();
    }

    private final Map instanceCreators;
}
