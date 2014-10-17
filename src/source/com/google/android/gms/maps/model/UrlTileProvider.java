// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps.model;

import java.io.*;
import java.net.URL;

// Referenced classes of package com.google.android.gms.maps.model:
//            TileProvider, Tile

public abstract class UrlTileProvider
    implements TileProvider
{

    public UrlTileProvider(int i, int j)
    {
        w = i;
        v = j;
    }

    private static long a(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        byte abyte0[] = new byte[4096];
        long l = 0L;
        do
        {
            int i = inputstream.read(abyte0);
            if(i == -1)
                return l;
            outputstream.write(abyte0, 0, i);
            l += i;
        } while(true);
    }

    private static byte[] a(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        a(inputstream, ((OutputStream) (bytearrayoutputstream)));
        return bytearrayoutputstream.toByteArray();
    }

    public final Tile getTile(int i, int j, int k)
    {
        URL url = getTileUrl(i, j, k);
        if(url == null)
            return NO_TILE;
        Tile tile;
        try
        {
            tile = new Tile(w, v, a(url.openStream()));
        }
        catch(IOException ioexception)
        {
            return null;
        }
        return tile;
    }

    public abstract URL getTileUrl(int i, int j, int k);

    private final int v;
    private final int w;
}
