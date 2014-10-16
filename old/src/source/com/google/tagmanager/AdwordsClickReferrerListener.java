// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import android.net.Uri;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            InstallReferrerUtil

class AdwordsClickReferrerListener
    implements DataLayer.Listener
{

    public AdwordsClickReferrerListener(Context context1)
    {
        context = context1;
    }

    public void changed(Map map)
    {
        Object obj = map.get("gtm.url");
        if(obj == null)
        {
            Object obj1 = map.get("gtm");
            if(obj1 != null && (obj1 instanceof Map))
                obj = ((Map)obj1).get("url");
        }
        String s;
        if(obj != null && (obj instanceof String))
            if((s = Uri.parse((String)obj).getQueryParameter("referrer")) != null)
            {
                InstallReferrerUtil.addClickReferrer(context, s);
                return;
            }
    }

    private final Context context;
}
