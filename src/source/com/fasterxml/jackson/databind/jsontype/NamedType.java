// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype;

import java.io.Serializable;

public final class NamedType
    implements Serializable
{

    public NamedType(Class class1)
    {
        this(class1, null);
    }

    public NamedType(Class class1, String s)
    {
        _class = class1;
        _hashCode = class1.getName().hashCode();
        setName(s);
    }

    public boolean equals(Object obj)
    {
        if(obj != this)
        {
            if(obj == null)
                return false;
            if(obj.getClass() != getClass())
                return false;
            if(_class != ((NamedType)obj)._class)
                return false;
        }
        return true;
    }

    public String getName()
    {
        return _name;
    }

    public Class getType()
    {
        return _class;
    }

    public boolean hasName()
    {
        return _name != null;
    }

    public int hashCode()
    {
        return _hashCode;
    }

    public void setName(String s)
    {
        if(s == null || s.length() == 0)
            s = null;
        _name = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("[NamedType, class ").append(_class.getName()).append(", name: ");
        String s;
        if(_name == null)
            s = "null";
        else
            s = (new StringBuilder()).append("'").append(_name).append("'").toString();
        return stringbuilder.append(s).append("]").toString();
    }

    private static final long serialVersionUID = 1L;
    protected final Class _class;
    protected final int _hashCode;
    protected String _name;
}
