// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobsandgeeks.saripaar.annotation;

import java.lang.annotation.Annotation;

public interface NumberRule
    extends Annotation
{
    public static final class NumberType extends Enum
    {

        public static NumberType valueOf(String s)
        {
            return (NumberType)Enum.valueOf(com/mobsandgeeks/saripaar/annotation/NumberRule$NumberType, s);
        }

        public static NumberType[] values()
        {
            return (NumberType[])$VALUES.clone();
        }

        private static final NumberType $VALUES[];
        public static final NumberType DOUBLE;
        public static final NumberType FLOAT;
        public static final NumberType INTEGER;
        public static final NumberType LONG;

        static 
        {
            INTEGER = new NumberType("INTEGER", 0);
            LONG = new NumberType("LONG", 1);
            FLOAT = new NumberType("FLOAT", 2);
            DOUBLE = new NumberType("DOUBLE", 3);
            NumberType anumbertype[] = new NumberType[4];
            anumbertype[0] = INTEGER;
            anumbertype[1] = LONG;
            anumbertype[2] = FLOAT;
            anumbertype[3] = DOUBLE;
            $VALUES = anumbertype;
        }

        private NumberType(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract double eq();

    public abstract double gt();

    public abstract double lt();

    public abstract String message();

    public abstract int messageResId();

    public abstract int order();

    public abstract NumberType type();
}
