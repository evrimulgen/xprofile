// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;

public interface JsonInclude
    extends Annotation
{
    public static final class Include extends Enum
    {

        public static Include valueOf(String s)
        {
            return (Include)Enum.valueOf(com/fasterxml/jackson/annotation/JsonInclude$Include, s);
        }

        public static Include[] values()
        {
            return (Include[])$VALUES.clone();
        }

        private static final Include $VALUES[];
        public static final Include ALWAYS;
        public static final Include NON_DEFAULT;
        public static final Include NON_EMPTY;
        public static final Include NON_NULL;

        static 
        {
            ALWAYS = new Include("ALWAYS", 0);
            NON_NULL = new Include("NON_NULL", 1);
            NON_DEFAULT = new Include("NON_DEFAULT", 2);
            NON_EMPTY = new Include("NON_EMPTY", 3);
            Include ainclude[] = new Include[4];
            ainclude[0] = ALWAYS;
            ainclude[1] = NON_NULL;
            ainclude[2] = NON_DEFAULT;
            ainclude[3] = NON_EMPTY;
            $VALUES = ainclude;
        }

        private Include(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract Include value();
}
