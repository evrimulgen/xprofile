// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.*;
import android.graphics.Bitmap;
import android.net.Uri;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.squareup.picasso:
//            BitmapHunter, Request, Utils, Picasso, 
//            Dispatcher, Cache, Stats, Action

class ContactsPhotoBitmapHunter extends BitmapHunter
{
    private static class ContactPhotoStreamIcs
    {

        static InputStream get(ContentResolver contentresolver, Uri uri)
        {
            return android.provider.ContactsContract.Contacts.openContactPhotoInputStream(contentresolver, uri, true);
        }

        private ContactPhotoStreamIcs()
        {
        }
    }


    ContactsPhotoBitmapHunter(Context context1, Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action)
    {
        super(picasso, dispatcher, cache, stats, action);
        context = context1;
    }

    private Bitmap decodeStream(InputStream inputstream, Request request)
        throws IOException
    {
        android.graphics.BitmapFactory.Options options;
        InputStream inputstream1;
        if(inputstream == null)
            return null;
        options = createBitmapOptions(request);
        if(!request.hasSize())
            break MISSING_BLOCK_LABEL_54;
        options.inJustDecodeBounds = true;
        inputstream1 = getInputStream();
        BitmapFactoryInstrumentation.decodeStream(inputstream1, null, options);
        Utils.closeQuietly(inputstream1);
        calculateInSampleSize(request.targetWidth, request.targetHeight, options);
        return BitmapFactoryInstrumentation.decodeStream(inputstream, null, options);
        Exception exception;
        exception;
        Utils.closeQuietly(inputstream1);
        throw exception;
    }

    private InputStream getInputStream()
        throws IOException
    {
        ContentResolver contentresolver = context.getContentResolver();
        Uri uri = getData().uri;
        switch(matcher.match(uri))
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("Invalid uri: ").append(uri).toString());

        case 1: // '\001'
            uri = android.provider.ContactsContract.Contacts.lookupContact(contentresolver, uri);
            if(uri == null)
                return null;
            // fall through

        case 3: // '\003'
            if(android.os.Build.VERSION.SDK_INT < 14)
                return android.provider.ContactsContract.Contacts.openContactPhotoInputStream(contentresolver, uri);
            else
                return ContactPhotoStreamIcs.get(contentresolver, uri);

        case 2: // '\002'
        case 4: // '\004'
            return contentresolver.openInputStream(uri);
        }
    }

    Bitmap decode(Request request)
        throws IOException
    {
        InputStream inputstream = null;
        Bitmap bitmap;
        inputstream = getInputStream();
        bitmap = decodeStream(inputstream, request);
        Utils.closeQuietly(inputstream);
        return bitmap;
        Exception exception;
        exception;
        Utils.closeQuietly(inputstream);
        throw exception;
    }

    Picasso.LoadedFrom getLoadedFrom()
    {
        return Picasso.LoadedFrom.DISK;
    }

    private static final int ID_CONTACT = 3;
    private static final int ID_DISPLAY_PHOTO = 4;
    private static final int ID_LOOKUP = 1;
    private static final int ID_THUMBNAIL = 2;
    private static final UriMatcher matcher;
    final Context context;

    static 
    {
        matcher = new UriMatcher(-1);
        matcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        matcher.addURI("com.android.contacts", "contacts/lookup/*", 1);
        matcher.addURI("com.android.contacts", "contacts/#/photo", 2);
        matcher.addURI("com.android.contacts", "contacts/#", 3);
        matcher.addURI("com.android.contacts", "display_photo/#", 4);
    }
}
