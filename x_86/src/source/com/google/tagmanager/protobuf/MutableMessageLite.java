// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

// Referenced classes of package com.google.tagmanager.protobuf:
//            MessageLite, ExtensionRegistryLite, ByteString, CodedInputStream, 
//            CodedOutputStream

public interface MutableMessageLite
    extends MessageLite, Cloneable
{

    public abstract MutableMessageLite clear();

    public abstract MutableMessageLite clone();

    public abstract int getCachedSize();

    public abstract MessageLite getDefaultInstanceForType();

    public abstract MessageLite immutableCopy();

    public abstract boolean mergeDelimitedFrom(InputStream inputstream);

    public abstract boolean mergeDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean mergeFrom(ByteString bytestring);

    public abstract boolean mergeFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean mergeFrom(CodedInputStream codedinputstream);

    public abstract boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean mergeFrom(InputStream inputstream);

    public abstract boolean mergeFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean mergeFrom(ByteBuffer bytebuffer);

    public abstract boolean mergeFrom(ByteBuffer bytebuffer, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean mergeFrom(byte abyte0[]);

    public abstract boolean mergeFrom(byte abyte0[], int i, int j);

    public abstract boolean mergeFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean mergeFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite);

    public abstract boolean mergePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite);

    public abstract MutableMessageLite newMessageForType();

    public abstract boolean parseDelimitedFrom(InputStream inputstream);

    public abstract boolean parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean parseFrom(ByteString bytestring);

    public abstract boolean parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean parseFrom(InputStream inputstream);

    public abstract boolean parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean parseFrom(ByteBuffer bytebuffer);

    public abstract boolean parseFrom(ByteBuffer bytebuffer, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean parseFrom(byte abyte0[]);

    public abstract boolean parseFrom(byte abyte0[], int i, int j);

    public abstract boolean parseFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite);

    public abstract boolean parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite);

    public abstract void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
        throws IOException;
}
