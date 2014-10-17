// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger;

import java.lang.annotation.Annotation;

public interface Provides
    extends Annotation
{
    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(dagger/Provides$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        private static final Type $VALUES[];
        public static final Type SET;
        public static final Type SET_VALUES;
        public static final Type UNIQUE;

        static 
        {
            UNIQUE = new Type("UNIQUE", 0);
            SET = new Type("SET", 1);
            SET_VALUES = new Type("SET_VALUES", 2);
            Type atype[] = new Type[3];
            atype[0] = UNIQUE;
            atype[1] = SET;
            atype[2] = SET_VALUES;
            $VALUES = atype;
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract Type type();
}
