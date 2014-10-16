// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.quantcast.measurement.service:
//            QCLog

public class QCReferrerReceiver extends BroadcastReceiver
{

    public QCReferrerReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        if(bundle != null)
            referrer = bundle.getString("referrer");
        QCLog.i(TAG, (new StringBuilder()).append("Referrer is: ").append(referrer).toString());
        PackageManager packagemanager = context.getPackageManager();
        if(packagemanager == null)
            break MISSING_BLOCK_LABEL_222;
        ActivityInfo activityinfo = packagemanager.getReceiverInfo(new ComponentName(context, "com.quantcast.measurement.service.QCReferrerReceiver"), 128);
        if(activityinfo == null)
            break MISSING_BLOCK_LABEL_222;
        Bundle bundle1 = activityinfo.metaData;
        if(bundle1 == null)
            break MISSING_BLOCK_LABEL_222;
        Iterator iterator = bundle1.keySet().iterator();
_L1:
        String s;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_222;
        s = bundle1.getString((String)iterator.next());
        ((BroadcastReceiver)Class.forName(s).newInstance()).onReceive(context, intent);
        QCLog.i(TAG, (new StringBuilder()).append("PASS REFERRER TO...").append(s).toString());
          goto _L1
        Exception exception;
        exception;
        QCLog.e(TAG, (new StringBuilder()).append("Error when passing to referrer.  Class might not exist: ").append(s).toString(), exception);
          goto _L1
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        QCLog.e(TAG, "Could not find package Name for referrer", namenotfoundexception);
    }

    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCReferrerReceiver);
    protected static String referrer;

}
