// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import java.io.IOException;

// Referenced classes of package com.squareup.picasso:
//            Picasso, Utils, FetchAction, GetAction, 
//            Dispatcher, BitmapHunter, PicassoDrawable, DeferredRequestCreator, 
//            Callback, ImageViewAction, Target, TargetAction, 
//            Transformation

public class RequestCreator
{

    RequestCreator()
    {
        picasso = null;
        data = new Request.Builder(null, 0);
    }

    RequestCreator(Picasso picasso1, Uri uri, int i)
    {
        if(picasso1.shutdown)
        {
            throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
        } else
        {
            picasso = picasso1;
            data = new Request.Builder(uri, i);
            return;
        }
    }

    public RequestCreator centerCrop()
    {
        data.centerCrop();
        return this;
    }

    public RequestCreator centerInside()
    {
        data.centerInside();
        return this;
    }

    public RequestCreator config(android.graphics.Bitmap.Config config1)
    {
        data.config(config1);
        return this;
    }

    public RequestCreator error(int i)
    {
        if(i == 0)
            throw new IllegalArgumentException("Error image resource invalid.");
        if(errorDrawable != null)
        {
            throw new IllegalStateException("Error image already set.");
        } else
        {
            errorResId = i;
            return this;
        }
    }

    public RequestCreator error(Drawable drawable)
    {
        if(drawable == null)
            throw new IllegalArgumentException("Error image may not be null.");
        if(errorResId != 0)
        {
            throw new IllegalStateException("Error image already set.");
        } else
        {
            errorDrawable = drawable;
            return this;
        }
    }

    public void fetch()
    {
        if(deferred)
            throw new IllegalStateException("Fit cannot be used with fetch.");
        if(data.hasImage())
        {
            Request request = picasso.transformRequest(data.build());
            String s = Utils.createKey(request);
            FetchAction fetchaction = new FetchAction(picasso, request, skipMemoryCache, s);
            picasso.enqueueAndSubmit(fetchaction);
        }
    }

    public RequestCreator fit()
    {
        deferred = true;
        return this;
    }

    public Bitmap get()
        throws IOException
    {
        Utils.checkNotMain();
        if(deferred)
            throw new IllegalStateException("Fit cannot be used with get.");
        if(!data.hasImage())
        {
            return null;
        } else
        {
            Request request = picasso.transformRequest(data.build());
            String s = Utils.createKey(request, new StringBuilder());
            GetAction getaction = new GetAction(picasso, request, skipMemoryCache, s);
            return BitmapHunter.forRequest(picasso.context, picasso, picasso.dispatcher, picasso.cache, picasso.stats, getaction, picasso.dispatcher.downloader).hunt();
        }
    }

    public void into(ImageView imageview)
    {
        into(imageview, null);
    }

    public void into(ImageView imageview, Callback callback)
    {
        if(imageview == null)
            throw new IllegalArgumentException("Target must not be null.");
        if(data.hasImage()) goto _L2; else goto _L1
_L1:
        picasso.cancelRequest(imageview);
        PicassoDrawable.setPlaceholder(imageview, placeholderResId, placeholderDrawable);
_L4:
        return;
_L2:
        Request request;
        String s;
        if(deferred)
        {
            if(data.hasSize())
                throw new IllegalStateException("Fit cannot be used with resize.");
            int i = imageview.getMeasuredWidth();
            int j = imageview.getMeasuredHeight();
            if(i == 0 || j == 0)
            {
                PicassoDrawable.setPlaceholder(imageview, placeholderResId, placeholderDrawable);
                picasso.defer(imageview, new DeferredRequestCreator(this, imageview, callback));
                return;
            }
            data.resize(i, j);
        }
        request = picasso.transformRequest(data.build());
        s = Utils.createKey(request);
        if(skipMemoryCache)
            break; /* Loop/switch isn't completed */
        Bitmap bitmap = picasso.quickMemoryCacheCheck(s);
        if(bitmap == null)
            break; /* Loop/switch isn't completed */
        picasso.cancelRequest(imageview);
        PicassoDrawable.setBitmap(imageview, picasso.context, bitmap, Picasso.LoadedFrom.MEMORY, noFade, picasso.debugging);
        if(callback != null)
        {
            callback.onSuccess();
            return;
        }
        if(true) goto _L4; else goto _L3
_L3:
        PicassoDrawable.setPlaceholder(imageview, placeholderResId, placeholderDrawable);
        ImageViewAction imageviewaction = new ImageViewAction(picasso, imageview, request, skipMemoryCache, noFade, errorResId, errorDrawable, s, callback);
        picasso.enqueueAndSubmit(imageviewaction);
        return;
    }

    public void into(Target target)
    {
        if(target == null)
            throw new IllegalArgumentException("Target must not be null.");
        if(deferred)
            throw new IllegalStateException("Fit cannot be used with a Target.");
        Drawable drawable;
        if(placeholderResId != 0)
            drawable = picasso.context.getResources().getDrawable(placeholderResId);
        else
            drawable = placeholderDrawable;
        if(!data.hasImage())
        {
            picasso.cancelRequest(target);
            target.onPrepareLoad(drawable);
            return;
        }
        Request request = picasso.transformRequest(data.build());
        String s = Utils.createKey(request);
        if(!skipMemoryCache)
        {
            Bitmap bitmap = picasso.quickMemoryCacheCheck(s);
            if(bitmap != null)
            {
                picasso.cancelRequest(target);
                target.onBitmapLoaded(bitmap, Picasso.LoadedFrom.MEMORY);
                return;
            }
        }
        target.onPrepareLoad(drawable);
        TargetAction targetaction = new TargetAction(picasso, target, request, skipMemoryCache, s);
        picasso.enqueueAndSubmit(targetaction);
    }

    public RequestCreator noFade()
    {
        noFade = true;
        return this;
    }

    public RequestCreator placeholder(int i)
    {
        if(i == 0)
            throw new IllegalArgumentException("Placeholder image resource invalid.");
        if(placeholderDrawable != null)
        {
            throw new IllegalStateException("Placeholder image already set.");
        } else
        {
            placeholderResId = i;
            return this;
        }
    }

    public RequestCreator placeholder(Drawable drawable)
    {
        if(placeholderResId != 0)
        {
            throw new IllegalStateException("Placeholder image already set.");
        } else
        {
            placeholderDrawable = drawable;
            return this;
        }
    }

    public RequestCreator resize(int i, int j)
    {
        data.resize(i, j);
        return this;
    }

    public RequestCreator resizeDimen(int i, int j)
    {
        Resources resources = picasso.context.getResources();
        return resize(resources.getDimensionPixelSize(i), resources.getDimensionPixelSize(j));
    }

    public RequestCreator rotate(float f)
    {
        data.rotate(f);
        return this;
    }

    public RequestCreator rotate(float f, float f1, float f2)
    {
        data.rotate(f, f1, f2);
        return this;
    }

    public RequestCreator skipMemoryCache()
    {
        skipMemoryCache = true;
        return this;
    }

    public RequestCreator transform(Transformation transformation)
    {
        data.transform(transformation);
        return this;
    }

    RequestCreator unfit()
    {
        deferred = false;
        return this;
    }

    private final Request.Builder data;
    private boolean deferred;
    private Drawable errorDrawable;
    private int errorResId;
    private boolean noFade;
    private final Picasso picasso;
    private Drawable placeholderDrawable;
    private int placeholderResId;
    private boolean skipMemoryCache;
}
