// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger;

import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.FailoverLoader;
import dagger.internal.Keys;
import dagger.internal.Linker;
import dagger.internal.Loader;
import dagger.internal.ModuleAdapter;
import dagger.internal.Modules;
import dagger.internal.ProblemDetector;
import dagger.internal.SetBinding;
import dagger.internal.StaticInjection;
import dagger.internal.ThrowingErrorHandler;
import java.util.*;

public abstract class ObjectGraph
{
    static class DaggerObjectGraph extends ObjectGraph
    {

        private static Object checkNotNull(Object obj, String s)
        {
            if(obj == null)
                throw new NullPointerException(s);
            else
                return obj;
        }

        private Binding getInjectableTypeBinding(ClassLoader classloader, String s, String s1)
        {
            Class class1;
            class1 = null;
            DaggerObjectGraph daggerobjectgraph = this;
label0:
            do
            {
label1:
                {
                    if(daggerobjectgraph != null)
                    {
                        class1 = (Class)daggerobjectgraph.injectableTypes.get(s);
                        if(class1 == null)
                            break label1;
                    }
                    if(class1 == null)
                        throw new IllegalArgumentException((new StringBuilder()).append("No inject registered for ").append(s).append(". You must explicitly add it to the 'injects' option in one of your modules.").toString());
                    break label0;
                }
                daggerobjectgraph = daggerobjectgraph.base;
            } while(true);
            Linker linker1 = linker;
            linker1;
            JVM INSTR monitorenter ;
            Binding binding = linker.requestBinding(s1, class1, classloader, false, true);
            if(binding == null)
                break MISSING_BLOCK_LABEL_116;
            if(binding.isLinked())
                break MISSING_BLOCK_LABEL_138;
            linker.linkRequested();
            binding = linker.requestBinding(s1, class1, classloader, false, true);
            linker1;
            JVM INSTR monitorexit ;
            return binding;
            Exception exception;
            exception;
            linker1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private Map linkEverything()
        {
            Map map = linker.fullyLinkedBindings();
            if(map != null)
                return map;
            Linker linker1 = linker;
            linker1;
            JVM INSTR monitorenter ;
            Map map1 = linker.fullyLinkedBindings();
            if(map1 == null)
                break MISSING_BLOCK_LABEL_40;
            linker1;
            JVM INSTR monitorexit ;
            return map1;
            Map map2;
            linkStaticInjections();
            linkInjectableTypes();
            map2 = linker.linkAll();
            linker1;
            JVM INSTR monitorexit ;
            return map2;
            Exception exception;
            exception;
            linker1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private void linkInjectableTypes()
        {
            java.util.Map.Entry entry;
            for(Iterator iterator = injectableTypes.entrySet().iterator(); iterator.hasNext(); linker.requestBinding((String)entry.getKey(), entry.getValue(), ((Class)entry.getValue()).getClassLoader(), false, true))
                entry = (java.util.Map.Entry)iterator.next();

        }

        private void linkStaticInjections()
        {
            StaticInjection staticinjection;
            for(Iterator iterator = staticInjections.entrySet().iterator(); iterator.hasNext(); staticinjection.attach(linker))
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                staticinjection = (StaticInjection)entry.getValue();
                if(staticinjection == null)
                {
                    staticinjection = plugin.getStaticInjection((Class)entry.getKey());
                    entry.setValue(staticinjection);
                }
            }

        }

        private static transient ObjectGraph makeGraph(DaggerObjectGraph daggerobjectgraph, Loader loader, Object aobj[])
        {
            LinkedHashMap linkedhashmap;
            LinkedHashMap linkedhashmap1;
            StandardBindings standardbindings;
            OverridesBindings overridesbindings;
            java.util.Map.Entry entry;
            ModuleAdapter moduleadapter;
            linkedhashmap = new LinkedHashMap();
            linkedhashmap1 = new LinkedHashMap();
            Iterator iterator;
            int i;
            if(daggerobjectgraph == null)
                standardbindings = new StandardBindings();
            else
                standardbindings = new StandardBindings(daggerobjectgraph.setBindings);
            overridesbindings = new OverridesBindings();
            iterator = Modules.loadModules(loader, aobj).entrySet().iterator();
_L2:
            if(!iterator.hasNext())
                break; /* Loop/switch isn't completed */
            entry = (java.util.Map.Entry)iterator.next();
            moduleadapter = (ModuleAdapter)entry.getKey();
            for(i = 0; i < moduleadapter.injectableTypes.length; i++)
                linkedhashmap.put(moduleadapter.injectableTypes[i], moduleadapter.moduleClass);

            for(int j = 0; j < moduleadapter.staticInjections.length; j++)
                linkedhashmap1.put(moduleadapter.staticInjections[j], null);

            Object obj;
            if(moduleadapter.overrides)
                obj = overridesbindings;
            else
                obj = standardbindings;
            try
            {
                moduleadapter.getBindings(((BindingsGroup) (obj)), entry.getValue());
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append(moduleadapter.moduleClass.getSimpleName()).append(": ").append(illegalargumentexception.getMessage()).toString(), illegalargumentexception);
            }
            if(true) goto _L2; else goto _L1
_L1:
            Linker linker1;
            Linker linker2;
            if(daggerobjectgraph != null)
                linker1 = daggerobjectgraph.linker;
            else
                linker1 = null;
            linker2 = new Linker(linker1, loader, new ThrowingErrorHandler());
            linker2.installBindings(standardbindings);
            linker2.installBindings(overridesbindings);
            return new DaggerObjectGraph(daggerobjectgraph, linker2, loader, linkedhashmap1, linkedhashmap, standardbindings.setBindings);
        }

        public Object get(Class class1)
        {
            String s = Keys.get(class1);
            String s1;
            if(class1.isInterface())
                s1 = s;
            else
                s1 = Keys.getMembersKey(class1);
            return getInjectableTypeBinding(class1.getClassLoader(), s1, s).get();
        }

        public Object inject(Object obj)
        {
            String s = Keys.getMembersKey(obj.getClass());
            getInjectableTypeBinding(obj.getClass().getClassLoader(), s, s).injectMembers(obj);
            return obj;
        }

        public void injectStatics()
        {
            synchronized(linker)
            {
                linkStaticInjections();
                linker.linkRequested();
                linkStaticInjections();
            }
            for(Iterator iterator = staticInjections.entrySet().iterator(); iterator.hasNext(); ((StaticInjection)((java.util.Map.Entry)iterator.next()).getValue()).inject());
            break MISSING_BLOCK_LABEL_76;
            exception;
            linker1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public transient ObjectGraph plus(Object aobj[])
        {
            linkEverything();
            return makeGraph(this, plugin, aobj);
        }

        public void validate()
        {
            Map map = linkEverything();
            (new ProblemDetector()).detectProblems(map.values());
        }

        private final DaggerObjectGraph base;
        private final Map injectableTypes;
        private final Linker linker;
        private final Loader plugin;
        private final List setBindings;
        private final Map staticInjections;


        DaggerObjectGraph(DaggerObjectGraph daggerobjectgraph, Linker linker1, Loader loader, Map map, Map map1, List list)
        {
            base = daggerobjectgraph;
            linker = (Linker)checkNotNull(linker1, "linker");
            plugin = (Loader)checkNotNull(loader, "plugin");
            staticInjections = (Map)checkNotNull(map, "staticInjections");
            injectableTypes = (Map)checkNotNull(map1, "injectableTypes");
            setBindings = (List)checkNotNull(list, "setBindings");
        }
    }

    private static final class OverridesBindings extends BindingsGroup
    {

        public Binding contributeSetBinding(String s, SetBinding setbinding)
        {
            throw new IllegalArgumentException("Module overrides cannot contribute set bindings.");
        }

        OverridesBindings()
        {
        }
    }

    private static final class StandardBindings extends BindingsGroup
    {

        public Binding contributeSetBinding(String s, SetBinding setbinding)
        {
            setBindings.add(setbinding);
            return super.put(s, setbinding);
        }

        private final List setBindings;


        public StandardBindings()
        {
            setBindings = new ArrayList();
        }

        public StandardBindings(List list)
        {
            setBindings = new ArrayList(list.size());
            SetBinding setbinding;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); put(setbinding.provideKey, setbinding))
            {
                setbinding = new SetBinding((SetBinding)iterator.next());
                setBindings.add(setbinding);
            }

        }
    }


    ObjectGraph()
    {
    }

    public static transient ObjectGraph create(Object aobj[])
    {
        return DaggerObjectGraph.makeGraph(null, new FailoverLoader(), aobj);
    }

    static transient ObjectGraph createWith(Loader loader, Object aobj[])
    {
        return DaggerObjectGraph.makeGraph(null, loader, aobj);
    }

    public abstract Object get(Class class1);

    public abstract Object inject(Object obj);

    public abstract void injectStatics();

    public transient abstract ObjectGraph plus(Object aobj[]);

    public abstract void validate();
}
