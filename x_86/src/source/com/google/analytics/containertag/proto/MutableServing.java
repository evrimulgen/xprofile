// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.containertag.proto;

import com.google.tagmanager.protobuf.*;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.*;

public final class MutableServing
{
    public static final class CacheOption extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        public static CacheOption getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            level_ = CacheLevel.NO_CACHE;
        }

        public static CacheOption newMessage()
        {
            return new CacheOption();
        }

        public CacheOption clear()
        {
            assertMutable();
            super.clear();
            level_ = CacheLevel.NO_CACHE;
            bitField0_ = -2 & bitField0_;
            expirationSeconds_ = 0;
            bitField0_ = -3 & bitField0_;
            gcacheExpirationSeconds_ = 0;
            bitField0_ = -5 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public CacheOption clearExpirationSeconds()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            expirationSeconds_ = 0;
            return this;
        }

        public CacheOption clearGcacheExpirationSeconds()
        {
            assertMutable();
            bitField0_ = -5 & bitField0_;
            gcacheExpirationSeconds_ = 0;
            return this;
        }

        public CacheOption clearLevel()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            level_ = CacheLevel.NO_CACHE;
            return this;
        }

        public CacheOption clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final CacheOption getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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
            int i = 1 & bitField0_;
            int j = 0;
            if(i == 1)
                j = 0 + CodedOutputStream.computeEnumSize(1, level_.getNumber());
            if((2 & bitField0_) == 2)
                j += CodedOutputStream.computeInt32Size(2, expirationSeconds_);
            if((4 & bitField0_) == 4)
                j += CodedOutputStream.computeInt32Size(3, gcacheExpirationSeconds_);
            int k = j + unknownFields.size();
            cachedSize = k;
            return k;
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
            int i = 41;
            if(hasLevel())
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + Internal.hashEnum(getLevel());
            }
            if(hasExpirationSeconds())
                i = 53 * (2 + i * 37) + getExpirationSeconds();
            if(hasGcacheExpirationSeconds())
                i = 53 * (3 + i * 37) + getGcacheExpirationSeconds();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Serving$CacheOption");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            return true;
        }

        public CacheOption mergeFrom(CacheOption cacheoption)
        {
            if(this == cacheoption)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(cacheoption == getDefaultInstance())
                return this;
            if(cacheoption.hasLevel())
                setLevel(cacheoption.getLevel());
            if(cacheoption.hasExpirationSeconds())
                setExpirationSeconds(cacheoption.getExpirationSeconds());
            if(cacheoption.hasGcacheExpirationSeconds())
                setGcacheExpirationSeconds(cacheoption.getGcacheExpirationSeconds());
            unknownFields = unknownFields.concat(cacheoption.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((CacheOption)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            int j;
            CacheLevel cachelevel;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 4: default 76
        //                       0: 207
        //                       8: 95
        //                       16: 149
        //                       24: 170;
               goto _L3 _L4 _L5 _L6 _L7
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            j = codedinputstream.readEnum();
            cachelevel = CacheLevel.valueOf(j);
            if(cachelevel != null) goto _L9; else goto _L8
_L8:
            codedoutputstream.writeRawVarint32(i);
            codedoutputstream.writeRawVarint32(j);
            continue; /* Loop/switch isn't completed */
_L9:
            bitField0_ = 1 | bitField0_;
            level_ = cachelevel;
            continue; /* Loop/switch isn't completed */
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
            return true;
_L4:
            flag = true;
            if(true) goto _L11; else goto _L10
_L11:
            break MISSING_BLOCK_LABEL_19;
_L10:
        }

        public CacheOption newMessageForType()
        {
            return new CacheOption();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public CacheOption setExpirationSeconds(int i)
        {
            assertMutable();
            bitField0_ = 2 | bitField0_;
            expirationSeconds_ = i;
            return this;
        }

        public CacheOption setGcacheExpirationSeconds(int i)
        {
            assertMutable();
            bitField0_ = 4 | bitField0_;
            gcacheExpirationSeconds_ = i;
            return this;
        }

        public CacheOption setLevel(CacheLevel cachelevel)
        {
            assertMutable();
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

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeEnum(1, level_.getNumber());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeInt32(2, expirationSeconds_);
            if((4 & bitField0_) == 4)
                codedoutputstream.writeInt32(3, gcacheExpirationSeconds_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int EXPIRATION_SECONDS_FIELD_NUMBER = 2;
        public static final int GCACHE_EXPIRATION_SECONDS_FIELD_NUMBER = 3;
        public static final int LEVEL_FIELD_NUMBER = 1;
        public static Parser PARSER;
        private static final CacheOption defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private int expirationSeconds_;
        private int gcacheExpirationSeconds_;
        private CacheLevel level_;

        static 
        {
            defaultInstance = new CacheOption(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private CacheOption()
        {
            level_ = CacheLevel.NO_CACHE;
            initFields();
        }

        private CacheOption(boolean flag)
        {
            level_ = CacheLevel.NO_CACHE;
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
            return (CacheOption.CacheLevel)Enum.valueOf(com/google/analytics/containertag/proto/MutableServing$CacheOption$CacheLevel, s);
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

    public static final class Container extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureJsResourceInitialized()
        {
            if(jsResource_ == Resource.getDefaultInstance())
                jsResource_ = Resource.newMessage();
        }

        public static Container getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            jsResource_ = Resource.getDefaultInstance();
            state_ = ResourceState.PREVIEW;
        }

        public static Container newMessage()
        {
            return new Container();
        }

        public Container clear()
        {
            assertMutable();
            super.clear();
            if(jsResource_ != Resource.getDefaultInstance())
                jsResource_.clear();
            bitField0_ = -2 & bitField0_;
            containerId_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -3 & bitField0_;
            state_ = ResourceState.PREVIEW;
            bitField0_ = -5 & bitField0_;
            version_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -9 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public Container clearContainerId()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            containerId_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public Container clearJsResource()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            if(jsResource_ != Resource.getDefaultInstance())
                jsResource_.clear();
            return this;
        }

        public Container clearState()
        {
            assertMutable();
            bitField0_ = -5 & bitField0_;
            state_ = ResourceState.PREVIEW;
            return this;
        }

        public Container clearVersion()
        {
            assertMutable();
            bitField0_ = -9 & bitField0_;
            version_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public Container clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                containerId_ = s;
            return s;
        }

        public byte[] getContainerIdAsBytes()
        {
            Object obj = containerId_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                containerId_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public final Container getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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

        public Resource getMutableJsResource()
        {
            assertMutable();
            ensureJsResourceInitialized();
            bitField0_ = 1 | bitField0_;
            return jsResource_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            int i = 0 + CodedOutputStream.computeMessageSize(1, jsResource_) + CodedOutputStream.computeByteArraySize(3, getContainerIdAsBytes()) + CodedOutputStream.computeEnumSize(4, state_.getNumber());
            if((8 & bitField0_) == 8)
                i += CodedOutputStream.computeByteArraySize(5, getVersionAsBytes());
            int j = i + unknownFields.size();
            cachedSize = j;
            return j;
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
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                version_ = s;
            return s;
        }

        public byte[] getVersionAsBytes()
        {
            Object obj = version_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                version_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
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
            int i = 41;
            if(hasJsResource())
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getJsResource().hashCode();
            }
            if(hasContainerId())
                i = 53 * (3 + i * 37) + getContainerId().hashCode();
            if(hasState())
                i = 53 * (4 + i * 37) + Internal.hashEnum(getState());
            if(hasVersion())
                i = 53 * (5 + i * 37) + getVersion().hashCode();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Serving$Container");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            while(!hasJsResource() || !hasContainerId() || !hasState() || !getJsResource().isInitialized()) 
                return false;
            return true;
        }

        public Container mergeFrom(Container container)
        {
            if(this == container)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(container == getDefaultInstance())
                return this;
            if(container.hasJsResource())
            {
                ensureJsResourceInitialized();
                jsResource_.mergeFrom(container.getJsResource());
                bitField0_ = 1 | bitField0_;
            }
            if(container.hasContainerId())
            {
                bitField0_ = 2 | bitField0_;
                if(container.containerId_ instanceof String)
                {
                    containerId_ = container.containerId_;
                } else
                {
                    byte abyte1[] = (byte[])(byte[])container.containerId_;
                    containerId_ = Arrays.copyOf(abyte1, abyte1.length);
                }
            }
            if(container.hasState())
                setState(container.getState());
            if(container.hasVersion())
            {
                bitField0_ = 8 | bitField0_;
                if(container.version_ instanceof String)
                {
                    version_ = container.version_;
                } else
                {
                    byte abyte0[] = (byte[])(byte[])container.version_;
                    version_ = Arrays.copyOf(abyte0, abyte0.length);
                }
            }
            unknownFields = unknownFields.concat(container.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((Container)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            int j;
            ResourceState resourcestate;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 5: default 84
        //                       0: 255
        //                       10: 103
        //                       26: 142
        //                       32: 163
        //                       42: 217;
               goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if(jsResource_ == Resource.getDefaultInstance())
                jsResource_ = Resource.newMessage();
            bitField0_ = 1 | bitField0_;
            codedinputstream.readMessage(jsResource_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L6:
            bitField0_ = 2 | bitField0_;
            containerId_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L7:
            j = codedinputstream.readEnum();
            resourcestate = ResourceState.valueOf(j);
            if(resourcestate != null) goto _L10; else goto _L9
_L9:
            codedoutputstream.writeRawVarint32(i);
            codedoutputstream.writeRawVarint32(j);
            continue; /* Loop/switch isn't completed */
_L10:
            bitField0_ = 4 | bitField0_;
            state_ = resourcestate;
            continue; /* Loop/switch isn't completed */
_L8:
            bitField0_ = 8 | bitField0_;
            version_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L12; else goto _L11
_L12:
            break MISSING_BLOCK_LABEL_19;
_L11:
        }

        public Container newMessageForType()
        {
            return new Container();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public Container setContainerId(String s)
        {
            assertMutable();
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

        public Container setContainerIdAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                containerId_ = abyte0;
                return this;
            }
        }

        public Container setJsResource(Resource resource)
        {
            assertMutable();
            if(resource == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                jsResource_ = resource;
                return this;
            }
        }

        public Container setState(ResourceState resourcestate)
        {
            assertMutable();
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

        public Container setVersion(String s)
        {
            assertMutable();
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

        public Container setVersionAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 8 | bitField0_;
                version_ = abyte0;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            codedoutputstream.writeMessageWithCachedSizes(1, jsResource_);
            codedoutputstream.writeByteArray(3, getContainerIdAsBytes());
            codedoutputstream.writeEnum(4, state_.getNumber());
            if((8 & bitField0_) == 8)
                codedoutputstream.writeByteArray(5, getVersionAsBytes());
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int CONTAINER_ID_FIELD_NUMBER = 3;
        public static final int JS_RESOURCE_FIELD_NUMBER = 1;
        public static Parser PARSER;
        public static final int STATE_FIELD_NUMBER = 4;
        public static final int VERSION_FIELD_NUMBER = 5;
        private static final Container defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private Object containerId_;
        private Resource jsResource_;
        private ResourceState state_;
        private Object version_;

        static 
        {
            defaultInstance = new Container(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private Container()
        {
            containerId_ = Internal.EMPTY_BYTE_ARRAY;
            state_ = ResourceState.PREVIEW;
            version_ = Internal.EMPTY_BYTE_ARRAY;
            initFields();
        }

        private Container(boolean flag)
        {
            containerId_ = Internal.EMPTY_BYTE_ARRAY;
            state_ = ResourceState.PREVIEW;
            version_ = Internal.EMPTY_BYTE_ARRAY;
        }
    }

    public static final class FunctionCall extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensurePropertyInitialized()
        {
            if(property_ == null)
                property_ = new ArrayList();
        }

        public static FunctionCall getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
        }

        public static FunctionCall newMessage()
        {
            return new FunctionCall();
        }

        public FunctionCall addAllProperty(Iterable iterable)
        {
            assertMutable();
            ensurePropertyInitialized();
            AbstractMutableMessageLite.addAll(iterable, property_);
            return this;
        }

        public FunctionCall addProperty(int i)
        {
            assertMutable();
            ensurePropertyInitialized();
            property_.add(Integer.valueOf(i));
            return this;
        }

        public FunctionCall clear()
        {
            assertMutable();
            super.clear();
            property_ = null;
            function_ = 0;
            bitField0_ = -2 & bitField0_;
            name_ = 0;
            bitField0_ = -3 & bitField0_;
            liveOnly_ = false;
            bitField0_ = -5 & bitField0_;
            serverSide_ = false;
            bitField0_ = -9 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public FunctionCall clearFunction()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            function_ = 0;
            return this;
        }

        public FunctionCall clearLiveOnly()
        {
            assertMutable();
            bitField0_ = -5 & bitField0_;
            liveOnly_ = false;
            return this;
        }

        public FunctionCall clearName()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            name_ = 0;
            return this;
        }

        public FunctionCall clearProperty()
        {
            assertMutable();
            property_ = null;
            return this;
        }

        public FunctionCall clearServerSide()
        {
            assertMutable();
            bitField0_ = -9 & bitField0_;
            serverSide_ = false;
            return this;
        }

        public FunctionCall clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final FunctionCall getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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

        public List getMutablePropertyList()
        {
            assertMutable();
            ensurePropertyInitialized();
            return property_;
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
            if(property_ == null)
                return 0;
            else
                return property_.size();
        }

        public List getPropertyList()
        {
            if(property_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(property_);
        }

        public int getSerializedSize()
        {
            List list = property_;
            int i = 0;
            if(list != null)
            {
                int l = property_.size();
                i = 0;
                if(l > 0)
                {
                    int i1 = 0;
                    for(int j1 = 0; j1 < property_.size(); j1++)
                        i1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)property_.get(j1)).intValue());

                    i = 0 + i1 + 1 * getPropertyList().size();
                }
            }
            int j = i + CodedOutputStream.computeInt32Size(2, function_);
            if((2 & bitField0_) == 2)
                j += CodedOutputStream.computeInt32Size(4, name_);
            if((4 & bitField0_) == 4)
                j += CodedOutputStream.computeBoolSize(6, liveOnly_);
            if((8 & bitField0_) == 8)
                j += CodedOutputStream.computeBoolSize(1, serverSide_);
            int k = j + unknownFields.size();
            cachedSize = k;
            return k;
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
            int i = 41;
            if(getPropertyCount() > 0)
            {
                int _tmp = 1517 + 3;
                i = 0x13ab0 + getPropertyList().hashCode();
            }
            if(hasFunction())
                i = 53 * (2 + i * 37) + getFunction();
            if(hasName())
                i = 53 * (4 + i * 37) + getName();
            if(hasLiveOnly())
                i = 53 * (6 + i * 37) + Internal.hashBoolean(getLiveOnly());
            if(hasServerSide())
                i = 53 * (1 + i * 37) + Internal.hashBoolean(getServerSide());
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Serving$FunctionCall");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            return hasFunction();
        }

        public FunctionCall mergeFrom(FunctionCall functioncall)
        {
            if(this == functioncall)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(functioncall == getDefaultInstance())
                return this;
            if(functioncall.hasServerSide())
                setServerSide(functioncall.getServerSide());
            if(functioncall.hasFunction())
                setFunction(functioncall.getFunction());
            if(functioncall.property_ != null && !functioncall.property_.isEmpty())
            {
                ensurePropertyInitialized();
                property_.addAll(functioncall.property_);
            }
            if(functioncall.hasName())
                setName(functioncall.getName());
            if(functioncall.hasLiveOnly())
                setLiveOnly(functioncall.getLiveOnly());
            unknownFields = unknownFields.concat(functioncall.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((FunctionCall)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            int j;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 7: default 100
        //                       0: 322
        //                       8: 119
        //                       16: 141
        //                       24: 162
        //                       26: 200
        //                       32: 264
        //                       48: 285;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            bitField0_ = 8 | bitField0_;
            serverSide_ = codedinputstream.readBool();
            continue; /* Loop/switch isn't completed */
_L6:
            bitField0_ = 1 | bitField0_;
            function_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L7:
            if(property_ == null)
                property_ = new ArrayList();
            property_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L8:
            j = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(property_ == null)
                property_ = new ArrayList();
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
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L12; else goto _L11
_L12:
            break MISSING_BLOCK_LABEL_19;
_L11:
        }

        public FunctionCall newMessageForType()
        {
            return new FunctionCall();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public FunctionCall setFunction(int i)
        {
            assertMutable();
            bitField0_ = 1 | bitField0_;
            function_ = i;
            return this;
        }

        public FunctionCall setLiveOnly(boolean flag)
        {
            assertMutable();
            bitField0_ = 4 | bitField0_;
            liveOnly_ = flag;
            return this;
        }

        public FunctionCall setName(int i)
        {
            assertMutable();
            bitField0_ = 2 | bitField0_;
            name_ = i;
            return this;
        }

        public FunctionCall setProperty(int i, int j)
        {
            assertMutable();
            ensurePropertyInitialized();
            property_.set(i, Integer.valueOf(j));
            return this;
        }

        public FunctionCall setServerSide(boolean flag)
        {
            assertMutable();
            bitField0_ = 8 | bitField0_;
            serverSide_ = flag;
            return this;
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if((8 & bitField0_) == 8)
                codedoutputstream.writeBool(1, serverSide_);
            codedoutputstream.writeInt32(2, function_);
            if(property_ != null)
            {
                for(int k = 0; k < property_.size(); k++)
                    codedoutputstream.writeInt32(3, ((Integer)property_.get(k)).intValue());

            }
            if((2 & bitField0_) == 2)
                codedoutputstream.writeInt32(4, name_);
            if((4 & bitField0_) == 4)
                codedoutputstream.writeBool(6, liveOnly_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int FUNCTION_FIELD_NUMBER = 2;
        public static final int LIVE_ONLY_FIELD_NUMBER = 6;
        public static final int NAME_FIELD_NUMBER = 4;
        public static Parser PARSER;
        public static final int PROPERTY_FIELD_NUMBER = 3;
        public static final int SERVER_SIDE_FIELD_NUMBER = 1;
        private static final FunctionCall defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private int function_;
        private boolean liveOnly_;
        private int name_;
        private List property_;
        private boolean serverSide_;

        static 
        {
            defaultInstance = new FunctionCall(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private FunctionCall()
        {
            property_ = null;
            initFields();
        }

        private FunctionCall(boolean flag)
        {
            property_ = null;
        }
    }

    public static final class OptionalResource extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureResourceInitialized()
        {
            if(resource_ == Resource.getDefaultInstance())
                resource_ = Resource.newMessage();
        }

        public static OptionalResource getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            resource_ = Resource.getDefaultInstance();
        }

        public static OptionalResource newMessage()
        {
            return new OptionalResource();
        }

        public OptionalResource clear()
        {
            assertMutable();
            super.clear();
            if(resource_ != Resource.getDefaultInstance())
                resource_.clear();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public OptionalResource clearResource()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            if(resource_ != Resource.getDefaultInstance())
                resource_.clear();
            return this;
        }

        public OptionalResource clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final OptionalResource getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Resource getMutableResource()
        {
            assertMutable();
            ensureResourceInitialized();
            bitField0_ = 1 | bitField0_;
            return resource_;
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
            int i = 1 & bitField0_;
            int j = 0;
            if(i == 1)
                j = 0 + CodedOutputStream.computeMessageSize(2, resource_);
            int k = j + unknownFields.size();
            cachedSize = k;
            return k;
        }

        public boolean hasResource()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            int i = 41;
            if(hasResource())
            {
                int _tmp = 1517 + 2;
                i = 0x13a7b + getResource().hashCode();
            }
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Serving$OptionalResource");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            return !hasResource() || getResource().isInitialized();
        }

        public OptionalResource mergeFrom(OptionalResource optionalresource)
        {
            if(this == optionalresource)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(optionalresource == getDefaultInstance())
                return this;
            if(optionalresource.hasResource())
            {
                ensureResourceInitialized();
                resource_.mergeFrom(optionalresource.getResource());
                bitField0_ = 1 | bitField0_;
            }
            unknownFields = unknownFields.concat(optionalresource.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((OptionalResource)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 2: default 60
        //                       0: 134
        //                       18: 79;
               goto _L3 _L4 _L5
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if(resource_ == Resource.getDefaultInstance())
                resource_ = Resource.newMessage();
            bitField0_ = 1 | bitField0_;
            codedinputstream.readMessage(resource_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L7; else goto _L6
_L7:
            break MISSING_BLOCK_LABEL_19;
_L6:
        }

        public OptionalResource newMessageForType()
        {
            return new OptionalResource();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public OptionalResource setResource(Resource resource)
        {
            assertMutable();
            if(resource == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                resource_ = resource;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessageWithCachedSizes(2, resource_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static Parser PARSER;
        public static final int RESOURCE_FIELD_NUMBER = 2;
        private static final OptionalResource defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private Resource resource_;

        static 
        {
            defaultInstance = new OptionalResource(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private OptionalResource()
        {
            initFields();
        }

        private OptionalResource(boolean flag)
        {
        }
    }

    public static final class Property extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        public static Property getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
        }

        public static Property newMessage()
        {
            return new Property();
        }

        public Property clear()
        {
            assertMutable();
            super.clear();
            key_ = 0;
            bitField0_ = -2 & bitField0_;
            value_ = 0;
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public Property clearKey()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            key_ = 0;
            return this;
        }

        public Property clearValue()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            value_ = 0;
            return this;
        }

        public Property clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final Property getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            int i = 0 + CodedOutputStream.computeInt32Size(1, key_) + CodedOutputStream.computeInt32Size(2, value_) + unknownFields.size();
            cachedSize = i;
            return i;
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
            int i = 41;
            if(hasKey())
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getKey();
            }
            if(hasValue())
                i = 53 * (2 + i * 37) + getValue();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Serving$Property");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            while(!hasKey() || !hasValue()) 
                return false;
            return true;
        }

        public Property mergeFrom(Property property)
        {
            if(this == property)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(property == getDefaultInstance())
                return this;
            if(property.hasKey())
                setKey(property.getKey());
            if(property.hasValue())
                setValue(property.getValue());
            unknownFields = unknownFields.concat(property.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((Property)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 3: default 68
        //                       0: 145
        //                       8: 87
        //                       16: 108;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            bitField0_ = 1 | bitField0_;
            key_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L6:
            bitField0_ = 2 | bitField0_;
            value_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L8; else goto _L7
_L8:
            break MISSING_BLOCK_LABEL_19;
_L7:
        }

        public Property newMessageForType()
        {
            return new Property();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public Property setKey(int i)
        {
            assertMutable();
            bitField0_ = 1 | bitField0_;
            key_ = i;
            return this;
        }

        public Property setValue(int i)
        {
            assertMutable();
            bitField0_ = 2 | bitField0_;
            value_ = i;
            return this;
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            codedoutputstream.writeInt32(1, key_);
            codedoutputstream.writeInt32(2, value_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int KEY_FIELD_NUMBER = 1;
        public static Parser PARSER;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final Property defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private int key_;
        private int value_;

        static 
        {
            defaultInstance = new Property(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private Property()
        {
            initFields();
        }

        private Property(boolean flag)
        {
        }
    }

    public static final class Resource extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureKeyInitialized()
        {
            if(key_ == null)
                key_ = new LazyStringArrayList();
        }

        private void ensureLiveJsCacheOptionInitialized()
        {
            if(liveJsCacheOption_ == CacheOption.getDefaultInstance())
                liveJsCacheOption_ = CacheOption.newMessage();
        }

        private void ensureMacroInitialized()
        {
            if(macro_ == null)
                macro_ = new ArrayList();
        }

        private void ensurePredicateInitialized()
        {
            if(predicate_ == null)
                predicate_ = new ArrayList();
        }

        private void ensurePropertyInitialized()
        {
            if(property_ == null)
                property_ = new ArrayList();
        }

        private void ensureRuleInitialized()
        {
            if(rule_ == null)
                rule_ = new ArrayList();
        }

        private void ensureTagInitialized()
        {
            if(tag_ == null)
                tag_ = new ArrayList();
        }

        private void ensureUsageContextInitialized()
        {
            if(usageContext_ == null)
                usageContext_ = new LazyStringArrayList();
        }

        private void ensureValueInitialized()
        {
            if(value_ == null)
                value_ = new ArrayList();
        }

        public static Resource getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            liveJsCacheOption_ = CacheOption.getDefaultInstance();
        }

        public static Resource newMessage()
        {
            return new Resource();
        }

        public Resource addAllKey(Iterable iterable)
        {
            assertMutable();
            ensureKeyInitialized();
            AbstractMutableMessageLite.addAll(iterable, key_);
            return this;
        }

        public Resource addAllMacro(Iterable iterable)
        {
            assertMutable();
            ensureMacroInitialized();
            AbstractMutableMessageLite.addAll(iterable, macro_);
            return this;
        }

        public Resource addAllPredicate(Iterable iterable)
        {
            assertMutable();
            ensurePredicateInitialized();
            AbstractMutableMessageLite.addAll(iterable, predicate_);
            return this;
        }

        public Resource addAllProperty(Iterable iterable)
        {
            assertMutable();
            ensurePropertyInitialized();
            AbstractMutableMessageLite.addAll(iterable, property_);
            return this;
        }

        public Resource addAllRule(Iterable iterable)
        {
            assertMutable();
            ensureRuleInitialized();
            AbstractMutableMessageLite.addAll(iterable, rule_);
            return this;
        }

        public Resource addAllTag(Iterable iterable)
        {
            assertMutable();
            ensureTagInitialized();
            AbstractMutableMessageLite.addAll(iterable, tag_);
            return this;
        }

        public Resource addAllUsageContext(Iterable iterable)
        {
            assertMutable();
            ensureUsageContextInitialized();
            AbstractMutableMessageLite.addAll(iterable, usageContext_);
            return this;
        }

        public Resource addAllValue(Iterable iterable)
        {
            assertMutable();
            ensureValueInitialized();
            AbstractMutableMessageLite.addAll(iterable, value_);
            return this;
        }

        public Resource addKey(String s)
        {
            assertMutable();
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureKeyInitialized();
                key_.add(s);
                return this;
            }
        }

        public Resource addKeyAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureKeyInitialized();
                key_.add(abyte0);
                return this;
            }
        }

        public FunctionCall addMacro()
        {
            assertMutable();
            ensureMacroInitialized();
            FunctionCall functioncall = FunctionCall.newMessage();
            macro_.add(functioncall);
            return functioncall;
        }

        public Resource addMacro(FunctionCall functioncall)
        {
            assertMutable();
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMacroInitialized();
                macro_.add(functioncall);
                return this;
            }
        }

        public FunctionCall addPredicate()
        {
            assertMutable();
            ensurePredicateInitialized();
            FunctionCall functioncall = FunctionCall.newMessage();
            predicate_.add(functioncall);
            return functioncall;
        }

        public Resource addPredicate(FunctionCall functioncall)
        {
            assertMutable();
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePredicateInitialized();
                predicate_.add(functioncall);
                return this;
            }
        }

        public Property addProperty()
        {
            assertMutable();
            ensurePropertyInitialized();
            Property property = Property.newMessage();
            property_.add(property);
            return property;
        }

        public Resource addProperty(Property property)
        {
            assertMutable();
            if(property == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertyInitialized();
                property_.add(property);
                return this;
            }
        }

        public Resource addRule(Rule rule)
        {
            assertMutable();
            if(rule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRuleInitialized();
                rule_.add(rule);
                return this;
            }
        }

        public Rule addRule()
        {
            assertMutable();
            ensureRuleInitialized();
            Rule rule = Rule.newMessage();
            rule_.add(rule);
            return rule;
        }

        public FunctionCall addTag()
        {
            assertMutable();
            ensureTagInitialized();
            FunctionCall functioncall = FunctionCall.newMessage();
            tag_.add(functioncall);
            return functioncall;
        }

        public Resource addTag(FunctionCall functioncall)
        {
            assertMutable();
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureTagInitialized();
                tag_.add(functioncall);
                return this;
            }
        }

        public Resource addUsageContext(String s)
        {
            assertMutable();
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureUsageContextInitialized();
                usageContext_.add(s);
                return this;
            }
        }

        public Resource addUsageContextAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureUsageContextInitialized();
                usageContext_.add(abyte0);
                return this;
            }
        }

        public Resource addValue(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value)
        {
            assertMutable();
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureValueInitialized();
                value_.add(value);
                return this;
            }
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value addValue()
        {
            assertMutable();
            ensureValueInitialized();
            com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.newMessage();
            value_.add(value);
            return value;
        }

        public Resource clear()
        {
            assertMutable();
            super.clear();
            key_ = null;
            value_ = null;
            property_ = null;
            macro_ = null;
            tag_ = null;
            predicate_ = null;
            rule_ = null;
            previewAuthCode_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -2 & bitField0_;
            malwareScanAuthCode_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -3 & bitField0_;
            templateVersionSet_ = getDefaultInstance().getTemplateVersionSetAsBytes();
            bitField0_ = -5 & bitField0_;
            version_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -9 & bitField0_;
            if(liveJsCacheOption_ != CacheOption.getDefaultInstance())
                liveJsCacheOption_.clear();
            bitField0_ = 0xffffffef & bitField0_;
            reportingSampleRate_ = 0.0F;
            bitField0_ = 0xffffffdf & bitField0_;
            enableAutoEventTracking_ = false;
            bitField0_ = 0xffffffbf & bitField0_;
            usageContext_ = null;
            resourceFormatVersion_ = 0;
            bitField0_ = 0xffffff7f & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public Resource clearEnableAutoEventTracking()
        {
            assertMutable();
            bitField0_ = 0xffffffbf & bitField0_;
            enableAutoEventTracking_ = false;
            return this;
        }

        public Resource clearKey()
        {
            assertMutable();
            key_ = null;
            return this;
        }

        public Resource clearLiveJsCacheOption()
        {
            assertMutable();
            bitField0_ = 0xffffffef & bitField0_;
            if(liveJsCacheOption_ != CacheOption.getDefaultInstance())
                liveJsCacheOption_.clear();
            return this;
        }

        public Resource clearMacro()
        {
            assertMutable();
            macro_ = null;
            return this;
        }

        public Resource clearMalwareScanAuthCode()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            malwareScanAuthCode_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public Resource clearPredicate()
        {
            assertMutable();
            predicate_ = null;
            return this;
        }

        public Resource clearPreviewAuthCode()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            previewAuthCode_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public Resource clearProperty()
        {
            assertMutable();
            property_ = null;
            return this;
        }

        public Resource clearReportingSampleRate()
        {
            assertMutable();
            bitField0_ = 0xffffffdf & bitField0_;
            reportingSampleRate_ = 0.0F;
            return this;
        }

        public Resource clearResourceFormatVersion()
        {
            assertMutable();
            bitField0_ = 0xffffff7f & bitField0_;
            resourceFormatVersion_ = 0;
            return this;
        }

        public Resource clearRule()
        {
            assertMutable();
            rule_ = null;
            return this;
        }

        public Resource clearTag()
        {
            assertMutable();
            tag_ = null;
            return this;
        }

        public Resource clearTemplateVersionSet()
        {
            assertMutable();
            bitField0_ = -5 & bitField0_;
            templateVersionSet_ = getDefaultInstance().getTemplateVersionSetAsBytes();
            return this;
        }

        public Resource clearUsageContext()
        {
            assertMutable();
            usageContext_ = null;
            return this;
        }

        public Resource clearValue()
        {
            assertMutable();
            value_ = null;
            return this;
        }

        public Resource clearVersion()
        {
            assertMutable();
            bitField0_ = -9 & bitField0_;
            version_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public Resource clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final Resource getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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

        public byte[] getKeyAsBytes(int i)
        {
            return key_.getByteArray(i);
        }

        public int getKeyCount()
        {
            if(key_ == null)
                return 0;
            else
                return key_.size();
        }

        public List getKeyList()
        {
            if(key_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(key_);
        }

        public List getKeyListAsBytes()
        {
            if(key_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(key_.asByteArrayList());
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
            if(macro_ == null)
                return 0;
            else
                return macro_.size();
        }

        public List getMacroList()
        {
            if(macro_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(macro_);
        }

        public String getMalwareScanAuthCode()
        {
            Object obj = malwareScanAuthCode_;
            if(obj instanceof String)
                return (String)obj;
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                malwareScanAuthCode_ = s;
            return s;
        }

        public byte[] getMalwareScanAuthCodeAsBytes()
        {
            Object obj = malwareScanAuthCode_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                malwareScanAuthCode_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public List getMutableKeyList()
        {
            assertMutable();
            ensureKeyInitialized();
            return key_;
        }

        public List getMutableKeyListAsBytes()
        {
            assertMutable();
            ensureKeyInitialized();
            return key_.asByteArrayList();
        }

        public CacheOption getMutableLiveJsCacheOption()
        {
            assertMutable();
            ensureLiveJsCacheOptionInitialized();
            bitField0_ = 0x10 | bitField0_;
            return liveJsCacheOption_;
        }

        public FunctionCall getMutableMacro(int i)
        {
            return (FunctionCall)macro_.get(i);
        }

        public List getMutableMacroList()
        {
            assertMutable();
            ensureMacroInitialized();
            return macro_;
        }

        public FunctionCall getMutablePredicate(int i)
        {
            return (FunctionCall)predicate_.get(i);
        }

        public List getMutablePredicateList()
        {
            assertMutable();
            ensurePredicateInitialized();
            return predicate_;
        }

        public Property getMutableProperty(int i)
        {
            return (Property)property_.get(i);
        }

        public List getMutablePropertyList()
        {
            assertMutable();
            ensurePropertyInitialized();
            return property_;
        }

        public Rule getMutableRule(int i)
        {
            return (Rule)rule_.get(i);
        }

        public List getMutableRuleList()
        {
            assertMutable();
            ensureRuleInitialized();
            return rule_;
        }

        public FunctionCall getMutableTag(int i)
        {
            return (FunctionCall)tag_.get(i);
        }

        public List getMutableTagList()
        {
            assertMutable();
            ensureTagInitialized();
            return tag_;
        }

        public List getMutableUsageContextList()
        {
            assertMutable();
            ensureUsageContextInitialized();
            return usageContext_;
        }

        public List getMutableUsageContextListAsBytes()
        {
            assertMutable();
            ensureUsageContextInitialized();
            return usageContext_.asByteArrayList();
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value getMutableValue(int i)
        {
            return (com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value)value_.get(i);
        }

        public List getMutableValueList()
        {
            assertMutable();
            ensureValueInitialized();
            return value_;
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
            if(predicate_ == null)
                return 0;
            else
                return predicate_.size();
        }

        public List getPredicateList()
        {
            if(predicate_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(predicate_);
        }

        public String getPreviewAuthCode()
        {
            Object obj = previewAuthCode_;
            if(obj instanceof String)
                return (String)obj;
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                previewAuthCode_ = s;
            return s;
        }

        public byte[] getPreviewAuthCodeAsBytes()
        {
            Object obj = previewAuthCode_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                previewAuthCode_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public Property getProperty(int i)
        {
            return (Property)property_.get(i);
        }

        public int getPropertyCount()
        {
            if(property_ == null)
                return 0;
            else
                return property_.size();
        }

        public List getPropertyList()
        {
            if(property_ == null)
                return Collections.emptyList();
            else
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
            if(rule_ == null)
                return 0;
            else
                return rule_.size();
        }

        public List getRuleList()
        {
            if(rule_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(rule_);
        }

        public int getSerializedSize()
        {
            LazyStringList lazystringlist = key_;
            int i = 0;
            if(lazystringlist != null)
            {
                int k2 = key_.size();
                i = 0;
                if(k2 > 0)
                {
                    int l2 = 0;
                    for(int i3 = 0; i3 < key_.size(); i3++)
                        l2 += CodedOutputStream.computeByteArraySizeNoTag(key_.getByteArray(i3));

                    i = 0 + l2 + 1 * key_.size();
                }
            }
            if(value_ != null)
            {
                for(int j2 = 0; j2 < value_.size(); j2++)
                    i += CodedOutputStream.computeMessageSize(2, (MessageLite)value_.get(j2));

            }
            if(property_ != null)
            {
                for(int i2 = 0; i2 < property_.size(); i2++)
                    i += CodedOutputStream.computeMessageSize(3, (MessageLite)property_.get(i2));

            }
            if(macro_ != null)
            {
                for(int l1 = 0; l1 < macro_.size(); l1++)
                    i += CodedOutputStream.computeMessageSize(4, (MessageLite)macro_.get(l1));

            }
            if(tag_ != null)
            {
                for(int k1 = 0; k1 < tag_.size(); k1++)
                    i += CodedOutputStream.computeMessageSize(5, (MessageLite)tag_.get(k1));

            }
            if(predicate_ != null)
            {
                for(int j1 = 0; j1 < predicate_.size(); j1++)
                    i += CodedOutputStream.computeMessageSize(6, (MessageLite)predicate_.get(j1));

            }
            if(rule_ != null)
            {
                for(int i1 = 0; i1 < rule_.size(); i1++)
                    i += CodedOutputStream.computeMessageSize(7, (MessageLite)rule_.get(i1));

            }
            if((1 & bitField0_) == 1)
                i += CodedOutputStream.computeByteArraySize(9, getPreviewAuthCodeAsBytes());
            if((2 & bitField0_) == 2)
                i += CodedOutputStream.computeByteArraySize(10, getMalwareScanAuthCodeAsBytes());
            if((4 & bitField0_) == 4)
                i += CodedOutputStream.computeByteArraySize(12, getTemplateVersionSetAsBytes());
            if((8 & bitField0_) == 8)
                i += CodedOutputStream.computeByteArraySize(13, getVersionAsBytes());
            if((0x10 & bitField0_) == 16)
                i += CodedOutputStream.computeMessageSize(14, liveJsCacheOption_);
            if((0x20 & bitField0_) == 32)
                i += CodedOutputStream.computeFloatSize(15, reportingSampleRate_);
            if((0x40 & bitField0_) == 64)
                i += CodedOutputStream.computeBoolSize(18, enableAutoEventTracking_);
            if(usageContext_ != null && usageContext_.size() > 0)
            {
                int k = 0;
                for(int l = 0; l < usageContext_.size(); l++)
                    k += CodedOutputStream.computeByteArraySizeNoTag(usageContext_.getByteArray(l));

                i = i + k + 2 * usageContext_.size();
            }
            if((0x80 & bitField0_) == 128)
                i += CodedOutputStream.computeInt32Size(17, resourceFormatVersion_);
            int j = i + unknownFields.size();
            cachedSize = j;
            return j;
        }

        public FunctionCall getTag(int i)
        {
            return (FunctionCall)tag_.get(i);
        }

        public int getTagCount()
        {
            if(tag_ == null)
                return 0;
            else
                return tag_.size();
        }

        public List getTagList()
        {
            if(tag_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(tag_);
        }

        public String getTemplateVersionSet()
        {
            Object obj = templateVersionSet_;
            if(obj instanceof String)
                return (String)obj;
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                templateVersionSet_ = s;
            return s;
        }

        public byte[] getTemplateVersionSetAsBytes()
        {
            Object obj = templateVersionSet_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                templateVersionSet_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public String getUsageContext(int i)
        {
            return (String)usageContext_.get(i);
        }

        public byte[] getUsageContextAsBytes(int i)
        {
            return usageContext_.getByteArray(i);
        }

        public int getUsageContextCount()
        {
            if(usageContext_ == null)
                return 0;
            else
                return usageContext_.size();
        }

        public List getUsageContextList()
        {
            if(usageContext_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(usageContext_);
        }

        public List getUsageContextListAsBytes()
        {
            if(usageContext_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(usageContext_.asByteArrayList());
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value getValue(int i)
        {
            return (com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value)value_.get(i);
        }

        public int getValueCount()
        {
            if(value_ == null)
                return 0;
            else
                return value_.size();
        }

        public List getValueList()
        {
            if(value_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(value_);
        }

        public String getVersion()
        {
            Object obj = version_;
            if(obj instanceof String)
                return (String)obj;
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                version_ = s;
            return s;
        }

        public byte[] getVersionAsBytes()
        {
            Object obj = version_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                version_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
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
            int i = 41;
            if(getKeyCount() > 0)
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getKeyList().hashCode();
            }
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
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Serving$Resource");
            return immutableDefault;
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

        public Resource mergeFrom(Resource resource)
        {
            if(this == resource)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(resource == getDefaultInstance())
                return this;
            if(resource.key_ != null && !resource.key_.isEmpty())
            {
                ensureKeyInitialized();
                key_.mergeFrom(resource.key_);
            }
            if(resource.value_ != null && !resource.value_.isEmpty())
            {
                ensureValueInitialized();
                AbstractMutableMessageLite.addAll(resource.value_, value_);
            }
            if(resource.property_ != null && !resource.property_.isEmpty())
            {
                ensurePropertyInitialized();
                AbstractMutableMessageLite.addAll(resource.property_, property_);
            }
            if(resource.macro_ != null && !resource.macro_.isEmpty())
            {
                ensureMacroInitialized();
                AbstractMutableMessageLite.addAll(resource.macro_, macro_);
            }
            if(resource.tag_ != null && !resource.tag_.isEmpty())
            {
                ensureTagInitialized();
                AbstractMutableMessageLite.addAll(resource.tag_, tag_);
            }
            if(resource.predicate_ != null && !resource.predicate_.isEmpty())
            {
                ensurePredicateInitialized();
                AbstractMutableMessageLite.addAll(resource.predicate_, predicate_);
            }
            if(resource.rule_ != null && !resource.rule_.isEmpty())
            {
                ensureRuleInitialized();
                AbstractMutableMessageLite.addAll(resource.rule_, rule_);
            }
            if(resource.hasPreviewAuthCode())
            {
                bitField0_ = 1 | bitField0_;
                if(resource.previewAuthCode_ instanceof String)
                {
                    previewAuthCode_ = resource.previewAuthCode_;
                } else
                {
                    byte abyte3[] = (byte[])(byte[])resource.previewAuthCode_;
                    previewAuthCode_ = Arrays.copyOf(abyte3, abyte3.length);
                }
            }
            if(resource.hasMalwareScanAuthCode())
            {
                bitField0_ = 2 | bitField0_;
                if(resource.malwareScanAuthCode_ instanceof String)
                {
                    malwareScanAuthCode_ = resource.malwareScanAuthCode_;
                } else
                {
                    byte abyte2[] = (byte[])(byte[])resource.malwareScanAuthCode_;
                    malwareScanAuthCode_ = Arrays.copyOf(abyte2, abyte2.length);
                }
            }
            if(resource.hasTemplateVersionSet())
            {
                bitField0_ = 4 | bitField0_;
                if(resource.templateVersionSet_ instanceof String)
                {
                    templateVersionSet_ = resource.templateVersionSet_;
                } else
                {
                    byte abyte1[] = (byte[])(byte[])resource.templateVersionSet_;
                    templateVersionSet_ = Arrays.copyOf(abyte1, abyte1.length);
                }
            }
            if(resource.hasVersion())
            {
                bitField0_ = 8 | bitField0_;
                if(resource.version_ instanceof String)
                {
                    version_ = resource.version_;
                } else
                {
                    byte abyte0[] = (byte[])(byte[])resource.version_;
                    version_ = Arrays.copyOf(abyte0, abyte0.length);
                }
            }
            if(resource.hasLiveJsCacheOption())
            {
                ensureLiveJsCacheOptionInitialized();
                liveJsCacheOption_.mergeFrom(resource.getLiveJsCacheOption());
                bitField0_ = 0x10 | bitField0_;
            }
            if(resource.hasReportingSampleRate())
                setReportingSampleRate(resource.getReportingSampleRate());
            if(resource.usageContext_ != null && !resource.usageContext_.isEmpty())
            {
                ensureUsageContextInitialized();
                usageContext_.mergeFrom(resource.usageContext_);
            }
            if(resource.hasResourceFormatVersion())
                setResourceFormatVersion(resource.getResourceFormatVersion());
            if(resource.hasEnableAutoEventTracking())
                setEnableAutoEventTracking(resource.getEnableAutoEventTracking());
            unknownFields = unknownFields.concat(resource.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((Resource)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 17: default 180
        //                       0: 519
        //                       10: 199
        //                       18: 219
        //                       26: 231
        //                       34: 243
        //                       42: 255
        //                       50: 267
        //                       58: 279
        //                       74: 291
        //                       82: 312
        //                       98: 333
        //                       106: 354
        //                       114: 376
        //                       125: 416
        //                       130: 438
        //                       136: 458
        //                       144: 481;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            ensureKeyInitialized();
            key_.add(codedinputstream.readByteArray());
            continue; /* Loop/switch isn't completed */
_L6:
            codedinputstream.readMessage(addValue(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L7:
            codedinputstream.readMessage(addProperty(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L8:
            codedinputstream.readMessage(addMacro(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L9:
            codedinputstream.readMessage(addTag(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L10:
            codedinputstream.readMessage(addPredicate(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L11:
            codedinputstream.readMessage(addRule(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L12:
            bitField0_ = 1 | bitField0_;
            previewAuthCode_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L13:
            bitField0_ = 2 | bitField0_;
            malwareScanAuthCode_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L14:
            bitField0_ = 4 | bitField0_;
            templateVersionSet_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L15:
            bitField0_ = 8 | bitField0_;
            version_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L16:
            if(liveJsCacheOption_ == CacheOption.getDefaultInstance())
                liveJsCacheOption_ = CacheOption.newMessage();
            bitField0_ = 0x10 | bitField0_;
            codedinputstream.readMessage(liveJsCacheOption_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L17:
            bitField0_ = 0x20 | bitField0_;
            reportingSampleRate_ = codedinputstream.readFloat();
            continue; /* Loop/switch isn't completed */
_L18:
            ensureUsageContextInitialized();
            usageContext_.add(codedinputstream.readByteArray());
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
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L22; else goto _L21
_L22:
            break MISSING_BLOCK_LABEL_19;
_L21:
        }

        public Resource newMessageForType()
        {
            return new Resource();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public Resource setEnableAutoEventTracking(boolean flag)
        {
            assertMutable();
            bitField0_ = 0x40 | bitField0_;
            enableAutoEventTracking_ = flag;
            return this;
        }

        public Resource setKey(int i, String s)
        {
            assertMutable();
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureKeyInitialized();
                key_.set(i, s);
                return this;
            }
        }

        public Resource setKeyAsBytes(int i, byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureKeyInitialized();
                key_.set(i, abyte0);
                return this;
            }
        }

        public Resource setLiveJsCacheOption(CacheOption cacheoption)
        {
            assertMutable();
            if(cacheoption == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x10 | bitField0_;
                liveJsCacheOption_ = cacheoption;
                return this;
            }
        }

        public Resource setMacro(int i, FunctionCall functioncall)
        {
            assertMutable();
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureMacroInitialized();
                macro_.set(i, functioncall);
                return this;
            }
        }

        public Resource setMalwareScanAuthCode(String s)
        {
            assertMutable();
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                malwareScanAuthCode_ = s;
                return this;
            }
        }

        public Resource setMalwareScanAuthCodeAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                malwareScanAuthCode_ = abyte0;
                return this;
            }
        }

        public Resource setPredicate(int i, FunctionCall functioncall)
        {
            assertMutable();
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePredicateInitialized();
                predicate_.set(i, functioncall);
                return this;
            }
        }

        public Resource setPreviewAuthCode(String s)
        {
            assertMutable();
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                previewAuthCode_ = s;
                return this;
            }
        }

        public Resource setPreviewAuthCodeAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                previewAuthCode_ = abyte0;
                return this;
            }
        }

        public Resource setProperty(int i, Property property)
        {
            assertMutable();
            if(property == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertyInitialized();
                property_.set(i, property);
                return this;
            }
        }

        public Resource setReportingSampleRate(float f)
        {
            assertMutable();
            bitField0_ = 0x20 | bitField0_;
            reportingSampleRate_ = f;
            return this;
        }

        public Resource setResourceFormatVersion(int i)
        {
            assertMutable();
            bitField0_ = 0x80 | bitField0_;
            resourceFormatVersion_ = i;
            return this;
        }

        public Resource setRule(int i, Rule rule)
        {
            assertMutable();
            if(rule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRuleInitialized();
                rule_.set(i, rule);
                return this;
            }
        }

        public Resource setTag(int i, FunctionCall functioncall)
        {
            assertMutable();
            if(functioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureTagInitialized();
                tag_.set(i, functioncall);
                return this;
            }
        }

        public Resource setTemplateVersionSet(String s)
        {
            assertMutable();
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 4 | bitField0_;
                templateVersionSet_ = s;
                return this;
            }
        }

        public Resource setTemplateVersionSetAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 4 | bitField0_;
                templateVersionSet_ = abyte0;
                return this;
            }
        }

        public Resource setUsageContext(int i, String s)
        {
            assertMutable();
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureUsageContextInitialized();
                usageContext_.set(i, s);
                return this;
            }
        }

        public Resource setUsageContextAsBytes(int i, byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureUsageContextInitialized();
                usageContext_.set(i, abyte0);
                return this;
            }
        }

        public Resource setValue(int i, com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value)
        {
            assertMutable();
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureValueInitialized();
                value_.set(i, value);
                return this;
            }
        }

        public Resource setVersion(String s)
        {
            assertMutable();
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

        public Resource setVersionAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 8 | bitField0_;
                version_ = abyte0;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if(key_ != null)
            {
                for(int j2 = 0; j2 < key_.size(); j2++)
                    codedoutputstream.writeByteArray(1, key_.getByteArray(j2));

            }
            if(value_ != null)
            {
                for(int i2 = 0; i2 < value_.size(); i2++)
                    codedoutputstream.writeMessageWithCachedSizes(2, (MutableMessageLite)value_.get(i2));

            }
            if(property_ != null)
            {
                for(int l1 = 0; l1 < property_.size(); l1++)
                    codedoutputstream.writeMessageWithCachedSizes(3, (MutableMessageLite)property_.get(l1));

            }
            if(macro_ != null)
            {
                for(int k1 = 0; k1 < macro_.size(); k1++)
                    codedoutputstream.writeMessageWithCachedSizes(4, (MutableMessageLite)macro_.get(k1));

            }
            if(tag_ != null)
            {
                for(int j1 = 0; j1 < tag_.size(); j1++)
                    codedoutputstream.writeMessageWithCachedSizes(5, (MutableMessageLite)tag_.get(j1));

            }
            if(predicate_ != null)
            {
                for(int i1 = 0; i1 < predicate_.size(); i1++)
                    codedoutputstream.writeMessageWithCachedSizes(6, (MutableMessageLite)predicate_.get(i1));

            }
            if(rule_ != null)
            {
                for(int l = 0; l < rule_.size(); l++)
                    codedoutputstream.writeMessageWithCachedSizes(7, (MutableMessageLite)rule_.get(l));

            }
            if((1 & bitField0_) == 1)
                codedoutputstream.writeByteArray(9, getPreviewAuthCodeAsBytes());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeByteArray(10, getMalwareScanAuthCodeAsBytes());
            if((4 & bitField0_) == 4)
                codedoutputstream.writeByteArray(12, getTemplateVersionSetAsBytes());
            if((8 & bitField0_) == 8)
                codedoutputstream.writeByteArray(13, getVersionAsBytes());
            if((0x10 & bitField0_) == 16)
                codedoutputstream.writeMessageWithCachedSizes(14, liveJsCacheOption_);
            if((0x20 & bitField0_) == 32)
                codedoutputstream.writeFloat(15, reportingSampleRate_);
            if(usageContext_ != null)
            {
                for(int k = 0; k < usageContext_.size(); k++)
                    codedoutputstream.writeByteArray(16, usageContext_.getByteArray(k));

            }
            if((0x80 & bitField0_) == 128)
                codedoutputstream.writeInt32(17, resourceFormatVersion_);
            if((0x40 & bitField0_) == 64)
                codedoutputstream.writeBool(18, enableAutoEventTracking_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int ENABLE_AUTO_EVENT_TRACKING_FIELD_NUMBER = 18;
        public static final int KEY_FIELD_NUMBER = 1;
        public static final int LIVE_JS_CACHE_OPTION_FIELD_NUMBER = 14;
        public static final int MACRO_FIELD_NUMBER = 4;
        public static final int MALWARE_SCAN_AUTH_CODE_FIELD_NUMBER = 10;
        public static Parser PARSER;
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
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
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

        static 
        {
            defaultInstance = new Resource(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private Resource()
        {
            key_ = null;
            value_ = null;
            property_ = null;
            macro_ = null;
            tag_ = null;
            predicate_ = null;
            rule_ = null;
            previewAuthCode_ = Internal.EMPTY_BYTE_ARRAY;
            malwareScanAuthCode_ = Internal.EMPTY_BYTE_ARRAY;
            templateVersionSet_ = Internal.byteArrayDefaultValue("0");
            version_ = Internal.EMPTY_BYTE_ARRAY;
            usageContext_ = null;
            initFields();
        }

        private Resource(boolean flag)
        {
            key_ = null;
            value_ = null;
            property_ = null;
            macro_ = null;
            tag_ = null;
            predicate_ = null;
            rule_ = null;
            previewAuthCode_ = Internal.EMPTY_BYTE_ARRAY;
            malwareScanAuthCode_ = Internal.EMPTY_BYTE_ARRAY;
            templateVersionSet_ = Internal.byteArrayDefaultValue("0");
            version_ = Internal.EMPTY_BYTE_ARRAY;
            usageContext_ = null;
        }
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
            return (ResourceState)Enum.valueOf(com/google/analytics/containertag/proto/MutableServing$ResourceState, s);
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
            return (ResourceType)Enum.valueOf(com/google/analytics/containertag/proto/MutableServing$ResourceType, s);
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

    public static final class Rule extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureAddMacroInitialized()
        {
            if(addMacro_ == null)
                addMacro_ = new ArrayList();
        }

        private void ensureAddMacroRuleNameInitialized()
        {
            if(addMacroRuleName_ == null)
                addMacroRuleName_ = new ArrayList();
        }

        private void ensureAddTagInitialized()
        {
            if(addTag_ == null)
                addTag_ = new ArrayList();
        }

        private void ensureAddTagRuleNameInitialized()
        {
            if(addTagRuleName_ == null)
                addTagRuleName_ = new ArrayList();
        }

        private void ensureNegativePredicateInitialized()
        {
            if(negativePredicate_ == null)
                negativePredicate_ = new ArrayList();
        }

        private void ensurePositivePredicateInitialized()
        {
            if(positivePredicate_ == null)
                positivePredicate_ = new ArrayList();
        }

        private void ensureRemoveMacroInitialized()
        {
            if(removeMacro_ == null)
                removeMacro_ = new ArrayList();
        }

        private void ensureRemoveMacroRuleNameInitialized()
        {
            if(removeMacroRuleName_ == null)
                removeMacroRuleName_ = new ArrayList();
        }

        private void ensureRemoveTagInitialized()
        {
            if(removeTag_ == null)
                removeTag_ = new ArrayList();
        }

        private void ensureRemoveTagRuleNameInitialized()
        {
            if(removeTagRuleName_ == null)
                removeTagRuleName_ = new ArrayList();
        }

        public static Rule getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
        }

        public static Rule newMessage()
        {
            return new Rule();
        }

        public Rule addAddMacro(int i)
        {
            assertMutable();
            ensureAddMacroInitialized();
            addMacro_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addAddMacroRuleName(int i)
        {
            assertMutable();
            ensureAddMacroRuleNameInitialized();
            addMacroRuleName_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addAddTag(int i)
        {
            assertMutable();
            ensureAddTagInitialized();
            addTag_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addAddTagRuleName(int i)
        {
            assertMutable();
            ensureAddTagRuleNameInitialized();
            addTagRuleName_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addAllAddMacro(Iterable iterable)
        {
            assertMutable();
            ensureAddMacroInitialized();
            AbstractMutableMessageLite.addAll(iterable, addMacro_);
            return this;
        }

        public Rule addAllAddMacroRuleName(Iterable iterable)
        {
            assertMutable();
            ensureAddMacroRuleNameInitialized();
            AbstractMutableMessageLite.addAll(iterable, addMacroRuleName_);
            return this;
        }

        public Rule addAllAddTag(Iterable iterable)
        {
            assertMutable();
            ensureAddTagInitialized();
            AbstractMutableMessageLite.addAll(iterable, addTag_);
            return this;
        }

        public Rule addAllAddTagRuleName(Iterable iterable)
        {
            assertMutable();
            ensureAddTagRuleNameInitialized();
            AbstractMutableMessageLite.addAll(iterable, addTagRuleName_);
            return this;
        }

        public Rule addAllNegativePredicate(Iterable iterable)
        {
            assertMutable();
            ensureNegativePredicateInitialized();
            AbstractMutableMessageLite.addAll(iterable, negativePredicate_);
            return this;
        }

        public Rule addAllPositivePredicate(Iterable iterable)
        {
            assertMutable();
            ensurePositivePredicateInitialized();
            AbstractMutableMessageLite.addAll(iterable, positivePredicate_);
            return this;
        }

        public Rule addAllRemoveMacro(Iterable iterable)
        {
            assertMutable();
            ensureRemoveMacroInitialized();
            AbstractMutableMessageLite.addAll(iterable, removeMacro_);
            return this;
        }

        public Rule addAllRemoveMacroRuleName(Iterable iterable)
        {
            assertMutable();
            ensureRemoveMacroRuleNameInitialized();
            AbstractMutableMessageLite.addAll(iterable, removeMacroRuleName_);
            return this;
        }

        public Rule addAllRemoveTag(Iterable iterable)
        {
            assertMutable();
            ensureRemoveTagInitialized();
            AbstractMutableMessageLite.addAll(iterable, removeTag_);
            return this;
        }

        public Rule addAllRemoveTagRuleName(Iterable iterable)
        {
            assertMutable();
            ensureRemoveTagRuleNameInitialized();
            AbstractMutableMessageLite.addAll(iterable, removeTagRuleName_);
            return this;
        }

        public Rule addNegativePredicate(int i)
        {
            assertMutable();
            ensureNegativePredicateInitialized();
            negativePredicate_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addPositivePredicate(int i)
        {
            assertMutable();
            ensurePositivePredicateInitialized();
            positivePredicate_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addRemoveMacro(int i)
        {
            assertMutable();
            ensureRemoveMacroInitialized();
            removeMacro_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addRemoveMacroRuleName(int i)
        {
            assertMutable();
            ensureRemoveMacroRuleNameInitialized();
            removeMacroRuleName_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addRemoveTag(int i)
        {
            assertMutable();
            ensureRemoveTagInitialized();
            removeTag_.add(Integer.valueOf(i));
            return this;
        }

        public Rule addRemoveTagRuleName(int i)
        {
            assertMutable();
            ensureRemoveTagRuleNameInitialized();
            removeTagRuleName_.add(Integer.valueOf(i));
            return this;
        }

        public Rule clear()
        {
            assertMutable();
            super.clear();
            positivePredicate_ = null;
            negativePredicate_ = null;
            addTag_ = null;
            removeTag_ = null;
            addTagRuleName_ = null;
            removeTagRuleName_ = null;
            addMacro_ = null;
            removeMacro_ = null;
            addMacroRuleName_ = null;
            removeMacroRuleName_ = null;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public Rule clearAddMacro()
        {
            assertMutable();
            addMacro_ = null;
            return this;
        }

        public Rule clearAddMacroRuleName()
        {
            assertMutable();
            addMacroRuleName_ = null;
            return this;
        }

        public Rule clearAddTag()
        {
            assertMutable();
            addTag_ = null;
            return this;
        }

        public Rule clearAddTagRuleName()
        {
            assertMutable();
            addTagRuleName_ = null;
            return this;
        }

        public Rule clearNegativePredicate()
        {
            assertMutable();
            negativePredicate_ = null;
            return this;
        }

        public Rule clearPositivePredicate()
        {
            assertMutable();
            positivePredicate_ = null;
            return this;
        }

        public Rule clearRemoveMacro()
        {
            assertMutable();
            removeMacro_ = null;
            return this;
        }

        public Rule clearRemoveMacroRuleName()
        {
            assertMutable();
            removeMacroRuleName_ = null;
            return this;
        }

        public Rule clearRemoveTag()
        {
            assertMutable();
            removeTag_ = null;
            return this;
        }

        public Rule clearRemoveTagRuleName()
        {
            assertMutable();
            removeTagRuleName_ = null;
            return this;
        }

        public Rule clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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
            if(addMacro_ == null)
                return 0;
            else
                return addMacro_.size();
        }

        public List getAddMacroList()
        {
            if(addMacro_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(addMacro_);
        }

        public int getAddMacroRuleName(int i)
        {
            return ((Integer)addMacroRuleName_.get(i)).intValue();
        }

        public int getAddMacroRuleNameCount()
        {
            if(addMacroRuleName_ == null)
                return 0;
            else
                return addMacroRuleName_.size();
        }

        public List getAddMacroRuleNameList()
        {
            if(addMacroRuleName_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(addMacroRuleName_);
        }

        public int getAddTag(int i)
        {
            return ((Integer)addTag_.get(i)).intValue();
        }

        public int getAddTagCount()
        {
            if(addTag_ == null)
                return 0;
            else
                return addTag_.size();
        }

        public List getAddTagList()
        {
            if(addTag_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(addTag_);
        }

        public int getAddTagRuleName(int i)
        {
            return ((Integer)addTagRuleName_.get(i)).intValue();
        }

        public int getAddTagRuleNameCount()
        {
            if(addTagRuleName_ == null)
                return 0;
            else
                return addTagRuleName_.size();
        }

        public List getAddTagRuleNameList()
        {
            if(addTagRuleName_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(addTagRuleName_);
        }

        public final Rule getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public List getMutableAddMacroList()
        {
            assertMutable();
            ensureAddMacroInitialized();
            return addMacro_;
        }

        public List getMutableAddMacroRuleNameList()
        {
            assertMutable();
            ensureAddMacroRuleNameInitialized();
            return addMacroRuleName_;
        }

        public List getMutableAddTagList()
        {
            assertMutable();
            ensureAddTagInitialized();
            return addTag_;
        }

        public List getMutableAddTagRuleNameList()
        {
            assertMutable();
            ensureAddTagRuleNameInitialized();
            return addTagRuleName_;
        }

        public List getMutableNegativePredicateList()
        {
            assertMutable();
            ensureNegativePredicateInitialized();
            return negativePredicate_;
        }

        public List getMutablePositivePredicateList()
        {
            assertMutable();
            ensurePositivePredicateInitialized();
            return positivePredicate_;
        }

        public List getMutableRemoveMacroList()
        {
            assertMutable();
            ensureRemoveMacroInitialized();
            return removeMacro_;
        }

        public List getMutableRemoveMacroRuleNameList()
        {
            assertMutable();
            ensureRemoveMacroRuleNameInitialized();
            return removeMacroRuleName_;
        }

        public List getMutableRemoveTagList()
        {
            assertMutable();
            ensureRemoveTagInitialized();
            return removeTag_;
        }

        public List getMutableRemoveTagRuleNameList()
        {
            assertMutable();
            ensureRemoveTagRuleNameInitialized();
            return removeTagRuleName_;
        }

        public int getNegativePredicate(int i)
        {
            return ((Integer)negativePredicate_.get(i)).intValue();
        }

        public int getNegativePredicateCount()
        {
            if(negativePredicate_ == null)
                return 0;
            else
                return negativePredicate_.size();
        }

        public List getNegativePredicateList()
        {
            if(negativePredicate_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(negativePredicate_);
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
            if(positivePredicate_ == null)
                return 0;
            else
                return positivePredicate_.size();
        }

        public List getPositivePredicateList()
        {
            if(positivePredicate_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(positivePredicate_);
        }

        public int getRemoveMacro(int i)
        {
            return ((Integer)removeMacro_.get(i)).intValue();
        }

        public int getRemoveMacroCount()
        {
            if(removeMacro_ == null)
                return 0;
            else
                return removeMacro_.size();
        }

        public List getRemoveMacroList()
        {
            if(removeMacro_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(removeMacro_);
        }

        public int getRemoveMacroRuleName(int i)
        {
            return ((Integer)removeMacroRuleName_.get(i)).intValue();
        }

        public int getRemoveMacroRuleNameCount()
        {
            if(removeMacroRuleName_ == null)
                return 0;
            else
                return removeMacroRuleName_.size();
        }

        public List getRemoveMacroRuleNameList()
        {
            if(removeMacroRuleName_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(removeMacroRuleName_);
        }

        public int getRemoveTag(int i)
        {
            return ((Integer)removeTag_.get(i)).intValue();
        }

        public int getRemoveTagCount()
        {
            if(removeTag_ == null)
                return 0;
            else
                return removeTag_.size();
        }

        public List getRemoveTagList()
        {
            if(removeTag_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(removeTag_);
        }

        public int getRemoveTagRuleName(int i)
        {
            return ((Integer)removeTagRuleName_.get(i)).intValue();
        }

        public int getRemoveTagRuleNameCount()
        {
            if(removeTagRuleName_ == null)
                return 0;
            else
                return removeTagRuleName_.size();
        }

        public List getRemoveTagRuleNameList()
        {
            if(removeTagRuleName_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(removeTagRuleName_);
        }

        public int getSerializedSize()
        {
            List list = positivePredicate_;
            int i = 0;
            if(list != null)
            {
                int i5 = positivePredicate_.size();
                i = 0;
                if(i5 > 0)
                {
                    int j5 = 0;
                    for(int k5 = 0; k5 < positivePredicate_.size(); k5++)
                        j5 += CodedOutputStream.computeInt32SizeNoTag(((Integer)positivePredicate_.get(k5)).intValue());

                    i = 0 + j5 + 1 * getPositivePredicateList().size();
                }
            }
            if(negativePredicate_ != null && negativePredicate_.size() > 0)
            {
                int k4 = 0;
                for(int l4 = 0; l4 < negativePredicate_.size(); l4++)
                    k4 += CodedOutputStream.computeInt32SizeNoTag(((Integer)negativePredicate_.get(l4)).intValue());

                i = i + k4 + 1 * getNegativePredicateList().size();
            }
            if(addTag_ != null && addTag_.size() > 0)
            {
                int i4 = 0;
                for(int j4 = 0; j4 < addTag_.size(); j4++)
                    i4 += CodedOutputStream.computeInt32SizeNoTag(((Integer)addTag_.get(j4)).intValue());

                i = i + i4 + 1 * getAddTagList().size();
            }
            if(removeTag_ != null && removeTag_.size() > 0)
            {
                int k3 = 0;
                for(int l3 = 0; l3 < removeTag_.size(); l3++)
                    k3 += CodedOutputStream.computeInt32SizeNoTag(((Integer)removeTag_.get(l3)).intValue());

                i = i + k3 + 1 * getRemoveTagList().size();
            }
            if(addTagRuleName_ != null && addTagRuleName_.size() > 0)
            {
                int i3 = 0;
                for(int j3 = 0; j3 < addTagRuleName_.size(); j3++)
                    i3 += CodedOutputStream.computeInt32SizeNoTag(((Integer)addTagRuleName_.get(j3)).intValue());

                i = i + i3 + 1 * getAddTagRuleNameList().size();
            }
            if(removeTagRuleName_ != null && removeTagRuleName_.size() > 0)
            {
                int k2 = 0;
                for(int l2 = 0; l2 < removeTagRuleName_.size(); l2++)
                    k2 += CodedOutputStream.computeInt32SizeNoTag(((Integer)removeTagRuleName_.get(l2)).intValue());

                i = i + k2 + 1 * getRemoveTagRuleNameList().size();
            }
            if(addMacro_ != null && addMacro_.size() > 0)
            {
                int i2 = 0;
                for(int j2 = 0; j2 < addMacro_.size(); j2++)
                    i2 += CodedOutputStream.computeInt32SizeNoTag(((Integer)addMacro_.get(j2)).intValue());

                i = i + i2 + 1 * getAddMacroList().size();
            }
            if(removeMacro_ != null && removeMacro_.size() > 0)
            {
                int k1 = 0;
                for(int l1 = 0; l1 < removeMacro_.size(); l1++)
                    k1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)removeMacro_.get(l1)).intValue());

                i = i + k1 + 1 * getRemoveMacroList().size();
            }
            if(addMacroRuleName_ != null && addMacroRuleName_.size() > 0)
            {
                int i1 = 0;
                for(int j1 = 0; j1 < addMacroRuleName_.size(); j1++)
                    i1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)addMacroRuleName_.get(j1)).intValue());

                i = i + i1 + 1 * getAddMacroRuleNameList().size();
            }
            if(removeMacroRuleName_ != null && removeMacroRuleName_.size() > 0)
            {
                int k = 0;
                for(int l = 0; l < removeMacroRuleName_.size(); l++)
                    k += CodedOutputStream.computeInt32SizeNoTag(((Integer)removeMacroRuleName_.get(l)).intValue());

                i = i + k + 1 * getRemoveMacroRuleNameList().size();
            }
            int j = i + unknownFields.size();
            cachedSize = j;
            return j;
        }

        public int hashCode()
        {
            int i = 41;
            if(getPositivePredicateCount() > 0)
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getPositivePredicateList().hashCode();
            }
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
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Serving$Rule");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            return true;
        }

        public Rule mergeFrom(Rule rule)
        {
            if(this == rule)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(rule == getDefaultInstance())
                return this;
            if(rule.positivePredicate_ != null && !rule.positivePredicate_.isEmpty())
            {
                ensurePositivePredicateInitialized();
                positivePredicate_.addAll(rule.positivePredicate_);
            }
            if(rule.negativePredicate_ != null && !rule.negativePredicate_.isEmpty())
            {
                ensureNegativePredicateInitialized();
                negativePredicate_.addAll(rule.negativePredicate_);
            }
            if(rule.addTag_ != null && !rule.addTag_.isEmpty())
            {
                ensureAddTagInitialized();
                addTag_.addAll(rule.addTag_);
            }
            if(rule.removeTag_ != null && !rule.removeTag_.isEmpty())
            {
                ensureRemoveTagInitialized();
                removeTag_.addAll(rule.removeTag_);
            }
            if(rule.addTagRuleName_ != null && !rule.addTagRuleName_.isEmpty())
            {
                ensureAddTagRuleNameInitialized();
                addTagRuleName_.addAll(rule.addTagRuleName_);
            }
            if(rule.removeTagRuleName_ != null && !rule.removeTagRuleName_.isEmpty())
            {
                ensureRemoveTagRuleNameInitialized();
                removeTagRuleName_.addAll(rule.removeTagRuleName_);
            }
            if(rule.addMacro_ != null && !rule.addMacro_.isEmpty())
            {
                ensureAddMacroInitialized();
                addMacro_.addAll(rule.addMacro_);
            }
            if(rule.removeMacro_ != null && !rule.removeMacro_.isEmpty())
            {
                ensureRemoveMacroInitialized();
                removeMacro_.addAll(rule.removeMacro_);
            }
            if(rule.addMacroRuleName_ != null && !rule.addMacroRuleName_.isEmpty())
            {
                ensureAddMacroRuleNameInitialized();
                addMacroRuleName_.addAll(rule.addMacroRuleName_);
            }
            if(rule.removeMacroRuleName_ != null && !rule.removeMacroRuleName_.isEmpty())
            {
                ensureRemoveMacroRuleNameInitialized();
                removeMacroRuleName_.addAll(rule.removeMacroRuleName_);
            }
            unknownFields = unknownFields.concat(rule.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((Rule)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            int j;
            int k;
            int l;
            int i1;
            int j1;
            int k1;
            int l1;
            int i2;
            int j2;
            int k2;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 21: default 212
        //                       0: 1267
        //                       8: 231
        //                       10: 269
        //                       16: 333
        //                       18: 371
        //                       24: 435
        //                       26: 473
        //                       32: 537
        //                       34: 575
        //                       40: 639
        //                       42: 677
        //                       48: 741
        //                       50: 779
        //                       56: 843
        //                       58: 881
        //                       64: 945
        //                       66: 983
        //                       72: 1047
        //                       74: 1085
        //                       80: 1149
        //                       82: 1187;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if(positivePredicate_ == null)
                positivePredicate_ = new ArrayList();
            positivePredicate_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L6:
            k2 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(positivePredicate_ == null)
                positivePredicate_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; positivePredicate_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(k2);
            continue; /* Loop/switch isn't completed */
_L7:
            if(negativePredicate_ == null)
                negativePredicate_ = new ArrayList();
            negativePredicate_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L8:
            j2 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(negativePredicate_ == null)
                negativePredicate_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; negativePredicate_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(j2);
            continue; /* Loop/switch isn't completed */
_L9:
            if(addTag_ == null)
                addTag_ = new ArrayList();
            addTag_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L10:
            i2 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(addTag_ == null)
                addTag_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; addTag_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(i2);
            continue; /* Loop/switch isn't completed */
_L11:
            if(removeTag_ == null)
                removeTag_ = new ArrayList();
            removeTag_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L12:
            l1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(removeTag_ == null)
                removeTag_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; removeTag_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(l1);
            continue; /* Loop/switch isn't completed */
_L13:
            if(addTagRuleName_ == null)
                addTagRuleName_ = new ArrayList();
            addTagRuleName_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L14:
            k1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(addTagRuleName_ == null)
                addTagRuleName_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; addTagRuleName_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(k1);
            continue; /* Loop/switch isn't completed */
_L15:
            if(removeTagRuleName_ == null)
                removeTagRuleName_ = new ArrayList();
            removeTagRuleName_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L16:
            j1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(removeTagRuleName_ == null)
                removeTagRuleName_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; removeTagRuleName_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(j1);
            continue; /* Loop/switch isn't completed */
_L17:
            if(addMacro_ == null)
                addMacro_ = new ArrayList();
            addMacro_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L18:
            i1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(addMacro_ == null)
                addMacro_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; addMacro_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(i1);
            continue; /* Loop/switch isn't completed */
_L19:
            if(removeMacro_ == null)
                removeMacro_ = new ArrayList();
            removeMacro_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L20:
            l = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(removeMacro_ == null)
                removeMacro_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; removeMacro_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(l);
            continue; /* Loop/switch isn't completed */
_L21:
            if(addMacroRuleName_ == null)
                addMacroRuleName_ = new ArrayList();
            addMacroRuleName_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L22:
            k = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(addMacroRuleName_ == null)
                addMacroRuleName_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; addMacroRuleName_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(k);
            continue; /* Loop/switch isn't completed */
_L23:
            if(removeMacroRuleName_ == null)
                removeMacroRuleName_ = new ArrayList();
            removeMacroRuleName_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L24:
            j = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(removeMacroRuleName_ == null)
                removeMacroRuleName_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; removeMacroRuleName_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(j);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L26; else goto _L25
_L26:
            break MISSING_BLOCK_LABEL_19;
_L25:
        }

        public Rule newMessageForType()
        {
            return new Rule();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public Rule setAddMacro(int i, int j)
        {
            assertMutable();
            ensureAddMacroInitialized();
            addMacro_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setAddMacroRuleName(int i, int j)
        {
            assertMutable();
            ensureAddMacroRuleNameInitialized();
            addMacroRuleName_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setAddTag(int i, int j)
        {
            assertMutable();
            ensureAddTagInitialized();
            addTag_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setAddTagRuleName(int i, int j)
        {
            assertMutable();
            ensureAddTagRuleNameInitialized();
            addTagRuleName_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setNegativePredicate(int i, int j)
        {
            assertMutable();
            ensureNegativePredicateInitialized();
            negativePredicate_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setPositivePredicate(int i, int j)
        {
            assertMutable();
            ensurePositivePredicateInitialized();
            positivePredicate_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setRemoveMacro(int i, int j)
        {
            assertMutable();
            ensureRemoveMacroInitialized();
            removeMacro_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setRemoveMacroRuleName(int i, int j)
        {
            assertMutable();
            ensureRemoveMacroRuleNameInitialized();
            removeMacroRuleName_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setRemoveTag(int i, int j)
        {
            assertMutable();
            ensureRemoveTagInitialized();
            removeTag_.set(i, Integer.valueOf(j));
            return this;
        }

        public Rule setRemoveTagRuleName(int i, int j)
        {
            assertMutable();
            ensureRemoveTagRuleNameInitialized();
            removeTagRuleName_.set(i, Integer.valueOf(j));
            return this;
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if(positivePredicate_ != null)
            {
                for(int l2 = 0; l2 < positivePredicate_.size(); l2++)
                    codedoutputstream.writeInt32(1, ((Integer)positivePredicate_.get(l2)).intValue());

            }
            if(negativePredicate_ != null)
            {
                for(int k2 = 0; k2 < negativePredicate_.size(); k2++)
                    codedoutputstream.writeInt32(2, ((Integer)negativePredicate_.get(k2)).intValue());

            }
            if(addTag_ != null)
            {
                for(int j2 = 0; j2 < addTag_.size(); j2++)
                    codedoutputstream.writeInt32(3, ((Integer)addTag_.get(j2)).intValue());

            }
            if(removeTag_ != null)
            {
                for(int i2 = 0; i2 < removeTag_.size(); i2++)
                    codedoutputstream.writeInt32(4, ((Integer)removeTag_.get(i2)).intValue());

            }
            if(addTagRuleName_ != null)
            {
                for(int l1 = 0; l1 < addTagRuleName_.size(); l1++)
                    codedoutputstream.writeInt32(5, ((Integer)addTagRuleName_.get(l1)).intValue());

            }
            if(removeTagRuleName_ != null)
            {
                for(int k1 = 0; k1 < removeTagRuleName_.size(); k1++)
                    codedoutputstream.writeInt32(6, ((Integer)removeTagRuleName_.get(k1)).intValue());

            }
            if(addMacro_ != null)
            {
                for(int j1 = 0; j1 < addMacro_.size(); j1++)
                    codedoutputstream.writeInt32(7, ((Integer)addMacro_.get(j1)).intValue());

            }
            if(removeMacro_ != null)
            {
                for(int i1 = 0; i1 < removeMacro_.size(); i1++)
                    codedoutputstream.writeInt32(8, ((Integer)removeMacro_.get(i1)).intValue());

            }
            if(addMacroRuleName_ != null)
            {
                for(int l = 0; l < addMacroRuleName_.size(); l++)
                    codedoutputstream.writeInt32(9, ((Integer)addMacroRuleName_.get(l)).intValue());

            }
            if(removeMacroRuleName_ != null)
            {
                for(int k = 0; k < removeMacroRuleName_.size(); k++)
                    codedoutputstream.writeInt32(10, ((Integer)removeMacroRuleName_.get(k)).intValue());

            }
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int ADD_MACRO_FIELD_NUMBER = 7;
        public static final int ADD_MACRO_RULE_NAME_FIELD_NUMBER = 9;
        public static final int ADD_TAG_FIELD_NUMBER = 3;
        public static final int ADD_TAG_RULE_NAME_FIELD_NUMBER = 5;
        public static final int NEGATIVE_PREDICATE_FIELD_NUMBER = 2;
        public static Parser PARSER;
        public static final int POSITIVE_PREDICATE_FIELD_NUMBER = 1;
        public static final int REMOVE_MACRO_FIELD_NUMBER = 8;
        public static final int REMOVE_MACRO_RULE_NAME_FIELD_NUMBER = 10;
        public static final int REMOVE_TAG_FIELD_NUMBER = 4;
        public static final int REMOVE_TAG_RULE_NAME_FIELD_NUMBER = 6;
        private static final Rule defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private List addMacroRuleName_;
        private List addMacro_;
        private List addTagRuleName_;
        private List addTag_;
        private List negativePredicate_;
        private List positivePredicate_;
        private List removeMacroRuleName_;
        private List removeMacro_;
        private List removeTagRuleName_;
        private List removeTag_;

        static 
        {
            defaultInstance = new Rule(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private Rule()
        {
            positivePredicate_ = null;
            negativePredicate_ = null;
            addTag_ = null;
            removeTag_ = null;
            addTagRuleName_ = null;
            removeTagRuleName_ = null;
            addMacro_ = null;
            removeMacro_ = null;
            addMacroRuleName_ = null;
            removeMacroRuleName_ = null;
            initFields();
        }

        private Rule(boolean flag)
        {
            positivePredicate_ = null;
            negativePredicate_ = null;
            addTag_ = null;
            removeTag_ = null;
            addTagRuleName_ = null;
            removeTagRuleName_ = null;
            addMacro_ = null;
            removeMacro_ = null;
            addMacroRuleName_ = null;
            removeMacroRuleName_ = null;
        }
    }

    public static final class ServingValue extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureListItemInitialized()
        {
            if(listItem_ == null)
                listItem_ = new ArrayList();
        }

        private void ensureMapKeyInitialized()
        {
            if(mapKey_ == null)
                mapKey_ = new ArrayList();
        }

        private void ensureMapValueInitialized()
        {
            if(mapValue_ == null)
                mapValue_ = new ArrayList();
        }

        private void ensureTemplateTokenInitialized()
        {
            if(templateToken_ == null)
                templateToken_ = new ArrayList();
        }

        public static ServingValue getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
        }

        public static ServingValue newMessage()
        {
            return new ServingValue();
        }

        public ServingValue addAllListItem(Iterable iterable)
        {
            assertMutable();
            ensureListItemInitialized();
            AbstractMutableMessageLite.addAll(iterable, listItem_);
            return this;
        }

        public ServingValue addAllMapKey(Iterable iterable)
        {
            assertMutable();
            ensureMapKeyInitialized();
            AbstractMutableMessageLite.addAll(iterable, mapKey_);
            return this;
        }

        public ServingValue addAllMapValue(Iterable iterable)
        {
            assertMutable();
            ensureMapValueInitialized();
            AbstractMutableMessageLite.addAll(iterable, mapValue_);
            return this;
        }

        public ServingValue addAllTemplateToken(Iterable iterable)
        {
            assertMutable();
            ensureTemplateTokenInitialized();
            AbstractMutableMessageLite.addAll(iterable, templateToken_);
            return this;
        }

        public ServingValue addListItem(int i)
        {
            assertMutable();
            ensureListItemInitialized();
            listItem_.add(Integer.valueOf(i));
            return this;
        }

        public ServingValue addMapKey(int i)
        {
            assertMutable();
            ensureMapKeyInitialized();
            mapKey_.add(Integer.valueOf(i));
            return this;
        }

        public ServingValue addMapValue(int i)
        {
            assertMutable();
            ensureMapValueInitialized();
            mapValue_.add(Integer.valueOf(i));
            return this;
        }

        public ServingValue addTemplateToken(int i)
        {
            assertMutable();
            ensureTemplateTokenInitialized();
            templateToken_.add(Integer.valueOf(i));
            return this;
        }

        public ServingValue clear()
        {
            assertMutable();
            super.clear();
            listItem_ = null;
            mapKey_ = null;
            mapValue_ = null;
            macroReference_ = 0;
            bitField0_ = -2 & bitField0_;
            templateToken_ = null;
            macroNameReference_ = 0;
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public ServingValue clearListItem()
        {
            assertMutable();
            listItem_ = null;
            return this;
        }

        public ServingValue clearMacroNameReference()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            macroNameReference_ = 0;
            return this;
        }

        public ServingValue clearMacroReference()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            macroReference_ = 0;
            return this;
        }

        public ServingValue clearMapKey()
        {
            assertMutable();
            mapKey_ = null;
            return this;
        }

        public ServingValue clearMapValue()
        {
            assertMutable();
            mapValue_ = null;
            return this;
        }

        public ServingValue clearTemplateToken()
        {
            assertMutable();
            templateToken_ = null;
            return this;
        }

        public ServingValue clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final ServingValue getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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
            if(listItem_ == null)
                return 0;
            else
                return listItem_.size();
        }

        public List getListItemList()
        {
            if(listItem_ == null)
                return Collections.emptyList();
            else
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
            if(mapKey_ == null)
                return 0;
            else
                return mapKey_.size();
        }

        public List getMapKeyList()
        {
            if(mapKey_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(mapKey_);
        }

        public int getMapValue(int i)
        {
            return ((Integer)mapValue_.get(i)).intValue();
        }

        public int getMapValueCount()
        {
            if(mapValue_ == null)
                return 0;
            else
                return mapValue_.size();
        }

        public List getMapValueList()
        {
            if(mapValue_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(mapValue_);
        }

        public List getMutableListItemList()
        {
            assertMutable();
            ensureListItemInitialized();
            return listItem_;
        }

        public List getMutableMapKeyList()
        {
            assertMutable();
            ensureMapKeyInitialized();
            return mapKey_;
        }

        public List getMutableMapValueList()
        {
            assertMutable();
            ensureMapValueInitialized();
            return mapValue_;
        }

        public List getMutableTemplateTokenList()
        {
            assertMutable();
            ensureTemplateTokenInitialized();
            return templateToken_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            List list = listItem_;
            int i = 0;
            if(list != null)
            {
                int i2 = listItem_.size();
                i = 0;
                if(i2 > 0)
                {
                    int j2 = 0;
                    for(int k2 = 0; k2 < listItem_.size(); k2++)
                        j2 += CodedOutputStream.computeInt32SizeNoTag(((Integer)listItem_.get(k2)).intValue());

                    i = 0 + j2 + 1 * getListItemList().size();
                }
            }
            if(mapKey_ != null && mapKey_.size() > 0)
            {
                int k1 = 0;
                for(int l1 = 0; l1 < mapKey_.size(); l1++)
                    k1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)mapKey_.get(l1)).intValue());

                i = i + k1 + 1 * getMapKeyList().size();
            }
            if(mapValue_ != null && mapValue_.size() > 0)
            {
                int i1 = 0;
                for(int j1 = 0; j1 < mapValue_.size(); j1++)
                    i1 += CodedOutputStream.computeInt32SizeNoTag(((Integer)mapValue_.get(j1)).intValue());

                i = i + i1 + 1 * getMapValueList().size();
            }
            if((1 & bitField0_) == 1)
                i += CodedOutputStream.computeInt32Size(4, macroReference_);
            if(templateToken_ != null && templateToken_.size() > 0)
            {
                int k = 0;
                for(int l = 0; l < templateToken_.size(); l++)
                    k += CodedOutputStream.computeInt32SizeNoTag(((Integer)templateToken_.get(l)).intValue());

                i = i + k + 1 * getTemplateTokenList().size();
            }
            if((2 & bitField0_) == 2)
                i += CodedOutputStream.computeInt32Size(6, macroNameReference_);
            int j = i + unknownFields.size();
            cachedSize = j;
            return j;
        }

        public int getTemplateToken(int i)
        {
            return ((Integer)templateToken_.get(i)).intValue();
        }

        public int getTemplateTokenCount()
        {
            if(templateToken_ == null)
                return 0;
            else
                return templateToken_.size();
        }

        public List getTemplateTokenList()
        {
            if(templateToken_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(templateToken_);
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
            int i = 41;
            if(getListItemCount() > 0)
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getListItemList().hashCode();
            }
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
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Serving$ServingValue");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            return true;
        }

        public ServingValue mergeFrom(ServingValue servingvalue)
        {
            if(this == servingvalue)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(servingvalue == getDefaultInstance())
                return this;
            if(servingvalue.listItem_ != null && !servingvalue.listItem_.isEmpty())
            {
                ensureListItemInitialized();
                listItem_.addAll(servingvalue.listItem_);
            }
            if(servingvalue.mapKey_ != null && !servingvalue.mapKey_.isEmpty())
            {
                ensureMapKeyInitialized();
                mapKey_.addAll(servingvalue.mapKey_);
            }
            if(servingvalue.mapValue_ != null && !servingvalue.mapValue_.isEmpty())
            {
                ensureMapValueInitialized();
                mapValue_.addAll(servingvalue.mapValue_);
            }
            if(servingvalue.hasMacroReference())
                setMacroReference(servingvalue.getMacroReference());
            if(servingvalue.templateToken_ != null && !servingvalue.templateToken_.isEmpty())
            {
                ensureTemplateTokenInitialized();
                templateToken_.addAll(servingvalue.templateToken_);
            }
            if(servingvalue.hasMacroNameReference())
                setMacroNameReference(servingvalue.getMacroNameReference());
            unknownFields = unknownFields.concat(servingvalue.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((ServingValue)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            int j;
            int k;
            int l;
            int i1;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 11: default 132
        //                       0: 617
        //                       8: 151
        //                       10: 189
        //                       16: 253
        //                       18: 291
        //                       24: 355
        //                       26: 393
        //                       32: 457
        //                       40: 478
        //                       42: 516
        //                       48: 580;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if(listItem_ == null)
                listItem_ = new ArrayList();
            listItem_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L6:
            i1 = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(listItem_ == null)
                listItem_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; listItem_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(i1);
            continue; /* Loop/switch isn't completed */
_L7:
            if(mapKey_ == null)
                mapKey_ = new ArrayList();
            mapKey_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L8:
            l = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(mapKey_ == null)
                mapKey_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; mapKey_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(l);
            continue; /* Loop/switch isn't completed */
_L9:
            if(mapValue_ == null)
                mapValue_ = new ArrayList();
            mapValue_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L10:
            k = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(mapValue_ == null)
                mapValue_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; mapValue_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(k);
            continue; /* Loop/switch isn't completed */
_L11:
            bitField0_ = 1 | bitField0_;
            macroReference_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L12:
            if(templateToken_ == null)
                templateToken_ = new ArrayList();
            templateToken_.add(Integer.valueOf(codedinputstream.readInt32()));
            continue; /* Loop/switch isn't completed */
_L13:
            j = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
            if(templateToken_ == null)
                templateToken_ = new ArrayList();
            for(; codedinputstream.getBytesUntilLimit() > 0; templateToken_.add(Integer.valueOf(codedinputstream.readInt32())));
            codedinputstream.popLimit(j);
            continue; /* Loop/switch isn't completed */
_L14:
            bitField0_ = 2 | bitField0_;
            macroNameReference_ = codedinputstream.readInt32();
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L16; else goto _L15
_L16:
            break MISSING_BLOCK_LABEL_19;
_L15:
        }

        public ServingValue newMessageForType()
        {
            return new ServingValue();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public ServingValue setListItem(int i, int j)
        {
            assertMutable();
            ensureListItemInitialized();
            listItem_.set(i, Integer.valueOf(j));
            return this;
        }

        public ServingValue setMacroNameReference(int i)
        {
            assertMutable();
            bitField0_ = 2 | bitField0_;
            macroNameReference_ = i;
            return this;
        }

        public ServingValue setMacroReference(int i)
        {
            assertMutable();
            bitField0_ = 1 | bitField0_;
            macroReference_ = i;
            return this;
        }

        public ServingValue setMapKey(int i, int j)
        {
            assertMutable();
            ensureMapKeyInitialized();
            mapKey_.set(i, Integer.valueOf(j));
            return this;
        }

        public ServingValue setMapValue(int i, int j)
        {
            assertMutable();
            ensureMapValueInitialized();
            mapValue_.set(i, Integer.valueOf(j));
            return this;
        }

        public ServingValue setTemplateToken(int i, int j)
        {
            assertMutable();
            ensureTemplateTokenInitialized();
            templateToken_.set(i, Integer.valueOf(j));
            return this;
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if(listItem_ != null)
            {
                for(int j1 = 0; j1 < listItem_.size(); j1++)
                    codedoutputstream.writeInt32(1, ((Integer)listItem_.get(j1)).intValue());

            }
            if(mapKey_ != null)
            {
                for(int i1 = 0; i1 < mapKey_.size(); i1++)
                    codedoutputstream.writeInt32(2, ((Integer)mapKey_.get(i1)).intValue());

            }
            if(mapValue_ != null)
            {
                for(int l = 0; l < mapValue_.size(); l++)
                    codedoutputstream.writeInt32(3, ((Integer)mapValue_.get(l)).intValue());

            }
            if((1 & bitField0_) == 1)
                codedoutputstream.writeInt32(4, macroReference_);
            if(templateToken_ != null)
            {
                for(int k = 0; k < templateToken_.size(); k++)
                    codedoutputstream.writeInt32(5, ((Integer)templateToken_.get(k)).intValue());

            }
            if((2 & bitField0_) == 2)
                codedoutputstream.writeInt32(6, macroNameReference_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int EXT_FIELD_NUMBER = 101;
        public static final int LIST_ITEM_FIELD_NUMBER = 1;
        public static final int MACRO_NAME_REFERENCE_FIELD_NUMBER = 6;
        public static final int MACRO_REFERENCE_FIELD_NUMBER = 4;
        public static final int MAP_KEY_FIELD_NUMBER = 2;
        public static final int MAP_VALUE_FIELD_NUMBER = 3;
        public static Parser PARSER;
        public static final int TEMPLATE_TOKEN_FIELD_NUMBER = 5;
        private static final ServingValue defaultInstance;
        public static final com.google.tagmanager.protobuf.GeneratedMessageLite.GeneratedExtension ext;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private List listItem_;
        private int macroNameReference_;
        private int macroReference_;
        private List mapKey_;
        private List mapValue_;
        private List templateToken_;

        static 
        {
            defaultInstance = new ServingValue(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
            ext = GeneratedMessageLite.newSingularGeneratedExtension(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 101, com.google.tagmanager.protobuf.WireFormat.FieldType.MESSAGE, com/google/analytics/containertag/proto/MutableServing$ServingValue);
        }

        private ServingValue()
        {
            listItem_ = null;
            mapKey_ = null;
            mapValue_ = null;
            templateToken_ = null;
            initFields();
        }

        private ServingValue(boolean flag)
        {
            listItem_ = null;
            mapKey_ = null;
            mapValue_ = null;
            templateToken_ = null;
        }
    }


    private MutableServing()
    {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionregistrylite)
    {
        extensionregistrylite.add(ServingValue.ext);
    }

}
