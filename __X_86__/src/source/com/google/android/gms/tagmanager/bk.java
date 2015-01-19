// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aj, di

class bk extends aj
{

    public bk(Context context)
    {
        super(ID, new String[0]);
        mContext = context;
    }

    protected String G(Context context)
    {
        return android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public boolean iy()
    {
        return true;
    }

    public com.google.android.gms.internal.d.a u(Map map)
    {
        String s = G(mContext);
        if(s == null)
            return di.ku();
        else
            return di.r(s);
    }

    private static final String ID;
    private final Context mContext;

    static 
    {
        ID = a.ao.toString();
    }
}
