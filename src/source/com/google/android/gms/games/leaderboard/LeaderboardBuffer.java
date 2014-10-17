// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            b, Leaderboard

public final class LeaderboardBuffer extends d
{

    public LeaderboardBuffer(DataHolder dataholder)
    {
        super(dataholder);
    }

    protected Object c(int i, int j)
    {
        return getEntry(i, j);
    }

    protected Leaderboard getEntry(int i, int j)
    {
        return new b(zU, i, j);
    }

    protected String getPrimaryDataMarkerColumn()
    {
        return "external_leaderboard_id";
    }
}
