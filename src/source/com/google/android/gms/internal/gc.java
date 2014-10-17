// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.*;

public final class gc
    implements RealTimeSocket
{

    gc(ParcelFileDescriptor parcelfiledescriptor)
    {
        AC = parcelfiledescriptor;
        Hz = new android.os.ParcelFileDescriptor.AutoCloseInputStream(parcelfiledescriptor);
        HA = new android.os.ParcelFileDescriptor.AutoCloseOutputStream(parcelfiledescriptor);
    }

    public void close()
        throws IOException
    {
        AC.close();
    }

    public InputStream getInputStream()
        throws IOException
    {
        return Hz;
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return HA;
    }

    public ParcelFileDescriptor getParcelFileDescriptor()
        throws IOException
    {
        return AC;
    }

    public boolean isClosed()
    {
        try
        {
            Hz.available();
        }
        catch(IOException ioexception)
        {
            return true;
        }
        return false;
    }

    private static final String TAG = com/google/android/gms/internal/gc.getSimpleName();
    private final ParcelFileDescriptor AC;
    private final OutputStream HA;
    private final InputStream Hz;

}
