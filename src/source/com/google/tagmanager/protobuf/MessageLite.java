// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.*;

// Referenced classes of package com.google.tagmanager.protobuf:
//            MessageLiteOrBuilder, Parser, MutableMessageLite, ByteString, 
//            CodedOutputStream, InvalidProtocolBufferException, ExtensionRegistryLite, CodedInputStream

public interface MessageLite
    extends MessageLiteOrBuilder
{
    public static interface Builder
        extends MessageLiteOrBuilder, Cloneable
    {

        public abstract MessageLite build();

        public abstract MessageLite buildPartial();

        public abstract Builder clear();

        public abstract Builder clone();

        public abstract boolean mergeDelimitedFrom(InputStream inputstream)
            throws IOException;

        public abstract boolean mergeDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException;

        public abstract Builder mergeFrom(ByteString bytestring)
            throws InvalidProtocolBufferException;

        public abstract Builder mergeFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException;

        public abstract Builder mergeFrom(CodedInputStream codedinputstream)
            throws IOException;

        public abstract Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException;

        public abstract Builder mergeFrom(InputStream inputstream)
            throws IOException;

        public abstract Builder mergeFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException;

        public abstract Builder mergeFrom(byte abyte0[])
            throws InvalidProtocolBufferException;

        public abstract Builder mergeFrom(byte abyte0[], int i, int j)
            throws InvalidProtocolBufferException;

        public abstract Builder mergeFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException;

        public abstract Builder mergeFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException;
    }


    public abstract Parser getParserForType();

    public abstract int getSerializedSize();

    public abstract MutableMessageLite mutableCopy();

    public abstract Builder newBuilderForType();

    public abstract Builder toBuilder();

    public abstract byte[] toByteArray();

    public abstract ByteString toByteString();

    public abstract void writeDelimitedTo(OutputStream outputstream)
        throws IOException;

    public abstract void writeTo(CodedOutputStream codedoutputstream)
        throws IOException;

    public abstract void writeTo(OutputStream outputstream)
        throws IOException;
}
