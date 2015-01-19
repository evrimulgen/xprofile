// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.mime;

import java.io.IOException;
import java.io.InputStream;

public interface TypedInput
{

    public abstract InputStream in()
        throws IOException;

    public abstract long length();

    public abstract String mimeType();
}
