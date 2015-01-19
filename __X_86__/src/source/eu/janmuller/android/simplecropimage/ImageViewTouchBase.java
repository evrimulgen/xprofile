// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package eu.janmuller.android.simplecropimage;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;

// Referenced classes of package eu.janmuller.android.simplecropimage:
//            RotateBitmap

abstract class ImageViewTouchBase extends ImageView
{
    public static interface Recycler
    {

        public abstract void recycle(Bitmap bitmap);
    }


    public ImageViewTouchBase(Context context)
    {
        super(context);
        mBaseMatrix = new Matrix();
        mSuppMatrix = new Matrix();
        mDisplayMatrix = new Matrix();
        mMatrixValues = new float[9];
        mBitmapDisplayed = new RotateBitmap(null);
        mThisWidth = -1;
        mThisHeight = -1;
        mHandler = new Handler();
        mOnLayoutRunnable = null;
        init();
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mBaseMatrix = new Matrix();
        mSuppMatrix = new Matrix();
        mDisplayMatrix = new Matrix();
        mMatrixValues = new float[9];
        mBitmapDisplayed = new RotateBitmap(null);
        mThisWidth = -1;
        mThisHeight = -1;
        mHandler = new Handler();
        mOnLayoutRunnable = null;
        init();
    }

    private void getProperBaseMatrix(RotateBitmap rotatebitmap, Matrix matrix)
    {
        float f = getWidth();
        float f1 = getHeight();
        float f2 = rotatebitmap.getWidth();
        float f3 = rotatebitmap.getHeight();
        rotatebitmap.getRotation();
        matrix.reset();
        float f4 = Math.min(Math.min(f / f2, 2.0F), Math.min(f1 / f3, 2.0F));
        matrix.postConcat(rotatebitmap.getRotateMatrix());
        matrix.postScale(f4, f4);
        matrix.postTranslate((f - f2 * f4) / 2.0F, (f1 - f3 * f4) / 2.0F);
    }

    private void init()
    {
        setScaleType(android.widget.ImageView.ScaleType.MATRIX);
    }

    private void setImageBitmap(Bitmap bitmap, int i)
    {
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if(drawable != null)
            drawable.setDither(true);
        Bitmap bitmap1 = mBitmapDisplayed.getBitmap();
        mBitmapDisplayed.setBitmap(bitmap);
        mBitmapDisplayed.setRotation(i);
        if(bitmap1 != null && bitmap1 != bitmap && mRecycler != null)
            mRecycler.recycle(bitmap1);
    }

    protected void center(boolean flag, boolean flag1)
    {
        if(mBitmapDisplayed.getBitmap() == null)
            return;
        Matrix matrix = getImageViewMatrix();
        RectF rectf = new RectF(0.0F, 0.0F, mBitmapDisplayed.getBitmap().getWidth(), mBitmapDisplayed.getBitmap().getHeight());
        matrix.mapRect(rectf);
        float f = rectf.height();
        float f1 = rectf.width();
        float f2 = 0.0F;
        float f3;
        if(flag1)
        {
            int k = getHeight();
            if(f < (float)k)
                f2 = ((float)k - f) / 2.0F - rectf.top;
            else
            if(rectf.top > 0.0F)
            {
                f2 = -rectf.top;
            } else
            {
                int l = rectf.bottom != (float)k;
                f2 = 0.0F;
                if(l < 0)
                    f2 = (float)getHeight() - rectf.bottom;
            }
        }
        f3 = 0.0F;
        if(flag)
        {
            int i = getWidth();
            if(f1 < (float)i)
                f3 = ((float)i - f1) / 2.0F - rectf.left;
            else
            if(rectf.left > 0.0F)
            {
                f3 = -rectf.left;
            } else
            {
                int j = rectf.right != (float)i;
                f3 = 0.0F;
                if(j < 0)
                    f3 = (float)i - rectf.right;
            }
        }
        postTranslate(f3, f2);
        setImageMatrix(getImageViewMatrix());
    }

    public void clear()
    {
        setImageBitmapResetBase(null, true);
    }

    protected Matrix getImageViewMatrix()
    {
        mDisplayMatrix.set(mBaseMatrix);
        mDisplayMatrix.postConcat(mSuppMatrix);
        return mDisplayMatrix;
    }

    protected float getScale()
    {
        return getScale(mSuppMatrix);
    }

    protected float getScale(Matrix matrix)
    {
        return getValue(matrix, 0);
    }

    protected float getValue(Matrix matrix, int i)
    {
        matrix.getValues(mMatrixValues);
        return mMatrixValues[i];
    }

    protected float maxZoom()
    {
        if(mBitmapDisplayed.getBitmap() == null)
            return 1.0F;
        else
            return 4F * Math.max((float)mBitmapDisplayed.getWidth() / (float)mThisWidth, (float)mBitmapDisplayed.getHeight() / (float)mThisHeight);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4 && getScale() > 1.0F)
        {
            zoomTo(1.0F);
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        mLeft = i;
        mRight = k;
        mTop = j;
        mBottom = l;
        mThisWidth = k - i;
        mThisHeight = l - j;
        Runnable runnable = mOnLayoutRunnable;
        if(runnable != null)
        {
            mOnLayoutRunnable = null;
            runnable.run();
        }
        if(mBitmapDisplayed.getBitmap() != null)
        {
            getProperBaseMatrix(mBitmapDisplayed, mBaseMatrix);
            setImageMatrix(getImageViewMatrix());
        }
    }

    protected void panBy(float f, float f1)
    {
        postTranslate(f, f1);
        setImageMatrix(getImageViewMatrix());
    }

    protected void postTranslate(float f, float f1)
    {
        mSuppMatrix.postTranslate(f, f1);
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        setImageBitmap(bitmap, 0);
    }

    public void setImageBitmapResetBase(Bitmap bitmap, boolean flag)
    {
        setImageRotateBitmapResetBase(new RotateBitmap(bitmap), flag);
    }

    public void setImageRotateBitmapResetBase(final RotateBitmap bitmap, final boolean resetSupp)
    {
        if(getWidth() <= 0)
        {
            mOnLayoutRunnable = new Runnable() {

                public void run()
                {
                    setImageRotateBitmapResetBase(bitmap, resetSupp);
                }

                final ImageViewTouchBase this$0;
                final RotateBitmap val$bitmap;
                final boolean val$resetSupp;

            
            {
                this$0 = ImageViewTouchBase.this;
                bitmap = rotatebitmap;
                resetSupp = flag;
                super();
            }
            }
;
            return;
        }
        if(bitmap.getBitmap() != null)
        {
            getProperBaseMatrix(bitmap, mBaseMatrix);
            setImageBitmap(bitmap.getBitmap(), bitmap.getRotation());
        } else
        {
            mBaseMatrix.reset();
            setImageBitmap(null);
        }
        if(resetSupp)
            mSuppMatrix.reset();
        setImageMatrix(getImageViewMatrix());
        mMaxZoom = maxZoom();
    }

    public void setRecycler(Recycler recycler)
    {
        mRecycler = recycler;
    }

    protected void zoomIn()
    {
        zoomIn(1.25F);
    }

    protected void zoomIn(float f)
    {
        while(getScale() >= mMaxZoom || mBitmapDisplayed.getBitmap() == null) 
            return;
        float f1 = (float)getWidth() / 2.0F;
        float f2 = (float)getHeight() / 2.0F;
        mSuppMatrix.postScale(f, f, f1, f2);
        setImageMatrix(getImageViewMatrix());
    }

    protected void zoomOut()
    {
        zoomOut(1.25F);
    }

    protected void zoomOut(float f)
    {
        if(mBitmapDisplayed.getBitmap() == null)
            return;
        float f1 = (float)getWidth() / 2.0F;
        float f2 = (float)getHeight() / 2.0F;
        Matrix matrix = new Matrix(mSuppMatrix);
        matrix.postScale(1.0F / f, 1.0F / f, f1, f2);
        if(getScale(matrix) < 1.0F)
            mSuppMatrix.setScale(1.0F, 1.0F, f1, f2);
        else
            mSuppMatrix.postScale(1.0F / f, 1.0F / f, f1, f2);
        setImageMatrix(getImageViewMatrix());
        center(true, true);
    }

    protected void zoomTo(float f)
    {
        zoomTo(f, (float)getWidth() / 2.0F, (float)getHeight() / 2.0F);
    }

    protected void zoomTo(float f, float f1, float f2)
    {
        if(f > mMaxZoom)
            f = mMaxZoom;
        float f3 = f / getScale();
        mSuppMatrix.postScale(f3, f3, f1, f2);
        setImageMatrix(getImageViewMatrix());
        center(true, true);
    }

    protected void zoomTo(float f, final float centerX, final float centerY, final float durationMs)
    {
        final float incrementPerMs = (f - getScale()) / durationMs;
        final float oldScale = getScale();
        final long startTime = System.currentTimeMillis();
        mHandler.post(new Runnable() {

            public void run()
            {
                long l = System.currentTimeMillis();
                float f1 = Math.min(durationMs, l - startTime);
                float f2 = oldScale + f1 * incrementPerMs;
                zoomTo(f2, centerX, centerY);
                if(f1 < durationMs)
                    mHandler.post(this);
            }

            final ImageViewTouchBase this$0;
            final float val$centerX;
            final float val$centerY;
            final float val$durationMs;
            final float val$incrementPerMs;
            final float val$oldScale;
            final long val$startTime;

            
            {
                this$0 = ImageViewTouchBase.this;
                durationMs = f;
                startTime = l;
                oldScale = f1;
                incrementPerMs = f2;
                centerX = f3;
                centerY = f4;
                super();
            }
        }
);
    }

    static final float SCALE_RATE = 1.25F;
    private static final String TAG = "ImageViewTouchBase";
    protected Matrix mBaseMatrix;
    protected final RotateBitmap mBitmapDisplayed;
    int mBottom;
    private final Matrix mDisplayMatrix;
    protected Handler mHandler;
    int mLeft;
    private final float mMatrixValues[];
    float mMaxZoom;
    private Runnable mOnLayoutRunnable;
    private Recycler mRecycler;
    int mRight;
    protected Matrix mSuppMatrix;
    int mThisHeight;
    int mThisWidth;
    int mTop;
}
