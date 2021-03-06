// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.common;

import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.gms.internal.er;

public class ErrorDialogFragment extends DialogFragment
{

    public ErrorDialogFragment()
    {
        mDialog = null;
        yK = null;
    }

    public static ErrorDialogFragment newInstance(Dialog dialog)
    {
        return newInstance(dialog, null);
    }

    public static ErrorDialogFragment newInstance(Dialog dialog, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        ErrorDialogFragment errordialogfragment = new ErrorDialogFragment();
        Dialog dialog1 = (Dialog)er.b(dialog, "Cannot display null dialog");
        dialog1.setOnCancelListener(null);
        dialog1.setOnDismissListener(null);
        errordialogfragment.mDialog = dialog1;
        if(oncancellistener != null)
            errordialogfragment.yK = oncancellistener;
        return errordialogfragment;
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        if(yK != null)
            yK.onCancel(dialoginterface);
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        return mDialog;
    }

    public void show(FragmentManager fragmentmanager, String s)
    {
        super.show(fragmentmanager, s);
    }

    private Dialog mDialog;
    private android.content.DialogInterface.OnCancelListener yK;
}
