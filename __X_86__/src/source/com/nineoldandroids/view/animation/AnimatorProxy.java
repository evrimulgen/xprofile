// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.view.animation;

import android.graphics.*;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class AnimatorProxy extends Animation
{

    private AnimatorProxy(View view)
    {
        mAlpha = 1.0F;
        mScaleX = 1.0F;
        mScaleY = 1.0F;
        setDuration(0L);
        setFillAfter(true);
        view.setAnimation(this);
        mView = new WeakReference(view);
    }

    private void computeRect(RectF rectf, View view)
    {
        rectf.set(0.0F, 0.0F, view.getWidth(), view.getHeight());
        Matrix matrix = mTempMatrix;
        matrix.reset();
        transformMatrix(matrix, view);
        mTempMatrix.mapRect(rectf);
        rectf.offset(view.getLeft(), view.getTop());
        if(rectf.right < rectf.left)
        {
            float f1 = rectf.right;
            rectf.right = rectf.left;
            rectf.left = f1;
        }
        if(rectf.bottom < rectf.top)
        {
            float f = rectf.top;
            rectf.top = rectf.bottom;
            rectf.bottom = f;
        }
    }

    private void invalidateAfterUpdate()
    {
        View view = (View)mView.get();
        if(view == null || view.getParent() == null)
        {
            return;
        } else
        {
            RectF rectf = mAfter;
            computeRect(rectf, view);
            rectf.union(mBefore);
            ((View)view.getParent()).invalidate((int)Math.floor(rectf.left), (int)Math.floor(rectf.top), (int)Math.ceil(rectf.right), (int)Math.ceil(rectf.bottom));
            return;
        }
    }

    private void prepareForUpdate()
    {
        View view = (View)mView.get();
        if(view != null)
            computeRect(mBefore, view);
    }

    private void transformMatrix(Matrix matrix, View view)
    {
        float f = view.getWidth();
        float f1 = view.getHeight();
        boolean flag = mHasPivot;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        if(flag)
            f2 = mPivotX;
        else
            f2 = f / 2.0F;
        if(flag)
            f3 = mPivotY;
        else
            f3 = f1 / 2.0F;
        f4 = mRotationX;
        f5 = mRotationY;
        f6 = mRotationZ;
        if(f4 != 0.0F || f5 != 0.0F || f6 != 0.0F)
        {
            Camera camera = mCamera;
            camera.save();
            camera.rotateX(f4);
            camera.rotateY(f5);
            camera.rotateZ(-f6);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f2, -f3);
            matrix.postTranslate(f2, f3);
        }
        f7 = mScaleX;
        f8 = mScaleY;
        if(f7 != 1.0F || f8 != 1.0F)
        {
            matrix.postScale(f7, f8);
            matrix.postTranslate(-(f2 / f) * (f7 * f - f), -(f3 / f1) * (f8 * f1 - f1));
        }
        matrix.postTranslate(mTranslationX, mTranslationY);
    }

    public static AnimatorProxy wrap(View view)
    {
        AnimatorProxy animatorproxy = (AnimatorProxy)PROXIES.get(view);
        if(animatorproxy == null || animatorproxy != view.getAnimation())
        {
            animatorproxy = new AnimatorProxy(view);
            PROXIES.put(view, animatorproxy);
        }
        return animatorproxy;
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        View view = (View)mView.get();
        if(view != null)
        {
            transformation.setAlpha(mAlpha);
            transformMatrix(transformation.getMatrix(), view);
        }
    }

    public float getAlpha()
    {
        return mAlpha;
    }

    public float getPivotX()
    {
        return mPivotX;
    }

    public float getPivotY()
    {
        return mPivotY;
    }

    public float getRotation()
    {
        return mRotationZ;
    }

    public float getRotationX()
    {
        return mRotationX;
    }

    public float getRotationY()
    {
        return mRotationY;
    }

    public float getScaleX()
    {
        return mScaleX;
    }

    public float getScaleY()
    {
        return mScaleY;
    }

    public int getScrollX()
    {
        View view = (View)mView.get();
        if(view == null)
            return 0;
        else
            return view.getScrollX();
    }

    public int getScrollY()
    {
        View view = (View)mView.get();
        if(view == null)
            return 0;
        else
            return view.getScrollY();
    }

    public float getTranslationX()
    {
        return mTranslationX;
    }

    public float getTranslationY()
    {
        return mTranslationY;
    }

    public float getX()
    {
        View view = (View)mView.get();
        if(view == null)
            return 0.0F;
        else
            return (float)view.getLeft() + mTranslationX;
    }

    public float getY()
    {
        View view = (View)mView.get();
        if(view == null)
            return 0.0F;
        else
            return (float)view.getTop() + mTranslationY;
    }

    public void setAlpha(float f)
    {
        if(mAlpha != f)
        {
            mAlpha = f;
            View view = (View)mView.get();
            if(view != null)
                view.invalidate();
        }
    }

    public void setPivotX(float f)
    {
        if(!mHasPivot || mPivotX != f)
        {
            prepareForUpdate();
            mHasPivot = true;
            mPivotX = f;
            invalidateAfterUpdate();
        }
    }

    public void setPivotY(float f)
    {
        if(!mHasPivot || mPivotY != f)
        {
            prepareForUpdate();
            mHasPivot = true;
            mPivotY = f;
            invalidateAfterUpdate();
        }
    }

    public void setRotation(float f)
    {
        if(mRotationZ != f)
        {
            prepareForUpdate();
            mRotationZ = f;
            invalidateAfterUpdate();
        }
    }

    public void setRotationX(float f)
    {
        if(mRotationX != f)
        {
            prepareForUpdate();
            mRotationX = f;
            invalidateAfterUpdate();
        }
    }

    public void setRotationY(float f)
    {
        if(mRotationY != f)
        {
            prepareForUpdate();
            mRotationY = f;
            invalidateAfterUpdate();
        }
    }

    public void setScaleX(float f)
    {
        if(mScaleX != f)
        {
            prepareForUpdate();
            mScaleX = f;
            invalidateAfterUpdate();
        }
    }

    public void setScaleY(float f)
    {
        if(mScaleY != f)
        {
            prepareForUpdate();
            mScaleY = f;
            invalidateAfterUpdate();
        }
    }

    public void setScrollX(int i)
    {
        View view = (View)mView.get();
        if(view != null)
            view.scrollTo(i, view.getScrollY());
    }

    public void setScrollY(int i)
    {
        View view = (View)mView.get();
        if(view != null)
            view.scrollTo(view.getScrollX(), i);
    }

    public void setTranslationX(float f)
    {
        if(mTranslationX != f)
        {
            prepareForUpdate();
            mTranslationX = f;
            invalidateAfterUpdate();
        }
    }

    public void setTranslationY(float f)
    {
        if(mTranslationY != f)
        {
            prepareForUpdate();
            mTranslationY = f;
            invalidateAfterUpdate();
        }
    }

    public void setX(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            setTranslationX(f - (float)view.getLeft());
    }

    public void setY(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            setTranslationY(f - (float)view.getTop());
    }

    public static final boolean NEEDS_PROXY;
    private static final WeakHashMap PROXIES = new WeakHashMap();
    private final RectF mAfter = new RectF();
    private float mAlpha;
    private final RectF mBefore = new RectF();
    private final Camera mCamera = new Camera();
    private boolean mHasPivot;
    private float mPivotX;
    private float mPivotY;
    private float mRotationX;
    private float mRotationY;
    private float mRotationZ;
    private float mScaleX;
    private float mScaleY;
    private final Matrix mTempMatrix = new Matrix();
    private float mTranslationX;
    private float mTranslationY;
    private final WeakReference mView;

    static 
    {
        boolean flag;
        if(Integer.valueOf(android.os.Build.VERSION.SDK).intValue() < 11)
            flag = true;
        else
            flag = false;
        NEEDS_PROXY = flag;
    }
}
