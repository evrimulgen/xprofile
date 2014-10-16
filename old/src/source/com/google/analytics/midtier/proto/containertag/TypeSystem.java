// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.midtier.proto.containertag;

import com.google.tagmanager.protobuf.*;
import java.io.*;
import java.util.*;

public final class TypeSystem
{
    public static final class Value extends com.google.tagmanager.protobuf.GeneratedMessageLite.ExtendableMessage
        implements ValueOrBuilder
    {

        public static Value getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            type_ = Type.STRING;
            string_ = "";
            listItem_ = Collections.emptyList();
            mapKey_ = Collections.emptyList();
            mapValue_ = Collections.emptyList();
            macroReference_ = "";
            functionId_ = "";
            integer_ = 0L;
            boolean_ = false;
            templateToken_ = Collections.emptyList();
            escaping_ = Collections.emptyList();
            containsReferences_ = false;
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(Value value)
        {
            return newBuilder().mergeFrom(value);
        }

        public static Value parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (Value)PARSER.parseDelimitedFrom(inputstream);
        }

        public static Value parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Value)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static Value parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (Value)PARSER.parseFrom(bytestring);
        }

        public static Value parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Value)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static Value parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (Value)PARSER.parseFrom(codedinputstream);
        }

        public static Value parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Value)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static Value parseFrom(InputStream inputstream)
            throws IOException
        {
            return (Value)PARSER.parseFrom(inputstream);
        }

        public static Value parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Value)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static Value parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (Value)PARSER.parseFrom(abyte0);
        }

        public static Value parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Value)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof Value))
                return super.equals(obj);
            Value value = (Value)obj;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            boolean flag6;
            boolean flag7;
            boolean flag8;
            boolean flag9;
            boolean flag10;
            boolean flag11;
            if(true && hasType() == value.hasType())
                flag = true;
            else
                flag = false;
            if(hasType())
                if(flag && getType() == value.getType())
                    flag = true;
                else
                    flag = false;
            if(flag && hasString() == value.hasString())
                flag1 = true;
            else
                flag1 = false;
            if(hasString())
                if(flag1 && getString().equals(value.getString()))
                    flag1 = true;
                else
                    flag1 = false;
            if(flag1 && getListItemList().equals(value.getListItemList()))
                flag2 = true;
            else
                flag2 = false;
            if(flag2 && getMapKeyList().equals(value.getMapKeyList()))
                flag3 = true;
            else
                flag3 = false;
            if(flag3 && getMapValueList().equals(value.getMapValueList()))
                flag4 = true;
            else
                flag4 = false;
            if(flag4 && hasMacroReference() == value.hasMacroReference())
                flag5 = true;
            else
                flag5 = false;
            if(hasMacroReference())
                if(flag5 && getMacroReference().equals(value.getMacroReference()))
                    flag5 = true;
                else
                    flag5 = false;
            if(flag5 && hasFunctionId() == value.hasFunctionId())
                flag6 = true;
            else
                flag6 = false;
            if(hasFunctionId())
                if(flag6 && getFunctionId().equals(value.getFunctionId()))
                    flag6 = true;
                else
                    flag6 = false;
            if(flag6 && hasInteger() == value.hasInteger())
                flag7 = true;
            else
                flag7 = false;
            if(hasInteger())
                if(flag7 && getInteger() == value.getInteger())
                    flag7 = true;
                else
                    flag7 = false;
            if(flag7 && hasBoolean() == value.hasBoolean())
                flag8 = true;
            else
                flag8 = false;
            if(hasBoolean())
                if(flag8 && getBoolean() == value.getBoolean())
                    flag8 = true;
                else
                    flag8 = false;
            if(flag8 && getTemplateTokenList().equals(value.getTemplateTokenList()))
                flag9 = true;
            else
                flag9 = false;
            if(flag9 && getEscapingList().equals(value.getEscapingList()))
                flag10 = true;
            else
                flag10 = false;
            if(flag10 && hasContainsReferences() == value.hasContainsReferences())
                flag11 = true;
            else
                flag11 = false;
            if(hasContainsReferences())
                if(flag11 && getContainsReferences() == value.getContainsReferences())
                    flag11 = true;
                else
                    flag11 = false;
            return flag11;
        }

        public boolean getBoolean()
        {
            return boolean_;
        }

        public boolean getContainsReferences()
        {
            return containsReferences_;
        }

        public Value getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Escaping getEscaping(int i)
        {
            return (Escaping)escaping_.get(i);
        }

        public int getEscapingCount()
        {
            return escaping_.size();
        }

        public List getEscapingList()
        {
            return escaping_;
        }

        public String getFunctionId()
        {
            Object obj = functionId_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                functionId_ = s;
            return s;
        }

        public ByteString getFunctionIdBytes()
        {
            Object obj = functionId_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                functionId_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public long getInteger()
        {
            return integer_;
        }

        public Value getListItem(int i)
        {
            return (Value)listItem_.get(i);
        }

        public int getListItemCount()
        {
            return listItem_.size();
        }

        public List getListItemList()
        {
            return listItem_;
        }

        public ValueOrBuilder getListItemOrBuilder(int i)
        {
            return (ValueOrBuilder)listItem_.get(i);
        }

        public List getListItemOrBuilderList()
        {
            return listItem_;
        }

        public String getMacroReference()
        {
            Object obj = macroReference_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                macroReference_ = s;
            return s;
        }

        public ByteString getMacroReferenceBytes()
        {
            Object obj = macroReference_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                macroReference_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Value getMapKey(int i)
        {
            return (Value)mapKey_.get(i);
        }

        public int getMapKeyCount()
        {
            return mapKey_.size();
        }

        public List getMapKeyList()
        {
            return mapKey_;
        }

        public ValueOrBuilder getMapKeyOrBuilder(int i)
        {
            return (ValueOrBuilder)mapKey_.get(i);
        }

        public List getMapKeyOrBuilderList()
        {
            return mapKey_;
        }

        public Value getMapValue(int i)
        {
            return (Value)mapValue_.get(i);
        }

        public int getMapValueCount()
        {
            return mapValue_.size();
        }

        public List getMapValueList()
        {
            return mapValue_;
        }

        public ValueOrBuilder getMapValueOrBuilder(int i)
        {
            return (ValueOrBuilder)mapValue_.get(i);
        }

        public List getMapValueOrBuilderList()
        {
            return mapValue_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 1 & bitField0_;
            int k = 0;
            if(j == 1)
                k = 0 + CodedOutputStream.computeEnumSize(1, type_.getNumber());
            if((2 & bitField0_) == 2)
                k += CodedOutputStream.computeBytesSize(2, getStringBytes());
            for(int l = 0; l < listItem_.size(); l++)
                k += CodedOutputStream.computeMessageSize(3, (MessageLite)listItem_.get(l));

            for(int i1 = 0; i1 < mapKey_.size(); i1++)
                k += CodedOutputStream.computeMessageSize(4, (MessageLite)mapKey_.get(i1));

            for(int j1 = 0; j1 < mapValue_.size(); j1++)
                k += CodedOutputStream.computeMessageSize(5, (MessageLite)mapValue_.get(j1));

            if((4 & bitField0_) == 4)
                k += CodedOutputStream.computeBytesSize(6, getMacroReferenceBytes());
            if((8 & bitField0_) == 8)
                k += CodedOutputStream.computeBytesSize(7, getFunctionIdBytes());
            if((0x10 & bitField0_) == 16)
                k += CodedOutputStream.computeInt64Size(8, integer_);
            if((0x40 & bitField0_) == 64)
                k += CodedOutputStream.computeBoolSize(9, containsReferences_);
            int k1 = 0;
            for(int l1 = 0; l1 < escaping_.size(); l1++)
                k1 += CodedOutputStream.computeEnumSizeNoTag(((Escaping)escaping_.get(l1)).getNumber());

            int i2 = k + k1 + 1 * escaping_.size();
            for(int j2 = 0; j2 < templateToken_.size(); j2++)
                i2 += CodedOutputStream.computeMessageSize(11, (MessageLite)templateToken_.get(j2));

            if((0x20 & bitField0_) == 32)
                i2 += CodedOutputStream.computeBoolSize(12, boolean_);
            int k2 = i2 + extensionsSerializedSize() + unknownFields.size();
            memoizedSerializedSize = k2;
            return k2;
        }

        public String getString()
        {
            Object obj = string_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                string_ = s;
            return s;
        }

        public ByteString getStringBytes()
        {
            Object obj = string_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                string_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Value getTemplateToken(int i)
        {
            return (Value)templateToken_.get(i);
        }

        public int getTemplateTokenCount()
        {
            return templateToken_.size();
        }

        public List getTemplateTokenList()
        {
            return templateToken_;
        }

        public ValueOrBuilder getTemplateTokenOrBuilder(int i)
        {
            return (ValueOrBuilder)templateToken_.get(i);
        }

        public List getTemplateTokenOrBuilderList()
        {
            return templateToken_;
        }

        public Type getType()
        {
            return type_;
        }

        public boolean hasBoolean()
        {
            return (0x20 & bitField0_) == 32;
        }

        public boolean hasContainsReferences()
        {
            return (0x40 & bitField0_) == 64;
        }

        public boolean hasFunctionId()
        {
            return (8 & bitField0_) == 8;
        }

        public boolean hasInteger()
        {
            return (0x10 & bitField0_) == 16;
        }

        public boolean hasMacroReference()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasString()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasType()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/midtier/proto/containertag/TypeSystem$Value.hashCode();
            if(hasType())
                i = 53 * (1 + i * 37) + Internal.hashEnum(getType());
            if(hasString())
                i = 53 * (2 + i * 37) + getString().hashCode();
            if(getListItemCount() > 0)
                i = 53 * (3 + i * 37) + getListItemList().hashCode();
            if(getMapKeyCount() > 0)
                i = 53 * (4 + i * 37) + getMapKeyList().hashCode();
            if(getMapValueCount() > 0)
                i = 53 * (5 + i * 37) + getMapValueList().hashCode();
            if(hasMacroReference())
                i = 53 * (6 + i * 37) + getMacroReference().hashCode();
            if(hasFunctionId())
                i = 53 * (7 + i * 37) + getFunctionId().hashCode();
            if(hasInteger())
                i = 53 * (8 + i * 37) + Internal.hashLong(getInteger());
            if(hasBoolean())
                i = 53 * (12 + i * 37) + Internal.hashBoolean(getBoolean());
            if(getTemplateTokenCount() > 0)
                i = 53 * (11 + i * 37) + getTemplateTokenList().hashCode();
            if(getEscapingCount() > 0)
                i = 53 * (10 + i * 37) + Internal.hashEnumList(getEscapingList());
            if(hasContainsReferences())
                i = 53 * (9 + i * 37) + Internal.hashBoolean(getContainsReferences());
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.midtier.proto.containertag.MutableTypeSystem$Value");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            boolean flag = true;
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
            {
                if(byte0 != flag)
                    flag = false;
                return flag;
            }
            if(!hasType())
            {
                memoizedIsInitialized = 0;
                return false;
            }
            for(int i = 0; i < getListItemCount(); i++)
                if(!getListItem(i).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int j = 0; j < getMapKeyCount(); j++)
                if(!getMapKey(j).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int k = 0; k < getMapValueCount(); k++)
                if(!getMapValue(k).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int l = 0; l < getTemplateTokenCount(); l++)
                if(!getTemplateToken(l).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            if(!extensionsAreInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = flag;
                return flag;
            }
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            com.google.tagmanager.protobuf.GeneratedMessageLite.ExtendableMessage.ExtensionWriter extensionwriter = newExtensionWriter();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeEnum(1, type_.getNumber());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeBytes(2, getStringBytes());
            for(int i = 0; i < listItem_.size(); i++)
                codedoutputstream.writeMessage(3, (MessageLite)listItem_.get(i));

            for(int j = 0; j < mapKey_.size(); j++)
                codedoutputstream.writeMessage(4, (MessageLite)mapKey_.get(j));

            for(int k = 0; k < mapValue_.size(); k++)
                codedoutputstream.writeMessage(5, (MessageLite)mapValue_.get(k));

            if((4 & bitField0_) == 4)
                codedoutputstream.writeBytes(6, getMacroReferenceBytes());
            if((8 & bitField0_) == 8)
                codedoutputstream.writeBytes(7, getFunctionIdBytes());
            if((0x10 & bitField0_) == 16)
                codedoutputstream.writeInt64(8, integer_);
            if((0x40 & bitField0_) == 64)
                codedoutputstream.writeBool(9, containsReferences_);
            for(int l = 0; l < escaping_.size(); l++)
                codedoutputstream.writeEnum(10, ((Escaping)escaping_.get(l)).getNumber());

            for(int i1 = 0; i1 < templateToken_.size(); i1++)
                codedoutputstream.writeMessage(11, (MessageLite)templateToken_.get(i1));

            if((0x20 & bitField0_) == 32)
                codedoutputstream.writeBool(12, boolean_);
            extensionwriter.writeUntil(0x20000000, codedoutputstream);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int BOOLEAN_FIELD_NUMBER = 12;
        public static final int CONTAINS_REFERENCES_FIELD_NUMBER = 9;
        public static final int ESCAPING_FIELD_NUMBER = 10;
        public static final int FUNCTION_ID_FIELD_NUMBER = 7;
        public static final int INTEGER_FIELD_NUMBER = 8;
        public static final int LIST_ITEM_FIELD_NUMBER = 3;
        public static final int MACRO_REFERENCE_FIELD_NUMBER = 6;
        public static final int MAP_KEY_FIELD_NUMBER = 4;
        public static final int MAP_VALUE_FIELD_NUMBER = 5;
        public static Parser PARSER = new AbstractParser() {

            public Value parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new Value(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int STRING_FIELD_NUMBER = 2;
        public static final int TEMPLATE_TOKEN_FIELD_NUMBER = 11;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final Value defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private boolean boolean_;
        private boolean containsReferences_;
        private List escaping_;
        private Object functionId_;
        private long integer_;
        private List listItem_;
        private Object macroReference_;
        private List mapKey_;
        private List mapValue_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object string_;
        private List templateToken_;
        private Type type_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new Value(true);
            defaultInstance.initFields();
        }


/*
        static long access$1002(Value value, long l)
        {
            value.integer_ = l;
            return l;
        }

*/


/*
        static boolean access$1102(Value value, boolean flag)
        {
            value.boolean_ = flag;
            return flag;
        }

*/



/*
        static List access$1202(Value value, List list)
        {
            value.templateToken_ = list;
            return list;
        }

*/



/*
        static List access$1302(Value value, List list)
        {
            value.escaping_ = list;
            return list;
        }

*/


/*
        static boolean access$1402(Value value, boolean flag)
        {
            value.containsReferences_ = flag;
            return flag;
        }

*/


/*
        static int access$1502(Value value, int i)
        {
            value.bitField0_ = i;
            return i;
        }

*/



/*
        static Type access$302(Value value, Type type)
        {
            value.type_ = type;
            return type;
        }

*/



/*
        static Object access$402(Value value, Object obj)
        {
            value.string_ = obj;
            return obj;
        }

*/



/*
        static List access$502(Value value, List list)
        {
            value.listItem_ = list;
            return list;
        }

*/



/*
        static List access$602(Value value, List list)
        {
            value.mapKey_ = list;
            return list;
        }

*/



/*
        static List access$702(Value value, List list)
        {
            value.mapValue_ = list;
            return list;
        }

*/



/*
        static Object access$802(Value value, Object obj)
        {
            value.macroReference_ = obj;
            return obj;
        }

*/



/*
        static Object access$902(Value value, Object obj)
        {
            value.functionId_ = obj;
            return obj;
        }

*/

        private Value(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            int i;
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            i = 0;
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L32:
            if(flag) goto _L2; else goto _L1
_L1:
            int j = codedinputstream.readTag();
            j;
            JVM INSTR lookupswitch 14: default 172
        //                       0: 1083
        //                       8: 191
        //                       18: 398
        //                       26: 423
        //                       34: 466
        //                       42: 512
        //                       50: 558
        //                       58: 583
        //                       64: 609
        //                       72: 631
        //                       80: 653
        //                       82: 731
        //                       90: 835
        //                       96: 884;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, j))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            int j1;
            Type type;
            j1 = codedinputstream.readEnum();
            type = Type.valueOf(j1);
            if(type != null) goto _L19; else goto _L18
_L18:
            codedoutputstream.writeRawVarint32(j);
            codedoutputstream.writeRawVarint32(j1);
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((i & 4) == 4)
                listItem_ = Collections.unmodifiableList(listItem_);
            if((i & 8) == 8)
                mapKey_ = Collections.unmodifiableList(mapKey_);
            if((i & 0x10) == 16)
                mapValue_ = Collections.unmodifiableList(mapValue_);
            if((i & 0x400) == 1024)
                escaping_ = Collections.unmodifiableList(escaping_);
            if((i & 0x200) == 512)
                templateToken_ = Collections.unmodifiableList(templateToken_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L30:
            makeExtensionsImmutable();
            throw exception1;
_L19:
            bitField0_ = 1 | bitField0_;
            type_ = type;
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L6:
            ByteString bytestring2 = codedinputstream.readBytes();
            bitField0_ = 2 | bitField0_;
            string_ = bytestring2;
            continue; /* Loop/switch isn't completed */
_L7:
            if((i & 4) == 4)
                break MISSING_BLOCK_LABEL_445;
            listItem_ = new ArrayList();
            i |= 4;
            listItem_.add(codedinputstream.readMessage(PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L8:
            if((i & 8) == 8)
                break MISSING_BLOCK_LABEL_491;
            mapKey_ = new ArrayList();
            i |= 8;
            mapKey_.add(codedinputstream.readMessage(PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L9:
            if((i & 0x10) == 16)
                break MISSING_BLOCK_LABEL_537;
            mapValue_ = new ArrayList();
            i |= 0x10;
            mapValue_.add(codedinputstream.readMessage(PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L10:
            ByteString bytestring1 = codedinputstream.readBytes();
            bitField0_ = 4 | bitField0_;
            macroReference_ = bytestring1;
            continue; /* Loop/switch isn't completed */
_L11:
            ByteString bytestring = codedinputstream.readBytes();
            bitField0_ = 8 | bitField0_;
            functionId_ = bytestring;
            continue; /* Loop/switch isn't completed */
_L12:
            bitField0_ = 0x10 | bitField0_;
            integer_ = codedinputstream.readInt64();
            continue; /* Loop/switch isn't completed */
_L13:
            bitField0_ = 0x40 | bitField0_;
            containsReferences_ = codedinputstream.readBool();
            continue; /* Loop/switch isn't completed */
_L14:
            int i1;
            Escaping escaping1;
            i1 = codedinputstream.readEnum();
            escaping1 = Escaping.valueOf(i1);
            if(escaping1 != null) goto _L21; else goto _L20
_L20:
            codedoutputstream.writeRawVarint32(j);
            codedoutputstream.writeRawVarint32(i1);
            continue; /* Loop/switch isn't completed */
_L21:
            if((i & 0x400) == 1024)
                break MISSING_BLOCK_LABEL_716;
            escaping_ = new ArrayList();
            i |= 0x400;
            escaping_.add(escaping1);
            continue; /* Loop/switch isn't completed */
_L15:
            int k = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
_L26:
            if(codedinputstream.getBytesUntilLimit() <= 0) goto _L23; else goto _L22
_L22:
            int l;
            Escaping escaping;
            l = codedinputstream.readEnum();
            escaping = Escaping.valueOf(l);
            if(escaping != null) goto _L25; else goto _L24
_L24:
            codedoutputstream.writeRawVarint32(j);
            codedoutputstream.writeRawVarint32(l);
              goto _L26
_L25:
            if((i & 0x400) == 1024)
                break MISSING_BLOCK_LABEL_811;
            escaping_ = new ArrayList();
            i |= 0x400;
            escaping_.add(escaping);
              goto _L26
_L23:
            codedinputstream.popLimit(k);
            continue; /* Loop/switch isn't completed */
_L16:
            if((i & 0x200) == 512)
                break MISSING_BLOCK_LABEL_863;
            templateToken_ = new ArrayList();
            i |= 0x200;
            templateToken_.add(codedinputstream.readMessage(PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L17:
            bitField0_ = 0x20 | bitField0_;
            boolean_ = codedinputstream.readBool();
            continue; /* Loop/switch isn't completed */
_L2:
            if((i & 4) == 4)
                listItem_ = Collections.unmodifiableList(listItem_);
            if((i & 8) == 8)
                mapKey_ = Collections.unmodifiableList(mapKey_);
            if((i & 0x10) == 16)
                mapValue_ = Collections.unmodifiableList(mapValue_);
            if((i & 0x400) == 1024)
                escaping_ = Collections.unmodifiableList(escaping_);
            if((i & 0x200) == 512)
                templateToken_ = Collections.unmodifiableList(templateToken_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L28:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L28; else goto _L27
_L27:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L30; else goto _L29
_L29:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L32; else goto _L31
_L31:
        }


        private Value(com.google.tagmanager.protobuf.GeneratedMessageLite.ExtendableBuilder extendablebuilder)
        {
            super(extendablebuilder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = extendablebuilder.getUnknownFields();
        }


        private Value(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class Value.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.ExtendableBuilder
        implements ValueOrBuilder
    {

        private static Value.Builder create()
        {
            return new Value.Builder();
        }

        private void ensureEscapingIsMutable()
        {
            if((0x400 & bitField0_) != 1024)
            {
                escaping_ = new ArrayList(escaping_);
                bitField0_ = 0x400 | bitField0_;
            }
        }

        private void ensureListItemIsMutable()
        {
            if((4 & bitField0_) != 4)
            {
                listItem_ = new ArrayList(listItem_);
                bitField0_ = 4 | bitField0_;
            }
        }

        private void ensureMapKeyIsMutable()
        {
            if((8 & bitField0_) != 8)
            {
                mapKey_ = new ArrayList(mapKey_);
                bitField0_ = 8 | bitField0_;
            }
        }

        private void ensureMapValueIsMutable()
        {
            if((0x10 & bitField0_) != 16)
            {
                mapValue_ = new ArrayList(mapValue_);
                bitField0_ = 0x10 | bitField0_;
            }
        }

        private void ensureTemplateTokenIsMutable()
        {
            if((0x200 & bitField0_) != 512)
            {
                templateToken_ = new ArrayList(templateToken_);
                bitField0_ = 0x200 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public Value.Builder addAllEscaping(Iterable iterable)
        {
            ensureEscapingIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, escaping_);
            return this;
        }

        public Value.Builder addAllListItem(Iterable iterable)
        {
            ensureListItemIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, listItem_);
            return this;
        }

        public Value.Builder addAllMapKey(Iterable iterable)
        {
            ensureMapKeyIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, mapKey_);
            return this;
        }

        public Value.Builder addAllMapValue(Iterable iterable)
        {
            ensureMapValueIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, mapValue_);
            return this;
        }

        public Value.Builder addAllTemplateToken(Iterable iterable)
        {
            ensureTemplateTokenIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, templateToken_);
            return this;
        }

        public Value.Builder addEscaping(Value.Escaping escaping)
        {
            if(escaping == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEscapingIsMutable();
                escaping_.add(escaping);
                return this;
            }
        }

        public Value.Builder addListItem(int i, Value.Builder builder)
        {
            ensureListItemIsMutable();
            listItem_.add(i, builder.build());
            return this;
        }

        public Value.Builder addListItem(int i, Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureListItemIsMutable();
                listItem_.add(i, value);
                return this;
            }
        }

        public Value.Builder addListItem(Value.Builder builder)
        {
            ensureListItemIsMutable();
            listItem_.add(builder.build());
            return this;
        }

        public Value.Builder addListItem(Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureListItemIsMutable();
                listItem_.add(value);
                return this;
            }
        }

        public Value.Builder addMapKey(int i, Value.Builder builder)
        {
            ensureMapKeyIsMutable();
            mapKey_.add(i, builder.build());
            return this;
        }

        public Value.Builder addMapKey(int i, Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMapKeyIsMutable();
                mapKey_.add(i, value);
                return this;
            }
        }

        public Value.Builder addMapKey(Value.Builder builder)
        {
            ensureMapKeyIsMutable();
            mapKey_.add(builder.build());
            return this;
        }

        public Value.Builder addMapKey(Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMapKeyIsMutable();
                mapKey_.add(value);
                return this;
            }
        }

        public Value.Builder addMapValue(int i, Value.Builder builder)
        {
            ensureMapValueIsMutable();
            mapValue_.add(i, builder.build());
            return this;
        }

        public Value.Builder addMapValue(int i, Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMapValueIsMutable();
                mapValue_.add(i, value);
                return this;
            }
        }

        public Value.Builder addMapValue(Value.Builder builder)
        {
            ensureMapValueIsMutable();
            mapValue_.add(builder.build());
            return this;
        }

        public Value.Builder addMapValue(Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMapValueIsMutable();
                mapValue_.add(value);
                return this;
            }
        }

        public Value.Builder addTemplateToken(int i, Value.Builder builder)
        {
            ensureTemplateTokenIsMutable();
            templateToken_.add(i, builder.build());
            return this;
        }

        public Value.Builder addTemplateToken(int i, Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureTemplateTokenIsMutable();
                templateToken_.add(i, value);
                return this;
            }
        }

        public Value.Builder addTemplateToken(Value.Builder builder)
        {
            ensureTemplateTokenIsMutable();
            templateToken_.add(builder.build());
            return this;
        }

        public Value.Builder addTemplateToken(Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureTemplateTokenIsMutable();
                templateToken_.add(value);
                return this;
            }
        }

        public Value build()
        {
            Value value = buildPartial();
            if(!value.isInitialized())
                throw newUninitializedMessageException(value);
            else
                return value;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public Value buildPartial()
        {
            Value value = new Value(this);
            int i = bitField0_;
            int j = i & 1;
            int k = 0;
            if(j == 1)
                k = false | true;
            value.type_ = type_;
            if((i & 2) == 2)
                k |= 2;
            value.string_ = string_;
            if((4 & bitField0_) == 4)
            {
                listItem_ = Collections.unmodifiableList(listItem_);
                bitField0_ = -5 & bitField0_;
            }
            value.listItem_ = listItem_;
            if((8 & bitField0_) == 8)
            {
                mapKey_ = Collections.unmodifiableList(mapKey_);
                bitField0_ = -9 & bitField0_;
            }
            value.mapKey_ = mapKey_;
            if((0x10 & bitField0_) == 16)
            {
                mapValue_ = Collections.unmodifiableList(mapValue_);
                bitField0_ = 0xffffffef & bitField0_;
            }
            value.mapValue_ = mapValue_;
            if((i & 0x20) == 32)
                k |= 4;
            value.macroReference_ = macroReference_;
            if((i & 0x40) == 64)
                k |= 8;
            value.functionId_ = functionId_;
            if((i & 0x80) == 128)
                k |= 0x10;
            value.integer_ = integer_;
            if((i & 0x100) == 256)
                k |= 0x20;
            value.boolean_ = boolean_;
            if((0x200 & bitField0_) == 512)
            {
                templateToken_ = Collections.unmodifiableList(templateToken_);
                bitField0_ = 0xfffffdff & bitField0_;
            }
            value.templateToken_ = templateToken_;
            if((0x400 & bitField0_) == 1024)
            {
                escaping_ = Collections.unmodifiableList(escaping_);
                bitField0_ = 0xfffffbff & bitField0_;
            }
            value.escaping_ = escaping_;
            if((i & 0x800) == 2048)
                k |= 0x40;
            value.containsReferences_ = containsReferences_;
            value.bitField0_ = k;
            return value;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public Value.Builder clear()
        {
            super.clear();
            type_ = Value.Type.STRING;
            bitField0_ = -2 & bitField0_;
            string_ = "";
            bitField0_ = -3 & bitField0_;
            listItem_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            mapKey_ = Collections.emptyList();
            bitField0_ = -9 & bitField0_;
            mapValue_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            macroReference_ = "";
            bitField0_ = 0xffffffdf & bitField0_;
            functionId_ = "";
            bitField0_ = 0xffffffbf & bitField0_;
            integer_ = 0L;
            bitField0_ = 0xffffff7f & bitField0_;
            boolean_ = false;
            bitField0_ = 0xfffffeff & bitField0_;
            templateToken_ = Collections.emptyList();
            bitField0_ = 0xfffffdff & bitField0_;
            escaping_ = Collections.emptyList();
            bitField0_ = 0xfffffbff & bitField0_;
            containsReferences_ = false;
            bitField0_ = 0xfffff7ff & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.ExtendableBuilder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public Value.Builder clearBoolean()
        {
            bitField0_ = 0xfffffeff & bitField0_;
            boolean_ = false;
            return this;
        }

        public Value.Builder clearContainsReferences()
        {
            bitField0_ = 0xfffff7ff & bitField0_;
            containsReferences_ = false;
            return this;
        }

        public Value.Builder clearEscaping()
        {
            escaping_ = Collections.emptyList();
            bitField0_ = 0xfffffbff & bitField0_;
            return this;
        }

        public Value.Builder clearFunctionId()
        {
            bitField0_ = 0xffffffbf & bitField0_;
            functionId_ = Value.getDefaultInstance().getFunctionId();
            return this;
        }

        public Value.Builder clearInteger()
        {
            bitField0_ = 0xffffff7f & bitField0_;
            integer_ = 0L;
            return this;
        }

        public Value.Builder clearListItem()
        {
            listItem_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            return this;
        }

        public Value.Builder clearMacroReference()
        {
            bitField0_ = 0xffffffdf & bitField0_;
            macroReference_ = Value.getDefaultInstance().getMacroReference();
            return this;
        }

        public Value.Builder clearMapKey()
        {
            mapKey_ = Collections.emptyList();
            bitField0_ = -9 & bitField0_;
            return this;
        }

        public Value.Builder clearMapValue()
        {
            mapValue_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            return this;
        }

        public Value.Builder clearString()
        {
            bitField0_ = -3 & bitField0_;
            string_ = Value.getDefaultInstance().getString();
            return this;
        }

        public Value.Builder clearTemplateToken()
        {
            templateToken_ = Collections.emptyList();
            bitField0_ = 0xfffffdff & bitField0_;
            return this;
        }

        public Value.Builder clearType()
        {
            bitField0_ = -2 & bitField0_;
            type_ = Value.Type.STRING;
            return this;
        }

        public Value.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.ExtendableBuilder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public boolean getBoolean()
        {
            return boolean_;
        }

        public boolean getContainsReferences()
        {
            return containsReferences_;
        }

        public Value getDefaultInstanceForType()
        {
            return Value.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Value.Escaping getEscaping(int i)
        {
            return (Value.Escaping)escaping_.get(i);
        }

        public int getEscapingCount()
        {
            return escaping_.size();
        }

        public List getEscapingList()
        {
            return Collections.unmodifiableList(escaping_);
        }

        public String getFunctionId()
        {
            Object obj = functionId_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    functionId_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getFunctionIdBytes()
        {
            Object obj = functionId_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                functionId_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public long getInteger()
        {
            return integer_;
        }

        public Value getListItem(int i)
        {
            return (Value)listItem_.get(i);
        }

        public int getListItemCount()
        {
            return listItem_.size();
        }

        public List getListItemList()
        {
            return Collections.unmodifiableList(listItem_);
        }

        public String getMacroReference()
        {
            Object obj = macroReference_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    macroReference_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getMacroReferenceBytes()
        {
            Object obj = macroReference_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                macroReference_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Value getMapKey(int i)
        {
            return (Value)mapKey_.get(i);
        }

        public int getMapKeyCount()
        {
            return mapKey_.size();
        }

        public List getMapKeyList()
        {
            return Collections.unmodifiableList(mapKey_);
        }

        public Value getMapValue(int i)
        {
            return (Value)mapValue_.get(i);
        }

        public int getMapValueCount()
        {
            return mapValue_.size();
        }

        public List getMapValueList()
        {
            return Collections.unmodifiableList(mapValue_);
        }

        public String getString()
        {
            Object obj = string_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    string_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getStringBytes()
        {
            Object obj = string_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                string_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Value getTemplateToken(int i)
        {
            return (Value)templateToken_.get(i);
        }

        public int getTemplateTokenCount()
        {
            return templateToken_.size();
        }

        public List getTemplateTokenList()
        {
            return Collections.unmodifiableList(templateToken_);
        }

        public Value.Type getType()
        {
            return type_;
        }

        public boolean hasBoolean()
        {
            return (0x100 & bitField0_) == 256;
        }

        public boolean hasContainsReferences()
        {
            return (0x800 & bitField0_) == 2048;
        }

        public boolean hasFunctionId()
        {
            return (0x40 & bitField0_) == 64;
        }

        public boolean hasInteger()
        {
            return (0x80 & bitField0_) == 128;
        }

        public boolean hasMacroReference()
        {
            return (0x20 & bitField0_) == 32;
        }

        public boolean hasString()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasType()
        {
            return (1 & bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
            if(hasType()) goto _L2; else goto _L1
_L1:
            return false;
_L2:
            int i = 0;
_L4:
            if(i >= getListItemCount())
                break MISSING_BLOCK_LABEL_36;
            if(!getListItem(i).isInitialized()) goto _L1; else goto _L3
_L3:
            i++;
              goto _L4
            int j = 0;
_L6:
            if(j >= getMapKeyCount())
                break MISSING_BLOCK_LABEL_63;
            if(!getMapKey(j).isInitialized()) goto _L1; else goto _L5
_L5:
            j++;
              goto _L6
            int k = 0;
_L8:
            if(k >= getMapValueCount())
                break MISSING_BLOCK_LABEL_90;
            if(!getMapValue(k).isInitialized()) goto _L1; else goto _L7
_L7:
            k++;
              goto _L8
            int l = 0;
_L10:
            if(l >= getTemplateTokenCount())
                continue; /* Loop/switch isn't completed */
            if(!getTemplateToken(l).isInitialized()) goto _L1; else goto _L9
_L9:
            l++;
              goto _L10
            if(!extensionsAreInitialized()) goto _L1; else goto _L11
_L11:
            return true;
        }

        public Value.Builder mergeFrom(Value value)
        {
            if(value == Value.getDefaultInstance())
                return this;
            if(value.hasType())
                setType(value.getType());
            if(value.hasString())
            {
                bitField0_ = 2 | bitField0_;
                string_ = value.string_;
            }
            if(!value.listItem_.isEmpty())
                if(listItem_.isEmpty())
                {
                    listItem_ = value.listItem_;
                    bitField0_ = -5 & bitField0_;
                } else
                {
                    ensureListItemIsMutable();
                    listItem_.addAll(value.listItem_);
                }
            if(!value.mapKey_.isEmpty())
                if(mapKey_.isEmpty())
                {
                    mapKey_ = value.mapKey_;
                    bitField0_ = -9 & bitField0_;
                } else
                {
                    ensureMapKeyIsMutable();
                    mapKey_.addAll(value.mapKey_);
                }
            if(!value.mapValue_.isEmpty())
                if(mapValue_.isEmpty())
                {
                    mapValue_ = value.mapValue_;
                    bitField0_ = 0xffffffef & bitField0_;
                } else
                {
                    ensureMapValueIsMutable();
                    mapValue_.addAll(value.mapValue_);
                }
            if(value.hasMacroReference())
            {
                bitField0_ = 0x20 | bitField0_;
                macroReference_ = value.macroReference_;
            }
            if(value.hasFunctionId())
            {
                bitField0_ = 0x40 | bitField0_;
                functionId_ = value.functionId_;
            }
            if(value.hasInteger())
                setInteger(value.getInteger());
            if(value.hasBoolean())
                setBoolean(value.getBoolean());
            if(!value.templateToken_.isEmpty())
                if(templateToken_.isEmpty())
                {
                    templateToken_ = value.templateToken_;
                    bitField0_ = 0xfffffdff & bitField0_;
                } else
                {
                    ensureTemplateTokenIsMutable();
                    templateToken_.addAll(value.templateToken_);
                }
            if(!value.escaping_.isEmpty())
                if(escaping_.isEmpty())
                {
                    escaping_ = value.escaping_;
                    bitField0_ = 0xfffffbff & bitField0_;
                } else
                {
                    ensureEscapingIsMutable();
                    escaping_.addAll(value.escaping_);
                }
            if(value.hasContainsReferences())
                setContainsReferences(value.getContainsReferences());
            mergeExtensionFields(value);
            setUnknownFields(getUnknownFields().concat(value.unknownFields));
            return this;
        }

        public Value.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            Value value = null;
            Value value1 = (Value)Value.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(value1 != null)
                mergeFrom(value1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            value = (Value)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(value != null)
                mergeFrom(value);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((Value)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public Value.Builder removeListItem(int i)
        {
            ensureListItemIsMutable();
            listItem_.remove(i);
            return this;
        }

        public Value.Builder removeMapKey(int i)
        {
            ensureMapKeyIsMutable();
            mapKey_.remove(i);
            return this;
        }

        public Value.Builder removeMapValue(int i)
        {
            ensureMapValueIsMutable();
            mapValue_.remove(i);
            return this;
        }

        public Value.Builder removeTemplateToken(int i)
        {
            ensureTemplateTokenIsMutable();
            templateToken_.remove(i);
            return this;
        }

        public Value.Builder setBoolean(boolean flag)
        {
            bitField0_ = 0x100 | bitField0_;
            boolean_ = flag;
            return this;
        }

        public Value.Builder setContainsReferences(boolean flag)
        {
            bitField0_ = 0x800 | bitField0_;
            containsReferences_ = flag;
            return this;
        }

        public Value.Builder setEscaping(int i, Value.Escaping escaping)
        {
            if(escaping == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEscapingIsMutable();
                escaping_.set(i, escaping);
                return this;
            }
        }

        public Value.Builder setFunctionId(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x40 | bitField0_;
                functionId_ = s;
                return this;
            }
        }

        public Value.Builder setFunctionIdBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x40 | bitField0_;
                functionId_ = bytestring;
                return this;
            }
        }

        public Value.Builder setInteger(long l)
        {
            bitField0_ = 0x80 | bitField0_;
            integer_ = l;
            return this;
        }

        public Value.Builder setListItem(int i, Value.Builder builder)
        {
            ensureListItemIsMutable();
            listItem_.set(i, builder.build());
            return this;
        }

        public Value.Builder setListItem(int i, Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureListItemIsMutable();
                listItem_.set(i, value);
                return this;
            }
        }

        public Value.Builder setMacroReference(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x20 | bitField0_;
                macroReference_ = s;
                return this;
            }
        }

        public Value.Builder setMacroReferenceBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x20 | bitField0_;
                macroReference_ = bytestring;
                return this;
            }
        }

        public Value.Builder setMapKey(int i, Value.Builder builder)
        {
            ensureMapKeyIsMutable();
            mapKey_.set(i, builder.build());
            return this;
        }

        public Value.Builder setMapKey(int i, Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMapKeyIsMutable();
                mapKey_.set(i, value);
                return this;
            }
        }

        public Value.Builder setMapValue(int i, Value.Builder builder)
        {
            ensureMapValueIsMutable();
            mapValue_.set(i, builder.build());
            return this;
        }

        public Value.Builder setMapValue(int i, Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMapValueIsMutable();
                mapValue_.set(i, value);
                return this;
            }
        }

        public Value.Builder setString(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                string_ = s;
                return this;
            }
        }

        public Value.Builder setStringBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                string_ = bytestring;
                return this;
            }
        }

        public Value.Builder setTemplateToken(int i, Value.Builder builder)
        {
            ensureTemplateTokenIsMutable();
            templateToken_.set(i, builder.build());
            return this;
        }

        public Value.Builder setTemplateToken(int i, Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureTemplateTokenIsMutable();
                templateToken_.set(i, value);
                return this;
            }
        }

        public Value.Builder setType(Value.Type type)
        {
            if(type == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                type_ = type;
                return this;
            }
        }

        private int bitField0_;
        private boolean boolean_;
        private boolean containsReferences_;
        private List escaping_;
        private Object functionId_;
        private long integer_;
        private List listItem_;
        private Object macroReference_;
        private List mapKey_;
        private List mapValue_;
        private Object string_;
        private List templateToken_;
        private Value.Type type_;


        private Value.Builder()
        {
            type_ = Value.Type.STRING;
            string_ = "";
            listItem_ = Collections.emptyList();
            mapKey_ = Collections.emptyList();
            mapValue_ = Collections.emptyList();
            macroReference_ = "";
            functionId_ = "";
            templateToken_ = Collections.emptyList();
            escaping_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }
    }

    public static final class Value.Escaping extends Enum
        implements com.google.tagmanager.protobuf.Internal.EnumLite
    {

        public static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalGetValueMap()
        {
            return internalValueMap;
        }

        public static Value.Escaping valueOf(int i)
        {
            switch(i)
            {
            default:
                return null;

            case 1: // '\001'
                return ESCAPE_HTML;

            case 2: // '\002'
                return ESCAPE_HTML_RCDATA;

            case 3: // '\003'
                return ESCAPE_HTML_ATTRIBUTE;

            case 4: // '\004'
                return ESCAPE_HTML_ATTRIBUTE_NOSPACE;

            case 5: // '\005'
                return FILTER_HTML_ELEMENT_NAME;

            case 6: // '\006'
                return FILTER_HTML_ATTRIBUTES;

            case 7: // '\007'
                return ESCAPE_JS_STRING;

            case 8: // '\b'
                return ESCAPE_JS_VALUE;

            case 9: // '\t'
                return ESCAPE_JS_REGEX;

            case 10: // '\n'
                return ESCAPE_CSS_STRING;

            case 11: // '\013'
                return FILTER_CSS_VALUE;

            case 12: // '\f'
                return ESCAPE_URI;

            case 13: // '\r'
                return NORMALIZE_URI;

            case 14: // '\016'
                return FILTER_NORMALIZE_URI;

            case 15: // '\017'
                return NO_AUTOESCAPE;

            case 17: // '\021'
                return TEXT;

            case 16: // '\020'
                return CONVERT_JS_VALUE_TO_EXPRESSION;
            }
        }

        public static Value.Escaping valueOf(String s)
        {
            return (Value.Escaping)Enum.valueOf(com/google/analytics/midtier/proto/containertag/TypeSystem$Value$Escaping, s);
        }

        public static Value.Escaping[] values()
        {
            return (Value.Escaping[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        private static final Value.Escaping $VALUES[];
        public static final Value.Escaping CONVERT_JS_VALUE_TO_EXPRESSION;
        public static final int CONVERT_JS_VALUE_TO_EXPRESSION_VALUE = 16;
        public static final Value.Escaping ESCAPE_CSS_STRING;
        public static final int ESCAPE_CSS_STRING_VALUE = 10;
        public static final Value.Escaping ESCAPE_HTML;
        public static final Value.Escaping ESCAPE_HTML_ATTRIBUTE;
        public static final Value.Escaping ESCAPE_HTML_ATTRIBUTE_NOSPACE;
        public static final int ESCAPE_HTML_ATTRIBUTE_NOSPACE_VALUE = 4;
        public static final int ESCAPE_HTML_ATTRIBUTE_VALUE = 3;
        public static final Value.Escaping ESCAPE_HTML_RCDATA;
        public static final int ESCAPE_HTML_RCDATA_VALUE = 2;
        public static final int ESCAPE_HTML_VALUE = 1;
        public static final Value.Escaping ESCAPE_JS_REGEX;
        public static final int ESCAPE_JS_REGEX_VALUE = 9;
        public static final Value.Escaping ESCAPE_JS_STRING;
        public static final int ESCAPE_JS_STRING_VALUE = 7;
        public static final Value.Escaping ESCAPE_JS_VALUE;
        public static final int ESCAPE_JS_VALUE_VALUE = 8;
        public static final Value.Escaping ESCAPE_URI;
        public static final int ESCAPE_URI_VALUE = 12;
        public static final Value.Escaping FILTER_CSS_VALUE;
        public static final int FILTER_CSS_VALUE_VALUE = 11;
        public static final Value.Escaping FILTER_HTML_ATTRIBUTES;
        public static final int FILTER_HTML_ATTRIBUTES_VALUE = 6;
        public static final Value.Escaping FILTER_HTML_ELEMENT_NAME;
        public static final int FILTER_HTML_ELEMENT_NAME_VALUE = 5;
        public static final Value.Escaping FILTER_NORMALIZE_URI;
        public static final int FILTER_NORMALIZE_URI_VALUE = 14;
        public static final Value.Escaping NORMALIZE_URI;
        public static final int NORMALIZE_URI_VALUE = 13;
        public static final Value.Escaping NO_AUTOESCAPE;
        public static final int NO_AUTOESCAPE_VALUE = 15;
        public static final Value.Escaping TEXT;
        public static final int TEXT_VALUE = 17;
        private static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalValueMap = new com.google.tagmanager.protobuf.Internal.EnumLiteMap() {

            public Value.Escaping findValueByNumber(int i)
            {
                return Value.Escaping.valueOf(i);
            }

            public volatile com.google.tagmanager.protobuf.Internal.EnumLite findValueByNumber(int i)
            {
                return findValueByNumber(i);
            }

        }
;
        private final int value;

        static 
        {
            ESCAPE_HTML = new Value.Escaping("ESCAPE_HTML", 0, 0, 1);
            ESCAPE_HTML_RCDATA = new Value.Escaping("ESCAPE_HTML_RCDATA", 1, 1, 2);
            ESCAPE_HTML_ATTRIBUTE = new Value.Escaping("ESCAPE_HTML_ATTRIBUTE", 2, 2, 3);
            ESCAPE_HTML_ATTRIBUTE_NOSPACE = new Value.Escaping("ESCAPE_HTML_ATTRIBUTE_NOSPACE", 3, 3, 4);
            FILTER_HTML_ELEMENT_NAME = new Value.Escaping("FILTER_HTML_ELEMENT_NAME", 4, 4, 5);
            FILTER_HTML_ATTRIBUTES = new Value.Escaping("FILTER_HTML_ATTRIBUTES", 5, 5, 6);
            ESCAPE_JS_STRING = new Value.Escaping("ESCAPE_JS_STRING", 6, 6, 7);
            ESCAPE_JS_VALUE = new Value.Escaping("ESCAPE_JS_VALUE", 7, 7, 8);
            ESCAPE_JS_REGEX = new Value.Escaping("ESCAPE_JS_REGEX", 8, 8, 9);
            ESCAPE_CSS_STRING = new Value.Escaping("ESCAPE_CSS_STRING", 9, 9, 10);
            FILTER_CSS_VALUE = new Value.Escaping("FILTER_CSS_VALUE", 10, 10, 11);
            ESCAPE_URI = new Value.Escaping("ESCAPE_URI", 11, 11, 12);
            NORMALIZE_URI = new Value.Escaping("NORMALIZE_URI", 12, 12, 13);
            FILTER_NORMALIZE_URI = new Value.Escaping("FILTER_NORMALIZE_URI", 13, 13, 14);
            NO_AUTOESCAPE = new Value.Escaping("NO_AUTOESCAPE", 14, 14, 15);
            TEXT = new Value.Escaping("TEXT", 15, 15, 17);
            CONVERT_JS_VALUE_TO_EXPRESSION = new Value.Escaping("CONVERT_JS_VALUE_TO_EXPRESSION", 16, 16, 16);
            Value.Escaping aescaping[] = new Value.Escaping[17];
            aescaping[0] = ESCAPE_HTML;
            aescaping[1] = ESCAPE_HTML_RCDATA;
            aescaping[2] = ESCAPE_HTML_ATTRIBUTE;
            aescaping[3] = ESCAPE_HTML_ATTRIBUTE_NOSPACE;
            aescaping[4] = FILTER_HTML_ELEMENT_NAME;
            aescaping[5] = FILTER_HTML_ATTRIBUTES;
            aescaping[6] = ESCAPE_JS_STRING;
            aescaping[7] = ESCAPE_JS_VALUE;
            aescaping[8] = ESCAPE_JS_REGEX;
            aescaping[9] = ESCAPE_CSS_STRING;
            aescaping[10] = FILTER_CSS_VALUE;
            aescaping[11] = ESCAPE_URI;
            aescaping[12] = NORMALIZE_URI;
            aescaping[13] = FILTER_NORMALIZE_URI;
            aescaping[14] = NO_AUTOESCAPE;
            aescaping[15] = TEXT;
            aescaping[16] = CONVERT_JS_VALUE_TO_EXPRESSION;
            $VALUES = aescaping;
        }

        private Value.Escaping(String s, int i, int j, int k)
        {
            super(s, i);
            value = k;
        }
    }

    public static final class Value.Type extends Enum
        implements com.google.tagmanager.protobuf.Internal.EnumLite
    {

        public static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalGetValueMap()
        {
            return internalValueMap;
        }

        public static Value.Type valueOf(int i)
        {
            switch(i)
            {
            default:
                return null;

            case 1: // '\001'
                return STRING;

            case 2: // '\002'
                return LIST;

            case 3: // '\003'
                return MAP;

            case 4: // '\004'
                return MACRO_REFERENCE;

            case 5: // '\005'
                return FUNCTION_ID;

            case 6: // '\006'
                return INTEGER;

            case 7: // '\007'
                return TEMPLATE;

            case 8: // '\b'
                return BOOLEAN;
            }
        }

        public static Value.Type valueOf(String s)
        {
            return (Value.Type)Enum.valueOf(com/google/analytics/midtier/proto/containertag/TypeSystem$Value$Type, s);
        }

        public static Value.Type[] values()
        {
            return (Value.Type[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        private static final Value.Type $VALUES[];
        public static final Value.Type BOOLEAN;
        public static final int BOOLEAN_VALUE = 8;
        public static final Value.Type FUNCTION_ID;
        public static final int FUNCTION_ID_VALUE = 5;
        public static final Value.Type INTEGER;
        public static final int INTEGER_VALUE = 6;
        public static final Value.Type LIST;
        public static final int LIST_VALUE = 2;
        public static final Value.Type MACRO_REFERENCE;
        public static final int MACRO_REFERENCE_VALUE = 4;
        public static final Value.Type MAP;
        public static final int MAP_VALUE = 3;
        public static final Value.Type STRING;
        public static final int STRING_VALUE = 1;
        public static final Value.Type TEMPLATE;
        public static final int TEMPLATE_VALUE = 7;
        private static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalValueMap = new com.google.tagmanager.protobuf.Internal.EnumLiteMap() {

            public Value.Type findValueByNumber(int i)
            {
                return Value.Type.valueOf(i);
            }

            public volatile com.google.tagmanager.protobuf.Internal.EnumLite findValueByNumber(int i)
            {
                return findValueByNumber(i);
            }

        }
;
        private final int value;

        static 
        {
            STRING = new Value.Type("STRING", 0, 0, 1);
            LIST = new Value.Type("LIST", 1, 1, 2);
            MAP = new Value.Type("MAP", 2, 2, 3);
            MACRO_REFERENCE = new Value.Type("MACRO_REFERENCE", 3, 3, 4);
            FUNCTION_ID = new Value.Type("FUNCTION_ID", 4, 4, 5);
            INTEGER = new Value.Type("INTEGER", 5, 5, 6);
            TEMPLATE = new Value.Type("TEMPLATE", 6, 6, 7);
            BOOLEAN = new Value.Type("BOOLEAN", 7, 7, 8);
            Value.Type atype[] = new Value.Type[8];
            atype[0] = STRING;
            atype[1] = LIST;
            atype[2] = MAP;
            atype[3] = MACRO_REFERENCE;
            atype[4] = FUNCTION_ID;
            atype[5] = INTEGER;
            atype[6] = TEMPLATE;
            atype[7] = BOOLEAN;
            $VALUES = atype;
        }

        private Value.Type(String s, int i, int j, int k)
        {
            super(s, i);
            value = k;
        }
    }

    public static interface ValueOrBuilder
        extends com.google.tagmanager.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
    {

        public abstract boolean getBoolean();

        public abstract boolean getContainsReferences();

        public abstract Value.Escaping getEscaping(int i);

        public abstract int getEscapingCount();

        public abstract List getEscapingList();

        public abstract String getFunctionId();

        public abstract ByteString getFunctionIdBytes();

        public abstract long getInteger();

        public abstract Value getListItem(int i);

        public abstract int getListItemCount();

        public abstract List getListItemList();

        public abstract String getMacroReference();

        public abstract ByteString getMacroReferenceBytes();

        public abstract Value getMapKey(int i);

        public abstract int getMapKeyCount();

        public abstract List getMapKeyList();

        public abstract Value getMapValue(int i);

        public abstract int getMapValueCount();

        public abstract List getMapValueList();

        public abstract String getString();

        public abstract ByteString getStringBytes();

        public abstract Value getTemplateToken(int i);

        public abstract int getTemplateTokenCount();

        public abstract List getTemplateTokenList();

        public abstract Value.Type getType();

        public abstract boolean hasBoolean();

        public abstract boolean hasContainsReferences();

        public abstract boolean hasFunctionId();

        public abstract boolean hasInteger();

        public abstract boolean hasMacroReference();

        public abstract boolean hasString();

        public abstract boolean hasType();
    }


    private TypeSystem()
    {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionregistrylite)
    {
    }

}
