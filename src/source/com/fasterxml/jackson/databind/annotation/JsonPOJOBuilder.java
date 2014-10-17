// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.annotation;

import java.lang.annotation.Annotation;

public interface JsonPOJOBuilder
    extends Annotation
{
    public static class Value
    {

        public final String buildMethodName;
        public final String withPrefix;

        public Value(JsonPOJOBuilder jsonpojobuilder)
        {
            buildMethodName = jsonpojobuilder.buildMethodName();
            withPrefix = jsonpojobuilder.withPrefix();
        }
    }


    public abstract String buildMethodName();

    public abstract String withPrefix();
}
