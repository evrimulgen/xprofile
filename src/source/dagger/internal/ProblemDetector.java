// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import java.util.*;

// Referenced classes of package dagger.internal:
//            Binding

public final class ProblemDetector
{
    static class ArraySet extends AbstractSet
    {

        public boolean add(Object obj)
        {
            list.add(obj);
            return true;
        }

        public Iterator iterator()
        {
            return list.iterator();
        }

        public int size()
        {
            throw new UnsupportedOperationException();
        }

        private final ArrayList list = new ArrayList();

        ArraySet()
        {
        }
    }


    public ProblemDetector()
    {
    }

    private static void detectCircularDependencies(Collection collection, List list)
    {
        Iterator iterator = collection.iterator();
_L2:
        Binding binding;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_264;
            binding = (Binding)iterator.next();
        } while(binding.isCycleFree());
        if(binding.isVisiting())
        {
            int i = list.indexOf(binding);
            StringBuilder stringbuilder = (new StringBuilder()).append("Dependency cycle:");
            for(int j = i; j < list.size(); j++)
                stringbuilder.append("\n    ").append(j - i).append(". ").append(((Binding)list.get(j)).provideKey).append(" bound by ").append(list.get(j));

            stringbuilder.append("\n    ").append(0).append(". ").append(binding.provideKey);
            throw new IllegalStateException(stringbuilder.toString());
        }
        binding.setVisiting(true);
        list.add(binding);
        ArraySet arrayset = new ArraySet();
        binding.getDependencies(arrayset, arrayset);
        detectCircularDependencies(((Collection) (arrayset)), list);
        binding.setCycleFree(true);
        list.remove(-1 + list.size());
        binding.setVisiting(false);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        list.remove(-1 + list.size());
        binding.setVisiting(false);
        throw exception;
    }

    public void detectCircularDependencies(Collection collection)
    {
        detectCircularDependencies(collection, ((List) (new ArrayList())));
    }

    public void detectProblems(Collection collection)
    {
        detectCircularDependencies(collection);
        detectUnusedBinding(collection);
    }

    public void detectUnusedBinding(Collection collection)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = collection.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Binding binding = (Binding)iterator.next();
            if(!binding.library() && !binding.dependedOn())
                arraylist.add(binding);
        } while(true);
        if(!arraylist.isEmpty())
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("You have these unused @Provider methods:");
            for(int i = 0; i < arraylist.size(); i++)
                stringbuilder.append("\n    ").append(i + 1).append(". ").append(((Binding)arraylist.get(i)).requiredBy);

            stringbuilder.append("\n    Set library=true in your module to disable this check.");
            throw new IllegalStateException(stringbuilder.toString());
        } else
        {
            return;
        }
    }
}
