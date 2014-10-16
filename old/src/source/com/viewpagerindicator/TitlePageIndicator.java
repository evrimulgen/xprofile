// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.*;
import android.util.AttributeSet;
import android.view.*;
import java.util.ArrayList;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator

public class TitlePageIndicator extends View
    implements PageIndicator
{
    public static final class IndicatorStyle extends Enum
    {

        public static IndicatorStyle fromValue(int i)
        {
            IndicatorStyle aindicatorstyle[] = values();
            int j = aindicatorstyle.length;
            for(int k = 0; k < j; k++)
            {
                IndicatorStyle indicatorstyle = aindicatorstyle[k];
                if(indicatorstyle.value == i)
                    return indicatorstyle;
            }

            return null;
        }

        public static IndicatorStyle valueOf(String s)
        {
            return (IndicatorStyle)Enum.valueOf(com/viewpagerindicator/TitlePageIndicator$IndicatorStyle, s);
        }

        public static IndicatorStyle[] values()
        {
            return (IndicatorStyle[])$VALUES.clone();
        }

        private static final IndicatorStyle $VALUES[];
        public static final IndicatorStyle None;
        public static final IndicatorStyle Triangle;
        public static final IndicatorStyle Underline;
        public final int value;

        static 
        {
            None = new IndicatorStyle("None", 0, 0);
            Triangle = new IndicatorStyle("Triangle", 1, 1);
            Underline = new IndicatorStyle("Underline", 2, 2);
            IndicatorStyle aindicatorstyle[] = new IndicatorStyle[3];
            aindicatorstyle[0] = None;
            aindicatorstyle[1] = Triangle;
            aindicatorstyle[2] = Underline;
            $VALUES = aindicatorstyle;
        }

        private IndicatorStyle(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class LinePosition extends Enum
    {

        public static LinePosition fromValue(int i)
        {
            LinePosition alineposition[] = values();
            int j = alineposition.length;
            for(int k = 0; k < j; k++)
            {
                LinePosition lineposition = alineposition[k];
                if(lineposition.value == i)
                    return lineposition;
            }

            return null;
        }

        public static LinePosition valueOf(String s)
        {
            return (LinePosition)Enum.valueOf(com/viewpagerindicator/TitlePageIndicator$LinePosition, s);
        }

        public static LinePosition[] values()
        {
            return (LinePosition[])$VALUES.clone();
        }

        private static final LinePosition $VALUES[];
        public static final LinePosition Bottom;
        public static final LinePosition Top;
        public final int value;

        static 
        {
            Bottom = new LinePosition("Bottom", 0, 0);
            Top = new LinePosition("Top", 1, 1);
            LinePosition alineposition[] = new LinePosition[2];
            alineposition[0] = Bottom;
            alineposition[1] = Top;
            $VALUES = alineposition;
        }

        private LinePosition(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static interface OnCenterItemClickListener
    {

        public abstract void onCenterItemClick(int i);
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(currentPage);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        int currentPage;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            currentPage = parcel.readInt();
        }


        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public TitlePageIndicator(Context context)
    {
        this(context, null);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.vpiTitlePageIndicatorStyle);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mCurrentPage = -1;
        mPaintText = new Paint();
        mPath = new Path();
        mBounds = new Rect();
        mPaintFooterLine = new Paint();
        mPaintFooterIndicator = new Paint();
        mLastMotionX = -1F;
        mActivePointerId = -1;
        if(isInEditMode())
            return;
        Resources resources = getResources();
        int j = resources.getColor(R.color.default_title_indicator_footer_color);
        float f = resources.getDimension(R.dimen.default_title_indicator_footer_line_height);
        int k = resources.getInteger(R.integer.default_title_indicator_footer_indicator_style);
        float f1 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_height);
        float f2 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_underline_padding);
        float f3 = resources.getDimension(R.dimen.default_title_indicator_footer_padding);
        int l = resources.getInteger(R.integer.default_title_indicator_line_position);
        int i1 = resources.getColor(R.color.default_title_indicator_selected_color);
        boolean flag = resources.getBoolean(R.bool.default_title_indicator_selected_bold);
        int j1 = resources.getColor(R.color.default_title_indicator_text_color);
        float f4 = resources.getDimension(R.dimen.default_title_indicator_text_size);
        float f5 = resources.getDimension(R.dimen.default_title_indicator_title_padding);
        float f6 = resources.getDimension(R.dimen.default_title_indicator_clip_padding);
        float f7 = resources.getDimension(R.dimen.default_title_indicator_top_padding);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.TitlePageIndicator, i, 0);
        mFooterLineHeight = typedarray.getDimension(6, f);
        mFooterIndicatorStyle = IndicatorStyle.fromValue(typedarray.getInteger(7, k));
        mFooterIndicatorHeight = typedarray.getDimension(8, f1);
        mFooterIndicatorUnderlinePadding = typedarray.getDimension(9, f2);
        mFooterPadding = typedarray.getDimension(10, f3);
        mLinePosition = LinePosition.fromValue(typedarray.getInteger(11, l));
        mTopPadding = typedarray.getDimension(14, f7);
        mTitlePadding = typedarray.getDimension(13, f5);
        mClipPadding = typedarray.getDimension(4, f6);
        mColorSelected = typedarray.getColor(3, i1);
        mColorText = typedarray.getColor(1, j1);
        mBoldText = typedarray.getBoolean(12, flag);
        float f8 = typedarray.getDimension(0, f4);
        int k1 = typedarray.getColor(5, j);
        mPaintText.setTextSize(f8);
        mPaintText.setAntiAlias(true);
        mPaintFooterLine.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        mPaintFooterLine.setStrokeWidth(mFooterLineHeight);
        mPaintFooterLine.setColor(k1);
        mPaintFooterIndicator.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        mPaintFooterIndicator.setColor(k1);
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(2);
        if(drawable != null)
            setBackgroundDrawable(drawable);
        typedarray.recycle();
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    private Rect calcBounds(int i, Paint paint)
    {
        Rect rect = new Rect();
        CharSequence charsequence = getTitle(i);
        rect.right = (int)paint.measureText(charsequence, 0, charsequence.length());
        rect.bottom = (int)(paint.descent() - paint.ascent());
        return rect;
    }

    private ArrayList calculateAllBounds(Paint paint)
    {
        ArrayList arraylist = new ArrayList();
        int i = mViewPager.getAdapter().getCount();
        int j = getWidth();
        int k = j / 2;
        for(int l = 0; l < i; l++)
        {
            Rect rect = calcBounds(l, paint);
            int i1 = rect.right - rect.left;
            int j1 = rect.bottom - rect.top;
            rect.left = (int)(((float)k - (float)i1 / 2.0F) + ((float)(l - mCurrentPage) - mPageOffset) * (float)j);
            rect.right = i1 + rect.left;
            rect.top = 0;
            rect.bottom = j1;
            arraylist.add(rect);
        }

        return arraylist;
    }

    private void clipViewOnTheLeft(Rect rect, float f, int i)
    {
        rect.left = (int)((float)i + mClipPadding);
        rect.right = (int)(f + mClipPadding);
    }

    private void clipViewOnTheRight(Rect rect, float f, int i)
    {
        rect.right = (int)((float)i - mClipPadding);
        rect.left = (int)((float)rect.right - f);
    }

    private CharSequence getTitle(int i)
    {
        Object obj = mViewPager.getAdapter().getPageTitle(i);
        if(obj == null)
            obj = "";
        return ((CharSequence) (obj));
    }

    public float getClipPadding()
    {
        return mClipPadding;
    }

    public int getFooterColor()
    {
        return mPaintFooterLine.getColor();
    }

    public float getFooterIndicatorHeight()
    {
        return mFooterIndicatorHeight;
    }

    public float getFooterIndicatorPadding()
    {
        return mFooterPadding;
    }

    public IndicatorStyle getFooterIndicatorStyle()
    {
        return mFooterIndicatorStyle;
    }

    public float getFooterLineHeight()
    {
        return mFooterLineHeight;
    }

    public LinePosition getLinePosition()
    {
        return mLinePosition;
    }

    public int getSelectedColor()
    {
        return mColorSelected;
    }

    public int getTextColor()
    {
        return mColorText;
    }

    public float getTextSize()
    {
        return mPaintText.getTextSize();
    }

    public float getTitlePadding()
    {
        return mTitlePadding;
    }

    public float getTopPadding()
    {
        return mTopPadding;
    }

    public Typeface getTypeface()
    {
        return mPaintText.getTypeface();
    }

    public boolean isSelectedBold()
    {
        return mBoldText;
    }

    public void notifyDataSetChanged()
    {
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i;
        if(mViewPager != null)
            if((i = mViewPager.getAdapter().getCount()) != 0)
            {
                if(mCurrentPage == -1 && mViewPager != null)
                    mCurrentPage = mViewPager.getCurrentItem();
                ArrayList arraylist = calculateAllBounds(mPaintText);
                int j = arraylist.size();
                if(mCurrentPage >= j)
                {
                    setCurrentItem(j - 1);
                    return;
                }
                int k = i - 1;
                float f = (float)getWidth() / 2.0F;
                int l = getLeft();
                float f1 = (float)l + mClipPadding;
                int i1 = getWidth();
                int j1 = getHeight();
                int k1 = l + i1;
                float f2 = (float)k1 - mClipPadding;
                int l1 = mCurrentPage;
                float f3;
                boolean flag;
                boolean flag1;
                float f4;
                Rect rect;
                float f5;
                if((double)mPageOffset <= 0.5D)
                {
                    f3 = mPageOffset;
                } else
                {
                    l1++;
                    f3 = 1.0F - mPageOffset;
                }
                if(f3 <= 0.25F)
                    flag = true;
                else
                    flag = false;
                if(f3 <= 0.05F)
                    flag1 = true;
                else
                    flag1 = false;
                f4 = (0.25F - f3) / 0.25F;
                rect = (Rect)arraylist.get(mCurrentPage);
                f5 = rect.right - rect.left;
                if((float)rect.left < f1)
                    clipViewOnTheLeft(rect, f5, l);
                if((float)rect.right > f2)
                    clipViewOnTheRight(rect, f5, k1);
                if(mCurrentPage > 0)
                {
                    for(int k3 = -1 + mCurrentPage; k3 >= 0; k3--)
                    {
                        Rect rect6 = (Rect)arraylist.get(k3);
                        if((float)rect6.left >= f1)
                            continue;
                        int l3 = rect6.right - rect6.left;
                        clipViewOnTheLeft(rect6, l3, l);
                        Rect rect7 = (Rect)arraylist.get(k3 + 1);
                        if((float)rect6.right + mTitlePadding > (float)rect7.left)
                        {
                            rect6.left = (int)((float)(rect7.left - l3) - mTitlePadding);
                            rect6.right = l3 + rect6.left;
                        }
                    }

                }
                if(mCurrentPage < k)
                {
                    for(int i3 = 1 + mCurrentPage; i3 < i; i3++)
                    {
                        Rect rect4 = (Rect)arraylist.get(i3);
                        if((float)rect4.right <= f2)
                            continue;
                        int j3 = rect4.right - rect4.left;
                        clipViewOnTheRight(rect4, j3, k1);
                        Rect rect5 = (Rect)arraylist.get(i3 - 1);
                        if((float)rect4.left - mTitlePadding < (float)rect5.right)
                        {
                            rect4.left = (int)((float)rect5.right + mTitlePadding);
                            rect4.right = j3 + rect4.left;
                        }
                    }

                }
                int i2 = mColorText >>> 24;
                int j2 = 0;
                while(j2 < i) 
                {
                    Rect rect2 = (Rect)arraylist.get(j2);
                    if((rect2.left <= l || rect2.left >= k1) && (rect2.right <= l || rect2.right >= k1))
                        continue;
                    boolean flag2;
                    CharSequence charsequence;
                    Paint paint;
                    boolean flag3;
                    int k2;
                    if(j2 == l1)
                        flag2 = true;
                    else
                        flag2 = false;
                    charsequence = getTitle(j2);
                    paint = mPaintText;
                    if(flag2 && flag1 && mBoldText)
                        flag3 = true;
                    else
                        flag3 = false;
                    paint.setFakeBoldText(flag3);
                    mPaintText.setColor(mColorText);
                    if(flag2 && flag)
                        mPaintText.setAlpha(i2 - (int)(f4 * (float)i2));
                    k2 = j - 1;
                    if(j2 < k2)
                    {
                        Rect rect3 = (Rect)arraylist.get(j2 + 1);
                        if((float)rect2.right + mTitlePadding > (float)rect3.left)
                        {
                            int l2 = rect2.right - rect2.left;
                            rect2.left = (int)((float)(rect3.left - l2) - mTitlePadding);
                            rect2.right = l2 + rect2.left;
                        }
                    }
                    canvas.drawText(charsequence, 0, charsequence.length(), rect2.left, (float)rect2.bottom + mTopPadding, mPaintText);
                    if(flag2 && flag)
                    {
                        mPaintText.setColor(mColorSelected);
                        mPaintText.setAlpha((int)(f4 * (float)(mColorSelected >>> 24)));
                        canvas.drawText(charsequence, 0, charsequence.length(), rect2.left, (float)rect2.bottom + mTopPadding, mPaintText);
                    }
                    j2++;
                }
                float f6 = mFooterLineHeight;
                float f7 = mFooterIndicatorHeight;
                if(mLinePosition == LinePosition.Top)
                {
                    j1 = 0;
                    f6 = -f6;
                    f7 = -f7;
                }
                mPath.reset();
                mPath.moveTo(0.0F, (float)j1 - f6 / 2.0F);
                mPath.lineTo(i1, (float)j1 - f6 / 2.0F);
                mPath.close();
                canvas.drawPath(mPath, mPaintFooterLine);
                float f8 = (float)j1 - f6;
                static class _cls1
                {

                    static final int $SwitchMap$com$viewpagerindicator$TitlePageIndicator$IndicatorStyle[];

                    static 
                    {
                        $SwitchMap$com$viewpagerindicator$TitlePageIndicator$IndicatorStyle = new int[IndicatorStyle.values().length];
                        try
                        {
                            $SwitchMap$com$viewpagerindicator$TitlePageIndicator$IndicatorStyle[IndicatorStyle.Triangle.ordinal()] = 1;
                        }
                        catch(NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$com$viewpagerindicator$TitlePageIndicator$IndicatorStyle[IndicatorStyle.Underline.ordinal()] = 2;
                        }
                        catch(NoSuchFieldError nosuchfielderror1)
                        {
                            return;
                        }
                    }
                }

                switch(_cls1..SwitchMap.com.viewpagerindicator.TitlePageIndicator.IndicatorStyle[mFooterIndicatorStyle.ordinal()])
                {
                default:
                    return;

                case 1: // '\001'
                    mPath.reset();
                    mPath.moveTo(f, f8 - f7);
                    mPath.lineTo(f + f7, f8);
                    mPath.lineTo(f - f7, f8);
                    mPath.close();
                    canvas.drawPath(mPath, mPaintFooterIndicator);
                    return;

                case 2: // '\002'
                    break;
                }
                if(flag && l1 < j)
                {
                    Rect rect1 = (Rect)arraylist.get(l1);
                    float f9 = (float)rect1.right + mFooterIndicatorUnderlinePadding;
                    float f10 = (float)rect1.left - mFooterIndicatorUnderlinePadding;
                    float f11 = f8 - f7;
                    mPath.reset();
                    mPath.moveTo(f10, f8);
                    mPath.lineTo(f9, f8);
                    mPath.lineTo(f9, f11);
                    mPath.lineTo(f10, f11);
                    mPath.close();
                    mPaintFooterIndicator.setAlpha((int)(255F * f4));
                    canvas.drawPath(mPath, mPaintFooterIndicator);
                    mPaintFooterIndicator.setAlpha(255);
                    return;
                }
            }
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(i);
        if(android.view.View.MeasureSpec.getMode(j) != 0x40000000) goto _L2; else goto _L1
_L1:
        float f = android.view.View.MeasureSpec.getSize(j);
_L4:
        setMeasuredDimension(k, (int)f);
        return;
_L2:
        mBounds.setEmpty();
        mBounds.bottom = (int)(mPaintText.descent() - mPaintText.ascent());
        f = (float)(mBounds.bottom - mBounds.top) + mFooterLineHeight + mFooterPadding + mTopPadding;
        if(mFooterIndicatorStyle != IndicatorStyle.None)
            f += mFooterIndicatorHeight;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onPageScrollStateChanged(int i)
    {
        mScrollState = i;
        if(mListener != null)
            mListener.onPageScrollStateChanged(i);
    }

    public void onPageScrolled(int i, float f, int j)
    {
        mCurrentPage = i;
        mPageOffset = f;
        invalidate();
        if(mListener != null)
            mListener.onPageScrolled(i, f, j);
    }

    public void onPageSelected(int i)
    {
        if(mScrollState == 0)
        {
            mCurrentPage = i;
            invalidate();
        }
        if(mListener != null)
            mListener.onPageSelected(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        mCurrentPage = savedstate.currentPage;
        requestLayout();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.currentPage = mCurrentPage;
        return savedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        if(super.onTouchEvent(motionevent))
            return true;
        if(mViewPager == null || mViewPager.getAdapter().getCount() == 0)
            return false;
        i = 0xff & motionevent.getAction();
        i;
        JVM INSTR tableswitch 0 6: default 84
    //                   0 86
    //                   1 200
    //                   2 106
    //                   3 200
    //                   4 84
    //                   5 390
    //                   6 419;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L5 _L6
_L1:
        return true;
_L2:
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        mLastMotionX = motionevent.getX();
        continue; /* Loop/switch isn't completed */
_L4:
        float f5 = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        float f6 = f5 - mLastMotionX;
        if(!mIsDragging && Math.abs(f6) > (float)mTouchSlop)
            mIsDragging = true;
        if(mIsDragging)
        {
            mLastMotionX = f5;
            if(mViewPager.isFakeDragging() || mViewPager.beginFakeDrag())
                mViewPager.fakeDragBy(f6);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!mIsDragging)
        {
            int i1 = mViewPager.getAdapter().getCount();
            int j1 = getWidth();
            float f = (float)j1 / 2.0F;
            float f1 = (float)j1 / 6F;
            float f2 = f - f1;
            float f3 = f + f1;
            float f4 = motionevent.getX();
            if(f4 < f2)
            {
                if(mCurrentPage > 0)
                {
                    if(i != 3)
                        mViewPager.setCurrentItem(-1 + mCurrentPage);
                    return true;
                }
            } else
            if(f4 > f3)
            {
                if(mCurrentPage < i1 - 1)
                {
                    if(i != 3)
                        mViewPager.setCurrentItem(1 + mCurrentPage);
                    return true;
                }
            } else
            if(mCenterItemClickListener != null && i != 3)
                mCenterItemClickListener.onCenterItemClick(mCurrentPage);
        }
        mIsDragging = false;
        mActivePointerId = -1;
        if(mViewPager.isFakeDragging())
            mViewPager.endFakeDrag();
        continue; /* Loop/switch isn't completed */
_L5:
        int l = MotionEventCompat.getActionIndex(motionevent);
        mLastMotionX = MotionEventCompat.getX(motionevent, l);
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, l);
        continue; /* Loop/switch isn't completed */
_L6:
        int j = MotionEventCompat.getActionIndex(motionevent);
        if(MotionEventCompat.getPointerId(motionevent, j) == mActivePointerId)
        {
            int k;
            if(j == 0)
                k = 1;
            else
                k = 0;
            mActivePointerId = MotionEventCompat.getPointerId(motionevent, k);
        }
        mLastMotionX = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void setClipPadding(float f)
    {
        mClipPadding = f;
        invalidate();
    }

    public void setCurrentItem(int i)
    {
        if(mViewPager == null)
        {
            throw new IllegalStateException("ViewPager has not been bound.");
        } else
        {
            mViewPager.setCurrentItem(i);
            mCurrentPage = i;
            invalidate();
            return;
        }
    }

    public void setFooterColor(int i)
    {
        mPaintFooterLine.setColor(i);
        mPaintFooterIndicator.setColor(i);
        invalidate();
    }

    public void setFooterIndicatorHeight(float f)
    {
        mFooterIndicatorHeight = f;
        invalidate();
    }

    public void setFooterIndicatorPadding(float f)
    {
        mFooterPadding = f;
        invalidate();
    }

    public void setFooterIndicatorStyle(IndicatorStyle indicatorstyle)
    {
        mFooterIndicatorStyle = indicatorstyle;
        invalidate();
    }

    public void setFooterLineHeight(float f)
    {
        mFooterLineHeight = f;
        mPaintFooterLine.setStrokeWidth(mFooterLineHeight);
        invalidate();
    }

    public void setLinePosition(LinePosition lineposition)
    {
        mLinePosition = lineposition;
        invalidate();
    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener oncenteritemclicklistener)
    {
        mCenterItemClickListener = oncenteritemclicklistener;
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        mListener = onpagechangelistener;
    }

    public void setSelectedBold(boolean flag)
    {
        mBoldText = flag;
        invalidate();
    }

    public void setSelectedColor(int i)
    {
        mColorSelected = i;
        invalidate();
    }

    public void setTextColor(int i)
    {
        mPaintText.setColor(i);
        mColorText = i;
        invalidate();
    }

    public void setTextSize(float f)
    {
        mPaintText.setTextSize(f);
        invalidate();
    }

    public void setTitlePadding(float f)
    {
        mTitlePadding = f;
        invalidate();
    }

    public void setTopPadding(float f)
    {
        mTopPadding = f;
        invalidate();
    }

    public void setTypeface(Typeface typeface)
    {
        mPaintText.setTypeface(typeface);
        invalidate();
    }

    public void setViewPager(ViewPager viewpager)
    {
        if(mViewPager == viewpager)
            return;
        if(mViewPager != null)
            mViewPager.setOnPageChangeListener(null);
        if(viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            mViewPager = viewpager;
            mViewPager.setOnPageChangeListener(this);
            invalidate();
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int i)
    {
        setViewPager(viewpager);
        setCurrentItem(i);
    }

    private static final float BOLD_FADE_PERCENTAGE = 0.05F;
    private static final String EMPTY_TITLE = "";
    private static final int INVALID_POINTER = -1;
    private static final float SELECTION_FADE_PERCENTAGE = 0.25F;
    private int mActivePointerId;
    private boolean mBoldText;
    private final Rect mBounds;
    private OnCenterItemClickListener mCenterItemClickListener;
    private float mClipPadding;
    private int mColorSelected;
    private int mColorText;
    private int mCurrentPage;
    private float mFooterIndicatorHeight;
    private IndicatorStyle mFooterIndicatorStyle;
    private float mFooterIndicatorUnderlinePadding;
    private float mFooterLineHeight;
    private float mFooterPadding;
    private boolean mIsDragging;
    private float mLastMotionX;
    private LinePosition mLinePosition;
    private android.support.v4.view.ViewPager.OnPageChangeListener mListener;
    private float mPageOffset;
    private final Paint mPaintFooterIndicator;
    private final Paint mPaintFooterLine;
    private final Paint mPaintText;
    private Path mPath;
    private int mScrollState;
    private float mTitlePadding;
    private float mTopPadding;
    private int mTouchSlop;
    private ViewPager mViewPager;
}
