// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.ParcelFileDescriptor;
import java.io.*;

public interface RealTimeSocket
{

    public abstract void close()
        throws IOException;

    public abstract InputStream getInputStream()
        throws IOException;

    public abstract OutputStream getOutputStream()
        throws IOException;

    public abstract ParcelFileDescriptor getParcelFileDescriptor()
        throws IOException;

    public abstract boolean isClosed();
}
