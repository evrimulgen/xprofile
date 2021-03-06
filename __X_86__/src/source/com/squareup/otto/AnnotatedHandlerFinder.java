// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.otto;

import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package com.squareup.otto:
//            EventProducer, EventHandler, Subscribe, Produce

final class AnnotatedHandlerFinder
{

    private AnnotatedHandlerFinder()
    {
    }

    static Map findAllProducers(Object obj)
    {
        Class class1 = obj.getClass();
        HashMap hashmap = new HashMap();
        if(!PRODUCERS_CACHE.containsKey(class1))
            loadAnnotatedMethods(class1);
        Map map = (Map)PRODUCERS_CACHE.get(class1);
        if(!map.isEmpty())
        {
            java.util.Map.Entry entry;
            EventProducer eventproducer;
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); hashmap.put(entry.getKey(), eventproducer))
            {
                entry = (java.util.Map.Entry)iterator.next();
                eventproducer = new EventProducer(obj, (Method)entry.getValue());
            }

        }
        return hashmap;
    }

    static Map findAllSubscribers(Object obj)
    {
        Class class1 = obj.getClass();
        HashMap hashmap = new HashMap();
        if(!SUBSCRIBERS_CACHE.containsKey(class1))
            loadAnnotatedMethods(class1);
        Map map = (Map)SUBSCRIBERS_CACHE.get(class1);
        if(!map.isEmpty())
        {
            java.util.Map.Entry entry;
            HashSet hashset;
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); hashmap.put(entry.getKey(), hashset))
            {
                entry = (java.util.Map.Entry)iterator.next();
                hashset = new HashSet();
                for(Iterator iterator1 = ((Set)entry.getValue()).iterator(); iterator1.hasNext(); hashset.add(new EventHandler(obj, (Method)iterator1.next())));
            }

        }
        return hashmap;
    }

    private static void loadAnnotatedMethods(Class class1)
    {
        HashMap hashmap = new HashMap();
        HashMap hashmap1 = new HashMap();
        Method amethod[] = class1.getDeclaredMethods();
        int i = amethod.length;
        int j = 0;
        while(j < i) 
        {
            Method method = amethod[j];
            if(method.isAnnotationPresent(com/squareup/otto/Subscribe))
            {
                Class aclass1[] = method.getParameterTypes();
                if(aclass1.length != 1)
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(method).append(" has @Subscribe annotation but requires ").append(aclass1.length).append(" arguments.  Methods must require a single argument.").toString());
                Class class3 = aclass1[0];
                if(class3.isInterface())
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(method).append(" has @Subscribe annotation on ").append(class3).append(" which is an interface.  Subscription must be on a concrete class type.").toString());
                if((1 & method.getModifiers()) == 0)
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(method).append(" has @Subscribe annotation on ").append(class3).append(" but is not 'public'.").toString());
                Object obj = (Set)hashmap.get(class3);
                if(obj == null)
                {
                    obj = new HashSet();
                    hashmap.put(class3, obj);
                }
                ((Set) (obj)).add(method);
            } else
            if(method.isAnnotationPresent(com/squareup/otto/Produce))
            {
                Class aclass[] = method.getParameterTypes();
                if(aclass.length != 0)
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(method).append("has @Produce annotation but requires ").append(aclass.length).append(" arguments.  Methods must require zero arguments.").toString());
                if(method.getReturnType() == java/lang/Void)
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(method).append(" has a return type of void.  Must declare a non-void type.").toString());
                Class class2 = method.getReturnType();
                if(class2.isInterface())
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(method).append(" has @Produce annotation on ").append(class2).append(" which is an interface.  Producers must return a concrete class type.").toString());
                if(class2.equals(Void.TYPE))
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(method).append(" has @Produce annotation but has no return type.").toString());
                if((1 & method.getModifiers()) == 0)
                    throw new IllegalArgumentException((new StringBuilder()).append("Method ").append(method).append(" has @Produce annotation on ").append(class2).append(" but is not 'public'.").toString());
                if(hashmap1.containsKey(class2))
                    throw new IllegalArgumentException((new StringBuilder()).append("Producer for type ").append(class2).append(" has already been registered.").toString());
                hashmap1.put(class2, method);
            }
            j++;
        }
        PRODUCERS_CACHE.put(class1, hashmap1);
        SUBSCRIBERS_CACHE.put(class1, hashmap);
    }

    private static final Map PRODUCERS_CACHE = new HashMap();
    private static final Map SUBSCRIBERS_CACHE = new HashMap();

}
