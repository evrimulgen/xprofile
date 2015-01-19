// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.io;

import java.io.*;

// Referenced classes of package com.fasterxml.jackson.core.io:
//            IOContext

public abstract class OutputDecorator
    implements Serializable
{

    public OutputDecorator()
    {
    }

    public abstract OutputStream decorate(IOContext iocontext, OutputStream outputstream)
        throws IOException;

    public abstract Writer decorate(IOContext iocontext, Writer writer)
        throws IOException;

    private static final long serialVersionUID = 1L;
}
