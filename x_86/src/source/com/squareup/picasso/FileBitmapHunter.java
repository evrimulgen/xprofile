// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import java.io.IOException;

// Referenced classes of package com.squareup.picasso:
//            ContentStreamBitmapHunter, Request, Picasso, Dispatcher, 
//            Cache, Stats, Action

class FileBitmapHunter extends ContentStreamBitmapHunter
{

    FileBitmapHunter(Context context, Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action)
    {
        super(context, picasso, dispatcher, cache, stats, action);
    }

    static int getFileExifRotation(Uri uri)
        throws IOException
    {
        switch((new ExifInterface(uri.getPath())).getAttributeInt("Orientation", 1))
        {
        case 4: // '\004'
        case 5: // '\005'
        case 7: // '\007'
        default:
            return 0;

        case 6: // '\006'
            return 90;

        case 3: // '\003'
            return 180;

        case 8: // '\b'
            return 270;
        }
    }

    Bitmap decode(Request request)
        throws IOException
    {
        setExifRotation(getFileExifRotation(request.uri));
        return super.decode(request);
    }
}
