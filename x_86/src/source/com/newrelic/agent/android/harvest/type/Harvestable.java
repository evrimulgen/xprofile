// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.*;

public interface Harvestable
{
    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(com/newrelic/agent/android/harvest/type/Harvestable$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        private static final Type $VALUES[];
        public static final Type ARRAY;
        public static final Type OBJECT;
        public static final Type VALUE;

        static 
        {
            OBJECT = new Type("OBJECT", 0);
            ARRAY = new Type("ARRAY", 1);
            VALUE = new Type("VALUE", 2);
            Type atype[] = new Type[3];
            atype[0] = OBJECT;
            atype[1] = ARRAY;
            atype[2] = VALUE;
            $VALUES = atype;
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract JsonElement asJson();

    public abstract JsonArray asJsonArray();

    public abstract JsonObject asJsonObject();

    public abstract JsonPrimitive asJsonPrimitive();

    public abstract Type getType();

    public abstract String toJsonString();
}
