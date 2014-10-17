// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobeta.android.dslv;

import android.content.Context;
import android.widget.Checkable;

// Referenced classes of package com.mobeta.android.dslv:
//            DragSortItemView

public class DragSortItemViewCheckable extends DragSortItemView
    implements Checkable
{

    public DragSortItemViewCheckable(Context context)
    {
        super(context);
    }

    public boolean isChecked()
    {
        android.view.View view = getChildAt(0);
        boolean flag = view instanceof Checkable;
        boolean flag1 = false;
        if(flag)
            flag1 = ((Checkable)view).isChecked();
        return flag1;
    }

    public void setChecked(boolean flag)
    {
        android.view.View view = getChildAt(0);
        if(view instanceof Checkable)
            ((Checkable)view).setChecked(flag);
    }

    public void toggle()
    {
        android.view.View view = getChildAt(0);
        if(view instanceof Checkable)
            ((Checkable)view).toggle();
    }
}
