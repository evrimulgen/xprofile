// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.analytics.containertag.common.FunctionType;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, Types, Log

class AppVersionMacro extends FunctionCallImplementation
{

    public AppVersionMacro(Context context)
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
            value = Types.objectToValue(Integer.valueOf(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode));
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.e((new StringBuilder()).append("Package name ").append(mContext.getPackageName()).append(" not found. ").append(namenotfoundexception.getMessage()).toString());
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
        ID = FunctionType.APP_VERSION.toString();
    }
}
