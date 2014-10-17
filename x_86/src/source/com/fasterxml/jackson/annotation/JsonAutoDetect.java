// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public interface JsonAutoDetect
    extends Annotation
{
    public static final class Visibility extends Enum
    {

        public static Visibility valueOf(String s)
        {
            return (Visibility)Enum.valueOf(com/fasterxml/jackson/annotation/JsonAutoDetect$Visibility, s);
        }

        public static Visibility[] values()
        {
            return (Visibility[])$VALUES.clone();
        }

        public boolean isVisible(Member member)
        {
            boolean flag = true;
            static class _cls1
            {

                static final int $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility[];

                static 
                {
                    $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility = new int[Visibility.values().length];
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility[Visibility.ANY.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility[Visibility.NONE.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility[Visibility.NON_PRIVATE.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility[Visibility.PROTECTED_AND_PUBLIC.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility[Visibility.PUBLIC_ONLY.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror4)
                    {
                        return;
                    }
                }
            }

            _cls1..SwitchMap.com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility[ordinal()];
            JVM INSTR tableswitch 1 5: default 44
        //                       1 46
        //                       2 48
        //                       3 50
        //                       4 64
        //                       5 76;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
            break MISSING_BLOCK_LABEL_46;
_L1:
            flag = false;
_L8:
            return flag;
_L3:
            return false;
_L4:
            if(!Modifier.isPrivate(member.getModifiers())) goto _L8; else goto _L7
_L7:
            return false;
_L5:
            if(Modifier.isProtected(member.getModifiers())) goto _L8; else goto _L6
_L6:
            return Modifier.isPublic(member.getModifiers());
        }

        private static final Visibility $VALUES[];
        public static final Visibility ANY;
        public static final Visibility DEFAULT;
        public static final Visibility NONE;
        public static final Visibility NON_PRIVATE;
        public static final Visibility PROTECTED_AND_PUBLIC;
        public static final Visibility PUBLIC_ONLY;

        static 
        {
            ANY = new Visibility("ANY", 0);
            NON_PRIVATE = new Visibility("NON_PRIVATE", 1);
            PROTECTED_AND_PUBLIC = new Visibility("PROTECTED_AND_PUBLIC", 2);
            PUBLIC_ONLY = new Visibility("PUBLIC_ONLY", 3);
            NONE = new Visibility("NONE", 4);
            DEFAULT = new Visibility("DEFAULT", 5);
            Visibility avisibility[] = new Visibility[6];
            avisibility[0] = ANY;
            avisibility[1] = NON_PRIVATE;
            avisibility[2] = PROTECTED_AND_PUBLIC;
            avisibility[3] = PUBLIC_ONLY;
            avisibility[4] = NONE;
            avisibility[5] = DEFAULT;
            $VALUES = avisibility;
        }

        private Visibility(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract Visibility creatorVisibility();

    public abstract Visibility fieldVisibility();

    public abstract Visibility getterVisibility();

    public abstract Visibility isGetterVisibility();

    public abstract Visibility setterVisibility();
}
