// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

// Referenced classes of package com.google.android.gms.games.request:
//            a, GameRequest

public final class GameRequestBuffer extends d
{

    public GameRequestBuffer(DataHolder dataholder)
    {
        super(dataholder);
    }

    protected Object c(int i, int j)
    {
        return getEntry(i, j);
    }

    protected GameRequest getEntry(int i, int j)
    {
        return new a(zU, i, j);
    }

    protected String getPrimaryDataMarkerColumn()
    {
        return "external_request_id";
    }
}
