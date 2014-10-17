// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.app.Activity;
import android.view.*;
import android.widget.TextView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;

public class RetryCrouton
{
    public static interface Listener
    {

        public abstract void onRetry();
    }


    public RetryCrouton(Activity activity, ViewGroup viewgroup, Listener listener1)
    {
        customView = LayoutInflater.from(activity).inflate(0x7f03005a, viewgroup, false);
        ButterKnife.inject(this, customView);
        crouton = Crouton.make(activity, customView, viewgroup);
        crouton.setConfiguration((new de.keyboardsurfer.android.widget.crouton.Configuration.Builder()).setDuration(-1).build());
        listener = listener1;
    }

    public View getCustomView()
    {
        return customView;
    }

    public void remove()
    {
        crouton.cancel();
    }

    public void retry()
    {
        listener.onRetry();
    }

    public RetryCrouton setErrorMessage(CharSequence charsequence)
    {
        errorMessage.setText(charsequence);
        return this;
    }

    public void show()
    {
        crouton.show();
    }

    private final Crouton crouton;
    private final View customView;
    TextView errorMessage;
    private final Listener listener;
}
