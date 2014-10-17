// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import java.io.*;
import java.util.*;
import java.util.concurrent.Future;

// Referenced classes of package com.squareup.picasso:
//            Action, Transformation, Picasso, Request, 
//            ResourceBitmapHunter, ContactsPhotoBitmapHunter, MediaStoreBitmapHunter, ContentStreamBitmapHunter, 
//            AssetBitmapHunter, FileBitmapHunter, NetworkBitmapHunter, Cache, 
//            Stats, Dispatcher, StatsSnapshot, Downloader

abstract class BitmapHunter
    implements Runnable
{

    BitmapHunter(Picasso picasso1, Dispatcher dispatcher1, Cache cache1, Stats stats1, Action action)
    {
        picasso = picasso1;
        dispatcher = dispatcher1;
        cache = cache1;
        stats = stats1;
        key = action.getKey();
        data = action.getData();
        skipMemoryCache = action.skipCache;
        attach(action);
    }

    static Bitmap applyCustomTransformations(List list, Bitmap bitmap)
    {
        int i = 0;
        int j = list.size();
        do
        {
            Transformation transformation;
            Bitmap bitmap1;
label0:
            {
                if(i < j)
                {
                    transformation = (Transformation)list.get(i);
                    bitmap1 = transformation.transform(bitmap);
                    if(bitmap1 != null)
                        break label0;
                    StringBuilder stringbuilder = (new StringBuilder()).append("Transformation ").append(transformation.key()).append(" returned null after ").append(i).append(" previous transformation(s).\n\nTransformation list:\n");
                    for(Iterator iterator = list.iterator(); iterator.hasNext(); stringbuilder.append(((Transformation)iterator.next()).key()).append('\n'));
                    Picasso.HANDLER.post(new Runnable(stringbuilder) {

                        public void run()
                        {
                            throw new NullPointerException(builder.toString());
                        }

                        final StringBuilder val$builder;

            
            {
                builder = stringbuilder;
                super();
            }
                    }
);
                    bitmap = null;
                }
                return bitmap;
            }
            if(bitmap1 == bitmap && bitmap.isRecycled())
            {
                Picasso.HANDLER.post(new Runnable(transformation) {

                    public void run()
                    {
                        throw new IllegalStateException((new StringBuilder()).append("Transformation ").append(transformation.key()).append(" returned input Bitmap but recycled it.").toString());
                    }

                    final Transformation val$transformation;

            
            {
                transformation = transformation1;
                super();
            }
                }
);
                return null;
            }
            if(bitmap1 != bitmap && !bitmap.isRecycled())
            {
                Picasso.HANDLER.post(new Runnable(transformation) {

                    public void run()
                    {
                        throw new IllegalStateException((new StringBuilder()).append("Transformation ").append(transformation.key()).append(" mutated input Bitmap but failed to recycle the original.").toString());
                    }

                    final Transformation val$transformation;

            
            {
                transformation = transformation1;
                super();
            }
                }
);
                return null;
            }
            bitmap = bitmap1;
            i++;
        } while(true);
    }

    static void calculateInSampleSize(int i, int j, int k, int l, android.graphics.BitmapFactory.Options options)
    {
        int i1 = 1;
        if(l > j || k > i)
        {
            int j1 = Math.round((float)l / (float)j);
            int k1 = Math.round((float)k / (float)i);
            if(j1 < k1)
                i1 = j1;
            else
                i1 = k1;
        }
        options.inSampleSize = i1;
        options.inJustDecodeBounds = false;
    }

    static void calculateInSampleSize(int i, int j, android.graphics.BitmapFactory.Options options)
    {
        calculateInSampleSize(i, j, options.outWidth, options.outHeight, options);
    }

    static android.graphics.BitmapFactory.Options createBitmapOptions(Request request)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        if(request.config != null)
            options.inPreferredConfig = request.config;
        return options;
    }

    static BitmapHunter forRequest(Context context, Picasso picasso1, Dispatcher dispatcher1, Cache cache1, Stats stats1, Action action, Downloader downloader)
    {
        if(action.getData().resourceId != 0)
            return new ResourceBitmapHunter(context, picasso1, dispatcher1, cache1, stats1, action);
        Uri uri = action.getData().uri;
        String s = uri.getScheme();
        if("content".equals(s))
        {
            if(android.provider.ContactsContract.Contacts.CONTENT_URI.getHost().equals(uri.getHost()) && !uri.getPathSegments().contains("photo"))
                return new ContactsPhotoBitmapHunter(context, picasso1, dispatcher1, cache1, stats1, action);
            if("media".equals(uri.getAuthority()))
                return new MediaStoreBitmapHunter(context, picasso1, dispatcher1, cache1, stats1, action);
            else
                return new ContentStreamBitmapHunter(context, picasso1, dispatcher1, cache1, stats1, action);
        }
        if("file".equals(s))
            if(!uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0)))
                return new AssetBitmapHunter(context, picasso1, dispatcher1, cache1, stats1, action);
            else
                return new FileBitmapHunter(context, picasso1, dispatcher1, cache1, stats1, action);
        if("android.resource".equals(s))
            return new ResourceBitmapHunter(context, picasso1, dispatcher1, cache1, stats1, action);
        else
            return new NetworkBitmapHunter(picasso1, dispatcher1, cache1, stats1, action, downloader);
    }

    static Bitmap transformResult(Request request, Bitmap bitmap, int i)
    {
        int j;
        int k;
        int l;
        int i1;
        Matrix matrix;
        boolean flag;
        int j1;
        int k1;
        j = bitmap.getWidth();
        k = bitmap.getHeight();
        l = j;
        i1 = k;
        matrix = new Matrix();
        flag = request.needsMatrixTransform();
        j1 = 0;
        k1 = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        int l1;
        int i2;
        l1 = request.targetWidth;
        i2 = request.targetHeight;
        float f = request.rotationDegrees;
        Bitmap bitmap1;
        int k2;
        if(f != 0.0F)
            if(request.hasRotationPivot)
                matrix.setRotate(f, request.rotationPivotX, request.rotationPivotY);
            else
                matrix.setRotate(f);
        if(!request.centerCrop) goto _L4; else goto _L3
_L3:
        float f4 = (float)l1 / (float)j;
        float f5 = (float)i2 / (float)k;
        float f6;
        if(f4 > f5)
        {
            f6 = f4;
            k2 = (int)Math.ceil((float)k * (f5 / f4));
            k1 = (k - k2) / 2;
            i1 = k2;
        } else
        {
            f6 = f5;
            int j2 = (int)Math.ceil((float)j * (f4 / f5));
            j1 = (j - j2) / 2;
            l = j2;
            k1 = 0;
        }
        matrix.preScale(f6, f6);
_L2:
        if(i != 0)
            matrix.preRotate(i);
        bitmap1 = Bitmap.createBitmap(bitmap, j1, k1, l, i1, matrix, true);
        if(bitmap1 != bitmap)
        {
            bitmap.recycle();
            bitmap = bitmap1;
        }
        return bitmap;
_L4:
        if(request.centerInside)
        {
            float f1 = (float)l1 / (float)j;
            float f2 = (float)i2 / (float)k;
            float f3;
            if(f1 < f2)
                f3 = f1;
            else
                f3 = f2;
            matrix.preScale(f3, f3);
            j1 = 0;
            k1 = 0;
            continue; /* Loop/switch isn't completed */
        }
        j1 = 0;
        k1 = 0;
        if(l1 == 0)
            continue; /* Loop/switch isn't completed */
        j1 = 0;
        k1 = 0;
        if(i2 == 0)
            continue; /* Loop/switch isn't completed */
        if(l1 == j)
        {
            j1 = 0;
            k1 = 0;
            if(i2 == k)
                continue; /* Loop/switch isn't completed */
        }
        matrix.preScale((float)l1 / (float)j, (float)i2 / (float)k);
        j1 = 0;
        k1 = 0;
        if(true) goto _L2; else goto _L5
_L5:
    }

    void attach(Action action)
    {
        actions.add(action);
    }

    boolean cancel()
    {
        boolean flag = actions.isEmpty();
        boolean flag1 = false;
        if(flag)
        {
            Future future1 = future;
            flag1 = false;
            if(future1 != null)
            {
                boolean flag2 = future.cancel(false);
                flag1 = false;
                if(flag2)
                    flag1 = true;
            }
        }
        return flag1;
    }

    abstract Bitmap decode(Request request)
        throws IOException;

    void detach(Action action)
    {
        actions.remove(action);
    }

    List getActions()
    {
        return actions;
    }

    Request getData()
    {
        return data;
    }

    Exception getException()
    {
        return exception;
    }

    String getKey()
    {
        return key;
    }

    Picasso.LoadedFrom getLoadedFrom()
    {
        return loadedFrom;
    }

    Bitmap getResult()
    {
        return result;
    }

    Bitmap hunt()
        throws IOException
    {
        if(!skipMemoryCache)
        {
            Bitmap bitmap1 = cache.get(key);
            if(bitmap1 != null)
            {
                stats.dispatchCacheHit();
                loadedFrom = Picasso.LoadedFrom.MEMORY;
                return bitmap1;
            }
        }
        Bitmap bitmap = decode(data);
        if(bitmap != null)
        {
            stats.dispatchBitmapDecoded(bitmap);
            if(data.needsTransformation() || exifRotation != 0)
            {
                synchronized(DECODE_LOCK)
                {
                    if(data.needsMatrixTransform() || exifRotation != 0)
                        bitmap = transformResult(data, bitmap, exifRotation);
                    if(data.hasCustomTransformations())
                        bitmap = applyCustomTransformations(data.transformations, bitmap);
                }
                stats.dispatchBitmapTransformed(bitmap);
            }
        }
        return bitmap;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    boolean isCancelled()
    {
        return future != null && future.isCancelled();
    }

    public void run()
    {
        Thread.currentThread().setName((new StringBuilder()).append("Picasso-").append(data.getName()).toString());
        result = hunt();
        if(result != null) goto _L2; else goto _L1
_L1:
        dispatcher.dispatchFailed(this);
_L4:
        Thread.currentThread().setName("Picasso-Idle");
        return;
_L2:
        dispatcher.dispatchComplete(this);
        if(true) goto _L4; else goto _L3
_L3:
        Downloader.ResponseException responseexception;
        responseexception;
        exception = responseexception;
        dispatcher.dispatchFailed(this);
        Thread.currentThread().setName("Picasso-Idle");
        return;
        IOException ioexception;
        ioexception;
        exception = ioexception;
        dispatcher.dispatchRetry(this);
        Thread.currentThread().setName("Picasso-Idle");
        return;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        StringWriter stringwriter = new StringWriter();
        stats.createSnapshot().dump(new PrintWriter(stringwriter));
        exception = new RuntimeException(stringwriter.toString(), outofmemoryerror);
        dispatcher.dispatchFailed(this);
        Thread.currentThread().setName("Picasso-Idle");
        return;
        Exception exception2;
        exception2;
        exception = exception2;
        dispatcher.dispatchFailed(this);
        Thread.currentThread().setName("Picasso-Idle");
        return;
        Exception exception1;
        exception1;
        Thread.currentThread().setName("Picasso-Idle");
        throw exception1;
    }

    protected void setExifRotation(int i)
    {
        exifRotation = i;
    }

    boolean shouldRetry(boolean flag, NetworkInfo networkinfo)
    {
        return false;
    }

    boolean shouldSkipMemoryCache()
    {
        return skipMemoryCache;
    }

    private static final Object DECODE_LOCK = new Object();
    final List actions = new ArrayList(4);
    final Cache cache;
    final Request data;
    final Dispatcher dispatcher;
    Exception exception;
    int exifRotation;
    Future future;
    final String key;
    Picasso.LoadedFrom loadedFrom;
    final Picasso picasso;
    Bitmap result;
    final boolean skipMemoryCache;
    final Stats stats;

}
