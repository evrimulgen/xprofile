// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class TypeBase extends JavaType
    implements JsonSerializable
{

    protected TypeBase(Class class1, int i, Object obj, Object obj1)
    {
        this(class1, i, obj, obj1, false);
    }

    protected TypeBase(Class class1, int i, Object obj, Object obj1, boolean flag)
    {
        super(class1, i, obj, obj1, flag);
    }

    protected static StringBuilder _classSignature(Class class1, StringBuilder stringbuilder, boolean flag)
    {
        if(!class1.isPrimitive()) goto _L2; else goto _L1
_L1:
        if(class1 != Boolean.TYPE) goto _L4; else goto _L3
_L3:
        stringbuilder.append('Z');
_L6:
        return stringbuilder;
_L4:
        if(class1 == Byte.TYPE)
        {
            stringbuilder.append('B');
            return stringbuilder;
        }
        if(class1 == Short.TYPE)
        {
            stringbuilder.append('S');
            return stringbuilder;
        }
        if(class1 == Character.TYPE)
        {
            stringbuilder.append('C');
            return stringbuilder;
        }
        if(class1 == Integer.TYPE)
        {
            stringbuilder.append('I');
            return stringbuilder;
        }
        if(class1 == Long.TYPE)
        {
            stringbuilder.append('J');
            return stringbuilder;
        }
        if(class1 == Float.TYPE)
        {
            stringbuilder.append('F');
            return stringbuilder;
        }
        if(class1 == Double.TYPE)
        {
            stringbuilder.append('D');
            return stringbuilder;
        }
        if(class1 == Void.TYPE)
        {
            stringbuilder.append('V');
            return stringbuilder;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Unrecognized primitive type: ").append(class1.getName()).toString());
        }
_L2:
        stringbuilder.append('L');
        String s = class1.getName();
        int i = s.length();
        for(int j = 0; j < i; j++)
        {
            char c = s.charAt(j);
            if(c == '.')
                c = '/';
            stringbuilder.append(c);
        }

        if(flag)
        {
            stringbuilder.append(';');
            return stringbuilder;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected abstract String buildCanonicalName();

    public abstract StringBuilder getErasedSignature(StringBuilder stringbuilder);

    public abstract StringBuilder getGenericSignature(StringBuilder stringbuilder);

    public Object getTypeHandler()
    {
        return _typeHandler;
    }

    public Object getValueHandler()
    {
        return _valueHandler;
    }

    public void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeString(toCanonical());
    }

    public void serializeWithType(JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonProcessingException
    {
        typeserializer.writeTypePrefixForScalar(this, jsongenerator);
        serialize(jsongenerator, serializerprovider);
        typeserializer.writeTypeSuffixForScalar(this, jsongenerator);
    }

    public String toCanonical()
    {
        String s = _canonicalName;
        if(s == null)
            s = buildCanonicalName();
        return s;
    }

    private static final long serialVersionUID = 0xce4d066641f17aa3L;
    volatile transient String _canonicalName;
}
