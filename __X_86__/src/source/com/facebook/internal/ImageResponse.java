// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.internal;

import android.graphics.Bitmap;

// Referenced classes of package com.facebook.internal:
//            ImageRequest

public class ImageResponse
{

    ImageResponse(ImageRequest imagerequest, Exception exception, boolean flag, Bitmap bitmap1)
    {
        request = imagerequest;
        error = exception;
        bitmap = bitmap1;
        isCachedRedirect = flag;
    }

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public Exception getError()
    {
        return error;
    }

    public ImageRequest getRequest()
    {
        return request;
    }

    public boolean isCachedRedirect()
    {
        return isCachedRedirect;
    }

    private Bitmap bitmap;
    private Exception error;
    private boolean isCachedRedirect;
    private ImageRequest request;
}
