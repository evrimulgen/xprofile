// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package eu.janmuller.android.simplecropimage;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package eu.janmuller.android.simplecropimage:
//            ImageViewTouchBase, HighlightView, RotateBitmap, CropImage

class CropImageView extends ImageViewTouchBase
{

    public CropImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mHighlightViews = new ArrayList();
        mMotionHighlightView = null;
        mContext = context;
    }

    private void centerBasedOnHighlightView(HighlightView highlightview)
    {
        Rect rect = highlightview.mDrawRect;
        float f = rect.width();
        float f1 = rect.height();
        float f2 = getWidth();
        float f3 = getHeight();
        float f4 = Math.max(1.0F, Math.min(0.6F * (f2 / f), 0.6F * (f3 / f1)) * getScale());
        if((double)(Math.abs(f4 - getScale()) / f4) > 0.10000000000000001D)
        {
            float af[] = new float[2];
            af[0] = highlightview.mCropRect.centerX();
            af[1] = highlightview.mCropRect.centerY();
            getImageMatrix().mapPoints(af);
            zoomTo(f4, af[0], af[1], 300F);
        }
        ensureVisible(highlightview);
    }

    private void ensureVisible(HighlightView highlightview)
    {
        Rect rect = highlightview.mDrawRect;
        int i = Math.max(0, mLeft - rect.left);
        int j = Math.min(0, mRight - rect.right);
        int k = Math.max(0, mTop - rect.top);
        int l = Math.min(0, mBottom - rect.bottom);
        int i1;
        int j1;
        if(i != 0)
            i1 = i;
        else
            i1 = j;
        if(k != 0)
            j1 = k;
        else
            j1 = l;
        if(i1 != 0 || j1 != 0)
            panBy(i1, j1);
    }

    private void recomputeFocus(MotionEvent motionevent)
    {
        for(int i = 0; i < mHighlightViews.size(); i++)
        {
            HighlightView highlightview1 = (HighlightView)mHighlightViews.get(i);
            highlightview1.setFocus(false);
            highlightview1.invalidate();
        }

        int j = 0;
        do
        {
label0:
            {
                if(j < mHighlightViews.size())
                {
                    HighlightView highlightview = (HighlightView)mHighlightViews.get(j);
                    if(highlightview.getHit(motionevent.getX(), motionevent.getY()) == 1)
                        break label0;
                    if(!highlightview.hasFocus())
                    {
                        highlightview.setFocus(true);
                        highlightview.invalidate();
                    }
                }
                invalidate();
                return;
            }
            j++;
        } while(true);
    }

    public void add(HighlightView highlightview)
    {
        mHighlightViews.add(highlightview);
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for(int i = 0; i < mHighlightViews.size(); i++)
            ((HighlightView)mHighlightViews.get(i)).draw(canvas);

    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(mBitmapDisplayed.getBitmap() != null)
        {
            Iterator iterator = mHighlightViews.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                HighlightView highlightview = (HighlightView)iterator.next();
                highlightview.mMatrix.set(getImageMatrix());
                highlightview.invalidate();
                if(highlightview.mIsFocused)
                    centerBasedOnHighlightView(highlightview);
            } while(true);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        CropImage cropimage;
        cropimage = (CropImage)mContext;
        if(cropimage.mSaving)
            return false;
        motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 48
    //                   0 78
    //                   1 213
    //                   2 364;
           goto _L1 _L2 _L3 _L4
_L1:
        motionevent.getAction();
        JVM INSTR tableswitch 1 2: default 76
    //                   1 442
    //                   2 451;
           goto _L5 _L6 _L7
_L5:
        return true;
_L2:
        if(!cropimage.mWaitingToPick) goto _L9; else goto _L8
_L8:
        recomputeFocus(motionevent);
          goto _L1
_L9:
        int k = 0;
_L13:
        if(k >= mHighlightViews.size()) goto _L1; else goto _L10
_L10:
        HighlightView highlightview1;
        int l;
        highlightview1 = (HighlightView)mHighlightViews.get(k);
        l = highlightview1.getHit(motionevent.getX(), motionevent.getY());
        if(l == 1) goto _L12; else goto _L11
_L11:
        mMotionEdge = l;
        mMotionHighlightView = highlightview1;
        mLastX = motionevent.getX();
        mLastY = motionevent.getY();
        HighlightView highlightview2 = mMotionHighlightView;
        HighlightView.ModifyMode modifymode;
        if(l == 32)
            modifymode = HighlightView.ModifyMode.Move;
        else
            modifymode = HighlightView.ModifyMode.Grow;
        highlightview2.setMode(modifymode);
          goto _L1
_L12:
        k++;
          goto _L13
_L3:
        if(cropimage.mWaitingToPick)
        {
            for(int i = 0; i < mHighlightViews.size(); i++)
            {
                HighlightView highlightview = (HighlightView)mHighlightViews.get(i);
                if(highlightview.hasFocus())
                {
                    cropimage.mCrop = highlightview;
                    int j = 0;
                    while(j < mHighlightViews.size()) 
                    {
                        if(j != i)
                            ((HighlightView)mHighlightViews.get(j)).setHidden(true);
                        j++;
                    }
                    centerBasedOnHighlightView(highlightview);
                    ((CropImage)mContext).mWaitingToPick = false;
                    return true;
                }
            }

        } else
        if(mMotionHighlightView != null)
        {
            centerBasedOnHighlightView(mMotionHighlightView);
            mMotionHighlightView.setMode(HighlightView.ModifyMode.None);
        }
        mMotionHighlightView = null;
          goto _L1
_L4:
        if(cropimage.mWaitingToPick)
            recomputeFocus(motionevent);
        else
        if(mMotionHighlightView != null)
        {
            mMotionHighlightView.handleMotion(mMotionEdge, motionevent.getX() - mLastX, motionevent.getY() - mLastY);
            mLastX = motionevent.getX();
            mLastY = motionevent.getY();
            ensureVisible(mMotionHighlightView);
        }
          goto _L1
_L6:
        center(true, true);
          goto _L5
_L7:
        if(getScale() == 1.0F)
            center(true, true);
          goto _L5
    }

    protected void postTranslate(float f, float f1)
    {
        super.postTranslate(f, f1);
        for(int i = 0; i < mHighlightViews.size(); i++)
        {
            HighlightView highlightview = (HighlightView)mHighlightViews.get(i);
            highlightview.mMatrix.postTranslate(f, f1);
            highlightview.invalidate();
        }

    }

    protected void zoomIn()
    {
        super.zoomIn();
        HighlightView highlightview;
        for(Iterator iterator = mHighlightViews.iterator(); iterator.hasNext(); highlightview.invalidate())
        {
            highlightview = (HighlightView)iterator.next();
            highlightview.mMatrix.set(getImageMatrix());
        }

    }

    protected void zoomOut()
    {
        super.zoomOut();
        HighlightView highlightview;
        for(Iterator iterator = mHighlightViews.iterator(); iterator.hasNext(); highlightview.invalidate())
        {
            highlightview = (HighlightView)iterator.next();
            highlightview.mMatrix.set(getImageMatrix());
        }

    }

    protected void zoomTo(float f, float f1, float f2)
    {
        super.zoomTo(f, f1, f2);
        HighlightView highlightview;
        for(Iterator iterator = mHighlightViews.iterator(); iterator.hasNext(); highlightview.invalidate())
        {
            highlightview = (HighlightView)iterator.next();
            highlightview.mMatrix.set(getImageMatrix());
        }

    }

    private Context mContext;
    ArrayList mHighlightViews;
    float mLastX;
    float mLastY;
    int mMotionEdge;
    HighlightView mMotionHighlightView;
}
