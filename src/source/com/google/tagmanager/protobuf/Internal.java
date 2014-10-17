// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.*;

// Referenced classes of package com.google.tagmanager.protobuf:
//            ByteString, LiteralByteString, AbstractMutableMessageLite, Utf8, 
//            MutableMessageLite, InvalidProtocolBufferException, MessageLite, CodedInputStream, 
//            ExtensionRegistryLite

public class Internal
{
    public static interface EnumLite
    {

        public abstract int getNumber();
    }

    public static interface EnumLiteMap
    {

        public abstract EnumLite findValueByNumber(int i);
    }


    public Internal()
    {
    }

    public static byte[] byteArrayDefaultValue(String s)
    {
        byte abyte0[];
        try
        {
            abyte0 = s.getBytes("ISO-8859-1");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new IllegalStateException("Java VM does not support a standard character set.", unsupportedencodingexception);
        }
        return abyte0;
    }

    public static ByteBuffer byteBufferDefaultValue(String s)
    {
        return ByteBuffer.wrap(byteArrayDefaultValue(s));
    }

    public static ByteString bytesDefaultValue(String s)
    {
        ByteString bytestring;
        try
        {
            bytestring = ByteString.copyFrom(s.getBytes("ISO-8859-1"));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new IllegalStateException("Java VM does not support a standard character set.", unsupportedencodingexception);
        }
        return bytestring;
    }

    public static ByteBuffer copyByteBuffer(ByteBuffer bytebuffer)
    {
        ByteBuffer bytebuffer1 = bytebuffer.duplicate();
        bytebuffer1.clear();
        ByteBuffer bytebuffer2 = ByteBuffer.allocate(bytebuffer1.capacity());
        bytebuffer2.put(bytebuffer1);
        bytebuffer2.clear();
        return bytebuffer2;
    }

    public static boolean equals(List list, List list1)
    {
        if(list.size() != list1.size())
            return false;
        for(int i = 0; i < list.size(); i++)
            if(!Arrays.equals((byte[])list.get(i), (byte[])list1.get(i)))
                return false;

        return true;
    }

    public static boolean equalsByteBuffer(ByteBuffer bytebuffer, ByteBuffer bytebuffer1)
    {
        if(bytebuffer.capacity() != bytebuffer1.capacity())
            return false;
        else
            return bytebuffer.duplicate().clear().equals(bytebuffer1.duplicate().clear());
    }

    public static boolean equalsByteBuffer(List list, List list1)
    {
        if(list.size() != list1.size())
            return false;
        for(int i = 0; i < list.size(); i++)
            if(!equalsByteBuffer((ByteBuffer)list.get(i), (ByteBuffer)list1.get(i)))
                return false;

        return true;
    }

    public static int hashBoolean(boolean flag)
    {
        return !flag ? 1237 : 1231;
    }

    public static int hashCode(List list)
    {
        int i = 1;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            byte abyte0[] = (byte[])iterator.next();
            i = i * 31 + hashCode(abyte0);
        }

        return i;
    }

    public static int hashCode(byte abyte0[])
    {
        return LiteralByteString.hashCode(abyte0);
    }

    public static int hashCodeByteBuffer(ByteBuffer bytebuffer)
    {
        int i = 4096;
        int j = 1;
        if(bytebuffer.hasArray())
        {
            int i1 = LiteralByteString.hashCode(bytebuffer.capacity(), bytebuffer.array(), bytebuffer.arrayOffset(), bytebuffer.capacity());
            if(i1 == 0)
                i1 = j;
            return i1;
        }
        byte abyte0[];
        ByteBuffer bytebuffer1;
        int k;
        if(bytebuffer.capacity() <= i)
            i = bytebuffer.capacity();
        abyte0 = new byte[i];
        bytebuffer1 = bytebuffer.duplicate();
        bytebuffer1.clear();
        k = bytebuffer.capacity();
        while(bytebuffer1.remaining() > 0) 
        {
            int l;
            if(bytebuffer1.remaining() <= i)
                l = bytebuffer1.remaining();
            else
                l = i;
            bytebuffer1.get(abyte0, 0, l);
            k = LiteralByteString.hashCode(k, abyte0, 0, l);
        }
        if(k != 0)
            j = k;
        return j;
    }

    public static int hashCodeByteBuffer(List list)
    {
        int i = 1;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            ByteBuffer bytebuffer = (ByteBuffer)iterator.next();
            i = i * 31 + hashCodeByteBuffer(bytebuffer);
        }

        return i;
    }

    public static int hashEnum(EnumLite enumlite)
    {
        return enumlite.getNumber();
    }

    public static int hashEnumList(List list)
    {
        int i = 1;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            EnumLite enumlite = (EnumLite)iterator.next();
            i = i * 31 + hashEnum(enumlite);
        }

        return i;
    }

    public static int hashLong(long l)
    {
        return (int)(l ^ l >>> 32);
    }

    static boolean isProto1Group(MessageLite messagelite)
    {
        return (messagelite instanceof AbstractMutableMessageLite) && ((AbstractMutableMessageLite)messagelite).isProto1Group();
    }

    public static boolean isValidUtf8(ByteString bytestring)
    {
        return bytestring.isValidUtf8();
    }

    public static boolean isValidUtf8(byte abyte0[])
    {
        return Utf8.isValidUtf8(abyte0);
    }

    public static MutableMessageLite mergeFrom(MutableMessageLite mutablemessagelite, CodedInputStream codedinputstream)
        throws IOException
    {
        if(!mutablemessagelite.mergeFrom(codedinputstream))
            throw InvalidProtocolBufferException.parseFailure();
        else
            return mutablemessagelite;
    }

    public static MutableMessageLite mergeFrom(MutableMessageLite mutablemessagelite, CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if(!mutablemessagelite.mergeFrom(codedinputstream, extensionregistrylite))
            throw InvalidProtocolBufferException.parseFailure();
        else
            return mutablemessagelite;
    }

    public static MutableMessageLite mergeFrom(MutableMessageLite mutablemessagelite, byte abyte0[])
        throws IOException
    {
        if(!mutablemessagelite.mergeFrom(abyte0))
            throw InvalidProtocolBufferException.parseFailure();
        else
            return mutablemessagelite;
    }

    public static String stringDefaultValue(String s)
    {
        String s1;
        try
        {
            s1 = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new IllegalStateException("Java VM does not support a standard character set.", unsupportedencodingexception);
        }
        return s1;
    }

    public static byte[] toByteArray(String s)
    {
        byte abyte0[];
        try
        {
            abyte0 = s.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException("UTF-8 not supported?", unsupportedencodingexception);
        }
        return abyte0;
    }

    public static String toStringUtf8(byte abyte0[])
    {
        String s;
        try
        {
            s = new String(abyte0, "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException("UTF-8 not supported?", unsupportedencodingexception);
        }
        return s;
    }

    private static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final byte EMPTY_BYTE_ARRAY[];
    public static final ByteBuffer EMPTY_BYTE_BUFFER;

    static 
    {
        EMPTY_BYTE_ARRAY = new byte[0];
        EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);
    }
}
