// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.appstate;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

// Referenced classes of package com.google.android.gms.appstate:
//            b, AppState

public final class AppStateBuffer extends DataBuffer
{

    public AppStateBuffer(DataHolder dataholder)
    {
        super(dataholder);
    }

    public AppState get(int i)
    {
        return new b(zU, i);
    }

    public volatile Object get(int i)
    {
        return get(i);
    }
}
