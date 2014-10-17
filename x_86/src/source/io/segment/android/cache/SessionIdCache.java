// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.cache;

import android.content.Context;
import io.segment.android.info.SessionId;

// Referenced classes of package io.segment.android.cache:
//            SimpleStringCache

public class SessionIdCache extends SimpleStringCache
{

    public SessionIdCache(Context context1)
    {
        super(context1, "session.id");
        context = context1;
        sessionIdLoader = new SessionId();
    }

    public String load()
    {
        return sessionIdLoader.get(context);
    }

    private Context context;
    private SessionId sessionIdLoader;
}
