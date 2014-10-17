// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.proto;

import com.google.tagmanager.protobuf.AbstractMutableMessageLite;
import com.google.tagmanager.protobuf.ByteString;
import com.google.tagmanager.protobuf.CodedInputStream;
import com.google.tagmanager.protobuf.CodedOutputStream;
import com.google.tagmanager.protobuf.ExtensionRegistryLite;
import com.google.tagmanager.protobuf.GeneratedMutableMessageLite;
import com.google.tagmanager.protobuf.Internal;
import com.google.tagmanager.protobuf.MessageLite;
import com.google.tagmanager.protobuf.MutableMessageLite;
import com.google.tagmanager.protobuf.Parser;
import java.io.IOException;
import java.io.ObjectStreamException;

public final class MutableResource
{
    public static final class ResourceWithMetadata extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureResourceInitialized()
        {
            if(resource_ == com.google.analytics.containertag.proto.MutableServing.Resource.getDefaultInstance())
                resource_ = com.google.analytics.containertag.proto.MutableServing.Resource.newMessage();
        }

        public static ResourceWithMetadata getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            resource_ = com.google.analytics.containertag.proto.MutableServing.Resource.getDefaultInstance();
        }

        public static ResourceWithMetadata newMessage()
        {
            return new ResourceWithMetadata();
        }

        public ResourceWithMetadata clear()
        {
            assertMutable();
            super.clear();
            timeStamp_ = 0L;
            bitField0_ = -2 & bitField0_;
            if(resource_ != com.google.analytics.containertag.proto.MutableServing.Resource.getDefaultInstance())
                resource_.clear();
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

        public ResourceWithMetadata clearResource()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            if(resource_ != com.google.analytics.containertag.proto.MutableServing.Resource.getDefaultInstance())
                resource_.clear();
            return this;
        }

        public ResourceWithMetadata clearTimeStamp()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            timeStamp_ = 0L;
            return this;
        }

        public ResourceWithMetadata clone()
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
            if(!(obj instanceof ResourceWithMetadata))
                return super.equals(obj);
            ResourceWithMetadata resourcewithmetadata = (ResourceWithMetadata)obj;
            boolean flag;
            boolean flag1;
            if(true && hasTimeStamp() == resourcewithmetadata.hasTimeStamp())
                flag = true;
            else
                flag = false;
            if(hasTimeStamp())
                if(flag && getTimeStamp() == resourcewithmetadata.getTimeStamp())
                    flag = true;
                else
                    flag = false;
            if(flag && hasResource() == resourcewithmetadata.hasResource())
                flag1 = true;
            else
                flag1 = false;
            if(hasResource())
                if(flag1 && getResource().equals(resourcewithmetadata.getResource()))
                    flag1 = true;
                else
                    flag1 = false;
            return flag1;
        }

        public final ResourceWithMetadata getDefaultInstanceForType()
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

        public com.google.analytics.containertag.proto.MutableServing.Resource getMutableResource()
        {
            assertMutable();
            ensureResourceInitialized();
            bitField0_ = 2 | bitField0_;
            return resource_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public com.google.analytics.containertag.proto.MutableServing.Resource getResource()
        {
            return resource_;
        }

        public int getSerializedSize()
        {
            int i = 0 + CodedOutputStream.computeInt64Size(1, timeStamp_) + CodedOutputStream.computeMessageSize(2, resource_) + unknownFields.size();
            cachedSize = i;
            return i;
        }

        public long getTimeStamp()
        {
            return timeStamp_;
        }

        public boolean hasResource()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasTimeStamp()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            int i = 41;
            if(hasTimeStamp())
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + Internal.hashLong(getTimeStamp());
            }
            if(hasResource())
                i = 53 * (2 + i * 37) + getResource().hashCode();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.tagmanager.proto.Resource$ResourceWithMetadata");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            while(!hasTimeStamp() || !hasResource() || !getResource().isInitialized()) 
                return false;
            return true;
        }

        public ResourceWithMetadata mergeFrom(ResourceWithMetadata resourcewithmetadata)
        {
            if(this == resourcewithmetadata)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(resourcewithmetadata == getDefaultInstance())
                return this;
            if(resourcewithmetadata.hasTimeStamp())
                setTimeStamp(resourcewithmetadata.getTimeStamp());
            if(resourcewithmetadata.hasResource())
            {
                ensureResourceInitialized();
                resource_.mergeFrom(resourcewithmetadata.getResource());
                bitField0_ = 2 | bitField0_;
            }
            unknownFields = unknownFields.concat(resourcewithmetadata.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((ResourceWithMetadata)generatedmutablemessagelite);
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
        //                       0: 163
        //                       8: 87
        //                       18: 108;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            bitField0_ = 1 | bitField0_;
            timeStamp_ = codedinputstream.readInt64();
            continue; /* Loop/switch isn't completed */
_L6:
            if(resource_ == com.google.analytics.containertag.proto.MutableServing.Resource.getDefaultInstance())
                resource_ = com.google.analytics.containertag.proto.MutableServing.Resource.newMessage();
            bitField0_ = 2 | bitField0_;
            codedinputstream.readMessage(resource_, extensionregistrylite);
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

        public ResourceWithMetadata newMessageForType()
        {
            return new ResourceWithMetadata();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public ResourceWithMetadata setResource(com.google.analytics.containertag.proto.MutableServing.Resource resource)
        {
            assertMutable();
            if(resource == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                resource_ = resource;
                return this;
            }
        }

        public ResourceWithMetadata setTimeStamp(long l)
        {
            assertMutable();
            bitField0_ = 1 | bitField0_;
            timeStamp_ = l;
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
            codedoutputstream.writeInt64(1, timeStamp_);
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
        public static final int TIME_STAMP_FIELD_NUMBER = 1;
        private static final ResourceWithMetadata defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private com.google.analytics.containertag.proto.MutableServing.Resource resource_;
        private long timeStamp_;

        static 
        {
            defaultInstance = new ResourceWithMetadata(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private ResourceWithMetadata()
        {
            initFields();
        }

        private ResourceWithMetadata(boolean flag)
        {
        }
    }


    private MutableResource()
    {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionregistrylite)
    {
    }

}
