// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.roadtrippers.adapters.base.InflaterAdapter;

// Referenced classes of package com.roadtrippers.widget:
//            TypefaceButton

public class ProgressTypefaceButton extends FrameLayout
{

    public ProgressTypefaceButton(Context context)
    {
        super(context);
        init(context, null, 0);
    }

    public ProgressTypefaceButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init(context, attributeset, 0);
    }

    public ProgressTypefaceButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        init(context, attributeset, i);
    }

    void init(Context context, AttributeSet attributeset, int i)
    {
        typefaceButton = new TypefaceButton(context, attributeset, i);
        text = typefaceButton.getText().toString();
        addView(typefaceButton);
        progressBar = new ProgressBar(context, attributeset, 0x1010079);
        InflaterAdapter.setVisibility(progressBar, 8);
        addView(progressBar, new android.widget.FrameLayout.LayoutParams(-2, -2, 17));
    }

    public void setLoading(boolean flag)
    {
        loading = flag;
        if(flag)
        {
            typefaceButton.setText("");
            InflaterAdapter.setVisibility(progressBar, 0);
        } else
        {
            typefaceButton.setText(text);
            InflaterAdapter.setVisibility(progressBar, 8);
        }
        invalidate();
    }

    public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        typefaceButton.setOnClickListener(onclicklistener);
    }

    public void setText(String s)
    {
        text = s;
        if(!loading)
            typefaceButton.setText(s);
    }

    public void setTextSize(int i, float f)
    {
        typefaceButton.setTextSize(i, f);
    }

    boolean loading;
    ProgressBar progressBar;
    String text;
    TypefaceButton typefaceButton;
}
