// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.IOException;

// Referenced classes of package com.squareup.picasso:
//            BitmapHunter, Request, Utils, Picasso, 
//            Dispatcher, Cache, Stats, Action

class AssetBitmapHunter extends BitmapHunter
{

    public AssetBitmapHunter(Context context, Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action)
    {
        super(picasso, dispatcher, cache, stats, action);
        assetManager = context.getAssets();
    }

    Bitmap decode(Request request)
        throws IOException
    {
        return decodeAsset(request.uri.toString().substring(ASSET_PREFIX_LENGTH));
    }

    Bitmap decodeAsset(String s)
        throws IOException
    {
        android.graphics.BitmapFactory.Options options;
        java.io.InputStream inputstream1;
        options = createBitmapOptions(data);
        if(!data.hasSize())
            break MISSING_BLOCK_LABEL_67;
        options.inJustDecodeBounds = true;
        inputstream1 = null;
        inputstream1 = assetManager.open(s);
        BitmapFactoryInstrumentation.decodeStream(inputstream1, null, options);
        Utils.closeQuietly(inputstream1);
        calculateInSampleSize(data.targetWidth, data.targetHeight, options);
        java.io.InputStream inputstream = assetManager.open(s);
        Bitmap bitmap = BitmapFactoryInstrumentation.decodeStream(inputstream, null, options);
        Utils.closeQuietly(inputstream);
        return bitmap;
        Exception exception1;
        exception1;
        Utils.closeQuietly(inputstream1);
        throw exception1;
        Exception exception;
        exception;
        Utils.closeQuietly(inputstream);
        throw exception;
    }

    Picasso.LoadedFrom getLoadedFrom()
    {
        return Picasso.LoadedFrom.DISK;
    }

    protected static final String ANDROID_ASSET = "android_asset";
    private static final int ASSET_PREFIX_LENGTH = "file:///android_asset/".length();
    private AssetManager assetManager;

}
