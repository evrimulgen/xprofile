// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import java.util.*;

// Referenced classes of package dagger.internal:
//            ModuleAdapter, Loader

public final class Modules
{

    private Modules()
    {
    }

    private static void collectIncludedModulesRecursively(Loader loader, ModuleAdapter moduleadapter, Map map)
    {
        Class aclass[] = moduleadapter.includes;
        int i = aclass.length;
        for(int j = 0; j < i; j++)
        {
            Class class1 = aclass[j];
            if(!map.containsKey(class1))
            {
                ModuleAdapter moduleadapter1 = loader.getModuleAdapter(class1);
                map.put(class1, moduleadapter1);
                collectIncludedModulesRecursively(loader, moduleadapter1, map);
            }
        }

    }

    public static Map loadModules(Loader loader, Object aobj[])
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap(aobj.length);
        int i = 0;
        while(i < aobj.length) 
        {
            if(aobj[i] instanceof Class)
            {
                ModuleAdapter moduleadapter1 = loader.getModuleAdapter((Class)aobj[i]);
                linkedhashmap.put(moduleadapter1, moduleadapter1.newModule());
            } else
            {
                linkedhashmap.put(loader.getModuleAdapter(aobj[i].getClass()), aobj[i]);
            }
            i++;
        }
        LinkedHashMap linkedhashmap1 = new LinkedHashMap(linkedhashmap);
        LinkedHashMap linkedhashmap2 = new LinkedHashMap();
        for(Iterator iterator = linkedhashmap.keySet().iterator(); iterator.hasNext(); collectIncludedModulesRecursively(loader, (ModuleAdapter)iterator.next(), linkedhashmap2));
        Iterator iterator1 = linkedhashmap2.values().iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            ModuleAdapter moduleadapter = (ModuleAdapter)iterator1.next();
            if(!linkedhashmap1.containsKey(moduleadapter))
                linkedhashmap1.put(moduleadapter, moduleadapter.newModule());
        } while(true);
        return linkedhashmap1;
    }
}
