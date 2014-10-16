// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.net.LocalSocket;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.*;

final class ge
    implements RealTimeSocket
{

    ge(LocalSocket localsocket, String s)
    {
        HG = localsocket;
        GZ = s;
    }

    public void close()
        throws IOException
    {
        HG.close();
    }

    public InputStream getInputStream()
        throws IOException
    {
        return HG.getInputStream();
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return HG.getOutputStream();
    }

    public ParcelFileDescriptor getParcelFileDescriptor()
        throws IOException
    {
        if(AC == null && !isClosed())
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeFileDescriptor(HG.getFileDescriptor());
            parcel.setDataPosition(0);
            AC = parcel.readFileDescriptor();
        }
        return AC;
    }

    public boolean isClosed()
    {
        return !HG.isConnected() && !HG.isBound();
    }

    private ParcelFileDescriptor AC;
    private final String GZ;
    private final LocalSocket HG;
}
