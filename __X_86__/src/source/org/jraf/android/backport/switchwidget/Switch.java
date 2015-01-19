// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.jraf.android.backport.switchwidget;

import android.content.Context;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.text.*;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.CompoundButton;

public class Switch extends CompoundButton
{

    public Switch(Context context)
    {
        this(context, null);
    }

    public Switch(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.switchStyle);
    }

    public Switch(Context context, AttributeSet attributeset, int i1)
    {
        TypedArray typedarray;
        int j1;
        super(context, attributeset, i1);
        l = VelocityTracker.obtain();
        z = new Rect();
        v = new TextPaint(1);
        Resources resources = getResources();
        v.density = resources.getDisplayMetrics().density;
        typedarray = context.obtainStyledAttributes(attributeset, R.styleable.Switch, i1, 0);
        a = typedarray.getDrawable(0);
        b = typedarray.getDrawable(1);
        f = typedarray.getText(2);
        g = typedarray.getText(3);
        c = typedarray.getDimensionPixelSize(4, 0);
        d = typedarray.getDimensionPixelSize(6, 0);
        e = typedarray.getDimensionPixelSize(7, 0);
        j1 = typedarray.getResourceId(5, 0);
        if(j1 == 0) goto _L2; else goto _L1
_L1:
        int i2;
        Typeface typeface;
        TypedArray typedarray1 = context.obtainStyledAttributes(j1, R.styleable.Android);
        ColorStateList colorstatelist = typedarray1.getColorStateList(3);
        ViewConfiguration viewconfiguration;
        int k1;
        int l1;
        int k2;
        TextPaint textpaint;
        int l2;
        boolean flag;
        TextPaint textpaint1;
        if(colorstatelist != null)
            w = colorstatelist;
        else
            w = getTextColors();
        k1 = typedarray1.getDimensionPixelSize(0, 0);
        if(k1 != 0 && (float)k1 != v.getTextSize())
        {
            v.setTextSize(k1);
            requestLayout();
        }
        l1 = typedarray1.getInt(1, -1);
        i2 = typedarray1.getInt(2, -1);
        typeface = null;
        l1;
        JVM INSTR tableswitch 1 3: default 280
    //                   1 436
    //                   2 444
    //                   3 452;
           goto _L3 _L4 _L5 _L6
_L3:
        break; /* Loop/switch isn't completed */
_L6:
        break MISSING_BLOCK_LABEL_452;
_L7:
        if(i2 > 0)
        {
            Typeface typeface1;
            int j2;
            float f1;
            if(typeface == null)
                typeface1 = Typeface.defaultFromStyle(i2);
            else
                typeface1 = Typeface.create(typeface, i2);
            setSwitchTypeface(typeface1);
            if(typeface1 != null)
                j2 = typeface1.getStyle();
            else
                j2 = 0;
            k2 = i2 & ~j2;
            textpaint = v;
            l2 = k2 & 1;
            flag = false;
            if(l2 != 0)
                flag = true;
            textpaint.setFakeBoldText(flag);
            textpaint1 = v;
            if((k2 & 2) != 0)
                f1 = -0.25F;
            else
                f1 = 0.0F;
            textpaint1.setTextSkewX(f1);
        } else
        {
            v.setFakeBoldText(false);
            v.setTextSkewX(0.0F);
            setSwitchTypeface(typeface);
        }
        typedarray1.recycle();
_L2:
        typedarray.recycle();
        viewconfiguration = ViewConfiguration.get(context);
        i = viewconfiguration.getScaledTouchSlop();
        m = viewconfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
        return;
_L4:
        typeface = Typeface.SANS_SERIF;
          goto _L7
_L5:
        typeface = Typeface.SERIF;
          goto _L7
        typeface = Typeface.MONOSPACE;
          goto _L7
    }

    private Layout a(CharSequence charsequence)
    {
        return new StaticLayout(charsequence, v, (int)Math.ceil(Layout.getDesiredWidth(charsequence, v)), android.text.Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
    }

    private boolean getTargetCheckedState()
    {
        return n >= (float)(getThumbScrollRange() / 2);
    }

    private int getThumbScrollRange()
    {
        if(b == null)
        {
            return 0;
        } else
        {
            b.getPadding(z);
            return o - q - z.left - z.right;
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        if(a != null)
            a.setState(ai);
        if(b != null)
            b.setState(ai);
        invalidate();
    }

    public int getCompoundPaddingRight()
    {
        int i1 = super.getCompoundPaddingRight() + o;
        if(!TextUtils.isEmpty(getText()))
            i1 += e;
        return i1;
    }

    public CharSequence getTextOff()
    {
        return g;
    }

    public CharSequence getTextOn()
    {
        return f;
    }

    protected int[] onCreateDrawableState(int i1)
    {
        int ai[] = super.onCreateDrawableState(i1 + 1);
        if(isChecked())
            mergeDrawableStates(ai, A);
        return ai;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i1 = r;
        int j1 = s;
        int k1 = t;
        int l1 = u;
        b.setBounds(i1, j1, k1, l1);
        b.draw(canvas);
        canvas.save();
        b.getPadding(z);
        int i2 = i1 + z.left;
        int j2 = j1 + z.top;
        int k2 = k1 - z.right;
        int l2 = l1 - z.bottom;
        canvas.clipRect(i2, j1, k2, l1);
        a.getPadding(z);
        int i3 = (int)(0.5F + n);
        int j3 = i3 + (i2 - z.left);
        int k3 = i2 + i3 + q + z.right;
        a.setBounds(j3, j1, k3, l1);
        a.draw(canvas);
        if(w != null)
            v.setColor(w.getColorForState(getDrawableState(), w.getDefaultColor()));
        v.drawableState = getDrawableState();
        Layout layout;
        if(getTargetCheckedState())
            layout = x;
        else
            layout = y;
        canvas.translate((j3 + k3) / 2 - layout.getWidth() / 2, (j2 + l2) / 2 - layout.getHeight() / 2);
        layout.draw(canvas);
        canvas.restore();
    }

    protected void onLayout(boolean flag, int i1, int j1, int k1, int l1)
    {
        int k2;
        int l2;
        super.onLayout(flag, i1, j1, k1, l1);
        float f1;
        int i2;
        int j2;
        if(isChecked())
            f1 = getThumbScrollRange();
        else
            f1 = 0.0F;
        n = f1;
        i2 = getWidth() - getPaddingRight();
        j2 = i2 - o;
        0x70 & getGravity();
        JVM INSTR lookupswitch 2: default 84
    //                   16: 130
    //                   80: 167;
           goto _L1 _L2 _L3
_L1:
        l2 = getPaddingTop();
        k2 = l2 + p;
_L5:
        r = j2;
        s = l2;
        u = k2;
        t = i2;
        return;
_L2:
        l2 = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2 - p / 2;
        k2 = l2 + p;
        continue; /* Loop/switch isn't completed */
_L3:
        k2 = getHeight() - getPaddingBottom();
        l2 = k2 - p;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onMeasure(int i1, int j1)
    {
        int i2;
label0:
        {
            if(x == null)
                x = a(f);
            if(y == null)
                y = a(g);
            b.getPadding(z);
            int k1 = Math.max(x.getWidth(), y.getWidth());
            int l1 = Math.max(d, k1 * 2 + 4 * c + z.left + z.right);
            i2 = b.getIntrinsicHeight();
            q = k1 + 2 * c;
            o = l1;
            p = i2;
            super.onMeasure(i1, j1);
            if(getMeasuredHeight() < i2)
            {
                if(android.os.Build.VERSION.SDK_INT >= 11)
                    break label0;
                setMeasuredDimension(getMeasuredWidth(), i2);
            }
            return;
        }
        setMeasuredDimension(getMeasuredWidthAndState(), i2);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        flag = true;
        l.addMovement(motionevent);
        int i1;
        if(android.os.Build.VERSION.SDK_INT < 8)
            i1 = motionevent.getAction();
        else
            i1 = motionevent.getActionMasked();
        i1;
        JVM INSTR tableswitch 0 3: default 56
    //                   0 72
    //                   1 442
    //                   2 268
    //                   3 442;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        flag = super.onTouchEvent(motionevent);
_L6:
        return flag;
_L2:
        float f6 = motionevent.getX();
        float f7 = motionevent.getY();
        if(isEnabled())
        {
            a.getPadding(z);
            int l1 = s - i;
            int i2 = (r + (int)(0.5F + n)) - i;
            int j2 = i2 + q + z.left + z.right + i;
            int k2 = u + i;
            int l2 = f6 != (float)i2;
            boolean flag2 = false;
            if(l2 > 0)
            {
                int i3 = f6 != (float)j2;
                flag2 = false;
                if(i3 < 0)
                {
                    int j3 = f7 != (float)l1;
                    flag2 = false;
                    if(j3 > 0)
                    {
                        int k3 = f7 != (float)k2;
                        flag2 = false;
                        if(k3 < 0)
                            flag2 = flag;
                    }
                }
            }
            if(flag2)
            {
                h = ((flag) ? 1 : 0);
                j = f6;
                k = f7;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        float f2;
        float f3;
        switch(h)
        {
        case 0: // '\0'
        default:
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            float f4 = motionevent.getX();
            float f5 = motionevent.getY();
            if(Math.abs(f4 - j) > (float)i || Math.abs(f5 - k) > (float)i)
            {
                h = 2;
                getParent().requestDisallowInterceptTouchEvent(flag);
                j = f4;
                k = f5;
                return flag;
            }
            continue; /* Loop/switch isn't completed */

        case 2: // '\002'
            f2 = motionevent.getX();
            f3 = Math.max(0.0F, Math.min((f2 - j) + n, getThumbScrollRange()));
            break;
        }
        if(f3 == n) goto _L6; else goto _L5
_L5:
        n = f3;
        j = f2;
        invalidate();
        return flag;
_L3:
        if(h == 2)
        {
            h = 0;
            int j1;
            MotionEvent motionevent1;
            if(motionevent.getAction() == flag && isEnabled())
                j1 = ((flag) ? 1 : 0);
            else
                j1 = 0;
            motionevent1 = MotionEvent.obtain(motionevent);
            motionevent1.setAction(3);
            super.onTouchEvent(motionevent1);
            motionevent1.recycle();
            if(j1 != 0)
            {
                l.computeCurrentVelocity(1000);
                float f1 = l.getXVelocity();
                boolean flag1;
                if(Math.abs(f1) > (float)m)
                {
                    int k1 = f1 != 0.0F;
                    flag1 = false;
                    if(k1 > 0)
                        flag1 = flag;
                } else
                {
                    flag1 = getTargetCheckedState();
                }
                setChecked(flag1);
                return flag;
            } else
            {
                setChecked(isChecked());
                return flag;
            }
        }
        h = 0;
        l.clear();
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void setChecked(boolean flag)
    {
        super.setChecked(flag);
        float f1;
        if(flag)
            f1 = getThumbScrollRange();
        else
            f1 = 0.0F;
        n = f1;
        invalidate();
    }

    public void setSwitchTypeface(Typeface typeface)
    {
        if(v.getTypeface() != typeface)
        {
            v.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setTextOff(CharSequence charsequence)
    {
        g = charsequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charsequence)
    {
        f = charsequence;
        requestLayout();
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == a || drawable == b;
    }

    private static final int A[] = {
        0x10100a0
    };
    private final Drawable a;
    private final Drawable b;
    private final int c;
    private final int d;
    private final int e;
    private CharSequence f;
    private CharSequence g;
    private int h;
    private final int i;
    private float j;
    private float k;
    private final VelocityTracker l;
    private final int m;
    private float n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private final TextPaint v;
    private ColorStateList w;
    private Layout x;
    private Layout y;
    private final Rect z;

}
