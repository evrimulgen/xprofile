// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.mime;

import java.io.*;
import java.util.Arrays;

// Referenced classes of package retrofit.mime:
//            TypedInput, TypedOutput

public class TypedByteArray
    implements TypedInput, TypedOutput
{

    public TypedByteArray(String s, byte abyte0[])
    {
        if(s == null)
            s = "application/unknown";
        if(abyte0 == null)
        {
            throw new NullPointerException("bytes");
        } else
        {
            mimeType = s;
            bytes = abyte0;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            TypedByteArray typedbytearray = (TypedByteArray)obj;
            if(!Arrays.equals(bytes, typedbytearray.bytes))
                return false;
            if(!mimeType.equals(typedbytearray.mimeType))
                return false;
        }
        return true;
    }

    public String fileName()
    {
        return null;
    }

    public byte[] getBytes()
    {
        return bytes;
    }

    public int hashCode()
    {
        return 31 * mimeType.hashCode() + Arrays.hashCode(bytes);
    }

    public InputStream in()
        throws IOException
    {
        return new ByteArrayInputStream(bytes);
    }

    public long length()
    {
        return (long)bytes.length;
    }

    public String mimeType()
    {
        return mimeType;
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        outputstream.write(bytes);
    }

    private final byte bytes[];
    private final String mimeType;
}
