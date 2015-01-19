// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import dagger.internal.loaders.ReflectiveAtInjectBinding;
import dagger.internal.loaders.ReflectiveStaticInjection;

// Referenced classes of package dagger.internal:
//            Loader, Binding, Memoizer, ModuleAdapter, 
//            StaticInjection

public final class FailoverLoader extends Loader
{

    public FailoverLoader()
    {
    }

    public Binding getAtInjectBinding(String s, String s1, ClassLoader classloader, boolean flag)
    {
        Binding binding = (Binding)instantiate(s1.concat("$$InjectAdapter"), classloader);
        if(binding != null)
            return binding;
        Class class1 = loadClass(classloader, s1);
        if(class1.equals(java/lang/Void))
            throw new IllegalStateException(String.format("Could not load class %s needed for binding %s", new Object[] {
                s1, s
            }));
        if(class1.isInterface())
            return null;
        else
            return ReflectiveAtInjectBinding.create(class1, flag);
    }

    public ModuleAdapter getModuleAdapter(Class class1)
    {
        return (ModuleAdapter)loadedAdapters.get(class1);
    }

    public StaticInjection getStaticInjection(Class class1)
    {
        StaticInjection staticinjection = (StaticInjection)instantiate(class1.getName().concat("$$StaticInjection"), class1.getClassLoader());
        if(staticinjection != null)
            return staticinjection;
        else
            return ReflectiveStaticInjection.create(class1);
    }

    private final Memoizer loadedAdapters = new Memoizer() {

        protected ModuleAdapter create(Class class1)
        {
            ModuleAdapter moduleadapter = (ModuleAdapter)instantiate(class1.getName().concat("$$ModuleAdapter"), class1.getClassLoader());
            if(moduleadapter == null)
                throw new IllegalStateException((new StringBuilder()).append("Module adapter for ").append(class1).append(" could not be loaded. ").append("Please ensure that code generation was run for this module.").toString());
            else
                return moduleadapter;
        }

        protected volatile Object create(Object obj)
        {
            return create((Class)obj);
        }

        final FailoverLoader this$0;

            
            {
                this$0 = FailoverLoader.this;
                super();
            }
    }
;
}
