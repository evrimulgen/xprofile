// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.app.Activity;
import android.content.*;
import android.util.Log;

public class ef
    implements android.content.DialogInterface.OnClickListener
{

    public ef(Activity activity, Intent intent, int i)
    {
        nd = activity;
        mIntent = intent;
        Bq = i;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        try
        {
            if(mIntent != null)
                nd.startActivityForResult(mIntent, Bq);
            dialoginterface.dismiss();
            return;
        }
        catch(ActivityNotFoundException activitynotfoundexception)
        {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }

    private final int Bq;
    private final Intent mIntent;
    private final Activity nd;
}
