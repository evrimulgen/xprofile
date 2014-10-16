// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.annotation;

import java.lang.annotation.Annotation;

public interface JsonSerialize
    extends Annotation
{
    public static final class Inclusion extends Enum
    {

        public static Inclusion valueOf(String s)
        {
            return (Inclusion)Enum.valueOf(com/fasterxml/jackson/databind/annotation/JsonSerialize$Inclusion, s);
        }

        public static Inclusion[] values()
        {
            return (Inclusion[])$VALUES.clone();
        }

        private static final Inclusion $VALUES[];
        public static final Inclusion ALWAYS;
        public static final Inclusion DEFAULT_INCLUSION;
        public static final Inclusion NON_DEFAULT;
        public static final Inclusion NON_EMPTY;
        public static final Inclusion NON_NULL;

        static 
        {
            ALWAYS = new Inclusion("ALWAYS", 0);
            NON_NULL = new Inclusion("NON_NULL", 1);
            NON_DEFAULT = new Inclusion("NON_DEFAULT", 2);
            NON_EMPTY = new Inclusion("NON_EMPTY", 3);
            DEFAULT_INCLUSION = new Inclusion("DEFAULT_INCLUSION", 4);
            Inclusion ainclusion[] = new Inclusion[5];
            ainclusion[0] = ALWAYS;
            ainclusion[1] = NON_NULL;
            ainclusion[2] = NON_DEFAULT;
            ainclusion[3] = NON_EMPTY;
            ainclusion[4] = DEFAULT_INCLUSION;
            $VALUES = ainclusion;
        }

        private Inclusion(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Typing extends Enum
    {

        public static Typing valueOf(String s)
        {
            return (Typing)Enum.valueOf(com/fasterxml/jackson/databind/annotation/JsonSerialize$Typing, s);
        }

        public static Typing[] values()
        {
            return (Typing[])$VALUES.clone();
        }

        private static final Typing $VALUES[];
        public static final Typing DEFAULT_TYPING;
        public static final Typing DYNAMIC;
        public static final Typing STATIC;

        static 
        {
            DYNAMIC = new Typing("DYNAMIC", 0);
            STATIC = new Typing("STATIC", 1);
            DEFAULT_TYPING = new Typing("DEFAULT_TYPING", 2);
            Typing atyping[] = new Typing[3];
            atyping[0] = DYNAMIC;
            atyping[1] = STATIC;
            atyping[2] = DEFAULT_TYPING;
            $VALUES = atyping;
        }

        private Typing(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract Class as();

    public abstract Class contentAs();

    public abstract Class contentConverter();

    public abstract Class contentUsing();

    public abstract Class converter();

    public abstract Inclusion include();

    public abstract Class keyAs();

    public abstract Class keyUsing();

    public abstract Class nullsUsing();

    public abstract Typing typing();

    public abstract Class using();
}
