// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.containertag.proto;

import com.google.tagmanager.protobuf.*;
import java.io.*;
import java.util.*;

public final class Serving
{
    public static final class CacheOption extends GeneratedMessageLite
        implements CacheOptionOrBuilder
    {

        public static CacheOption getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            level_ = CacheLevel.NO_CACHE;
            expirationSeconds_ = 0;
            gcacheExpirationSeconds_ = 0;
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(CacheOption cacheoption)
        {
            return newBuilder().mergeFrom(cacheoption);
        }

        public static CacheOption parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (CacheOption)PARSER.parseDelimitedFrom(inputstream);
        }

        public static CacheOption parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (CacheOption)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static CacheOption parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (CacheOption)PARSER.parseFrom(bytestring);
        }

        public static CacheOption parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (CacheOption)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static CacheOption parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (CacheOption)PARSER.parseFrom(codedinputstream);
        }

        public static CacheOption parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (CacheOption)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static CacheOption parseFrom(InputStream inputstream)
            throws IOException
        {
            return (CacheOption)PARSER.parseFrom(inputstream);
        }

        public static CacheOption parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (CacheOption)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static CacheOption parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (CacheOption)PARSER.parseFrom(abyte0);
        }

        public static CacheOption parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (CacheOption)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof CacheOption))
                return super.equals(obj);
            CacheOption cacheoption = (CacheOption)obj;
            boolean flag;
            boolean flag1;
            boolean flag2;
            if(true && hasLevel() == cacheoption.hasLevel())
                flag = true;
            else
                flag = false;
            if(hasLevel())
                if(flag && getLevel() == cacheoption.getLevel())
                    flag = true;
                else
                    flag = false;
            if(flag && hasExpirationSeconds() == cacheoption.hasExpirationSeconds())
                flag1 = true;
            else
                flag1 = false;
            if(hasExpirationSeconds())
                if(flag1 && getExpirationSeconds() == cacheoption.getExpirationSeconds())
                    flag1 = true;
                else
                    flag1 = false;
            if(flag1 && hasGcacheExpirationSeconds() == cacheoption.hasGcacheExpirationSeconds())
                flag2 = true;
            else
                flag2 = false;
            if(hasGcacheExpirationSeconds())
                if(flag2 && getGcacheExpirationSeconds() == cacheoption.getGcacheExpirationSeconds())
                    flag2 = true;
                else
                    flag2 = false;
            return flag2;
        }

        public CacheOption getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getExpirationSeconds()
        {
            return expirationSeconds_;
        }

        public int getGcacheExpirationSeconds()
        {
            return gcacheExpirationSeconds_;
        }

        public CacheLevel getLevel()
        {
            return level_;
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
                k = 0 + CodedOutputStream.computeEnumSize(1, level_.getNumber());
            if((2 & bitField0_) == 2)
                k += CodedOutputStream.computeInt32Size(2, expirationSeconds_);
            if((4 & bitField0_) == 4)
                k += CodedOutputStream.computeInt32Size(3, gcacheExpirationSeconds_);
            int l = k + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public boolean hasExpirationSeconds()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasGcacheExpirationSeconds()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasLevel()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Serving$CacheOption.hashCode();
            if(hasLevel())
                i = 53 * (1 + i * 37) + Internal.hashEnum(getLevel());
            if(hasExpirationSeconds())
                i = 53 * (2 + i * 37) + getExpirationSeconds();
            if(hasGcacheExpirationSeconds())
                i = 53 * (3 + i * 37) + getGcacheExpirationSeconds();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableServing$CacheOption");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
            {
                return byte0 == 1;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
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
            if((1 & bitField0_) == 1)
                codedoutputstream.writeEnum(1, level_.getNumber());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeInt32(2, expirationSeconds_);
            if((4 & bitField0_) == 4)
                codedoutputstream.writeInt32(3, gcacheExpirationSeconds_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int EXPIRATION_SECONDS_FIELD_NUMBER = 2;
        public static final int GCACHE_EXPIRATION_SECONDS_FIELD_NUMBER = 3;
        public static final int LEVEL_FIELD_NUMBER = 1;
        public static Parser PARSER = new AbstractParser() {

            public CacheOption parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new CacheOption(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        private static final CacheOption defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private int expirationSeconds_;
        private int gcacheExpirationSeconds_;
        private CacheLevel level_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new CacheOption(true);
            defaultInstance.initFields();
        }


/*
        static CacheLevel access$5402(CacheOption cacheoption, CacheLevel cachelevel)
        {
            cacheoption.level_ = cachelevel;
            return cachelevel;
        }

*/


/*
        static int access$5502(CacheOption cacheoption, int i)
        {
            cacheoption.expirationSeconds_ = i;
            return i;
        }

*/


/*
        static int access$5602(CacheOption cacheoption, int i)
        {
            cacheoption.gcacheExpirationSeconds_ = i;
            return i;
        }

*/


/*
        static int access$5702(CacheOption cacheoption, int i)
        {
            cacheoption.bitField0_ = i;
            return i;
        }

*/


        private CacheOption(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L15:
            if(flag) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 4: default 88
        //                       0: 323
        //                       8: 107
        //                       16: 211
        //                       24: 232;
               goto _L3 _L4 _L5 _L6 _L7
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            int j;
            CacheLevel cachelevel;
            j = codedinputstream.readEnum();
            cachelevel = CacheLevel.valueOf(j);
            if(cachelevel != null) goto _L9; else goto _L8
_L8:
            codedoutputstream.writeRawVarint32(i);
            codedoutputstream.writeRawVarint32(j);
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L13:
            makeExtensionsImmutable();
            throw exception1;
_L9:
            bitField0_ = 1 | bitField0_;
            level_ = cachelevel;
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L6:
            bitField0_ = 2 | bitField0_;
            expirationSeconds_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L7:
            bitField0_ = 4 | bitField0_;
            gcacheExpirationSeconds_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L11:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L11; else goto _L10
_L10:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L13; else goto _L12
_L12:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L15; else goto _L14
_L14:
        }


        private CacheOption(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private CacheOption(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class CacheOption.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements CacheOptionOrBuilder
    {

        private static CacheOption.Builder create()
        {
            return new CacheOption.Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public CacheOption build()
        {
            CacheOption cacheoption = buildPartial();
            if(!cacheoption.isInitialized())
                throw newUninitializedMessageException(cacheoption);
            else
                return cacheoption;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public CacheOption buildPartial()
        {
            CacheOption cacheoption = new CacheOption(this);
            int i = bitField0_;
            int j = i & 1;
            int k = 0;
            if(j == 1)
                k = false | true;
            cacheoption.level_ = level_;
            if((i & 2) == 2)
                k |= 2;
            cacheoption.expirationSeconds_ = expirationSeconds_;
            if((i & 4) == 4)
                k |= 4;
            cacheoption.gcacheExpirationSeconds_ = gcacheExpirationSeconds_;
            cacheoption.bitField0_ = k;
            return cacheoption;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public CacheOption.Builder clear()
        {
            super.clear();
            level_ = CacheOption.CacheLevel.NO_CACHE;
            bitField0_ = -2 & bitField0_;
            expirationSeconds_ = 0;
            bitField0_ = -3 & bitField0_;
            gcacheExpirationSeconds_ = 0;
            bitField0_ = -5 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public CacheOption.Builder clearExpirationSeconds()
        {
            bitField0_ = -3 & bitField0_;
            expirationSeconds_ = 0;
            return this;
        }

        public CacheOption.Builder clearGcacheExpirationSeconds()
        {
            bitField0_ = -5 & bitField0_;
            gcacheExpirationSeconds_ = 0;
            return this;
        }

        public CacheOption.Builder clearLevel()
        {
            bitField0_ = -2 & bitField0_;
            level_ = CacheOption.CacheLevel.NO_CACHE;
            return this;
        }

        public CacheOption.Builder clone()
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

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public CacheOption getDefaultInstanceForType()
        {
            return CacheOption.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getExpirationSeconds()
        {
            return expirationSeconds_;
        }

        public int getGcacheExpirationSeconds()
        {
            return gcacheExpirationSeconds_;
        }

        public CacheOption.CacheLevel getLevel()
        {
            return level_;
        }

        public boolean hasExpirationSeconds()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasGcacheExpirationSeconds()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasLevel()
        {
            return (1 & bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
            return true;
        }

        public CacheOption.Builder mergeFrom(CacheOption cacheoption)
        {
            if(cacheoption == CacheOption.getDefaultInstance())
                return this;
            if(cacheoption.hasLevel())
                setLevel(cacheoption.getLevel());
            if(cacheoption.hasExpirationSeconds())
                setExpirationSeconds(cacheoption.getExpirationSeconds());
            if(cacheoption.hasGcacheExpirationSeconds())
                setGcacheExpirationSeconds(cacheoption.getGcacheExpirationSeconds());
            setUnknownFields(getUnknownFields().concat(cacheoption.unknownFields));
            return this;
        }

        public CacheOption.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            CacheOption cacheoption = null;
            CacheOption cacheoption1 = (CacheOption)CacheOption.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(cacheoption1 != null)
                mergeFrom(cacheoption1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            cacheoption = (CacheOption)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(cacheoption != null)
                mergeFrom(cacheoption);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((CacheOption)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public CacheOption.Builder setExpirationSeconds(int i)
        {
            bitField0_ = 2 | bitField0_;
            expirationSeconds_ = i;
            return this;
        }

        public CacheOption.Builder setGcacheExpirationSeconds(int i)
        {
            bitField0_ = 4 | bitField0_;
            gcacheExpirationSeconds_ = i;
            return this;
        }

        public CacheOption.Builder setLevel(CacheOption.CacheLevel cachelevel)
        {
            if(cachelevel == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                level_ = cachelevel;
                return this;
            }
        }

        private int bitField0_;
        private int expirationSeconds_;
        private int gcacheExpirationSeconds_;
        private CacheOption.CacheLevel level_;


        private CacheOption.Builder()
        {
            level_ = CacheOption.CacheLevel.NO_CACHE;
            maybeForceBuilderInitialization();
        }
    }

    public static final class CacheOption.CacheLevel extends Enum
        implements com.google.tagmanager.protobuf.Internal.EnumLite
    {

        public static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalGetValueMap()
        {
            return internalValueMap;
        }

        public static CacheOption.CacheLevel valueOf(int i)
        {
            switch(i)
            {
            default:
                return null;

            case 1: // '\001'
                return NO_CACHE;

            case 2: // '\002'
                return PRIVATE;

            case 3: // '\003'
                return PUBLIC;
            }
        }

        public static CacheOption.CacheLevel valueOf(String s)
        {
            return (CacheOption.CacheLevel)Enum.valueOf(com/google/analytics/containertag/proto/Serving$CacheOption$CacheLevel, s);
        }

        public static CacheOption.CacheLevel[] values()
        {
            return (CacheOption.CacheLevel[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        private static final CacheOption.CacheLevel $VALUES[];
        public static final CacheOption.CacheLevel NO_CACHE;
        public static final int NO_CACHE_VALUE = 1;
        public static final CacheOption.CacheLevel PRIVATE;
        public static final int PRIVATE_VALUE = 2;
        public static final CacheOption.CacheLevel PUBLIC;
        public static final int PUBLIC_VALUE = 3;
        private static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalValueMap = new com.google.tagmanager.protobuf.Internal.EnumLiteMap() {

            public CacheOption.CacheLevel findValueByNumber(int i)
            {
                return CacheOption.CacheLevel.valueOf(i);
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
            NO_CACHE = new CacheOption.CacheLevel("NO_CACHE", 0, 0, 1);
            PRIVATE = new CacheOption.CacheLevel("PRIVATE", 1, 1, 2);
            PUBLIC = new CacheOption.CacheLevel("PUBLIC", 2, 2, 3);
            CacheOption.CacheLevel acachelevel[] = new CacheOption.CacheLevel[3];
            acachelevel[0] = NO_CACHE;
            acachelevel[1] = PRIVATE;
            acachelevel[2] = PUBLIC;
            $VALUES = acachelevel;
        }

        private CacheOption.CacheLevel(String s, int i, int j, int k)
        {
            super(s, i);
            value = k;
        }
    }

    public static interface CacheOptionOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract int getExpirationSeconds();

        public abstract int getGcacheExpirationSeconds();

        public abstract CacheOption.CacheLevel getLevel();

        public abstract boolean hasExpirationSeconds();

        public abstract boolean hasGcacheExpirationSeconds();

        public abstract boolean hasLevel();
    }

    public static final class Container extends GeneratedMessageLite
        implements ContainerOrBuilder
    {

        public static Container getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            jsResource_ = Resource.getDefaultInstance();
            containerId_ = "";
            state_ = ResourceState.PREVIEW;
            version_ = "";
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(Container container)
        {
            return newBuilder().mergeFrom(container);
        }

        public static Container parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (Container)PARSER.parseDelimitedFrom(inputstream);
        }

        public static Container parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Container)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static Container parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (Container)PARSER.parseFrom(bytestring);
        }

        public static Container parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Container)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static Container parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (Container)PARSER.parseFrom(codedinputstream);
        }

        public static Container parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Container)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static Container parseFrom(InputStream inputstream)
            throws IOException
        {
            return (Container)PARSER.parseFrom(inputstream);
        }

        public static Container parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Container)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static Container parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (Container)PARSER.parseFrom(abyte0);
        }

        public static Container parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Container)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof Container))
                return super.equals(obj);
            Container container = (Container)obj;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            if(true && hasJsResource() == container.hasJsResource())
                flag = true;
            else
                flag = false;
            if(hasJsResource())
                if(flag && getJsResource().equals(container.getJsResource()))
                    flag = true;
                else
                    flag = false;
            if(flag && hasContainerId() == container.hasContainerId())
                flag1 = true;
            else
                flag1 = false;
            if(hasContainerId())
                if(flag1 && getContainerId().equals(container.getContainerId()))
                    flag1 = true;
                else
                    flag1 = false;
            if(flag1 && hasState() == container.hasState())
                flag2 = true;
            else
                flag2 = false;
            if(hasState())
                if(flag2 && getState() == container.getState())
                    flag2 = true;
                else
                    flag2 = false;
            if(flag2 && hasVersion() == container.hasVersion())
                flag3 = true;
            else
                flag3 = false;
            if(hasVersion())
                if(flag3 && getVersion().equals(container.getVersion()))
                    flag3 = true;
                else
                    flag3 = false;
            return flag3;
        }

        public String getContainerId()
        {
            Object obj = containerId_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                containerId_ = s;
            return s;
        }

        public ByteString getContainerIdBytes()
        {
            Object obj = containerId_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                containerId_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Container getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Resource getJsResource()
        {
            return jsResource_;
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
                k = 0 + CodedOutputStream.computeMessageSize(1, jsResource_);
            if((2 & bitField0_) == 2)
                k += CodedOutputStream.computeBytesSize(3, getContainerIdBytes());
            if((4 & bitField0_) == 4)
                k += CodedOutputStream.computeEnumSize(4, state_.getNumber());
            if((8 & bitField0_) == 8)
                k += CodedOutputStream.computeBytesSize(5, getVersionBytes());
            int l = k + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public ResourceState getState()
        {
            return state_;
        }

        public String getVersion()
        {
            Object obj = version_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                version_ = s;
            return s;
        }

        public ByteString getVersionBytes()
        {
            Object obj = version_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                version_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public boolean hasContainerId()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasJsResource()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasState()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasVersion()
        {
            return (8 & bitField0_) == 8;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Serving$Container.hashCode();
            if(hasJsResource())
                i = 53 * (1 + i * 37) + getJsResource().hashCode();
            if(hasContainerId())
                i = 53 * (3 + i * 37) + getContainerId().hashCode();
            if(hasState())
                i = 53 * (4 + i * 37) + Internal.hashEnum(getState());
            if(hasVersion())
                i = 53 * (5 + i * 37) + getVersion().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableServing$Container");
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
            if(!hasJsResource())
            {
                memoizedIsInitialized = 0;
                return false;
            }
            if(!hasContainerId())
            {
                memoizedIsInitialized = 0;
                return false;
            }
            if(!hasState())
            {
                memoizedIsInitialized = 0;
                return false;
            }
            if(!getJsResource().isInitialized())
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
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessage(1, jsResource_);
            if((2 & bitField0_) == 2)
                codedoutputstream.writeBytes(3, getContainerIdBytes());
            if((4 & bitField0_) == 4)
                codedoutputstream.writeEnum(4, state_.getNumber());
            if((8 & bitField0_) == 8)
                codedoutputstream.writeBytes(5, getVersionBytes());
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int CONTAINER_ID_FIELD_NUMBER = 3;
        public static final int JS_RESOURCE_FIELD_NUMBER = 1;
        public static Parser PARSER = new AbstractParser() {

            public Container parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new Container(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int STATE_FIELD_NUMBER = 4;
        public static final int VERSION_FIELD_NUMBER = 5;
        private static final Container defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private Object containerId_;
        private Resource jsResource_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private ResourceState state_;
        private final ByteString unknownFields;
        private Object version_;

        static 
        {
            defaultInstance = new Container(true);
            defaultInstance.initFields();
        }


/*
        static Resource access$302(Container container, Resource resource)
        {
            container.jsResource_ = resource;
            return resource;
        }

*/



/*
        static Object access$402(Container container, Object obj)
        {
            container.containerId_ = obj;
            return obj;
        }

*/


/*
        static ResourceState access$502(Container container, ResourceState resourcestate)
        {
            container.state_ = resourcestate;
            return resourcestate;
        }

*/



/*
        static Object access$602(Container container, Object obj)
        {
            container.version_ = obj;
            return obj;
        }

*/


/*
        static int access$702(Container container, int i)
        {
            container.bitField0_ = i;
            return i;
        }

*/


        private Container(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L20:
            if(flag) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 5: default 96
        //                       0: 418
        //                       10: 115
        //                       26: 224
        //                       32: 268
        //                       42: 322;
               goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            int k = 1 & bitField0_;
            Resource.Builder builder = null;
            if(k != 1) goto _L10; else goto _L9
_L9:
            builder = jsResource_.toBuilder();
_L10:
            jsResource_ = (Resource)codedinputstream.readMessage(Resource.PARSER, extensionregistrylite);
            if(builder == null) goto _L12; else goto _L11
_L11:
            builder.mergeFrom(jsResource_);
            jsResource_ = builder.buildPartial();
_L12:
            bitField0_ = 1 | bitField0_;
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L18:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            ByteString bytestring1 = codedinputstream.readBytes();
            bitField0_ = 2 | bitField0_;
            containerId_ = bytestring1;
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L7:
            int j;
            ResourceState resourcestate;
            j = codedinputstream.readEnum();
            resourcestate = ResourceState.valueOf(j);
            if(resourcestate != null) goto _L14; else goto _L13
_L13:
            codedoutputstream.writeRawVarint32(i);
            codedoutputstream.writeRawVarint32(j);
            continue; /* Loop/switch isn't completed */
_L14:
            bitField0_ = 4 | bitField0_;
            state_ = resourcestate;
            continue; /* Loop/switch isn't completed */
_L8:
            ByteString bytestring = codedinputstream.readBytes();
            bitField0_ = 8 | bitField0_;
            version_ = bytestring;
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L16:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L16; else goto _L15
_L15:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L18; else goto _L17
_L17:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L20; else goto _L19
_L19:
        }


        private Container(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private Container(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class Container.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements ContainerOrBuilder
    {

        private static Container.Builder create()
        {
            return new Container.Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public Container build()
        {
            Container container = buildPartial();
            if(!container.isInitialized())
                throw newUninitializedMessageException(container);
            else
                return container;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public Container buildPartial()
        {
            Container container = new Container(this);
            int i = bitField0_;
            int j = i & 1;
            int k = 0;
            if(j == 1)
                k = false | true;
            container.jsResource_ = jsResource_;
            if((i & 2) == 2)
                k |= 2;
            container.containerId_ = containerId_;
            if((i & 4) == 4)
                k |= 4;
            container.state_ = state_;
            if((i & 8) == 8)
                k |= 8;
            container.version_ = version_;
            container.bitField0_ = k;
            return container;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public Container.Builder clear()
        {
            super.clear();
            jsResource_ = Resource.getDefaultInstance();
            bitField0_ = -2 & bitField0_;
            containerId_ = "";
            bitField0_ = -3 & bitField0_;
            state_ = ResourceState.PREVIEW;
            bitField0_ = -5 & bitField0_;
            version_ = "";
            bitField0_ = -9 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public Container.Builder clearContainerId()
        {
            bitField0_ = -3 & bitField0_;
            containerId_ = Container.getDefaultInstance().getContainerId();
            return this;
        }

        public Container.Builder clearJsResource()
        {
            jsResource_ = Resource.getDefaultInstance();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public Container.Builder clearState()
        {
            bitField0_ = -5 & bitField0_;
            state_ = ResourceState.PREVIEW;
            return this;
        }

        public Container.Builder clearVersion()
        {
            bitField0_ = -9 & bitField0_;
            version_ = Container.getDefaultInstance().getVersion();
            return this;
        }

        public Container.Builder clone()
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

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public String getContainerId()
        {
            Object obj = containerId_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    containerId_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getContainerIdBytes()
        {
            Object obj = containerId_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                containerId_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Container getDefaultInstanceForType()
        {
            return Container.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Resource getJsResource()
        {
            return jsResource_;
        }

        public ResourceState getState()
        {
            return state_;
        }

        public String getVersion()
        {
            Object obj = version_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    version_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getVersionBytes()
        {
            Object obj = version_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                version_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public boolean hasContainerId()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasJsResource()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasState()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasVersion()
        {
            return (8 & bitField0_) == 8;
        }

        public final boolean isInitialized()
        {
            while(!hasJsResource() || !hasContainerId() || !hasState() || !getJsResource().isInitialized()) 
                return false;
            return true;
        }

        public Container.Builder mergeFrom(Container container)
        {
            if(container == Container.getDefaultInstance())
                return this;
            if(container.hasJsResource())
                mergeJsResource(container.getJsResource());
            if(container.hasContainerId())
            {
                bitField0_ = 2 | bitField0_;
                containerId_ = container.containerId_;
            }
            if(container.hasState())
                setState(container.getState());
            if(container.hasVersion())
            {
                bitField0_ = 8 | bitField0_;
                version_ = container.version_;
            }
            setUnknownFields(getUnknownFields().concat(container.unknownFields));
            return this;
        }

        public Container.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            Container container = null;
            Container container1 = (Container)Container.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(container1 != null)
                mergeFrom(container1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            container = (Container)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(container != null)
                mergeFrom(container);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((Container)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public Container.Builder mergeJsResource(Resource resource)
        {
            if((1 & bitField0_) == 1 && jsResource_ != Resource.getDefaultInstance())
                jsResource_ = Resource.newBuilder(jsResource_).mergeFrom(resource).buildPartial();
            else
                jsResource_ = resource;
            bitField0_ = 1 | bitField0_;
            return this;
        }

        public Container.Builder setContainerId(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                containerId_ = s;
                return this;
            }
        }

        public Container.Builder setContainerIdBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                containerId_ = bytestring;
                return this;
            }
        }

        public Container.Builder setJsResource(Resource.Builder builder)
        {
            jsResource_ = builder.build();
            bitField0_ = 1 | bitField0_;
            return this;
        }

        public Container.Builder setJsResource(Resource resource)
        {
            if(resource == null)
            {
                throw new NullPointerException();
            } else
            {
                jsResource_ = resource;
                bitField0_ = 1 | bitField0_;
                return this;
            }
        }

        public Container.Builder setState(ResourceState resourcestate)
        {
            if(resourcestate == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 4 | bitField0_;
                state_ = resourcestate;
                return this;
            }
        }

        public Container.Builder setVersion(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 8 | bitField0_;
                version_ = s;
                return this;
            }
        }

        public Container.Builder setVersionBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 8 | bitField0_;
                version_ = bytestring;
                return this;
            }
        }

        private int bitField0_;
        private Object containerId_;
        private Resource jsResource_;
        private ResourceState state_;
        private Object version_;


        private Container.Builder()
        {
            jsResource_ = Resource.getDefaultInstance();
            containerId_ = "";
            state_ = ResourceState.PREVIEW;
            version_ = "";
            maybeForceBuilderInitialization();
        }
    }

    public static interface ContainerOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract String getContainerId();

        public abstract ByteString getContainerIdBytes();

        public abstract Resource getJsResource();

        public abstract ResourceState getState();

        public abstract String getVersion();

        public abstract ByteString getVersionBytes();

        public abstract boolean hasContainerId();

        public abstract boolean hasJsResource();

        public abstract boolean hasState();

        public abstract boolean hasVersion();
    }

    public static final class FunctionCall extends GeneratedMessageLite
        implements FunctionCallOrBuilder
    {

        public static FunctionCall getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            property_ = Collections.emptyList();
            function_ = 0;
            name_ = 0;
            liveOnly_ = false;
            serverSide_ = false;
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(FunctionCall functioncall)
        {
            return newBuilder().mergeFrom(functioncall);
        }

        public static FunctionCall parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (FunctionCall)PARSER.parseDelimitedFrom(inputstream);
        }

        public static FunctionCall parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (FunctionCall)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static FunctionCall parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (FunctionCall)PARSER.parseFrom(bytestring);
        }

        public static FunctionCall parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (FunctionCall)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static FunctionCall parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (FunctionCall)PARSER.parseFrom(codedinputstream);
        }

        public static FunctionCall parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (FunctionCall)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static FunctionCall parseFrom(InputStream inputstream)
            throws IOException
        {
            return (FunctionCall)PARSER.parseFrom(inputstream);
        }

        public static FunctionCall parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (FunctionCall)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static FunctionCall parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (FunctionCall)PARSER.parseFrom(abyte0);
        }

        public static FunctionCall parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (FunctionCall)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof FunctionCall))
                return super.equals(obj);
            FunctionCall functioncall = (FunctionCall)obj;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            if(true && getPropertyList().equals(functioncall.getPropertyList()))
                flag = true;
            else
                flag = false;
            if(flag && hasFunction() == functioncall.hasFunction())
                flag1 = true;
            else
                flag1 = false;
            if(hasFunction())
                if(flag1 && getFunction() == functioncall.getFunction())
                    flag1 = true;
                else
                    flag1 = false;
            if(flag1 && hasName() == functioncall.hasName())
                flag2 = true;
            else
                flag2 = false;
            if(hasName())
                if(flag2 && getName() == functioncall.getName())
                    flag2 = true;
                else
                    flag2 = false;
            if(flag2 && hasLiveOnly() == functioncall.hasLiveOnly())
                flag3 = true;
            else
                flag3 = false;
            if(hasLiveOnly())
                if(flag3 && getLiveOnly() == functioncall.getLiveOnly())
                    flag3 = true;
                else
                    flag3 = false;
            if(flag3 && hasServerSide() == functioncall.hasServerSide())
                flag4 = true;
            else
                flag4 = false;
            if(hasServerSide())
                if(flag4 && getServerSide() == functioncall.getServerSide())
                    flag4 = true;
                else
                    flag4 = false;
            return flag4;
        }

        public FunctionCall getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getFunction()
        {
            return function_;
        }

        public boolean getLiveOnly()
        {
            return liveOnly_;
        }

        public int getName()
        {
            return name_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getProperty(int i)
        {
            return ((Integer)property_.get(i)).intValue();
        }

        public int getPropertyCount()
        {
            return property_.size();
        }

        public List getPropertyList()
        {
            return property_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 8 & bitField0_;
            int k = 0;
            if(j == 8)
                k = 0 + CodedOutputStream.computeBoolSize(1, serverSide_);
            if((1 & bitField0_) == 1)
                k += CodedOutputStream.computeInt32Size(2, function_);
            int l = 0;
            for(int i1 = 0; i1 < property_.size(); i1++)
                l += CodedOutputStream.computeInt32SizeNoTag(((Integer)property_.get(i1)).intValue());

            int j1 = k + l + 1 * getPropertyList().size();
            if((2 & bitField0_) == 2)
                j1 += CodedOutputStream.computeInt32Size(4, name_);
            if((4 & bitField0_) == 4)
                j1 += CodedOutputStream.computeBoolSize(6, liveOnly_);
            int k1 = j1 + unknownFields.size();
            memoizedSerializedSize = k1;
            return k1;
        }

        public boolean getServerSide()
        {
            return serverSide_;
        }

        public boolean hasFunction()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasLiveOnly()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasName()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasServerSide()
        {
            return (8 & bitField0_) == 8;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Serving$FunctionCall.hashCode();
            if(getPropertyCount() > 0)
                i = 53 * (3 + i * 37) + getPropertyList().hashCode();
            if(hasFunction())
                i = 53 * (2 + i * 37) + getFunction();
            if(hasName())
                i = 53 * (4 + i * 37) + getName();
            if(hasLiveOnly())
                i = 53 * (6 + i * 37) + Internal.hashBoolean(getLiveOnly());
            if(hasServerSide())
                i = 53 * (1 + i * 37) + Internal.hashBoolean(getServerSide());
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableServing$FunctionCall");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            if(!hasFunction())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
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
            if((8 & bitField0_) == 8)
                codedoutputstream.writeBool(1, serverSide_);
            if((1 & bitField0_) == 1)
                codedoutputstream.writeInt32(2, function_);
            for(int i = 0; i < property_.size(); i++)
                codedoutputstream.writeInt32(3, ((Integer)property_.get(i)).intValue());

            if((2 & bitField0_) == 2)
                codedoutputstream.writeInt32(4, name_);
            if((4 & bitField0_) == 4)
                codedoutputstream.writeBool(6, liveOnly_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int FUNCTION_FIELD_NUMBER = 2;
        public static final int LIVE_ONLY_FIELD_NUMBER = 6;
        public static final int NAME_FIELD_NUMBER = 4;
        public static Parser PARSER = new AbstractParser() {

            public FunctionCall parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new FunctionCall(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int PROPERTY_FIELD_NUMBER = 3;
        public static final int SERVER_SIDE_FIELD_NUMBER = 1;
        private static final FunctionCall defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private int function_;
        private boolean liveOnly_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private List property_;
        private boolean serverSide_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new FunctionCall(true);
            defaultInstance.initFields();
        }



/*
        static List access$3002(FunctionCall functioncall, List list)
        {
            functioncall.property_ = list;
            return list;
        }

*/


/*
        static int access$3102(FunctionCall functioncall, int i)
        {
            functioncall.function_ = i;
            return i;
        }

*/


/*
        static int access$3202(FunctionCall functioncall, int i)
        {
            functioncall.name_ = i;
            return i;
        }

*/


/*
        static boolean access$3302(FunctionCall functioncall, boolean flag)
        {
            functioncall.liveOnly_ = flag;
            return flag;
        }

*/


/*
        static boolean access$3402(FunctionCall functioncall, boolean flag)
        {
            functioncall.serverSide_ = flag;
            return flag;
        }

*/


/*
        static int access$3502(FunctionCall functioncall, int i)
        {
            functioncall.bitField0_ = i;
            return i;
        }

*/


        private FunctionCall(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            boolean flag;
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag1;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            flag = false;
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag1 = false;
_L16:
            if(flag1) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 7: default 116
        //                       0: 499
        //                       8: 135
        //                       16: 207
        //                       24: 247
        //                       26: 289
        //                       32: 364
        //                       48: 385;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag1 = true;
            continue; /* Loop/switch isn't completed */
_L5:
            bitField0_ = 8 | bitField0_;
            serverSide_ = codedinputstream.readBool();
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((flag & true))
                property_ = Collections.unmodifiableList(property_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L14:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            bitField0_ = 1 | bitField0_;
            function_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L7:
            if((flag & true))
                break MISSING_BLOCK_LABEL_269;
            property_ = new ArrayList();
            flag |= true;
            property_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L8:
            int j = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((flag & true))
                break MISSING_BLOCK_LABEL_328;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_328;
            property_ = new ArrayList();
            flag |= true;
            for(; codedinputstream.getBytesUntilLimit() > 0; property_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(j);
            continue; /* Loop/switch isn't completed */
_L9:
            bitField0_ = 2 | bitField0_;
            name_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L10:
            bitField0_ = 4 | bitField0_;
            liveOnly_ = codedinputstream.readBool();
            continue; /* Loop/switch isn't completed */
_L2:
            if((flag & true))
                property_ = Collections.unmodifiableList(property_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L12:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L12; else goto _L11
_L11:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L14; else goto _L13
_L13:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag1 = true;
            if(true) goto _L16; else goto _L15
_L15:
        }


        private FunctionCall(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private FunctionCall(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class FunctionCall.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements FunctionCallOrBuilder
    {

        private static FunctionCall.Builder create()
        {
            return new FunctionCall.Builder();
        }

        private void ensurePropertyIsMutable()
        {
            if((1 & bitField0_) != 1)
            {
                property_ = new ArrayList(property_);
                bitField0_ = 1 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public FunctionCall.Builder addAllProperty(Iterable iterable)
        {
            ensurePropertyIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, property_);
            return this;
        }

        public FunctionCall.Builder addProperty(int i)
        {
            ensurePropertyIsMutable();
            property_.add(Integer.valueOf(i));
            return this;
        }

        public FunctionCall build()
        {
            FunctionCall functioncall = buildPartial();
            if(!functioncall.isInitialized())
                throw newUninitializedMessageException(functioncall);
            else
                return functioncall;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public FunctionCall buildPartial()
        {
            FunctionCall functioncall = new FunctionCall(this);
            int i = bitField0_;
            if((1 & bitField0_) == 1)
            {
                property_ = Collections.unmodifiableList(property_);
                bitField0_ = -2 & bitField0_;
            }
            functioncall.property_ = property_;
            int j = i & 2;
            int k = 0;
            if(j == 2)
                k = false | true;
            functioncall.function_ = function_;
            if((i & 4) == 4)
                k |= 2;
            functioncall.name_ = name_;
            if((i & 8) == 8)
                k |= 4;
            functioncall.liveOnly_ = liveOnly_;
            if((i & 0x10) == 16)
                k |= 8;
            functioncall.serverSide_ = serverSide_;
            functioncall.bitField0_ = k;
            return functioncall;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public FunctionCall.Builder clear()
        {
            super.clear();
            property_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            function_ = 0;
            bitField0_ = -3 & bitField0_;
            name_ = 0;
            bitField0_ = -5 & bitField0_;
            liveOnly_ = false;
            bitField0_ = -9 & bitField0_;
            serverSide_ = false;
            bitField0_ = 0xffffffef & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public FunctionCall.Builder clearFunction()
        {
            bitField0_ = -3 & bitField0_;
            function_ = 0;
            return this;
        }

        public FunctionCall.Builder clearLiveOnly()
        {
            bitField0_ = -9 & bitField0_;
            liveOnly_ = false;
            return this;
        }

        public FunctionCall.Builder clearName()
        {
            bitField0_ = -5 & bitField0_;
            name_ = 0;
            return this;
        }

        public FunctionCall.Builder clearProperty()
        {
            property_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public FunctionCall.Builder clearServerSide()
        {
            bitField0_ = 0xffffffef & bitField0_;
            serverSide_ = false;
            return this;
        }

        public FunctionCall.Builder clone()
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

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public FunctionCall getDefaultInstanceForType()
        {
            return FunctionCall.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getFunction()
        {
            return function_;
        }

        public boolean getLiveOnly()
        {
            return liveOnly_;
        }

        public int getName()
        {
            return name_;
        }

        public int getProperty(int i)
        {
            return ((Integer)property_.get(i)).intValue();
        }

        public int getPropertyCount()
        {
            return property_.size();
        }

        public List getPropertyList()
        {
            return Collections.unmodifiableList(property_);
        }

        public boolean getServerSide()
        {
            return serverSide_;
        }

        public boolean hasFunction()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasLiveOnly()
        {
            return (8 & bitField0_) == 8;
        }

        public boolean hasName()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasServerSide()
        {
            return (0x10 & bitField0_) == 16;
        }

        public final boolean isInitialized()
        {
            return hasFunction();
        }

        public FunctionCall.Builder mergeFrom(FunctionCall functioncall)
        {
            if(functioncall == FunctionCall.getDefaultInstance())
                return this;
            if(!functioncall.property_.isEmpty())
                if(property_.isEmpty())
                {
                    property_ = functioncall.property_;
                    bitField0_ = -2 & bitField0_;
                } else
                {
                    ensurePropertyIsMutable();
                    property_.addAll(functioncall.property_);
                }
            if(functioncall.hasFunction())
                setFunction(functioncall.getFunction());
            if(functioncall.hasName())
                setName(functioncall.getName());
            if(functioncall.hasLiveOnly())
                setLiveOnly(functioncall.getLiveOnly());
            if(functioncall.hasServerSide())
                setServerSide(functioncall.getServerSide());
            setUnknownFields(getUnknownFields().concat(functioncall.unknownFields));
            return this;
        }

        public FunctionCall.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            FunctionCall functioncall = null;
            FunctionCall functioncall1 = (FunctionCall)FunctionCall.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(functioncall1 != null)
                mergeFrom(functioncall1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            functioncall = (FunctionCall)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(functioncall != null)
                mergeFrom(functioncall);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((FunctionCall)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public FunctionCall.Builder setFunction(int i)
        {
            bitField0_ = 2 | bitField0_;
            function_ = i;
            return this;
        }

        public FunctionCall.Builder setLiveOnly(boolean flag)
        {
            bitField0_ = 8 | bitField0_;
            liveOnly_ = flag;
            return this;
        }

        public FunctionCall.Builder setName(int i)
        {
            bitField0_ = 4 | bitField0_;
            name_ = i;
            return this;
        }

        public FunctionCall.Builder setProperty(int i, int j)
        {
            ensurePropertyIsMutable();
            property_.set(i, Integer.valueOf(j));
            return this;
        }

        public FunctionCall.Builder setServerSide(boolean flag)
        {
            bitField0_ = 0x10 | bitField0_;
            serverSide_ = flag;
            return this;
        }

        private int bitField0_;
        private int function_;
        private boolean liveOnly_;
        private int name_;
        private List property_;
        private boolean serverSide_;


        private FunctionCall.Builder()
        {
            property_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }
    }

    public static interface FunctionCallOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract int getFunction();

        public abstract boolean getLiveOnly();

        public abstract int getName();

        public abstract int getProperty(int i);

        public abstract int getPropertyCount();

        public abstract List getPropertyList();

        public abstract boolean getServerSide();

        public abstract boolean hasFunction();

        public abstract boolean hasLiveOnly();

        public abstract boolean hasName();

        public abstract boolean hasServerSide();
    }

    public static final class OptionalResource extends GeneratedMessageLite
        implements OptionalResourceOrBuilder
    {

        public static OptionalResource getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            resource_ = Resource.getDefaultInstance();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(OptionalResource optionalresource)
        {
            return newBuilder().mergeFrom(optionalresource);
        }

        public static OptionalResource parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (OptionalResource)PARSER.parseDelimitedFrom(inputstream);
        }

        public static OptionalResource parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (OptionalResource)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static OptionalResource parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (OptionalResource)PARSER.parseFrom(bytestring);
        }

        public static OptionalResource parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (OptionalResource)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static OptionalResource parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (OptionalResource)PARSER.parseFrom(codedinputstream);
        }

        public static OptionalResource parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (OptionalResource)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static OptionalResource parseFrom(InputStream inputstream)
            throws IOException
        {
            return (OptionalResource)PARSER.parseFrom(inputstream);
        }

        public static OptionalResource parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (OptionalResource)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static OptionalResource parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (OptionalResource)PARSER.parseFrom(abyte0);
        }

        public static OptionalResource parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (OptionalResource)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof OptionalResource))
                return super.equals(obj);
            OptionalResource optionalresource = (OptionalResource)obj;
            boolean flag;
            if(true && hasResource() == optionalresource.hasResource())
                flag = true;
            else
                flag = false;
            if(hasResource())
                if(flag && getResource().equals(optionalresource.getResource()))
                    flag = true;
                else
                    flag = false;
            return flag;
        }

        public OptionalResource getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public Resource getResource()
        {
            return resource_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 1 & bitField0_;
            int k = 0;
            if(j == 1)
                k = 0 + CodedOutputStream.computeMessageSize(2, resource_);
            int l = k + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public boolean hasResource()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Serving$OptionalResource.hashCode();
            if(hasResource())
                i = 53 * (2 + i * 37) + getResource().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableServing$OptionalResource");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            if(hasResource() && !getResource().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
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
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessage(2, resource_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static Parser PARSER = new AbstractParser() {

            public OptionalResource parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new OptionalResource(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int RESOURCE_FIELD_NUMBER = 2;
        private static final OptionalResource defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Resource resource_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new OptionalResource(true);
            defaultInstance.initFields();
        }


/*
        static Resource access$8302(OptionalResource optionalresource, Resource resource)
        {
            optionalresource.resource_ = resource;
            return resource;
        }

*/


/*
        static int access$8402(OptionalResource optionalresource, int i)
        {
            optionalresource.bitField0_ = i;
            return i;
        }

*/


        private OptionalResource(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L15:
            if(flag) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 2: default 72
        //                       0: 289
        //                       18: 91;
               goto _L3 _L4 _L5
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            int j = 1 & bitField0_;
            Resource.Builder builder = null;
            if(j != 1) goto _L7; else goto _L6
_L6:
            builder = resource_.toBuilder();
_L7:
            resource_ = (Resource)codedinputstream.readMessage(Resource.PARSER, extensionregistrylite);
            if(builder == null) goto _L9; else goto _L8
_L8:
            builder.mergeFrom(resource_);
            resource_ = builder.buildPartial();
_L9:
            bitField0_ = 1 | bitField0_;
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L13:
            makeExtensionsImmutable();
            throw exception1;
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L11:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L11; else goto _L10
_L10:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L13; else goto _L12
_L12:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L15; else goto _L14
_L14:
        }


        private OptionalResource(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private OptionalResource(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class OptionalResource.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements OptionalResourceOrBuilder
    {

        private static OptionalResource.Builder create()
        {
            return new OptionalResource.Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public OptionalResource build()
        {
            OptionalResource optionalresource = buildPartial();
            if(!optionalresource.isInitialized())
                throw newUninitializedMessageException(optionalresource);
            else
                return optionalresource;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public OptionalResource buildPartial()
        {
            OptionalResource optionalresource = new OptionalResource(this);
            int i = 1 & bitField0_;
            int j = 0;
            if(i == 1)
                j = false | true;
            optionalresource.resource_ = resource_;
            optionalresource.bitField0_ = j;
            return optionalresource;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public OptionalResource.Builder clear()
        {
            super.clear();
            resource_ = Resource.getDefaultInstance();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public OptionalResource.Builder clearResource()
        {
            resource_ = Resource.getDefaultInstance();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public OptionalResource.Builder clone()
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

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public OptionalResource getDefaultInstanceForType()
        {
            return OptionalResource.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Resource getResource()
        {
            return resource_;
        }

        public boolean hasResource()
        {
            return (1 & bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
            return !hasResource() || getResource().isInitialized();
        }

        public OptionalResource.Builder mergeFrom(OptionalResource optionalresource)
        {
            if(optionalresource == OptionalResource.getDefaultInstance())
                return this;
            if(optionalresource.hasResource())
                mergeResource(optionalresource.getResource());
            setUnknownFields(getUnknownFields().concat(optionalresource.unknownFields));
            return this;
        }

        public OptionalResource.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            OptionalResource optionalresource = null;
            OptionalResource optionalresource1 = (OptionalResource)OptionalResource.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(optionalresource1 != null)
                mergeFrom(optionalresource1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            optionalresource = (OptionalResource)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(optionalresource != null)
                mergeFrom(optionalresource);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((OptionalResource)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public OptionalResource.Builder mergeResource(Resource resource)
        {
            if((1 & bitField0_) == 1 && resource_ != Resource.getDefaultInstance())
                resource_ = Resource.newBuilder(resource_).mergeFrom(resource).buildPartial();
            else
                resource_ = resource;
            bitField0_ = 1 | bitField0_;
            return this;
        }

        public OptionalResource.Builder setResource(Resource.Builder builder)
        {
            resource_ = builder.build();
            bitField0_ = 1 | bitField0_;
            return this;
        }

        public OptionalResource.Builder setResource(Resource resource)
        {
            if(resource == null)
            {
                throw new NullPointerException();
            } else
            {
                resource_ = resource;
                bitField0_ = 1 | bitField0_;
                return this;
            }
        }

        private int bitField0_;
        private Resource resource_;


        private OptionalResource.Builder()
        {
            resource_ = Resource.getDefaultInstance();
            maybeForceBuilderInitialization();
        }
    }

    public static interface OptionalResourceOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract Resource getResource();

        public abstract boolean hasResource();
    }

    public static final class Property extends GeneratedMessageLite
        implements PropertyOrBuilder
    {

        public static Property getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            key_ = 0;
            value_ = 0;
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(Property property)
        {
            return newBuilder().mergeFrom(property);
        }

        public static Property parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (Property)PARSER.parseDelimitedFrom(inputstream);
        }

        public static Property parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Property)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static Property parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (Property)PARSER.parseFrom(bytestring);
        }

        public static Property parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Property)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static Property parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (Property)PARSER.parseFrom(codedinputstream);
        }

        public static Property parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Property)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static Property parseFrom(InputStream inputstream)
            throws IOException
        {
            return (Property)PARSER.parseFrom(inputstream);
        }

        public static Property parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Property)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static Property parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (Property)PARSER.parseFrom(abyte0);
        }

        public static Property parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Property)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof Property))
                return super.equals(obj);
            Property property = (Property)obj;
            boolean flag;
            boolean flag1;
            if(true && hasKey() == property.hasKey())
                flag = true;
            else
                flag = false;
            if(hasKey())
                if(flag && getKey() == property.getKey())
                    flag = true;
                else
                    flag = false;
            if(flag && hasValue() == property.hasValue())
                flag1 = true;
            else
                flag1 = false;
            if(hasValue())
                if(flag1 && getValue() == property.getValue())
                    flag1 = true;
                else
                    flag1 = false;
            return flag1;
        }

        public Property getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getKey()
        {
            return key_;
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
                k = 0 + CodedOutputStream.computeInt32Size(1, key_);
            if((2 & bitField0_) == 2)
                k += CodedOutputStream.computeInt32Size(2, value_);
            int l = k + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public int getValue()
        {
            return value_;
        }

        public boolean hasKey()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasValue()
        {
            return (2 & bitField0_) == 2;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Serving$Property.hashCode();
            if(hasKey())
                i = 53 * (1 + i * 37) + getKey();
            if(hasValue())
                i = 53 * (2 + i * 37) + getValue();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableServing$Property");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            if(!hasKey())
            {
                memoizedIsInitialized = 0;
                return false;
            }
            if(!hasValue())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
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
            if((1 & bitField0_) == 1)
                codedoutputstream.writeInt32(1, key_);
            if((2 & bitField0_) == 2)
                codedoutputstream.writeInt32(2, value_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int KEY_FIELD_NUMBER = 1;
        public static Parser PARSER = new AbstractParser() {

            public Property parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new Property(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final Property defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private int key_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;
        private int value_;

        static 
        {
            defaultInstance = new Property(true);
            defaultInstance.initFields();
        }


/*
        static int access$2302(Property property, int i)
        {
            property.key_ = i;
            return i;
        }

*/


/*
        static int access$2402(Property property, int i)
        {
            property.value_ = i;
            return i;
        }

*/


/*
        static int access$2502(Property property, int i)
        {
            property.bitField0_ = i;
            return i;
        }

*/


        private Property(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L12:
            if(flag) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 3: default 80
        //                       0: 261
        //                       8: 99
        //                       16: 151;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            bitField0_ = 1 | bitField0_;
            key_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L10:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            bitField0_ = 2 | bitField0_;
            value_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L8:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L8; else goto _L7
_L7:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L10; else goto _L9
_L9:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L12; else goto _L11
_L11:
        }


        private Property(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private Property(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class Property.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements PropertyOrBuilder
    {

        private static Property.Builder create()
        {
            return new Property.Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public Property build()
        {
            Property property = buildPartial();
            if(!property.isInitialized())
                throw newUninitializedMessageException(property);
            else
                return property;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public Property buildPartial()
        {
            Property property = new Property(this);
            int i = bitField0_;
            int j = i & 1;
            int k = 0;
            if(j == 1)
                k = false | true;
            property.key_ = key_;
            if((i & 2) == 2)
                k |= 2;
            property.value_ = value_;
            property.bitField0_ = k;
            return property;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public Property.Builder clear()
        {
            super.clear();
            key_ = 0;
            bitField0_ = -2 & bitField0_;
            value_ = 0;
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public Property.Builder clearKey()
        {
            bitField0_ = -2 & bitField0_;
            key_ = 0;
            return this;
        }

        public Property.Builder clearValue()
        {
            bitField0_ = -3 & bitField0_;
            value_ = 0;
            return this;
        }

        public Property.Builder clone()
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

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Property getDefaultInstanceForType()
        {
            return Property.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getKey()
        {
            return key_;
        }

        public int getValue()
        {
            return value_;
        }

        public boolean hasKey()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasValue()
        {
            return (2 & bitField0_) == 2;
        }

        public final boolean isInitialized()
        {
            while(!hasKey() || !hasValue()) 
                return false;
            return true;
        }

        public Property.Builder mergeFrom(Property property)
        {
            if(property == Property.getDefaultInstance())
                return this;
            if(property.hasKey())
                setKey(property.getKey());
            if(property.hasValue())
                setValue(property.getValue());
            setUnknownFields(getUnknownFields().concat(property.unknownFields));
            return this;
        }

        public Property.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            Property property = null;
            Property property1 = (Property)Property.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(property1 != null)
                mergeFrom(property1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            property = (Property)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(property != null)
                mergeFrom(property);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((Property)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public Property.Builder setKey(int i)
        {
            bitField0_ = 1 | bitField0_;
            key_ = i;
            return this;
        }

        public Property.Builder setValue(int i)
        {
            bitField0_ = 2 | bitField0_;
            value_ = i;
            return this;
        }

        private int bitField0_;
        private int key_;
        private int value_;


        private Property.Builder()
        {
            maybeForceBuilderInitialization();
        }
    }

    public static interface PropertyOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract int getKey();

        public abstract int getValue();

        public abstract boolean hasKey();

        public abstract boolean hasValue();
    }

    public static final class Resource extends GeneratedMessageLite
        implements ResourceOrBuilder
    {

        public static Resource getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            key_ = LazyStringArrayList.EMPTY;
            value_ = Collections.emptyList();
            property_ = Collections.emptyList();
            macro_ = Collections.emptyList();
            tag_ = Collections.emptyList();
            predicate_ = Collections.emptyList();
            rule_ = Collections.emptyList();
            previewAuthCode_ = "";
            malwareScanAuthCode_ = "";
            templateVersionSet_ = "0";
            version_ = "";
            liveJsCacheOption_ = CacheOption.getDefaultInstance();
            reportingSampleRate_ = 0.0F;
            enableAutoEventTracking_ = false;
            usageContext_ = LazyStringArrayList.EMPTY;
            resourceFormatVersion_ = 0;
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(Resource resource)
        {
            return newBuilder().mergeFrom(resource);
        }

        public static Resource parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (Resource)PARSER.parseDelimitedFrom(inputstream);
        }

        public static Resource parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Resource)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static Resource parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (Resource)PARSER.parseFrom(bytestring);
        }

        public static Resource parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Resource)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static Resource parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (Resource)PARSER.parseFrom(codedinputstream);
        }

        public static Resource parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Resource)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static Resource parseFrom(InputStream inputstream)
            throws IOException
        {
            return (Resource)PARSER.parseFrom(inputstream);
        }

        public static Resource parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Resource)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static Resource parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (Resource)PARSER.parseFrom(abyte0);
        }

        public static Resource parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Resource)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof Resource))
                return super.equals(obj);
            Resource resource = (Resource)obj;
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
            boolean flag12;
            boolean flag13;
            boolean flag14;
            boolean flag15;
            if(true && getKeyList().equals(resource.getKeyList()))
                flag = true;
            else
                flag = false;
            if(flag && getValueList().equals(resource.getValueList()))
                flag1 = true;
            else
                flag1 = false;
            if(flag1 && getPropertyList().equals(resource.getPropertyList()))
                flag2 = true;
            else
                flag2 = false;
            if(flag2 && getMacroList().equals(resource.getMacroList()))
                flag3 = true;
            else
                flag3 = false;
            if(flag3 && getTagList().equals(resource.getTagList()))
                flag4 = true;
            else
                flag4 = false;
            if(flag4 && getPredicateList().equals(resource.getPredicateList()))
                flag5 = true;
            else
                flag5 = false;
            if(flag5 && getRuleList().equals(resource.getRuleList()))
                flag6 = true;
            else
                flag6 = false;
            if(flag6 && hasPreviewAuthCode() == resource.hasPreviewAuthCode())
                flag7 = true;
            else
                flag7 = false;
            if(hasPreviewAuthCode())
                if(flag7 && getPreviewAuthCode().equals(resource.getPreviewAuthCode()))
                    flag7 = true;
                else
                    flag7 = false;
            if(flag7 && hasMalwareScanAuthCode() == resource.hasMalwareScanAuthCode())
                flag8 = true;
            else
                flag8 = false;
            if(hasMalwareScanAuthCode())
                if(flag8 && getMalwareScanAuthCode().equals(resource.getMalwareScanAuthCode()))
                    flag8 = true;
                else
                    flag8 = false;
            if(flag8 && hasTemplateVersionSet() == resource.hasTemplateVersionSet())
                flag9 = true;
            else
                flag9 = false;
            if(hasTemplateVersionSet())
                if(flag9 && getTemplateVersionSet().equals(resource.getTemplateVersionSet()))
                    flag9 = true;
                else
                    flag9 = false;
            if(flag9 && hasVersion() == resource.hasVersion())
                flag10 = true;
            else
                flag10 = false;
            if(hasVersion())
                if(flag10 && getVersion().equals(resource.getVersion()))
                    flag10 = true;
                else
                    flag10 = false;
            if(flag10 && hasLiveJsCacheOption() == resource.hasLiveJsCacheOption())
                flag11 = true;
            else
                flag11 = false;
            if(hasLiveJsCacheOption())
                if(flag11 && getLiveJsCacheOption().equals(resource.getLiveJsCacheOption()))
                    flag11 = true;
                else
                    flag11 = false;
            if(flag11 && hasReportingSampleRate() == resource.hasReportingSampleRate())
                flag12 = true;
            else
                flag12 = false;
            if(hasReportingSampleRate())
                if(flag12 && Float.floatToIntBits(getReportingSampleRate()) == Float.floatToIntBits(resource.getReportingSampleRate()))
                    flag12 = true;
                else
                    flag12 = false;
            if(flag12 && hasEnableAutoEventTracking() == resource.hasEnableAutoEventTracking())
                flag13 = true;
            else
                flag13 = false;
            if(hasEnableAutoEventTracking())
                if(flag13 && getEnableAutoEventTracking() == resource.getEnableAutoEventTracking())
                    flag13 = true;
                else
                    flag13 = false;
            if(flag13 && getUsageContextList().equals(resource.getUsageContextList()))
                flag14 = true;
            else
                flag14 = false;
            if(flag14 && hasResourceFormatVersion() == resource.hasResourceFormatVersion())
                flag15 = true;
            else
                flag15 = false;
            if(hasResourceFormatVersion())
                if(flag15 && getResourceFormatVersion() == resource.getResourceFormatVersion())
                    flag15 = true;
                else
                    flag15 = false;
            return flag15;
        }

        public Resource getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public boolean getEnableAutoEventTracking()
        {
            return enableAutoEventTracking_;
        }

        public String getKey(int i)
        {
            return (String)key_.get(i);
        }

        public ByteString getKeyBytes(int i)
        {
            return key_.getByteString(i);
        }

        public int getKeyCount()
        {
            return key_.size();
        }

        public List getKeyList()
        {
            return key_;
        }

        public CacheOption getLiveJsCacheOption()
        {
            return liveJsCacheOption_;
        }

        public FunctionCall getMacro(int i)
        {
            return (FunctionCall)macro_.get(i);
        }

        public int getMacroCount()
        {
            return macro_.size();
        }

        public List getMacroList()
        {
            return macro_;
        }

        public FunctionCallOrBuilder getMacroOrBuilder(int i)
        {
            return (FunctionCallOrBuilder)macro_.get(i);
        }

        public List getMacroOrBuilderList()
        {
            return macro_;
        }

        public String getMalwareScanAuthCode()
        {
            Object obj = malwareScanAuthCode_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                malwareScanAuthCode_ = s;
            return s;
        }

        public ByteString getMalwareScanAuthCodeBytes()
        {
            Object obj = malwareScanAuthCode_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                malwareScanAuthCode_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public FunctionCall getPredicate(int i)
        {
            return (FunctionCall)predicate_.get(i);
        }

        public int getPredicateCount()
        {
            return predicate_.size();
        }

        public List getPredicateList()
        {
            return predicate_;
        }

        public FunctionCallOrBuilder getPredicateOrBuilder(int i)
        {
            return (FunctionCallOrBuilder)predicate_.get(i);
        }

        public List getPredicateOrBuilderList()
        {
            return predicate_;
        }

        public String getPreviewAuthCode()
        {
            Object obj = previewAuthCode_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                previewAuthCode_ = s;
            return s;
        }

        public ByteString getPreviewAuthCodeBytes()
        {
            Object obj = previewAuthCode_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                previewAuthCode_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Property getProperty(int i)
        {
            return (Property)property_.get(i);
        }

        public int getPropertyCount()
        {
            return property_.size();
        }

        public List getPropertyList()
        {
            return property_;
        }

        public PropertyOrBuilder getPropertyOrBuilder(int i)
        {
            return (PropertyOrBuilder)property_.get(i);
        }

        public List getPropertyOrBuilderList()
        {
            return property_;
        }

        public float getReportingSampleRate()
        {
            return reportingSampleRate_;
        }

        public int getResourceFormatVersion()
        {
            return resourceFormatVersion_;
        }

        public Rule getRule(int i)
        {
            return (Rule)rule_.get(i);
        }

        public int getRuleCount()
        {
            return rule_.size();
        }

        public List getRuleList()
        {
            return rule_;
        }

        public RuleOrBuilder getRuleOrBuilder(int i)
        {
            return (RuleOrBuilder)rule_.get(i);
        }

        public List getRuleOrBuilderList()
        {
            return rule_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 0;
            for(int k = 0; k < key_.size(); k++)
                j += CodedOutputStream.computeBytesSizeNoTag(key_.getByteString(k));

            int l = 0 + j + 1 * getKeyList().size();
            for(int i1 = 0; i1 < value_.size(); i1++)
                l += CodedOutputStream.computeMessageSize(2, (MessageLite)value_.get(i1));

            for(int j1 = 0; j1 < property_.size(); j1++)
                l += CodedOutputStream.computeMessageSize(3, (MessageLite)property_.get(j1));

            for(int k1 = 0; k1 < macro_.size(); k1++)
                l += CodedOutputStream.computeMessageSize(4, (MessageLite)macro_.get(k1));

            for(int l1 = 0; l1 < tag_.size(); l1++)
                l += CodedOutputStream.computeMessageSize(5, (MessageLite)tag_.get(l1));

            for(int i2 = 0; i2 < predicate_.size(); i2++)
                l += CodedOutputStream.computeMessageSize(6, (MessageLite)predicate_.get(i2));

            for(int j2 = 0; j2 < rule_.size(); j2++)
                l += CodedOutputStream.computeMessageSize(7, (MessageLite)rule_.get(j2));

            if((1 & bitField0_) == 1)
                l += CodedOutputStream.computeBytesSize(9, getPreviewAuthCodeBytes());
            if((2 & bitField0_) == 2)
                l += CodedOutputStream.computeBytesSize(10, getMalwareScanAuthCodeBytes());
            if((4 & bitField0_) == 4)
                l += CodedOutputStream.computeBytesSize(12, getTemplateVersionSetBytes());
            if((8 & bitField0_) == 8)
                l += CodedOutputStream.computeBytesSize(13, getVersionBytes());
            if((0x10 & bitField0_) == 16)
                l += CodedOutputStream.computeMessageSize(14, liveJsCacheOption_);
            if((0x20 & bitField0_) == 32)
                l += CodedOutputStream.computeFloatSize(15, reportingSampleRate_);
            int k2 = 0;
            for(int l2 = 0; l2 < usageContext_.size(); l2++)
                k2 += CodedOutputStream.computeBytesSizeNoTag(usageContext_.getByteString(l2));

            int i3 = l + k2 + 2 * getUsageContextList().size();
            if((0x80 & bitField0_) == 128)
                i3 += CodedOutputStream.computeInt32Size(17, resourceFormatVersion_);
            if((0x40 & bitField0_) == 64)
                i3 += CodedOutputStream.computeBoolSize(18, enableAutoEventTracking_);
            int j3 = i3 + unknownFields.size();
            memoizedSerializedSize = j3;
            return j3;
        }

        public FunctionCall getTag(int i)
        {
            return (FunctionCall)tag_.get(i);
        }

        public int getTagCount()
        {
            return tag_.size();
        }

        public List getTagList()
        {
            return tag_;
        }

        public FunctionCallOrBuilder getTagOrBuilder(int i)
        {
            return (FunctionCallOrBuilder)tag_.get(i);
        }

        public List getTagOrBuilderList()
        {
            return tag_;
        }

        public String getTemplateVersionSet()
        {
            Object obj = templateVersionSet_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                templateVersionSet_ = s;
            return s;
        }

        public ByteString getTemplateVersionSetBytes()
        {
            Object obj = templateVersionSet_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                templateVersionSet_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public String getUsageContext(int i)
        {
            return (String)usageContext_.get(i);
        }

        public ByteString getUsageContextBytes(int i)
        {
            return usageContext_.getByteString(i);
        }

        public int getUsageContextCount()
        {
            return usageContext_.size();
        }

        public List getUsageContextList()
        {
            return usageContext_;
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.Value getValue(int i)
        {
            return (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)value_.get(i);
        }

        public int getValueCount()
        {
            return value_.size();
        }

        public List getValueList()
        {
            return value_;
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.ValueOrBuilder getValueOrBuilder(int i)
        {
            return (com.google.analytics.midtier.proto.containertag.TypeSystem.ValueOrBuilder)value_.get(i);
        }

        public List getValueOrBuilderList()
        {
            return value_;
        }

        public String getVersion()
        {
            Object obj = version_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                version_ = s;
            return s;
        }

        public ByteString getVersionBytes()
        {
            Object obj = version_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                version_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public boolean hasEnableAutoEventTracking()
        {
            return (0x40 & bitField0_) == 64;
        }

        public boolean hasLiveJsCacheOption()
        {
            return (0x10 & bitField0_) == 16;
        }

        public boolean hasMalwareScanAuthCode()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasPreviewAuthCode()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasReportingSampleRate()
        {
            return (0x20 & bitField0_) == 32;
        }

        public boolean hasResourceFormatVersion()
        {
            return (0x80 & bitField0_) == 128;
        }

        public boolean hasTemplateVersionSet()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasVersion()
        {
            return (8 & bitField0_) == 8;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Serving$Resource.hashCode();
            if(getKeyCount() > 0)
                i = 53 * (1 + i * 37) + getKeyList().hashCode();
            if(getValueCount() > 0)
                i = 53 * (2 + i * 37) + getValueList().hashCode();
            if(getPropertyCount() > 0)
                i = 53 * (3 + i * 37) + getPropertyList().hashCode();
            if(getMacroCount() > 0)
                i = 53 * (4 + i * 37) + getMacroList().hashCode();
            if(getTagCount() > 0)
                i = 53 * (5 + i * 37) + getTagList().hashCode();
            if(getPredicateCount() > 0)
                i = 53 * (6 + i * 37) + getPredicateList().hashCode();
            if(getRuleCount() > 0)
                i = 53 * (7 + i * 37) + getRuleList().hashCode();
            if(hasPreviewAuthCode())
                i = 53 * (9 + i * 37) + getPreviewAuthCode().hashCode();
            if(hasMalwareScanAuthCode())
                i = 53 * (10 + i * 37) + getMalwareScanAuthCode().hashCode();
            if(hasTemplateVersionSet())
                i = 53 * (12 + i * 37) + getTemplateVersionSet().hashCode();
            if(hasVersion())
                i = 53 * (13 + i * 37) + getVersion().hashCode();
            if(hasLiveJsCacheOption())
                i = 53 * (14 + i * 37) + getLiveJsCacheOption().hashCode();
            if(hasReportingSampleRate())
                i = 53 * (15 + i * 37) + Float.floatToIntBits(getReportingSampleRate());
            if(hasEnableAutoEventTracking())
                i = 53 * (18 + i * 37) + Internal.hashBoolean(getEnableAutoEventTracking());
            if(getUsageContextCount() > 0)
                i = 53 * (16 + i * 37) + getUsageContextList().hashCode();
            if(hasResourceFormatVersion())
                i = 53 * (17 + i * 37) + getResourceFormatVersion();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableServing$Resource");
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
            for(int i = 0; i < getValueCount(); i++)
                if(!getValue(i).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int j = 0; j < getPropertyCount(); j++)
                if(!getProperty(j).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int k = 0; k < getMacroCount(); k++)
                if(!getMacro(k).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int l = 0; l < getTagCount(); l++)
                if(!getTag(l).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int i1 = 0; i1 < getPredicateCount(); i1++)
                if(!getPredicate(i1).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            memoizedIsInitialized = flag;
            return flag;
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
            for(int i = 0; i < key_.size(); i++)
                codedoutputstream.writeBytes(1, key_.getByteString(i));

            for(int j = 0; j < value_.size(); j++)
                codedoutputstream.writeMessage(2, (MessageLite)value_.get(j));

            for(int k = 0; k < property_.size(); k++)
                codedoutputstream.writeMessage(3, (MessageLite)property_.get(k));

            for(int l = 0; l < macro_.size(); l++)
                codedoutputstream.writeMessage(4, (MessageLite)macro_.get(l));

            for(int i1 = 0; i1 < tag_.size(); i1++)
                codedoutputstream.writeMessage(5, (MessageLite)tag_.get(i1));

            for(int j1 = 0; j1 < predicate_.size(); j1++)
                codedoutputstream.writeMessage(6, (MessageLite)predicate_.get(j1));

            for(int k1 = 0; k1 < rule_.size(); k1++)
                codedoutputstream.writeMessage(7, (MessageLite)rule_.get(k1));

            if((1 & bitField0_) == 1)
                codedoutputstream.writeBytes(9, getPreviewAuthCodeBytes());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeBytes(10, getMalwareScanAuthCodeBytes());
            if((4 & bitField0_) == 4)
                codedoutputstream.writeBytes(12, getTemplateVersionSetBytes());
            if((8 & bitField0_) == 8)
                codedoutputstream.writeBytes(13, getVersionBytes());
            if((0x10 & bitField0_) == 16)
                codedoutputstream.writeMessage(14, liveJsCacheOption_);
            if((0x20 & bitField0_) == 32)
                codedoutputstream.writeFloat(15, reportingSampleRate_);
            for(int l1 = 0; l1 < usageContext_.size(); l1++)
                codedoutputstream.writeBytes(16, usageContext_.getByteString(l1));

            if((0x80 & bitField0_) == 128)
                codedoutputstream.writeInt32(17, resourceFormatVersion_);
            if((0x40 & bitField0_) == 64)
                codedoutputstream.writeBool(18, enableAutoEventTracking_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int ENABLE_AUTO_EVENT_TRACKING_FIELD_NUMBER = 18;
        public static final int KEY_FIELD_NUMBER = 1;
        public static final int LIVE_JS_CACHE_OPTION_FIELD_NUMBER = 14;
        public static final int MACRO_FIELD_NUMBER = 4;
        public static final int MALWARE_SCAN_AUTH_CODE_FIELD_NUMBER = 10;
        public static Parser PARSER = new AbstractParser() {

            public Resource parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new Resource(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int PREDICATE_FIELD_NUMBER = 6;
        public static final int PREVIEW_AUTH_CODE_FIELD_NUMBER = 9;
        public static final int PROPERTY_FIELD_NUMBER = 3;
        public static final int REPORTING_SAMPLE_RATE_FIELD_NUMBER = 15;
        public static final int RESOURCE_FORMAT_VERSION_FIELD_NUMBER = 17;
        public static final int RULE_FIELD_NUMBER = 7;
        public static final int TAG_FIELD_NUMBER = 5;
        public static final int TEMPLATE_VERSION_SET_FIELD_NUMBER = 12;
        public static final int USAGE_CONTEXT_FIELD_NUMBER = 16;
        public static final int VALUE_FIELD_NUMBER = 2;
        public static final int VERSION_FIELD_NUMBER = 13;
        private static final Resource defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private boolean enableAutoEventTracking_;
        private LazyStringList key_;
        private CacheOption liveJsCacheOption_;
        private List macro_;
        private Object malwareScanAuthCode_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List predicate_;
        private Object previewAuthCode_;
        private List property_;
        private float reportingSampleRate_;
        private int resourceFormatVersion_;
        private List rule_;
        private List tag_;
        private Object templateVersionSet_;
        private final ByteString unknownFields;
        private LazyStringList usageContext_;
        private List value_;
        private Object version_;

        static 
        {
            defaultInstance = new Resource(true);
            defaultInstance.initFields();
        }



/*
        static LazyStringList access$6202(Resource resource, LazyStringList lazystringlist)
        {
            resource.key_ = lazystringlist;
            return lazystringlist;
        }

*/



/*
        static List access$6302(Resource resource, List list)
        {
            resource.value_ = list;
            return list;
        }

*/



/*
        static List access$6402(Resource resource, List list)
        {
            resource.property_ = list;
            return list;
        }

*/



/*
        static List access$6502(Resource resource, List list)
        {
            resource.macro_ = list;
            return list;
        }

*/



/*
        static List access$6602(Resource resource, List list)
        {
            resource.tag_ = list;
            return list;
        }

*/



/*
        static List access$6702(Resource resource, List list)
        {
            resource.predicate_ = list;
            return list;
        }

*/



/*
        static List access$6802(Resource resource, List list)
        {
            resource.rule_ = list;
            return list;
        }

*/



/*
        static Object access$6902(Resource resource, Object obj)
        {
            resource.previewAuthCode_ = obj;
            return obj;
        }

*/



/*
        static Object access$7002(Resource resource, Object obj)
        {
            resource.malwareScanAuthCode_ = obj;
            return obj;
        }

*/



/*
        static Object access$7102(Resource resource, Object obj)
        {
            resource.templateVersionSet_ = obj;
            return obj;
        }

*/



/*
        static Object access$7202(Resource resource, Object obj)
        {
            resource.version_ = obj;
            return obj;
        }

*/


/*
        static CacheOption access$7302(Resource resource, CacheOption cacheoption)
        {
            resource.liveJsCacheOption_ = cacheoption;
            return cacheoption;
        }

*/


/*
        static float access$7402(Resource resource, float f)
        {
            resource.reportingSampleRate_ = f;
            return f;
        }

*/


/*
        static boolean access$7502(Resource resource, boolean flag)
        {
            resource.enableAutoEventTracking_ = flag;
            return flag;
        }

*/



/*
        static LazyStringList access$7602(Resource resource, LazyStringList lazystringlist)
        {
            resource.usageContext_ = lazystringlist;
            return lazystringlist;
        }

*/


/*
        static int access$7702(Resource resource, int i)
        {
            resource.resourceFormatVersion_ = i;
            return i;
        }

*/


/*
        static int access$7802(Resource resource, int i)
        {
            resource.bitField0_ = i;
            return i;
        }

*/


        private Resource(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
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
_L30:
            if(flag) goto _L2; else goto _L1
_L1:
            int j = codedinputstream.readTag();
            j;
            JVM INSTR lookupswitch 17: default 196
        //                       0: 1278
        //                       10: 215
        //                       18: 453
        //                       26: 515
        //                       34: 558
        //                       42: 604
        //                       50: 650
        //                       58: 696
        //                       74: 742
        //                       82: 767
        //                       98: 792
        //                       106: 817
        //                       114: 843
        //                       125: 924
        //                       130: 946
        //                       136: 994
        //                       144: 1017;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, j))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            ByteString bytestring5 = codedinputstream.readBytes();
            if((i & 1) == 1)
                break MISSING_BLOCK_LABEL_243;
            key_ = new LazyStringArrayList();
            i |= 1;
            key_.add(bytestring5);
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((i & 1) == 1)
                key_ = new UnmodifiableLazyStringList(key_);
            if((i & 2) == 2)
                value_ = Collections.unmodifiableList(value_);
            if((i & 4) == 4)
                property_ = Collections.unmodifiableList(property_);
            if((i & 8) == 8)
                macro_ = Collections.unmodifiableList(macro_);
            if((i & 0x10) == 16)
                tag_ = Collections.unmodifiableList(tag_);
            if((i & 0x20) == 32)
                predicate_ = Collections.unmodifiableList(predicate_);
            if((i & 0x40) == 64)
                rule_ = Collections.unmodifiableList(rule_);
            if((i & 0x4000) == 16384)
                usageContext_ = new UnmodifiableLazyStringList(usageContext_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L28:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            if((i & 2) == 2)
                break MISSING_BLOCK_LABEL_475;
            value_ = new ArrayList();
            i |= 2;
            value_.add(codedinputstream.readMessage(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L7:
            if((i & 4) == 4)
                break MISSING_BLOCK_LABEL_537;
            property_ = new ArrayList();
            i |= 4;
            property_.add(codedinputstream.readMessage(Property.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L8:
            if((i & 8) == 8)
                break MISSING_BLOCK_LABEL_583;
            macro_ = new ArrayList();
            i |= 8;
            macro_.add(codedinputstream.readMessage(FunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L9:
            if((i & 0x10) == 16)
                break MISSING_BLOCK_LABEL_629;
            tag_ = new ArrayList();
            i |= 0x10;
            tag_.add(codedinputstream.readMessage(FunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L10:
            if((i & 0x20) == 32)
                break MISSING_BLOCK_LABEL_675;
            predicate_ = new ArrayList();
            i |= 0x20;
            predicate_.add(codedinputstream.readMessage(FunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L11:
            if((i & 0x40) == 64)
                break MISSING_BLOCK_LABEL_721;
            rule_ = new ArrayList();
            i |= 0x40;
            rule_.add(codedinputstream.readMessage(Rule.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L12:
            ByteString bytestring4 = codedinputstream.readBytes();
            bitField0_ = 1 | bitField0_;
            previewAuthCode_ = bytestring4;
            continue; /* Loop/switch isn't completed */
_L13:
            ByteString bytestring3 = codedinputstream.readBytes();
            bitField0_ = 2 | bitField0_;
            malwareScanAuthCode_ = bytestring3;
            continue; /* Loop/switch isn't completed */
_L14:
            ByteString bytestring2 = codedinputstream.readBytes();
            bitField0_ = 4 | bitField0_;
            templateVersionSet_ = bytestring2;
            continue; /* Loop/switch isn't completed */
_L15:
            ByteString bytestring1 = codedinputstream.readBytes();
            bitField0_ = 8 | bitField0_;
            version_ = bytestring1;
            continue; /* Loop/switch isn't completed */
_L16:
            int k = 0x10 & bitField0_;
            CacheOption.Builder builder = null;
            if(k != 16) goto _L22; else goto _L21
_L21:
            builder = liveJsCacheOption_.toBuilder();
_L22:
            liveJsCacheOption_ = (CacheOption)codedinputstream.readMessage(CacheOption.PARSER, extensionregistrylite);
            if(builder == null) goto _L24; else goto _L23
_L23:
            builder.mergeFrom(liveJsCacheOption_);
            liveJsCacheOption_ = builder.buildPartial();
_L24:
            bitField0_ = 0x10 | bitField0_;
            continue; /* Loop/switch isn't completed */
_L17:
            bitField0_ = 0x20 | bitField0_;
            reportingSampleRate_ = codedinputstream.readFloat();
            continue; /* Loop/switch isn't completed */
_L18:
            ByteString bytestring = codedinputstream.readBytes();
            if((i & 0x4000) == 16384)
                break MISSING_BLOCK_LABEL_980;
            usageContext_ = new LazyStringArrayList();
            i |= 0x4000;
            usageContext_.add(bytestring);
            continue; /* Loop/switch isn't completed */
_L19:
            bitField0_ = 0x80 | bitField0_;
            resourceFormatVersion_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L20:
            bitField0_ = 0x40 | bitField0_;
            enableAutoEventTracking_ = codedinputstream.readBool();
            continue; /* Loop/switch isn't completed */
_L2:
            if((i & 1) == 1)
                key_ = new UnmodifiableLazyStringList(key_);
            if((i & 2) == 2)
                value_ = Collections.unmodifiableList(value_);
            if((i & 4) == 4)
                property_ = Collections.unmodifiableList(property_);
            if((i & 8) == 8)
                macro_ = Collections.unmodifiableList(macro_);
            if((i & 0x10) == 16)
                tag_ = Collections.unmodifiableList(tag_);
            if((i & 0x20) == 32)
                predicate_ = Collections.unmodifiableList(predicate_);
            if((i & 0x40) == 64)
                rule_ = Collections.unmodifiableList(rule_);
            if((i & 0x4000) == 16384)
                usageContext_ = new UnmodifiableLazyStringList(usageContext_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L26:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L26; else goto _L25
_L25:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L28; else goto _L27
_L27:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L30; else goto _L29
_L29:
        }


        private Resource(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private Resource(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class Resource.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements ResourceOrBuilder
    {

        private static Resource.Builder create()
        {
            return new Resource.Builder();
        }

        private void ensureKeyIsMutable()
        {
            if((1 & bitField0_) != 1)
            {
                key_ = new LazyStringArrayList(key_);
                bitField0_ = 1 | bitField0_;
            }
        }

        private void ensureMacroIsMutable()
        {
            if((8 & bitField0_) != 8)
            {
                macro_ = new ArrayList(macro_);
                bitField0_ = 8 | bitField0_;
            }
        }

        private void ensurePredicateIsMutable()
        {
            if((0x20 & bitField0_) != 32)
            {
                predicate_ = new ArrayList(predicate_);
                bitField0_ = 0x20 | bitField0_;
            }
        }

        private void ensurePropertyIsMutable()
        {
            if((4 & bitField0_) != 4)
            {
                property_ = new ArrayList(property_);
                bitField0_ = 4 | bitField0_;
            }
        }

        private void ensureRuleIsMutable()
        {
            if((0x40 & bitField0_) != 64)
            {
                rule_ = new ArrayList(rule_);
                bitField0_ = 0x40 | bitField0_;
            }
        }

        private void ensureTagIsMutable()
        {
            if((0x10 & bitField0_) != 16)
            {
                tag_ = new ArrayList(tag_);
                bitField0_ = 0x10 | bitField0_;
            }
        }

        private void ensureUsageContextIsMutable()
        {
            if((0x4000 & bitField0_) != 16384)
            {
                usageContext_ = new LazyStringArrayList(usageContext_);
                bitField0_ = 0x4000 | bitField0_;
            }
        }

        private void ensureValueIsMutable()
        {
            if((2 & bitField0_) != 2)
            {
                value_ = new ArrayList(value_);
                bitField0_ = 2 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public Resource.Builder addAllKey(Iterable iterable)
        {
            ensureKeyIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, key_);
            return this;
        }

        public Resource.Builder addAllMacro(Iterable iterable)
        {
            ensureMacroIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, macro_);
            return this;
        }

        public Resource.Builder addAllPredicate(Iterable iterable)
        {
            ensurePredicateIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, predicate_);
            return this;
        }

        public Resource.Builder addAllProperty(Iterable iterable)
        {
            ensurePropertyIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, property_);
            return this;
        }

        public Resource.Builder addAllRule(Iterable iterable)
        {
            ensureRuleIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, rule_);
            return this;
        }

        public Resource.Builder addAllTag(Iterable iterable)
        {
            ensureTagIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, tag_);
            return this;
        }

        public Resource.Builder addAllUsageContext(Iterable iterable)
        {
            ensureUsageContextIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, usageContext_);
            return this;
        }

        public Resource.Builder addAllValue(Iterable iterable)
        {
            ensureValueIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, value_);
            return this;
        }

        public Resource.Builder addKey(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureKeyIsMutable();
                key_.add(s);
                return this;
            }
        }

        public Resource.Builder addKeyBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureKeyIsMutable();
                key_.add(bytestring);
                return this;
            }
        }

        public Resource.Builder addMacro(int i, FunctionCall.Builder builder)
        {
            ensureMacroIsMutable();
            macro_.add(i, builder.build());
            return this;
        }

        public Resource.Builder addMacro(int i, FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMacroIsMutable();
                macro_.add(i, functioncall);
                return this;
            }
        }

        public Resource.Builder addMacro(FunctionCall.Builder builder)
        {
            ensureMacroIsMutable();
            macro_.add(builder.build());
            return this;
        }

        public Resource.Builder addMacro(FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMacroIsMutable();
                macro_.add(functioncall);
                return this;
            }
        }

        public Resource.Builder addPredicate(int i, FunctionCall.Builder builder)
        {
            ensurePredicateIsMutable();
            predicate_.add(i, builder.build());
            return this;
        }

        public Resource.Builder addPredicate(int i, FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePredicateIsMutable();
                predicate_.add(i, functioncall);
                return this;
            }
        }

        public Resource.Builder addPredicate(FunctionCall.Builder builder)
        {
            ensurePredicateIsMutable();
            predicate_.add(builder.build());
            return this;
        }

        public Resource.Builder addPredicate(FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePredicateIsMutable();
                predicate_.add(functioncall);
                return this;
            }
        }

        public Resource.Builder addProperty(int i, Property.Builder builder)
        {
            ensurePropertyIsMutable();
            property_.add(i, builder.build());
            return this;
        }

        public Resource.Builder addProperty(int i, Property property)
        {
            if(property == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertyIsMutable();
                property_.add(i, property);
                return this;
            }
        }

        public Resource.Builder addProperty(Property.Builder builder)
        {
            ensurePropertyIsMutable();
            property_.add(builder.build());
            return this;
        }

        public Resource.Builder addProperty(Property property)
        {
            if(property == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertyIsMutable();
                property_.add(property);
                return this;
            }
        }

        public Resource.Builder addRule(int i, Rule.Builder builder)
        {
            ensureRuleIsMutable();
            rule_.add(i, builder.build());
            return this;
        }

        public Resource.Builder addRule(int i, Rule rule)
        {
            if(rule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRuleIsMutable();
                rule_.add(i, rule);
                return this;
            }
        }

        public Resource.Builder addRule(Rule.Builder builder)
        {
            ensureRuleIsMutable();
            rule_.add(builder.build());
            return this;
        }

        public Resource.Builder addRule(Rule rule)
        {
            if(rule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRuleIsMutable();
                rule_.add(rule);
                return this;
            }
        }

        public Resource.Builder addTag(int i, FunctionCall.Builder builder)
        {
            ensureTagIsMutable();
            tag_.add(i, builder.build());
            return this;
        }

        public Resource.Builder addTag(int i, FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureTagIsMutable();
                tag_.add(i, functioncall);
                return this;
            }
        }

        public Resource.Builder addTag(FunctionCall.Builder builder)
        {
            ensureTagIsMutable();
            tag_.add(builder.build());
            return this;
        }

        public Resource.Builder addTag(FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureTagIsMutable();
                tag_.add(functioncall);
                return this;
            }
        }

        public Resource.Builder addUsageContext(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureUsageContextIsMutable();
                usageContext_.add(s);
                return this;
            }
        }

        public Resource.Builder addUsageContextBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureUsageContextIsMutable();
                usageContext_.add(bytestring);
                return this;
            }
        }

        public Resource.Builder addValue(int i, com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder)
        {
            ensureValueIsMutable();
            value_.add(i, builder.build());
            return this;
        }

        public Resource.Builder addValue(int i, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureValueIsMutable();
                value_.add(i, value);
                return this;
            }
        }

        public Resource.Builder addValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder)
        {
            ensureValueIsMutable();
            value_.add(builder.build());
            return this;
        }

        public Resource.Builder addValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureValueIsMutable();
                value_.add(value);
                return this;
            }
        }

        public Resource build()
        {
            Resource resource = buildPartial();
            if(!resource.isInitialized())
                throw newUninitializedMessageException(resource);
            else
                return resource;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public Resource buildPartial()
        {
            Resource resource = new Resource(this);
            int i = bitField0_;
            if((1 & bitField0_) == 1)
            {
                key_ = new UnmodifiableLazyStringList(key_);
                bitField0_ = -2 & bitField0_;
            }
            resource.key_ = key_;
            if((2 & bitField0_) == 2)
            {
                value_ = Collections.unmodifiableList(value_);
                bitField0_ = -3 & bitField0_;
            }
            resource.value_ = value_;
            if((4 & bitField0_) == 4)
            {
                property_ = Collections.unmodifiableList(property_);
                bitField0_ = -5 & bitField0_;
            }
            resource.property_ = property_;
            if((8 & bitField0_) == 8)
            {
                macro_ = Collections.unmodifiableList(macro_);
                bitField0_ = -9 & bitField0_;
            }
            resource.macro_ = macro_;
            if((0x10 & bitField0_) == 16)
            {
                tag_ = Collections.unmodifiableList(tag_);
                bitField0_ = 0xffffffef & bitField0_;
            }
            resource.tag_ = tag_;
            if((0x20 & bitField0_) == 32)
            {
                predicate_ = Collections.unmodifiableList(predicate_);
                bitField0_ = 0xffffffdf & bitField0_;
            }
            resource.predicate_ = predicate_;
            if((0x40 & bitField0_) == 64)
            {
                rule_ = Collections.unmodifiableList(rule_);
                bitField0_ = 0xffffffbf & bitField0_;
            }
            resource.rule_ = rule_;
            int j = i & 0x80;
            int k = 0;
            if(j == 128)
                k = false | true;
            resource.previewAuthCode_ = previewAuthCode_;
            if((i & 0x100) == 256)
                k |= 2;
            resource.malwareScanAuthCode_ = malwareScanAuthCode_;
            if((i & 0x200) == 512)
                k |= 4;
            resource.templateVersionSet_ = templateVersionSet_;
            if((i & 0x400) == 1024)
                k |= 8;
            resource.version_ = version_;
            if((i & 0x800) == 2048)
                k |= 0x10;
            resource.liveJsCacheOption_ = liveJsCacheOption_;
            if((i & 0x1000) == 4096)
                k |= 0x20;
            resource.reportingSampleRate_ = reportingSampleRate_;
            if((i & 0x2000) == 8192)
                k |= 0x40;
            resource.enableAutoEventTracking_ = enableAutoEventTracking_;
            if((0x4000 & bitField0_) == 16384)
            {
                usageContext_ = new UnmodifiableLazyStringList(usageContext_);
                bitField0_ = 0xffffbfff & bitField0_;
            }
            resource.usageContext_ = usageContext_;
            if((i & 0x8000) == 32768)
                k |= 0x80;
            resource.resourceFormatVersion_ = resourceFormatVersion_;
            resource.bitField0_ = k;
            return resource;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public Resource.Builder clear()
        {
            super.clear();
            key_ = LazyStringArrayList.EMPTY;
            bitField0_ = -2 & bitField0_;
            value_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            property_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            macro_ = Collections.emptyList();
            bitField0_ = -9 & bitField0_;
            tag_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            predicate_ = Collections.emptyList();
            bitField0_ = 0xffffffdf & bitField0_;
            rule_ = Collections.emptyList();
            bitField0_ = 0xffffffbf & bitField0_;
            previewAuthCode_ = "";
            bitField0_ = 0xffffff7f & bitField0_;
            malwareScanAuthCode_ = "";
            bitField0_ = 0xfffffeff & bitField0_;
            templateVersionSet_ = "0";
            bitField0_ = 0xfffffdff & bitField0_;
            version_ = "";
            bitField0_ = 0xfffffbff & bitField0_;
            liveJsCacheOption_ = CacheOption.getDefaultInstance();
            bitField0_ = 0xfffff7ff & bitField0_;
            reportingSampleRate_ = 0.0F;
            bitField0_ = 0xffffefff & bitField0_;
            enableAutoEventTracking_ = false;
            bitField0_ = 0xffffdfff & bitField0_;
            usageContext_ = LazyStringArrayList.EMPTY;
            bitField0_ = 0xffffbfff & bitField0_;
            resourceFormatVersion_ = 0;
            bitField0_ = 0xffff7fff & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public Resource.Builder clearEnableAutoEventTracking()
        {
            bitField0_ = 0xffffdfff & bitField0_;
            enableAutoEventTracking_ = false;
            return this;
        }

        public Resource.Builder clearKey()
        {
            key_ = LazyStringArrayList.EMPTY;
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public Resource.Builder clearLiveJsCacheOption()
        {
            liveJsCacheOption_ = CacheOption.getDefaultInstance();
            bitField0_ = 0xfffff7ff & bitField0_;
            return this;
        }

        public Resource.Builder clearMacro()
        {
            macro_ = Collections.emptyList();
            bitField0_ = -9 & bitField0_;
            return this;
        }

        public Resource.Builder clearMalwareScanAuthCode()
        {
            bitField0_ = 0xfffffeff & bitField0_;
            malwareScanAuthCode_ = Resource.getDefaultInstance().getMalwareScanAuthCode();
            return this;
        }

        public Resource.Builder clearPredicate()
        {
            predicate_ = Collections.emptyList();
            bitField0_ = 0xffffffdf & bitField0_;
            return this;
        }

        public Resource.Builder clearPreviewAuthCode()
        {
            bitField0_ = 0xffffff7f & bitField0_;
            previewAuthCode_ = Resource.getDefaultInstance().getPreviewAuthCode();
            return this;
        }

        public Resource.Builder clearProperty()
        {
            property_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            return this;
        }

        public Resource.Builder clearReportingSampleRate()
        {
            bitField0_ = 0xffffefff & bitField0_;
            reportingSampleRate_ = 0.0F;
            return this;
        }

        public Resource.Builder clearResourceFormatVersion()
        {
            bitField0_ = 0xffff7fff & bitField0_;
            resourceFormatVersion_ = 0;
            return this;
        }

        public Resource.Builder clearRule()
        {
            rule_ = Collections.emptyList();
            bitField0_ = 0xffffffbf & bitField0_;
            return this;
        }

        public Resource.Builder clearTag()
        {
            tag_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            return this;
        }

        public Resource.Builder clearTemplateVersionSet()
        {
            bitField0_ = 0xfffffdff & bitField0_;
            templateVersionSet_ = Resource.getDefaultInstance().getTemplateVersionSet();
            return this;
        }

        public Resource.Builder clearUsageContext()
        {
            usageContext_ = LazyStringArrayList.EMPTY;
            bitField0_ = 0xffffbfff & bitField0_;
            return this;
        }

        public Resource.Builder clearValue()
        {
            value_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public Resource.Builder clearVersion()
        {
            bitField0_ = 0xfffffbff & bitField0_;
            version_ = Resource.getDefaultInstance().getVersion();
            return this;
        }

        public Resource.Builder clone()
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

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Resource getDefaultInstanceForType()
        {
            return Resource.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public boolean getEnableAutoEventTracking()
        {
            return enableAutoEventTracking_;
        }

        public String getKey(int i)
        {
            return (String)key_.get(i);
        }

        public ByteString getKeyBytes(int i)
        {
            return key_.getByteString(i);
        }

        public int getKeyCount()
        {
            return key_.size();
        }

        public List getKeyList()
        {
            return Collections.unmodifiableList(key_);
        }

        public CacheOption getLiveJsCacheOption()
        {
            return liveJsCacheOption_;
        }

        public FunctionCall getMacro(int i)
        {
            return (FunctionCall)macro_.get(i);
        }

        public int getMacroCount()
        {
            return macro_.size();
        }

        public List getMacroList()
        {
            return Collections.unmodifiableList(macro_);
        }

        public String getMalwareScanAuthCode()
        {
            Object obj = malwareScanAuthCode_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    malwareScanAuthCode_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getMalwareScanAuthCodeBytes()
        {
            Object obj = malwareScanAuthCode_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                malwareScanAuthCode_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public FunctionCall getPredicate(int i)
        {
            return (FunctionCall)predicate_.get(i);
        }

        public int getPredicateCount()
        {
            return predicate_.size();
        }

        public List getPredicateList()
        {
            return Collections.unmodifiableList(predicate_);
        }

        public String getPreviewAuthCode()
        {
            Object obj = previewAuthCode_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    previewAuthCode_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getPreviewAuthCodeBytes()
        {
            Object obj = previewAuthCode_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                previewAuthCode_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Property getProperty(int i)
        {
            return (Property)property_.get(i);
        }

        public int getPropertyCount()
        {
            return property_.size();
        }

        public List getPropertyList()
        {
            return Collections.unmodifiableList(property_);
        }

        public float getReportingSampleRate()
        {
            return reportingSampleRate_;
        }

        public int getResourceFormatVersion()
        {
            return resourceFormatVersion_;
        }

        public Rule getRule(int i)
        {
            return (Rule)rule_.get(i);
        }

        public int getRuleCount()
        {
            return rule_.size();
        }

        public List getRuleList()
        {
            return Collections.unmodifiableList(rule_);
        }

        public FunctionCall getTag(int i)
        {
            return (FunctionCall)tag_.get(i);
        }

        public int getTagCount()
        {
            return tag_.size();
        }

        public List getTagList()
        {
            return Collections.unmodifiableList(tag_);
        }

        public String getTemplateVersionSet()
        {
            Object obj = templateVersionSet_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    templateVersionSet_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getTemplateVersionSetBytes()
        {
            Object obj = templateVersionSet_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                templateVersionSet_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public String getUsageContext(int i)
        {
            return (String)usageContext_.get(i);
        }

        public ByteString getUsageContextBytes(int i)
        {
            return usageContext_.getByteString(i);
        }

        public int getUsageContextCount()
        {
            return usageContext_.size();
        }

        public List getUsageContextList()
        {
            return Collections.unmodifiableList(usageContext_);
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.Value getValue(int i)
        {
            return (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)value_.get(i);
        }

        public int getValueCount()
        {
            return value_.size();
        }

        public List getValueList()
        {
            return Collections.unmodifiableList(value_);
        }

        public String getVersion()
        {
            Object obj = version_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    version_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getVersionBytes()
        {
            Object obj = version_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                version_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public boolean hasEnableAutoEventTracking()
        {
            return (0x2000 & bitField0_) == 8192;
        }

        public boolean hasLiveJsCacheOption()
        {
            return (0x800 & bitField0_) == 2048;
        }

        public boolean hasMalwareScanAuthCode()
        {
            return (0x100 & bitField0_) == 256;
        }

        public boolean hasPreviewAuthCode()
        {
            return (0x80 & bitField0_) == 128;
        }

        public boolean hasReportingSampleRate()
        {
            return (0x1000 & bitField0_) == 4096;
        }

        public boolean hasResourceFormatVersion()
        {
            return (0x8000 & bitField0_) == 32768;
        }

        public boolean hasTemplateVersionSet()
        {
            return (0x200 & bitField0_) == 512;
        }

        public boolean hasVersion()
        {
            return (0x400 & bitField0_) == 1024;
        }

        public final boolean isInitialized()
        {
            int i = 0;
_L7:
            if(i >= getValueCount()) goto _L2; else goto _L1
_L1:
            if(getValue(i).isInitialized()) goto _L4; else goto _L3
_L3:
            return false;
_L4:
            i++;
            continue; /* Loop/switch isn't completed */
_L2:
            for(int j = 0; j < getPropertyCount(); j++)
                if(!getProperty(j).isInitialized())
                    continue; /* Loop/switch isn't completed */

            for(int k = 0; k < getMacroCount(); k++)
                if(!getMacro(k).isInitialized())
                    continue; /* Loop/switch isn't completed */

            for(int l = 0; l < getTagCount(); l++)
                if(!getTag(l).isInitialized())
                    continue; /* Loop/switch isn't completed */

            int i1 = 0;
label0:
            do
            {
label1:
                {
                    if(i1 >= getPredicateCount())
                        break label1;
                    if(!getPredicate(i1).isInitialized())
                        break label0;
                    i1++;
                }
            } while(true);
            if(true) goto _L3; else goto _L5
_L5:
            return true;
            if(true) goto _L7; else goto _L6
_L6:
        }

        public Resource.Builder mergeFrom(Resource resource)
        {
            if(resource == Resource.getDefaultInstance())
                return this;
            if(!resource.key_.isEmpty())
                if(key_.isEmpty())
                {
                    key_ = resource.key_;
                    bitField0_ = -2 & bitField0_;
                } else
                {
                    ensureKeyIsMutable();
                    key_.addAll(resource.key_);
                }
            if(!resource.value_.isEmpty())
                if(value_.isEmpty())
                {
                    value_ = resource.value_;
                    bitField0_ = -3 & bitField0_;
                } else
                {
                    ensureValueIsMutable();
                    value_.addAll(resource.value_);
                }
            if(!resource.property_.isEmpty())
                if(property_.isEmpty())
                {
                    property_ = resource.property_;
                    bitField0_ = -5 & bitField0_;
                } else
                {
                    ensurePropertyIsMutable();
                    property_.addAll(resource.property_);
                }
            if(!resource.macro_.isEmpty())
                if(macro_.isEmpty())
                {
                    macro_ = resource.macro_;
                    bitField0_ = -9 & bitField0_;
                } else
                {
                    ensureMacroIsMutable();
                    macro_.addAll(resource.macro_);
                }
            if(!resource.tag_.isEmpty())
                if(tag_.isEmpty())
                {
                    tag_ = resource.tag_;
                    bitField0_ = 0xffffffef & bitField0_;
                } else
                {
                    ensureTagIsMutable();
                    tag_.addAll(resource.tag_);
                }
            if(!resource.predicate_.isEmpty())
                if(predicate_.isEmpty())
                {
                    predicate_ = resource.predicate_;
                    bitField0_ = 0xffffffdf & bitField0_;
                } else
                {
                    ensurePredicateIsMutable();
                    predicate_.addAll(resource.predicate_);
                }
            if(!resource.rule_.isEmpty())
                if(rule_.isEmpty())
                {
                    rule_ = resource.rule_;
                    bitField0_ = 0xffffffbf & bitField0_;
                } else
                {
                    ensureRuleIsMutable();
                    rule_.addAll(resource.rule_);
                }
            if(resource.hasPreviewAuthCode())
            {
                bitField0_ = 0x80 | bitField0_;
                previewAuthCode_ = resource.previewAuthCode_;
            }
            if(resource.hasMalwareScanAuthCode())
            {
                bitField0_ = 0x100 | bitField0_;
                malwareScanAuthCode_ = resource.malwareScanAuthCode_;
            }
            if(resource.hasTemplateVersionSet())
            {
                bitField0_ = 0x200 | bitField0_;
                templateVersionSet_ = resource.templateVersionSet_;
            }
            if(resource.hasVersion())
            {
                bitField0_ = 0x400 | bitField0_;
                version_ = resource.version_;
            }
            if(resource.hasLiveJsCacheOption())
                mergeLiveJsCacheOption(resource.getLiveJsCacheOption());
            if(resource.hasReportingSampleRate())
                setReportingSampleRate(resource.getReportingSampleRate());
            if(resource.hasEnableAutoEventTracking())
                setEnableAutoEventTracking(resource.getEnableAutoEventTracking());
            if(!resource.usageContext_.isEmpty())
                if(usageContext_.isEmpty())
                {
                    usageContext_ = resource.usageContext_;
                    bitField0_ = 0xffffbfff & bitField0_;
                } else
                {
                    ensureUsageContextIsMutable();
                    usageContext_.addAll(resource.usageContext_);
                }
            if(resource.hasResourceFormatVersion())
                setResourceFormatVersion(resource.getResourceFormatVersion());
            setUnknownFields(getUnknownFields().concat(resource.unknownFields));
            return this;
        }

        public Resource.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            Resource resource = null;
            Resource resource1 = (Resource)Resource.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(resource1 != null)
                mergeFrom(resource1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            resource = (Resource)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(resource != null)
                mergeFrom(resource);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((Resource)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public Resource.Builder mergeLiveJsCacheOption(CacheOption cacheoption)
        {
            if((0x800 & bitField0_) == 2048 && liveJsCacheOption_ != CacheOption.getDefaultInstance())
                liveJsCacheOption_ = CacheOption.newBuilder(liveJsCacheOption_).mergeFrom(cacheoption).buildPartial();
            else
                liveJsCacheOption_ = cacheoption;
            bitField0_ = 0x800 | bitField0_;
            return this;
        }

        public Resource.Builder removeMacro(int i)
        {
            ensureMacroIsMutable();
            macro_.remove(i);
            return this;
        }

        public Resource.Builder removePredicate(int i)
        {
            ensurePredicateIsMutable();
            predicate_.remove(i);
            return this;
        }

        public Resource.Builder removeProperty(int i)
        {
            ensurePropertyIsMutable();
            property_.remove(i);
            return this;
        }

        public Resource.Builder removeRule(int i)
        {
            ensureRuleIsMutable();
            rule_.remove(i);
            return this;
        }

        public Resource.Builder removeTag(int i)
        {
            ensureTagIsMutable();
            tag_.remove(i);
            return this;
        }

        public Resource.Builder removeValue(int i)
        {
            ensureValueIsMutable();
            value_.remove(i);
            return this;
        }

        public Resource.Builder setEnableAutoEventTracking(boolean flag)
        {
            bitField0_ = 0x2000 | bitField0_;
            enableAutoEventTracking_ = flag;
            return this;
        }

        public Resource.Builder setKey(int i, String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureKeyIsMutable();
                key_.set(i, s);
                return this;
            }
        }

        public Resource.Builder setLiveJsCacheOption(CacheOption.Builder builder)
        {
            liveJsCacheOption_ = builder.build();
            bitField0_ = 0x800 | bitField0_;
            return this;
        }

        public Resource.Builder setLiveJsCacheOption(CacheOption cacheoption)
        {
            if(cacheoption == null)
            {
                throw new NullPointerException();
            } else
            {
                liveJsCacheOption_ = cacheoption;
                bitField0_ = 0x800 | bitField0_;
                return this;
            }
        }

        public Resource.Builder setMacro(int i, FunctionCall.Builder builder)
        {
            ensureMacroIsMutable();
            macro_.set(i, builder.build());
            return this;
        }

        public Resource.Builder setMacro(int i, FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMacroIsMutable();
                macro_.set(i, functioncall);
                return this;
            }
        }

        public Resource.Builder setMalwareScanAuthCode(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x100 | bitField0_;
                malwareScanAuthCode_ = s;
                return this;
            }
        }

        public Resource.Builder setMalwareScanAuthCodeBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x100 | bitField0_;
                malwareScanAuthCode_ = bytestring;
                return this;
            }
        }

        public Resource.Builder setPredicate(int i, FunctionCall.Builder builder)
        {
            ensurePredicateIsMutable();
            predicate_.set(i, builder.build());
            return this;
        }

        public Resource.Builder setPredicate(int i, FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePredicateIsMutable();
                predicate_.set(i, functioncall);
                return this;
            }
        }

        public Resource.Builder setPreviewAuthCode(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x80 | bitField0_;
                previewAuthCode_ = s;
                return this;
            }
        }

        public Resource.Builder setPreviewAuthCodeBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x80 | bitField0_;
                previewAuthCode_ = bytestring;
                return this;
            }
        }

        public Resource.Builder setProperty(int i, Property.Builder builder)
        {
            ensurePropertyIsMutable();
            property_.set(i, builder.build());
            return this;
        }

        public Resource.Builder setProperty(int i, Property property)
        {
            if(property == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertyIsMutable();
                property_.set(i, property);
                return this;
            }
        }

        public Resource.Builder setReportingSampleRate(float f)
        {
            bitField0_ = 0x1000 | bitField0_;
            reportingSampleRate_ = f;
            return this;
        }

        public Resource.Builder setResourceFormatVersion(int i)
        {
            bitField0_ = 0x8000 | bitField0_;
            resourceFormatVersion_ = i;
            return this;
        }

        public Resource.Builder setRule(int i, Rule.Builder builder)
        {
            ensureRuleIsMutable();
            rule_.set(i, builder.build());
            return this;
        }

        public Resource.Builder setRule(int i, Rule rule)
        {
            if(rule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRuleIsMutable();
                rule_.set(i, rule);
                return this;
            }
        }

        public Resource.Builder setTag(int i, FunctionCall.Builder builder)
        {
            ensureTagIsMutable();
            tag_.set(i, builder.build());
            return this;
        }

        public Resource.Builder setTag(int i, FunctionCall functioncall)
        {
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureTagIsMutable();
                tag_.set(i, functioncall);
                return this;
            }
        }

        public Resource.Builder setTemplateVersionSet(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x200 | bitField0_;
                templateVersionSet_ = s;
                return this;
            }
        }

        public Resource.Builder setTemplateVersionSetBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x200 | bitField0_;
                templateVersionSet_ = bytestring;
                return this;
            }
        }

        public Resource.Builder setUsageContext(int i, String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureUsageContextIsMutable();
                usageContext_.set(i, s);
                return this;
            }
        }

        public Resource.Builder setValue(int i, com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder)
        {
            ensureValueIsMutable();
            value_.set(i, builder.build());
            return this;
        }

        public Resource.Builder setValue(int i, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureValueIsMutable();
                value_.set(i, value);
                return this;
            }
        }

        public Resource.Builder setVersion(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x400 | bitField0_;
                version_ = s;
                return this;
            }
        }

        public Resource.Builder setVersionBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x400 | bitField0_;
                version_ = bytestring;
                return this;
            }
        }

        private int bitField0_;
        private boolean enableAutoEventTracking_;
        private LazyStringList key_;
        private CacheOption liveJsCacheOption_;
        private List macro_;
        private Object malwareScanAuthCode_;
        private List predicate_;
        private Object previewAuthCode_;
        private List property_;
        private float reportingSampleRate_;
        private int resourceFormatVersion_;
        private List rule_;
        private List tag_;
        private Object templateVersionSet_;
        private LazyStringList usageContext_;
        private List value_;
        private Object version_;


        private Resource.Builder()
        {
            key_ = LazyStringArrayList.EMPTY;
            value_ = Collections.emptyList();
            property_ = Collections.emptyList();
            macro_ = Collections.emptyList();
            tag_ = Collections.emptyList();
            predicate_ = Collections.emptyList();
            rule_ = Collections.emptyList();
            previewAuthCode_ = "";
            malwareScanAuthCode_ = "";
            templateVersionSet_ = "0";
            version_ = "";
            liveJsCacheOption_ = CacheOption.getDefaultInstance();
            usageContext_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }
    }

    public static interface ResourceOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract boolean getEnableAutoEventTracking();

        public abstract String getKey(int i);

        public abstract ByteString getKeyBytes(int i);

        public abstract int getKeyCount();

        public abstract List getKeyList();

        public abstract CacheOption getLiveJsCacheOption();

        public abstract FunctionCall getMacro(int i);

        public abstract int getMacroCount();

        public abstract List getMacroList();

        public abstract String getMalwareScanAuthCode();

        public abstract ByteString getMalwareScanAuthCodeBytes();

        public abstract FunctionCall getPredicate(int i);

        public abstract int getPredicateCount();

        public abstract List getPredicateList();

        public abstract String getPreviewAuthCode();

        public abstract ByteString getPreviewAuthCodeBytes();

        public abstract Property getProperty(int i);

        public abstract int getPropertyCount();

        public abstract List getPropertyList();

        public abstract float getReportingSampleRate();

        public abstract int getResourceFormatVersion();

        public abstract Rule getRule(int i);

        public abstract int getRuleCount();

        public abstract List getRuleList();

        public abstract FunctionCall getTag(int i);

        public abstract int getTagCount();

        public abstract List getTagList();

        public abstract String getTemplateVersionSet();

        public abstract ByteString getTemplateVersionSetBytes();

        public abstract String getUsageContext(int i);

        public abstract ByteString getUsageContextBytes(int i);

        public abstract int getUsageContextCount();

        public abstract List getUsageContextList();

        public abstract com.google.analytics.midtier.proto.containertag.TypeSystem.Value getValue(int i);

        public abstract int getValueCount();

        public abstract List getValueList();

        public abstract String getVersion();

        public abstract ByteString getVersionBytes();

        public abstract boolean hasEnableAutoEventTracking();

        public abstract boolean hasLiveJsCacheOption();

        public abstract boolean hasMalwareScanAuthCode();

        public abstract boolean hasPreviewAuthCode();

        public abstract boolean hasReportingSampleRate();

        public abstract boolean hasResourceFormatVersion();

        public abstract boolean hasTemplateVersionSet();

        public abstract boolean hasVersion();
    }

    public static final class ResourceState extends Enum
        implements com.google.tagmanager.protobuf.Internal.EnumLite
    {

        public static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalGetValueMap()
        {
            return internalValueMap;
        }

        public static ResourceState valueOf(int i)
        {
            switch(i)
            {
            default:
                return null;

            case 1: // '\001'
                return PREVIEW;

            case 2: // '\002'
                return LIVE;
            }
        }

        public static ResourceState valueOf(String s)
        {
            return (ResourceState)Enum.valueOf(com/google/analytics/containertag/proto/Serving$ResourceState, s);
        }

        public static ResourceState[] values()
        {
            return (ResourceState[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        private static final ResourceState $VALUES[];
        public static final ResourceState LIVE;
        public static final int LIVE_VALUE = 2;
        public static final ResourceState PREVIEW;
        public static final int PREVIEW_VALUE = 1;
        private static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalValueMap = new com.google.tagmanager.protobuf.Internal.EnumLiteMap() {

            public ResourceState findValueByNumber(int i)
            {
                return ResourceState.valueOf(i);
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
            PREVIEW = new ResourceState("PREVIEW", 0, 0, 1);
            LIVE = new ResourceState("LIVE", 1, 1, 2);
            ResourceState aresourcestate[] = new ResourceState[2];
            aresourcestate[0] = PREVIEW;
            aresourcestate[1] = LIVE;
            $VALUES = aresourcestate;
        }

        private ResourceState(String s, int i, int j, int k)
        {
            super(s, i);
            value = k;
        }
    }

    public static final class ResourceType extends Enum
        implements com.google.tagmanager.protobuf.Internal.EnumLite
    {

        public static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalGetValueMap()
        {
            return internalValueMap;
        }

        public static ResourceType valueOf(int i)
        {
            switch(i)
            {
            default:
                return null;

            case 1: // '\001'
                return JS_RESOURCE;

            case 2: // '\002'
                return NS_RESOURCE;

            case 3: // '\003'
                return PIXEL_COLLECTION;

            case 4: // '\004'
                return SET_COOKIE;

            case 5: // '\005'
                return GET_COOKIE;

            case 6: // '\006'
                return CLEAR_CACHE;

            case 7: // '\007'
                return RAW_PROTO;
            }
        }

        public static ResourceType valueOf(String s)
        {
            return (ResourceType)Enum.valueOf(com/google/analytics/containertag/proto/Serving$ResourceType, s);
        }

        public static ResourceType[] values()
        {
            return (ResourceType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        private static final ResourceType $VALUES[];
        public static final ResourceType CLEAR_CACHE;
        public static final int CLEAR_CACHE_VALUE = 6;
        public static final ResourceType GET_COOKIE;
        public static final int GET_COOKIE_VALUE = 5;
        public static final ResourceType JS_RESOURCE;
        public static final int JS_RESOURCE_VALUE = 1;
        public static final ResourceType NS_RESOURCE;
        public static final int NS_RESOURCE_VALUE = 2;
        public static final ResourceType PIXEL_COLLECTION;
        public static final int PIXEL_COLLECTION_VALUE = 3;
        public static final ResourceType RAW_PROTO;
        public static final int RAW_PROTO_VALUE = 7;
        public static final ResourceType SET_COOKIE;
        public static final int SET_COOKIE_VALUE = 4;
        private static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalValueMap = new com.google.tagmanager.protobuf.Internal.EnumLiteMap() {

            public ResourceType findValueByNumber(int i)
            {
                return ResourceType.valueOf(i);
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
            JS_RESOURCE = new ResourceType("JS_RESOURCE", 0, 0, 1);
            NS_RESOURCE = new ResourceType("NS_RESOURCE", 1, 1, 2);
            PIXEL_COLLECTION = new ResourceType("PIXEL_COLLECTION", 2, 2, 3);
            SET_COOKIE = new ResourceType("SET_COOKIE", 3, 3, 4);
            GET_COOKIE = new ResourceType("GET_COOKIE", 4, 4, 5);
            CLEAR_CACHE = new ResourceType("CLEAR_CACHE", 5, 5, 6);
            RAW_PROTO = new ResourceType("RAW_PROTO", 6, 6, 7);
            ResourceType aresourcetype[] = new ResourceType[7];
            aresourcetype[0] = JS_RESOURCE;
            aresourcetype[1] = NS_RESOURCE;
            aresourcetype[2] = PIXEL_COLLECTION;
            aresourcetype[3] = SET_COOKIE;
            aresourcetype[4] = GET_COOKIE;
            aresourcetype[5] = CLEAR_CACHE;
            aresourcetype[6] = RAW_PROTO;
            $VALUES = aresourcetype;
        }

        private ResourceType(String s, int i, int j, int k)
        {
            super(s, i);
            value = k;
        }
    }

    public static final class Rule extends GeneratedMessageLite
        implements RuleOrBuilder
    {

        public static Rule getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            positivePredicate_ = Collections.emptyList();
            negativePredicate_ = Collections.emptyList();
            addTag_ = Collections.emptyList();
            removeTag_ = Collections.emptyList();
            addTagRuleName_ = Collections.emptyList();
            removeTagRuleName_ = Collections.emptyList();
            addMacro_ = Collections.emptyList();
            removeMacro_ = Collections.emptyList();
            addMacroRuleName_ = Collections.emptyList();
            removeMacroRuleName_ = Collections.emptyList();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(Rule rule)
        {
            return newBuilder().mergeFrom(rule);
        }

        public static Rule parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (Rule)PARSER.parseDelimitedFrom(inputstream);
        }

        public static Rule parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Rule)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static Rule parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (Rule)PARSER.parseFrom(bytestring);
        }

        public static Rule parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Rule)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static Rule parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (Rule)PARSER.parseFrom(codedinputstream);
        }

        public static Rule parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Rule)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static Rule parseFrom(InputStream inputstream)
            throws IOException
        {
            return (Rule)PARSER.parseFrom(inputstream);
        }

        public static Rule parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (Rule)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static Rule parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (Rule)PARSER.parseFrom(abyte0);
        }

        public static Rule parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (Rule)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof Rule))
                return super.equals(obj);
            Rule rule = (Rule)obj;
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
            if(true && getPositivePredicateList().equals(rule.getPositivePredicateList()))
                flag = true;
            else
                flag = false;
            if(flag && getNegativePredicateList().equals(rule.getNegativePredicateList()))
                flag1 = true;
            else
                flag1 = false;
            if(flag1 && getAddTagList().equals(rule.getAddTagList()))
                flag2 = true;
            else
                flag2 = false;
            if(flag2 && getRemoveTagList().equals(rule.getRemoveTagList()))
                flag3 = true;
            else
                flag3 = false;
            if(flag3 && getAddTagRuleNameList().equals(rule.getAddTagRuleNameList()))
                flag4 = true;
            else
                flag4 = false;
            if(flag4 && getRemoveTagRuleNameList().equals(rule.getRemoveTagRuleNameList()))
                flag5 = true;
            else
                flag5 = false;
            if(flag5 && getAddMacroList().equals(rule.getAddMacroList()))
                flag6 = true;
            else
                flag6 = false;
            if(flag6 && getRemoveMacroList().equals(rule.getRemoveMacroList()))
                flag7 = true;
            else
                flag7 = false;
            if(flag7 && getAddMacroRuleNameList().equals(rule.getAddMacroRuleNameList()))
                flag8 = true;
            else
                flag8 = false;
            if(flag8 && getRemoveMacroRuleNameList().equals(rule.getRemoveMacroRuleNameList()))
                flag9 = true;
            else
                flag9 = false;
            return flag9;
        }

        public int getAddMacro(int i)
        {
            return ((Integer)addMacro_.get(i)).intValue();
        }

        public int getAddMacroCount()
        {
            return addMacro_.size();
        }

        public List getAddMacroList()
        {
            return addMacro_;
        }

        public int getAddMacroRuleName(int i)
        {
            return ((Integer)addMacroRuleName_.get(i)).intValue();
        }

        public int getAddMacroRuleNameCount()
        {
            return addMacroRuleName_.size();
        }

        public List getAddMacroRuleNameList()
        {
            return addMacroRuleName_;
        }

        public int getAddTag(int i)
        {
            return ((Integer)addTag_.get(i)).intValue();
        }

        public int getAddTagCount()
        {
            return addTag_.size();
        }

        public List getAddTagList()
        {
            return addTag_;
        }

        public int getAddTagRuleName(int i)
        {
            return ((Integer)addTagRuleName_.get(i)).intValue();
        }

        public int getAddTagRuleNameCount()
        {
            return addTagRuleName_.size();
        }

        public List getAddTagRuleNameList()
        {
            return addTagRuleName_;
        }

        public Rule getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getNegativePredicate(int i)
        {
            return ((Integer)negativePredicate_.get(i)).intValue();
        }

        public int getNegativePredicateCount()
        {
            return negativePredicate_.size();
        }

        public List getNegativePredicateList()
        {
            return negativePredicate_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getPositivePredicate(int i)
        {
            return ((Integer)positivePredicate_.get(i)).intValue();
        }

        public int getPositivePredicateCount()
        {
            return positivePredicate_.size();
        }

        public List getPositivePredicateList()
        {
            return positivePredicate_;
        }

        public int getRemoveMacro(int i)
        {
            return ((Integer)removeMacro_.get(i)).intValue();
        }

        public int getRemoveMacroCount()
        {
            return removeMacro_.size();
        }

        public List getRemoveMacroList()
        {
            return removeMacro_;
        }

        public int getRemoveMacroRuleName(int i)
        {
            return ((Integer)removeMacroRuleName_.get(i)).intValue();
        }

        public int getRemoveMacroRuleNameCount()
        {
            return removeMacroRuleName_.size();
        }

        public List getRemoveMacroRuleNameList()
        {
            return removeMacroRuleName_;
        }

        public int getRemoveTag(int i)
        {
            return ((Integer)removeTag_.get(i)).intValue();
        }

        public int getRemoveTagCount()
        {
            return removeTag_.size();
        }

        public List getRemoveTagList()
        {
            return removeTag_;
        }

        public int getRemoveTagRuleName(int i)
        {
            return ((Integer)removeTagRuleName_.get(i)).intValue();
        }

        public int getRemoveTagRuleNameCount()
        {
            return removeTagRuleName_.size();
        }

        public List getRemoveTagRuleNameList()
        {
            return removeTagRuleName_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 0;
            for(int k = 0; k < positivePredicate_.size(); k++)
                j += CodedOutputStream.computeInt32SizeNoTag(((Integer)positivePredicate_.get(k)).intValue());

            int l = 0 + j + 1 * getPositivePredicateList().size();
            int i1 = 0;
            for(int j1 = 0; j1 < negativePredicate_.size(); j1++)
                i1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)negativePredicate_.get(j1)).intValue());

            int k1 = l + i1 + 1 * getNegativePredicateList().size();
            int l1 = 0;
            for(int i2 = 0; i2 < addTag_.size(); i2++)
                l1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)addTag_.get(i2)).intValue());

            int j2 = k1 + l1 + 1 * getAddTagList().size();
            int k2 = 0;
            for(int l2 = 0; l2 < removeTag_.size(); l2++)
                k2 += CodedOutputStream.computeInt32SizeNoTag(((Integer)removeTag_.get(l2)).intValue());

            int i3 = j2 + k2 + 1 * getRemoveTagList().size();
            int j3 = 0;
            for(int k3 = 0; k3 < addTagRuleName_.size(); k3++)
                j3 += CodedOutputStream.computeInt32SizeNoTag(((Integer)addTagRuleName_.get(k3)).intValue());

            int l3 = i3 + j3 + 1 * getAddTagRuleNameList().size();
            int i4 = 0;
            for(int j4 = 0; j4 < removeTagRuleName_.size(); j4++)
                i4 += CodedOutputStream.computeInt32SizeNoTag(((Integer)removeTagRuleName_.get(j4)).intValue());

            int k4 = l3 + i4 + 1 * getRemoveTagRuleNameList().size();
            int l4 = 0;
            for(int i5 = 0; i5 < addMacro_.size(); i5++)
                l4 += CodedOutputStream.computeInt32SizeNoTag(((Integer)addMacro_.get(i5)).intValue());

            int j5 = k4 + l4 + 1 * getAddMacroList().size();
            int k5 = 0;
            for(int l5 = 0; l5 < removeMacro_.size(); l5++)
                k5 += CodedOutputStream.computeInt32SizeNoTag(((Integer)removeMacro_.get(l5)).intValue());

            int i6 = j5 + k5 + 1 * getRemoveMacroList().size();
            int j6 = 0;
            for(int k6 = 0; k6 < addMacroRuleName_.size(); k6++)
                j6 += CodedOutputStream.computeInt32SizeNoTag(((Integer)addMacroRuleName_.get(k6)).intValue());

            int l6 = i6 + j6 + 1 * getAddMacroRuleNameList().size();
            int i7 = 0;
            for(int j7 = 0; j7 < removeMacroRuleName_.size(); j7++)
                i7 += CodedOutputStream.computeInt32SizeNoTag(((Integer)removeMacroRuleName_.get(j7)).intValue());

            int k7 = l6 + i7 + 1 * getRemoveMacroRuleNameList().size() + unknownFields.size();
            memoizedSerializedSize = k7;
            return k7;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Serving$Rule.hashCode();
            if(getPositivePredicateCount() > 0)
                i = 53 * (1 + i * 37) + getPositivePredicateList().hashCode();
            if(getNegativePredicateCount() > 0)
                i = 53 * (2 + i * 37) + getNegativePredicateList().hashCode();
            if(getAddTagCount() > 0)
                i = 53 * (3 + i * 37) + getAddTagList().hashCode();
            if(getRemoveTagCount() > 0)
                i = 53 * (4 + i * 37) + getRemoveTagList().hashCode();
            if(getAddTagRuleNameCount() > 0)
                i = 53 * (5 + i * 37) + getAddTagRuleNameList().hashCode();
            if(getRemoveTagRuleNameCount() > 0)
                i = 53 * (6 + i * 37) + getRemoveTagRuleNameList().hashCode();
            if(getAddMacroCount() > 0)
                i = 53 * (7 + i * 37) + getAddMacroList().hashCode();
            if(getRemoveMacroCount() > 0)
                i = 53 * (8 + i * 37) + getRemoveMacroList().hashCode();
            if(getAddMacroRuleNameCount() > 0)
                i = 53 * (9 + i * 37) + getAddMacroRuleNameList().hashCode();
            if(getRemoveMacroRuleNameCount() > 0)
                i = 53 * (10 + i * 37) + getRemoveMacroRuleNameList().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableServing$Rule");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
            {
                return byte0 == 1;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
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
            for(int i = 0; i < positivePredicate_.size(); i++)
                codedoutputstream.writeInt32(1, ((Integer)positivePredicate_.get(i)).intValue());

            for(int j = 0; j < negativePredicate_.size(); j++)
                codedoutputstream.writeInt32(2, ((Integer)negativePredicate_.get(j)).intValue());

            for(int k = 0; k < addTag_.size(); k++)
                codedoutputstream.writeInt32(3, ((Integer)addTag_.get(k)).intValue());

            for(int l = 0; l < removeTag_.size(); l++)
                codedoutputstream.writeInt32(4, ((Integer)removeTag_.get(l)).intValue());

            for(int i1 = 0; i1 < addTagRuleName_.size(); i1++)
                codedoutputstream.writeInt32(5, ((Integer)addTagRuleName_.get(i1)).intValue());

            for(int j1 = 0; j1 < removeTagRuleName_.size(); j1++)
                codedoutputstream.writeInt32(6, ((Integer)removeTagRuleName_.get(j1)).intValue());

            for(int k1 = 0; k1 < addMacro_.size(); k1++)
                codedoutputstream.writeInt32(7, ((Integer)addMacro_.get(k1)).intValue());

            for(int l1 = 0; l1 < removeMacro_.size(); l1++)
                codedoutputstream.writeInt32(8, ((Integer)removeMacro_.get(l1)).intValue());

            for(int i2 = 0; i2 < addMacroRuleName_.size(); i2++)
                codedoutputstream.writeInt32(9, ((Integer)addMacroRuleName_.get(i2)).intValue());

            for(int j2 = 0; j2 < removeMacroRuleName_.size(); j2++)
                codedoutputstream.writeInt32(10, ((Integer)removeMacroRuleName_.get(j2)).intValue());

            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int ADD_MACRO_FIELD_NUMBER = 7;
        public static final int ADD_MACRO_RULE_NAME_FIELD_NUMBER = 9;
        public static final int ADD_TAG_FIELD_NUMBER = 3;
        public static final int ADD_TAG_RULE_NAME_FIELD_NUMBER = 5;
        public static final int NEGATIVE_PREDICATE_FIELD_NUMBER = 2;
        public static Parser PARSER = new AbstractParser() {

            public Rule parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new Rule(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int POSITIVE_PREDICATE_FIELD_NUMBER = 1;
        public static final int REMOVE_MACRO_FIELD_NUMBER = 8;
        public static final int REMOVE_MACRO_RULE_NAME_FIELD_NUMBER = 10;
        public static final int REMOVE_TAG_FIELD_NUMBER = 4;
        public static final int REMOVE_TAG_RULE_NAME_FIELD_NUMBER = 6;
        private static final Rule defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private List addMacroRuleName_;
        private List addMacro_;
        private List addTagRuleName_;
        private List addTag_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List negativePredicate_;
        private List positivePredicate_;
        private List removeMacroRuleName_;
        private List removeMacro_;
        private List removeTagRuleName_;
        private List removeTag_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new Rule(true);
            defaultInstance.initFields();
        }



/*
        static List access$4002(Rule rule, List list)
        {
            rule.positivePredicate_ = list;
            return list;
        }

*/



/*
        static List access$4102(Rule rule, List list)
        {
            rule.negativePredicate_ = list;
            return list;
        }

*/



/*
        static List access$4202(Rule rule, List list)
        {
            rule.addTag_ = list;
            return list;
        }

*/



/*
        static List access$4302(Rule rule, List list)
        {
            rule.removeTag_ = list;
            return list;
        }

*/



/*
        static List access$4402(Rule rule, List list)
        {
            rule.addTagRuleName_ = list;
            return list;
        }

*/



/*
        static List access$4502(Rule rule, List list)
        {
            rule.removeTagRuleName_ = list;
            return list;
        }

*/



/*
        static List access$4602(Rule rule, List list)
        {
            rule.addMacro_ = list;
            return list;
        }

*/



/*
        static List access$4702(Rule rule, List list)
        {
            rule.removeMacro_ = list;
            return list;
        }

*/



/*
        static List access$4802(Rule rule, List list)
        {
            rule.addMacroRuleName_ = list;
            return list;
        }

*/



/*
        static List access$4902(Rule rule, List list)
        {
            rule.removeMacroRuleName_ = list;
            return list;
        }

*/


        private Rule(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
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
_L31:
            if(flag) goto _L2; else goto _L1
_L1:
            int j = codedinputstream.readTag();
            j;
            JVM INSTR lookupswitch 21: default 228
        //                       0: 2003
        //                       8: 247
        //                       10: 521
        //                       16: 615
        //                       18: 657
        //                       24: 732
        //                       26: 774
        //                       32: 849
        //                       34: 894
        //                       40: 972
        //                       42: 1017
        //                       48: 1095
        //                       50: 1140
        //                       56: 1218
        //                       58: 1263
        //                       64: 1341
        //                       66: 1389
        //                       72: 1470
        //                       74: 1518
        //                       80: 1599
        //                       82: 1647;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, j))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if((i & 1) == 1)
                break MISSING_BLOCK_LABEL_269;
            positivePredicate_ = new ArrayList();
            i |= 1;
            positivePredicate_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((i & 1) == 1)
                positivePredicate_ = Collections.unmodifiableList(positivePredicate_);
            if((i & 2) == 2)
                negativePredicate_ = Collections.unmodifiableList(negativePredicate_);
            if((i & 4) == 4)
                addTag_ = Collections.unmodifiableList(addTag_);
            if((i & 8) == 8)
                removeTag_ = Collections.unmodifiableList(removeTag_);
            if((i & 0x10) == 16)
                addTagRuleName_ = Collections.unmodifiableList(addTagRuleName_);
            if((i & 0x20) == 32)
                removeTagRuleName_ = Collections.unmodifiableList(removeTagRuleName_);
            if((i & 0x40) == 64)
                addMacro_ = Collections.unmodifiableList(addMacro_);
            if((i & 0x80) == 128)
                removeMacro_ = Collections.unmodifiableList(removeMacro_);
            if((i & 0x100) == 256)
                addMacroRuleName_ = Collections.unmodifiableList(addMacroRuleName_);
            if((i & 0x200) == 512)
                removeMacroRuleName_ = Collections.unmodifiableList(removeMacroRuleName_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L29:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            int l2 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 1) == 1)
                break MISSING_BLOCK_LABEL_560;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_560;
            positivePredicate_ = new ArrayList();
            i |= 1;
            for(; codedinputstream.getBytesUntilLimit() > 0; positivePredicate_.add(Integer.valueOf(codedinputstream.readInt32())));
              goto _L25
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L25:
            codedinputstream.popLimit(l2);
            continue; /* Loop/switch isn't completed */
_L7:
            if((i & 2) == 2)
                break MISSING_BLOCK_LABEL_637;
            negativePredicate_ = new ArrayList();
            i |= 2;
            negativePredicate_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L8:
            int k2 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 2) == 2)
                break MISSING_BLOCK_LABEL_696;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_696;
            negativePredicate_ = new ArrayList();
            i |= 2;
            for(; codedinputstream.getBytesUntilLimit() > 0; negativePredicate_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(k2);
            continue; /* Loop/switch isn't completed */
_L9:
            if((i & 4) == 4)
                break MISSING_BLOCK_LABEL_754;
            addTag_ = new ArrayList();
            i |= 4;
            addTag_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L10:
            int j2 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 4) == 4)
                break MISSING_BLOCK_LABEL_813;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_813;
            addTag_ = new ArrayList();
            i |= 4;
            for(; codedinputstream.getBytesUntilLimit() > 0; addTag_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(j2);
            continue; /* Loop/switch isn't completed */
_L11:
            if((i & 8) == 8)
                break MISSING_BLOCK_LABEL_874;
            removeTag_ = new ArrayList();
            i |= 8;
            removeTag_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L12:
            int i2 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 8) == 8)
                break MISSING_BLOCK_LABEL_936;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_936;
            removeTag_ = new ArrayList();
            i |= 8;
            for(; codedinputstream.getBytesUntilLimit() > 0; removeTag_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(i2);
            continue; /* Loop/switch isn't completed */
_L13:
            if((i & 0x10) == 16)
                break MISSING_BLOCK_LABEL_997;
            addTagRuleName_ = new ArrayList();
            i |= 0x10;
            addTagRuleName_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L14:
            int l1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 0x10) == 16)
                break MISSING_BLOCK_LABEL_1059;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_1059;
            addTagRuleName_ = new ArrayList();
            i |= 0x10;
            for(; codedinputstream.getBytesUntilLimit() > 0; addTagRuleName_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(l1);
            continue; /* Loop/switch isn't completed */
_L15:
            if((i & 0x20) == 32)
                break MISSING_BLOCK_LABEL_1120;
            removeTagRuleName_ = new ArrayList();
            i |= 0x20;
            removeTagRuleName_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L16:
            int k1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 0x20) == 32)
                break MISSING_BLOCK_LABEL_1182;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_1182;
            removeTagRuleName_ = new ArrayList();
            i |= 0x20;
            for(; codedinputstream.getBytesUntilLimit() > 0; removeTagRuleName_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(k1);
            continue; /* Loop/switch isn't completed */
_L17:
            if((i & 0x40) == 64)
                break MISSING_BLOCK_LABEL_1243;
            addMacro_ = new ArrayList();
            i |= 0x40;
            addMacro_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L18:
            int j1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 0x40) == 64)
                break MISSING_BLOCK_LABEL_1305;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_1305;
            addMacro_ = new ArrayList();
            i |= 0x40;
            for(; codedinputstream.getBytesUntilLimit() > 0; addMacro_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(j1);
            continue; /* Loop/switch isn't completed */
_L19:
            if((i & 0x80) == 128)
                break MISSING_BLOCK_LABEL_1369;
            removeMacro_ = new ArrayList();
            i |= 0x80;
            removeMacro_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L20:
            int i1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 0x80) == 128)
                break MISSING_BLOCK_LABEL_1434;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_1434;
            removeMacro_ = new ArrayList();
            i |= 0x80;
            for(; codedinputstream.getBytesUntilLimit() > 0; removeMacro_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(i1);
            continue; /* Loop/switch isn't completed */
_L21:
            if((i & 0x100) == 256)
                break MISSING_BLOCK_LABEL_1498;
            addMacroRuleName_ = new ArrayList();
            i |= 0x100;
            addMacroRuleName_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L22:
            int l = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 0x100) == 256)
                break MISSING_BLOCK_LABEL_1563;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_1563;
            addMacroRuleName_ = new ArrayList();
            i |= 0x100;
            for(; codedinputstream.getBytesUntilLimit() > 0; addMacroRuleName_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(l);
            continue; /* Loop/switch isn't completed */
_L23:
            if((i & 0x200) == 512)
                break MISSING_BLOCK_LABEL_1627;
            removeMacroRuleName_ = new ArrayList();
            i |= 0x200;
            removeMacroRuleName_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L24:
            int k = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 0x200) == 512)
                break MISSING_BLOCK_LABEL_1692;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_1692;
            removeMacroRuleName_ = new ArrayList();
            i |= 0x200;
            for(; codedinputstream.getBytesUntilLimit() > 0; removeMacroRuleName_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(k);
            continue; /* Loop/switch isn't completed */
_L2:
            if((i & 1) == 1)
                positivePredicate_ = Collections.unmodifiableList(positivePredicate_);
            if((i & 2) == 2)
                negativePredicate_ = Collections.unmodifiableList(negativePredicate_);
            if((i & 4) == 4)
                addTag_ = Collections.unmodifiableList(addTag_);
            if((i & 8) == 8)
                removeTag_ = Collections.unmodifiableList(removeTag_);
            if((i & 0x10) == 16)
                addTagRuleName_ = Collections.unmodifiableList(addTagRuleName_);
            if((i & 0x20) == 32)
                removeTagRuleName_ = Collections.unmodifiableList(removeTagRuleName_);
            if((i & 0x40) == 64)
                addMacro_ = Collections.unmodifiableList(addMacro_);
            if((i & 0x80) == 128)
                removeMacro_ = Collections.unmodifiableList(removeMacro_);
            if((i & 0x100) == 256)
                addMacroRuleName_ = Collections.unmodifiableList(addMacroRuleName_);
            if((i & 0x200) == 512)
                removeMacroRuleName_ = Collections.unmodifiableList(removeMacroRuleName_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L27:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L27; else goto _L26
_L26:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L29; else goto _L28
_L28:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L31; else goto _L30
_L30:
        }


        private Rule(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private Rule(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class Rule.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements RuleOrBuilder
    {

        private static Rule.Builder create()
        {
            return new Rule.Builder();
        }

        private void ensureAddMacroIsMutable()
        {
            if((0x40 & bitField0_) != 64)
            {
                addMacro_ = new ArrayList(addMacro_);
                bitField0_ = 0x40 | bitField0_;
            }
        }

        private void ensureAddMacroRuleNameIsMutable()
        {
            if((0x100 & bitField0_) != 256)
            {
                addMacroRuleName_ = new ArrayList(addMacroRuleName_);
                bitField0_ = 0x100 | bitField0_;
            }
        }

        private void ensureAddTagIsMutable()
        {
            if((4 & bitField0_) != 4)
            {
                addTag_ = new ArrayList(addTag_);
                bitField0_ = 4 | bitField0_;
            }
        }

        private void ensureAddTagRuleNameIsMutable()
        {
            if((0x10 & bitField0_) != 16)
            {
                addTagRuleName_ = new ArrayList(addTagRuleName_);
                bitField0_ = 0x10 | bitField0_;
            }
        }

        private void ensureNegativePredicateIsMutable()
        {
            if((2 & bitField0_) != 2)
            {
                negativePredicate_ = new ArrayList(negativePredicate_);
                bitField0_ = 2 | bitField0_;
            }
        }

        private void ensurePositivePredicateIsMutable()
        {
            if((1 & bitField0_) != 1)
            {
                positivePredicate_ = new ArrayList(positivePredicate_);
                bitField0_ = 1 | bitField0_;
            }
        }

        private void ensureRemoveMacroIsMutable()
        {
            if((0x80 & bitField0_) != 128)
            {
                removeMacro_ = new ArrayList(removeMacro_);
                bitField0_ = 0x80 | bitField0_;
            }
        }

        private void ensureRemoveMacroRuleNameIsMutable()
        {
            if((0x200 & bitField0_) != 512)
            {
                removeMacroRuleName_ = new ArrayList(removeMacroRuleName_);
                bitField0_ = 0x200 | bitField0_;
            }
        }

        private void ensureRemoveTagIsMutable()
        {
            if((8 & bitField0_) != 8)
            {
                removeTag_ = new ArrayList(removeTag_);
                bitField0_ = 8 | bitField0_;
            }
        }

        private void ensureRemoveTagRuleNameIsMutable()
        {
            if((0x20 & bitField0_) != 32)
            {
                removeTagRuleName_ = new ArrayList(removeTagRuleName_);
                bitField0_ = 0x20 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public Rule.Builder addAddMacro(int i)
        {
            ensureAddMacroIsMutable();
            addMacro_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addAddMacroRuleName(int i)
        {
            ensureAddMacroRuleNameIsMutable();
            addMacroRuleName_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addAddTag(int i)
        {
            ensureAddTagIsMutable();
            addTag_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addAddTagRuleName(int i)
        {
            ensureAddTagRuleNameIsMutable();
            addTagRuleName_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addAllAddMacro(Iterable iterable)
        {
            ensureAddMacroIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, addMacro_);
            return this;
        }

        public Rule.Builder addAllAddMacroRuleName(Iterable iterable)
        {
            ensureAddMacroRuleNameIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, addMacroRuleName_);
            return this;
        }

        public Rule.Builder addAllAddTag(Iterable iterable)
        {
            ensureAddTagIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, addTag_);
            return this;
        }

        public Rule.Builder addAllAddTagRuleName(Iterable iterable)
        {
            ensureAddTagRuleNameIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, addTagRuleName_);
            return this;
        }

        public Rule.Builder addAllNegativePredicate(Iterable iterable)
        {
            ensureNegativePredicateIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, negativePredicate_);
            return this;
        }

        public Rule.Builder addAllPositivePredicate(Iterable iterable)
        {
            ensurePositivePredicateIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, positivePredicate_);
            return this;
        }

        public Rule.Builder addAllRemoveMacro(Iterable iterable)
        {
            ensureRemoveMacroIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, removeMacro_);
            return this;
        }

        public Rule.Builder addAllRemoveMacroRuleName(Iterable iterable)
        {
            ensureRemoveMacroRuleNameIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, removeMacroRuleName_);
            return this;
        }

        public Rule.Builder addAllRemoveTag(Iterable iterable)
        {
            ensureRemoveTagIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, removeTag_);
            return this;
        }

        public Rule.Builder addAllRemoveTagRuleName(Iterable iterable)
        {
            ensureRemoveTagRuleNameIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, removeTagRuleName_);
            return this;
        }

        public Rule.Builder addNegativePredicate(int i)
        {
            ensureNegativePredicateIsMutable();
            negativePredicate_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addPositivePredicate(int i)
        {
            ensurePositivePredicateIsMutable();
            positivePredicate_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addRemoveMacro(int i)
        {
            ensureRemoveMacroIsMutable();
            removeMacro_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addRemoveMacroRuleName(int i)
        {
            ensureRemoveMacroRuleNameIsMutable();
            removeMacroRuleName_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addRemoveTag(int i)
        {
            ensureRemoveTagIsMutable();
            removeTag_.add(Integer.valueOf(i));
            return this;
        }

        public Rule.Builder addRemoveTagRuleName(int i)
        {
            ensureRemoveTagRuleNameIsMutable();
            removeTagRuleName_.add(Integer.valueOf(i));
            return this;
        }

        public Rule build()
        {
            Rule rule = buildPartial();
            if(!rule.isInitialized())
                throw newUninitializedMessageException(rule);
            else
                return rule;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public Rule buildPartial()
        {
            Rule rule = new Rule(this);
            int _tmp = bitField0_;
            if((1 & bitField0_) == 1)
            {
                positivePredicate_ = Collections.unmodifiableList(positivePredicate_);
                bitField0_ = -2 & bitField0_;
            }
            rule.positivePredicate_ = positivePredicate_;
            if((2 & bitField0_) == 2)
            {
                negativePredicate_ = Collections.unmodifiableList(negativePredicate_);
                bitField0_ = -3 & bitField0_;
            }
            rule.negativePredicate_ = negativePredicate_;
            if((4 & bitField0_) == 4)
            {
                addTag_ = Collections.unmodifiableList(addTag_);
                bitField0_ = -5 & bitField0_;
            }
            rule.addTag_ = addTag_;
            if((8 & bitField0_) == 8)
            {
                removeTag_ = Collections.unmodifiableList(removeTag_);
                bitField0_ = -9 & bitField0_;
            }
            rule.removeTag_ = removeTag_;
            if((0x10 & bitField0_) == 16)
            {
                addTagRuleName_ = Collections.unmodifiableList(addTagRuleName_);
                bitField0_ = 0xffffffef & bitField0_;
            }
            rule.addTagRuleName_ = addTagRuleName_;
            if((0x20 & bitField0_) == 32)
            {
                removeTagRuleName_ = Collections.unmodifiableList(removeTagRuleName_);
                bitField0_ = 0xffffffdf & bitField0_;
            }
            rule.removeTagRuleName_ = removeTagRuleName_;
            if((0x40 & bitField0_) == 64)
            {
                addMacro_ = Collections.unmodifiableList(addMacro_);
                bitField0_ = 0xffffffbf & bitField0_;
            }
            rule.addMacro_ = addMacro_;
            if((0x80 & bitField0_) == 128)
            {
                removeMacro_ = Collections.unmodifiableList(removeMacro_);
                bitField0_ = 0xffffff7f & bitField0_;
            }
            rule.removeMacro_ = removeMacro_;
            if((0x100 & bitField0_) == 256)
            {
                addMacroRuleName_ = Collections.unmodifiableList(addMacroRuleName_);
                bitField0_ = 0xfffffeff & bitField0_;
            }
            rule.addMacroRuleName_ = addMacroRuleName_;
            if((0x200 & bitField0_) == 512)
            {
                removeMacroRuleName_ = Collections.unmodifiableList(removeMacroRuleName_);
                bitField0_ = 0xfffffdff & bitField0_;
            }
            rule.removeMacroRuleName_ = removeMacroRuleName_;
            return rule;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public Rule.Builder clear()
        {
            super.clear();
            positivePredicate_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            negativePredicate_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            addTag_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            removeTag_ = Collections.emptyList();
            bitField0_ = -9 & bitField0_;
            addTagRuleName_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            removeTagRuleName_ = Collections.emptyList();
            bitField0_ = 0xffffffdf & bitField0_;
            addMacro_ = Collections.emptyList();
            bitField0_ = 0xffffffbf & bitField0_;
            removeMacro_ = Collections.emptyList();
            bitField0_ = 0xffffff7f & bitField0_;
            addMacroRuleName_ = Collections.emptyList();
            bitField0_ = 0xfffffeff & bitField0_;
            removeMacroRuleName_ = Collections.emptyList();
            bitField0_ = 0xfffffdff & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public Rule.Builder clearAddMacro()
        {
            addMacro_ = Collections.emptyList();
            bitField0_ = 0xffffffbf & bitField0_;
            return this;
        }

        public Rule.Builder clearAddMacroRuleName()
        {
            addMacroRuleName_ = Collections.emptyList();
            bitField0_ = 0xfffffeff & bitField0_;
            return this;
        }

        public Rule.Builder clearAddTag()
        {
            addTag_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            return this;
        }

        public Rule.Builder clearAddTagRuleName()
        {
            addTagRuleName_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            return this;
        }

        public Rule.Builder clearNegativePredicate()
        {
            negativePredicate_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public Rule.Builder clearPositivePredicate()
        {
            positivePredicate_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public Rule.Builder clearRemoveMacro()
        {
            removeMacro_ = Collections.emptyList();
            bitField0_ = 0xffffff7f & bitField0_;
            return this;
        }

        public Rule.Builder clearRemoveMacroRuleName()
        {
            removeMacroRuleName_ = Collections.emptyList();
            bitField0_ = 0xfffffdff & bitField0_;
            return this;
        }

        public Rule.Builder clearRemoveTag()
        {
            removeTag_ = Collections.emptyList();
            bitField0_ = -9 & bitField0_;
            return this;
        }

        public Rule.Builder clearRemoveTagRuleName()
        {
            removeTagRuleName_ = Collections.emptyList();
            bitField0_ = 0xffffffdf & bitField0_;
            return this;
        }

        public Rule.Builder clone()
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

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public int getAddMacro(int i)
        {
            return ((Integer)addMacro_.get(i)).intValue();
        }

        public int getAddMacroCount()
        {
            return addMacro_.size();
        }

        public List getAddMacroList()
        {
            return Collections.unmodifiableList(addMacro_);
        }

        public int getAddMacroRuleName(int i)
        {
            return ((Integer)addMacroRuleName_.get(i)).intValue();
        }

        public int getAddMacroRuleNameCount()
        {
            return addMacroRuleName_.size();
        }

        public List getAddMacroRuleNameList()
        {
            return Collections.unmodifiableList(addMacroRuleName_);
        }

        public int getAddTag(int i)
        {
            return ((Integer)addTag_.get(i)).intValue();
        }

        public int getAddTagCount()
        {
            return addTag_.size();
        }

        public List getAddTagList()
        {
            return Collections.unmodifiableList(addTag_);
        }

        public int getAddTagRuleName(int i)
        {
            return ((Integer)addTagRuleName_.get(i)).intValue();
        }

        public int getAddTagRuleNameCount()
        {
            return addTagRuleName_.size();
        }

        public List getAddTagRuleNameList()
        {
            return Collections.unmodifiableList(addTagRuleName_);
        }

        public Rule getDefaultInstanceForType()
        {
            return Rule.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getNegativePredicate(int i)
        {
            return ((Integer)negativePredicate_.get(i)).intValue();
        }

        public int getNegativePredicateCount()
        {
            return negativePredicate_.size();
        }

        public List getNegativePredicateList()
        {
            return Collections.unmodifiableList(negativePredicate_);
        }

        public int getPositivePredicate(int i)
        {
            return ((Integer)positivePredicate_.get(i)).intValue();
        }

        public int getPositivePredicateCount()
        {
            return positivePredicate_.size();
        }

        public List getPositivePredicateList()
        {
            return Collections.unmodifiableList(positivePredicate_);
        }

        public int getRemoveMacro(int i)
        {
            return ((Integer)removeMacro_.get(i)).intValue();
        }

        public int getRemoveMacroCount()
        {
            return removeMacro_.size();
        }

        public List getRemoveMacroList()
        {
            return Collections.unmodifiableList(removeMacro_);
        }

        public int getRemoveMacroRuleName(int i)
        {
            return ((Integer)removeMacroRuleName_.get(i)).intValue();
        }

        public int getRemoveMacroRuleNameCount()
        {
            return removeMacroRuleName_.size();
        }

        public List getRemoveMacroRuleNameList()
        {
            return Collections.unmodifiableList(removeMacroRuleName_);
        }

        public int getRemoveTag(int i)
        {
            return ((Integer)removeTag_.get(i)).intValue();
        }

        public int getRemoveTagCount()
        {
            return removeTag_.size();
        }

        public List getRemoveTagList()
        {
            return Collections.unmodifiableList(removeTag_);
        }

        public int getRemoveTagRuleName(int i)
        {
            return ((Integer)removeTagRuleName_.get(i)).intValue();
        }

        public int getRemoveTagRuleNameCount()
        {
            return removeTagRuleName_.size();
        }

        public List getRemoveTagRuleNameList()
        {
            return Collections.unmodifiableList(removeTagRuleName_);
        }

        public final boolean isInitialized()
        {
            return true;
        }

        public Rule.Builder mergeFrom(Rule rule)
        {
            if(rule == Rule.getDefaultInstance())
                return this;
            if(!rule.positivePredicate_.isEmpty())
                if(positivePredicate_.isEmpty())
                {
                    positivePredicate_ = rule.positivePredicate_;
                    bitField0_ = -2 & bitField0_;
                } else
                {
                    ensurePositivePredicateIsMutable();
                    positivePredicate_.addAll(rule.positivePredicate_);
                }
            if(!rule.negativePredicate_.isEmpty())
                if(negativePredicate_.isEmpty())
                {
                    negativePredicate_ = rule.negativePredicate_;
                    bitField0_ = -3 & bitField0_;
                } else
                {
                    ensureNegativePredicateIsMutable();
                    negativePredicate_.addAll(rule.negativePredicate_);
                }
            if(!rule.addTag_.isEmpty())
                if(addTag_.isEmpty())
                {
                    addTag_ = rule.addTag_;
                    bitField0_ = -5 & bitField0_;
                } else
                {
                    ensureAddTagIsMutable();
                    addTag_.addAll(rule.addTag_);
                }
            if(!rule.removeTag_.isEmpty())
                if(removeTag_.isEmpty())
                {
                    removeTag_ = rule.removeTag_;
                    bitField0_ = -9 & bitField0_;
                } else
                {
                    ensureRemoveTagIsMutable();
                    removeTag_.addAll(rule.removeTag_);
                }
            if(!rule.addTagRuleName_.isEmpty())
                if(addTagRuleName_.isEmpty())
                {
                    addTagRuleName_ = rule.addTagRuleName_;
                    bitField0_ = 0xffffffef & bitField0_;
                } else
                {
                    ensureAddTagRuleNameIsMutable();
                    addTagRuleName_.addAll(rule.addTagRuleName_);
                }
            if(!rule.removeTagRuleName_.isEmpty())
                if(removeTagRuleName_.isEmpty())
                {
                    removeTagRuleName_ = rule.removeTagRuleName_;
                    bitField0_ = 0xffffffdf & bitField0_;
                } else
                {
                    ensureRemoveTagRuleNameIsMutable();
                    removeTagRuleName_.addAll(rule.removeTagRuleName_);
                }
            if(!rule.addMacro_.isEmpty())
                if(addMacro_.isEmpty())
                {
                    addMacro_ = rule.addMacro_;
                    bitField0_ = 0xffffffbf & bitField0_;
                } else
                {
                    ensureAddMacroIsMutable();
                    addMacro_.addAll(rule.addMacro_);
                }
            if(!rule.removeMacro_.isEmpty())
                if(removeMacro_.isEmpty())
                {
                    removeMacro_ = rule.removeMacro_;
                    bitField0_ = 0xffffff7f & bitField0_;
                } else
                {
                    ensureRemoveMacroIsMutable();
                    removeMacro_.addAll(rule.removeMacro_);
                }
            if(!rule.addMacroRuleName_.isEmpty())
                if(addMacroRuleName_.isEmpty())
                {
                    addMacroRuleName_ = rule.addMacroRuleName_;
                    bitField0_ = 0xfffffeff & bitField0_;
                } else
                {
                    ensureAddMacroRuleNameIsMutable();
                    addMacroRuleName_.addAll(rule.addMacroRuleName_);
                }
            if(!rule.removeMacroRuleName_.isEmpty())
                if(removeMacroRuleName_.isEmpty())
                {
                    removeMacroRuleName_ = rule.removeMacroRuleName_;
                    bitField0_ = 0xfffffdff & bitField0_;
                } else
                {
                    ensureRemoveMacroRuleNameIsMutable();
                    removeMacroRuleName_.addAll(rule.removeMacroRuleName_);
                }
            setUnknownFields(getUnknownFields().concat(rule.unknownFields));
            return this;
        }

        public Rule.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            Rule rule = null;
            Rule rule1 = (Rule)Rule.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(rule1 != null)
                mergeFrom(rule1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            rule = (Rule)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(rule != null)
                mergeFrom(rule);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((Rule)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public Rule.Builder setAddMacro(int i, int j)
        {
            ensureAddMacroIsMutable();
            addMacro_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setAddMacroRuleName(int i, int j)
        {
            ensureAddMacroRuleNameIsMutable();
            addMacroRuleName_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setAddTag(int i, int j)
        {
            ensureAddTagIsMutable();
            addTag_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setAddTagRuleName(int i, int j)
        {
            ensureAddTagRuleNameIsMutable();
            addTagRuleName_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setNegativePredicate(int i, int j)
        {
            ensureNegativePredicateIsMutable();
            negativePredicate_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setPositivePredicate(int i, int j)
        {
            ensurePositivePredicateIsMutable();
            positivePredicate_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setRemoveMacro(int i, int j)
        {
            ensureRemoveMacroIsMutable();
            removeMacro_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setRemoveMacroRuleName(int i, int j)
        {
            ensureRemoveMacroRuleNameIsMutable();
            removeMacroRuleName_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setRemoveTag(int i, int j)
        {
            ensureRemoveTagIsMutable();
            removeTag_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule.Builder setRemoveTagRuleName(int i, int j)
        {
            ensureRemoveTagRuleNameIsMutable();
            removeTagRuleName_.set(i, Integer.valueOf(j));
            return this;
        }

        private List addMacroRuleName_;
        private List addMacro_;
        private List addTagRuleName_;
        private List addTag_;
        private int bitField0_;
        private List negativePredicate_;
        private List positivePredicate_;
        private List removeMacroRuleName_;
        private List removeMacro_;
        private List removeTagRuleName_;
        private List removeTag_;


        private Rule.Builder()
        {
            positivePredicate_ = Collections.emptyList();
            negativePredicate_ = Collections.emptyList();
            addTag_ = Collections.emptyList();
            removeTag_ = Collections.emptyList();
            addTagRuleName_ = Collections.emptyList();
            removeTagRuleName_ = Collections.emptyList();
            addMacro_ = Collections.emptyList();
            removeMacro_ = Collections.emptyList();
            addMacroRuleName_ = Collections.emptyList();
            removeMacroRuleName_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }
    }

    public static interface RuleOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract int getAddMacro(int i);

        public abstract int getAddMacroCount();

        public abstract List getAddMacroList();

        public abstract int getAddMacroRuleName(int i);

        public abstract int getAddMacroRuleNameCount();

        public abstract List getAddMacroRuleNameList();

        public abstract int getAddTag(int i);

        public abstract int getAddTagCount();

        public abstract List getAddTagList();

        public abstract int getAddTagRuleName(int i);

        public abstract int getAddTagRuleNameCount();

        public abstract List getAddTagRuleNameList();

        public abstract int getNegativePredicate(int i);

        public abstract int getNegativePredicateCount();

        public abstract List getNegativePredicateList();

        public abstract int getPositivePredicate(int i);

        public abstract int getPositivePredicateCount();

        public abstract List getPositivePredicateList();

        public abstract int getRemoveMacro(int i);

        public abstract int getRemoveMacroCount();

        public abstract List getRemoveMacroList();

        public abstract int getRemoveMacroRuleName(int i);

        public abstract int getRemoveMacroRuleNameCount();

        public abstract List getRemoveMacroRuleNameList();

        public abstract int getRemoveTag(int i);

        public abstract int getRemoveTagCount();

        public abstract List getRemoveTagList();

        public abstract int getRemoveTagRuleName(int i);

        public abstract int getRemoveTagRuleNameCount();

        public abstract List getRemoveTagRuleNameList();
    }

    public static final class ServingValue extends GeneratedMessageLite
        implements ServingValueOrBuilder
    {

        public static ServingValue getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            listItem_ = Collections.emptyList();
            mapKey_ = Collections.emptyList();
            mapValue_ = Collections.emptyList();
            macroReference_ = 0;
            templateToken_ = Collections.emptyList();
            macroNameReference_ = 0;
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(ServingValue servingvalue)
        {
            return newBuilder().mergeFrom(servingvalue);
        }

        public static ServingValue parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (ServingValue)PARSER.parseDelimitedFrom(inputstream);
        }

        public static ServingValue parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ServingValue)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static ServingValue parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (ServingValue)PARSER.parseFrom(bytestring);
        }

        public static ServingValue parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (ServingValue)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static ServingValue parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (ServingValue)PARSER.parseFrom(codedinputstream);
        }

        public static ServingValue parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ServingValue)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static ServingValue parseFrom(InputStream inputstream)
            throws IOException
        {
            return (ServingValue)PARSER.parseFrom(inputstream);
        }

        public static ServingValue parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ServingValue)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static ServingValue parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (ServingValue)PARSER.parseFrom(abyte0);
        }

        public static ServingValue parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (ServingValue)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof ServingValue))
                return super.equals(obj);
            ServingValue servingvalue = (ServingValue)obj;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            if(true && getListItemList().equals(servingvalue.getListItemList()))
                flag = true;
            else
                flag = false;
            if(flag && getMapKeyList().equals(servingvalue.getMapKeyList()))
                flag1 = true;
            else
                flag1 = false;
            if(flag1 && getMapValueList().equals(servingvalue.getMapValueList()))
                flag2 = true;
            else
                flag2 = false;
            if(flag2 && hasMacroReference() == servingvalue.hasMacroReference())
                flag3 = true;
            else
                flag3 = false;
            if(hasMacroReference())
                if(flag3 && getMacroReference() == servingvalue.getMacroReference())
                    flag3 = true;
                else
                    flag3 = false;
            if(flag3 && getTemplateTokenList().equals(servingvalue.getTemplateTokenList()))
                flag4 = true;
            else
                flag4 = false;
            if(flag4 && hasMacroNameReference() == servingvalue.hasMacroNameReference())
                flag5 = true;
            else
                flag5 = false;
            if(hasMacroNameReference())
                if(flag5 && getMacroNameReference() == servingvalue.getMacroNameReference())
                    flag5 = true;
                else
                    flag5 = false;
            return flag5;
        }

        public ServingValue getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getListItem(int i)
        {
            return ((Integer)listItem_.get(i)).intValue();
        }

        public int getListItemCount()
        {
            return listItem_.size();
        }

        public List getListItemList()
        {
            return listItem_;
        }

        public int getMacroNameReference()
        {
            return macroNameReference_;
        }

        public int getMacroReference()
        {
            return macroReference_;
        }

        public int getMapKey(int i)
        {
            return ((Integer)mapKey_.get(i)).intValue();
        }

        public int getMapKeyCount()
        {
            return mapKey_.size();
        }

        public List getMapKeyList()
        {
            return mapKey_;
        }

        public int getMapValue(int i)
        {
            return ((Integer)mapValue_.get(i)).intValue();
        }

        public int getMapValueCount()
        {
            return mapValue_.size();
        }

        public List getMapValueList()
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
            int j = 0;
            for(int k = 0; k < listItem_.size(); k++)
                j += CodedOutputStream.computeInt32SizeNoTag(((Integer)listItem_.get(k)).intValue());

            int l = 0 + j + 1 * getListItemList().size();
            int i1 = 0;
            for(int j1 = 0; j1 < mapKey_.size(); j1++)
                i1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)mapKey_.get(j1)).intValue());

            int k1 = l + i1 + 1 * getMapKeyList().size();
            int l1 = 0;
            for(int i2 = 0; i2 < mapValue_.size(); i2++)
                l1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)mapValue_.get(i2)).intValue());

            int j2 = k1 + l1 + 1 * getMapValueList().size();
            if((1 & bitField0_) == 1)
                j2 += CodedOutputStream.computeInt32Size(4, macroReference_);
            int k2 = 0;
            for(int l2 = 0; l2 < templateToken_.size(); l2++)
                k2 += CodedOutputStream.computeInt32SizeNoTag(((Integer)templateToken_.get(l2)).intValue());

            int i3 = j2 + k2 + 1 * getTemplateTokenList().size();
            if((2 & bitField0_) == 2)
                i3 += CodedOutputStream.computeInt32Size(6, macroNameReference_);
            int j3 = i3 + unknownFields.size();
            memoizedSerializedSize = j3;
            return j3;
        }

        public int getTemplateToken(int i)
        {
            return ((Integer)templateToken_.get(i)).intValue();
        }

        public int getTemplateTokenCount()
        {
            return templateToken_.size();
        }

        public List getTemplateTokenList()
        {
            return templateToken_;
        }

        public boolean hasMacroNameReference()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasMacroReference()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Serving$ServingValue.hashCode();
            if(getListItemCount() > 0)
                i = 53 * (1 + i * 37) + getListItemList().hashCode();
            if(getMapKeyCount() > 0)
                i = 53 * (2 + i * 37) + getMapKeyList().hashCode();
            if(getMapValueCount() > 0)
                i = 53 * (3 + i * 37) + getMapValueList().hashCode();
            if(hasMacroReference())
                i = 53 * (4 + i * 37) + getMacroReference();
            if(getTemplateTokenCount() > 0)
                i = 53 * (5 + i * 37) + getTemplateTokenList().hashCode();
            if(hasMacroNameReference())
                i = 53 * (6 + i * 37) + getMacroNameReference();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableServing$ServingValue");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
            {
                return byte0 == 1;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
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
            for(int i = 0; i < listItem_.size(); i++)
                codedoutputstream.writeInt32(1, ((Integer)listItem_.get(i)).intValue());

            for(int j = 0; j < mapKey_.size(); j++)
                codedoutputstream.writeInt32(2, ((Integer)mapKey_.get(j)).intValue());

            for(int k = 0; k < mapValue_.size(); k++)
                codedoutputstream.writeInt32(3, ((Integer)mapValue_.get(k)).intValue());

            if((1 & bitField0_) == 1)
                codedoutputstream.writeInt32(4, macroReference_);
            for(int l = 0; l < templateToken_.size(); l++)
                codedoutputstream.writeInt32(5, ((Integer)templateToken_.get(l)).intValue());

            if((2 & bitField0_) == 2)
                codedoutputstream.writeInt32(6, macroNameReference_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int EXT_FIELD_NUMBER = 101;
        public static final int LIST_ITEM_FIELD_NUMBER = 1;
        public static final int MACRO_NAME_REFERENCE_FIELD_NUMBER = 6;
        public static final int MACRO_REFERENCE_FIELD_NUMBER = 4;
        public static final int MAP_KEY_FIELD_NUMBER = 2;
        public static final int MAP_VALUE_FIELD_NUMBER = 3;
        public static Parser PARSER = new AbstractParser() {

            public ServingValue parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new ServingValue(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int TEMPLATE_TOKEN_FIELD_NUMBER = 5;
        private static final ServingValue defaultInstance;
        public static final com.google.tagmanager.protobuf.GeneratedMessageLite.GeneratedExtension ext;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private List listItem_;
        private int macroNameReference_;
        private int macroReference_;
        private List mapKey_;
        private List mapValue_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List templateToken_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new ServingValue(true);
            defaultInstance.initFields();
            ext = GeneratedMessageLite.newSingularGeneratedExtension(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 101, com.google.tagmanager.protobuf.WireFormat.FieldType.MESSAGE, com/google/analytics/containertag/proto/Serving$ServingValue);
        }



/*
        static List access$1202(ServingValue servingvalue, List list)
        {
            servingvalue.listItem_ = list;
            return list;
        }

*/



/*
        static List access$1302(ServingValue servingvalue, List list)
        {
            servingvalue.mapKey_ = list;
            return list;
        }

*/



/*
        static List access$1402(ServingValue servingvalue, List list)
        {
            servingvalue.mapValue_ = list;
            return list;
        }

*/


/*
        static int access$1502(ServingValue servingvalue, int i)
        {
            servingvalue.macroReference_ = i;
            return i;
        }

*/



/*
        static List access$1602(ServingValue servingvalue, List list)
        {
            servingvalue.templateToken_ = list;
            return list;
        }

*/


/*
        static int access$1702(ServingValue servingvalue, int i)
        {
            servingvalue.macroNameReference_ = i;
            return i;
        }

*/


/*
        static int access$1802(ServingValue servingvalue, int i)
        {
            servingvalue.bitField0_ = i;
            return i;
        }

*/


        private ServingValue(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
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
_L21:
            if(flag) goto _L2; else goto _L1
_L1:
            int j = codedinputstream.readTag();
            j;
            JVM INSTR lookupswitch 11: default 148
        //                       0: 957
        //                       8: 167
        //                       10: 315
        //                       16: 409
        //                       18: 451
        //                       24: 526
        //                       26: 568
        //                       32: 643
        //                       40: 664
        //                       42: 709
        //                       48: 787;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, j))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if((i & 1) == 1)
                break MISSING_BLOCK_LABEL_189;
            listItem_ = new ArrayList();
            i |= 1;
            listItem_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((i & 1) == 1)
                listItem_ = Collections.unmodifiableList(listItem_);
            if((i & 2) == 2)
                mapKey_ = Collections.unmodifiableList(mapKey_);
            if((i & 4) == 4)
                mapValue_ = Collections.unmodifiableList(mapValue_);
            if((i & 0x10) == 16)
                templateToken_ = Collections.unmodifiableList(templateToken_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L19:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            int j1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 1) == 1)
                break MISSING_BLOCK_LABEL_354;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_354;
            listItem_ = new ArrayList();
            i |= 1;
            for(; codedinputstream.getBytesUntilLimit() > 0; listItem_.add(Integer.valueOf(codedinputstream.readInt32())));
              goto _L15
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L15:
            codedinputstream.popLimit(j1);
            continue; /* Loop/switch isn't completed */
_L7:
            if((i & 2) == 2)
                break MISSING_BLOCK_LABEL_431;
            mapKey_ = new ArrayList();
            i |= 2;
            mapKey_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L8:
            int i1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 2) == 2)
                break MISSING_BLOCK_LABEL_490;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_490;
            mapKey_ = new ArrayList();
            i |= 2;
            for(; codedinputstream.getBytesUntilLimit() > 0; mapKey_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(i1);
            continue; /* Loop/switch isn't completed */
_L9:
            if((i & 4) == 4)
                break MISSING_BLOCK_LABEL_548;
            mapValue_ = new ArrayList();
            i |= 4;
            mapValue_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L10:
            int l = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 4) == 4)
                break MISSING_BLOCK_LABEL_607;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_607;
            mapValue_ = new ArrayList();
            i |= 4;
            for(; codedinputstream.getBytesUntilLimit() > 0; mapValue_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(l);
            continue; /* Loop/switch isn't completed */
_L11:
            bitField0_ = 1 | bitField0_;
            macroReference_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L12:
            if((i & 0x10) == 16)
                break MISSING_BLOCK_LABEL_689;
            templateToken_ = new ArrayList();
            i |= 0x10;
            templateToken_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L13:
            int k = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if((i & 0x10) == 16)
                break MISSING_BLOCK_LABEL_751;
            if(codedinputstream.getBytesUntilLimit() <= 0)
                break MISSING_BLOCK_LABEL_751;
            templateToken_ = new ArrayList();
            i |= 0x10;
            for(; codedinputstream.getBytesUntilLimit() > 0; templateToken_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(k);
            continue; /* Loop/switch isn't completed */
_L14:
            bitField0_ = 2 | bitField0_;
            macroNameReference_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L2:
            if((i & 1) == 1)
                listItem_ = Collections.unmodifiableList(listItem_);
            if((i & 2) == 2)
                mapKey_ = Collections.unmodifiableList(mapKey_);
            if((i & 4) == 4)
                mapValue_ = Collections.unmodifiableList(mapValue_);
            if((i & 0x10) == 16)
                templateToken_ = Collections.unmodifiableList(templateToken_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L17:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L17; else goto _L16
_L16:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L19; else goto _L18
_L18:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L21; else goto _L20
_L20:
        }


        private ServingValue(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private ServingValue(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class ServingValue.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements ServingValueOrBuilder
    {

        private static ServingValue.Builder create()
        {
            return new ServingValue.Builder();
        }

        private void ensureListItemIsMutable()
        {
            if((1 & bitField0_) != 1)
            {
                listItem_ = new ArrayList(listItem_);
                bitField0_ = 1 | bitField0_;
            }
        }

        private void ensureMapKeyIsMutable()
        {
            if((2 & bitField0_) != 2)
            {
                mapKey_ = new ArrayList(mapKey_);
                bitField0_ = 2 | bitField0_;
            }
        }

        private void ensureMapValueIsMutable()
        {
            if((4 & bitField0_) != 4)
            {
                mapValue_ = new ArrayList(mapValue_);
                bitField0_ = 4 | bitField0_;
            }
        }

        private void ensureTemplateTokenIsMutable()
        {
            if((0x10 & bitField0_) != 16)
            {
                templateToken_ = new ArrayList(templateToken_);
                bitField0_ = 0x10 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public ServingValue.Builder addAllListItem(Iterable iterable)
        {
            ensureListItemIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, listItem_);
            return this;
        }

        public ServingValue.Builder addAllMapKey(Iterable iterable)
        {
            ensureMapKeyIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, mapKey_);
            return this;
        }

        public ServingValue.Builder addAllMapValue(Iterable iterable)
        {
            ensureMapValueIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, mapValue_);
            return this;
        }

        public ServingValue.Builder addAllTemplateToken(Iterable iterable)
        {
            ensureTemplateTokenIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, templateToken_);
            return this;
        }

        public ServingValue.Builder addListItem(int i)
        {
            ensureListItemIsMutable();
            listItem_.add(Integer.valueOf(i));
            return this;
        }

        public ServingValue.Builder addMapKey(int i)
        {
            ensureMapKeyIsMutable();
            mapKey_.add(Integer.valueOf(i));
            return this;
        }

        public ServingValue.Builder addMapValue(int i)
        {
            ensureMapValueIsMutable();
            mapValue_.add(Integer.valueOf(i));
            return this;
        }

        public ServingValue.Builder addTemplateToken(int i)
        {
            ensureTemplateTokenIsMutable();
            templateToken_.add(Integer.valueOf(i));
            return this;
        }

        public ServingValue build()
        {
            ServingValue servingvalue = buildPartial();
            if(!servingvalue.isInitialized())
                throw newUninitializedMessageException(servingvalue);
            else
                return servingvalue;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public ServingValue buildPartial()
        {
            ServingValue servingvalue = new ServingValue(this);
            int i = bitField0_;
            if((1 & bitField0_) == 1)
            {
                listItem_ = Collections.unmodifiableList(listItem_);
                bitField0_ = -2 & bitField0_;
            }
            servingvalue.listItem_ = listItem_;
            if((2 & bitField0_) == 2)
            {
                mapKey_ = Collections.unmodifiableList(mapKey_);
                bitField0_ = -3 & bitField0_;
            }
            servingvalue.mapKey_ = mapKey_;
            if((4 & bitField0_) == 4)
            {
                mapValue_ = Collections.unmodifiableList(mapValue_);
                bitField0_ = -5 & bitField0_;
            }
            servingvalue.mapValue_ = mapValue_;
            int j = i & 8;
            int k = 0;
            if(j == 8)
                k = false | true;
            servingvalue.macroReference_ = macroReference_;
            if((0x10 & bitField0_) == 16)
            {
                templateToken_ = Collections.unmodifiableList(templateToken_);
                bitField0_ = 0xffffffef & bitField0_;
            }
            servingvalue.templateToken_ = templateToken_;
            if((i & 0x20) == 32)
                k |= 2;
            servingvalue.macroNameReference_ = macroNameReference_;
            servingvalue.bitField0_ = k;
            return servingvalue;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public ServingValue.Builder clear()
        {
            super.clear();
            listItem_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            mapKey_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            mapValue_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            macroReference_ = 0;
            bitField0_ = -9 & bitField0_;
            templateToken_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            macroNameReference_ = 0;
            bitField0_ = 0xffffffdf & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public ServingValue.Builder clearListItem()
        {
            listItem_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public ServingValue.Builder clearMacroNameReference()
        {
            bitField0_ = 0xffffffdf & bitField0_;
            macroNameReference_ = 0;
            return this;
        }

        public ServingValue.Builder clearMacroReference()
        {
            bitField0_ = -9 & bitField0_;
            macroReference_ = 0;
            return this;
        }

        public ServingValue.Builder clearMapKey()
        {
            mapKey_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public ServingValue.Builder clearMapValue()
        {
            mapValue_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            return this;
        }

        public ServingValue.Builder clearTemplateToken()
        {
            templateToken_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            return this;
        }

        public ServingValue.Builder clone()
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

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public ServingValue getDefaultInstanceForType()
        {
            return ServingValue.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public int getListItem(int i)
        {
            return ((Integer)listItem_.get(i)).intValue();
        }

        public int getListItemCount()
        {
            return listItem_.size();
        }

        public List getListItemList()
        {
            return Collections.unmodifiableList(listItem_);
        }

        public int getMacroNameReference()
        {
            return macroNameReference_;
        }

        public int getMacroReference()
        {
            return macroReference_;
        }

        public int getMapKey(int i)
        {
            return ((Integer)mapKey_.get(i)).intValue();
        }

        public int getMapKeyCount()
        {
            return mapKey_.size();
        }

        public List getMapKeyList()
        {
            return Collections.unmodifiableList(mapKey_);
        }

        public int getMapValue(int i)
        {
            return ((Integer)mapValue_.get(i)).intValue();
        }

        public int getMapValueCount()
        {
            return mapValue_.size();
        }

        public List getMapValueList()
        {
            return Collections.unmodifiableList(mapValue_);
        }

        public int getTemplateToken(int i)
        {
            return ((Integer)templateToken_.get(i)).intValue();
        }

        public int getTemplateTokenCount()
        {
            return templateToken_.size();
        }

        public List getTemplateTokenList()
        {
            return Collections.unmodifiableList(templateToken_);
        }

        public boolean hasMacroNameReference()
        {
            return (0x20 & bitField0_) == 32;
        }

        public boolean hasMacroReference()
        {
            return (8 & bitField0_) == 8;
        }

        public final boolean isInitialized()
        {
            return true;
        }

        public ServingValue.Builder mergeFrom(ServingValue servingvalue)
        {
            if(servingvalue == ServingValue.getDefaultInstance())
                return this;
            if(!servingvalue.listItem_.isEmpty())
                if(listItem_.isEmpty())
                {
                    listItem_ = servingvalue.listItem_;
                    bitField0_ = -2 & bitField0_;
                } else
                {
                    ensureListItemIsMutable();
                    listItem_.addAll(servingvalue.listItem_);
                }
            if(!servingvalue.mapKey_.isEmpty())
                if(mapKey_.isEmpty())
                {
                    mapKey_ = servingvalue.mapKey_;
                    bitField0_ = -3 & bitField0_;
                } else
                {
                    ensureMapKeyIsMutable();
                    mapKey_.addAll(servingvalue.mapKey_);
                }
            if(!servingvalue.mapValue_.isEmpty())
                if(mapValue_.isEmpty())
                {
                    mapValue_ = servingvalue.mapValue_;
                    bitField0_ = -5 & bitField0_;
                } else
                {
                    ensureMapValueIsMutable();
                    mapValue_.addAll(servingvalue.mapValue_);
                }
            if(servingvalue.hasMacroReference())
                setMacroReference(servingvalue.getMacroReference());
            if(!servingvalue.templateToken_.isEmpty())
                if(templateToken_.isEmpty())
                {
                    templateToken_ = servingvalue.templateToken_;
                    bitField0_ = 0xffffffef & bitField0_;
                } else
                {
                    ensureTemplateTokenIsMutable();
                    templateToken_.addAll(servingvalue.templateToken_);
                }
            if(servingvalue.hasMacroNameReference())
                setMacroNameReference(servingvalue.getMacroNameReference());
            setUnknownFields(getUnknownFields().concat(servingvalue.unknownFields));
            return this;
        }

        public ServingValue.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            ServingValue servingvalue = null;
            ServingValue servingvalue1 = (ServingValue)ServingValue.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(servingvalue1 != null)
                mergeFrom(servingvalue1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            servingvalue = (ServingValue)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(servingvalue != null)
                mergeFrom(servingvalue);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((ServingValue)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public ServingValue.Builder setListItem(int i, int j)
        {
            ensureListItemIsMutable();
            listItem_.set(i, Integer.valueOf(j));
            return this;
        }

        public ServingValue.Builder setMacroNameReference(int i)
        {
            bitField0_ = 0x20 | bitField0_;
            macroNameReference_ = i;
            return this;
        }

        public ServingValue.Builder setMacroReference(int i)
        {
            bitField0_ = 8 | bitField0_;
            macroReference_ = i;
            return this;
        }

        public ServingValue.Builder setMapKey(int i, int j)
        {
            ensureMapKeyIsMutable();
            mapKey_.set(i, Integer.valueOf(j));
            return this;
        }

        public ServingValue.Builder setMapValue(int i, int j)
        {
            ensureMapValueIsMutable();
            mapValue_.set(i, Integer.valueOf(j));
            return this;
        }

        public ServingValue.Builder setTemplateToken(int i, int j)
        {
            ensureTemplateTokenIsMutable();
            templateToken_.set(i, Integer.valueOf(j));
            return this;
        }

        private int bitField0_;
        private List listItem_;
        private int macroNameReference_;
        private int macroReference_;
        private List mapKey_;
        private List mapValue_;
        private List templateToken_;


        private ServingValue.Builder()
        {
            listItem_ = Collections.emptyList();
            mapKey_ = Collections.emptyList();
            mapValue_ = Collections.emptyList();
            templateToken_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }
    }

    public static interface ServingValueOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract int getListItem(int i);

        public abstract int getListItemCount();

        public abstract List getListItemList();

        public abstract int getMacroNameReference();

        public abstract int getMacroReference();

        public abstract int getMapKey(int i);

        public abstract int getMapKeyCount();

        public abstract List getMapKeyList();

        public abstract int getMapValue(int i);

        public abstract int getMapValueCount();

        public abstract List getMapValueList();

        public abstract int getTemplateToken(int i);

        public abstract int getTemplateTokenCount();

        public abstract List getTemplateTokenList();

        public abstract boolean hasMacroNameReference();

        public abstract boolean hasMacroReference();
    }


    private Serving()
    {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionregistrylite)
    {
        extensionregistrylite.add(ServingValue.ext);
    }

}
