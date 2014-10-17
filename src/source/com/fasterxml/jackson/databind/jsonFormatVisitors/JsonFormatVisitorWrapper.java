// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;

// Referenced classes of package com.fasterxml.jackson.databind.jsonFormatVisitors:
//            JsonFormatVisitorWithSerializerProvider, JsonAnyFormatVisitor, JsonArrayFormatVisitor, JsonBooleanFormatVisitor, 
//            JsonIntegerFormatVisitor, JsonMapFormatVisitor, JsonNullFormatVisitor, JsonNumberFormatVisitor, 
//            JsonObjectFormatVisitor, JsonStringFormatVisitor

public interface JsonFormatVisitorWrapper
    extends JsonFormatVisitorWithSerializerProvider
{

    public abstract JsonAnyFormatVisitor expectAnyFormat(JavaType javatype)
        throws JsonMappingException;

    public abstract JsonArrayFormatVisitor expectArrayFormat(JavaType javatype)
        throws JsonMappingException;

    public abstract JsonBooleanFormatVisitor expectBooleanFormat(JavaType javatype)
        throws JsonMappingException;

    public abstract JsonIntegerFormatVisitor expectIntegerFormat(JavaType javatype)
        throws JsonMappingException;

    public abstract JsonMapFormatVisitor expectMapFormat(JavaType javatype)
        throws JsonMappingException;

    public abstract JsonNullFormatVisitor expectNullFormat(JavaType javatype)
        throws JsonMappingException;

    public abstract JsonNumberFormatVisitor expectNumberFormat(JavaType javatype)
        throws JsonMappingException;

    public abstract JsonObjectFormatVisitor expectObjectFormat(JavaType javatype)
        throws JsonMappingException;

    public abstract JsonStringFormatVisitor expectStringFormat(JavaType javatype)
        throws JsonMappingException;
}
