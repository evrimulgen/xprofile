// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;


// Referenced classes of package dagger.internal:
//            BindingsGroup

public abstract class ModuleAdapter
{

    protected ModuleAdapter(Class class1, String as[], Class aclass[], boolean flag, Class aclass1[], boolean flag1, boolean flag2)
    {
        moduleClass = class1;
        injectableTypes = as;
        staticInjections = aclass;
        overrides = flag;
        includes = aclass1;
        complete = flag1;
        library = flag2;
    }

    public final boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(obj instanceof ModuleAdapter)
        {
            ModuleAdapter moduleadapter = (ModuleAdapter)obj;
            return moduleClass.equals(moduleadapter.moduleClass);
        } else
        {
            return false;
        }
    }

    public void getBindings(BindingsGroup bindingsgroup, Object obj)
    {
    }

    public final int hashCode()
    {
        return moduleClass.hashCode();
    }

    protected Object newModule()
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("No no-args constructor on ").append(getClass().getName()).toString());
    }

    public final boolean complete;
    public final Class includes[];
    public final String injectableTypes[];
    public final boolean library;
    public final Class moduleClass;
    public final boolean overrides;
    public final Class staticInjections[];
}
