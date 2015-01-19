// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package butterknife.internal;

import java.util.*;

// Referenced classes of package butterknife.internal:
//            MethodBinding, Listener, FieldBinding

final class ViewInjection
{

    ViewInjection(int i)
    {
        id = i;
    }

    public void addFieldBinding(FieldBinding fieldbinding)
    {
        fieldBindings.add(fieldbinding);
    }

    public void addMethodBinding(Listener listener, MethodBinding methodbinding)
    {
        MethodBinding methodbinding1 = (MethodBinding)methodBindings.get(listener);
        if(methodbinding1 != null)
        {
            throw new IllegalStateException((new StringBuilder()).append("View ").append(id).append(" already has method binding for ").append(listener.getType()).append(" on ").append(methodbinding1.getName()).toString());
        } else
        {
            methodBindings.put(listener, methodbinding);
            return;
        }
    }

    public Collection getFieldBindings()
    {
        return fieldBindings;
    }

    public int getId()
    {
        return id;
    }

    public Map getMethodBindings()
    {
        return Collections.unmodifiableMap(new LinkedHashMap(methodBindings));
    }

    public List getRequiredBindings()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = fieldBindings.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            FieldBinding fieldbinding = (FieldBinding)iterator.next();
            if(fieldbinding.isRequired())
                arraylist.add(fieldbinding);
        } while(true);
        Iterator iterator1 = methodBindings.values().iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            MethodBinding methodbinding = (MethodBinding)iterator1.next();
            if(methodbinding.isRequired())
                arraylist.add(methodbinding);
        } while(true);
        return arraylist;
    }

    private final Set fieldBindings = new LinkedHashSet();
    private final int id;
    private final Map methodBindings = new LinkedHashMap();
}
