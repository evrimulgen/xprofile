// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;


// Referenced classes of package com.newrelic.com.google.gson:
//            JsonElement, JsonPrimitive

public abstract class LongSerializationPolicy extends Enum
{

    private LongSerializationPolicy(String s, int i)
    {
        super(s, i);
    }


    public static LongSerializationPolicy valueOf(String s)
    {
        return (LongSerializationPolicy)Enum.valueOf(com/newrelic/com/google/gson/LongSerializationPolicy, s);
    }

    public static LongSerializationPolicy[] values()
    {
        return (LongSerializationPolicy[])$VALUES.clone();
    }

    public abstract JsonElement serialize(Long long1);

    private static final LongSerializationPolicy $VALUES[];
    public static final LongSerializationPolicy DEFAULT;
    public static final LongSerializationPolicy STRING;

    static 
    {
        DEFAULT = new LongSerializationPolicy("DEFAULT", 0) {

            public JsonElement serialize(Long long1)
            {
                return new JsonPrimitive(long1);
            }

        }
;
        STRING = new LongSerializationPolicy("STRING", 1) {

            public JsonElement serialize(Long long1)
            {
                return new JsonPrimitive(String.valueOf(long1));
            }

        }
;
        LongSerializationPolicy alongserializationpolicy[] = new LongSerializationPolicy[2];
        alongserializationpolicy[0] = DEFAULT;
        alongserializationpolicy[1] = STRING;
        $VALUES = alongserializationpolicy;
    }
}
