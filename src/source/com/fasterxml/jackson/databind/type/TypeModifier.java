// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Type;

// Referenced classes of package com.fasterxml.jackson.databind.type:
//            TypeBindings, TypeFactory

public abstract class TypeModifier
{

    public TypeModifier()
    {
    }

    public abstract JavaType modifyType(JavaType javatype, Type type, TypeBindings typebindings, TypeFactory typefactory);
}
