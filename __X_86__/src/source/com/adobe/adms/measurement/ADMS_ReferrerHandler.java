// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.measurement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.HashMap;

// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_Measurement

public final class ADMS_ReferrerHandler
{

    public ADMS_ReferrerHandler()
    {
    }

    public void processIntent(Context context, Intent intent)
    {
        ADMS_Measurement.sharedInstance(context);
        if(intent.getAction().equals("com.android.vending.INSTALL_REFERRER")) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Bundle bundle;
        String s;
        String as[];
        int i;
        int j;
        String as2[];
        try
        {
            bundle = intent.getExtras();
        }
        catch(Exception exception)
        {
            return;
        }
        if(bundle == null)
            break MISSING_BLOCK_LABEL_36;
        bundle.containsKey(null);
        s = intent.getStringExtra("referrer");
        if(s != null && s.length() != 0)
        {
            HashMap hashmap = new HashMap();
            as = s.split("&");
            i = as.length;
            for(j = 0; j < i; j++)
            {
                as2 = as[j].split("=");
                if(as2.length == 2)
                    hashmap.put(as2[0], as2[1]);
            }

            String as1[] = REFERRER_FIELDS;
            int k = as1.length;
            for(int l = 0; l < k; l++)
            {
                String s1 = as1[l];
                if(hashmap.containsKey(s1) && hashmap.get(s1) != null)
                    ADMS_Measurement.editor.putString(s1, (String)hashmap.get(s1));
            }

            ADMS_Measurement.editor.commit();
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    static final String REFERRER_FIELDS[] = {
        "utm_source", "utm_medium", "utm_term", "utm_content", "utm_campaign"
    };

}
