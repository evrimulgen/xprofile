// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.mime;

import java.io.IOException;
import java.io.OutputStream;

public interface TypedOutput
{

    public abstract String fileName();

    public abstract long length();

    public abstract String mimeType();

    public abstract void writeTo(OutputStream outputstream)
        throws IOException;
}
