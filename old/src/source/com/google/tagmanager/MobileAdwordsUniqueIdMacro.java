// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types

class MobileAdwordsUniqueIdMacro extends FunctionCallImplementation
{

    public MobileAdwordsUniqueIdMacro(Context context)
    {
        super(ID, new String[0]);
        mContext = context;
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        String s = getAndroidId(mContext);
        if(s == null)
            return Types.getDefaultValue();
        else
            return Types.objectToValue(s);
    }

    protected String getAndroidId(Context context)
    {
        return android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ID;
    private final Context mContext;

    static 
    {
        ID = FunctionType.MOBILE_ADWORDS_UNIQUE_ID.toString();
    }
}
