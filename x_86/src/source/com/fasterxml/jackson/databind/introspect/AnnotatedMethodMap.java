// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.introspect:
//            MemberKey, AnnotatedMethod

public final class AnnotatedMethodMap
    implements Iterable
{

    public AnnotatedMethodMap()
    {
    }

    public void add(AnnotatedMethod annotatedmethod)
    {
        if(_methods == null)
            _methods = new LinkedHashMap();
        _methods.put(new MemberKey(annotatedmethod.getAnnotated()), annotatedmethod);
    }

    public AnnotatedMethod find(String s, Class aclass[])
    {
        if(_methods == null)
            return null;
        else
            return (AnnotatedMethod)_methods.get(new MemberKey(s, aclass));
    }

    public AnnotatedMethod find(Method method)
    {
        if(_methods == null)
            return null;
        else
            return (AnnotatedMethod)_methods.get(new MemberKey(method));
    }

    public boolean isEmpty()
    {
        return _methods == null || _methods.size() == 0;
    }

    public Iterator iterator()
    {
        if(_methods != null)
            return _methods.values().iterator();
        else
            return Collections.emptyList().iterator();
    }

    public AnnotatedMethod remove(AnnotatedMethod annotatedmethod)
    {
        return remove(annotatedmethod.getAnnotated());
    }

    public AnnotatedMethod remove(Method method)
    {
        if(_methods != null)
            return (AnnotatedMethod)_methods.remove(new MemberKey(method));
        else
            return null;
    }

    public int size()
    {
        if(_methods == null)
            return 0;
        else
            return _methods.size();
    }

    protected LinkedHashMap _methods;
}
