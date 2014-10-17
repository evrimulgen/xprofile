// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.jraf.android.backport.switchwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public abstract class TwoStatePreference extends Preference
{

    public TwoStatePreference(Context context)
    {
        this(context, null);
    }

    public TwoStatePreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public boolean getDisableDependentsState()
    {
        return mDisableDependentsState;
    }

    public CharSequence getSummaryOff()
    {
        return mSummaryOff;
    }

    public CharSequence getSummaryOn()
    {
        return mSummaryOn;
    }

    public boolean isChecked()
    {
        return mChecked;
    }

    protected void onClick()
    {
        super.onClick();
        boolean flag;
        if(!isChecked())
            flag = true;
        else
            flag = false;
        if(!callChangeListener(Boolean.valueOf(flag)))
        {
            return;
        } else
        {
            setChecked(flag);
            return;
        }
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return Boolean.valueOf(typedarray.getBoolean(i, false));
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable == null || !parcelable.getClass().equals(org/jraf/android/backport/switchwidget/TwoStatePreference$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            SavedState savedstate = (SavedState)parcelable;
            super.onRestoreInstanceState(savedstate.getSuperState());
            setChecked(savedstate.a);
            return;
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        Parcelable parcelable = super.onSaveInstanceState();
        if(isPersistent())
        {
            return parcelable;
        } else
        {
            SavedState savedstate = new SavedState(parcelable);
            savedstate.a = isChecked();
            return savedstate;
        }
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
        boolean flag1;
        if(flag)
            flag1 = getPersistedBoolean(mChecked);
        else
            flag1 = ((Boolean)obj).booleanValue();
        setChecked(flag1);
    }

    public void setChecked(boolean flag)
    {
        if(mChecked != flag)
        {
            mChecked = flag;
            persistBoolean(flag);
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void setDisableDependentsState(boolean flag)
    {
        mDisableDependentsState = flag;
    }

    public void setSummaryOff(int i)
    {
        setSummaryOff(((CharSequence) (getContext().getString(i))));
    }

    public void setSummaryOff(CharSequence charsequence)
    {
        mSummaryOff = charsequence;
        if(!isChecked())
            notifyChanged();
    }

    public void setSummaryOn(int i)
    {
        setSummaryOn(((CharSequence) (getContext().getString(i))));
    }

    public void setSummaryOn(CharSequence charsequence)
    {
        mSummaryOn = charsequence;
        if(isChecked())
            notifyChanged();
    }

    public boolean shouldDisableDependents()
    {
label0:
        {
            boolean flag;
            boolean flag1;
            if(mDisableDependentsState)
                flag = mChecked;
            else
            if(!mChecked)
                flag = true;
            else
                flag = false;
            if(!flag)
            {
                boolean flag2 = super.shouldDisableDependents();
                flag1 = false;
                if(!flag2)
                    break label0;
            }
            flag1 = true;
        }
        return flag1;
    }

    void syncSummaryView(View view)
    {
        TextView textview = (TextView)view.findViewById(0x1020010);
        if(textview == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        boolean flag1;
        flag = true;
        CharSequence charsequence;
        if(mChecked && mSummaryOn != null)
        {
            textview.setText(mSummaryOn);
            flag = false;
        } else
        if(!mChecked && mSummaryOff != null)
        {
            textview.setText(mSummaryOff);
            flag = false;
        }
        if(!flag) goto _L4; else goto _L3
_L3:
        charsequence = getSummary();
        if(charsequence == null) goto _L4; else goto _L5
_L5:
        textview.setText(charsequence);
        flag1 = false;
_L7:
        int i = 0;
        if(flag1)
            i = 8;
        if(i != textview.getVisibility())
            textview.setVisibility(i);
_L2:
        return;
_L4:
        flag1 = flag;
        if(true) goto _L7; else goto _L6
_L6:
    }

    boolean mChecked;
    private boolean mDisableDependentsState;
    private CharSequence mSummaryOff;
    private CharSequence mSummaryOn;

    private class SavedState extends android.preference.Preference.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            int j;
            if(a)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
        }

        public static final android.os.Parcelable.Creator CREATOR = new b();
        boolean a;


        public SavedState(Parcel parcel)
        {
            boolean flag = true;
            super(parcel);
            if(parcel.readInt() != flag)
                flag = false;
            a = flag;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

}
