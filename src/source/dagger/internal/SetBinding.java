// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import java.util.*;

// Referenced classes of package dagger.internal:
//            Binding, Linker, BindingsGroup

public final class SetBinding extends Binding
{

    public SetBinding(SetBinding setbinding)
    {
        super(setbinding.provideKey, null, false, setbinding.requiredBy);
        parent = setbinding;
        setLibrary(setbinding.library());
        setDependedOn(setbinding.dependedOn());
        contributors = new ArrayList();
    }

    public SetBinding(String s, Object obj)
    {
        super(s, null, false, obj);
        parent = null;
        contributors = new ArrayList();
    }

    public static void add(BindingsGroup bindingsgroup, String s, Binding binding)
    {
        prepareSetBinding(bindingsgroup, s, binding).contributors.add(Linker.scope(binding));
    }

    private static SetBinding prepareSetBinding(BindingsGroup bindingsgroup, String s, Binding binding)
    {
        Binding binding1 = bindingsgroup.get(s);
        if(binding1 instanceof SetBinding)
        {
            SetBinding setbinding1 = (SetBinding)binding1;
            boolean flag;
            if(setbinding1.library() && binding.library())
                flag = true;
            else
                flag = false;
            setbinding1.setLibrary(flag);
            return setbinding1;
        }
        if(binding1 != null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate:\n    ").append(binding1).append("\n    ").append(binding).toString());
        } else
        {
            SetBinding setbinding = new SetBinding(s, binding.requiredBy);
            setbinding.setLibrary(binding.library());
            bindingsgroup.contributeSetBinding(s, setbinding);
            return (SetBinding)bindingsgroup.get(s);
        }
    }

    public void attach(Linker linker)
    {
        for(Iterator iterator = contributors.iterator(); iterator.hasNext(); ((Binding)iterator.next()).attach(linker));
    }

    public volatile Object get()
    {
        return get();
    }

    public Set get()
    {
        ArrayList arraylist = new ArrayList();
        for(SetBinding setbinding = this; setbinding != null; setbinding = setbinding.parent)
        {
            int i = 0;
            int j = setbinding.contributors.size();
            while(i < j) 
            {
                Binding binding = (Binding)setbinding.contributors.get(i);
                Object obj = binding.get();
                if(binding.provideKey.equals(provideKey))
                    arraylist.addAll((Set)obj);
                else
                    arraylist.add(obj);
                i++;
            }
        }

        return Collections.unmodifiableSet(new LinkedHashSet(arraylist));
    }

    public void getDependencies(Set set, Set set1)
    {
        for(SetBinding setbinding = this; setbinding != null; setbinding = setbinding.parent)
            set.addAll(setbinding.contributors);

    }

    public volatile void injectMembers(Object obj)
    {
        injectMembers((Set)obj);
    }

    public void injectMembers(Set set)
    {
        throw new UnsupportedOperationException("Cannot inject members on a contributed Set<T>.");
    }

    public int size()
    {
        int i = 0;
        for(SetBinding setbinding = this; setbinding != null; setbinding = setbinding.parent)
            i += setbinding.contributors.size();

        return i;
    }

    public String toString()
    {
        boolean flag = true;
        StringBuilder stringbuilder = new StringBuilder("SetBinding[");
        for(SetBinding setbinding = this; setbinding != null; setbinding = setbinding.parent)
        {
            int i = 0;
            for(int j = setbinding.contributors.size(); i < j;)
            {
                if(!flag)
                    stringbuilder.append(",");
                stringbuilder.append(setbinding.contributors.get(i));
                i++;
                flag = false;
            }

        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    private final List contributors;
    private final SetBinding parent;
}
