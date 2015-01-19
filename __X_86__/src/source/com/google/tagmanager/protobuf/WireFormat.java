// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;


// Referenced classes of package com.google.tagmanager.protobuf:
//            ByteString

public final class WireFormat
{
    public static class FieldType extends Enum
    {

        public static FieldType valueOf(String s)
        {
            return (FieldType)Enum.valueOf(com/google/tagmanager/protobuf/WireFormat$FieldType, s);
        }

        public static FieldType[] values()
        {
            return (FieldType[])$VALUES.clone();
        }

        public JavaType getJavaType()
        {
            return javaType;
        }

        public int getWireType()
        {
            return wireType;
        }

        public boolean isPackable()
        {
            return true;
        }

        private static final FieldType $VALUES[];
        public static final FieldType BOOL;
        public static final FieldType BYTES;
        public static final FieldType DOUBLE;
        public static final FieldType ENUM;
        public static final FieldType FIXED32;
        public static final FieldType FIXED64;
        public static final FieldType FLOAT;
        public static final FieldType GROUP;
        public static final FieldType INT32;
        public static final FieldType INT64;
        public static final FieldType MESSAGE;
        public static final FieldType SFIXED32;
        public static final FieldType SFIXED64;
        public static final FieldType SINT32;
        public static final FieldType SINT64;
        public static final FieldType STRING;
        public static final FieldType UINT32;
        public static final FieldType UINT64;
        private final JavaType javaType;
        private final int wireType;

        static 
        {
            DOUBLE = new FieldType("DOUBLE", 0, JavaType.DOUBLE, 1);
            FLOAT = new FieldType("FLOAT", 1, JavaType.FLOAT, 5);
            INT64 = new FieldType("INT64", 2, JavaType.LONG, 0);
            UINT64 = new FieldType("UINT64", 3, JavaType.LONG, 0);
            INT32 = new FieldType("INT32", 4, JavaType.INT, 0);
            FIXED64 = new FieldType("FIXED64", 5, JavaType.LONG, 1);
            FIXED32 = new FieldType("FIXED32", 6, JavaType.INT, 5);
            BOOL = new FieldType("BOOL", 7, JavaType.BOOLEAN, 0);
            STRING = new FieldType("STRING", 8, JavaType.STRING, 2) {

                public boolean isPackable()
                {
                    return false;
                }

            }
;
            GROUP = new FieldType("GROUP", 9, JavaType.MESSAGE, 3) {

                public boolean isPackable()
                {
                    return false;
                }

            }
;
            MESSAGE = new FieldType("MESSAGE", 10, JavaType.MESSAGE, 2) {

                public boolean isPackable()
                {
                    return false;
                }

            }
;
            BYTES = new FieldType("BYTES", 11, JavaType.BYTE_STRING, 2) {

                public boolean isPackable()
                {
                    return false;
                }

            }
;
            UINT32 = new FieldType("UINT32", 12, JavaType.INT, 0);
            ENUM = new FieldType("ENUM", 13, JavaType.ENUM, 0);
            SFIXED32 = new FieldType("SFIXED32", 14, JavaType.INT, 5);
            SFIXED64 = new FieldType("SFIXED64", 15, JavaType.LONG, 1);
            SINT32 = new FieldType("SINT32", 16, JavaType.INT, 0);
            SINT64 = new FieldType("SINT64", 17, JavaType.LONG, 0);
            FieldType afieldtype[] = new FieldType[18];
            afieldtype[0] = DOUBLE;
            afieldtype[1] = FLOAT;
            afieldtype[2] = INT64;
            afieldtype[3] = UINT64;
            afieldtype[4] = INT32;
            afieldtype[5] = FIXED64;
            afieldtype[6] = FIXED32;
            afieldtype[7] = BOOL;
            afieldtype[8] = STRING;
            afieldtype[9] = GROUP;
            afieldtype[10] = MESSAGE;
            afieldtype[11] = BYTES;
            afieldtype[12] = UINT32;
            afieldtype[13] = ENUM;
            afieldtype[14] = SFIXED32;
            afieldtype[15] = SFIXED64;
            afieldtype[16] = SINT32;
            afieldtype[17] = SINT64;
            $VALUES = afieldtype;
        }

        private FieldType(String s, int i, JavaType javatype, int j)
        {
            super(s, i);
            javaType = javatype;
            wireType = j;
        }

    }

    public static final class JavaType extends Enum
    {

        public static JavaType valueOf(String s)
        {
            return (JavaType)Enum.valueOf(com/google/tagmanager/protobuf/WireFormat$JavaType, s);
        }

        public static JavaType[] values()
        {
            return (JavaType[])$VALUES.clone();
        }

        Object getDefaultDefault()
        {
            return defaultDefault;
        }

        private static final JavaType $VALUES[];
        public static final JavaType BOOLEAN;
        public static final JavaType BYTE_STRING;
        public static final JavaType DOUBLE;
        public static final JavaType ENUM;
        public static final JavaType FLOAT;
        public static final JavaType INT;
        public static final JavaType LONG;
        public static final JavaType MESSAGE;
        public static final JavaType STRING;
        private final Object defaultDefault;

        static 
        {
            INT = new JavaType("INT", 0, Integer.valueOf(0));
            LONG = new JavaType("LONG", 1, Long.valueOf(0L));
            FLOAT = new JavaType("FLOAT", 2, Float.valueOf(0.0F));
            DOUBLE = new JavaType("DOUBLE", 3, Double.valueOf(0.0D));
            BOOLEAN = new JavaType("BOOLEAN", 4, Boolean.valueOf(false));
            STRING = new JavaType("STRING", 5, "");
            BYTE_STRING = new JavaType("BYTE_STRING", 6, ByteString.EMPTY);
            ENUM = new JavaType("ENUM", 7, null);
            MESSAGE = new JavaType("MESSAGE", 8, null);
            JavaType ajavatype[] = new JavaType[9];
            ajavatype[0] = INT;
            ajavatype[1] = LONG;
            ajavatype[2] = FLOAT;
            ajavatype[3] = DOUBLE;
            ajavatype[4] = BOOLEAN;
            ajavatype[5] = STRING;
            ajavatype[6] = BYTE_STRING;
            ajavatype[7] = ENUM;
            ajavatype[8] = MESSAGE;
            $VALUES = ajavatype;
        }

        private JavaType(String s, int i, Object obj)
        {
            super(s, i);
            defaultDefault = obj;
        }
    }


    private WireFormat()
    {
    }

    public static int getTagFieldNumber(int i)
    {
        return i >>> 3;
    }

    static int getTagWireType(int i)
    {
        return i & 7;
    }

    static int makeTag(int i, int j)
    {
        return j | i << 3;
    }

    static final int MESSAGE_SET_ITEM = 1;
    static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
    static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
    static final int MESSAGE_SET_MESSAGE = 3;
    static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);
    static final int MESSAGE_SET_TYPE_ID = 2;
    static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
    static final int TAG_TYPE_BITS = 3;
    static final int TAG_TYPE_MASK = 7;
    public static final int WIRETYPE_END_GROUP = 4;
    public static final int WIRETYPE_FIXED32 = 5;
    public static final int WIRETYPE_FIXED64 = 1;
    public static final int WIRETYPE_LENGTH_DELIMITED = 2;
    public static final int WIRETYPE_START_GROUP = 3;
    public static final int WIRETYPE_VARINT;

}
