// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.mime;

import java.io.UnsupportedEncodingException;

// Referenced classes of package retrofit.mime:
//            TypedByteArray

public class TypedString extends TypedByteArray
{

    public TypedString(String s)
    {
        super("text/plain; charset=UTF-8", convertToBytes(s));
    }

    private static byte[] convertToBytes(String s)
    {
        byte abyte0[];
        try
        {
            abyte0 = s.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException(unsupportedencodingexception);
        }
        return abyte0;
    }
}
