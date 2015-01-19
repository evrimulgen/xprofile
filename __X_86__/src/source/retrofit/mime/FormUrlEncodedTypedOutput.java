// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.mime;

import java.io.*;
import java.net.URLEncoder;

// Referenced classes of package retrofit.mime:
//            TypedOutput

public final class FormUrlEncodedTypedOutput
    implements TypedOutput
{

    public FormUrlEncodedTypedOutput()
    {
    }

    public void addField(String s, String s1)
    {
        if(s == null)
            throw new NullPointerException("name");
        if(s1 == null)
            throw new NullPointerException("value");
        if(content.size() > 0)
            content.write(38);
        try
        {
            String s2 = URLEncoder.encode(s, "UTF-8");
            String s3 = URLEncoder.encode(s1, "UTF-8");
            content.write(s2.getBytes("UTF-8"));
            content.write(61);
            content.write(s3.getBytes("UTF-8"));
            return;
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
    }

    public String fileName()
    {
        return null;
    }

    public long length()
    {
        return (long)content.size();
    }

    public String mimeType()
    {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        outputstream.write(content.toByteArray());
    }

    final ByteArrayOutputStream content = new ByteArrayOutputStream();
}
