// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.TextView;

// Referenced classes of package com.google.maps.android.ui:
//            RotationLayout

public class IconGenerator
{

    public IconGenerator(Context context)
    {
        mAnchorU = 0.5F;
        mAnchorV = 1.0F;
        mContext = context;
    }

    private void ensureViewsSetUp()
    {
        if(mContainer == null)
        {
            mContainer = (ViewGroup)LayoutInflater.from(mContext).inflate(com.google.maps.android.R.layout.text_bubble, null);
            mRotationLayout = (RotationLayout)mContainer.getChildAt(0);
            TextView textview = (TextView)mRotationLayout.findViewById(com.google.maps.android.R.id.text);
            mTextView = textview;
            mContentView = textview;
        }
    }

    private static int getBackground(int i)
    {
        switch(i)
        {
        default:
            return com.google.maps.android.R.drawable.bubble_white;

        case 3: // '\003'
            return com.google.maps.android.R.drawable.bubble_red;

        case 4: // '\004'
            return com.google.maps.android.R.drawable.bubble_blue;

        case 5: // '\005'
            return com.google.maps.android.R.drawable.bubble_green;

        case 6: // '\006'
            return com.google.maps.android.R.drawable.bubble_purple;

        case 7: // '\007'
            return com.google.maps.android.R.drawable.bubble_orange;
        }
    }

    private ViewGroup getContainer()
    {
        ensureViewsSetUp();
        return mContainer;
    }

    private static int getTextStyle(int i)
    {
        switch(i)
        {
        default:
            return com.google.maps.android.R.style.Bubble_TextAppearance_Dark;

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
            return com.google.maps.android.R.style.Bubble_TextAppearance_Light;
        }
    }

    private float rotateAnchor(float f, float f1)
    {
        switch(mRotation)
        {
        default:
            throw new IllegalStateException();

        case 1: // '\001'
            f = 1.0F - f1;
            // fall through

        case 0: // '\0'
            return f;

        case 2: // '\002'
            return 1.0F - f;

        case 3: // '\003'
            return f1;
        }
    }

    public float getAnchorU()
    {
        return rotateAnchor(mAnchorU, mAnchorV);
    }

    public float getAnchorV()
    {
        return rotateAnchor(mAnchorV, mAnchorU);
    }

    public Bitmap makeIcon()
    {
        ViewGroup viewgroup = getContainer();
        int i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        viewgroup.measure(i, i);
        int j = viewgroup.getMeasuredWidth();
        int k = viewgroup.getMeasuredHeight();
        viewgroup.layout(0, 0, j, k);
        if(mRotation == 1 || mRotation == 3)
        {
            k = viewgroup.getMeasuredWidth();
            j = viewgroup.getMeasuredHeight();
        }
        Bitmap bitmap = Bitmap.createBitmap(j, k, android.graphics.Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(0);
        Canvas canvas = new Canvas(bitmap);
        if(mRotation != 0)
            if(mRotation == 1)
            {
                canvas.translate(j, 0.0F);
                canvas.rotate(90F);
            } else
            if(mRotation == 2)
            {
                canvas.rotate(180F, j / 2, k / 2);
            } else
            {
                canvas.translate(0.0F, k);
                canvas.rotate(270F);
            }
        viewgroup.draw(canvas);
        return bitmap;
    }

    public Bitmap makeIcon(String s)
    {
        ensureViewsSetUp();
        if(mTextView != null)
            mTextView.setText(s);
        return makeIcon();
    }

    public void setBackground(Drawable drawable)
    {
        getContainer().setBackgroundDrawable(drawable);
        if(drawable != null)
        {
            Rect rect = new Rect();
            drawable.getPadding(rect);
            getContainer().setPadding(rect.left, rect.top, rect.right, rect.bottom);
            return;
        } else
        {
            getContainer().setPadding(0, 0, 0, 0);
            return;
        }
    }

    public void setContentPadding(int i, int j, int k, int l)
    {
        ensureViewsSetUp();
        mContentView.setPadding(i, j, k, l);
    }

    public void setContentRotation(int i)
    {
        ensureViewsSetUp();
        mRotationLayout.setViewRotation(i);
    }

    public void setContentView(View view)
    {
        ensureViewsSetUp();
        mRotationLayout.removeAllViews();
        mRotationLayout.addView(view);
        mContentView = view;
        View view1 = mRotationLayout.findViewById(com.google.maps.android.R.id.text);
        TextView textview;
        if(view1 instanceof TextView)
            textview = (TextView)view1;
        else
            textview = null;
        mTextView = textview;
    }

    public void setRotation(int i)
    {
        mRotation = ((i + 360) % 360) / 90;
    }

    public void setStyle(int i)
    {
        setBackground(mContext.getResources().getDrawable(getBackground(i)));
        setTextAppearance(mContext, getTextStyle(i));
    }

    public void setTextAppearance(int i)
    {
        setTextAppearance(mContext, i);
    }

    public void setTextAppearance(Context context, int i)
    {
        ensureViewsSetUp();
        if(mTextView != null)
            mTextView.setTextAppearance(context, i);
    }

    public static final int STYLE_BLUE = 4;
    public static final int STYLE_DEFAULT = 1;
    public static final int STYLE_GREEN = 5;
    public static final int STYLE_ORANGE = 7;
    public static final int STYLE_PURPLE = 6;
    public static final int STYLE_RED = 3;
    public static final int STYLE_WHITE = 2;
    private float mAnchorU;
    private float mAnchorV;
    private ViewGroup mContainer;
    private View mContentView;
    private final Context mContext;
    private int mRotation;
    private RotationLayout mRotationLayout;
    private TextView mTextView;
}
