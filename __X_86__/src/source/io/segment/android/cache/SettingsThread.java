// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.cache;

import android.os.Handler;
import io.segment.android.request.IRequester;
import io.segment.android.utils.LooperThreadWithHandler;

// Referenced classes of package io.segment.android.cache:
//            ISettingsLayer

public class SettingsThread extends LooperThreadWithHandler
    implements ISettingsLayer
{

    public SettingsThread(IRequester irequester)
    {
        requester = irequester;
    }

    public void fetch(final ISettingsLayer.SettingsCallback callback)
    {
        handler().post(new Runnable() {

            public void run()
            {
                io.segment.android.models.EasyJSONObject easyjsonobject = requester.fetchSettings();
                if(callback != null)
                {
                    ISettingsLayer.SettingsCallback settingscallback = callback;
                    boolean flag;
                    if(easyjsonobject != null)
                        flag = true;
                    else
                        flag = false;
                    settingscallback.onSettingsLoaded(flag, easyjsonobject);
                }
            }

            final SettingsThread this$0;
            private final ISettingsLayer.SettingsCallback val$callback;

            
            {
                this$0 = SettingsThread.this;
                callback = settingscallback;
                super();
            }
        }
);
    }

    private IRequester requester;

}
