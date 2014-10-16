// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.*;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;

// Referenced classes of package com.squareup.picasso:
//            ContentStreamBitmapHunter, Request, Picasso, Dispatcher, 
//            Cache, Stats, Action

class MediaStoreBitmapHunter extends ContentStreamBitmapHunter
{
    static final class PicassoKind extends Enum
    {

        public static PicassoKind valueOf(String s)
        {
            return (PicassoKind)Enum.valueOf(com/squareup/picasso/MediaStoreBitmapHunter$PicassoKind, s);
        }

        public static PicassoKind[] values()
        {
            return (PicassoKind[])$VALUES.clone();
        }

        private static final PicassoKind $VALUES[];
        public static final PicassoKind FULL;
        public static final PicassoKind MICRO;
        public static final PicassoKind MINI;
        final int androidKind;
        final int height;
        final int width;

        static 
        {
            MICRO = new PicassoKind("MICRO", 0, 3, 96, 96);
            MINI = new PicassoKind("MINI", 1, 1, 512, 384);
            FULL = new PicassoKind("FULL", 2, 2, -1, -1);
            PicassoKind apicassokind[] = new PicassoKind[3];
            apicassokind[0] = MICRO;
            apicassokind[1] = MINI;
            apicassokind[2] = FULL;
            $VALUES = apicassokind;
        }

        private PicassoKind(String s, int i, int j, int k, int l)
        {
            super(s, i);
            androidKind = j;
            width = k;
            height = l;
        }
    }


    MediaStoreBitmapHunter(Context context, Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action)
    {
        super(context, picasso, dispatcher, cache, stats, action);
    }

    static int getExitOrientation(ContentResolver contentresolver, Uri uri)
    {
        Cursor cursor = null;
        boolean flag;
        int i;
        int j;
        try
        {
            cursor = contentresolver.query(uri, CONTENT_ORIENTATION, null, null, null);
        }
        catch(RuntimeException runtimeexception)
        {
            if(cursor != null)
                cursor.close();
            return 0;
        }
        if(cursor == null) goto _L2; else goto _L1
_L1:
        flag = cursor.moveToFirst();
        if(flag) goto _L3; else goto _L2
_L2:
        if(cursor != null)
            cursor.close();
        j = 0;
_L5:
        return j;
_L3:
        i = cursor.getInt(0);
        j = i;
        if(cursor == null) goto _L5; else goto _L4
_L4:
        cursor.close();
        return j;
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    static PicassoKind getPicassoKind(int i, int j)
    {
        if(i <= PicassoKind.MICRO.width && j <= PicassoKind.MICRO.height)
            return PicassoKind.MICRO;
        if(i <= PicassoKind.MINI.width && j <= PicassoKind.MINI.height)
            return PicassoKind.MINI;
        else
            return PicassoKind.FULL;
    }

    Bitmap decode(Request request)
        throws IOException
    {
        ContentResolver contentresolver;
        contentresolver = context.getContentResolver();
        setExifRotation(getExitOrientation(contentresolver, request.uri));
        if(!request.hasSize()) goto _L2; else goto _L1
_L1:
        PicassoKind picassokind = getPicassoKind(request.targetWidth, request.targetHeight);
        if(picassokind != PicassoKind.FULL) goto _L4; else goto _L3
_L3:
        Bitmap bitmap = super.decode(request);
_L5:
        return bitmap;
_L4:
        long l = ContentUris.parseId(request.uri);
        android.graphics.BitmapFactory.Options options = createBitmapOptions(request);
        options.inJustDecodeBounds = true;
        calculateInSampleSize(request.targetWidth, request.targetHeight, picassokind.width, picassokind.height, options);
        bitmap = android.provider.MediaStore.Images.Thumbnails.getThumbnail(contentresolver, l, picassokind.androidKind, options);
        if(bitmap != null) goto _L5; else goto _L2
_L2:
        return super.decode(request);
    }

    private static final String CONTENT_ORIENTATION[] = {
        "orientation"
    };

}
