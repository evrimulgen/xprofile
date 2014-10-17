// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import io.segment.android.models.EasyJSONObject;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.info:
//            Info

public class Build
    implements Info
{

    public Build()
    {
    }

    public volatile Object get(Context context)
    {
        return get(context);
    }

    public JSONObject get(Context context)
    {
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        try
        {
            PackageInfo packageinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            easyjsonobject.put("code", packageinfo.versionCode);
            easyjsonobject.put("name", packageinfo.versionName);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return easyjsonobject;
        }
        return easyjsonobject;
    }

    public String getKey()
    {
        return "build";
    }
}
