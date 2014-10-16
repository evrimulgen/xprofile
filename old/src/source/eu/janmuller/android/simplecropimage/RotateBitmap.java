// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package eu.janmuller.android.simplecropimage;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class RotateBitmap
{

    public RotateBitmap(Bitmap bitmap)
    {
        mBitmap = bitmap;
        mRotation = 0;
    }

    public RotateBitmap(Bitmap bitmap, int i)
    {
        mBitmap = bitmap;
        mRotation = i % 360;
    }

    public Bitmap getBitmap()
    {
        return mBitmap;
    }

    public int getHeight()
    {
        if(isOrientationChanged())
            return mBitmap.getWidth();
        else
            return mBitmap.getHeight();
    }

    public Matrix getRotateMatrix()
    {
        Matrix matrix = new Matrix();
        if(mRotation != 0)
        {
            int i = mBitmap.getWidth() / 2;
            int j = mBitmap.getHeight() / 2;
            matrix.preTranslate(-i, -j);
            matrix.postRotate(mRotation);
            matrix.postTranslate(getWidth() / 2, getHeight() / 2);
        }
        return matrix;
    }

    public int getRotation()
    {
        return mRotation;
    }

    public int getWidth()
    {
        if(isOrientationChanged())
            return mBitmap.getHeight();
        else
            return mBitmap.getWidth();
    }

    public boolean isOrientationChanged()
    {
        return (mRotation / 90) % 2 != 0;
    }

    public void recycle()
    {
        if(mBitmap != null)
        {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    public void setBitmap(Bitmap bitmap)
    {
        mBitmap = bitmap;
    }

    public void setRotation(int i)
    {
        mRotation = i;
    }

    public static final String TAG = "RotateBitmap";
    private Bitmap mBitmap;
    private int mRotation;
}
