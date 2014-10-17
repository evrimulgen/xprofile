// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;

public class ValueInjector extends com.fasterxml.jackson.databind.BeanProperty.Std
{

    public ValueInjector(PropertyName propertyname, JavaType javatype, Annotations annotations, AnnotatedMember annotatedmember, Object obj)
    {
        super(propertyname, javatype, null, annotations, annotatedmember, PropertyMetadata.STD_OPTIONAL);
        _valueId = obj;
    }

    public ValueInjector(String s, JavaType javatype, Annotations annotations, AnnotatedMember annotatedmember, Object obj)
    {
        this(new PropertyName(s), javatype, annotations, annotatedmember, obj);
    }

    public Object findValue(DeserializationContext deserializationcontext, Object obj)
    {
        return deserializationcontext.findInjectableValue(_valueId, this, obj);
    }

    public void inject(DeserializationContext deserializationcontext, Object obj)
        throws IOException
    {
        _member.setValue(obj, findValue(deserializationcontext, obj));
    }

    protected final Object _valueId;
}
