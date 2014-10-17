// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package butterknife.internal;

import java.util.*;
import javax.lang.model.element.*;
import javax.lang.model.util.Types;

final class Listener
{

    private Listener(String s, String s1, String s2, String s3, String s4, List list)
    {
        ownerType = s;
        setterName = s1;
        type = s2;
        returnType = s3;
        methodName = s4;
        parameterTypes = list;
    }

    static Listener from(TypeElement typeelement, Types types)
    {
        List list = typeelement.getEnclosedElements();
        if(list.size() != 1)
            throw new IllegalArgumentException((new StringBuilder()).append(typeelement.getSimpleName()).append(" is not a single-method interface").toString());
        ExecutableElement executableelement = (ExecutableElement)list.get(0);
        String s = types.erasure(typeelement.getEnclosingElement().asType()).toString();
        String s1 = (new StringBuilder()).append("set").append(typeelement.getSimpleName()).toString();
        String s2 = typeelement.getQualifiedName().toString();
        String s3 = executableelement.getSimpleName().toString();
        String s4 = executableelement.getReturnType().toString();
        List list1 = executableelement.getParameters();
        ArrayList arraylist = new ArrayList(list1.size());
        String s5;
        for(Iterator iterator = list1.iterator(); iterator.hasNext(); arraylist.add(s5))
        {
            s5 = ((VariableElement)iterator.next()).asType().toString();
            if(s5.startsWith("java.lang.") && !s5.substring(10).contains("."))
                s5 = s5.substring(10);
        }

        return new Listener(s, s1, s2, s4, s3, arraylist);
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            Listener listener = (Listener)obj;
            if(!methodName.equals(listener.methodName) || !ownerType.equals(listener.ownerType) || !parameterTypes.equals(listener.parameterTypes) || !returnType.equals(listener.returnType) || !setterName.equals(listener.setterName) || !type.equals(listener.type))
                return false;
        }
        return true;
    }

    String getMethodName()
    {
        return methodName;
    }

    public String getOwnerType()
    {
        return ownerType;
    }

    List getParameterTypes()
    {
        return parameterTypes;
    }

    String getReturnType()
    {
        return returnType;
    }

    public String getSetterName()
    {
        return setterName;
    }

    String getType()
    {
        return type;
    }

    public int hashCode()
    {
        return 31 * (31 * (31 * (31 * (31 * ownerType.hashCode() + setterName.hashCode()) + type.hashCode()) + returnType.hashCode()) + methodName.hashCode()) + parameterTypes.hashCode();
    }

    private final String methodName;
    private final String ownerType;
    private final List parameterTypes;
    private final String returnType;
    private final String setterName;
    private final String type;
}
