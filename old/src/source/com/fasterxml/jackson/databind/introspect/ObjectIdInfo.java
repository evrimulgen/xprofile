// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.PropertyName;

public class ObjectIdInfo
{

    public ObjectIdInfo(PropertyName propertyname, Class class1, Class class2)
    {
        this(propertyname, class1, class2, false);
    }

    protected ObjectIdInfo(PropertyName propertyname, Class class1, Class class2, boolean flag)
    {
        _propertyName = propertyname;
        _scope = class1;
        _generator = class2;
        _alwaysAsId = flag;
    }

    public ObjectIdInfo(String s, Class class1, Class class2)
    {
        this(new PropertyName(s), class1, class2, false);
    }

    public boolean getAlwaysAsId()
    {
        return _alwaysAsId;
    }

    public Class getGeneratorType()
    {
        return _generator;
    }

    public PropertyName getPropertyName()
    {
        return _propertyName;
    }

    public Class getScope()
    {
        return _scope;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("ObjectIdInfo: propName=").append(_propertyName).append(", scope=");
        String s;
        StringBuilder stringbuilder1;
        String s1;
        if(_scope == null)
            s = "null";
        else
            s = _scope.getName();
        stringbuilder1 = stringbuilder.append(s).append(", generatorType=");
        if(_generator == null)
            s1 = "null";
        else
            s1 = _generator.getName();
        return stringbuilder1.append(s1).append(", alwaysAsId=").append(_alwaysAsId).toString();
    }

    public ObjectIdInfo withAlwaysAsId(boolean flag)
    {
        if(_alwaysAsId == flag)
            return this;
        else
            return new ObjectIdInfo(_propertyName, _scope, _generator, flag);
    }

    protected final boolean _alwaysAsId;
    protected final Class _generator;
    protected final PropertyName _propertyName;
    protected final Class _scope;
}
