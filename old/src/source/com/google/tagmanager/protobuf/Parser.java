// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.InputStream;

// Referenced classes of package com.google.tagmanager.protobuf:
//            InvalidProtocolBufferException, ExtensionRegistryLite, ByteString, CodedInputStream

public interface Parser
{

    public abstract Object parseDelimitedFrom(InputStream inputstream)
        throws InvalidProtocolBufferException;

    public abstract Object parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(ByteString bytestring)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(CodedInputStream codedinputstream)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(InputStream inputstream)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(byte abyte0[])
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(byte abyte0[], int i, int j)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialDelimitedFrom(InputStream inputstream)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(ByteString bytestring)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(CodedInputStream codedinputstream)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(InputStream inputstream)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(byte abyte0[])
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(byte abyte0[], int i, int j)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;

    public abstract Object parsePartialFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;
}
