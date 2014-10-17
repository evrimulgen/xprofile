// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

// Referenced classes of package com.fasterxml.jackson.databind.jsontype.impl:
//            ClassNameIdResolver

public class MinimalClassNameIdResolver extends ClassNameIdResolver
{

    protected MinimalClassNameIdResolver(JavaType javatype, TypeFactory typefactory)
    {
        super(javatype, typefactory);
        String s = javatype.getRawClass().getName();
        int i = s.lastIndexOf('.');
        if(i < 0)
        {
            _basePackageName = "";
            _basePackagePrefix = ".";
            return;
        } else
        {
            _basePackagePrefix = s.substring(0, i + 1);
            _basePackageName = s.substring(0, i);
            return;
        }
    }

    protected JavaType _typeFromId(String s, TypeFactory typefactory)
    {
        if(s.startsWith("."))
        {
            StringBuilder stringbuilder = new StringBuilder(s.length() + _basePackageName.length());
            if(_basePackageName.length() == 0)
                stringbuilder.append(s.substring(1));
            else
                stringbuilder.append(_basePackageName).append(s);
            s = stringbuilder.toString();
        }
        return super._typeFromId(s, typefactory);
    }

    public com.fasterxml.jackson.annotation.JsonTypeInfo.Id getMechanism()
    {
        return com.fasterxml.jackson.annotation.JsonTypeInfo.Id.MINIMAL_CLASS;
    }

    public String idFromValue(Object obj)
    {
        String s = obj.getClass().getName();
        if(s.startsWith(_basePackagePrefix))
            s = s.substring(-1 + _basePackagePrefix.length());
        return s;
    }

    protected final String _basePackageName;
    protected final String _basePackagePrefix;
}
