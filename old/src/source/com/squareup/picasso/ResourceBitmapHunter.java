// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.IOException;

// Referenced classes of package com.squareup.picasso:
//            BitmapHunter, Request, Utils, Picasso, 
//            Dispatcher, Cache, Stats, Action

class ResourceBitmapHunter extends BitmapHunter
{

    ResourceBitmapHunter(Context context1, Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action)
    {
        super(picasso, dispatcher, cache, stats, action);
        context = context1;
    }

    private Bitmap decodeResource(Resources resources, int i, Request request)
    {
        android.graphics.BitmapFactory.Options options = createBitmapOptions(request);
        if(request.hasSize())
        {
            options.inJustDecodeBounds = true;
            BitmapFactoryInstrumentation.decodeResource(resources, i, options);
            calculateInSampleSize(request.targetWidth, request.targetHeight, options);
        }
        return BitmapFactoryInstrumentation.decodeResource(resources, i, options);
    }

    Bitmap decode(Request request)
        throws IOException
    {
        Resources resources = Utils.getResources(context, request);
        return decodeResource(resources, Utils.getResourceId(resources, request), request);
    }

    Picasso.LoadedFrom getLoadedFrom()
    {
        return Picasso.LoadedFrom.DISK;
    }

    private final Context context;
}
