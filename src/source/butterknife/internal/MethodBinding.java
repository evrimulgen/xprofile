// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package butterknife.internal;

import java.util.*;

// Referenced classes of package butterknife.internal:
//            Binding

final class MethodBinding
    implements Binding
{

    MethodBinding(String s, List list, boolean flag)
    {
        name = s;
        parameters = Collections.unmodifiableList(new ArrayList(list));
        required = flag;
    }

    public String getDescription()
    {
        return (new StringBuilder()).append("method '").append(name).append("'").toString();
    }

    public String getName()
    {
        return name;
    }

    public List getParameters()
    {
        return parameters;
    }

    public boolean isRequired()
    {
        return required;
    }

    private final String name;
    private final List parameters;
    private final boolean required;
}
