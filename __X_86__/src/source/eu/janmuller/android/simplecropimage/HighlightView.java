// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package eu.janmuller.android.simplecropimage;

import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.View;

class HighlightView
{
    static final class ModifyMode extends Enum
    {

        public static ModifyMode valueOf(String s)
        {
            return (ModifyMode)Enum.valueOf(eu/janmuller/android/simplecropimage/HighlightView$ModifyMode, s);
        }

        public static ModifyMode[] values()
        {
            return (ModifyMode[])$VALUES.clone();
        }

        private static final ModifyMode $VALUES[];
        public static final ModifyMode Grow;
        public static final ModifyMode Move;
        public static final ModifyMode None;

        static 
        {
            None = new ModifyMode("None", 0);
            Move = new ModifyMode("Move", 1);
            Grow = new ModifyMode("Grow", 2);
            ModifyMode amodifymode[] = new ModifyMode[3];
            amodifymode[0] = None;
            amodifymode[1] = Move;
            amodifymode[2] = Grow;
            $VALUES = amodifymode;
        }

        private ModifyMode(String s, int i)
        {
            super(s, i);
        }
    }


    public HighlightView(View view)
    {
        mMode = ModifyMode.None;
        mMaintainAspectRatio = false;
        mCircle = false;
        mContext = view;
    }

    private Rect computeLayout()
    {
        RectF rectf = new RectF(mCropRect.left, mCropRect.top, mCropRect.right, mCropRect.bottom);
        mMatrix.mapRect(rectf);
        return new Rect(Math.round(rectf.left), Math.round(rectf.top), Math.round(rectf.right), Math.round(rectf.bottom));
    }

    private void init()
    {
        Resources resources = mContext.getResources();
        mResizeDrawableWidth = resources.getDrawable(R.drawable.camera_crop_width);
        mResizeDrawableHeight = resources.getDrawable(R.drawable.camera_crop_height);
        mResizeDrawableDiagonal = resources.getDrawable(R.drawable.indicator_autocrop);
    }

    protected void draw(Canvas canvas)
    {
        if(!mHidden)
        {
            Path path = new Path();
            if(!hasFocus())
            {
                mOutlinePaint.setColor(0xff000000);
                canvas.drawRect(mDrawRect, mOutlinePaint);
                return;
            }
            Rect rect = new Rect();
            mContext.getDrawingRect(rect);
            if(mCircle)
            {
                canvas.save();
                float f = mDrawRect.width();
                float f1 = mDrawRect.height();
                path.addCircle((float)mDrawRect.left + f / 2.0F, (float)mDrawRect.top + f1 / 2.0F, f / 2.0F, android.graphics.Path.Direction.CW);
                mOutlinePaint.setColor(0xffef04d6);
                canvas.clipPath(path, android.graphics.Region.Op.DIFFERENCE);
                int k2;
                int l2;
                int i3;
                int j3;
                int k3;
                Paint paint4;
                if(hasFocus())
                    paint4 = mFocusPaint;
                else
                    paint4 = mNoFocusPaint;
                canvas.drawRect(rect, paint4);
                canvas.restore();
            } else
            {
                Rect rect1 = new Rect(rect.left, rect.top, rect.right, mDrawRect.top);
                if(rect1.width() > 0 && rect1.height() > 0)
                {
                    Rect rect2;
                    Rect rect3;
                    Rect rect4;
                    Paint paint3;
                    if(hasFocus())
                        paint3 = mFocusPaint;
                    else
                        paint3 = mNoFocusPaint;
                    canvas.drawRect(rect1, paint3);
                }
                rect2 = new Rect(rect.left, mDrawRect.bottom, rect.right, rect.bottom);
                if(rect2.width() > 0 && rect2.height() > 0)
                {
                    Paint paint2;
                    if(hasFocus())
                        paint2 = mFocusPaint;
                    else
                        paint2 = mNoFocusPaint;
                    canvas.drawRect(rect2, paint2);
                }
                rect3 = new Rect(rect.left, rect1.bottom, mDrawRect.left, rect2.top);
                if(rect3.width() > 0 && rect3.height() > 0)
                {
                    Paint paint1;
                    if(hasFocus())
                        paint1 = mFocusPaint;
                    else
                        paint1 = mNoFocusPaint;
                    canvas.drawRect(rect3, paint1);
                }
                rect4 = new Rect(mDrawRect.right, rect1.bottom, rect.right, rect2.top);
                if(rect4.width() > 0 && rect4.height() > 0)
                {
                    Paint paint;
                    if(hasFocus())
                        paint = mFocusPaint;
                    else
                        paint = mNoFocusPaint;
                    canvas.drawRect(rect4, paint);
                }
                path.addRect(new RectF(mDrawRect), android.graphics.Path.Direction.CW);
                mOutlinePaint.setColor(-30208);
            }
            canvas.drawPath(path, mOutlinePaint);
            if(mMode == ModifyMode.Grow)
                if(mCircle)
                {
                    k2 = mResizeDrawableDiagonal.getIntrinsicWidth();
                    l2 = mResizeDrawableDiagonal.getIntrinsicHeight();
                    i3 = (int)Math.round(Math.cos(0.78539816339744828D) * ((double)mDrawRect.width() / 2D));
                    j3 = (i3 + (mDrawRect.left + mDrawRect.width() / 2)) - k2 / 2;
                    k3 = (mDrawRect.top + mDrawRect.height() / 2) - i3 - l2 / 2;
                    mResizeDrawableDiagonal.setBounds(j3, k3, j3 + mResizeDrawableDiagonal.getIntrinsicWidth(), k3 + mResizeDrawableDiagonal.getIntrinsicHeight());
                    mResizeDrawableDiagonal.draw(canvas);
                    return;
                } else
                {
                    int i = 1 + mDrawRect.left;
                    int j = 1 + mDrawRect.right;
                    int k = 4 + mDrawRect.top;
                    int l = 3 + mDrawRect.bottom;
                    int i1 = mResizeDrawableWidth.getIntrinsicWidth() / 2;
                    int j1 = mResizeDrawableWidth.getIntrinsicHeight() / 2;
                    int k1 = mResizeDrawableHeight.getIntrinsicHeight() / 2;
                    int l1 = mResizeDrawableHeight.getIntrinsicWidth() / 2;
                    int i2 = mDrawRect.left + (mDrawRect.right - mDrawRect.left) / 2;
                    int j2 = mDrawRect.top + (mDrawRect.bottom - mDrawRect.top) / 2;
                    mResizeDrawableWidth.setBounds(i - i1, j2 - j1, i + i1, j2 + j1);
                    mResizeDrawableWidth.draw(canvas);
                    mResizeDrawableWidth.setBounds(j - i1, j2 - j1, j + i1, j2 + j1);
                    mResizeDrawableWidth.draw(canvas);
                    mResizeDrawableHeight.setBounds(i2 - l1, k - k1, i2 + l1, k + k1);
                    mResizeDrawableHeight.draw(canvas);
                    mResizeDrawableHeight.setBounds(i2 - l1, l - k1, i2 + l1, l + k1);
                    mResizeDrawableHeight.draw(canvas);
                    return;
                }
        }
    }

    public Rect getCropRect()
    {
        return new Rect((int)mCropRect.left, (int)mCropRect.top, (int)mCropRect.right, (int)mCropRect.bottom);
    }

    public int getHit(float f, float f1)
    {
        Rect rect;
        int i;
        rect = computeLayout();
        i = 1;
        if(!mCircle) goto _L2; else goto _L1
_L1:
        float f2;
        float f3;
        int j;
        int k;
        f2 = f - (float)rect.centerX();
        f3 = f1 - (float)rect.centerY();
        j = (int)Math.sqrt(f2 * f2 + f3 * f3);
        k = mDrawRect.width() / 2;
        if((float)Math.abs(j - k) > 20F) goto _L4; else goto _L3
_L3:
        if(Math.abs(f3) <= Math.abs(f2)) goto _L6; else goto _L5
_L5:
        if(f3 >= 0.0F) goto _L8; else goto _L7
_L7:
        i = 8;
_L10:
        return i;
_L8:
        return 16;
_L6:
        return f2 >= 0.0F ? 4 : 2;
_L4:
        return j >= k ? 1 : 32;
_L2:
        boolean flag;
        boolean flag1;
        if(f1 >= (float)rect.top - 20F && f1 < 20F + (float)rect.bottom)
            flag = true;
        else
            flag = false;
        if(f >= (float)rect.left - 20F && f < 20F + (float)rect.right)
            flag1 = true;
        else
            flag1 = false;
        if(Math.abs((float)rect.left - f) < 20F && flag)
            i |= 2;
        if(Math.abs((float)rect.right - f) < 20F && flag)
            i |= 4;
        if(Math.abs((float)rect.top - f1) < 20F && flag1)
            i |= 8;
        if(Math.abs((float)rect.bottom - f1) < 20F && flag1)
            i |= 0x10;
        if(i == 1 && rect.contains((int)f, (int)f1))
            return 32;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public ModifyMode getMode()
    {
        return mMode;
    }

    void growBy(float f, float f1)
    {
        float f2 = 25F;
        RectF rectf;
        if(mMaintainAspectRatio)
            if(f != 0.0F)
                f1 = f / mInitialAspectRatio;
            else
            if(f1 != 0.0F)
                f = f1 * mInitialAspectRatio;
        rectf = new RectF(mCropRect);
        if(f > 0.0F && rectf.width() + 2.0F * f > mImageRect.width())
        {
            f = (mImageRect.width() - rectf.width()) / 2.0F;
            if(mMaintainAspectRatio)
                f1 = f / mInitialAspectRatio;
        }
        if(f1 > 0.0F && rectf.height() + 2.0F * f1 > mImageRect.height())
        {
            f1 = (mImageRect.height() - rectf.height()) / 2.0F;
            if(mMaintainAspectRatio)
                f = f1 * mInitialAspectRatio;
        }
        rectf.inset(-f, -f1);
        if(rectf.width() < f2)
            rectf.inset(-(f2 - rectf.width()) / 2.0F, 0.0F);
        if(mMaintainAspectRatio)
            f2 /= mInitialAspectRatio;
        if(rectf.height() < f2)
            rectf.inset(0.0F, -(f2 - rectf.height()) / 2.0F);
        if(rectf.left < mImageRect.left)
            rectf.offset(mImageRect.left - rectf.left, 0.0F);
        else
        if(rectf.right > mImageRect.right)
            rectf.offset(-(rectf.right - mImageRect.right), 0.0F);
        if(rectf.top < mImageRect.top)
            rectf.offset(0.0F, mImageRect.top - rectf.top);
        else
        if(rectf.bottom > mImageRect.bottom)
            rectf.offset(0.0F, -(rectf.bottom - mImageRect.bottom));
        mCropRect.set(rectf);
        mDrawRect = computeLayout();
        mContext.invalidate();
    }

    void handleMotion(int i, float f, float f1)
    {
        int j = -1;
        Rect rect = computeLayout();
        if(i == 1)
            return;
        if(i == 32)
        {
            moveBy(f * (mCropRect.width() / (float)rect.width()), f1 * (mCropRect.height() / (float)rect.height()));
            return;
        }
        if((i & 6) == 0)
            f = 0.0F;
        if((i & 0x18) == 0)
            f1 = 0.0F;
        float f2 = f * (mCropRect.width() / (float)rect.width());
        float f3 = f1 * (mCropRect.height() / (float)rect.height());
        int k;
        float f4;
        if((i & 2) != 0)
            k = j;
        else
            k = 1;
        f4 = f2 * (float)k;
        if((i & 8) == 0)
            j = 1;
        growBy(f4, f3 * (float)j);
    }

    public boolean hasFocus()
    {
        return mIsFocused;
    }

    public void invalidate()
    {
        mDrawRect = computeLayout();
    }

    void moveBy(float f, float f1)
    {
        Rect rect = new Rect(mDrawRect);
        mCropRect.offset(f, f1);
        mCropRect.offset(Math.max(0.0F, mImageRect.left - mCropRect.left), Math.max(0.0F, mImageRect.top - mCropRect.top));
        mCropRect.offset(Math.min(0.0F, mImageRect.right - mCropRect.right), Math.min(0.0F, mImageRect.bottom - mCropRect.bottom));
        mDrawRect = computeLayout();
        rect.union(mDrawRect);
        rect.inset(-10, -10);
        mContext.invalidate(rect);
    }

    public void setFocus(boolean flag)
    {
        mIsFocused = flag;
    }

    public void setHidden(boolean flag)
    {
        mHidden = flag;
    }

    public void setMode(ModifyMode modifymode)
    {
        if(modifymode != mMode)
        {
            mMode = modifymode;
            mContext.invalidate();
        }
    }

    public void setup(Matrix matrix, Rect rect, RectF rectf, boolean flag, boolean flag1)
    {
        if(flag)
            flag1 = true;
        mMatrix = new Matrix(matrix);
        mCropRect = rectf;
        mImageRect = new RectF(rect);
        mMaintainAspectRatio = flag1;
        mCircle = flag;
        mInitialAspectRatio = mCropRect.width() / mCropRect.height();
        mDrawRect = computeLayout();
        mFocusPaint.setARGB(125, 50, 50, 50);
        mNoFocusPaint.setARGB(125, 50, 50, 50);
        mOutlinePaint.setStrokeWidth(3F);
        mOutlinePaint.setStyle(android.graphics.Paint.Style.STROKE);
        mOutlinePaint.setAntiAlias(true);
        mMode = ModifyMode.None;
        init();
    }

    public static final int GROW_BOTTOM_EDGE = 16;
    public static final int GROW_LEFT_EDGE = 2;
    public static final int GROW_NONE = 1;
    public static final int GROW_RIGHT_EDGE = 4;
    public static final int GROW_TOP_EDGE = 8;
    public static final int MOVE = 32;
    private static final String TAG = "HighlightView";
    private boolean mCircle;
    View mContext;
    RectF mCropRect;
    Rect mDrawRect;
    private final Paint mFocusPaint = new Paint();
    boolean mHidden;
    private RectF mImageRect;
    private float mInitialAspectRatio;
    boolean mIsFocused;
    private boolean mMaintainAspectRatio;
    Matrix mMatrix;
    private ModifyMode mMode;
    private final Paint mNoFocusPaint = new Paint();
    private final Paint mOutlinePaint = new Paint();
    private Drawable mResizeDrawableDiagonal;
    private Drawable mResizeDrawableHeight;
    private Drawable mResizeDrawableWidth;
}
