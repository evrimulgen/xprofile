// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.graphics.Bitmap;

// Referenced classes of package com.squareup.picasso:
//            Action, Target, Picasso, Request

final class TargetAction extends Action
{

    TargetAction(Picasso picasso, Target target, Request request, boolean flag, String s)
    {
        super(picasso, target, request, flag, false, 0, null, s);
    }

    void complete(Bitmap bitmap, Picasso.LoadedFrom loadedfrom)
    {
        if(bitmap == null)
            throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[] {
                this
            }));
        Target target = (Target)getTarget();
        if(target != null)
        {
            target.onBitmapLoaded(bitmap, loadedfrom);
            if(bitmap.isRecycled())
                throw new IllegalStateException("Target callback must not recycle bitmap!");
        }
    }

    void error()
    {
        Target target = (Target)getTarget();
        if(target != null)
            target.onBitmapFailed(errorDrawable);
    }
}
