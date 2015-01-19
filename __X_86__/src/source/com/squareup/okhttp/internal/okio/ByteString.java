// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.okio;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

// Referenced classes of package com.squareup.okhttp.internal.okio:
//            Base64

public final class ByteString
{

    ByteString(byte abyte0[])
    {
        data = abyte0;
    }

    public static transient ByteString concat(ByteString abytestring[])
    {
        int i = 0;
        int j = abytestring.length;
        for(int k = 0; k < j; k++)
            i += abytestring[k].size();

        byte abyte0[] = new byte[i];
        int l = 0;
        int i1 = abytestring.length;
        for(int j1 = 0; j1 < i1; j1++)
        {
            ByteString bytestring = abytestring[j1];
            System.arraycopy(bytestring.data, 0, abyte0, l, bytestring.size());
            l += bytestring.size();
        }

        return new ByteString(abyte0);
    }

    public static ByteString decodeBase64(String s)
    {
        byte abyte0[] = Base64.decode(s);
        if(abyte0 != null)
            return new ByteString(abyte0);
        else
            return null;
    }

    public static ByteString decodeHex(String s)
    {
        if(s.length() % 2 != 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected hex string: ").append(s).toString());
        byte abyte0[] = new byte[s.length() / 2];
        for(int i = 0; i < abyte0.length; i++)
            abyte0[i] = (byte)((decodeHexDigit(s.charAt(i * 2)) << 4) + decodeHexDigit(s.charAt(1 + i * 2)));

        return of(abyte0);
    }

    private static int decodeHexDigit(char c)
    {
        if(c >= '0' && c <= '9')
            return c - 48;
        if(c >= 'a' && c <= 'f')
            return 10 + (c - 97);
        if(c >= 'A' && c <= 'F')
            return 10 + (c - 65);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected hex digit: ").append(c).toString());
    }

    public static ByteString encodeUtf8(String s)
    {
        ByteString bytestring;
        try
        {
            bytestring = new ByteString(s.getBytes("UTF-8"));
            bytestring.utf8 = s;
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return bytestring;
    }

    public static transient ByteString of(byte abyte0[])
    {
        return new ByteString((byte[])abyte0.clone());
    }

    public static ByteString read(InputStream inputstream, int i)
        throws IOException
    {
        byte abyte0[] = new byte[i];
        int k;
        for(int j = 0; j < i; j += k)
        {
            k = inputstream.read(abyte0, j, i - j);
            if(k == -1)
                throw new EOFException();
        }

        return new ByteString(abyte0);
    }

    public String base64()
    {
        return Base64.encode(data);
    }

    public boolean equals(Object obj)
    {
        return obj == this || (obj instanceof ByteString) && Arrays.equals(((ByteString)obj).data, data);
    }

    public boolean equalsAscii(String s)
    {
        boolean flag = true;
        if(s != null && data.length == s.length()) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        if(s != utf8)
        {
            int i = 0;
            while(i < data.length) 
            {
                if(data[i] != s.charAt(i))
                    return false;
                i++;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int hashCode()
    {
        int i = hashCode;
        if(i != 0)
        {
            return i;
        } else
        {
            int j = Arrays.hashCode(data);
            hashCode = j;
            return j;
        }
    }

    public String hex()
    {
        char ac[] = new char[2 * data.length];
        byte abyte0[] = data;
        int i = abyte0.length;
        int j = 0;
        int k = 0;
        for(; j < i; j++)
        {
            byte byte0 = abyte0[j];
            int l = k + 1;
            ac[k] = HEX_DIGITS[0xf & byte0 >> 4];
            k = l + 1;
            ac[l] = HEX_DIGITS[byte0 & 0xf];
        }

        return new String(ac);
    }

    public int size()
    {
        return data.length;
    }

    public ByteString toAsciiLowercase()
    {
label0:
        {
            int i = 0;
            byte byte0;
            do
            {
                if(i >= data.length)
                    break label0;
                byte0 = data[i];
                if(byte0 >= 65 && byte0 <= 90)
                    break;
                i++;
            } while(true);
            byte abyte0[] = (byte[])data.clone();
            int j = i + 1;
            abyte0[i] = (byte)(byte0 + 32);
            int k = j;
            while(k < abyte0.length) 
            {
                byte byte1 = abyte0[k];
                if(byte1 >= 65 && byte1 <= 90)
                    abyte0[k] = (byte)(byte1 + 32);
                k++;
            }
            this = new ByteString(abyte0);
        }
        return this;
    }

    public byte[] toByteArray()
    {
        return (byte[])data.clone();
    }

    public String toString()
    {
        if(data.length == 0)
            return "ByteString[size=0]";
        if(data.length <= 16)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = Integer.valueOf(data.length);
            aobj1[1] = hex();
            return String.format("ByteString[size=%s data=%s]", aobj1);
        }
        String s;
        try
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(data.length);
            aobj[1] = of(MessageDigest.getInstance("MD5").digest(data)).hex();
            s = String.format("ByteString[size=%s md5=%s]", aobj);
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new AssertionError();
        }
        return s;
    }

    public String utf8()
    {
        String s = utf8;
        if(s != null)
            return s;
        String s1;
        try
        {
            s1 = new String(data, "UTF-8");
            utf8 = s1;
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return s1;
    }

    public void write(OutputStream outputstream)
        throws IOException
    {
        outputstream.write(data);
    }

    public static final ByteString EMPTY = of(new byte[0]);
    private static final char HEX_DIGITS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };
    final byte data[];
    private transient int hashCode;
    private transient String utf8;

}
