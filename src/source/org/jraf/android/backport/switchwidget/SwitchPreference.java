// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.jraf.android.backport.switchwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

// Referenced classes of package org.jraf.android.backport.switchwidget:
//            TwoStatePreference, a, Switch

public class SwitchPreference extends TwoStatePreference
{

    public SwitchPreference(Context context)
    {
        super(context);
        mListener = new a(this, (byte)0);
    }

    public SwitchPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.switchPreferenceStyle);
    }

    public SwitchPreference(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mListener = new a(this, (byte)0);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.SwitchPreference, R.attr.switchPreferenceStyle, 0);
        setSummaryOn(typedarray.getString(0));
        setSummaryOff(typedarray.getString(1));
        setSwitchTextOn(typedarray.getString(2));
        setSwitchTextOff(typedarray.getString(3));
        setDisableDependentsState(typedarray.getBoolean(4, false));
        typedarray.recycle();
    }

    public CharSequence getSwitchTextOff()
    {
        return mSwitchOff;
    }

    public CharSequence getSwitchTextOn()
    {
        return mSwitchOn;
    }

    protected void onBindView(View view)
    {
        super.onBindView(view);
        Switch switch1 = (Switch)view.findViewById(R.id.switchWidget);
        if(switch1 != null)
        {
            switch1.setChecked(mChecked);
            switch1.setTextOn(mSwitchOn);
            switch1.setTextOff(mSwitchOff);
            switch1.setOnCheckedChangeListener(mListener);
        }
        syncSummaryView(view);
    }

    public void setSwitchTextOff(int i)
    {
        setSwitchTextOff(((CharSequence) (getContext().getString(i))));
    }

    public void setSwitchTextOff(CharSequence charsequence)
    {
        mSwitchOff = charsequence;
        notifyChanged();
    }

    public void setSwitchTextOn(int i)
    {
        setSwitchTextOn(((CharSequence) (getContext().getString(i))));
    }

    public void setSwitchTextOn(CharSequence charsequence)
    {
        mSwitchOn = charsequence;
        notifyChanged();
    }

    private final a mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

}
