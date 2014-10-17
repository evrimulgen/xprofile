// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.tagmanager.protobuf:
//            MessageLite, ByteString, UninitializedMessageException, CodedOutputStream, 
//            MutableMessageLite, LazyStringList, ExtensionRegistryLite, CodedInputStream, 
//            InvalidProtocolBufferException

public abstract class AbstractMessageLite
    implements MessageLite
{
    public static abstract class Builder
        implements MessageLite.Builder
    {

        protected static void addAll(Iterable iterable, Collection collection)
        {
            if(iterable instanceof LazyStringList)
            {
                checkForNullValues(((LazyStringList)iterable).getUnderlyingElements());
                collection.addAll((Collection)iterable);
            } else
            {
                if(iterable instanceof Collection)
                {
                    checkForNullValues(iterable);
                    collection.addAll((Collection)iterable);
                    return;
                }
                Iterator iterator = iterable.iterator();
                while(iterator.hasNext()) 
                {
                    Object obj = iterator.next();
                    if(obj == null)
                        throw new NullPointerException();
                    collection.add(obj);
                }
            }
        }

        private static void checkForNullValues(Iterable iterable)
        {
            for(Iterator iterator = iterable.iterator(); iterator.hasNext();)
                if(iterator.next() == null)
                    throw new NullPointerException();

        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite messagelite)
        {
            return new UninitializedMessageException(messagelite);
        }

        public abstract Builder clone();

        public volatile MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public boolean mergeDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return mergeDelimitedFrom(inputstream, ExtensionRegistryLite.getEmptyRegistry());
        }

        public boolean mergeDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            int i = inputstream.read();
            if(i == -1)
            {
                return false;
            } else
            {
                mergeFrom(new LimitedInputStream(inputstream, CodedInputStream.readRawVarint32(i, inputstream)), extensionregistrylite);
                return true;
            }
        }

        public Builder mergeFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            try
            {
                CodedInputStream codedinputstream = bytestring.newCodedInput();
                mergeFrom(codedinputstream);
                codedinputstream.checkLastTagWas(0);
            }
            catch(InvalidProtocolBufferException invalidprotocolbufferexception)
            {
                throw invalidprotocolbufferexception;
            }
            catch(IOException ioexception)
            {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", ioexception);
            }
            return this;
        }

        public Builder mergeFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            try
            {
                CodedInputStream codedinputstream = bytestring.newCodedInput();
                mergeFrom(codedinputstream, extensionregistrylite);
                codedinputstream.checkLastTagWas(0);
            }
            catch(InvalidProtocolBufferException invalidprotocolbufferexception)
            {
                throw invalidprotocolbufferexception;
            }
            catch(IOException ioexception)
            {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", ioexception);
            }
            return this;
        }

        public Builder mergeFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return mergeFrom(codedinputstream, ExtensionRegistryLite.getEmptyRegistry());
        }

        public abstract Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException;

        public Builder mergeFrom(InputStream inputstream)
            throws IOException
        {
            CodedInputStream codedinputstream = CodedInputStream.newInstance(inputstream);
            mergeFrom(codedinputstream);
            codedinputstream.checkLastTagWas(0);
            return this;
        }

        public Builder mergeFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            CodedInputStream codedinputstream = CodedInputStream.newInstance(inputstream);
            mergeFrom(codedinputstream, extensionregistrylite);
            codedinputstream.checkLastTagWas(0);
            return this;
        }

        public Builder mergeFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return mergeFrom(abyte0, 0, abyte0.length);
        }

        public Builder mergeFrom(byte abyte0[], int i, int j)
            throws InvalidProtocolBufferException
        {
            try
            {
                CodedInputStream codedinputstream = CodedInputStream.newInstance(abyte0, i, j);
                mergeFrom(codedinputstream);
                codedinputstream.checkLastTagWas(0);
            }
            catch(InvalidProtocolBufferException invalidprotocolbufferexception)
            {
                throw invalidprotocolbufferexception;
            }
            catch(IOException ioexception)
            {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", ioexception);
            }
            return this;
        }

        public Builder mergeFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            try
            {
                CodedInputStream codedinputstream = CodedInputStream.newInstance(abyte0, i, j);
                mergeFrom(codedinputstream, extensionregistrylite);
                codedinputstream.checkLastTagWas(0);
            }
            catch(InvalidProtocolBufferException invalidprotocolbufferexception)
            {
                throw invalidprotocolbufferexception;
            }
            catch(IOException ioexception)
            {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", ioexception);
            }
            return this;
        }

        public Builder mergeFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return mergeFrom(abyte0, 0, abyte0.length, extensionregistrylite);
        }

        public volatile MessageLite.Builder mergeFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return mergeFrom(bytestring);
        }

        public volatile MessageLite.Builder mergeFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return mergeFrom(bytestring, extensionregistrylite);
        }

        public volatile MessageLite.Builder mergeFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return mergeFrom(codedinputstream);
        }

        public volatile MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile MessageLite.Builder mergeFrom(InputStream inputstream)
            throws IOException
        {
            return mergeFrom(inputstream);
        }

        public volatile MessageLite.Builder mergeFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(inputstream, extensionregistrylite);
        }

        public volatile MessageLite.Builder mergeFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return mergeFrom(abyte0);
        }

        public volatile MessageLite.Builder mergeFrom(byte abyte0[], int i, int j)
            throws InvalidProtocolBufferException
        {
            return mergeFrom(abyte0, i, j);
        }

        public volatile MessageLite.Builder mergeFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return mergeFrom(abyte0, i, j, extensionregistrylite);
        }

        public volatile MessageLite.Builder mergeFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return mergeFrom(abyte0, extensionregistrylite);
        }

        public Builder()
        {
        }
    }

    static final class Builder.LimitedInputStream extends FilterInputStream
    {

        public int available()
            throws IOException
        {
            return Math.min(super.available(), limit);
        }

        public int read()
            throws IOException
        {
            int i;
            if(limit <= 0)
            {
                i = -1;
            } else
            {
                i = super.read();
                if(i >= 0)
                {
                    limit = -1 + limit;
                    return i;
                }
            }
            return i;
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            int k;
            if(limit <= 0)
            {
                k = -1;
            } else
            {
                k = super.read(abyte0, i, Math.min(j, limit));
                if(k >= 0)
                {
                    limit = limit - k;
                    return k;
                }
            }
            return k;
        }

        public long skip(long l)
            throws IOException
        {
            long l1 = super.skip(Math.min(l, limit));
            if(l1 >= 0L)
                limit = (int)((long)limit - l1);
            return l1;
        }

        private int limit;

        Builder.LimitedInputStream(InputStream inputstream, int i)
        {
            super(inputstream);
            limit = i;
        }
    }


    public AbstractMessageLite()
    {
        memoizedHashCode = 0;
    }

    protected static void checkByteStringIsUtf8(ByteString bytestring)
        throws IllegalArgumentException
    {
        if(!bytestring.isValidUtf8())
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        else
            return;
    }

    public MutableMessageLite mutableCopy()
    {
        throw new UnsupportedOperationException("mutableCopy() is not implemented.");
    }

    UninitializedMessageException newUninitializedMessageException()
    {
        return new UninitializedMessageException(this);
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

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        CodedOutputStream codedoutputstream = CodedOutputStream.newInstance(outputstream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(codedoutputstream);
        codedoutputstream.flush();
    }

    protected int memoizedHashCode;
}
