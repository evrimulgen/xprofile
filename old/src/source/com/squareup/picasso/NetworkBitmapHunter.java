// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.NetworkInfo;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.squareup.picasso:
//            BitmapHunter, MarkableInputStream, Utils, Request, 
//            Downloader, Picasso, Dispatcher, Cache, 
//            Stats, Action

class NetworkBitmapHunter extends BitmapHunter
{

    public NetworkBitmapHunter(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action, Downloader downloader1)
    {
        super(picasso, dispatcher, cache, stats, action);
        downloader = downloader1;
        retryCount = 2;
    }

    private Bitmap decodeStream(InputStream inputstream, Request request)
        throws IOException
    {
        if(inputstream == null)
            return null;
        MarkableInputStream markableinputstream = new MarkableInputStream(inputstream);
        long l = markableinputstream.savePosition(0x10000);
        boolean flag = Utils.isWebPFile(markableinputstream);
        markableinputstream.reset(l);
        if(flag)
        {
            byte abyte0[] = Utils.toByteArray(markableinputstream);
            android.graphics.BitmapFactory.Options options1 = createBitmapOptions(request);
            if(request.hasSize())
            {
                options1.inJustDecodeBounds = true;
                BitmapFactoryInstrumentation.decodeByteArray(abyte0, 0, abyte0.length, options1);
                calculateInSampleSize(request.targetWidth, request.targetHeight, options1);
            }
            return BitmapFactoryInstrumentation.decodeByteArray(abyte0, 0, abyte0.length, options1);
        }
        android.graphics.BitmapFactory.Options options = createBitmapOptions(request);
        if(request.hasSize())
        {
            options.inJustDecodeBounds = true;
            BitmapFactoryInstrumentation.decodeStream(markableinputstream, null, options);
            calculateInSampleSize(request.targetWidth, request.targetHeight, options);
            markableinputstream.reset(l);
        }
        return BitmapFactoryInstrumentation.decodeStream(markableinputstream, null, options);
    }

    Bitmap decode(Request request)
        throws IOException
    {
        Downloader.Response response;
        Bitmap bitmap;
        boolean flag;
        if(retryCount == 0)
            flag = true;
        else
            flag = false;
        response = downloader.load(request.uri, flag);
        if(response != null) goto _L2; else goto _L1
_L1:
        bitmap = null;
_L4:
        return bitmap;
_L2:
        InputStream inputstream;
        Picasso.LoadedFrom loadedfrom;
        Bitmap bitmap1;
        if(response.cached)
            loadedfrom = Picasso.LoadedFrom.DISK;
        else
            loadedfrom = Picasso.LoadedFrom.NETWORK;
        loadedFrom = loadedfrom;
        bitmap = response.getBitmap();
        if(bitmap != null) goto _L4; else goto _L3
_L3:
        inputstream = response.getInputStream();
        bitmap1 = decodeStream(inputstream, request);
        Utils.closeQuietly(inputstream);
        return bitmap1;
        Exception exception;
        exception;
        Utils.closeQuietly(inputstream);
        throw exception;
    }

    boolean shouldRetry(boolean flag, NetworkInfo networkinfo)
    {
        boolean flag1;
        if(retryCount > 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            retryCount = -1 + retryCount;
            if(networkinfo == null || networkinfo.isConnectedOrConnecting())
                return true;
        }
        return false;
    }

    static final int DEFAULT_RETRY_COUNT = 2;
    private static final int MARKER = 0x10000;
    private final Downloader downloader;
    int retryCount;
}
