// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package se.emilsjolander.stickylistheaders:
//            WrapperView

class WrapperViewList extends ListView
{
    static interface LifeCycleListener
    {

        public abstract void onDispatchDrawOccurred(Canvas canvas);
    }


    public WrapperViewList(Context context)
    {
        super(context);
        mSelectorRect = new Rect();
        mClippingToPadding = true;
        try
        {
            Field field = android/widget/AbsListView.getDeclaredField("mSelectorRect");
            field.setAccessible(true);
            mSelectorRect = (Rect)field.get(this);
            if(android.os.Build.VERSION.SDK_INT >= 14)
            {
                mSelectorPositionField = android/widget/AbsListView.getDeclaredField("mSelectorPosition");
                mSelectorPositionField.setAccessible(true);
            }
            return;
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            nosuchfieldexception.printStackTrace();
            return;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
            return;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            illegalaccessexception.printStackTrace();
        }
    }

    private int getSelectorPosition()
    {
        if(mSelectorPositionField == null)
        {
            for(int j = 0; j < getChildCount(); j++)
                if(getChildAt(j).getBottom() == mSelectorRect.bottom)
                    return j + getFixedFirstVisibleItem();

            break MISSING_BLOCK_LABEL_68;
        }
        int i = mSelectorPositionField.getInt(this);
        return i;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        illegalargumentexception.printStackTrace();
_L2:
        return -1;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        illegalaccessexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void positionSelectorRect()
    {
        if(!mSelectorRect.isEmpty())
        {
            int i = getSelectorPosition();
            if(i >= 0)
            {
                View view = getChildAt(i - getFixedFirstVisibleItem());
                if(view instanceof WrapperView)
                {
                    WrapperView wrapperview = (WrapperView)view;
                    mSelectorRect.top = wrapperview.getTop() + wrapperview.mItemTop;
                }
            }
        }
    }

    public void addFooterView(View view)
    {
        super.addFooterView(view);
        if(mFooterViews == null)
            mFooterViews = new ArrayList();
        mFooterViews.add(view);
    }

    boolean containsFooterView(View view)
    {
        if(mFooterViews == null)
            return false;
        else
            return mFooterViews.contains(view);
    }

    protected void dispatchDraw(Canvas canvas)
    {
        positionSelectorRect();
        if(mTopClippingLength != 0)
        {
            canvas.save();
            Rect rect = canvas.getClipBounds();
            rect.top = mTopClippingLength;
            canvas.clipRect(rect);
            super.dispatchDraw(canvas);
            canvas.restore();
        } else
        {
            super.dispatchDraw(canvas);
        }
        mLifeCycleListener.onDispatchDrawOccurred(canvas);
    }

    int getFixedFirstVisibleItem()
    {
        int i = getFirstVisiblePosition();
        if(android.os.Build.VERSION.SDK_INT >= 11)
            return i;
        int j = 0;
        do
        {
label0:
            {
                if(j < getChildCount())
                {
                    if(getChildAt(j).getBottom() < 0)
                        break label0;
                    i += j;
                }
                if(!mClippingToPadding && getPaddingTop() > 0 && i > 0 && getChildAt(0).getTop() > 0)
                    i--;
                return i;
            }
            j++;
        } while(true);
    }

    public boolean performItemClick(View view, int i, long l)
    {
        if(view instanceof WrapperView)
            view = ((WrapperView)view).mItem;
        return super.performItemClick(view, i, l);
    }

    public boolean removeFooterView(View view)
    {
        if(super.removeFooterView(view))
        {
            mFooterViews.remove(view);
            return true;
        } else
        {
            return false;
        }
    }

    public void setClipToPadding(boolean flag)
    {
        mClippingToPadding = flag;
        super.setClipToPadding(flag);
    }

    void setLifeCycleListener(LifeCycleListener lifecyclelistener)
    {
        mLifeCycleListener = lifecyclelistener;
    }

    void setTopClippingLength(int i)
    {
        mTopClippingLength = i;
    }

    private boolean mClippingToPadding;
    private List mFooterViews;
    private LifeCycleListener mLifeCycleListener;
    private Field mSelectorPositionField;
    private Rect mSelectorRect;
    private int mTopClippingLength;
}
