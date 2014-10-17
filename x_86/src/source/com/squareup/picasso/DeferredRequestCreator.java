// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

// Referenced classes of package com.squareup.picasso:
//            RequestCreator, Callback

class DeferredRequestCreator
    implements android.view.ViewTreeObserver.OnPreDrawListener
{

    DeferredRequestCreator(RequestCreator requestcreator, ImageView imageview)
    {
        this(requestcreator, imageview, null);
    }

    DeferredRequestCreator(RequestCreator requestcreator, ImageView imageview, Callback callback1)
    {
        creator = requestcreator;
        target = new WeakReference(imageview);
        callback = callback1;
        imageview.getViewTreeObserver().addOnPreDrawListener(this);
    }

    void cancel()
    {
        callback = null;
        ImageView imageview = (ImageView)target.get();
        ViewTreeObserver viewtreeobserver;
        if(imageview != null)
            if((viewtreeobserver = imageview.getViewTreeObserver()).isAlive())
            {
                viewtreeobserver.removeOnPreDrawListener(this);
                return;
            }
    }

    public boolean onPreDraw()
    {
        ImageView imageview = (ImageView)target.get();
        ViewTreeObserver viewtreeobserver;
        if(imageview != null)
            if((viewtreeobserver = imageview.getViewTreeObserver()).isAlive())
            {
                int i = imageview.getMeasuredWidth();
                int j = imageview.getMeasuredHeight();
                if(i > 0 && j > 0)
                {
                    viewtreeobserver.removeOnPreDrawListener(this);
                    creator.unfit().resize(i, j).into(imageview, callback);
                    return true;
                }
            }
        return true;
    }

    Callback callback;
    final RequestCreator creator;
    final WeakReference target;
}
