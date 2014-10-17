// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types, Log

class AppNameMacro extends FunctionCallImplementation
{

    public AppNameMacro(Context context)
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
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value;
        try
        {
            PackageManager packagemanager = mContext.getPackageManager();
            value = Types.objectToValue(packagemanager.getApplicationLabel(packagemanager.getApplicationInfo(mContext.getPackageName(), 0)).toString());
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.e("App name is not found.", namenotfoundexception);
            return Types.getDefaultValue();
        }
        return value;
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ID;
    private final Context mContext;

    static 
    {
        ID = FunctionType.APP_NAME.toString();
    }
}
