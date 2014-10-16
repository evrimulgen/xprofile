// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.ctrlplusz.anytextview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

// Referenced classes of package com.ctrlplusz.anytextview:
//            Util

public class AnyTextView extends TextView
{

    public AnyTextView(Context context)
    {
        super(context);
    }

    public AnyTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        if(!isInEditMode())
            Util.setTypeface(attributeset, this);
    }

    public AnyTextView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        if(!isInEditMode())
            Util.setTypeface(attributeset, this);
    }
}
