// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Collection;

// Referenced classes of package com.google.tagmanager.protobuf:
//            MutableMessageLite, UninitializedMessageException, ExtensionRegistryLite, CodedInputStream, 
//            ByteString, CodedOutputStream, Parser, MessageLite, 
//            AbstractParser, InvalidProtocolBufferException

public abstract class AbstractMutableMessageLite
    implements MutableMessageLite
{

    public AbstractMutableMessageLite()
    {
        isMutable = true;
        cachedSize = -1;
    }

    protected static void addAll(Iterable iterable, Collection collection)
    {
        AbstractMessageLite.Builder.addAll(iterable, collection);
    }

    protected static Parser internalNewParserForType(MutableMessageLite mutablemessagelite)
    {
        return new AbstractParser(mutablemessagelite) {

            public MutableMessageLite parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                MutableMessageLite mutablemessagelite1 = defaultInstance.newMessageForType();
                if(!mutablemessagelite1.mergeFrom(codedinputstream, extensionregistrylite))
                    throw InvalidProtocolBufferException.parseFailure().setUnfinishedMessage(mutablemessagelite1);
                else
                    return mutablemessagelite1;
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

            final MutableMessageLite val$defaultInstance;

            
            {
                defaultInstance = mutablemessagelite;
                super();
            }
        }
;
    }

    protected static UninitializedMessageException newUninitializedMessageException(MessageLite messagelite)
    {
        return new UninitializedMessageException(messagelite);
    }

    protected void assertMutable()
    {
        if(!isMutable)
            throw new IllegalStateException("Try to modify an immutable message.");
        else
            return;
    }

    public MutableMessageLite clone()
    {
        throw new UnsupportedOperationException("clone() should be implemented by subclasses.");
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public final int getCachedSize()
    {
        return cachedSize;
    }

    protected boolean isProto1Group()
    {
        return false;
    }

    protected void makeImmutable()
    {
        isMutable = false;
    }

    public boolean mergeDelimitedFrom(InputStream inputstream)
    {
        return mergeDelimitedFrom(inputstream, ExtensionRegistryLite.getEmptyRegistry());
    }

    public boolean mergeDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
    {
        int i;
        boolean flag;
        try
        {
            i = inputstream.read();
        }
        catch(IOException ioexception)
        {
            return false;
        }
        if(i == -1)
            return false;
        flag = mergeFrom(new AbstractMessageLite.Builder.LimitedInputStream(inputstream, CodedInputStream.readRawVarint32(i, inputstream)), extensionregistrylite);
        return flag;
    }

    public boolean mergeFrom(ByteString bytestring)
    {
        CodedInputStream codedinputstream = bytestring.newCodedInput();
        return mergeFrom(codedinputstream) && codedinputstream.getLastTag() == 0;
    }

    public boolean mergeFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
    {
        CodedInputStream codedinputstream = bytestring.newCodedInput();
        return mergeFrom(codedinputstream, extensionregistrylite) && codedinputstream.getLastTag() == 0;
    }

    public boolean mergeFrom(CodedInputStream codedinputstream)
    {
        return mergeFrom(codedinputstream, ExtensionRegistryLite.getEmptyRegistry());
    }

    public boolean mergeFrom(InputStream inputstream)
    {
        CodedInputStream codedinputstream = CodedInputStream.newInstance(inputstream);
        return mergeFrom(codedinputstream) && codedinputstream.getLastTag() == 0;
    }

    public boolean mergeFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
    {
        CodedInputStream codedinputstream = CodedInputStream.newInstance(inputstream);
        return mergeFrom(codedinputstream, extensionregistrylite) && codedinputstream.getLastTag() == 0;
    }

    public boolean mergeFrom(ByteBuffer bytebuffer)
    {
        CodedInputStream codedinputstream = CodedInputStream.newInstance(bytebuffer);
        return mergeFrom(codedinputstream) && codedinputstream.getLastTag() == 0;
    }

    public boolean mergeFrom(ByteBuffer bytebuffer, ExtensionRegistryLite extensionregistrylite)
    {
        CodedInputStream codedinputstream = CodedInputStream.newInstance(bytebuffer);
        return mergeFrom(codedinputstream, extensionregistrylite) && codedinputstream.getLastTag() == 0;
    }

    public boolean mergeFrom(byte abyte0[])
    {
        return mergeFrom(abyte0, 0, abyte0.length);
    }

    public boolean mergeFrom(byte abyte0[], int i, int j)
    {
        CodedInputStream codedinputstream = CodedInputStream.newInstance(abyte0, i, j);
        return mergeFrom(codedinputstream) && codedinputstream.getLastTag() == 0;
    }

    public boolean mergeFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite)
    {
        CodedInputStream codedinputstream = CodedInputStream.newInstance(abyte0, i, j);
        return mergeFrom(codedinputstream, extensionregistrylite) && codedinputstream.getLastTag() == 0;
    }

    public boolean mergeFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
    {
        return mergeFrom(abyte0, 0, abyte0.length, extensionregistrylite);
    }

    public boolean mergePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
    {
        return mergeFrom(codedinputstream, extensionregistrylite);
    }

    public MutableMessageLite mutableCopy()
    {
        throw new UnsupportedOperationException("mutableCopy() is not supported in mutable messages. Use clone() if you need to make a copy of the mutable message.");
    }

    public MessageLite.Builder newBuilderForType()
    {
        throw new UnsupportedOperationException("newBuilderForType() is not supported in mutable messages.");
    }

    UninitializedMessageException newUninitializedMessageException()
    {
        return new UninitializedMessageException(this);
    }

    public boolean parseDelimitedFrom(InputStream inputstream)
    {
        clear();
        return mergeDelimitedFrom(inputstream);
    }

    public boolean parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
    {
        clear();
        return mergeDelimitedFrom(inputstream, extensionregistrylite);
    }

    public boolean parseFrom(ByteString bytestring)
    {
        clear();
        return mergeFrom(bytestring);
    }

    public boolean parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
    {
        clear();
        return mergeFrom(bytestring, extensionregistrylite);
    }

    public boolean parseFrom(CodedInputStream codedinputstream)
    {
        clear();
        return mergeFrom(codedinputstream);
    }

    public boolean parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
    {
        clear();
        return mergeFrom(codedinputstream, extensionregistrylite);
    }

    public boolean parseFrom(InputStream inputstream)
    {
        clear();
        return mergeFrom(inputstream);
    }

    public boolean parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
    {
        clear();
        return mergeFrom(inputstream, extensionregistrylite);
    }

    public boolean parseFrom(ByteBuffer bytebuffer)
    {
        clear();
        return mergeFrom(bytebuffer);
    }

    public boolean parseFrom(ByteBuffer bytebuffer, ExtensionRegistryLite extensionregistrylite)
    {
        clear();
        return mergeFrom(bytebuffer, extensionregistrylite);
    }

    public boolean parseFrom(byte abyte0[])
    {
        clear();
        return mergeFrom(abyte0, 0, abyte0.length);
    }

    public boolean parseFrom(byte abyte0[], int i, int j)
    {
        clear();
        return mergeFrom(abyte0, i, j);
    }

    public boolean parseFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite)
    {
        clear();
        return mergeFrom(abyte0, i, j, extensionregistrylite);
    }

    public boolean parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
    {
        clear();
        return mergeFrom(abyte0, 0, abyte0.length, extensionregistrylite);
    }

    public MessageLite.Builder toBuilder()
    {
        throw new UnsupportedOperationException("toBuilder() is not supported in mutable messages.");
    }

    public byte[] toByteArray()
    {
        byte abyte0[];
        try
        {
            abyte0 = new byte[getSerializedSize()];
            CodedOutputStream codedoutputstream = CodedOutputStream.newInstance(abyte0);
            writeTo(codedoutputstream);
            codedoutputstream.checkNoSpaceLeft();
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ioexception);
        }
        return abyte0;
    }

    public ByteString toByteString()
    {
        ByteString bytestring;
        try
        {
            ByteString.CodedBuilder codedbuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(codedbuilder.getCodedOutput());
            bytestring = codedbuilder.build();
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", ioexception);
        }
        return bytestring;
    }

    public void writeDelimitedTo(OutputStream outputstream)
        throws IOException
    {
        int i = getSerializedSize();
        CodedOutputStream codedoutputstream = CodedOutputStream.newInstance(outputstream, CodedOutputStream.computePreferredBufferSize(i + CodedOutputStream.computeRawVarint32Size(i)));
        codedoutputstream.writeRawVarint32(i);
        writeTo(codedoutputstream);
        codedoutputstream.flush();
    }

    public void writeTo(CodedOutputStream codedoutputstream)
        throws IOException
    {
        getSerializedSize();
        writeToWithCachedSizes(codedoutputstream);
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        CodedOutputStream codedoutputstream = CodedOutputStream.newInstance(outputstream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(codedoutputstream);
        codedoutputstream.flush();
    }

    protected int cachedSize;
    private boolean isMutable;
}
