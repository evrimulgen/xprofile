// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.cache;

import io.segment.android.models.EasyJSONObject;
import io.segment.android.utils.IThreadedLayer;

public interface ISettingsLayer
    extends IThreadedLayer
{
    public static interface SettingsCallback
    {

        public abstract void onSettingsLoaded(boolean flag, EasyJSONObject easyjsonobject);
    }


    public abstract void fetch(SettingsCallback settingscallback);
}
