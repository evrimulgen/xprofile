// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.util.InternCache;
import java.io.Serializable;

public class PropertyName
    implements Serializable
{

    public PropertyName(String s)
    {
        this(s, null);
    }

    public PropertyName(String s, String s1)
    {
        if(s == null)
            s = "";
        _simpleName = s;
        _namespace = s1;
    }

    public static PropertyName construct(String s, String s1)
    {
        if(s == null)
            s = "";
        if(s1 == null && s.length() == 0)
            return USE_DEFAULT;
        else
            return new PropertyName(s, s1);
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj != this) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = false;
        if(obj == null) goto _L4; else goto _L3
_L3:
        Class class1;
        Class class2;
        class1 = obj.getClass();
        class2 = getClass();
        flag1 = false;
        if(class1 != class2) goto _L4; else goto _L5
_L5:
        PropertyName propertyname;
        String s;
        propertyname = (PropertyName)obj;
        if(_simpleName != null)
            break MISSING_BLOCK_LABEL_82;
        s = propertyname._simpleName;
        flag1 = false;
        if(s != null) goto _L4; else goto _L6
_L6:
        if(_namespace == null)
        {
            if(propertyname._namespace != null)
                flag = false;
            return flag;
        } else
        {
            return _namespace.equals(propertyname._namespace);
        }
        if(!_simpleName.equals(propertyname._simpleName))
            return false;
          goto _L6
    }

    public String getNamespace()
    {
        return _namespace;
    }

    public String getSimpleName()
    {
        return _simpleName;
    }

    public boolean hasNamespace()
    {
        return _namespace != null;
    }

    public boolean hasSimpleName()
    {
        return _simpleName.length() > 0;
    }

    public boolean hasSimpleName(String s)
    {
        if(s == null)
            return _simpleName == null;
        else
            return s.equals(_simpleName);
    }

    public int hashCode()
    {
        if(_namespace == null)
            return _simpleName.hashCode();
        else
            return _namespace.hashCode() ^ _simpleName.hashCode();
    }

    public PropertyName internSimpleName()
    {
        String s;
        if(_simpleName.length() != 0)
            if((s = InternCache.instance.intern(_simpleName)) != _simpleName)
                return new PropertyName(s, _namespace);
        return this;
    }

    protected Object readResolve()
    {
        if(_simpleName == null || "".equals(_simpleName))
            this = USE_DEFAULT;
        else
        if(_simpleName.equals("") && _namespace == null)
            return NO_NAME;
        return this;
    }

    public String toString()
    {
        if(_namespace == null)
            return _simpleName;
        else
            return (new StringBuilder()).append("{").append(_namespace).append("}").append(_simpleName).toString();
    }

    public PropertyName withNamespace(String s)
    {
        if(s != null ? s.equals(_namespace) : _namespace == null)
            return this;
        else
            return new PropertyName(_simpleName, s);
    }

    public PropertyName withSimpleName(String s)
    {
        if(s == null)
            s = "";
        if(s.equals(_simpleName))
            return this;
        else
            return new PropertyName(s, _namespace);
    }

    public static final PropertyName NO_NAME = new PropertyName(new String(""), null);
    public static final PropertyName USE_DEFAULT = new PropertyName("", null);
    private static final String _NO_NAME = "";
    private static final String _USE_DEFAULT = "";
    private static final long serialVersionUID = 0x6e0fe282c0ebea86L;
    protected final String _namespace;
    protected final String _simpleName;

}
