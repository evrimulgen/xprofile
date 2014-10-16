// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.widget.ImageView;

final class PicassoDrawable extends Drawable
{

    PicassoDrawable(Context context, Drawable drawable, Bitmap bitmap, Picasso.LoadedFrom loadedfrom, boolean flag, boolean flag1)
    {
        alpha = 255;
        Resources resources = context.getResources();
        debugging = flag1;
        density = resources.getDisplayMetrics().density;
        loadedFrom = loadedfrom;
        image = new BitmapDrawable(resources, bitmap);
        boolean flag2;
        if(loadedfrom != Picasso.LoadedFrom.MEMORY && !flag)
            flag2 = true;
        else
            flag2 = false;
        if(flag2)
        {
            placeholder = drawable;
            animating = true;
            startTimeMillis = SystemClock.uptimeMillis();
        }
    }

    private void drawDebugIndicator(Canvas canvas)
    {
        DEBUG_PAINT.setColor(-1);
        canvas.drawPath(getTrianglePath(new Point(0, 0), (int)(16F * density)), DEBUG_PAINT);
        DEBUG_PAINT.setColor(loadedFrom.debugColor);
        canvas.drawPath(getTrianglePath(new Point(0, 0), (int)(15F * density)), DEBUG_PAINT);
    }

    private static Path getTrianglePath(Point point, int i)
    {
        Point point1 = new Point(i + point.x, point.y);
        Point point2 = new Point(point.x, i + point.y);
        Path path = new Path();
        path.moveTo(point.x, point.y);
        path.lineTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        return path;
    }

    static void setBitmap(ImageView imageview, Context context, Bitmap bitmap, Picasso.LoadedFrom loadedfrom, boolean flag, boolean flag1)
    {
        Drawable drawable = imageview.getDrawable();
        if(drawable instanceof AnimationDrawable)
            ((AnimationDrawable)drawable).stop();
        imageview.setImageDrawable(new PicassoDrawable(context, drawable, bitmap, loadedfrom, flag, flag1));
    }

    static void setPlaceholder(ImageView imageview, int i, Drawable drawable)
    {
        if(i != 0)
            imageview.setImageResource(i);
        else
            imageview.setImageDrawable(drawable);
        if(imageview.getDrawable() instanceof AnimationDrawable)
            ((AnimationDrawable)imageview.getDrawable()).start();
    }

    public void draw(Canvas canvas)
    {
        if(!animating)
        {
            image.draw(canvas);
        } else
        {
            float f = (float)(SystemClock.uptimeMillis() - startTimeMillis) / 200F;
            if(f >= 1.0F)
            {
                animating = false;
                placeholder = null;
                image.draw(canvas);
            } else
            {
                if(placeholder != null)
                    placeholder.draw(canvas);
                int i = (int)(f * (float)alpha);
                image.setAlpha(i);
                image.draw(canvas);
                image.setAlpha(alpha);
                invalidateSelf();
            }
        }
        if(debugging)
            drawDebugIndicator(canvas);
    }

    public int getIntrinsicHeight()
    {
        return image.getIntrinsicHeight();
    }

    public int getIntrinsicWidth()
    {
        return image.getIntrinsicWidth();
    }

    public int getOpacity()
    {
        return image.getOpacity();
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        image.setBounds(rect);
        if(placeholder != null)
            placeholder.setBounds(rect);
    }

    public void setAlpha(int i)
    {
        alpha = i;
        if(placeholder != null)
            placeholder.setAlpha(i);
        image.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(placeholder != null)
            placeholder.setColorFilter(colorfilter);
        image.setColorFilter(colorfilter);
    }

    private static final Paint DEBUG_PAINT = new Paint();
    private static final float FADE_DURATION = 200F;
    int alpha;
    boolean animating;
    private final boolean debugging;
    private final float density;
    final BitmapDrawable image;
    private final Picasso.LoadedFrom loadedFrom;
    Drawable placeholder;
    long startTimeMillis;

}
