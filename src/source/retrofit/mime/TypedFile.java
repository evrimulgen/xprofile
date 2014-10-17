// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.mime;

import java.io.*;

// Referenced classes of package retrofit.mime:
//            TypedInput, TypedOutput

public class TypedFile
    implements TypedInput, TypedOutput
{

    public TypedFile(String s, File file1)
    {
        if(s == null)
            throw new NullPointerException("mimeType");
        if(file1 == null)
        {
            throw new NullPointerException("file");
        } else
        {
            mimeType = s;
            file = file1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof TypedFile)
        {
            TypedFile typedfile = (TypedFile)obj;
            return file.equals(typedfile.file);
        } else
        {
            return false;
        }
    }

    public File file()
    {
        return file;
    }

    public String fileName()
    {
        return file.getName();
    }

    public int hashCode()
    {
        return file.hashCode();
    }

    public InputStream in()
        throws IOException
    {
        return new FileInputStream(file);
    }

    public long length()
    {
        return file.length();
    }

    public String mimeType()
    {
        return mimeType;
    }

    public void moveTo(TypedFile typedfile)
        throws IOException
    {
        if(!mimeType().equals(typedfile.mimeType()))
            throw new IOException("Type mismatch.");
        if(!file.renameTo(typedfile.file()))
            throw new IOException("Rename failed!");
        else
            return;
    }

    public String toString()
    {
        return (new StringBuilder()).append(file.getAbsolutePath()).append(" (").append(mimeType()).append(")").toString();
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        byte abyte0[];
        FileInputStream fileinputstream;
        abyte0 = new byte[4096];
        fileinputstream = new FileInputStream(file);
_L1:
        int i = fileinputstream.read(abyte0);
        if(i == -1)
            break MISSING_BLOCK_LABEL_51;
        outputstream.write(abyte0, 0, i);
          goto _L1
        Exception exception;
        exception;
        fileinputstream.close();
        throw exception;
        fileinputstream.close();
        return;
    }

    private static final int BUFFER_SIZE = 4096;
    private final File file;
    private final String mimeType;
}
