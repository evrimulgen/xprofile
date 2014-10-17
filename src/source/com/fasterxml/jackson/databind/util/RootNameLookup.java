// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;

// Referenced classes of package com.fasterxml.jackson.databind.util:
//            LRUMap

public class RootNameLookup
    implements Serializable
{

    public RootNameLookup()
    {
    }

    public SerializedString findRootName(JavaType javatype, MapperConfig mapperconfig)
    {
        return findRootName(javatype.getRawClass(), mapperconfig);
    }

    public SerializedString findRootName(Class class1, MapperConfig mapperconfig)
    {
        ClassKey classkey = new ClassKey(class1);
        this;
        JVM INSTR monitorenter ;
        if(_rootNames != null) goto _L2; else goto _L1
_L1:
        _rootNames = new LRUMap(20, 200);
_L4:
        this;
        JVM INSTR monitorexit ;
        BeanDescription beandescription = mapperconfig.introspectClassAnnotations(class1);
        PropertyName propertyname = mapperconfig.getAnnotationIntrospector().findRootName(beandescription.getClassInfo());
        Exception exception;
        SerializedString serializedstring;
        String s;
        SerializedString serializedstring1;
        if(propertyname == null || !propertyname.hasSimpleName())
            s = class1.getSimpleName();
        else
            s = propertyname.getSimpleName();
        serializedstring1 = new SerializedString(s);
        this;
        JVM INSTR monitorenter ;
        _rootNames.put(classkey, serializedstring1);
        this;
        JVM INSTR monitorexit ;
        return serializedstring1;
_L2:
        serializedstring = (SerializedString)_rootNames.get(classkey);
        if(serializedstring == null) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return serializedstring;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    private static final long serialVersionUID = 1L;
    protected transient LRUMap _rootNames;
}
