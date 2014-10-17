// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import io.segment.android.utils.AndroidUtils;
import java.lang.reflect.Field;
import java.util.UUID;

// Referenced classes of package io.segment.android.info:
//            Info

public class SessionId
    implements Info
{

    public SessionId()
    {
    }

    public volatile Object get(Context context)
    {
        return get(context);
    }

    public String get(Context context)
    {
        String s = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
        if(!TextUtils.isEmpty(s) && !s.equals("9774d56d682e549c"))
            return s;
        String s2;
        boolean flag;
        try
        {
            s2 = (String)android/os/Build.getField("SERIAL").get(null);
            flag = TextUtils.isEmpty(s2);
        }
        catch(Exception exception)
        {
            if(AndroidUtils.permissionGranted(context, "android.permission.READ_PHONE_STATE") && context.getPackageManager().hasSystemFeature("android.hardware.telephony"))
            {
                String s1 = ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
                if(!TextUtils.isEmpty(s1))
                    return s1;
            }
            return UUID.randomUUID().toString();
        }
label0:
        {
            if(!flag)
                return s2;
            break label0;
        }
    }

    public String getKey()
    {
        return "sessionId";
    }
}
