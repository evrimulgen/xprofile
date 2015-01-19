// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;


public final class JsonNodeType extends Enum
{

    private JsonNodeType(String s, int i)
    {
        super(s, i);
    }

    public static JsonNodeType valueOf(String s)
    {
        return (JsonNodeType)Enum.valueOf(com/fasterxml/jackson/databind/node/JsonNodeType, s);
    }

    public static JsonNodeType[] values()
    {
        return (JsonNodeType[])$VALUES.clone();
    }

    private static final JsonNodeType $VALUES[];
    public static final JsonNodeType ARRAY;
    public static final JsonNodeType BINARY;
    public static final JsonNodeType BOOLEAN;
    public static final JsonNodeType MISSING;
    public static final JsonNodeType NULL;
    public static final JsonNodeType NUMBER;
    public static final JsonNodeType OBJECT;
    public static final JsonNodeType POJO;
    public static final JsonNodeType STRING;

    static 
    {
        ARRAY = new JsonNodeType("ARRAY", 0);
        BINARY = new JsonNodeType("BINARY", 1);
        BOOLEAN = new JsonNodeType("BOOLEAN", 2);
        MISSING = new JsonNodeType("MISSING", 3);
        NULL = new JsonNodeType("NULL", 4);
        NUMBER = new JsonNodeType("NUMBER", 5);
        OBJECT = new JsonNodeType("OBJECT", 6);
        POJO = new JsonNodeType("POJO", 7);
        STRING = new JsonNodeType("STRING", 8);
        JsonNodeType ajsonnodetype[] = new JsonNodeType[9];
        ajsonnodetype[0] = ARRAY;
        ajsonnodetype[1] = BINARY;
        ajsonnodetype[2] = BOOLEAN;
        ajsonnodetype[3] = MISSING;
        ajsonnodetype[4] = NULL;
        ajsonnodetype[5] = NUMBER;
        ajsonnodetype[6] = OBJECT;
        ajsonnodetype[7] = POJO;
        ajsonnodetype[8] = STRING;
        $VALUES = ajsonnodetype;
    }
}
