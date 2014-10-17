// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;

public interface JsonTypeInfo
    extends Annotation
{
    public static final class As extends Enum
    {

        public static As valueOf(String s)
        {
            return (As)Enum.valueOf(com/fasterxml/jackson/annotation/JsonTypeInfo$As, s);
        }

        public static As[] values()
        {
            return (As[])$VALUES.clone();
        }

        private static final As $VALUES[];
        public static final As EXISTING_PROPERTY;
        public static final As EXTERNAL_PROPERTY;
        public static final As PROPERTY;
        public static final As WRAPPER_ARRAY;
        public static final As WRAPPER_OBJECT;

        static 
        {
            PROPERTY = new As("PROPERTY", 0);
            WRAPPER_OBJECT = new As("WRAPPER_OBJECT", 1);
            WRAPPER_ARRAY = new As("WRAPPER_ARRAY", 2);
            EXTERNAL_PROPERTY = new As("EXTERNAL_PROPERTY", 3);
            EXISTING_PROPERTY = new As("EXISTING_PROPERTY", 4);
            As aas[] = new As[5];
            aas[0] = PROPERTY;
            aas[1] = WRAPPER_OBJECT;
            aas[2] = WRAPPER_ARRAY;
            aas[3] = EXTERNAL_PROPERTY;
            aas[4] = EXISTING_PROPERTY;
            $VALUES = aas;
        }

        private As(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Id extends Enum
    {

        public static Id valueOf(String s)
        {
            return (Id)Enum.valueOf(com/fasterxml/jackson/annotation/JsonTypeInfo$Id, s);
        }

        public static Id[] values()
        {
            return (Id[])$VALUES.clone();
        }

        public String getDefaultPropertyName()
        {
            return _defaultPropertyName;
        }

        private static final Id $VALUES[];
        public static final Id CLASS;
        public static final Id CUSTOM;
        public static final Id MINIMAL_CLASS;
        public static final Id NAME;
        public static final Id NONE;
        private final String _defaultPropertyName;

        static 
        {
            NONE = new Id("NONE", 0, null);
            CLASS = new Id("CLASS", 1, "@class");
            MINIMAL_CLASS = new Id("MINIMAL_CLASS", 2, "@c");
            NAME = new Id("NAME", 3, "@type");
            CUSTOM = new Id("CUSTOM", 4, null);
            Id aid[] = new Id[5];
            aid[0] = NONE;
            aid[1] = CLASS;
            aid[2] = MINIMAL_CLASS;
            aid[3] = NAME;
            aid[4] = CUSTOM;
            $VALUES = aid;
        }

        private Id(String s, int i, String s1)
        {
            super(s, i);
            _defaultPropertyName = s1;
        }
    }

    public static abstract class None
    {

        public None()
        {
        }
    }


    public abstract Class defaultImpl();

    public abstract As include();

    public abstract String property();

    public abstract Id use();

    public abstract boolean visible();
}
