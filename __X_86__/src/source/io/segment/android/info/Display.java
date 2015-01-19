// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import io.segment.android.models.EasyJSONObject;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.info:
//            Info

public class Display
    implements Info
{

    public Display()
    {
    }

    public volatile Object get(Context context)
    {
        return get(context);
    }

    public JSONObject get(Context context)
    {
        EasyJSONObject easyjsonobject = new EasyJSONObject();
        WindowManager windowmanager = (WindowManager)context.getSystemService("window");
        if(windowmanager != null)
        {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            windowmanager.getDefaultDisplay().getMetrics(displaymetrics);
            easyjsonobject.put("height", displaymetrics.heightPixels);
            easyjsonobject.put("width", displaymetrics.widthPixels);
            easyjsonobject.put("density", displaymetrics.density);
        }
        return easyjsonobject;
    }

    public String getKey()
    {
        return "display";
    }
}
